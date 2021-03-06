package sbi.kiosk.swayam.transactiondashboard.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.RealTimeTransaction;
import sbi.kiosk.swayam.common.entity.SwayamTransactionReport;

@Repository
public interface RealTimeTxnRepositoryPaging extends PagingAndSortingRepository<RealTimeTransaction, String>{
	
	//Page<RealTimeTransaction> findByFromDate(Pageable pageable,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	/* Commented by Manisha on 20-Jan-2021
	 * @Query(
	 * value="SELECT  BM.CRCL_NAME CRCL_NAME, BM.NETWORK NETWORK, BM.MODULE MODULE, BM.REGION REGION, BM.BRANCH_CODE BRANCH_CODE, BM.BRANCH_NAME BRANCH_NAME,"
	 * + "STR.KIOSK_ID KIOSK_ID, STR.NO_OF_TXNS NO_OF_TXNS, STR.VENDOR VENDOR "+
	 * "FROM TBL_BRANCH_MASTER BM "+
	 * "JOIN TBL_SWAYAM_TXN_REPORT STR ON BM.BRANCH_CODE = STR.BRANCH_CODE "+
	 * "AND to_date(STR.TXN_DATE,'dd-mm-yyyy')=to_date(:fromdate,'dd-mm-yyyy')  ORDER BY STR.TXN_DATE  DESC"
	 * ,nativeQuery=true,countQuery =
	 * "SELECT count(STR.BRANCH_CODE)  FROM TBL_BRANCH_MASTER BM JOIN TBL_SWAYAM_TXN_REPORT STR "
	 * +
	 * " ON BM.BRANCH_CODE = STR.BRANCH_CODE AND to_date(STR.TXN_DATE,'dd-mm-yyyy')=to_date(:fromdate,'dd-mm-yyyy')  ORDER BY STR.TXN_DATE  DESC"
	 * )
	 */
	
	/*
	 * @Query(
	 * value="SELECT  BM.CRCL_NAME CRCL_NAME, BM.NETWORK NETWORK, BM.MODULE MODULE, BM.REGION REGION, BM.BRANCH_CODE BRANCH_CODE, BM.BRANCH_NAME BRANCH_NAME,"
	 * + "STR.KIOSK_ID KIOSK_ID, STR.NO_OF_TXNS NO_OF_TXNS, STR.VENDOR VENDOR "+
	 * "FROM TBL_BRANCH_MASTER BM "+
	 * "JOIN TBL_SWAYAM_TXN_REPORT STR ON BM.BRANCH_CODE = STR.BRANCH_CODE "+
	 * "AND STR.TXN_DATE=to_date(:fromdate,'dd-mm-yyyy')  ORDER BY STR.TXN_DATE  DESC"
	 * ,nativeQuery=true,countQuery =
	 * "SELECT count(STR.BRANCH_CODE)  FROM TBL_BRANCH_MASTER BM JOIN TBL_SWAYAM_TXN_REPORT STR "
	 * +
	 * " ON BM.BRANCH_CODE = STR.BRANCH_CODE AND STR.TXN_DATE=to_date(:fromdate,'dd-mm-yyyy')  ORDER BY STR.TXN_DATE  DESC"
	 * )
	 */
	
	
	  @Query(
	  value="			  SELECT  BM.CRCL_NAME CRCL_NAME,BM.NETWORK NETWORK,BM.MODULE MODULE,BM.REGION REGION,	BM.BRANCH_CODE BRANCH_CODE,"
	  +
	  "				BM.BRANCH_NAME BRANCH_NAME,	km.KIOSK_ID KIOSK_ID,STR.NO_OF_TXNS NO_OF_TXNS,	km.VENDOR VENDOR "
	  +
	  "				FROM  tbl_kiosk_master km	INNER JOIN TBL_BRANCH_MASTER BM ON KM.BRANCH_CODE = BM.BRANCH_CODE "
	  +
	  "				INNER JOIN TBL_SWAYAM_TXN_REPORT STR ON KM.BRANCH_CODE = STR.BRANCH_CODE AND UPPER(STR.KIOSK_ID)=UPPER(KM.KIOSK_ID) "
	  +
	  "				AND STR.TXN_DATE=to_date(:fromdate,'dd-mm-yyyy')  ORDER BY STR.TXN_DATE  DESC "
	  ,nativeQuery=true,countQuery =
	  "	SELECT  BM.CRCL_NAME CRCL_NAME,BM.NETWORK NETWORK,BM.MODULE MODULE,BM.REGION REGION,BM.BRANCH_CODE BRANCH_CODE,"
	  +
	  "				BM.BRANCH_NAME BRANCH_NAME,	km.KIOSK_ID KIOSK_ID,STR.NO_OF_TXNS NO_OF_TXNS,	km.VENDOR VENDOR "
	  +
	  "				FROM  tbl_kiosk_master km	INNER JOIN TBL_BRANCH_MASTER BM ON KM.BRANCH_CODE = BM.BRANCH_CODE "
	  +
	  "				INNER JOIN TBL_SWAYAM_TXN_REPORT STR ON KM.BRANCH_CODE = STR.BRANCH_CODE AND UPPER(STR.KIOSK_ID)=UPPER(KM.KIOSK_ID) "
	  +
	  "				AND STR.TXN_DATE=to_date(:fromdate,'dd-mm-yyyy')  ORDER BY STR.TXN_DATE  DESC "
	  )
	 
