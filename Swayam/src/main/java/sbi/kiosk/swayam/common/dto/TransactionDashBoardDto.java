package sbi.kiosk.swayam.common.dto;
import lombok.Data;
import sbi.kiosk.swayam.common.entity.SwayamMigrationSummary;

@Data
public class TransactionDashBoardDto {
	
public TransactionDashBoardDto() {
		
	}
	
	public TransactionDashBoardDto(SwayamMigrationSummary swayamMigrationSummary){
		
	}
	
	private Integer id;
	private String txnDate;
	private String branchCode;
	private String vendor;
	private String kioskId;
	private String noOfTxns;
	private String noOfSuccessTxns;
	private String noOfFial;
	private String noOfTechFial;
	private String noOfBusinessFail;
	
	private String circle;
	private String network;
	private String modeCode;
	private String region;
	private String branchName;
	
	
	
	

}
