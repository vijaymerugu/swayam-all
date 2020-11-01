package sbi.kiosk.swayam.billingpayment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.InvoiceCompare;
import sbi.kiosk.swayam.common.entity.RfpIdMaster;


@Repository
public interface RfpRepository extends CrudRepository<RfpIdMaster, String>,
					PagingAndSortingRepository<RfpIdMaster, String>  {

	
	/*
	 * @Query(value = "select DISTINCT RFP_NO from TBL_RFP_DETAILS", nativeQuery =
	 * true) List<RfpIdMaster> findRfpNumber();
	 */
	  
	  
	 
}
