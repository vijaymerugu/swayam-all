package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="TBL_ROLE")
public class Role {

	@Id
	@Column(name="ROLE_ID")
	private String roleId;
	
	@Column(name="ROLE")
	private String role;
	
	@Column(name="ROLE_DESC")
	private String roleDesc;
	
	@Column(name="CREATED_DATE")
	private String createdDate;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	

}
