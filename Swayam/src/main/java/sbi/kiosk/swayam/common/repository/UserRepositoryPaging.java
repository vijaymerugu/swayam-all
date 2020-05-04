package sbi.kiosk.swayam.common.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import sbi.kiosk.swayam.common.dto.UserManagementDto;
import sbi.kiosk.swayam.common.entity.User;

@Repository
public interface UserRepositoryPaging extends PagingAndSortingRepository<User, Long> {

	public Page<User> findByRoleAndEnabled(@Param("type") String type,@Param("enabled") String enabled, Pageable pageable);
	
	public Page<User> findByEnabled(@Param("enabled") String enabled, Pageable pageable);
	
	public Page<User> findByCircleAndEnabled(@Param("type") String type,@Param("enabled") String enabled, Pageable pageable);

	//public Page<UserManagementDto> findByEnabled(@Param("enabled") String enabled, Pageable pageable);
	

}
