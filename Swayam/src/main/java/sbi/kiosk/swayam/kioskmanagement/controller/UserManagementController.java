package sbi.kiosk.swayam.kioskmanagement.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.common.constants.ExceptionConstants;
import sbi.kiosk.swayam.common.dto.AddUserDto;
import sbi.kiosk.swayam.common.dto.RolesDto;
import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.dto.UserManagementDto;
import sbi.kiosk.swayam.common.entity.Circle;
import sbi.kiosk.swayam.common.entity.User;
import sbi.kiosk.swayam.kioskmanagement.repository.UsersRepository;
import sbi.kiosk.swayam.kioskmanagement.service.RoleService;
import sbi.kiosk.swayam.kioskmanagement.service.UserService;

@RestController
public class UserManagementController {
	
	Logger logger = LoggerFactory.getLogger(UserManagementController.class);
	
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	
	 @Autowired
	 UsersRepository userRepo;

	@RequestMapping(value = { "km/userList" })
//	@PreAuthorize("hasPermission('UMuserList','CREATE')")
	public ModelAndView userList(ModelAndView model, HttpSession session) {

		try {
			UserDto user = (UserDto) session.getAttribute("userObj");
			List<UserManagementDto> userList = userService.findAllUsers(user);
			model.addObject("usersList", userList);
			/* Commented on 22-09-2020 
			 * if (user.getRole().equals("CC")) { model.setViewName("userlistView"); } else
			 */ if (user.getRole().equals("LA")) {
				int cmsCount=userService.findCMSCountByCircle();
				//int circleCountByRole=userService.findCircleCountByRole(user.getCircle());
				//int ccCount=userService.findCCCount();
				int cmfCount= userService.findCMFCountByCircle();
				//int laCount= userService.findLACount();
				//int saCount= userService.findSACount();
				int circleUserCount = userService.findCircleUserCountByCircle();
				int laCount= userService.findLACountByCircle();
				
				model.addObject("cmfCount",cmfCount);
				model.addObject("cmsCount", cmsCount);
				//model.addObject("circleCountByRole", circleCountByRole);
				model.addObject("laCount",laCount);
				//model.addObject("ccCount",ccCount);
				//model.addObject("saCount",saCount);
				model.addObject("circleUserCount",circleUserCount);
				
				model.setViewName("userlistLA");
			}			
			else {
				// Vijay Login
				int cmsCount=userService.findCMSCount();
				int circleCount=userService.findCircleCount();
				int ccCount=userService.findCCCount();
				int cmfCount= userService.findCMFCount();
				int laCount= userService.findLACount();
				int saCount= userService.findSACount();
				int circleUserCount = userService.findCircleUserCount();
				
				model.addObject("cmfCount",cmfCount);
				model.addObject("cmsCount", cmsCount);
				model.addObject("circleCount", circleCount);
				model.addObject("laCount",laCount);
				model.addObject("ccCount",ccCount);
				model.addObject("saCount",saCount);
				model.addObject("circleUserCount",circleUserCount);
				model.setViewName("userlist");
			}
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	
	@RequestMapping(value = { "km/userListCC" })
//	@PreAuthorize("hasPermission('UMuserListCC','CREATE')")
	public ModelAndView userListCC(HttpSession session) {
		ModelAndView model = new ModelAndView("userlistCC");
		try {			
			UserDto user = (UserDto) session.getAttribute("userObj");
			List<UserManagementDto> userList = userService.findAllUsers(user);
			model.addObject("usersList", userList);
				
			if (user.getRole().equals("SA")) {
				int cmsCount=userService.findCMSCount();
				int circleCount=userService.findCircleCount();
				int ccCount=userService.findCCCount();
				int cmfCount= userService.findCMFCount();
				int laCount= userService.findLACount();
				int saCount= userService.findSACount();
				int circleUserCount = userService.findCircleUserCount();
				
				model.addObject("cmfCount",cmfCount);
				model.addObject("cmsCount", cmsCount);
				model.addObject("circleCount", circleCount);
				model.addObject("laCount",laCount);
				model.addObject("ccCount",ccCount);
				model.addObject("saCount",saCount);
				model.addObject("circleUserCount",circleUserCount);
				model.setViewName("userlist");
			}
			else {
				int cmsCount=userService.findCMSCount();
				int circleCount=userService.findCircleUserCount();
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
			}			
			
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	

	
	
	@RequestMapping(value = "users/get", params = { "page", "size" ,"type"}, method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasPermission('UMfindPaginatedUserGet','CREATE')")
	public Page<UserManagementDto> findPaginated(
		      @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("type") String type) {
		 Page<UserManagementDto> resultPage = null;
		if(type.equals("CMF")){
			resultPage= userService.findPaginatedCount(page, size, type);
		}else if(type.equals("CMS")){
			resultPage= userService.findPaginatedCount(page, size, type);
		}else if(type.equalsIgnoreCase("C")){
			resultPage= userService.findPaginatedCount(page, size, type);
		}else if(type.equals("LA")){
			resultPage= userService.findPaginatedCount(page, size, type);
		}else if(type.equals("SA")){
			resultPage= userService.findPaginatedCount(page, size, type);
		}else if(type.equals("CC")){
		   resultPage= userService.findPaginatedCount(page, size, type);
	    }else{
	      resultPage = userService.findPaginated(page, size);
		    if (page > resultPage.getTotalPages()){
		            //throw new MyResourceNotFoundException();
		        }
		}
		 return resultPage;
		    }
	
	@RequestMapping(value = "usersByCircle/get", params = { "page", "size" ,"type"}, method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasPermission('UMfindPaginatedByCircleUserGet','CREATE')")
	public Page<UserManagementDto> findPaginatedByCircle(
		      @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("type") String type) {
		 
		 Page<UserManagementDto> resultPage = null;
		if(type.equals("CMF")){
			resultPage= userService.findPaginatedCountByCircle(page, size, type);
		}else if(type.equals("CMS")){
			resultPage= userService.findPaginatedCountByCircle(page, size, type);
		}else if(type.equalsIgnoreCase("C")){
			resultPage= userService.findPaginatedCountByCircle(page, size, type);
		}else if(type.equals("LA")){
			resultPage= userService.findPaginatedCountByCircle(page, size, type);
		}else{
	      resultPage = userService.findPaginatedByCircle(page, size);
		    if (page > resultPage.getTotalPages()){
		        }
		}
		 return resultPage;
	}
	
	

	

	

	@RequestMapping(value = { "km/addUser" })
//	@PreAuthorize("hasPermission('UMkmaddUser','CREATE')")
	public ModelAndView addUser(ModelAndView model, @ModelAttribute("addUser") AddUserDto addUser,HttpServletRequest request) {

		try {
	              List<RolesDto> roleList = roleService.findAllRole();
		           model.addObject("roleList", roleList);
		           List<Circle> circleList = roleService.findAllCircle();
		           model.addObject("circleList", circleList);
		           model.setViewName("addUser");
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	
	
	@RequestMapping(value = { "km/addUserLA" })
	@PreAuthorize("hasPermission('UMkmaddUserLA','CREATE')")
	public ModelAndView addUserLa(ModelAndView model, @ModelAttribute("addUserDto") AddUserDto addUserDto ,  HttpSession session) {

		try {
	              List<RolesDto> roleList = roleService.findAllRole();	
	              Iterator<RolesDto> itr = roleList.iterator();
	              while(itr.hasNext()){
	            	  String role = itr.next().getRole();
	            	  if(role.equals("SA") || role.equals("CC") || role.equals("BC") || role.equals("BM") ){
	            		  itr.remove();
	            	  }
	              }
		           model.addObject("roleList", roleList);
		        //   List<Circle> circleList = roleService.findAllCircle();  
					
					  UserDto user = (UserDto) session.getAttribute("userObj");
						/*
						 * @SuppressWarnings("unchecked") List<User> circleList = (List<User>)
						 * userService.getUserByPfId(user.getPfId());
						 */
					 
					  String circleList = userRepo.findCircleByPfId(user.getPfId());
		          model.addObject("circleList", circleList);
		          model.setViewName("addUserLA");
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}

	
	
	@RequestMapping(value = "km/editUserMaster")
	@PreAuthorize("hasPermission('UMkmeditUserMaster','CREATE')")
	public ModelAndView editUserMaster(ModelAndView model, HttpServletRequest request,@RequestParam("userId") String userId,@ModelAttribute("addUser") AddUserDto addUser) {
		try {
			
			
			addUser= userService.findUserByUserId(userId);
			addUser.setCheckAction("Edit");
			
			
			model.addObject("addUser", addUser);
			List<RolesDto> roleList = roleService.findAllRole();
			model.addObject("roleList", roleList);
			List<Circle> circleList = roleService.findAllCircle();
	        model.addObject("circleList", circleList);
			model.setViewName("editUser");

		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
			
		} finally {
		}
		return model;
	}
	
	
	
	@RequestMapping(value = "km/editUserMasterLA")
	@PreAuthorize("hasPermission('UMkmeditUserMasterLA','CREATE')")
	public ModelAndView editUserMasterLA(ModelAndView model, HttpServletRequest request,@RequestParam("userId") String userId,@ModelAttribute("addUserDto") AddUserDto addUserDto) {
		try {
			
			
			addUserDto= userService.findUserByUserId(userId);
			addUserDto.setCheckAction("Edit");
			
			
			model.addObject("addUserDto", addUserDto);
			List<RolesDto> roleList = roleService.findAllRole();
			Iterator<RolesDto> itr = roleList.iterator();
            while(itr.hasNext()){
              String role = itr.next().getRole();
          	  if(role.equals("SA") || role.equals("CC") || role.equals("BM") || role.equals("BC")){
          		  itr.remove();
          	  }
            }
			model.addObject("roleList", roleList);
			List<Circle> circleList = roleService.findAllCircle();
	        model.addObject("circleList", circleList);
			model.setViewName("editUserLA");

		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
			
		} finally {
		}
		return model;
	}
	

	/*@RequestMapping(value = "km/saveEditUserMaster")
	public ModelAndView saveEditUserMaster(ModelAndView model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		try {

			String message = null;
			String pattern = "MM-dd-yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(new Date());
			UserDto userBean = new UserDto();
			// userBean.setUserId(Integer.parseInt(request.getParameter("userId")));
			userBean.setUsername(request.getParameter("username"));
			userBean.setRole(request.getParameter("role"));
			userBean.setKioskId(request.getParameter("kioskId"));
			userBean.setFirstName(request.getParameter("firstName"));
			userBean.setLastName(request.getParameter("lastName"));
			userBean.setAddress(request.getParameter("address"));
			userBean.setAddressline1(request.getParameter("addressline1"));
			userBean.setAddressline2(request.getParameter("addressline2"));
			userBean.setGender(request.getParameter("gender"));
			userBean.setCity(request.getParameter("city"));
			userBean.setState(request.getParameter("state"));
			userBean.setMailId(request.getParameter("mailId"));
			userBean.setPhoneNo(request.getParameter("mobileNo"));
			userBean.setCircle(request.getParameter("circle"));
			//userBean.setCreatedDate(date);// request.getParameter("creationDate")
			userBean.setCreatedBy(request.getParameter("createdBy"));
			//userBean.setModifiedDate(date);// request.getParameter("modifiedDate")
			userBean.setModifiedBy(request.getParameter("modifiedBy"));			
			// userBean.setEnabled(request.getParameter("enabled"));

			if ((request.getParameter("checkAction") != null) && !request.getParameter("checkAction").isEmpty()
					&& (request.getParameter("checkAction").equalsIgnoreCase("Edit"))) {
				userBean.setUserId(Integer.parseInt(request.getParameter("userId")));
				userService.updateUserById(userBean);
				String roleVal = request.getParameter("role");
				message = roleVal + " " + " Updated Successfully!";
				redirectAttributes.addFlashAttribute("successMessage", message);
			} else {
				userService.saveUserMaster(userBean);
				String userVal = request.getParameter("role");
				message = userVal + " Created Successfully!";
				redirectAttributes.addFlashAttribute("successMessage", message);
			}
			model.setViewName("redirect:/km/userList");
		} catch (Exception e) {
			
		} finally {
		}
		return model;
	}*/
	
	@RequestMapping(value = "km/deleteUserMaster")
	@PreAuthorize("hasPermission('UMkmdeleteUserMaster','CREATE')")
	public ModelAndView deleteUserMaster(ModelAndView model,@RequestParam("userId") String userId, @ModelAttribute("addUser") AddUserDto addUser,HttpServletRequest request) {
		try {
			addUser= userService.findUserByUserId(userId);
			model.addObject("addUser", addUser);
			List<RolesDto> roleList = roleService.findAllRole();
			model.addObject("roleList", roleList);
			List<Circle> circleList = roleService.findAllCircle();
	        model.addObject("circleList", circleList);
			model.setViewName("deleteUser");
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
			
		} finally {
		}
		return model;
	}

	@RequestMapping(value = { "km/deleteUser" })
	@PreAuthorize("hasPermission('UMkmdeleteUser','EDIT')")
	public ResponseEntity<String>activeAndInActiveUser(ModelAndView model,@RequestParam("userId") String userId,@ModelAttribute("usersBean") UserDto usersBean) {
		ResponseEntity<String> entity=null;
		try {
			Boolean result= userService.deActivateUserById(usersBean);
			if(result){
				entity=ResponseEntity.ok(usersBean.getUsername());	
			}else{
				entity=ResponseEntity.ok("Cancel User");
			}
			//model.setViewName("redirect:/km/userList");
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return entity;
	}

	@RequestMapping(value = { "km/searchUser" })
	public ModelAndView searchUsers(ModelAndView model, HttpServletRequest request, HttpSession session) {

		try {
			UserDto user = (UserDto) session.getAttribute("userObj");
			String userName = request.getParameter("userName");

			if (userName != null && !userName.isEmpty()) {

				List<UserManagementDto> userList = userService.findByUserName(userName);
				model.addObject("usersList", userList);
				model.setViewName("userlist");
			} else {
				List<UserManagementDto> userList = userService.findAllUsers(user);
				model.addObject("usersList", userList);
				model.setViewName("redirect:/km/userList");
				// model.setViewName("UserList");
			}

		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	
	@RequestMapping(value ="km/getUserByUsername")
	@PreAuthorize("hasPermission('UMkmgetUserByUsername','READ')")
	public User getUserByPfId(@RequestParam("username") String pfId){
		return userService.getUserByPfId(pfId);		
	}

}