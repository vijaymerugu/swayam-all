package sbi.kiosk.swayam.transactiondashboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.ErrorReporting;

@Repository
public interface ErrorReportingRepositoryPaging extends PagingAndSortingRepository<ErrorReporting, String>{

	@Query(value=" SELECT  BM.CRCL_NAME, BM.NETWORK, BM.MODULE, BM.REGION, BM.BRANCH_CODE, BM.BRANCH_NAME, "
			+ " STR.KIOSK_ID, STR.VENDOR, (NVL(STR.NO_OF_TECH_FAIL,0) + NVL(ERRs.ERR_COUNT,0)) AS NO_OF_ERRORS"
			+ "   FROM TBL_BRANCH_MASTER BM  JOIN TBL_SWAYAM_TXN_REPORT STR ON BM.BRANCH_CODE = STR.BRANCH_CODE "
			+ " LEFT OUTER JOIN TBL_ERROR_STATS ERRs ON ERRs.KIOSK_ID = STR.KIOSK_ID  WHERE to_date(STR.TXN_DATE, 'dd-mm-yyyy')"
			+ " between trunc(to_date(:fromdate, 'dd-mm-yyyy'))  and trunc(to_date(:todate, 'dd-mm-yyyy'))",nativeQuery=true)
	Page<ErrorReporting> findByDate(@Param("fromdate") String fromdate,@Param("todate") String todate,Pageable pageable);
}