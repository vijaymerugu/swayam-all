package sbi.kiosk.swayam.healthmonitoring.controller;

import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sbi.kiosk.swayam.common.dto.RequestsDto;
import sbi.kiosk.swayam.common.dto.RequestsManagementDto;
import sbi.kiosk.swayam.common.validation.ValidationCommon;
import sbi.kiosk.swayam.healthmonitoring.service.HealthMonitoringService;
import sbi.kiosk.swayam.kioskmanagement.controller.UserManagementController;

@RestController
public class HealthMonitoringController {
	
	Logger logger = LoggerFactory.getLogger(HealthMonitoringController.class);
	
	@Autowired
	HealthMonitoringService healthMonitoringService;
	
	@RequestMapping("hm/requestFormCmf")
	@PreAuthorize("hasPermission('requestFormCmf','CREATE')")
	public ModelAndView requestFormCmf(ModelAndView mav,@ModelAttribute("requestDto") RequestsManagementDto requestDto) {
		logger.info("Comment "+requestDto.getComments());
		//ModelAndView mav = new ModelAndView("requestFormCmf");
		mav.setViewName("requestFormCmf");
		return mav;
	}
	
	@RequestMapping("hm/requestFormGridCmf")
	@PreAuthorize("hasPermission('requestFormGridCmf','CREATE')")
	public ModelAndView requestFormGridCmf() {
		
		ModelAndView mav = new ModelAndView("requestFormGridCmf");
		return mav;
	}
	
	@RequestMapping("hm/requestFormCms")
	@PreAuthorize("hasPermission('requestFormCms','CREATE')")
	public ModelAndView requestFormCms() {
		
		ModelAndView mav = new ModelAndView("requestFormCms");
		return mav;
	}
	
	@RequestMapping("hm/requestFormCC")
	@PreAuthorize("hasPermission('requestFormCC','CREATE')")
	public ModelAndView requestFormCC() {
		
		ModelAndView mav = new ModelAndView("requestFormCC");
		return mav;
	}
	
	
	
	@GetMapping("hm/checkDuplicateKiosk/{kioskId}")
	public ResponseEntity<String>  checkDuplicateKiosk(@PathVariable("kioskId") String kioskId) {
		String result=healthMonitoringService.checkDuplicateKiosAppr(kioskId);
		
		ResponseEntity<String> entity=ResponseEntity.ok(result);
		return entity;
	}
	
	
	@PostMapping(value = "hm/addRequest")
	@PreAuthorize("hasPermission('saveRequestForCmf','CREATE')")
	public ResponseEntity<String>  saveRequestForCmf(ModelAndView model, HttpServletRequest request,
			RedirectAttributes redirectAttributes,@ModelAttribute("requestDto") RequestsManagementDto requestDto) {
		System.err.println("saveRequestForCmf==="+requestDto.getBranchCode());
		ResponseEntity<String> entity=null;
		RequestsDto dto = new RequestsDto();		
		dto.setBranchCode(ValidationCommon.validateString(request.getParameter("branchCode")));
		dto.setKioskId(ValidationCommon.validateString(request.getParameter("kioskId")));
		dto.setVendor(ValidationCommon.validateString(request.getParameter("vendor")));
		dto.setTypeOfRequest(ValidationCommon.validateString(request.getParameter("typeOfRequest")));
		//dto.setCategory(ValidationCommon.validateString(request.getParameter("category")));
		//dto.setSubCategory(ValidationCommon.validateString(request.getParameter("subCategory")));
		dto.setSubject(ValidationCommon.validateString(request.getParameter("subject")));
		dto.setComments(ValidationCommon.validateStringChar(request.getParameter("comments")));		
		String result=healthMonitoringService.saveRequestForCmf(dto);
		entity=ResponseEntity.ok(result);
		
		return entity;
	}
	
	
	
