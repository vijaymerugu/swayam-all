package sbi.kiosk.swayam.billingpayment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.State;

@Repository
public interface BranchStateRepository extends CrudRepository<State, String>{
	
	
	@Query(value = "select DISTINCT u.stat_code, u.stat_desc from TBL_BRANCH_MASTER u where u.CRCL_CODE=:crclCode "
			+ "AND stat_desc IS NOT NULL", 
			nativeQuery = true)
	List<State> findByCirclehCode(@Param("crclCode") String circleCode);


}
