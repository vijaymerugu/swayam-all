package sbi.kiosk.swayam.healthmonitoring.model;

import lombok.Data;

@Data
public class RfpResponse {
	
	private String status;
	

	public RfpResponse(String status) {
		super();
		this.status = status;
	}

}
