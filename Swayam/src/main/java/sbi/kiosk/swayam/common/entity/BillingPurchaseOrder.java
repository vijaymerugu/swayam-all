package sbi.kiosk.swayam.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TBL_PURCHASE_ORDER")
public class BillingPurchaseOrder implements Serializable {

	/*
	 * @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
	 * "SEQ_TBL_PURCHASE_ORDER")
	 * 
	 * @SequenceGenerator(sequenceName = "SEQ_TBL_PURCHASE_ORDER", allocationSize =
	 * 1, name = "SEQ_TBL_PURCHASE_ORDER")
	 */

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "Billing_ALLOC_ID")
	private Integer billingallocId;

	@Column(name = "PO_NUMBER")
	private double poNumber;

	@Column(name = "PO_DATE")
	private String poDate;

	@Column(name = "PO_QUANTITY")
	private double poQantity;

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

	@OneToOne(mappedBy = "billingPurchaseOrder")

	@JsonIgnore
	private BillingAllocation billingAllocation;

	public BillingAllocation getBillingAllocation() {
		return billingAllocation;
	}

	public void setBillingAllocation(BillingAllocation billingAllocation) {
		this.billingAllocation = billingAllocation;
	}

	public Integer getBillingallocId() {
		return billingallocId;
	}

	public void setBillingallocId(Integer billingallocId) {
		this.billingallocId = billingallocId;
	}

	public String getPoDate() {
		return poDate;
	}

	public void setPoDate(String poDate) {
		this.poDate = poDate;
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

	public double getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(double poNumber) {
		this.poNumber = poNumber;
	}

	public double getPoQantity() {
		return poQantity;
	}

	public void setPoQantity(double poQantity) {
		this.poQantity = poQantity;
	}

	public BillingPurchaseOrder() {
	}

	public BillingPurchaseOrder(Integer billingallocId, double poQantity, String status,
			BillingAllocation billingAllocation) {
		super();
		this.billingallocId = billingallocId;
		this.poQantity = poQantity;
		this.status = status;
		this.billingAllocation = billingAllocation;
	}

}
