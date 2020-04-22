package sbi.kiosk.swayam.healthmonitoring.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.Requests;

@Repository
public interface RequestsRepositoryPaging extends PagingAndSortingRepository<Requests, Integer> {

	Page<Requests> findByUserTypeAndModifiedByIn(@Param("userType") String userType,@Param("modifiedBy") Set<String> modifiedBy,Pageable pageable);
	
	Page<Requests> findByUserTypeAndReqCategory(@Param("userType") String userType,@Param("reqCategory") String reqCategory,Pageable pageable);
	
	Page<Requests> findByCreatedByAndReqCategoryIn(@Param("createdBy") String createdBy,@Param("reqCategory") Set<String> reqCategory,Pageable pageable);
	
	
}
