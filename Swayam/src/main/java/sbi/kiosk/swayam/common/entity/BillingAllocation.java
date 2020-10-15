package sbi.kiosk.swayam.common.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "TBL_ALLOCATION")
public class BillingAllocation {

	@Id
	
	  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
	  "SEQ_TBL_ALLOCATION")
	  
	  @SequenceGenerator(sequenceName = "SEQ_TBL_ALLOCATION", allocationSize = 1,
	  name = "SEQ_TBL_ALLOCATION")
	 

	
	@Column(name = "ALLOC_ID")
	private Integer allocId;

	@Column(name = "RFP_REF_NO")
	private String repRefNo;

	@Column(name = "VENDOR_ID")
	private double vendorId;

	@Column(name = "CRCL_CODE")
	private double crclCode;

	@Column(name = "ALLOCATED_QUANTITY")
	private double allocatedQuantity;//Integer

	@Column(name = "REMAINING_QUANTITY")
	private double remainingQuantity;//Integer

	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "UNIT_PRICES")
	private double unitPrice;
	
	@Column(name = "STATUS")
	private String status;
	

	@Column(name = "CREATED_BY")
	private String createdBy;
	
	
	@Column(name = "CREATED_DATE")
	private String createdDate;
	
	
	@Column(name = "UPDATED_BY")
	private String updatdBy;
	
	
	@Column(name = "UPDATED_DATE")
	private String updatdDate;

	
	
	
	   
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
    @JoinColumn(name = "ALLOC_ID", referencedColumnName = "Billing_ALLOC_ID")
    private BillingPurchaseOrder billingPurchaseOrder;

	 public BillingAllocation()
	 {
	 }



	public Integer getAllocId() {
		return allocId;
	}



	public void setAllocId(Integer allocId) {
		this.allocId = allocId;
	}



	public String getRepRefNo() {
		return repRefNo;
	}



	public void setRepRefNo(String repRefNo) {
		this.repRefNo = repRefNo;
	}



	public double getVendorId() {
		return vendorId;
	}



	public void setVendorId(double vendorId) {
		this.vendorId = vendorId;
	}



	public double getCrclCode() {
		return crclCode;
	}



	public void setCrclCode(double crclCode) {
		this.crclCode = crclCode;
	}



	public double getAllocatedQuantity() {
		return allocatedQuantity;
	}



	public void setAllocatedQuantity(double allocatedQuantity) {
		this.allocatedQuantity = allocatedQuantity;
	}



	public double getRemainingQuantity() {
		return remainingQuantity;
	}



	public void setRemainingQuantity(double remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public double getUnitPrice() {
		return unitPrice;
	}



	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}



	public String getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}



	public String getUpdatdBy() {
		return updatdBy;
	}



	public void setUpdatdBy(String updatdBy) {
		this.updatdBy = updatdBy;
	}



	public String getUpdatdDate() {
		return updatdDate;
	}



	public void setUpdatdDate(String updatdDate) {
		this.updatdDate = updatdDate;
	}

	public BillingPurchaseOrder getBillingPurchaseOrder() {
		return billingPurchaseOrder;
	}

	public void setBillingPurchaseOrder(BillingPurchaseOrder billingPurchaseOrder) {
		this.billingPurchaseOrder = billingPurchaseOrder;
	}

	public BillingAllocation(Integer allocId, String repRefNo, double vendorId, double crclCode,
			double allocatedQuantity, double remainingQuantity, String type, double unitPrice, String status,
			String createdBy, String createdDate, String updatdBy, String updatdDate) {
		super();
		this.allocId = allocId;
		this.repRefNo = repRefNo;
		this.vendorId = vendorId;
		this.crclCode = crclCode;
		this.allocatedQuantity = allocatedQuantity;
		this.remainingQuantity = remainingQuantity;
		this.type = type;
		this.unitPrice = unitPrice;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatdBy = updatdBy;
		this.updatdDate = updatdDate;
	}
	 



	 

 


	
	
}
