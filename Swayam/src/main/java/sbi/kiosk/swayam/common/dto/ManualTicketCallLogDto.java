package sbi.kiosk.swayam.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

@JsonIgnoreType 
public class ManualTicketCallLogDto {
	
	
	private String branchCode;
	private String kioskId;
	private String vendor;
	private String circle;
	private String contactPerson;
	private String contactNo;
	private String kioskError;
	private String comment;
	private String status="P";
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getKioskId() {
		return kioskId;
	}
	public void setKioskId(String kioskId) {
		this.kioskId = kioskId;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getCircle() {
		return circle;
	}
	public void setCircle(String circle) {
		this.circle = circle;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getKioskError() {
		return kioskError;
	}
	public void setKioskError(String kioskError) {
		this.kioskError = kioskError;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
