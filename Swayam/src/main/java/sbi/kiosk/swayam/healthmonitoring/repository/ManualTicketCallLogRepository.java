package sbi.kiosk.swayam.healthmonitoring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.ManualTicketCallLog;

@Repository
public interface ManualTicketCallLogRepository  extends CrudRepository<ManualTicketCallLog,String>{
   
	@Query(value = "select MANUAL_CALL_LOG_ID_SEQ.nextval from dual", nativeQuery = true)
	String findSeq();
	
	   @Modifying
	   @Query(value="update tbl_manual_call_log cl set cl.CREATEDDATE=:createdDate, cl.COMPLAINTID=:complaintId where cl.KIOSK_ID=:kioskId" , nativeQuery=true)
	   public void updateComplaintId(@Param("createdDate")String createdDate,@Param("complaintId")String complaintId,@Param("kioskId") String kioskId);
		
	
	List<ManualTicketCallLog> findByKioskId(String kioskId);
	
	
}
