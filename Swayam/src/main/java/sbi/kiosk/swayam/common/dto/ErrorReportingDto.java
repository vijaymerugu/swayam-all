package sbi.kiosk.swayam.common.dto;

import lombok.Data;

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
	private String crclName;
	private String network;
	private String module;
	private String region;
	private String branchCode;
	private String branchName;
	private String vendor;
	private String noOfErrors;

}
