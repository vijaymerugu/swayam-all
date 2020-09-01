package sbi.kiosk.swayam.billingpayment.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.InvoiceSummaryEntity;


@Repository
public interface InvoiceSummaryRepository extends PagingAndSortingRepository<InvoiceSummaryEntity, String> {
	
	
	
	@Query(value = 
			"SELECT DISTINCT \r\n" + 
			"    INV.FIN_YR,\r\n" + 
			"    KM.VENDOR,\r\n" + 
			"    BM.CRCL_NAME,\r\n" + 
			"    BM.STAT_DESC,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q1',INVOICE_AMT,0) ) AS Q1_INVOICE_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q2',INVOICE_AMT,0) ) AS Q2_INVOICE_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q3',INVOICE_AMT,0) ) AS Q3_INVOICE_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q4',INVOICE_AMT,0) ) AS Q4_INVOICE_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q1',PENALTY_AMT,0) ) AS Q1_PENALTY_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q2',PENALTY_AMT,0) ) AS Q2_PENALTY_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q3',PENALTY_AMT,0) ) AS Q3_PENALTY_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q4',PENALTY_AMT,0) ) AS Q4_PENALTY_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q1',FINAL_AMT,0) ) AS Q1_FINAL_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q2',FINAL_AMT,0) ) AS Q2_FINAL_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q3',FINAL_AMT,0) ) AS Q3_FINAL_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q4',FINAL_AMT,0) ) AS Q4_FINAL_AMT\r\n" + 
			"FROM\r\n" + 
			"    TBL_INVOICE INV\r\n" + 
			"    INNER JOIN TBL_KIOSK_MASTER KM ON INV.KIOSK_ID = KM.KIOSK_ID\r\n" + 
			"    INNER JOIN TBL_BRANCH_MASTER BM ON KM.BRANCH_CODE = BM.BRANCH_CODE    \r\n" + 
			"Where \r\n" + 
			"    bm.crcl_code=:selectedCircelId AND\r\n" + 
			"    bm.stat_code=:selectedStateId AND\r\n" + 
			"    INV.FIN_YR=:quterTimePeriod   \r\n" + 
			"GROUP BY\r\n" + 
			"    INV.FIN_YR,\r\n" + 
			"    KM.VENDOR,\r\n" + 
			"    BM.CRCL_NAME,\r\n" + 
			"    BM.STAT_DESC",nativeQuery = true)
	Page<InvoiceSummaryEntity> findbyFilter(@Param("selectedCircelId")
    String selectedCircelId, @Param("selectedStateId") String selectedStateId,
    @Param("quterTimePeriod") String quterTimePeriod,Pageable pageable);
	
	
	
	
	@Query(value = 
			"SELECT DISTINCT \r\n" + 
			"    INV.FIN_YR,\r\n" + 
			"    KM.VENDOR,\r\n" + 
			"    BM.CRCL_NAME,\r\n" + 
			"    BM.STAT_DESC,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q1',INVOICE_AMT,0) ) AS Q1_INVOICE_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q2',INVOICE_AMT,0) ) AS Q2_INVOICE_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q3',INVOICE_AMT,0) ) AS Q3_INVOICE_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q4',INVOICE_AMT,0) ) AS Q4_INVOICE_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q1',PENALTY_AMT,0) ) AS Q1_PENALTY_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q2',PENALTY_AMT,0) ) AS Q2_PENALTY_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q3',PENALTY_AMT,0) ) AS Q3_PENALTY_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q4',PENALTY_AMT,0) ) AS Q4_PENALTY_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q1',FINAL_AMT,0) ) AS Q1_FINAL_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q2',FINAL_AMT,0) ) AS Q2_FINAL_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q3',FINAL_AMT,0) ) AS Q3_FINAL_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q4',FINAL_AMT,0) ) AS Q4_FINAL_AMT\r\n" + 
			"FROM\r\n" + 
			"    TBL_INVOICE INV\r\n" + 
			"    INNER JOIN TBL_KIOSK_MASTER KM ON INV.KIOSK_ID = KM.KIOSK_ID\r\n" + 
			"    INNER JOIN TBL_BRANCH_MASTER BM ON KM.BRANCH_CODE = BM.BRANCH_CODE    \r\n" + 
			"Where \r\n" + 
			"    bm.crcl_code=:selectedCircelId AND\r\n" +  
			"    INV.FIN_YR=:quterTimePeriod   \r\n" + 
			"GROUP BY\r\n" + 
			"    INV.FIN_YR,\r\n" + 
			"    KM.VENDOR,\r\n" + 
			"    BM.CRCL_NAME,\r\n" + 
			"    BM.STAT_DESC",nativeQuery = true,
			countQuery="SELECT count(KM.BRANCH_CODE)/4 " +
					"FROM\r\n" + 
					"    TBL_INVOICE INV\r\n" + 
					"    INNER JOIN TBL_KIOSK_MASTER KM ON INV.KIOSK_ID = KM.KIOSK_ID\r\n" + 
					"    INNER JOIN TBL_BRANCH_MASTER BM ON KM.BRANCH_CODE = BM.BRANCH_CODE    \r\n" + 
					"Where \r\n" + 
					"    bm.crcl_code=:selectedCircelId AND\r\n" + 
					"    INV.FIN_YR=:quterTimePeriod ")
	Page<InvoiceSummaryEntity> findbyWithoutStateFilter(@Param("selectedCircelId")
    String selectedCircelId,
    @Param("quterTimePeriod") String quterTimePeriod,Pageable pageable);
	
	
	
