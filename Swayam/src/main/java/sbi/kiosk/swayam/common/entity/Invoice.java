package sbi.kiosk.swayam.common.entity;

import java.util.Date;

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
	@Column(name="FIN_YR")
	private String finYear;
	@Column(name="QTR_ID")
	private String qauter;
	@Column(name="INVO_DT")
	private Date invoiceDate;
	@Column(name="INVO_FROM")
	private Date invoiceFrom;
	@Column(name="INVO_TO")
	private Date invoiceTo;
	@Column(name="AMC_COST")
	private Float amc;
	@Column(name="SPARE_PARTS_COST")
	private Float sapreParts;
	@Column(name="PENALTY_AMT")
	private Float penalty;
	@Column(name="INVOICE_AMT")
	private Float invoiceAmount;
	@Column(name="CORRECTION_AMT")
	private double corrections;
	@Column(name="FINAL_AMT")
	private Float finalAmount;
	
	@Column(name="REMARKS")
	private String remarks;
	@Column(name="STATUS")
	private String status;
	
	@Column(name="CREATED_BY")
	private String CREATED_BY;
	
	@Column(name="CREATED_DATE")
	private Date CREATED_DATE;
	
	@Column(name="UPDATED_BY")
	private String UPDATED_BY;
	
	@Column(name="UPDATED_DATE")
	private Date UPDATED_DATE;
	
	
	
	

}