	@RequestMapping(value = "hm/requestsCmf/get", params = { "page", "size" }, method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasPermission('HMfindPaginatedCmf','CREATE')")
	public Page<RequestsDto> findPaginatedCmf(
		      @RequestParam("page") int page, @RequestParam("size") int size) {
		 
		        Page<RequestsDto> resultPage = healthMonitoringService.findPaginatedCmf(page, size);
		        if (page > resultPage.getTotalPages()) {
		            //throw new MyResourceNotFoundException();
		        }
		 
		        return resultPage;
		    }
	
	@RequestMapping(value = "hm/requestsCms/get", params = { "page", "size" }, method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasPermission('HMfindPaginatedCms','CREATE')")
	public Page<RequestsDto> findPaginated(
		      @RequestParam("page") int page, @RequestParam("size") int size) {
		 
		        Page<RequestsDto> resultPage = healthMonitoringService.findPaginated(page, size);
		        if (page > resultPage.getTotalPages()) {
		            //throw new MyResourceNotFoundException();
		        }
		 
		        return resultPage;
		    }
	
	@RequestMapping(value = "hm/requestsCC/get", params = { "page", "size" }, method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasPermission('HMfindPaginatedCC','CREATE')")
	public Page<RequestsDto> findPaginatedCC(
		      @RequestParam("page") int page, @RequestParam("size") int size) {
		 
		        Page<RequestsDto> resultPage = healthMonitoringService.findPaginatedCC(page, size);
		        if (page > resultPage.getTotalPages()) {
		            //throw new MyResourceNotFoundException();
		        }
		 
		        return resultPage;
		    }
	
	@RequestMapping(value = "hm/saveCheckerCommentsCms" , method = RequestMethod.POST)
	//public ModelAndView saveCheckerComments(@RequestParam("array") List<CheckerComments> array) {
	@PreAuthorize("hasPermission('HMsaveCheckerCommentsCms','CREATE')")
	public ResponseEntity<String> saveCheckerCommentsCms(@RequestBody String array) {
		
		healthMonitoringService.saveCheckerCommentsCms(array);
		logger.info("saveCheckerCommentsCms===array=");
		//ModelAndView mav = new ModelAndView("requestFormCms");
		ResponseEntity<String> entiry = ResponseEntity.ok(array);
		return entiry;
	}
	
	@RequestMapping(value = "hm/rejectCheckerCommentsCms" , method = RequestMethod.POST)
	//public ModelAndView saveCheckerComments(@RequestParam("array") List<CheckerComments> array) {
	@PreAuthorize("hasPermission('HMrejectCheckerCommentsCms','EDIT')")
		
	public ResponseEntity<String> rejectCheckerCommentsCms(@RequestBody String array) {
		healthMonitoringService.rejectCheckerCommentsCms(array);
		
		ModelAndView mav = new ModelAndView("requestFormCms");
		ResponseEntity<String> entiry = ResponseEntity.ok(array);
		return entiry;
	}
	
	@RequestMapping(value = "hm/saveApproverCommentsCC" , method = RequestMethod.POST)	
	@PreAuthorize("hasPermission('HMsaveApproverCommentsCC','CREATE')")
	public ResponseEntity<String> saveApproverCommentsCC(@RequestBody String array) {
		
		healthMonitoringService.saveApproverCommentsCC(array);
		
		
		ResponseEntity<String> entiry = ResponseEntity.ok(array);
		return entiry;
	}
	
	@RequestMapping(value = "hm/rejectApproverCommentsCC" , method = RequestMethod.POST)
	@PreAuthorize("hasPermission('HMrejectApproverCommentsCC','EDIT')")
	public ResponseEntity<String> rejectApproverCommentsCC(@RequestBody String array) {
		
		healthMonitoringService.rejectApproverCommentsCC(array);
		
		ResponseEntity<String> entiry = ResponseEntity.ok(array);
		return entiry;
	}
	
	@RequestMapping(value = "hm/viewCmfCaseId")	
	@PreAuthorize("hasPermission('HMviewCmfCaseId','READ')")
	public ModelAndView viewCmfCaseId(@RequestParam("caseId") int caseId) {
		
		RequestsManagementDto dto = healthMonitoringService.viewCaseId(caseId);
		
		ModelAndView mav = new ModelAndView("requestFormCmfCaseId");
		mav.addObject("dto", dto);
		return mav;
	}
	
	@RequestMapping(value = "hm/viewCmsCaseId")	
	@PreAuthorize("hasPermission('HMviewCmsCaseId','READ')")
	public ModelAndView viewCmsCaseId(@RequestParam("caseId") int caseId) {
		
		RequestsManagementDto dto = healthMonitoringService.viewCaseId(caseId);
		
		ModelAndView mav = new ModelAndView("requestFormCmsCaseId");
		mav.addObject("dto", dto);
		return mav;
	}
	
	@RequestMapping(value = "hm/viewCCCaseId")	
	@PreAuthorize("hasPermission('HMviewCCCaseId','READ')")
	public ModelAndView viewCCCaseId(@RequestParam("caseId") int caseId) {
		
		RequestsManagementDto dto = healthMonitoringService.viewCaseId(caseId);
		
		ModelAndView mav = new ModelAndView("requestFormCCCaseId");
		mav.addObject("dto", dto);
		return mav;
	}
}
