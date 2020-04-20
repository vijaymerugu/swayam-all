package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="tbl_manual_call_log")

public class ManualTicketCallLog {
	
	@Id
//	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name = "MANUAL_CALL_LOG_ID", nullable = false)
	private String manual_call_log_id;
	@Column(name = "BRANCH_CODE")
	private String branchCode;
	@Column(name = "KIOSK_ID")
	private String kioskId;
	@Column(name = "VENDOR")
	private String vendor;
	@Column(name = "CIRCLE")
	private String circle;
	@Column(name = "CONTACT_PERSON")
	private String contactPerson;
	@Column(name = "CONTACT_NO")
	private String contactNo;
	@Column(name = "ERROR")
	private String kioskError;
	@Column(name="AGENT_STATUS")
	private String agenetStatus;
	@Column(name="CREATEDDATE")
	private String createdDate;
	@Column(name="COMPLAINTID")
	private String complaintId;
	
	@Column(name="STATUS")
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(String complaintId) {
		this.complaintId = complaintId;
	}
	public String getManual_call_log_id() {
		return manual_call_log_id;
	}
	public void setManual_call_log_id(String manual_call_log_id) {
		this.manual_call_log_id = manual_call_log_id;
	}
	public String getAgenetStatus() {
		return agenetStatus;
	}
	public void setAgenetStatus(String agenetStatus) {
		this.agenetStatus = agenetStatus;
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
	@Override
	public String toString() {
		return "ManualTicketCallLogModel [manual_call_log_id=" + manual_call_log_id + ", branchCode=" + branchCode
				+ ", kioskId=" + kioskId + ", vendor=" + vendor + ", circle=" + circle + ", contactPerson="
				+ contactPerson + ", contactNo=" + contactNo + ", kioskError=" + kioskError + ", agenetStatus="
				+ agenetStatus + "]";
	}
	
}
