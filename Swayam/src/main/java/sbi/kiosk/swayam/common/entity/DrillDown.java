package sbi.kiosk.swayam.common.entity;

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
name="sp_drill_down_proc",
procedureName="sp_drill_down_proc",
resultClasses=DrillDown.class,
parameters={
@StoredProcedureParameter(name="in_fromdate",type=String.class, mode= ParameterMode.IN),
@StoredProcedureParameter(name="in_todate", type=String.class, mode= ParameterMode.IN),
@StoredProcedureParameter(name="in_circle_code", type=String.class, mode= ParameterMode.IN),
@StoredProcedureParameter(name="in_network_code", type=String.class, mode= ParameterMode.IN),
@StoredProcedureParameter(name="in_module_code", type=String.class, mode= ParameterMode.IN),
@StoredProcedureParameter(name="in_region_code", type=String.class, mode= ParameterMode.IN),
@StoredProcedureParameter(name="cur", type=void.class, mode= ParameterMode.REF_CURSOR)
}
)
public class DrillDown {
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