package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="TBL_USER_ROLE_PRIVILEGES")
public class UserRolePrivileges {
	
	@Id	
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@Column(name="ROLE")
	private String role;
	
	@Column(name="IDENTITY_METHOD")
	private String identityMethod;
	
	@Column(name="ACCESS_VAL")
	private String access;		
	
}
