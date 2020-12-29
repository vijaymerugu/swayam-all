package sbi.kiosk.swayam.billingpayment.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.TaxSummaryEntity;
import sbi.kiosk.swayam.common.entity.TaxSummaryEntity;


@Repository
public interface TaxSummaryRepository extends PagingAndSortingRepository<TaxSummaryEntity, String> {
	
	
	
	@Query(value = 
			"SELECT DISTINCT \r\n" + 
			"			    tax.RFP_NO,\r\n" + 
			"                tax.FIN_YR,\r\n" + 
			"			    vd.COMPANY_SHORT_NM,\r\n" + 
			"			    tax.CRCL_NAME,\r\n" + 
			"			    tax.STAT_DESC,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q1',GST_INVOICE_AMT,0) ) AS Q1_INVOICE_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q2',GST_INVOICE_AMT,0) ) AS Q2_INVOICE_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q3',GST_INVOICE_AMT,0) ) AS Q3_INVOICE_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q4',GST_INVOICE_AMT,0) ) AS Q4_INVOICE_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q1',GST_PENALTY_AMT,0) ) AS Q1_PENALTY_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q2',GST_PENALTY_AMT,0) ) AS Q2_PENALTY_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q3',GST_PENALTY_AMT,0) ) AS Q3_PENALTY_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q4',GST_PENALTY_AMT,0) ) AS Q4_PENALTY_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q1',TOTAL_GST,0) ) AS Q1_FINAL_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q2',TOTAL_GST,0) ) AS Q2_FINAL_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q3',TOTAL_GST,0) ) AS Q3_FINAL_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q4',TOTAL_GST,0) ) AS Q4_FINAL_AMT\r\n" + 
			"			FROM\r\n" + 
			"			    TBL_TAX tax\r\n" + 
			"				INNER JOIN tbl_vendor_details vd\r\n" + 
			"                ON tax.vendor_id=vd.VENDOR_ID\r\n" + 
			"                \r\n" + 
			"            WHERE\r\n" + 
			"            tax.FIN_YR=:quterTimePeriod AND \r\n" + 
			"            tax.CRCL_NAME=(select crcl_name from TBL_CIRCLE WHERE crcl_code=:selectedCircelId) AND \r\n" + 
			"            tax.stat_desc=(select DISTINCT stat_desc from TBL_BRANCH_MASTER WHERE stat_code=:selectedStateId)\r\n" + 
			"            \r\n" + 
			"				\r\n" + 
			"				GROUP BY\r\n" + 
			"                tax.RFP_NO,\r\n" + 
			"			    tax.FIN_YR,\r\n" + 
			"                COMPANY_SHORT_NM,\r\n" + 
			"			    tax.CRCL_NAME,\r\n" + 
			"			    tax.STAT_DESC",nativeQuery = true,
			countQuery="")
	Page<TaxSummaryEntity> findbyFilter(@Param("selectedCircelId")
    String selectedCircelId, @Param("selectedStateId") String selectedStateId,
    @Param("quterTimePeriod") String quterTimePeriod,Pageable pageable);
	
	
	
	
	@Query(value = 
			"SELECT DISTINCT \r\n" + 
			"			    tax.RFP_NO,\r\n" + 
			"                tax.FIN_YR,\r\n" + 
			"			    vd.COMPANY_SHORT_NM,\r\n" + 
			"			    tax.CRCL_NAME,\r\n" + 
			"			    tax.STAT_DESC,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q1',GST_INVOICE_AMT,0) ) AS Q1_INVOICE_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q2',GST_INVOICE_AMT,0) ) AS Q2_INVOICE_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q3',GST_INVOICE_AMT,0) ) AS Q3_INVOICE_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q4',GST_INVOICE_AMT,0) ) AS Q4_INVOICE_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q1',GST_PENALTY_AMT,0) ) AS Q1_PENALTY_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q2',GST_PENALTY_AMT,0) ) AS Q2_PENALTY_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q3',GST_PENALTY_AMT,0) ) AS Q3_PENALTY_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q4',GST_PENALTY_AMT,0) ) AS Q4_PENALTY_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q1',TOTAL_GST,0) ) AS Q1_FINAL_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q2',TOTAL_GST,0) ) AS Q2_FINAL_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q3',TOTAL_GST,0) ) AS Q3_FINAL_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q4',TOTAL_GST,0) ) AS Q4_FINAL_AMT\r\n" + 
			"			FROM\r\n" + 
			"			    TBL_TAX tax\r\n" + 
			"				INNER JOIN tbl_vendor_details vd\r\n" + 
			"                ON tax.vendor_id=vd.VENDOR_ID\r\n" + 
			"                \r\n" + 
			"            WHERE\r\n" + 
			"            tax.FIN_YR=:quterTimePeriod AND \r\n" + 
			"            tax.CRCL_NAME=(select crcl_name from TBL_CIRCLE WHERE crcl_code=:selectedCircelId) " +
			"				\r\n" + 
			"				GROUP BY\r\n" + 
			"                tax.RFP_NO,\r\n" + 
			"			    tax.FIN_YR,\r\n" + 
			"                COMPANY_SHORT_NM,\r\n" + 
			"			    tax.CRCL_NAME,\r\n" + 
			"			    tax.STAT_DESC",nativeQuery = true,
			countQuery="")
	Page<TaxSummaryEntity> findbyWithoutStateFilter(@Param("selectedCircelId")
    String selectedCircelId,
    @Param("quterTimePeriod") String quterTimePeriod,Pageable pageable);
	
	
	
