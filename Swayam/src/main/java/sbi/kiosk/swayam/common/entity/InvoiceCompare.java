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
	@Column(name="RFP_NO")
	private String rpfRefNumber;
	@Column(name="AMC_COST")
	private Double invoiceAmountSBI;
	@Column(name="SPARE_PARTS_COST")
	private Double amcSpareParts;
	@Column(name="AMC_AMT")
	private Double invoiceAmountVendor;	
	@Column(name="PENALTY_SBI_AMT")
	private Double penaltyAmountSBI;
	@Column(name="CORRECTION_AMT")
	private Double correctionAmount;
	@Column(name="VENDOR_PENALTY_AMT")
	private Double penaltyAmountVendor;
	@Column(name="DIFFERENCE")
	private Double difference;
	
	/*
	 * @Column(name="AMC_COST") private Float invoiceAmountSBI;
	 * 
	 * @Column(name="AMC_AMT") private Float invoiceAmountVendor;
	 * 
	 * @Column(name="PENALTY_SBI_AMT") private Float penaltyAmountSBI;
	 * 
	 * @Column(name="CORRECTION_AMT") private Float correctionAmount;
	 * 
	 * @Column(name="VENDOR_PENALTY_AMT") private Float penaltyAmountVendor;
	 * 
	 * @Column(name="DIFFERENCE") private Float difference;
	 */
	
	
	
	

}
