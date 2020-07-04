package sbi.kiosk.swayam.transactiondashboard.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.common.entity.RealTimeTransaction;
import sbi.kiosk.swayam.transactiondashboard.service.RealTimeTransactionService;

@RestController
public class RealTimeTransactionController {
	
	@Autowired
	RealTimeTransactionService realTimeTransactionService;
	
	@RequestMapping("td/realTimeTransaction")
	public ModelAndView realTimeTransation(ModelAndView mav) {
		mav.setViewName("realTimeTransaction");
		return mav;
	}
	
	@GetMapping("td/getCurrentDate")
	public ResponseEntity<String>  getByPfId() {
		
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 Date curDate=new Date();
		 String lastUpdatedDate=sdf.format(curDate);
		// Map<String, String> lastUpdateDateMap=new HashMap<String, String>();
		// lastUpdateDateMap.put("CurentDate", lastUpdatedDate);
		//mav.addObject("lastUpdatedDate", lastUpdatedDate);
		
		ResponseEntity<String> entity=ResponseEntity.ok(lastUpdatedDate);
		return entity;
	}
	
	@RequestMapping("td/realTimeTransactionYestrday")
	public ModelAndView realTimeTransationYestarday(ModelAndView mav) {
		mav.setViewName("realTimeTransactionYestrday");
		return mav;
	}
	
	
	@RequestMapping("td/fromToDate")
	public ModelAndView fromToDate(ModelAndView mav,HttpServletRequest request) {
		
		String fromDate= request.getParameter("date1");
		String toDate= request.getParameter("date2");
		mav.setViewName("realTimeTransaction");
		return mav;
	}

	
	
	@RequestMapping(value = "td/realTimeTxn/get", params = { "page", "size","fromdate" }, method = RequestMethod.GET, produces = "application/json")
	public Page<RealTimeTransaction> findPaginatedRealTimeFromToDate(
		      @RequestParam("page") int page, @RequestParam("size") int size,
		      @RequestParam("fromdate") String fromdate) {
		//Page<RealTimeTransaction> resultPage = realTimeTransactionService.findPaginatedFromToDate(page, size,fromDate,toDate);
		      Page<RealTimeTransaction> resultPage = realTimeTransactionService.findPaginated(page, size,fromdate);
				
		        if (page > resultPage.getTotalPages()) {
		            //throw new MyResourceNotFoundException();
		        }
		 
		        return resultPage;
		    }

}
