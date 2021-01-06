package sbi.kiosk.swayam.billingpayment.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.FormDetail;

@Repository
public interface FormDetailRepository extends CrudRepository<FormDetail, Integer> {
	
	
	
	@Query(value = "select count(k.kiosk_id)\r\n" + 
			"from tbl_kiosk_master k\r\n" + 
			"inner Join tbl_branch_master bm\r\n" + 
			"on k.branch_code=bm.branch_code \r\n" + 
			"where bm.crcl_code=:selectedCircelId and\r\n" + 
			"    bm.stat_code=:selectedStateId and\r\n" + 
			"    k.vendor= (select COMPANY_SHORT_NM from tbl_vendor_details where VENDOR_ID=:selectedVendorId)", nativeQuery =true)
	int findKioskCount(@Param("selectedCircelId")
    String selectedCircelId, @Param("selectedStateId") String selectedStateId,
    @Param("selectedVendorId") String selectedVendorId);
	
	
	
	@Query(value = "select count(inv.kiosk_id) \r\n" + 
			"			from TBL_INVOICE inv\r\n" + 
			"            inner join tbl_kiosk_master k \r\n" + 
			"            on inv.kiosk_id =k.kiosk_id\r\n" + 
			"			inner Join tbl_branch_master bm \r\n" + 
			"			on k.branch_code=bm.branch_code  \r\n" + 
			"			where bm.crcl_code=:selectedCircelId and \r\n" + 
			"			bm.stat_code=:selectedStateId and \r\n" + 
			"			k.vendor= :selectedVendorId and \r\n" + 
			"            inv.fin_yr=:selectedYear and \r\n" + 
			"            inv.qtr_id=:selectedQtr", nativeQuery =true)
	int findCount(@Param("selectedCircelId")
    String selectedCircelId, @Param("selectedStateId") String selectedStateId,
    @Param("selectedVendorId") String selectedVendorId, @Param("selectedYear") String selectedYear,
    @Param("selectedQtr") String selectedQtr);
	

}
