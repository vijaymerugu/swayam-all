package sbi.kiosk.swayam.billingpayment.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.data.domain.Page;

import sbi.kiosk.swayam.common.dto.AllocationDto;
import sbi.kiosk.swayam.common.dto.BillingAllocationDto;

import sbi.kiosk.swayam.common.entity.BillingAllocation;


public interface BillingService {
	
	//public String upload(String path);
	public String upload(InputStream is);

	public Page<BillingAllocationDto> findPaginated(final int page, final int size);

	BillingAllocationDto findBillingallocId(Integer allocId);

	String updateBillingAllocation(BillingAllocationDto dto);
	
	public Page<AllocationDto> findPaginatedAllocation(final int page, final int size);


}