	Page<RealTimeTransaction> findByFromDate(@Param("fromdate") String fromdate, Pageable pageable);
	
	
	
	/* Commented by Manisha on 20-Jan-2021
	 * @Query(
	 * value="SELECT  BM.CRCL_NAME CRCL_NAME, BM.NETWORK NETWORK, BM.MODULE MODULE, BM.REGION REGION, BM.BRANCH_CODE BRANCH_CODE, BM.BRANCH_NAME BRANCH_NAME,"
	 * + "STR.KIOSK_ID KIOSK_ID, STR.NO_OF_TXNS NO_OF_TXNS, STR.VENDOR VENDOR "+
	 * "FROM TBL_BRANCH_MASTER BM "+
	 * "JOIN TBL_SWAYAM_TXN_REPORT STR ON BM.BRANCH_CODE = STR.BRANCH_CODE "+
	 * "AND to_date(STR.TXN_DATE,'dd-mm-yyyy')=to_date(:fromdate,'dd-mm-yyyy') "+
	 * "and (BM.CRCL_NAME=:searchText or str.branch_code= :searchText or bm.branch_name=:searchText or str.kiosk_id= :searchText) ORDER BY STR.TXN_DATE  DESC"
	 * ,nativeQuery=true,countQuery =
	 * "SELECT count(STR.BRANCH_CODE)  FROM TBL_BRANCH_MASTER BM JOIN TBL_SWAYAM_TXN_REPORT STR "
	 * +
	 * " ON BM.BRANCH_CODE = STR.BRANCH_CODE AND to_date(STR.TXN_DATE,'dd-mm-yyyy')=to_date(:fromdate,'dd-mm-yyyy') "
	 * +
	 * " and (BM.CRCL_NAME=:searchText or str.branch_code= :searchText or bm.branch_name=:searchText or str.kiosk_id= :searchText) ORDER BY STR.TXN_DATE  DESC"
	 * )
	 */
	
	/*
	 * @Query(
	 * value="SELECT  BM.CRCL_NAME CRCL_NAME, BM.NETWORK NETWORK, BM.MODULE MODULE, BM.REGION REGION, BM.BRANCH_CODE BRANCH_CODE, BM.BRANCH_NAME BRANCH_NAME,"
	 * + "STR.KIOSK_ID KIOSK_ID, STR.NO_OF_TXNS NO_OF_TXNS, STR.VENDOR VENDOR "+
	 * "FROM TBL_BRANCH_MASTER BM "+
	 * "JOIN TBL_SWAYAM_TXN_REPORT STR ON BM.BRANCH_CODE = STR.BRANCH_CODE "+
	 * "AND STR.TXN_DATE=to_date(:fromdate,'dd-mm-yyyy') "+
	 * "and (BM.CRCL_NAME=UPPER(:searchText) or str.branch_code= UPPER(:searchText) or bm.branch_name=UPPER(:searchText) or str.kiosk_id= UPPER(:searchText)) ORDER BY STR.TXN_DATE  DESC"
	 * ,nativeQuery=true,countQuery =
	 * "SELECT count(STR.BRANCH_CODE)  FROM TBL_BRANCH_MASTER BM JOIN TBL_SWAYAM_TXN_REPORT STR "
	 * +
	 * " ON BM.BRANCH_CODE = STR.BRANCH_CODE AND STR.TXN_DATE = to_date(:fromdate,'dd-mm-yyyy') "
	 * +
	 * " and (BM.CRCL_NAME=UPPER(:searchText) or str.branch_code= UPPER(:searchText) or bm.branch_name=UPPER(:searchText) or str.kiosk_id= UPPER(:searchText)) ORDER BY STR.TXN_DATE  DESC"
	 * )
	 */
	