	@Query(value = 
			"SELECT DISTINCT \r\n" + 
			"			    tax.RFP_NO,\r\n" + 
			"                tax.FIN_YR,\r\n" + 
			"			    vd.COMPANY_SHORT_NM,\r\n" + 
			"			    tax.CRCL_NAME,\r\n" + 
			"			    tax.STAT_DESC,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q1',GST_INVOICE_AMT,0) ) AS Q1_INVOICE_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q2',GST_INVOICE_AMT,0) ) AS Q2_INVOICE_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q3',GST_INVOICE_AMT,0) ) AS Q3_INVOICE_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q4',GST_INVOICE_AMT,0) ) AS Q4_INVOICE_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q1',GST_PENALTY_AMT,0) ) AS Q1_PENALTY_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q2',GST_PENALTY_AMT,0) ) AS Q2_PENALTY_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q3',GST_PENALTY_AMT,0) ) AS Q3_PENALTY_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q4',GST_PENALTY_AMT,0) ) AS Q4_PENALTY_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q1',TOTAL_GST,0) ) AS Q1_FINAL_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q2',TOTAL_GST,0) ) AS Q2_FINAL_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q3',TOTAL_GST,0) ) AS Q3_FINAL_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q4',TOTAL_GST,0) ) AS Q4_FINAL_AMT\r\n" + 
			"			FROM\r\n" + 
			"			    TBL_TAX tax\r\n" + 
			"				INNER JOIN tbl_vendor_details vd\r\n" + 
			"                ON tax.vendor_id=vd.VENDOR_ID\r\n" + 
			"                \r\n" + 
			"            WHERE\r\n" + 
			"            tax.FIN_YR=:quterTimePeriod \r\n" + 
			"            \r\n" + 
			"				\r\n" + 
			"				GROUP BY\r\n" + 
			"                tax.RFP_NO,\r\n" + 
			"			    tax.FIN_YR,\r\n" + 
			"                COMPANY_SHORT_NM,\r\n" + 
			"			    tax.CRCL_NAME,\r\n" + 
			"			    tax.STAT_DESC",nativeQuery = true,
			countQuery="")
	Page<TaxSummaryEntity> findCCFilter(
			@Param("quterTimePeriod") String quterTimePeriod,Pageable pageable);

	
	
	
	@Query(value = 
			"SELECT DISTINCT \r\n" + 
			"			    tax.RFP_NO,\r\n" + 
			"                tax.FIN_YR,\r\n" + 
			"			    vd.COMPANY_SHORT_NM,\r\n" + 
			"			    tax.CRCL_NAME,\r\n" + 
			"			    tax.STAT_DESC,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q1',GST_INVOICE_AMT,0) ) AS Q1_INVOICE_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q2',GST_INVOICE_AMT,0) ) AS Q2_INVOICE_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q3',GST_INVOICE_AMT,0) ) AS Q3_INVOICE_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q4',GST_INVOICE_AMT,0) ) AS Q4_INVOICE_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q1',GST_PENALTY_AMT,0) ) AS Q1_PENALTY_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q2',GST_PENALTY_AMT,0) ) AS Q2_PENALTY_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q3',GST_PENALTY_AMT,0) ) AS Q3_PENALTY_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q4',GST_PENALTY_AMT,0) ) AS Q4_PENALTY_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q1',TOTAL_GST,0) ) AS Q1_FINAL_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q2',TOTAL_GST,0) ) AS Q2_FINAL_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q3',TOTAL_GST,0) ) AS Q3_FINAL_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q4',TOTAL_GST,0) ) AS Q4_FINAL_AMT\r\n" + 
			"			FROM\r\n" + 
			"			    TBL_TAX tax\r\n" + 
			"				INNER JOIN tbl_vendor_details vd\r\n" + 
			"                ON tax.vendor_id=vd.VENDOR_ID\r\n" + 
			"                \r\n" + 
			"            WHERE\r\n" + 
			"            tax.FIN_YR=:quterTimePeriod AND \r\n" + 
			"            tax.CRCL_NAME=(select crcl_name from TBL_CIRCLE WHERE crcl_code=:selectedCircelId) AND \r\n" + 
			"            tax.stat_desc=(select DISTINCT stat_desc from TBL_BRANCH_MASTER WHERE stat_code=:selectedStateId)\r\n" + 
			"            \r\n" + 
			"				\r\n" + 
			"				GROUP BY\r\n" + 
			"                tax.RFP_NO,\r\n" + 
			"			    tax.FIN_YR,\r\n" + 
			"                COMPANY_SHORT_NM,\r\n" + 
			"			    tax.CRCL_NAME,\r\n" + 
			"			    tax.STAT_DESC",nativeQuery = true,
			countQuery="")
	List<TaxSummaryEntity> findbyFilterReport(@Param("selectedCircelId")
    String selectedCircelId, @Param("selectedStateId") String selectedStateId,
    @Param("quterTimePeriod") String quterTimePeriod);
	
	
	
	
	@Query(value = 
			"SELECT DISTINCT \r\n" + 
			"			    tax.RFP_NO,\r\n" + 
			"                tax.FIN_YR,\r\n" + 
			"			    vd.COMPANY_SHORT_NM,\r\n" + 
			"			    tax.CRCL_NAME,\r\n" + 
			"			    tax.STAT_DESC,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q1',GST_INVOICE_AMT,0) ) AS Q1_INVOICE_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q2',GST_INVOICE_AMT,0) ) AS Q2_INVOICE_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q3',GST_INVOICE_AMT,0) ) AS Q3_INVOICE_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q4',GST_INVOICE_AMT,0) ) AS Q4_INVOICE_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q1',GST_PENALTY_AMT,0) ) AS Q1_PENALTY_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q2',GST_PENALTY_AMT,0) ) AS Q2_PENALTY_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q3',GST_PENALTY_AMT,0) ) AS Q3_PENALTY_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q4',GST_PENALTY_AMT,0) ) AS Q4_PENALTY_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q1',TOTAL_GST,0) ) AS Q1_FINAL_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q2',TOTAL_GST,0) ) AS Q2_FINAL_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q3',TOTAL_GST,0) ) AS Q3_FINAL_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q4',TOTAL_GST,0) ) AS Q4_FINAL_AMT\r\n" + 
			"			FROM\r\n" + 
			"			    TBL_TAX tax\r\n" + 
			"				INNER JOIN tbl_vendor_details vd\r\n" + 
			"                ON tax.vendor_id=vd.VENDOR_ID\r\n" + 
			"                \r\n" + 
			"            WHERE\r\n" + 
			"            tax.FIN_YR=:quterTimePeriod AND \r\n" + 
			"            tax.CRCL_NAME=(select crcl_name from TBL_CIRCLE WHERE crcl_code=:selectedCircelId) " +
			"				\r\n" + 
			"				GROUP BY\r\n" + 
			"                tax.RFP_NO,\r\n" + 
			"			    tax.FIN_YR,\r\n" + 
			"                COMPANY_SHORT_NM,\r\n" + 
			"			    tax.CRCL_NAME,\r\n" + 
			"			    tax.STAT_DESC",nativeQuery = true,
			countQuery="")
	List<TaxSummaryEntity> findbyWithoutStateFilterReport(@Param("selectedCircelId")
    String selectedCircelId,
    @Param("quterTimePeriod") String quterTimePeriod);
	
	
	
