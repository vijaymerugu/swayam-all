package sbi.kiosk.swayam.common.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name="TBL_SANCTION_DTLS")
public class SanctionRequestEntity {
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="seq")
	@SequenceGenerator(initialValue = 1, name = "seq")
	@Column(name="REQUEST_ID")
	private Integer requestId;
	
	@NotNull
	@Column(name="SAN_AUTH")
	private String sanctionAuth;
	
	@NotNull
	@Column(name="CTRL_AUTH")
	private String ctlrAuth;
	
	@NotNull
	@Column(name="REC_AUTH")
	private String recomAuth;
	
	@NotNull
	@Column(name="CRCL_NAME")
	private String circle;
	
	@NotNull
	@Column(name="STAT_DESC	")
	private String state;
	
	@NotNull
	@Column(name="VENDOR")
	private String vendor;
	
	
	@Column(name="SAN_NOTE_NO")
	private String sanNoteNo;
	
	@Column(name="SAN_NOTE_DT")
	private Date sanNoteDt;
	
	@NotNull
	@Size(min = 15 ,max = 15, message="Please enter correct format {min}-{max} character")
	@Pattern(regexp="^[A-Z]{3}\\/[0-9]{4}\\/[0-9]{6}$",message="length must be 15") 
	@Column(name="INVO_NO")
	private String invoiceNo;
	
	@Column(name="INVO_DT")
	private Date invoiceDt;
	
	@Column(name="INVO_FROM")
	private Date invFr;
	
	@Column(name="INVO_TO")
	private Date invTo;
	
	@Column(name="INVOICE_AMT")
	private Double invoiceAmt;
	
	@Column(name="RECEIPT_DT")
	private Date receiptDt;
	
	@NotNull
	@Column(name="GST_TYPE")
	private String gstType;
	
	
	/*
	 * @Column(name="IGST_PCT") private Float igst;
	 * 
	 * @Column(name="SGST_PCT") private Float sgst;
	 * 
	 * @Column(name="CGST_PCT") private Float cgst;
	 */
	 
	
	@DecimalMin(value = "0.0", inclusive = true)
	@DecimalMax(value = "20.00", inclusive = true)
	@Digits(integer = 2, fraction = 2)
	@Column(name = "IGST_PCT")
	private BigDecimal igst;

	@DecimalMin(value = "0.0", inclusive = true)
	@DecimalMax(value = "10.00", inclusive = true)
	@Digits(integer = 2, fraction = 2)
	@Column(name = "SGST_PCT")
	private BigDecimal sgst;

	@DecimalMin(value = "0.0", inclusive = true)
	@DecimalMax(value = "10.00", inclusive = true)
	@Digits(integer = 2, fraction = 2)
	@Column(name = "CGST_PCT")
	private BigDecimal cgst;
	 
	
	@Column(name="PENALTY_AMT")
	private Double penaltyAmt;
	
	@Column(name="GST_INVOICE_AMT")
	private Double gstInvoiceAmt;
	
	@Column(name="GST_PENALTY_AMT")
	private Double gstPenaltyAmt;
	
	
	/*
	 * @Column(name="TDS_PCT") private Float tdsPct;
	 * 
	 * @Column(name="TDS_AMT") private Double tdsAmt;
	 * 
	 * @Column(name="GST_TDS_LIMIT_AMT") private Double gstTdsLimitAmt;
	 * 
	 * @Column(name="GST_TDS_PCT") private Float gstTdsPer;
	 * 
	 * @Column(name="GST_TDS_AMT") private Double gstTdsAmt;
	 */
	 
	
	
	@DecimalMin(value = "0.0", inclusive = true)
	@DecimalMax(value = "5.00", inclusive = true)
	@Digits(integer = 1, fraction = 2)
	@Column(name = "TDS_PCT")
	private BigDecimal tdsPct;

	@Column(name = "TDS_AMT")
	private BigDecimal tdsAmt;

	@DecimalMin(value = "250000.0", inclusive = true)
	@DecimalMax(value = "1000000.00", inclusive = true)
	@Digits(integer = 7, fraction = 2)
	@Column(name = "GST_TDS_LIMIT_AMT")
	private BigDecimal gstTdsLimitAmt;

	@DecimalMin(value = "0.0", inclusive = true)
	@DecimalMax(value = "7.00", inclusive = true)
	@Digits(integer = 1, fraction = 2)
	@Column(name = "GST_TDS_PCT")
	private BigDecimal gstTdsPer;

	@Column(name = "GST_TDS_AMT")
	private BigDecimal gstTdsAmt;
	
	/*
	 * @Column(name="AMT_WORDS") private String amtWords;
	 */
	
	
	@Size(max=200,message="criteria not met")
	@Column(name="MANUAL_ENTRY")
	private String manualEntry;
	
	
	@Size(max=200,message="criteria not met")
	@Column(name="CREDIT_ENTRY")
	private String creditEntry;
	
	@Column(name="NO_KIOSK")
	private Integer noOfKiosk;
	
	@NotNull
	@Column(name="QTR_ID")
	private String quarter;
	
	@NotNull
	@Column(name="FIN_YR")
	private String year;
	
	@Min(10000)
	@Max(99999999)
	@Column(name="SANCTION_LIMIT")
	private Long sanLimit;
	
	@NotNull
	@Size(min = 21 ,max = 21, message="Please enter correct format {min}-{max} character")
	@Pattern(regexp="^[A-Z]{3}\\/[A-Z]{3}\\-[A-Z]{3}\\/[0-9]{1}\\/[0-9]{4}\\-[0-9]{2}$",message="length must be 21") 
	@Column(name="CIRCULAR_NO")
	private String circularNo;
	
	@Column(name="CIRCULAR_DT")
	private Date circularDate;
	
	@NotNull
	@Size(min = 12 ,max = 12, message="Please enter correct format {min}-{max} character")
	@Pattern(regexp="^[0-9]{4}\\/[0-9]{4}\\-[0-9]{2}$",message="length must be 12") 
	@Column(name="CIRCULAR_SL_NO")
	private String circularSlNo;

}
