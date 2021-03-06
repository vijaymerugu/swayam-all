package sbi.kiosk.swayam.healthmonitoring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.dto.TerminalStatusDto;
import sbi.kiosk.swayam.common.entity.BranchMaster;
import sbi.kiosk.swayam.common.entity.TerminalStatus;


@Repository("terminalStatusRepository")
public interface TerminalStatusRepository extends PagingAndSortingRepository<TerminalStatus,Integer>{
 
	//@Query(value="from TerminalStatus",nativeQuery=true)
	List<TerminalStatus> findAll();
	
	 @Query(value="select DISTINCT BRANCH_CODE,KIOSK_SERIAL_NO,KIOSK_ID from TBL_KIOSK_MASTER  where KIOSK_ID in(select KIOSK_ID from TBL_TERMINAL_STATUS)",nativeQuery=true)
	 List<TerminalStatus> findByID();
	 
	 
	// @Query(value="select kiosk_id from TBL_TERMINAL_STATUS",nativeQuery=true)
	// String  findKisoskId();
	 
	 @Query(value="select kiosk_id from TBL_TERMINAL_STATUS",nativeQuery=true)
	 List<String>  findKioskId();
	 
	 @Query(value=" SELECT USERNAME FROM TBL_USER WHERE PF_ID IN(SELECT PF_ID FROM TBL_USER_KIOSK_MAPPING WHERE KIOSK_ID=:kioskId)",nativeQuery=true)
	  String findByKisoskId(@Param("kioskId") String kioskId);
	 
	 @Query(value=" SELECT DISTINCT BRANCH_CODE FROM TBL_BRANCH_MASTER WHERE  BRANCH_CODE IN(SELECT BRANCH_CODE FROM TBL_KIOSK_MASTER WHERE KIOSK_ID=:kioskId)",nativeQuery=true)
	  String  findBranchCodeByKioskId(@Param("kioskId") String kioskId);
	 
	 


@Query(value="select BRANCH_NAME from TBL_BRANCH_MASTER  where  BRANCH_CODE=:brachCode",nativeQuery=true)
List<BranchMaster> findAllByBranchCode(@Param("brachCode") String brachCode);

// comments for summary query chages
	 
	/* 
	 @Query(value ="SELECT count(AGENT_STATUS) FROM TBL_TERMINAL_STATUS where AGENT_STATUS=:agentStatus",nativeQuery=true)
		int findByAgentStatusRed(@Param("agentStatus") String agentStatus);
	 
	 @Query(value ="SELECT count(AGENT_STATUS) FROM TBL_TERMINAL_STATUS where AGENT_STATUS=:agentStatus",nativeQuery=true)
		int findByAgentStatusGreen(@Param("agentStatus") String agentStatus);
	 
	 @Query(value ="SELECT count(AGENT_STATUS) FROM TBL_TERMINAL_STATUS where AGENT_STATUS=:agentStatus",nativeQuery=true)
		int findByAgentStatusGrey(@Param("agentStatus") String agentStatus);*/



/*
 * @Query(value
 * ="SELECT count(AGENT_STATUS) FROM TBL_TERMINAL_STATUS where AGENT_STATUS in('Red','RED','red') "
 * ,nativeQuery=true)
 */
@Query(value ="SELECT count(ts.AGENT_STATUS) FROM TBL_TERMINAL_STATUS ts " + 
		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)" + 
		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))" + 
		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID) " + 
		"where UPPER(ts.AGENT_STATUS)='RED' ",nativeQuery=true)
	int findByAgentStatusRed(@Param("agentStatus") String agentStatus);

	/*
	 * @Query(value
	 * ="SELECT count(AGENT_STATUS) FROM TBL_TERMINAL_STATUS where AGENT_STATUS in('Green','GREEN','green') "
	 * ,nativeQuery=true)
	 */
@Query(value ="SELECT count(ts.AGENT_STATUS) FROM TBL_TERMINAL_STATUS ts " + 
		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
		"where UPPER(ts.AGENT_STATUS)='GREEN' ",nativeQuery=true)
	int findByAgentStatusGreen(@Param("agentStatus") String agentStatus);

	/*
	 * @Query(value
	 * ="SELECT count(AGENT_STATUS) FROM TBL_TERMINAL_STATUS where AGENT_STATUS in('Gray','GRAY','gray') "
	 * ,nativeQuery=true)
	 */
