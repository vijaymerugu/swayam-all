package sbi.kiosk.swayam.common.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.User;

@Data
public class UserManagementReportDto {

	public static String formatTimestampToString(String dateString) throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(dateString);
		String formattedDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm").format(date);
		return formattedDate;
	}

	UserManagementReportDto() {

	}

	public UserManagementReportDto(User user) {
		this.userId = user.getUserId();
		this.pfId = user.getPfId();
		this.username = user.getUsername();
		this.enabled = user.getEnabled();
		this.role = user.getRole();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.mailId = user.getMailId();
		try {
			this.createdDate = user.getCreatedDate() != null ? formatTimestampToString(user.getCreatedDate().toString())
					: "";
		} catch (ParseException e) {
		}
		this.createdBy = user.getCreatedBy();
		try {
			this.modifiedDate = user.getModifiedDate() != null
					? formatTimestampToString(user.getModifiedDate().toString()): "";
		} catch (ParseException e) {
		}
		this.modifiedBy = user.getModifiedBy();
		this.phoneNo = user.getPhoneNo();
		this.circle = user.getCircle();
		this.reportingAuthorityName = user.getReportingAuthorityName();
		this.reportingAuthorityEmail = user.getReportingAuthorityEmail();

	}

	private Integer userId;

	private String pfId;

	private String username;

	private String enabled;

	private String role;

	private String firstName;

	private String lastName;

	private String mailId;

	private String createdDate;

	private String createdBy;

	private String modifiedDate;

	private String modifiedBy;

	private String phoneNo;

	private String circle;

	private String noOfAssignedKiosks;

	private String reportingAuthorityName;

	private String reportingAuthorityEmail;

}
