package sbi.kiosk.swayam.common.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.healthmonitoring.service.TicketCentorFilterService;
import sbi.kiosk.swayam.healthmonitoring.service.TicketCentorService;
import sbi.kiosk.swayam.kioskmanagement.service.KioskManagementService;

@RestController
public class ViewResolverController {
	@Autowired
	KioskManagementService kioskManagementService;
	@Autowired
	TicketCentorService ticketCentorService;
	
	@Autowired
	TicketCentorFilterService ticketCentorFilterService;
	
	
	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	@RequestMapping("/km/userkioskmapping")
	public ModelAndView userKioskMapping() {		
		
		ModelAndView mav = new ModelAndView("userkioskmapping");
		return mav;
	}
	
	@RequestMapping("/km/cmscmfmapping")
	public ModelAndView cmsCmfkMapping() {		
		
		ModelAndView mav = new ModelAndView("cmscmfmapping");
		return mav;
	}
	
	@RequestMapping("/km/kioskManagement")
	public ModelAndView kioskManagement(ModelAndView mav) {
		Map<String, Integer> mapDataCount = null;
		mapDataCount = kioskManagementService.findAllKioskMasterCount();
		if (mapDataCount != null && !mapDataCount.isEmpty()) {
			mav.addObject("mapDataCount", mapDataCount);
		}

		mav.setViewName("kioskManagement");
		return mav;
	}

	
	@RequestMapping("/km/ticketcentor")
	public ModelAndView ticketCentor(ModelAndView mav) {

		Map<String, Integer> mapDataList = null;
		mapDataList = ticketCentorService.findAllSeverityOfTicketsCount();
		if (mapDataList != null && !mapDataList.isEmpty()) {
			mav.addObject("mapDataList", mapDataList);
		}
		
		Map<String, Integer> ageingMapDataList = null;
		ageingMapDataList = ticketCentorService.findAllAgeingOfTicketsCount();
		if (ageingMapDataList != null && !ageingMapDataList.isEmpty()) {
			mav.addObject("ageingMapDataList", ageingMapDataList);
		}
		mav.setViewName("ticketCentor");
		return mav;
	}

	
	
	
	@RequestMapping("/km/ticketcentorCallCategory")
	public ModelAndView ticketcentorCallCategory(ModelAndView mav,HttpServletRequest request) {

		Map<String, Integer> mapDataList = null;
		mapDataList = ticketCentorFilterService.findAllSeverityOfTicketsCount();
		if (mapDataList != null && !mapDataList.isEmpty()) {
			mav.addObject("mapDataList", mapDataList);
		}
		
		Map<String, Integer> ageingMapDataList = null;
		ageingMapDataList = ticketCentorFilterService.findAllAgeingOfTicketsCount();
		if (ageingMapDataList != null && !ageingMapDataList.isEmpty()) {
			mav.addObject("ageingMapDataList", ageingMapDataList);
		}

		Map<String, Object> categoryMapDataList  =ticketCentorFilterService.findAllCategory();
		if (categoryMapDataList != null && !categoryMapDataList.isEmpty()) {
			mav.addObject("categoryMapDataList", categoryMapDataList);
		}
		mav.setViewName("ticketCentorSA");
		return mav;
	}

}
