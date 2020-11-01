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
	

	@Query(value = "select s6.RFP_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s3.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.INVOICE_AMT,s5.INVO_AMT,ABS(s3.INVOICE_AMT - s5.INVO_AMT) as DIFFERENCE  \r\n" + 
			"			from TBL_INVOICE s3 \r\n" + 
			"			INNER JOIN TBL_KIOSK_MASTER s1 \r\n" + 
			"			ON s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO \r\n" + 
			"			INNER JOIN TBL_VENDOR_DETAILS s4 \r\n" + 
			"			ON s1.VENDOR=s4.COMPANY_SHORT_NM \r\n" + 
			"			INNER JOIN TBL_BRANCH_MASTER s2 \r\n" + 
			"			ON s1.BRANCH_CODE = s2.BRANCH_CODE \r\n" + 
			"			INNER JOIN TBL_INVOICE_VENDOR s5 \r\n" + 
			"			ON s5.PRN_SRN=s3.KIOSK_SERIAL_NO INNER JOIN TBL_RFP_DETAILS s6 ON s6.RFP_ID=s1.RFP_ID  AND s6.vendor=s1.vendor \r\n" + 
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
	
	
	
	@Query(value = "select s6.RFP_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s3.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.INVOICE_AMT,s5.INVO_AMT,ABS(s3.INVOICE_AMT - s5.INVO_AMT) as DIFFERENCE \r\n" + 
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
			"s3.QTR_ID=:quterTimePeriod",nativeQuery = true,
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
	
	
	@Query(value = "select s6.RFP_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s3.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.INVOICE_AMT,s5.INVO_AMT,ABS(s3.INVOICE_AMT - s5.INVO_AMT) as DIFFERENCE \r\n" + 
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
			"s6.RFP_NO=:selectedRfpID",nativeQuery = true,
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
	
	@Query(value = "select s6.RFP_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s3.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.INVOICE_AMT,s5.INVO_AMT,ABS(s3.INVOICE_AMT - s5.INVO_AMT) as DIFFERENCE \r\n" + 
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
			"s6.RFP_NO=:selectedRfpID",nativeQuery = true,
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
	

	@Query(value = "select s6.RFP_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s3.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.INVOICE_AMT,s5.INVO_AMT,ABS(s3.INVOICE_AMT - s5.INVO_AMT) as DIFFERENCE \r\n" + 
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
			"s3.QTR_ID=:quterTimePeriod",nativeQuery = true,
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
	
	
	@Query(value = "select s6.RFP_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s3.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.INVOICE_AMT,s5.INVO_AMT,ABS(s3.INVOICE_AMT - s5.INVO_AMT) as DIFFERENCE \r\n" + 
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
			"s3.QTR_ID=:quterTimePeriod",nativeQuery = true,
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
	
	
	@Query(value = "select s6.RFP_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s3.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.INVOICE_AMT,s5.INVO_AMT,ABS(s3.INVOICE_AMT - s5.INVO_AMT) as DIFFERENCE \r\n" + 
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
			"s6.RFP_NO=:selectedRfpID",nativeQuery = true,
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
	
	@Query(value = "select s6.RFP_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s3.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.INVOICE_AMT,s5.INVO_AMT,ABS(s3.INVOICE_AMT - s5.INVO_AMT) as DIFFERENCE \r\n" + 
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
			"s6.RFP_NO=:selectedRfpID",nativeQuery = true,
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


}
