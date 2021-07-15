package sbi.kiosk.swayam.common.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class BpCompoisteID  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String kisokId;

	private String vendor;

	private String circleName;
	
	private String state;
	
	public BpCompoisteID() {
	
	}

	public BpCompoisteID(String kisokId, String vendor, String circleName, String state) {
		super();
		this.kisokId = kisokId;
		this.vendor = vendor;
		this.circleName = circleName;
		this.state = state;
	}

}
