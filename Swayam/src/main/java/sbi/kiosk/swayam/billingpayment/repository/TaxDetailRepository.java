package sbi.kiosk.swayam.billingpayment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.InvoiceCompareDtls;
import sbi.kiosk.swayam.common.entity.TaxDetailEnity;

@Repository
public interface TaxDetailRepository extends CrudRepository<TaxDetailEnity, String>,
PagingAndSortingRepository<TaxDetailEnity, String>  {
	
	@Query(value ="SELECT * FROM TBL_TAX_REQUEST_DTLS where REQUEST_ID=:requestId",nativeQuery=true)
	List<TaxDetailEnity> findAllByRequestId(@Param("requestId") Integer status);

}
