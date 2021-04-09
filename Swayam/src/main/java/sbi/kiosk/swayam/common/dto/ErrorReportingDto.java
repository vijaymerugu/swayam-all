package sbi.kiosk.swayam.common.dto;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.ErrorReportingDrillDown;

@Data
public class ErrorReportingDto {
	
		ErrorReportingDto(){
		
	     }
	public ErrorReportingDto(ErrorReportingDrillDown errorRepoDrillDown) {
		this.name = errorRepoDrillDown.getName();
		this.code = errorRepoDrillDown.getCode();
		this.totalNoOfTxns = errorRepoDrillDown.getTotalNoOfTxns();
		this.noOfSuccTxns = errorRepoDrillDown.getNoOfSuccTxns();
		this.noOfFailTxns = errorRepoDrillDown.getNoOfFailTxns();
		this.noOfTchFailTxns = errorRepoDrillDown.getNoOfTchFailTxns();
		this.noOfBsnsFailTxns = errorRepoDrillDown.getNoOfBsnsFailTxns();
	}
	
	private String name;
	private String code;
	private Integer totalNoOfTxns;

	private Integer noOfSuccTxns;
	private Integer noOfFailTxns;
	private Integer noOfTchFailTxns;
	private Integer noOfBsnsFailTxns;

}
