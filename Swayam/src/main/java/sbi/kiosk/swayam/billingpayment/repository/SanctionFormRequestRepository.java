package sbi.kiosk.swayam.billingpayment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.SanctionRequestEntity;

@Repository
public interface SanctionFormRequestRepository extends CrudRepository<SanctionRequestEntity, Integer>,
PagingAndSortingRepository<SanctionRequestEntity, Integer> {
	
	
	@Query(value ="SELECT * FROM TBL_SANCTION_DTLS where REQUEST_ID=:requestId",nativeQuery=true)
	List<SanctionRequestEntity> findAllByRequestId(@Param("requestId") Integer status);
	
	
	 	

}
