package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="TBL_VENDOR_DETAILS")
public class VendorMaster {
	@Id
	@Column(name="VEND_ID")
	private Integer vendorId;
	
	@Column(name="VENDOR")
	private String vendor;
	
	public VendorMaster() {
		
	}
	
	public VendorMaster(Integer vendorId, String vendor) {
		super();
		this.vendorId = vendorId;
		this.vendor = vendor;
	}

}
