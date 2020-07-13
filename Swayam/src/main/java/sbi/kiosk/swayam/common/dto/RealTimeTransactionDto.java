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
		this.module = realTimeTransaction.getModule();
		this.network = realTimeTransaction.getNetwork();
		this.crclName = realTimeTransaction.getCrclName();
		this.branchName = realTimeTransaction.getBranchName();
		//this.createdDate=realTimeTransaction.getCreatedDate();
	}

	private String crclName;
	private String kioskId;
	private String branchCode;
	private String module;
	private String network;
	private String region;
	private String branchName;
	private String vendor;
	private String noOfTxns;
	private String createdDate;

}
