package sbi.kiosk.swayam.healthmonitoring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.DownTime;

@Repository
public interface DowntimePagingRepository extends PagingAndSortingRepository<DownTime, String>{

	@Query(value = "select km.KIOSK_ID,km.VENDOR,bm.CRCL_NAME,bm.NETWORK,bm.MODULE,bm.BRANCH_CODE,\r\n" + 
			"dt.DOWNTIME_HRS from  TBL_KIOSK_MASTER km inner JOIN TBL_BRANCH_MASTER bm \r\n" + 
			"on km.BRANCH_CODE=bm.BRANCH_CODE inner join TBL_DOWNTIME dt  \r\n" + 
			"on km.KIOSK_ID=dt.KIOSK_ID ",nativeQuery = true)
	Page<DownTime> findAll( Pageable pageable);
	

/*	
	@Query(value =
					
//			"SELECT CRCL_NAME, NETWORK, MODULE, BRANCH.BRANCH_CODE,KIOSK_ID, KIOSK.VENDOR, USERNAME AS CMS_CMF, 0 AS TOTAL_OPERATING_HRS,"
//			+ " SUM(DT.DOWNTIME_HRS) AS TOTAL_DOWNTIME	FROM TBL_KIOSK_MASTER KIOSK	JOIN TBL_BRANCH_MASTER BRANCH "
//			+ " ON BRANCH.BRANCH_CODE=KIOSK.BRANCH_CODE	JOIN TBL_DOWNTIME DT	ON KIOSK.KIOSK_ID=DT.KIOSK_ID"
//			+ "	JOIN TBL_USER_KIOSK_MAPPING UKM ON UKM.KIOSK_ID=KIOSK.KIOSK_ID	JOIN TBL_USER USR ON USR.PF_ID=UKM.PF_ID"
//			+ "	 WHERE"
//			+ "	BRANCH.CRCL_NAME LIKE %?1% "
//			+ "	AND KIOSK.VENDOR LIKE %?2% "
//			+ "	AND UKM.PF_ID LIKE %?3% "
//			+ "	AND DT.START_DTTM=?4 "
//			+ "	AND DT.END_DTTM=?5 "
//			+ "	GROUP BY CRCL_NAME, NETWORK, MODULE, BRANCH.BRANCH_CODE, KIOSK_ID,KIOSK.VENDOR, USERNAME,DT.DOWNTIME_HRS"
//			
			
			"SELECT KIOSK.circle, concat('NET-0',substr(NETWORK,1,1)) as NETWORK, MODULE, BRANCH.BRANCH_CODE, KIOSK.KIOSK_ID,KIOSK.VENDOR, USERNAME AS CMS_CMF, "
			 +"	(((to_date(?1, 'dd-mm-yyyy') - to_date( ?2, 'dd-mm-yyyy'))-(select count(*) as holidays from "
			+ " TBL_BRANCH_HOLIDAY   where circle=KIOSK.circle and to_date(HOLIDAY_DATE, 'dd-mm-yyyy') "
			 + " between to_date(?2, 'dd-mm-yyyy') and TO_DATE( ?1,'dd-mm-yyyy')))*8)  AS TOTAL_OPERATING_HRS, "
			+ " (SELECT nvl(SUM(dt.DOWNTIME_HRS),0) FROM TBL_DOWNTIME dt WHERE dt.KIOSK_ID=KIOSK.KIOSK_ID) AS TOTAL_DOWNTIME "
			+ " FROM TBL_KIOSK_MASTER KIOSK	JOIN TBL_BRANCH_MASTER BRANCH ON BRANCH.BRANCH_CODE=KIOSK.BRANCH_CODE "
			+ " JOIN TBL_USER_KIOSK_MAPPING UKM ON UKM.KIOSK_ID=KIOSK.KIOSK_ID 	JOIN TBL_USER USR ON USR.PF_ID=UKM.PF_ID "
			+ "	 WHERE "
			+ "	KIOSK.circle LIKE %?3% "
			+ "	AND KIOSK.VENDOR LIKE %?4% "
			+ "	AND UKM.PF_ID LIKE %?5% "
		    + " GROUP BY KIOSK.circle, NETWORK, MODULE, BRANCH.BRANCH_CODE,KIOSK.KIOSK_ID, KIOSK.VENDOR, USERNAME "
			
			
			,nativeQuery = true,
			
			countQuery = 		
			

			" SELECT count(KIOSK.KIOSK_ID), "
			 +"	(((to_date(?1, 'dd-mm-yyyy') - to_date( ?2, 'dd-mm-yyyy'))-(select count(*) as holidays from "
			+ " TBL_BRANCH_HOLIDAY   where circle=KIOSK.circle and to_date(HOLIDAY_DATE, 'dd-mm-yyyy') "
			 + " between to_date(?2, 'dd-mm-yyyy') and TO_DATE( ?1,'dd-mm-yyyy')))*8)  AS TOTAL_OPERATING_HRS, "
			+ " (SELECT nvl(SUM(dt.DOWNTIME_HRS),0) FROM TBL_DOWNTIME dt WHERE dt.KIOSK_ID=KIOSK.KIOSK_ID) AS TOTAL_DOWNTIME "
			+ " FROM TBL_KIOSK_MASTER KIOSK	JOIN TBL_BRANCH_MASTER BRANCH ON BRANCH.BRANCH_CODE=KIOSK.BRANCH_CODE "
			+ " JOIN TBL_USER_KIOSK_MAPPING UKM ON UKM.KIOSK_ID=KIOSK.KIOSK_ID 	JOIN TBL_USER USR ON USR.PF_ID=UKM.PF_ID "
			+ "	 WHERE "
			+ "	KIOSK.circle LIKE %?3% "
			+ "	AND KIOSK.VENDOR LIKE %?4% "
			+ "	AND UKM.PF_ID LIKE %?5% "
		    + " GROUP BY KIOSK.circle, NETWORK, MODULE, BRANCH.BRANCH_CODE,KIOSK.KIOSK_ID, KIOSK.VENDOR, USERNAME ")
	*/
	
