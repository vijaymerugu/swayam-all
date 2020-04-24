package sbi.kiosk.swayam.healthmonitoring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sbi.kiosk.swayam.common.dto.RequestsDto;
import sbi.kiosk.swayam.common.dto.RequestsManagementDto;
import sbi.kiosk.swayam.healthmonitoring.service.HealthMonitoringService;

@RestController
public class HealthMonitoringController {
	
	@Autowired
	HealthMonitoringService healthMonitoringService;
	
	@RequestMapping("/hm/requestFormCmf")
	public ModelAndView requestFormCmf() {
		
		ModelAndView mav = new ModelAndView("requestFormCmf");
		return mav;
	}
	
	@RequestMapping("/hm/requestFormGridCmf")
	public ModelAndView requestFormGridCmf() {
		
		ModelAndView mav = new ModelAndView("requestFormGridCmf");
		return mav;
	}
	
	@RequestMapping("/hm/requestFormCms")
	public ModelAndView requestFormCms() {
		
		ModelAndView mav = new ModelAndView("requestFormCms");
		return mav;
	}
	
	@RequestMapping("/hm/requestFormCC")
	public ModelAndView requestFormCC() {
		
		ModelAndView mav = new ModelAndView("requestFormCC");
		return mav;
	}
	
	
	@RequestMapping(value = "/hm/addRequest")
	public ModelAndView saveRequestForCmf(ModelAndView model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		
		RequestsDto dto = new RequestsDto();		
		dto.setBranchCode(request.getParameter("branchCode"));
		dto.setKioskId(request.getParameter("kioskId"));
		dto.setVendor(request.getParameter("vendor"));
		dto.setTypeOfRequest(request.getParameter("typeOfRequest"));
		dto.setCategory(request.getParameter("category"));
		dto.setSubCategory(request.getParameter("subCategory"));
		dto.setSubject(request.getParameter("subject"));
		dto.setComments(request.getParameter("comments"));		
		healthMonitoringService.saveRequestForCmf(dto);
		return model;
	}
	
	@RequestMapping(value = "/hm/requestsCmf/get", params = { "page", "size" }, method = RequestMethod.GET, produces = "application/json")
	public Page<RequestsDto> findPaginatedCmf(
		      @RequestParam("page") int page, @RequestParam("size") int size) {
		 
		        Page<RequestsDto> resultPage = healthMonitoringService.findPaginatedCmf(page, size);
		        if (page > resultPage.getTotalPages()) {
		            //throw new MyResourceNotFoundException();
		        }
		 
		        return resultPage;
		    }
	
	@RequestMapping(value = "/hm/requestsCms/get", params = { "page", "size" }, method = RequestMethod.GET, produces = "application/json")
	public Page<RequestsDto> findPaginated(
		      @RequestParam("page") int page, @RequestParam("size") int size) {
		 
		        Page<RequestsDto> resultPage = healthMonitoringService.findPaginated(page, size);
		        if (page > resultPage.getTotalPages()) {
		            //throw new MyResourceNotFoundException();
		        }
		 
		        return resultPage;
		    }
	
	@RequestMapping(value = "/hm/requestsCC/get", params = { "page", "size" }, method = RequestMethod.GET, produces = "application/json")
	public Page<RequestsDto> findPaginatedCC(
		      @RequestParam("page") int page, @RequestParam("size") int size) {
		 
		        Page<RequestsDto> resultPage = healthMonitoringService.findPaginatedCC(page, size);
		        if (page > resultPage.getTotalPages()) {
		            //throw new MyResourceNotFoundException();
		        }
		 
		        return resultPage;
		    }
	
	@RequestMapping(value = "/hm/saveCheckerCommentsCms" , method = RequestMethod.POST)
	//public ModelAndView saveCheckerComments(@RequestParam("array") List<CheckerComments> array) {
	public ModelAndView saveCheckerCommentsCms(@RequestBody String array) {
		
		healthMonitoringService.saveCheckerCommentsCms(array);
		
		ModelAndView mav = new ModelAndView("requestFormCms");
		return mav;
	}
	
	@RequestMapping(value = "/hm/rejectCheckerCommentsCms" , method = RequestMethod.POST)
	//public ModelAndView saveCheckerComments(@RequestParam("array") List<CheckerComments> array) {
	public ModelAndView rejectCheckerCommentsCms(@RequestBody String array) {
		
		healthMonitoringService.rejectCheckerCommentsCms(array);
		
		ModelAndView mav = new ModelAndView("requestFormCms");
		return mav;
	}
	
	@RequestMapping(value = "/hm/saveApproverCommentsCC" , method = RequestMethod.POST)	
	public ModelAndView saveApproverCommentsCC(@RequestBody String array) {
		
		healthMonitoringService.saveApproverCommentsCC(array);
		
		ModelAndView mav = new ModelAndView("requestFormCC");
		return mav;
	}
	
	@RequestMapping(value = "/hm/rejectApproverCommentsCC" , method = RequestMethod.POST)	
	public ModelAndView rejectApproverCommentsCC(@RequestBody String array) {
		
		healthMonitoringService.rejectApproverCommentsCC(array);
		
		ModelAndView mav = new ModelAndView("requestFormCC");
		return mav;
	}
	
	@RequestMapping(value = "/hm/viewCmfCaseId")	
	public ModelAndView viewCmfCaseId(@RequestParam("caseId") int caseId) {
		
		RequestsManagementDto dto = healthMonitoringService.viewCaseId(caseId);
		
		ModelAndView mav = new ModelAndView("requestFormCmfCaseId");
		mav.addObject("dto", dto);
		return mav;
	}
	
	@RequestMapping(value = "/hm/viewCmsCaseId")	
	public ModelAndView viewCmsCaseId(@RequestParam("caseId") int caseId) {
		
		RequestsManagementDto dto = healthMonitoringService.viewCaseId(caseId);
		
		ModelAndView mav = new ModelAndView("requestFormCmsCaseId");
		mav.addObject("dto", dto);
		return mav;
	}
	
	@RequestMapping(value = "/hm/viewCCCaseId")	
	public ModelAndView viewCCCaseId(@RequestParam("caseId") int caseId) {
		
		RequestsManagementDto dto = healthMonitoringService.viewCaseId(caseId);
		
		ModelAndView mav = new ModelAndView("requestFormCCCaseId");
		mav.addObject("dto", dto);
		return mav;
	}
}
