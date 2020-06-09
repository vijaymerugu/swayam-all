package sbi.kiosk.swayam.transactiondashboard.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import sbi.kiosk.swayam.common.entity.ZeroTransactionKiosks;
import sbi.kiosk.swayam.transactiondashboard.service.ZeroTransactionKiosksService;

@RestController
public class ZeroTransactionKiosksController {
	
	@Autowired
	ZeroTransactionKiosksService zeroTransactionKiosksService;
	
	@RequestMapping("td/zeroTransactionKiosks")
	public ModelAndView zeroTransactionKiosksPage(ModelAndView model, HttpSession session) {
		
		try {
		
			System.out.println("zeroTransactionKiosksList");
			
			model.setViewName("zeroTransactionKiosks");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	
	@RequestMapping(value = "zeroTransactionKiosks/get", params = { "page", "size" ,"type", "fromDate", "toDate"}, method = RequestMethod.GET, produces = "application/json")
	public Page<ZeroTransactionKiosks> findPaginated(
		      @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("type") String type, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
		 
		System.out.println("type=========Yogesh========="+type);
		System.out.println("From Date--- "+fromDate);
		System.out.println("To Date----- "+toDate);
		Page<ZeroTransactionKiosks> resultPage = null;
		
		if(fromDate.equals("undefined") || toDate.equals("undefined")) {		
			fromDate="";
			toDate="";
		}
			
	      resultPage = zeroTransactionKiosksService.findPaginatedByDate(page, size, fromDate, toDate);
		
		    if (page > resultPage.getTotalPages()){
		            //throw new MyResourceNotFoundException();
		        }
		  return resultPage;
	}

}
