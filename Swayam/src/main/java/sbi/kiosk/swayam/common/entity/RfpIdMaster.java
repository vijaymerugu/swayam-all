package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="TBL_RFP_DETAILS")
public class RfpIdMaster {
	
	@Id	
	@Column(name="RFP_NO")
	private String rfpNo;
	
	@Column(name="RFP_ID")
	private String rfpId;

	@Column(name="VENDOR")
	private String vendor;
	
	@Column(name="KIOSK_COST")
	private double kisokCost;
	
	@Column(name="AMC_COST")
	private double amcCost;
	
	@Column(name="COMP_PENALTY_HR")
	private double companyPenaltyHour;
	
	@Column(name="COMP_PERM_DNTM_MU_HRS")
	private double companyPermDntmMuHrs;
	
	@Column(name="COMP_PERM_DNTM_SR_HRS")
	private double companyPermDntmSrHrs;
	
	@Column(name="CRCL_PERM_DNTM_PCT")
	private double companyPermDntmPct;
	
	@Column(name="MAX_PENALTY_PCT")
	private double maxPenaltyPct;
	
	/*
	 * public RfpIdMaster() { // TODO Auto-generated constructor stub }
	 * 
	 * 
	 * public RfpIdMaster(String rfpId, String rfpNo, String vendor, double
	 * kisokCost, double amcCost, double companyPenaltyHour, double
	 * companyPermDntmMuHrs, double companyPermDntmSrHrs, double companyPermDntmPct,
	 * double maxPenaltyPct) { super(); this.rfpId = rfpId; this.rfpNo = rfpNo;
	 * this.vendor = vendor; this.kisokCost = kisokCost; this.amcCost = amcCost;
	 * this.companyPenaltyHour = companyPenaltyHour; this.companyPermDntmMuHrs =
	 * companyPermDntmMuHrs; this.companyPermDntmSrHrs = companyPermDntmSrHrs;
	 * this.companyPermDntmPct = companyPermDntmPct; this.maxPenaltyPct =
	 * maxPenaltyPct; }
	 */
	
	

}
