package sbi.kiosk.swayam.transactiondashboard.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.common.entity.ErrorReporting;
import sbi.kiosk.swayam.transactiondashboard.repository.ErrorReportingRepositoryPaging;

@Service("ErrorReportingServiceImpl")
public class ErrorReportingServiceImpl implements ErrorReportingService {

	Logger logger = LoggerFactory.getLogger(ErrorReportingServiceImpl.class);

	@Autowired
	ErrorReportingRepositoryPaging errorReportingRepo;

	@Override
	public Page<ErrorReporting> findPaginated(int page, int size, String fromDate, String toDate) {
		logger.info("ErrorReportingServiceImpl Started() fromDate:: "+fromDate);
		logger.info("ErrorReportingServiceImpl Started() toDate::"+toDate);
		//List<ErrorReporting> errorRepoList = nearByEntities(fromDate, toDate);
		logger.info("fromdate22222222==   "+fromDate);
		 logger.info("todate6666666666==   "+toDate);
		  Page<ErrorReporting> pageErrorReporting= errorReportingRepo.findByDate(fromDate, toDate, PageRequest.of(page, size));			
		  return pageErrorReporting;
	}
	

}
