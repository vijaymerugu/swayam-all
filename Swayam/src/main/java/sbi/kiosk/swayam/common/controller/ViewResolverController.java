package sbi.kiosk.swayam.common.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
	
	@RequestMapping("km/userkioskmapping")
	public ModelAndView userKioskMapping() {		
		
		ModelAndView mav = new ModelAndView("userkioskmapping");
		return mav;
	}
	 
	@RequestMapping("km/cmscmfmapping")
	public ModelAndView cmsCmfkMapping() {		
		
		ModelAndView mav = new ModelAndView("cmscmfmapping");
		return mav;
	}
	
	@RequestMapping("km/kioskManagement")
	@PreAuthorize("hasPermission('kioskManagement','CREATE')")
	public ModelAndView kioskManagement(ModelAndView mav) {
		Map<String, Integer> mapDataCount = null;
		mapDataCount = kioskManagementService.findAllKioskMasterCountByCircle();
		if (mapDataCount != null && !mapDataCount.isEmpty()) {
			mav.addObject("mapDataCount", mapDataCount);
		}

		mav.setViewName("kioskManagement");
		return mav;
	}
	
	@RequestMapping("km/kioskManagementCC")
	@PreAuthorize("hasPermission('kioskManagementCC','CREATE')")
	public ModelAndView kioskManagementCC() {
		ModelAndView mav = new ModelAndView("kioskManagementCC");	
		Map<String, Integer> mapDataCount = null;
		mapDataCount = kioskManagementService.findAllKioskMasterCount();
		if (mapDataCount != null && !mapDataCount.isEmpty()) {
			mav.addObject("mapDataCount", mapDataCount);
		}
			
		return mav;
	}

	
	@RequestMapping("hm/ticketcentor")
	@PreAuthorize("hasPermission('ticketCentorCC','CREATE')")
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
	
	@RequestMapping("hm/ticketcentorByCircle")
	@PreAuthorize("hasPermission('ticketCentorByCircle','CREATE')")
	public ModelAndView ticketCentorByCircle(ModelAndView mav) {

		Map<String, Integer> mapDataList = null;
		mapDataList = ticketCentorService.findAllSeverityOfTicketsCountByCircle();
		if (mapDataList != null && !mapDataList.isEmpty()) {
			mav.addObject("mapDataList", mapDataList);
		}
		
		Map<String, Integer> ageingMapDataList = null;
		ageingMapDataList = ticketCentorService.findAllAgeingOfTicketsCountByCircle();
		if (ageingMapDataList != null && !ageingMapDataList.isEmpty()) {
			mav.addObject("ageingMapDataList", ageingMapDataList);
		}
		mav.setViewName("ticketCentorCU");
		return mav;
	}

	
	
	
	@RequestMapping("hm/ticketcentorCallCategory")	
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
	
	@RequestMapping("hm/ticketcentorCallCategoryCMF")
	@PreAuthorize("hasPermission('ticketcentorCallCategoryCMF','CREATE')")
	public ModelAndView ticketcentorCallCategoryCMF(ModelAndView mav,HttpServletRequest request) {

		Map<String, Integer> mapDataList = null;
		mapDataList = ticketCentorFilterService.findAllSeverityOfTicketsCountCMF();
		if (mapDataList != null && !mapDataList.isEmpty()) {
			mav.addObject("mapDataList", mapDataList);
		}
		
		Map<String, Integer> ageingMapDataList = null;
		ageingMapDataList = ticketCentorFilterService.findAllAgeingOfTicketsCountCMF();
		if (ageingMapDataList != null && !ageingMapDataList.isEmpty()) {
			mav.addObject("ageingMapDataList", ageingMapDataList);
		}

		Map<String, Object> categoryMapDataList  =ticketCentorFilterService.findAllCategoryCMF();
		if (categoryMapDataList != null && !categoryMapDataList.isEmpty()) {
			mav.addObject("categoryMapDataList", categoryMapDataList);
		}
		mav.setViewName("ticketCentorCMF");
		return mav;
	}

	@RequestMapping("hm/ticketcentorCallCategoryCMS")
	@PreAuthorize("hasPermission('ticketcentorCallCategoryCMS','CREATE')")
	public ModelAndView ticketcentorCallCategoryCMS(ModelAndView mav,HttpServletRequest request) {

		Map<String, Integer> mapDataList = null;
		mapDataList = ticketCentorFilterService.findAllSeverityOfTicketsCountCMS();
		if (mapDataList != null && !mapDataList.isEmpty()) {
			mav.addObject("mapDataList", mapDataList);
		}
		
		Map<String, Integer> ageingMapDataList = null;
		ageingMapDataList = ticketCentorFilterService.findAllAgeingOfTicketsCountCMS();
		if (ageingMapDataList != null && !ageingMapDataList.isEmpty()) {
			mav.addObject("ageingMapDataList", ageingMapDataList);
		}

		Map<String, Object> categoryMapDataList  =ticketCentorFilterService.findAllCategoryCMS();
		if (categoryMapDataList != null && !categoryMapDataList.isEmpty()) {
			mav.addObject("categoryMapDataList", categoryMapDataList);
		}
		mav.setViewName("ticketCentorCMS");
		return mav;
	}

	@RequestMapping("km/headerTemplate")
	public ModelAndView headerTemplate() {		
		
		ModelAndView mav = new ModelAndView("header-template");
		return mav;
	}
	
	@RequestMapping("da/availability")
	@PreAuthorize("hasPermission('daAvailability','READ')")
	public ModelAndView dataAnalyserAvailability(ModelAndView mav) {
	
		mav.setViewName("daAvailability");
		return mav;
	}
	
	@RequestMapping("da/vendorWiseUpTime")
	@PreAuthorize("hasPermission('daVendorWiseUpTime','READ')")
	public ModelAndView dataAnalyserVendorWiseUpTime(ModelAndView mav) {
	
		mav.setViewName("daVendorWiseUpTime");
		return mav;
	}
	
	@RequestMapping("da/errorTypeWiseUpTime")
	@PreAuthorize("hasPermission('daErrorTypeWiseUpTime','READ')")
	public ModelAndView dataAnalyserErrorTypeWiseUpTime(ModelAndView mav) {
	
		mav.setViewName("daErrorTypeWiseUpTime");
		return mav;
	}
	
	@RequestMapping("da/summaryOfDownKiosks")
	@PreAuthorize("hasPermission('daSummaryOfDownKiosks','READ')")
	public ModelAndView dataAnalyserSummaryOfDownKiosks(ModelAndView mav) {
	
		mav.setViewName("daSummaryOfDownKiosks");
		return mav;
	}
	
	@RequestMapping("da/TATOfDownKiosks")
	@PreAuthorize("hasPermission('daTATOfDownKiosks','READ')")
	public ModelAndView dataAnalyserTATOfDownKiosks(ModelAndView mav) {
	
		mav.setViewName("daTATOfDownKiosks");
		return mav;
	}
	
	@RequestMapping("da/cumulativeDataCC")
	@PreAuthorize("hasPermission('daCumulativeDataCC','READ')")
	public ModelAndView dataAnalyserCumulativeDataCC(ModelAndView mav) {
	
		mav.setViewName("daCumulativeDataCC");
		return mav;
	}
	
	@RequestMapping("da/cumulativeCircleData")
	@PreAuthorize("hasPermission('daCumulativeCircleData','READ')")
	public ModelAndView dataAnalyserCumulativeCircleData(ModelAndView mav) {
	
		mav.setViewName("daCumulativeCircleData");
		return mav;
	}
	
	@RequestMapping("da/userWiseData")
	@PreAuthorize("hasPermission('daUserWiseData','READ')")
	public ModelAndView dataAnalyserUserWiseData(ModelAndView mav) {
	
		mav.setViewName("daUserWiseData");
		return mav;
	}
	
	@RequestMapping("mis-report/view")
	@PreAuthorize("hasPermission('misReportView','READ')")
	public ModelAndView misReportView(ModelAndView mav) {
	
		mav.setViewName("misReportView");
		return mav;
	}

}
