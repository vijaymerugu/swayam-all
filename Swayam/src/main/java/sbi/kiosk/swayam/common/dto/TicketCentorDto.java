package sbi.kiosk.swayam.common.dto;

import java.util.Date;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.TicketCentor;

@Data
public class TicketCentorDto {
	
	public TicketCentorDto(){
		
	}

	public TicketCentorDto(TicketCentor ticketCenter) {

		this.vendor = ticketCenter.getVendor();
		this.ticketId = ticketCenter.getTicketId();
		this.kisokId = ticketCenter.getKisokId();
		this.branchCode = ticketCenter.getBranchCode();
		this.callCategory = ticketCenter.getCallCategory();
		this.cms_cmf_assigned = ticketCenter.getCms_cmf_assigned();
		this.call_log_date = ticketCenter.getCall_log_date();
		this.statusOfComplaint = ticketCenter.getStatusOfComplaint();
		this.assigned_to_FE = ticketCenter.getAssigned_to_FE();
		this.fe_schedule = ticketCenter.getFe_schedule();
		this.serveriry = ticketCenter.getServeriry();
		this.callSubCategory = ticketCenter.getCallSubCategory();
		this.ageing = ticketCenter.getAgeing();
	}

	private String vendor;
	private String ticketId;
	private String kisokId;
	private String branchCode;
	private String callCategory;
	private String callSubCategory;
	private String cms_cmf_assigned;
	private Date call_log_date;
	private String statusOfComplaint;
	private String assigned_to_FE;
	private String fe_schedule;
	private String serveriry;
	private String ageing;

	

}
