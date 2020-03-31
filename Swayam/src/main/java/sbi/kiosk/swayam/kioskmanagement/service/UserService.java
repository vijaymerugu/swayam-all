package sbi.kiosk.swayam.kioskmanagement.service;

import java.util.List;

import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.dto.UserManagementDto;

public interface UserService extends IOperations<UserManagementDto>{

	public boolean deActivateUserById(UserDto userBean);
	boolean updateUserById(UserDto userBean);
	public void saveUserMaster(UserDto user);
	List<UserManagementDto> findAllUsers(UserDto userDto);
	List<UserManagementDto> findByUserName(String UserName);
	
	
	

}
