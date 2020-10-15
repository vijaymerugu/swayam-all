package sbi.kiosk.swayam.billingpayment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.Allocation;
import sbi.kiosk.swayam.common.entity.BillingAllocation;

@Repository
public interface AllocationRepository extends PagingAndSortingRepository<Allocation, Integer> {
	
	
	@Query(value="SELECT * FROM TBL_ALLOCATION where STATUS =1",nativeQuery=true )
	Page<Allocation> findAll(Pageable pageable);

}
