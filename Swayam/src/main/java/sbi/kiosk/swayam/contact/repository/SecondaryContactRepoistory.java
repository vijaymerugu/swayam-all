package sbi.kiosk.swayam.contact.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.SecondaryContacts;

@Repository
public interface SecondaryContactRepoistory extends CrudRepository<SecondaryContacts, Integer> {
	
	
	@Query(value = "SELECT * FROM TBL_SECONDARY_CONTACTS WHERE type = 1 ORDER BY NAME", nativeQuery  = true)
	List<SecondaryContacts> getfirstLevelContact();
	
	@Query(value = "SELECT * FROM TBL_SECONDARY_CONTACTS WHERE type = 2 ORDER BY NAME", nativeQuery = true)
	List<SecondaryContacts> getSecondLevelContact();

}
