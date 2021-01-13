package sbi.kiosk.swayam.transactiondashboard.service;

import org.springframework.data.domain.Page;


import sbi.kiosk.swayam.common.entity.SwayamMigrationSummary;

public interface TransactionDashBoardService {
	Page<SwayamMigrationSummary> findPaginated(final int page, final int size,String fromdate,String todate);
	
	String findSwayamTxnLastUpdatedJob();
	Page<SwayamMigrationSummary> findPaginatedSearchNext(int page, int size, String fromdate, String todate,
			String searchText);
}
