package sbi.kiosk.swayam.common.dto;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.KioskBranchMaster;


@Data
public class KioskBranchMasterUserDto {
	
	public KioskBranchMasterUserDto(KioskBranchMaster master){
		this.id = master.getId();
		this.kioskId = master.getKioskId();
		this.vendor = master.getVendor();
		this.installationDate = master.getInstallationDate();
		this.kioskIp = master.getKioskIp();
		this.kioskMacAddress = master.getKioskMacAddress();
		this.siteType = master.getSiteType();
		this.location = master.getLocation();
		this.address = master.getAddress();
		this.branchCode = master.getBranchCode();
		this.kioskSerialNo = master.getKioskSerialNo();
		this.installationStatus = master.getInstallationStatus();
	}
	
	private Integer id;

	private String kioskId;
	
	
	private String vendor;
	
	
	private String installationDate;
	
	
	private String kioskIp;
	
	
	private String kioskMacAddress;
	
	
	private String siteType;
	
	
	private String location;
	
	
	private String address;
	
	
	private String branchCode;	
	
	
	private String kioskSerialNo;	
	
	private String installationStatus;
	
	private String pfId;
	
	private String username;
	
	private String circle;

}
