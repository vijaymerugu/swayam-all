package sbi.kiosk.swayam.healthmonitoring.model;

import org.springframework.stereotype.Component;
import lombok.Data;


@Component

public class BillingPaymentReport {
	
	private String circle;
	private String state;
	private String vendor;
	private String timePeiod;
	private String rpfNumber;
	public String getCircle() {
		return circle;
	}
	public void setCircle(String circle) {
		this.circle = circle;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getTimePeiod() {
		return timePeiod;
	}
	public void setTimePeiod(String timePeiod) {
		this.timePeiod = timePeiod;
	}
	public String getRpfNumber() {
		return rpfNumber;
	}
	public void setRpfNumber(String rpfNumber) {
		this.rpfNumber = rpfNumber;
	}
	

}
