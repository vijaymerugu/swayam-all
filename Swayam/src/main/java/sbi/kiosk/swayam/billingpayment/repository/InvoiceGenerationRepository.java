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
	
	
	
	@Query(value = "select s1.RFP_REF_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s3.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.PENALTY_AMT, s3.SPARE_PARTS, s3.INVOICE_AMT, \r\n" + 
			"            s3.CORRECTION_AMT,s3.FINAL_AMT from(select d.KIOSK_ID,d.KIOSK_SERIAL_NO ,d.FIN_YR,d.QTR_ID, d.PENALTY_AMT, (d.SPARE_PARTS_COST + d.AMC_COST) as SPARE_PARTS, d.INVOICE_AMT, "
			+ "			d.CORRECTION_AMT, d.FINAL_AMT from TBL_INVOICE d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 \r\n" + 
			"					JOIN \r\n" + 
			"					((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.RFP_REF_NO,k.KIOSK_SERIAL_NO from TBL_KIOSK_MASTER k where k.VENDOR In  \r\n" + 
			"					(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId)) s1 \r\n" + 
			"					JOIN  \r\n" + 
			"					(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId AND STAT_CODE=:selectedStateId) s2 \r\n" + 
			"					On  s1.BRANCH_CODE = s2.BRANCH_CODE)\r\n" + 
			"                    on s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO",nativeQuery = true,
			countQuery = "select count(s3.KIOSK_SERIAL_NO) from(select d.KIOSK_ID,d.KIOSK_SERIAL_NO ,d.FIN_YR,d.QTR_ID, d.PENALTY_AMT, (d.SPARE_PARTS_COST + d.AMC_COST) as SPARE_PARTS, d.INVOICE_AMT, "
					+ "			d.CORRECTION_AMT, d.FINAL_AMT from TBL_INVOICE d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 \r\n" + 
					"					JOIN \r\n" + 
					"					((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.RFP_REF_NO,k.KIOSK_SERIAL_NO from TBL_KIOSK_MASTER k where k.VENDOR In  \r\n" + 
					"					(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId)) s1 \r\n" + 
					"					JOIN  \r\n" + 
					"					(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId AND STAT_CODE=:selectedStateId) s2 \r\n" + 
					"					On  s1.BRANCH_CODE = s2.BRANCH_CODE)\r\n" + 
					"                    on s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO")
	Page<InvoiceGeneration> findbyFilter(@Param("selectedCircelId")
    String selectedCircelId, @Param("selectedStateId") String selectedStateId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId,Pageable pageable);
	
	
	
	@Query(value = "select s1.RFP_REF_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s3.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.PENALTY_AMT, s3.SPARE_PARTS, s3.INVOICE_AMT, \r\n" + 
			"            s3.CORRECTION_AMT,s3.FINAL_AMT from(select d.KIOSK_ID,d.KIOSK_SERIAL_NO ,d.FIN_YR,d.QTR_ID, d.PENALTY_AMT, (d.SPARE_PARTS_COST + d.AMC_COST) as SPARE_PARTS, d.INVOICE_AMT, "
			+ "			d.CORRECTION_AMT, d.FINAL_AMT from TBL_INVOICE d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 \r\n" + 
			"					JOIN \r\n" + 
			"					((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.RFP_REF_NO,k.KIOSK_SERIAL_NO from TBL_KIOSK_MASTER k where k.VENDOR In  \r\n" + 
			"					(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId)) s1 \r\n" + 
			"					JOIN  \r\n" + 
			"					(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId) s2 \r\n" + 
			"					On  s1.BRANCH_CODE = s2.BRANCH_CODE)\r\n" + 
			"                    on s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO",nativeQuery = true,
			countQuery = "select count(s3.KIOSK_SERIAL_NO) from(select d.KIOSK_ID,d.KIOSK_SERIAL_NO ,d.FIN_YR,d.QTR_ID, d.PENALTY_AMT, (d.SPARE_PARTS_COST + d.AMC_COST) as SPARE_PARTS, d.INVOICE_AMT, "
					+ "			d.CORRECTION_AMT, d.FINAL_AMT from TBL_INVOICE d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 \r\n" + 
					"					JOIN \r\n" + 
					"					((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.RFP_REF_NO,k.KIOSK_SERIAL_NO from TBL_KIOSK_MASTER k where k.VENDOR In  \r\n" + 
					"					(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId)) s1 \r\n" + 
					"					JOIN  \r\n" + 
					"					(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId) s2 \r\n" + 
					"					On  s1.BRANCH_CODE = s2.BRANCH_CODE)\r\n" + 
					"                    on s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO")
	Page<InvoiceGeneration> findbyWithoutStateFilter(@Param("selectedCircelId")
    String selectedCircelId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId,Pageable pageable);
	
	
	@Query(value = "select s1.RFP_REF_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s3.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.PENALTY_AMT, s3.SPARE_PARTS, s3.INVOICE_AMT, \r\n" + 
			"            s3.CORRECTION_AMT,s3.FINAL_AMT from(select d.KIOSK_ID,d.KIOSK_SERIAL_NO ,d.FIN_YR,d.QTR_ID, d.PENALTY_AMT, (d.SPARE_PARTS_COST + d.AMC_COST) as SPARE_PARTS, d.INVOICE_AMT, "
			+ "			d.CORRECTION_AMT, d.FINAL_AMT from TBL_INVOICE d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 \r\n" + 
			"					JOIN \r\n" + 
			"					((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.RFP_REF_NO,k.KIOSK_SERIAL_NO from TBL_KIOSK_MASTER k where k.VENDOR In  \r\n" + 
			"					(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId) AND k.RFP_REF_NO=:selectedRfpID) s1 \r\n" + 
			"					JOIN  \r\n" + 
			"					(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId AND STAT_CODE=:selectedStateId) s2 \r\n" + 
			"					On  s1.BRANCH_CODE = s2.BRANCH_CODE)\r\n" + 
			"                    on s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO",nativeQuery = true,
			countQuery = "select count(s3.KIOSK_SERIAL_NO) from(select d.KIOSK_ID,d.KIOSK_SERIAL_NO ,d.FIN_YR,d.QTR_ID, d.PENALTY_AMT, (d.SPARE_PARTS_COST + d.AMC_COST) as SPARE_PARTS, d.INVOICE_AMT, "
					+ "			d.CORRECTION_AMT, d.FINAL_AMT from TBL_INVOICE d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 \r\n" + 
					"					JOIN \r\n" + 
					"					((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.RFP_REF_NO,k.KIOSK_SERIAL_NO from TBL_KIOSK_MASTER k where k.VENDOR In  \r\n" + 
					"					(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId) AND k.RFP_REF_NO=:selectedRfpID) s1 \r\n" + 
					"					JOIN  \r\n" + 
					"					(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId AND STAT_CODE=:selectedStateId) s2 \r\n" + 
					"					On  s1.BRANCH_CODE = s2.BRANCH_CODE)\r\n" + 
					"                    on s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO")
	Page<InvoiceGeneration> findbyFilterWithRFP(@Param("selectedCircelId")
    String selectedCircelId, @Param("selectedStateId") String selectedStateId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId, @Param("selectedRfpID")String selectedRfpID, Pageable pageable);
	
	@Query(value = "select s1.RFP_REF_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s3.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.PENALTY_AMT, s3.SPARE_PARTS, s3.INVOICE_AMT, \r\n" + 
			"            s3.CORRECTION_AMT,s3.FINAL_AMT from(select d.KIOSK_ID,d.KIOSK_SERIAL_NO ,d.FIN_YR,d.QTR_ID, d.PENALTY_AMT, (d.SPARE_PARTS_COST + d.AMC_COST) as SPARE_PARTS, d.INVOICE_AMT, "
			+ "			d.CORRECTION_AMT, d.FINAL_AMT from TBL_INVOICE d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 \r\n" + 
			"					JOIN \r\n" + 
			"					((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.RFP_REF_NO,k.KIOSK_SERIAL_NO from TBL_KIOSK_MASTER k where k.VENDOR In  \r\n" + 
			"					(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId) AND k.RFP_REF_NO=:selectedRfpID) s1 \r\n" + 
			"					JOIN  \r\n" + 
			"					(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId) s2 \r\n" + 
			"					On  s1.BRANCH_CODE = s2.BRANCH_CODE)\r\n" + 
			"                    on s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO",nativeQuery = true,
			countQuery = "select count(s3.KIOSK_SERIAL_NO) from(select d.KIOSK_ID,d.KIOSK_SERIAL_NO ,d.FIN_YR,d.QTR_ID, d.PENALTY_AMT, (d.SPARE_PARTS_COST + d.AMC_COST) as SPARE_PARTS, d.INVOICE_AMT, "
					+ "			d.CORRECTION_AMT, d.FINAL_AMT from TBL_INVOICE d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 \r\n" + 
					"					JOIN \r\n" + 
					"					((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.RFP_REF_NO,k.KIOSK_SERIAL_NO from TBL_KIOSK_MASTER k where k.VENDOR In  \r\n" + 
					"					(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId) AND k.RFP_REF_NO=:selectedRfpID) s1 \r\n" + 
					"					JOIN  \r\n" + 
					"					(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId) s2 \r\n" + 
					"					On  s1.BRANCH_CODE = s2.BRANCH_CODE)\r\n" + 
					"                    on s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO")
	Page<InvoiceGeneration> findbyFilterRfpWithoutState(@Param("selectedCircelId")
    String selectedCircelId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId, @Param("selectedRfpID")String selectedRfpID, Pageable pageable);
	
	
	@Query(value = "select s1.RFP_REF_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s3.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.PENALTY_AMT, s3.SPARE_PARTS, s3.INVOICE_AMT, \r\n" + 
			"            s3.CORRECTION_AMT,s3.FINAL_AMT from(select d.KIOSK_ID,d.KIOSK_SERIAL_NO ,d.FIN_YR,d.QTR_ID, d.PENALTY_AMT, (d.SPARE_PARTS_COST + d.AMC_COST) as SPARE_PARTS, d.INVOICE_AMT, "
			+ "			d.CORRECTION_AMT, d.FINAL_AMT from TBL_INVOICE d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 \r\n" + 
			"					JOIN \r\n" + 
			"					((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.RFP_REF_NO,k.KIOSK_SERIAL_NO from TBL_KIOSK_MASTER k where k.VENDOR In  \r\n" + 
			"					(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId)) s1 \r\n" + 
			"					JOIN  \r\n" + 
			"					(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId AND STAT_CODE=:selectedStateId) s2 \r\n" + 
			"					On  s1.BRANCH_CODE = s2.BRANCH_CODE)\r\n" + 
			"                    on s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO",nativeQuery = true,
			countQuery = "select count(s3.KIOSK_SERIAL_NO) from(select d.KIOSK_ID,d.KIOSK_SERIAL_NO ,d.FIN_YR,d.QTR_ID, d.PENALTY_AMT, (d.SPARE_PARTS_COST + d.AMC_COST) as SPARE_PARTS, d.INVOICE_AMT, "
					+ "			d.CORRECTION_AMT, d.FINAL_AMT from TBL_INVOICE d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 \r\n" + 
					"					JOIN \r\n" + 
					"					((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.RFP_REF_NO,k.KIOSK_SERIAL_NO from TBL_KIOSK_MASTER k where k.VENDOR In  \r\n" + 
					"					(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId)) s1 \r\n" + 
					"					JOIN  \r\n" + 
					"					(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId AND STAT_CODE=:selectedStateId) s2 \r\n" + 
					"					On  s1.BRANCH_CODE = s2.BRANCH_CODE)\r\n" + 
					"                    on s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO")
	List<InvoiceGeneration> findbyFilterReport(@Param("selectedCircelId")
    String selectedCircelId, @Param("selectedStateId") String selectedStateId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId);
	
	
	
	@Query(value = "select s1.RFP_REF_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s3.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.PENALTY_AMT, s3.SPARE_PARTS, s3.INVOICE_AMT, \r\n" + 
			"            s3.CORRECTION_AMT,s3.FINAL_AMT from(select d.KIOSK_ID,d.KIOSK_SERIAL_NO ,d.FIN_YR,d.QTR_ID, d.PENALTY_AMT, (d.SPARE_PARTS_COST + d.AMC_COST) as SPARE_PARTS, d.INVOICE_AMT, "
			+ "			d.CORRECTION_AMT, d.FINAL_AMT from TBL_INVOICE d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 \r\n" + 
			"					JOIN \r\n" + 
			"					((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.RFP_REF_NO,k.KIOSK_SERIAL_NO from TBL_KIOSK_MASTER k where k.VENDOR In  \r\n" + 
			"					(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId)) s1 \r\n" + 
			"					JOIN  \r\n" + 
			"					(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId) s2 \r\n" + 
			"					On  s1.BRANCH_CODE = s2.BRANCH_CODE)\r\n" + 
			"                    on s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO",nativeQuery = true,
			countQuery = "select count(s3.KIOSK_SERIAL_NO) from(select d.KIOSK_ID,d.KIOSK_SERIAL_NO ,d.FIN_YR,d.QTR_ID, d.PENALTY_AMT, (d.SPARE_PARTS_COST + d.AMC_COST) as SPARE_PARTS, d.INVOICE_AMT, "
					+ "			d.CORRECTION_AMT, d.FINAL_AMT from TBL_INVOICE d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 \r\n" + 
					"					JOIN \r\n" + 
					"					((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.RFP_REF_NO,k.KIOSK_SERIAL_NO from TBL_KIOSK_MASTER k where k.VENDOR In  \r\n" + 
					"					(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId)) s1 \r\n" + 
					"					JOIN  \r\n" + 
					"					(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId) s2 \r\n" + 
					"					On  s1.BRANCH_CODE = s2.BRANCH_CODE)\r\n" + 
					"                    on s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO")
	List<InvoiceGeneration> findbyWithoutStateFilterReport(@Param("selectedCircelId")
    String selectedCircelId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId);
	
	
	@Query(value = "select s1.RFP_REF_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s3.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.PENALTY_AMT, s3.SPARE_PARTS, s3.INVOICE_AMT, \r\n" + 
			"            s3.CORRECTION_AMT,s3.FINAL_AMT from(select d.KIOSK_ID,d.KIOSK_SERIAL_NO ,d.FIN_YR,d.QTR_ID, d.PENALTY_AMT, (d.SPARE_PARTS_COST + d.AMC_COST) as SPARE_PARTS, d.INVOICE_AMT, "
			+ "			d.CORRECTION_AMT, d.FINAL_AMT from TBL_INVOICE d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 \r\n" + 
			"					JOIN \r\n" + 
			"					((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.RFP_REF_NO,k.KIOSK_SERIAL_NO from TBL_KIOSK_MASTER k where k.VENDOR In  \r\n" + 
			"					(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId) AND k.RFP_REF_NO=:selectedRfpID) s1 \r\n" + 
			"					JOIN  \r\n" + 
			"					(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId AND STAT_CODE=:selectedStateId) s2 \r\n" + 
			"					On  s1.BRANCH_CODE = s2.BRANCH_CODE)\r\n" + 
			"                    on s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO",nativeQuery = true,
			countQuery = "select count(s3.KIOSK_SERIAL_NO) from(select d.KIOSK_ID,d.KIOSK_SERIAL_NO ,d.FIN_YR,d.QTR_ID, d.PENALTY_AMT, (d.SPARE_PARTS_COST + d.AMC_COST) as SPARE_PARTS, d.INVOICE_AMT, "
					+ "			d.CORRECTION_AMT, d.FINAL_AMT from TBL_INVOICE d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 \r\n" + 
					"					JOIN \r\n" + 
					"					((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.RFP_REF_NO,k.KIOSK_SERIAL_NO from TBL_KIOSK_MASTER k where k.VENDOR In  \r\n" + 
					"					(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId) AND k.RFP_REF_NO=:selectedRfpID) s1 \r\n" + 
					"					JOIN  \r\n" + 
					"					(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId AND STAT_CODE=:selectedStateId) s2 \r\n" + 
					"					On  s1.BRANCH_CODE = s2.BRANCH_CODE)\r\n" + 
					"                    on s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO")
	List<InvoiceGeneration> findbyFilterWithRFPReport(@Param("selectedCircelId")
    String selectedCircelId, @Param("selectedStateId") String selectedStateId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId, @Param("selectedRfpID")String selectedRfpID);
	
	@Query(value = "select s1.RFP_REF_NO,s1.VENDOR,  s2.CRCL_NAME, s2.STAT_DESC, s3.KIOSK_ID, s3.KIOSK_SERIAL_NO,s3.QTR_ID,s3.FIN_YR,s3.PENALTY_AMT, s3.SPARE_PARTS, s3.INVOICE_AMT, \r\n" + 
			"            s3.CORRECTION_AMT,s3.FINAL_AMT from(select d.KIOSK_ID,d.KIOSK_SERIAL_NO ,d.FIN_YR,d.QTR_ID, d.PENALTY_AMT, (d.SPARE_PARTS_COST + d.AMC_COST) as SPARE_PARTS, d.INVOICE_AMT, "
			+ "			d.CORRECTION_AMT, d.FINAL_AMT from TBL_INVOICE d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 \r\n" + 
			"					JOIN \r\n" + 
			"					((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.RFP_REF_NO,k.KIOSK_SERIAL_NO from TBL_KIOSK_MASTER k where k.VENDOR In  \r\n" + 
			"					(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId) AND k.RFP_REF_NO=:selectedRfpID) s1 \r\n" + 
			"					JOIN  \r\n" + 
			"					(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId) s2 \r\n" + 
			"					On  s1.BRANCH_CODE = s2.BRANCH_CODE)\r\n" + 
			"                    on s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO",nativeQuery = true,
			countQuery = "select count(s3.KIOSK_SERIAL_NO) from(select d.KIOSK_ID,d.KIOSK_SERIAL_NO ,d.FIN_YR,d.QTR_ID, d.PENALTY_AMT, (d.SPARE_PARTS_COST + d.AMC_COST) as SPARE_PARTS, d.INVOICE_AMT, "
					+ "			d.CORRECTION_AMT, d.FINAL_AMT from TBL_INVOICE d where d.QTR_ID=:quterTimePeriod AND d.FIN_YR=:finacialYear) s3 \r\n" + 
					"					JOIN \r\n" + 
					"					((select k.KIOSK_ID, k.BRANCH_CODE ,k.VENDOR,k.RFP_REF_NO,k.KIOSK_SERIAL_NO from TBL_KIOSK_MASTER k where k.VENDOR In  \r\n" + 
					"					(select v.VENDOR from TBL_VENDOR_DETAILS v where VEND_ID=:selectedVendorId) AND k.RFP_REF_NO=:selectedRfpID) s1 \r\n" + 
					"					JOIN  \r\n" + 
					"					(select b.BRANCH_CODE, b.CRCL_NAME, b.STAT_DESC from TBL_BRANCH_MASTER b where CRCL_CODE=:selectedCircelId) s2 \r\n" + 
					"					On  s1.BRANCH_CODE = s2.BRANCH_CODE)\r\n" + 
					"                    on s1.KIOSK_SERIAL_NO=s3.KIOSK_SERIAL_NO")
	List<InvoiceGeneration> findbyFilterRfpWithoutStateReport(@Param("selectedCircelId")
    String selectedCircelId,
    @Param("quterTimePeriod") String quterTimePeriod,@Param("finacialYear")String finacialYear,
    @Param("selectedVendorId") String selectedVendorId, @Param("selectedRfpID")String selectedRfpID);

}