package sbi.kiosk.swayam.healthmonitoring.controller;

import java.util.List;

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
import sbi.kiosk.swayam.healthmonitoring.model.CheckerComments;
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
	
	@RequestMapping("/hm/requestFormCms")
	public ModelAndView requestFormCms() {
		
		ModelAndView mav = new ModelAndView("requestFormCms");
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
	
	@RequestMapping(value = "/hm/requestsCms/get", params = { "page", "size" }, method = RequestMethod.GET, produces = "application/json")
	public Page<RequestsDto> findPaginated(
		      @RequestParam("page") int page, @RequestParam("size") int size) {
		 
		        Page<RequestsDto> resultPage = healthMonitoringService.findPaginated(page, size);
		        if (page > resultPage.getTotalPages()) {
		            //throw new MyResourceNotFoundException();
		        }
		 
		        return resultPage;
		    }
	
	@RequestMapping(value = "/hm/saveCheckerComments" , method = RequestMethod.POST)
	//public ModelAndView saveCheckerComments(@RequestParam("array") List<CheckerComments> array) {
	public ModelAndView saveCheckerComments(@RequestBody String array) {
		
		ModelAndView mav = new ModelAndView("requestFormCms");
		return mav;
	}
}
