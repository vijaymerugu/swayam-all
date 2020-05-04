package sbi.kiosk.swayam.healthmonitoring.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.Requests;

@Repository
public interface RequestsRepository extends CrudRepository<Requests, Integer>{
	
	Requests findById(int id);
	
	@Query(value="SELECT count(REQ_CAT) FROM TBL_REQUESTS WHERE KIOSK_ID=:kioskId and REQ_CAT not IN ('APRD')",nativeQuery=true)
	int findByKioskId(@Param("kioskId") String kioskId);

	@Modifying
	@Query(value="update TBL_REQUESTS r set r.CASE_ID =:caseId WHERE r.ID = :id ",nativeQuery=true)
	void update(@Param("caseId")  String caseId,@Param("id")  Integer id);
	
	//@Query(value="SELECT TBL_REQ_SEQ.NEXTVAL FROM DUAL",nativeQuery=true)
	//int findRequSeq();
}
