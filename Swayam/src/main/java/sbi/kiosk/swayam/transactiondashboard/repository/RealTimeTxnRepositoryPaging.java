package sbi.kiosk.swayam.transactiondashboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.RealTimeTransaction;

@Repository("realTimeTxnRepositoryPaging")
public interface RealTimeTxnRepositoryPaging extends PagingAndSortingRepository<RealTimeTransaction, Integer>{
	
	//Page<RealTimeTransaction> findByFromDate(Pageable pageable,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	Page<RealTimeTransaction> findByFromDate(Pageable pageable,@Param("fromdate") String fromdate);
}
