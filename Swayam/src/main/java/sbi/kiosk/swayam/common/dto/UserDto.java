package sbi.kiosk.swayam.common.dto;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.User;

@Data
public class UserDto {
	
	public UserDto() {
		
	}
	
	public UserDto(User user) {	
		this.userId = user.getUserId();
		this.username =  user.getUsername();
		this.enabled = user.getEnabled();
		this.role = user.getRole();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.address = user.getAddress();
		this.addressline1 = user.getAddressline1();
		this.addressline2 = user.getAddressline2();
		this.gender = user.getGender();
		this.pincode = user.getPincode();
		this.circle = user.getCircle();
	}
	
	
	private Integer userId;
	
	
	private String username;
	
	
	private String enabled;
	
	
	private String role;
	
	
	private String kioskId;
	
	
	private String firstName;
	
	
	private String lastName;
	
	
	private String address;
	
	
	private String addressline1;
	
	
	private String addressline2;
	
	
	private String gender;
	
	
	private String pincode;
	
	
	private String city;
	
	
	private String state;
	
	
	private String mailId;
	
	
	private String createdDate;
	
	
	private String createdBy;
	
	
	private String modifiedDate;
	
	
	private String modifiedBy;
	
	
	private String mobileNo;
	
	
	private String circle;
	
	private String checkAction = null;

}
