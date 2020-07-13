package sbi.kiosk.swayam.common.dto;

import lombok.Data;

@Data
public class ErrorReportingDto {

	private String kioskId;
	private String crclName;
	private String network;
	private String module;
	private String region;
	private String branchCode;
	private String branchName;
	private String vendor;
	private String noOfErrors;

}
