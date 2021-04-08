package sbi.kiosk.swayam.transactiondashboard.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.SwayamTxnDaily;

@Repository
public interface SwayamTxnDailyRepository extends PagingAndSortingRepository<SwayamTxnDaily, Serializable>{

	@Query(value = " select count(error_code) error_code_count,error_desc,error_code   from TBL_SWAYAM_TXN_DAILY where REQUESTING_BRANCH =:branchCode "
			+ " and REQUEST_DATE_TIME between :fromdate and :todate and status=:statusParam GROUP BY error_code,error_desc ",nativeQuery = true
			,countQuery="select count(error_code) error_code_count,error_desc,error_code   from TBL_SWAYAM_TXN_DAILY where REQUESTING_BRANCH =:branchCode "
					+ " and REQUEST_DATE_TIME between :fromdate and :todate and status=:statusParam  GROUP BY error_code,error_desc ")
	List<SwayamTxnDaily> findAllByReqBranchNoOfTechFail( @Param("branchCode") String branchCode, @Param("fromdate") String fromdate,@Param("todate") String todate,@Param("statusParam") String statusParam);
	
	
	@Query(value = " select count(error_code) error_code_count,error_desc,error_code  from TBL_SWAYAM_TXN_DAILY where REQUESTING_BRANCH =:branchCode "
			+ " and REQUEST_DATE_TIME between :fromdate and :todate and status=:statusParam GROUP BY error_code,error_desc ",nativeQuery = true
			,countQuery="select count(error_code) error_code_count,error_desc,error_code from TBL_SWAYAM_TXN_DAILY where REQUESTING_BRANCH =:branchCode "
					+ " and REQUEST_DATE_TIME between :fromdate and :todate and status=:statusParam GROUP BY error_code,error_desc ")
	List<SwayamTxnDaily> findAllByNoOfBussTxnFail( @Param("branchCode") String branchCode, @Param("fromdate") String fromdate,@Param("todate") String todate,@Param("statusParam") String statusParam);
	
	

}
