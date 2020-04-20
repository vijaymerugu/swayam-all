package sbi.kiosk.swayam.common.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TBL_KIOSK_MASTER")
public class KioskMaster {

	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBL_KIOSK_MASTER")
	@SequenceGenerator(sequenceName = "SEQ_TBL_KIOSK_MASTER", allocationSize = 1, name = "SEQ_TBL_KIOSK_MASTER")
	private Long id;
	
	@Column(name="SR_NO")
	private Long srNo;
	
	@Column(name="KIOSK_ID")
	private String kioskId;
	
	@Column(name="VENDOR")
	private String vendor;
	
	@Column(name="INSTALLATION_DATE")
	private Date installationDate;
	
	@Column(name="KIOSK_IP")
	private String kioskIP;
	
	@Column(name="KIOSK_MAC_ADDRESS")
	private String kioskMackAddress;
	
	
	@Column(name="SITE_TYPE") 
	private String siteType;
	
	@Column(name="LOCATION")
	private String location;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="BRANCH_CODE")
	private String branchCode;
	
	@Column(name="KIOSK_SERIAL_NO")
	private String kioskSerialNo;
	
	@Column(name="CIRCLE")
    private String circle;
	
	@Column(name="BRANCH_NAME")	
	private String branchName;
	
	@Column(name="OS")		
	private String os;

	@Column(name="MAKE")	
	private String make;
	
	@Column(name="INSTALLATION_STATUS")	
	private String installationStatua;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSrNo() {
		return srNo;
	}

	public void setSrNo(Long srNo) {
		this.srNo = srNo;
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

	public Date getInstallationDate() {
		return installationDate;
	}

	public void setInstallationDate(Date installationDate) {
		this.installationDate = installationDate;
	}

	public String getKioskIP() {
		return kioskIP;
	}

	public void setKioskIP(String kioskIP) {
		this.kioskIP = kioskIP;
	}

	public String getKioskMackAddress() {
		return kioskMackAddress;
	}

	public void setKioskMackAddress(String kioskMackAddress) {
		this.kioskMackAddress = kioskMackAddress;
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

	public String getKioskSerialNo() {
		return kioskSerialNo;
	}

	public void setKioskSerialNo(String kioskSerialNo) {
		this.kioskSerialNo = kioskSerialNo;
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

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getInstallationStatua() {
		return installationStatua;
	}

	public void setInstallationStatua(String installationStatua) {
		this.installationStatua = installationStatua;
	}
	
	
	
}
