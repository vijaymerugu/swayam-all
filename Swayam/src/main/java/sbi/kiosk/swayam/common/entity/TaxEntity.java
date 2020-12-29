package sbi.kiosk.swayam.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TBL_TAX")
@IdClass(value = TaxCalCompositeId.class)
public class TaxEntity implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CRCL_CODE")
	private Integer circleCode;
	
	@Id
	@Column(name="CRCL_NAME")
	private String circleName;
	
	@Id
	@Column(name="VENDOR_ID")
	private Integer vendorId;
	
	@Id
	@Column(name="STAT_DESC")
	private String state;
	
	@Id
	@Column(name="VENDOR")
	private String vendor;
	
	@Id
	@Column(name="FIN_YR")
	private String finyear;
	
	@Id
	@Column(name="QTR_ID")
	private String quarter;
	
	@Id
	@Column(name="RFP_NO")
	private String rfpRefNumber;
	
	@Column(name="GST_TYPE")
	private String gstType;
	
	@Column(name="IGST_PCT")
	private Float gst;
	
	@Column(name="SGST_PCT")
	private Float sgst;
	
	@Column(name="CGST_PCT")
	private Float cgst;
	
	@Column(name="INVOICE_AMT")
	private Double invoiceAmount;
	
	@Column(name="PENALTY_AMT")
	private Double penaltyAmount;
	
	@Column(name="GST_INVOICE_AMT")
	private Double amcGst;
	
	@Column(name="GST_PENALTY_AMT")
	private Double penGst;
	
	@Column(name="TOTAL_GST")
	private Double toatalGST;
	

}
