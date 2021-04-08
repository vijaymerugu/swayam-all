package sbi.kiosk.swayam.transactiondashboard.service;

import java.util.List;

import org.springframework.data.domain.Page;

import sbi.kiosk.swayam.common.dto.SwayamTxnDailyDto;
import sbi.kiosk.swayam.common.entity.ErrorReporting;
import sbi.kiosk.swayam.common.entity.ErrorReportingDrillDown;

public interface ErrorReportingService{

	Page<ErrorReporting> findPaginated(int page, int size, String fromDate, String toDate);
	String findSwayamTxnLastUpdatedJob();
	//Page<ErrorReporting> findPaginatedSearchNext(int page, int size, String fromDate, String toDate, String searchText);
	Page<ErrorReportingDrillDown> findPaginatedByTxnDate(int page, int size, String type, String fromdate,
			String todate, String in_circle_code, String in_network_code, String in_module_code, String in_region_code);
	Page<ErrorReportingDrillDown> findPaginatedByTxnDateSearchNext(int page, int size, String type, String fromdate,
			String todate, String in_circle_code, String in_network_code, String in_module_code, String in_region_code,
			String searchText);
	List<SwayamTxnDailyDto> findByCircleCodeErrorCount(String code,String fromdate,String todate);
	List<SwayamTxnDailyDto> findByCircleCodeErrorCountBussTxnFail(String circleCode, String fromdate, String todate);
	List<SwayamTxnDailyDto> findByNCodeErrorCount( String networkCode, String fromDate,String toDate);
	List<SwayamTxnDailyDto> findByMCodeTechFailErrorCount(String moduleCode, String fromDate, String toDate);
	List<SwayamTxnDailyDto> findByMCodeBussTxnFailErrorCount(String moduleCode, String fromDate, String toDate);
	List<SwayamTxnDailyDto> findByRegionCodeBussFailErrorCount(String regionName, String fromDate, String toDate);
	List<SwayamTxnDailyDto> findByRegionCodeTechFailErrorCount(String regionName, String fromDate, String toDate);

}
