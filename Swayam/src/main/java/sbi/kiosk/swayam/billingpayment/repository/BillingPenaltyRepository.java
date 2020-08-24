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
			"select s1.RFP_REF_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s1.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.TOT_HRS,s3.TOT_DOWNTIME,s3.RELAXATION_HRS,s3.ACT_DOWNTIME, "+
					"s3.PENALTY_AMT from(select d.KIOSK_ID,  d.TOT_HRS, d.TOT_DOWNTIME, d.RELAXATION_HRS, d.ACT_DOWNTIME, "+
					"d.PENALTY_AMT ,d.FIN_YR,d.QTR_ID from TBL_DOWNTIME_QTR d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 "+
					"JOIN "+
					"((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.KIOSK_SERIAL_NO,k.RFP_REF_NO from TBL_KIOSK_MASTER k where k.VENDOR In " +
					"(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId )) s1 "+
					"JOIN "+ 
					"(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId AND STAT_CODE=:selectedStateId) s2 "+
					"On  s1.BRANCH_CODE = s2.BRANCH_CODE) "+
						"on s1.KIOSK_ID=s3.KIOSK_ID",nativeQuery = true,
						countQuery ="select count(s3.KIOSK_ID) from(select d.KIOSK_ID,  d.TOT_HRS, d.TOT_DOWNTIME, d.RELAXATION_HRS, d.ACT_DOWNTIME, "+
								"d.PENALTY_AMT from TBL_DOWNTIME_QTR d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 "+
								"JOIN "+
								"((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.KIOSK_SERIAL_NO,k.RFP_REF_NO from TBL_KIOSK_MASTER k where k.VENDOR In " +
								"(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId )) s1 "+
								"JOIN "+ 
								"(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where  CRCL_CODE=:selectedCircelId AND STAT_CODE=:selectedStateId) s2 "+
								"On  s1.BRANCH_CODE = s2.BRANCH_CODE) "+
									"on s1.KIOSK_ID=s3.KIOSK_ID" )
	Page<BillingPenaltyEntity> findbyFilter(@Param("selectedCircelId")
    String selectedCircelId, @Param("selectedStateId") String selectedStateId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId,Pageable pageable);
	
	
	
	
	@Query(value = 
			"select s1.RFP_REF_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s1.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.TOT_HRS,s3.TOT_DOWNTIME,s3.RELAXATION_HRS,s3.ACT_DOWNTIME, "+
					"s3.PENALTY_AMT from(select d.KIOSK_ID,  d.TOT_HRS, d.TOT_DOWNTIME, d.RELAXATION_HRS, d.ACT_DOWNTIME, "+
					"d.PENALTY_AMT ,d.FIN_YR,d.QTR_ID from TBL_DOWNTIME_QTR d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 "+
					"JOIN "+
					"((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.KIOSK_SERIAL_NO,k.RFP_REF_NO from TBL_KIOSK_MASTER k where k.VENDOR In " +
					"(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId )) s1 "+
					"JOIN "+ 
					"(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId) s2 "+
					"On  s1.BRANCH_CODE = s2.BRANCH_CODE) "+
						"on s1.KIOSK_ID=s3.KIOSK_ID",nativeQuery = true,
						countQuery ="select count(s3.KIOSK_ID) from(select d.KIOSK_ID,  d.TOT_HRS, d.TOT_DOWNTIME, d.RELAXATION_HRS, d.ACT_DOWNTIME, "+
								"d.PENALTY_AMT from TBL_DOWNTIME_QTR d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 "+
								"JOIN "+
								"((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.KIOSK_SERIAL_NO,k.RFP_REF_NO from TBL_KIOSK_MASTER k where k.VENDOR In " +
								"(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId )) s1 "+
								"JOIN "+ 
								"(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where  CRCL_CODE=:selectedCircelId) s2 "+
								"On  s1.BRANCH_CODE = s2.BRANCH_CODE) "+
									"on s1.KIOSK_ID=s3.KIOSK_ID" )
	Page<BillingPenaltyEntity> findbyWithoutStateFilter(@Param("selectedCircelId")
    String selectedCircelId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId,Pageable pageable);


	
	@Query(value = 
			"select s1.RFP_REF_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s1.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.TOT_HRS,s3.TOT_DOWNTIME,s3.RELAXATION_HRS,s3.ACT_DOWNTIME, "+
					"s3.PENALTY_AMT from(select d.KIOSK_ID,  d.TOT_HRS, d.TOT_DOWNTIME, d.RELAXATION_HRS, d.ACT_DOWNTIME, "+
					"d.PENALTY_AMT,d.FIN_YR,d.QTR_ID  from TBL_DOWNTIME_QTR d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 "+
					"JOIN "+
					"((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.KIOSK_SERIAL_NO,k.RFP_REF_NO from TBL_KIOSK_MASTER k where k.VENDOR In " +
					"(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId ) AND k.RFP_REF_NO=:selectedRfpID) s1 "+
					"JOIN "+ 
					"(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId AND STAT_CODE=:selectedStateId) s2 "+
					"On  s1.BRANCH_CODE = s2.BRANCH_CODE) "+
						"on s1.KIOSK_ID=s3.KIOSK_ID",nativeQuery = true,
						countQuery ="select count(s3.KIOSK_ID) from(select d.KIOSK_ID,  d.TOT_HRS, d.TOT_DOWNTIME, d.RELAXATION_HRS, d.ACT_DOWNTIME, "+
								"d.PENALTY_AMT from TBL_DOWNTIME_QTR d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 "+
								"JOIN "+
								"((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.KIOSK_SERIAL_NO,k.RFP_REF_NO from TBL_KIOSK_MASTER k where k.VENDOR In " +
								"(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId )) s1 "+
								"JOIN "+ 
								"(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where  CRCL_CODE=:selectedCircelId AND STAT_CODE=:selectedStateId) s2 "+
								"On  s1.BRANCH_CODE = s2.BRANCH_CODE) "+
									"on s1.KIOSK_ID=s3.KIOSK_ID" )
	Page<BillingPenaltyEntity> findbyFilterWithRFP(@Param("selectedCircelId")
    String selectedCircelId, @Param("selectedStateId") String selectedStateId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId, @Param("selectedRfpID")String selectedRfpID, Pageable pageable);
	
	@Query(value = 
			"select s1.RFP_REF_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s1.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.TOT_HRS,s3.TOT_DOWNTIME,s3.RELAXATION_HRS,s3.ACT_DOWNTIME, "+
					"s3.PENALTY_AMT from(select d.KIOSK_ID,  d.TOT_HRS, d.TOT_DOWNTIME, d.RELAXATION_HRS, d.ACT_DOWNTIME, "+
					"d.PENALTY_AMT,d.FIN_YR,d.QTR_ID  from TBL_DOWNTIME_QTR d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 "+
					"JOIN "+
					"((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.KIOSK_SERIAL_NO,k.RFP_REF_NO from TBL_KIOSK_MASTER k where k.VENDOR In " +
					"(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId ) AND k.RFP_REF_NO=:selectedRfpID) s1 "+
					"JOIN "+ 
					"(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId) s2 "+
					"On  s1.BRANCH_CODE = s2.BRANCH_CODE) "+
						"on s1.KIOSK_ID=s3.KIOSK_ID",nativeQuery = true,
						countQuery ="select count(s3.KIOSK_ID) from(select d.KIOSK_ID,  d.TOT_HRS, d.TOT_DOWNTIME, d.RELAXATION_HRS, d.ACT_DOWNTIME, "+
								"d.PENALTY_AMT from TBL_DOWNTIME_QTR d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 "+
								"JOIN "+
								"((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.KIOSK_SERIAL_NO,k.RFP_REF_NO from TBL_KIOSK_MASTER k where k.VENDOR In " +
								"(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId )) s1 "+
								"JOIN "+ 
								"(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where  CRCL_CODE=:selectedCircelId AND STAT_CODE=:selectedStateId) s2 "+
								"On  s1.BRANCH_CODE = s2.BRANCH_CODE) "+
									"on s1.KIOSK_ID=s3.KIOSK_ID" )
	Page<BillingPenaltyEntity> findbyFilterRfpWithoutState(@Param("selectedCircelId")
    String selectedCircelId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId, @Param("selectedRfpID")String selectedRfpID, Pageable pageable);
	
	
	@Query(value = 
			"select s1.RFP_REF_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s1.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.TOT_HRS,s3.TOT_DOWNTIME,s3.RELAXATION_HRS,s3.ACT_DOWNTIME, "+
					"s3.PENALTY_AMT from(select d.KIOSK_ID,  d.TOT_HRS, d.TOT_DOWNTIME, d.RELAXATION_HRS, d.ACT_DOWNTIME, "+
					"d.PENALTY_AMT,d.FIN_YR,d.QTR_ID  from TBL_DOWNTIME_QTR d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 "+
					"JOIN "+
					"((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.KIOSK_SERIAL_NO,k.RFP_REF_NO from TBL_KIOSK_MASTER k where k.VENDOR In " +
					"(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId )) s1 "+
					"JOIN "+ 
					"(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId AND STAT_CODE=:selectedStateId) s2 "+
					"On  s1.BRANCH_CODE = s2.BRANCH_CODE) "+
						"on s1.KIOSK_ID=s3.KIOSK_ID",nativeQuery = true,
						countQuery ="select count(s3.KIOSK_ID) from(select d.KIOSK_ID,  d.TOT_HRS, d.TOT_DOWNTIME, d.RELAXATION_HRS, d.ACT_DOWNTIME, "+
								"d.PENALTY_AMT from TBL_DOWNTIME_QTR d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 "+
								"JOIN "+
								"((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.KIOSK_SERIAL_NO,k.RFP_REF_NO from TBL_KIOSK_MASTER k where k.VENDOR In " +
								"(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId )) s1 "+
								"JOIN "+ 
								"(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where  CRCL_CODE=:selectedCircelId AND STAT_CODE=:selectedStateId) s2 "+
								"On  s1.BRANCH_CODE = s2.BRANCH_CODE) "+
									"on s1.KIOSK_ID=s3.KIOSK_ID" )
	List<BillingPenaltyEntity> findbyFilterReport(@Param("selectedCircelId")
    String selectedCircelId, @Param("selectedStateId") String selectedStateId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId);
	
	
	
	
	@Query(value = 
			"select s1.RFP_REF_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s1.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.TOT_HRS,s3.TOT_DOWNTIME,s3.RELAXATION_HRS,s3.ACT_DOWNTIME, "+
					"s3.PENALTY_AMT from(select d.KIOSK_ID,  d.TOT_HRS, d.TOT_DOWNTIME, d.RELAXATION_HRS, d.ACT_DOWNTIME, "+
					"d.PENALTY_AMT,d.FIN_YR,d.QTR_ID  from TBL_DOWNTIME_QTR d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 "+
					"JOIN "+
					"((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.KIOSK_SERIAL_NO,k.RFP_REF_NO from TBL_KIOSK_MASTER k where k.VENDOR In " +
					"(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId )) s1 "+
					"JOIN "+ 
					"(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId) s2 "+
					"On  s1.BRANCH_CODE = s2.BRANCH_CODE) "+
						"on s1.KIOSK_ID=s3.KIOSK_ID",nativeQuery = true,
						countQuery ="select count(s3.KIOSK_ID) from(select d.KIOSK_ID,  d.TOT_HRS, d.TOT_DOWNTIME, d.RELAXATION_HRS, d.ACT_DOWNTIME, "+
								"d.PENALTY_AMT from TBL_DOWNTIME_QTR d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 "+
								"JOIN "+
								"((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.KIOSK_SERIAL_NO,k.RFP_REF_NO from TBL_KIOSK_MASTER k where k.VENDOR In " +
								"(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId )) s1 "+
								"JOIN "+ 
								"(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where  CRCL_CODE=:selectedCircelId) s2 "+
								"On  s1.BRANCH_CODE = s2.BRANCH_CODE) "+
									"on s1.KIOSK_ID=s3.KIOSK_ID" )
	List<BillingPenaltyEntity> findbyWithoutStateFilterReport(@Param("selectedCircelId")
    String selectedCircelId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId);


	
	@Query(value = 
			"select s1.RFP_REF_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s1.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.TOT_HRS,s3.TOT_DOWNTIME,s3.RELAXATION_HRS,s3.ACT_DOWNTIME, "+
					"s3.PENALTY_AMT from(select d.KIOSK_ID,  d.TOT_HRS, d.TOT_DOWNTIME, d.RELAXATION_HRS, d.ACT_DOWNTIME, "+
					"d.PENALTY_AMT,d.FIN_YR,d.QTR_ID  from TBL_DOWNTIME_QTR d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 "+
					"JOIN "+
					"((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.KIOSK_SERIAL_NO,k.RFP_REF_NO from TBL_KIOSK_MASTER k where k.VENDOR In " +
					"(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId ) AND k.RFP_REF_NO=:selectedRfpID) s1 "+
					"JOIN "+ 
					"(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId AND STAT_CODE=:selectedStateId) s2 "+
					"On  s1.BRANCH_CODE = s2.BRANCH_CODE) "+
						"on s1.KIOSK_ID=s3.KIOSK_ID",nativeQuery = true,
						countQuery ="select count(s3.KIOSK_ID) from(select d.KIOSK_ID,  d.TOT_HRS, d.TOT_DOWNTIME, d.RELAXATION_HRS, d.ACT_DOWNTIME, "+
								"d.PENALTY_AMT from TBL_DOWNTIME_QTR d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 "+
								"JOIN "+
								"((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.KIOSK_SERIAL_NO,k.RFP_REF_NO from TBL_KIOSK_MASTER k where k.VENDOR In " +
								"(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId )) s1 "+
								"JOIN "+ 
								"(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where  CRCL_CODE=:selectedCircelId AND STAT_CODE=:selectedStateId) s2 "+
								"On  s1.BRANCH_CODE = s2.BRANCH_CODE) "+
									"on s1.KIOSK_ID=s3.KIOSK_ID" )
	List<BillingPenaltyEntity> findbyFilterWithRFPReport(@Param("selectedCircelId")
    String selectedCircelId, @Param("selectedStateId") String selectedStateId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId, @Param("selectedRfpID")String selectedRfpID);
	
	@Query(value = 
			"select s1.RFP_REF_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s1.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.TOT_HRS,s3.TOT_DOWNTIME,s3.RELAXATION_HRS,s3.ACT_DOWNTIME, "+
					"s3.PENALTY_AMT from(select d.KIOSK_ID,  d.TOT_HRS, d.TOT_DOWNTIME, d.RELAXATION_HRS, d.ACT_DOWNTIME, "+
					"d.PENALTY_AMT,d.FIN_YR,d.QTR_ID  from TBL_DOWNTIME_QTR d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 "+
					"JOIN "+
					"((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.KIOSK_SERIAL_NO,k.RFP_REF_NO from TBL_KIOSK_MASTER k where k.VENDOR In " +
					"(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId ) AND k.RFP_REF_NO=:selectedRfpID) s1 "+
					"JOIN "+ 
					"(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId) s2 "+
					"On  s1.BRANCH_CODE = s2.BRANCH_CODE) "+
						"on s1.KIOSK_ID=s3.KIOSK_ID",nativeQuery = true,
						countQuery ="select count(s3.KIOSK_ID) from(select d.KIOSK_ID,  d.TOT_HRS, d.TOT_DOWNTIME, d.RELAXATION_HRS, d.ACT_DOWNTIME, "+
								"d.PENALTY_AMT from TBL_DOWNTIME_QTR d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 "+
								"JOIN "+
								"((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.KIOSK_SERIAL_NO,k.RFP_REF_NO from TBL_KIOSK_MASTER k where k.VENDOR In " +
								"(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId )) s1 "+
								"JOIN "+ 
								"(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where  CRCL_CODE=:selectedCircelId AND STAT_CODE=:selectedStateId) s2 "+
								"On  s1.BRANCH_CODE = s2.BRANCH_CODE) "+
									"on s1.KIOSK_ID=s3.KIOSK_ID" )
	List<BillingPenaltyEntity> findbyFilterRfpWithoutStateReport(@Param("selectedCircelId")
    String selectedCircelId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId, @Param("selectedRfpID")String selectedRfpID);
	

}
