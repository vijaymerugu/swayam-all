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
	
	@RequestMapping("km/userkioskmapping/usersbyca")
	public List<User> fetchAllUsersByCircleAdmin(HttpSession session) {
		
		UserDto user = (UserDto) session.getAttribute("userObj");
		
		return kioskManagementService.fetchAllUsersByCircleAdmin(user.getUsername(),user.getCircle());
		
		
	}
	
	@RequestMapping("km/userkioskmapping/kiosksbycircle")
	public List<String> fetchAllKiosksByCircleAndNotInUserKioskMapping(HttpSession session) {
		
		UserDto user = (UserDto) session.getAttribute("userObj");
		
		return kioskManagementService.fetchAllKiosksByCircleAndNotInUserKioskMapping(user.getCircle());
		
		
	}
	
	@RequestMapping(value ="km/userkioskmapping/kiosksbycirclepost", method = RequestMethod.POST)
	public ModelAndView saveUserKioskMapping( @RequestParam(value="username") String username, @RequestParam(value="kioskIdList") ArrayList<String> kioskIdList) {
		
		
		//UserDto user = (UserDto) session.getAttribute("userObj");
		kioskManagementService.saveUserKioskMapping(username, kioskIdList);
		
		
		//return kioskManagementService.fetchAllKiosksByCircleAndNotInUserKioskMapping(user.getCircle());
		ModelAndView mav = new ModelAndView("userkioskmapping");
		return mav;
		
	}
	
	@RequestMapping("km/cmscmfmapping/cmsusersbyca")
	public List<User> fetchAllCmsUsersByCircleAdmin(HttpSession session) {
		
		UserDto user = (UserDto) session.getAttribute("userObj");
		
		return kioskManagementService.fetchAllCmsUsersByCircleAdmin(user.getUsername(),user.getCircle());
		
		
	}
	
	@RequestMapping("km/cmscmfmapping/cmfusersbyca")
	public List<User> fetchAllCmfUsersByCircleAndInUserKioskMapping(HttpSession session) {
		
		UserDto user = (UserDto) session.getAttribute("userObj");
		
		return kioskManagementService.fetchAllCmfUsersByCircleAndInUserKioskMapping(user.getCircle());
		
		
	}
	
	@RequestMapping(value ="km/cmscmfmapping/cmscmfmappingpost", method = RequestMethod.POST)
	public ModelAndView saveCmsCmfUserMapping( @RequestParam(value="username") String cmsusername, @RequestParam(value="cmfUserIdIdList") ArrayList<String> cmfUserIdIdList) {
		
		
		//UserDto user = (UserDto) session.getAttribute("userObj");
		kioskManagementService.saveCmsCmfUserMapping(cmsusername, cmfUserIdIdList);
		
		
		//return kioskManagementService.fetchAllKiosksByCircleAndNotInUserKioskMapping(user.getCircle());
		ModelAndView mav = new ModelAndView("cmscmfmapping");
		return mav;
		
		
	}
	
	@RequestMapping(value ="km/userkioskmappingpopup")
	public ModelAndView getKiosksForUser( @RequestParam(value="username") String pfId) {
		
		
		//UserDto user = (UserDto) session.getAttribute("userObj");
		//kioskManagementService.saveCmsCmfUserMapping(cmsusername, cmfUserIdIdList);
		List<UserKioskMappingDeMapperDto> kiosksList = kioskManagementService.getKiosksForUser(pfId);
		
		//return kioskManagementService.fetchAllKiosksByCircleAndNotInUserKioskMapping(user.getCircle());
		ModelAndView model = new ModelAndView("kioskAssignedLA");
		model.addObject("kiosksList", kiosksList);
		return model;
		
		
	}
	
	@RequestMapping(value ="km/userkioskmappingpopupselected")
	public ModelAndView deMapKiosksForUser( @RequestParam(value="array") String[] kiosksArray, @RequestParam(value="uname") String username) {
		
		
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
	
	@RequestMapping(value = "kiosks/get", params = { "page", "size" ,"type"}, method = RequestMethod.GET, produces = "application/json")
	public Page<KioskBranchMasterUserDto> findPaginated( 
		      @RequestParam("page") int page, @RequestParam("size") int size,@RequestParam("type") String type) {
		 
		

			 Page<KioskBranchMasterUserDto> resultPage;
			if(type.equals("InstalledKiosks")){
			    resultPage= kioskManagementService.findPaginated(page, size);
			}else if(type.equals("CMS")){
				resultPage= kioskManagementService.findPaginatedCount(page, size, type);
			}else if(type.equals("LIPI")){
				resultPage= kioskManagementService.findPaginatedCount(page, size, type);
			}else if(type.equalsIgnoreCase("InstalledCMSVendor")){
				resultPage= kioskManagementService.findPaginatedCount(page, size, type);
			}else if(type.equals("DeleviredCMSVendor")){
				resultPage= kioskManagementService.findPaginatedCount(page, size, type);
			}else if(type.equals("InstalledLIPIVendor")){
				resultPage= kioskManagementService.findPaginatedCount(page, size, type);
			}else if(type.equals("DeleviredLIPIVendor")){
			   resultPage= kioskManagementService.findPaginatedCount(page, size, type);
		    }else{
		         resultPage = kioskManagementService.findPaginated(page, size);
		        if (page > resultPage.getTotalPages()) {
		            //throw new MyResourceNotFoundException();
		        }
		        return resultPage;
		    }
		        return resultPage;
		    }
	
	
	@RequestMapping(value = "kiosksByCircle/get", params = { "page", "size" ,"type"}, method = RequestMethod.GET, produces = "application/json")
	public Page<KioskBranchMasterUserDto> findPaginatedByCircle( 
		      @RequestParam("page") int page, @RequestParam("size") int size,@RequestParam("type") String type) {
		 
		

			 Page<KioskBranchMasterUserDto> resultPage;
			if(type.equals("InstalledKiosks")){
			    resultPage= kioskManagementService.findPaginatedByCircle(page, size);
			}else if(type.equals("CMS")){
				resultPage= kioskManagementService.findPaginatedCountByCircle(page, size, type);
			}else if(type.equals("LIPI")){
				resultPage= kioskManagementService.findPaginatedCountByCircle(page, size, type);
			}else if(type.equalsIgnoreCase("InstalledCMSVendor")){
				resultPage= kioskManagementService.findPaginatedCountByCircle(page, size, type);
			}else if(type.equals("DeleviredCMSVendor")){
				resultPage= kioskManagementService.findPaginatedCountByCircle(page, size, type);
			}else if(type.equals("InstalledLIPIVendor")){
				resultPage= kioskManagementService.findPaginatedCountByCircle(page, size, type);
			}else if(type.equals("DeleviredLIPIVendor")){
			   resultPage= kioskManagementService.findPaginatedCountByCircle(page, size, type);
		    }else{
		         resultPage = kioskManagementService.findPaginatedByCircle(page, size);
		        if (page > resultPage.getTotalPages()) {
		            //throw new MyResourceNotFoundException();
		        }
		        return resultPage;
		    }
		        return resultPage;
		    }

	
	
	
	@RequestMapping(value ="km/assignCmfForKiosk")
	public ModelAndView getAssignCmfForKiosk( @RequestParam(value="kioskId") String kioskId,ModelAndView model,HttpSession session) {	
		UserDto user = (UserDto) session.getAttribute("userObj");
		
		KioskBranchMasterUserDto kioskDto = kioskManagementService.getKiosksFromKioskBranchMasterByKioskId(kioskId);
		List<User> usersList = userService.fetchAllCmfUserByCircle(user.getCircle());
		//return kioskManagementService.fetchAllKiosksByCircleAndNotInUserKioskMapping(user.getCircle());
		model.setViewName("assignCmfForKiosk");
		model.addObject("kioskDto", kioskDto);
		model.addObject("usersList", usersList);
		
		return model;
		
		
	}
	
	@RequestMapping(value ="km/saveSingleCmfKioskMapping")
	public ModelAndView saveSingleUserKioskMapping( @RequestParam(value="username") String pfId, @RequestParam(value="kioskId") String kioskId) {
				
		kioskManagementService.saveSingleUserKioskMapping(pfId, kioskId);		
		
		ModelAndView mav = new ModelAndView("successCmfForKiosk");
		return mav;
		
	}
	
}
