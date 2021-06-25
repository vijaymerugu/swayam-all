package sbi.kiosk.swayam.healthmonitoring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	Logger logger = LoggerFactory.getLogger(TicketCentorController.class);
	

	@Autowired
	TicketCentorService ticketCentorService;
	@Autowired
	TicketCentorFilterService ticketCentorFilterService;
	
	@RequestMapping(value = "hm/ticketCentor/get", params = { "page", "size","type"}, method = RequestMethod.GET, produces = "application/json")
	public Page<TicketCentorDto> findPaginated( @RequestParam("type") String type,
		      @RequestParam("page") int page, @RequestParam("size") int size) {
		 
		logger.info("type==findPaginated===========ticketCentorServiceCC====="+type);
		 Page<TicketCentorDto> resultPage = null;
		 
	     
		    
			if(type.equals("High")){
				resultPage= ticketCentorService.findPaginatedCount(page, size, type);
			}else if(type.equals("Medium")){
				resultPage= ticketCentorService.findPaginatedCount(page, size, type);
			}else if(type.equalsIgnoreCase("Low")){
				resultPage= ticketCentorService.findPaginatedCount(page, size, type);
			}else if(type.equals("Total")){
				resultPage= ticketCentorService.findPaginatedCount(page, size, type);
				
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
		    	logger.info("type==findPaginated===========ticketCentorServiceCC=====CC ELSE");
			
			 resultPage = ticketCentorService.findPaginated(page, size);
		      
			    if (resultPage !=null && resultPage.getSize()>0){
			    	//return resultPage;
			        }
		    }
		        return resultPage;
		    }
	
	@RequestMapping(value = "hm/ticketCentorByCircle/get", params = { "page", "size","type"}, method = RequestMethod.GET, produces = "application/json")
	public Page<TicketCentorDto> findPaginatedByCircle( @RequestParam("type") String type,
		      @RequestParam("page") int page, @RequestParam("size") int size) {
		 
		
		 Page<TicketCentorDto> resultPage = null;
		 
	     
		 if(type!=null && !type.isEmpty() && !type.equals("undefined")) {
		    
			if(type.equals("High")){
				resultPage= ticketCentorService.findPaginatedCountByCircle(page, size, type);
			}else if(type.equals("Medium")){
				resultPage= ticketCentorService.findPaginatedCountByCircle(page, size, type);
			}else if(type.equalsIgnoreCase("Low")){
				resultPage= ticketCentorService.findPaginatedCountByCircle(page, size, type);
			}else if(type.equals("Total")){
				resultPage= ticketCentorService.findPaginatedCountByCircle(page, size, type);
				
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
		      
			    if (resultPage !=null && resultPage.getSize()>0){
			    	//return resultPage;
			        }
		    }
		 }else {
			 resultPage = ticketCentorService.findPaginatedByCircle(page, size);
		    }
			
		        return resultPage;
		    }



	
	@RequestMapping(value = "hm/ticketCentorFilter/get", params = { "page", "size","type"}, method = RequestMethod.GET, produces = "application/json")
	public Page<TicketCentorDto> findPaginatedByCategory( @RequestParam("type") String type,
		      @RequestParam("page") int page, @RequestParam("size") int size) {
		logger.info("findPaginatedByCategory SA----page::::: "+page); 
		logger.info("findPaginatedByCategory SA----size::::: "+size); 
		logger.info("findPaginatedByCategory SA----type::::: "+type); 
		
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
				
			}else if(type.equals("TwoToFourHrsCount")){
				resultPage= ticketCentorFilterService.findPaginatedCount(page, size, type);
			}else if(type.equals("OneDaysCount")){
			   resultPage= ticketCentorFilterService.findPaginatedCount(page, size, type);
			}else if(type.equals("ThreeDaysLessCount")){
				   resultPage= ticketCentorFilterService.findPaginatedCount(page, size, type);
		    }else if(type.equals("ThreeDayGreaterCount")){
		    	resultPage= ticketCentorFilterService.findPaginatedCount(page, size, type);
		         
		   }else if(type.equals("TotalCount")){
		    	resultPage= ticketCentorFilterService.findPaginatedCount(page, size, type);
		         
		    }else if(type !=null && type !=""){
		    	resultPage= ticketCentorFilterService.findPaginatedCount(page, size, type);
		         
		    }else{
		    	//resultPage= ticketCentorFilterService.findPaginatedCount(page, size, type);
			
		    	logger.info("findPaginatedByCategory SA----findPaginated:::::type=");
		    	resultPage = ticketCentorFilterService.findPaginated(page, size);
		      
			    if (resultPage !=null && resultPage.getSize()>0){
			    	//return resultPage;
			        }
		    }
		        return resultPage;
		    }


	@RequestMapping(value = "hm/ticketCentorFilterCMF/get", params = { "page", "size","type"}, method = RequestMethod.GET, produces = "application/json")
	public Page<TicketCentorDto> findPaginatedByCategoryCMF( @RequestParam("type") String type,
		      @RequestParam("page") int page, @RequestParam("size") int size) {
		 
		logger.info("type==findPaginated===========findPaginatedByCategoryCMF=====+++TYPE="+type);
		
		if(type !=null && type.trim().equals("undefined")){
			type = "";
		}
		if(type !=null && type.trim().equals(">")){
			type = "";
		}
		
		 Page<TicketCentorDto> resultPage = null;
		    
			if(type.equals("High")){
				logger.info("=========findPaginatedByCategoryCMF=====+++TYPE="+type);
				resultPage= ticketCentorFilterService.findPaginatedCountCmf(page, size, type);
			}else if(type.equals("Medium")){
				logger.info("=========findPaginatedByCategoryCMF=====+++TYPE="+type);
				resultPage= ticketCentorFilterService.findPaginatedCountCmf(page, size, type);
			}else if(type.equalsIgnoreCase("Low")){
				logger.info("=========findPaginatedByCategoryCMF=====+++TYPE="+type);
				resultPage= ticketCentorFilterService.findPaginatedCountCmf(page, size, type);
			}else if(type.equals("Total")){
				logger.info("=========findPaginatedByCategoryCMF=====+++TYPE="+type);
				resultPage= ticketCentorFilterService.findPaginatedCountCmf(page, size, type);
				
			}else if(type.equals("TwoToFourHrsCount")){
				logger.info("=========findPaginatedByCategoryCMF=====+++TYPE="+type);
				resultPage= ticketCentorFilterService.findPaginatedCountCmf(page, size, type);
			}else if(type.equals("OneDaysCount")){
				logger.info("=========findPaginatedByCategoryCMF=====+++TYPE="+type);
			   resultPage= ticketCentorFilterService.findPaginatedCountCmf(page, size, type);
			}else if(type.equals("ThreeDaysLessCount")){
				logger.info("=========findPaginatedByCategoryCMF=====+++TYPE="+type);
				   resultPage= ticketCentorFilterService.findPaginatedCountCmf(page, size, type);
		    }else if(type.equals("ThreeDayGreaterCount")){
		    	logger.info("=========findPaginatedByCategoryCMF=====+++TYPE="+type);
		    	resultPage= ticketCentorFilterService.findPaginatedCountCmf(page, size, type);
		         
		    }else if(type !=null && type !="" && type !=">"){
		    	logger.info("=========findPaginatedByCategoryCMF=====+++ONLY=TYPE="+type);
		    	resultPage= ticketCentorFilterService.findPaginatedCountCmf(page, size, type);
		         
		    }else{
		    	//resultPage= ticketCentorFilterService.findPaginatedCount(page, size, type);
		    logger.info("=========findPaginatedCmf=====+++");
			 resultPage = ticketCentorFilterService.findPaginatedCmf(page, size);
			 logger.info("resultPage=="+resultPage.getContent());
			    if (resultPage !=null && resultPage.getSize()>0){
			    	//return resultPage;
			        }
		    }
		        return resultPage;
		    }

	@RequestMapping(value = "hm/ticketCentorFilterCMS/get", params = { "page", "size","type"}, method = RequestMethod.GET, produces = "application/json")
	public Page<TicketCentorDto> findPaginatedByCategoryCMS( @RequestParam("type") String type,
		      @RequestParam("page") int page, @RequestParam("size") int size) {
		 
		
		if(type !=null && type.trim().equals("undefined")){
			type = "";
		}
		if(type !=null && (type.equals(">") || type ==">")){
			type = "";
		}
		
		 Page<TicketCentorDto> resultPage = null;
		    
			if(type.equals("High")){
				resultPage= ticketCentorFilterService.findPaginatedCountCms(page, size, type);
			}else if(type.equals("Medium")){
				resultPage= ticketCentorFilterService.findPaginatedCountCms(page, size, type);
			}else if(type.equalsIgnoreCase("Low")){
				resultPage= ticketCentorFilterService.findPaginatedCountCms(page, size, type);
			}else if(type.equals("Total")){
				resultPage= ticketCentorFilterService.findPaginatedCountCms(page, size, type);
				
			}else if(type.equals("TwoToFourHrsCount")){
				resultPage= ticketCentorFilterService.findPaginatedCountCms(page, size, type);
			}else if(type.equals("OneDaysCount")){
			   resultPage= ticketCentorFilterService.findPaginatedCountCms(page, size, type);
			}else if(type.equals("ThreeDaysLessCount")){
				   resultPage= ticketCentorFilterService.findPaginatedCountCms(page, size, type);
		    }else if(type.equals("ThreeDayGreaterCount")){
		    	resultPage= ticketCentorFilterService.findPaginatedCountCms(page, size, type);
		         
		    }else if(type !=null && type !="" && type !=">"){
		    	resultPage= ticketCentorFilterService.findPaginatedCountCms(page, size, type);
		         
		    }else{
		    	//resultPage= ticketCentorFilterService.findPaginatedCount(page, size, type);
			 resultPage = ticketCentorFilterService.findPaginatedCms(page, size);
		      
			    if (resultPage !=null && resultPage.getSize()>0){
			    	//return resultPage;
			        }
		    }
		        return resultPage;
		    }	
	
	@RequestMapping(value = "hm/categoryCall/{category}")
	public ResponseEntity<List<CallTypeDto>> callLogCategory(ModelAndView model,
			@PathVariable("category") String category, HttpServletRequest request) {
		
		
		
		       List<CallTypeDto> subCategoryList = ticketCentorFilterService.findSubCategoryByCategory(category.trim());
		       
		       model.addObject("subCategoryList", subCategoryList);
		       ResponseEntity<List<CallTypeDto>> entiry = ResponseEntity.ok(subCategoryList);
		       
		       model.setViewName("ticketCentorSA");
		return entiry;
	}
	
	
	@RequestMapping(value = "hm/subCategoryCall/{category}/{subCategory}")
	public ResponseEntity<List<TicketCentorDto>> subCategoryCall(ModelAndView model,@PathVariable("category") String category, @PathVariable("subCategory") String subCategory,
			HttpServletRequest request) {
		
		List<TicketCentorDto> ticketCategoryAndSubCatList = ticketCentorFilterService.findByCategoryAndSubCategory(category,subCategory);
		ResponseEntity<List<TicketCentorDto>> respEntity = ResponseEntity.ok(ticketCategoryAndSubCatList);
		model.addObject("ticketCategoryAndSubCatList", ticketCategoryAndSubCatList);
		model.setViewName("ticketCentorSA");
		return respEntity;
	}

	
	
	// Search Text
	
	
	
	@RequestMapping(value = "hm/ticketCentorFilterCMFSearch/getSearchNext", params = { "page", "size","type","searchText"}, method = RequestMethod.GET, produces = "application/json")
	public Page<TicketCentorDto> findPaginatedByCategoryCMFSearchText( @RequestParam("type") String type,
		      @RequestParam("page") int page, @RequestParam("size") int size,@RequestParam("searchText") String searchText) {
		 
		logger.info("type==findPaginated===========findPaginatedByCategoryCMFSearchText=====+++TYPE="+type+" AND "+searchText);
		
		if(type !=null && type.trim().equals("undefined")){
			type = "";
		}
		if(type !=null && type.trim().equals(">")){
			type = "";
		}
		
		 Page<TicketCentorDto> resultPage = null;
		    
			if(type.equals("High")){
				logger.info("=========findPaginatedByCategoryCMFSearchText=====+++TYPE="+type);
				resultPage= ticketCentorFilterService.findPaginatedCountCmfSearchText(page, size, type,searchText);
			}else if(type.equals("Medium")){
				logger.info("=========findPaginatedByCategoryCMFSearchText=====+++TYPE="+type);
				resultPage= ticketCentorFilterService.findPaginatedCountCmfSearchText(page, size, type,searchText);
			}else if(type.equalsIgnoreCase("Low")){
				logger.info("=========findPaginatedByCategoryCMFSearchText=====+++TYPE="+type);
				resultPage= ticketCentorFilterService.findPaginatedCountCmfSearchText(page, size, type,searchText);
			}else if(type.equals("Total")){
				logger.info("=========findPaginatedByCategoryCMFSearchText=====+++TYPE="+type);
				resultPage= ticketCentorFilterService.findPaginatedCountCmfSearchText(page, size, type,searchText);
				
			}else if(type.equals("TwoToFourHrsCount")){
				logger.info("=========findPaginatedByCategoryCMFSearchText=====+++TYPE="+type);
				resultPage= ticketCentorFilterService.findPaginatedCountCmfSearchText(page, size, type,searchText);
			}else if(type.equals("OneDaysCount")){
				logger.info("=========findPaginatedByCategoryCMFSearchText=====+++TYPE="+type);
			   resultPage= ticketCentorFilterService.findPaginatedCountCmfSearchText(page, size, type,searchText);
			}else if(type.equals("ThreeDaysLessCount")){
				logger.info("=========findPaginatedByCategoryCMFSearchText=====+++TYPE="+type);
				   resultPage= ticketCentorFilterService.findPaginatedCountCmfSearchText(page, size, type,searchText);
		    }else if(type.equals("ThreeDayGreaterCount")){
		    	logger.info("=========findPaginatedByCategoryCMFSearchText=====+++TYPE="+type);
		    	resultPage= ticketCentorFilterService.findPaginatedCountCmfSearchText(page, size, type,searchText);
		         
		    }else if(type !=null && type !="" && type !=">"){
		    	logger.info("=========findPaginatedByCategoryCMFSearchText=====+++ONLY=TYPE="+type);
		    	resultPage= ticketCentorFilterService.findPaginatedCountCmfSearchText(page, size, type,searchText);
		         
		    }else{
		    	//resultPage= ticketCentorFilterService.findPaginatedCount(page, size, type);
		    logger.info("=========findPaginatedByCategoryCMFSearchText=====+++");
			 resultPage = ticketCentorFilterService.findPaginatedCmfSearchText(page, size,searchText);
			 logger.info("findPaginatedByCategoryCMFSearchText ResultPage::::"+resultPage.getContent());
			    if (resultPage !=null && resultPage.getSize()>0){
			    	//return resultPage;
			        }
		    }
		        return resultPage;
		    }
	
	
	// CMS Search Text
	
	
	@RequestMapping(value = "hm/ticketCentorFilterCMSSearch/getSearchNext", params = { "page", "size","type","searchText"}, method = RequestMethod.GET, produces = "application/json")
	public Page<TicketCentorDto> findPaginatedByCategoryCMSSearch( @RequestParam("type") String type,
		      @RequestParam("page") int page, @RequestParam("size") int size ,@RequestParam("searchText") String searchText ) {
		 
		
		if(type !=null && type.trim().equals("undefined")){
			type = "";
		}
		if(type !=null && (type.equals(">") || type ==">")){
			type = "";
		}
		
		 Page<TicketCentorDto> resultPage = null;
		 logger.info("findPaginatedByCategoryCMSSearch--Start----searchText----"+searchText); 
		 logger.info("findPaginatedByCategoryCMSSearch----Start----type----"+type); 
			if(type.equals("High")){
				resultPage= ticketCentorFilterService.findPaginatedCountCmsSearchText(page, size, type,searchText);
			}else if(type.equals("Medium")){
				resultPage= ticketCentorFilterService.findPaginatedCountCmsSearchText(page, size, type,searchText);
			}else if(type.equalsIgnoreCase("Low")){
				resultPage= ticketCentorFilterService.findPaginatedCountCmsSearchText(page, size, type,searchText);
			}else if(type.equals("Total")){
				resultPage= ticketCentorFilterService.findPaginatedCountCmsSearchText(page, size, type,searchText);
				
			}else if(type.equals("TwoToFourHrsCount")){
				resultPage= ticketCentorFilterService.findPaginatedCountCmsSearchText(page, size, type,searchText);
			}else if(type.equals("OneDaysCount")){
			   resultPage= ticketCentorFilterService.findPaginatedCountCmsSearchText(page, size, type,searchText);
			}else if(type.equals("ThreeDaysLessCount")){
				   resultPage= ticketCentorFilterService.findPaginatedCountCmsSearchText(page, size, type,searchText);
		    }else if(type.equals("ThreeDayGreaterCount")){
		    	resultPage= ticketCentorFilterService.findPaginatedCountCmsSearchText(page, size, type,searchText);
		         
		    }else if(type !=null && type !="" && type !=">"){
		    	resultPage= ticketCentorFilterService.findPaginatedCountCmsSearchText(page, size, type,searchText);
		         
		    }else{
		    	logger.info("findPaginatedCmsSearchText---------"+searchText);
		    	//resultPage= ticketCentorFilterService.findPaginatedCount(page, size, type);
			 resultPage = ticketCentorFilterService.findPaginatedCmsSearchText(page, size,searchText);
		      
			    if (resultPage !=null && resultPage.getSize()>0){
			    	//return resultPage;
			        }
		    }
		        return resultPage;
		    }	
	
	
	
	
	@RequestMapping(value = "hm/ticketCentorFilterCUSearch/getSearchNext", params = { "page", "size","type","searchText"}, method = RequestMethod.GET, produces = "application/json")
	public Page<TicketCentorDto> findPaginatedByCircleSearch( @RequestParam("type") String type,
		      @RequestParam("page") int page, @RequestParam("size") int size,@RequestParam("searchText") String searchText) {
		 
		
		 Page<TicketCentorDto> resultPage = null;
		 logger.info("findPaginatedByCircleSearch-----------searchText"+searchText);
	     
		 if(type!=null && !type.isEmpty() && !type.equals("undefined")) {
		    
			if(type.equals("High")){
				resultPage= ticketCentorService.findPaginatedCountByCircleSearch(page, size, type,searchText);
			}else if(type.equals("Medium")){
				resultPage= ticketCentorService.findPaginatedCountByCircleSearch(page, size, type,searchText);
			}else if(type.equalsIgnoreCase("Low")){
				resultPage= ticketCentorService.findPaginatedCountByCircleSearch(page, size, type,searchText);
			}else if(type.equals("Total")){
				resultPage= ticketCentorService.findPaginatedCountByCircleSearch(page, size, type,searchText);
				
			}else if(type.equals("TwoToFourHrsCount")){
				resultPage= ticketCentorService.findPaginatedCountByCircleSearch(page, size, type,searchText);
			}else if(type.equals("OneDaysCount")){
			   resultPage= ticketCentorService.findPaginatedCountByCircleSearch(page, size, type,searchText);
			}else if(type.equals("ThreeDaysLessCount")){
				   resultPage= ticketCentorService.findPaginatedCountByCircleSearch(page, size, type,searchText);
		    }else if(type.equals("ThreeDayGreaterCount")){
		    	resultPage= ticketCentorService.findPaginatedCountByCircleSearch(page, size, type,searchText);
		         
		    }else{
		    	//resultPage= ticketCentorService.findPaginatedCount(page, size, type);
		    
			
			 resultPage = ticketCentorService.findPaginatedByCircleSearch(page, size,searchText);
		      
			    if (resultPage !=null && resultPage.getSize()>0){
			    	//return resultPage;
			        }
		    }
		 }else {
			 resultPage = ticketCentorService.findPaginatedByCircleSearch(page, size,searchText);
		    }
			
		        return resultPage;
		    }
	
	
	
	
	@RequestMapping(value = "hm/ticketCentorCCUSearch/getSearchNext", params = { "page", "size","type","searchText"}, method = RequestMethod.GET, produces = "application/json")
	public Page<TicketCentorDto> findPaginatedCCUSearchText( @RequestParam("type") String type,
		      @RequestParam("page") int page, @RequestParam("size") int size,@RequestParam("searchText") String searchText) {
		 
		logger.info("type==findPaginated===========findPaginatedCCUSearchText==CCU==="+type+"  searchText::::::"+searchText);
		 Page<TicketCentorDto> resultPage = null;
		    
			if(type.equals("High")){
				resultPage= ticketCentorService.findPaginatedCCUCountSearch(page, size, type,searchText);
			}else if(type.equals("Medium")){
				resultPage= ticketCentorService.findPaginatedCCUCountSearch(page, size, type,searchText);
			}else if(type.equalsIgnoreCase("Low")){
				resultPage= ticketCentorService.findPaginatedCCUCountSearch(page, size, type,searchText);
			}else if(type.equals("Total")){
				resultPage= ticketCentorService.findPaginatedCCUCountSearch(page, size, type,searchText);
				
			}else if(type.equals("TwoToFourHrsCount")){
				resultPage= ticketCentorService.findPaginatedCCUCountSearch(page, size, type,searchText);
			}else if(type.equals("OneDaysCount")){
			   resultPage= ticketCentorService.findPaginatedCCUCountSearch(page, size, type,searchText);
			}else if(type.equals("ThreeDaysLessCount")){
				   resultPage= ticketCentorService.findPaginatedCCUCountSearch(page, size, type,searchText);
		    }else if(type.equals("ThreeDayGreaterCount")){
		    	resultPage= ticketCentorService.findPaginatedCCUCountSearch(page, size, type,searchText);
		         
		    }else{
		    	//resultPage= ticketCentorService.findPaginatedCount(page, size, type);
		    	logger.info("type==findPaginated===========ticketCentorServiceCC=====CC ELSE");
			
			 resultPage = ticketCentorService.findPaginatedCCUSearch(page, size,searchText);
		      
			    if (resultPage !=null && resultPage.getSize()>0){
			    	//return resultPage;
			        }
		    }
		        return resultPage;
		    }
	
	
	@RequestMapping(value = "hm/ticketCentorFilterSASearch/getSearchNext", params = { "page", "size","type","searchText"}, method = RequestMethod.GET, produces = "application/json")
	public Page<TicketCentorDto> findPaginatedByCategorySASearch( @RequestParam("type") String type,
		      @RequestParam("page") int page, @RequestParam("size") int size,@RequestParam("searchText") String searchText) {
		
		logger.info("findPaginatedByCategorySASearch-----------searchText:: "+searchText);
		logger.info("findPaginatedByCategorySASearch-----------type:: "+type);
		
		logger.info("findPaginatedByCategorySASearch-----------page::::: "+page);
		
		logger.info("findPaginatedByCategorySASearch-----------size::::: "+size);
		
		if(type !=null && type.trim().equals("undefined")){
			type = "";
		}
		
		 Page<TicketCentorDto> resultPage = null;
		    
			if(type.equals("High")){
				resultPage= ticketCentorFilterService.findPaginatedCountSearch(page, size, type,searchText);
			}else if(type.equals("Medium")){
				resultPage= ticketCentorFilterService.findPaginatedCountSearch(page, size, type,searchText);
			}else if(type.equalsIgnoreCase("Low")){
				resultPage= ticketCentorFilterService.findPaginatedCountSearch(page, size, type,searchText);
			}else if(type.equals("Total")){
				resultPage= ticketCentorFilterService.findPaginatedCountSearch(page, size, type,searchText);
				
			}else if(type.equals("TwoToFourHrsCount")){
				resultPage= ticketCentorFilterService.findPaginatedCountSearch(page, size, type,searchText);
			}else if(type.equals("OneDaysCount")){
			   resultPage= ticketCentorFilterService.findPaginatedCountSearch(page, size, type,searchText);
			}else if(type.equals("ThreeDaysLessCount")){
				   resultPage= ticketCentorFilterService.findPaginatedCountSearch(page, size, type,searchText);
		    }else if(type.equals("ThreeDayGreaterCount")){
		    	resultPage= ticketCentorFilterService.findPaginatedCountSearch(page, size, type,searchText);
		         
		    }else if(type.equals("TotalCount")){
    	       resultPage= ticketCentorFilterService.findPaginatedCountSearch(page, size, type,searchText);
         
            } else if(type !=null && type !=""){
		    	resultPage= ticketCentorFilterService.findPaginatedCountSearch(page, size, type,searchText);
		         
		    }else{
		    	//resultPage= ticketCentorFilterService.findPaginatedCount(page, size, type);
			 resultPage = ticketCentorFilterService.findPaginatedSASearch(page, size,searchText);
		      
			    if (resultPage !=null && resultPage.getSize()>0){
			    	//return resultPage;
			        }
		    }
		        return resultPage;
		    }
	
	

}
