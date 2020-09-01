package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="TBL_BRANCH_MASTER")
public class BranchEntity {
	
	@Id
	@Column(name="BR_ID")
	private Integer brId;
	
	
	@Column(name="CIRCLE")
	private String circle;
	
	@Column(name="CRCL_NAME")
	private String circleName;
	
	@Column(name="NETWORK")
	private String network;
	
	@Column(name="MODULE")
	private String module;
	
	@Column(name="REGION")
	private String region;
	
	@Column(name="BRANCH_CODE")
	private String branchCode;
	
	@Column(name="BRANCH_NAME")
	private String branchName;

	
	@Column(name="CRCL_CODE")
	private String crclCode;	
	
	@Column(name="POP_GROUP")
	private String popGroup;
	
	@Column(name="POP_DESC")
	private String popDesc;
	
	

	@Column(name="OPEN_CLOSE_STATUS")
	private String  openCloseStatus;
	
	@Column(name="OPENDT")
	private String opendt; 
	
	@Column(name="STAT_CODE")
	private String statCode;
	
	@Column(name="STAT_DESC")
	private String statDesc;
	
	@Column(name="DIST_CODE")
	private String distCode;
	
	@Column(name="DIST_DESC")
	private String distDesc;
	
	
	@Column(name="ADDRESS1")
	private String address1;
	
	@Column(name="ADDRESS2")
	private String address2;
	
	@Column(name="ADDRESS3")
	private String address3;	
	
	@Column(name="ADDRESS4")
	private String address4;
	
	@Column(name="PINCODE")
	private String pinCode;
	
	@Column(name="STD_CODE")
	private String stdCode;
	
	@Column(name="PHONE")
	private String phone;
	
	@Column(name="MICR_CODE")
	private String micrCode;
	
	@Column(name="IFSC")
	private String ifsc;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="BRANCHMGR_NAME")
	private String branchMgrName;
	
	@Column(name="BRANCHMGR_MOBILE")
	private String BranchMgrMobileNo;
	
	
	@Column(name="BUSINESSHRS")
	private String businessHrs;
	
	@Column(name="OFFICE_TYPE")
	private String officeType;
	
	@Column(name="OFFICE_DESC")
	private String officeDesc;
	
	@Column(name="MOD_CODE")
	private String modCode;
	
	
	public BranchEntity() {
		
	}
	
	
	public BranchEntity(Integer brId, String circle, String circleName, String network, String module, String region,
			String branchCode, String branchName, String crclCode, String popGroup, String popDesc,
			String openCloseStatus, String opendt, String statCode, String statDesc, String distCode, String distDesc,
			String address1, String address2, String address3, String address4, String pinCode, String stdCode,
			String phone, String micrCode, String ifsc, String email, String branchMgrName, String branchMgrMobileNo,
			String businessHrs, String officeType, String officeDesc, String modCode) {
		super();
		this.brId = brId;
		this.circle = circle;
		this.circleName = circleName;
		this.network = network;
		this.module = module;
		this.region = region;
		this.branchCode = branchCode;
		this.branchName = branchName;
		this.crclCode = crclCode;
		this.popGroup = popGroup;
		this.popDesc = popDesc;
		this.openCloseStatus = openCloseStatus;
		this.opendt = opendt;
		this.statCode = statCode;
		this.statDesc = statDesc;
		this.distCode = distCode;
		this.distDesc = distDesc;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.address4 = address4;
		this.pinCode = pinCode;
		this.stdCode = stdCode;
		this.phone = phone;
		this.micrCode = micrCode;
		this.ifsc = ifsc;
		this.email = email;
		this.branchMgrName = branchMgrName;
		BranchMgrMobileNo = branchMgrMobileNo;
		this.businessHrs = businessHrs;
		this.officeType = officeType;
		this.officeDesc = officeDesc;
		this.modCode = modCode;
	}

}
