package sbi.kiosk.swayam.dataanalyser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.DaUrgentInfo;


@Repository
public interface DaUrgentMessageRepository extends CrudRepository<DaUrgentInfo, String> {
	
	
	@Query(value = "Select info_id , message from tbl_urgent_info  where status='Active' ORDER BY updated_date DESC", nativeQuery = true )
	List<DaUrgentInfo> findMessages();

}
