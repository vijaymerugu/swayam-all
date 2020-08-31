package sbi.kiosk.swayam.misreports.dto;

import lombok.Data;

/**
 * @author vmph2371481
 *
 */
@Data
public class MISReportOutputDto {

	public MISReportOutputDto() {}

	public MISReportOutputDto(String swayamTransaction, String branchCounter, Double migrationPercent, Double uptimePercent,
			Integer noOfKiosks, String totalDowntime, String onSiteOffSite, String standaloneTTW, Integer noOfRequestRaised,
			String typeOfRequest, String tatOfRequestCompletion, String selectedGroup) {

		this.swayamTransaction = swayamTransaction;
		this.branchCounter = branchCounter;
		this.migrationPercent = migrationPercent;
		this.uptimePercent = uptimePercent;
		this.noOfKiosks = noOfKiosks;
		this.totalDowntime = totalDowntime;
		this.onSiteOffSite = onSiteOffSite;
		this.standaloneTTW = standaloneTTW;
		this.noOfRequestRaised = noOfRequestRaised;
		this.typeOfRequest = typeOfRequest;
		this.tatOfRequestCompletion = tatOfRequestCompletion;
		this.selectedGroup = selectedGroup;
	}

	private String swayamTransaction;
	private String branchCounter;
	private Double migrationPercent;
	private Double uptimePercent;
	private Integer noOfKiosks;
	private String totalDowntime;
	private String onSiteOffSite;
	private String standaloneTTW;
	private Integer noOfRequestRaised;
	private String typeOfRequest;
	private String tatOfRequestCompletion;
	private String selectedGroup;
}