@Query(value ="SELECT count(ts.AGENT_STATUS) FROM TBL_TERMINAL_STATUS ts  " + 
		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
		"where UPPER(ts.AGENT_STATUS)='GRAY' ",nativeQuery=true)
	int findByAgentStatusGrey(@Param("agentStatus") String agentStatus);
	 
	 
	/* @Query(value ="SELECT count(CARTRIDGE_STATUS) FROM TBL_TERMINAL_STATUS where CARTRIDGE_STATUS=:agentStatus",nativeQuery=true)
		int findByCartridgeStatusRed(@Param("agentStatus") String agentStatus);
	 
	 @Query(value ="SELECT count(CARTRIDGE_STATUS) FROM TBL_TERMINAL_STATUS where CARTRIDGE_STATUS=:agentStatus",nativeQuery=true)
		int findByCartridgeStatusGreen(@Param("agentStatus") String agentStatus);
	 
	 @Query(value ="SELECT count(CARTRIDGE_STATUS) FROM TBL_TERMINAL_STATUS where CARTRIDGE_STATUS=:agentStatus",nativeQuery=true)
		int findByCartridgeStatusGrey(@Param("agentStatus") String agentStatus);*/


	/*
	 * @Query(value
	 * ="SELECT count(CARTRIDGE_STATUS) FROM TBL_TERMINAL_STATUS where CARTRIDGE_STATUS in('Red','RED','red') "
	 * ,nativeQuery=true)
	 */
@Query(value ="SELECT count(ts.CARTRIDGE_STATUS) FROM TBL_TERMINAL_STATUS ts  " + 
		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
		"where UPPER(ts.CARTRIDGE_STATUS)='RED' ",nativeQuery=true)
int findByCartridgeStatusRed(@Param("agentStatus") String agentStatus);

/*
 * @Query(value
 * ="SELECT count(CARTRIDGE_STATUS) FROM TBL_TERMINAL_STATUS where CARTRIDGE_STATUS in('Green','GREEN','green') "
 * ,nativeQuery=true)
 */
@Query(value ="SELECT count(ts.CARTRIDGE_STATUS) FROM TBL_TERMINAL_STATUS ts  " + 
		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
		"where UPPER(ts.CARTRIDGE_STATUS)='GREEN' ",nativeQuery=true)
int findByCartridgeStatusGreen(@Param("agentStatus") String agentStatus);

/*
 * @Query(value
 * ="SELECT count(CARTRIDGE_STATUS) FROM TBL_TERMINAL_STATUS where CARTRIDGE_STATUS in('Gray','GRAY','gray') "
 * ,nativeQuery=true)
 */
@Query(value ="SELECT count(ts.CARTRIDGE_STATUS) FROM TBL_TERMINAL_STATUS ts  " + 
		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
		"where UPPER(ts.CARTRIDGE_STATUS)='GRAY' ",nativeQuery=true)
int findByCartridgeStatusGrey(@Param("agentStatus") String agentStatus);
	 
	 
	 
	/* @Query(value ="SELECT count(PRINTER_STATUS) FROM TBL_TERMINAL_STATUS where PRINTER_STATUS=:agentStatus",nativeQuery=true)
		int findByAntivirusStatusRed(@Param("agentStatus") String agentStatus);
	 
	 @Query(value ="SELECT count(PRINTER_STATUS) FROM TBL_TERMINAL_STATUS where PRINTER_STATUS=:agentStatus",nativeQuery=true)
		int findByAntivirusStatusGreen(@Param("agentStatus") String agentStatus);
	 
	 @Query(value ="SELECT count(PRINTER_STATUS) FROM TBL_TERMINAL_STATUS where PRINTER_STATUS=:agentStatus",nativeQuery=true)
		int findByAntivirusStatusGrey(@Param("agentStatus") String agentStatus);*/
	 

/*
 * @Query(value
 * ="SELECT count(PRINTER_STATUS) FROM TBL_TERMINAL_STATUS where PRINTER_STATUS in('Red','RED','red') "
 * ,nativeQuery=true)
 */
