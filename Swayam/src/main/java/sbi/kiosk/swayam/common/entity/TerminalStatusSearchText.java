package sbi.kiosk.swayam.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class TerminalStatusSearchText {

	@Id
	@Column(name = "KIOSK_ID")
	private String kioskId;
	@Column(name = "KIOSK_SERIAL_NO")
	private String kioskSerialNo;
	@Column(name = "RMS_TICKET_NUMBER")
	private String rmmsConnectivity;
	@Column(name = "PRINTER_STATUS")
	private String pbPrinterStatus;
	@Column(name = "CARTRIDGE_STATUS")
	private String cartridgeStatus;
	@Column(name = "AGENT_STATUS")
	private String agentStatus;
	@Column(name = "APPLICATION_STATUS")
	private String aplicationStatus;
	@Column(name = "LAST_PRNT_TXN_DTTM")
	private Date lastPrntTxnDttm;
	@Column(name = "LAST_PM_DTTM")
	private Date lastPmDttm;

	@Column(name = "CRCL_NAME")
	private String crclName;

	@Column(name = "BRANCH_CODE")
	private String branchCode;

	@Column(name = "USERNAME")
	private String username;

	public String getCmf() {
		// TODO Auto-generated method stub
		return null;
	}

}
