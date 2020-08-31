/**
 * 
 */
package sbi.kiosk.swayam.misreports.service;

import java.util.List;

import sbi.kiosk.swayam.common.entity.MISAvailableColumns;
import sbi.kiosk.swayam.common.entity.MISGroupingCriteria;
import sbi.kiosk.swayam.misreports.dto.MISReportInputDto;
import sbi.kiosk.swayam.misreports.dto.MISReportOutputDto;

/**
 * @author vmph2371481
 *
 */
public interface MISReporterService {
	
	List<MISReportOutputDto> getMisReportData(MISReportInputDto misReportInputDto);

	List<MISGroupingCriteria> getMISGroupingCriteria();
	
	List<MISAvailableColumns> getMISAvailableColumns();

}
