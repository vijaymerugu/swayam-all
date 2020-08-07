package sbi.kiosk.swayam.kioskmanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

//By ankur verma


import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.dto.BillingPurchaseOrderDto;
import sbi.kiosk.swayam.common.entity.BillingAllocation;
import sbi.kiosk.swayam.common.entity.BillingPurchaseOrder;
import sbi.kiosk.swayam.common.entity.User;


	@Repository
	public interface BillingPurchaseOrderRepository extends PagingAndSortingRepository<BillingPurchaseOrder, Integer>{
		
		@Query(value="select ta.rfp_ref_no , ta.vendor_id ,ta.crcl_code ,ta.allocated_quantity ,tp.po_number ,ta.remaining_quantity,tp.updated_date" + 
				"from tbl_allocation ta left join tbl_purchase_order tp on ta.alloc_id = ta.alloc_id  where ta.status ='1';",nativeQuery=true)
	
		Page<BillingPurchaseOrder> findAll(Pageable pageable);

		
		
	}


