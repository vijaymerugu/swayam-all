package sbi.kiosk.swayam.common.dto;

import lombok.Data;

@Data
public class AddUserDto {
	private Integer userId;
	private String pfId;
	private String username;
	private String phoneNo;
	private String emailId;
	private String userType;
	private String role;
	private String reportingAuthorityName;
	private String reportingAuthorityEmail;
	private String checkAction = null;
	
	
	
	
		

}
