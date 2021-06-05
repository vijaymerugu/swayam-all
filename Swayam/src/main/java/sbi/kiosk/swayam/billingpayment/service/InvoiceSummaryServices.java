package sbi.kiosk.swayam.billingpayment.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.billingpayment.repository.InvoiceSummaryRepository;
import sbi.kiosk.swayam.common.dto.InvoiceSummaryDto;
import sbi.kiosk.swayam.common.entity.BillingPenaltyEntity;
import sbi.kiosk.swayam.common.entity.InvoiceCompare;
import sbi.kiosk.swayam.common.entity.InvoiceGeneration;
import sbi.kiosk.swayam.common.entity.InvoiceSummaryEntity;

@Service
public class InvoiceSummaryServices implements InvoiceSummaryService {
	
	Logger logger = LoggerFactory.getLogger(InvoiceSummaryServices.class);
	
	 @Autowired 
	 InvoiceSummaryRepository isRepository;
	 
	@Override
	public Page<InvoiceSummaryDto> findPageByFilterIs(int page, int size, String type, String selectedCircelId,
			String selectedStateId, String quterTimePeriod) {
		logger.info("Inside findPageByFilterIs "); 
		Page<InvoiceSummaryDto> entity = isRepository.findbyFilter
				(selectedCircelId, selectedStateId, quterTimePeriod, PageRequest.of(page, size)).map(InvoiceSummaryDto::new);
		//Page<InvoiceSummaryDto> entity = repo3.findbyFilter(selectedCircelId, selectedStateId, quterTimePeriod, PageRequest.of(page, size)).map(InvoiceSummaryDto::new);
		
		return entity;
	}
	
	
	@Override
	public Page<InvoiceSummaryDto> findPageWithoutStateIs(int page, int size, String type, String selectedCircelId,
			String quterTimePeriod) {
		logger.info("Inside findPageWithoutStateIs ");
		System.out.println("Circle ID " + selectedCircelId );
		 Page<InvoiceSummaryDto> entity =null;
		
		if(selectedCircelId.equals("0")) {
			entity = isRepository.findCCFilter
				  	(quterTimePeriod, PageRequest.of(page, size)).map(InvoiceSummaryDto::new);
		}else {
			
		
		  entity = isRepository.findbyWithoutStateFilter
				  	(selectedCircelId, quterTimePeriod, PageRequest.of(page, size)).map(InvoiceSummaryDto::new);
		 
		}
		//Page<InvoiceSummaryDto> entity = repo3.findbyWithoutStateFilter(selectedCircelId, quterTimePeriod,  PageRequest.of(page, size)).map(InvoiceSummaryDto::new);
		
		
			/*
			 * List<InvoiceSummaryEntity> listEntity =
			 * isRepository.findbyWithoutStateFilterReport(selectedCircelId,
			 * quterTimePeriod);
			 */
	
		
		//List<InvoiceSummaryEntity> listEntity=isRepo.findSummary(selectedCircelId, quterTimePeriod);
		
		//System.out.println("Inside findPageWithoutStateIs List Entity  " + listEntity);
		
		return entity;
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

	/*
	 * @Override public Page<InvoiceCompare> findPageByFilterIc(int page, int size,
	 * String type, String selectedCircelId, String selectedStateId, String
	 * quterTimePeriod, String selectedVendorId, String selectedRfpID) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public Page<InvoiceCompare> findPageWithoutStateIc(int page, int
	 * size, String type, String selectedCircelId, String quterTimePeriod, String
	 * selectedVendorId, String selectedRfpID) { // TODO Auto-generated method stub
	 * return null; }
	 */



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


	@Override
	public Page<InvoiceCompare> findPageByFilterIc(int page, int size, String type, String selectedCircelId,
			String selectedStateId, String quterTimePeriod, String selectedVendorId, String selectedRfpID,
			String selectedKioskId, String selectedBranch) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Page<InvoiceCompare> findPageWithoutStateIc(int page, int size, String type, String selectedCircelId,
			String quterTimePeriod, String selectedVendorId, String selectedRfpID, String selectedKioskId,
			String selectedBranch) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
