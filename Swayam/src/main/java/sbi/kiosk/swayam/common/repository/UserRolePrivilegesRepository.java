package sbi.kiosk.swayam.common.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.UserRolePrivileges;

@Repository
public interface UserRolePrivilegesRepository extends CrudRepository<UserRolePrivileges, Integer>{

	
	List<UserRolePrivileges> findAll();
}
