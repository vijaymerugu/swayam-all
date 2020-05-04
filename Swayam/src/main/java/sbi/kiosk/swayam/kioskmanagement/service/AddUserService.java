package sbi.kiosk.swayam.kioskmanagement.service;

import sbi.kiosk.swayam.common.dto.AddUserDto;

public interface AddUserService {


	public String getByPfId(String pfId);
	String addUser(AddUserDto dto, String role);
	String updateUser(AddUserDto dto, String role);
}
