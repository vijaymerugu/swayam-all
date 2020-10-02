package sbi.kiosk.swayam.billingpayment.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.billingpayment.repository.RfpRepository;
import sbi.kiosk.swayam.billingpayment.service.RfpDetailsImpl;
import sbi.kiosk.swayam.common.constants.ExceptionConstants;
import sbi.kiosk.swayam.common.dto.UserManagementDto;
import sbi.kiosk.swayam.common.entity.RfpIdMaster;
import sbi.kiosk.swayam.healthmonitoring.model.BillingPaymentReport;
import sbi.kiosk.swayam.healthmonitoring.model.RfpResponse;
import sbi.kiosk.swayam.transactiondashboard.controller.DrillDownController;

@RestController
public class RfpController {
	
	Logger logger = LoggerFactory.getLogger(RfpController.class);
	
	@Autowired
	RfpRepository rfpRepository;
	
	@Autowired
	BillingPaymentReport report;
	
	@Autowired
	RfpDetailsImpl rfpDetail;
	
	@RequestMapping("bp/rfpdetail")
	public ModelAndView billingPenaltyPage(ModelAndView model, HttpSession session) {
		
		try {
			
			
			model.setViewName("rfpDetail");
			
		} catch (Exception e) {
			
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	
	
	@RequestMapping(value = "rfpDetails/get", params = { "page", "size" ,"type"}, method = RequestMethod.GET)
	public Page<RfpIdMaster> findPaginated(
		      @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("type") String type) {
		logger.info("Inside findPaginated");  
		Page<RfpIdMaster> resultPage = null;
		 
		 resultPage = rfpDetail.findPageWithRfpDetail(page, size, type);
			
		 return resultPage;
		    }
	
	@RequestMapping(value = "rf/update", method = RequestMethod.POST)
	//@PreAuthorize("hasPermission('UpdateRFP','CREATE')")
    public ResponseEntity<RfpResponse> updateRfpDetails(@Valid @RequestBody RfpIdMaster user, BindingResult result) {
       // System.out.println("Updating RF " + user.getRfpId());
		logger.info("Inside Update Rfp Details");
		if(result.hasErrors()) {
			logger.error("Validation Fail updateRfpDetails");
			return ResponseEntity.ok(new RfpResponse("Server side validation fail"));
			
		}else {
			rfpRepository.save(user);
		}
        
        return ResponseEntity.ok(new RfpResponse("RfId "+user.getRfpId()+" Updated Successfully"));
    }
 
 
	@RequestMapping(value = "rf/add", method = RequestMethod.POST)
	//@PreAuthorize("hasPermission('AddRFP','CREATE')")
    public ResponseEntity<RfpResponse> addRfpDetails(@Valid @RequestBody RfpIdMaster user, BindingResult result) {
        //System.out.println("Adding RF " + user.getRfpId());
		logger.info("Inside Add Rfp Details");
        
        	Optional<RfpIdMaster> check = rfpRepository.findById(user.getRfpId());
        	if(check.isPresent()) {
        	//	System.out.println("Inside check "+ check.isPresent());
        		logger.info("RfId "+user.getRfpId()+" Already Present");
        		return ResponseEntity.ok(new RfpResponse("RfId "+user.getRfpId()+" Already Present"));
        		
        	}else {
        		
        		if(result.hasErrors()) {
        			System.out.println("Error " + result.getAllErrors());
        			logger.error("Validation Fail Update Rfp Details");
        			return ResponseEntity.ok(new RfpResponse("Server side validation fail"));
        			
        		}else {
        		rfpRepository.save(user);
        		
        		}
        		
        	}
        	logger.info("RfId "+user.getRfpId()+" Added Successfully");
        	return ResponseEntity.ok(new RfpResponse("RfId "+user.getRfpId()+" Added Successfully"));
    }
 
 
	@RequestMapping(value = "rf/delete", method = RequestMethod.POST)
	//@PreAuthorize("hasPermission('DeleteRFP','CREATE')")
    public ResponseEntity<RfpResponse> deleteRfp(@RequestBody RfpIdMaster user) {
        //System.out.println("deleting RF " + user.getRfpId());
		logger.info("Inside  Delete Rfp");
        rfpRepository.delete(user);
        logger.info("RfId "+user.getRfpId()+" Deleted Successfully");
        return ResponseEntity.ok(new RfpResponse("RfId "+user.getRfpId()+" Deleted Successfully"));
        //return new ResponseEntity<>(HttpStatus.OK);
    }
 

	
	

}
