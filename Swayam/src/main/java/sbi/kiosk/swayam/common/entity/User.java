package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import sbi.kiosk.swayam.common.dto.UserDto;

@Data
@Entity
@Table(name="TBL_USER")
public class User extends Common {
	
	public User() {
		
	}
	
	public User(UserDto userDto) {	
		this.userId = userDto.getUserId();
		this.pfId = userDto.getPfId();
		this.username =  userDto.getUsername();
		this.enabled = userDto.getEnabled();
		this.role = userDto.getRole();
		this.firstName = userDto.getFirstName();
		this.lastName = userDto.getLastName();
		this.address = userDto.getAddress();
		this.addressline1 = userDto.getAddressline1();
		this.addressline2 = userDto.getAddressline2();
		this.gender = userDto.getGender();
		this.pincode = userDto.getPincode();
		this.city = userDto.getCity();
		this.state = userDto.getState();
		this.mailId =  userDto.getMailId();
		this.mobileNo = userDto.getMobileNo();
		this.circle = userDto.getCircle();
		this.createdBy = userDto.getCreatedBy();
		this.createdDate = userDto.getCreatedDate();
		this.modifiedBy = userDto.getModifiedBy();
		this.modifiedDate = userDto.getModifiedDate();
		this.reportingAuthorityName=userDto.getReportingAuthorityName();
		this.reportingAuthorityEmail=userDto.getReportingAuthorityEmail();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_SEQ")
	@SequenceGenerator(sequenceName = "USER_ID_SEQ", allocationSize = 1, name = "USER_ID_SEQ")	
	@Column(name = "USER_ID", nullable = false)
	private Integer userId;
	
	@Column(name="PF_ID")
	private String pfId;
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="ENABLED")
	private String enabled;
	
	@Column(name="ROLE")	
	private String role;	
	
	
	@Column(name="KIOSK_ID")	
	private String kioskId;
	@Column(name="FIRSTNAME")
	private String firstName;
	
	@Column(name="LASTNAME")
	private String lastName;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="ADDRESSLINE1")
	private String addressline1;
	
	@Column(name="ADDRESSLINE2")
	private String addressline2;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="PINCODE")
	private String pincode;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="STATE")
	private String state;
	
	@Column(name="MAIL_ID")
	private String mailId;	
	
	@Column(name="MOBILENO")
	private String mobileNo;	
	
	@Column(name="CIRCLE")
	private String circle;
	
	@Column(name="REPORTING_AUTHORITY_NAME ")
	private String reportingAuthorityName;
	
	@Column(name="REPORTING_AUTHORITY_EMAIL")
	private String reportingAuthorityEmail;
	

}
