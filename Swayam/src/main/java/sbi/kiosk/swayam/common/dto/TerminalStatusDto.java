package sbi.kiosk.swayam.common.dto;

import java.util.Date;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.TerminalStatus;

@Data
public class TerminalStatusDto {
	
	public TerminalStatusDto(){
		
	}
	
	
	public TerminalStatusDto(TerminalStatus  terminalStatus) {
		this.kioskId = terminalStatus.getKioskId();
		//this.kioskSrNo = terminalStatus.getKioskSrNo();
		//this.brCode = terminalStatus.getBrCode();
		//this.cmf = terminalStatus.getCmf();
		this.rmmsConnectivity = terminalStatus.getRmmsConnectivity();
		this.pbPrinterStatus = terminalStatus.getPbPrinterStatus();
		this.cartridgeStatus = terminalStatus.getCartridgeStatus();
		this.antivirusStatus = terminalStatus.getAntivirusStatus();
		this.aplicationStatus = terminalStatus.getAplicationStatus();
		//this.issue = terminalStatus.getIssue();
		this.createdDttm = terminalStatus.getCreatedDttm();
		this.modifiedDttm = terminalStatus.getModifiedDttm();
		this.lastFeedDttm = terminalStatus.getLastFeedDttm();
		this.timeDiff = terminalStatus.getTimeDiff();
		this.lastPrntTxnDttm = terminalStatus.getLastPrntTxnDttm();
		this.lastPmDttm = terminalStatus.getLastPmDttm();
	}
	private String kioskId;
	private String kioskSrNo;
	private String branchCode;
	private String circle;
	private String cmf;
	private String rmmsConnectivity;
	private String pbPrinterStatus;
	private String cartridgeStatus;
	private String antivirusStatus;
	private String aplicationStatus;
	//private String issue;
	private Date createdDttm;  
	private Date modifiedDttm;  
	private Date lastFeedDttm; 
	private Date timeDiff; 
	private Date lastPrntTxnDttm;  
	private Date lastPmDttm;

	

}
