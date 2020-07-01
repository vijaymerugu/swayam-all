package sbi.kiosk.swayam.common.dto;

import java.util.Date;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.ErrorReporting;

@Data
public class ErrorReportingDto {
	
	/*public ErrorReportingDto(ErrorReporting errorRepor) {
		this.kioskId = errorRepor.getKioskId();
		this.brCode = errorRepor.getBrCode();
		this.txnDateTime = errorRepor.getTxnDateTime();
		this.totalTxns = errorRepor.getTotalTxns();
		this.totalSuccessTxns = errorRepor.getTotalSuccessTxns();
		this.totalFailureTxns = errorRepor.getTotalFailureTxns();
		this.errorCode = errorRepor.getErrorCode();
		this.errorDesc = errorRepor.getErrorDesc();
		this.errorCount = errorRepor.getErrorCount();
		this.status = errorRepor.getStatus();
		this.createdBy = errorRepor.getCreatedBy();
		this.createdDateTime = errorRepor.getCreatedDateTime();
		this.modifiedBy = errorRepor.getModifiedBy();
		this.modifiedDateTime = errorRepor.getModifiedDateTime();
	}*/
	private String kioskId;
	private String brCode;
	private Date txnDateTime;
	private String totalTxns;
	private String totalSuccessTxns;
	private String totalFailureTxns;
	private String errorCode;
	private String errorDesc;
	private String errorCount;
	private String status;
	private String createdBy;
	private Date createdDateTime;
	private String modifiedBy;
	private Date modifiedDateTime;

}