	@Query(value = 
			"SELECT DISTINCT \r\n" + 
			"    INV.FIN_YR,\r\n" + 
			"    KM.VENDOR,\r\n" + 
			"    BM.CRCL_NAME,\r\n" + 
			"    BM.STAT_DESC,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q1',INVOICE_AMT,0) ) AS Q1_INVOICE_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q2',INVOICE_AMT,0) ) AS Q2_INVOICE_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q3',INVOICE_AMT,0) ) AS Q3_INVOICE_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q4',INVOICE_AMT,0) ) AS Q4_INVOICE_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q1',PENALTY_AMT,0) ) AS Q1_PENALTY_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q2',PENALTY_AMT,0) ) AS Q2_PENALTY_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q3',PENALTY_AMT,0) ) AS Q3_PENALTY_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q4',PENALTY_AMT,0) ) AS Q4_PENALTY_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q1',FINAL_AMT,0) ) AS Q1_FINAL_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q2',FINAL_AMT,0) ) AS Q2_FINAL_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q3',FINAL_AMT,0) ) AS Q3_FINAL_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q4',FINAL_AMT,0) ) AS Q4_FINAL_AMT\r\n" + 
			"FROM\r\n" + 
			"    TBL_INVOICE INV\r\n" + 
			"    INNER JOIN TBL_KIOSK_MASTER KM ON INV.KIOSK_ID = KM.KIOSK_ID\r\n" + 
			"    INNER JOIN TBL_BRANCH_MASTER BM ON KM.BRANCH_CODE = BM.BRANCH_CODE    \r\n" + 
			"Where \r\n" + 
			"    bm.crcl_code=:selectedCircelId AND\r\n" + 
			"    bm.stat_code=:selectedStateId AND\r\n" + 
			"    INV.FIN_YR=:quterTimePeriod   \r\n" + 
			"GROUP BY\r\n" + 
			"    INV.FIN_YR,\r\n" + 
			"    KM.VENDOR,\r\n" + 
			"    BM.CRCL_NAME,\r\n" + 
			"    BM.STAT_DESC",nativeQuery = true)
	List<InvoiceSummaryEntity> findbyFilterReport(@Param("selectedCircelId")
    String selectedCircelId, @Param("selectedStateId") String selectedStateId,
    @Param("quterTimePeriod") String quterTimePeriod);
	
	
	
	
	@Query(value = 
			"SELECT DISTINCT" + 
			"    INV.FIN_YR,\r\n" + 
			"    KM.VENDOR,\r\n" + 
			"    BM.CRCL_NAME,\r\n" + 
			"    BM.STAT_DESC,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q1',INVOICE_AMT,0) ) AS Q1_INVOICE_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q2',INVOICE_AMT,0) ) AS Q2_INVOICE_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q3',INVOICE_AMT,0) ) AS Q3_INVOICE_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q4',INVOICE_AMT,0) ) AS Q4_INVOICE_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q1',PENALTY_AMT,0) ) AS Q1_PENALTY_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q2',PENALTY_AMT,0) ) AS Q2_PENALTY_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q3',PENALTY_AMT,0) ) AS Q3_PENALTY_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q4',PENALTY_AMT,0) ) AS Q4_PENALTY_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q1',FINAL_AMT,0) ) AS Q1_FINAL_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q2',FINAL_AMT,0) ) AS Q2_FINAL_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q3',FINAL_AMT,0) ) AS Q3_FINAL_AMT,\r\n" + 
			"    SUM(DECODE(QTR_ID,'Q4',FINAL_AMT,0) ) AS Q4_FINAL_AMT\r\n" + 
			"FROM\r\n" + 
			"    TBL_INVOICE INV\r\n" + 
			"    JOIN TBL_KIOSK_MASTER KM ON INV.KIOSK_ID = KM.KIOSK_ID\r\n" + 
			"    JOIN TBL_BRANCH_MASTER BM ON KM.BRANCH_CODE = BM.BRANCH_CODE    \r\n" + 
			"Where \r\n" + 
			"    bm.crcl_code=:selectedCircelId AND \r\n" +  
			"    INV.FIN_YR=:quterTimePeriod   \r\n" + 
			"GROUP BY\r\n" + 
			"    INV.FIN_YR,\r\n" + 
			"    KM.VENDOR,\r\n" + 
			"    BM.CRCL_NAME,\r\n" + 
			"    BM.STAT_DESC",nativeQuery = true)
	List<InvoiceSummaryEntity> findbyWithoutStateFilterReport(@Param("selectedCircelId")
    String selectedCircelId,
    @Param("quterTimePeriod") String quterTimePeriod);
	
	
	

}
