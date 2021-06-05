package sbi.kiosk.swayam.healthmonitoring.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.TerminalStatusSearchText;

@Repository
public interface TerminalStatusSearchTextRepositoryPaging extends PagingAndSortingRepository<TerminalStatusSearchText,Serializable>{
	Page<TerminalStatusSearchText> findByAplicationStatus(Pageable pageable, @Param("type") String type);
	
	@Query(value =" select  km.kiosk_id,km.KIOSK_SERIAL_NO,bm.branch_code,bm.CRCL_NAME,us.USERNAME,"
    		+ " ts.RMS_TICKET_NUMBER,ts.PRINTER_STATUS,ts.CARTRIDGE_STATUS,ts.AGENT_STATUS,ts.APPLICATION_STATUS "
    		+ " ,ts.LAST_PRNT_TXN_DTTM,ts.LAST_PM_DTTM "
    		+ " FROM  tbl_kiosk_master km  "
    		+ " inner JOIN TBL_TERMINAL_STATUS ts ON UPPER(ts.kiosk_id) = (km.kiosk_id) "
    		+ " left outer join  tbl_branch_master bm "
    		+ " ON bm.branch_code = km.branch_code "
    		
    		+ " LEFT JOIN tbl_user_kiosk_mapping uk "
    		+ " ON km.kiosk_id = uk.kiosk_id  LEFT JOIN tbl_user us ON uk.pf_id = us.pf_id "
    	 ,
    		
    		countQuery="  select  count(km.kiosk_id) "
    	    		+ "  FROM  tbl_kiosk_master km  "
    	    		+ "	   inner JOIN TBL_TERMINAL_STATUS ts ON UPPER(ts.kiosk_id) = (km.kiosk_id) "
    	    		+ "    left outer join  tbl_branch_master bm    ON bm.branch_code = km.branch_code "+
    	    		               
    	              "  LEFT JOIN tbl_user_kiosk_mapping uk "
    	    		+ "			     ON km.kiosk_id = uk.kiosk_id  LEFT JOIN tbl_user us ON uk.pf_id = us.pf_id ",nativeQuery=true)
	
	Page<TerminalStatusSearchText> findByAll(Pageable pageable);
	
	
	 @Query(value =" select  km.kiosk_id,km.KIOSK_SERIAL_NO,bm.branch_code,bm.CRCL_NAME,us.USERNAME,"
	    		+ " ts.RMS_TICKET_NUMBER,ts.PRINTER_STATUS,ts.CARTRIDGE_STATUS,ts.AGENT_STATUS,ts.APPLICATION_STATUS "
	    		+ " ,ts.LAST_PRNT_TXN_DTTM,ts.LAST_PM_DTTM "
	    		+ " FROM  tbl_kiosk_master km  "
	    		+ " inner JOIN TBL_TERMINAL_STATUS ts ON UPPER(ts.kiosk_id) = (km.kiosk_id) "
	    		+ " left outer join  tbl_branch_master bm "
	    		+ " ON bm.branch_code = km.branch_code "
	    		
	    		+ " LEFT JOIN tbl_user_kiosk_mapping uk "
	    		+ " ON km.kiosk_id = uk.kiosk_id  LEFT JOIN tbl_user us ON uk.pf_id = us.pf_id "
	    		+ " where   km.kiosk_id=UPPER(:searchText) OR  "
	    		+ " km.BRANCH_CODE=UPPER(:searchText) OR "
	    		+ " bm.CRCL_NAME=UPPER(:searchText) OR "
	    		+ " ts.RMS_TICKET_NUMBER=UPPER(:searchText)  ",
	    		
	    		countQuery="  select  count(km.kiosk_id) "
	    	    		+ "  FROM  tbl_kiosk_master km  "
	    	    		+ "	   inner JOIN TBL_TERMINAL_STATUS ts ON UPPER(ts.kiosk_id) = (km.kiosk_id) "
	    	    		+ "    left outer join  tbl_branch_master bm    ON bm.branch_code = km.branch_code "+
	    	    		               
	    	              "  LEFT JOIN tbl_user_kiosk_mapping uk "
	    	    		+ "			     ON km.kiosk_id = uk.kiosk_id  LEFT JOIN tbl_user us ON uk.pf_id = us.pf_id "
	    	    		+ "                 where   km.kiosk_id=UPPER(:searchText) OR  "
	    	    		+ "                 km.BRANCH_CODE=UPPER(:searchText) OR "
	    	    		+ "                 bm.CRCL_NAME=UPPER(:searchText) OR "
	    	    		+ "                 ts.RMS_TICKET_NUMBER=UPPER(:searchText)  "
	    		,nativeQuery=true)
		Page<TerminalStatusSearchText> findBySearchText( @Param("searchText") String searchText, Pageable pageable);
	 
	 
	 
	 
	 //
	 
		/*
		 * @Query(value
		 * ="SELECT * FROM TBL_TERMINAL_STATUS where PRINTER_STATUS in('Red','RED','red') "
		 * ,nativeQuery=true)
		 */
	 @Query(value ="select km.kiosk_id,km.KIOSK_SERIAL_NO,bm.branch_code,bm.CRCL_NAME,us.USERNAME,"
		 		+ "          ts.RMS_TICKET_NUMBER,ts.PRINTER_STATUS,ts.CARTRIDGE_STATUS,ts.AGENT_STATUS,ts.APPLICATION_STATUS"
		 		+ "          ,ts.LAST_PRNT_TXN_DTTM,ts.LAST_PM_DTTM FROM TBL_TERMINAL_STATUS ts  " + 
		 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
		 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
		 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
		 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
		 		"where UPPER(ts.PRINTER_STATUS) ='RED' ",nativeQuery=true, countQuery = "SELECT count(km.kiosk_id) FROM TBL_TERMINAL_STATUS ts  " + 
		 		 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
		 		 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
		 		 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  "
		 		 		+ " LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
		 		 		"where UPPER(ts.PRINTER_STATUS) ='RED' " )
	 
