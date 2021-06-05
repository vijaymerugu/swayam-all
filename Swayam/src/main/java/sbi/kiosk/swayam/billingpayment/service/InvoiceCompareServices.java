package sbi.kiosk.swayam.billingpayment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	Logger logger =LoggerFactory.getLogger(InvoiceCompareServices.class);
	
	@Autowired
	InvoiceCompareRepository invoiceCompareRepository;	
	
	@Override
	public Page<InvoiceCompare> findPageByFilterIc(int page, int size, String type, String selectedCircelId,
			String selectedStateId, String quterTimePeriod, String selectedVendorId, String selectedRfpID,
			String selectedKioskId,String selectedBranch) {
	
		logger.info("Inside findPageByFilterIc "); 
		String quarter =null;
		String finacialYear= null;
		Page<InvoiceCompare> entities=null;
		if(quterTimePeriod!="") {
		quarter= quterTimePeriod.substring(0, 2);
		finacialYear= quterTimePeriod.substring(3);
		
		if(selectedRfpID.equalsIgnoreCase("1")){
			
			
			entities =
					invoiceCompareRepository.findbyFilter(selectedCircelId, selectedStateId,
							quarter,finacialYear, selectedVendorId, PageRequest.of(page, size),selectedKioskId,selectedBranch);
		}else {
			
			entities = invoiceCompareRepository.findbyFilterWithRFP(selectedCircelId, selectedStateId, 
					quarter,finacialYear, selectedVendorId, selectedRfpID, PageRequest.of(page, size),selectedKioskId,selectedBranch);
		}
		}
		//System.out.println("Inside findPaginatedByFilter " +entities);
		return entities;
	}

	@Override
	public Page<InvoiceCompare> findPageWithoutStateIc(int page, int size, String type, String selectedCircelId,
			String quterTimePeriod, String selectedVendorId, String selectedRfpID,
			String selectedKioskId,String selectedBranch) {
		
		logger.info("Inside findPageWithoutStateIc "); 
		String quarter =null;
		String finacialYear= null;
		Page<InvoiceCompare> entities=null;
		if(quterTimePeriod!="") {
		quarter= quterTimePeriod.substring(0, 2);
		finacialYear= quterTimePeriod.substring(3);
	
		
		if(selectedRfpID.equalsIgnoreCase("1")){
			
			logger.info("Selected RfpID " +selectedRfpID );
			logger.info("Selected CircleId  " +selectedCircelId );
			
			if(selectedCircelId.equals("0")) {
				logger.info("Inside Without RFID and CC");
				entities =invoiceCompareRepository.findbyWithoutStateFilterCC(quarter,finacialYear,
						selectedVendorId, PageRequest.of(page, size),selectedKioskId,selectedBranch);
				
			}else {
				logger.info("Inside Without RFID and without CC");
				entities =invoiceCompareRepository.findbyWithoutStateFilter(selectedCircelId, quarter,
						finacialYear, selectedVendorId, PageRequest.of(page, size),selectedKioskId,selectedBranch);
				
			}
			
			
			
			//entities =invoiceCompareRepository.findbyWithoutStateFilter(selectedCircelId, quarter,finacialYear, selectedVendorId, PageRequest.of(page, size));
		}else {
			
			if(selectedCircelId.equals("0")) {
				
				logger.info("Inside With RFID and CC");
				
				entities =invoiceCompareRepository.findbyFilterRfpWithoutStateCC(quarter,finacialYear, 
								selectedVendorId, selectedRfpID, PageRequest.of(page, size),selectedKioskId,selectedBranch);
				
			}else {
				logger.info("Inside With RFID and without CC");
				entities =invoiceCompareRepository.findbyFilterRfpWithoutState(selectedCircelId, quarter,finacialYear, 
						selectedVendorId, selectedRfpID, PageRequest.of(page, size),selectedKioskId,selectedBranch);
			}
		
			
			
			
			//entities =invoiceCompareRepository.findbyFilterRfpWithoutState(selectedCircelId, quarter,finacialYear, selectedVendorId, selectedRfpID, PageRequest.of(page, size));

		}
		
		}
		
		return entities;
	}
	


	/*
	 * @Override public Page<InvoiceGeneration> findPageByFilterIg(int page, int
	 * size, String type, String selectedCircelId, String selectedStateId, String
	 * quterTimePeriod, String selectedVendorId, String selectedRfpID) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public Page<InvoiceGeneration> findPageWithoutStateIg(int page, int
	 * size, String type, String selectedCircelId, String quterTimePeriod, String
	 * selectedVendorId, String selectedRfpID) { // TODO Auto-generated method stub
	 * return null; }
	 */

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

	@Override
	public Page<BillingPenaltyEntity> findPaginatedByFilterSS(int page, int size, String type, String selectedCircelId,
			String selectedStateId, String quterTimePeriod, String selectedVendorId, String selectedRfpID,
			String selectedKioskId, String selectedBranch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<BillingPenaltyEntity> findPaginatedWithoutStateSS(int page, int size, String type,
			String selectedCircelId, String quterTimePeriod, String selectedVendorId, String selectedRfpID,
			String selectedKioskId, String selectedBranch) {
		// TODO Auto-generated method stub
		return null;
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
			String selectedStateId, String quterTimePeriod, String selectedVendorId, String selectedRfpID,
			String selectedKioskId, String selectedBranch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<InvoiceGeneration> findPageWithoutStateIg(int page, int size, String type, String selectedCircelId,
			String quterTimePeriod, String selectedVendorId, String selectedRfpID, String selectedKioskId,
			String selectedBranch) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
