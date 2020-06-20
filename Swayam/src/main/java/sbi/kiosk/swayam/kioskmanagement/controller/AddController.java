package sbi.kiosk.swayam.kioskmanagement.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sbi.kiosk.swayam.common.dto.AddUserDto;
import sbi.kiosk.swayam.kioskmanagement.service.AddUserService;

@RestController
public class AddController {

	@Autowired
	private AddUserService addUserService;
	
	
	@GetMapping("km/getByPfIdSA/{pfId}")
	public ResponseEntity<String>  getByPfId(@PathVariable("pfId") String pfId) {
		String result=addUserService.getByPfId(pfId);
		ResponseEntity<String> entity=ResponseEntity.ok(result);
		return entity;
	}
	
	@PostMapping("km/addUsers")
	public ResponseEntity<String> addUserData(ModelAndView model,@ModelAttribute("addUser") AddUserDto addUser,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		ResponseEntity<String> entity=null;
		String addUserResut=null;
		
		System.out.println("addUserSA  111 Start(-,-)");
		System.out.println("request= userId from hiden==="+request.getParameter("userId"));
		System.out.println("request checkAction action=="+request.getParameter("checkAction"));
		
		System.out.println("request= pf id from hiden==="+request.getParameter("pfId"));

		if ((request.getParameter("checkAction") != null) && !request.getParameter("checkAction").isEmpty()
				&& (request.getParameter("checkAction").equalsIgnoreCase("Edit"))) {
			addUser.setUserId(Integer.parseInt(request.getParameter("userId")));
			
			addUserResut=addUserService.updateUser(addUser,request.getParameter("role"),request.getParameter("circle"));
			String result="User: "+addUserResut+ " has been successfully Updated";
			entity=ResponseEntity.ok(result);
	} else {
		    addUserResut=addUserService.addUser(addUser,request.getParameter("role"),request.getParameter("circle"));
		    System.out.println(addUserResut);
		
		if(addUserResut.equals("User Allready Exists")){
		 }else{
			System.out.println("else add"+addUserResut);
			String result="User: "+addUserResut+ " has been successfully Created";
			entity=ResponseEntity.ok(result);
		 }
		    
	}
	return entity;
	}
	
	@GetMapping("km/getByPfId/{pfId}")
	public ResponseEntity<String>  getByPfIdLA(@PathVariable("pfId") String pfId) {
		String result=addUserService.getByPfId(pfId);
		System.out.println("LA result=="+result);
		ResponseEntity<String> entity=ResponseEntity.ok(result);
		return entity;
	}
	
	@PostMapping("km/addUsersLA")
	public ResponseEntity<String> addUserLA(ModelAndView model,@ModelAttribute("addUserDto") AddUserDto addUserDto,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		ResponseEntity<String> entity=null;
		String addUserResut=null;
		System.out.println("addUserLA  2222 Start(-,-)");
		
		System.out.println("request= userId from hiden==="+request.getParameter("userId"));
		System.out.println("request checkAction action=="+request.getParameter("checkAction"));
		
		System.out.println("request= pf id from hiden==="+request.getParameter("pfId"));

		if ((request.getParameter("checkAction") != null) && !request.getParameter("checkAction").isEmpty()
				&& (request.getParameter("checkAction").equalsIgnoreCase("Edit"))) {
			addUserDto.setUserId(Integer.parseInt(request.getParameter("userId")));
			
			addUserResut=addUserService.updateUser(addUserDto,request.getParameter("role"),request.getParameter("circle"));
			String result="User: "+addUserResut+ " has been successfully Updated";
			entity=ResponseEntity.ok(result);
	} else {
		    addUserResut=addUserService.addUser(addUserDto,request.getParameter("role"),request.getParameter("circle"));
		    System.out.println(addUserResut);
		
		if(addUserResut.equals("User is Allready Exist")){
		 }else{
			System.out.println("else add"+addUserResut);
			String result="User: "+addUserResut+ " has been successfully Created";
			entity=ResponseEntity.ok(result);
		 }
		    
	}
	return entity;
		
		
		
	}
	
	
	
	
	
	@GetMapping("km/reSetAddUser")
	public ModelAndView reSeAddUser(@ModelAttribute("addUser") AddUserDto dto) {
		ModelAndView view=new ModelAndView();
		view.setViewName("addUser");
		return view;
	}
	

}
