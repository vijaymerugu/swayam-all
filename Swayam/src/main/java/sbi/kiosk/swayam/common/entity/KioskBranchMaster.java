package sbi.kiosk.swayam.common.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="TBL_KIOSK_MASTER")
public class KioskBranchMaster {
	
	@Id
	private String kioskId;
	
	private String vendor;
	
	private String installationDate;
	
	private String kioskIp;
	
	private String kioskMacAddress;
	
	private String siteType;
	
	private String location;
	
	private String address;
	
	private String branchCode;	
	
	

}
