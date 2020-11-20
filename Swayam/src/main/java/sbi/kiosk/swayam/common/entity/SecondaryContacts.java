package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TBL_SECONDARY_CONTACTS")
public class SecondaryContacts {
	
	@Id
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESIGNATION")
	private String designation;
	
	@Column(name = "CONTACT_NO")
	private String contactNo;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "TYPE")
	private Integer type;
	

}
