package sbi.kiosk.swayam.transactiondashboard.repository;

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
	
	@Query(value="SELECT BM.CRCL_NAME CRCL_NAME, concat('NET-0',substr(BM.NETWORK,1,1)) as NETWORK, "+
    "BM.MODULE MODULE, BM.REGION REGION, BM.BRANCH_CODE BRANCH_CODE, BM.BRANCH_NAME BRANCH_NAME, KM.KIOSK_ID KIOSK_ID, KM.VENDOR VENDOR "+
    "FROM TBL_BRANCH_MASTER BM "+
    "JOIN TBL_KIOSK_MASTER KM ON BM.BRANCH_CODE = KM.BRANCH_CODE "+
    "WHERE KM.KIOSK_ID NOT IN ("+
            "SELECT STR.KIOSK_ID FROM TBL_SWAYAM_TXN_REPORT STR "+
            "WHERE to_date(STR.TXN_DATE,'dd-mm-yyyy')>= to_date(:fromdate, 'dd-mm-yyyy') "+ 
               "and to_date(STR.TXN_DATE,'dd-mm-yyyy')<= to_date(:todate, 'dd-mm-yyyy')"+
        ")",nativeQuery=true,
        countQuery="SELECT count(KM.BRANCH_CODE) FROM TBL_BRANCH_MASTER BM"
        		+ "  JOIN TBL_KIOSK_MASTER KM ON BM.BRANCH_CODE = KM.BRANCH_CODE  WHERE KM.KIOSK_ID NOT IN ( SELECT STR.KIOSK_ID FROM "
        		+ "TBL_SWAYAM_TXN_REPORT STR  WHERE to_date(STR.TXN_DATE,'dd-mm-yyyy')>= to_date(:fromdate, 'dd-mm-yyyy')"
        		+ "   and to_date(STR.TXN_DATE,'dd-mm-yyyy')<= to_date(:todate, 'dd-mm-yyyy'))")
        			Page<ZeroTransactionKiosks> findByDate(@Param("fromdate") String fromdate,@Param("todate") String todate,Pageable pageable);
	
}
