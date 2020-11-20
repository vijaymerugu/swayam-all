package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import sbi.kiosk.swayam.common.dto.KioskBranchMasterUserDto;

@Data
@Entity
@Table(name="TBL_KIOSK_MASTER")
public class KioskBranchMaster extends Common{
	public KioskBranchMaster(){
		
	}
	
	public KioskBranchMaster(KioskBranchMasterUserDto dto){
		this.id = dto.getId();
		this.kioskId = dto.getKioskId();
		this.vendor = dto.getVendor();
		this.installationDate = dto.getInstallationDate();
		this.kioskIp = dto.getKioskIp();
		this.kioskMacAddress = dto.getKioskMacAddress();
		this.siteType = dto.getSiteType();
		this.location = dto.getLocation();
		this.address = dto.getAddress();
		this.branchCode = dto.getBranchCode();
		this.kioskSerialNo = dto.getKioskSerialNo();
		this.circle = dto.getCircle();
		this.branchName = dto.getBranchName();
		this.os = dto.getOs();
		this.installationStatus = dto.getInstallationStatus();
	    this.refId=dto.getRefId();
	    this.installationType = dto.getInstallationType();
	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBL_KIOSK_MASTER")
	@SequenceGenerator(sequenceName = "SEQ_TBL_KIOSK_MASTER", allocationSize = 1, name = "SEQ_TBL_KIOSK_MASTER")
	@Column(name="ID")
	private Integer id;
	
	//@Column(name="SR_NO")
	//private String srNo;
	
	@Column(name="KIOSK_ID")
	private String kioskId;
	
	@Column(name="VENDOR")
	private String vendor;
	
	@Column(name="INSTALLATION_DATE")
	private String installationDate;
	
	@Column(name="KIOSK_IP")
	private String kioskIp;
	
	@Column(name="KIOSK_MAC_ADDRESS")
	private String kioskMacAddress;
	
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

	//@Column(name="MAKE")	
	//private String make;
	
	@Column(name="INSTALLATION_STATUS")
	private String installationStatus;
	
	@Column(name="RFP_ID")	
	private String refId;

	@Column(name="INSTALLATION_TYPE")	
	private String installationType;
	
	
	
	
	
	
	/*@Column(name="CREATED_BY")	
	private String  createdBy;
    
    @Column(name="CREATED_DATE")	
    private String  mody
    @Column(name="MODIFIED_DATE")	
    private Date MODIFIED_DATE*/
	
}
