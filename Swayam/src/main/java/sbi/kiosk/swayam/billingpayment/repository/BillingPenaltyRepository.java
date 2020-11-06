package sbi.kiosk.swayam.billingpayment.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.BillingPenaltyEntity;

@Repository
public interface BillingPenaltyRepository extends PagingAndSortingRepository<BillingPenaltyEntity, String> {
	
	
	
	@Query(value = 
			"select s5.RFP_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s1.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.TOT_HRS,s3.TOT_DOWNTIME,s3.RELAXATION_HRS,s3.ACT_DOWNTIME, s3.PENALTY_AMT \r\n" + 
			"from TBL_DOWNTIME_QTR s3\r\n" + 
			"INNER JOIN TBL_KIOSK_MASTER s1\r\n" + 
			"ON s1.KIOSK_ID=s3.KIOSK_ID\r\n" + 
			"INNER JOIN TBL_VENDOR_DETAILS s4\r\n" + 
			"ON s1.VENDOR=s4.COMPANY_SHORT_NM\r\n" + 
			"INNER JOIN TBL_BRANCH_MASTER s2\r\n" + 
			"ON s1.BRANCH_CODE = s2.BRANCH_CODE INNER JOIN TBL_RFP_DETAILS s5 ON s5.RFP_ID=s1.RFP_ID AND s5.vendor=s1.vendor"+
			" where \r\n" + 
			"s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
			"s2.CRCL_CODE=:selectedCircelId AND \r\n" + 
			"s2.STAT_CODE=:selectedStateId AND\r\n" + 
			"s3.FIN_YR=:finacialYear AND\r\n" + 
			"s3.QTR_ID=:quterTimePeriod",nativeQuery = true,
						countQuery ="select count(s3.KIOSK_ID)\r\n" + 
								"from TBL_DOWNTIME_QTR s3\r\n" + 
								"INNER JOIN TBL_KIOSK_MASTER s1\r\n" + 
								"ON s1.KIOSK_ID=s3.KIOSK_ID\r\n" + 
								"INNER JOIN TBL_VENDOR_DETAILS s4\r\n" + 
								"ON s1.VENDOR=s4.COMPANY_SHORT_NM\r\n" + 
								"INNER JOIN TBL_BRANCH_MASTER s2\r\n" + 
								"ON s1.BRANCH_CODE = s2.BRANCH_CODE INNER JOIN TBL_RFP_DETAILS s5 ON s5.RFP_ID=s1.RFP_ID AND s5.vendor=s1.vendor"+
								" where \r\n" + 
								"s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
								"s2.CRCL_CODE=:selectedCircelId AND \r\n" + 
								"s2.STAT_CODE=:selectedStateId AND\r\n" + 
								"s3.FIN_YR=:finacialYear AND\r\n" + 
								"s3.QTR_ID=:quterTimePeriod" )
	Page<BillingPenaltyEntity> findbyFilter(@Param("selectedCircelId")
    String selectedCircelId, @Param("selectedStateId") String selectedStateId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId,Pageable pageable);
	
	
	
	
	@Query(value = 
			"select s5.RFP_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s1.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.TOT_HRS,s3.TOT_DOWNTIME,s3.RELAXATION_HRS,s3.ACT_DOWNTIME, s3.PENALTY_AMT \r\n" + 
			"from TBL_DOWNTIME_QTR s3\r\n" + 
			"INNER JOIN TBL_KIOSK_MASTER s1\r\n" + 
			"ON s1.KIOSK_ID=s3.KIOSK_ID\r\n" + 
			"INNER JOIN TBL_VENDOR_DETAILS s4\r\n" + 
			"ON s1.VENDOR=s4.COMPANY_SHORT_NM\r\n" + 
			"INNER JOIN TBL_BRANCH_MASTER s2\r\n" + 
			"ON s1.BRANCH_CODE = s2.BRANCH_CODE INNER JOIN TBL_RFP_DETAILS s5 ON s5.RFP_ID=s1.RFP_ID AND s5.vendor=s1.vendor"+
			" where \r\n" + 
			"s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
			"s2.CRCL_CODE=:selectedCircelId AND \r\n" +
			"s3.FIN_YR=:finacialYear AND\r\n" + 
			"s3.QTR_ID=:quterTimePeriod",nativeQuery = true,
						countQuery ="select count(s3.KIOSK_ID)\r\n" + 
								"from TBL_DOWNTIME_QTR s3\r\n" + 
								"INNER JOIN TBL_KIOSK_MASTER s1\r\n" + 
								"ON s1.KIOSK_ID=s3.KIOSK_ID\r\n" + 
								"INNER JOIN TBL_VENDOR_DETAILS s4\r\n" + 
								"ON s1.VENDOR=s4.COMPANY_SHORT_NM\r\n" + 
								"INNER JOIN TBL_BRANCH_MASTER s2\r\n" + 
								"ON s1.BRANCH_CODE = s2.BRANCH_CODE INNER JOIN TBL_RFP_DETAILS s5 ON s5.RFP_ID=s1.RFP_ID AND s5.vendor=s1.vendor"+
								" where \r\n" + 
								"s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
								"s2.CRCL_CODE=:selectedCircelId AND \r\n" +
								"s3.FIN_YR=:finacialYear AND\r\n" + 
								"s3.QTR_ID=:quterTimePeriod" )
	Page<BillingPenaltyEntity> findbyWithoutStateFilter(@Param("selectedCircelId")
    String selectedCircelId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId,Pageable pageable);


	
	@Query(value = 
			"select s5.RFP_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s1.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.TOT_HRS,s3.TOT_DOWNTIME,s3.RELAXATION_HRS,s3.ACT_DOWNTIME, s3.PENALTY_AMT \r\n" + 
			"from TBL_DOWNTIME_QTR s3\r\n" + 
			"INNER JOIN TBL_KIOSK_MASTER s1\r\n" + 
			"ON s1.KIOSK_ID=s3.KIOSK_ID\r\n" + 
			"INNER JOIN TBL_VENDOR_DETAILS s4\r\n" + 
			"ON s1.VENDOR=s4.COMPANY_SHORT_NM\r\n" + 
			"INNER JOIN TBL_BRANCH_MASTER s2\r\n" + 
			"ON s1.BRANCH_CODE = s2.BRANCH_CODE INNER JOIN TBL_RFP_DETAILS s5 ON s5.RFP_ID=s1.RFP_ID AND s5.vendor=s1.vendor"+
			" where \r\n" + 
			"s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
			"s2.CRCL_CODE=:selectedCircelId AND \r\n" + 
			"s2.STAT_CODE=:selectedStateId AND\r\n" + 
			"s3.FIN_YR=:finacialYear AND\r\n" + 
			"s3.QTR_ID=:quterTimePeriod AND\r\n" + 
			"s5.RFP_NO=:selectedRfpID",nativeQuery = true,
						countQuery ="select count(s3.KIOSK_ID)\r\n" + 
								"from TBL_DOWNTIME_QTR s3\r\n" + 
								"INNER JOIN TBL_KIOSK_MASTER s1\r\n" + 
								"ON s1.KIOSK_ID=s3.KIOSK_ID\r\n" + 
								"INNER JOIN TBL_VENDOR_DETAILS s4\r\n" + 
								"ON s1.VENDOR=s4.COMPANY_SHORT_NM\r\n" + 
								"INNER JOIN TBL_BRANCH_MASTER s2\r\n" + 
								"ON s1.BRANCH_CODE = s2.BRANCH_CODE INNER JOIN TBL_RFP_DETAILS s5 ON s5.RFP_ID=s1.RFP_ID AND s5.vendor=s1.vendor"+
								" where \r\n" + 
								"s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
								"s2.CRCL_CODE=:selectedCircelId AND \r\n" + 
								"s2.STAT_CODE=:selectedStateId AND\r\n" + 
								"s3.FIN_YR=:finacialYear AND\r\n" + 
								"s3.QTR_ID=:quterTimePeriod AND\r\n" + 
								"s5.RFP_NO=:selectedRfpID" )
	Page<BillingPenaltyEntity> findbyFilterWithRFP(@Param("selectedCircelId")
    String selectedCircelId, @Param("selectedStateId") String selectedStateId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId, @Param("selectedRfpID")String selectedRfpID, Pageable pageable);
	
