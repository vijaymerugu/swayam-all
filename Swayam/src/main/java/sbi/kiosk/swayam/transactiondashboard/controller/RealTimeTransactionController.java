package sbi.kiosk.swayam.transactiondashboard.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.common.entity.RealTimeTransaction;
import sbi.kiosk.swayam.common.validation.ValidationCommon;
import sbi.kiosk.swayam.transactiondashboard.repository.RealTimeTxnRepositoryPaging;
import sbi.kiosk.swayam.transactiondashboard.service.RealTimeTransactionService;

@RestController
public class RealTimeTransactionController {
	
	@Autowired
	RealTimeTransactionService realTimeTransactionService;
	
	@Autowired
	RealTimeTxnRepositoryPaging realTimeTxnRepository;
	
	
	@RequestMapping("td/realTimeTransaction")
	public ModelAndView realTimeTransation(ModelAndView mav) {
		mav.setViewName("realTimeTransaction");
		return mav;
	}
	
	@GetMapping("td/getCurrentDate")
	public ResponseEntity<String>  getByPfId() {
		
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 Date curDate=new Date();
		// String lastUpdatedDate=sdf.format(curDate);
		// Map<String, String> lastUpdateDateMap=new HashMap<String, String>();
		// lastUpdateDateMap.put("CurentDate", lastUpdatedDate);
		//mav.addObject("lastUpdatedDate", lastUpdatedDate);
		 String lastUpdatedDate= realTimeTransactionService.findLastUpdatedRealTimeJob();
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
		
		String fromDate= ValidationCommon.validateString(request.getParameter("date1"));
		String toDate= ValidationCommon.validateString(request.getParameter("date2"));
		mav.setViewName("realTimeTransaction");
		return mav;
	}

	
	
	@RequestMapping(value = "td/realTimeTxn/get", params = { "page", "size","fromdate" }, method = RequestMethod.GET, produces = "application/json")
	public Page<RealTimeTransaction> findPaginatedRealTimeFromToDate(
		      @RequestParam("page") int page, @RequestParam("size") int size,
		      @RequestParam("fromdate") String fromdate) {
		//Page<RealTimeTransaction> resultPage = realTimeTransactionService.findPaginatedFromToDate(page, size,fromDate,toDate);
		      Page<RealTimeTransaction> resultPage = realTimeTransactionService.findPaginated(page, size,fromdate);
				System.out.println("Size of page: "+ size);
				System.out.println("No of page: "+ page);
		        if (page > resultPage.getTotalPages()) {
		            //throw new MyResourceNotFoundException();
		        }
	
		 
		        return resultPage;
		    }
	
	@RequestMapping(value = "td/realTimeTxn/getSearchNext", params = { "page", "size","fromdate", "searchText" }, method = RequestMethod.GET, produces = "application/json")
	public Page<RealTimeTransaction> findPaginatedAfterSearchNextRealTimeFromToDate(
		      @RequestParam("page") int page, @RequestParam("size") int size,
		      @RequestParam("fromdate") String fromdate,  @RequestParam("searchText") String searchText) {

		      Page<RealTimeTransaction> resultPage = realTimeTransactionService.findPaginatedSearchNext(page, size,fromdate, searchText);
				System.out.println("Size of page: "+ size);
				System.out.println("No of page: "+ page);
		        if (page > resultPage.getTotalPages()) {
		            //throw new MyResourceNotFoundException();
		        }

		 
		        return resultPage;
		    }

	
	@RequestMapping(value = "td/realTimeTxn/getSearch", params = { "page", "size","fromdate", "searchText" }, method = RequestMethod.GET, produces = "application/json")
	public Page<RealTimeTransaction> findPaginatedAfterSearchRealTimeFromToDate(
		      @RequestParam("page") int page, @RequestParam("size") int size,
		      @RequestParam("fromdate") String fromdate,  @RequestParam("searchText") String searchText) {
		
		 String passedDate = null;
		 
			
		 if(fromdate!=null && !fromdate.isEmpty() && fromdate.equalsIgnoreCase("yesterday")){
			 SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			 Date curDate=new Date();
			 curDate.setTime(curDate.getTime()-24*60*60*1000); 
			 passedDate=sdf.format(curDate);
			 
				
		 }else if(fromdate!=null && !fromdate.isEmpty() && fromdate.equalsIgnoreCase("today") && !fromdate.equalsIgnoreCase("undefined")){
			 SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			 Date curDate=new Date();
			 passedDate=sdf.format(curDate);
				 
		}else{
				 SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
				 Date curDate=new Date();
				 passedDate=sdf.format(curDate);
		
		}
		
		List<RealTimeTransaction> list = realTimeTxnRepository.findByDate(passedDate);
	
		
		List<RealTimeTransaction> list2 = list.stream().filter(x-> x.getCrclName().equalsIgnoreCase(searchText) || x.getBranchCode().equalsIgnoreCase(searchText)
				|| x.getKioskId().equalsIgnoreCase(searchText) || x.getBranchName().equalsIgnoreCase(searchText))
				.collect(Collectors.toList());


		
		@SuppressWarnings("deprecation")
		Page<RealTimeTransaction> resultPage = new PageImpl<RealTimeTransaction>(list2, PageRequest.of(page, size), list2.size());

		        return resultPage;
		    }
}
