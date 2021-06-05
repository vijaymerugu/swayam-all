package sbi.kiosk.swayam.healthmonitoring.model;

import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
public class BillingPaymentReport {
	
	
	private String circle;
	private String state;
	private String vendor;
	private String timePeiod;
	private String rpfNumber;
	private Float gst;
	private Float cgst;
	private Float sgst;
	private String quarter;
	private String year;
	private String gstType;
	private String kioskID;
	private String branchCode;
	
	/*
	 * public String getCircle() { return circle; } public void setCircle(String
	 * circle) { this.circle = circle; } public String getState() { return state; }
	 * public void setState(String state) { this.state = state; } public String
	 * getVendor() { return vendor; } public void setVendor(String vendor) {
	 * this.vendor = vendor; } public String getTimePeiod() { return timePeiod; }
	 * public void setTimePeiod(String timePeiod) { this.timePeiod = timePeiod; }
	 * public String getRpfNumber() { return rpfNumber; } public void
	 * setRpfNumber(String rpfNumber) { this.rpfNumber = rpfNumber; } public Integer
	 * getGst() { return gst; } public void setGst(Integer gst) { this.gst = gst; }
	 * public String getQuarter() { return quarter; } public void setQuarter(String
	 * quarter) { this.quarter = quarter; } public String getYear() { return year; }
	 * public void setYear(String year) { this.year = year; } public String
	 * getGstType() { return gstType; } public void setGstType(String gstType) {
	 * this.gstType = gstType; } public Integer getCgst() { return cgst; } public
	 * void setCgst(Integer cgst) { this.cgst = cgst; } public Integer getSgst() {
	 * return sgst; } public void setSgst(Integer sgst) { this.sgst = sgst; }
	 */
	

}
