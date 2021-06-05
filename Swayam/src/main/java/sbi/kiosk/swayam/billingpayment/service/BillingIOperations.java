package sbi.kiosk.swayam.billingpayment.service;

import org.springframework.data.domain.Page;

import sbi.kiosk.swayam.common.dto.InvoiceSummaryDto;
import sbi.kiosk.swayam.common.entity.BillingPenaltyEntity;
import sbi.kiosk.swayam.common.entity.InvoiceCompare;
import sbi.kiosk.swayam.common.entity.InvoiceGeneration;

public interface BillingIOperations<T> {
		
	
		Page<BillingPenaltyEntity> findPaginatedByFilterSS(int page, int size, String type,
		      String selectedCircelId, String selectedStateId, String quterTimePeriod, String selectedVendorId,String selectedRfpID,
		      String selectedKioskId,String selectedBranch);
			
			Page<BillingPenaltyEntity> findPaginatedWithoutStateSS(int page, int size, String type,
				      String selectedCircelId, String quterTimePeriod, String selectedVendorId, String selectedRfpID,
				      String selectedKioskId,String selectedBranch);
	
		Page<BillingPenaltyEntity> findPaginatedByFilter(int page, int size, String type,
	      String selectedCircelId, String selectedStateId, String quterTimePeriod, String selectedVendorId,String selectedRfpID);
		
		Page<BillingPenaltyEntity> findPaginatedWithoutState(int page, int size, String type,
			      String selectedCircelId, String quterTimePeriod, String selectedVendorId, String selectedRfpID);
		
		Page<InvoiceGeneration> findPageByFilterIg(int page, int size, String type,
			      String selectedCircelId, String selectedStateId, String quterTimePeriod, 
			      String selectedVendorId,String selectedRfpID,
			      String selectedKioskId,String selectedBranch);
				
		Page<InvoiceGeneration> findPageWithoutStateIg(int page, int size, String type,
					      String selectedCircelId, String quterTimePeriod,
					      String selectedVendorId, String selectedRfpID,
					      String selectedKioskId,String selectedBranch);
		
		Page<InvoiceCompare> findPageByFilterIc(int page, int size, String type,
			      String selectedCircelId, String selectedStateId, String quterTimePeriod, 
			      String selectedVendorId,String selectedRfpID,
			      String selectedKioskId,String selectedBranch);
				
		Page<InvoiceCompare> findPageWithoutStateIc(int page, int size, String type,
					      String selectedCircelId, String quterTimePeriod,
					      String selectedVendorId, String selectedRfpID,
					      String selectedKioskId,String selectedBranch);
		
		Page<InvoiceSummaryDto> findPageByFilterIs(int page, int size, String type,
			      String selectedCircelId, String selectedStateId, String quterTimePeriod);
		
		
		Page<InvoiceSummaryDto> findPageWithoutStateIs(int page, int size, String type,
			      String selectedCircelId, String quterTimePeriod);
}	
				
		
