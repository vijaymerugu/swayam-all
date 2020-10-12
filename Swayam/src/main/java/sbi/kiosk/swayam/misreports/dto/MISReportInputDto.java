package sbi.kiosk.swayam.misreports.dto;

import lombok.Data;

/**
 * @author vmph2371481
 *
 */
@Data
public class MISReportInputDto {

	public MISReportInputDto() {
	}

	public MISReportInputDto(String fromDate, String toDate, Integer groupingCriteriaId, String groupingCriteriaName, String selectedColumnIndexes, String reportType,String removeIds) {
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.groupingCriteriaId = groupingCriteriaId;
		this.selectedColumnIndexes = selectedColumnIndexes;
		this.reportType = reportType;
		//chenges
		this.removeIds=removeIds;
	}

	private String fromDate;
	private String toDate;
	private Integer groupingCriteriaId;
	private String groupingCriteriaName;
	private String selectedColumnIndexes;
	private String reportType;
	//Changes for observations
	private String removeIds;
	

}
