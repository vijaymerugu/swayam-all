package sbi.kiosk.swayam.billingpayment.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.BillingPenaltyEntity;
import sbi.kiosk.swayam.common.entity.InvoiceGeneration;

@Repository
public interface InvoiceGenerationRepository extends PagingAndSortingRepository<InvoiceGeneration, String> {
	
	//Sharan Change -07-11-2020
	
	@Query(value = "SELECT rd.RFP_NO,km.VENDOR,bm.CRCL_NAME, bm.STAT_DESC, iv.KIOSK_ID,iv.KIOSK_SERIAL_NO,iv.QTR_ID,iv.FIN_YR,iv.PENALTY_AMT,(iv.SPARE_PARTS_COST + iv.AMC_COST) as SPARE_PARTS, iv.INVOICE_AMT, iv.CORRECTION_AMT,iv.FINAL_AMT, (iv.PENALTY_AMT - iv.CORRECTION_AMT) AS FINAL_PENALTY " + 
			" FROM TBL_KIOSK_MASTER km " + 
			" INNER JOIN TBL_BRANCH_MASTER bm " + 
			" ON km.BRANCH_CODE = bm.BRANCH_CODE " + 
			" INNER JOIN  TBL_INVOICE iv " + 
			" ON km.KIOSK_SERIAL_NO=iv.KIOSK_SERIAL_NO " + 
			" INNER JOIN TBL_VENDOR_DETAILS vd " + 
			" ON km.VENDOR=vd.COMPANY_SHORT_NM  " + 
			" INNER JOIN TBL_RFP_DETAILS rd  " + 
			" ON rd.RFP_ID=km.RFP_ID AND rd.vendor=km.vendor"
			+" WHERE " + 
			"vd.VENDOR_ID=:selectedVendorId AND " + 
			"iv.FIN_YR=:finacialYear AND " + 
			"iv.QTR_ID=:quterTimePeriod AND "
			+ "iv.KIOSK_ID LIKE %:selectedKioskId% AND "
			+ "km.BRANCH_CODE LIKE %:selectedBranch% " ,nativeQuery = true)
	Page<InvoiceGeneration> findbyWithoutStateFilterCC(@Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId,
    @Param("selectedKioskId")String selectedKioskId,@Param("selectedBranch")String selectedBranch,
    Pageable pageable);
	
	
	
