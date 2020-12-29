package sbi.kiosk.swayam.common.dto;

import lombok.Data;

@Data
public class TaxCalculationDto {
	
	
	
	public TaxCalculationDto() {
		
	}
	
	
	private String circleName;	
	



	private String rfpRefNumber;

	private String vendor;
	
	private String state;
	
	private Integer circleCode;
	
	private Integer vendorId;

	private String quarter;
	
	private String finyear;
	
	private Double invoiceAmount;
	
	private Double penaltyAmount;
	
	private String gstType;
	
	private Float gst;
	
	private Double amcGst;
	
	private Double penGst;
	
	private Double toatalGST;
	
	/*
	 * public TaxCalculationDto(String circleName, String rfpRefNumber, String
	 * vendor, String state, Integer circleCode, Integer vendorId, String quarter,
	 * String finyear, double invoiceAmount, double penaltyAmount, String gstType,
	 * double gst, double amcGst, double penGst, double toatalGST) { super();
	 * this.circleName = circleName; this.rfpRefNumber = rfpRefNumber; this.vendor =
	 * vendor; this.state = state; this.circleCode = circleCode; this.vendorId =
	 * vendorId; this.quarter = quarter; this.finyear = finyear; this.invoiceAmount
	 * = invoiceAmount; this.penaltyAmount = penaltyAmount; this.gstType = gstType;
	 * this.gst = gst; this.amcGst = amcGst; this.penGst = penGst; this.toatalGST =
	 * toatalGST; }
	 */

	/*
	 * public TaxCalculationDto(String circleName, String rfpRefNumber, String
	 * vendor, String state, Integer circleCode, Integer vendorId, String quarter,
	 * String finyear, Long invoiceAmount, Long penaltyAmount, String gstType,
	 * Integer gst, Long amcGst, Long penGst, Long toatalGST) { super();
	 * this.circleName = circleName; this.rfpRefNumber = rfpRefNumber; this.vendor =
	 * vendor; this.state = state; this.circleCode = circleCode; this.vendorId =
	 * vendorId; this.quarter = quarter; this.finyear = finyear; this.invoiceAmount
	 * = invoiceAmount; this.penaltyAmount = penaltyAmount; this.gstType = gstType;
	 * this.gst = gst; this.amcGst = amcGst; this.penGst = penGst; this.toatalGST =
	 * toatalGST; }
	 */
	
	
	
	
	
	

}
