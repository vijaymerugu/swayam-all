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
@Table(name="TBL_ALLOCATION")
public class Allocation {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="seq")
	@SequenceGenerator(initialValue = 1, name = "seq")
	@Column(name = "ALLOC_ID")
	private Integer allocId;

	@Column(name = "RFP_REF_NO")
	private String repRefNo;

	@Column(name = "VENDOR_ID")
	private Integer vendorId;

	@Column(name = "CRCL_CODE")
	private Integer crclCode;

	@Column(name = "ALLOCATED_QUANTITY")
	private long allocatedQuantity;
	
	@Column(name = "REMAINING_QUANTITY")
	private long remainingQuantity;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "UNIT_PRICES")
	private double unitPrice;
	
	@Column(name = "STATUS")
	private String status;
	

	@Column(name = "CREATED_BY")
	private String createdBy;
	
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	
	@Column(name = "UPDATED_BY")
	private String updatdBy;
	
	
	@Column(name = "UPDATED_DATE")
	private Date updatdDate;

}
