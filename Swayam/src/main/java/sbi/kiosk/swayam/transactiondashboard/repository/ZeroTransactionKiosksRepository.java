package sbi.kiosk.swayam.transactiondashboard.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.ZeroTransactionKiosks;

@Repository
public interface ZeroTransactionKiosksRepository extends PagingAndSortingRepository <ZeroTransactionKiosks, String> {
	
	@Query(value="SELECT * FROM VW_ZERO_TRANSACTION_KIOSKS",nativeQuery=true)
	
	Page<ZeroTransactionKiosks> findAll(Pageable pageable);
	
//	@Query(value="SELECT BM.CRCL_NAME CRCL_NAME, concat('NET-0',substr(BM.NETWORK,1,1)) as NETWORK, "+
//    "BM.MODULE MODULE, BM.REGION REGION, BM.BRANCH_CODE BRANCH_CODE, BM.BRANCH_NAME BRANCH_NAME, KM.KIOSK_ID KIOSK_ID, KM.VENDOR VENDOR "+
//    "FROM TBL_BRANCH_MASTER BM "+
//    "JOIN TBL_KIOSK_MASTER KM ON BM.BRANCH_CODE = KM.BRANCH_CODE "+
//    "WHERE KM.KIOSK_ID NOT IN ("+
//            "SELECT STR.KIOSK_ID FROM TBL_SWAYAM_TXN_REPORT STR "+
//            "WHERE to_date(STR.TXN_DATE,'dd-mm-yyyy')>= to_date(:fromdate, 'dd-mm-yyyy') "+ 
//               "and to_date(STR.TXN_DATE,'dd-mm-yyyy')<= to_date(:todate, 'dd-mm-yyyy')"+
//        ")",nativeQuery=true,
//        countQuery="SELECT count(KM.BRANCH_CODE) FROM TBL_BRANCH_MASTER BM"
//        		+ "  JOIN TBL_KIOSK_MASTER KM ON BM.BRANCH_CODE = KM.BRANCH_CODE  WHERE KM.KIOSK_ID NOT IN ( SELECT STR.KIOSK_ID FROM "
//        		+ "TBL_SWAYAM_TXN_REPORT STR  WHERE to_date(STR.TXN_DATE,'dd-mm-yyyy')>= to_date(:fromdate, 'dd-mm-yyyy')"
//        		+ "   and to_date(STR.TXN_DATE,'dd-mm-yyyy')<= to_date(:todate, 'dd-mm-yyyy'))")
	
	@Query(value="SELECT   bm.crcl_name crcl_name, concat('NET-0',substr(bm.network,1,1)) AS network, bm.module module, "
			+ "  bm.region region,   bm.branch_code branch_code,  bm.branch_name branch_name,  km.kiosk_id kiosk_id, "
			+ " km.vendor vendor FROM  tbl_branch_master bm   JOIN tbl_kiosk_master km ON bm.branch_code = km.branch_code "
			+ " WHERE   upper(km.kiosk_id) NOT IN ( SELECT  upper(str.kiosk_id)  FROM  tbl_swayam_txn_report str"
			+ " WHERE TO_DATE(str.txn_date,'dd-mm-yyyy') >= TO_DATE(:fromdate,'dd-mm-yyyy')  AND  "
			+ " TO_DATE(str.txn_date,'dd-mm-yyyy') <= TO_DATE(:todate,'dd-mm-yyyy') )"
			,nativeQuery=true,countQuery="SELECT count(KM.BRANCH_CODE)  FROM  tbl_branch_master bm   JOIN tbl_kiosk_master km ON bm.branch_code = km.branch_code "
					+ " WHERE   upper(km.kiosk_id) NOT IN ( SELECT  upper(str.kiosk_id)  FROM  tbl_swayam_txn_report str"
					+ " WHERE TO_DATE(str.txn_date,'dd-mm-yyyy') >= TO_DATE(:fromdate,'dd-mm-yyyy')  AND  "
					+ " TO_DATE(str.txn_date,'dd-mm-yyyy') <= TO_DATE(:todate,'dd-mm-yyyy') ) ")
        
        			Page<ZeroTransactionKiosks> findByDate(@Param("fromdate") String fromdate,@Param("todate") String todate,Pageable pageable);
	
	@Query(value="SELECT   bm.crcl_name crcl_name, concat('NET-0',substr(bm.network,1,1)) AS network, bm.module module, "
			+ "  bm.region region,   bm.branch_code branch_code,  bm.branch_name branch_name,  km.kiosk_id kiosk_id, "
			+ " km.vendor vendor FROM  tbl_branch_master bm   JOIN tbl_kiosk_master km ON bm.branch_code = km.branch_code "
			+ " WHERE   upper(km.kiosk_id) NOT IN ( SELECT  upper(str.kiosk_id)  FROM  tbl_swayam_txn_report str"
			+ " WHERE TO_DATE(str.txn_date,'dd-mm-yyyy') >= TO_DATE(:fromdate,'dd-mm-yyyy')  AND  "
			+ " TO_DATE(str.txn_date,'dd-mm-yyyy') <= TO_DATE(:todate,'dd-mm-yyyy') ) "
			+ " AND (bm.CRCL_NAME=:searchText OR bm.NETWORK=:searchText OR bm.MODULE=:searchText OR bm.REGION=:searchText OR bm.BRANCH_CODE=:searchText OR bm.BRANCH_NAME=:searchText or concat('NET-0',substr(bm.network,1,1))= :searchText "  
								+ " or km.kiosk_id=:searchText ) "
			,nativeQuery=true,countQuery="SELECT count(KM.BRANCH_CODE)  FROM  tbl_branch_master bm   JOIN tbl_kiosk_master km ON bm.branch_code = km.branch_code "
					+ " WHERE   upper(km.kiosk_id) NOT IN ( SELECT  upper(str.kiosk_id)  FROM  tbl_swayam_txn_report str"
					+ " WHERE TO_DATE(str.txn_date,'dd-mm-yyyy') >= TO_DATE(:fromdate,'dd-mm-yyyy')  AND  "
					+ " TO_DATE(str.txn_date,'dd-mm-yyyy') <= TO_DATE(:todate,'dd-mm-yyyy') ) "
					+ "AND (bm.CRCL_NAME=:searchText OR bm.NETWORK=:searchText OR bm.MODULE=:searchText OR bm.REGION=:searchText OR bm.BRANCH_CODE=:searchText  OR bm.BRANCH_NAME=:searchText or concat('NET-0',substr(bm.network,1,1))= :searchText "  
					+ "or km.kiosk_id=:searchText ) ")
        
