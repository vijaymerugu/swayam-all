package sbi.kiosk.swayam.healthmonitoring.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.dto.TerminalStatusDto;
import sbi.kiosk.swayam.common.entity.BranchMaster;
import sbi.kiosk.swayam.common.entity.TerminalStatus;
import sbi.kiosk.swayam.healthmonitoring.model.TerminalStatusModel;

@Repository("terminalStatusRepository")
public interface TerminalStatusRepository extends CrudRepository<TerminalStatus, String>{
 
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

	 
	 
	 @Query(value ="SELECT count(AGENT_STATUS) FROM TBL_TERMINAL_STATUS where AGENT_STATUS=:agentStatus",nativeQuery=true)
		int findByAgentStatusRed(@Param("agentStatus") String agentStatus);
	 
	 @Query(value ="SELECT count(AGENT_STATUS) FROM TBL_TERMINAL_STATUS where AGENT_STATUS=:agentStatus",nativeQuery=true)
		int findByAgentStatusGreen(@Param("agentStatus") String agentStatus);
	 
	 @Query(value ="SELECT count(AGENT_STATUS) FROM TBL_TERMINAL_STATUS where AGENT_STATUS=:agentStatus",nativeQuery=true)
		int findByAgentStatusGrey(@Param("agentStatus") String agentStatus);
	 
	 
	 @Query(value ="SELECT count(CARTRIDGE_STATUS) FROM TBL_TERMINAL_STATUS where CARTRIDGE_STATUS=:agentStatus",nativeQuery=true)
		int findByCartridgeStatusRed(@Param("agentStatus") String agentStatus);
	 
	 @Query(value ="SELECT count(CARTRIDGE_STATUS) FROM TBL_TERMINAL_STATUS where CARTRIDGE_STATUS=:agentStatus",nativeQuery=true)
		int findByCartridgeStatusGreen(@Param("agentStatus") String agentStatus);
	 
	 @Query(value ="SELECT count(CARTRIDGE_STATUS) FROM TBL_TERMINAL_STATUS where CARTRIDGE_STATUS=:agentStatus",nativeQuery=true)
		int findByCartridgeStatusGrey(@Param("agentStatus") String agentStatus);
	 
	 
	 
	 @Query(value ="SELECT count(PRINTER_STATUS) FROM TBL_TERMINAL_STATUS where PRINTER_STATUS=:agentStatus",nativeQuery=true)
		int findByAntivirusStatusRed(@Param("agentStatus") String agentStatus);
	 
	 @Query(value ="SELECT count(PRINTER_STATUS) FROM TBL_TERMINAL_STATUS where PRINTER_STATUS=:agentStatus",nativeQuery=true)
		int findByAntivirusStatusGreen(@Param("agentStatus") String agentStatus);
	 
	 @Query(value ="SELECT count(PRINTER_STATUS) FROM TBL_TERMINAL_STATUS where PRINTER_STATUS=:agentStatus",nativeQuery=true)
		int findByAntivirusStatusGrey(@Param("agentStatus") String agentStatus);
	 
	 
	 
	 @Query(value ="SELECT count(APPLICATION_STATUS) FROM TBL_TERMINAL_STATUS where APPLICATION_STATUS=:agentStatus",nativeQuery=true)
		int findByApplicatinStatusRed(@Param("agentStatus") String agentStatus);
	 
	 @Query(value ="SELECT count(APPLICATION_STATUS) FROM TBL_TERMINAL_STATUS where APPLICATION_STATUS=:agentStatus",nativeQuery=true)
		int findByApplicatinStatusGreen(@Param("agentStatus") String agentStatus);
	 
	 @Query(value ="SELECT count(APPLICATION_STATUS) FROM TBL_TERMINAL_STATUS where APPLICATION_STATUS=:agentStatus",nativeQuery=true)
		int findByApplicatinStatusGrey(@Param("agentStatus") String agentStatus);
	 
	 

}
