package sbi.kiosk.swayam.billingpayment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.InvoiceCompareDtls;


@Repository
public interface InvoiceCompareFormRepository extends CrudRepository<InvoiceCompareDtls, Integer>,
PagingAndSortingRepository<InvoiceCompareDtls, Integer>  {
	
	@Query(value ="SELECT * FROM TBL_INVOICE_COMPARE_DTLS where REQUEST_ID=:requestId",nativeQuery=true)
	List<InvoiceCompareDtls> findAllByRequestId(@Param("requestId") Integer status);

}
