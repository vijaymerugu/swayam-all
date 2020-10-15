package sbi.kiosk.swayam.billingpayment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.Allocation;
import sbi.kiosk.swayam.common.entity.DisplayAllocation;

@Repository
public interface DisplayAllocationRepository extends PagingAndSortingRepository<DisplayAllocation, Integer>{
	
	
	@Query(value="select a.ALLOC_ID, a.RFP_REF_NO, v.COMPANY_SHORT_NM, c.CRCL_NAME, a.ALLOCATED_QUANTITY,a.remaining_quantity,a.vendor_id,a.crcl_code,TO_CHAR(a.UPDATED_DATE,'dd-MM-yy') as PODATE from tbl_allocation a \r\n" + 
			"Inner JOIN tbl_vendor_details v\r\n" + 
			"on v.vendor_id=a.vendor_id\r\n" + 
			"Inner JOIN tbl_circle c\r\n" + 
			"on c.crcl_code=a.crcl_code\r\n" + 
			"where a.status=1",countQuery = "select count(a.RFP_REF_NO) from tbl_allocation a \r\n" + 
					"Inner JOIN tbl_vendor_details v\r\n" + 
					"on v.vendor_id=a.vendor_id\r\n" + 
					"Inner JOIN tbl_circle c\r\n" + 
					"on c.crcl_code=a.crcl_code "+ 
					"where a.status=1",nativeQuery=true)
	Page<DisplayAllocation> findAll(Pageable pageable);

}