	///changes by satendra 03052021
	
	/*
	@Query(value =" SELECT  ukm.pf_id, km.circle, concat('NET-0',substr(network,1,1) ) AS network,"
	   + "  module,bm.branch_code,km.kiosk_id,  km.vendor,username AS cms_cmf,"
	   + "  ( ( ( TO_DATE(?1,'dd-mm-yyyy') - TO_DATE(?2,'dd-mm-yyyy') ) - ( "
	   + " SELECT   COUNT(*) AS holidays   FROM  tbl_branch_holiday "
	   + "   WHERE circle = km.circle   AND TO_DATE(holiday_date,'dd-mm-yy') "
	   + " BETWEEN TO_DATE(?2,'dd-mm-yyyy') AND TO_DATE(?1,'dd-mm-yyyy') ) ) * 8 ) "
	   + " AS total_operating_hrs,   (  SELECT  nvl( SUM(dt.downtime_hrs),  0 )"
	   + "   FROM tbl_downtime dt  WHERE  dt.kiosk_id = km.kiosk_id ) AS total_downtime "
	   + " FROM  tbl_kiosk_master km  inner JOIN tbl_branch_master bm "
	   + " ON bm.branch_code = km.branch_code  LEFT JOIN tbl_user_kiosk_mapping ukm  "
	   + " ON km.kiosk_id = ukm.kiosk_id   left JOIN tbl_user usr "
	   + " ON ukm.pf_id = usr.pf_id "
	   + " WHERE"
	   + " km.circle LIKE %?3% AND "
	   + " km.vendor LIKE %?4% "
	   + "AND "
	   + " nvl(ukm.pf_id,0) LIKE %?5% "
	   + " GROUP BY ukm.pf_id,km.circle, network, module,bm.branch_code,"
	   + " km.kiosk_id,  km.vendor,  username"	,
	   nativeQuery = true,countQuery = "  SELECT  count(km.kiosk_id) "
	    
			   + " FROM  tbl_kiosk_master km  inner JOIN tbl_branch_master bm "
			   + " ON bm.branch_code = km.branch_code  LEFT JOIN tbl_user_kiosk_mapping ukm  "
			   + " ON km.kiosk_id = ukm.kiosk_id   left JOIN tbl_user usr "
			   + " ON ukm.pf_id = usr.pf_id "
			   + " WHERE"
			   + " km.circle LIKE %?3% AND "
			   + " km.vendor LIKE %?4% "
			  + "AND "
			 +   " nvl(ukm.pf_id,0) LIKE %?5% "
			   + " GROUP BY ukm.pf_id,km.circle, network, module,bm.branch_code,"
			   + " km.kiosk_id,  km.vendor,  username")
	
	*/
	
	
	@Query(value ="	SELECT uk.pf_id,  km.circle,   concat( 'NET-0',  substr(bm.network, 1,1 ) ) NETWORK,"
			    + " bm.module, bm.branch_code, km.kiosk_id, km.vendor,  us.username AS cms_cmf,"
			    + " nvl(SUM(dt.branch_wrkng_hrs),   0  ) Branch_Operating_Hours,"
			    + " nvl( SUM(dt.act_downtime), 0 ) Total_Downtime FROM  tbl_kiosk_master km  "
			    + " INNER JOIN tbl_branch_master bm ON bm.branch_code = km.branch_code INNER JOIN tbl_downtime dt "
			    + " ON km.kiosk_id = dt.kiosk_id  LEFT JOIN tbl_user_kiosk_mapping uk "
			    + " ON km.kiosk_id = uk.kiosk_id  LEFT JOIN tbl_user us ON uk.pf_id = us.pf_id "
			    + " WHERE   ( dt.downtime_dt BETWEEN TO_DATE(?1,'dd-mm-yyyy') AND TO_DATE(?2,'dd-mm-yyyy')  ) "
			    + " AND km.circle LIKE %?3% "
			    + " AND km.vendor LIKE %?4% "
			    + " AND  nvl(uk.pf_id,0) LIKE %?5% "
			    +" AND bm.branch_code LIKE %?6% "
			    +" AND km.kiosk_id LIKE %?7% "
			    + " GROUP BY uk.pf_id, km.circle,bm.network,bm.module,bm.branch_code,km.kiosk_id,km.vendor,us.username",
			    
			    nativeQuery = true,countQuery ="	SELECT count(km.kiosk_id)  FROM  tbl_kiosk_master km  "
					    + " INNER JOIN tbl_branch_master bm ON bm.branch_code = km.branch_code INNER JOIN tbl_downtime dt "
					    + " ON km.kiosk_id = dt.kiosk_id  LEFT JOIN tbl_user_kiosk_mapping uk "
					    + " ON km.kiosk_id = uk.kiosk_id  LEFT JOIN tbl_user us ON uk.pf_id = us.pf_id "
					    + " WHERE   ( dt.downtime_dt BETWEEN TO_DATE(?1,'dd-mm-yyyy') AND TO_DATE(?2,'dd-mm-yyyy')  ) "
					    + " AND km.circle LIKE %?3% "
					    + " AND km.vendor LIKE %?4% "
					    + " AND  nvl(uk.pf_id,0) LIKE %?5% "
					    +" AND bm.branch_code LIKE %?6% "
					    +" AND km.kiosk_id LIKE %?7% "
					    + " GROUP BY uk.pf_id, km.circle,bm.network,bm.module,bm.branch_code,km.kiosk_id,km.vendor,us.username")
	
	
	
	
	
