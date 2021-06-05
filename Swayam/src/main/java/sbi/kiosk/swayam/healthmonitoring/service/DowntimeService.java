package sbi.kiosk.swayam.healthmonitoring.service;

import org.springframework.data.domain.Page;

import sbi.kiosk.swayam.common.dto.DownTimeDto;
import sbi.kiosk.swayam.healthmonitoring.repository.IOperations;

public interface DowntimeService  extends IOperations<DownTimeDto>{

	Page<DownTimeDto> findAllPaginated(int size, int page, String type, String selectedCircelId, String selectedVendorId, String selectedCmsCmfId, String selectedFromDateId, String selectedToDateId,String selectedBranchCodeId,String selectedKioskId);

}
