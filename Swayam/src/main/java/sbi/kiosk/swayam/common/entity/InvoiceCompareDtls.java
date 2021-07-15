package sbi.kiosk.swayam.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="TBL_INVOICE_COMPARE_DTLS")
public class InvoiceCompareDtls  {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="seq")
	@SequenceGenerator(initialValue = 1, name = "seq")
	@Column(name="REQUEST_ID")
	private Integer requestId;
	
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
	@Column(name="RFP_NO")
	private String rpfRefNumber;
	@Column(name="AMC_COST")
	private Double invoiceAmountSBI;
	@Column(name="AMC_AMT")
	private Double invoiceAmountVendor;	
	@Column(name="PENALTY_SBI_AMT")
	private Double penaltyAmountSBI;
	@Column(name="CORRECTION_AMT")
	private Double correctionAmount;
	@Column(name="LAST_CORRECTION_AMT")
	private Double lastcorrectionAmount;
	@Column(name="VENDOR_PENALTY_AMT")
	private Double penaltyAmountVendor;
	@Column(name="DIFFERENCE")
	private Double difference;
	@Column(name="FINAL_AMT")
	private Double finalAmount;
	@Column(name="REMARKS")
	private String remarks;
	@Column(name="SPARE_PARTS_COST")
	private Double amcSpareParts;
	
}
