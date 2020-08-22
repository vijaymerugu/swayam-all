package sbi.kiosk.swayam.healthmonitoring.model;

import lombok.Data;

@Data
public class InvoiceUpdateReposne {
	
	
	private String status;
	private String messgae;
	
	
	
	public InvoiceUpdateReposne() {
		// TODO Auto-generated constructor stub
	}


	public InvoiceUpdateReposne(String status, String messgae) {
		super();
		
		this.status = status;
		this.messgae = messgae;
		
	}

}
