package sbi.kiosk.swayam.healthmonitoring.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.common.dto.TerminalStatusDto;
import sbi.kiosk.swayam.healthmonitoring.service.TerminalStatusService;

@RestController
public class TerminalStatusController {
	
	@Autowired
	TerminalStatusService terminalStatusService;
	
	@RequestMapping(value="/ts/terminalStatus")
	public ModelAndView tirminalStatus(ModelAndView mav){
		System.out.println("tirminalStatus start().....");
		Map<String, Integer> mapAgentStatusCount = null;
		mapAgentStatusCount = terminalStatusService.findAllCountAgentStatus();
		if (mapAgentStatusCount != null && !mapAgentStatusCount.isEmpty()) {
			mav.addObject("mapAgentStatusCount", mapAgentStatusCount);
		}
		mav.setViewName("terminalstatus");
		return mav;
	}
	
	
	
		
	@RequestMapping(value = "/ts/terminalStatusGet/get", params = { "page", "size","type"}, method = RequestMethod.GET, produces = "application/json")
	public Page<TerminalStatusDto> findPaginated(
		      @RequestParam("page") int page, @RequestParam("size") int size,@RequestParam("type") String type) {
		 System.out.println("type=======1134==========="+type);
		 Page<TerminalStatusDto> resultPage = null;
		 
		 if(type!=null && !type.isEmpty() && !type.equals("undefined")){
			  System.out.println("If=="+type);
			  resultPage = terminalStatusService.findPaginatedCount(page, size,type);
		 }else{
		      resultPage = terminalStatusService.findPaginated(page, size);
	     System.out.println("resultPage==="+resultPage.getContent());
	    }if (page > resultPage.getTotalPages()){
		            //throw new MyResourceNotFoundException();
		        }
		 return resultPage;
	}
	
	
	

}
