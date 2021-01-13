package sbi.kiosk.swayam.transactiondashboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.common.entity.DateFrame;
import sbi.kiosk.swayam.common.entity.ErrorReporting;
import sbi.kiosk.swayam.transactiondashboard.service.ErrorReportingService;

@RestController
public class ErrorReportingContoller {

	
Logger logger = LoggerFactory.getLogger(ErrorReportingContoller.class);
	
	@Autowired
	ErrorReportingService errorReportingService;

	@Autowired
	DateFrame dateFrame;
	
	@RequestMapping("td/errorReporting")
	public ModelAndView errorReporting() {
		logger.info("errorReporting==========");
		ModelAndView mav = new ModelAndView("errorReporting");
		return mav;
	}
	
	@RequestMapping("td/noOfErrorsKiosk")
	public ModelAndView noOfErrorsKiosk(@RequestParam("kioskId") String kioskId) {
	//	logger.info("noOfErrorsKiosk=========="+kioskId);
		ModelAndView mav = new ModelAndView("errorSummary");
		return mav;
	}
	
	@RequestMapping(value = "td/errorReporting/get", params = { "page", "size", "fromDate",	"toDate" }, method = RequestMethod.GET, produces = "application/json")
	public Page<ErrorReporting> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size,@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
		
		Page<ErrorReporting> resultPage = errorReportingService.findPaginated(page, size, fromDate,toDate);
		
		
		  dateFrame.setFromDate(fromDate); dateFrame.setToDate(toDate);
		  
	/*	  logger.info("Inside ZeroTransactionKiosksController From date from jsp: "
		  +dateFrame.getFromDate());
		  logger.info("Inside ZeroTransactionKiosksController To date from jsp: "
		  +dateFrame.getToDate()); 
		 
		  
		logger.info("resultPage==" + resultPage.getNumberOfElements()); */
		if (page > resultPage.getTotalPages()) {
			// throw new MyResourceNotFoundException();
		}

		return resultPage;
	}
	@RequestMapping(value = "td/errorReporting/getSearchNext", params = { "page", "size", "fromDate",	"toDate", "searchText" }, method = RequestMethod.GET, produces = "application/json")
	public Page<ErrorReporting> findPaginatedAfterSearchNext(@RequestParam("page") int page, @RequestParam("size") int size,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
			@RequestParam("searchText") String searchText) {
		
		Page<ErrorReporting> resultPage = errorReportingService.findPaginatedSearchNext(page, size, fromDate,toDate,searchText);
		
		
		  dateFrame.setFromDate(fromDate); dateFrame.setToDate(toDate);
		  

		if (page > resultPage.getTotalPages()) {
			// throw new MyResourceNotFoundException();
		}

		return resultPage;
	}
	@GetMapping("td/getErrorReportingLastUpDated")
	public ResponseEntity<String>  getLastUpdatedJob() {
		 String lastUpdatedDate= errorReportingService.findSwayamTxnLastUpdatedJob();
		ResponseEntity<String> entity=ResponseEntity.ok(lastUpdatedDate);
		return entity;
	}
	
}
