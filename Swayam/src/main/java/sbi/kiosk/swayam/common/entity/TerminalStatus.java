package sbi.kiosk.swayam.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="TBL_TERMINAL_STATUS")
public class TerminalStatus{

	@Id
	@Column(name="KIOSK_ID")
	private String kioskId;
	@Column(name="RMS_TICKET_NUMBER")
	private String rmmsConnectivity;
	@Column(name="PRINTER_STATUS")
	private String pbPrinterStatus;
	@Column(name="CARTRIDGE_STATUS")
	private String cartridgeStatus;
	@Column(name="AGENT_STATUS")
	private String antivirusStatus;
	@Column(name="APPLICATION_STATUS")
	private String aplicationStatus;
	@Column(name="CREATED_DTTM")
	private Date createdDttm;  
	@Column(name="MODIFIED_DTTM")
	private Date modifiedDttm;  
	@Column(name="LAST_FEED_DTTM")
	private Date lastFeedDttm; 
	@Column(name="TIME_DIFF")
	private Date timeDiff; 
	@Column(name="LAST_PRNT_TXN_DTTM")
	private Date lastPrntTxnDttm;  
	@Column(name="LAST_PM_DTTM")
	private Date lastPmDttm;
	

}
