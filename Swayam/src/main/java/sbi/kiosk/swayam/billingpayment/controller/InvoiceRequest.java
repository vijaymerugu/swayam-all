package sbi.kiosk.swayam.billingpayment.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sbi.kiosk.swayam.billingpayment.repository.BpRequestRepository;
import sbi.kiosk.swayam.billingpayment.repository.InvoiceCompareFormRepository;
import sbi.kiosk.swayam.billingpayment.repository.InvoiceCorrectionRepository;
import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.entity.BpRequest;
import sbi.kiosk.swayam.common.entity.InvoiceCompareDtls;
import sbi.kiosk.swayam.common.entity.SanctionRequestEntity;
import sbi.kiosk.swayam.healthmonitoring.model.InvoiceUpdateReposne;
import sbi.kiosk.swayam.healthmonitoring.model.RfpResponse;

@RestController
public class InvoiceRequest {
	
	
Logger logger = LoggerFactory.getLogger(InvoiceRequest.class);
	
	@Autowired
	BpRequestRepository bprequest;
	
	@Autowired
	HttpSession httpSession;
	
	@Autowired 
	InvoiceCompareFormRepository compareformRepo;
	
	@Autowired
	InvoiceCorrectionRepository invoiceCorrectionRepository;
	
	
	
	
	@RequestMapping(value = "bp/IcInsert", method = RequestMethod.POST)
	//@PreAuthorize("hasPermission('AddRFP','CREATE')")
    public ResponseEntity<RfpResponse> addSanctionRequest(@RequestBody String payload,
    		HttpServletRequest req) throws JsonParseException, JsonMappingException, IOException {
			ObjectMapper objectMapper = new ObjectMapper();
			
			byte[] decodedBytes = Base64.getMimeDecoder().decode(payload);
			String decodedString = new String(decodedBytes);
			@Valid
			InvoiceCompareDtls request = objectMapper.readValue(decodedString, InvoiceCompareDtls.class);
			
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
			  if(role.equalsIgnoreCase("BM") == false) { 
				  return ResponseEntity.ok(new RfpResponse("Authorization Required"));  
				  }
			  
			compareformRepo.save(request);
			
			BpRequest request2 = new BpRequest();
			
			request2.setRequestId(request.getRequestId());
			request2.setReqType("Invoice Compare");
			request2.setMakersComment(request.getRemarks());
			request2.setReqDate(new Date());
			request2.setMakerPfid(user.getPfId());
			request2.setStatus("Submitted");
			request2.setUserCircle(user.getCircle());
			
			bprequest.save(request2);
        		
        	
        	//logger.info("Request Id "+ request.getRequestId()  +" Added Successfully");
        	return ResponseEntity.ok(new 
        			RfpResponse("Request Id  "+request2.getRequestId()  +" submitted Successfully"));
    }
	
	
	
	@RequestMapping("approveICDetails/get")
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
	    			RfpResponse("Cannot approve request Id "+ requestId +" already rejected"));
		}
		
		String pfid = user.getPfId();
		
		
		List<InvoiceCompareDtls> list = compareformRepo.findAllByRequestId(requestId);
		
		InvoiceCompareDtls dtls= list.get(0);
		
		if(dtls.getCorrectionAmount()>=0) {
			
			int status = invoiceCorrectionRepository.updateInvoiceFormCorrection(dtls.getCorrectionAmount(), dtls.getKisokId(),
					dtls.getKioskSerialNumber(), dtls.getRemarks(), dtls.getQuarterId(), 
					dtls.getYear(),pfid,new Date());
			
			logger.info("Status " + status);
			
			bprequest.updateApprove(requestId, new Date(), commnets, pfid);
			
			
			return ResponseEntity.ok(new RfpResponse("Request Id  "+ requestId
					  +" Updated Successfully"));
			/*
			 * if(status==1) { return ResponseEntity.ok(new InvoiceUpdateReposne("Sucess",
			 * "Data Saved Successfully")); }else{ return ResponseEntity.ok(new
			 * InvoiceUpdateReposne("fail","Data not updated pleae try")); }
			 */
			
		}else {
			//return ResponseEntity.ok(new InvoiceUpdateReposne("Fail","Correction must be positive Integer"));
		
			  return ResponseEntity.ok(new RfpResponse("Failed - Correction must be positive Integer"));
		
		}
		
		
		
		//httpSession.setAttribute("csrfToken", UUID.randomUUID().toString());
		
		
		 
		
	}
	
	
	
	@RequestMapping("rejectICDetails/get")
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
