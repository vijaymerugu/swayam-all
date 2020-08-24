package sbi.kiosk.swayam.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class InvoiceCompare  {
	
	@Id
	@Column(name="KIOSK_ID")
	private String kisokId;
	@Column(name="VENDOR")
	private String vendor;
	@Column(name="CRCL_NAME")
	private String circleName;
	@Column(name="STAT_DESC")
	private String state;
	@Column(name="KIOSK_SERIAL_NO")
	private String kioskSerialNumber;
	@Column(name="QTR_ID")
	private String quarterId;
	@Column(name="FIN_YR")
	private String year;
	@Column(name="RFP_REF_NO")
	private String rpfRefNumber;
	@Column(name="INVOICE_AMT")
	private Float invoiceAmountSBI;
	@Column(name="INVO_AMT")
	private Float invoiceAmountVendor;
	
	@Column(name="DIFFERENCE")
	private Float difference;
	
	
	
	

}
