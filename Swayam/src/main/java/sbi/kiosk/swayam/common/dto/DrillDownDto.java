package sbi.kiosk.swayam.common.dto;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.DrillDown;

@Data
public class DrillDownDto {
	
	public DrillDownDto(){
		
	}
	
	public DrillDownDto(DrillDown drillDown) {
		
		this.circleName = drillDown.getCircleName();
		this.circleCode = drillDown.getCircleCode();
		this.network = drillDown.getNetwork();
		this.networkCode = drillDown.getNetworkCode();
		this.module = drillDown.getModule();
		this.moduleCode = drillDown.getModuleCode();
		this.region = drillDown.getRegion();
		this.regionCode = drillDown.getRegionCode();
		this.branchName = drillDown.getBranchName();
		this.totalSwayamBranches = drillDown.getTotalSwayamBranches();
		this.totalSwayamKiosks = drillDown.getTotalSwayamKiosks();
		this.lipiKiosks = drillDown.getLipiKiosks();
		this.lipiTxns = drillDown.getLipiTxns();
		this.forbesKiosks = drillDown.getForbesKiosks();
		this.forbesTxns = drillDown.getForbesTxns();
		this.cmsKiosks = drillDown.getCmsKiosks();
		this.cmsTxns = drillDown.getCmsTxns();
		this.totalSwayamTxns = drillDown.getTotalSwayamTxns();
		this.totalBranchCounterTxns = drillDown.getTotalBranchCounterTxns();
		this.migrationPercentage = drillDown.getMigrationPercentage();
			
	}

	private String circleName;
	
	private String circleCode;
	
	private String network;
	
	private String networkCode;
	
	private String module;
	
	private String moduleCode;
	
	private String region;
	
	private String regionCode;
	
	private String branchName;
	
	private Integer totalSwayamBranches;
	
	private Integer totalSwayamKiosks;
	
	private Integer lipiKiosks;
	
	private Integer lipiTxns;
	
	private Integer forbesKiosks;
	
	private Integer forbesTxns;
	
    private Integer cmsKiosks;
	
	private Integer cmsTxns; 
	
	private Integer totalSwayamTxns;
	
	private Integer totalBranchCounterTxns;
	
	private Double migrationPercentage;

}
