package sbi.kiosk.swayam.kioskmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.common.dto.KioskBranchMasterUserDto;
import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.dto.UserKioskMappingDeMapperDto;
import sbi.kiosk.swayam.common.entity.User;
import sbi.kiosk.swayam.kioskmanagement.service.KioskManagementService;
import sbi.kiosk.swayam.kioskmanagement.service.UserService;

@RestController
public class KioskManagementController {	
	
	@Autowired
	KioskManagementService kioskManagementService;
	
	@Autowired
	UserService userService;
	
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
	
	@RequestMapping(value ="/km/userkioskmappingpopup")
	public ModelAndView getKiosksForUser( @RequestParam(value="username") String username) {
		
		System.out.println("444444444444444");
		//UserDto user = (UserDto) session.getAttribute("userObj");
		//kioskManagementService.saveCmsCmfUserMapping(cmsusername, cmfUserIdIdList);
		List<UserKioskMappingDeMapperDto> kiosksList = kioskManagementService.getKiosksForUser(username);
		
		//return kioskManagementService.fetchAllKiosksByCircleAndNotInUserKioskMapping(user.getCircle());
		ModelAndView model = new ModelAndView("kioskAssignedLA");
		model.addObject("kiosksList", kiosksList);
		return model;
		
		
	}
	
	@RequestMapping(value ="/km/userkioskmappingpopupselected")
	public ModelAndView deMapKiosksForUser( @RequestParam(value="array") String[] kiosksArray, @RequestParam(value="uname") String username) {
		
		System.out.println("444444444444444");
		//UserDto user = (UserDto) session.getAttribute("userObj");
		//kioskManagementService.saveCmsCmfUserMapping(cmsusername, cmfUserIdIdList);
		List<UserKioskMappingDeMapperDto> dto = new ArrayList<UserKioskMappingDeMapperDto>();
		for(String arr:kiosksArray){
			UserKioskMappingDeMapperDto dt = new UserKioskMappingDeMapperDto();
			dt.setKioskId(arr);
			dt.setUsername(username);
			dto.add(dt);
		}
		
		List<UserKioskMappingDeMapperDto> kiosksList = kioskManagementService.deleteUserKioskMapping(dto);
		
		//return kioskManagementService.fetchAllKiosksByCircleAndNotInUserKioskMapping(user.getCircle());
		//List<UserKioskMappingDeMapperDto> kiosksList = new ArrayList<UserKioskMappingDeMapperDto>();
		/*for(String arr:kiosksArray){
			UserKioskMappingDeMapperDto dto = new UserKioskMappingDeMapperDto();
			String array[] = arr.split(",");
			dto.setKioskId(array[0]);
			dto.setUsername(array[1]);
			dto.setVendor(array[2]);
			dto.setInstallationStatus(array[3]);
			kiosksList.add(dto);
		}
		*/
		//List<UserKioskMappingDeMapperDto> kiosksList = Arrays.asList(kiosksArray);
		ModelAndView model = new ModelAndView("kioskAssignedLAConfirm");
		model.addObject("kiosksList", kiosksList);
		return model;
		
		
	}
	
	@RequestMapping(value = "/kiosks/get", params = { "page", "size" }, method = RequestMethod.GET, produces = "application/json")
	public Page<KioskBranchMasterUserDto> findPaginated(
		      @RequestParam("page") int page, @RequestParam("size") int size) {
		 
		        Page<KioskBranchMasterUserDto> resultPage = kioskManagementService.findPaginated(page, size);
		        if (page > resultPage.getTotalPages()) {
		            //throw new MyResourceNotFoundException();
		        }
		 
		        return resultPage;
		    }
	
	
	@RequestMapping(value ="/km/assignCmfForKiosk")
	public ModelAndView getAssignCmfForKiosk( @RequestParam(value="kioskId") String kioskId,ModelAndView model) {	
		
		
		KioskBranchMasterUserDto kioskDto = kioskManagementService.getKiosksFromKioskBranchMasterByKioskId(kioskId);
		List<User> usersList = userService.fetchAllCmfUserByCircle(kioskDto.getCircle());
		//return kioskManagementService.fetchAllKiosksByCircleAndNotInUserKioskMapping(user.getCircle());
		model.setViewName("assignCmfForKiosk");
		model.addObject("kioskDto", kioskDto);
		model.addObject("usersList", usersList);
		
		return model;
		
		
	}
	
	@RequestMapping(value ="/km/saveSingleCmfKioskMapping")
	public ModelAndView saveSingleUserKioskMapping( @RequestParam(value="username") String username, @RequestParam(value="kioskId") String kioskId) {
				
		kioskManagementService.saveSingleUserKioskMapping(username, kioskId);		
		
		ModelAndView mav = new ModelAndView("successCmfForKiosk");
		return mav;
		
	}
}
