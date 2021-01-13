package sbi.kiosk.swayam.transactiondashboard.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

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

import sbi.kiosk.swayam.common.constants.ExceptionConstants;
import sbi.kiosk.swayam.common.entity.DateFrame;
import sbi.kiosk.swayam.common.entity.ZeroTransactionKiosks;
import sbi.kiosk.swayam.transactiondashboard.service.ZeroTransactionKiosksService;

@RestController
public class ZeroTransactionKiosksController {
	Logger logger = LoggerFactory.getLogger(ZeroTransactionKiosksController.class);
	@Autowired
	ZeroTransactionKiosksService zeroTransactionKiosksService;
	
	@Autowired
	DateFrame dateFrame;
	
	@RequestMapping("td/zeroTransactionKiosks")
	public ModelAndView zeroTransactionKiosksPage(ModelAndView model, HttpSession session) {
		
		try {
		
			logger.info("zeroTransactionKiosksList");
			
			model.setViewName("zeroTransactionKiosks");
			
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	
	
	@RequestMapping(value = "zeroTransactionKiosks/get", params = { "page", "size" ,"type", "fromDate", "toDate"}, method = RequestMethod.GET, produces = "application/json")
	public Page<ZeroTransactionKiosks> findPaginated(
		      @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("type") String type, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
		 
		
		Page<ZeroTransactionKiosks> resultPage = null;
		
		if(fromDate.equals("undefined") || toDate.equals("undefined")) {		
			fromDate="";
			toDate="";
		}
			
		  dateFrame.setFromDate(fromDate); 
		  dateFrame.setToDate(toDate);
		  
		 		  
	      resultPage = zeroTransactionKiosksService.findPaginatedByDate(page, size, fromDate, toDate);
		
		    if (page > resultPage.getTotalPages()){
		            //throw new MyResourceNotFoundException();
		        }
		  return resultPage;
	}
	
	@RequestMapping(value = "zeroTransactionKiosks/getSearchNext", params = { "page", "size" ,"type", "fromDate", "toDate"}, method = RequestMethod.GET, produces = "application/json")
	public Page<ZeroTransactionKiosks> findPaginatedAfterSearchNext(
		      @RequestParam("page") int page, @RequestParam("size") int size, 
		      @RequestParam("type") String type, @RequestParam("fromDate") String fromDate, 
		      @RequestParam("toDate") String toDate,  @RequestParam("searchText") String searchText) {
		 
		
		Page<ZeroTransactionKiosks> resultPage = null;
		
		if(fromDate.equals("undefined") || toDate.equals("undefined")) {		
			fromDate="";
			toDate="";
		}
			
		  dateFrame.setFromDate(fromDate); 
		  dateFrame.setToDate(toDate);
		  
		 		  
	      resultPage = zeroTransactionKiosksService.findPaginatedByDateSearchNext(page, size, fromDate, toDate,searchText);
		
		    if (page > resultPage.getTotalPages()){
		            //throw new MyResourceNotFoundException();
		        }
		  return resultPage;
	}
	
	@GetMapping("td/getZeroLastUpDated")
	public ResponseEntity<String>  getLastUpdatedJob() {
		
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 Date curDate=new Date();
		 String lastUpdatedDate= zeroTransactionKiosksService.findZeroTxnLastUpdatedJob();
		ResponseEntity<String> entity=ResponseEntity.ok(lastUpdatedDate);
		return entity;
	}
	

}
