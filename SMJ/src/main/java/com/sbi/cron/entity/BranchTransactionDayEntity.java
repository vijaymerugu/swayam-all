package com.sbi.cron.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;


@Entity

@Table(name="TBL_BRANCH_TXN_DAILY")
public class BranchTransactionDayEntity {

	@Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "CIRCLENAME")
	private String circleName;//1
	@Column(name = "MODULENAME")
	private String moduleName;//2
	@Column(name = "NETWORKNAME")
	private String networkName;//3
	@Column(name = "REGIONNAME")
	private String regionName;//4
	@Column(name = "BRANCH_NO")
	private String branchNo;//5
	@Column(name = "BRANCHNAME")
	private String branchName;//6
	@Column(name = "LAST_PBK_DT")
	private String lastPbkdate;//7
	@Column(name = "NO_OF_ACCOUNTS")
	private String noOfAccounts;//8
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCircleName() {
		return circleName;
	}
	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getNetworkName() {
		return networkName;
	}
	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getBranchNo() {
		return branchNo;
	}
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getLastPbkdate() {
		return lastPbkdate;
	}
	public void setLastPbkdate(String lastPbkdate) {
		this.lastPbkdate = lastPbkdate;
	}
	public String getNoOfAccounts() {
		return noOfAccounts;
	}
	public void setNoOfAccounts(String noOfAccounts) {
		this.noOfAccounts = noOfAccounts;
	}
	
}