@Query(value ="SELECT count(ts.PRINTER_STATUS) FROM TBL_TERMINAL_STATUS ts  " + 
		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
		"where UPPER(ts.PRINTER_STATUS)='RED' ",nativeQuery=true)
		int findByAntivirusStatusRed(@Param("agentStatus") String agentStatus);
	 
		/*
		 * @Query(value
		 * ="SELECT count(PRINTER_STATUS) FROM TBL_TERMINAL_STATUS where PRINTER_STATUS in('Green','GREEN','GREEN') "
		 * ,nativeQuery=true)
		 */
@Query(value ="SELECT count(ts.PRINTER_STATUS) FROM TBL_TERMINAL_STATUS ts  " + 
		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
		"where UPPER(ts.PRINTER_STATUS) ='GREEN' ",nativeQuery=true)
		int findByAntivirusStatusGreen(@Param("agentStatus") String agentStatus);
	 
		/*
		 * @Query(value
		 * ="SELECT count(PRINTER_STATUS) FROM TBL_TERMINAL_STATUS where PRINTER_STATUS in('Grey','GREY','grey')"
		 * ,nativeQuery=true)
		 */
@Query(value ="SELECT count(ts.PRINTER_STATUS) FROM TBL_TERMINAL_STATUS ts  " + 
		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
		"where UPPER(ts.PRINTER_STATUS) ='GRAY' ",nativeQuery=true)
		int findByAntivirusStatusGrey(@Param("agentStatus") String agentStatus);
	 
	 
	 
	/* @Query(value ="SELECT count(APPLICATION_STATUS) FROM TBL_TERMINAL_STATUS where APPLICATION_STATUS=:agentStatus",nativeQuery=true)
		int findByApplicatinStatusRed(@Param("agentStatus") String agentStatus);
	 
	 @Query(value ="SELECT count(APPLICATION_STATUS) FROM TBL_TERMINAL_STATUS where APPLICATION_STATUS=:agentStatus",nativeQuery=true)
		int findByApplicatinStatusGreen(@Param("agentStatus") String agentStatus);
	 
	 @Query(value ="SELECT count(APPLICATION_STATUS) FROM TBL_TERMINAL_STATUS where APPLICATION_STATUS=:agentStatus",nativeQuery=true)
		int findByApplicatinStatusGrey(@Param("agentStatus") String agentStatus);
*/	 
	 
		/*
		 * @Query(value
		 * ="SELECT count(APPLICATION_STATUS) FROM TBL_TERMINAL_STATUS where APPLICATION_STATUS in('Red','RED','red') "
		 * ,nativeQuery=true)
		 */
@Query(value ="SELECT count(ts.APPLICATION_STATUS) FROM TBL_TERMINAL_STATUS ts  " + 
		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
		"where UPPER(ts.APPLICATION_STATUS)='RED'  ",nativeQuery=true)
		int findByApplicatinStatusRed(@Param("agentStatus") String agentStatus);
	 
		/*
		 * @Query(value
		 * ="SELECT count(APPLICATION_STATUS) FROM TBL_TERMINAL_STATUS where APPLICATION_STATUS in('Green','GREEN','green') "
		 * ,nativeQuery=true)
		 */
@Query(value ="SELECT count(ts.APPLICATION_STATUS) FROM TBL_TERMINAL_STATUS ts  " + 
		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
		"where UPPER(ts.APPLICATION_STATUS)='GREEN'  ",nativeQuery=true)
		int findByApplicatinStatusGreen(@Param("agentStatus") String agentStatus);
	 
		/*
		 * @Query(value
		 * ="SELECT count(APPLICATION_STATUS) FROM TBL_TERMINAL_STATUS where APPLICATION_STATUS in('Gray','GRAY','gray') "
		 * ,nativeQuery=true)
		 */
@Query(value ="SELECT count(ts.APPLICATION_STATUS) FROM TBL_TERMINAL_STATUS ts  " + 
		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
		"where UPPER(ts.APPLICATION_STATUS)='GRAY' ",nativeQuery=true)
		int findByApplicatinStatusGray(@Param("agentStatus") String agentStatus);
	 
	 
