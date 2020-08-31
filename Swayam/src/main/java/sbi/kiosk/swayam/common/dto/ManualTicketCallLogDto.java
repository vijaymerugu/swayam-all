package sbi.kiosk.swayam.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import lombok.Data;

@JsonIgnoreType 
@Data
public class ManualTicketCallLogDto {
	
	
	private String branchCode;
	private String branchName;
	private String kioskId;
	private String vendor;
	private String circle;
	private String contactPerson;
	private String contactNo;
	private String kioskError;
	private String comment;
	//private String status="P";
	
	private String status=null;
	private String subCategory;
	
	
	
}
