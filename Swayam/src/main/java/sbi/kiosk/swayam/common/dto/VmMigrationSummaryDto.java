package sbi.kiosk.swayam.common.dto;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.SwayamMigrationSummary;

@Data
public class VmMigrationSummaryDto {
	public VmMigrationSummaryDto(){
		
	}
	
	public VmMigrationSummaryDto(SwayamMigrationSummary vmMigrationSummary) {
	//	this.id = vmMigrationSummary.getId();
		this.crclName=vmMigrationSummary.getCrclName();
		this.network = vmMigrationSummary.getNetwork();
		this.module = vmMigrationSummary.getModule();
		this.region = vmMigrationSummary.getRegion();
		this.branchCode = vmMigrationSummary.getBranchCode();
		this.branchName = vmMigrationSummary.getBranchName();
		this.aaKioskCount = vmMigrationSummary.getAaKioskCount();
		this.aaTxnCount = vmMigrationSummary.getAaTxnCount();
		this.bbKioskCount = vmMigrationSummary.getBbKioskCount();
		this.bbTxnCount = vmMigrationSummary.getBbTxnCount();
		this.ccKioskCount = vmMigrationSummary.getCcKioskCount();
		this.ccTxnCount = vmMigrationSummary.getCcTxnCount();
		this.branchTxn = vmMigrationSummary.getBranchTxn();
		this.swayamTxn = vmMigrationSummary.getSwayamTxn();
		this.migrationPerc = vmMigrationSummary.getMigrationPerc();
	}


	//private Integer id;
	private String crclName;
	private String network;
	private String module;
	private String region;
	private String branchCode;
	private String branchName;
	private String aaKioskCount;
	private String aaTxnCount;
	private String bbKioskCount;
	private String bbTxnCount;
	private String ccKioskCount;
	private String ccTxnCount;
	private String branchTxn;
	private String swayamTxn;
	private Double migrationPerc;

}
