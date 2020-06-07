package sbi.kiosk.swayam.common.controller;

import java.util.List;
import java.util.Map;

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
import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.dto.UserManagementDto;
import sbi.kiosk.swayam.common.dto.ZeroTransactionKiosksDto;
import sbi.kiosk.swayam.common.entity.DrillDown;
import sbi.kiosk.swayam.common.entity.ZeroTransactionKiosks;
import sbi.kiosk.swayam.healthmonitoring.service.TicketCentorFilterService;
import sbi.kiosk.swayam.healthmonitoring.service.TicketCentorService;
import sbi.kiosk.swayam.kioskmanagement.service.KioskManagementService;
import sbi.kiosk.swayam.kioskmanagement.service.UserService;
import sbi.kiosk.swayam.transactiondashboard.service.DrillDownService;
import sbi.kiosk.swayam.transactiondashboard.service.ZeroTransactionKiosksService;

@RestController
public class ViewResolverController {
	@Autowired
	KioskManagementService kioskManagementService;
	@Autowired
	TicketCentorService ticketCentorService;
	
	@Autowired
	TicketCentorFilterService ticketCentorFilterService;
	
	@Autowired 
	UserService userService;
	
	@Autowired
	ZeroTransactionKiosksService zeroTransactionKiosksService;
	 
	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
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
	
//	@RequestMapping(value = "/zeroTransactionKiosks/get", params = { "page", "size" ,"type"}, method = RequestMethod.GET, produces = "application/json")
//	public Page<ZeroTransactionKiosksDto> findPaginated(
//		      @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("type") String type) {
//		 
//		System.out.println("type=========Yogesh========="+type);
//		 Page<ZeroTransactionKiosksDto> resultPage = null;
////		if(type.equals("CMF")){
////			resultPage= userService.findPaginatedCount(page, size, type);
////		}else if(type.equals("CMS")){
////			resultPage= userService.findPaginatedCount(page, size, type);
////		}else if(type.equalsIgnoreCase("MUMBAI")){
////			resultPage= userService.findPaginatedCount(page, size, type);
////		}else if(type.equals("LA")){
////			resultPage= userService.findPaginatedCount(page, size, type);
////		}else if(type.equals("SA")){
////			resultPage= userService.findPaginatedCount(page, size, type);
////		}else if(type.equals("CC")){
////		   resultPage= userService.findPaginatedCount(page, size, type);
////	    }else{
//	      resultPage = zeroTransactionKiosksService.findPaginated(page, size);
//		    if (page > resultPage.getTotalPages()){
//		            //throw new MyResourceNotFoundException();
//		        }
////		}
//		 return resultPage;
//		    }
	
	@RequestMapping(value = "/zeroTransactionKiosks/get", params = { "page", "size" ,"type"}, method = RequestMethod.GET, produces = "application/json")
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
	
	@RequestMapping("/km/userkioskmapping")
	public ModelAndView userKioskMapping() {		
		
		ModelAndView mav = new ModelAndView("userkioskmapping");
		return mav;
	}
	
	@RequestMapping("/km/cmscmfmapping")
	public ModelAndView cmsCmfkMapping() {		
		
		ModelAndView mav = new ModelAndView("cmscmfmapping");
		return mav;
	}
	
	@RequestMapping("/km/kioskManagement")
	public ModelAndView kioskManagement(ModelAndView mav) {
		Map<String, Integer> mapDataCount = null;
		mapDataCount = kioskManagementService.findAllKioskMasterCount();
		if (mapDataCount != null && !mapDataCount.isEmpty()) {
			mav.addObject("mapDataCount", mapDataCount);
		}

		mav.setViewName("kioskManagement");
		return mav;
	}

	
	@RequestMapping("/hm/ticketcentor")
	public ModelAndView ticketCentor(ModelAndView mav) {

		Map<String, Integer> mapDataList = null;
		mapDataList = ticketCentorService.findAllSeverityOfTicketsCount();
		if (mapDataList != null && !mapDataList.isEmpty()) {
			mav.addObject("mapDataList", mapDataList);
		}
		
		Map<String, Integer> ageingMapDataList = null;
		ageingMapDataList = ticketCentorService.findAllAgeingOfTicketsCount();
		if (ageingMapDataList != null && !ageingMapDataList.isEmpty()) {
			mav.addObject("ageingMapDataList", ageingMapDataList);
		}
		mav.setViewName("ticketCentor");
		return mav;
	}

	
	
	
	@RequestMapping("/hm/ticketcentorCallCategory")
	public ModelAndView ticketcentorCallCategory(ModelAndView mav,HttpServletRequest request) {

		Map<String, Integer> mapDataList = null;
		mapDataList = ticketCentorFilterService.findAllSeverityOfTicketsCount();
		if (mapDataList != null && !mapDataList.isEmpty()) {
			mav.addObject("mapDataList", mapDataList);
		}
		
		Map<String, Integer> ageingMapDataList = null;
		ageingMapDataList = ticketCentorFilterService.findAllAgeingOfTicketsCount();
		if (ageingMapDataList != null && !ageingMapDataList.isEmpty()) {
			mav.addObject("ageingMapDataList", ageingMapDataList);
		}

		Map<String, Object> categoryMapDataList  =ticketCentorFilterService.findAllCategory();
		if (categoryMapDataList != null && !categoryMapDataList.isEmpty()) {
			mav.addObject("categoryMapDataList", categoryMapDataList);
		}
		mav.setViewName("ticketCentorSA");
		return mav;
	}
	
	@RequestMapping("/km/headerTemplate")
	public ModelAndView headerTemplate() {		
		
		ModelAndView mav = new ModelAndView("header-template");
		return mav;
	}

}
