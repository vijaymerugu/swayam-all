package sbi.kiosk.swayam.billingpayment.service;

import java.util.Map;

public interface SanctionRequsetService {
	
	
	Map<String, Integer> findAllCountRequestStatus();
	
	Map<String,Object> findSRApprovalForm(Integer requestId);

}
