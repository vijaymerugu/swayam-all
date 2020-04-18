package sbi.kiosk.swayam.kioskmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.common.dto.CallTypeDto;
import sbi.kiosk.swayam.common.dto.KioskBranchMasterUserDto;
import sbi.kiosk.swayam.common.dto.TicketCentorDto;
import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.dto.UserKioskMappingDeMapperDto;
import sbi.kiosk.swayam.common.entity.User;
import sbi.kiosk.swayam.healthmonitoring.service.TicketCentorFilterService;
import sbi.kiosk.swayam.healthmonitoring.service.TicketCentorService;
import sbi.kiosk.swayam.kioskmanagement.service.KioskManagementService;
import sbi.kiosk.swayam.kioskmanagement.service.UserService;

@RestController
public class KioskManagementController {	
	
	@Autowired
	KioskManagementService kioskManagementService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TicketCentorService ticketCentorService;
	@Autowired
	TicketCentorFilterService ticketCentorFilterService;
	
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
	public ModelAndView getKiosksForUser( @RequestParam(value="username") String pfId) {
		
		System.out.println("444444444444444");
		//UserDto user = (UserDto) session.getAttribute("userObj");
		//kioskManagementService.saveCmsCmfUserMapping(cmsusername, cmfUserIdIdList);
		List<UserKioskMappingDeMapperDto> kiosksList = kioskManagementService.getKiosksForUser(pfId);
		
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
	
	@RequestMapping(value = "/kiosks/get", params = { "page", "size" ,"type"}, method = RequestMethod.GET, produces = "application/json")
	public Page<KioskBranchMasterUserDto> findPaginated( 
		      @RequestParam("page") int page, @RequestParam("size") int size,@RequestParam("type") String type) {
		 
		
		System.out.println("type===11==============="+type);

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
	public ModelAndView saveSingleUserKioskMapping( @RequestParam(value="username") String pfId, @RequestParam(value="kioskId") String kioskId) {
				
		kioskManagementService.saveSingleUserKioskMapping(pfId, kioskId);		
		
		ModelAndView mav = new ModelAndView("successCmfForKiosk");
		return mav;
		
	}
	
	
	
	
	@RequestMapping(value = "/ticketCentor/get", params = { "page", "size","type"}, method = RequestMethod.GET, produces = "application/json")
	public Page<TicketCentorDto> findPaginated( @RequestParam("type") String type,
		      @RequestParam("page") int page, @RequestParam("size") int size) {
		 
		System.out.println("type==findPaginated===========ticketCentorService====="+type);
		 Page<TicketCentorDto> resultPage = null;
		 
	     
		    
			if(type.equals("High")){
				resultPage= ticketCentorService.findPaginatedCount(page, size, type);
			}else if(type.equals("Medium")){
				resultPage= ticketCentorService.findPaginatedCount(page, size, type);
			}else if(type.equalsIgnoreCase("Low")){
				resultPage= ticketCentorService.findPaginatedCount(page, size, type);
			}else if(type.equals("Total")){
				resultPage= ticketCentorService.findPaginatedCount(page, size, type);
				System.out.println("Total Size:::: "+resultPage.getContent().size());
			}else if(type.equals("TwoToFourHrsCount")){
				resultPage= ticketCentorService.findPaginatedCount(page, size, type);
			}else if(type.equals("OneDaysCount")){
			   resultPage= ticketCentorService.findPaginatedCount(page, size, type);
			}else if(type.equals("ThreeDaysLessCount")){
				   resultPage= ticketCentorService.findPaginatedCount(page, size, type);
		    }else if(type.equals("ThreeDayGreaterCount")){
		    	resultPage= ticketCentorService.findPaginatedCount(page, size, type);
		         
		    }else{
		    	//resultPage= ticketCentorService.findPaginatedCount(page, size, type);
		    
			
			 resultPage = ticketCentorService.findPaginated(page, size);
		      System.out.println("resultPage=="+resultPage.getContent());
			    if (resultPage !=null && resultPage.getSize()>0){
			    	//return resultPage;
			        }
		    }
		        return resultPage;
		    }

	
	@RequestMapping(value = "/ticketCentorFilter/get", params = { "page", "size","type"}, method = RequestMethod.GET, produces = "application/json")
	public Page<TicketCentorDto> findPaginatedByCategory( @RequestParam("type") String type,
		      @RequestParam("page") int page, @RequestParam("size") int size) {
		 
		System.out.println("type==findPaginated===========findPaginatedByCategory====="+type);
		 Page<TicketCentorDto> resultPage = null;
		    
			if(type.equals("High")){
				resultPage= ticketCentorFilterService.findPaginatedCount(page, size, type);
			}else if(type.equals("Medium")){
				resultPage= ticketCentorFilterService.findPaginatedCount(page, size, type);
			}else if(type.equalsIgnoreCase("Low")){
				resultPage= ticketCentorFilterService.findPaginatedCount(page, size, type);
			}else if(type.equals("Total")){
				resultPage= ticketCentorFilterService.findPaginatedCount(page, size, type);
				System.out.println("Total Size:::: "+resultPage.getContent().size());
			}else if(type.equals("TwoToFourHrsCount")){
				resultPage= ticketCentorFilterService.findPaginatedCount(page, size, type);
			}else if(type.equals("OneDaysCount")){
			   resultPage= ticketCentorFilterService.findPaginatedCount(page, size, type);
			}else if(type.equals("ThreeDaysLessCount")){
				   resultPage= ticketCentorFilterService.findPaginatedCount(page, size, type);
		    }else if(type.equals("ThreeDayGreaterCount")){
		    	resultPage= ticketCentorFilterService.findPaginatedCount(page, size, type);
		         
		    }else{
		    	//resultPage= ticketCentorFilterService.findPaginatedCount(page, size, type);
			 resultPage = ticketCentorFilterService.findPaginated(page, size);
		      System.out.println("resultPage=="+resultPage.getContent());
			    if (resultPage !=null && resultPage.getSize()>0){
			    	//return resultPage;
			        }
		    }
		        return resultPage;
		    }


	
	
	
	@RequestMapping(value = "/km/categoryCall/{category}")
	public ResponseEntity<List<CallTypeDto>> callLogCategory(ModelAndView model,
			@PathVariable("category") String category, HttpServletRequest request) {
		
		System.out.println("category===12345=="+category);
		
		       List<CallTypeDto> subCategoryList = ticketCentorFilterService.findSubCategoryByCategory(category.trim());
		       System.out.println("callLogCategory==entiry.getBody():::category:::::::"+subCategoryList);
		       model.addObject("subCategoryList", subCategoryList);
		       ResponseEntity<List<CallTypeDto>> entiry = ResponseEntity.ok(subCategoryList);
		       System.out.println("entiry.getBody():::category:::::::");
		       model.setViewName("ticketCentorSA");
		return entiry;
	}
	
	
	@RequestMapping(value = "/km/subCategoryCall/{category}/{subCategory}")
	public ResponseEntity<List<TicketCentorDto>> subCategoryCall(ModelAndView model,@PathVariable("category") String category, @PathVariable("subCategory") String subCategory,
			HttpServletRequest request) {
		
		List<TicketCentorDto> ticketCategoryAndSubCatList = ticketCentorFilterService.findByCategoryAndSubCategory(category,subCategory);
		ResponseEntity<List<TicketCentorDto>> respEntity = ResponseEntity.ok(ticketCategoryAndSubCatList);
		model.addObject("ticketCategoryAndSubCatList", ticketCategoryAndSubCatList);
		model.setViewName("ticketCentorSA");
		return respEntity;
	}
}
