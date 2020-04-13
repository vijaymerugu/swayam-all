package sbi.kiosk.swayam.kioskmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.UserKioskMapping;

@Repository
public interface UserKioskMappingRepository extends CrudRepository<UserKioskMapping, String>{

	
	List<UserKioskMapping> findByPfId(String username);
	
	void deleteByKioskId(String kioskId);
	
	UserKioskMapping findByKioskId(String kioskId);
	
	
}
