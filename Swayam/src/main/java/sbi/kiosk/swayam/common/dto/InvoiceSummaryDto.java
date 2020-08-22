package sbi.kiosk.swayam.common.dto;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.InvoiceSummaryEntity;

@Data
public class InvoiceSummaryDto {
	
	
	
	public InvoiceSummaryDto() {
		// TODO Auto-generated constructor stub
	}
	
	

	public InvoiceSummaryDto(InvoiceSummaryEntity summary) {
		super();
		this.year = summary.getYear();
		this.vendor = summary.getVendor();
		this.circleName = summary.getCircleName();
		this.state = summary.getState();
		this.q1Im = summary.getQ1Im();
		this.q2Im = summary.getQ2Im();
		this.q3Im = summary.getQ3Im();
		this.q4Im = summary.getQ4Im();
		this.q1P = summary.getQ1P();
		this.q2P = summary.getQ2P();
		this.q3P = summary.getQ3P();
		this.q4P = summary.getQ4P();
		this.q1Ba = summary.getQ1Ba();
		this.q2Ba = summary.getQ2Ba();
		this.q3Ba = summary.getQ3Ba();
		this.q4Ba = summary.getQ4Ba();
	}

	private String year;
	
	
	private String vendor;

	private String circleName;
	
	private String state;
	
	private String q1Im;
	
	private String q2Im;
	
	private String q3Im;
	
	private String q4Im;
	
	private String q1P;
	
	private String q2P;
	
	private String q3P;
	
	private String q4P;
	
	private String q1Ba;
	
	private String q2Ba;
	
	private String q3Ba;
	
	private String q4Ba;

}
