package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
@Data
@Entity
public class InvoiceGeneration {
	
	
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
	@Column(name="FIN_YR")
	private String year;
	@Column(name="QTR_ID")
	private String quarterId;
	@Column(name="RFP_NO")
	private String rpfRefNumber;
//	@Column(name="SPARE_PARTS")
//	private String spareParts;
	@Column(name="SPARE_PARTS")
	private Float spareParts;
	@Column(name="PENALTY_AMT")
	private Float penalty;
	@Column(name="INVOICE_AMT")
	private Float invoiceAmount;
	@Column(name="CORRECTION_AMT")
	private Float corrections;
	@Column(name="FINAL_AMT")
	private Float finalAmount;
	
	@Column(name="FINAL_PENALTY")
	private Float finalPenalty;
	
	
	

}