	Page<DownTime> findAllByFilter(String selectedFromDateId, String selectedToDateId, String selectedCircelId,
			String selectedVendorId ,String selectedCmsCmfId,String selectedBranchCodeId,String selectedKioskId,  Pageable pageable);
	
	
	@Query(value = "select USERNAME from tbl_User where pf_id in(select pf_id from tbl_User_Kiosk_Mapping where KIOSK_ID=:kioskId )",nativeQuery = true)
         String  findByKioskId(@Param("kioskId") String kioskId);
	
	@Query(value = "select USERNAME from tbl_User where pf_id in(select pf_id from tbl_User_Kiosk_Mapping) ",nativeQuery = true)
    List<String>  findAllCmfCmsUser();
	
	
	

//	@Query(value=" select CIRCLE, DOWNTIME.KIOSK_ID, (((to_date(:toDate, 'dd-mm-yyyy') - to_date(:fromDate, 'dd-mm-yyyy'))-(select count(*) as holidays"
//			+ " from TBL_BRANCH_HOLIDAY where 	circle=kioskmst.circle and to_date(HOLIDAY_DATE, 'DD-MON-YY')"
//			+ " between to_date(:fromDate, 'DD-mm-YY') and TO_DATE(:toDate,'DD-mm-YY')))*8) AS TOTAL_WORKING_HRS"
//			+ "	FROM TBL_DOWNTIME DOWNTIME, TBL_KIOSK_MASTER kioskmst	GROUP BY CIRCLE, DOWNTIME.KIOSK_ID",nativeQuery = true)
//			List<DowntimeModel>  findByHolidayDate(@Param("fromDate") String fromDate,@Param("toDate") String toDate);

			
			
