package com.sbi.cron.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "TBL_SWAYAM_TXN_HOURLY")
public class SwayamTranactionhourEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "UNIQUE_REFERENCE_NO")
	private String uniqueReferenceNo;
	@Column(name = "REQUEST_DATE_TIME")
	private String requestDateTime;
	@Column(name = "REQUESTING_BRANCH")
	private String requestingBranch;
	@Column(name = "KIOSK_ID")
	private String kioskId;
	@Column(name = "RESPONSE_DATE_TIME")
	private String responseDateTime;
	@Column(name = "ACKNOWLEDGE_DATE_TIME")
	private String acknowledgeDateTime;
	@Column(name = "RESPONSE_CODE")
	private String responseCode;
	@Column(name = "ERROR_CODE")
	private String errorCode;
	@Column(name = "ERROR_DESC")
	private String errorDesc;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "BARCODE")
	private String barcode;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUniqueReferenceNo() {
		return uniqueReferenceNo;
	}
	public void setUniqueReferenceNo(String uniqueReferenceNo) {
		this.uniqueReferenceNo = uniqueReferenceNo;
	}
	public String getRequestDateTime() {
		return requestDateTime;
	}
	public void setRequestDateTime(String requestDateTime) {
		this.requestDateTime = requestDateTime;
	}
	public String getRequestingBranch() {
		return requestingBranch;
	}
	public void setRequestingBranch(String requestingBranch) {
		this.requestingBranch = requestingBranch;
	}
	public String getKioskId() {
		return kioskId;
	}
	public void setKioskId(String kioskId) {
		this.kioskId = kioskId;
	}
	public String getResponseDateTime() {
		return responseDateTime;
	}
	public void setResponseDateTime(String responseDateTime) {
		this.responseDateTime = responseDateTime;
	}
	public String getAcknowledgeDateTime() {
		return acknowledgeDateTime;
	}
	public void setAcknowledgeDateTime(String acknowledgeDateTime) {
		this.acknowledgeDateTime = acknowledgeDateTime;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	
}
