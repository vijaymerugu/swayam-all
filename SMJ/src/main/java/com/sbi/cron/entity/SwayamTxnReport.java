package com.sbi.cron.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@NamedStoredProcedureQueries({
	  
	   @NamedStoredProcedureQuery(name = "SP_SWAYAM_TXN_REPORT", 
	                              procedureName = "SP_SWAYAM_TXN_REPORT",
	                            		  resultClasses = {SwayamTxnReport.class},
	                              parameters = {
	                                 @StoredProcedureParameter(mode = ParameterMode.IN, name = "txnDate", type = String.class),
	                                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "cur", type= String.class)
	                              })
	})
@Entity
@Table(name="TBL_SWAYAM_TXN_REPORT")
public class SwayamTxnReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;	
	@Column(name = "TXN_DATE")
	private String txnDate;
	@Column(name = "BRANCH_CODE")
	private String branchCode;
	@Column(name = "VENDOR")
	private String vendor;
	@Column(name = "KIOSK_ID")
	private String kioskId;
	@Column(name = "NO_OF_TXNS")
	private String noOfTxns;
	@Column(name = "NO_OF_SUCCESS_TXNS")
	private String noOfSuccessTxns;
	@Column(name = "NO_OF_FAIL")
	private String noOfFail;
	@Column(name = "NO_OF_TECH_FAIL")
	private String noOfTechFail;
	@Column(name = "NO_OF_BUSINESS_FAIL")
	private String noOfBusinessFail;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTxnDate() {
		return txnDate;
	}
	public void setTxnDate(String txnDate) {
		this.txnDate = txnDate;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getKioskId() {
		return kioskId;
	}
	public void setKioskId(String kioskId) {
		this.kioskId = kioskId;
	}
	public String getNoOfTxns() {
		return noOfTxns;
	}
	public void setNoOfTxns(String noOfTxns) {
		this.noOfTxns = noOfTxns;
	}
	public String getNoOfSuccessTxns() {
		return noOfSuccessTxns;
	}
	public void setNoOfSuccessTxns(String noOfSuccessTxns) {
		this.noOfSuccessTxns = noOfSuccessTxns;
	}
	public String getNoOfFail() {
		return noOfFail;
	}
	public void setNoOfFail(String noOfFail) {
		this.noOfFail = noOfFail;
	}
	public String getNoOfTechFail() {
		return noOfTechFail;
	}
	public void setNoOfTechFail(String noOfTechFail) {
		this.noOfTechFail = noOfTechFail;
	}
	public String getNoOfBusinessFail() {
		return noOfBusinessFail;
	}
	public void setNoOfBusinessFail(String noOfBusinessFail) {
		this.noOfBusinessFail = noOfBusinessFail;
	}
	
	
	
}
