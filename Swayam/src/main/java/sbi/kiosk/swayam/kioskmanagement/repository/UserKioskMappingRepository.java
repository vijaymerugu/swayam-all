package sbi.kiosk.swayam.kioskmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.UserKioskMapping;

@Repository
public interface UserKioskMappingRepository extends CrudRepository<UserKioskMapping, String>{

	
	List<UserKioskMapping> findByPfId(String username);
	
	void deleteByKioskId(String kioskId);
	
	UserKioskMapping findByKioskId(String kioskId);
	
	@Query(value="SELECT COUNT(*) FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID =:pfId",nativeQuery=true)
	int findKiosksCountByPfId(@Param("pfId") String pfId);
	
	@Query(value="SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE KIOSK_ID =:kioskId and  PF_ID =:pfId",nativeQuery=true)
    String findByKioskid(@Param("kioskId") String kioskId,@Param("pfId") String pfId);
	
	
}
