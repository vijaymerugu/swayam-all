package sbi.kiosk.swayam.billingpayment.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.PurchaseOrder;

@Repository
public interface PurchaseOrderRepository extends PagingAndSortingRepository<PurchaseOrder, Integer> {
	
	
	@Query(value = "select p.PO_ID,a.ALLOC_ID,p.po_number, a.RFP_REF_NO,TO_CHAR(R.RFP_DATE, 'dd-MM-yyyy')as RFPDATE,v.COMPANY_SHORT_NM, c.CRCL_NAME, a.ALLOCATED_QUANTITY,a.vendor_id,a.crcl_code,p.quantity,v.CONTACT_ADDRESS,v.CONTACT_NAME,a.remaining_quantity,TO_CHAR(p.PO_DATE,'dd-MM-yy') as PODATE,p.STATUS from tbl_allocation a \r\n" + 
			"Inner JOIN tbl_vendor_details v\r\n" + 
			"on v.vendor_id=a.vendor_id\r\n" + 
			"Inner JOIN tbl_circle c\r\n" + 
			"on c.crcl_code=a.crcl_code\r\n" + 
			"inner join tbl_purchase_order p\r\n" + 
			"on a.alloc_id=p.alloc_id\r\n" + 
			"inner join TBL_RFP_DETAILS R      \r\n" + 
			"on a.RFP_REF_NO=R.RFP_NO  \r\n" + 
			"where p.status='S'",countQuery = "select count(a.PO_ID) from tbl_allocation a \r\n" + 
					"Inner JOIN tbl_vendor_details v\r\n" + 
					"on v.vendor_id=a.vendor_id\r\n" + 
					"Inner JOIN tbl_circle c\r\n" + 
					"on c.crcl_code=a.crcl_code\r\n" + 
					"inner join tbl_purchase_order p\r\n" + 
					"on a.alloc_id=p.alloc_id\r\n" + 
					"inner join TBL_RFP_DETAILS R      \r\n" + 
					"on a.RFP_REF_NO=R.RFP_NO  \r\n" + 
					"where p.status='S'" ,nativeQuery = true)
	Page<PurchaseOrder> findPagePurchaseOrder(Pageable pageable);
	
	
	@Query(value = "select p.PO_ID,a.ALLOC_ID,p.po_number, a.RFP_REF_NO, TO_CHAR(R.RFP_DATE, 'dd-MM-yyyy')as RFPDATE, v.COMPANY_SHORT_NM, c.CRCL_NAME, a.ALLOCATED_QUANTITY,a.vendor_id,a.crcl_code,p.quantity,v.CONTACT_ADDRESS,v.CONTACT_NAME,a.remaining_quantity,TO_CHAR(p.PO_DATE,'dd-MM-yy') as PODATE,p.STATUS from tbl_allocation a      \r\n" + 
			"			 			 			Inner JOIN tbl_vendor_details v     \r\n" + 
			"			 			 			on v.vendor_id=a.vendor_id     \r\n" + 
			"			 			 			Inner JOIN tbl_circle c     \r\n" + 
			"			 			 			on c.crcl_code=a.crcl_code     \r\n" + 
			"			 			 			inner join tbl_purchase_order p     \r\n" + 
			"			 			 			on a.alloc_id=p.alloc_id  \r\n" + 
			"									inner join TBL_RFP_DETAILS R      \r\n" + 
			"			 			 			on a.RFP_REF_NO=R.RFP_NO  \r\n" + 
			"			 			 			where p.status='S' AND p.PO_ID IN (:poId)",countQuery ="select count(p.poId) from tbl_allocation a  \r\n" + 
					"			Inner JOIN tbl_vendor_details v \r\n" + 
					"			on v.vendor_id=a.vendor_id \r\n" + 
					"			Inner JOIN tbl_circle c \r\n" + 
					"			on c.crcl_code=a.crcl_code \r\n" + 
					"			inner join tbl_purchase_order p \r\n" + 
					"			on a.alloc_id=p.alloc_id \r\n" + 
					"           inner join TBL_RFP_DETAILS R  \r\n" + 
					"			 on a.RFP_REF_NO=R.RFP_NO  \r\n" + 
					"			where a.status=1 AND p.PO_ID IN (:poId)",nativeQuery = true)
	List<PurchaseOrder> findPoReport(@Param("poId")List<String> poId);

}
