package sbi.kiosk.swayam.common.entity;

import java.util.Date;

import javax.persistence.Column;

import lombok.Data;

@Data
public class SanctionPdfInfo {
	
	private Integer requestId;
	
	
	private String sanctionAuth;

	private String ctlrAuth;
	
	private String recomAuth;
	
	private String circle;
	
	private String state;
	
	private String vendor;
	
	private String sanNoteNo;
	
	private Date sanNoteDt;
	
	private String invoiceNo;
	
	private Date invoiceDt;
	
	private Date invFr;
	
	private Date invTo;
	
	private Double invoiceAmt;
	
	private Date receiptDt;

	private String gstType;
	
	private Float igst;
	
	private Float sgst;
	
	private Float cgst;
	
	private Double penaltyAmt;
	
	private Double gstInvoiceAmt;
	
	private Double gstPenaltyAmt;
	
	private Float tdsPct;
	
	private Double tdsAmt;
	
	private Double gstTdsLimitAmt;
	
	private Float gstTdsPer;
	
	private Double gstTdsAmt;
	
	private Double gstTdsAmt2;
	
	private Double gstTdsAmt3;
	
	private String amtWords;
	
	private String manualEntry;
	
	private String creditEntry;

	private Integer noOfKiosk;
	
	private String quarter;
	
	private String year;
	
	private String vendorFullName;
	
	private Double cgstInvoiceAmt;
	private Double sgstInvoiceAmt;
	private Double igstInvoiceAmt;
	private Double igstPenAmt;
	private Double cgstPenAmt;
	private Double sgstPenAmt;
	private Double totalTax;
	private Double netAmount;
	
	private Double totalamountAfterPenalty;
	
	private Long sanLimit;
	
	private String circularNo;
	
	
	private Date circularDate;
	
	private String circuDt;
	
	private String gstTdsDisplayAmt;
	
	private String hindiVendor;
	
	private Double penaltyWithGst;

	private Double netPaybaleAmount;
	
	private Double netGst;
	
	private String circularSlNo;

}
