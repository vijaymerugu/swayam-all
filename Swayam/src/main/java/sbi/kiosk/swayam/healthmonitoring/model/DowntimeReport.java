
  package sbi.kiosk.swayam.healthmonitoring.model;

import org.springframework.stereotype.Component;

@Component
  public class DowntimeReport {
  
		private String circle;
		private String vendor;
		private String cmsCmf;
		private String fromDate;
		private String toDate;
		private String branchCode;
		private String kioskId;
		public String getCircle() {
			return circle;
		}
		public void setCircle(String circle) {
			this.circle = circle;
		}
		public String getVendor() {
			return vendor;
		}
		public void setVendor(String vendor) {
			this.vendor = vendor;
		}
		public String getCmsCmf() {
			return cmsCmf;
		}
		public void setCmsCmf(String cmsCmf) {
			this.cmsCmf = cmsCmf;
		}
		public String getFromDate() {
			return fromDate;
		}
		public void setFromDate(String fromDate) {
			this.fromDate = fromDate;
		}
		public String getToDate() {
			return toDate;
		}
		public void setToDate(String toDate) {
			this.toDate = toDate;
		}
		public String getBranchCode() {
			return branchCode;
		}
		public void setBranchCode(String branchCode) {
			this.branchCode = branchCode;
		}
		public String getKioskId() {
			return kioskId;
		}
		public void setKioskId(String kioskId) {
			this.kioskId = kioskId;
		}
		
		
		
		
		
  
  }
 