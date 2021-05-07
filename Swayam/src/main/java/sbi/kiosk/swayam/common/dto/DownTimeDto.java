package sbi.kiosk.swayam.common.dto;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.DownTime;

@Data
public class DownTimeDto {
	
	public DownTimeDto() {
	}
	
	
	public DownTimeDto(DownTime downTime) {
		this.kioskId = downTime.getKioskId();
		this.circle = downTime.getCircle();
		this.network = downTime.getNetwork();
		this.module = downTime.getModule();
		this.branchCode = downTime.getBranchCode();
		this.vendor = downTime.getVendor();
		this.cmsCmf = downTime.getCmsCmf();
		this.totalOperatingHours = downTime.getTotalOperatingHours();
		this.totalDowntime = downTime.getTotalDowntime();
	}
	private String  kioskId;
	private String circle;
	private String network;
	private String module;
	private String branchCode;
	private String vendor;
	private String cmsCmf;
	private Integer totalOperatingHours;
	private Integer  totalDowntime;

}
