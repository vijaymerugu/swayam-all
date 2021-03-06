package sbi.kiosk.swayam.common.entity;

import java.math.BigDecimal;
import java.util.Date;

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
	
	

	@Min(0)
	@Max(999)
	@Column(name="COMP_PENALTY_HR")
	private Integer companyPenaltyHour;
	
	
	
	@Min(0)
	@Max(12)
	@Column(name="COMP_PERM_DNTM_MU_HRS")
	private Integer companyPermDntmMuHrs;
	
	
	
	@Min(0)
	@Max(12)
	@Column(name="COMP_PERM_DNTM_SR_HRS")
	private Integer companyPermDntmSrHrs;
	
	
	@Min(0)
	@Max(100)
	@Column(name="CRCL_PERM_DNTM_PCT")
	private Integer companyPermDntmPct;
	

	
	@Min(0)
	@Max(100)
	@Column(name="MAX_PENALTY_PCT")
	private Integer maxPenaltyPct;
	

	
	@Column(name = "RFP_DATE")
	private Date rfpDate;
	
	@Column(name = "AMC_START_DATE")
	private Date amcStartDate;

	
	//Changes - 10-11-2020
	
	@Column(name = "SLA_START_DATE")
	private Date slaStartDate;
	
	@Column(name = "SLA_EXPIRY_DATE")
	private Date slaExpiryDate;
	
	@NotNull
	@Column(name="AMC_PERIODICITY")
	private String periodicity;
	
	@NotNull
	@Column(name="PENALTY_TYPE")
	private String penaltyType;
	
	@Min(0)
	@Max(999)
	@Column(name="PENALTY_RANGE_TO")
	private Integer penaltyToRange;
	
	@Min(0)
	@Max(999)
	@Column(name="PENALTY_RANGE_From")
	private Integer penaltyfromRange;
	
}
