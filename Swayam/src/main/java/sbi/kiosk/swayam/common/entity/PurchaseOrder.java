package sbi.kiosk.swayam.common.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
public class PurchaseOrder {
	
	@Id
	@Column(name = "PO_ID")
	private Integer poId;
	
	@Column(name = "ALLOC_ID")
	private Integer allocId;	
	
	@Column(name = "PO_NUMBER")
	private Integer poNumber;

	@Column(name = "RFP_REF_NO")
	private String rfpRefNo;

	@Column(name = "VENDOR_ID")
	private Integer vendorId;

	@Column(name = "COMPANY_SHORT_NM")
	private String vendor;
	
	@Column(name = "CRCL_CODE")
	private Integer crclCode;
	
	@Column(name = "CONTACT_ADDRESS")
	private String address;
	
	@Column(name = "CONTACT_NAME")
	private String contactName;
	
	@Column(name = "CRCL_NAME")
	private String circle;


	@Column(name = "ALLOCATED_QUANTITY")
	private long allocatedQuantity;

	@Column(name = "QUANTITY")
	private long poQuantity;

	@Column(name = "REMAINING_QUANTITY")
	private long remainingQuantity;
	
	@Column(name = "PODATE")
	private String poDate;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "RFPDATE")
	private String rfpDate;

}
