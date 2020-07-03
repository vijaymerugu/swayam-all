package sbi.kiosk.swayam.transactiondashboard.service;

import org.springframework.data.domain.Page;

import sbi.kiosk.swayam.common.entity.ErrorReporting;
import sbi.kiosk.swayam.transactiondashboard.repository.IOperations;

public interface ErrorReportingService{

	Page<ErrorReporting> findPaginated(int page, int size, String fromDate, String toDate);

}
