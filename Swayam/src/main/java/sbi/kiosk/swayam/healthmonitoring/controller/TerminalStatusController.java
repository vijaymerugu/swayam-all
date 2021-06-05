package sbi.kiosk.swayam.healthmonitoring.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.common.dto.TerminalStatusDto;
import sbi.kiosk.swayam.common.dto.TerminalStatusSearchTextDto;
import sbi.kiosk.swayam.healthmonitoring.service.TerminalStatusService;

@RestController
public class TerminalStatusController {
	Logger logger = LoggerFactory.getLogger(TerminalStatusController.class);
	@Autowired
	TerminalStatusService terminalStatusService;
	
	@RequestMapping(value="ts/terminalStatus")
	public ModelAndView tirminalStatus(ModelAndView mav){
		logger.info("tirminalStatus start().....");
		Map<String, Integer> mapAgentStatusCount = null;
		mapAgentStatusCount = terminalStatusService.findAllCountAgentStatus();
		if (mapAgentStatusCount != null && !mapAgentStatusCount.isEmpty()) {
			mav.addObject("mapAgentStatusCount", mapAgentStatusCount);
		}
		mav.setViewName("terminalstatus");
		return mav;
	}
	
	
	
	//changes by satendra 28052021
	
	/*
		
	@RequestMapping(value = "ts/terminalStatusGet/get", params = { "page", "size","type"}, method = RequestMethod.GET, produces = "application/json")
	public Page<TerminalStatusDto> findPaginated(
		      @RequestParam("page") int page, @RequestParam("size") int size,@RequestParam("type") String type) {
		logger.info("type=======1134==========="+type);
		 Page<TerminalStatusDto> resultPage = null;
		 
		 if(type!=null && !type.isEmpty() && !type.equals("undefined")){
			 logger.info("If=="+type);
			  resultPage = terminalStatusService.findPaginatedCount(page, size,type);
		 }else{
		      resultPage = terminalStatusService.findPaginated(page, size);
	    logger.info("resultPage==="+resultPage.getContent());
	    }if (page > resultPage.getTotalPages()){
		            //throw new MyResourceNotFoundException();
		        }
		 return resultPage;
	}
	
	*/
	
	@RequestMapping(value = "ts/terminalStatusGet/get", params = { "page", "size","type"}, method = RequestMethod.GET, produces = "application/json")
	public Page<TerminalStatusSearchTextDto> findPaginated(
		      @RequestParam("page") int page, @RequestParam("size") int size,@RequestParam("type") String type) {
		logger.info("type=======1134==========="+type);
		 Page<TerminalStatusSearchTextDto> resultPage = null;
		 
		 if(type!=null && !type.isEmpty() && !type.equals("undefined")){
			 logger.info("If=="+type);
			 // resultPage = terminalStatusService.findPaginatedCount(page, size,type);
			  resultPage = terminalStatusService.findPaginatedCount_NQ(page, size,type);
		 }else{
		      resultPage = terminalStatusService.findPaginatedNew(page, size);
	    logger.info("resultPage==="+resultPage.getContent());
	    }if (page > resultPage.getTotalPages()){
		            //throw new MyResourceNotFoundException();
		        }
		 return resultPage;
	}
	
	
	
	@RequestMapping(value = "ts/terminalStatusSearch/getSearchNext", params = { "page", "size","type", "searchText" }, method = RequestMethod.GET, produces = "application/json")
	public Page<TerminalStatusSearchTextDto> findPaginatedAfterSearchNext(
		      @RequestParam("page") int page, @RequestParam("size") int size,@RequestParam("type") String type,@RequestParam("searchText") String searchText) {
		logger.info("type=======1134==========="+type);
		logger.info("=======searchText==========="+searchText);
		 Page<TerminalStatusSearchTextDto> resultPage = null;
		 
		 if(type!=null && !type.isEmpty() && !type.equals("undefined")){
			 logger.info("If=="+type);
			  //resultPage = terminalStatusService.findPaginatedCount(page, size,type);
			  resultPage = terminalStatusService.findPaginatedCount_SearchTextNQ(page, size,type,searchText);
			  
		 }else{
		      resultPage = terminalStatusService.findPaginatedSearchText(page, size,searchText);
	    logger.info("resultPage==="+resultPage.getContent());
	    }if (page > resultPage.getTotalPages()){
		            //throw new MyResourceNotFoundException();
		        }
		 return resultPage;
	}
	
	
	
	

}
