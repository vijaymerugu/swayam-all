package sbi.kiosk.swayam.transactiondashboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import sbi.kiosk.swayam.common.entity.DrillDown;
import sbi.kiosk.swayam.transactiondashboard.service.DrillDownService;

@RestController
public class DrillDownController {
	
	@Autowired 
    DrillDownService drillDownService;
	
	@RequestMapping("td/drillDown")
	public ModelAndView drillDownPage(ModelAndView model, HttpSession session) {
		
		try {
			
			System.out.println("drillDownList");
			
			model.setViewName("drillDown");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping("td/drillDownNetwork")
	public ModelAndView drillDownNetwork(ModelAndView model, HttpSession session, HttpServletRequest request) {
		
		try {
			
			System.out.println("drillDownNetworkList");
			
			String circleName = request.getParameter("circleName");
			
			model.addObject("circleName", circleName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	
	@RequestMapping("td/drillDownModule")
	public ModelAndView drillDownModule(ModelAndView model, HttpSession session, HttpServletRequest request) {
		
		try {
		
			System.out.println("drillDownModuleList");
			
			String circleName = request.getParameter("circleName");	
			String networkName = request.getParameter("networkName");
			
			model.addObject("circleName", circleName);
			model.addObject("networkName", networkName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	
	@RequestMapping("td/drillDownRegion")
	public ModelAndView drillDownRegion(ModelAndView model, HttpSession session, HttpServletRequest request) {
		
		try {
			
			System.out.println("drillDownRegionList");
		
			String circleName = request.getParameter("circleName");	
			String networkName = request.getParameter("networkName");
            String moduleName = request.getParameter("moduleName");
                     
            model.addObject("circleName", circleName);
			model.addObject("networkName", networkName);
			model.addObject("moduleName", moduleName);
			
			model.setViewName("drillDownRegion");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	
	
	@RequestMapping("td/drillDownBranch")
	public ModelAndView drillDownBranch(ModelAndView model, HttpSession session, HttpServletRequest request) {
		
		try {
			
			System.out.println("drillDownBranchList");
			
			String circleName = request.getParameter("circleName");	
			String networkName = request.getParameter("networkName");
            String moduleName = request.getParameter("moduleName");
            String regionName = request.getParameter("regionName");
			
            model.addObject("circleName", circleName);
			model.addObject("networkName", networkName);
			model.addObject("moduleName", moduleName);
			model.addObject("regionName", regionName);
						
			model.setViewName("drillDownBranch");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "drillDown/get", params = { "page", "size", "type", "circleName", "networkName", "moduleName", "regionName", "fromDate", "toDate"}, method = RequestMethod.GET, produces = "application/json")
	public Page<DrillDown> findPaginated(
		      @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("type") String type, @RequestParam("circleName") String circleName,
		      @RequestParam("networkName") String networkName, @RequestParam("moduleName") String moduleName, @RequestParam("regionName") String regionName, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
		 
		System.out.println("Circle------- "+circleName);
		System.out.println("Network------- "+networkName);
		System.out.println("Module------- "+moduleName);
		System.out.println("Region------- "+regionName);
		System.out.println("From Date------- "+fromDate);
		System.out.println("To Date------- "+toDate);
		 Page<DrillDown> resultPage = null;
		 
		 if(fromDate.equals("undefined") || toDate.equals("undefined")) {		
				fromDate="05-MAY-20";
				toDate="20-MAY-20";
	     }
		 
		 if(networkName.equals("undefined") || moduleName.equals("undefined") || regionName.equals("undefined")) {	 
			 networkName="";
			 moduleName="";
			 regionName="";
		 }
		 
		 if(!circleName.equals("undefined")) {
		 
				resultPage = drillDownService.findPaginatedByTxnDate(page, size, fromDate, toDate, circleName, networkName, moduleName, regionName);
		 
		 }

		      return resultPage;
		    }

}
