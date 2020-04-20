package sbi.kiosk.swayam.common.dto;

public class AddUserDto {
	
	private String pfId;
	private String userName;
	private String phoneNumber;
	private String emailId;
	private String userType;
	private String role;
	private String reportingAuthorityName;
	private String reportingAuthorityEmail;
	
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPfId() {
		return pfId;
	}
	public void setPfId(String pfId) {
		this.pfId = pfId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getReportingAuthorityName() {
		return reportingAuthorityName;
	}
	public void setReportingAuthorityName(String reportingAuthorityName) {
		this.reportingAuthorityName = reportingAuthorityName;
	}
	public String getReportingAuthorityEmail() {
		return reportingAuthorityEmail;
	}
	public void setReportingAuthorityEmail(String reportingAuthorityEmail) {
		this.reportingAuthorityEmail = reportingAuthorityEmail;
	}
		

}
