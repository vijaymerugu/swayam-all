package sbi.kiosk.swayam.transactiondashboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.common.constants.ExceptionConstants;
import sbi.kiosk.swayam.common.entity.DrillDown;
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
			
			
			String circleName = request.getParameter("circleName");
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			
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
		
			
			String circleName = request.getParameter("circleName");	
			String networkName = request.getParameter("networkName");
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			
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
			
		
			String circleName = request.getParameter("circleName");	
			String networkName = request.getParameter("networkName");
            String moduleName = request.getParameter("moduleName");
            String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
                     
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
			
			String circleName = request.getParameter("circleName");	
			String networkName = request.getParameter("networkName");
            String moduleName = request.getParameter("moduleName");
            String regionName = request.getParameter("regionName");
            String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			
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
		 
		 if(fromDate.equals("undefined") || toDate.equals("undefined")) {		
				//fromDate="05-MAY-20";
				//toDate="20-MAY-20";
	     }
		 
		 if(networkName.equals("undefined") || moduleName.equals("undefined") || regionName.equals("undefined")) {	 
			 networkName="";
			 moduleName="";
			 regionName="";
		 }
		 
		 if(fromDate !=null && !fromDate.isEmpty() && toDate !=null && !toDate.isEmpty()) {
		 
				resultPage = drillDownService.findPaginatedByTxnDate(page, size,type, fromDate, toDate, circleName, networkName, moduleName, regionName);
		 
		 }

		      return resultPage;
		    }

}
