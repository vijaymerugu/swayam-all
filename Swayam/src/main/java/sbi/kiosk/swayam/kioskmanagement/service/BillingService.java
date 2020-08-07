package sbi.kiosk.swayam.kioskmanagement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import sbi.kiosk.swayam.common.dto.BillingAllocationDto;
import sbi.kiosk.swayam.common.dto.BillingPurchaseOrderDto;
import sbi.kiosk.swayam.common.entity.BillingAllocation;
import sbi.kiosk.swayam.common.entity.BillingPurchaseOrder;

public interface BillingService {
	
	public String upload(String path);

	public Page<BillingAllocationDto> findPaginated(final int page, final int size);

	BillingAllocationDto findBillingallocId(Integer allocId);

	String updateBillingAllocation(BillingAllocationDto dto);


}
