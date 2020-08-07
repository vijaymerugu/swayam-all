package sbi.kiosk.swayam.common.dto;

import javax.persistence.Column;


import sbi.kiosk.swayam.common.entity.BillingAllocation;
import sbi.kiosk.swayam.common.entity.BillingPurchaseOrder;


public class BillingAllocationDto {
	public BillingAllocationDto()
	{}
	
	public BillingAllocationDto(BillingAllocation billingAllocation) {
	
		this.allocId = billingAllocation.getAllocId();
		this.repRefNo = billingAllocation.getRepRefNo();
		this.vendorId = billingAllocation.getVendorId();
		this.crclCode = billingAllocation.getCrclCode();
		this.allocatedQuantity = billingAllocation.getAllocatedQuantity();
		this.remainingQuantity = billingAllocation.getRemainingQuantity();
		this.type = billingAllocation.getType();
		this.unitPrice = billingAllocation.getUnitPrice();
		this.status = billingAllocation.getStatus();
		this.createdBy = billingAllocation.getCreatedBy();
		this.createdDate=billingAllocation.getCreatedDate();
		this.updatdBy = billingAllocation.getUpdatdBy();
		this.updatdDate =billingAllocation.getUpdatdDate() ;
	}
	


	private Integer allocId;
	
	private String repRefNo;
	
	private double vendorId;
	
	private double crclCode;
	
	private double allocatedQuantity;
	
	private double remainingQuantity;

	private String type;

	private double unitPrice;

	private String status;
	
	private String createdBy;
	
	private String createdDate;
	
	private String updatdBy;

	private String updatdDate;
	
	// private BillingPurchaseOrder billingPurchaseOrder;
	   

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

	

	public void setCrclCode(Integer crclCode) {
		this.crclCode = crclCode;
	}

	

	public void setRemainingQuantity(Integer remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	



}


