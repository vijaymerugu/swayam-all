package sbi.kiosk.swayam.common.dto;

import lombok.Data;


@Data
public class ContactDto {
	
	
	
	
	private Integer contactId;
	
	
	private String contactName;

	private String l1contactNo;
	
	private String email;
	
	private String type;
	
	/*
	 * @Column(name = "L2_CONTACT") private Blob l2Contact;
	 */
	
	//private String l2Contact;
	
	
	private String esMatrix;

}
