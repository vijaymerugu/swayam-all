package sbi.kiosk.swayam.kioskmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.entity.User;
import sbi.kiosk.swayam.kioskmanagement.service.KioskManagementService;

@RestController
public class KioskManagementController {	
	
	@Autowired
	KioskManagementService kioskManagementService;
	
	@RequestMapping("/km/userkioskmapping/usersbyca")
	public List<User> fetchAllUsersByCircleAdmin(HttpSession session) {
		System.out.println("22222222222222222222");
		UserDto user = (UserDto) session.getAttribute("userObj");
		
		return kioskManagementService.fetchAllUsersByCircleAdmin(user.getUsername(),user.getCircle());
		
		
	}
	
	@RequestMapping("/km/userkioskmapping/kiosksbycircle")
	public List<String> fetchAllKiosksByCircleAndNotInUserKioskMapping(HttpSession session) {
		System.out.println("333333333333333333333");
		UserDto user = (UserDto) session.getAttribute("userObj");
		
		return kioskManagementService.fetchAllKiosksByCircleAndNotInUserKioskMapping(user.getCircle());
		
		
	}
	
	@RequestMapping(value ="/km/userkioskmapping/kiosksbycirclepost", method = RequestMethod.POST)
	public ModelAndView saveUserKioskMapping( @RequestParam(value="username") String username, @RequestParam(value="kioskIdList") ArrayList<String> kioskIdList) {
		
		System.out.println("444444444444444");
		//UserDto user = (UserDto) session.getAttribute("userObj");
		kioskManagementService.saveUserKioskMapping(username, kioskIdList);
		
		
		//return kioskManagementService.fetchAllKiosksByCircleAndNotInUserKioskMapping(user.getCircle());
		ModelAndView mav = new ModelAndView("userkioskmapping");
		return mav;
		
	}
	
	@RequestMapping("/km/cmscmfmapping/cmsusersbyca")
	public List<User> fetchAllCmsUsersByCircleAdmin(HttpSession session) {
		System.out.println("22222222222222222222");
		UserDto user = (UserDto) session.getAttribute("userObj");
		
		return kioskManagementService.fetchAllCmsUsersByCircleAdmin(user.getUsername(),user.getCircle());
		
		
	}
	
	@RequestMapping("/km/cmscmfmapping/cmfusersbyca")
	public List<User> fetchAllCmfUsersByCircleAndInUserKioskMapping(HttpSession session) {
		System.out.println("333333333333333333333");
		UserDto user = (UserDto) session.getAttribute("userObj");
		
		return kioskManagementService.fetchAllCmfUsersByCircleAndInUserKioskMapping(user.getCircle());
		
		
	}
	
	@RequestMapping(value ="/km/cmscmfmapping/cmscmfmappingpost", method = RequestMethod.POST)
	public ModelAndView saveCmsCmfUserMapping( @RequestParam(value="username") String cmsusername, @RequestParam(value="cmfUserIdIdList") ArrayList<String> cmfUserIdIdList) {
		
		System.out.println("444444444444444");
		//UserDto user = (UserDto) session.getAttribute("userObj");
		kioskManagementService.saveCmsCmfUserMapping(cmsusername, cmfUserIdIdList);
		
		
		//return kioskManagementService.fetchAllKiosksByCircleAndNotInUserKioskMapping(user.getCircle());
		ModelAndView mav = new ModelAndView("cmscmfmapping");
		return mav;
		
		
	}
	
	
	
	
}
