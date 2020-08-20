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
   
	@Query(value = "SELECT MANUAL_CALL_LOG_ID_SEQ.NEXTVAL FROM DUAL", nativeQuery = true)
	String findSeq();
	
	   @Modifying
	   @Query(value="UPDATE TBL_MANUAL_CALL_LOG CL SET cl.COMPLAINTID=:complaintId WHERE CL.KIOSK_ID=:kioskId" , nativeQuery=true)
	   public void updateComplaintId(@Param("complaintId")String complaintId,@Param("kioskId") String kioskId);
		
	
	List<ManualTicketCallLog> findByKioskId(String kioskId);
	
	
	//
	
	@Query(value = "select COMPLAINTID from TBL_MANUAL_CALL_LOG cl where cl.kiosk_id=:kiosk_id and cl.CALL_SUBCATEGORY=:subCategory and cl.STATUS=:status ", nativeQuery = true)
	public String findByKisokIdAndCallSubCategoryAndStatus(@Param("kiosk_id") String kisokid,
			@Param("subCategory") String subCategory, @Param("status") String status);

	
	
}
