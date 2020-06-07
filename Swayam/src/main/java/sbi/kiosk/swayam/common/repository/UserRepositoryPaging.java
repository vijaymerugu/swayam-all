package sbi.kiosk.swayam.common.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.User;

@Repository
public interface UserRepositoryPaging extends PagingAndSortingRepository<User, Long> {

	public Page<User> findByRoleAndEnabled(@Param("role") String role,@Param("enabled") String enabled, Pageable pageable);
	
	public Page<User> findByEnabled(@Param("enabled") String enabled, Pageable pageable);
	
	public Page<User> findByCircleAndEnabledAndRoleNotIn(@Param("circle") String circle,@Param("enabled") String enabled,@Param("role") List<String> role, Pageable pageable);
	
	
	public Page<User> findByCircleAndRoleAndEnabled(@Param("circle") String circle,@Param("role") String role,@Param("enabled") String enabled, Pageable pageable);

	//public Page<UserManagementDto> findByEnabled(@Param("enabled") String enabled, Pageable pageable);
	

}
