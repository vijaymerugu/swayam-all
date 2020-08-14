
  package sbi.kiosk.swayam.healthmonitoring.model;

import org.springframework.stereotype.Component;

@Component
  public class DowntimeReport {
  
		private String circle;
		private String vendor;
		private String cmsCmf;
		private String fromDate;
		private String toDate;
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
		
		
		
		
		
  
  }
 