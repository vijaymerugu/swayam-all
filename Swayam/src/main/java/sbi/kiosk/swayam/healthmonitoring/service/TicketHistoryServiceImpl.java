package sbi.kiosk.swayam.healthmonitoring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.common.dto.TicketHistoryDto;
import sbi.kiosk.swayam.healthmonitoring.repository.TicketHistoryPagingRepository;

@Service
public class TicketHistoryServiceImpl implements TicketHistoryService {
	Logger logger = LoggerFactory.getLogger(TicketHistoryServiceImpl.class);
	
	@Autowired
	TicketHistoryPagingRepository ticketHistoryPagingRepo;

	@Override
	public Page<TicketHistoryDto> paginated(int size, int page) {

		Page<TicketHistoryDto> pageEntity = null;
		try {
			logger.info("pageEntity:::"+size);
			pageEntity = ticketHistoryPagingRepo.findAll(PageRequest.of(page, size)).map(TicketHistoryDto::new);
			logger.info("pageEntity:::"+pageEntity);
		} catch (Exception e) {
			logger.error("TicketHistoryServiceImpl Exception()::", e, e.getMessage());
		}
		return pageEntity;
	}
	
	
	@Override
	public Page<TicketHistoryDto> findPaginatedByFilter(int page, int size, String type, String selectedKioskId,
			String selectedCallLogDateId, String selectedCategoryId, String selectedCircelId,
			String selectedCallClosedDateId, String selectedSubCategoryId, String selectedBranchCode,
			String selectedVendorId) {
		
		
		Page<TicketHistoryDto> pageEntity = null;
		try {
			logger.info("selectedKioskId:::"+selectedKioskId);
			logger.info("selectedCategoryId:::"+selectedCategoryId);
				
				if(selectedKioskId.equals("undefined")) {
					selectedKioskId="";	
				}
					
				if(selectedCallLogDateId.equals("undefined")) {
					selectedCallLogDateId="";	
				}

				if(selectedCategoryId.equals("0") || selectedCategoryId.equals("undefined")) {
					selectedCategoryId="";	
				}

				if(selectedCallClosedDateId.equals("undefined")) {
					selectedCallClosedDateId="";	
				}

				if(selectedSubCategoryId.equals("0") || selectedSubCategoryId.equals("undefined")) {
					selectedSubCategoryId="";	
				}
				if(selectedBranchCode.equals("undefined")) {
					selectedBranchCode="";	
				}

				if(selectedVendorId.equals("0") || selectedVendorId.equals("undefined")) {
					selectedVendorId="";	
				}
				
				if(selectedCircelId.equals("0") || selectedCircelId.equals("undefined")) {
					selectedCircelId="";	
				}
				
				logger.info("selectedCallLogDateId==Service====="+selectedCallLogDateId);
				pageEntity =ticketHistoryPagingRepo.findbyFilter(selectedKioskId,selectedCallLogDateId,selectedCategoryId,selectedBranchCode,selectedCallClosedDateId,selectedSubCategoryId,
						    selectedCircelId,selectedVendorId, PageRequest.of(page, size)).map(TicketHistoryDto::new);
			
			logger.info("pageEntity::::::::::"+pageEntity);
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("TicketHistoryServiceImpl Exception()::", e, e.getMessage());
		}
		return pageEntity;
		
	}

}
