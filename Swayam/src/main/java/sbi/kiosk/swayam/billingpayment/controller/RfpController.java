package sbi.kiosk.swayam.billingpayment.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
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
	
	

}