	@Query(value = "SELECT rd.RFP_NO,km.VENDOR,bm.CRCL_NAME, bm.STAT_DESC, iv.KIOSK_ID,iv.KIOSK_SERIAL_NO,iv.QTR_ID,iv.FIN_YR,iv.PENALTY_AMT,(iv.SPARE_PARTS_COST + iv.AMC_COST) as SPARE_PARTS, iv.INVOICE_AMT, iv.CORRECTION_AMT,iv.FINAL_AMT, (iv.PENALTY_AMT - iv.CORRECTION_AMT) AS FINAL_PENALTY " + 
			" FROM TBL_KIOSK_MASTER km " + 
			" INNER JOIN TBL_BRANCH_MASTER bm " + 
			" ON km.BRANCH_CODE = bm.BRANCH_CODE " + 
			" INNER JOIN  TBL_INVOICE iv " + 
			" ON km.KIOSK_SERIAL_NO=iv.KIOSK_SERIAL_NO " + 
			" INNER JOIN TBL_VENDOR_DETAILS vd " + 
			" ON km.VENDOR=vd.COMPANY_SHORT_NM  " + 
			" INNER JOIN TBL_RFP_DETAILS rd  " + 
			" ON rd.RFP_ID=km.RFP_ID AND rd.vendor=km.vendor"
			+" WHERE " + 
			"vd.VENDOR_ID=:selectedVendorId AND " + 
			"iv.FIN_YR=:finacialYear AND " + 
			"iv.QTR_ID=:quterTimePeriod AND " + 
			"rd.RFP_NO=:selectedRfpID AND "
			+ "iv.KIOSK_ID LIKE %:selectedKioskId% AND "
			+ "km.BRANCH_CODE LIKE %:selectedBranch% " ,nativeQuery = true)
	Page<InvoiceGeneration> findbyFilterRfpWithoutStateCC(
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId, @Param("selectedRfpID")String selectedRfpID,
    @Param("selectedKioskId")String selectedKioskId,@Param("selectedBranch")String selectedBranch,
    Pageable pageable);
	
	
	
	
	
	
	@Query(value = "SELECT rd.RFP_NO,km.VENDOR,bm.CRCL_NAME, bm.STAT_DESC, iv.KIOSK_ID,iv.KIOSK_SERIAL_NO,iv.QTR_ID,iv.FIN_YR,iv.PENALTY_AMT,(iv.SPARE_PARTS_COST + iv.AMC_COST) as SPARE_PARTS, iv.INVOICE_AMT, iv.CORRECTION_AMT,iv.FINAL_AMT, (iv.PENALTY_AMT - iv.CORRECTION_AMT) AS FINAL_PENALTY " + 
			" FROM TBL_KIOSK_MASTER km " + 
			" INNER JOIN TBL_BRANCH_MASTER bm " + 
			" ON km.BRANCH_CODE = bm.BRANCH_CODE " + 
			" INNER JOIN  TBL_INVOICE iv " + 
			" ON km.KIOSK_SERIAL_NO=iv.KIOSK_SERIAL_NO " + 
			" INNER JOIN TBL_VENDOR_DETAILS vd " + 
			" ON km.VENDOR=vd.COMPANY_SHORT_NM  " + 
			" INNER JOIN TBL_RFP_DETAILS rd  " + 
			" ON rd.RFP_ID=km.RFP_ID AND rd.vendor=km.vendor"
			+" WHERE " +
			"			vd.VENDOR_ID=:selectedVendorId AND " + 
			"			bm.CRCL_CODE=:selectedCircelId AND  " + 
			"			bm.STAT_CODE=:selectedStateId AND " + 
			"			iv.FIN_YR=:finacialYear AND " + 
			"			iv.QTR_ID=:quterTimePeriod AND "
			+ "iv.KIOSK_ID LIKE %:selectedKioskId% AND "
			+ "km.BRANCH_CODE LIKE %:selectedBranch% " ,nativeQuery = true)
	Page<InvoiceGeneration> findbyFilter(@Param("selectedCircelId")
    String selectedCircelId, @Param("selectedStateId") String selectedStateId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId,
    @Param("selectedKioskId")String selectedKioskId,@Param("selectedBranch")String selectedBranch,
    Pageable pageable);
	
	
	
