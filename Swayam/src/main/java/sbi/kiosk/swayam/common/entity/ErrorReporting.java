package sbi.kiosk.swayam.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
//@Table(name="TBL_ERROR_STATS")

@NamedStoredProcedureQuery(
name="SP_KIOSK_ERROR_SUMMARY_PROC",
procedureName="SP_KIOSK_ERROR_SUMMARY_PROC",
resultClasses=ErrorReporting.class,
parameters={
@StoredProcedureParameter( name="fromDate_param",type=String.class, mode= ParameterMode.IN),
@StoredProcedureParameter(name="toDate_param", type=String.class, mode= ParameterMode.IN),
@StoredProcedureParameter(name="cur", type=void.class, mode= ParameterMode.REF_CURSOR)
}
)
public class ErrorReporting {
	/*@Id
	@Column(name="KIOSK_ID")
	private String kioskId;
	@Column(name="BR_CODE")
	private String brCode;
	@Column(name="TXN_DTTM")
	private Date txnDateTime;
	@Column(name="TOTAL_TXNS")
	private String totalTxns;
	@Column(name="TOTAL_SUCCESS_TXNS")
	private String totalSuccessTxns;
	@Column(name="TOTAL_FAILURE_TXNS")
	private String totalFailureTxns;
	@Column(name="ERR_CODE")
	private String errorCode;
	@Column(name="ERR_DESC")
	private String errorDesc;
	@Column(name="ERR_COUNT")
	private String errorCount;
	@Column(name="STATUS")
	private String status;
	@Column(name="CREATED_BY")
	private String createdBy;
	@Column(name="CREATED_DTTM")
	private Date createdDateTime;
	@Column(name="MODIFIED_BY")
	private String modifiedBy;
	@Column(name="MODIFIED_DTTM")
	private Date modifiedDateTime;
	@Override
	public String toString() {
		return "ErrorReporting [kioskId=" + kioskId + ", brCode=" + brCode + ", txnDateTime=" + txnDateTime
				+ ", totalTxns=" + totalTxns + ", totalSuccessTxns=" + totalSuccessTxns + ", totalFailureTxns="
				+ totalFailureTxns + ", errorCode=" + errorCode + ", errorDesc=" + errorDesc + ", errorCount="
				+ errorCount + ", status=" + status + ", createdBy=" + createdBy + ", createdDateTime="
				+ createdDateTime + ", modifiedBy=" + modifiedBy + ", modifiedDateTime=" + modifiedDateTime + "]";
	}*/

	
	 @Id
	 @Column(name="KIOSK_ID")
		private String kioskId;
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
	    
	    @Column(name = "VENDOR")
		private String vendor;
	    @Column(name = "NO_OF_ERRORS")
		private String noOfError;
	
}
