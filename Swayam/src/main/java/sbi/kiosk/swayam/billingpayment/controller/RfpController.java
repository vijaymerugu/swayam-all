package sbi.kiosk.swayam.billingpayment.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
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

import sbi.kiosk.swayam.billingpayment.repository.BpRequestRepository;
import sbi.kiosk.swayam.billingpayment.repository.RfpRepository;
import sbi.kiosk.swayam.billingpayment.repository.RfpdtlsRepository;
import sbi.kiosk.swayam.billingpayment.service.RfpDetailsImpl;
import sbi.kiosk.swayam.common.constants.ExceptionConstants;
import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.dto.UserManagementDto;
import sbi.kiosk.swayam.common.entity.BpRequest;
import sbi.kiosk.swayam.common.entity.InvoiceCompareDtls;
import sbi.kiosk.swayam.common.entity.RfpDetails;
import sbi.kiosk.swayam.common.entity.RfpIdMaster;
import sbi.kiosk.swayam.common.entity.TaxEntity;
import sbi.kiosk.swayam.common.utils.ObjectMapperUtils;
import sbi.kiosk.swayam.healthmonitoring.model.BillingPaymentReport;
import sbi.kiosk.swayam.healthmonitoring.model.RfpResponse;
import sbi.kiosk.swayam.transactiondashboard.controller.DrillDownController;

@RestController
public class RfpController {
	
	Logger logger =LoggerFactory.getLogger(RfpController.class);
	
	@Autowired
	RfpRepository rfpRepository;
	
	@Autowired
	RfpdtlsRepository rfpdtlsRepository;
	
	@Autowired
	BpRequestRepository bprequest;
	
	@Autowired
	BillingPaymentReport report;
	
	@Autowired
	RfpDetailsImpl rfpDetail;
	
	@Autowired
	HttpSession httpSession;
	
