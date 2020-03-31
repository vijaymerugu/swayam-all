package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="TBL_KIOSK_MASTER")
public class KioskBranchMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBL_KIOSK_MASTER")
	@SequenceGenerator(sequenceName = "SEQ_TBL_KIOSK_MASTER", allocationSize = 1, name = "SEQ_TBL_KIOSK_MASTER")
	@Column(name="KIOSK_ID")
	private Integer kioskId;
	
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
	
	@Column(name="INSTALLATION_STATUS")
	private String installationStatus;

}
