package sbi.kiosk.swayam.healthmonitoring.service;

import sbi.kiosk.swayam.common.dto.RequestsDto;
import sbi.kiosk.swayam.kioskmanagement.service.IOperations;

public interface HealthMonitoringService extends IOperations<RequestsDto>{

	public void saveRequestForCmf(RequestsDto dto);
}
