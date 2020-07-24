package sbi.kiosk.swayam.common.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Entity
@Table(name="TBL_AUDIT_Log")

public class AuditLogger {
	
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id;
	 
	private String user_Id;
	private String status;
	private String session_Date;
	private String token;
	private String path;
	
	
	public AuditLogger() {
		// TODO Auto-generated constructor stub
	}
	
	public AuditLogger(String user_Id, String status, String session_Date, String token, String path) {
		this.user_Id = user_Id;
		this.status = status;
		this.session_Date = session_Date;
		this.token = token;
		this.path = path;
	}

}
