package sbi.kiosk.swayam.common.dto;

import javax.persistence.Column;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.BranchMaster;

@Data
public class BranchMasterDto {
	
	BranchMasterDto(){
		
	}
	
	
	public BranchMasterDto(BranchMaster branchMaster) {
		this.brId = branchMaster.getBrId();
		this.circle = branchMaster.getCircle();
		this.circleName = branchMaster.getCircleName();
		this.network = branchMaster.getNetwork();
		this.module = branchMaster.getModule();
		this.region = branchMaster.getRegion();
		this.branchCode = branchMaster.getBranchCode();
		this.branchName = branchMaster.getBranchName();
		this.modCode = branchMaster.getModCode();
		this.cRCLCode = branchMaster.getCRCLCode();
		this.popGroup = branchMaster.getPopGroup();
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
		this.BranchMgrMobileNo = BranchMgrMobileNo;
		this.businessHrs = businessHrs;
		this.officeType = officeType;
		this.officeDesc = officeDesc;
	}


	private Integer brId;
	private String circle;
	private String circleName;
	private String network;
	private String module;
	private String region;
	private String branchCode;
	private String branchName;
	private String modCode;
	private String cRCLCode;	
	private String popGroup;
	private String popDesc;
	private String  openCloseStatus;
	private String opendt; 
	private String statCode;
	private String statDesc;
	private String distCode;
	private String distDesc;
	private String address1;
	private String address2;
	private String address3;	
	private String address4;
	private String pinCode;
	private String stdCode;
	private String phone;
	private String micrCode;
	private String ifsc;
	private String email;
	private String branchMgrName;
	private String BranchMgrMobileNo;
	private String businessHrs;
	private String officeType;
	private String officeDesc;

	
	

}
