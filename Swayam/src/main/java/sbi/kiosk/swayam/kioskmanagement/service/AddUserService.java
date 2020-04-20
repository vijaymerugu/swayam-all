package sbi.kiosk.swayam.kioskmanagement.service;

import sbi.kiosk.swayam.common.dto.AddUserDto;

public interface AddUserService {

	public String addUser(AddUserDto dto);

	public String getByPfId(String pfId);

	public String saveRole(String role);
}
