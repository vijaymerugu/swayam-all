package sbi.kiosk.swayam.healthmonitoring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.TicketHistory;

@Repository
public interface TicketHistoryPagingRepository extends PagingAndSortingRepository<TicketHistory, String>{

	@Query(value = "select km.KIOSK_ID,km.VENDOR,th.CALL_LOG_DATE,th.CALL_CLOSED_DATE,th.CALL_CATEGORY,\r\n" + 
			" th.CALL_SUB_CATEGORY,bm.CRCL_NAME,bm.BRANCH_CODE from TBL_TICKET_HISTORY th \r\n" + 
			" inner JOIN TBL_KIOSK_MASTER km on km.KIOSK_ID=th.KIOSK_ID \r\n" + 
			" inner JOIN TBL_BRANCH_MASTER bm on km.BRANCH_CODE=bm.BRANCH_CODE ",nativeQuery = true)
	Page<TicketHistory> findAll( Pageable pageable);
	
	
	
	
	
	
	@Query(value =" select km.KIOSK_ID,km.VENDOR,th.CALL_LOG_DATE,th.CALL_CLOSED_DATE,th.CALL_CATEGORY, "
			+ " th.CALL_SUB_CATEGORY,bm.CRCL_NAME,bm.BRANCH_NAME from TBL_TICKET_HISTORY th  inner JOIN TBL_KIOSK_MASTER km "
			+ " on km.KIOSK_ID=th.KIOSK_ID   inner JOIN TBL_BRANCH_MASTER bm on km.BRANCH_CODE=bm.BRANCH_CODE"
			+ "  where th.KIOSK_ID=:selectedKioskId AND km.CIRCLE=:selectedCircelId OR bm.BRANCH_NAME=:selectedBranchId  "
			+ "  OR  to_date(th.CALL_CLOSED_DATE, 'dd-mm-yyyy') = trunc(to_date(:selectedCallClosedDateId, 'dd-mm-yyyy'))   " 
		   + "  OR  to_date(th.CALL_LOG_DATE, 'dd-mm-yyyy') = trunc(to_date(:selectedCallLogDateId, 'dd-mm-yyyy')) OR  km.VENDOR=:selectedVendorId OR th.CALL_CATEGORY=:selectedCategoryId OR th.CALL_SUB_CATEGORY=:selectedSubCategoryId " ,
			nativeQuery = true
			,countQuery = " select count(km.KIOSK_ID) from TBL_TICKET_HISTORY th  inner JOIN TBL_KIOSK_MASTER km "
					+ " on km.KIOSK_ID=th.KIOSK_ID   inner JOIN TBL_BRANCH_MASTER bm on km.BRANCH_CODE=bm.BRANCH_CODE"
					+ "  where th.KIOSK_ID=:selectedKioskId OR km.CIRCLE=:selectedCircelId AND bm.BRANCH_NAME=:selectedBranchId  "
					+ "  OR  to_date(th.CALL_CLOSED_DATE, 'dd-mm-yyyy') = trunc(to_date(:selectedCallClosedDateId, 'dd-mm-yyyy'))   " 
					   + "  OR  to_date(th.CALL_LOG_DATE, 'dd-mm-yyyy') = trunc(to_date(:selectedCallLogDateId, 'dd-mm-yyyy')) "
					+ "  OR  km.VENDOR=:selectedVendorId OR th.CALL_CATEGORY=:selectedCategoryId OR th.CALL_SUB_CATEGORY=:selectedSubCategoryId " )
	 
	Page<TicketHistory>  findbyFilter1(@Param("selectedKioskId") String selectedKioskId,
			@Param("selectedCallLogDateId") String selectedCallLogDateId, @Param("selectedCategoryId") String selectedCategoryId, 
			@Param("selectedCircelId") String selectedCircelId,	@Param("selectedCallClosedDateId") 	String selectedCallClosedDateId,
			@Param("selectedSubCategoryId")  String selectedSubCategoryId,@Param("selectedBranchId") String selectedBranchId,
			@Param("selectedVendorId") 	String selectedVendorId,Pageable pageable);





