package sbi.kiosk.swayam.kioskmanagement.service;

import java.util.List;

import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.dto.UserManagementDto;
import sbi.kiosk.swayam.common.entity.User;

public interface UserService extends IOperations<UserManagementDto>{

	public boolean deActivateUserById(UserDto userBean);
	boolean updateUserById(UserDto userBean);
	public void saveUserMaster(UserDto user);
	List<UserManagementDto> findAllUsers(UserDto userDto);
	List<UserManagementDto> findByUserName(String UserName);
	List<User> fetchAllCmfUserByCircle(String circle);
	public User getUserByPfId(String pfId);
	
	
	int findSACount();
	int findCCCount();
	int findLACount();
	int findCircleCount();
	int findCMSCount();
	int findCMFCount();
    int findCircleCountByRole(String circle);
	
	

}
