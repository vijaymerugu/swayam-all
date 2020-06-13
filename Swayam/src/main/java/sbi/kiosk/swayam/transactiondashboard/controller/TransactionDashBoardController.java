package sbi.kiosk.swayam.transactiondashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.common.entity.SwayamMigrationSummary;
import sbi.kiosk.swayam.transactiondashboard.service.TransactionDashBoardService;

@RestController
public class TransactionDashBoardController {
	
	@Autowired
	TransactionDashBoardService transactionDashBoardService;
	
	
	@RequestMapping("/td/transactionDashBoard")
	public ModelAndView transactionDashBoard() {
		System.out.println("transa==========");
		ModelAndView mav = new ModelAndView("transactionDashBoard");
		return mav;
	}
	
	
	/*@RequestMapping(value = "/td/dashBoardTxnBM/get", params = { "page", "size" }, method = RequestMethod.GET, produces = "application/json")
	public Page<SwayamMigrationSummary> findPaginated(
		      @RequestParam("page") int page, @RequestParam("size") int size) {
		 
		        Page<SwayamMigrationSummary> resultPage = transactionDashBoardService.findPaginated(page, size);
		       System.out.println("resultPage=="+resultPage.getNumberOfElements());
		        if (page > resultPage.getTotalPages()) {
		            //throw new MyResourceNotFoundException();
		        }
		 
		        return resultPage;
		    }
	*/
	
	@RequestMapping(value = "/td/dashBoardTxnBM/get", params = { "page", "size", "fromdate","todate"}, method = RequestMethod.GET, produces = "application/json")
	public Page<SwayamMigrationSummary> findPaginated(
		      @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("fromdate") String fromdate, @RequestParam("todate") String todate) {
		 
		       // Page<SwayamMigrationSummary> resultPage = transactionDashBoardService.findPaginated(page, size);
		       
		   Page<SwayamMigrationSummary> resultPage = transactionDashBoardService.findPaginated(page, size,fromdate,todate);
		System.out.println("resultPage=="+resultPage.getNumberOfElements());
		        if (page > resultPage.getTotalPages()) {
		            //throw new MyResourceNotFoundException();
		        }
		 
		        return resultPage;
		    }
	
	
	
}
