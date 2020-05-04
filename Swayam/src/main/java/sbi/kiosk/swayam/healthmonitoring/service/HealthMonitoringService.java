package sbi.kiosk.swayam.healthmonitoring.service;

import sbi.kiosk.swayam.common.dto.RequestsDto;
import sbi.kiosk.swayam.common.dto.RequestsManagementDto;

public interface HealthMonitoringService extends IOperations<RequestsDto>{

	public String saveRequestForCmf(RequestsDto dto);
	
	public void saveCheckerCommentsCms(String array);
	
	public void rejectCheckerCommentsCms(String array);
	
	public void saveApproverCommentsCC(String array);
	
	public void rejectApproverCommentsCC(String array);
	
	public RequestsManagementDto viewCaseId(int caseId);
	String checkDuplicateKiosAppr(String kioskId);

}
