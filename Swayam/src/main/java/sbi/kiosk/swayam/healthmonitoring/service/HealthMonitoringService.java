package sbi.kiosk.swayam.healthmonitoring.service;

import java.util.List;

import sbi.kiosk.swayam.common.dto.ManualTicketCallLogDto;
import sbi.kiosk.swayam.common.dto.RequestsDto;
import sbi.kiosk.swayam.common.dto.RequestsManagementDto;
import sbi.kiosk.swayam.common.entity.VendorMaster;

public interface HealthMonitoringService extends IOperations<RequestsDto>{

	public String saveRequestForCmf(RequestsDto dto);
	
	public void saveCheckerCommentsCms(String array);
	
	public void rejectCheckerCommentsCms(String array);
	
	public void saveApproverCommentsCC(String array);
	
	public void rejectApproverCommentsCC(String array);
	
	public RequestsManagementDto viewCaseId(int caseId);
	String checkDuplicateKiosAppr(String kioskId);

	public Iterable<VendorMaster> getVendor(String brachCode);

	public List<RequestsDto> getByVendorAndBranchCode(String vendor, String branchcode);

	public RequestsManagementDto activateKiosk(int caseId);

}
