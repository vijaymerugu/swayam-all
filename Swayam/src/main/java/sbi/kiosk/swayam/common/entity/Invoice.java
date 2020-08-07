package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;



@Data
@Entity
@Table(name="TBL_INVOICE")
public class Invoice {
	
	
	
	@Id
	@Column(name="KIOSK_ID")
	private String kiokId;
	@Column(name="KIOSK_SERIAL_NO")
	private String kioskSerialNumber;
	@Column(name="FIN_YEAR")
	private String finYear;
	@Column(name="INVO_DATE")
	private String invoiceDate;
	@Column(name="INVO_FROM")
	private String invoiceFrom;
	@Column(name="INVO_TO")
	private String invoiceTo;
	@Column(name="AMC")
	private String amc;
	@Column(name="SPARE_PARTS")
	private String sapreParts;
	@Column(name="PENALTY")
	private Float penalty;
	@Column(name="INVOICE_AMOUNT")
	private Float invoiceAmount;
	@Column(name="CORRECTIONS")
	private double corrections;
	@Column(name="FINAL_AMOUNT")
	private Float finalAmount;
	@Column(name="TIME_PERIOD")
	private String timePeriod;
	@Column(name="REMARKS")
	private String remarks;
	
	

}
