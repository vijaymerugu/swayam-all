package sbi.kiosk.swayam.common.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class InvoiceSummaryCompositeId implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String year;
	
	
	private String vendor;

	private String circleName;
	
	private String state;
	
	public InvoiceSummaryCompositeId(){
		
		
	}

	public InvoiceSummaryCompositeId(String year, String vendor, String circleName, String state) {
		super();
		this.year = year;
		this.vendor = vendor;
		this.circleName = circleName;
		this.state = state;
	}

}
