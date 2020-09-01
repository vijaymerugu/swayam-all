package sbi.kiosk.swayam.common.entity;

import java.util.Date;

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
	@Column(name="VENDOR_ID")
	private Integer vendorId;
	
	@Column(name="COMPANY_SHORT_NM")
	private String vendor;
	
	@Column(name="COMPANY_NM")
	private String comapnyName;
	
	@Column(name="CONTACT_NAME")
	private String contactName;
	
	@Column(name="CONTACT_ADDRESS")
	private String contactAddress;
	
	@Column(name="VENDOR_EMAIL")
	private String vendorEmail;
	
	@Column(name="VENDOR_MOBILE")
	private Long vendorMobile;
	
	@Column(name="STATUS")
	private String status;
	
	
	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "UPDATED_BY")
	private String updatdBy;

	@Column(name = "UPDATED_DATE")
	private Date updatdDate;
	
	

	
	

}
