package sbi.kiosk.swayam.common.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.common.dto.MenuMasterDto;
import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.entity.User;
import sbi.kiosk.swayam.common.service.LoginService;

@RestController
public class LoginController{ 
	
	@Autowired
	LoginService loginService;
	
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(@RequestParam("username") String username, HttpSession session) {	
		
		UserDto userObj = loginService.getRoleByUsername(username);
		
		session.setAttribute("username", username);
		session.setAttribute("userObj", userObj);
		
		
		System.out.println("Session Val"+ session.getAttribute("username"));
		ModelAndView mav = new ModelAndView("home");
		return mav;		
	}
	
	@RequestMapping(value="/common/menu", method=RequestMethod.GET)
	public List<MenuMasterDto> getMenu(HttpSession session) {		
		UserDto userObj =(UserDto) session.getAttribute("userObj");
		//session.setAttribute("username", username);
		System.out.println("Session Val1111"+ session.getAttribute("username"));
		
		
		return loginService.getMenusByUserRole(userObj.getRole());
		//ModelAndView mav = new ModelAndView("home");
		//return mav;		
	}
	
	@RequestMapping("/summary")
	public ModelAndView summary() {
		ModelAndView mav = new ModelAndView("userlist");
		return mav;
	}

}