	@Query(value = "SELECT rd.RFP_NO,km.VENDOR,bm.CRCL_NAME, bm.STAT_DESC, iv.KIOSK_ID,iv.KIOSK_SERIAL_NO,iv.QTR_ID,iv.FIN_YR,iv.PENALTY_AMT,(iv.SPARE_PARTS_COST + iv.AMC_COST) as SPARE_PARTS, iv.INVOICE_AMT, iv.CORRECTION_AMT,iv.FINAL_AMT, (iv.PENALTY_AMT - iv.CORRECTION_AMT) AS FINAL_PENALTY " + 
			" FROM TBL_KIOSK_MASTER km " + 
			" INNER JOIN TBL_BRANCH_MASTER bm " + 
			" ON km.BRANCH_CODE = bm.BRANCH_CODE " + 
			" INNER JOIN  TBL_INVOICE iv " + 
			" ON km.KIOSK_SERIAL_NO=iv.KIOSK_SERIAL_NO " + 
			" INNER JOIN TBL_VENDOR_DETAILS vd " + 
			" ON km.VENDOR=vd.COMPANY_SHORT_NM  " + 
			" INNER JOIN TBL_RFP_DETAILS rd  " + 
			" ON rd.RFP_ID=km.RFP_ID AND rd.vendor=km.vendor"
			+" WHERE " +
			"vd.VENDOR_ID=:selectedVendorId AND " + 
			"bm.CRCL_CODE=:selectedCircelId AND " +
			"iv.FIN_YR=:finacialYear AND " + 
			"iv.QTR_ID=:quterTimePeriod AND "
			+ "iv.KIOSK_ID LIKE %:selectedKioskId% AND "
			+ "km.BRANCH_CODE LIKE %:selectedBranch% " ,nativeQuery = true)
	Page<InvoiceGeneration> findbyWithoutStateFilter(@Param("selectedCircelId")
    String selectedCircelId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId,
    @Param("selectedKioskId")String selectedKioskId,@Param("selectedBranch")String selectedBranch,
    Pageable pageable);
	
	
	@Query(value = "SELECT rd.RFP_NO,km.VENDOR,bm.CRCL_NAME, bm.STAT_DESC, iv.KIOSK_ID,iv.KIOSK_SERIAL_NO,iv.QTR_ID,iv.FIN_YR,iv.PENALTY_AMT,(iv.SPARE_PARTS_COST + iv.AMC_COST) as SPARE_PARTS, iv.INVOICE_AMT, iv.CORRECTION_AMT,iv.FINAL_AMT, (iv.PENALTY_AMT - iv.CORRECTION_AMT) AS FINAL_PENALTY " + 
			" FROM TBL_KIOSK_MASTER km " + 
			" INNER JOIN TBL_BRANCH_MASTER bm " + 
			" ON km.BRANCH_CODE = bm.BRANCH_CODE " + 
			" INNER JOIN  TBL_INVOICE iv " + 
			" ON km.KIOSK_SERIAL_NO=iv.KIOSK_SERIAL_NO " + 
			" INNER JOIN TBL_VENDOR_DETAILS vd " + 
			" ON km.VENDOR=vd.COMPANY_SHORT_NM  " + 
			" INNER JOIN TBL_RFP_DETAILS rd  " + 
			" ON rd.RFP_ID=km.RFP_ID AND rd.vendor=km.vendor"
			+" WHERE " +
			"vd.VENDOR_ID=:selectedVendorId AND " + 
			"bm.CRCL_CODE=:selectedCircelId AND  " + 
			"bm.STAT_CODE=:selectedStateId AND " + 
			"iv.FIN_YR=:finacialYear AND " + 
			"iv.QTR_ID=:quterTimePeriod AND " + 
			"rd.RFP_NO=:selectedRfpID AND "
			+ "iv.KIOSK_ID LIKE %:selectedKioskId% AND "
			+ "km.BRANCH_CODE LIKE %:selectedBranch% " ,nativeQuery = true)
	Page<InvoiceGeneration> findbyFilterWithRFP(@Param("selectedCircelId")
    String selectedCircelId, @Param("selectedStateId") String selectedStateId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId, @Param("selectedRfpID")String selectedRfpID, 
    @Param("selectedKioskId")String selectedKioskId,@Param("selectedBranch")String selectedBranch,
    Pageable pageable);
	
