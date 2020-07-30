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

@NamedStoredProcedureQuery(name = "SP_MIGRATION_SUMMARY", procedureName = "SP_MIGRATION_SUMMARY", resultClasses = SwayamMigrationSummary.class, parameters = {
		@StoredProcedureParameter(name = "fromdate", type = String.class, mode = ParameterMode.IN),
		@StoredProcedureParameter(name = "todate", type = String.class, mode = ParameterMode.IN),
		@StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR) })

public class SwayamMigrationSummary {

	@Id  
    @Column(name = "CRCL_NAME")  
    private String crclName;  
    @Column(name = "NETWORK")  
    private String network;  
    @Column(name = "MODULE")  
    private String module;  
    @Column(name = "REGION")  
    private String region;  
    @Column(name = "BRANCH_CODE")  
    private String branchCode;  
    @Column(name = "BRANCH_NAME")  
    private String branchName;  
    @Column(name = "lipi_kiosk_cnt")  
    private Integer lipiKioskCount;  
    @Column(name = "lipi_txn_cnt")  
    private Integer lipiTxnCount;  
    @Column(name = "FORBES_kiosk_cnt")  
    private Integer forbesKioskCount;  
    @Column(name = "FORBES_txn_cnt")  
    private Integer forbesTxnCount;  
    @Column(name = "cms_kiosk_cnt")  
    private Integer cmsKioskCount;  
    @Column(name = "cms_txn_cnt")  
    private Integer cmsTxnCount;  
    @Column(name = "manual_txns")  
    private Integer manualTxn;  
    @Column(name = "total_swayam_txns")  
    private Integer totalSwayamTxn;  
    @Column(name = "mig_prcnt")  
    private Double migrationPerc;

}