	@Query(value="  SELECT  BM.CRCL_NAME CRCL_NAME,BM.NETWORK NETWORK,BM.MODULE MODULE,BM.REGION REGION,	BM.BRANCH_CODE BRANCH_CODE," + 
			"	BM.BRANCH_NAME BRANCH_NAME,	km.KIOSK_ID KIOSK_ID,STR.NO_OF_TXNS NO_OF_TXNS,	km.VENDOR VENDOR " + 
			"	FROM  tbl_kiosk_master km	INNER JOIN TBL_BRANCH_MASTER BM ON KM.BRANCH_CODE = BM.BRANCH_CODE " + 
			"	INNER JOIN TBL_SWAYAM_TXN_REPORT STR ON KM.BRANCH_CODE = STR.BRANCH_CODE AND UPPER(STR.KIOSK_ID)=UPPER(KM.KIOSK_ID) " + 
			"	AND STR.TXN_DATE=to_date(:fromdate,'dd-mm-yyyy')  "+
			" and (BM.CRCL_NAME=UPPER(:searchText) or str.branch_code= UPPER(:searchText) or bm.branch_name=UPPER(:searchText) or str.kiosk_id= UPPER(:searchText)) ORDER BY STR.TXN_DATE  DESC"
			,nativeQuery=true,countQuery = " SELECT  BM.CRCL_NAME CRCL_NAME,BM.NETWORK NETWORK,BM.MODULE MODULE,BM.REGION REGION,	BM.BRANCH_CODE BRANCH_CODE," + 
					" BM.BRANCH_NAME BRANCH_NAME,	km.KIOSK_ID KIOSK_ID,STR.NO_OF_TXNS NO_OF_TXNS,	km.VENDOR VENDOR " + 
					" FROM  tbl_kiosk_master km	INNER JOIN TBL_BRANCH_MASTER BM ON KM.BRANCH_CODE = BM.BRANCH_CODE " + 
					"	INNER JOIN TBL_SWAYAM_TXN_REPORT STR ON KM.BRANCH_CODE = STR.BRANCH_CODE AND UPPER(STR.KIOSK_ID)=UPPER(KM.KIOSK_ID) " + 
					"	AND STR.TXN_DATE=to_date(:fromdate,'dd-mm-yyyy') " + 
					" and (BM.CRCL_NAME=UPPER(:searchText) or str.branch_code= UPPER(:searchText) or bm.branch_name=UPPER(:searchText) or str.kiosk_id= UPPER(:searchText)) ORDER BY STR.TXN_DATE  DESC" )
	
	
	Page<RealTimeTransaction> findByFromDateSearchText(@Param("fromdate") String fromdate,@Param("searchText") String searchText, Pageable pageable);
	
	/* Commented by Manisha on 20-Jan-2021
	 * @Query(
	 * value="SELECT  BM.CRCL_NAME CRCL_NAME, BM.NETWORK NETWORK, BM.MODULE MODULE, BM.REGION REGION, BM.BRANCH_CODE BRANCH_CODE, BM.BRANCH_NAME BRANCH_NAME,"
	 * + "STR.KIOSK_ID KIOSK_ID, STR.NO_OF_TXNS NO_OF_TXNS, STR.VENDOR VENDOR "+
	 * "FROM TBL_BRANCH_MASTER BM "+
	 * "JOIN TBL_SWAYAM_TXN_REPORT STR ON BM.BRANCH_CODE = STR.BRANCH_CODE "+
	 * "AND to_date(STR.TXN_DATE,'dd-mm-yyyy')=to_date(:fromdate,'dd-mm-yyyy')  ORDER BY STR.TXN_DATE  DESC"
	 * ,nativeQuery=true,countQuery =
	 * "SELECT count(STR.BRANCH_CODE)  FROM TBL_BRANCH_MASTER BM JOIN TBL_SWAYAM_TXN_REPORT STR "
	 * +
	 * " ON BM.BRANCH_CODE = STR.BRANCH_CODE AND to_date(STR.TXN_DATE,'dd-mm-yyyy')=to_date(:fromdate,'dd-mm-yyyy')  ORDER BY STR.TXN_DATE  DESC"
	 * )
	 */
	
