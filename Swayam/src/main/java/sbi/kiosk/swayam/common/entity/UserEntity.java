package sbi.kiosk.swayam.common.entity;

import lombok.Data;

@Data
public class UserEntity {
	
	public UserEntity(){
		
	}
	
	
	private Long pfId;
	
	private String userName;
	
	private String email;
	
	private String phoneNumber;
}