	@Query(value = 
			"select s5.RFP_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s1.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.TOT_HRS,s3.TOT_DOWNTIME,s3.RELAXATION_HRS,s3.ACT_DOWNTIME, s3.PENALTY_AMT \r\n" + 
			"from TBL_DOWNTIME_QTR s3\r\n" + 
			"INNER JOIN TBL_KIOSK_MASTER s1\r\n" + 
			"ON s1.KIOSK_ID=s3.KIOSK_ID\r\n" + 
			"INNER JOIN TBL_VENDOR_DETAILS s4\r\n" + 
			"ON s1.VENDOR=s4.COMPANY_SHORT_NM\r\n" + 
			"INNER JOIN TBL_BRANCH_MASTER s2\r\n" + 
			"ON s1.BRANCH_CODE = s2.BRANCH_CODE INNER JOIN TBL_RFP_DETAILS s5 ON s5.RFP_ID=s1.RFP_ID AND s5.vendor=s1.vendor"+
			" where \r\n" + 
			"s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
			"s2.CRCL_CODE=:selectedCircelId AND \r\n"+
			"s3.FIN_YR=:finacialYear AND\r\n" + 
			"s3.QTR_ID=:quterTimePeriod AND\r\n" + 
			"s5.RFP_NO=:selectedRfpID",nativeQuery = true,
						countQuery ="select count(s3.KIOSK_ID)\r\n" + 
								"from TBL_DOWNTIME_QTR s3\r\n" + 
								"INNER JOIN TBL_KIOSK_MASTER s1\r\n" + 
								"ON s1.KIOSK_ID=s3.KIOSK_ID\r\n" + 
								"INNER JOIN TBL_VENDOR_DETAILS s4\r\n" + 
								"ON s1.VENDOR=s4.COMPANY_SHORT_NM\r\n" + 
								"INNER JOIN TBL_BRANCH_MASTER s2\r\n" + 
								"ON s1.BRANCH_CODE = s2.BRANCH_CODE INNER JOIN TBL_RFP_DETAILS s5 ON s5.RFP_ID=s1.RFP_ID AND s5.vendor=s1.vendor"+
								" where \r\n" + 
								"s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
								"s2.CRCL_CODE=:selectedCircelId AND \r\n"+
								"s3.FIN_YR=:finacialYear AND\r\n" + 
								"s3.QTR_ID=:quterTimePeriod AND\r\n" + 
								"s5.RFP_NO=:selectedRfpID" )
	Page<BillingPenaltyEntity> findbyFilterRfpWithoutState(@Param("selectedCircelId")
    String selectedCircelId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId, @Param("selectedRfpID")String selectedRfpID, Pageable pageable);
	
	
	@Query(value = 
			"select s5.RFP_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s1.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.TOT_HRS,s3.TOT_DOWNTIME,s3.RELAXATION_HRS,s3.ACT_DOWNTIME, s3.PENALTY_AMT \r\n" + 
			"from TBL_DOWNTIME_QTR s3\r\n" + 
			"INNER JOIN TBL_KIOSK_MASTER s1\r\n" + 
			"ON s1.KIOSK_ID=s3.KIOSK_ID\r\n" + 
			"INNER JOIN TBL_VENDOR_DETAILS s4\r\n" + 
			"ON s1.VENDOR=s4.COMPANY_SHORT_NM\r\n" + 
			"INNER JOIN TBL_BRANCH_MASTER s2\r\n" + 
			"ON s1.BRANCH_CODE = s2.BRANCH_CODE INNER JOIN TBL_RFP_DETAILS s5 ON s5.RFP_ID=s1.RFP_ID AND s5.vendor=s1.vendor"+
			" where \r\n" + 
			"s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
			"s2.CRCL_CODE=:selectedCircelId AND \r\n" + 
			"s2.STAT_CODE=:selectedStateId AND\r\n" + 
			"s3.FIN_YR=:finacialYear AND\r\n" + 
			"s3.QTR_ID=:quterTimePeriod",nativeQuery = true,
						countQuery ="select count(s3.KIOSK_ID)\r\n" + 
								"from TBL_DOWNTIME_QTR s3\r\n" + 
								"INNER JOIN TBL_KIOSK_MASTER s1\r\n" + 
								"ON s1.KIOSK_ID=s3.KIOSK_ID\r\n" + 
								"INNER JOIN TBL_VENDOR_DETAILS s4\r\n" + 
								"ON s1.VENDOR=s4.COMPANY_SHORT_NM\r\n" + 
								"INNER JOIN TBL_BRANCH_MASTER s2\r\n" + 
								"ON s1.BRANCH_CODE = s2.BRANCH_CODE INNER JOIN TBL_RFP_DETAILS s5 ON s5.RFP_ID=s1.RFP_ID AND s5.vendor=s1.vendor"+
								" where \r\n" + 
								"s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
								"s2.CRCL_CODE=:selectedCircelId AND \r\n" + 
								"s2.STAT_CODE=:selectedStateId AND\r\n" + 
								"s3.FIN_YR=:finacialYear AND\r\n" + 
								"s3.QTR_ID=:quterTimePeriod" )
	List<BillingPenaltyEntity> findbyFilterReport(@Param("selectedCircelId")
    String selectedCircelId, @Param("selectedStateId") String selectedStateId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId);
	
	
	
	
	@Query(value = 
			"select s5.RFP_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s1.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.TOT_HRS,s3.TOT_DOWNTIME,s3.RELAXATION_HRS,s3.ACT_DOWNTIME, s3.PENALTY_AMT \r\n" + 
			"from TBL_DOWNTIME_QTR s3\r\n" + 
			"INNER JOIN TBL_KIOSK_MASTER s1\r\n" + 
			"ON s1.KIOSK_ID=s3.KIOSK_ID\r\n" + 
			"INNER JOIN TBL_VENDOR_DETAILS s4\r\n" + 
			"ON s1.VENDOR=s4.COMPANY_SHORT_NM\r\n" + 
			"INNER JOIN TBL_BRANCH_MASTER s2\r\n" + 
			"ON s1.BRANCH_CODE = s2.BRANCH_CODE INNER JOIN TBL_RFP_DETAILS s5 ON s5.RFP_ID=s1.RFP_ID AND s5.vendor=s1.vendor"+
			" where \r\n" + 
			"s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
			"s2.CRCL_CODE=:selectedCircelId AND \r\n" +
			"s3.FIN_YR=:finacialYear AND\r\n" + 
			"s3.QTR_ID=:quterTimePeriod",nativeQuery = true,
						countQuery ="select count(s3.KIOSK_ID)\r\n" + 
								"from TBL_DOWNTIME_QTR s3\r\n" + 
								"INNER JOIN TBL_KIOSK_MASTER s1\r\n" + 
								"ON s1.KIOSK_ID=s3.KIOSK_ID\r\n" + 
								"INNER JOIN TBL_VENDOR_DETAILS s4\r\n" + 
								"ON s1.VENDOR=s4.COMPANY_SHORT_NM\r\n" + 
								"INNER JOIN TBL_BRANCH_MASTER s2\r\n" + 
								"ON s1.BRANCH_CODE = s2.BRANCH_CODE INNER JOIN TBL_RFP_DETAILS s5 ON s5.RFP_ID=s1.RFP_ID AND s5.vendor=s1.vendor"+
								" where \r\n" + 
								"s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
								"s2.CRCL_CODE=:selectedCircelId AND \r\n" +
								"s3.FIN_YR=:finacialYear AND\r\n" + 
								"s3.QTR_ID=:quterTimePeriod" )
	List<BillingPenaltyEntity> findbyWithoutStateFilterReport(@Param("selectedCircelId")
    String selectedCircelId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId);


	
	@Query(value = 
			"select s5.RFP_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s1.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.TOT_HRS,s3.TOT_DOWNTIME,s3.RELAXATION_HRS,s3.ACT_DOWNTIME, s3.PENALTY_AMT \r\n" + 
			"from TBL_DOWNTIME_QTR s3\r\n" + 
			"INNER JOIN TBL_KIOSK_MASTER s1\r\n" + 
			"ON s1.KIOSK_ID=s3.KIOSK_ID\r\n" + 
			"INNER JOIN TBL_VENDOR_DETAILS s4\r\n" + 
			"ON s1.VENDOR=s4.COMPANY_SHORT_NM\r\n" + 
			"INNER JOIN TBL_BRANCH_MASTER s2\r\n" + 
			"ON s1.BRANCH_CODE = s2.BRANCH_CODE INNER JOIN TBL_RFP_DETAILS s5 ON s5.RFP_ID=s1.RFP_ID AND s5.vendor=s1.vendor"+
			" where \r\n" + 
			"s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
			"s2.CRCL_CODE=:selectedCircelId AND \r\n" + 
			"s2.STAT_CODE=:selectedStateId AND\r\n" + 
			"s3.FIN_YR=:finacialYear AND\r\n" + 
			"s3.QTR_ID=:quterTimePeriod AND\r\n" + 
			"s5.RFP_NO=:selectedRfpID",nativeQuery = true,
						countQuery ="select count(s3.KIOSK_ID)\r\n" + 
								"from TBL_DOWNTIME_QTR s3\r\n" + 
								"INNER JOIN TBL_KIOSK_MASTER s1\r\n" + 
								"ON s1.KIOSK_ID=s3.KIOSK_ID\r\n" + 
								"INNER JOIN TBL_VENDOR_DETAILS s4\r\n" + 
								"ON s1.VENDOR=s4.COMPANY_SHORT_NM\r\n" + 
								"INNER JOIN TBL_BRANCH_MASTER s2\r\n" + 
								"ON s1.BRANCH_CODE = s2.BRANCH_CODE INNER JOIN TBL_RFP_DETAILS s5 ON s5.RFP_ID=s1.RFP_ID AND s5.vendor=s1.vendor"+
								" where \r\n" + 
								"s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
								"s2.CRCL_CODE=:selectedCircelId AND \r\n" + 
								"s2.STAT_CODE=:selectedStateId AND\r\n" + 
								"s3.FIN_YR=:finacialYear AND\r\n" + 
								"s3.QTR_ID=:quterTimePeriod AND\r\n" + 
								"s5.RFP_NO=:selectedRfpID" )
	List<BillingPenaltyEntity> findbyFilterWithRFPReport(@Param("selectedCircelId")
    String selectedCircelId, @Param("selectedStateId") String selectedStateId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId, @Param("selectedRfpID")String selectedRfpID);
	