			  @Query(value="SELECT DOWNTIME.KIOSK_ID, SUM(DOWNTIME_HRS) AS TOTAL_DOWNTIME	FROM TBL_DOWNTIME DOWNTIME, "
			  + "TBL_KIOSK_MASTER kioskmst WHERE DOWNTIME.KIOSK_ID =kioskmst.KIOSK_ID"
			  + "	AND START_DTTM>=to_date(:startDate, 'dd-mm-yyyy') "
			  + " and END_DTTM<=TO_DATE(:endDate,'dd-mm-yyyy') GROUP BY DOWNTIME.KIOSK_ID " ,nativeQuery = true,
			  countQuery = "SELECT count(DOWNTIME.KIOSK_ID) FROM TBL_DOWNTIME DOWNTIME, "
					  + "TBL_KIOSK_MASTER kioskmst WHERE DOWNTIME.KIOSK_ID =kioskmst.KIOSK_ID"
					  + " AND START_DTTM>=to_date(:startDate, 'dd-mm-yyyy') "
					  + " and END_DTTM<=TO_DATE(:endDate,'dd-mm-yyyy') GROUP BY DOWNTIME.KIOSK_ID ")
			  
			  List<DownTime> findByFilter(@Param("startDate")  Date startDate,@Param("endDate")  Date endDate, Pageable pageable);
			 
