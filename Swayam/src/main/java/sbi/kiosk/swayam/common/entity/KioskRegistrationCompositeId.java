package sbi.kiosk.swayam.common.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class KioskRegistrationCompositeId implements Serializable {
	
	

	private static final long serialVersionUID = 1L;
	
	private String circle;
	private String branchCode;
	private String vendor;
	private String kioskId;

	public KioskRegistrationCompositeId() {
		// TODO Auto-generated constructor stub
	}
	public KioskRegistrationCompositeId(String circle,String branchCode,String vendor, String kioskId) {
		super();
		this.circle = circle;
		this.branchCode = branchCode;
		this.vendor = vendor;
		this.kioskId = kioskId;
	}

}
