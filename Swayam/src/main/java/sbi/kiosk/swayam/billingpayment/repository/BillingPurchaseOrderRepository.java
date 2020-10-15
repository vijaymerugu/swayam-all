 package sbi.kiosk.swayam.billingpayment.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sbi.kiosk.swayam.common.entity.BillingPurchaseOrder;

@Repository
public interface BillingPurchaseOrderRepository extends PagingAndSortingRepository<BillingPurchaseOrder, Integer> {

	
	
	@Query(value = "SELECT MAX(PO_NUMBER)\r\n" + 
			" FROM tbl_purchase_order\r\n" + 
			" WHERE STATUS='G'"
			,nativeQuery = true)
	int findLastPO();
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE tbl_purchase_order p \r\n" + 
			"SET p.PO_NUMBER=:poNumber \r\n" + 
			"WHERE p.PO_ID IN (:poId)",nativeQuery = true)
	int updatePoNumber(@Param("poNumber")int poNumber,@Param("poId")List<Integer> poId);
	
	
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE tbl_purchase_order p \r\n" + 
			"SET p.STATUS='G', p.UPDATED_DATE=:updateDate" + 
			" WHERE p.PO_ID IN (:poIds)",nativeQuery = true)
	int updatePoStatus(@Param("poIds")List<Integer> poIds,@Param("updateDate") Date updateDate);
}
