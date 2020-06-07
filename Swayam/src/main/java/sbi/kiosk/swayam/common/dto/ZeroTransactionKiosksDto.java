package sbi.kiosk.swayam.common.dto;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.ZeroTransactionKiosks;

@Data
public class ZeroTransactionKiosksDto {
	
	public ZeroTransactionKiosksDto(){
		
	}
	
    public ZeroTransactionKiosksDto(ZeroTransactionKiosks zeroTransactionKiosks) {	
       this.circleName = zeroTransactionKiosks.getCircleName();
       this.network = zeroTransactionKiosks.getNetwork();
       this.module = zeroTransactionKiosks.getModule();
       this.region = zeroTransactionKiosks.getRegion();
	   this.branchCode = zeroTransactionKiosks.getBranchCode();
	   this.branchName = zeroTransactionKiosks.getBranchName();
	   this.kioskId = zeroTransactionKiosks.getKioskId();
	   this.vendor = zeroTransactionKiosks.getVendor();
    }
	

	private String circleName;
	
	private String network;
	
	private String module;
	
	private String region;
	
	private String branchCode;
	
	private String branchName;
	
	private String kioskId;
	
	private String vendor;

}
