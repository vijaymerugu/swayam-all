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
@Table(name="TBL_BRANCH_MASTER")
public class BranchMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBL_BRANCH_MASTER")
	@SequenceGenerator(sequenceName = "SEQ_TBL_BRANCH_MASTER", allocationSize = 1, name = "SEQ_TBL_BRANCH_MASTER")
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
	
	

	

	
	
	
	@Column(name="MOD_CODE")
	private String modCode;
	
	
	@Column(name="CRCL_CODE")
	private String cRCLCode;	
	
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

}
