package sbi.kiosk.swayam.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
public class Vendor {
	

	@Id
	@Column(name="VENDOR_ID")
	private Integer vendorId;
	
	@Column(name="COMPANY_SHORT_NM")
	private String vendor;
	
	
	

	
	

}
