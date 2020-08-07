package sbi.kiosk.swayam.billingpayment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.RfpIdMaster;


@Repository
public interface RfpRepository extends CrudRepository<RfpIdMaster, String> {
	
	/*
	 * @Query(value = "select * from TBL_RFP_DETAILS", nativeQuery = true)
	 * List<RfpIdMaster> findRfId();
	 */
}
