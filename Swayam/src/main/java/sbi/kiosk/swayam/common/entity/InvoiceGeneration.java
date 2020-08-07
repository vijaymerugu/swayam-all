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
	@Column(name="TIME_PERIOD")
	private String timePeriod;
	@Column(name="RFP_REF_NO")
	private String rpfRefNumber;
	
	@Column(name="SPARE_PARTS")
	private String spareParts;
	@Column(name="PENALTY")
	private Float penalty;
	@Column(name="INVOICE_AMOUNT")
	private Float invoiceAmount;
	@Column(name="CORRECTIONS")
	private Float corrections;
	@Column(name="FINAL_AMOUNT")
	private Float finalAmount;
	
	
	

}
