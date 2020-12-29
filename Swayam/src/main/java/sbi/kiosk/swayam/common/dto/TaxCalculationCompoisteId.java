package sbi.kiosk.swayam.common.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class TaxCalculationCompoisteId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String circleName;
	
	private String rfpRefNumber;

	private String vendor;

	private String state;
	
//	private Integer circleCode;
//	private Integer vendorId;
	
	
	public  TaxCalculationCompoisteId() {

	}


	public TaxCalculationCompoisteId(String circleName, String rfpRefNumber, String vendor, String state) {
		super();
		this.circleName = circleName;
		this.rfpRefNumber = rfpRefNumber;
		this.vendor = vendor;
		this.state = state;
//		this.circleCode = circleCode;
//		this.vendorId = vendorId;
	}


	

}
