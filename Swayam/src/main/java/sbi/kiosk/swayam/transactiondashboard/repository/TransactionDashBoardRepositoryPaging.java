package sbi.kiosk.swayam.transactiondashboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.SwayamMigrationSummary;

@Repository
public interface TransactionDashBoardRepositoryPaging extends PagingAndSortingRepository<SwayamMigrationSummary, String>{

	Page<SwayamMigrationSummary> findAll(Pageable pageable);
	
	
}
