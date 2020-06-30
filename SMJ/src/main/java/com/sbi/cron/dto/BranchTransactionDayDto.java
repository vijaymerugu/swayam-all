package com.sbi.cron.dto;

public class BranchTransactionDayDto {
	//TBL_BRANCH_TXN_DAILY
	private Integer id;//1
	private String circleName;//2
	private String moduleName;//3
    private String networkName;//4
    private String regionName;//5
    private String branchNo;//6
    private String branchName;
    private String lastPbkdate;
    private String noOfAccounts;
    
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
