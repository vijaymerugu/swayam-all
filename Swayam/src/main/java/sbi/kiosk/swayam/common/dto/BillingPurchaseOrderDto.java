package sbi.kiosk.swayam.common.dto;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.BillingAllocation;


@Data
public class BillingPurchaseOrderDto {
	
	private Integer poNumber;

	private Integer allocId;

	private String poDate;

	private Integer poQantity;

	private String status;
	
	private String createdBy;
	
	private String createdDate;
	
	private String updatdBy;

	private String updatdDate;

}