/*	 @Query(value ="SELECT count(PRINTER_STATUS) FROM TBL_TERMINAL_STATUS where PRINTER_STATUS=:agentStatus",nativeQuery=true)
		int findByPrinterStatusRed(@Param("agentStatus") String agentStatus);
	 
	 @Query(value ="SELECT count(PRINTER_STATUS) FROM TBL_TERMINAL_STATUS where PRINTER_STATUS=:agentStatus",nativeQuery=true)
		int findByPrinterStatusGreen(@Param("agentStatus") String agentStatus);
	 
	 @Query(value ="SELECT count(PRINTER_STATUS) FROM TBL_TERMINAL_STATUS where PRINTER_STATUS=:agentStatus",nativeQuery=true)
		int findByPrinterStatusGrey(@Param("agentStatus") String agentStatus);*/
	 
		/*
		 * @Query(value
		 * ="SELECT count(PRINTER_STATUS) FROM TBL_TERMINAL_STATUS where PRINTER_STATUS in('Red','RED','red') "
		 * ,nativeQuery=true)
		 */
	 @Query(value ="SELECT count(ts.PRINTER_STATUS) FROM TBL_TERMINAL_STATUS ts  " + 
	 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
	 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
	 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
	 		"where UPPER(ts.PRINTER_STATUS)='RED' ",nativeQuery=true)	
	 int findByPrinterStatusRed(@Param("agentStatus") String agentStatus);
	 
		/*
		 * @Query(value
		 * ="SELECT count(PRINTER_STATUS) FROM TBL_TERMINAL_STATUS where PRINTER_STATUS in('Green','GREEN','green') "
		 * ,nativeQuery=true)
		 */
	 @Query(value ="SELECT count(ts.PRINTER_STATUS) FROM TBL_TERMINAL_STATUS ts  " + 
	 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
	 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
	 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
	 		"where UPPER(ts.PRINTER_STATUS) ='GREEN'  ",nativeQuery=true)
		int findByPrinterStatusGreen(@Param("agentStatus") String agentStatus);
	 
		/*
		 * @Query(value
		 * ="SELECT count(PRINTER_STATUS) FROM TBL_TERMINAL_STATUS where PRINTER_STATUS in('Gray','GRAY','gray') "
		 * ,nativeQuery=true)
		 */
	 @Query(value ="SELECT count(ts.PRINTER_STATUS) FROM TBL_TERMINAL_STATUS ts  " + 
	 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
	 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
	 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
	 		"where UPPER(ts.PRINTER_STATUS) ='GRAY' ",nativeQuery=true)
	 
		int findByPrinterStatusGrey(@Param("agentStatus") String agentStatus);
	 
	 
	 
	 

	 //
	 
		/*
		 * @Query(value
		 * ="SELECT * FROM TBL_TERMINAL_STATUS where PRINTER_STATUS in('Red','RED','red') "
		 * ,nativeQuery=true)
		 */
	 @Query(value ="SELECT ts.* FROM TBL_TERMINAL_STATUS ts  " + 
	 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
	 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
	 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
	 		"where UPPER(ts.PRINTER_STATUS)='RED' ",nativeQuery=true,
	 		countQuery = "SELECT ts.* FROM TBL_TERMINAL_STATUS ts  " + 
	 		 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
	 		 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
	 		 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
	 		 		"where UPPER(ts.PRINTER_STATUS)='RED' ")
	 
	 Page<TerminalStatus> findByPrinterStatusRedList(Pageable pageable);
	 
		/*
		 * @Query(value
		 * ="SELECT * FROM TBL_TERMINAL_STATUS where PRINTER_STATUS in('Green','GREEN','green') "
		 * ,nativeQuery=true)
		 */
	 @Query(value ="SELECT ts.* FROM TBL_TERMINAL_STATUS ts  " + 
	 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
	 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
	 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
	 		"where UPPER(ts.PRINTER_STATUS) ='GREEN' ",nativeQuery=true, countQuery = "SELECT ts.* FROM TBL_TERMINAL_STATUS ts  " + 
	 		 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
	 		 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
	 		 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
	 		 		"where UPPER(ts.PRINTER_STATUS) ='GREEN' " )
	 Page<TerminalStatus> findByPrinterStatusGreenList(Pageable pageable);
	 
		/*
		 * @Query(value
		 * ="SELECT * FROM TBL_TERMINAL_STATUS where PRINTER_STATUS in('Gray','GRAY','gray') "
		 * ,nativeQuery=true)
		 */
	 @Query(value ="SELECT ts.* FROM TBL_TERMINAL_STATUS ts  " + 
	 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
	 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
	 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
	 		"where UPPER(ts.PRINTER_STATUS) ='GRAY' ",nativeQuery=true, countQuery ="SELECT ts.* FROM TBL_TERMINAL_STATUS ts  " + 
	 		 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
	 		 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
	 		 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
	 		 		"where UPPER(ts.PRINTER_STATUS) ='GRAY' ")
	 Page<TerminalStatus> findByPrinterStatusGrayList(Pageable pageable);
	 
	 

		/*
		 * @Query(value
		 * ="SELECT * FROM TBL_TERMINAL_STATUS where CARTRIDGE_STATUS in('Red','RED','red') "
		 * ,nativeQuery=true)
		 */
	 @Query(value ="SELECT ts.* FROM TBL_TERMINAL_STATUS ts  " + 
	 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
	 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
	 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
	 		"where UPPER(ts.CARTRIDGE_STATUS)='RED'",nativeQuery=true, countQuery = "SELECT ts.* FROM TBL_TERMINAL_STATUS ts  " + 
	 		 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
	 		 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0')) " + 
	 		 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
	 		 		"where UPPER(ts.CARTRIDGE_STATUS)='RED'")
