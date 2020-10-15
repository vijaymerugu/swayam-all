package sbi.kiosk.swayam.common.dto;

import java.util.Date;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import sbi.kiosk.swayam.billingpayment.repository.AllocationRepository;
import sbi.kiosk.swayam.billingpayment.repository.VendorRepository;
import sbi.kiosk.swayam.common.entity.Allocation;

@Data
@Component
public class AllocationDto {
	@Autowired
	VendorRepository vendorRepo;
	
	public AllocationDto() {
		// TODO Auto-generated constructor stub
	}
	
	public AllocationDto(String vendorName, String circleName) {
		this.vendor=vendorName;
		this.circle=circleName;
	}
	

	public AllocationDto(Allocation allocation) {
		this.allocId = allocation.getAllocId();
		this.repRefNo = allocation.getRepRefNo();
		this.vendorId = allocation.getVendorId();
		this.crclCode = allocation.getCrclCode();
		this.allocatedQuantity = allocation.getAllocatedQuantity();
		this.remainingQuantity = allocation.getRemainingQuantity();
		this.type = allocation.getType();
		this.unitPrice = allocation.getUnitPrice();
		this.status = allocation.getStatus();
		this.createdBy = allocation.getCreatedBy();
		this.createdDate=allocation.getCreatedDate();
		this.updatdBy = allocation.getUpdatdBy();
		this.updatdDate =allocation.getUpdatdDate() ;
		
	}
	
	
	
	
	private Integer allocId;

	private String repRefNo;

	private Integer vendorId;
	
	

	private Integer crclCode;
	
	private String circle;
	
	
	
	private String vendor;

	
	private long allocatedQuantity;

	private long remainingQuantity;
	
	private String type;

	private double unitPrice;
	
	private String status;
	
	private String createdBy;
	
	private Date createdDate;
	
	private String updatdBy;
	
	private Date updatdDate;

}
