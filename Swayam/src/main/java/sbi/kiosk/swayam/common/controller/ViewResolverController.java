package sbi.kiosk.swayam.common.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ViewResolverController {
	
	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
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
	public ModelAndView kioskManagement() {		
		
		ModelAndView mav = new ModelAndView("kioskManagement");
		return mav;
	}
	
	/*
	 * @RequestMapping(value = "/km/userList") public ModelAndView welcomePage() {
	 * ModelAndView model = new ModelAndView(); model.setViewName("userlist");
	 * return model; }
	 */

}
