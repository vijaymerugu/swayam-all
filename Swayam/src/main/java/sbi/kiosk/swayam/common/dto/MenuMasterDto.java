package sbi.kiosk.swayam.common.dto;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.MenuMaster;

@Data
public class MenuMasterDto {
	
	public MenuMasterDto() {
		
	}
	
	public MenuMasterDto(MenuMaster menuMaster) {
		this.id = menuMaster.getId();
		this.menuId = menuMaster.getMenuId();
		this.name = menuMaster.getName();
		this.parentId = menuMaster.getParentId();
		this.role = menuMaster.getRole();
		this.fileName = menuMaster.getFileName();
		this.url = menuMaster.getUrl();
		this.useYN = menuMaster.getUseYN();
	}
	
	
	private String id;
	
	
	private String menuId;
	
	
	private String name;
	
	
	private String parentId;
	
	
	private String role;
	
	
	private String fileName;
	
	
	private String url;
	
	
	private String useYN;

}
