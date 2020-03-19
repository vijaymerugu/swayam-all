package sbi.kiosk.swayam.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class Common {
	
	@Column(name="CREATED_BY")
	public String createdBy;
	
	@Column(name="CREATED_DATE")
	public Date createdDate;
	
	@Column(name="MODIFIED_BY")
	public String modifiedBy;
	
	@Column(name="MODIFIED_DATE")
	public Date modifiedDate;
	

}