        			Page<ZeroTransactionKiosks> findByDateSearchNext(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("searchText") String searchText,Pageable pageable);
	

//	@Query(value="SELECT BM.CRCL_NAME CRCL_NAME, concat('NET-0',substr(BM.NETWORK,1,1)) as NETWORK, "+
//		    "BM.MODULE MODULE, BM.REGION REGION, BM.BRANCH_CODE BRANCH_CODE, BM.BRANCH_NAME BRANCH_NAME, KM.KIOSK_ID KIOSK_ID, KM.VENDOR VENDOR "+
//		    "FROM TBL_BRANCH_MASTER BM "+
//		    "JOIN TBL_KIOSK_MASTER KM ON BM.BRANCH_CODE = KM.BRANCH_CODE "+
//		    "WHERE KM.KIOSK_ID NOT IN ("+
//		            "SELECT STR.KIOSK_ID FROM TBL_SWAYAM_TXN_REPORT STR "+
//		            "WHERE to_date(STR.TXN_DATE,'dd-mm-yyyy')>= to_date(:fromdate, 'dd-mm-yyyy') "+ 
//		               "and to_date(STR.TXN_DATE,'dd-mm-yyyy')<= to_date(:todate, 'dd-mm-yyyy')"+
//		        ")",nativeQuery=true,
//		        countQuery="SELECT count(KM.BRANCH_CODE) FROM TBL_BRANCH_MASTER BM"
//		        		+ "  JOIN TBL_KIOSK_MASTER KM ON BM.BRANCH_CODE = KM.BRANCH_CODE  WHERE KM.KIOSK_ID NOT IN ( SELECT STR.KIOSK_ID FROM "
//		        		+ "TBL_SWAYAM_TXN_REPORT STR  WHERE to_date(STR.TXN_DATE,'dd-mm-yyyy')>= to_date(:fromdate, 'dd-mm-yyyy')"
//		        		+ "   and to_date(STR.TXN_DATE,'dd-mm-yyyy')<= to_date(:todate, 'dd-mm-yyyy'))")
	
	@Query(value="SELECT   bm.crcl_name crcl_name, concat('NET-0',substr(bm.network,1,1)) AS network, bm.module module, "
			+ "  bm.region region,   bm.branch_code branch_code,  bm.branch_name branch_name,  km.kiosk_id kiosk_id, "
			+ " km.vendor vendor FROM  tbl_branch_master bm   JOIN tbl_kiosk_master km ON bm.branch_code = km.branch_code "
			+ " WHERE   upper(km.kiosk_id) NOT IN ( SELECT  upper(str.kiosk_id)  FROM  tbl_swayam_txn_report str"
			+ " WHERE TO_DATE(str.txn_date,'dd-mm-yyyy') >= TO_DATE(:fromdate,'dd-mm-yyyy')  AND  "
			+ " TO_DATE(str.txn_date,'dd-mm-yyyy') <= TO_DATE(:todate,'dd-mm-yyyy') )"
			,nativeQuery=true,countQuery="SELECT count(KM.BRANCH_CODE)  FROM  tbl_branch_master bm   JOIN tbl_kiosk_master km ON bm.branch_code = km.branch_code "
					+ " WHERE   upper(km.kiosk_id) NOT IN ( SELECT  upper(str.kiosk_id)  FROM  tbl_swayam_txn_report str"
					+ " WHERE TO_DATE(str.txn_date,'dd-mm-yyyy') >= TO_DATE(:fromdate,'dd-mm-yyyy')  AND  "
					+ " TO_DATE(str.txn_date,'dd-mm-yyyy') <= TO_DATE(:todate,'dd-mm-yyyy') ) ")
	
		
		        			List<ZeroTransactionKiosks> findByDateZeroTxn(@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	// 12c
	//@Query(value="select to_char(end_dttm,'dd-Mon-yy hh24:mm:ss') from tbl_audit_job where job_name='TBL_SWAYAM_TXN_DAILY' and status='C' order by end_dttm desc fetch first 1 row only ",nativeQuery = true )
	
	// new query
			@Query(value="select to_char(end_dttm,'dd-mm-yyyy hh24:mm:ss') from tbl_audit_job where job_name='TBL_SWAYAM_TXN_DAILY' and status='C' order by end_dttm desc fetch first 1 row only",nativeQuery = true)
	//for 11g
		//@Query(value="select to_char(end_dttm,'dd-Mon-yy hh24:mm:ss') from  tbl_audit_job where job_name='TBL_SWAYAM_TXN_DAILY' and status='C' and rownum <= 1 order by end_dttm desc ",nativeQuery = true )
		String findCurrentDateAuditJob();
	
}
