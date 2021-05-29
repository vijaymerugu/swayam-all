package sbi.kiosk.swayam.transactiondashboard.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.ErrorReporting;
import sbi.kiosk.swayam.common.entity.ErrorReportingDrillDown;

@Repository
public interface ErrorReportingRepositoryPaging extends PagingAndSortingRepository<ErrorReportingDrillDown, String>{


/*	
	@Query(value=" SELECT  BM.CRCL_NAME, BM.NETWORK, BM.MODULE, BM.REGION, BM.BRANCH_CODE, BM.BRANCH_NAME, "
			+ " STR.KIOSK_ID, STR.VENDOR, (NVL(STR.NO_OF_TECH_FAIL,0) + NVL(ERRs.ERR_COUNT,0)) AS NO_OF_ERRORS"
			+ "   FROM TBL_BRANCH_MASTER BM  JOIN TBL_SWAYAM_TXN_REPORT STR ON BM.BRANCH_CODE = STR.BRANCH_CODE "
			+ " LEFT OUTER JOIN TBL_ERROR_STATS ERRs ON ERRs.KIOSK_ID = STR.KIOSK_ID  WHERE STR.TXN_DATE "

			+ " between trunc(to_date(:fromdate, 'dd-mm-yyyy'))  and trunc(to_date(:todate, 'dd-mm-yyyy'))",nativeQuery=true,
			countQuery= "SELECT  BM.CRCL_NAME, BM.NETWORK, BM.MODULE, BM.REGION, BM.BRANCH_CODE, BM.BRANCH_NAME, " 
					+"	STR.KIOSK_ID, STR.VENDOR, (NVL(STR.NO_OF_TECH_FAIL,0) + NVL(ERRs.ERR_COUNT,0)) AS NO_OF_ERRORS " 
					+"	FROM TBL_BRANCH_MASTER BM  JOIN TBL_SWAYAM_TXN_REPORT STR ON BM.BRANCH_CODE = STR.BRANCH_CODE " 
					+"	LEFT OUTER JOIN TBL_ERROR_STATS ERRs ON ERRs.KIOSK_ID = STR.KIOSK_ID  WHERE STR.TXN_DATE " 
					+"	between trunc(to_date(:fromdate, 'dd-mm-yyyy'))  and trunc(to_date(:todate, 'dd-mm-yyyy'))")

	*/
	
