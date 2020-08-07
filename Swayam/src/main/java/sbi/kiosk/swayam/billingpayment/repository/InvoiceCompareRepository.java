package sbi.kiosk.swayam.billingpayment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.InvoiceCompare;
import sbi.kiosk.swayam.common.entity.InvoiceGeneration;

@Repository
public interface InvoiceCompareRepository extends PagingAndSortingRepository<InvoiceCompare, String>{
	
	
	@Query(value = "select s1.RFP_REF_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s3.KIOSK_SERIAL_NO,s3.TIME_PERIOD,s3.INVOICE_AMOUNT,s4.INVO_AMT,(s3.INVOICE_AMOUNT - s4.INVO_AMT) as DIFFERENCE from\r\n" + 
			"                        (select v.PRN_SRN,v.INVO_AMT from TBL_INVOICE_VENDOR v) s4\r\n" + 
			"                        JOIN\r\n" + 
			"                        ((select d.KIOSK_ID,d.KIOSK_SERIAL_NO ,d.TIME_PERIOD, d.PENALTY, d.SPARE_PARTS, d.INVOICE_AMOUNT, \r\n" + 
			"						d.CORRECTIONS, d.FINAL_AMOUNT from TBL_INVOICE d where  d.TIME_PERIOD=:quterTimePeriod) s3 \r\n" + 
			"								JOIN \r\n" + 
			"								((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.RFP_REF_NO,k.KIOSK_SERIAL_NO from TBL_KIOSK_MASTER k where k.VENDOR In  \r\n" + 
			"								(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId)) s1 \r\n" + 
			"								JOIN  \r\n" + 
			"								(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId AND STAT_CODE=:selectedStateId) s2 \r\n" + 
			"								ON  s1.BRANCH_CODE = s2.BRANCH_CODE)\r\n" + 
			"			                    ON s1.KIOSK_ID=s3.KIOSK_ID)\r\n" + 
			"                                ON s4.PRN_SRN=s3.KIOSK_SERIAL_NO",nativeQuery = true,
			countQuery = "select count(s4.PRN_SRN) from\r\n" + 
					"                        (select v.PRN_SRN,v.INVO_AMT from TBL_INVOICE_VENDOR v) s4\r\n" + 
					"                        JOIN\r\n" + 
					"                        ((select d.KIOSK_ID,d.KIOSK_SERIAL_NO ,d.TIME_PERIOD, d.PENALTY, d.SPARE_PARTS, d.INVOICE_AMOUNT, \r\n" + 
					"						d.CORRECTIONS, d.FINAL_AMOUNT from TBL_INVOICE d where  d.TIME_PERIOD=:quterTimePeriod) s3 \r\n" + 
					"								JOIN \r\n" + 
					"								((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.RFP_REF_NO,k.KIOSK_SERIAL_NO from TBL_KIOSK_MASTER k where k.VENDOR In  \r\n" + 
					"								(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId)) s1 \r\n" + 
					"								JOIN  \r\n" + 
					"								(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId AND STAT_CODE=:selectedStateId) s2 \r\n" + 
					"								ON  s1.BRANCH_CODE = s2.BRANCH_CODE)\r\n" + 
					"			                    ON s1.KIOSK_ID=s3.KIOSK_ID)\r\n" + 
					"                                ON s4.PRN_SRN=s3.KIOSK_SERIAL_NO")
	Page<InvoiceCompare> findbyFilter(@Param("selectedCircelId")
    String selectedCircelId, @Param("selectedStateId") String selectedStateId,
    @Param("quterTimePeriod") String quterTimePeriod,
    @Param("selectedVendorId") String selectedVendorId,Pageable pageable);
	
	
	
