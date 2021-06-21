package sbi.kiosk.swayam.healthmonitoring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import sbi.kiosk.swayam.common.dto.DownTimeDto;
import sbi.kiosk.swayam.common.entity.Circle;
import sbi.kiosk.swayam.common.entity.User;
import sbi.kiosk.swayam.common.repository.UserRepository;
import sbi.kiosk.swayam.healthmonitoring.model.DowntimeReport;
import sbi.kiosk.swayam.healthmonitoring.service.DowntimeService;
import sbi.kiosk.swayam.kioskmanagement.repository.CircleRepository;

@RestController
public class DownTimeController {
	
	Logger logger = LoggerFactory.getLogger(DownTimeController.class);
	@Autowired
	DowntimeService downtimeService;
	
	@Autowired
	CircleRepository circleRepo;
	//@Autowired
	//DowntimePagingRepository downtimePagingRepo;
	@Autowired
	UserRepository userRepo;

	@Autowired
	DowntimeReport downtimeReport;
	
	
	@RequestMapping(value = "hm/downtime")
	@PreAuthorize("hasPermission('HMdowntime','CREATE')")
	ModelAndView downtime(ModelAndView model) {
		
		model.setViewName("downtime");
		
		return model;

	}
	
	
	@RequestMapping(value = "hm/getBycircle", method = RequestMethod.GET)
	@PreAuthorize("hasPermission('HMdowntimeGetByCircle','CREATE')")
	public ResponseEntity<?> getCircles(){
		logger.info("circles ===================================================");
		//circleRepo.findAll();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List<Circle> circleList = circleRepo.findAll();
		 String json = gson.toJson(circleList);
	//	logger.info("circles "+ json);
		 
		
		return ResponseEntity.ok(json);
		
		
	}
	
	@RequestMapping(value = "hm/getCmsCmf", method = RequestMethod.GET)
	@PreAuthorize("hasPermission('HMdowntimeGetCmsCmf','CREATE')")
	public ResponseEntity<?> getCmsCmf(){
		logger.info("getCmsCmf ===================================================");
		//circleRepo.findAll();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List<User> cmsCmfList = userRepo.findAllCmfCmsUser();
		
		String json = gson.toJson(cmsCmfList);
	//	logger.info("cmsCmfList "+ json);
		 
		
		return ResponseEntity.ok(json);
		
		
	}
	

	
	@RequestMapping(value = "hm/downtime/get", params = { "page", "size","type","selectedCircelId"
			,"selectedVendorId" ,"selectedCmsCmfId","selectedFromDateId","selectedToDateId",
			"selectedBranchCodeId","selectedKioskId"}, method = RequestMethod.GET, produces = "application/json")
	
	@PreAuthorize("hasPermission('HMdowntimeFindPaginated','CREATE')")
	public Page<DownTimeDto> findPaginated( @RequestParam("type") String type,
		      @RequestParam("page") int page, @RequestParam("size") int size,
		      @RequestParam("selectedCircelId") String selectedCircelId
		      ,@RequestParam("selectedVendorId") String selectedVendorId
		      ,@RequestParam("selectedCmsCmfId") String selectedCmsCmfId
		      ,@RequestParam("selectedFromDateId") String selectedFromDateId
		      ,@RequestParam("selectedToDateId") String selectedToDateId
		      ,@RequestParam("selectedBranchCodeId") String selectedBranchCodeId
		      ,@RequestParam("selectedKioskId") String selectedKioskId) {
		
		 logger.info("downtime selectedCircelId:::"+selectedCircelId);
		 logger.info("downtime selectedVendorId:::"+selectedVendorId);
		 logger.info("downtime selectedCmsCmfId:::"+selectedCmsCmfId);
		 logger.info("downtime selectedFromDateId:::"+selectedFromDateId);
		 logger.info("downtime selectedToDateId:::"+selectedToDateId);
		 logger.info("downtime selectedBranchCodeId:::"+selectedBranchCodeId);
		 logger.info("downtime selectedKioskId:::"+selectedKioskId);
		 
		 downtimeReport.setCircle(selectedCircelId);
		 downtimeReport.setVendor(selectedVendorId);
		 downtimeReport.setCmsCmf(selectedCmsCmfId);
		 downtimeReport.setFromDate(selectedFromDateId);
		 downtimeReport.setToDate(selectedToDateId);
		 downtimeReport.setBranchCode(selectedBranchCodeId);
		 downtimeReport.setKioskId(selectedKioskId);
		 
		 
		 
	
		 
		
		 Page<DownTimeDto> resultPage = downtimeService.findAllPaginated(size, page,type,selectedCircelId,selectedVendorId
				 ,selectedCmsCmfId,selectedFromDateId,selectedToDateId,
				 selectedBranchCodeId,selectedKioskId);
		 logger.info("resultPage:::"+resultPage.getContent());
	     
		    
			
			return	resultPage; //ticketCentorService.findPaginatedCountByCircle(page, size);
	
	}

}
