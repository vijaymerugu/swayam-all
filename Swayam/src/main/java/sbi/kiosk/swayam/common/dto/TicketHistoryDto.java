package sbi.kiosk.swayam.common.dto;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.TicketHistory;

@Data
public class TicketHistoryDto {
	public TicketHistoryDto() {
		
	}
	
	public TicketHistoryDto(TicketHistory ticketHistory) {
		this.kisokId = ticketHistory.getKisokId();
		this.circle = ticketHistory.getCircle();
		this.branchCode = ticketHistory.getBranchCode();
		this.call_log_date = ticketHistory.getCall_log_date();
		this.call_closed_date = ticketHistory.getCall_closed_date();
		this.vendor = ticketHistory.getVendor();
		this.callCategory = ticketHistory.getCallCategory();
		this.callSubCategory = ticketHistory.getCallSubCategory();
		this.ticketId = ticketHistory.getTicketId();
	}
	
	
	private String  kisokId;
	private String circle;
	private String branchCode;
	private String call_log_date;
	private String call_closed_date;
	private String vendor;
	private String callCategory;
	private String  callSubCategory;
	private String  ticketId;

}