	@Query(value = 
			"select s5.RFP_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s1.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.TOT_HRS,s3.TOT_DOWNTIME,s3.RELAXATION_HRS,s3.ACT_DOWNTIME, s3.PENALTY_AMT \r\n" + 
			"from TBL_DOWNTIME_QTR s3\r\n" + 
			"INNER JOIN TBL_KIOSK_MASTER s1\r\n" + 
			"ON s1.KIOSK_ID=s3.KIOSK_ID\r\n" + 
			"INNER JOIN TBL_VENDOR_DETAILS s4\r\n" + 
			"ON s1.VENDOR=s4.COMPANY_SHORT_NM\r\n" + 
			"INNER JOIN TBL_BRANCH_MASTER s2\r\n" + 
			"ON s1.BRANCH_CODE = s2.BRANCH_CODE INNER JOIN TBL_RFP_DETAILS s5 ON s5.RFP_ID=s1.RFP_ID AND s5.vendor=s1.vendor"+
			" where \r\n" + 
			"s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
			"s2.CRCL_CODE=:selectedCircelId AND \r\n" +
			"s3.FIN_YR=:finacialYear AND\r\n" + 
			"s3.QTR_ID=:quterTimePeriod AND\r\n" + 
			"s5.RFP_NO=:selectedRfpID",nativeQuery = true,
						countQuery ="select count(s3.KIOSK_ID)\r\n" + 
								"from TBL_DOWNTIME_QTR s3\r\n" + 
								"INNER JOIN TBL_KIOSK_MASTER s1\r\n" + 
								"ON s1.KIOSK_ID=s3.KIOSK_ID\r\n" + 
								"INNER JOIN TBL_VENDOR_DETAILS s4\r\n" + 
								"ON s1.VENDOR=s4.COMPANY_SHORT_NM\r\n" + 
								"INNER JOIN TBL_BRANCH_MASTER s2\r\n" + 
								"ON s1.BRANCH_CODE = s2.BRANCH_CODE INNER JOIN TBL_RFP_DETAILS s5 ON s5.RFP_ID=s1.RFP_ID AND s5.vendor=s1.vendor"+
								" where \r\n" + 
								"s4.VENDOR_ID=:selectedVendorId AND\r\n" + 
								"s2.CRCL_CODE=:selectedCircelId AND \r\n" + 
								"s3.FIN_YR=:finacialYear AND\r\n" + 
								"s3.QTR_ID=:quterTimePeriod AND\r\n" + 
								"s5.RFP_NO=:selectedRfpID")
	List<BillingPenaltyEntity> findbyFilterRfpWithoutStateReport(@Param("selectedCircelId")
    String selectedCircelId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId, @Param("selectedRfpID")String selectedRfpID);
	

}
