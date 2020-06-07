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
import sbi.kiosk.swayam.common.dto.DrillDownDto;
import sbi.kiosk.swayam.common.entity.DrillDown;
import sbi.kiosk.swayam.transactiondashboard.service.DrillDownService;

@RestController
public class DrillDownController {
	
	/*
	 * @Autowired UserService userService;
	 */
	
	@Autowired 
    DrillDownService drillDownService;
	
	@RequestMapping("/td/drillDown")
	public ModelAndView drillDownPage(ModelAndView model, HttpSession session) {
		
		try {
			//UserDto user = (UserDto) session.getAttribute("userObj");
			System.out.println("drillDownList");
			//List<DrillDownDto> drillDownList = drillDownService.list();
			
			//List<UserManagementDto> userList = userService.findAllUsers(user);
			//model.addObject("usersList", userList);
			/*if (user.getRole().equals("CC")) {
				model.setViewName("userlistView");
			} else if (user.getRole().equals("LA")) {
				System.out.println("Users========Yogesh=============================="+user.getCircle());
				int cmsCount=userService.findCMSCount();
				int circleCountByRole=userService.findCircleCountByRole(user.getCircle());
				int ccCount=userService.findCCCount();
				int cmfCount= userService.findCMFCount();
				int laCount= userService.findLACount();
				int saCount= userService.findSACount();
				
				model.addObject("cmfCount",cmfCount);
				model.addObject("cmsCount", cmsCount);
				model.addObject("circleCountByRole", circleCountByRole);
				model.addObject("laCount",laCount);
				model.addObject("ccCount",ccCount);
				model.addObject("saCount",saCount);
				
				model.setViewName("userlistLA");
			}			
			else {
				// Vijay Login
				System.out.println("Users========Vijay=============================="+user.getCircle());
				int cmsCount=userService.findCMSCount();
				int circleCount=userService.findCircleCount();
				int ccCount=userService.findCCCount();
				int cmfCount= userService.findCMFCount();
				int laCount= userService.findLACount();
				int saCount= userService.findSACount();
				
				model.addObject("cmfCount",cmfCount);
				model.addObject("cmsCount", cmsCount);
				model.addObject("circleCount", circleCount);
				model.addObject("laCount",laCount);
				model.addObject("ccCount",ccCount);
				model.addObject("saCount",saCount);*/
				model.setViewName("drillDown");
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping("/td/drillDownNetwork")
	public ModelAndView drillDownNetwork(ModelAndView model, HttpSession session, HttpServletRequest request) {
		
		try {
			//UserDto user = (UserDto) session.getAttribute("userObj");
			System.out.println("drillDownNetworkList");
			
			String circleName = request.getParameter("circleName");
			//List<DrillDown> drillDownList = drillDownService.list();
			model.addObject("circleName", circleName);
			//List<UserManagementDto> userList = userService.findAllUsers(user);
			//model.addObject("usersList", userList);
//			if (user.getRole().equals("CC")) {
//				model.setViewName("userlistView");
//			} else if (user.getRole().equals("LA")) {
//				System.out.println("Users========Yogesh=============================="+user.getCircle());
//				int cmsCount=userService.findCMSCount();
//				int circleCountByRole=userService.findCircleCountByRole(user.getCircle());
//				int ccCount=userService.findCCCount();
//				int cmfCount= userService.findCMFCount();
//				int laCount= userService.findLACount();
//				int saCount= userService.findSACount();
//				
//				model.addObject("cmfCount",cmfCount);
//				model.addObject("cmsCount", cmsCount);
//				model.addObject("circleCountByRole", circleCountByRole);
//				model.addObject("laCount",laCount);
//				model.addObject("ccCount",ccCount);
//				model.addObject("saCount",saCount);
//				
//				model.setViewName("userlistLA");
//			}			
//			else {
//				// Vijay Login
//				System.out.println("Users========Vijay=============================="+user.getCircle());
//				int cmsCount=userService.findCMSCount();
//				int circleCount=userService.findCircleCount();
//				int ccCount=userService.findCCCount();
//				int cmfCount= userService.findCMFCount();
//				int laCount= userService.findLACount();
//				int saCount= userService.findSACount();
//				
//				model.addObject("cmfCount",cmfCount);
//				model.addObject("cmsCount", cmsCount);
//				model.addObject("circleCount", circleCount);
//				model.addObject("laCount",laCount);
//				model.addObject("ccCount",ccCount);
//				model.addObject("saCount",saCount);
				model.setViewName("drillDownNetwork");
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	
	@RequestMapping("/td/drillDownModule")
	public ModelAndView drillDownModule(ModelAndView model, HttpSession session, HttpServletRequest request) {
		
		try {
			//UserDto user = (UserDto) session.getAttribute("userObj");
			System.out.println("drillDownModuleList");
			//List<DrillDown> drillDownList = drillDownService.list();
			
			String circleName = request.getParameter("circleName");	
			String networkName = request.getParameter("networkName");
			
			model.addObject("circleName", circleName);
			model.addObject("networkName", networkName);
			
			//List<UserManagementDto> userList = userService.findAllUsers(user);
			//model.addObject("usersList", userList);
			/*if (user.getRole().equals("CC")) {
				model.setViewName("userlistView");
			} else if (user.getRole().equals("LA")) {
				System.out.println("Users========Yogesh=============================="+user.getCircle());
				int cmsCount=userService.findCMSCount();
				int circleCountByRole=userService.findCircleCountByRole(user.getCircle());
				int ccCount=userService.findCCCount();
				int cmfCount= userService.findCMFCount();
				int laCount= userService.findLACount();
				int saCount= userService.findSACount();
				
				model.addObject("cmfCount",cmfCount);
				model.addObject("cmsCount", cmsCount);
				model.addObject("circleCountByRole", circleCountByRole);
				model.addObject("laCount",laCount);
				model.addObject("ccCount",ccCount);
				model.addObject("saCount",saCount);
				
				model.setViewName("userlistLA");
			}			
			else {
				// Vijay Login
				System.out.println("Users========Vijay=============================="+user.getCircle());
				int cmsCount=userService.findCMSCount();
				int circleCount=userService.findCircleCount();
				int ccCount=userService.findCCCount();
				int cmfCount= userService.findCMFCount();
				int laCount= userService.findLACount();
				int saCount= userService.findSACount();
				
				model.addObject("cmfCount",cmfCount);
				model.addObject("cmsCount", cmsCount);
				model.addObject("circleCount", circleCount);
				model.addObject("laCount",laCount);
				model.addObject("ccCount",ccCount);
				model.addObject("saCount",saCount);*/
				model.setViewName("drillDownModule");
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	
	@RequestMapping("/td/drillDownRegion")
	public ModelAndView drillDownRegion(ModelAndView model, HttpSession session, HttpServletRequest request) {
		
		try {
			//UserDto user = (UserDto) session.getAttribute("userObj");
			System.out.println("drillDownRegionList");
			//List<DrillDown> drillDownList = drillDownService.list();
			
			
			String circleName = request.getParameter("circleName");	
			String networkName = request.getParameter("networkName");
            String moduleName = request.getParameter("moduleName");
                     
            model.addObject("circleName", circleName);
			model.addObject("networkName", networkName);
			model.addObject("moduleName", moduleName);
			
			//List<UserManagementDto> userList = userService.findAllUsers(user);
			//model.addObject("usersList", userList);
			/*if (user.getRole().equals("CC")) {
				model.setViewName("userlistView");
			} else if (user.getRole().equals("LA")) {
				System.out.println("Users========Yogesh=============================="+user.getCircle());
				int cmsCount=userService.findCMSCount();
				int circleCountByRole=userService.findCircleCountByRole(user.getCircle());
				int ccCount=userService.findCCCount();
				int cmfCount= userService.findCMFCount();
				int laCount= userService.findLACount();
				int saCount= userService.findSACount();
				
				model.addObject("cmfCount",cmfCount);
				model.addObject("cmsCount", cmsCount);
				model.addObject("circleCountByRole", circleCountByRole);
				model.addObject("laCount",laCount);
				model.addObject("ccCount",ccCount);
				model.addObject("saCount",saCount);
				
				model.setViewName("userlistLA");
			}			
			else {
				// Vijay Login
				System.out.println("Users========Vijay=============================="+user.getCircle());
				int cmsCount=userService.findCMSCount();
				int circleCount=userService.findCircleCount();
				int ccCount=userService.findCCCount();
				int cmfCount= userService.findCMFCount();
				int laCount= userService.findLACount();
				int saCount= userService.findSACount();
				
				model.addObject("cmfCount",cmfCount);
				model.addObject("cmsCount", cmsCount);
				model.addObject("circleCount", circleCount);
				model.addObject("laCount",laCount);
				model.addObject("ccCount",ccCount);
				model.addObject("saCount",saCount);*/
				model.setViewName("drillDownRegion");
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	
	
	@RequestMapping("/td/drillDownBranch")
	public ModelAndView drillDownBranch(ModelAndView model, HttpSession session, HttpServletRequest request) {
		
		try {
			//UserDto user = (UserDto) session.getAttribute("userObj");
			System.out.println("drillDownBranchList");
			//List<DrillDown> drillDownList = drillDownService.list();
			
			String circleName = request.getParameter("circleName");	
			String networkName = request.getParameter("networkName");
            String moduleName = request.getParameter("moduleName");
            String regionName = request.getParameter("regionName");
			
            model.addObject("circleName", circleName);
			model.addObject("networkName", networkName);
			model.addObject("moduleName", moduleName);
			model.addObject("regionName", regionName);
			
			//List<UserManagementDto> userList = userService.findAllUsers(user);
			//model.addObject("usersList", userList);
			/*if (user.getRole().equals("CC")) {
				model.setViewName("userlistView");
			} else if (user.getRole().equals("LA")) {
				System.out.println("Users========Yogesh=============================="+user.getCircle());
				int cmsCount=userService.findCMSCount();
				int circleCountByRole=userService.findCircleCountByRole(user.getCircle());
				int ccCount=userService.findCCCount();
				int cmfCount= userService.findCMFCount();
				int laCount= userService.findLACount();
				int saCount= userService.findSACount();
				
				model.addObject("cmfCount",cmfCount);
				model.addObject("cmsCount", cmsCount);
				model.addObject("circleCountByRole", circleCountByRole);
				model.addObject("laCount",laCount);
				model.addObject("ccCount",ccCount);
				model.addObject("saCount",saCount);
				
				model.setViewName("userlistLA");
			}			
			else {
				// Vijay Login
				System.out.println("Users========Vijay=============================="+user.getCircle());
				int cmsCount=userService.findCMSCount();
				int circleCount=userService.findCircleCount();
				int ccCount=userService.findCCCount();
				int cmfCount= userService.findCMFCount();
				int laCount= userService.findLACount();
				int saCount= userService.findSACount();
				
				model.addObject("cmfCount",cmfCount);
				model.addObject("cmsCount", cmsCount);
				model.addObject("circleCount", circleCount);
				model.addObject("laCount",laCount);
				model.addObject("ccCount",ccCount);
				model.addObject("saCount",saCount);*/
				model.setViewName("drillDownBranch");
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	/*
	 * @RequestMapping(value = "/drillDown/get", params = { "page", "size", "type",
	 * "circleName", "networkName", "moduleName", "regionName"}, method =
	 * RequestMethod.GET, produces = "application/json") public Page<DrillDownDto>
	 * findPaginated(
	 * 
	 * @RequestParam("page") int page, @RequestParam("size") int
	 * size, @RequestParam("type") String type, @RequestParam("circleName") String
	 * circleName,
	 * 
	 * @RequestParam("networkName") String networkName, @RequestParam("moduleName")
	 * String moduleName, @RequestParam("regionName") String regionName) {
	 * 
	 * //System.out.println("type=========Yogesh========="+type);
	 * System.out.println("Circle------- "+circleName);
	 * System.out.println("Network------- "+networkName);
	 * System.out.println("Module------- "+moduleName);
	 * System.out.println("Region------- "+regionName); Page<DrillDownDto>
	 * resultPage = null;
	 * 
	 * if(circleName.equals("undefined")) {
	 * 
	 * resultPage = drillDownService.findPaginated(page, size);
	 * 
	 * }
	 * 
	 * if(!circleName.equals("undefined") && !circleName.equals("")) {
	 * 
	 * resultPage = drillDownService.findPaginatedByCircle(page, size, circleName);
	 * }
	 * 
	 * if(!networkName.equals("undefined") && !networkName.equals("")) {
	 * 
	 * resultPage = drillDownService.findPaginatedByNetwork(page, size,
	 * networkName); }
	 * 
	 * if(!moduleName.equals("undefined")) {
	 * 
	 * resultPage = drillDownService.findPaginatedByModule(page, size, moduleName);
	 * }
	 * 
	 * if(!regionName.equals("undefined")) {
	 * 
	 * resultPage = drillDownService.findPaginatedByRegion(page, size, regionName);
	 * }
	 * 
	 * if (page > resultPage.getTotalPages()){ //throw new
	 * MyResourceNotFoundException(); }
	 * 
	 * return resultPage; }
	 */
	
	@RequestMapping(value = "/drillDown/get", params = { "page", "size", "type", "circleName", "networkName", "moduleName", "regionName"}, method = RequestMethod.GET, produces = "application/json")
	public Page<DrillDown> findPaginated(
		      @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("type") String type, @RequestParam("circleName") String circleName,
		      @RequestParam("networkName") String networkName, @RequestParam("moduleName") String moduleName, @RequestParam("regionName") String regionName, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
		 
		//System.out.println("type=========Yogesh========="+type);
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
		 
//		 if(circleName.equals("undefined")) {
//		 
//	          resultPage = drillDownService.findPaginatedByDateProc(page, size); 
//	      
//		 }
//		 
//	      if(!circleName.equals("undefined") && !circleName.equals("")) {
//	        
//	    	  resultPage = drillDownService.findPaginatedByCircle(page, size, circleName);
//	      }
//	      
//	      if(!networkName.equals("undefined") && !networkName.equals("")) {
//	    	  
//	    	  resultPage = drillDownService.findPaginatedByNetwork(page, size, networkName);
//	      }
//	      
//          if(!moduleName.equals("undefined")) {
//	    	  
//	    	  resultPage = drillDownService.findPaginatedByModule(page, size, moduleName);
//	      }
//          
//          if(!regionName.equals("undefined")) {
//	    	  
//	    	  resultPage = drillDownService.findPaginatedByRegion(page, size, regionName);
//	      }
//		    
//	      if (page > resultPage.getTotalPages()){
//		            //throw new MyResourceNotFoundException();
//		  }

		      return resultPage;
		    }

}
