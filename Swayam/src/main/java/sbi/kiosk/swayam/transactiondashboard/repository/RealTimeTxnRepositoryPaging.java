package sbi.kiosk.swayam.transactiondashboard.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.RealTimeTransaction;

@Repository
public interface RealTimeTxnRepositoryPaging extends PagingAndSortingRepository<RealTimeTransaction, String>{
	
	//Page<RealTimeTransaction> findByFromDate(Pageable pageable,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value="SELECT  BM.CRCL_NAME CRCL_NAME, BM.NETWORK NETWORK, BM.MODULE MODULE, BM.REGION REGION, BM.BRANCH_CODE BRANCH_CODE, BM.BRANCH_NAME BRANCH_NAME,"+ 
            "STR.KIOSK_ID KIOSK_ID, STR.NO_OF_TXNS NO_OF_TXNS, STR.VENDOR VENDOR "+  
            "FROM TBL_BRANCH_MASTER BM "+ 
    "JOIN TBL_SWAYAM_TXN_REPORT STR ON BM.BRANCH_CODE = STR.BRANCH_CODE "+  
    "AND to_date(STR.TXN_DATE,'dd-mm-yyyy')=to_date(:fromdate,'dd-mm-yyyy')  ORDER BY STR.TXN_DATE  DESC",nativeQuery=true,countQuery = "SELECT count(STR.BRANCH_CODE)  FROM TBL_BRANCH_MASTER BM JOIN TBL_SWAYAM_TXN_REPORT STR "
    		+ " ON BM.BRANCH_CODE = STR.BRANCH_CODE AND to_date(STR.TXN_DATE,'dd-mm-yyyy')=to_date(:fromdate,'dd-mm-yyyy')  ORDER BY STR.TXN_DATE  DESC" )
	Page<RealTimeTransaction> findByFromDate(@Param("fromdate") String fromdate, Pageable pageable);
	
	@Query(value="SELECT  BM.CRCL_NAME CRCL_NAME, BM.NETWORK NETWORK, BM.MODULE MODULE, BM.REGION REGION, BM.BRANCH_CODE BRANCH_CODE, BM.BRANCH_NAME BRANCH_NAME,"+ 
            "STR.KIOSK_ID KIOSK_ID, STR.NO_OF_TXNS NO_OF_TXNS, STR.VENDOR VENDOR "+  
            "FROM TBL_BRANCH_MASTER BM "+ 
    "JOIN TBL_SWAYAM_TXN_REPORT STR ON BM.BRANCH_CODE = STR.BRANCH_CODE "+  
    "AND to_date(STR.TXN_DATE,'dd-mm-yyyy')=to_date(:fromdate,'dd-mm-yyyy')  ORDER BY STR.TXN_DATE  DESC",nativeQuery=true,countQuery = "SELECT count(STR.BRANCH_CODE)  FROM TBL_BRANCH_MASTER BM JOIN TBL_SWAYAM_TXN_REPORT STR "
    		+ " ON BM.BRANCH_CODE = STR.BRANCH_CODE AND to_date(STR.TXN_DATE,'dd-mm-yyyy')=to_date(:fromdate,'dd-mm-yyyy')  ORDER BY STR.TXN_DATE  DESC" )
	List<RealTimeTransaction> findByDate(@Param("fromdate") String fromdate);
	
	// 12c
	@Query(value="select to_char(end_dttm,'dd-Mon-yy hh24:mm:ss') from  tbl_audit_job where job_name='TBL_SWAYAM_TXN_DAILY'  order by end_dttm desc fetch first 1 row only ",nativeQuery = true )
	//for 11g
	//@Query(value="select to_char(end_dttm,'dd-Mon-yy hh24:mm:ss') from  tbl_audit_job where job_name='TBL_SWAYAM_TXN_DAILY' and rownum <= 1 order by end_dttm desc ",nativeQuery = true )
	String findCurrentDateAuditJob();
}
