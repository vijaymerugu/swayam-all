package sbi.kiosk.swayam.common.dto;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.Role;

@Data
public class RolesDto {
	
	public RolesDto(Role roleEnty) {
		this.roleId = roleEnty.getRoleId();
		this.role = roleEnty.getRole();
		this.roleDescription = roleEnty.getRoleDesc();
		this.createdDate = roleEnty.getCreatedDate();
		this.createdBy = roleEnty.getCreatedBy();
	}
	
	private String roleId;
	private String role;
	private String roleDescription;
	private String createdDate;
	private String createdBy;
	
}
