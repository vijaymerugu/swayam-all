package sbi.kiosk.swayam.kioskmanagement.repository;

import org.springframework.data.jpa.repository.Query;

//By Pankul 28-04-2020-----------STARTS---------

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sbi.kiosk.swayam.common.entity.InvoiceVendor;

	@Repository
	public interface InvoiceVendorRepository extends CrudRepository<InvoiceVendor, String>{
		
		@Query(value="SELECT PRN_SRN FROM TBL_INVOICE_VENDOR WHERE PRN_SRN=:prnSrn and INVO_FROM=:invoiceFrom and INVO_UPTO=:invoiceUpTo",nativeQuery=true)
		String findDuplicate(@Param("prnSrn") String prnSrn ,@Param("invoiceFrom") String invoiceFrom ,@Param("invoiceUpTo") String invoiceUpTo);

	}
