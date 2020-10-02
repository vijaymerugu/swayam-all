package sbi.kiosk.swayam.billingpayment.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import sbi.kiosk.swayam.billingpayment.repository.BranchStateRepository;
import sbi.kiosk.swayam.billingpayment.repository.InvoiceCorrectionRepository;
import sbi.kiosk.swayam.billingpayment.repository.RfpRepository;
import sbi.kiosk.swayam.billingpayment.repository.VendorRepository;
import sbi.kiosk.swayam.billingpayment.service.BillingPenaltyService;
import sbi.kiosk.swayam.billingpayment.service.InvoiceCompareServices;
import sbi.kiosk.swayam.billingpayment.service.InvoiceSummaryServices;
import sbi.kiosk.swayam.common.constants.ExceptionConstants;
import sbi.kiosk.swayam.common.dto.InvoiceSummaryDto;
import sbi.kiosk.swayam.common.entity.BillingPenaltyEntity;
import sbi.kiosk.swayam.common.entity.InvoiceCompare;
import sbi.kiosk.swayam.common.entity.InvoiceGeneration;
import sbi.kiosk.swayam.common.repository.BillingCircleRepository;
import sbi.kiosk.swayam.healthmonitoring.model.BillingPaymentReport;
import sbi.kiosk.swayam.healthmonitoring.model.InvoiceUpdateReposne;



@RestController
public class BillingPenaltyController {
	
	Logger logger =  LoggerFactory.getLogger(BillingPenaltyController.class);
	
	@Autowired
	BillingCircleRepository circleRepository;
	
	@Autowired
	BranchStateRepository stateRepository;
	
	@Autowired
	VendorRepository vendorRepository;
	
	@Autowired
	BillingPenaltyService billingPenaltyService;
	
	@Autowired
	InvoiceCompareServices invoiceCompareService;
	
	@Autowired
	InvoiceCorrectionRepository invoiceCorrectionRepository;
	
	@Autowired
	RfpRepository rfpRepository;
	
	
	
	/*
	 * @Autowired InvoiceSummaryRepository invoiceSummaryRepository;
	 */
	
	@Autowired
	InvoiceSummaryServices isservices;
	
	
	@Autowired
	BillingPaymentReport report;
	