	/*
	
				@Query(value =
						
//						"SELECT CRCL_NAME, NETWORK, MODULE, BRANCH.BRANCH_CODE,KIOSK_ID, KIOSK.VENDOR, USERNAME AS CMS_CMF, 0 AS TOTAL_OPERATING_HRS,"
//						+ " SUM(DT.DOWNTIME_HRS) AS TOTAL_DOWNTIME	FROM TBL_KIOSK_MASTER KIOSK	JOIN TBL_BRANCH_MASTER BRANCH "
//						+ " ON BRANCH.BRANCH_CODE=KIOSK.BRANCH_CODE	JOIN TBL_DOWNTIME DT	ON KIOSK.KIOSK_ID=DT.KIOSK_ID"
//						+ "	JOIN TBL_USER_KIOSK_MAPPING UKM ON UKM.KIOSK_ID=KIOSK.KIOSK_ID	JOIN TBL_USER USR ON USR.PF_ID=UKM.PF_ID"
//						+ "	 WHERE"
//						+ "	BRANCH.CRCL_NAME LIKE %?1% "
//						+ "	AND KIOSK.VENDOR LIKE %?2% "
//						+ "	AND UKM.PF_ID LIKE %?3% "
//						+ "	AND DT.START_DTTM=?4 "
//						+ "	AND DT.END_DTTM=?5 "
//						+ "	GROUP BY CRCL_NAME, NETWORK, MODULE, BRANCH.BRANCH_CODE, KIOSK_ID,KIOSK.VENDOR, USERNAME,DT.DOWNTIME_HRS"
//						
						
						"SELECT KIOSK.circle, concat('NET-0',substr(NETWORK,1,1)) as NETWORK, MODULE, BRANCH.BRANCH_CODE, KIOSK.KIOSK_ID,KIOSK.VENDOR, USERNAME AS CMS_CMF, "
						 +"	(((to_date(?1, 'dd-mm-yyyy') - to_date( ?2, 'dd-mm-yyyy'))-(select count(*) as holidays from "
						+ " TBL_BRANCH_HOLIDAY   where circle=KIOSK.circle and to_date(HOLIDAY_DATE, 'dd-mm-yyyy') "
						 + " between to_date(?2, 'dd-mm-yyyy') and TO_DATE( ?1,'dd-mm-yyyy')))*8)  AS TOTAL_OPERATING_HRS, "
						+ " (SELECT nvl(SUM(dt.DOWNTIME_HRS),0) FROM TBL_DOWNTIME dt WHERE dt.KIOSK_ID=KIOSK.KIOSK_ID) AS TOTAL_DOWNTIME "
						+ " FROM TBL_KIOSK_MASTER KIOSK	JOIN TBL_BRANCH_MASTER BRANCH ON BRANCH.BRANCH_CODE=KIOSK.BRANCH_CODE "
						+ " JOIN TBL_USER_KIOSK_MAPPING UKM ON UKM.KIOSK_ID=KIOSK.KIOSK_ID 	JOIN TBL_USER USR ON USR.PF_ID=UKM.PF_ID "
						+ "	 WHERE "
						+ "	KIOSK.circle LIKE %?3% "
						+ "	AND KIOSK.VENDOR LIKE %?4% "
						+ "	AND UKM.PF_ID LIKE %?5% "
					    + " GROUP BY KIOSK.circle, NETWORK, MODULE, BRANCH.BRANCH_CODE,KIOSK.KIOSK_ID, KIOSK.VENDOR, USERNAME "
						
						
						,nativeQuery = true,
						
						countQuery = 						
					  "	SELECT count(KIOSK.KIOSK_ID) FROM TBL_KIOSK_MASTER KIOSK JOIN TBL_BRANCH_MASTER BRANCH "
					 + " ON BRANCH.BRANCH_CODE=KIOSK.BRANCH_CODE  JOIN TBL_DOWNTIME DT ON KIOSK.KIOSK_ID=DT.KIOSK_ID "
						+ "	JOIN TBL_USER_KIOSK_MAPPING UKM ON UKM.KIOSK_ID=KIOSK.KIOSK_ID	JOIN TBL_USER USR ON USR.PF_ID=UKM.PF_ID "
						+ "	 WHERE"
						+ "	KIOSK.circle LIKE %?3% "
						+ "	AND KIOSK.VENDOR LIKE %?4% "
						+ "	GROUP BY KIOSK.circle, NETWORK, MODULE, BRANCH.BRANCH_CODE,KIOSK.KIOSK_ID, KIOSK.VENDOR, USERNAME,DT.DOWNTIME_HRS" )
				
				*/
				
