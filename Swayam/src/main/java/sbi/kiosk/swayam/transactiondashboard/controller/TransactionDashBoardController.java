package sbi.kiosk.swayam.transactiondashboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.common.entity.DateFrame;
import sbi.kiosk.swayam.common.entity.SwayamMigrationSummary;
import sbi.kiosk.swayam.transactiondashboard.service.TransactionDashBoardService;

@RestController
public class TransactionDashBoardController {
	Logger logger = LoggerFactory.getLogger(TransactionDashBoardController.class);
	
	@Autowired
	TransactionDashBoardService transactionDashBoardService;
	
	@Autowired
	DateFrame dateFrame;

	@RequestMapping("td/transactionSummary")
	public ModelAndView transactionDashBoard() {
		logger.info("transa==========");
		ModelAndView mav = new ModelAndView("transactionDashBoard");
		return mav;
	}

	@RequestMapping(value = "td/dashBoardTxnBM/get", params = { "page", "size", "fromdate",	"todate" }, method = RequestMethod.GET, produces = "application/json")
	public Page<SwayamMigrationSummary> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size,@RequestParam("fromdate") String fromdate, @RequestParam("todate") String todate) {
		Page<SwayamMigrationSummary> resultPage = transactionDashBoardService.findPaginated(page, size, fromdate,todate);
		
		
		  dateFrame.setFromDate(fromdate); dateFrame.setToDate(todate);
		  
			/*
			 * logger.info("Inside TransactionDashboardController From date from jsp: "
			 * +dateFrame.getFromDate());
			 * logger.info("Inside TransactionDashboardController To date from jsp: "
			 * +dateFrame.getToDate());
			 */
		 
		
	//	logger.info("resultPage==" + resultPage.getNumberOfElements());
		if (page > resultPage.getTotalPages()) {
			// throw new MyResourceNotFoundException();
		}

		return resultPage;
	}

}
