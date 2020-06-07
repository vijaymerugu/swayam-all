package sbi.kiosk.swayam.transactiondashboard.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.common.dto.ZeroTransactionKiosksDto;
import sbi.kiosk.swayam.common.entity.ZeroTransactionKiosks;
import sbi.kiosk.swayam.kioskmanagement.service.UserService;
import sbi.kiosk.swayam.transactiondashboard.service.ZeroTransactionKiosksService;

public class ZeroTransactionKiosksController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ZeroTransactionKiosksService zeroTransactionKiosksService;
	
	@RequestMapping("/td/zeroTransactionKiosks")
	public ModelAndView zeroTransactionKiosksPage(ModelAndView model, HttpSession session) {
		
		try {
			//UserDto user = (UserDto) session.getAttribute("userObj");
			System.out.println("zeroTransactionKiosksList");
			//List<ZeroTransactionKiosksDto> zeroTransactionKiosksList = zeroTransactionKiosksService.list();
			
			//List<UserManagementDto> userList = userService.findAllUsers(user);
			//model.addObject("usersList", zeroTransactionKiosksList);
			model.setViewName("zeroTransactionKiosks");
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
				model.addObject("saCount",saCount);
				model.setViewName("drillDown");
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	/*
	 * @RequestMapping(value = "/zeroTransactionKiosks/get", params = { "page",
	 * "size" ,"type"}, method = RequestMethod.GET, produces = "application/json")
	 * public Page<ZeroTransactionKiosksDto> findPaginated(
	 * 
	 * @RequestParam("page") int page, @RequestParam("size") int
	 * size, @RequestParam("type") String type) {
	 * 
	 * System.out.println("type=========Yogesh========="+type);
	 * Page<ZeroTransactionKiosksDto> resultPage = null; resultPage =
	 * zeroTransactionKiosksService.findPaginated(page, size); if (page >
	 * resultPage.getTotalPages()){ //throw new MyResourceNotFoundException(); }
	 * return resultPage; }
	 */
	
	@RequestMapping(value = "/zeroTransactionKiosks/get", params = { "page", "size" ,"type"}, method = RequestMethod.GET, produces = "application/json")
	public Page<ZeroTransactionKiosks> findPaginated(
		      @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("type") String type, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
		 
		System.out.println("type=========Yogesh========="+type);
		System.out.println("From Date--- "+fromDate);
		System.out.println("To Date----- "+toDate);
		Page<ZeroTransactionKiosks> resultPage = null;
	      resultPage = zeroTransactionKiosksService.findPaginatedByDate(page, size, fromDate, toDate);
		    if (page > resultPage.getTotalPages()){
		            //throw new MyResourceNotFoundException();
		        }
		  return resultPage;
	 }

}