	@Query(value = "SELECT rd.RFP_NO,km.VENDOR,bm.CRCL_NAME, bm.STAT_DESC, iv.KIOSK_ID,iv.KIOSK_SERIAL_NO,iv.QTR_ID,iv.FIN_YR,iv.PENALTY_AMT,(iv.SPARE_PARTS_COST + iv.AMC_COST) as SPARE_PARTS, iv.INVOICE_AMT, iv.CORRECTION_AMT,iv.FINAL_AMT, (iv.PENALTY_AMT - iv.CORRECTION_AMT) AS FINAL_PENALTY " + 
			" FROM TBL_KIOSK_MASTER km " + 
			" INNER JOIN TBL_BRANCH_MASTER bm " + 
			" ON km.BRANCH_CODE = bm.BRANCH_CODE " + 
			" INNER JOIN  TBL_INVOICE iv " + 
			" ON km.KIOSK_SERIAL_NO=iv.KIOSK_SERIAL_NO " + 
			" INNER JOIN TBL_VENDOR_DETAILS vd " + 
			" ON km.VENDOR=vd.COMPANY_SHORT_NM  " + 
			" INNER JOIN TBL_RFP_DETAILS rd  " + 
			" ON rd.RFP_ID=km.RFP_ID AND rd.vendor=km.vendor"
			+" WHERE " +
			"vd.VENDOR_ID=:selectedVendorId AND " + 
			"bm.CRCL_CODE=:selectedCircelId AND "+
			"iv.FIN_YR=:finacialYear AND " + 
			"iv.QTR_ID=:quterTimePeriod AND " + 
			"rd.RFP_NO=:selectedRfpID AND "
			+ "iv.KIOSK_ID LIKE %:selectedKioskId% AND "
			+ "km.BRANCH_CODE LIKE %:selectedBranch% " ,nativeQuery = true)
	Page<InvoiceGeneration> findbyFilterRfpWithoutState(@Param("selectedCircelId")
    String selectedCircelId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId, @Param("selectedRfpID")String selectedRfpID,
    @Param("selectedKioskId")String selectedKioskId,@Param("selectedBranch")String selectedBranch,
    Pageable pageable);
	
	
	@Query(value = "SELECT rd.RFP_NO,km.VENDOR,bm.CRCL_NAME, bm.STAT_DESC, iv.KIOSK_ID,iv.KIOSK_SERIAL_NO,iv.QTR_ID,iv.FIN_YR,iv.PENALTY_AMT,(iv.SPARE_PARTS_COST + iv.AMC_COST) as SPARE_PARTS, iv.INVOICE_AMT, iv.CORRECTION_AMT,iv.FINAL_AMT, (iv.PENALTY_AMT - iv.CORRECTION_AMT) AS FINAL_PENALTY " + 
			" FROM TBL_KIOSK_MASTER km " + 
			" INNER JOIN TBL_BRANCH_MASTER bm " + 
			" ON km.BRANCH_CODE = bm.BRANCH_CODE " + 
			" INNER JOIN  TBL_INVOICE iv " + 
			" ON km.KIOSK_SERIAL_NO=iv.KIOSK_SERIAL_NO " + 
			" INNER JOIN TBL_VENDOR_DETAILS vd " + 
			" ON km.VENDOR=vd.COMPANY_SHORT_NM  " + 
			" INNER JOIN TBL_RFP_DETAILS rd  " + 
			" ON rd.RFP_ID=km.RFP_ID AND rd.vendor=km.vendor"
			+" WHERE " +
			"vd.VENDOR_ID=:selectedVendorId AND " + 
			"bm.CRCL_CODE=:selectedCircelId AND " + 
			"bm.STAT_CODE=:selectedStateId AND " + 
			"iv.FIN_YR=:finacialYear AND " + 
			"iv.QTR_ID=:quterTimePeriod AND "
			+ "iv.KIOSK_ID LIKE %:selectedKioskId% AND "
			+ "km.BRANCH_CODE LIKE %:selectedBranch% " ,nativeQuery = true)
	List<InvoiceGeneration> findbyFilterReport(@Param("selectedCircelId")
    String selectedCircelId, @Param("selectedStateId") String selectedStateId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId,
    @Param("selectedKioskId")String selectedKioskId,@Param("selectedBranch")String selectedBranch);
	
	
	
