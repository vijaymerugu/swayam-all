package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="TBL_MANUAL_CALL_LOG")
public class ManualTicketCallLog extends Common {
	
	@Id
//	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name = "MANUAL_CALL_LOG_ID", nullable = false)
	private String manual_call_log_id;
	@Column(name = "BRANCH_CODE")
	private String branchCode;
	@Column(name = "KIOSK_ID")
	private String kioskId;
	@Column(name = "VENDOR")
	private String vendor;
	@Column(name = "CIRCLE")
	private String circle;
	@Column(name = "CONTACT_PERSON")
	private String contactPerson;
	@Column(name = "CONTACT_NO")
	private String contactNo;
	@Column(name = "ERROR")
	private String kioskError;
	@Column(name="COMMENTS")
	private String comments;
	@Column(name="COMPLAINTID")
	private String complaintId;
	
	@Column(name="STATUS")
	private String status;
	
	//
	
	@Column(name="CALL_SUBCATEGORY")
	private String subCategory;
	
		
	
}
