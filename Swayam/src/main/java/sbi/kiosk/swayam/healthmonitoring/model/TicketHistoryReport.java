
package sbi.kiosk.swayam.healthmonitoring.model;

import org.springframework.stereotype.Component;

@Component

public class TicketHistoryReport {

	private String kisokId;
	private String circle;
	private String branchCode;
	private String call_log_date;
	private String call_closed_date;
	private String vendor;
	private String callCategory;
	private String callSubCategory;

	public String getKisokId() {
		return kisokId;
	}

	public void setKisokId(String kisokId) {
		this.kisokId = kisokId;
	}

	public String getCircle() {
		return circle;
	}

	public void setCircle(String circle) {
		this.circle = circle;
	}

	

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getCall_log_date() {
		return call_log_date;
	}

	public void setCall_log_date(String call_log_date) {
		this.call_log_date = call_log_date;
	}

	public String getCall_closed_date() {
		return call_closed_date;
	}

	public void setCall_closed_date(String call_closed_date) {
		this.call_closed_date = call_closed_date;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getCallCategory() {
		return callCategory;
	}

	public void setCallCategory(String callCategory) {
		this.callCategory = callCategory;
	}

	public String getCallSubCategory() {
		return callSubCategory;
	}

	public void setCallSubCategory(String callSubCategory) {
		this.callSubCategory = callSubCategory;
	}

}
