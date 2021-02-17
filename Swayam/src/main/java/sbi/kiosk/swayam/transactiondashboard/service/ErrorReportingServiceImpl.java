package sbi.kiosk.swayam.transactiondashboard.service;

import java.text.SimpleDateFormat;
import java.util.Date;

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
		//logger.info("ErrorReportingServiceImpl Started() fromDate:: "+fromDate);
		//logger.info("ErrorReportingServiceImpl Started() toDate::"+toDate);
		//List<ErrorReporting> errorRepoList = nearByEntities(fromDate, toDate);
		if((fromDate ==null || fromDate.isEmpty()) && (toDate ==null || toDate.isEmpty()) || (fromDate.equalsIgnoreCase("undefined") || fromDate.equalsIgnoreCase("undefined"))){
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			Date curDate=new Date();
			fromDate=sdf.format(curDate);
			toDate=sdf.format(curDate);
		}
		  Page<ErrorReporting> pageErrorReporting= errorReportingRepo.findByDate(fromDate, toDate, PageRequest.of(page, size));			
		//  logger.info("pageErrorReporting"+pageErrorReporting);
		  return pageErrorReporting;
	}
	
	@Override
	public Page<ErrorReporting> findPaginatedSearchNext(int page, int size, String fromDate, String toDate, String searchText) {
		//logger.info("ErrorReportingServiceImpl Started() fromDate:: "+fromDate);
		//logger.info("ErrorReportingServiceImpl Started() toDate::"+toDate);
		//List<ErrorReporting> errorRepoList = nearByEntities(fromDate, toDate);
		if((fromDate ==null || fromDate.isEmpty()) && (toDate ==null || toDate.isEmpty()) || (fromDate.equalsIgnoreCase("undefined") || fromDate.equalsIgnoreCase("undefined"))){
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			Date curDate=new Date();
			fromDate=sdf.format(curDate);
			toDate=sdf.format(curDate);
		}
		  Page<ErrorReporting> pageErrorReporting= errorReportingRepo.findByDateSearchNext(fromDate, toDate,searchText, PageRequest.of(page, size));			
		  logger.info("pageErrorReporting"+pageErrorReporting);
		  return pageErrorReporting;
	}

	@Override
	public String findSwayamTxnLastUpdatedJob() {
		
		String swayamMigrationTxnDate=null;
		
		try {
			swayamMigrationTxnDate=errorReportingRepo.findCurrentDateAuditJob();
			
		} catch (Exception e) {
		//	e.printStackTrace();
		}
		return swayamMigrationTxnDate;
		
		
		
	}
	
	
}
