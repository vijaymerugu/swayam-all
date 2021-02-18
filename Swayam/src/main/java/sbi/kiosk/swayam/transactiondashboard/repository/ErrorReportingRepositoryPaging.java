package sbi.kiosk.swayam.transactiondashboard.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.ErrorReporting;

@Repository
public interface ErrorReportingRepositoryPaging extends PagingAndSortingRepository<ErrorReporting, String>{

	/* commented by Manisha on 20-01-2021
	 * @Query(
	 * value=" SELECT  BM.CRCL_NAME, BM.NETWORK, BM.MODULE, BM.REGION, BM.BRANCH_CODE, BM.BRANCH_NAME, "
	 * +
	 * " STR.KIOSK_ID, STR.VENDOR, (NVL(STR.NO_OF_TECH_FAIL,0) + NVL(ERRs.ERR_COUNT,0)) AS NO_OF_ERRORS"
	 * +
	 * "   FROM TBL_BRANCH_MASTER BM  JOIN TBL_SWAYAM_TXN_REPORT STR ON BM.BRANCH_CODE = STR.BRANCH_CODE "
	 * +
	 * " LEFT OUTER JOIN TBL_ERROR_STATS ERRs ON ERRs.KIOSK_ID = STR.KIOSK_ID  WHERE to_date(STR.TXN_DATE, 'dd-mm-yyyy')"
	 * +
	 * " between trunc(to_date(:fromdate, 'dd-mm-yyyy'))  and trunc(to_date(:todate, 'dd-mm-yyyy'))"
	 * ,nativeQuery=true, countQuery=
	 * "SELECT count(STR.KIOSK_ID)  FROM TBL_BRANCH_MASTER BM  JOIN TBL_SWAYAM_TXN_REPORT STR ON BM.BRANCH_CODE = STR.BRANCH_CODE "
	 * +
	 * " LEFT OUTER JOIN TBL_ERROR_STATS ERRs ON ERRs.KIOSK_ID = STR.KIOSK_ID  WHERE to_date(STR.TXN_DATE, 'dd-mm-yyyy')"
	 * +
	 * " between trunc(to_date(:fromdate, 'dd-mm-yyyy'))  and trunc(to_date(:todate, 'dd-mm-yyyy'))"
	 * )
	 */
	
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
	
	
	Page<ErrorReporting> findByDate(@Param("fromdate") String fromdate,@Param("todate") String todate,Pageable pageable);
	
	/* commented by Manisha on 20-01-2021
	 * @Query(
	 * value=" SELECT  BM.CRCL_NAME, BM.NETWORK, BM.MODULE, BM.REGION, BM.BRANCH_CODE, BM.BRANCH_NAME, "
	 * +
	 * " STR.KIOSK_ID, STR.VENDOR, (NVL(STR.NO_OF_TECH_FAIL,0) + NVL(ERRs.ERR_COUNT,0)) AS NO_OF_ERRORS"
	 * +
	 * "   FROM TBL_BRANCH_MASTER BM  JOIN TBL_SWAYAM_TXN_REPORT STR ON BM.BRANCH_CODE = STR.BRANCH_CODE "
	 * +
	 * " LEFT OUTER JOIN TBL_ERROR_STATS ERRs ON ERRs.KIOSK_ID = STR.KIOSK_ID  WHERE to_date(STR.TXN_DATE, 'dd-mm-yyyy')"
	 * +
	 * " between trunc(to_date(:fromdate, 'dd-mm-yyyy'))  and trunc(to_date(:todate, 'dd-mm-yyyy'))"
	 * +
	 * " and (BM.CRCL_NAME=:searchText or BM.BRANCH_CODE=:searchText or STR.KIOSK_ID=:searchText or BM.BRANCH_NAME=:searchText )"
	 * ,nativeQuery=true, countQuery=
	 * "SELECT count(STR.KIOSK_ID)  FROM TBL_BRANCH_MASTER BM  JOIN TBL_SWAYAM_TXN_REPORT STR ON BM.BRANCH_CODE = STR.BRANCH_CODE "
	 * +
	 * " LEFT OUTER JOIN TBL_ERROR_STATS ERRs ON ERRs.KIOSK_ID = STR.KIOSK_ID  WHERE to_date(STR.TXN_DATE, 'dd-mm-yyyy')"
	 * +
	 * " between trunc(to_date(:fromdate, 'dd-mm-yyyy'))  and trunc(to_date(:todate, 'dd-mm-yyyy'))"
	 * +
	 * "and (BM.CRCL_NAME=:searchText or BM.BRANCH_CODE=:searchText or STR.KIOSK_ID=:searchText or BM.BRANCH_NAME=:searchText)"
	 * )
	 */	
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

	
	Page<ErrorReporting> findByDateSearchNext(@Param("fromdate") String fromdate,@Param("todate") String todate, @Param("searchText") String searchText,Pageable pageable);

