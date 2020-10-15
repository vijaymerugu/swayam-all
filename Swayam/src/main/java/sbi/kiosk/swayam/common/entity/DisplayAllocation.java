package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class DisplayAllocation {
	
	@Id
	@Column(name = "ALLOC_ID")
	private Integer allocId;

	@Column(name = "RFP_REF_NO")
	private String repRefNo;
	
	
	@Column(name = "COMPANY_SHORT_NM")
	private String vendor;
	
	@Column(name = "CRCL_NAME")
	private String circle;

	@Column(name = "VENDOR_ID")
	private Integer vendorId;

	@Column(name = "CRCL_CODE")
	private Integer crclCode;

	@Column(name = "ALLOCATED_QUANTITY")
	private long allocatedQuantity;
	
	@Column(name = "REMAINING_QUANTITY")
	private long remainingQuantity;
	
	@Column(name = "PODATE")
	private String poDate;
	
	
}
