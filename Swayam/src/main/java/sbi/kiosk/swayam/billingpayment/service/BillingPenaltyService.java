 package sbi.kiosk.swayam.billingpayment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.billingpayment.controller.BillingPenaltyController;
import sbi.kiosk.swayam.billingpayment.repository.BillingPenaltyRepository;
import sbi.kiosk.swayam.billingpayment.repository.InvoiceGenerationRepository;
import sbi.kiosk.swayam.common.dto.InvoiceSummaryDto;
import sbi.kiosk.swayam.common.entity.BillingPenaltyEntity;
import sbi.kiosk.swayam.common.entity.InvoiceCompare;
import sbi.kiosk.swayam.common.entity.InvoiceGeneration;

@Service
public class BillingPenaltyService  implements PenaltyServices{
	
	Logger logger =LoggerFactory.getLogger(BillingPenaltyService.class);
	
	@Autowired
	BillingPenaltyRepository billingPenaltyRepository;
	
	@Autowired
	InvoiceGenerationRepository invoiceGenerationRepository;


	@Override
	public Page<BillingPenaltyEntity> findPaginatedByFilter(int page, int size, String type, String selectedCircelId,
			String selectedStateId, String quterTimePeriod, String selectedVendorId, String selectedRfpID) {
	
		logger.info("Inside findPaginatedByFilter "); 
		logger.info("quterTimePeriod-----" + quterTimePeriod); 
		Page<BillingPenaltyEntity> entities = null;
		String quarter =null;
		String finacialYear= null;
		try {			
		if(quterTimePeriod!="") {
		quarter= quterTimePeriod.substring(0, 2);
		finacialYear= quterTimePeriod.substring(3);
		
		
		
		if(selectedRfpID.equalsIgnoreCase("1")){
			//System.out.println("selectedRfpID "+ selectedRfpID);
			
			entities =
					billingPenaltyRepository.findbyFilter(selectedCircelId, selectedStateId,
							quarter,finacialYear, selectedVendorId, PageRequest.of(page, size));
		}else {
			//System.out.println("selectedRfpID "+ selectedRfpID);
			entities =
					billingPenaltyRepository.findbyFilterWithRFP(selectedCircelId, selectedStateId, quarter,finacialYear, selectedVendorId, selectedRfpID, PageRequest.of(page, size));
		}
		
		}
	}catch (NullPointerException e) {
		logger.error("Exception quaterperiod undefined");
	}
		return entities;
	}

	@Override
	public Page<BillingPenaltyEntity> findPaginatedWithoutState(int page, int size, String type,
			String selectedCircelId, String quterTimePeriod, String selectedVendorId, String selectedRfpID) {
		logger.info("Inside findPaginatedWithoutState "); 
		logger.info("quterTimePeriod-----" + quterTimePeriod); 
		Page<BillingPenaltyEntity> entities = null;
		try {
			
		
		String quarter =null;
		String finacialYear= null;
		
		if(quterTimePeriod!="") {
		quarter= quterTimePeriod.substring(0, 2);
		finacialYear= quterTimePeriod.substring(3);
		
		
		
		if(selectedRfpID.equalsIgnoreCase("1")){
			//System.out.println("selectedRfpID "+ selectedRfpID);
			entities =billingPenaltyRepository.findbyWithoutStateFilter(selectedCircelId, quarter,finacialYear, selectedVendorId, PageRequest.of(page, size));
		}else {
			//System.out.println("selectedRfpID "+ selectedRfpID);
			entities =billingPenaltyRepository.findbyFilterRfpWithoutState(selectedCircelId, quarter,finacialYear, selectedVendorId, selectedRfpID, PageRequest.of(page, size));

		}
		
		}
		//System.out.println("Inside findPaginatedWithoutState " +entities);
		
		}catch (NullPointerException e) {
			logger.error("Exception quaterperiod undefined");
		}
		return entities;
	}

	@Override
	public Page<InvoiceGeneration> findPageByFilterIg(int page, int size, String type, String selectedCircelId,
			String selectedStateId, String quterTimePeriod, String selectedVendorId, String selectedRfpID) {
		logger.info("Inside findPageByFilterIg "); 
		
		String quarter =null;
		String finacialYear= null;
		Page<InvoiceGeneration> entities = null;
		try {
		if(quterTimePeriod!="") {
		quarter= quterTimePeriod.substring(0, 2);
		finacialYear= quterTimePeriod.substring(3);
		
		
		if(selectedRfpID.equalsIgnoreCase("1")){
			//System.out.println("selectedRfpID "+ selectedRfpID);
			
			entities =
					invoiceGenerationRepository.findbyFilter(selectedCircelId, selectedStateId,
							quarter,finacialYear,selectedVendorId, PageRequest.of(page, size));
		}else {
			//System.out.println("selectedRfpID "+ selectedRfpID);
			entities = invoiceGenerationRepository.findbyFilterWithRFP(selectedCircelId, selectedStateId, 
					quarter,finacialYear, selectedVendorId, selectedRfpID, PageRequest.of(page, size));
		}
		
		}
		}catch (NullPointerException e) {
			logger.error("Exception quaterperiod undefined");
		}
		//System.out.println("Inside findPaginatedByFilter " +entities);
		return entities;
	}

	@Override
	public Page<InvoiceGeneration> findPageWithoutStateIg(int page, int size, String type, String selectedCircelId,
			String quterTimePeriod, String selectedVendorId, String selectedRfpID) {
		logger.info("Inside findPageWithoutStateIg "); 
		String quarter =null;
		String finacialYear= null;
		Page<InvoiceGeneration> entities = null;
		
		if(quterTimePeriod!="") {
		quarter= quterTimePeriod.substring(0, 2);
		finacialYear= quterTimePeriod.substring(3);
		
		if(selectedRfpID.equalsIgnoreCase("1")){
			//System.out.println("selectedRfpID "+ selectedRfpID);
			entities =invoiceGenerationRepository.findbyWithoutStateFilter(selectedCircelId, quarter,finacialYear, selectedVendorId, PageRequest.of(page, size));
		}else {
			//System.out.println("selectedRfpID "+ selectedRfpID);
			entities =invoiceGenerationRepository.findbyFilterRfpWithoutState(selectedCircelId, quarter,finacialYear, selectedVendorId, selectedRfpID, PageRequest.of(page, size));

		}
		}
		//System.out.println("Inside findPaginatedWithoutState " +entities);
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