	@Query(value = "select s1.RFP_REF_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s3.KIOSK_SERIAL_NO,s3.TIME_PERIOD,s3.INVOICE_AMOUNT,s4.INVO_AMT,(s3.INVOICE_AMOUNT - s4.INVO_AMT) as DIFFERENCE from\r\n" + 
			"                        (select v.PRN_SRN,v.INVO_AMT from TBL_INVOICE_VENDOR v) s4\r\n" + 
			"                        JOIN\r\n" + 
			"                        ((select d.KIOSK_ID,d.KIOSK_SERIAL_NO ,d.TIME_PERIOD, d.PENALTY, d.SPARE_PARTS, d.INVOICE_AMOUNT, \r\n" + 
			"						d.CORRECTIONS, d.FINAL_AMOUNT from TBL_INVOICE d where  d.TIME_PERIOD=:quterTimePeriod) s3 \r\n" + 
			"								JOIN \r\n" + 
			"								((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.RFP_REF_NO,k.KIOSK_SERIAL_NO from TBL_KIOSK_MASTER k where k.VENDOR In  \r\n" + 
			"								(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId)) s1 \r\n" + 
			"								JOIN  \r\n" + 
			"								(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId) s2 \r\n" + 
			"								ON  s1.BRANCH_CODE = s2.BRANCH_CODE)\r\n" + 
			"			                    ON s1.KIOSK_ID=s3.KIOSK_ID)\r\n" + 
			"                                ON s4.PRN_SRN=s3.KIOSK_SERIAL_NO",nativeQuery = true,
			countQuery = "select count(s4.PRN_SRN) from\r\n" + 
					"                        (select v.PRN_SRN,v.INVO_AMT from TBL_INVOICE_VENDOR v) s4\r\n" + 
					"                        JOIN\r\n" + 
					"                        ((select d.KIOSK_ID,d.KIOSK_SERIAL_NO ,d.TIME_PERIOD, d.PENALTY, d.SPARE_PARTS, d.INVOICE_AMOUNT, \r\n" + 
					"						d.CORRECTIONS, d.FINAL_AMOUNT from TBL_INVOICE d where  d.TIME_PERIOD=:quterTimePeriod) s3 \r\n" + 
					"								JOIN \r\n" + 
					"								((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.RFP_REF_NO,k.KIOSK_SERIAL_NO from TBL_KIOSK_MASTER k where k.VENDOR In  \r\n" + 
					"								(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId)) s1 \r\n" + 
					"								JOIN  \r\n" + 
					"								(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId) s2 \r\n" + 
					"								ON  s1.BRANCH_CODE = s2.BRANCH_CODE)\r\n" + 
					"			                    ON s1.KIOSK_ID=s3.KIOSK_ID)\r\n" + 
					"                                ON s4.PRN_SRN=s3.KIOSK_SERIAL_NO")
	Page<InvoiceCompare> findbyWithoutStateFilter(@Param("selectedCircelId")
    String selectedCircelId,
    @Param("quterTimePeriod") String quterTimePeriod,
    @Param("selectedVendorId") String selectedVendorId,Pageable pageable);
	
	
	@Query(value = "select s1.RFP_REF_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s3.KIOSK_SERIAL_NO,s3.TIME_PERIOD, s3.INVOICE_AMOUNT,s4.INVO_AMT,(s3.INVOICE_AMOUNT - s4.INVO_AMT) as DIFFERENCE from\r\n" + 
			"                        (select v.PRN_SRN,v.INVO_AMT from TBL_INVOICE_VENDOR v) s4\r\n" + 
			"                        JOIN\r\n" + 
			"                        ((select d.KIOSK_ID,d.KIOSK_SERIAL_NO ,d.TIME_PERIOD, d.PENALTY, d.SPARE_PARTS, d.INVOICE_AMOUNT, \r\n" + 
			"						d.CORRECTIONS, d.FINAL_AMOUNT from TBL_INVOICE d where  d.TIME_PERIOD=:quterTimePeriod) s3 \r\n" + 
			"								JOIN \r\n" + 
			"								((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.RFP_REF_NO,k.KIOSK_SERIAL_NO from TBL_KIOSK_MASTER k where k.VENDOR In  \r\n" + 
			"								(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId) AND k.RFP_REF_NO=:selectedRfpID) s1 \r\n" + 
			"								JOIN  \r\n" + 
			"								(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId AND STAT_CODE=:selectedStateId) s2 \r\n" + 
			"								ON  s1.BRANCH_CODE = s2.BRANCH_CODE)\r\n" + 
			"			                    ON s1.KIOSK_ID=s3.KIOSK_ID)\r\n" + 
			"                                ON s4.PRN_SRN=s3.KIOSK_SERIAL_NO",nativeQuery = true,
			countQuery = "select count(s4.PRN_SRN) from\r\n" + 
					"                        (select v.PRN_SRN,v.INVO_AMT from TBL_INVOICE_VENDOR v) s4\r\n" + 
					"                        JOIN\r\n" + 
					"                        ((select d.KIOSK_ID,d.KIOSK_SERIAL_NO ,d.TIME_PERIOD, d.PENALTY, d.SPARE_PARTS, d.INVOICE_AMOUNT, \r\n" + 
					"						d.CORRECTIONS, d.FINAL_AMOUNT from TBL_INVOICE d where  d.TIME_PERIOD=:quterTimePeriod) s3 \r\n" + 
					"								JOIN \r\n" + 
					"								((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.RFP_REF_NO,k.KIOSK_SERIAL_NO from TBL_KIOSK_MASTER k where k.VENDOR In  \r\n" + 
					"								(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId) AND k.RFP_REF_NO=:selectedRfpID) s1 \r\n" + 
					"								JOIN  \r\n" + 
					"								(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId AND STAT_CODE=:selectedStateId) s2 \r\n" + 
					"								ON  s1.BRANCH_CODE = s2.BRANCH_CODE)\r\n" + 
					"			                    ON s1.KIOSK_ID=s3.KIOSK_ID)\r\n" + 
					"                                ON s4.PRN_SRN=s3.KIOSK_SERIAL_NO")
	Page<InvoiceCompare> findbyFilterWithRFP(@Param("selectedCircelId")
    String selectedCircelId, @Param("selectedStateId") String selectedStateId,
    @Param("quterTimePeriod") String quterTimePeriod,
    @Param("selectedVendorId") String selectedVendorId, @Param("selectedRfpID")String selectedRfpID, Pageable pageable);
	
