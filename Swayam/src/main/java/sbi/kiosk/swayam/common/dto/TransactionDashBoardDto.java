package sbi.kiosk.swayam.common.dto;
import lombok.Data;
import sbi.kiosk.swayam.common.entity.SwayamMigrationSummary;

@Data
public class TransactionDashBoardDto {
	
	public TransactionDashBoardDto() {  
        
    }  
      
          
    public TransactionDashBoardDto(SwayamMigrationSummary swayamMigrationSummary)  
          
     {

    	this.lipiKioskCount = swayamMigrationSummary.getLipiKioskCount();  
    	this.lipiTxnCount = swayamMigrationSummary.getLipiTxnCount();  
        this.forbesKioskCount = swayamMigrationSummary.getForbesKioskCount();  
        this.forbesTxnCount = swayamMigrationSummary.getForbesTxnCount();  
        this.cmsKioskCount = swayamMigrationSummary.getCmsKioskCount();  
        this.cmsTxnCount = swayamMigrationSummary.getCmsTxnCount();  
        this.manualTxn = swayamMigrationSummary.getManualTxn();  
        this.totalSwayamTxn = swayamMigrationSummary.getTotalSwayamTxn();  
        this.migrationPerc = swayamMigrationSummary.getMigrationPerc();  
    }  
    private String crclName;  
    private String network;  
    private String module;  
    private String region;  
    private String branchCode;  
    private String branchName;  
    private Integer lipiKioskCount;  
    private Integer lipiTxnCount;  
    private Integer forbesKioskCount;  
    private Integer forbesTxnCount;  
    private Integer cmsKioskCount;  
    private Integer cmsTxnCount;  
    private Integer manualTxn;  
    private Integer totalSwayamTxn;  
    private Double migrationPerc;  
      
      
      
      
}  
