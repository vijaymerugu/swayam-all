package sbi.kiosk.swayam.billingpayment.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import sbi.kiosk.swayam.billingpayment.repository.BpRequestRepository;
import sbi.kiosk.swayam.billingpayment.repository.FormDetailRepository;
import sbi.kiosk.swayam.billingpayment.repository.SanctionFormRequestRepository;
import sbi.kiosk.swayam.billingpayment.repository.TaxRepository;
import sbi.kiosk.swayam.billingpayment.repository.VendorRepository;
import sbi.kiosk.swayam.billingpayment.service.SanctionRequestServiceImpl;
import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.entity.BpRequest;
import sbi.kiosk.swayam.common.entity.PurchaseOrder;
import sbi.kiosk.swayam.common.entity.SanctionPdfInfo;
import sbi.kiosk.swayam.common.entity.SanctionRequestEntity;
import sbi.kiosk.swayam.common.entity.TaxEntity;
import sbi.kiosk.swayam.common.repository.UserRepository;
import sbi.kiosk.swayam.common.utils.ObjectMapperUtils;
import sbi.kiosk.swayam.healthmonitoring.model.RfpResponse;

@RestController
public class SanctionRequest {
	
	Logger logger = LoggerFactory.getLogger(SanctionRequest.class);
	
	@Autowired
	BpRequestRepository bprequest;
	
	@Autowired
	SanctionRequestServiceImpl sanctionRequestservice;
	
	@Autowired
	SanctionFormRequestRepository requestRepo;
	
	@Autowired
	HttpSession httpSession;
	
	@Autowired
	FormDetailRepository detailRepo;
	
	@Autowired
	TaxRepository taxDetailRepo;
	
	@Autowired
	SanctionFormRequestRepository sanRequestRepo;
	
	@Autowired
	VendorRepository vendRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Value("${report.path}")
	private String reportPath;

	@Value("${jrxml.path}")
	private String jrxmlPath;
	
