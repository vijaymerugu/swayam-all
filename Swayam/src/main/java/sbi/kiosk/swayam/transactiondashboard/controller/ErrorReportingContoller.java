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

import sbi.kiosk.swayam.common.entity.ErrorReporting;
import sbi.kiosk.swayam.transactiondashboard.service.ErrorReportingService;

@RestController
public class ErrorReportingContoller {

	
Logger logger = LoggerFactory.getLogger(ErrorReportingContoller.class);
	
	@Autowired
	ErrorReportingService errorReportingService;

	@RequestMapping("td/errorReporting")
	public ModelAndView errorReporting() {
		logger.info("errorReporting==========");
		ModelAndView mav = new ModelAndView("errorReporting");
		return mav;
	}
	
	
	
	@RequestMapping(value = "td/errorReporting/get", params = { "page", "size", "fromdate",	"todate" }, method = RequestMethod.GET, produces = "application/json")
	public Page<ErrorReporting> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size,@RequestParam("fromdate") String fromdate, @RequestParam("todate") String todate) {
		
		Page<ErrorReporting> resultPage = errorReportingService.findPaginated(page, size, fromdate,todate);
		
		logger.info("resultPage==" + resultPage.getNumberOfElements());
		if (page > resultPage.getTotalPages()) {
			// throw new MyResourceNotFoundException();
		}

		return resultPage;
	}
	
}