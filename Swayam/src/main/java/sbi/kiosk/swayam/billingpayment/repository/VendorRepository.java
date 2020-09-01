package sbi.kiosk.swayam.billingpayment.repository;

import java.util.List;

import org.jboss.logging.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import sbi.kiosk.swayam.common.entity.BranchEntity;
import sbi.kiosk.swayam.common.entity.VendorMaster;

public interface VendorRepository extends CrudRepository<VendorMaster, Integer> {
	
	@Query(value = "SELECT v.VENDOR_ID, v.COMPANY_SHORT_NM FROM TBL_VENDOR_DETAILS v",nativeQuery = true)
	List<VendorMaster> findVendors();

}
