package com.sbi.cron.dto;

public class SwayamTransactionhourlyDto {
	
	private Integer id;
	
	private String uniqueReferenceNo;
	
	private String requestDateTime;
	
	private String requestingBranch;
    
	private String kioskId;
    
	private String responseDateTime;
	
	private String acknowledgeDateTime;
	
	private String responseCode;
    
	private String errorCode;
    
	private String errorDesc;
	
	private String status;
    
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
