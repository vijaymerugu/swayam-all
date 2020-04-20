package sbi.kiosk.swayam.kioskmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.common.dto.AddUserDto;
import sbi.kiosk.swayam.kioskmanagement.service.AddUserService;

@RestController
@RequestMapping("/km")
public class AddController {

	@Autowired
	private AddUserService addUserService;
	
	
	@GetMapping("/getByPfId/{pfId}")
	public ResponseEntity<String>  getByPfId(@PathVariable("pfId") String pfId) {
		String result=addUserService.getByPfId(pfId);
		ResponseEntity<String> entity=ResponseEntity.ok(result);
		return entity;
	}
	
	@PostMapping("/addUser1")
	public ModelAndView addUserData(@ModelAttribute("addUser") AddUserDto dto) {
		ModelAndView view=new ModelAndView();
		String addUserResut=addUserService.addUser(dto);
		System.out.println(addUserResut);
		view.setViewName("addUser");
		return view;
	}
	
	
	@GetMapping("/reSetAddUser")
	public ModelAndView reSeAddUser(@ModelAttribute("addUser") AddUserDto dto) {
		ModelAndView view=new ModelAndView();
		view.setViewName("addUser");
		return view;
	}

}