	@RequestMapping("bp/billingPenalty")
	public ModelAndView billingPenaltyPage(ModelAndView model, HttpSession session) {
		
		try {
			
			
			model.setViewName("billingPenalty");
			
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	
	@RequestMapping("bp/invoiceGenaration")
	public ModelAndView invoiceGenarationPage(ModelAndView model, HttpSession session) {
		
		try {
			
			
			model.setViewName("invoiceGeneration");
			
		} catch (Exception e) {
			
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	
	
	@RequestMapping("bp/invoiceCompare")
	public ModelAndView invoiceComparePage(ModelAndView model, HttpSession session) {
		
		try {
			
			
			model.setViewName("invoiceCompare");
			
		} catch (Exception e) {
		
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	
	@RequestMapping("bp/invoiceSummary")
	public ModelAndView invoiceSummaryPage(ModelAndView model, HttpSession session) {
		
		try {
			
			
			model.setViewName("invoiceSummary");
			
		} catch (Exception e) {
			
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	
	
	
	
	@RequestMapping(value = "bp/getcircle", method = RequestMethod.GET)
	public ResponseEntity<?> getCircles(){
		
		//circleRepo.findAll();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		 String json = gson.toJson(circleRepository.findAll());
		//logger.info("circles "+ json);
		 
		
		return ResponseEntity.ok(json);
		
		
	}
	
	
	
	@RequestMapping(value = "bp/getstate", method = RequestMethod.GET)
	public ResponseEntity<?> getState(@RequestHeader(value="circleId") String circleID){
		
		//circleRepo.findAll();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		 String json = gson.toJson(stateRepository.findByCirclehCode(circleID));
		//logger.info("cites "+ json);
		 
		
		return ResponseEntity.ok(json);
		
		
	}
	
	@RequestMapping(value = "bp/getVendor", method = RequestMethod.GET)
	public ResponseEntity<?> getVendors(){
		
		//circleRepo.findAll();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(vendorRepository.findAll());
		//logger.info("cites "+ json);
	
		return ResponseEntity.ok(json);
		
		
	}
	
	@RequestMapping(value = "bp/getRfpId", method = RequestMethod.GET)
	public ResponseEntity<?> getRfIds(){
		//System.out.println("Inside getRfIds");
		String json =null;
	try {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		json = gson.toJson(rfpRepository.findAll());
		//logger.info("RfpID "+ json);
	}catch (Exception e) {
		logger.error("Exception "+ExceptionConstants.EXCEPTION);
	}
		 
		
		return ResponseEntity.ok(json);
		
		
	}
	
	@RequestMapping(value = "billingPenalty/get", params = { "page", "size" ,"type", "selectedCircelId", "selectedStateId","quterTimePeriod","selectedVendorId","selectedRfpID"}, method = RequestMethod.GET, produces = "application/json")
	public Page<BillingPenaltyEntity> findPaginated(
		      @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("type") String type, @RequestParam("selectedCircelId")
		      String selectedCircelId, @RequestParam("selectedStateId") String selectedStateId,
		      @RequestParam("quterTimePeriod") String quterTimePeriod,@RequestParam("selectedVendorId") String selectedVendorId,@RequestParam("selectedRfpID") String selectedRfpID) {
		logger.info("Inside findPaginated"); 
//		logger.info("type=================="+type);
//		logger.info("selectedCircelId--- "+selectedCircelId);
//		logger.info("selectedStateId---- "+selectedStateId);
//		logger.info("quterTimePeriod---- "+quterTimePeriod);
//		logger.info("selectedVendorId--- "+selectedVendorId);
//		logger.info("selectedRfpID--- "+selectedRfpID);
	
		
	
		
		/*
		 * String quarter= quterTimePeriod.substring(0, 2); String finacialYear=
		 * quterTimePeriod.substring(3); System.out.println("Quater "+ quarter);
		 * System.out.println("finacialYear "+ finacialYear);
		 */
		
		report.setCircle(selectedCircelId);
		report.setState(selectedStateId);
		report.setTimePeiod(quterTimePeriod);
		report.setVendor(selectedVendorId);
		report.setRpfNumber(selectedRfpID);
		
		
		
		
		Page<BillingPenaltyEntity> resultPage = null;
		
		if(selectedCircelId.equals("undefined") ||
			selectedStateId.equals("undefined") ||
			quterTimePeriod.equals("undefined")  ||
			selectedVendorId.equals("undefined")
			) {		
			selectedCircelId="";
			selectedStateId="";
			quterTimePeriod="";
			selectedVendorId="";
		}
		if(selectedStateId.equals("0"))	{
			
			resultPage = billingPenaltyService.findPaginatedWithoutState(page, size, type, selectedCircelId, quterTimePeriod, selectedVendorId,selectedRfpID);
		
		}else{
			resultPage = 
					billingPenaltyService.findPaginatedByFilter(page, size, type, selectedCircelId, selectedStateId, quterTimePeriod, selectedVendorId,selectedRfpID);
		}
		
		  return resultPage;
	}
	
	
	@RequestMapping(value = "invoicegenaration/get", params = { "page", "size" ,"type", "selectedCircelId", "selectedStateId","quterTimePeriod","selectedVendorId","selectedRfpID"}, method = RequestMethod.GET, produces = "application/json")
	public Page<InvoiceGeneration> findPaginatedIg(
		      @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("type") String type, @RequestParam("selectedCircelId")
		      String selectedCircelId, @RequestParam("selectedStateId") String selectedStateId,
		      @RequestParam("quterTimePeriod") String quterTimePeriod,@RequestParam("selectedVendorId") String selectedVendorId,@RequestParam("selectedRfpID") String selectedRfpID) {
		logger.info("Inside findPaginatedIg"); 
		/*
		 * logger.info("type=================="+type);
		 * logger.info("selectedCircelId--- "+selectedCircelId);
		 * logger.info("selectedStateId---- "+selectedStateId);
		 * logger.info("quterTimePeriod---- "+quterTimePeriod);
		 * logger.info("selectedVendorId--- "+selectedVendorId);
		 * logger.info("selectedRfpID--- "+selectedRfpID);
		 */
		
		report.setCircle(selectedCircelId);
		report.setState(selectedStateId);
		report.setTimePeiod(quterTimePeriod);
		report.setVendor(selectedVendorId);
		report.setRpfNumber(selectedRfpID);
		
		Page<InvoiceGeneration> resultPage = null;
		
		if(selectedCircelId.equals("undefined") ||
			selectedStateId.equals("undefined") ||
			quterTimePeriod.equals("undefined")  ||
			selectedVendorId.equals("undefined")
			) {		
			selectedCircelId="";
			selectedStateId="";
			quterTimePeriod="";
			selectedVendorId="";
		}
		if(selectedStateId.equals("0"))	{
			
			resultPage = billingPenaltyService.findPageWithoutStateIg(page, size, type, selectedCircelId, quterTimePeriod, selectedVendorId, selectedRfpID);
		
		}else{
			resultPage = 
					billingPenaltyService.findPageByFilterIg(page, size, type, selectedCircelId, selectedStateId, quterTimePeriod, selectedVendorId,selectedRfpID);
		}
		  
		  return resultPage;
	}
	
	
	@RequestMapping(value = "invoiceCompare/get", params = { "page", "size" ,"type", "selectedCircelId", "selectedStateId","quterTimePeriod","selectedVendorId","selectedRfpID"}, method = RequestMethod.GET, produces = "application/json")
	public Page<InvoiceCompare> findPaginatedIc(
		      @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("type") String type, @RequestParam("selectedCircelId")
		      String selectedCircelId, @RequestParam("selectedStateId") String selectedStateId,
		      @RequestParam("quterTimePeriod") String quterTimePeriod,@RequestParam("selectedVendorId") String selectedVendorId,@RequestParam("selectedRfpID") String selectedRfpID) {
		logger.info("Inside findPaginatedIc");
		/*
		 * logger.info("type=================="+type);
		 * logger.info("selectedCircelId--- "+selectedCircelId);
		 * logger.info("selectedStateId---- "+selectedStateId);
		 * logger.info("quterTimePeriod---- "+quterTimePeriod);
		 * logger.info("selectedVendorId--- "+selectedVendorId);
		 * logger.info("selectedRfpID IC--- "+selectedRfpID);
		 */
		report.setCircle(selectedCircelId);
		report.setState(selectedStateId);
		report.setTimePeiod(quterTimePeriod);
		report.setVendor(selectedVendorId);
		report.setRpfNumber(selectedRfpID);
		
		//System.out.println("Check ");
		
		Page<InvoiceCompare> resultPage = null;
		
		if(selectedCircelId.equals("undefined") ||
			selectedStateId.equals("undefined") ||
			quterTimePeriod.equals("undefined")  ||
			selectedVendorId.equals("undefined")
			) {		
			
			selectedCircelId="";
			selectedStateId="";
			quterTimePeriod="";
			selectedVendorId="";
		}
		if(selectedStateId.equals("0"))	{
			logger.info("IF selectedRfpID IC--- "+selectedRfpID);
			resultPage = invoiceCompareService.findPageWithoutStateIc(page, size, type, selectedCircelId, quterTimePeriod, selectedVendorId, selectedRfpID);
		
		}else{
			logger.info("else selectedRfpID IC--- "+selectedRfpID);
			resultPage = 
					invoiceCompareService.findPageByFilterIc(page, size, type, selectedCircelId, selectedStateId, quterTimePeriod, selectedVendorId,selectedRfpID);
		}
		 
		  return resultPage;
	}
	
	
	@RequestMapping(value = "invoiceSummary/get", params = { "page", "size" ,"type", "selectedCircelId", "selectedStateId","quterTimePeriod"}, method = RequestMethod.GET, produces = "application/json")
	public Page<InvoiceSummaryDto> findPaginatedIS(
		      @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("type") String type, @RequestParam("selectedCircelId")
		      String selectedCircelId, @RequestParam("selectedStateId") String selectedStateId,
		      @RequestParam("quterTimePeriod") String quterTimePeriod) {
		logger.info("Inside findPaginatedIS");
		/*
		 * logger.info("type=================="+type);
		 * logger.info("selectedCircelId--- "+selectedCircelId);
		 * logger.info("selectedStateId---- "+selectedStateId);
		 * logger.info("quterTimePeriod---- "+quterTimePeriod);
		 */
	
		
		report.setCircle(selectedCircelId);
		report.setState(selectedStateId);
		report.setTimePeiod(quterTimePeriod);
	
		
		Page<InvoiceSummaryDto> resultPage = null;
		
		if(selectedCircelId.equals("undefined") ||
			selectedStateId.equals("undefined") ||
			quterTimePeriod.equals("undefined") 
			) {		
			selectedCircelId="";
			selectedStateId="";
			quterTimePeriod="";
			
		}
		if(selectedStateId.equals("0"))	{
			
			resultPage = isservices.findPageWithoutStateIs(page, size, type, selectedCircelId, quterTimePeriod);
			
			
//			resultPage = invoiceSummaryRepository.
//					findbyWithoutStateFilter(selectedCircelId, quterTimePeriod, PageRequest.of(page, size));
		}else{
			
			resultPage = isservices.findPageByFilterIs(page, size, type, selectedCircelId, selectedStateId, quterTimePeriod);
//			resultPage = invoiceSummaryRepository.
//					findbyFilter(selectedCircelId, selectedStateId, quterTimePeriod,  PageRequest.of(page, size));
		}
		   
		   // System.out.println("resultPage Content invoiceSummary "+resultPage.getContent());
		  return resultPage;
	}
	
	
	@RequestMapping(value = "ic/UpdateCorrection",params = { "Corrections", "remarks" ,"rpfRefNumber", "kioskSerialNumber", "kisokId","quarter","yearid"}, method = RequestMethod.GET)
	public ResponseEntity<?> updateCorrections(@RequestParam("Corrections")double Corrections,@RequestParam("remarks") String remarks
			, @RequestParam("rpfRefNumber") String rpfRefNumber,
			@RequestParam("kioskSerialNumber") String kioskSerialNumber,@RequestParam("kisokId") String kisokId,
			@RequestParam("quarter") String quarter,@RequestParam("yearid") String yearid)
			throws Exception {
		
		logger.info("Inside updateCorrections");
		if(Corrections>=0) {
			
			int status = invoiceCorrectionRepository.
					updateInvoiceCorrection(Corrections, kisokId, kioskSerialNumber,remarks,quarter,yearid);
			//System.out.println("Status " + status);
			logger.info("Status " + status);
			
			return ResponseEntity.ok(new InvoiceUpdateReposne("Success", "Correction Updated Successfully"));
			
			/*
			 * if(status==1) { return ResponseEntity.ok(new InvoiceUpdateReposne("Sucess",
			 * "Data Saved Successfully")); }else{ return ResponseEntity.ok(new
			 * InvoiceUpdateReposne("fail","Data not updated pleae try")); }
			 */
			
		}else {
			return ResponseEntity.ok(new InvoiceUpdateReposne("Fail","Correction must be positive Integer"));
		}
		
		/*
		 * int status = invoiceCorrectionRepository.
		 * updateInvoiceCorrection(Corrections, kisokId,
		 * kioskSerialNumber,remarks,quarter,yearid); //System.out.println("Status " +
		 * status); logger.info("Status " + status);
		 * 
		 * if(status==1) { return ResponseEntity.ok(new InvoiceUpdateReposne("Sucess",
		 * "Data Saved Successfully")); }else{ return ResponseEntity.ok(new
		 * InvoiceUpdateReposne("fail","Data not updated pleae try")); }
		 */
		

	}
	
	
}