	@RequestMapping("bp/snrequest")
	public ModelAndView sanctionRequestPage(ModelAndView model, HttpSession session) {
		
		UserDto user = (UserDto) httpSession.getAttribute("userObj");
		
		Map<String, Integer> mapStatusCount = null;
		mapStatusCount = sanctionRequestservice.findAllCountRequestStatus();
		
		String role = userRepo.findRoleByPfId(user.getPfId());
		
		if (mapStatusCount != null && !mapStatusCount.isEmpty()) {
			model.addObject("mapStatusCount", mapStatusCount);
		}
		
		try {
			
			if(role.equalsIgnoreCase("BM")) {
				
				//model.setViewName("SanctionRequestMakerForm");
				model.setViewName("SanctionRequest");
				
			}else if(role.equalsIgnoreCase("BC")) {
				//model.setViewName("SanctionRequestApprovalForm");
				
				model.setViewName("SanctionRequestChecker");
			}
			
//			model.setViewName("SanctionRequest");
		} catch (Exception e) {			
			//logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	

	@RequestMapping(value = "sannctionRequest/get", params = { "page", "size" ,"type"}, method = RequestMethod.GET)
	public Page<BpRequest> findPaginated(
		      @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("type") String type) {
		//logger.info("Inside findPaginated");  
		Page<BpRequest> resultPage = null;
		
		if(type!=null && !type.isEmpty() && !type.equals("undefined")){
			
			// resultPage = bprequest.findAll(PageRequest.of(page, size));
			if(type.equalsIgnoreCase("Approved") 
					|| type.equalsIgnoreCase("Rejected") 
					|| type.equalsIgnoreCase("Submitted")) {
			
				resultPage = bprequest.findPageByStatus(type, PageRequest.of(page, size));
			}else {
				
				resultPage = bprequest.findPageType(type, PageRequest.of(page, size));
			}
		}else {
			
			 resultPage = bprequest.findAll(PageRequest.of(page, size));
			 
			 
		}
		 return resultPage;
	}
	
	
	
	@RequestMapping("bp/getFormRequest")
	public ModelAndView formRequestPage(ModelAndView model, HttpSession session) {
		
		try {
			
			
			model.setViewName("SanctionRequestForm");
			
		} catch (Exception e) {			
			//logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	
	
	@RequestMapping("bp/getSanctionApprovalForm")
	public ModelAndView formForViewApproveReject(ModelAndView model, @RequestParam("requestId") int requestId) {
		UserDto user = (UserDto) httpSession.getAttribute("userObj");
		Map<String, Object> mapFormDetail = null;
		mapFormDetail = sanctionRequestservice.findSRApprovalForm(requestId);
		
		if (mapFormDetail != null && !mapFormDetail.isEmpty()) {
			model.addObject("mapFormDetail", mapFormDetail);
		}
		
		String role = userRepo.findRoleByPfId(user.getPfId());
		
		try {
			if(role.equalsIgnoreCase("BM")) {
				
				model.setViewName("SanctionRequestMakerForm");
				
			}else if(role.equalsIgnoreCase("BC")) {
				model.setViewName("SanctionRequestApprovalForm");
			}
			
			
			
			
		} catch (Exception e) {			
			//logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	
	
	
	
	@RequestMapping("approveDetails/get")
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
		 
		 
		 String role = userRepo.findRoleByPfId(user.getPfId());
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
		
		bprequest.updateApprove(requestId, new Date(), commnets, pfid);
		//httpSession.setAttribute("csrfToken", UUID.randomUUID().toString());
		return ResponseEntity.ok(new 
    			RfpResponse("Request Id  "+ requestId +" Approved Successfully"));
		
	}
	
	
	@RequestMapping("formDetails/get")
	 public int kisokCount(@RequestParam("SelectedVendorId") String SelectedVendorId,
			 @RequestParam("SelectedStateId") String SelectedStateId,
			 @RequestParam("SelectedCircelId") String SelectedCircelId,
			 @RequestParam("selectedYear") String selectedYear,
			 @RequestParam("selectedQtr") String selectedQtr) {
		
//		System.out.println("SelectedVendorId " + SelectedVendorId);
//		System.out.println("SelectedStateId " + SelectedStateId);
//		System.out.println("SelectedCircelId " + SelectedCircelId);
//		System.out.println("selectedYear " + selectedYear);
//		System.out.println("selectedQtr " + selectedQtr);
		
//		int count = detailRepo.findKioskCount(SelectedCircelId, SelectedStateId, SelectedVendorId);
		int count = detailRepo.findCount(SelectedCircelId, SelectedStateId, 
				SelectedVendorId,selectedYear,selectedQtr);
		//System.out.println("Kiosk Count "+ count);
		
				return count;
	}
	
	@RequestMapping("invoiceDetails/get")
		public ResponseEntity<TaxEntity> getTaxDetail(@RequestParam("SelectedVendorId") String SelectedVendorId,
			 @RequestParam("SelectedStateId") String SelectedStateId,
			 @RequestParam("SelectedCircelId") String SelectedCircelId,
			 @RequestParam("SelectedYearId") String SelectedYearId,
			 @RequestParam("SelectedQuarterId") String SelectedQuarterId) {
		
		
		List<TaxEntity> entity = taxDetailRepo.getTaxDetail(SelectedCircelId,
				SelectedVendorId, SelectedStateId, SelectedYearId, SelectedQuarterId);
		Iterator<TaxEntity> iterator = entity.iterator();
		
		TaxEntity taxEntity = null;
		while (iterator.hasNext()) {
			taxEntity = (TaxEntity) iterator.next();
			
		}
		
		//System.out.println("tax entity "+ taxEntity);
		
		return ResponseEntity.ok(taxEntity);
						
	}
	
	
	@RequestMapping("rejectDetails/get")
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
		 
		 	String role = userRepo.findRoleByPfId(user.getPfId());
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
	
	
	@RequestMapping("updateComment/get")
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
		
		String role = userRepo.findRoleByPfId(pfid);
		  if(role.equalsIgnoreCase("BM") == false) { 
			  return ResponseEntity.ok(new RfpResponse("Authorization Required"));  
			  }
		
		bprequest.updateComment(requestId,commnets, pfid);
		
		return ResponseEntity.ok(new 
  			RfpResponse("Request Id  "+ requestId +" comment updated"));
	}
	
	
	@RequestMapping(value = "bp/sanctionInsert", method = RequestMethod.POST)
	//@PreAuthorize("hasPermission('AddRFP','CREATE')")
    public ResponseEntity<RfpResponse> addSanctionRequest(@RequestBody String payload,
    		HttpServletRequest req) throws JsonParseException, JsonMappingException, IOException {
			ObjectMapper objectMapper = new ObjectMapper();
			//System.out.println("Inside addSanctionRequest");
			byte[] decodedBytes = Base64.getMimeDecoder().decode(payload);
			
			String decodedString = new String(decodedBytes);
			//System.out.println("Decoded String " + decodedString);
			
			
			@Valid
			SanctionRequestEntity request = objectMapper.readValue(decodedString, SanctionRequestEntity.class);
			
			
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
			 
			 String role = userRepo.findRoleByPfId(user.getPfId());
			  if(role.equalsIgnoreCase("BM") == false) { 
				  return ResponseEntity.ok(new RfpResponse("Authorization Required"));  
				  }
			  
					/*
					 * if(result.hasErrors()) { //System.out.println("Error " +
					 * result.getAllErrors()); logger.error("Validation Failed" +
					 * result.getAllErrors()); return ResponseEntity.ok(new
					 * RfpResponse("Server side validation fail"));
					 * 
					 * }
					 */
			
			requestRepo.save(request);
			
			BpRequest request2 = new BpRequest();
			
			request2.setRequestId(request.getRequestId());
			request2.setReqType("Sanction Note");
			request2.setReqDate(new Date());
			request2.setMakerPfid(user.getPfId());
			request2.setStatus("Submitted");
			
			bprequest.save(request2);
        		
        	
        	//logger.info("Request Id "+ request.getRequestId()  +" Added Successfully");
        	return ResponseEntity.ok(new 
        			RfpResponse("Request Id  "+request2.getRequestId()  +" submitted Successfully"));
    }
	
	
	
//	@RequestMapping(value = "bp/sanctionInsert", method = RequestMethod.POST)
//	//@PreAuthorize("hasPermission('AddRFP','CREATE')")
//    public ResponseEntity<RfpResponse> addSanctionRequest(@Valid @RequestBody SanctionRequestEntity request,
//    		HttpServletRequest req, BindingResult result) {
//			System.out.println("Inside addSanctionRequest ");
//			UserDto user = (UserDto) httpSession.getAttribute("userObj");
//			
//			String csrfToken = req.getHeader("X-CSRF-TOKEN");
//			System.out.println("Inside addSanctionRequest " + csrfToken);
//			 if (csrfToken ==null || csrfToken.isEmpty()) {
//				 req.getSession().invalidate();
//				 return null;
//				 
//			 }else  if(req.getSession() !=null && csrfToken.equals(req.getSession().getAttribute("csrfToken"))) {
//				 httpSession.setAttribute("csrfToken", UUID.randomUUID().toString());
//			 }else{
//				 req.getSession().invalidate();
//				 return null;
//			 }
//			 
//			 String role = userRepo.findRoleByPfId(user.getPfId());
//			  if(role.equalsIgnoreCase("BM") == false) { 
//				  return ResponseEntity.ok(new RfpResponse("Authorization Required"));  
//				  }
//			  
//			  if(result.hasErrors()) {
//      			//System.out.println("Error " + result.getAllErrors());
//      			logger.error("Validation Failed" + result.getAllErrors());
//      			return ResponseEntity.ok(new RfpResponse("Server side validation fail"));
//      			
//      		}
//			
//			requestRepo.save(request);
//			
//			BpRequest request2 = new BpRequest();
//			
//			request2.setRequestId(request.getRequestId());
//			request2.setReqType("Sanction Note");
//			request2.setReqDate(new Date());
//			request2.setMakerPfid(user.getPfId());
//			request2.setStatus("Submitted");
//			
//			bprequest.save(request2);
//        		
//        	
//        	//logger.info("Request Id "+ request.getRequestId()  +" Added Successfully");
//        	return ResponseEntity.ok(new 
//        			RfpResponse("Request Id  "+request2.getRequestId()  +" submitted Successfully"));
//    }
//	
	
	
	@RequestMapping(value = "generate/sanctionPdf",params = { "requestId"}, method = RequestMethod.GET)
	public ResponseEntity<RfpResponse> genearteSanctionRequest(@RequestParam("requestId")int requestId)
			throws Exception {
		
		logger.info("Inside genearteSanctionRequest");
		
		List<SanctionRequestEntity>  sanctionRequest = 
				sanRequestRepo.findAllByRequestId(requestId);
		
		logger.info("sanctionRequest " + sanctionRequest);
		
		List<SanctionPdfInfo>  sanctionPdfDetail = null;
		
		sanctionPdfDetail = ObjectMapperUtils.mapAll(sanctionRequest, SanctionPdfInfo.class);
		
		Iterator<SanctionPdfInfo> iterator = sanctionPdfDetail.iterator();
		SanctionPdfInfo sanctionRequestEntity =null;
		while (iterator.hasNext()) {
			sanctionRequestEntity = (SanctionPdfInfo) iterator.next();
			
		}
		Double cgstInvoiceAmt=null;
		Double sgstInvoiceAmt=null;
		Double igstInvoiceAmt=null;
		Double cgstPenAmt=null;
		Double sgstPenAmt=null;
		Double igstPenAmt=null;
		Double totalTax=null;
		Double netAmount=null;
		Double totalamountAfterPenalty=null;
		
		if((sanctionRequestEntity.getGstType()).equals("SGST/CGST")) {
			
			cgstInvoiceAmt = (sanctionRequestEntity.getInvoiceAmt() * sanctionRequestEntity.getCgst())/100;
			sgstInvoiceAmt = (sanctionRequestEntity.getInvoiceAmt() * sanctionRequestEntity.getSgst())/100;
			
			cgstPenAmt = (sanctionRequestEntity.getPenaltyAmt() * sanctionRequestEntity.getCgst())/100;
			sgstPenAmt = (sanctionRequestEntity.getPenaltyAmt() * sanctionRequestEntity.getSgst())/100;
			
			
			
			sanctionRequestEntity.setCgstInvoiceAmt(cgstInvoiceAmt);
			sanctionRequestEntity.setSgstInvoiceAmt(sgstInvoiceAmt);
			
			sanctionRequestEntity.setCgstPenAmt(cgstPenAmt);
			sanctionRequestEntity.setSgstPenAmt(sgstPenAmt);
			
			sanctionRequestEntity.setGstInvoiceAmt(cgstInvoiceAmt+ sgstInvoiceAmt);
			sanctionRequestEntity.setGstPenaltyAmt(cgstPenAmt+sgstPenAmt);
			
		}else if((sanctionRequestEntity.getGstType()).equals("IGST")){
			igstInvoiceAmt = (sanctionRequestEntity.getInvoiceAmt() * sanctionRequestEntity.getIgst())/100;
			igstPenAmt = (sanctionRequestEntity.getPenaltyAmt() * sanctionRequestEntity.getIgst())/100;
			
			sanctionRequestEntity.setIgstInvoiceAmt(igstInvoiceAmt);
			sanctionRequestEntity.setIgstPenAmt(igstPenAmt);
			
			
			sanctionRequestEntity.setGstInvoiceAmt(igstInvoiceAmt);
			sanctionRequestEntity.setGstPenaltyAmt(igstPenAmt);
			
			
		}
		
		
		
		
		
		
		
		
		
		/*
		 * if (250000 < sanctionRequestEntity.getInvoiceAmt() &&
		 * sanctionRequestEntity.getInvoiceAmt() < 1000000) {
		 * 
		 * sanctionRequestEntity.setGstTdsAmt2(sanctionRequestEntity.getGstTdsAmt());
		 * 
		 * } else if (sanctionRequestEntity.getInvoiceAmt() > 1000000) {
		 * 
		 * 
		 * sanctionRequestEntity.setGstTdsAmt3(sanctionRequestEntity.getGstTdsAmt()); }
		 */
		
		
		if(sanctionRequestEntity.getCreditEntry()==null) {
			
			sanctionRequestEntity.setPenaltyWithGst(sanctionRequestEntity.getPenaltyAmt()
					+ sanctionRequestEntity.getGstPenaltyAmt());
			
			if (250000 < sanctionRequestEntity.getInvoiceAmt() && sanctionRequestEntity.getInvoiceAmt() < 1000000) {

				sanctionRequestEntity.setGstTdsAmt2(sanctionRequestEntity.getGstTdsAmt());
			
			} else if (sanctionRequestEntity.getInvoiceAmt() > 1000000) {

				
				sanctionRequestEntity.setGstTdsAmt3(sanctionRequestEntity.getGstTdsAmt());
			}else if (sanctionRequestEntity.getInvoiceAmt()<=250000) {
				
			}
			
			
			netAmount = sanctionRequestEntity.getInvoiceAmt()+ sanctionRequestEntity.getGstInvoiceAmt();
			totalamountAfterPenalty = netAmount - sanctionRequestEntity.getPenaltyAmt() - 
					sanctionRequestEntity.getGstPenaltyAmt() - sanctionRequestEntity.getTdsAmt()
					- sanctionRequestEntity.getGstTdsAmt();
			
		}else {
			
			sanctionRequestEntity.setPenaltyWithGst(sanctionRequestEntity.getPenaltyAmt()+sanctionRequestEntity.getGstPenaltyAmt());
			
			sanctionRequestEntity.setNetPaybaleAmount(sanctionRequestEntity.getInvoiceAmt()-
					sanctionRequestEntity.getPenaltyAmt());
			
			sanctionRequestEntity.setNetGst(sanctionRequestEntity.getGstInvoiceAmt()-
					sanctionRequestEntity.getGstPenaltyAmt());
			
			if (250000 < sanctionRequestEntity.getNetPaybaleAmount() && sanctionRequestEntity.getNetPaybaleAmount() < 1000000) {

				sanctionRequestEntity.setGstTdsAmt2(sanctionRequestEntity.getGstTdsAmt());
			
			} else if (sanctionRequestEntity.getInvoiceAmt() > 1000000) {

				
				sanctionRequestEntity.setGstTdsAmt3(sanctionRequestEntity.getGstTdsAmt());
			}
			
			netAmount = sanctionRequestEntity.getInvoiceAmt()+ sanctionRequestEntity.getGstInvoiceAmt();
			totalamountAfterPenalty = sanctionRequestEntity.getNetPaybaleAmount() 
									+ sanctionRequestEntity.getNetGst()
									- sanctionRequestEntity.getTdsAmt() 
									- sanctionRequestEntity.getGstTdsAmt();
			
			
		}
		
		
				
		/*
		 * netAmount = sanctionRequestEntity.getInvoiceAmt()+
		 * sanctionRequestEntity.getGstInvoiceAmt(); totalamountAfterPenalty = netAmount
		 * - sanctionRequestEntity.getPenaltyAmt() -
		 * sanctionRequestEntity.getGstPenaltyAmt() - sanctionRequestEntity.getTdsAmt()
		 * - sanctionRequestEntity.getGstTdsAmt();
		 */
				
		sanctionRequestEntity.setTotalamountAfterPenalty(totalamountAfterPenalty);
		sanctionRequestEntity.setAmtWords(SanctionRequest.convertToIndianCurrency(totalamountAfterPenalty.toString()));
		
		sanctionRequestEntity.setNetAmount(netAmount);
		
		sanctionRequestEntity.setState(sanctionRequestEntity.getState().toUpperCase());
		
		
		
//		String s = "मेसर्स लिपि डाटा सिस्टम्स लिमिटेड";
//		String s1 = "सीएमएस डाटा सिस्टम्स लिमिटेड";
//		String s2 = "फोर्ब्सडाटा सिस्टम्स लिमिटेड";
		
		if(sanctionRequestEntity.getVendor().equalsIgnoreCase("CMS")) {
			sanctionRequestEntity.setHindiVendor("सीएमएस  सिस्टम्स लिमिटेड");
			
		}else if(sanctionRequestEntity.getVendor().equalsIgnoreCase("LIPI")) {
			sanctionRequestEntity.setHindiVendor("मेसर्स लिपि डाटा सिस्टम्स लिमिटेड");
		}else {
			sanctionRequestEntity.setHindiVendor("फोर्ब्स  सिस्टम्स लिमिटेड");
		}


		//System.out.println("Hindi Data " + s);
		
		
		
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		String circularDate= dateFormat.format(sanctionRequestEntity.getCircularDate());
		
		sanctionRequestEntity.setCircuDt(circularDate);
		
		logger.info("sanctionRequestEntity " + sanctionRequestEntity);
		String vendorFullName = vendRepo.findVendorFullName(sanctionRequestEntity.getVendor());
		logger.info("vendorFullName " + vendorFullName);
		sanctionRequestEntity.setVendorFullName(vendorFullName);
		logger.info("sanctionRequestEntity with vendor " + sanctionRequestEntity);
		
		
		
		
		
		List<SanctionPdfInfo> list = new ArrayList<SanctionPdfInfo>();
		list.add(sanctionRequestEntity);
		JasperReport jasperReport = null;
		JRBeanCollectionDataSource source = null;
		JasperPrint jasperPrint = null;
		String filename = null;
		jrxmlPath = jrxmlPath.replaceAll(">", "");
		reportPath = reportPath.replaceAll(">", "");
		
		
		
		File file =null;
		if((sanctionRequestEntity.getManualEntry()==null || sanctionRequestEntity.getManualEntry().isEmpty())
				&&(sanctionRequestEntity.getCreditEntry()==null || sanctionRequestEntity.getCreditEntry().isEmpty())) {
			
			file = ResourceUtils.getFile(jrxmlPath + "SanctionNoteWithoutMC.jrxml");
			
		}else if(sanctionRequestEntity.getManualEntry()==null || sanctionRequestEntity.getManualEntry().isEmpty()) {
			
			file = ResourceUtils.getFile(jrxmlPath + "SanctionNoteWithC.jrxml");
			
		}else if(sanctionRequestEntity.getCreditEntry()==null || sanctionRequestEntity.getCreditEntry().isEmpty()) {
			
			file = ResourceUtils.getFile(jrxmlPath + "SanctionNoteWithM.jrxml");
			
		}else {
			file = ResourceUtils.getFile(jrxmlPath + "SanctionNote.jrxml");
		}
		
		
		//file = ResourceUtils.getFile(jrxmlPath + "SanctionNote.jrxml");
		
		
		InputStream input = new FileInputStream(file);
		jasperReport = JasperCompileManager.compileReport(input);
		source = new JRBeanCollectionDataSource(list);
		Map<String, Object> parameters = new HashMap<>();
		jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
		String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
		//filename = "SanctionNote_"+timeStamp+".pdf";
		
		String quarter= sanctionRequestEntity.getQuarter();
		String finacialYear= sanctionRequestEntity.getYear();
			
		if(quarter.equalsIgnoreCase("Q1")) {
			String Q1StartDate =  "0104"+finacialYear.substring(0, 4);
			//System.out.println("Q1StartDate "+ Q1StartDate);
			String Q1LastDate =  "3006"+finacialYear.substring(0, 4);
			//System.out.println("Q1LastDate "+ Q1LastDate);
			filename = "Request_"+Q1StartDate+"_"+Q1LastDate+".pdf";
			//System.out.println("FileName "+ filename);
			
		}else if(quarter.equalsIgnoreCase("Q2")) {
			String Q2StartDate =  "0107"+finacialYear.substring(0, 4);
			String Q2LastDate =  "3009"+finacialYear.substring(0, 4);
			filename = "Request_"+Q2StartDate+"_"+Q2LastDate+".pdf";
		}else if(quarter.equalsIgnoreCase("Q3")) {
			String Q3StartDate =  "0110"+finacialYear.substring(0, 4);
			String Q3LastDate =  "3112"+finacialYear.substring(0, 4);
			
			filename = "Request_"+Q3StartDate+"_"+Q3LastDate+".pdf";
			
		}else if(quarter.equalsIgnoreCase("Q4")) {
			String Q4StartDate =  "0101"+finacialYear.substring(5);
			String Q4LastDate =  "3103-"+finacialYear.substring(5);
			filename = "Request_"+Q4StartDate+"_"+Q4LastDate+".pdf";
		}else {
			logger.info("No Quater Period selected");
		}
		
		
		
		
		
		
		JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
	
		
		
		
		return ResponseEntity.ok(new 
    			RfpResponse(filename));
		
	
	}
	
	
	public static String convertToIndianCurrency(String num) {
        BigDecimal bd = new BigDecimal(num);
        long number = bd.longValue();
        long no = bd.longValue();
        int decimal = (int) (bd.remainder(BigDecimal.ONE).doubleValue() * 100);
        int digits_length = String.valueOf(no).length();
        int i = 0;
        ArrayList<String> str = new ArrayList<>();
        HashMap<Integer, String> words = new HashMap<>();
        words.put(0, "");
        words.put(1, "One");
        words.put(2, "Two");
        words.put(3, "Three");
        words.put(4, "Four");
        words.put(5, "Five");
        words.put(6, "Six");
        words.put(7, "Seven");
        words.put(8, "Eight");
        words.put(9, "Nine");
        words.put(10, "Ten");
        words.put(11, "Eleven");
        words.put(12, "Twelve");
        words.put(13, "Thirteen");
        words.put(14, "Fourteen");
        words.put(15, "Fifteen");
        words.put(16, "Sixteen");
        words.put(17, "Seventeen");
        words.put(18, "Eighteen");
        words.put(19, "Nineteen");
        words.put(20, "Twenty");
        words.put(30, "Thirty");
        words.put(40, "Forty");
        words.put(50, "Fifty");
        words.put(60, "Sixty");
        words.put(70, "Seventy");
        words.put(80, "Eighty");
        words.put(90, "Ninety");
        String digits[] = {"", "Hundred", "Thousand", "Lakh", "Crore"};
        while (i < digits_length) {
            int divider = (i == 2) ? 10 : 100;
            number = no % divider;
            no = no / divider;
            i += divider == 10 ? 1 : 2;
            if (number > 0) {
                int counter = str.size();
                String plural = (counter > 0 && number > 9) ? "s" : "";
                String tmp = (number < 21) ? words.get(Integer.valueOf((int) number)) + " " + digits[counter] + plural : words.get(Integer.valueOf((int) Math.floor(number / 10) * 10)) + " " + words.get(Integer.valueOf((int) (number % 10))) + " " + digits[counter] + plural;                
                str.add(tmp);
            } else {
                str.add("");
            }
        }
 
        Collections.reverse(str);
        String Rupees = String.join(" ", str).trim();
 
        String paise = (decimal) > 0 ? " And Paise " + words.get(Integer.valueOf((int) (decimal - decimal % 10))) + " " + words.get(Integer.valueOf((int) (decimal % 10))) : "";
        return "Rupees " + Rupees + paise + " Only";
    }
	
	

}
