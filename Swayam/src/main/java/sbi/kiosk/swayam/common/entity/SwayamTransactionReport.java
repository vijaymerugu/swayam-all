package sbi.kiosk.swayam.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import lombok.Data;


@SuppressWarnings("serial")
@Data
@Entity

@NamedStoredProcedureQuery(
name="DEMO_SP_MIGRATION_SUMMARY",
procedureName="DEMO_SP_MIGRATION_SUMMARY",
resultClasses=SwayamTransactionReport.class,
parameters={
@StoredProcedureParameter( name="fromdate",type=String.class, mode= ParameterMode.IN),
@StoredProcedureParameter(name="todate", type=String.class, mode= ParameterMode.IN),
@StoredProcedureParameter(name="cur", type=void.class, mode= ParameterMode.REF_CURSOR)
}
)

public class SwayamTransactionReport implements Serializable{
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
	    @Column(name = "a_KIOSK_COUNT")
		private String aaKioskCount;
	    @Column(name = "a_TXN_COUNT")
		private String aaTxnCount;
	    @Column(name = "b_KIOSK_COUNT")
		private String bbKioskCount;
	    @Column(name = "b_TXN_COUNT")
		private String bbTxnCount;
	    @Column(name = "c_KIOSK_COUNT")
		private String ccKioskCount;
	    @Column(name = "c_TXN_COUNT")
		private String ccTxnCount;
	    @Column(name = "BRANCH_TXN")
		private String branchTxn;
	    
	    @Column(name = "SWAYAM_TXN")
		private String swayamTxn;
	    @Column(name = "MIGRATION_PERCENTAGE")
		private Double migrationPerc;
	 
	/* @Column(name = "txn_date")
		private String fromDate;*/


}
