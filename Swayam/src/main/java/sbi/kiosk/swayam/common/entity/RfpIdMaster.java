package sbi.kiosk.swayam.common.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name="TBL_RFP_DETAILS")
public class RfpIdMaster {
	
	
	@Id
	@Column(name="RFP_ID")
	private String rfpId;
	
	@NotNull
	@Size(min = 23 ,max = 23, message="Please enter correct format {min}-{max} character")
	@Pattern(regexp="^[A-Z]{3}\\:[A-Z]{2}\\:[A-Z]{3}\\:[A-Z]{2}\\:\\d{2}\\-\\d{2}\\:[0-9]{3}$",message="length must be 23") 
	@Column(name="RFP_NO")
	private String rfpNo;
	
	@NotNull
	@Column(name="VENDOR")
	private String vendor;

	@DecimalMin(value = "0.0", inclusive = true)
	@DecimalMax(value = "99999.99", inclusive = true)
	@Digits(integer=5, fraction=2)
	@Column(name="KIOSK_COST")
	private BigDecimal kisokCost;
	
	@DecimalMin(value = "0.0", inclusive = true)
	@DecimalMax(value = "999.99", inclusive = true)
	@Digits(integer=3, fraction=2)
	@Column(name="AMC_COST")
	private BigDecimal amcCost;
	
	
	@PositiveOrZero
	@Min(0)
	@Max(999)
	@Column(name="COMP_PENALTY_HR")
	private Integer companyPenaltyHour;
	
	
	@PositiveOrZero
	@Min(0)
	@Max(12)
	@Column(name="COMP_PERM_DNTM_MU_HRS")
	private Integer companyPermDntmMuHrs;
	
	
	@PositiveOrZero
	@Min(0)
	@Max(12)
	@Column(name="COMP_PERM_DNTM_SR_HRS")
	private Integer companyPermDntmSrHrs;
	
	@PositiveOrZero
	@Min(0)
	@Max(100)
	@Column(name="CRCL_PERM_DNTM_PCT")
	private Integer companyPermDntmPct;
	

	@PositiveOrZero
	@Min(0)
	@Max(100)
	@Column(name="MAX_PENALTY_PCT")
	private Integer maxPenaltyPct;
	
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