	/* commented by manisha on 2-mar-2021
	 * @Query(
	 * value="SELECT  BM.CRCL_NAME CRCL_NAME, BM.NETWORK NETWORK, BM.MODULE MODULE, BM.REGION REGION, BM.BRANCH_CODE BRANCH_CODE, BM.BRANCH_NAME BRANCH_NAME,"
	 * + "STR.KIOSK_ID KIOSK_ID, STR.NO_OF_TXNS NO_OF_TXNS, STR.VENDOR VENDOR "+
	 * "FROM TBL_BRANCH_MASTER BM "+
	 * "JOIN TBL_SWAYAM_TXN_REPORT STR ON BM.BRANCH_CODE = STR.BRANCH_CODE "+
	 * "AND STR.TXN_DATE = to_date(:fromdate,'dd-mm-yyyy')  ORDER BY STR.TXN_DATE  DESC"
	 * ,nativeQuery=true,countQuery =
	 * "SELECT count(STR.BRANCH_CODE)  FROM TBL_BRANCH_MASTER BM JOIN TBL_SWAYAM_TXN_REPORT STR "
	 * +
	 * " ON BM.BRANCH_CODE = STR.BRANCH_CODE AND STR.TXN_DATE = to_date(:fromdate,'dd-mm-yyyy')  ORDER BY STR.TXN_DATE  DESC"
	 * )
	 */
	
	@Query(value="SELECT  BM.CRCL_NAME CRCL_NAME,BM.NETWORK NETWORK,BM.MODULE MODULE,BM.REGION REGION,	BM.BRANCH_CODE BRANCH_CODE," + 
			"				BM.BRANCH_NAME BRANCH_NAME,	km.KIOSK_ID KIOSK_ID,STR.NO_OF_TXNS NO_OF_TXNS,	km.VENDOR VENDOR " + 
			"				FROM  tbl_kiosk_master km	INNER JOIN TBL_BRANCH_MASTER BM ON KM.BRANCH_CODE = BM.BRANCH_CODE " + 
			"				INNER JOIN TBL_SWAYAM_TXN_REPORT STR ON KM.BRANCH_CODE = STR.BRANCH_CODE AND UPPER(STR.KIOSK_ID)=UPPER(KM.KIOSK_ID) " + 
			"				AND STR.TXN_DATE=to_date(:fromdate,'dd-mm-yyyy')  ORDER BY STR.TXN_DATE  DESC",nativeQuery=true,countQuery = "SELECT  BM.CRCL_NAME CRCL_NAME,BM.NETWORK NETWORK,BM.MODULE MODULE,BM.REGION REGION,	BM.BRANCH_CODE BRANCH_CODE," + 
					"				BM.BRANCH_NAME BRANCH_NAME,	km.KIOSK_ID KIOSK_ID,STR.NO_OF_TXNS NO_OF_TXNS,	km.VENDOR VENDOR " + 
					"				FROM  tbl_kiosk_master km	INNER JOIN TBL_BRANCH_MASTER BM ON KM.BRANCH_CODE = BM.BRANCH_CODE " + 
					"				INNER JOIN TBL_SWAYAM_TXN_REPORT STR ON KM.BRANCH_CODE = STR.BRANCH_CODE AND UPPER(STR.KIOSK_ID)=UPPER(KM.KIOSK_ID) " + 
					"				AND STR.TXN_DATE=to_date(:fromdate,'dd-mm-yyyy')  ORDER BY STR.TXN_DATE  DESC" )
	
	List<RealTimeTransaction> findByDate(@Param("fromdate") String fromdate);
	
	// 12c
	@Query(value="select to_char(end_dttm,'dd-mm-yy hh24:mm:ss') from tbl_audit_job where job_name='TBL_SWAYAM_TXN_DAILY' and status='C' order by end_dttm desc fetch first 1 row only ",nativeQuery = true )
	//for 11g
//	@Query(value="select to_char(end_dttm,'dd-mm-yy hh24:mm:ss') from  tbl_audit_job where job_name='TBL_SWAYAM_TXN_DAILY' and status='C' and rownum <= 1 order by end_dttm desc ",nativeQuery = true )
	String findCurrentDateAuditJob();
}