	/* commented by Manisha on 20-01-2021
	 * @Query(
	 * value=" SELECT  BM.CRCL_NAME, BM.NETWORK, BM.MODULE, BM.REGION, BM.BRANCH_CODE, BM.BRANCH_NAME, "
	 * +
	 * " STR.KIOSK_ID, STR.VENDOR, (NVL(STR.NO_OF_TECH_FAIL,0) + NVL(ERRs.ERR_COUNT,0)) AS NO_OF_ERRORS"
	 * +
	 * "   FROM TBL_BRANCH_MASTER BM  JOIN TBL_SWAYAM_TXN_REPORT STR ON BM.BRANCH_CODE = STR.BRANCH_CODE "
	 * +
	 * " LEFT OUTER JOIN TBL_ERROR_STATS ERRs ON ERRs.KIOSK_ID = STR.KIOSK_ID  WHERE to_date(STR.TXN_DATE, 'dd-mm-yyyy')"
	 * +
	 * " between trunc(to_date(:fromdate, 'dd-mm-yyyy'))  and trunc(to_date(:todate, 'dd-mm-yyyy'))"
	 * ,nativeQuery=true, countQuery=
	 * "SELECT count(STR.KIOSK_ID)  FROM TBL_BRANCH_MASTER BM  JOIN TBL_SWAYAM_TXN_REPORT STR ON BM.BRANCH_CODE = STR.BRANCH_CODE "
	 * +
	 * " LEFT OUTER JOIN TBL_ERROR_STATS ERRs ON ERRs.KIOSK_ID = STR.KIOSK_ID  WHERE to_date(STR.TXN_DATE, 'dd-mm-yyyy')"
	 * +
	 * " between trunc(to_date(:fromdate, 'dd-mm-yyyy'))  and trunc(to_date(:todate, 'dd-mm-yyyy'))"
	 * )
	 */	
	
	@Query(value=" SELECT  BM.CRCL_NAME, BM.NETWORK, BM.MODULE, BM.REGION, BM.BRANCH_CODE, BM.BRANCH_NAME, "
			+ " STR.KIOSK_ID, STR.VENDOR, (NVL(STR.NO_OF_TECH_FAIL,0) + NVL(ERRs.ERR_COUNT,0)) AS NO_OF_ERRORS"
			+ "   FROM TBL_BRANCH_MASTER BM  JOIN TBL_SWAYAM_TXN_REPORT STR ON BM.BRANCH_CODE = STR.BRANCH_CODE "
			+ " LEFT OUTER JOIN TBL_ERROR_STATS ERRs ON ERRs.KIOSK_ID = STR.KIOSK_ID  WHERE STR.TXN_DATE "
			+ " between trunc(to_date(:fromdate, 'dd-mm-yyyy'))  and trunc(to_date(:todate, 'dd-mm-yyyy'))",nativeQuery=true,
			countQuery= "SELECT count(STR.KIOSK_ID)  FROM TBL_BRANCH_MASTER BM  JOIN TBL_SWAYAM_TXN_REPORT STR ON BM.BRANCH_CODE = STR.BRANCH_CODE "
					+ " LEFT OUTER JOIN TBL_ERROR_STATS ERRs ON ERRs.KIOSK_ID = STR.KIOSK_ID  WHERE STR.TXN_DATE "
					+ " between trunc(to_date(:fromdate, 'dd-mm-yyyy'))  and trunc(to_date(:todate, 'dd-mm-yyyy'))")

	
	List<ErrorReporting> findAllErrReport(@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	// 12c
	//@Query(value="select to_date(last_pbk_dt,'yyyy-mm-dd') from tbl_branch_txn_daily order by last_pbk_dt desc fetch first 1 row only ",nativeQuery = true )
	@Query(value="select to_char(end_dttm,'dd-Mon-yy hh24:mm:ss') from tbl_audit_job where job_name='TBL_SWAYAM_TXN_DAILY' and status='C' order by end_dttm desc fetch first 1 row only ",nativeQuery = true )		
	//for 11g
	//		@Query(value="select to_char(end_dttm,'dd-Mon-yy hh24:mm:ss') from  tbl_audit_job where job_name='TBL_SWAYAM_TXN_DAILY' and rownum <= 1 order by end_dttm desc ",nativeQuery = true )
			String findCurrentDateAuditJob();
}