	 Page<TerminalStatusSearchText> findByPrinterStatusRedList(Pageable pageable);
	 
		/*
		 * @Query(value
		 * ="SELECT * FROM TBL_TERMINAL_STATUS where PRINTER_STATUS in('Green','GREEN','green') "
		 * ,nativeQuery=true)
		 */
	 @Query(value ="select km.kiosk_id,km.KIOSK_SERIAL_NO,bm.branch_code,bm.CRCL_NAME,us.USERNAME,"
	 		+ "          ts.RMS_TICKET_NUMBER,ts.PRINTER_STATUS,ts.CARTRIDGE_STATUS,ts.AGENT_STATUS,ts.APPLICATION_STATUS"
	 		+ "          ,ts.LAST_PRNT_TXN_DTTM,ts.LAST_PM_DTTM FROM TBL_TERMINAL_STATUS ts  " + 
	 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
	 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
	 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
	 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		"where UPPER(ts.PRINTER_STATUS) ='GREEN' ",nativeQuery=true, countQuery = "SELECT count(km.kiosk_id) FROM TBL_TERMINAL_STATUS ts  " + 
	 		 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
	 		 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
	 		 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  "
	 		 		+ " LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		 		"where UPPER(ts.PRINTER_STATUS) ='GREEN' " )
	 Page<TerminalStatusSearchText> findByPrinterStatusGreenList(Pageable pageable);
	 
		/*
		 * @Query(value
		 * ="SELECT * FROM TBL_TERMINAL_STATUS where PRINTER_STATUS in('Gray','GRAY','gray') "
		 * ,nativeQuery=true)
		 */
	 @Query(value ="select km.kiosk_id,km.KIOSK_SERIAL_NO,bm.branch_code,bm.CRCL_NAME,us.USERNAME,"
	 		+" ts.RMS_TICKET_NUMBER,ts.PRINTER_STATUS,ts.CARTRIDGE_STATUS,ts.AGENT_STATUS,ts.APPLICATION_STATUS, "
	 		+ " ts.LAST_PRNT_TXN_DTTM,ts.LAST_PM_DTTM FROM TBL_TERMINAL_STATUS ts"
	 		+ " inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
	 		" inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
	 		" left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " 
	 		+ " LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		"where UPPER(ts.PRINTER_STATUS) ='GRAY' ",nativeQuery=true, countQuery ="SELECT count(km.kiosk_id) FROM TBL_TERMINAL_STATUS ts  " + 
	 		 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
	 		 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
	 		 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " 
	 		 		+ " LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		 		"where UPPER(ts.PRINTER_STATUS) ='GRAY' ")
	 Page<TerminalStatusSearchText> findByPrinterStatusGrayList(Pageable pageable);
	 
	 

		/*
		 * @Query(value
		 * ="SELECT * FROM TBL_TERMINAL_STATUS where CARTRIDGE_STATUS in('Red','RED','red') "
		 * ,nativeQuery=true)
		 */
	 @Query(value ="select km.kiosk_id,km.KIOSK_SERIAL_NO,bm.branch_code,bm.CRCL_NAME,us.USERNAME,"
		 		+" ts.RMS_TICKET_NUMBER,ts.PRINTER_STATUS,ts.CARTRIDGE_STATUS,ts.AGENT_STATUS,ts.APPLICATION_STATUS, "
		 		+ " ts.LAST_PRNT_TXN_DTTM,ts.LAST_PM_DTTM FROM TBL_TERMINAL_STATUS ts "+
	 		" inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
	 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
	 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " +
	 	    " LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		"where UPPER(ts.CARTRIDGE_STATUS)='RED'",nativeQuery=true, countQuery = "SELECT count(km.kiosk_id) FROM TBL_TERMINAL_STATUS ts  " + 
	 		 		" inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
	 		 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
	 		 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
	 		 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " +
	 		 		"where UPPER(ts.CARTRIDGE_STATUS)='RED'")
Page<TerminalStatusSearchText> findByCartridgeStatusRedList(Pageable pageable);

/*
* @Query(value
* ="SELECT * FROM TBL_TERMINAL_STATUS where CARTRIDGE_STATUS in('Green','GREEN','green') "
* ,nativeQuery=true)
*/
	 @Query(value ="select km.kiosk_id,km.KIOSK_SERIAL_NO,bm.branch_code,bm.CRCL_NAME,us.USERNAME,"
		 		+" ts.RMS_TICKET_NUMBER,ts.PRINTER_STATUS,ts.CARTRIDGE_STATUS,ts.AGENT_STATUS,ts.APPLICATION_STATUS, "
		 		+ " ts.LAST_PRNT_TXN_DTTM,ts.LAST_PM_DTTM FROM TBL_TERMINAL_STATUS ts"+ 
	 		" inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)  " + 
	 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))  " + 
	 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
	 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		"where UPPER(ts.CARTRIDGE_STATUS) ='GREEN' ",nativeQuery=true,countQuery="SELECT count(km.kiosk_id) FROM TBL_TERMINAL_STATUS ts   " + 
	 		 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)  " + 
	 		 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))  " + 
	 		 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
	 		 	    " LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		 		"where UPPER(ts.CARTRIDGE_STATUS) ='GREEN' " )
	  
