package sbi.kiosk.swayam.common.dto;

import java.util.Date;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.TerminalStatusSearchText;

@Data
public class TerminalStatusSearchTextDto {
	
public TerminalStatusSearchTextDto(){
		
	}
	
	
	public TerminalStatusSearchTextDto(TerminalStatusSearchText  terminalStatus) {
		this.kioskId = terminalStatus.getKioskId();
		this.kioskSerialNo = terminalStatus.getKioskSerialNo();
		this.brCode = terminalStatus.getBranchCode();
		this.username = terminalStatus.getUsername();
		this.rmmsConnectivity = terminalStatus.getRmmsConnectivity();
		this.pbPrinterStatus = terminalStatus.getPbPrinterStatus();
		this.cartridgeStatus = terminalStatus.getCartridgeStatus();
		//this.antivirusStatus = terminalStatus.getAgentStatus();
		this.agentStatus = terminalStatus.getAgentStatus();
		this.aplicationStatus = terminalStatus.getAplicationStatus();
		//this.issue = terminalStatus.getIssue();
		//this.createdDttm = terminalStatus.getCreatedDttm();
		//this.modifiedDttm = terminalStatus.getModifiedDttm();
		//this.lastFeedDttm = terminalStatus.getLastFeedDttm();
		//this.timeDiff = terminalStatus.getTimeDiff();
		this.lastPrntTxnDttm = terminalStatus.getLastPrntTxnDttm();
		this.lastPmDttm = terminalStatus.getLastPmDttm();
		this.crclName=terminalStatus.getCrclName();
	}
	private String kioskId;
	private String kioskSerialNo;
	private String branchCode;
	private String crclName;
	private String username;
	private String rmmsConnectivity;
	private String pbPrinterStatus;
	private String cartridgeStatus;
	//private String antivirusStatus;
	private String agentStatus;
	private String aplicationStatus;
	//private String issue;
	//private Date createdDttm;  
	//private Date modifiedDttm;  
	//private Date lastFeedDttm; 
	//private String timeDiff; 
	private Date lastPrntTxnDttm;  
	private Date lastPmDttm;
	private String brCode;

	

}