	@Query(value =" select km.KIOSK_ID,km.VENDOR,th.CALL_LOG_DATE,th.CALL_CLOSED_DATE,th.CALL_CATEGORY, "
			+ " th.CALL_SUB_CATEGORY,bm.CRCL_NAME,bm.BRANCH_CODE from TBL_TICKET_HISTORY th  inner JOIN TBL_KIOSK_MASTER km "
			+ " on km.KIOSK_ID=th.KIOSK_ID   inner JOIN TBL_BRANCH_MASTER bm on km.BRANCH_CODE=bm.BRANCH_CODE "
			+ "where '1'=:selectedKioskId",
			nativeQuery = true)
			//,countQuery = " select count(km.KIOSK_ID) from TBL_TICKET_HISTORY th  inner JOIN TBL_KIOSK_MASTER km "
				//	+ " on km.KIOSK_ID=th.KIOSK_ID   inner JOIN TBL_BRANCH_MASTER bm on km.BRANCH_CODE=bm.BRANCH_CODE ")
					//+ "where '1'=:selectedKioskId" )
	Page<TicketHistory> findbyselectedKioskId(@Param("selectedKioskId") String selectedKioskId,Pageable pageable);

	
	@Query(value =" select km.KIOSK_ID,km.VENDOR,th.CALL_LOG_DATE,th.CALL_CLOSED_DATE,th.CALL_CATEGORY, "
			+ " th.CALL_SUB_CATEGORY,bm.CRCL_NAME,bm.BRANCH_CODE from TBL_TICKET_HISTORY th  inner JOIN TBL_KIOSK_MASTER km "
			+ " on km.KIOSK_ID=th.KIOSK_ID   inner JOIN TBL_BRANCH_MASTER bm on km.BRANCH_CODE=bm.BRANCH_CODE"
			//+ "  where (?1 is null OR  th.KIOSK_ID= ?1 )"
			//+ "  AND  (?2 is null OR th.CALL_LOG_DATE=?2 )  "
			///+ " AND (?3 is null OR th.CALL_CATEGORY=?3 ) "
			//+ " AND (?4 is null OR bm.BRANCH_NAME=?4 ) "
			//+ " AND (?5 is null OR th.CALL_CLOSED_DATE=?5 ) "
			//+ " AND (?6 is null OR th.CALL_SUB_CATEGORY=?6 ) "
			//+ " AND (?7 is null OR km.CIRCLE=?7 ) "
			// + " AND (?8 is null OR km.VENDOR=?8 ) "
			
			 + " where th.KIOSK_ID LIKE %?1%"
			// + " AND th.CALL_LOG_DATE LIKE %?2%"
			//+" th.CALL_LOG_DATE=to_date( LIKE %?2%,'dd-mm-yyyy') "
			// +" AND th.CALL_LOG_DATE LIKE to_date(?2,'dd-mm-yyyy') "
			 + "  AND  (?2 is null OR th.CALL_LOG_DATE LIKE to_date(?2,'dd-mm-yyyy') )  "
			 + " AND th.CALL_CATEGORY LIKE %?3%" 
			 + " AND bm.BRANCH_CODE LIKE %?4%"
			// + " AND th.CALL_CLOSED_DATE LIKE %?5%"
			+ "  AND  (?5 is null OR th.CALL_CLOSED_DATE LIKE to_date(?5,'dd-mm-yyyy') )  "
			 + " AND th.CALL_SUB_CATEGORY LIKE %?6%"
			 + " AND bm.CRCL_NAME LIKE %?7%"
		     + " AND km.VENDOR LIKE %?8%"
	/*
	 * + " AND th.CALL_CLOSED_DATE LIKE %?5%" +
	 * " AND th.CALL_SUB_CATEGORY LIKE %?6%" + " AND km.CIRCLE LIKE %?7%" +
	 * " AND km.VENDOR LIKE %?8%"
	 */
			,
			nativeQuery = true
			,countQuery = " select count(km.KIOSK_ID) from TBL_TICKET_HISTORY th  inner JOIN TBL_KIOSK_MASTER km "
					+ " on km.KIOSK_ID=th.KIOSK_ID   inner JOIN TBL_BRANCH_MASTER bm on km.BRANCH_CODE=bm.BRANCH_CODE"
				//	+ "  where (?1 is null OR  th.KIOSK_ID= ?1 )"
					//+ "  AND  (?2 is null OR th.CALL_LOG_DATE=?2 )  "
					//+ " AND (?3 is null OR th.CALL_CATEGORY=?3 ) "
	               // + " AND (?4 is null OR bm.BRANCH_NAME=?4 ) " 
	               + " where th.KIOSK_ID LIKE %?1%"
	              // + " AND th.CALL_LOG_DATE LIKE %?2%"
	             // +" AND th.CALL_LOG_DATE LIKE %to_date(?2,'dd-mm-yyyy') %"
	              + "  AND  (?2 is null OR th.CALL_LOG_DATE LIKE to_date(?2,'dd-mm-yyyy') )  "
	               + " AND th.CALL_CATEGORY LIKE %?3%" 
	               + " AND bm.BRANCH_CODE LIKE %?4%"
	              // + " AND th.CALL_CLOSED_DATE LIKE %?5%"
	               + "  AND  (?5 is null OR th.CALL_CLOSED_DATE LIKE to_date(?5,'dd-mm-yyyy') )  "
	               + " AND th.CALL_SUB_CATEGORY LIKE %?6%"
	               + " AND bm.CRCL_NAME LIKE %?7%"
	          	    + " AND km.VENDOR LIKE %?8%"
	
			)