Page<TerminalStatusSearchText> findByCartridgeStatusGreenList(Pageable pageable);

/* 
* @Query(value
* ="SELECT * FROM TBL_TERMINAL_STATUS where CARTRIDGE_STATUS in('Gray','GRAY','gray') "
* ,nativeQuery=true)
*/
	 @Query(value ="select km.kiosk_id,km.KIOSK_SERIAL_NO,bm.branch_code,bm.CRCL_NAME,us.USERNAME,"
		 		+" ts.RMS_TICKET_NUMBER,ts.PRINTER_STATUS,ts.CARTRIDGE_STATUS,ts.AGENT_STATUS,ts.APPLICATION_STATUS, "
		 		+ " ts.LAST_PRNT_TXN_DTTM,ts.LAST_PM_DTTM FROM TBL_TERMINAL_STATUS ts" + 
	 		" inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)  " + 
	 		" inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))  " + 
	 		" left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
	 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		"where UPPER(ts.CARTRIDGE_STATUS)='GRAY'",nativeQuery=true,countQuery="SELECT count(km.kiosk_id) FROM TBL_TERMINAL_STATUS ts   " + 
	 		 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)  " + 
	 		 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))  " + 
	 		 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
	 		 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		 		"where UPPER(ts.CARTRIDGE_STATUS)='GRAY'")
Page<TerminalStatusSearchText> findByCartridgeStatusGrayList(Pageable pageable);

	 
	 