Page<TerminalStatus> findByCartridgeStatusRedList(Pageable pageable);

/*
 * @Query(value
 * ="SELECT * FROM TBL_TERMINAL_STATUS where CARTRIDGE_STATUS in('Green','GREEN','green') "
 * ,nativeQuery=true)
 */
	 @Query(value ="SELECT ts.* FROM TBL_TERMINAL_STATUS ts   " + 
	 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)  " + 
	 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))  " + 
	 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
	 		"where UPPER(ts.CARTRIDGE_STATUS) ='GREEN' ",nativeQuery=true,countQuery="SELECT ts.* FROM TBL_TERMINAL_STATUS ts   " + 
	 		 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)  " + 
	 		 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))  " + 
	 		 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
	 		 		"where UPPER(ts.CARTRIDGE_STATUS) ='GREEN' " )
	  
Page<TerminalStatus> findByCartridgeStatusGreenList(Pageable pageable);

/* 
 * @Query(value
 * ="SELECT * FROM TBL_TERMINAL_STATUS where CARTRIDGE_STATUS in('Gray','GRAY','gray') "
 * ,nativeQuery=true)
 */
	 @Query(value ="SELECT ts.* FROM TBL_TERMINAL_STATUS ts   " + 
	 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)  " + 
	 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))  " + 
	 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
	 		"where UPPER(ts.CARTRIDGE_STATUS)='GRAY'",nativeQuery=true,countQuery="SELECT ts.* FROM TBL_TERMINAL_STATUS ts   " + 
	 		 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)  " + 
	 		 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))  " + 
	 		 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
	 		 		"where UPPER(ts.CARTRIDGE_STATUS)='GRAY'")
Page<TerminalStatus> findByCartridgeStatusGrayList(Pageable pageable);

	 
	 
