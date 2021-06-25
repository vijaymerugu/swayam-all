package sbi.kiosk.swayam.common.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.dto.UserManagementDto;
import sbi.kiosk.swayam.common.entity.User;

@Repository
public interface UserRepositoryPaging extends  PagingAndSortingRepository<User, Long> {

	public Page<User> findByRoleAndEnabled(@Param("role") String role,@Param("enabled") String enabled, Pageable pageable);
	
	public Page<User> findByEnabled(@Param("enabled") String enabled, Pageable pageable);
	
	public Page<User> findByCircleAndEnabledAndRoleNotIn(@Param("circle") String circle,@Param("enabled") String enabled,@Param("role") List<String> role, Pageable pageable);
	
	
	public Page<User> findByCircleAndRoleAndEnabled(@Param("circle") String circle,@Param("role") String role,@Param("enabled") String enabled, Pageable pageable);

	//public Page<UserManagementDto> findByEnabledSearchText(String string, String searchText, PageRequest of);

	
	@Query(value="select * from tbl_user where enabled=:enabled and ( pf_id=(:searchText) or username=(:searchText) "
			  + "or mail_id=(:searchText) or circle=(:searchText) ) 	" , nativeQuery=true,
			  countQuery ="select * from tbl_user where enabled=:enabled and ( pf_id=(:searchText) or username=(:searchText) "
			  + "or mail_id=(:searchText) or circle=(:searchText) ) 	" ) 
	public List<User> findByEnabledSearchText(@Param("enabled") String enabled, @Param("searchText") String searchText, Pageable pageable);
	 
	@Query(value="select * from tbl_user where role=:role and enabled=:enabled and ( pf_id=(:searchText) or username=(:searchText) "
			  + "or mail_id=(:searchText) or circle=(:searchText) ) 	" , nativeQuery=true,
			  countQuery ="select * from tbl_user where role=:role and enabled=:enabled and ( pf_id=(:searchText) or username=(:searchText) "
			  + "or mail_id=(:searchText) or circle=(:searchText) ) 	" ) 
	public List<User> findByRoleAndEnabledSearchText(@Param("role") String role,@Param("enabled") String enabled, @Param("searchText") String searchText, Pageable pageable);
	
	//public Page<UserManagementDto> findByEnabled(@Param("enabled") String enabled, Pageable pageable);
	
	 
}
