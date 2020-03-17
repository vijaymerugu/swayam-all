package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="TBL_MENU_MASTER")
public class MenuMaster {
	
	@Id
	@Column(name="ID")
	private String id;
	
	@Column(name="MENU_ID")
	private String menuId;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="PARENT_ID")
	private String parentId;
	
	@Column(name="ROLE")
	private String role;
	
	@Column(name="FILE_NAME")
	private String fileName;
	
	@Column(name="URL")
	private String url;
	
	@Column(name="USE_YN")
	private String useYN;
	

}
