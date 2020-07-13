package sbi.kiosk.swayam.common.dto;
import javax.persistence.Column;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.SwayamMigrationSummary;

@Data
public class TransactionDashBoardDto {
	
	public TransactionDashBoardDto() {
		
	}
	
	public TransactionDashBoardDto(SwayamMigrationSummary swayamMigrationSummary){
		
	}
	
	private String crclName;
	private String network;
	private String module;
	private String region;
	private String branchCode;
	private String branchName;
	private String lipiKioskCount;
	private String lipiTxnCount;
	private String forbesKioskCount;
	private String forbesTxnCount;
	private String cmsKioskCount;
	private String cmsTxnCount;
	private String manualTxn;
	private String totalSwayamTxn;
	private Double migrationPerc;
	
	
	

}
