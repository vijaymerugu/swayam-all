package sbi.kiosk.swayam.common.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.Supervisor;

@Repository
public interface SupervisorRepository extends CrudRepository<Supervisor, Integer>{

	List<Supervisor> findByPfIdSupervisor(String pfIdSupervisor);
	
	
	@Query(value ="select PF_ID from TBL_SUPERVISOR where PF_ID_SUPERVISOR =:pfIdSupervisor",
			nativeQuery=true)
	Set<String> findPfIdListByPfIdSupervisor(@Param("pfIdSupervisor") String pfIdSupervisor);
	
	Supervisor findByPfId(String pfId);
}
