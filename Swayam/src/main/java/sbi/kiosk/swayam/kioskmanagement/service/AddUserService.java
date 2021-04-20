package sbi.kiosk.swayam.kioskmanagement.service;

import java.util.List;

import sbi.kiosk.swayam.common.dto.AddUserDto;
import sbi.kiosk.swayam.common.entity.User;

public interface AddUserService {


	public String getByPfId(String pfId);
	String addUser(AddUserDto dto, String role,String circle);
	String updateUser(AddUserDto dto, String role,String circle);
	String addUserBMAndCMF(AddUserDto dto, String role, String circle);
	List<User> getReportingAutMailIdByPfId(String pfId);
}
