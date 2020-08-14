package sbi.kiosk.swayam.healthmonitoring.service;

import org.springframework.data.domain.Page;

import sbi.kiosk.swayam.common.dto.TicketHistoryDto;

public interface TicketHistoryService  {
	
	public Page<TicketHistoryDto> paginated(int size, int page);

	public Page<TicketHistoryDto> findPaginatedByFilter(int page, int size, String type, String selectedKioskId,
			String selectedCallLogDateId, String selectedCategoryId, String selectedCircelId,
			String selectedCallClosedDateId, String selectedSubCategoryId, String selectedBranchCode,
			String selectedVendorId);
	
	

}