				///changes BY Satendra 03May2021
		/*		
				@Query(value =" SELECT  ukm.pf_id, km.circle, concat('NET-0',substr(network,1,1) ) AS network, "
				   + "  module,bm.branch_code,km.kiosk_id,  km.vendor,username AS cms_cmf,"
				   + "  ( ( ( TO_DATE(?1,'dd-mm-yyyy') - TO_DATE(?2,'dd-mm-yyyy') ) - ( "
				   + " SELECT   COUNT(*) AS holidays   FROM  tbl_branch_holiday "
				   + "   WHERE circle = km.circle   AND TO_DATE(holiday_date,'dd-mm-yy') "
				   + " BETWEEN TO_DATE(?2,'dd-mm-yyyy') AND TO_DATE(?1,'dd-mm-yyyy') ) ) * 8 ) "
				   + " AS total_operating_hrs,   (  SELECT  nvl( SUM(dt.downtime_hrs),  0 )"
				   + "   FROM tbl_downtime dt  WHERE  dt.kiosk_id = km.kiosk_id ) AS total_downtime "
				   + " FROM  tbl_kiosk_master km  inner JOIN tbl_branch_master bm "
				   + " ON bm.branch_code = km.branch_code  LEFT JOIN tbl_user_kiosk_mapping ukm  "
				   + " ON km.kiosk_id = ukm.kiosk_id   left JOIN tbl_user usr "
				   + " ON ukm.pf_id = usr.pf_id "
				   + " WHERE "
				   + " km.circle LIKE %?3% AND "
				   + " km.vendor LIKE %?4% AND "
				   + " nvl(ukm.pf_id,0) LIKE %?5% "
				   + " GROUP BY ukm.pf_id,km.circle, network, module,bm.branch_code, "
				   + " km.kiosk_id,  km.vendor,  username "	,
				   nativeQuery = true,countQuery = "SELECT  count(km.kiosk_id) "
						   + "   FROM tbl_downtime dt  WHERE  dt.kiosk_id = km.kiosk_id ) AS total_downtime "
						   + " FROM  tbl_kiosk_master km  inner JOIN tbl_branch_master bm "
						   + " ON bm.branch_code = km.branch_code  LEFT JOIN tbl_user_kiosk_mapping ukm  "
						   + " ON km.kiosk_id = ukm.kiosk_id   left JOIN tbl_user usr "
						   + " ON ukm.pf_id = usr.pf_id "
						   + " WHERE "
						   + " km.circle LIKE %?3% AND "
						   + " km.vendor LIKE %?4% AND "
						   + " nvl(ukm.pf_id,0) LIKE %?5% "
						   + " GROUP BY ukm.pf_id,km.circle, network, module,bm.branch_code,"
						   + " km.kiosk_id,  km.vendor,  username ")
				
				*/
				
				
				@Query(value ="	SELECT uk.pf_id,  km.circle,   concat( 'NET-0',  substr(bm.network, 1,1 ) ) NETWORK,"
					    + " bm.module, bm.branch_code, km.kiosk_id, km.vendor,  us.username AS cms_cmf,"
					    + " nvl(SUM(dt.branch_wrkng_hrs),   0  ) Branch_Operating_Hours,"
					    + " nvl( SUM(dt.act_downtime), 0 ) Total_Downtime FROM  tbl_kiosk_master km  "
					    + " INNER JOIN tbl_branch_master bm ON bm.branch_code = km.branch_code INNER JOIN tbl_downtime dt "
					    + " ON km.kiosk_id = dt.kiosk_id  LEFT JOIN tbl_user_kiosk_mapping uk "
					    + " ON km.kiosk_id = uk.kiosk_id  LEFT JOIN tbl_user us ON uk.pf_id = us.pf_id "
					    + " WHERE   ( dt.downtime_dt BETWEEN TO_DATE(?1,'dd-mm-yyyy') AND TO_DATE(?2,'dd-mm-yyyy')  ) "
					    + " AND km.circle LIKE %?3% "
					    + " AND km.vendor LIKE %?4% "
					    + " AND  nvl(uk.pf_id,0) LIKE %?5% "
					    +" AND bm.branch_code LIKE %?6% "
					    +" AND km.kiosk_id LIKE %?7% "
					    + " GROUP BY uk.pf_id, km.circle,bm.network,bm.module,bm.branch_code,km.kiosk_id,km.vendor,us.username",
					    
					    nativeQuery = true,countQuery ="	SELECT count(km.kiosk_id)  FROM  tbl_kiosk_master km  "
							    + " INNER JOIN tbl_branch_master bm ON bm.branch_code = km.branch_code INNER JOIN tbl_downtime dt "
							    + " ON km.kiosk_id = dt.kiosk_id  LEFT JOIN tbl_user_kiosk_mapping uk "
							    + " ON km.kiosk_id = uk.kiosk_id  LEFT JOIN tbl_user us ON uk.pf_id = us.pf_id "
							    + " WHERE   ( dt.downtime_dt BETWEEN TO_DATE(?1,'dd-mm-yyyy') AND TO_DATE(?2,'dd-mm-yyyy')  ) "
							    + " AND km.circle LIKE %?3% "
							    + " AND km.vendor LIKE %?4% "
							    + " AND  nvl(uk.pf_id,0) LIKE %?5% "
							    +" AND bm.branch_code LIKE %?6% "
							    +" AND km.kiosk_id LIKE %?7% "
							    + " GROUP BY uk.pf_id, km.circle,bm.network,bm.module,bm.branch_code,km.kiosk_id,km.vendor,us.username")
			
			
				
				
				List<DownTime> findAllByFilterDTimeReports( String selectedFromDateId, String selectedToDateId, String selectedCircelId,
						String selectedVendorId ,String selectedCmsCmfId,String selectedBranchCodeId,String selectedKioskId);
			  
			  
			  
}
