package sbi.kiosk.swayam.common.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class TaxCalCompositeId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer circleCode;
	
	private String circleName;
	
	private Integer vendorId;
	
	private String state;
	
	private String vendor;

	private String finyear;

	private String quarter;
	
	private String rfpRefNumber;
	
	public TaxCalCompositeId() {
		// TODO Auto-generated constructor stub
	}

	public TaxCalCompositeId(Integer circleCode, String circleName, Integer vendorId, String state, String vendor,
			String finyear, String quarter, String rfpRefNumber) {
		super();
		this.circleCode = circleCode;
		this.circleName = circleName;
		this.vendorId = vendorId;
		this.state = state;
		this.vendor = vendor;
		this.finyear = finyear;
		this.quarter = quarter;
		this.rfpRefNumber = rfpRefNumber;
	}

}
