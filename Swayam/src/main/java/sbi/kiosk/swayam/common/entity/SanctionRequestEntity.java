package sbi.kiosk.swayam.common.entity;

import java.util.Date;

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
@Table(name="TBL_SANCTION_DTLS")
public class SanctionRequestEntity {
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="seq")
	@SequenceGenerator(initialValue = 1, name = "seq")
	@Column(name="REQUEST_ID")
	private Integer requestId;
	
	@Column(name="SAN_AUTH")
	private String sanctionAuth;
	
	@Column(name="CTRL_AUTH")
	private String ctlrAuth;
	
	@Column(name="REC_AUTH")
	private String recomAuth;
	
	@Column(name="CRCL_NAME")
	private String circle;
	
	@Column(name="STAT_DESC	")
	private String state;
	
	@Column(name="VENDOR")
	private String vendor;
	
	@Column(name="SAN_NOTE_NO")
	private String sanNoteNo;
	
	@Column(name="SAN_NOTE_DT")
	private Date sanNoteDt;
	
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
	
	@Column(name="GST_TYPE")
	private String gstType;
	
	@Column(name="IGST_PCT")
	private Float igst;
	
	@Column(name="SGST_PCT")
	private Float sgst;
	
	@Column(name="CGST_PCT")
	private Float cgst;
	
	@Column(name="PENALTY_AMT")
	private Double penaltyAmt;
	
	@Column(name="GST_INVOICE_AMT")
	private Double gstInvoiceAmt;
	
	@Column(name="GST_PENALTY_AMT")
	private Double gstPenaltyAmt;
	
	@Column(name="TDS_PCT")
	private Float tdsPct;
	
	@Column(name="TDS_AMT")
	private Double tdsAmt;
	
	@Column(name="GST_TDS_LIMIT_AMT")
	private Double gstTdsLimitAmt;
	
	@Column(name="GST_TDS_PCT")
	private Float gstTdsPer;
	
	@Column(name="GST_TDS_AMT")
	private Double gstTdsAmt;
	
	/*
	 * @Column(name="AMT_WORDS") private String amtWords;
	 */
	
	@Column(name="MANUAL_ENTRY")
	private String manualEntry;
	
	@Column(name="CREDIT_ENTRY")
	private String creditEntry;
	
	@Column(name="NO_KIOSK")
	private Integer noOfKiosk;
	
	@Column(name="QTR_ID")
	private String quarter;
	
	@Column(name="FIN_YR")
	private String year;
	
	@Column(name="SANCTION_LIMIT")
	private Long sanLimit;
	
	@Column(name="CIRCULAR_NO")
	private String circularNo;
	
	@Column(name="CIRCULAR_DT")
	private Date circularDate;
	
	@Column(name="CIRCULAR_SL_NO")
	private String circularSlNo;

}