/*
 * @Query(value
 * ="SELECT * FROM TBL_TERMINAL_STATUS where AGENT_STATUS in('Red','RED','red') "
 * ,nativeQuery=true)
 */
	 @Query(value ="SELECT ts.* FROM  TBL_TERMINAL_STATUS ts   " + 
	 		"inner join  TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)  " + 
	 		"inner join  TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))  " + 
	 		"left join  TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)   " + 
	 		"where UPPER(ts.AGENT_STATUS)='RED'",nativeQuery=true,countQuery="SELECT ts.* FROM  TBL_TERMINAL_STATUS ts   " + 
	 		 		"inner join  TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)  " + 
	 		 		"inner join  TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))  " + 
	 		 		"left join  TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)   " + 
	 		 		"where UPPER(ts.AGENT_STATUS)='RED'" )
	 Page<TerminalStatus> findByAgentStatusRedList(Pageable pageable);
	 

	 //
	 
		/*
		 * @Query(value
		 * ="SELECT * FROM TBL_TERMINAL_STATUS where AGENT_STATUS in('Green','GREEN','green') "
		 * ,nativeQuery=true)
		 */
	 @Query(value ="SELECT ts.* FROM TBL_TERMINAL_STATUS ts    " + 
	 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)   " + 
	 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))   " + 
	 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)   " + 
	 		"where UPPER(ts.AGENT_STATUS) ='GREEN'",nativeQuery=true,countQuery="SELECT ts.* FROM TBL_TERMINAL_STATUS ts    " + 
	 		 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)   " + 
	 		 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))   " + 
	 		 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)   " + 
	 		 		"where UPPER(ts.AGENT_STATUS) ='GREEN'")
	 Page<TerminalStatus> findByAgentStatusGreenList(Pageable pageable);

	 //
	 
		/*
		 * @Query(value
		 * ="SELECT * FROM TBL_TERMINAL_STATUS where AGENT_STATUS in('Gray','GRAY','gray') "
		 * ,nativeQuery=true)
		 */
	 @Query(value ="SELECT ts.* FROM TBL_TERMINAL_STATUS ts    " + 
	 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)   " + 
	 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))   " + 
	 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)    " + 
	 		"where UPPER(ts.AGENT_STATUS) ='GRAY'",nativeQuery=true,countQuery="SELECT ts.* FROM TBL_TERMINAL_STATUS ts    " + 
	 		 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)   " + 
	 		 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))   " + 
	 		 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)    " + 
	 		 		"where UPPER(ts.AGENT_STATUS) ='GRAY'")
	 Page<TerminalStatus> findByAgentStatusGrayList(Pageable pageable);
	 
	 
	 
	 
	 
		/*
		 * @Query(value
		 * ="SELECT * FROM TBL_TERMINAL_STATUS where APPLICATION_STATUS in('Red','RED','red') "
		 * ,nativeQuery=true)
		 */
	 @Query(value ="SELECT ts.* FROM TBL_TERMINAL_STATUS ts     " + 
	 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)    " + 
	 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))    " + 
	 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)    " + 
	 		"where UPPER(ts.APPLICATION_STATUS)='RED'",nativeQuery=true,countQuery="SELECT ts.* FROM TBL_TERMINAL_STATUS ts     " + 
	 		 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)    " + 
	 		 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))    " + 
	 		 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)    " + 
	 		 		"where UPPER(ts.APPLICATION_STATUS)='RED'")
	 Page<TerminalStatus> findByApplicatinStatusRedList(Pageable pageable);
	 
		/*
		 * @Query(value
		 * ="SELECT * FROM TBL_TERMINAL_STATUS where APPLICATION_STATUS in('Green','GREEN','green') "
		 * ,nativeQuery=true)
		 */
	 @Query(value ="SELECT ts.* FROM TBL_TERMINAL_STATUS ts  " + 
	 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID) " + 
	 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))  " + 
	 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
	 		"where UPPER(ts.APPLICATION_STATUS) ='GREEN'",nativeQuery=true, countQuery = "SELECT ts.* FROM TBL_TERMINAL_STATUS ts     " + 
	 		 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)  " + 
	 		 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))  " + 
	 		 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)  " + 
	 		 		"where UPPER(ts.APPLICATION_STATUS) ='GREEN'")
	 Page<TerminalStatus> findByApplicatinStatusGreenList(Pageable pageable);
	 
		/*
		 * @Query(value
		 * ="SELECT * FROM TBL_TERMINAL_STATUS where APPLICATION_STATUS in('Gray','GRAY','gray') "
		 * ,nativeQuery=true)
		 */
	 @Query(value ="SELECT ts.* FROM TBL_TERMINAL_STATUS ts    " + 
	 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)    " + 
	 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))    " + 
	 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)    " + 
	 		"where UPPER(ts.APPLICATION_STATUS) ='GRAY'",nativeQuery=true, countQuery="SELECT ts.* FROM TBL_TERMINAL_STATUS ts    " + 
	 		 		"inner join TBL_KIOSK_MASTER km on UPPER(km.KIOSK_ID)=UPPER(ts.KIOSK_ID)    " + 
	 		 		"inner join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=TRIM(LPAD(ts.BR_CODE,5,'0'))    " + 
	 		 		"left join TBL_USER_KIOSK_MAPPING UKM on UPPER(km.KIOSK_ID)=upper(UKM.KIOSK_ID)    " + 
	 		 		"where UPPER(ts.APPLICATION_STATUS) ='GRAY'")
	 Page<TerminalStatus> findByApplicatinStatusGrayList(Pageable pageable);

	 
	 
	 
	 
	
}