/*
* @Query(value
* ="SELECT * FROM TBL_TERMINAL_STATUS where AGENT_STATUS in('Red','RED','red') "
* ,nativeQuery=true)
*/
	 @Query(value ="select km.kiosk_id,km.KIOSK_SERIAL_NO,bm.branch_code,bm.CRCL_NAME,us.USERNAME,"
		 		+" ts.RMS_TICKET_NUMBER,ts.PRINTER_STATUS,ts.CARTRIDGE_STATUS,ts.AGENT_STATUS,ts.APPLICATION_STATUS, "
		 		+ " ts.LAST_PRNT_TXN_DTTM,ts.LAST_PM_DTTM FROM TBL_TERMINAL_STATUS ts" + 
	 		" inner join  TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)  " + 
	 		" inner join  TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))  " + 
	 		" left join  TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)   " + 
	 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		"where UPPER(ts.AGENT_STATUS)='RED'",nativeQuery=true,countQuery="SELECT count(km.kiosk_id) FROM  TBL_TERMINAL_STATUS ts   " + 
	 		 		" inner join  TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)  " + 
	 		 		" inner join  TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))  " + 
	 		 		" left join  TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)   " + 
	 		 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		 		" where UPPER(ts.AGENT_STATUS)='RED'" )
	 Page<TerminalStatusSearchText> findByAgentStatusRedList(Pageable pageable);
	 

	 //
	 
		/*
		 * @Query(value
		 * ="SELECT * FROM TBL_TERMINAL_STATUS where AGENT_STATUS in('Green','GREEN','green') "
		 * ,nativeQuery=true)
		 */
	 @Query(value ="select km.kiosk_id,km.KIOSK_SERIAL_NO,bm.branch_code,bm.CRCL_NAME,us.USERNAME,"
		 		+" ts.RMS_TICKET_NUMBER,ts.PRINTER_STATUS,ts.CARTRIDGE_STATUS,ts.AGENT_STATUS,ts.APPLICATION_STATUS, "
		 		+ " ts.LAST_PRNT_TXN_DTTM,ts.LAST_PM_DTTM FROM TBL_TERMINAL_STATUS ts" + 
	 		" inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)   " + 
	 		" inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))   " + 
	 		" left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)   " + 
	 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		" where UPPER(ts.AGENT_STATUS) ='GREEN'",nativeQuery=true,countQuery="SELECT count(km.kiosk_id) FROM TBL_TERMINAL_STATUS ts    " + 
	 		 		" inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)   " + 
	 		 		" inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))   " + 
	 		 		" left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)   " + 
	 		 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		 		" where UPPER(ts.AGENT_STATUS) ='GREEN' ")
	 Page<TerminalStatusSearchText> findByAgentStatusGreenList(Pageable pageable);

	 //
	 
		/*
		 * @Query(value
		 * ="SELECT * FROM TBL_TERMINAL_STATUS where AGENT_STATUS in('Gray','GRAY','gray') "
		 * ,nativeQuery=true)
		 */
	 @Query(value ="select km.kiosk_id,km.KIOSK_SERIAL_NO,bm.branch_code,bm.CRCL_NAME,us.USERNAME,"
		 		+" ts.RMS_TICKET_NUMBER,ts.PRINTER_STATUS,ts.CARTRIDGE_STATUS,ts.AGENT_STATUS,ts.APPLICATION_STATUS, "
		 		+ " ts.LAST_PRNT_TXN_DTTM,ts.LAST_PM_DTTM FROM TBL_TERMINAL_STATUS ts " + 
	 		" inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)   " + 
	 		" inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))   " + 
	 		" left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)    " + 
	 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		" where UPPER(ts.AGENT_STATUS) ='GRAY' ",nativeQuery=true,countQuery="SELECT count(km.kiosk_id) FROM TBL_TERMINAL_STATUS ts    " + 
	 		 		" inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)   " + 
	 		 		" inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))   " + 
	 		 		" left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)    " + 
	 		 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		 		" where UPPER(ts.AGENT_STATUS) ='GRAY'")
	 Page<TerminalStatusSearchText> findByAgentStatusGrayList(Pageable pageable);
	 
	 
	 
	 
	 
		/*
		 * @Query(value
		 * ="SELECT * FROM TBL_TERMINAL_STATUS where APPLICATION_STATUS in('Red','RED','red') "
		 * ,nativeQuery=true)
		 */
	 @Query(value ="select km.kiosk_id,km.KIOSK_SERIAL_NO,bm.branch_code,bm.CRCL_NAME,us.USERNAME,"
		 		+" ts.RMS_TICKET_NUMBER,ts.PRINTER_STATUS,ts.CARTRIDGE_STATUS,ts.AGENT_STATUS,ts.APPLICATION_STATUS, "
		 		+ " ts.LAST_PRNT_TXN_DTTM,ts.LAST_PM_DTTM FROM TBL_TERMINAL_STATUS ts" + 
	 		" inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)    " + 
	 		" inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))    " + 
	 		" left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)    " + 
	 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		" where UPPER(ts.APPLICATION_STATUS)='RED'",nativeQuery=true,countQuery="SELECT count(km.kiosk_id) FROM TBL_TERMINAL_STATUS ts     " + 
	 		 		" inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)    " + 
	 		 		" inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))    " + 
	 		 		" left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)    " + 
	 		 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		 		"where UPPER(ts.APPLICATION_STATUS)='RED'")
	 Page<TerminalStatusSearchText> findByApplicatinStatusRedList(Pageable pageable);
	 
		/*
		 * @Query(value
		 * ="SELECT * FROM TBL_TERMINAL_STATUS where APPLICATION_STATUS in('Green','GREEN','green') "
		 * ,nativeQuery=true)
		 */
	 @Query(value ="select km.kiosk_id,km.KIOSK_SERIAL_NO,bm.branch_code,bm.CRCL_NAME,us.USERNAME,"
		 		+" ts.RMS_TICKET_NUMBER,ts.PRINTER_STATUS,ts.CARTRIDGE_STATUS,ts.AGENT_STATUS,ts.APPLICATION_STATUS, "
		 		+ " ts.LAST_PRNT_TXN_DTTM,ts.LAST_PM_DTTM FROM TBL_TERMINAL_STATUS ts" + 
	 		" inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)    " + 
	 		" inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))    " + 
	 		" left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)    " + 
	 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		" where UPPER(ts.APPLICATION_STATUS)='GREEN'",nativeQuery=true,countQuery="SELECT count(km.kiosk_id) FROM TBL_TERMINAL_STATUS ts     " + 
	 		 		" inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)    " + 
	 		 		" inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))    " + 
	 		 		" left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)    " + 
	 		 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		 		"where UPPER(ts.APPLICATION_STATUS)='GREEN'")
	 Page<TerminalStatusSearchText> findByApplicatinStatusGreenList(Pageable pageable);
	 
		/*
		 * @Query(value
		 * ="SELECT * FROM TBL_TERMINAL_STATUS where APPLICATION_STATUS in('Gray','GRAY','gray') "
		 * ,nativeQuery=true)
		 */
	 @Query(value ="select km.kiosk_id,km.KIOSK_SERIAL_NO,bm.branch_code,bm.CRCL_NAME,us.USERNAME,"
		 		+" ts.RMS_TICKET_NUMBER,ts.PRINTER_STATUS,ts.CARTRIDGE_STATUS,ts.AGENT_STATUS,ts.APPLICATION_STATUS, "
		 		+ " ts.LAST_PRNT_TXN_DTTM,ts.LAST_PM_DTTM FROM TBL_TERMINAL_STATUS ts" + 
	 		" inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)    " + 
	 		" inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))    " + 
	 		" left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)    " + 
	 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		" where UPPER(ts.APPLICATION_STATUS) ='GRAY'",nativeQuery=true, countQuery="SELECT count(km.kiosk_id) FROM TBL_TERMINAL_STATUS ts    " + 
	 		 		" inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)    " + 
	 		 		" inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))    " + 
	 		 		" left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)    " +
	 		 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		 		" where UPPER(ts.APPLICATION_STATUS) ='GRAY' ")
	 Page<TerminalStatusSearchText> findByApplicatinStatusGrayList(Pageable pageable);
	 
	 
	 @Query(value ="select km.kiosk_id,km.KIOSK_SERIAL_NO,bm.branch_code,bm.CRCL_NAME,us.USERNAME,"
		 		+ "          ts.RMS_TICKET_NUMBER,ts.PRINTER_STATUS,ts.CARTRIDGE_STATUS,ts.AGENT_STATUS,ts.APPLICATION_STATUS"
		 		+ "          ,ts.LAST_PRNT_TXN_DTTM,ts.LAST_PM_DTTM FROM TBL_TERMINAL_STATUS ts  " + 
		 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
		 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
		 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
		 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
		 		"where UPPER(ts.PRINTER_STATUS) ='RED' AND   km.kiosk_id=UPPER(:searchText) OR  "
	    		+ " km.BRANCH_CODE=UPPER(:searchText) OR "
	    		+ " bm.CRCL_NAME=UPPER(:searchText) OR "
	    		+ " ts.RMS_TICKET_NUMBER=UPPER(:searchText)  "
		 		,nativeQuery=true, countQuery = "SELECT count(km.kiosk_id) FROM TBL_TERMINAL_STATUS ts  " + 
		 		 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
		 		 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
		 		 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  "
		 		 		+ " LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
		 		 		"where UPPER(ts.PRINTER_STATUS) ='RED' AND   km.kiosk_id=UPPER(:searchText) OR  "
			    		+ " km.BRANCH_CODE=UPPER(:searchText) OR "
			    		+ " bm.CRCL_NAME=UPPER(:searchText) OR "
			    		+ " ts.RMS_TICKET_NUMBER=UPPER(:searchText)  ")

	Page<TerminalStatusSearchText> findByPrinterStatusRedSearchTextList(@Param("searchText") String searchText, Pageable pageable);
	 
	 @Query(value ="select km.kiosk_id,km.KIOSK_SERIAL_NO,bm.branch_code,bm.CRCL_NAME,us.USERNAME,"
		 		+ "          ts.RMS_TICKET_NUMBER,ts.PRINTER_STATUS,ts.CARTRIDGE_STATUS,ts.AGENT_STATUS,ts.APPLICATION_STATUS"
		 		+ "          ,ts.LAST_PRNT_TXN_DTTM,ts.LAST_PM_DTTM FROM TBL_TERMINAL_STATUS ts  " + 
		 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
		 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
		 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
		 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
		 		"where UPPER(ts.PRINTER_STATUS) ='GREEN' AND   km.kiosk_id=UPPER(:searchText) OR  "
	    		+ " km.BRANCH_CODE=UPPER(:searchText) OR "
	    		+ " bm.CRCL_NAME=UPPER(:searchText) OR "
	    		+ " ts.RMS_TICKET_NUMBER=UPPER(:searchText)  "
		 		,nativeQuery=true, countQuery = "SELECT count(km.kiosk_id) FROM TBL_TERMINAL_STATUS ts  " + 
		 		 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
		 		 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
		 		 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  "
		 		 		+ " LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
		 		 		"where UPPER(ts.PRINTER_STATUS) ='GREEN' AND   km.kiosk_id=UPPER(:searchText) OR  "
			    		+ " km.BRANCH_CODE=UPPER(:searchText) OR "
			    		+ " bm.CRCL_NAME=UPPER(:searchText) OR "
			    		+ " ts.RMS_TICKET_NUMBER=UPPER(:searchText)  ")
	Page<TerminalStatusSearchText> findByPrinterStatusGreenSearchTextList(@Param("searchText") String searchText, Pageable pageable);

	 @Query(value ="select km.kiosk_id,km.KIOSK_SERIAL_NO,bm.branch_code,bm.CRCL_NAME,us.USERNAME,"
		 		+ "          ts.RMS_TICKET_NUMBER,ts.PRINTER_STATUS,ts.CARTRIDGE_STATUS,ts.AGENT_STATUS,ts.APPLICATION_STATUS"
		 		+ "          ,ts.LAST_PRNT_TXN_DTTM,ts.LAST_PM_DTTM FROM TBL_TERMINAL_STATUS ts  " + 
		 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
		 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
		 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
		 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
		 		"where UPPER(ts.PRINTER_STATUS) ='GRAY' AND   km.kiosk_id=UPPER(:searchText) OR  "
	    		+ " km.BRANCH_CODE=UPPER(:searchText) OR "
	    		+ " bm.CRCL_NAME=UPPER(:searchText) OR "
	    		+ " ts.RMS_TICKET_NUMBER=UPPER(:searchText)  "
		 		,nativeQuery=true, countQuery = "SELECT count(km.kiosk_id) FROM TBL_TERMINAL_STATUS ts  " + 
		 		 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
		 		 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
		 		 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  "
		 		 		+ " LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
		 		 		"where UPPER(ts.PRINTER_STATUS) ='GRAY' AND   km.kiosk_id=UPPER(:searchText) OR  "
			    		+ " km.BRANCH_CODE=UPPER(:searchText) OR "
			    		+ " bm.CRCL_NAME=UPPER(:searchText) OR "
			    		+ " ts.RMS_TICKET_NUMBER=UPPER(:searchText)  ")
	Page<TerminalStatusSearchText> findByPrinterStatusGraySearchTextList(@Param("searchText") String searchText, Pageable pageable);
	 
	 @Query(value ="select km.kiosk_id,km.KIOSK_SERIAL_NO,bm.branch_code,bm.CRCL_NAME,us.USERNAME,"
		 		+" ts.RMS_TICKET_NUMBER,ts.PRINTER_STATUS,ts.CARTRIDGE_STATUS,ts.AGENT_STATUS,ts.APPLICATION_STATUS, "
		 		+ " ts.LAST_PRNT_TXN_DTTM,ts.LAST_PM_DTTM FROM TBL_TERMINAL_STATUS ts "+
	 		" inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
	 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
	 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " +
	 	    " LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		"where UPPER(ts.CARTRIDGE_STATUS)='RED'	AND   km.kiosk_id=UPPER(:searchText) OR  "
		    		+ " km.BRANCH_CODE=UPPER(:searchText) OR "
		    		+ " bm.CRCL_NAME=UPPER(:searchText) OR "
		    		+ " ts.RMS_TICKET_NUMBER=UPPER(:searchText) "
	 	    ,nativeQuery=true, countQuery = "SELECT count(km.kiosk_id) FROM TBL_TERMINAL_STATUS ts  " + 
	 		 		" inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
	 		 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
	 		 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
	 		 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " +
	 		 		"where UPPER(ts.CARTRIDGE_STATUS)='RED'	AND   km.kiosk_id=UPPER(:searchText) OR  "
				    		+ " km.BRANCH_CODE=UPPER(:searchText) OR "
				    		+ " bm.CRCL_NAME=UPPER(:searchText) OR "
				    		+ " ts.RMS_TICKET_NUMBER=UPPER(:searchText)  ")
	 		 		

	Page<TerminalStatusSearchText> findByCartridgeStatusRedSearchTextList(@Param("searchText") String searchText, Pageable pageable);
	 
	 @Query(value ="select km.kiosk_id,km.KIOSK_SERIAL_NO,bm.branch_code,bm.CRCL_NAME,us.USERNAME,"
		 		+" ts.RMS_TICKET_NUMBER,ts.PRINTER_STATUS,ts.CARTRIDGE_STATUS,ts.AGENT_STATUS,ts.APPLICATION_STATUS, "
		 		+ " ts.LAST_PRNT_TXN_DTTM,ts.LAST_PM_DTTM FROM TBL_TERMINAL_STATUS ts "+
	 		" inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
	 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
	 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " +
	 	    " LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		"where UPPER(ts.CARTRIDGE_STATUS)='GREEN'	AND   km.kiosk_id=UPPER(:searchText) OR  "
		    		+ " km.BRANCH_CODE=UPPER(:searchText) OR "
		    		+ " bm.CRCL_NAME=UPPER(:searchText) OR "
		    		+ " ts.RMS_TICKET_NUMBER=UPPER(:searchText) "
	 	    ,nativeQuery=true, countQuery = "SELECT count(km.kiosk_id) FROM TBL_TERMINAL_STATUS ts  " + 
	 		 		" inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
	 		 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
	 		 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
	 		 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " +
	 		 		"where UPPER(ts.CARTRIDGE_STATUS)='GREEN'	AND   km.kiosk_id=UPPER(:searchText) OR  "
				    		+ " km.BRANCH_CODE=UPPER(:searchText) OR "
				    		+ " bm.CRCL_NAME=UPPER(:searchText) OR "
				    		+ " ts.RMS_TICKET_NUMBER=UPPER(:searchText)  ")

	Page<TerminalStatusSearchText> findByCartridgeStatusGreenSearchTextList(@Param("searchText") String searchText, Pageable pageable);
	 
	 @Query(value ="select km.kiosk_id,km.KIOSK_SERIAL_NO,bm.branch_code,bm.CRCL_NAME,us.USERNAME,"
		 		+" ts.RMS_TICKET_NUMBER,ts.PRINTER_STATUS,ts.CARTRIDGE_STATUS,ts.AGENT_STATUS,ts.APPLICATION_STATUS, "
		 		+ " ts.LAST_PRNT_TXN_DTTM,ts.LAST_PM_DTTM FROM TBL_TERMINAL_STATUS ts "+
	 		" inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
	 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
	 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " +
	 	    " LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		"where UPPER(ts.CARTRIDGE_STATUS)='GRAY'	AND   km.kiosk_id=UPPER(:searchText) OR  "
		    		+ " km.BRANCH_CODE=UPPER(:searchText) OR "
		    		+ " bm.CRCL_NAME=UPPER(:searchText) OR "
		    		+ " ts.RMS_TICKET_NUMBER=UPPER(:searchText) "
	 	    ,nativeQuery=true, countQuery = "SELECT count(km.kiosk_id) FROM TBL_TERMINAL_STATUS ts  " + 
	 		 		" inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
	 		 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
	 		 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
	 		 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " +
	 		 		"where UPPER(ts.CARTRIDGE_STATUS)='GRAY'	AND   km.kiosk_id=UPPER(:searchText) OR  "
				    		+ " km.BRANCH_CODE=UPPER(:searchText) OR "
				    		+ " bm.CRCL_NAME=UPPER(:searchText) OR "
				    		+ " ts.RMS_TICKET_NUMBER=UPPER(:searchText)  ")

	Page<TerminalStatusSearchText> findByCartridgeStatusGraySearchTextList(@Param("searchText") String searchText, Pageable pageable);
	 
	 @Query(value ="select km.kiosk_id,km.KIOSK_SERIAL_NO,bm.branch_code,bm.CRCL_NAME,us.USERNAME,"
		 		+" ts.RMS_TICKET_NUMBER,ts.PRINTER_STATUS,ts.CARTRIDGE_STATUS,ts.AGENT_STATUS,ts.APPLICATION_STATUS, "
		 		+ " ts.LAST_PRNT_TXN_DTTM,ts.LAST_PM_DTTM FROM TBL_TERMINAL_STATUS ts" + 
	 		" inner join  TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)  " + 
	 		" inner join  TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))  " + 
	 		" left join  TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)   " + 
	 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		"where UPPER(ts.AGENT_STATUS)='RED' AND   km.kiosk_id=UPPER(:searchText) OR  "
		    		+ " km.BRANCH_CODE=UPPER(:searchText) OR "
		    		+ " bm.CRCL_NAME=UPPER(:searchText) OR "
		    		+ " ts.RMS_TICKET_NUMBER=UPPER(:searchText)  "
	 		,
	 		nativeQuery=true,countQuery="SELECT count(km.kiosk_id) FROM  TBL_TERMINAL_STATUS ts   " + 
	 		 		" inner join  TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)  " + 
	 		 		" inner join  TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))  " + 
	 		 		" left join  TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)   " + 
	 		 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		 		" where UPPER(ts.AGENT_STATUS)='RED' AND   km.kiosk_id=UPPER(:searchText) OR  "
				    		+ " km.BRANCH_CODE=UPPER(:searchText) OR "
				    		+ " bm.CRCL_NAME=UPPER(:searchText) OR "
				    		+ " ts.RMS_TICKET_NUMBER=UPPER(:searchText)  ")		
			 

	Page<TerminalStatusSearchText> findByAgentStatusRedSearchTextList(@Param("searchText") String searchText, Pageable pageable);
	 
	 
	 @Query(value ="select km.kiosk_id,km.KIOSK_SERIAL_NO,bm.branch_code,bm.CRCL_NAME,us.USERNAME,"
		 		+" ts.RMS_TICKET_NUMBER,ts.PRINTER_STATUS,ts.CARTRIDGE_STATUS,ts.AGENT_STATUS,ts.APPLICATION_STATUS, "
		 		+ " ts.LAST_PRNT_TXN_DTTM,ts.LAST_PM_DTTM FROM TBL_TERMINAL_STATUS ts" + 
	 		" inner join  TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)  " + 
	 		" inner join  TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))  " + 
	 		" left join  TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)   " + 
	 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		"where UPPER(ts.AGENT_STATUS)='GREEN' AND   km.kiosk_id=UPPER(:searchText) OR  "
		    		+ " km.BRANCH_CODE=UPPER(:searchText) OR "
		    		+ " bm.CRCL_NAME=UPPER(:searchText) OR "
		    		+ " ts.RMS_TICKET_NUMBER=UPPER(:searchText)  "
	 		,
	 		nativeQuery=true,countQuery="SELECT count(km.kiosk_id) FROM  TBL_TERMINAL_STATUS ts   " + 
	 		 		" inner join  TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)  " + 
	 		 		" inner join  TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))  " + 
	 		 		" left join  TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)   " + 
	 		 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		 		" where UPPER(ts.AGENT_STATUS)='GREEN' AND   km.kiosk_id=UPPER(:searchText) OR  "
				    		+ " km.BRANCH_CODE=UPPER(:searchText) OR "
				    		+ " bm.CRCL_NAME=UPPER(:searchText) OR "
				    		+ " ts.RMS_TICKET_NUMBER=UPPER(:searchText)  ")	

	Page<TerminalStatusSearchText> findByAgentStatusGreenSearchTextList(@Param("searchText") String searchText, Pageable pageable);
	 
	 
	 @Query(value ="select km.kiosk_id,km.KIOSK_SERIAL_NO,bm.branch_code,bm.CRCL_NAME,us.USERNAME,"
		 		+" ts.RMS_TICKET_NUMBER,ts.PRINTER_STATUS,ts.CARTRIDGE_STATUS,ts.AGENT_STATUS,ts.APPLICATION_STATUS, "
		 		+ " ts.LAST_PRNT_TXN_DTTM,ts.LAST_PM_DTTM FROM TBL_TERMINAL_STATUS ts" + 
	 		" inner join  TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)  " + 
	 		" inner join  TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))  " + 
	 		" left join  TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)   " + 
	 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		"where UPPER(ts.AGENT_STATUS)='GRAY' AND   km.kiosk_id=UPPER(:searchText) OR  "
		    		+ " km.BRANCH_CODE=UPPER(:searchText) OR "
		    		+ " bm.CRCL_NAME=UPPER(:searchText) OR "
		    		+ " ts.RMS_TICKET_NUMBER=UPPER(:searchText)  "
	 		,
	 		nativeQuery=true,countQuery="SELECT count(km.kiosk_id) FROM  TBL_TERMINAL_STATUS ts   " + 
	 		 		" inner join  TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)  " + 
	 		 		" inner join  TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))  " + 
	 		 		" left join  TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)   " + 
	 		 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		 		" where UPPER(ts.AGENT_STATUS)='GRAY' AND   km.kiosk_id=UPPER(:searchText) OR  "
				    		+ " km.BRANCH_CODE=UPPER(:searchText) OR "
				    		+ " bm.CRCL_NAME=UPPER(:searchText) OR "
				    		+ " ts.RMS_TICKET_NUMBER=UPPER(:searchText)  ")	

	Page<TerminalStatusSearchText> findByAgentStatusGraySearchTextList(@Param("searchText") String searchText, Pageable pageable);
	 
	 @Query(value ="select km.kiosk_id,km.KIOSK_SERIAL_NO,bm.branch_code,bm.CRCL_NAME,us.USERNAME,"
		 		+" ts.RMS_TICKET_NUMBER,ts.PRINTER_STATUS,ts.CARTRIDGE_STATUS,ts.AGENT_STATUS,ts.APPLICATION_STATUS, "
		 		+ " ts.LAST_PRNT_TXN_DTTM,ts.LAST_PM_DTTM FROM TBL_TERMINAL_STATUS ts" + 
	 		" inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)    " + 
	 		" inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))    " + 
	 		" left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)    " + 
	 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		" where UPPER(ts.APPLICATION_STATUS)='RED'	AND   km.kiosk_id=UPPER(:searchText) OR  "
		    		+ " km.BRANCH_CODE=UPPER(:searchText) OR "
		    		+ " bm.CRCL_NAME=UPPER(:searchText) OR "
		    		+ " ts.RMS_TICKET_NUMBER=UPPER(:searchText)  "
	 		
	 		,nativeQuery=true,countQuery="SELECT count(km.kiosk_id) FROM TBL_TERMINAL_STATUS ts     " + 
	 		 		" inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)    " + 
	 		 		" inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))    " + 
	 		 		" left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)    " + 
	 		 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		 		"where UPPER(ts.APPLICATION_STATUS)='RED' AND   km.kiosk_id=UPPER(:searchText) OR  "
				    		+ " km.BRANCH_CODE=UPPER(:searchText) OR "
				    		+ " bm.CRCL_NAME=UPPER(:searchText) OR "
				    		+ " ts.RMS_TICKET_NUMBER=UPPER(:searchText)  ")

	Page<TerminalStatusSearchText> findByApplicatinStatusRedSearchTextList(@Param("searchText") String searchText, Pageable pageable);

	 @Query(value ="select km.kiosk_id,km.KIOSK_SERIAL_NO,bm.branch_code,bm.CRCL_NAME,us.USERNAME,"
		 		+" ts.RMS_TICKET_NUMBER,ts.PRINTER_STATUS,ts.CARTRIDGE_STATUS,ts.AGENT_STATUS,ts.APPLICATION_STATUS, "
		 		+ " ts.LAST_PRNT_TXN_DTTM,ts.LAST_PM_DTTM FROM TBL_TERMINAL_STATUS ts" + 
	 		" inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)    " + 
	 		" inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))    " + 
	 		" left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)    " + 
	 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		" where UPPER(ts.APPLICATION_STATUS)='GREEN'	AND   km.kiosk_id=UPPER(:searchText) OR  "
		    		+ " km.BRANCH_CODE=UPPER(:searchText) OR "
		    		+ " bm.CRCL_NAME=UPPER(:searchText) OR "
		    		+ " ts.RMS_TICKET_NUMBER=UPPER(:searchText)  "
	 		
	 		,nativeQuery=true,countQuery="SELECT count(km.kiosk_id) FROM TBL_TERMINAL_STATUS ts     " + 
	 		 		" inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)    " + 
	 		 		" inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))    " + 
	 		 		" left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)    " + 
	 		 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		 		"where UPPER(ts.APPLICATION_STATUS)='GREEN' AND   km.kiosk_id=UPPER(:searchText) OR  "
				    		+ " km.BRANCH_CODE=UPPER(:searchText) OR "
				    		+ " bm.CRCL_NAME=UPPER(:searchText) OR "
				    		+ " ts.RMS_TICKET_NUMBER=UPPER(:searchText)  ")
	Page<TerminalStatusSearchText> findByApplicatinStatusGreenSearchTextList(@Param("searchText") String searchText, Pageable pageable);
	 
	 @Query(value ="select km.kiosk_id,km.KIOSK_SERIAL_NO,bm.branch_code,bm.CRCL_NAME,us.USERNAME,"
		 		+" ts.RMS_TICKET_NUMBER,ts.PRINTER_STATUS,ts.CARTRIDGE_STATUS,ts.AGENT_STATUS,ts.APPLICATION_STATUS, "
		 		+ " ts.LAST_PRNT_TXN_DTTM,ts.LAST_PM_DTTM FROM TBL_TERMINAL_STATUS ts" + 
	 		" inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)    " + 
	 		" inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))    " + 
	 		" left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)    " + 
	 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		" where UPPER(ts.APPLICATION_STATUS)='GRAY'	AND   km.kiosk_id=UPPER(:searchText) OR  "
		    		+ " km.BRANCH_CODE=UPPER(:searchText) OR "
		    		+ " bm.CRCL_NAME=UPPER(:searchText) OR "
		    		+ " ts.RMS_TICKET_NUMBER=UPPER(:searchText)  "
	 		
	 		,nativeQuery=true,countQuery="SELECT count(km.kiosk_id) FROM TBL_TERMINAL_STATUS ts     " + 
	 		 		" inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)    " + 
	 		 		" inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))    " + 
	 		 		" left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)    " + 
	 		 		" LEFT JOIN tbl_user us ON ukm.pf_id = us.pf_id  " + 
	 		 		"where UPPER(ts.APPLICATION_STATUS)='GRAY' AND   km.kiosk_id=UPPER(:searchText) OR  "
				    		+ " km.BRANCH_CODE=UPPER(:searchText) OR "
				    		+ " bm.CRCL_NAME=UPPER(:searchText) OR "
				    		+ " ts.RMS_TICKET_NUMBER=UPPER(:searchText)  ")

	Page<TerminalStatusSearchText> findByApplicatinStatusGraySearchTextList(@Param("searchText") String searchText, Pageable pageable);

	
	 
		@Query(value =" select  km.kiosk_id,km.KIOSK_SERIAL_NO,bm.branch_code,bm.CRCL_NAME,us.USERNAME,"
	    		+ " ts.RMS_TICKET_NUMBER,ts.PRINTER_STATUS,ts.CARTRIDGE_STATUS,ts.AGENT_STATUS,ts.APPLICATION_STATUS "
	    		+ " ,ts.LAST_PRNT_TXN_DTTM,ts.LAST_PM_DTTM "
	    		+ " FROM  tbl_kiosk_master km  "
	    		+ " inner JOIN TBL_TERMINAL_STATUS ts ON UPPER(ts.kiosk_id) = (km.kiosk_id) "
	    		+ " left outer join  tbl_branch_master bm "
	    		+ " ON bm.branch_code = km.branch_code "
	    		
	    		+ " LEFT JOIN tbl_user_kiosk_mapping uk "
	    		+ " ON km.kiosk_id = uk.kiosk_id  LEFT JOIN tbl_user us ON uk.pf_id = us.pf_id "
	    	 ,
	    		
	    		countQuery="  select  count(km.kiosk_id) "
	    	    		+ "  FROM  tbl_kiosk_master km  "
	    	    		+ "	   inner JOIN TBL_TERMINAL_STATUS ts ON UPPER(ts.kiosk_id) = (km.kiosk_id) "
	    	    		+ "    left outer join  tbl_branch_master bm    ON bm.branch_code = km.branch_code "+
	    	    		               
	    	              "  LEFT JOIN tbl_user_kiosk_mapping uk "
	    	    		+ "			     ON km.kiosk_id = uk.kiosk_id  LEFT JOIN tbl_user us ON uk.pf_id = us.pf_id ",nativeQuery=true)
		
		List<TerminalStatusSearchText> getReport();
		


}