	@Query(value = "SELECT rd.RFP_NO,km.VENDOR,bm.CRCL_NAME, bm.STAT_DESC, iv.KIOSK_ID,iv.KIOSK_SERIAL_NO,iv.QTR_ID,iv.FIN_YR,iv.PENALTY_AMT,(iv.SPARE_PARTS_COST + iv.AMC_COST) as SPARE_PARTS, iv.INVOICE_AMT, iv.CORRECTION_AMT,iv.FINAL_AMT, (iv.PENALTY_AMT - iv.CORRECTION_AMT) AS FINAL_PENALTY " + 
			" FROM TBL_KIOSK_MASTER km " + 
			" INNER JOIN TBL_BRANCH_MASTER bm " + 
			" ON km.BRANCH_CODE = bm.BRANCH_CODE " + 
			" INNER JOIN  TBL_INVOICE iv " + 
			" ON km.KIOSK_SERIAL_NO=iv.KIOSK_SERIAL_NO " + 
			" INNER JOIN TBL_VENDOR_DETAILS vd " + 
			" ON km.VENDOR=vd.COMPANY_SHORT_NM  " + 
			" INNER JOIN TBL_RFP_DETAILS rd  " + 
			" ON rd.RFP_ID=km.RFP_ID AND rd.vendor=km.vendor"
			+" WHERE " +
			"vd.VENDOR_ID=:selectedVendorId AND " + 
			"bm.CRCL_CODE=:selectedCircelId AND " +
			"iv.FIN_YR=:finacialYear AND " + 
			"iv.QTR_ID=:quterTimePeriod AND "
			+ "iv.KIOSK_ID LIKE %:selectedKioskId% AND "
			+ "km.BRANCH_CODE LIKE %:selectedBranch% " ,nativeQuery = true)
	List<InvoiceGeneration> findbyWithoutStateFilterReport(@Param("selectedCircelId")
    String selectedCircelId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId,
    @Param("selectedKioskId")String selectedKioskId,@Param("selectedBranch")String selectedBranch);
	
	
	@Query(value = "SELECT rd.RFP_NO,km.VENDOR,bm.CRCL_NAME, bm.STAT_DESC, iv.KIOSK_ID,iv.KIOSK_SERIAL_NO,iv.QTR_ID,iv.FIN_YR,iv.PENALTY_AMT,(iv.SPARE_PARTS_COST + iv.AMC_COST) as SPARE_PARTS, iv.INVOICE_AMT, iv.CORRECTION_AMT,iv.FINAL_AMT, (iv.PENALTY_AMT - iv.CORRECTION_AMT) AS FINAL_PENALTY " + 
			" FROM TBL_KIOSK_MASTER km " + 
			" INNER JOIN TBL_BRANCH_MASTER bm " + 
			" ON km.BRANCH_CODE = bm.BRANCH_CODE " + 
			" INNER JOIN  TBL_INVOICE iv " + 
			" ON km.KIOSK_SERIAL_NO=iv.KIOSK_SERIAL_NO " + 
			" INNER JOIN TBL_VENDOR_DETAILS vd " + 
			" ON km.VENDOR=vd.COMPANY_SHORT_NM  " + 
			" INNER JOIN TBL_RFP_DETAILS rd  " + 
			" ON rd.RFP_ID=km.RFP_ID AND rd.vendor=km.vendor"
			+" WHERE " +
			"vd.VENDOR_ID=:selectedVendorId AND " + 
			"bm.CRCL_CODE=:selectedCircelId AND " + 
			"bm.STAT_CODE=:selectedStateId AND " + 
			"iv.FIN_YR=:finacialYear AND " + 
			"iv.QTR_ID=:quterTimePeriod AND " + 
			"rd.RFP_NO=:selectedRfpID AND "
			+ "iv.KIOSK_ID LIKE %:selectedKioskId% AND "
			+ "km.BRANCH_CODE LIKE %:selectedBranch% " ,nativeQuery = true)
	List<InvoiceGeneration> findbyFilterWithRFPReport(@Param("selectedCircelId")
    String selectedCircelId, @Param("selectedStateId") String selectedStateId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId, @Param("selectedRfpID")String selectedRfpID,
    @Param("selectedKioskId")String selectedKioskId,@Param("selectedBranch")String selectedBranch);
	
	@Query(value =  "SELECT rd.RFP_NO,km.VENDOR,bm.CRCL_NAME, bm.STAT_DESC, iv.KIOSK_ID,iv.KIOSK_SERIAL_NO,iv.QTR_ID,iv.FIN_YR,iv.PENALTY_AMT,(iv.SPARE_PARTS_COST + iv.AMC_COST) as SPARE_PARTS, iv.INVOICE_AMT, iv.CORRECTION_AMT,iv.FINAL_AMT, (iv.PENALTY_AMT - iv.CORRECTION_AMT) AS FINAL_PENALTY " + 
			" FROM TBL_KIOSK_MASTER km " + 
			" INNER JOIN TBL_BRANCH_MASTER bm " + 
			" ON km.BRANCH_CODE = bm.BRANCH_CODE " + 
			" INNER JOIN  TBL_INVOICE iv " + 
			" ON km.KIOSK_SERIAL_NO=iv.KIOSK_SERIAL_NO " + 
			" INNER JOIN TBL_VENDOR_DETAILS vd " + 
			" ON km.VENDOR=vd.COMPANY_SHORT_NM  " + 
			" INNER JOIN TBL_RFP_DETAILS rd  " + 
			" ON rd.RFP_ID=km.RFP_ID AND rd.vendor=km.vendor"
			+" WHERE " +
			"vd.VENDOR_ID=:selectedVendorId AND " + 
			"bm.CRCL_CODE=:selectedCircelId AND "+
			"iv.FIN_YR=:finacialYear AND " + 
			"iv.QTR_ID=:quterTimePeriod AND " + 
			"rd.RFP_NO=:selectedRfpID AND "
			+ "iv.KIOSK_ID LIKE %:selectedKioskId% AND "
			+ "km.BRANCH_CODE LIKE %:selectedBranch% " ,nativeQuery = true)
	List<InvoiceGeneration> findbyFilterRfpWithoutStateReport(@Param("selectedCircelId")
    String selectedCircelId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId, @Param("selectedRfpID")String selectedRfpID,
    @Param("selectedKioskId")String selectedKioskId,@Param("selectedBranch")String selectedBranch);

