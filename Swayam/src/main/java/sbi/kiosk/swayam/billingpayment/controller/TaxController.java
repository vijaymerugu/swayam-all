package sbi.kiosk.swayam.billingpayment.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.billingpayment.repository.BpRequestRepository;
import sbi.kiosk.swayam.billingpayment.repository.TaxCalInsertRepository;
import sbi.kiosk.swayam.billingpayment.repository.TaxDetailRepository;
import sbi.kiosk.swayam.billingpayment.repository.TaxRepository;
import sbi.kiosk.swayam.billingpayment.service.TaxService;
import sbi.kiosk.swayam.common.constants.ExceptionConstants;
import sbi.kiosk.swayam.common.dto.TaxCalculationDto;
import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.entity.BpRequest;
import sbi.kiosk.swayam.common.entity.InvoiceCompareDtls;
import sbi.kiosk.swayam.common.entity.TaxDetailEnity;
import sbi.kiosk.swayam.common.entity.TaxEntity;
import sbi.kiosk.swayam.common.entity.TaxSummaryEntity;
import sbi.kiosk.swayam.common.utils.ObjectMapperUtils;
import sbi.kiosk.swayam.healthmonitoring.model.BillingPaymentReport;
import sbi.kiosk.swayam.healthmonitoring.model.RfpResponse;


@RestController
public class TaxController {
	
	
	Logger logger =LoggerFactory.getLogger(TaxController.class);
	
	@Autowired
	BillingPaymentReport report;
	
	@Autowired
	TaxService taxService;
	
//	@Autowired
//	TaxService taxService2;
	
	@Autowired
	TaxRepository taxRepo;
	
	@Autowired
	TaxDetailRepository taxdetailRepo;
	
	@Autowired
	TaxCalInsertRepository insertRepo;
	
	@Autowired
	HttpSession httpSession;
	
	@Autowired
	BpRequestRepository bprequest;
	
