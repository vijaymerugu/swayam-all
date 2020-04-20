package sbi.kiosk.swayam.healthmonitoring.service;


import java.util.List;

import sbi.kiosk.swayam.common.dto.CallTypeDto;
import sbi.kiosk.swayam.common.dto.ManualTicketCallLogDto;
import sbi.kiosk.swayam.common.exception.ManualTicketNotFoudException;



public interface ManualTicketService {

	//List<ManualTicketCallLogDto>  getAllManualTicketCallLog(String brachCode);

	public String createManualTicket(ManualTicketCallLogDto manualTicketCallLogDto) throws ManualTicketNotFoudException;
	
	public List<ManualTicketCallLogDto> getByBranchCode(String brachCode);
	
	public  List<ManualTicketCallLogDto>  getByKioskId(String kioskId);
	
	public  List<ManualTicketCallLogDto>  getByVendor(String vendor,String branchcode);

	List<CallTypeDto> getCallTypeCategoryError();


}