	//Sharan Change -07-11-2020
	
		@Query(value = "SELECT rd.RFP_NO,km.VENDOR,bm.CRCL_NAME, bm.STAT_DESC, iv.KIOSK_ID,iv.KIOSK_SERIAL_NO,iv.QTR_ID,iv.FIN_YR,iv.PENALTY_AMT,(iv.SPARE_PARTS_COST + iv.AMC_COST) as SPARE_PARTS, iv.INVOICE_AMT, iv.CORRECTION_AMT,iv.FINAL_AMT, (iv.PENALTY_AMT - iv.CORRECTION_AMT) AS FINAL_PENALTY " + 
				" FROM TBL_KIOSK_MASTER km " + 
				" INNER JOIN TBL_BRANCH_MASTER bm " + 
				" ON km.BRANCH_CODE = bm.BRANCH_CODE " + 
				" INNER JOIN  TBL_INVOICE iv " + 
				" ON km.KIOSK_SERIAL_NO=iv.KIOSK_SERIAL_NO " + 
				" INNER JOIN TBL_VENDOR_DETAILS vd " + 
				" ON km.VENDOR=vd.COMPANY_SHORT_NM  " + 
				" INNER JOIN TBL_RFP_DETAILS rd  " + 
				" ON rd.RFP_ID=km.RFP_ID AND rd.vendor=km.vendor"
				+" WHERE " +
				"vd.VENDOR_ID=:selectedVendorId AND " + 
				"iv.FIN_YR=:finacialYear AND " + 
				"iv.QTR_ID=:quterTimePeriod AND "
				+ "iv.KIOSK_ID LIKE %:selectedKioskId% AND "
				+ "km.BRANCH_CODE LIKE %:selectedBranch% " ,nativeQuery = true)
		List<InvoiceGeneration> findbyWithoutStateFilterCCReport(@Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
	    @Param("selectedVendorId") String selectedVendorId,
	    @Param("selectedKioskId")String selectedKioskId,@Param("selectedBranch")String selectedBranch);
		
		
		
		@Query(value =  "SELECT rd.RFP_NO,km.VENDOR,bm.CRCL_NAME, bm.STAT_DESC, iv.KIOSK_ID,iv.KIOSK_SERIAL_NO,iv.QTR_ID,iv.FIN_YR,iv.PENALTY_AMT,(iv.SPARE_PARTS_COST + iv.AMC_COST) as SPARE_PARTS, iv.INVOICE_AMT, iv.CORRECTION_AMT,iv.FINAL_AMT, (iv.PENALTY_AMT - iv.CORRECTION_AMT) AS FINAL_PENALTY " + 
				" FROM TBL_KIOSK_MASTER km " + 
				" INNER JOIN TBL_BRANCH_MASTER bm " + 
				" ON km.BRANCH_CODE = bm.BRANCH_CODE " + 
				" INNER JOIN  TBL_INVOICE iv " + 
				" ON km.KIOSK_SERIAL_NO=iv.KIOSK_SERIAL_NO " + 
				" INNER JOIN TBL_VENDOR_DETAILS vd " + 
				" ON km.VENDOR=vd.COMPANY_SHORT_NM  " + 
				" INNER JOIN TBL_RFP_DETAILS rd  " + 
				" ON rd.RFP_ID=km.RFP_ID AND rd.vendor=km.vendor"
				+" WHERE " +
				"vd.VENDOR_ID=:selectedVendorId AND " + 
				"iv.FIN_YR=:finacialYear AND " + 
				"iv.QTR_ID=:quterTimePeriod AND " + 
				"rd.RFP_NO=:selectedRfpID AND "
				+ "iv.KIOSK_ID LIKE %:selectedKioskId% AND "
				+ "km.BRANCH_CODE LIKE %:selectedBranch% " ,nativeQuery = true)
		List<InvoiceGeneration> findbyFilterRfpWithoutStateCCReport(
	    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
	    @Param("selectedVendorId") String selectedVendorId, @Param("selectedRfpID")String selectedRfpID,
	    @Param("selectedKioskId")String selectedKioskId,@Param("selectedBranch")String selectedBranch);
		
		
		
		



}
