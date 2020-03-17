package sbi.kiosk.swayam.common.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, String>{
	
	Role findByRoleId(String roleId);

}
