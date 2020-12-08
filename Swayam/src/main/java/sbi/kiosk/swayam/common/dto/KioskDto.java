package sbi.kiosk.swayam.common.dto;

public class KioskDto {
	
//	private String srNo; 
	private String kioskID;
	private String vendor;
	private String installationDate;
	private String kioskIPAddress;
	private String kioskMacAddress;
	private String siteType;
	private String location;
	private String address;
	private String branchCode;
	private String kioskSerialNumber;
	private String circle;
	private String branchName;	
	private String oS;	
	//private String make;
	private String installationStatus;
	private String rfpID;
	private String installationType;
	
	public String getRfpID() {
		return rfpID;
	}
	public void setRfpID(String rfpID) {
		this.rfpID = rfpID;
	}
	public String getInstallationType() {
		return installationType;
	}
	public void setInstallationType(String installationType) {
		this.installationType = installationType;
	}

	/*
	 * public String getSrNo() { return srNo; } public void setSrNo(String srNo) {
	 * this.srNo = srNo; }
	 */
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getInstallationDate() {
		return installationDate;
	}
	public void setInstallationDate(String installationDate) {
		this.installationDate = installationDate;
	}
	public String getKioskID() {
		return kioskID;
	}
	public void setKioskID(String kioskID) {
		this.kioskID = kioskID;
	}
	public String getKioskMacAddress() {
		return kioskMacAddress;
	}
	public void setKioskMacAddress(String kioskMacAddress) {
		this.kioskMacAddress = kioskMacAddress;
	}
	public String getSiteType() {
		return siteType;
	}
	public void setSiteType(String siteType) {
		this.siteType = siteType;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getKioskSerialNumber() {
		return kioskSerialNumber;
	}
	public void setKioskSerialNumber(String kioskSerialNumber) {
		this.kioskSerialNumber = kioskSerialNumber;
	}
	public String getCircle() {
		return circle;
	}
	public void setCircle(String circle) {
		this.circle = circle;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getKioskIPAddress() {
		return kioskIPAddress;
	}
	public void setKioskIPAddress(String kioskIPAddress) {
		this.kioskIPAddress = kioskIPAddress;
	}
	public String getoS() {
		return oS;
	}
	public void setoS(String oS) {
		this.oS = oS;
	}

	/*
	 * public String getMake() { return make; } public void setMake(String make) {
	 * this.make = make; }
	 */
	public String getInstallationStatus() {
		return installationStatus;
	}
	public void setInstallationStatus(String installationStatus) {
		this.installationStatus = installationStatus;
	}
	
}
