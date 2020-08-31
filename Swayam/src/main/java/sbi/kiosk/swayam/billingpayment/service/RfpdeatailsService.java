package sbi.kiosk.swayam.billingpayment.service;

import org.springframework.data.domain.Page;

import sbi.kiosk.swayam.common.entity.RfpIdMaster;

public interface RfpdeatailsService {
	
	
	
	Page<RfpIdMaster> findPageWithRfpDetail(int page, int size, String type);

}
