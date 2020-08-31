package sbi.kiosk.swayam.billingpayment.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sbi.kiosk.swayam.common.entity.Invoice;

@Repository
public interface InvoiceCorrectionRepository extends CrudRepository<Invoice, String> {
		
		@Modifying
		@Transactional
		@Query(value = "UPDATE 	TBL_INVOICE i SET i.CORRECTION_AMT=:correction,i.FINAL_AMT = (i.INVOICE_AMT) + (:correction),i.REMARKS=:remarks "
				+ "WHERE i.KIOSK_ID=:kisokId AND i.KIOSK_SERIAL_NO=:kisokSerialNumber "
				+ "AND i.QTR_ID=:quarter AND i.FIN_YR=:year",nativeQuery = true)
		int updateInvoiceCorrection(@Param("correction") double correction,
				@Param("kisokId") String kisokId, @Param("kisokSerialNumber")String kisokSerialNumber, 
				@Param("remarks")String remarks,@Param("quarter")String quarter,@Param("year")String year); 
}
