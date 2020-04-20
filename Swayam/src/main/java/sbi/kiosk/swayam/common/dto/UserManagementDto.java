package sbi.kiosk.swayam.common.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.User;

@Data
public class UserManagementDto  {
	
	public static String formatTimestampToString(String dateString) throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(dateString);
		String formattedDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm").format(date);
		return formattedDate;
	}
	UserManagementDto(){
		
	}
	
	public UserManagementDto(User user) {
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
		this.pincode = user.getPincode();
		this.city = user.getCity();
		this.state = user.getState();*/
		this.mailId = user.getMailId();
		try {
			this.createdDate = user.getCreatedDate() !=null ?formatTimestampToString(user.getCreatedDate().toString()):"";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.createdBy = user.getCreatedBy();
		try {
			this.modifiedDate = user.getModifiedDate() !=null ?formatTimestampToString(user.getModifiedDate().toString()):"" ;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.modifiedBy = user.getModifiedBy();;
		this.mobileNo = user.getPhoneNo();	
		this.circle = user.getCircle();
		
	}


	private Integer userId;
	
	private String pfId;	
	
	private String username;
	
	
	private String enabled;
	
	
	private String role;
	
	
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
	
	
	private String createdDate;
	
	
	private String createdBy;
	
	
	private String modifiedDate;
	
	
	private String modifiedBy;
	
	
	private String mobileNo;
	
	
	private String circle;
	


	

}
