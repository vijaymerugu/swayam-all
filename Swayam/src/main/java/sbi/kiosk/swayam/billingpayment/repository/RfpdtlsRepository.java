package sbi.kiosk.swayam.billingpayment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.InvoiceCompareDtls;
import sbi.kiosk.swayam.common.entity.RfpDetails;


@Repository
public interface RfpdtlsRepository extends CrudRepository<RfpDetails, String>,
					PagingAndSortingRepository<RfpDetails, String>  {
	
	@Query(value ="SELECT * FROM TBL_RFP_REQUEST_DTLS where REQUEST_ID=:requestId",nativeQuery=true)
	List<RfpDetails> findAllByRequestId(@Param("requestId") Integer status);

	
	  
	 
}
