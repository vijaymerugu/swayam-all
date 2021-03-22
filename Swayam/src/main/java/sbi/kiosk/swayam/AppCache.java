package sbi.kiosk.swayam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;

import sbi.kiosk.swayam.common.entity.UserRolePrivileges;
import sbi.kiosk.swayam.common.repository.UserRolePrivilegesRepository;

@Component
//@EnableCaching
public class AppCache {

	@Autowired
	UserRolePrivilegesRepository userRolePrivilege;
	
	//@Cacheable("userPrivilegeVal")
	  public List<UserRolePrivileges> getAllUserRolePrivileges() {
	   
	    return userRolePrivilege.findAll();
	  }
}
