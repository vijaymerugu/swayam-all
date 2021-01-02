package sbi.kiosk.swayam.transactiondashboard.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.common.constants.ExceptionConstants;
import sbi.kiosk.swayam.common.entity.DrillDown;
import sbi.kiosk.swayam.common.validation.ValidationCommon;
import sbi.kiosk.swayam.transactiondashboard.service.DrillDownService;

@RestController
public class DrillDownController {
	
	Logger logger = LoggerFactory.getLogger(DrillDownController.class);
	
	@Autowired 
    DrillDownService drillDownService;
	
	@RequestMapping("td/drillDown")
	public ModelAndView drillDownPage(ModelAndView model, HttpSession session) {
		
		try {
			
			
			model.setViewName("drillDown");
			
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	
	@RequestMapping("td/drillDownNetwork")
	public ModelAndView drillDownNetwork(ModelAndView model, HttpSession session, HttpServletRequest request) {
		
		try {
			
			
			String circleName = ValidationCommon.validateString(request.getParameter("circleName"));
			String fromDate = ValidationCommon.validateString(request.getParameter("fromDate"));
			String toDate = ValidationCommon.validateString(request.getParameter("toDate"));
			
			model.addObject("circleName", circleName);
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);
			
			model.setViewName("drillDownNetwork");
			
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	
	
	@RequestMapping("td/drillDownModule")
	public ModelAndView drillDownModule(ModelAndView model, HttpSession session, HttpServletRequest request) {
		
		try {
		
			
			String circleName = ValidationCommon.validateString(request.getParameter("circleName"));	
			String networkName = ValidationCommon.validateString(request.getParameter("networkName"));
			String fromDate = ValidationCommon.validateString(request.getParameter("fromDate"));
			String toDate = ValidationCommon.validateString(request.getParameter("toDate"));
			
			model.addObject("circleName", circleName);
			model.addObject("networkName", networkName);
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);
			
			model.setViewName("drillDownModule");
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	
	
	@RequestMapping("td/drillDownRegion")
	public ModelAndView drillDownRegion(ModelAndView model, HttpSession session, HttpServletRequest request) {
		
		try {
			
		
			String circleName = ValidationCommon.validateString(request.getParameter("circleName"));	
			String networkName = ValidationCommon.validateString(request.getParameter("networkName"));
            String moduleName = ValidationCommon.validateString(request.getParameter("moduleName"));
            String fromDate = ValidationCommon.validateString(request.getParameter("fromDate"));
			String toDate = ValidationCommon.validateString(request.getParameter("toDate"));
                     
            model.addObject("circleName", circleName);
			model.addObject("networkName", networkName);
			model.addObject("moduleName", moduleName);
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);
			
			model.setViewName("drillDownRegion");
			
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	
	
	
	@RequestMapping("td/drillDownBranch")
	public ModelAndView drillDownBranch(ModelAndView model, HttpSession session, HttpServletRequest request) {
		
		try {
			
			String circleName = ValidationCommon.validateString(request.getParameter("circleName"));	
			String networkName = ValidationCommon.validateString(request.getParameter("networkName"));
            String moduleName = ValidationCommon.validateString(request.getParameter("moduleName"));
            String regionName = ValidationCommon.validateString(request.getParameter("regionName"));
            String fromDate = ValidationCommon.validateString(request.getParameter("fromDate"));
			String toDate = ValidationCommon.validateString(request.getParameter("toDate"));
			
            model.addObject("circleName", circleName);
			model.addObject("networkName", networkName);
			model.addObject("moduleName", moduleName);
			model.addObject("regionName", regionName);
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);
						
			model.setViewName("drillDownBranch");
			
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	
	@RequestMapping(value = "drillDown/get", params = { "page", "size", "type", "circleName", "networkName", "moduleName", "regionName", "fromDate", "toDate"}, method = RequestMethod.GET, produces = "application/json")
	public Page<DrillDown> findPaginated(
		      @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("type") String type, @RequestParam("circleName") String circleName,
		      @RequestParam("networkName") String networkName, @RequestParam("moduleName") String moduleName, @RequestParam("regionName") String regionName, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
		 Page<DrillDown> resultPage = null;
		 
		 if(networkName.equals("undefined") || moduleName.equals("undefined") || regionName.equals("undefined")) {	 
			 networkName="";
			 moduleName="";
			 regionName="";
		 }
		 
		 if(fromDate !=null && !fromDate.isEmpty() && toDate !=null && !toDate.isEmpty()) {
		 
				resultPage = drillDownService.findPaginatedByTxnDate(page, size,type, fromDate, toDate, circleName, networkName, moduleName, regionName);
		 
		 }else {
					SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
					 Date curDate=new Date();
					 curDate.setTime(curDate.getTime()-48*60*60*1000); 
					 String passedDate=sdf.format(curDate);
				
					String fromdate=passedDate;
					String todate=passedDate;
					logger.info("t-2-fromdate::"+fromdate);
					logger.info("t-2-todate::"+todate);
			 resultPage = drillDownService.findPaginatedByTxnDate(page, size,type, fromdate, todate, circleName, networkName, moduleName, regionName);
		 }

		      return resultPage;
	}
	
	
	@GetMapping("td/getDrillDownLastUpDated")
	public ResponseEntity<String>  getLastUpdatedJob() {
		 String lastUpdatedDate= drillDownService.findSwayamTxnLastUpdatedJob();
		ResponseEntity<String> entity=ResponseEntity.ok(lastUpdatedDate);
		return entity;
	}

}
