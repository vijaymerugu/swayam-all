package sbi.kiosk.swayam.kioskmanagement.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import oracle.net.aso.b;
import sbi.kiosk.swayam.common.dto.AddUserDto;
import sbi.kiosk.swayam.common.dto.RolesDto;
import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.dto.UserManagementDto;
import sbi.kiosk.swayam.common.entity.User;
import sbi.kiosk.swayam.kioskmanagement.service.RoleService;
import sbi.kiosk.swayam.kioskmanagement.service.UserService;

@RestController
public class UserManagementController {

	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;

	@RequestMapping(value = { "/km/userList" })
	public ModelAndView userList(ModelAndView model, HttpSession session) {

		try {
			UserDto user = (UserDto) session.getAttribute("userObj");
			System.out.println("userList");
			List<UserManagementDto> userList = userService.findAllUsers(user);
			model.addObject("usersList", userList);
			if (user.getRole().equals("CC")) {
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
				model.setViewName("userlist");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	
	@RequestMapping(value = "/users/get", params = { "page", "size" ,"type"}, method = RequestMethod.GET, produces = "application/json")
	public Page<UserManagementDto> findPaginated(
		      @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("type") String type) {
		 
		System.out.println("type=========Yogesh========="+type);
		 Page<UserManagementDto> resultPage = null;
		if(type.equals("CMF")){
			resultPage= userService.findPaginatedCount(page, size, type);
		}else if(type.equals("CMS")){
			resultPage= userService.findPaginatedCount(page, size, type);
		}else if(type.equalsIgnoreCase("MUMBAI")){
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
	
	

	

	
	@RequestMapping(value = { "/km/addUser" })
	public ModelAndView addUser(ModelAndView model, @ModelAttribute("addUser") AddUserDto addUser,HttpServletRequest request) {

		try {
			      System.out.println("inside addUser jsp call.."+request.getParameter("userName"));
	              List<RolesDto> roleList = roleService.findAllRole();
		           model.addObject("roleList", roleList);
		           model.setViewName("addUser");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	
	@RequestMapping(value = { "/km/addUserLA" })
	public ModelAndView addUserLa(ModelAndView model, @ModelAttribute("addUserDto") AddUserDto addUserDto) {

		try {
			      System.out.println("inside addUserLA jsp call..");
	              List<RolesDto> roleList = roleService.findAllRole();
		           model.addObject("roleList", roleList);
		           model.setViewName("addUserLA");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	
	
	@RequestMapping(value = "/km/editUserMaster")
	public ModelAndView editUserMaster(ModelAndView model, HttpServletRequest request,@RequestParam("userId") String userId,@ModelAttribute("addUser") AddUserDto addUser) {
		System.out.println("editUserMaster(-,-) :: START "+userId);
		try {
			
			System.out.println("createdBy=======1==========="+request.getParameter("createdBy"));
			
			addUser= userService.findUserByUserId(userId);
			addUser.setCheckAction("Edit");
			System.out.println("get == userId====="+addUser.getUserId());
			
			
			model.addObject("addUser", addUser);
			List<RolesDto> roleList = roleService.findAllRole();
			model.addObject("roleList", roleList);
			model.setViewName("addUser");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An error occured while editUserMaster :Exception " + e.getMessage());
		} finally {
			System.out.println("editUserMaster(-,-) :: END");
		}
		return model;
	}
	
	
	
	@RequestMapping(value = "/km/editUserMasterLA")
	public ModelAndView editUserMasterLA(ModelAndView model, HttpServletRequest request,@RequestParam("userId") String userId,@ModelAttribute("addUserDto") AddUserDto addUserDto) {
		System.out.println("editUserMasterLA(-,-) :: START "+userId);
		try {
			
			System.out.println("createdBy=======2==========="+request.getParameter("createdBy"));
			
			addUserDto= userService.findUserByUserId(userId);
			addUserDto.setCheckAction("Edit");
			System.out.println("get == userId====="+addUserDto.getUserId());
			
			
			model.addObject("addUserDto", addUserDto);
			List<RolesDto> roleList = roleService.findAllRole();
			model.addObject("roleList", roleList);
			model.setViewName("addUserLA");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An error occured while editUserMasterLA :Exception " + e.getMessage());
		} finally {
			System.out.println("editUserMasterLA(-,-) :: END");
		}
		return model;
	}
	

	/*@RequestMapping(value = "/km/saveEditUserMaster")
	public ModelAndView saveEditUserMaster(ModelAndView model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		System.out.println("saveEditUserMaster(-,-) :: START");

		try {

			String message = null;
			String pattern = "MM-dd-yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(new Date());
			System.out.println(date);
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
			// System.out.println("Enable==="+request.getParameter("enabled"));
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
			e.printStackTrace();
		} finally {
			System.out.println("saveUserr(-,-) :: END");
		}
		return model;
	}*/
	
	@RequestMapping(value = "/km/deleteUserMaster")
	public ModelAndView deleteUserMaster(ModelAndView model,@RequestParam("userId") String userId, @ModelAttribute("addUser") AddUserDto addUser,HttpServletRequest request) {
		System.out.println("deleteUserMaster(-,-) :: START");
		try {
			addUser= userService.findUserByUserId(userId);
			System.out.println("get == userId====="+addUser.getUserId());
			model.addObject("addUser", addUser);
			List<RolesDto> roleList = roleService.findAllRole();
			model.addObject("roleList", roleList);
			model.setViewName("deleteUser");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An error occured while editUserMaster :Exception " + e.getMessage());
		} finally {
			System.out.println("deleteUserMaster(-,-) :: END");
		}
		return model;
	}

	@RequestMapping(value = { "/km/deleteUser" })
	public ResponseEntity<String>activeAndInActiveUser(ModelAndView model,@RequestParam("userId") String userId,@ModelAttribute("usersBean") UserDto usersBean) {
		ResponseEntity<String> entity=null;
		try {
			System.out.println("activeAndInActiveUser() --Start"+userId);
			Boolean result= userService.deActivateUserById(usersBean);
			if(result){
				entity=ResponseEntity.ok(userId);	
			}else{
				entity=ResponseEntity.ok("Cancel User");
			}
			//model.setViewName("redirect:/km/userList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

	@RequestMapping(value = { "/km/searchUser" })
	public ModelAndView searchUsers(ModelAndView model, HttpServletRequest request, HttpSession session) {

		try {
			UserDto user = (UserDto) session.getAttribute("userObj");
			System.out.println("searchUsers" + request.getParameter("userName"));
			String userName = request.getParameter("userName");

			if (userName != null && !userName.isEmpty()) {

				List<UserManagementDto> userList = userService.findByUserName(userName);
				System.out.println("userList::::" + userList);
				model.addObject("usersList", userList);
				model.setViewName("userlist");
			} else {
				List<UserManagementDto> userList = userService.findAllUsers(user);
				model.addObject("usersList", userList);
				model.setViewName("redirect:/km/userList");
				// model.setViewName("UserList");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value ="/km/getUserByUsername")
	public User getUserByPfId(@RequestParam("username") String pfId){
		return userService.getUserByPfId(pfId);		
	}

}