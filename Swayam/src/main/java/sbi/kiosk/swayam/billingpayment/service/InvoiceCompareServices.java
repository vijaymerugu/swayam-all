package sbi.kiosk.swayam.billingpayment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.billingpayment.repository.InvoiceCompareRepository;
import sbi.kiosk.swayam.common.dto.InvoiceSummaryDto;
import sbi.kiosk.swayam.common.entity.BillingPenaltyEntity;
import sbi.kiosk.swayam.common.entity.InvoiceCompare;
import sbi.kiosk.swayam.common.entity.InvoiceGeneration;

@Service
public class InvoiceCompareServices implements InvoiceCompareService{
	
	
	@Autowired
	InvoiceCompareRepository invoiceCompareRepository;
	
	
	@Override
	public Page<InvoiceCompare> findPageByFilterIc(int page, int size, String type, String selectedCircelId,
			String selectedStateId, String quterTimePeriod, String selectedVendorId, String selectedRfpID) {
		System.out.println("Inside findPageByFilterIc");
		String quarter= quterTimePeriod.substring(0, 2);
		String finacialYear= quterTimePeriod.substring(3);
		System.out.println("Quater "+ quarter);
		System.out.println("finacialYear "+ finacialYear);
		Page<InvoiceCompare> entities;
		if(selectedRfpID.equalsIgnoreCase("1")){
			System.out.println("findPageByFilterIc selectedRfpID "+ selectedRfpID);
			
			entities =
					invoiceCompareRepository.findbyFilter(selectedCircelId, selectedStateId,
							quarter,finacialYear, selectedVendorId, PageRequest.of(page, size));
		}else {
			System.out.println("findPageByFilterIc else selectedRfpID "+ selectedRfpID);
			entities = invoiceCompareRepository.findbyFilterWithRFP(selectedCircelId, selectedStateId, 
					quarter,finacialYear, selectedVendorId, selectedRfpID, PageRequest.of(page, size));
		}
		
		System.out.println("Inside findPaginatedByFilter " +entities);
		return entities;
	}

	@Override
	public Page<InvoiceCompare> findPageWithoutStateIc(int page, int size, String type, String selectedCircelId,
			String quterTimePeriod, String selectedVendorId, String selectedRfpID) {
		System.out.println("Inside findPaginatedWithoutState" +selectedRfpID);
		String quarter= quterTimePeriod.substring(0, 2);
		String finacialYear= quterTimePeriod.substring(3);
		//System.out.println("Quater "+ quarter);
		//System.out.println("finacialYear "+ finacialYear);
		Page<InvoiceCompare> entities;
		if(selectedRfpID.equalsIgnoreCase("1")){
			System.out.println("findPageWithoutStateIc selectedRfpID "+ selectedRfpID);
			entities =invoiceCompareRepository.findbyWithoutStateFilter(selectedCircelId, quarter,finacialYear, selectedVendorId, PageRequest.of(page, size));
		}else {
			System.out.println("findPageWithoutStateIc else selectedRfpID "+ selectedRfpID);
			entities =invoiceCompareRepository.findbyFilterRfpWithoutState(selectedCircelId, quarter,finacialYear, selectedVendorId, selectedRfpID, PageRequest.of(page, size));

		}
		System.out.println("Inside findPaginatedWithoutState " +entities);
		return entities;
	}
	

	@Override
	public Page<BillingPenaltyEntity> findPaginatedByFilter(int page, int size, String type, String selectedCircelId,
			String selectedStateId, String quterTimePeriod, String selectedVendorId, String selectedRfpID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<BillingPenaltyEntity> findPaginatedWithoutState(int page, int size, String type,
			String selectedCircelId, String quterTimePeriod, String selectedVendorId, String selectedRfpID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<InvoiceGeneration> findPageByFilterIg(int page, int size, String type, String selectedCircelId,
			String selectedStateId, String quterTimePeriod, String selectedVendorId, String selectedRfpID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<InvoiceGeneration> findPageWithoutStateIg(int page, int size, String type, String selectedCircelId,
			String quterTimePeriod, String selectedVendorId, String selectedRfpID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<InvoiceSummaryDto> findPageByFilterIs(int page, int size, String type, String selectedCircelId,
			String selectedStateId, String quterTimePeriod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<InvoiceSummaryDto> findPageWithoutStateIs(int page, int size, String type, String selectedCircelId,
			String quterTimePeriod) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
