package sbi.kiosk.swayam.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ErrorReportingDrillDown implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "NAME")
	private String name;
	@Column(name = "CODE")
	private String code;
	//@Column(name = "BRANCH_CODE_COUNT")
	//private String branchCodeCount;
	//@Column(name = "TOTAL_SWAYAM_KIOSKS")
	//private Integer totalSwayamKiosks;
	//@Column(name = "MANUAL_TXNS")
	//private Integer totalBranchCounterTxns;
	@Column(name = "No_of_Txns")
	private Integer totalNoOfTxns;
	@Column(name = "No_of_success_Txns")
	private Integer noOfSuccTxns;
	@Column(name = "No_of_failure_Txns")
	private Integer noOfFailTxns;
	@Column(name = "No_of_technical_failure_Txns")
	private Integer noOfTchFailTxns;
	@Column(name = "No_of_business_failure_Txns")
	private Integer noOfBsnsFailTxns;

}
