package sbi.kiosk.swayam.common.dto;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.RealTimeTransaction;

@Data
public class RealTimeTransactionDto {
	RealTimeTransactionDto(){
		
	}
	
	public RealTimeTransactionDto(RealTimeTransaction realTimeTransaction) {
		this.kioskId = realTimeTransaction.getKioskId();
		this.branchCode = realTimeTransaction.getBranchCode();
		this.region = realTimeTransaction.getRegion();
		this.module = realTimeTransaction.getModule();
		this.network = realTimeTransaction.getNetwork();
		this.crclName = realTimeTransaction.getCrclName();
		this.branchName = realTimeTransaction.getBranchName();
		this.vendor = realTimeTransaction.getVendor();
		this.noOfTxns=realTimeTransaction.getNoOfTxns();
	}

	private String kioskId;
	private String branchCode;
	private String region;
	private String vendor;
	private String module;
	private String network;
	private String crclName;
	private String branchName;
	private String noOfTxns;

}