	@Query(value = 
			"SELECT DISTINCT \r\n" + 
			"			    tax.RFP_NO,\r\n" + 
			"                tax.FIN_YR,\r\n" + 
			"			    vd.COMPANY_SHORT_NM,\r\n" + 
			"			    tax.CRCL_NAME,\r\n" + 
			"			    tax.STAT_DESC,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q1',GST_INVOICE_AMT,0) ) AS Q1_INVOICE_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q2',GST_INVOICE_AMT,0) ) AS Q2_INVOICE_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q3',GST_INVOICE_AMT,0) ) AS Q3_INVOICE_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q4',GST_INVOICE_AMT,0) ) AS Q4_INVOICE_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q1',GST_PENALTY_AMT,0) ) AS Q1_PENALTY_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q2',GST_PENALTY_AMT,0) ) AS Q2_PENALTY_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q3',GST_PENALTY_AMT,0) ) AS Q3_PENALTY_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q4',GST_PENALTY_AMT,0) ) AS Q4_PENALTY_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q1',TOTAL_GST,0) ) AS Q1_FINAL_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q2',TOTAL_GST,0) ) AS Q2_FINAL_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q3',TOTAL_GST,0) ) AS Q3_FINAL_AMT,\r\n" + 
			"			    SUM(DECODE(QTR_ID,'Q4',TOTAL_GST,0) ) AS Q4_FINAL_AMT\r\n" + 
			"			FROM\r\n" + 
			"			    TBL_TAX tax\r\n" + 
			"				INNER JOIN tbl_vendor_details vd\r\n" + 
			"                ON tax.vendor_id=vd.VENDOR_ID\r\n" + 
			"                \r\n" + 
			"            WHERE\r\n" + 
			"            tax.FIN_YR=:quterTimePeriod \r\n" + 
			"            \r\n" + 
			"				\r\n" + 
			"				GROUP BY\r\n" + 
			"                tax.RFP_NO,\r\n" + 
			"			    tax.FIN_YR,\r\n" + 
			"                COMPANY_SHORT_NM,\r\n" + 
			"			    tax.CRCL_NAME,\r\n" + 
			"			    tax.STAT_DESC",nativeQuery = true,
			countQuery="")
	List<TaxSummaryEntity> findCCFilterReport(
			@Param("quterTimePeriod") String quterTimePeriod);
	
	

}
