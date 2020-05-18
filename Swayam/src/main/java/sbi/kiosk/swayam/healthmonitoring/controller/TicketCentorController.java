package sbi.kiosk.swayam.healthmonitoring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import sbi.kiosk.swayam.common.dto.TicketCentorDto;
import sbi.kiosk.swayam.healthmonitoring.service.TicketCentorFilterService;
import sbi.kiosk.swayam.healthmonitoring.service.TicketCentorService;

@RestController
public class TicketCentorController {
	

	@Autowired
	TicketCentorService ticketCentorService;
	@Autowired
	TicketCentorFilterService ticketCentorFilterService;
	
	@RequestMapping(value = "/hm/ticketCentor/get", params = { "page", "size","type"}, method = RequestMethod.GET, produces = "application/json")
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
	
	@RequestMapping(value = "/hm/ticketCentorByCircle/get", params = { "page", "size","type"}, method = RequestMethod.GET, produces = "application/json")
	public Page<TicketCentorDto> findPaginatedByCircle( @RequestParam("type") String type,
		      @RequestParam("page") int page, @RequestParam("size") int size) {
		 
		System.out.println("type==findPaginated===========ticketCentorService====="+type);
		 Page<TicketCentorDto> resultPage = null;
		 
	     
		    
			if(type.equals("High")){
				resultPage= ticketCentorService.findPaginatedCountByCircle(page, size, type);
			}else if(type.equals("Medium")){
				resultPage= ticketCentorService.findPaginatedCountByCircle(page, size, type);
			}else if(type.equalsIgnoreCase("Low")){
				resultPage= ticketCentorService.findPaginatedCountByCircle(page, size, type);
			}else if(type.equals("Total")){
				resultPage= ticketCentorService.findPaginatedCountByCircle(page, size, type);
				System.out.println("Total Size:::: "+resultPage.getContent().size());
			}else if(type.equals("TwoToFourHrsCount")){
				resultPage= ticketCentorService.findPaginatedCountByCircle(page, size, type);
			}else if(type.equals("OneDaysCount")){
			   resultPage= ticketCentorService.findPaginatedCountByCircle(page, size, type);
			}else if(type.equals("ThreeDaysLessCount")){
				   resultPage= ticketCentorService.findPaginatedCountByCircle(page, size, type);
		    }else if(type.equals("ThreeDayGreaterCount")){
		    	resultPage= ticketCentorService.findPaginatedCountByCircle(page, size, type);
		         
		    }else{
		    	//resultPage= ticketCentorService.findPaginatedCount(page, size, type);
		    
			
			 resultPage = ticketCentorService.findPaginatedByCircle(page, size);
		      System.out.println("resultPage=="+resultPage.getContent());
			    if (resultPage !=null && resultPage.getSize()>0){
			    	//return resultPage;
			        }
		    }
		        return resultPage;
		    }



	
	@RequestMapping(value = "/hm/ticketCentorFilter/get", params = { "page", "size","type"}, method = RequestMethod.GET, produces = "application/json")
	public Page<TicketCentorDto> findPaginatedByCategory( @RequestParam("type") String type,
		      @RequestParam("page") int page, @RequestParam("size") int size) {
		 
		System.out.println("type==findPaginated===========findPaginatedByCategory=====+++"+type);
		if(type !=null && type.trim().equals("undefined")){
			type = "";
		}
		
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
		         
		    }else if(type !=null && type !=""){
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


	
	
	
	@RequestMapping(value = "/hm/categoryCall/{category}")
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
	
	
	@RequestMapping(value = "/hm/subCategoryCall/{category}/{subCategory}")
	public ResponseEntity<List<TicketCentorDto>> subCategoryCall(ModelAndView model,@PathVariable("category") String category, @PathVariable("subCategory") String subCategory,
			HttpServletRequest request) {
		
		List<TicketCentorDto> ticketCategoryAndSubCatList = ticketCentorFilterService.findByCategoryAndSubCategory(category,subCategory);
		ResponseEntity<List<TicketCentorDto>> respEntity = ResponseEntity.ok(ticketCategoryAndSubCatList);
		model.addObject("ticketCategoryAndSubCatList", ticketCategoryAndSubCatList);
		model.setViewName("ticketCentorSA");
		return respEntity;
	}

	
	
	
	
	

}