	Page<TicketHistory> findbyFilter(String selectedKioskId, String selectedCallLogDateId ,
			 String selectedCategoryId,String selectedBranchCode,String selectedCallClosedDateId,String selectedSubCategoryId,
			 String selectedCircelId,String selectedVendorId,Pageable pageable);

	

	@Query(value =" select km.KIOSK_ID,km.VENDOR,th.CALL_LOG_DATE,th.CALL_CLOSED_DATE,th.CALL_CATEGORY, "
			+ " th.CALL_SUB_CATEGORY,bm.CRCL_NAME,bm.BRANCH_CODE from TBL_TICKET_HISTORY th  inner JOIN TBL_KIOSK_MASTER km "
			+ " on km.KIOSK_ID=th.KIOSK_ID   inner JOIN TBL_BRANCH_MASTER bm on km.BRANCH_CODE=bm.BRANCH_CODE"
			//+ "  where (?1 is null OR  th.KIOSK_ID= ?1 )"
			//+ "  AND  (?2 is null OR th.CALL_LOG_DATE=?2 )  "
			///+ " AND (?3 is null OR th.CALL_CATEGORY=?3 ) "
			//+ " AND (?4 is null OR bm.BRANCH_NAME=?4 ) "
			//+ " AND (?5 is null OR th.CALL_CLOSED_DATE=?5 ) "
			//+ " AND (?6 is null OR th.CALL_SUB_CATEGORY=?6 ) "
			//+ " AND (?7 is null OR km.CIRCLE=?7 ) "
			// + " AND (?8 is null OR km.VENDOR=?8 ) "
			
			 + " where th.KIOSK_ID LIKE %?1%"
			// + " AND th.CALL_LOG_DATE LIKE %?2%"
			//+" th.CALL_LOG_DATE=to_date( LIKE %?2%,'dd-mm-yyyy') "
			// +" AND th.CALL_LOG_DATE LIKE to_date(?2,'dd-mm-yyyy') "
			 + "  AND  (?2 is null OR th.CALL_LOG_DATE LIKE to_date(?2,'dd-mm-yyyy') )  "
			 + " AND th.CALL_CATEGORY LIKE %?3%" 
			 + " AND bm.BRANCH_CODE LIKE %?4%"
			// + " AND th.CALL_CLOSED_DATE LIKE %?5%"
			+ "  AND  (?5 is null OR th.CALL_CLOSED_DATE LIKE to_date(?5,'dd-mm-yyyy') )  "
			 + " AND th.CALL_SUB_CATEGORY LIKE %?6%"
			 + " AND bm.CRCL_NAME LIKE %?7%"
		     + " AND km.VENDOR LIKE %?8%"
	/*
	 * + " AND th.CALL_CLOSED_DATE LIKE %?5%" +
	 * " AND th.CALL_SUB_CATEGORY LIKE %?6%" + " AND km.CIRCLE LIKE %?7%" +
	 * " AND km.VENDOR LIKE %?8%"
	 */
			,
			nativeQuery = true
			,countQuery = " select count(km.KIOSK_ID) from TBL_TICKET_HISTORY th  inner JOIN TBL_KIOSK_MASTER km "
					+ " on km.KIOSK_ID=th.KIOSK_ID   inner JOIN TBL_BRANCH_MASTER bm on km.BRANCH_CODE=bm.BRANCH_CODE"
				//	+ "  where (?1 is null OR  th.KIOSK_ID= ?1 )"
					//+ "  AND  (?2 is null OR th.CALL_LOG_DATE=?2 )  "
					//+ " AND (?3 is null OR th.CALL_CATEGORY=?3 ) "
	               // + " AND (?4 is null OR bm.BRANCH_NAME=?4 ) " 
	               + " where th.KIOSK_ID LIKE %?1%"
	              // + " AND th.CALL_LOG_DATE LIKE %?2%"
	             // +" AND th.CALL_LOG_DATE LIKE %to_date(?2,'dd-mm-yyyy') %"
	              + "  AND  (?2 is null OR th.CALL_LOG_DATE LIKE to_date(?2,'dd-mm-yyyy') )  "
	               + " AND th.CALL_CATEGORY LIKE %?3%" 
	               + " AND bm.BRANCH_CODE LIKE %?4%"
	              // + " AND th.CALL_CLOSED_DATE LIKE %?5%"
	               + "  AND  (?5 is null OR th.CALL_CLOSED_DATE LIKE to_date(?5,'dd-mm-yyyy') )  "
	               + " AND th.CALL_SUB_CATEGORY LIKE %?6%"
	               + " AND bm.CRCL_NAME LIKE %?7%"
	          	    + " AND km.VENDOR LIKE %?8%"
	
			)

	List<TicketHistory> findbyFilterAndReport(String selectedKioskId, String selectedCallLogDateId ,
			 String selectedCategoryId,String selectedBranchId,String selectedCallClosedDateId,String selectedSubCategoryId,
			 String selectedCircelId,String selectedVendorId);

	
	
}
