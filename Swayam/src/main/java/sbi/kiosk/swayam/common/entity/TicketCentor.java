package sbi.kiosk.swayam.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TBL_TICKET_CENTRE")
public class TicketCentor extends Common {
	@Id
	@Column(name = "TICKET_ID")
	private String ticketId;
	@Column(name = "VENDOR")
	private String vendor;
	@Column(name = "KIOSK_ID")
	private String kisokId;
	@Column(name = "BRANCH_CODE")
	private String branchCode;
	@Column(name = "CALL_CATEGORY")
	private String callCategory;
	@Column(name = "CALL_SUBCATEGORY")
	private String callSubCategory;
	@Column(name = "CMS_CMF_ASSIGNED")
	private String cms_cmf_assigned;
	@Column(name = "CALL_LOG_DATE")
	private Date call_log_date;
	
	
	@Column(name = "SEVERITY")
	private String serveriry;
	@Column(name = "AGEING")
	private String ageing;
	
	@Column(name = "STATUS_OF_COMPLAINT")
	private String statusOfComplaint;
	@Column(name = "ASSIGNED_TO_FE")
	private String assigned_to_FE;
	@Column(name = "FE_SCHEDULE")
	private String fe_schedule;

	
}
