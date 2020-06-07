package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SequenceGenerator;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
//@Table(name="VW_DRILL_DOWN")

@NamedStoredProcedureQuery(
name="SP_DRILL_DOWN",
procedureName="SP_DRILL_DOWN",
resultClasses=DrillDown.class,
parameters={
@StoredProcedureParameter( name="fromdate",type=String.class, mode= ParameterMode.IN),
@StoredProcedureParameter(name="todate", type=String.class, mode= ParameterMode.IN),
@StoredProcedureParameter(name="circleName", type=String.class, mode= ParameterMode.IN),
@StoredProcedureParameter(name="networkName", type=String.class, mode= ParameterMode.IN),
@StoredProcedureParameter(name="moduleName", type=String.class, mode= ParameterMode.IN),
@StoredProcedureParameter(name="regionName", type=String.class, mode= ParameterMode.IN),
@StoredProcedureParameter(name="cur", type=void.class, mode= ParameterMode.REF_CURSOR)
}
)
public class DrillDown {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBL_USER_KIOSK_MAPPING")
	//@SequenceGenerator(sequenceName = "SEQ_TBL_USER_KIOSK_MAPPING", allocationSize = 1, name = "SEQ_TBL_USER_KIOSK_MAPPING")
	@Column(name="BR_ID")
	private Integer id;
	
	@Column(name="CRCL_NAME")
    private String  circleName;
	
	@Column(name="CRCL_CODE")
    private String circleCode;
	
	@Column(name="NETWORK")
    private String network;
	
	@Column(name="NETWORK_CODE")
	private String networkCode;
	
	@Column(name="MODULE")
	private String module;
	
	@Column(name="MOD_CODE")
	private String moduleCode;
	
	@Column(name="REGION")
	private String region;
	
	@Column(name="REGION_CODE")
	private String regionCode;
	
	@Column(name="BRANCH_NAME")
	private String branchName;
	
	@Column(name="TOTAL_SWAYAM_BRANCHES")
	private Integer totalSwayamBranches;
	
	@Column(name="TOTAL_SWAYAM_KIOSKS")
	private Integer totalSwayamKiosks;
	
	@Column(name="A_KIOSK_COUNT")
	private Integer lipiKiosks;
	
	@Column(name="A_TXN_COUNT")
	private Integer lipiTxns;
	
	@Column(name="B_KIOSK_COUNT")
	private Integer forbesKiosks;
	
	@Column(name="B_TXN_COUNT")
	private Integer forbesTxns;
	
	@Column(name="C_KIOSK_COUNT")
    private Integer cmsKiosks;
	
	@Column(name="C_TXN_COUNT")
	private Integer cmsTxns; 
	
	@Column(name="BRANCH_TXN")
	private Integer totalSwayamTxns;
	
	@Column(name="SWAYAM_TXN")
	private Integer totalBranchCounterTxns;
	
	@Column(name="MIGRATION_PERCENTAGE")
	private Double migrationPercentage;

}
