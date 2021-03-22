package sbi.kiosk.swayam.common.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class KioskBranchMasterCompositeId implements Serializable {
	
	

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String kioskId;

	public KioskBranchMasterCompositeId() {
		// TODO Auto-generated constructor stub
	}
	public KioskBranchMasterCompositeId(Integer id, String kioskId) {
		super();
		this.id = id;
		this.kioskId = kioskId;
	}

}