	@Query(value ="select bm.crcl_code AS CODE ,bm.crcl_name AS NAME,"
			+ " SUM(NO_OF_TXNS) AS No_of_Txns,"
			+ "	sum(txn.no_of_success_txns) as No_of_success_Txns,sum(txn.no_of_fail) as No_of_failure_Txns, "
			+ " sum(txn.no_of_tech_fail) as No_of_technical_failure_Txns, sum(txn.no_of_business_fail) as No_of_business_failure_Txns from "
			+ "	 tbl_swayam_txn_report txn inner join tbl_branch_master bm on txn.branch_code=bm.branch_code "
			+ " where txn_date BETWEEN TO_DATE(:fromdate, 'dd-mm-yy') AND TO_DATE(:todate, 'dd-mm-yy') "
			+ " group by bm.crcl_code,bm.crcl_name ORDER BY   bm.crcl_name ASC",
			nativeQuery = true,countQuery = "select count(bm.crcl_code) as CODE "
					+ "	from  tbl_swayam_txn_report txn inner join tbl_branch_master bm on txn.branch_code=bm.branch_code "
					+ " where txn_date BETWEEN TO_DATE(:fromdate, 'dd-mm-yy') AND TO_DATE(:todate, 'dd-mm-yy') "
					+ " group by bm.crcl_code,bm.crcl_name ORDER BY   bm.crcl_name ASC ")
	Page<ErrorReporting> findByDate(@Param("fromdate") String fromdate,@Param("todate") String todate,Pageable pageable);
	
/*
	@Query(value=" SELECT  BM.CRCL_NAME, BM.NETWORK, BM.MODULE, BM.REGION, BM.BRANCH_CODE, BM.BRANCH_NAME, "
			+ " STR.KIOSK_ID, STR.VENDOR, (NVL(STR.NO_OF_TECH_FAIL,0) + NVL(ERRs.ERR_COUNT,0)) AS NO_OF_ERRORS"
			+ "   FROM TBL_BRANCH_MASTER BM  JOIN TBL_SWAYAM_TXN_REPORT STR ON BM.BRANCH_CODE = STR.BRANCH_CODE "
			+ " LEFT OUTER JOIN TBL_ERROR_STATS ERRs ON ERRs.KIOSK_ID = STR.KIOSK_ID  WHERE STR.TXN_DATE "
			+ " between trunc(to_date(:fromdate, 'dd-mm-yyyy'))  and trunc(to_date(:todate, 'dd-mm-yyyy'))"
			+ " and (BM.CRCL_NAME=UPPER(:searchText) or BM.BRANCH_CODE=UPPER(:searchText) or STR.KIOSK_ID=UPPER(:searchText) or BM.BRANCH_NAME=UPPER(:searchText) )",nativeQuery=true,
			countQuery= "SELECT  BM.CRCL_NAME, BM.NETWORK, BM.MODULE, BM.REGION, BM.BRANCH_CODE, BM.BRANCH_NAME, " 
					+"	 STR.KIOSK_ID, STR.VENDOR, (NVL(STR.NO_OF_TECH_FAIL,0) + NVL(ERRs.ERR_COUNT,0)) AS NO_OF_ERRORS "
					+"	 FROM TBL_BRANCH_MASTER BM  JOIN TBL_SWAYAM_TXN_REPORT STR ON BM.BRANCH_CODE = STR.BRANCH_CODE " 
					+"	 LEFT OUTER JOIN TBL_ERROR_STATS ERRs ON ERRs.KIOSK_ID = STR.KIOSK_ID  WHERE STR.TXN_DATE "
					+"	 between trunc(to_date(:fromdate, 'dd-mm-yyyy'))  and trunc(to_date(:todate, 'dd-mm-yyyy')) "
					+"	 and (BM.CRCL_NAME=UPPER(:searchText) or BM.BRANCH_CODE=UPPER(:searchText) or STR.KIOSK_ID=UPPER(:searchText) or BM.BRANCH_NAME=UPPER(:searchText) )")
*/
	
	@Query(value ="select bm.crcl_code AS CODE ,bm.crcl_name AS NAME,"
			+ " SUM(NO_OF_TXNS) AS No_of_Txns,"
			+ "	sum(txn.no_of_success_txns) as No_of_success_Txns,sum(txn.no_of_fail) as No_of_failure_Txns, "
			+ " sum(txn.no_of_tech_fail) as No_of_technical_failure_Txns, sum(txn.no_of_business_fail) as No_of_business_failure_Txns from "
			+ "	 tbl_swayam_txn_report txn inner join tbl_branch_master bm on txn.branch_code=bm.branch_code "
			+ " where txn_date BETWEEN TO_DATE(:fromdate, 'dd-mm-yy') AND TO_DATE(:todate, 'dd-mm-yy') "
		    + " and (BM.CRCL_NAME=UPPER(:searchText) "
            + " or BM.BRANCH_CODE=UPPER(:searchText) "
            + " or BM.BRANCH_NAME=UPPER(:searchText) ) "
			+ " group by bm.crcl_code,bm.crcl_name ORDER BY   bm.crcl_name ASC",
			nativeQuery = true,countQuery = "select count(bm.crcl_code) as CODE "
			+ "	from  tbl_swayam_txn_report txn inner join tbl_branch_master bm on txn.branch_code=bm.branch_code "
			+ " where txn_date BETWEEN TO_DATE(:fromdate, 'dd-mm-yy') AND TO_DATE(:todate, 'dd-mm-yy') "
		    + " and (BM.CRCL_NAME=UPPER(:searchText) "
			+ " or BM.BRANCH_CODE=UPPER(:searchText) "
			+ " or BM.BRANCH_NAME=UPPER(:searchText) ) "
		    + " group by bm.crcl_code,bm.crcl_name ORDER BY   bm.crcl_name ASC ")
	
	
	Page<ErrorReportingDrillDown> findByDateSearchNext(@Param("fromdate") String fromdate,@Param("todate") String todate, @Param("searchText") String searchText,Pageable pageable);

/*
	
	@Query(value=" SELECT  BM.CRCL_NAME, BM.NETWORK, BM.MODULE, BM.REGION, BM.BRANCH_CODE, BM.BRANCH_NAME, "
			+ " STR.KIOSK_ID, STR.VENDOR, (NVL(STR.NO_OF_TECH_FAIL,0) + NVL(ERRs.ERR_COUNT,0)) AS NO_OF_ERRORS"
			+ "   FROM TBL_BRANCH_MASTER BM  JOIN TBL_SWAYAM_TXN_REPORT STR ON BM.BRANCH_CODE = STR.BRANCH_CODE "
			+ " LEFT OUTER JOIN TBL_ERROR_STATS ERRs ON ERRs.KIOSK_ID = STR.KIOSK_ID  WHERE STR.TXN_DATE "
			+ " between trunc(to_date(:fromdate, 'dd-mm-yyyy'))  and trunc(to_date(:todate, 'dd-mm-yyyy'))",nativeQuery=true,
			countQuery= "SELECT count(STR.KIOSK_ID)  FROM TBL_BRANCH_MASTER BM  JOIN TBL_SWAYAM_TXN_REPORT STR ON BM.BRANCH_CODE = STR.BRANCH_CODE "
					+ " LEFT OUTER JOIN TBL_ERROR_STATS ERRs ON ERRs.KIOSK_ID = STR.KIOSK_ID  WHERE STR.TXN_DATE "
					+ " between trunc(to_date(:fromdate, 'dd-mm-yyyy'))  and trunc(to_date(:todate, 'dd-mm-yyyy'))")

	*/
	
