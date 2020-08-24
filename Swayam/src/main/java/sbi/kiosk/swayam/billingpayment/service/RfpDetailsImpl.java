package sbi.kiosk.swayam.billingpayment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.billingpayment.repository.RfpRepository;
import sbi.kiosk.swayam.common.entity.RfpIdMaster;

@Service
public class RfpDetailsImpl implements RfpdeatailsService {
	
	@Autowired
	RfpRepository rfpReposisory;
	
	
	@Override
	public Page<RfpIdMaster> findPageWithRfpDetail(int page, int size, String type) {
		Page<RfpIdMaster> entities = rfpReposisory.findAll(PageRequest.of(page, size));
		
		return entities;
		
	}

}
