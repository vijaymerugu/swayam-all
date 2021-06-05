package sbi.kiosk.swayam.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import lombok.Data;

@Data
@Entity
@NamedStoredProcedureQuery(
name="SP_DRILL_DOWN_PROC",
procedureName="SP_DRILL_DOWN_PROC",
resultClasses=DrillDown.class,
parameters={
@StoredProcedureParameter(name="FROMDATE",type=String.class, mode= ParameterMode.IN),
@StoredProcedureParameter(name="TODATE", type=String.class, mode= ParameterMode.IN),
@StoredProcedureParameter(name="IN_CIRCLE_CODE", type=String.class, mode= ParameterMode.IN),
@StoredProcedureParameter(name="IN_NETWORK_CODE", type=String.class, mode= ParameterMode.IN),
@StoredProcedureParameter(name="IN_MODULE_CODE", type=String.class, mode= ParameterMode.IN),
@StoredProcedureParameter(name="IN_REGION_CODE", type=String.class, mode= ParameterMode.IN),
//@StoredProcedureParameter(name = "SEARCHTEXT", type = String.class, mode = ParameterMode.IN),
@StoredProcedureParameter(name = "IN_TYPE", type = String.class, mode = ParameterMode.IN),
@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "RESULT_SET", type= String.class)})

public class DrillDown implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*@Id
	@Column(name="NAME")
	private String name;
	@Column(name="CODE")
	private String code;
	@Column(name="BRANCH_CODE_COUNT")
	private String branchCodeCount;
	@Column(name="TOTAL_SWAYAM_KIOSKS")
	private Integer totalSwayamKiosks;
	@Column(name="LIPI_KIOSK_COUNT")
	private Integer lipiKiosks;
	@Column(name="LIPI_TXN_COUNT")
	private Integer lipiTxns;
	@Column(name="FORBES_KIOSK_COUNT")
	private Integer forbesKiosks;
	@Column(name="FORBES_TXN_COUNT")
	private Integer forbesTxns;
	@Column(name="CMS_KIOSK_COUNT")
     private Integer cmsKiosks;
	@Column(name="CMS_TXN_COUNT")
	private Integer cmsTxns;
	@Column(name="TOTAL_SWAYAM_TXNS")
	private Integer totalSwayamTxns;
	@Column(name="BRANCH_TXNS")
	private Integer totalBranchCounterTxns;
	@Column(name="mig_prcnt")
	private Double migrationPercentage;
	*/
	
	
	@Id
	@Column(name="NAME")
	private String name;
	@Column(name="CODE")
	private String code;
	@Column(name="BRANCH_CODE_COUNT")
	private String branchCodeCount;
	@Column(name="TOTAL_SWAYAM_KIOSKS")
	private Integer totalSwayamKiosks;
	@Column(name="LIPI_KIOSK_CNT")
	private Integer lipiKiosks;
	@Column(name="LIPI_TXN_CNT")
	private Integer lipiTxns;
	@Column(name="FORBES_KIOSK_CNT")
	private Integer forbesKiosks;
	@Column(name="FORBES_TXN_CNT")
	private Integer forbesTxns;
	@Column(name="CMS_KIOSK_CNT")
	     private Integer cmsKiosks;
	@Column(name="CMS_TXN_CNT")
	private Integer cmsTxns;
	@Column(name="TOTAL_SWAYAM_TXNS")
	private Integer totalSwayamTxns;
	//@Column(name="BRANCH_TXNS")
	@Column(name="MANUAL_TXNS")
	private Integer totalBranchCounterTxns;
	@Column(name="mig_prcnt")
	private Double migrationPercentage;
	
	
}