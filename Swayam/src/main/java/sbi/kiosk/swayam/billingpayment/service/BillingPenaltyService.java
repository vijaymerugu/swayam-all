 package sbi.kiosk.swayam.billingpayment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.billingpayment.repository.BillingPenaltyRepository;
import sbi.kiosk.swayam.billingpayment.repository.InvoiceGenerationRepository;
import sbi.kiosk.swayam.common.entity.BillingPenaltyEntity;
import sbi.kiosk.swayam.common.entity.InvoiceCompare;
import sbi.kiosk.swayam.common.entity.InvoiceGeneration;

@Service
public class BillingPenaltyService  implements PenaltyServices{
	
	@Autowired
	BillingPenaltyRepository billingPenaltyRepository;
	
	@Autowired
	InvoiceGenerationRepository invoiceGenerationRepository;



	@Override
	public Page<BillingPenaltyEntity> findPaginatedByFilter(int page, int size, String type, String selectedCircelId,
			String selectedStateId, String quterTimePeriod, String selectedVendorId, String selectedRfpID) {
		System.out.println("Inside findPaginatedByFilter "+selectedRfpID);
		Page<BillingPenaltyEntity> entities;
		if(selectedRfpID.equalsIgnoreCase("1")){
			System.out.println("selectedRfpID "+ selectedRfpID);
			
			entities =
					billingPenaltyRepository.findbyFilter(selectedCircelId, selectedStateId,
							quterTimePeriod, selectedVendorId, PageRequest.of(page, size));
		}else {
			System.out.println("selectedRfpID "+ selectedRfpID);
			entities =
					billingPenaltyRepository.findbyFilterWithRFP(selectedCircelId, selectedStateId, quterTimePeriod, selectedVendorId, selectedRfpID, PageRequest.of(page, size));
		}
		
		System.out.println("Inside findPaginatedByFilter " +entities);
		return entities;
	}

	@Override
	public Page<BillingPenaltyEntity> findPaginatedWithoutState(int page, int size, String type,
			String selectedCircelId, String quterTimePeriod, String selectedVendorId, String selectedRfpID) {
		
		System.out.println("Inside findPaginatedWithoutState" +selectedRfpID);
		Page<BillingPenaltyEntity> entities;
		if(selectedRfpID.equalsIgnoreCase("1")){
			System.out.println("selectedRfpID "+ selectedRfpID);
			entities =billingPenaltyRepository.findbyWithoutStateFilter(selectedCircelId, quterTimePeriod, selectedVendorId, PageRequest.of(page, size));
		}else {
			System.out.println("selectedRfpID "+ selectedRfpID);
			entities =billingPenaltyRepository.findbyFilterRfpWithoutState(selectedCircelId, quterTimePeriod, selectedVendorId, selectedRfpID, PageRequest.of(page, size));

		}
		System.out.println("Inside findPaginatedWithoutState " +entities);
		return entities;
	}

	@Override
	public Page<InvoiceGeneration> findPageByFilterIg(int page, int size, String type, String selectedCircelId,
			String selectedStateId, String quterTimePeriod, String selectedVendorId, String selectedRfpID) {
		Page<InvoiceGeneration> entities;
		if(selectedRfpID.equalsIgnoreCase("1")){
			System.out.println("selectedRfpID "+ selectedRfpID);
			
			entities =
					invoiceGenerationRepository.findbyFilter(selectedCircelId, selectedStateId,
							quterTimePeriod, selectedVendorId, PageRequest.of(page, size));
		}else {
			System.out.println("selectedRfpID "+ selectedRfpID);
			entities = invoiceGenerationRepository.findbyFilterWithRFP(selectedCircelId, selectedStateId, 
					quterTimePeriod, selectedVendorId, selectedRfpID, PageRequest.of(page, size));
		}
		
		System.out.println("Inside findPaginatedByFilter " +entities);
		return entities;
	}

	@Override
	public Page<InvoiceGeneration> findPageWithoutStateIg(int page, int size, String type, String selectedCircelId,
			String quterTimePeriod, String selectedVendorId, String selectedRfpID) {
		System.out.println("Inside findPaginatedWithoutState" +selectedRfpID);
		Page<InvoiceGeneration> entities;
		if(selectedRfpID.equalsIgnoreCase("1")){
			System.out.println("selectedRfpID "+ selectedRfpID);
			entities =invoiceGenerationRepository.findbyWithoutStateFilter(selectedCircelId, quterTimePeriod, selectedVendorId, PageRequest.of(page, size));
		}else {
			System.out.println("selectedRfpID "+ selectedRfpID);
			entities =invoiceGenerationRepository.findbyFilterRfpWithoutState(selectedCircelId, quterTimePeriod, selectedVendorId, selectedRfpID, PageRequest.of(page, size));

		}
		System.out.println("Inside findPaginatedWithoutState " +entities);
		return entities;
	}

	@Override
	public Page<InvoiceCompare> findPageByFilterIc(int page, int size, String type, String selectedCircelId,
			String selectedStateId, String quterTimePeriod, String selectedVendorId, String selectedRfpID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<InvoiceCompare> findPageWithoutStateIc(int page, int size, String type, String selectedCircelId,
			String quterTimePeriod, String selectedVendorId, String selectedRfpID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
