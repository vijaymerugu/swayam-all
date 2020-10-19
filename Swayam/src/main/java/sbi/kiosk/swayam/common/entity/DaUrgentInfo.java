package sbi.kiosk.swayam.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
public class DaUrgentInfo {
	@Id
	@Column(name="INFO_ID")
	private String info_Id;
	
	@Column(name="MESSAGE")
	private String message;
	
	/*
	 * @Column(name="STATUS") private String status;
	 * 
	 * @Column(name="CREATED_DATE") private Date createdDate;
	 * 
	 * @Column(name="UPDATED_DATE") private Date updatedDate;
	 */

}
