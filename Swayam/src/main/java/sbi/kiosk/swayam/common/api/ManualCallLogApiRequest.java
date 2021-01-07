package sbi.kiosk.swayam.common.api;

import java.util.Date;

import lombok.Data;
@Data
public class ManualCallLogApiRequest {

	String requestId;
	String reqTicketNumber;
	String reqType;
	String  reqDatetime;
	String brCode;
	String src;
	String kioskProvider;
	String kioskId;
	String kioskSrno;
	String issueCategory;
	String issueSubcategory;
	String issueDescription;
	String contactName;
	String contactNumber;
	String contactEmailId;
	String status;
	

}