	@Query(value ="select bm.crcl_code AS CODE ,bm.crcl_name AS NAME,"
			+ " SUM(NO_OF_TXNS) AS No_of_Txns,"
			+ "	sum(txn.no_of_success_txns) as No_of_success_Txns,sum(txn.no_of_fail) as No_of_failure_Txns, "
			+ " sum(txn.no_of_tech_fail) as No_of_technical_failure_Txns, sum(txn.no_of_business_fail) as No_of_business_failure_Txns from "
			+ "	 tbl_swayam_txn_report txn inner join tbl_branch_master bm on txn.branch_code=bm.branch_code "
			+ " where txn_date BETWEEN TO_DATE(:fromdate, 'dd-mm-yy') AND TO_DATE(:todate, 'dd-mm-yy') "
			+ " group by bm.crcl_code,bm.crcl_name ORDER BY   bm.crcl_name ASC",
			nativeQuery = true,countQuery = "select count(bm.crcl_code) as CODE "
					+ "	from  tbl_swayam_txn_report txn inner join tbl_branch_master bm on txn.branch_code=bm.branch_code "
					+ " where txn_date BETWEEN TO_DATE(:fromdate, 'dd-mm-yy') AND TO_DATE(:todate, 'dd-mm-yy') "
					+ " group by bm.crcl_code,bm.crcl_name ORDER BY   bm.crcl_name ASC ")
	
	List<ErrorReportingDrillDown> findAllErrReport(@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	// 12c
	//@Query(value="select to_date(last_pbk_dt,'yyyy-mm-dd') from tbl_branch_txn_daily order by last_pbk_dt desc fetch first 1 row only ",nativeQuery = true )
	@Query(value="select to_char(end_dttm,'dd-Mon-yy hh24:mm:ss') from tbl_audit_job where job_name='TBL_SWAYAM_TXN_DAILY' and status='C' order by end_dttm desc fetch first 1 row only ",nativeQuery = true )		
	//for 11g
	//		@Query(value="select to_char(end_dttm,'dd-Mon-yy hh24:mm:ss') from  tbl_audit_job where job_name='TBL_SWAYAM_TXN_DAILY' and rownum <= 1 order by end_dttm desc ",nativeQuery = true )
			String findCurrentDateAuditJob();
}
