package sbi.kiosk.swayam.billingpayment.repository;

import java.util.List;

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
	
	
	//Sharan Change -07-11-2020
	
	@Query(value = "select s6.RFP_NO,s1.VENDOR,  \r\n" + 
			"			s2.CRCL_NAME, \r\n" + 
			"			s2.STAT_DESC, \r\n" + 
			"			s3.KIOSK_ID, \r\n" + 
			"			s3.KIOSK_SERIAL_NO,\r\n" + 
			"			s3.QTR_ID,\r\n" + 
			"			s3.FIN_YR,\r\n" + 
			"			s3.AMC_COST,\r\n" + 
			"			s5.AMC_AMT,\r\n" + 
			"			(s3.PENALTY_AMT - s3.CORRECTION_AMT) AS PENALTY_SBI_AMT , s3.CORRECTION_AMT ,\r\n" + 
			"			s5.PENALTY_AMT as VENDOR_PENALTY_AMT,\r\n" + 
			"			ABS((s3.PENALTY_AMT - s3.CORRECTION_AMT) - s5.PENALTY_AMT) as DIFFERENCE  \r\n" + 
			"			from TBL_INVOICE s3 \r\n" + 
			"			INNER JOIN TBL_KIOSK_MASTER s1 \r\n" + 
			"			ON s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO \r\n" + 
			"			INNER JOIN TBL_VENDOR_DETAILS s4 \r\n" + 
			"			ON s1.VENDOR=s4.COMPANY_SHORT_NM \r\n" + 
			"			INNER JOIN TBL_BRANCH_MASTER s2 \r\n" + 
			"			ON s1.BRANCH_CODE = s2.BRANCH_CODE \r\n" + 
			"			INNER JOIN TBL_INVOICE_VENDOR s5 \r\n" + 
			"			ON s5.PRN_SRN=s3.KIOSK_SERIAL_NO \r\n" + 
			"			INNER JOIN TBL_RFP_DETAILS s6 \r\n" + 
			"			ON s6.RFP_ID=s1.RFP_ID  AND \r\n" + 
			"			s6.vendor=s1.vendor \r\n" + 
			"			where   \r\n" + 
			"			s4.VENDOR_ID=:selectedVendorId AND \r\n" + 
			"			s3.FIN_YR=:finacialYear AND \r\n" + 
			"			s3.QTR_ID=:quterTimePeriod",nativeQuery = true,
			countQuery = "select count(s5.PRN_SRN) \r\n" + 
					"from TBL_INVOICE s3\r\n" + 
					"INNER JOIN TBL_KIOSK_MASTER s1\r\n" + 
					"ON s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO\r\n" + 
					"INNER JOIN TBL_VENDOR_DETAILS s4\r\n" + 
					"ON s1.VENDOR=s4.COMPANY_SHORT_NM\r\n" + 
					"INNER JOIN TBL_BRANCH_MASTER s2\r\n" + 
					"ON s1.BRANCH_CODE = s2.BRANCH_CODE\r\n" + 
					"INNER JOIN TBL_INVOICE_VENDOR s5\r\n" + 
					"			ON s5.PRN_SRN=s3.KIOSK_SERIAL_NO INNER JOIN TBL_RFP_DETAILS s6 ON s6.RFP_ID=s1.RFP_ID  AND s6.vendor=s1.vendor \r\n" + 
					" where \r\n" + 
					"s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
					"s3.FIN_YR=:finacialYear AND\r\n" + 
					"s3.QTR_ID=:quterTimePeriod")
	Page<InvoiceCompare> findbyWithoutStateFilterCC(
   @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId,Pageable pageable);
	
	
	
	@Query(value = "select s6.RFP_NO,s1.VENDOR,  \r\n" + 
			"			s2.CRCL_NAME, \r\n" + 
			"			s2.STAT_DESC, \r\n" + 
			"			s3.KIOSK_ID, \r\n" + 
			"			s3.KIOSK_SERIAL_NO,\r\n" + 
			"			s3.QTR_ID,\r\n" + 
			"			s3.FIN_YR,\r\n" + 
			"			s3.AMC_COST,\r\n" + 
			"			s5.AMC_AMT,\r\n" + 
			"			(s3.PENALTY_AMT - s3.CORRECTION_AMT) AS PENALTY_SBI_AMT , s3.CORRECTION_AMT ,\r\n" + 
			"			s5.PENALTY_AMT as VENDOR_PENALTY_AMT,\r\n" + 
			"			ABS((s3.PENALTY_AMT - s3.CORRECTION_AMT) - s5.PENALTY_AMT) as DIFFERENCE  \r\n" + 
			"			from TBL_INVOICE s3 \r\n" + 
			"			INNER JOIN TBL_KIOSK_MASTER s1 \r\n" + 
			"			ON s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO \r\n" + 
			"			INNER JOIN TBL_VENDOR_DETAILS s4 \r\n" + 
			"			ON s1.VENDOR=s4.COMPANY_SHORT_NM \r\n" + 
			"			INNER JOIN TBL_BRANCH_MASTER s2 \r\n" + 
			"			ON s1.BRANCH_CODE = s2.BRANCH_CODE \r\n" + 
			"			INNER JOIN TBL_INVOICE_VENDOR s5 \r\n" + 
			"			ON s5.PRN_SRN=s3.KIOSK_SERIAL_NO \r\n" + 
			"			INNER JOIN TBL_RFP_DETAILS s6 \r\n" + 
			"			ON s6.RFP_ID=s1.RFP_ID  AND \r\n" + 
			"			s6.vendor=s1.vendor \r\n" + 
			"			 where  \r\n" + 
			"			s4.VENDOR_ID=:selectedVendorId AND \r\n" + 
			"			s3.FIN_YR=:finacialYear AND \r\n" + 
			"			s3.QTR_ID=:quterTimePeriod AND \r\n" + 
			"			s6.RFP_NO=:selectedRfpID",nativeQuery = true,
			countQuery = "select count(s5.PRN_SRN) \r\n" + 
					"from TBL_INVOICE s3\r\n" + 
					"INNER JOIN TBL_KIOSK_MASTER s1\r\n" + 
					"ON s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO\r\n" + 
					"INNER JOIN TBL_VENDOR_DETAILS s4\r\n" + 
					"ON s1.VENDOR=s4.COMPANY_SHORT_NM\r\n" + 
					"INNER JOIN TBL_BRANCH_MASTER s2\r\n" + 
					"ON s1.BRANCH_CODE = s2.BRANCH_CODE\r\n" + 
					"INNER JOIN TBL_INVOICE_VENDOR s5\r\n" + 
					"			ON s5.PRN_SRN=s3.KIOSK_SERIAL_NO INNER JOIN TBL_RFP_DETAILS s6 ON s6.RFP_ID=s1.RFP_ID  AND s6.vendor=s1.vendor \r\n" + 
					" where \r\n" + 
					"s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
					"s3.FIN_YR=:finacialYear AND\r\n" + 
					"s3.QTR_ID=:quterTimePeriod AND\r\n" + 
					"s6.RFP_NO=:selectedRfpID")
	Page<InvoiceCompare> findbyFilterRfpWithoutStateCC(
   @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId, @Param("selectedRfpID")String selectedRfpID, Pageable pageable);
	
	
	

	@Query(value = "select s6.RFP_NO,s1.VENDOR,  \r\n" + 
			"			s2.CRCL_NAME, \r\n" + 
			"			s2.STAT_DESC, \r\n" + 
			"			s3.KIOSK_ID, \r\n" + 
			"			s3.KIOSK_SERIAL_NO,\r\n" + 
			"			s3.QTR_ID,\r\n" + 
			"			s3.FIN_YR,\r\n" + 
			"			s3.AMC_COST,\r\n" + 
			"			s5.AMC_AMT,\r\n" + 
			"			(s3.PENALTY_AMT - s3.CORRECTION_AMT) AS PENALTY_SBI_AMT , s3.CORRECTION_AMT ,\r\n" + 
			"			s5.PENALTY_AMT as VENDOR_PENALTY_AMT,\r\n" + 
			"			ABS((s3.PENALTY_AMT - s3.CORRECTION_AMT) - s5.PENALTY_AMT) as DIFFERENCE  \r\n" + 
			"			from TBL_INVOICE s3 \r\n" + 
			"			INNER JOIN TBL_KIOSK_MASTER s1 \r\n" + 
			"			ON s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO \r\n" + 
			"			INNER JOIN TBL_VENDOR_DETAILS s4 \r\n" + 
			"			ON s1.VENDOR=s4.COMPANY_SHORT_NM \r\n" + 
			"			INNER JOIN TBL_BRANCH_MASTER s2 \r\n" + 
			"			ON s1.BRANCH_CODE = s2.BRANCH_CODE \r\n" + 
			"			INNER JOIN TBL_INVOICE_VENDOR s5 \r\n" + 
			"			ON s5.PRN_SRN=s3.KIOSK_SERIAL_NO \r\n" + 
			"			INNER JOIN TBL_RFP_DETAILS s6 \r\n" + 
			"			ON s6.RFP_ID=s1.RFP_ID  AND \r\n" + 
			"			s6.vendor=s1.vendor \r\n" +  
			"			where  \r\n" + 
			"			s4.VENDOR_ID=:selectedVendorId AND \r\n" + 
			"			s2.CRCL_CODE=:selectedCircelId AND  \r\n" + 
			"			s2.STAT_CODE=:selectedStateId AND \r\n" + 
			"			s3.FIN_YR=:finacialYear AND \r\n" + 
			"			s3.QTR_ID=:quterTimePeriod",nativeQuery = true,
			countQuery = "select count(s5.PRN_SRN) \r\n" + 
					"from TBL_INVOICE s3\r\n" + 
					"INNER JOIN TBL_KIOSK_MASTER s1\r\n" + 
					"ON s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO\r\n" + 
					"INNER JOIN TBL_VENDOR_DETAILS s4\r\n" + 
					"ON s1.VENDOR=s4.COMPANY_SHORT_NM\r\n" + 
					"INNER JOIN TBL_BRANCH_MASTER s2\r\n" + 
					"ON s1.BRANCH_CODE = s2.BRANCH_CODE\r\n" + 
					"INNER JOIN TBL_INVOICE_VENDOR s5\r\n" + 
					"			ON s5.PRN_SRN=s3.KIOSK_SERIAL_NO INNER JOIN TBL_RFP_DETAILS s6 ON s6.RFP_ID=s1.RFP_ID  AND s6.vendor=s1.vendor \r\n" + 
					" where \r\n" + 
					"s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
					"s2.CRCL_CODE=:selectedCircelId AND \r\n" + 
					"s2.STAT_CODE=:selectedStateId AND\r\n" + 
					"s3.FIN_YR=:finacialYear AND\r\n" + 
					"s3.QTR_ID=:quterTimePeriod")
	Page<InvoiceCompare> findbyFilter(@Param("selectedCircelId")
    String selectedCircelId, @Param("selectedStateId") String selectedStateId,
   @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId,Pageable pageable);
	
	
	
	@Query(value = "select s6.RFP_NO,s1.VENDOR,  \r\n" + 
			"			s2.CRCL_NAME, \r\n" + 
			"			s2.STAT_DESC, \r\n" + 
			"			s3.KIOSK_ID, \r\n" + 
			"			s3.KIOSK_SERIAL_NO,\r\n" + 
			"			s3.QTR_ID,\r\n" + 
			"			s3.FIN_YR,\r\n" + 
			"			s3.AMC_COST,\r\n" + 
			"			s5.AMC_AMT,\r\n" + 
			"			(s3.PENALTY_AMT - s3.CORRECTION_AMT) AS PENALTY_SBI_AMT , s3.CORRECTION_AMT ,\r\n" + 
			"			s5.PENALTY_AMT as VENDOR_PENALTY_AMT,\r\n" + 
			"			ABS((s3.PENALTY_AMT - s3.CORRECTION_AMT) - s5.PENALTY_AMT) as DIFFERENCE  \r\n" + 
			"			from TBL_INVOICE s3 \r\n" + 
			"			INNER JOIN TBL_KIOSK_MASTER s1 \r\n" + 
			"			ON s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO \r\n" + 
			"			INNER JOIN TBL_VENDOR_DETAILS s4 \r\n" + 
			"			ON s1.VENDOR=s4.COMPANY_SHORT_NM \r\n" + 
			"			INNER JOIN TBL_BRANCH_MASTER s2 \r\n" + 
			"			ON s1.BRANCH_CODE = s2.BRANCH_CODE \r\n" + 
			"			INNER JOIN TBL_INVOICE_VENDOR s5 \r\n" + 
			"			ON s5.PRN_SRN=s3.KIOSK_SERIAL_NO \r\n" + 
			"			INNER JOIN TBL_RFP_DETAILS s6 \r\n" + 
			"			ON s6.RFP_ID=s1.RFP_ID  AND \r\n" + 
			"			s6.vendor=s1.vendor \r\n" + 
			"			where  \r\n" + 
			"			s4.VENDOR_ID=:selectedVendorId AND \r\n" + 
			"			s2.CRCL_CODE=:selectedCircelId AND \r\n" + 
			"			s3.FIN_YR=:finacialYear AND \r\n" + 
			"			s3.QTR_ID=:quterTimePeriod",nativeQuery = true,
			countQuery = "select count(s5.PRN_SRN) \r\n" + 
					"from TBL_INVOICE s3\r\n" + 
					"INNER JOIN TBL_KIOSK_MASTER s1\r\n" + 
					"ON s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO\r\n" + 
					"INNER JOIN TBL_VENDOR_DETAILS s4\r\n" + 
					"ON s1.VENDOR=s4.COMPANY_SHORT_NM\r\n" + 
					"INNER JOIN TBL_BRANCH_MASTER s2\r\n" + 
					"ON s1.BRANCH_CODE = s2.BRANCH_CODE\r\n" + 
					"INNER JOIN TBL_INVOICE_VENDOR s5\r\n" + 
					"			ON s5.PRN_SRN=s3.KIOSK_SERIAL_NO INNER JOIN TBL_RFP_DETAILS s6 ON s6.RFP_ID=s1.RFP_ID  AND s6.vendor=s1.vendor \r\n" + 
					" where \r\n" + 
					"s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
					"s2.CRCL_CODE=:selectedCircelId AND \r\n" +
					"s3.FIN_YR=:finacialYear AND\r\n" + 
					"s3.QTR_ID=:quterTimePeriod")
	Page<InvoiceCompare> findbyWithoutStateFilter(@Param("selectedCircelId")
    String selectedCircelId,
   @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId,Pageable pageable);
	
	
	@Query(value = "select s6.RFP_NO,s1.VENDOR,  \r\n" + 
			"			s2.CRCL_NAME, \r\n" + 
			"			s2.STAT_DESC, \r\n" + 
			"			s3.KIOSK_ID, \r\n" + 
			"			s3.KIOSK_SERIAL_NO,\r\n" + 
			"			s3.QTR_ID,\r\n" + 
			"			s3.FIN_YR,\r\n" + 
			"			s3.AMC_COST,\r\n" + 
			"			s5.AMC_AMT,\r\n" + 
			"			(s3.PENALTY_AMT - s3.CORRECTION_AMT) AS PENALTY_SBI_AMT , s3.CORRECTION_AMT ,\r\n" + 
			"			s5.PENALTY_AMT as VENDOR_PENALTY_AMT,\r\n" + 
			"			ABS((s3.PENALTY_AMT - s3.CORRECTION_AMT) - s5.PENALTY_AMT) as DIFFERENCE  \r\n" + 
			"			from TBL_INVOICE s3 \r\n" + 
			"			INNER JOIN TBL_KIOSK_MASTER s1 \r\n" + 
			"			ON s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO \r\n" + 
			"			INNER JOIN TBL_VENDOR_DETAILS s4 \r\n" + 
			"			ON s1.VENDOR=s4.COMPANY_SHORT_NM \r\n" + 
			"			INNER JOIN TBL_BRANCH_MASTER s2 \r\n" + 
			"			ON s1.BRANCH_CODE = s2.BRANCH_CODE \r\n" + 
			"			INNER JOIN TBL_INVOICE_VENDOR s5 \r\n" + 
			"			ON s5.PRN_SRN=s3.KIOSK_SERIAL_NO \r\n" + 
			"			INNER JOIN TBL_RFP_DETAILS s6 \r\n" + 
			"			ON s6.RFP_ID=s1.RFP_ID  AND \r\n" + 
			"			s6.vendor=s1.vendor \r\n" + 
			"			where \r\n" + 
			"			s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
			"			s2.CRCL_CODE=:selectedCircelId AND \r\n" + 
			"			s2.STAT_CODE=:selectedStateId AND\r\n" + 
			"			s3.FIN_YR=:finacialYear AND\r\n" + 
			"			s3.QTR_ID=:quterTimePeriod AND\r\n" + 
			"			s6.RFP_NO=:selectedRfpID",nativeQuery = true,
			countQuery = "select count(s5.PRN_SRN) \r\n" + 
					"from TBL_INVOICE s3\r\n" + 
					"INNER JOIN TBL_KIOSK_MASTER s1\r\n" + 
					"ON s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO\r\n" + 
					"INNER JOIN TBL_VENDOR_DETAILS s4\r\n" + 
					"ON s1.VENDOR=s4.COMPANY_SHORT_NM\r\n" + 
					"INNER JOIN TBL_BRANCH_MASTER s2\r\n" + 
					"ON s1.BRANCH_CODE = s2.BRANCH_CODE\r\n" + 
					"INNER JOIN TBL_INVOICE_VENDOR s5\r\n" + 
					"			ON s5.PRN_SRN=s3.KIOSK_SERIAL_NO INNER JOIN TBL_RFP_DETAILS s6 ON s6.RFP_ID=s1.RFP_ID  AND s6.vendor=s1.vendor \r\n" + 
					" where \r\n" + 
					"s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
					"s2.CRCL_CODE=:selectedCircelId AND \r\n" + 
					"s2.STAT_CODE=:selectedStateId AND\r\n" + 
					"s3.FIN_YR=:finacialYear AND\r\n" + 
					"s3.QTR_ID=:quterTimePeriod AND\r\n" + 
					"s6.RFP_NO=:selectedRfpID")
	Page<InvoiceCompare> findbyFilterWithRFP(@Param("selectedCircelId")
    String selectedCircelId, @Param("selectedStateId") String selectedStateId,
   @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear") String finacialYear,
    @Param("selectedVendorId") String selectedVendorId, @Param("selectedRfpID") String selectedRfpID, Pageable pageable);
	
	@Query(value = "select s6.RFP_NO,s1.VENDOR,  \r\n" + 
			"			s2.CRCL_NAME, \r\n" + 
			"			s2.STAT_DESC, \r\n" + 
			"			s3.KIOSK_ID, \r\n" + 
			"			s3.KIOSK_SERIAL_NO,\r\n" + 
			"			s3.QTR_ID,\r\n" + 
			"			s3.FIN_YR,\r\n" + 
			"			s3.AMC_COST,\r\n" + 
			"			s5.AMC_AMT,\r\n" + 
			"			(s3.PENALTY_AMT - s3.CORRECTION_AMT) AS PENALTY_SBI_AMT , s3.CORRECTION_AMT ,\r\n" + 
			"			s5.PENALTY_AMT as VENDOR_PENALTY_AMT,\r\n" + 
			"			ABS((s3.PENALTY_AMT - s3.CORRECTION_AMT) - s5.PENALTY_AMT) as DIFFERENCE  \r\n" + 
			"			from TBL_INVOICE s3 \r\n" + 
			"			INNER JOIN TBL_KIOSK_MASTER s1 \r\n" + 
			"			ON s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO \r\n" + 
			"			INNER JOIN TBL_VENDOR_DETAILS s4 \r\n" + 
			"			ON s1.VENDOR=s4.COMPANY_SHORT_NM \r\n" + 
			"			INNER JOIN TBL_BRANCH_MASTER s2 \r\n" + 
			"			ON s1.BRANCH_CODE = s2.BRANCH_CODE \r\n" + 
			"			INNER JOIN TBL_INVOICE_VENDOR s5 \r\n" + 
			"			ON s5.PRN_SRN=s3.KIOSK_SERIAL_NO \r\n" + 
			"			INNER JOIN TBL_RFP_DETAILS s6 \r\n" + 
			"			ON s6.RFP_ID=s1.RFP_ID  AND \r\n" + 
			"			s6.vendor=s1.vendor \r\n" + 
			"			where  \r\n" + 
			"			s4.VENDOR_ID=:selectedVendorId AND \r\n" + 
			"			s2.CRCL_CODE=:selectedCircelId AND \r\n" + 
			"			s3.FIN_YR=:finacialYear AND \r\n" + 
			"			s3.QTR_ID=:quterTimePeriod AND \r\n" + 
			"			s6.RFP_NO=:selectedRfpID",nativeQuery = true,
			countQuery = "select count(s5.PRN_SRN) \r\n" + 
					"from TBL_INVOICE s3\r\n" + 
					"INNER JOIN TBL_KIOSK_MASTER s1\r\n" + 
					"ON s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO\r\n" + 
					"INNER JOIN TBL_VENDOR_DETAILS s4\r\n" + 
					"ON s1.VENDOR=s4.COMPANY_SHORT_NM\r\n" + 
					"INNER JOIN TBL_BRANCH_MASTER s2\r\n" + 
					"ON s1.BRANCH_CODE = s2.BRANCH_CODE\r\n" + 
					"INNER JOIN TBL_INVOICE_VENDOR s5\r\n" + 
					"			ON s5.PRN_SRN=s3.KIOSK_SERIAL_NO INNER JOIN TBL_RFP_DETAILS s6 ON s6.RFP_ID=s1.RFP_ID  AND s6.vendor=s1.vendor \r\n" + 
					" where \r\n" + 
					"s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
					"s2.CRCL_CODE=:selectedCircelId AND \r\n"+
					"s3.FIN_YR=:finacialYear AND\r\n" + 
					"s3.QTR_ID=:quterTimePeriod AND\r\n" + 
					"s6.RFP_NO=:selectedRfpID")
	Page<InvoiceCompare> findbyFilterRfpWithoutState(@Param("selectedCircelId")
    String selectedCircelId,
   @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId, @Param("selectedRfpID")String selectedRfpID, Pageable pageable);
	

	@Query(value = "select s6.RFP_NO,s1.VENDOR,  \r\n" + 
			"			s2.CRCL_NAME, \r\n" + 
			"			s2.STAT_DESC, \r\n" + 
			"			s3.KIOSK_ID, \r\n" + 
			"			s3.KIOSK_SERIAL_NO,\r\n" + 
			"			s3.QTR_ID,\r\n" + 
			"			s3.FIN_YR,\r\n" + 
			"			s3.AMC_COST,\r\n" + 
			"			s5.AMC_AMT,\r\n" + 
			"			(s3.PENALTY_AMT - s3.CORRECTION_AMT) AS PENALTY_SBI_AMT , s3.CORRECTION_AMT ,\r\n" + 
			"			s5.PENALTY_AMT as VENDOR_PENALTY_AMT,\r\n" + 
			"			ABS((s3.PENALTY_AMT - s3.CORRECTION_AMT) - s5.PENALTY_AMT) as DIFFERENCE  \r\n" + 
			"			from TBL_INVOICE s3 \r\n" + 
			"			INNER JOIN TBL_KIOSK_MASTER s1 \r\n" + 
			"			ON s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO \r\n" + 
			"			INNER JOIN TBL_VENDOR_DETAILS s4 \r\n" + 
			"			ON s1.VENDOR=s4.COMPANY_SHORT_NM \r\n" + 
			"			INNER JOIN TBL_BRANCH_MASTER s2 \r\n" + 
			"			ON s1.BRANCH_CODE = s2.BRANCH_CODE \r\n" + 
			"			INNER JOIN TBL_INVOICE_VENDOR s5 \r\n" + 
			"			ON s5.PRN_SRN=s3.KIOSK_SERIAL_NO \r\n" + 
			"			INNER JOIN TBL_RFP_DETAILS s6 \r\n" + 
			"			ON s6.RFP_ID=s1.RFP_ID  AND \r\n" + 
			"			s6.vendor=s1.vendor \r\n" + 
			"			where  \r\n" + 
			"			s4.VENDOR_ID=:selectedVendorId AND \r\n" + 
			"			s2.CRCL_CODE=:selectedCircelId AND  \r\n" + 
			"			s2.STAT_CODE=:selectedStateId AND \r\n" + 
			"			s3.FIN_YR=:finacialYear AND \r\n" + 
			"			s3.QTR_ID=:quterTimePeriod",nativeQuery = true,
			countQuery = "select count(s5.PRN_SRN) \r\n" + 
					"from TBL_INVOICE s3\r\n" + 
					"INNER JOIN TBL_KIOSK_MASTER s1\r\n" + 
					"ON s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO\r\n" + 
					"INNER JOIN TBL_VENDOR_DETAILS s4\r\n" + 
					"ON s1.VENDOR=s4.COMPANY_SHORT_NM\r\n" + 
					"INNER JOIN TBL_BRANCH_MASTER s2\r\n" + 
					"ON s1.BRANCH_CODE = s2.BRANCH_CODE\r\n" + 
					"INNER JOIN TBL_INVOICE_VENDOR s5\r\n" + 
					"			ON s5.PRN_SRN=s3.KIOSK_SERIAL_NO INNER JOIN TBL_RFP_DETAILS s6 ON s6.RFP_ID=s1.RFP_ID  AND s6.vendor=s1.vendor \r\n" + 
					" where \r\n" + 
					"s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
					"s2.CRCL_CODE=:selectedCircelId AND \r\n" + 
					"s2.STAT_CODE=:selectedStateId AND\r\n" + 
					"s3.FIN_YR=:finacialYear AND\r\n" + 
					"s3.QTR_ID=:quterTimePeriod")
	List<InvoiceCompare> findbyFilterReport(@Param("selectedCircelId")
    String selectedCircelId, @Param("selectedStateId") String selectedStateId,
   @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId);
	
	
	@Query(value = "select s6.RFP_NO,s1.VENDOR,  \r\n" + 
			"			s2.CRCL_NAME, \r\n" + 
			"			s2.STAT_DESC, \r\n" + 
			"			s3.KIOSK_ID, \r\n" + 
			"			s3.KIOSK_SERIAL_NO,\r\n" + 
			"			s3.QTR_ID,\r\n" + 
			"			s3.FIN_YR,\r\n" + 
			"			s3.AMC_COST,\r\n" + 
			"			s5.AMC_AMT,\r\n" + 
			"			(s3.PENALTY_AMT - s3.CORRECTION_AMT) AS PENALTY_SBI_AMT , s3.CORRECTION_AMT ,\r\n" + 
			"			s5.PENALTY_AMT as VENDOR_PENALTY_AMT,\r\n" + 
			"			ABS((s3.PENALTY_AMT - s3.CORRECTION_AMT) - s5.PENALTY_AMT) as DIFFERENCE  \r\n" + 
			"			from TBL_INVOICE s3 \r\n" + 
			"			INNER JOIN TBL_KIOSK_MASTER s1 \r\n" + 
			"			ON s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO \r\n" + 
			"			INNER JOIN TBL_VENDOR_DETAILS s4 \r\n" + 
			"			ON s1.VENDOR=s4.COMPANY_SHORT_NM \r\n" + 
			"			INNER JOIN TBL_BRANCH_MASTER s2 \r\n" + 
			"			ON s1.BRANCH_CODE = s2.BRANCH_CODE \r\n" + 
			"			INNER JOIN TBL_INVOICE_VENDOR s5 \r\n" + 
			"			ON s5.PRN_SRN=s3.KIOSK_SERIAL_NO \r\n" + 
			"			INNER JOIN TBL_RFP_DETAILS s6 \r\n" + 
			"			ON s6.RFP_ID=s1.RFP_ID  AND \r\n" + 
			"			s6.vendor=s1.vendor \r\n" + 
			"			where  \r\n" + 
			"			s4.VENDOR_ID=:selectedVendorId AND \r\n" + 
			"			s2.CRCL_CODE=:selectedCircelId AND \r\n" + 
			"			s3.FIN_YR=:finacialYear AND \r\n" + 
			"			s3.QTR_ID=:quterTimePeriod",nativeQuery = true,
			countQuery = "select count(s5.PRN_SRN) \r\n" + 
					"from TBL_INVOICE s3\r\n" + 
					"INNER JOIN TBL_KIOSK_MASTER s1\r\n" + 
					"ON s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO\r\n" + 
					"INNER JOIN TBL_VENDOR_DETAILS s4\r\n" + 
					"ON s1.VENDOR=s4.COMPANY_SHORT_NM\r\n" + 
					"INNER JOIN TBL_BRANCH_MASTER s2\r\n" + 
					"ON s1.BRANCH_CODE = s2.BRANCH_CODE\r\n" + 
					"INNER JOIN TBL_INVOICE_VENDOR s5\r\n" + 
					"			ON s5.PRN_SRN=s3.KIOSK_SERIAL_NO INNER JOIN TBL_RFP_DETAILS s6 ON s6.RFP_ID=s1.RFP_ID  AND s6.vendor=s1.vendor \r\n" + 
					" where \r\n" + 
					"s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
					"s2.CRCL_CODE=:selectedCircelId AND \r\n" +
					"s3.FIN_YR=:finacialYear AND\r\n" + 
					"s3.QTR_ID=:quterTimePeriod")
	List<InvoiceCompare> findbyWithoutStateFilterReport(@Param("selectedCircelId")
    String selectedCircelId,
   @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId);
	
	
	@Query(value = "select s6.RFP_NO,s1.VENDOR,  \r\n" + 
			"			s2.CRCL_NAME, \r\n" + 
			"			s2.STAT_DESC, \r\n" + 
			"			s3.KIOSK_ID, \r\n" + 
			"			s3.KIOSK_SERIAL_NO,\r\n" + 
			"			s3.QTR_ID,\r\n" + 
			"			s3.FIN_YR,\r\n" + 
			"			s3.AMC_COST,\r\n" + 
			"			s5.AMC_AMT,\r\n" + 
			"			(s3.PENALTY_AMT - s3.CORRECTION_AMT) AS PENALTY_SBI_AMT , s3.CORRECTION_AMT ,\r\n" + 
			"			s5.PENALTY_AMT as VENDOR_PENALTY_AMT,\r\n" + 
			"			ABS((s3.PENALTY_AMT - s3.CORRECTION_AMT) - s5.PENALTY_AMT) as DIFFERENCE  \r\n" + 
			"			from TBL_INVOICE s3 \r\n" + 
			"			INNER JOIN TBL_KIOSK_MASTER s1 \r\n" + 
			"			ON s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO \r\n" + 
			"			INNER JOIN TBL_VENDOR_DETAILS s4 \r\n" + 
			"			ON s1.VENDOR=s4.COMPANY_SHORT_NM \r\n" + 
			"			INNER JOIN TBL_BRANCH_MASTER s2 \r\n" + 
			"			ON s1.BRANCH_CODE = s2.BRANCH_CODE \r\n" + 
			"			INNER JOIN TBL_INVOICE_VENDOR s5 \r\n" + 
			"			ON s5.PRN_SRN=s3.KIOSK_SERIAL_NO \r\n" + 
			"			INNER JOIN TBL_RFP_DETAILS s6 \r\n" + 
			"			ON s6.RFP_ID=s1.RFP_ID  AND \r\n" + 
			"			s6.vendor=s1.vendor \r\n" + 
					"			where \r\n" + 
			"			s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
			"			s2.CRCL_CODE=:selectedCircelId AND \r\n" + 
			"			s2.STAT_CODE=:selectedStateId AND\r\n" + 
			"			s3.FIN_YR=:finacialYear AND\r\n" + 
			"			s3.QTR_ID=:quterTimePeriod AND\r\n" + 
			"			s6.RFP_NO=:selectedRfpID",nativeQuery = true,
			countQuery = "select count(s5.PRN_SRN) \r\n" + 
					"from TBL_INVOICE s3\r\n" + 
					"INNER JOIN TBL_KIOSK_MASTER s1\r\n" + 
					"ON s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO\r\n" + 
					"INNER JOIN TBL_VENDOR_DETAILS s4\r\n" + 
					"ON s1.VENDOR=s4.COMPANY_SHORT_NM\r\n" + 
					"INNER JOIN TBL_BRANCH_MASTER s2\r\n" + 
					"ON s1.BRANCH_CODE = s2.BRANCH_CODE\r\n" + 
					"INNER JOIN TBL_INVOICE_VENDOR s5\r\n" + 
					"			ON s5.PRN_SRN=s3.KIOSK_SERIAL_NO INNER JOIN TBL_RFP_DETAILS s6 ON s6.RFP_ID=s1.RFP_ID  AND s6.vendor=s1.vendor \r\n" + 
					" where \r\n" + 
					"s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
					"s2.CRCL_CODE=:selectedCircelId AND \r\n" + 
					"s2.STAT_CODE=:selectedStateId AND\r\n" + 
					"s3.FIN_YR=:finacialYear AND\r\n" + 
					"s3.QTR_ID=:quterTimePeriod AND\r\n" + 
					"s6.RFP_NO=:selectedRfpID")
	List<InvoiceCompare> findbyFilterWithRFPReport(@Param("selectedCircelId")
    String selectedCircelId, @Param("selectedStateId") String selectedStateId,
   @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId, @Param("selectedRfpID")String selectedRfpID);
	
	@Query(value = "select s6.RFP_NO,s1.VENDOR,  \r\n" + 
			"			s2.CRCL_NAME, \r\n" + 
			"			s2.STAT_DESC, \r\n" + 
			"			s3.KIOSK_ID, \r\n" + 
			"			s3.KIOSK_SERIAL_NO,\r\n" + 
			"			s3.QTR_ID,\r\n" + 
			"			s3.FIN_YR,\r\n" + 
			"			s3.AMC_COST,\r\n" + 
			"			s5.AMC_AMT,\r\n" + 
			"			(s3.PENALTY_AMT - s3.CORRECTION_AMT) AS PENALTY_SBI_AMT , s3.CORRECTION_AMT ,\r\n" + 
			"			s5.PENALTY_AMT as VENDOR_PENALTY_AMT,\r\n" + 
			"			ABS((s3.PENALTY_AMT - s3.CORRECTION_AMT) - s5.PENALTY_AMT) as DIFFERENCE  \r\n" + 
			"			from TBL_INVOICE s3 \r\n" + 
			"			INNER JOIN TBL_KIOSK_MASTER s1 \r\n" + 
			"			ON s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO \r\n" + 
			"			INNER JOIN TBL_VENDOR_DETAILS s4 \r\n" + 
			"			ON s1.VENDOR=s4.COMPANY_SHORT_NM \r\n" + 
			"			INNER JOIN TBL_BRANCH_MASTER s2 \r\n" + 
			"			ON s1.BRANCH_CODE = s2.BRANCH_CODE \r\n" + 
			"			INNER JOIN TBL_INVOICE_VENDOR s5 \r\n" + 
			"			ON s5.PRN_SRN=s3.KIOSK_SERIAL_NO \r\n" + 
			"			INNER JOIN TBL_RFP_DETAILS s6 \r\n" + 
			"			ON s6.RFP_ID=s1.RFP_ID  AND \r\n" + 
			"			s6.vendor=s1.vendor \r\n" + 
			"			where  \r\n" + 
			"			s4.VENDOR_ID=:selectedVendorId AND \r\n" + 
			"			s2.CRCL_CODE=:selectedCircelId AND \r\n" + 
			"			s3.FIN_YR=:finacialYear AND \r\n" + 
			"			s3.QTR_ID=:quterTimePeriod AND \r\n" + 
			"			s6.RFP_NO=:selectedRfpID",nativeQuery = true,
			countQuery = "select count(s5.PRN_SRN) \r\n" + 
					"from TBL_INVOICE s3\r\n" + 
					"INNER JOIN TBL_KIOSK_MASTER s1\r\n" + 
					"ON s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO\r\n" + 
					"INNER JOIN TBL_VENDOR_DETAILS s4\r\n" + 
					"ON s1.VENDOR=s4.COMPANY_SHORT_NM\r\n" + 
					"INNER JOIN TBL_BRANCH_MASTER s2\r\n" + 
					"ON s1.BRANCH_CODE = s2.BRANCH_CODE\r\n" + 
					"INNER JOIN TBL_INVOICE_VENDOR s5\r\n" + 
					"			ON s5.PRN_SRN=s3.KIOSK_SERIAL_NO INNER JOIN TBL_RFP_DETAILS s6 ON s6.RFP_ID=s1.RFP_ID  AND s6.vendor=s1.vendor \r\n" + 
					" where \r\n" + 
					"s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
					"s2.CRCL_CODE=:selectedCircelId AND \r\n"+
					"s3.FIN_YR=:finacialYear AND\r\n" + 
					"s3.QTR_ID=:quterTimePeriod AND\r\n" + 
					"s6.RFP_NO=:selectedRfpID")
	List<InvoiceCompare> findbyFilterRfpWithoutStateReport(@Param("selectedCircelId")
    String selectedCircelId,
   @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId, @Param("selectedRfpID")String selectedRfpID);


	
	//Sharan Change -07-11-2020
	
	@Query(value = "select s6.RFP_NO,s1.VENDOR,  \r\n" + 
			"			s2.CRCL_NAME, \r\n" + 
			"			s2.STAT_DESC, \r\n" + 
			"			s3.KIOSK_ID, \r\n" + 
			"			s3.KIOSK_SERIAL_NO,\r\n" + 
			"			s3.QTR_ID,\r\n" + 
			"			s3.FIN_YR,\r\n" + 
			"			s3.AMC_COST,\r\n" + 
			"			s5.AMC_AMT,\r\n" + 
			"			(s3.PENALTY_AMT - s3.CORRECTION_AMT) AS PENALTY_SBI_AMT , s3.CORRECTION_AMT ,\r\n" + 
			"			s5.PENALTY_AMT as VENDOR_PENALTY_AMT,\r\n" + 
			"			ABS((s3.PENALTY_AMT - s3.CORRECTION_AMT) - s5.PENALTY_AMT) as DIFFERENCE  \r\n" + 
			"			from TBL_INVOICE s3 \r\n" + 
			"			INNER JOIN TBL_KIOSK_MASTER s1 \r\n" + 
			"			ON s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO \r\n" + 
			"			INNER JOIN TBL_VENDOR_DETAILS s4 \r\n" + 
			"			ON s1.VENDOR=s4.COMPANY_SHORT_NM \r\n" + 
			"			INNER JOIN TBL_BRANCH_MASTER s2 \r\n" + 
			"			ON s1.BRANCH_CODE = s2.BRANCH_CODE \r\n" + 
			"			INNER JOIN TBL_INVOICE_VENDOR s5 \r\n" + 
			"			ON s5.PRN_SRN=s3.KIOSK_SERIAL_NO \r\n" + 
			"			INNER JOIN TBL_RFP_DETAILS s6 \r\n" + 
			"			ON s6.RFP_ID=s1.RFP_ID  AND \r\n" + 
			"			s6.vendor=s1.vendor \r\n" + 
			"			where   \r\n" + 
			"			s4.VENDOR_ID=:selectedVendorId AND \r\n" + 
			"			s3.FIN_YR=:finacialYear AND \r\n" + 
			"			s3.QTR_ID=:quterTimePeriod",nativeQuery = true,
			countQuery = "select count(s5.PRN_SRN) \r\n" + 
					"from TBL_INVOICE s3\r\n" + 
					"INNER JOIN TBL_KIOSK_MASTER s1\r\n" + 
					"ON s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO\r\n" + 
					"INNER JOIN TBL_VENDOR_DETAILS s4\r\n" + 
					"ON s1.VENDOR=s4.COMPANY_SHORT_NM\r\n" + 
					"INNER JOIN TBL_BRANCH_MASTER s2\r\n" + 
					"ON s1.BRANCH_CODE = s2.BRANCH_CODE\r\n" + 
					"INNER JOIN TBL_INVOICE_VENDOR s5\r\n" + 
					"			ON s5.PRN_SRN=s3.KIOSK_SERIAL_NO INNER JOIN TBL_RFP_DETAILS s6 ON s6.RFP_ID=s1.RFP_ID  AND s6.vendor=s1.vendor \r\n" + 
					" where \r\n" + 
					"s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
					"s3.FIN_YR=:finacialYear AND\r\n" + 
					"s3.QTR_ID=:quterTimePeriod")
	List<InvoiceCompare> findbyWithoutStateFilterCCReport(
   @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId);
	
	
	
	@Query(value = "select s6.RFP_NO,s1.VENDOR,  \r\n" + 
			"			s2.CRCL_NAME, \r\n" + 
			"			s2.STAT_DESC, \r\n" + 
			"			s3.KIOSK_ID, \r\n" + 
			"			s3.KIOSK_SERIAL_NO,\r\n" + 
			"			s3.QTR_ID,\r\n" + 
			"			s3.FIN_YR,\r\n" + 
			"			s3.AMC_COST,\r\n" + 
			"			s5.AMC_AMT,\r\n" + 
			"			(s3.PENALTY_AMT - s3.CORRECTION_AMT) AS PENALTY_SBI_AMT , s3.CORRECTION_AMT ,\r\n" + 
			"			s5.PENALTY_AMT as VENDOR_PENALTY_AMT,\r\n" + 
			"			ABS((s3.PENALTY_AMT - s3.CORRECTION_AMT) - s5.PENALTY_AMT) as DIFFERENCE  \r\n" + 
			"			from TBL_INVOICE s3 \r\n" + 
			"			INNER JOIN TBL_KIOSK_MASTER s1 \r\n" + 
			"			ON s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO \r\n" + 
			"			INNER JOIN TBL_VENDOR_DETAILS s4 \r\n" + 
			"			ON s1.VENDOR=s4.COMPANY_SHORT_NM \r\n" + 
			"			INNER JOIN TBL_BRANCH_MASTER s2 \r\n" + 
			"			ON s1.BRANCH_CODE = s2.BRANCH_CODE \r\n" + 
			"			INNER JOIN TBL_INVOICE_VENDOR s5 \r\n" + 
			"			ON s5.PRN_SRN=s3.KIOSK_SERIAL_NO \r\n" + 
			"			INNER JOIN TBL_RFP_DETAILS s6 \r\n" + 
			"			ON s6.RFP_ID=s1.RFP_ID  AND \r\n" + 
			"			s6.vendor=s1.vendor \r\n" + 
			"			 where  \r\n" + 
			"			s4.VENDOR_ID=:selectedVendorId AND \r\n" + 
			"			s3.FIN_YR=:finacialYear AND \r\n" + 
			"			s3.QTR_ID=:quterTimePeriod AND \r\n" + 
			"			s6.RFP_NO=:selectedRfpID",nativeQuery = true,
			countQuery = "select count(s5.PRN_SRN) \r\n" + 
					"from TBL_INVOICE s3\r\n" + 
					"INNER JOIN TBL_KIOSK_MASTER s1\r\n" + 
					"ON s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO\r\n" + 
					"INNER JOIN TBL_VENDOR_DETAILS s4\r\n" + 
					"ON s1.VENDOR=s4.COMPANY_SHORT_NM\r\n" + 
					"INNER JOIN TBL_BRANCH_MASTER s2\r\n" + 
					"ON s1.BRANCH_CODE = s2.BRANCH_CODE\r\n" + 
					"INNER JOIN TBL_INVOICE_VENDOR s5\r\n" + 
					"			ON s5.PRN_SRN=s3.KIOSK_SERIAL_NO INNER JOIN TBL_RFP_DETAILS s6 ON s6.RFP_ID=s1.RFP_ID  AND s6.vendor=s1.vendor \r\n" + 
					" where \r\n" + 
					"s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
					"s3.FIN_YR=:finacialYear AND\r\n" + 
					"s3.QTR_ID=:quterTimePeriod AND\r\n" + 
					"s6.RFP_NO=:selectedRfpID")
	List<InvoiceCompare> findbyFilterRfpWithoutStateCCReport(
   @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId, @Param("selectedRfpID")String selectedRfpID);
	
	
	
	
}
