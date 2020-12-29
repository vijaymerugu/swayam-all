package sbi.kiosk.swayam.common.dto;

import java.io.Serializable;

import lombok.Data;


@Data
public class TaxSummaryCompositeId implements Serializable {
	
	
	
private static final long serialVersionUID = 1L;

	private String year;
	
	private String circleName;
	
	private String rfpRefNumber;

	private String vendor;

	private String state;
	
	
	public TaxSummaryCompositeId(){
		
	}


	public TaxSummaryCompositeId(String year, String circleName, String rfpRefNumber, String vendor, String state) {
		super();
		this.year = year;
		this.circleName = circleName;
		this.rfpRefNumber = rfpRefNumber;
		this.vendor = vendor;
		this.state = state;
	}

}
