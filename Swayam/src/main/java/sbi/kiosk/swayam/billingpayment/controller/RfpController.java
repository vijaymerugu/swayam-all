package sbi.kiosk.swayam.billingpayment.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.billingpayment.repository.RfpRepository;
import sbi.kiosk.swayam.billingpayment.service.RfpDetailsImpl;

import sbi.kiosk.swayam.common.dto.UserManagementDto;
import sbi.kiosk.swayam.common.entity.RfpIdMaster;
import sbi.kiosk.swayam.healthmonitoring.model.BillingPaymentReport;
import sbi.kiosk.swayam.healthmonitoring.model.RfpResponse;

@RestController
public class RfpController {
	
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
			e.printStackTrace();
			//logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	
	
	@RequestMapping(value = "rfpDetails/get", params = { "page", "size" ,"type"}, method = RequestMethod.GET)
	public Page<RfpIdMaster> findPaginated(
		      @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("type") String type) {
		 Page<RfpIdMaster> resultPage = null;
		 
		 resultPage = rfpDetail.findPageWithRfpDetail(page, size, type);
			
		 return resultPage;
		    }
	
	@RequestMapping(value = "rf/update", method = RequestMethod.POST)
	//@PreAuthorize("hasPermission('UpdateRFP','CREATE')")
    public ResponseEntity<RfpResponse> updateRfpDetails(@RequestBody RfpIdMaster user) {
        System.out.println("Updating RF " + user.getRfpId());
        rfpRepository.save(user);
        return ResponseEntity.ok(new RfpResponse("RfId "+user.getRfpId()+" Updated Successfully"));
    }
 
 
	@RequestMapping(value = "rf/add", method = RequestMethod.POST)
	//@PreAuthorize("hasPermission('AddRFP','CREATE')")
    public ResponseEntity<RfpResponse> addRfpDetails(@RequestBody RfpIdMaster user) {
        System.out.println("Adding RF " + user.getRfpId());
        
        	Optional<RfpIdMaster> check = rfpRepository.findById(user.getRfpId());
        	if(check.isPresent()) {
        		System.out.println("Inside check "+ check.isPresent());
        		
        		return ResponseEntity.ok(new RfpResponse("RfId "+user.getRfpId()+" Already Present"));
        		
        	}else {
        	
        		rfpRepository.save(user);
        		
        	}
        	return ResponseEntity.ok(new RfpResponse("RfId "+user.getRfpId()+" Added Successfully"));
    }
 
 
	@RequestMapping(value = "rf/delete", method = RequestMethod.POST)
	//@PreAuthorize("hasPermission('DeleteRFP','CREATE')")
    public ResponseEntity<RfpResponse> deleteRfp(@RequestBody RfpIdMaster user) {
        System.out.println("deleting RF " + user.getRfpId());
        rfpRepository.delete(user);
        
        return ResponseEntity.ok(new RfpResponse("RfId "+user.getRfpId()+" Deleted Successfully"));
        //return new ResponseEntity<>(HttpStatus.OK);
    }
 

	
	

}
