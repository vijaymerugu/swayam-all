package sbi.kiosk.swayam.healthmonitoring.service;

import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.common.dto.RequestsDto;
import sbi.kiosk.swayam.healthmonitoring.service.IOperations;

public interface HealthMonitoringService extends IOperations<RequestsDto>{

	public void saveRequestForCmf(RequestsDto dto);
	
	public void saveCheckerCommentsCms(String array);
	
	public void rejectCheckerCommentsCms(String array);
	
	public void saveApproverCommentsCC(String array);
	
	public void rejectApproverCommentsCC(String array);
}
