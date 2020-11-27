package sbi.kiosk.swayam.common.entity;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TBL_CONTACT_DETAILS")
public class ContactEntity {
	
	@Id
	@Column(name = "CONTACT_ID")
	private Integer contactId;
	
	@Column(name = "CONTACT_NAME")
	private String contactName;
	
	@Column(name = "CONATACT_NUMBER")
	private String l1contactNo;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "TYPE")
	private String type;
	
	/*
	 * @Column(name = "L2_CONTACT") private Blob l2Contact;
	 */
	
	/*
	 * @Lob
	 * 
	 * @Column(name = "L2_CONTACT") private byte[] l2Contact;
	 */
	
	@Column(name = "ES_MATRIX")
	private String esMatrix;
	
	

}