	@Query(value = "select s1.RFP_REF_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s3.KIOSK_SERIAL_NO,s3.TIME_PERIOD,s3.INVOICE_AMOUNT,s4.INVO_AMT,(s3.INVOICE_AMOUNT - s4.INVO_AMT) as DIFFERENCE from\r\n" + 
			"                        (select v.PRN_SRN,v.INVO_AMT from TBL_INVOICE_VENDOR v) s4\r\n" + 
			"                        JOIN\r\n" + 
			"                        ((select d.KIOSK_ID,d.KIOSK_SERIAL_NO ,d.TIME_PERIOD, d.PENALTY, d.SPARE_PARTS, d.INVOICE_AMOUNT, \r\n" + 
			"						d.CORRECTIONS, d.FINAL_AMOUNT from TBL_INVOICE d where  d.TIME_PERIOD=:quterTimePeriod) s3 \r\n" + 
			"								JOIN \r\n" + 
			"								((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.RFP_REF_NO,k.KIOSK_SERIAL_NO from TBL_KIOSK_MASTER k where k.VENDOR In  \r\n" + 
			"								(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId) AND k.RFP_REF_NO=:selectedRfpID) s1 \r\n" + 
			"								JOIN  \r\n" + 
			"								(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId) s2 \r\n" + 
			"								ON  s1.BRANCH_CODE = s2.BRANCH_CODE)\r\n" + 
			"			                    ON s1.KIOSK_ID=s3.KIOSK_ID)\r\n" + 
			"                                ON s4.PRN_SRN=s3.KIOSK_SERIAL_NO",nativeQuery = true,
			countQuery = "select count(s4.PRN_SRN) from\r\n" + 
					"                        (select v.PRN_SRN,v.INVO_AMT from TBL_INVOICE_VENDOR v) s4\r\n" + 
					"                        JOIN\r\n" + 
					"                        ((select d.KIOSK_ID,d.KIOSK_SERIAL_NO ,d.TIME_PERIOD, d.PENALTY, d.SPARE_PARTS, d.INVOICE_AMOUNT, \r\n" + 
					"						d.CORRECTIONS, d.FINAL_AMOUNT from TBL_INVOICE d where  d.TIME_PERIOD=:quterTimePeriod) s3 \r\n" + 
					"								JOIN \r\n" + 
					"								((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.RFP_REF_NO,k.KIOSK_SERIAL_NO from TBL_KIOSK_MASTER k where k.VENDOR In  \r\n" + 
					"								(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId) AND k.RFP_REF_NO=:selectedRfpID) s1 \r\n" + 
					"								JOIN  \r\n" + 
					"								(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId) s2 \r\n" + 
					"								ON  s1.BRANCH_CODE = s2.BRANCH_CODE)\r\n" + 
					"			                    ON s1.KIOSK_ID=s3.KIOSK_ID)\r\n" + 
					"                                ON s4.PRN_SRN=s3.KIOSK_SERIAL_NO")
	Page<InvoiceCompare> findbyFilterRfpWithoutState(@Param("selectedCircelId")
    String selectedCircelId,
    @Param("quterTimePeriod") String quterTimePeriod,
    @Param("selectedVendorId") String selectedVendorId, @Param("selectedRfpID")String selectedRfpID, Pageable pageable);


}
