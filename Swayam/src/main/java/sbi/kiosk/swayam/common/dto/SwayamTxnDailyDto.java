package sbi.kiosk.swayam.common.dto;

import lombok.Data;

@Data
public class SwayamTxnDailyDto {
	
	private String respCode;
	private String errorCode; 
	private String errorDesc; 
	private String errorCodeCount;

}
