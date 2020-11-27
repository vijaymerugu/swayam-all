package sbi.kiosk.swayam.common.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.Circle;

@Repository
public interface BillingCircleRepository extends CrudRepository<Circle, String>{
	
	
	@Query(value = "SELECT DISTINCT CRCL_CODE, CRCL_NAME FROM TBL_CIRCLE  ORDER BY CRCL_NAME ASC",nativeQuery = true)
	List<Circle> findCircles();


}