	@RequestMapping("bp/taxCal")
	public ModelAndView taxCalculationPage(ModelAndView model, HttpSession session) {
		
		UserDto user = (UserDto) httpSession.getAttribute("userObj");
		String  circleID = null;
		String  circle = user.getCircle();
		String role = user.getRole(); 
		
		if(circle.equalsIgnoreCase("CORPORATE CENTRE")) {
			
			model.setViewName("taxCalculationCC");
			
		}else {
		circleID = (String) httpSession.getAttribute("circelID");
		//String circleID = circleRepository.findCircleId(user.getCircle());
		logger.debug("Role -- " +role +" Circle -- " +  circle + " Circle ID -- "+ circleID);
		try {
			model.addObject("circle", user.getCircle());
			model.addObject("circleId", circleID);
			model.setViewName("taxCalculation");
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		
		}
		
		/*
		 * try {
		 * 
		 * 
		 * model.setViewName("taxCalculation");
		 * 
		 * } catch (Exception e) {
		 * logger.error("Exception "+ExceptionConstants.EXCEPTION); }
		 */
		return model;
	}
	
	
	
	@RequestMapping("bp/taxSum")
	public ModelAndView taxSummaryPage(ModelAndView model, HttpSession session) {
		
		try {
			
			
			model.setViewName("taxSummary");
			
		} catch (Exception e) {			
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	
	@RequestMapping("tax/insert")
	public int taxInsert() {
		//int status = 
		return 1;
	}
	
	
	@RequestMapping(value = "taxCalculation/get", params = { "page", "size", "type", "selectedCircelId",
			"selectedStateId", "selectedQuarterId", "selectedYearId", "selectedVendorId", "selectedRfpID",
			"selectedGST", "selectedGSTType","selectedSGST","selectedCGST" }, method = RequestMethod.GET, produces = "application/json")
	public Page<TaxCalculationDto> findPaginated(HttpServletRequest request) {
		logger.info("Inside findPaginated");
		UserDto user = (UserDto) httpSession.getAttribute("userObj");
		int page = Integer.parseInt(request.getParameter("page"));
		int size = Integer.parseInt(request.getParameter("size"));
		String type = request.getParameter("type");
		String selectedCircelId = request.getParameter("selectedCircelId");
		String selectedStateId = request.getParameter("selectedStateId");
		// String quterTimePeriod= request.getParameter("quterTimePeriod");
		String quarter = request.getParameter("selectedQuarterId");
		String year = request.getParameter("selectedYearId");
		String selectedVendorId = request.getParameter("selectedVendorId");
		String selectedRfpID = request.getParameter("selectedRfpID");
		//System.out.println("IGST "+ request.getParameter("selectedGST"));
		Float selectedGST = Float.parseFloat(request.getParameter("selectedGST"));
				
		String selectedGSTType = request.getParameter("selectedGSTType");
		Float selectedSGST = Float.parseFloat(request.getParameter("selectedSGST"));
		Float selectedCGST = Float.parseFloat(request.getParameter("selectedCGST"));
		

		report.setCircle(selectedCircelId);
		report.setState(selectedStateId);
		report.setYear(year);
		report.setQuarter(quarter);
		report.setVendor(selectedVendorId);
		report.setRpfNumber(selectedRfpID);
		report.setGst(selectedGST);
		report.setGstType(selectedGSTType);
		report.setCgst(selectedCGST);
		report.setSgst(selectedSGST);

		
		
		ModelMapper mapper= new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		List<TaxCalculationDto> taxList = null;		
		List<TaxCalculationDto> taxList2 = null;		
		List<TaxDetailEnity> taxEntity = null;



		if(selectedGSTType.equalsIgnoreCase("IGST")) {
			taxList= ObjectMapperUtils.mapAll(
					taxService.getTaxCalculation(report, 1, "IGST"), TaxCalculationDto.class);
			
			taxEntity= ObjectMapperUtils.mapAll(taxList, TaxDetailEnity.class);
			  Iterator<TaxDetailEnity> iterable= taxEntity.iterator();
			  
			  TaxDetailEnity taxEntity2 = null;
			  while (iterable.hasNext()) { 
				  taxEntity2 = (TaxDetailEnity)
						  iterable.next(); 
								  
				
				  
				 taxdetailRepo.save(taxEntity2) ;
				 
				 BpRequest request2 = new BpRequest();
					
					request2.setRequestId(taxEntity2.getRequestId());
					request2.setReqType("Tax Request");
					request2.setMakersComment("");
					request2.setReqDate(new Date());
					request2.setMakerPfid(user.getPfId());
					request2.setStatus("Submitted");
					request2.setUserCircle(user.getCircle());
					
					bprequest.save(request2);
				  
				//taxRepo.save(taxEntity2);
				
			  }
			 
			
		
			
		}else {
			
			taxList = ObjectMapperUtils.mapAll(taxService.getTaxCalculation(report, 1, "CGST"),
					TaxCalculationDto.class);
			taxList2 = ObjectMapperUtils.mapAll(taxService.getTaxSGST(report, 1, "SGST"),
					TaxCalculationDto.class);
			taxList.addAll(taxList2);
			taxEntity= ObjectMapperUtils.mapAll(taxList, TaxDetailEnity.class);
			Iterator<TaxDetailEnity> iterable=  taxEntity.iterator();
			int i=0;
			TaxDetailEnity taxEntity2 =null;
			double invoiceAmt = 0;
			double penaltyAmt =0;
			double gstInvoiceAmt = 0;
			double gstPenaltyAmt =0;
			double totalGst =0;
			while (iterable.hasNext()) {
				taxEntity2 = (TaxDetailEnity) iterable.next();
				gstInvoiceAmt = gstInvoiceAmt + taxEntity2.getAmcGst();
				gstPenaltyAmt = gstPenaltyAmt + taxEntity2.getPenGst();
				totalGst = totalGst + taxEntity2.getToatalGST();
			}
			
			taxEntity2.setGstType("SGST/CGST");
			taxEntity2.setCgst(selectedCGST);
			taxEntity2.setSgst(selectedSGST);
			taxEntity2.setGst(null);
			taxEntity2.setAmcGst(gstInvoiceAmt);
			taxEntity2.setPenGst(gstPenaltyAmt);
			taxEntity2.setToatalGST(totalGst);
					
			taxdetailRepo.save(taxEntity2);
			
			 BpRequest request2 = new BpRequest();
				
				request2.setRequestId(taxEntity2.getRequestId());
				request2.setReqType("Tax Request");
				request2.setMakersComment("");
				request2.setReqDate(new Date());
				request2.setMakerPfid(user.getPfId());
				request2.setStatus("Submitted");
				request2.setUserCircle(user.getCircle());
				
				bprequest.save(request2);
		}

		
		Page<TaxCalculationDto> resultPage = new PageImpl<TaxCalculationDto>(taxList, PageRequest.of(page, size),
				taxList.size());
	
		return resultPage;
	}
	
	
	
	/*
	 * @RequestMapping(value = "taxCalculation/get", params = { "page", "size",
	 * "type", "selectedCircelId", "selectedStateId", "selectedQuarterId",
	 * "selectedYearId", "selectedVendorId", "selectedRfpID", "selectedGST",
	 * "selectedGSTType","selectedSGST","selectedCGST" }, method =
	 * RequestMethod.GET, produces = "application/json") public
	 * Page<TaxCalculationDto> findPaginated(HttpServletRequest request) {
	 * logger.info("Inside findPaginated");
	 * 
	 * int page = Integer.parseInt(request.getParameter("page")); int size =
	 * Integer.parseInt(request.getParameter("size")); String type =
	 * request.getParameter("type"); String selectedCircelId =
	 * request.getParameter("selectedCircelId"); String selectedStateId =
	 * request.getParameter("selectedStateId"); // String quterTimePeriod=
	 * request.getParameter("quterTimePeriod"); String quarter =
	 * request.getParameter("selectedQuarterId"); String year =
	 * request.getParameter("selectedYearId"); String selectedVendorId =
	 * request.getParameter("selectedVendorId"); String selectedRfpID =
	 * request.getParameter("selectedRfpID"); //System.out.println("IGST "+
	 * request.getParameter("selectedGST")); Float selectedGST =
	 * Float.parseFloat(request.getParameter("selectedGST"));
	 * 
	 * String selectedGSTType = request.getParameter("selectedGSTType"); Float
	 * selectedSGST = Float.parseFloat(request.getParameter("selectedSGST")); Float
	 * selectedCGST = Float.parseFloat(request.getParameter("selectedCGST"));
	 * 
	 * 
	 * report.setCircle(selectedCircelId); report.setState(selectedStateId);
	 * report.setYear(year); report.setQuarter(quarter);
	 * report.setVendor(selectedVendorId); report.setRpfNumber(selectedRfpID);
	 * report.setGst(selectedGST); report.setGstType(selectedGSTType);
	 * report.setCgst(selectedCGST); report.setSgst(selectedSGST);
	 * 
	 * 
	 * 
	 * ModelMapper mapper= new ModelMapper();
	 * mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	 * 
	 * List<TaxCalculationDto> taxList = null; List<TaxCalculationDto> taxList2 =
	 * null; List<TaxEntity> taxEntity = null;
	 * 
	 * 
	 * 
	 * if(selectedGSTType.equalsIgnoreCase("IGST")) { taxList=
	 * ObjectMapperUtils.mapAll( taxService.getTaxCalculation(report, 1, "IGST"),
	 * TaxCalculationDto.class);
	 * 
	 * taxEntity= ObjectMapperUtils.mapAll(taxList, TaxEntity.class);
	 * Iterator<TaxEntity> iterable= taxEntity.iterator();
	 * 
	 * TaxEntity taxEntity2 = null; while (iterable.hasNext()) { taxEntity2 =
	 * (TaxEntity) iterable.next();
	 * 
	 * //insertRepo.insert(taxEntity2); taxRepo.save(taxEntity2);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * }else {
	 * 
	 * taxList = ObjectMapperUtils.mapAll(taxService.getTaxCalculation(report, 1,
	 * "CGST"), TaxCalculationDto.class); taxList2 =
	 * ObjectMapperUtils.mapAll(taxService.getTaxSGST(report, 1, "SGST"),
	 * TaxCalculationDto.class); taxList.addAll(taxList2); taxEntity=
	 * ObjectMapperUtils.mapAll(taxList, TaxEntity.class); Iterator<TaxEntity>
	 * iterable= taxEntity.iterator(); int i=0; TaxEntity taxEntity2 =null; double
	 * invoiceAmt = 0; double penaltyAmt =0; double gstInvoiceAmt = 0; double
	 * gstPenaltyAmt =0; double totalGst =0; while (iterable.hasNext()) { taxEntity2
	 * = (TaxEntity) iterable.next(); gstInvoiceAmt = gstInvoiceAmt +
	 * taxEntity2.getAmcGst(); gstPenaltyAmt = gstPenaltyAmt +
	 * taxEntity2.getPenGst(); totalGst = totalGst + taxEntity2.getToatalGST(); }
	 * 
	 * taxEntity2.setGstType("SGST/CGST"); taxEntity2.setCgst(selectedCGST);
	 * taxEntity2.setSgst(selectedSGST); taxEntity2.setGst(null);
	 * taxEntity2.setAmcGst(gstInvoiceAmt); taxEntity2.setPenGst(gstPenaltyAmt);
	 * taxEntity2.setToatalGST(totalGst);
	 * 
	 * taxRepo.save(taxEntity2); }
	 * 
	 * 
	 * Page<TaxCalculationDto> resultPage = new PageImpl<TaxCalculationDto>(taxList,
	 * PageRequest.of(page, size), taxList.size());
	 * 
	 * return resultPage; }
	 */
	
	@RequestMapping(value = "taxSummary/get", params = { "page", "size" ,"type", "selectedCircelId", "selectedStateId","quterTimePeriod"}, method = RequestMethod.GET, produces = "application/json")
	public Page<TaxSummaryEntity> findPaginatedIS(
		      @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("type") String type, @RequestParam("selectedCircelId")
		      String selectedCircelId, @RequestParam("selectedStateId") String selectedStateId,
		      @RequestParam("quterTimePeriod") String quterTimePeriod) {
		logger.info("Inside findPaginatedTS");
	
		
		report.setCircle(selectedCircelId);
		report.setState(selectedStateId);
		report.setTimePeiod(quterTimePeriod);
	
		
		Page<TaxSummaryEntity> resultPage = null;
		
		if(selectedCircelId.equals("undefined") ||
			selectedStateId.equals("undefined") ||
			quterTimePeriod.equals("undefined") 
			) {		
			selectedCircelId="";
			selectedStateId="";
			quterTimePeriod="";
			
		}
		if(selectedStateId.equals("0"))	{
			resultPage = taxService.findPageWithoutStateIs(page, size, type, selectedCircelId, quterTimePeriod);
			
		}else{
			
			resultPage = taxService.findPageByFilterIs(page, size, type, selectedCircelId, selectedStateId, quterTimePeriod);
	}
		   
		  return resultPage;
	}
	
	
	@RequestMapping(value = "taxCal/insert", params = { "selectedCircelId",
			"selectedStateId", "selectedQuarterId", "selectedYearId", "selectedVendorId", "selectedRfpID"}, method = RequestMethod.GET, produces = "application/json")
	public int insertTaxCal(HttpServletRequest request) {
		logger.info("Inside findPaginated");

	
		String selectedCircelId = request.getParameter("selectedCircelId");
		String selectedStateId = request.getParameter("selectedStateId");	
		String quarter = request.getParameter("selectedQuarterId");
		String year = request.getParameter("selectedYearId");
		String selectedVendorId = request.getParameter("selectedVendorId");
		String selectedRfpID = request.getParameter("selectedRfpID");


	
		
		int count = taxRepo.findCount(selectedCircelId,
				selectedVendorId, selectedStateId, year,
				  quarter, selectedRfpID);
		
		
		return count;
	}
	
	
	@RequestMapping("approveTaxDetails/get")
	 public ResponseEntity<RfpResponse> formApprove(@RequestParam("reqId") int requestId,
			 @RequestParam("commnets") String commnets,HttpServletRequest req) {
		UserDto user = (UserDto) httpSession.getAttribute("userObj");
		//int statusCount = bprequest.findCountApproved(requestId);
		//System.out.println("Inside formApprove");
		String csrfToken = req.getHeader("X-CSRF-TOKEN");
		
		
		 if (csrfToken ==null || csrfToken.isEmpty()) {
			 req.getSession().invalidate();
			 return null;
			 
		 }else  if(req.getSession() !=null && csrfToken.equals(req.getSession().getAttribute("csrfToken"))) {
			 httpSession.setAttribute("csrfToken", UUID.randomUUID().toString());
		 }else{
			 req.getSession().invalidate();
			 return null;
		 }
		 
		 
		 String role = user.getRole();
		  if(role.equalsIgnoreCase("BC") == false) { 
			  
			  return ResponseEntity.ok(new RfpResponse("Authorization Required")); 
			  }
		 
		
		 int approved = 0;
			approved = bprequest.findApproved(requestId);
		if(approved>0){
			
			return ResponseEntity.ok(new 
	    			RfpResponse("Request Id "+ requestId +" Already Approved"));
		}
		
		int rejected =0;
		rejected= bprequest.findRejected(requestId);
		//System.out.println("rejected " +rejected);
		
		if(rejected>0) {
			//httpSession.setAttribute("csrfToken", UUID.randomUUID().toString());
			return ResponseEntity.ok(new 
	    			RfpResponse("Canot approve request Id "+ requestId +" Already Rejected"));
		}
		
		String pfid = user.getPfId();
		
		
		List<TaxDetailEnity> list = taxdetailRepo.findAllByRequestId(requestId);
		

		List<TaxEntity> taxEntity= ObjectMapperUtils.mapAll(list, TaxEntity.class);
		
		TaxEntity entity = taxEntity.get(0);
		try {
			 taxRepo.save(entity);
			 bprequest.updateApprove(requestId, new Date(), commnets, pfid);
			
		} catch (Exception e) {
			return ResponseEntity.ok(new RfpResponse("Request Id  "+ requestId
					  +" failed to updated"));
		}
		
		return ResponseEntity.ok(new RfpResponse("Request Id  "+ requestId
				  +" updated successfully"));
		
	}
	
	
	
	@RequestMapping("rejectTaxDetails/get")
	 public ResponseEntity<RfpResponse> formReject(
			 @RequestParam("reqId") int requestId, @RequestParam("commnets") String commnets,HttpServletRequest req) {
		UserDto user = (UserDto) httpSession.getAttribute("userObj");
		String csrfToken = req.getHeader("X-CSRF-TOKEN");
		 if (csrfToken ==null || csrfToken.isEmpty()) {
			 req.getSession().invalidate();
			 return null;
			 
		 }else  if(req.getSession() !=null && csrfToken.equals(req.getSession().getAttribute("csrfToken"))) {
			 httpSession.setAttribute("csrfToken", UUID.randomUUID().toString());
		 }else{
			 req.getSession().invalidate();
			 return null;
		 }
		 
		 	String role = user.getRole();
		 	if(role.equalsIgnoreCase("BC") == false) { 
			  return ResponseEntity.ok(new RfpResponse("Authorization Required")); 
			  }
		
		
		
		int rejected =0;
		rejected= bprequest.findRejected(requestId);
		
		if(rejected>0)  {
			return ResponseEntity.ok(new 
	    			RfpResponse("Request Id "+ requestId +" Already Rejected"));
		}
		
		int approved = 0;
		approved = bprequest.findApproved(requestId);
		
		if(approved>0) {
			return ResponseEntity.ok(new 
	    			RfpResponse("Can not reject request Id "+ requestId +" Already Approved"));
		}
		
		String pfid = user.getPfId();
		
		bprequest.updateReject(requestId, new Date(), commnets, pfid);
		
		return ResponseEntity.ok(new 
 			RfpResponse("Request Id  "+ requestId +" Rejected"));
	}
	
	
	@RequestMapping("updateTaxComment/get")
	 public ResponseEntity<RfpResponse> updateMakersComment(
			 @RequestParam("reqId") int requestId, @RequestParam("commnets") String commnets,HttpServletRequest req) {
		UserDto user = (UserDto) httpSession.getAttribute("userObj");
		String csrfToken = req.getHeader("X-CSRF-TOKEN");
		 if (csrfToken ==null || csrfToken.isEmpty()) {
			 req.getSession().invalidate();
			 return null;
			 
		 }else  if(req.getSession() !=null && csrfToken.equals(req.getSession().getAttribute("csrfToken"))) {
			 httpSession.setAttribute("csrfToken", UUID.randomUUID().toString());
		 }else{
			 req.getSession().invalidate();
			 return null;
		 }
		
		String pfid = user.getPfId();
		
		String role = user.getRole();
		  if(role.equalsIgnoreCase("BM") == false) { 
			  return ResponseEntity.ok(new RfpResponse("Authorization Required"));  
			  }
		
		bprequest.updateComment(requestId,commnets, pfid);
		
		return ResponseEntity.ok(new 
 			RfpResponse("Request Id  "+ requestId +" comment updated"));
	}
	
	
	

}
