package sbi.kiosk.swayam.common.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.User;

@Data
public class UserDto {
	
	public static Date formatStringToDate(String dateString) throws ParseException {		
		Date date=new SimpleDateFormat("dd-MMM-yyyy HH:mm").parse(dateString);  
		return date;
	}
	
	public UserDto() {
		
	}
	
	public UserDto(User user) {	
		this.userId = user.getUserId();
		this.pfId = user.getPfId();
		this.username =  user.getUsername();
		this.enabled = user.getEnabled();
		this.role = user.getRole();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		/*this.address = user.getAddress();
		this.addressline1 = user.getAddressline1();
		this.addressline2 = user.getAddressline2();
		this.gender = user.getGender();
		this.pincode = user.getPincode();*/
		this.mailId =  user.getMailId();
		this.phoneNo = user.getPhoneNo();
		this.circle = user.getCircle();
		this.createdBy = user.getCreatedBy();
		this.createdDate = user.getCreatedDate();
		this.modifiedBy = user.getModifiedBy();
		this.modifiedDate = user.getModifiedDate();
		this.reportingAuthorityName=user.getReportingAuthorityName();
		this.reportingAuthorityEmail=user.getReportingAuthorityEmail();
	}
	
	
	
	private Integer userId;
	
	private String pfId;	
	
	private String username;
	
	
	private String enabled;
	
	
	private String role;
	
	
	private String kioskId;
	
	
	private String firstName;
	
	
	private String lastName;
	
	
	/*private String address;
	
	
	private String addressline1;
	
	
	private String addressline2;
	
	
	private String gender;
	
	
	private String pincode;
	
	
	private String city;
	
	
	private String state;
	*/
	
	private String mailId;
	
	
	private Date createdDate;
	
	
	private String createdBy;
	
	
	private Date modifiedDate;
	
	
	private String modifiedBy;
	
	
	private String phoneNo;
	
	
	private String circle;
	
	private String checkAction = null;
	
    private String reportingAuthorityName;
	
	private String reportingAuthorityEmail;

}
     		