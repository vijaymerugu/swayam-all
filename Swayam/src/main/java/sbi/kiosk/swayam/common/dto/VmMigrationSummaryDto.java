package sbi.kiosk.swayam.common.dto;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.SwayamMigrationSummary;

@Data
public class VmMigrationSummaryDto {
	public VmMigrationSummaryDto(){
		
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