	@RequestMapping("bp/rfpdetail")
	public ModelAndView billingPenaltyPage(ModelAndView model, HttpSession session) {
		
		UserDto user = (UserDto) httpSession.getAttribute("userObj");
		String role = user.getRole();
		
		if(role.equalsIgnoreCase("BM")) {
			try {
				model.setViewName("rfpDetail");
				
			} catch (Exception e) {			
				logger.error("Exception "+ExceptionConstants.EXCEPTION);
			}
			
		}else if(role.equalsIgnoreCase("BC")) {
			model.setViewName("rfpDetailBC");
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
	
			/*
			 * @RequestMapping(value = "rf/update", method = RequestMethod.POST)
			 * //@PreAuthorize("hasPermission('UpdateRFP','CREATE')") public
			 * ResponseEntity<RfpResponse> updateRfpDetails(@Valid @RequestBody RfpIdMaster
			 * user, BindingResult result) { // System.out.println("Updating RF " +
			 * user.getRfpId()); logger.info("Inside Update Rfp Details");
			 * if(result.hasErrors()) { logger.error("Validation Fail updateRfpDetails " +
			 * result.getAllErrors()); return ResponseEntity.ok(new
			 * RfpResponse("Server side validation fail"));
			 * 
			 * }else { rfpRepository.save(user); }
			 * 
			 * return ResponseEntity.ok(new
			 * RfpResponse("RfId "+user.getRfpId()+" Updated Successfully")); }
			 */
	
	@RequestMapping(value = "rf/update", method = RequestMethod.POST)
	//@PreAuthorize("hasPermission('UpdateRFP','CREATE')")
    public ResponseEntity<RfpResponse> updateRfpDetails(@Valid @RequestBody RfpDetails request, BindingResult result) {
       // System.out.println("Updating RF " + user.getRfpId());
		logger.info("Inside Update Rfp Details");
		UserDto user = (UserDto) httpSession.getAttribute("userObj");
		if(result.hasErrors()) {
			logger.error("Validation Fail updateRfpDetails " + result.getAllErrors());
			return ResponseEntity.ok(new RfpResponse("Server side validation fail"));
			
		}else {
			request.setReqOps("Update");
			rfpdtlsRepository.save(request);
			
			BpRequest request2 = new BpRequest();
			
			request2.setRequestId(request.getRequestId());
			request2.setReqType("RFP Request");
			request2.setMakersComment("Update Request");
			request2.setReqDate(new Date());
			request2.setMakerPfid(user.getPfId());
			request2.setStatus("Submitted");
			request2.setUserCircle(user.getCircle());
			bprequest.save(request2);
		}
        
        return ResponseEntity.ok(new RfpResponse("Request for update successful"));
    }
 
 
	@RequestMapping(value = "rf/add", method = RequestMethod.POST)
	//@PreAuthorize("hasPermission('AddRFP','CREATE')")
    public ResponseEntity<RfpResponse> addRfpDetails(@Valid @RequestBody RfpDetails request, BindingResult result) {
        //System.out.println("Adding RF " + user.getRfpId());
		logger.info("Inside Add Rfp Details");
		UserDto user = (UserDto) httpSession.getAttribute("userObj");
        
        	Optional<RfpIdMaster> check = rfpRepository.findById(request.getRfpId());
        	if(check.isPresent()) {
        	//	System.out.println("Inside check "+ check.isPresent());
        		logger.info("RfId "+request.getRfpId()+" Already Present");
        		return ResponseEntity.ok(new RfpResponse("RfId "+request.getRfpId()+" Already Present"));
        		
        	}else {
        		
        		if(result.hasErrors()) {
        			//System.out.println("Error " + result.getAllErrors());
        			logger.error("Validation Fail Update Rfp Details " + result.getAllErrors());
        			return ResponseEntity.ok(new RfpResponse("Server side validation fail"));
        			
        		}else {
        		request.setReqOps("Add");
        		rfpdtlsRepository.save(request);
        		
        		BpRequest request2 = new BpRequest();
    			
    			request2.setRequestId(request.getRequestId());
    			request2.setReqType("RFP Request");
    			request2.setMakersComment("Add Request");
    			request2.setReqDate(new Date());
    			request2.setMakerPfid(user.getPfId());
    			request2.setStatus("Submitted");
    			request2.setUserCircle(user.getCircle());
    			
    			bprequest.save(request2);
        		
        		}
        		
        	}
        	//logger.info("RfId "+request.getRfpId()+" Added Successfully");
        	return ResponseEntity.ok(new RfpResponse("Request for add successful"));
    }
 
 
	@RequestMapping(value = "rf/delete", method = RequestMethod.POST)
	//@PreAuthorize("hasPermission('DeleteRFP','CREATE')")
    public ResponseEntity<RfpResponse> deleteRfp(@RequestBody RfpDetails request) {
        //System.out.println("deleting RF " + user.getRfpId());
		logger.info("Inside  Delete Rfp");
		request.setReqOps("Delete");
		UserDto user = (UserDto) httpSession.getAttribute("userObj");
		
	  /*	Optional<RfpIdMaster> check = rfpRepository.findById(request.getRfpId());
    	if(check.isPresent()) {
    	//	System.out.println("Inside check "+ check.isPresent());
    		logger.info("RfId "+request.getRfpId()+" Already Present");
    		return ResponseEntity.ok(new RfpResponse("RfId "+request.getRfpId()+" Already Present"));
    		
    	}else {*/
    		/*
    		if(result.hasErrors()) {
    			//System.out.println("Error " + result.getAllErrors());
    			logger.error("Validation Fail Delete request " + result.getAllErrors());
    			return ResponseEntity.ok(new RfpResponse("Server side validation fail"));
    			
    		}else {*/
    			
    			   rfpdtlsRepository.save(request);
    		        
    		        
    		        BpRequest request2 = new BpRequest();
    				request2.setRequestId(request.getRequestId());
    				request2.setReqType("RFP Request");
    				request2.setMakersComment("Delete Request");
    				request2.setReqDate(new Date());
    				request2.setMakerPfid(user.getPfId());
    				request2.setStatus("Submitted");
    				request2.setUserCircle(user.getCircle());
    				
    				bprequest.save(request2);
    		//}
    		
//    	}
     
        
        logger.info("RfId "+request.getRfpId()+" delete request successful");
        return ResponseEntity.ok(new RfpResponse("Request for delete  successful"));
        //return new ResponseEntity<>(HttpStatus.OK);
    }
 

	@RequestMapping("approveRfpDetails/get")
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
		
		
		List<RfpDetails> list = rfpdtlsRepository.findAllByRequestId(requestId);
		
		List<RfpIdMaster> taxEntity= ObjectMapperUtils.mapAll(list, RfpIdMaster.class);
		
		RfpDetails dtls= list.get(0);
		
		if(dtls.getReqOps().equals("Update")) {
			
			rfpRepository.save(taxEntity.get(0));
			
			
			
			bprequest.updateApprove(requestId, new Date(), commnets, pfid);
			
			
			return ResponseEntity.ok(new RfpResponse("Upadte request approved for request id - "
			+ requestId));
		
		}else
		if(dtls.getReqOps().equals("Add")) {
			
			rfpRepository.save(taxEntity.get(0));
			
			
			
			bprequest.updateApprove(requestId, new Date(), commnets, pfid);
			
			
			return ResponseEntity.ok(new RfpResponse("Add request approved for request id - "
			+ requestId));
		
		}else if(dtls.getReqOps().equals("Delete")) {
			
			rfpRepository.delete(taxEntity.get(0));
			
			
			
			bprequest.updateApprove(requestId, new Date(), commnets, pfid);
			
			
			return ResponseEntity.ok(new RfpResponse("Delete request approved for request id  "
			+ requestId));
		
		}
		
		
		return ResponseEntity.ok(new RfpResponse("Approve request failed for -"
				+ requestId));
		
		
		
		
		//httpSession.setAttribute("csrfToken", UUID.randomUUID().toString());
		
		
		 
		
	}
	
	
	
	@RequestMapping("rejectRfpDetails/get")
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
	
	

}
