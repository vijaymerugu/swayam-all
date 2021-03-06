package sbi.kiosk.swayam.kioskmanagement.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sbi.kiosk.swayam.common.dto.AddUserDto;
import sbi.kiosk.swayam.common.entity.User;
import sbi.kiosk.swayam.kioskmanagement.service.AddUserService;

@RestController
public class AddController {
	Logger logger = LoggerFactory.getLogger(AddController.class);

	@Autowired
	private AddUserService addUserService;
	
	
	@GetMapping("km/getByPfIdSA/{pfId}")
	public ResponseEntity<String>  getByPfId(@PathVariable("pfId") String pfId) {
		String result=addUserService.getByPfId(pfId);
		ResponseEntity<String> entity=ResponseEntity.ok(result);
		return entity;
	}
	
	 @PostMapping("km/addUsers") 

//	@PreAuthorize("hasPermission('ACkmaddUserData','CREATE')")
	public ResponseEntity<String> addUserData(ModelAndView model,@ModelAttribute("addUser") AddUserDto addUser,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		ResponseEntity<String> entity=null;
		String addUserResut=null;
		
		
		if ((request.getParameter("checkAction") != null) && !request.getParameter("checkAction").isEmpty()
				&& (request.getParameter("checkAction").equalsIgnoreCase("Edit"))) {
			addUser.setUserId(Integer.parseInt(request.getParameter("userId")));
			
			addUserResut=addUserService.updateUser(addUser,request.getParameter("role"),request.getParameter("circle"));
			String result="User: "+addUserResut+ " has been successfully Updated";
			entity=ResponseEntity.ok(result);
	} else {
		    addUserResut=addUserService.addUser(addUser,request.getParameter("role"),request.getParameter("circle"));
		
		if(addUserResut.equals("User Allready Exists")){
		 }else{
			String result="User: "+addUserResut+ " has been successfully Created";
			entity=ResponseEntity.ok(result);
		 }
		    
	}
	return entity;
	}
	
	@GetMapping("km/getByPfId/{pfId}")
	public ResponseEntity<String>  getByPfIdLA(@PathVariable("pfId") String pfId) {
		String result=addUserService.getByPfId(pfId);
		ResponseEntity<String> entity=ResponseEntity.ok(result);
		return entity;
	}
	
	@GetMapping("km/getReportingAuthMailIdByPfId/{pfId}")
	public ResponseEntity<List<User>>  getRepAuthByPfIdLA(@PathVariable("pfId") String pfId) {
		List<User> result=addUserService.getReportingAutMailIdByPfId(pfId);
		ResponseEntity<List<User>> entity=ResponseEntity.ok(result);
		return entity;
	}
	
	@PostMapping("km/addUsersLA")
//	@PreAuthorize("hasPermission('ACkmaddUserLA','CREATE')")
	public ResponseEntity<String> addUserLA(ModelAndView model,@ModelAttribute("addUserDto") AddUserDto addUserDto,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		ResponseEntity<String> entity=null;
		String addUserResut=null;

		if ((request.getParameter("checkAction") != null) && !request.getParameter("checkAction").isEmpty()
				&& (request.getParameter("checkAction").equalsIgnoreCase("Edit"))) {
			addUserDto.setUserId(Integer.parseInt(request.getParameter("userId")));
			
			addUserResut=addUserService.updateUser(addUserDto,request.getParameter("role"),request.getParameter("circle"));
			String result="User: "+addUserResut+ " has been successfully Updated";
			entity=ResponseEntity.ok(result);
	} else {
		
		logger.info("pfId:::::::::::"+request.getParameter("pfId"));
		logger.info("addUserDto::::::reportingAuthorityPfId:::::"+addUserDto.getReportingAuthorityPfId());
		
		logger.info("addUserDto::::::reportingAuthorityPfId:::::"+request.getParameter("reportingAuthorityPfId"));
		
		logger.info("Role:::::::::::"+request.getParameter("role"));
        if(request.getParameter("role").equals("BM") || request.getParameter("role").equals("CMF")) {
        	logger.info("Role::::::::if:::");
        	
        	addUserResut=addUserService.addUserBMAndCMF(addUserDto,request.getParameter("role"),request.getParameter("circle"));
          }else {
		    addUserResut=addUserService.addUser(addUserDto,request.getParameter("role"),request.getParameter("circle"));
          }
		if(addUserResut.equals("User is Allready Exist")){
			entity=ResponseEntity.ok(addUserResut);
		 }else{
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
