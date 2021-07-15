package sbi.kiosk.swayam.billingpayment.service;

import java.util.Map;

public interface SanctionRequsetService {
	
	
	Map<String, Integer> findAllCountRequestStatus(String circle);
	
	Map<String,Object> findSRApprovalForm(Integer requestId);
	
	Map<String,Object> findICApprovalForm(Integer requestId);
	Map<String,Object> findTaxApprovalForm(Integer requestId);
	Map<String,Object> findRFPApprovalForm(Integer requestId);
	
	

}
