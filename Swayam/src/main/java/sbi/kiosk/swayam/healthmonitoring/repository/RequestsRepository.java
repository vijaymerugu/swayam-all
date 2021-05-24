package sbi.kiosk.swayam.healthmonitoring.repository;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sbi.kiosk.swayam.common.entity.Requests;

@Repository
public interface RequestsRepository extends JpaRepository<Requests, Integer>{
	
	Requests findById(int id);
	
	@Query(value="SELECT count(REQ_CAT) FROM TBL_REQUESTS WHERE KIOSK_ID=:kioskId and REQ_CAT not IN ('APRD')",nativeQuery=true)
	int findByKioskId(@Param("kioskId") String kioskId);

	@Modifying
	@Query(value="update TBL_REQUESTS r set r.CASE_ID =:caseId WHERE r.ID = :id ",nativeQuery=true)
	void update(@Param("caseId")  String caseId,@Param("id")  Integer id);
	@Query(value="SELECT count(KIOSK_ID) FROM TBL_REQUESTS WHERE KIOSK_ID=:kioskId and USER_TYPE=:userType ",nativeQuery=true)
	int findByKioskIdAndUserType(@Param("kioskId") String kioskId, @Param("userType") String userType);
	@Modifying
	@Query(value="update TBL_REQUESTS r set r.to_date=TO_DATE(:toDate,  'dd-mm-yy')  WHERE r.ID =:caseId ",nativeQuery=true)
	 void updateToDate(@Param("toDate")  String toDate,@Param("caseId")  int caseId);

	@Modifying
    //@Transactional
	@Query(value="update TBL_REQUESTS r set r.COMMENTS=:comments ,r.REQ_CAT=:reqCategory,r.USER_TYPE=:userType,r.MODIFIED_BY=:modifiedBy,"
			+ " r.MODIFIED_DATE=TO_TIMESTAMP(:modifiedDate,'DD-MM-YY HH:MI:SS.FF9 AM') "
			//+ " r.created_date=TO_TIMESTAMP(:createdDate,'DD-MM-YY HH:MI:SS.FF9 AM')  "
			+ " WHERE r.ID = :id ",nativeQuery=true)

	void updateAndSave(@Param("comments") String comments, @Param("reqCategory") String reqCategory,@Param("userType") 
	String userType, @Param("modifiedBy") String modifiedBy,@Param("modifiedDate") String modifiedDate,@Param("id") Integer id);
	
	@Query(value="SELECT KIOSK_ID FROM TBL_REQUESTS WHERE KIOSK_ID=:kioskId and USER_TYPE=:userType ",nativeQuery=true)
	String findKioskId(@Param("kioskId") String kioskId, @Param("userType") String userType);
	//@Query(value="SELECT TBL_REQ_SEQ.NEXTVAL FROM DUAL",nativeQuery=true)
	//int findRequSeq();
}
