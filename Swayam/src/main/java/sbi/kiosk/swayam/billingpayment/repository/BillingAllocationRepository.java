package sbi.kiosk.swayam.billingpayment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;
import sbi.kiosk.swayam.common.entity.BillingAllocation;



	@Repository
	public interface BillingAllocationRepository extends PagingAndSortingRepository<BillingAllocation, Integer>{
		
		@Query(value="SELECT * FROM TBL_ALLOCATION where STATUS ='s'",nativeQuery=true )
		Page<BillingAllocation> findAll(Pageable pageable);

		
		@Query(value="SELECT * FROM TBL_ALLOCATION where ALLOC_ID = :allocId",nativeQuery=true)
		BillingAllocation findrepRefNo(@Param("allocId") int allocId);
		
		@Query(value="UPDATE TBL_ALLOCATION e  set  e.STATUS = :status where e.ALLOC_ID = :allocId ",nativeQuery=true)
		BillingAllocation Updatestatus(@Param("status")String status ,@Param("allocId") int allocId );
		
	}


