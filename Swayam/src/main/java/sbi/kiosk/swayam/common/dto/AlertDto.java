package sbi.kiosk.swayam.common.dto;

public class AlertDto {
	//String postData = "content_type=text&sender_id=SBIBNK&mobile=9987720876&message=Call ID TEST001  has been logged for Swayam Kiosk Sr. No SRNOTEAT01 in Branch 04430 on 03.03.2020 at 16:00 for Issue:Test call log.&intflag=0&charging=0";
	private String contentType;
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	private String senderId;
	private String mobileNo;
	private String message;
	private String callLogId;
	private String kioskSrNo;
	private String branchCode;
	private String dateTime;
	private String intflag;
	private String charging;
	
	
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCallLogId() {
		return callLogId;
	}
	public void setCallLogId(String callLogId) {
		this.callLogId = callLogId;
	}
	public String getKioskSrNo() {
		return kioskSrNo;
	}
	public void setKioskSrNo(String kioskSrNo) {
		this.kioskSrNo = kioskSrNo;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getIntflag() {
		return intflag;
	}
	public void setIntflag(String intflag) {
		this.intflag = intflag;
	}
	public String getCharging() {
		return charging;
	}
	public void setCharging(String charging) {
		this.charging = charging;
	}
	@Override
	public String toString() {
		return "AlertDto [contentType=" + contentType + ", senderId=" + senderId + ", mobileNo=" + mobileNo
				+ ", message=" + message + ", callLogId=" + callLogId + ", kioskSrNo=" + kioskSrNo + ", branchCode="
				+ branchCode + ", dateTime=" + dateTime + ", intflag=" + intflag + ", charging=" + charging + "]";
	}
	
	
	

}
