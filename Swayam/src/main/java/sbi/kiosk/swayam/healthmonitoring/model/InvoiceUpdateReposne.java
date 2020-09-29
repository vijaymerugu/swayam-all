package sbi.kiosk.swayam.healthmonitoring.model;

import lombok.Data;

@Data
public class InvoiceUpdateReposne {
	
	
	private String status;
	private String message;
	
	
	public InvoiceUpdateReposne() {
		// TODO Auto-generated constructor stub
	}


	public InvoiceUpdateReposne(String status, String message) {
		super();
		
		this.status = status;
		this.message = message;
		
	}

}
