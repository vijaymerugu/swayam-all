package sbi.kiosk.swayam.healthmonitoring.controller;

import java.util.ArrayList;
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

import sbi.kiosk.swayam.common.dto.CallTypeDto;
import sbi.kiosk.swayam.common.dto.TicketHistoryDto;
import sbi.kiosk.swayam.common.entity.Circle;
import sbi.kiosk.swayam.healthmonitoring.model.DowntimeReport;
import sbi.kiosk.swayam.healthmonitoring.model.TicketHistoryReport;
import sbi.kiosk.swayam.healthmonitoring.repository.CallTypeRepository;
import sbi.kiosk.swayam.healthmonitoring.service.TicketHistoryService;
import sbi.kiosk.swayam.kioskmanagement.repository.CircleRepository;

@RestController
public class TiketHistoryController {
	Logger logger = LoggerFactory.getLogger(TiketHistoryController.class);
	@Autowired
	TicketHistoryService ticketHistoryService;
	@Autowired
	CircleRepository circleRepo;
	@Autowired
	CallTypeRepository callTypeRepo;
	@Autowired
	TicketHistoryReport ticketHistoryReport;

	@RequestMapping(value = "hm/ticketHistory")
	@PreAuthorize("hasPermission('HMticketHistory','CREATE')")
	ModelAndView tiketHistory(ModelAndView model) {
		
		model.setViewName("tickethistory");
		
		return model;

	}
	
	
	
	
	
	@RequestMapping(value = "hm/getcircle", method = RequestMethod.GET)
	@PreAuthorize("hasPermission('HMGetCircle','CREATE')")
	public ResponseEntity<?> getCircles(){
		logger.info("circles ===================================================");
		//circleRepo.findAll();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List<Circle> circleList = circleRepo.findAll();
		 String json = gson.toJson(circleList);
		logger.info("circles "+ json);
		 
		
		return ResponseEntity.ok(json);
		
		
	}
	
	@RequestMapping(value = "hm/getCategory", method = RequestMethod.GET)
	@PreAuthorize("hasPermission('HMGetCategory','CREATE')")
	public ResponseEntity<?> getCategory(){
		logger.info("CallType ===================================================");
		//circleRepo.findAll();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List<CallTypeDto> callTypeDtoList= new ArrayList<CallTypeDto>();
		
		CallTypeDto callTypeDto=null;
		
		List<String> callList = callTypeRepo.findCallType();
		for (String category : callList) {
			callTypeDto=new CallTypeDto();
			callTypeDto.setCategory(category);
			callTypeDtoList.add(callTypeDto);
		}
		List<String> callSubCategoryList = callTypeRepo.findCallTypeSubCategory();
		logger.info("callSubCategoryList "+ callSubCategoryList.toString());
		
		for (String subCategory : callSubCategoryList) {
			callTypeDto=new CallTypeDto();
			callTypeDto.setSubCategory(subCategory);
			callTypeDtoList.add(callTypeDto);
				}
		
		 String json = gson.toJson(callTypeDtoList);
		logger.info("CallType "+ json);
		return ResponseEntity.ok(json);
		
		
	}

	
	@RequestMapping(value = "hm/ticketHistory/get", params = { "page", "size","type","selectedKioskId",
			"selectedCallLogDateId","selectedCategoryId","selectedCircelId","selectedCallClosedDateId","selectedSubCategoryId",
			"selectedBranchCode","selectedVendorId"}, method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasPermission('HMTicketHistoryfindPaginatedUserGet','CREATE')")
	public Page<TicketHistoryDto> findPaginated( @RequestParam("type") String type,@RequestParam("page") int page, @RequestParam("size") int size,
			@RequestParam("selectedKioskId") String selectedKioskId,@RequestParam("selectedCallLogDateId") String selectedCallLogDateId, 
			@RequestParam("selectedCategoryId") String selectedCategoryId,@RequestParam("selectedCircelId") String selectedCircelId,
			@RequestParam("selectedCallClosedDateId") String selectedCallClosedDateId, @RequestParam("selectedSubCategoryId") String selectedSubCategoryId,
		    @RequestParam("selectedBranchCode") String selectedBranchCode, @RequestParam("selectedVendorId") String selectedVendorId) {
		 		
		
			 logger.info("selectedKioskId:::----------------"+selectedKioskId);
			 logger.info("selectedCallLogDateId---- "+selectedCallLogDateId);
			 logger.info("selectedCategoryId:::"+selectedCategoryId);
			 logger.info("selectedCircelId--- "+selectedCircelId);
			 logger.info("selectedCallClosedDateId---- "+selectedCallClosedDateId);
			 logger.info("selectedSubCategoryId--- "+selectedSubCategoryId);
			 logger.info("selectedBranchCode:::::-------------"+selectedBranchCode);
			 logger.info("selectedVendorId--- "+selectedVendorId);
			 
			 ticketHistoryReport.setKisokId(selectedKioskId);
			 ticketHistoryReport.setCall_log_date(selectedCallLogDateId);
			 ticketHistoryReport.setCallCategory(selectedCategoryId);
			 ticketHistoryReport.setCircle(selectedCircelId);
			 ticketHistoryReport.setCall_closed_date(selectedCallClosedDateId);
			 ticketHistoryReport.setCallSubCategory(selectedSubCategoryId);
			 ticketHistoryReport.setBranchCode(selectedBranchCode);
			 ticketHistoryReport.setVendor(selectedVendorId);
			 
			 
			 
			 
			 
			 
			 Page<TicketHistoryDto> resultPage = null;
		     resultPage = ticketHistoryService.findPaginatedByFilter(page, size, type,
		     selectedKioskId, selectedCallLogDateId,
		     selectedCategoryId,selectedCircelId,selectedCallClosedDateId,
		     selectedSubCategoryId,selectedBranchCode,selectedVendorId);
			
			    if (page > resultPage.getTotalPages()){
			            //throw new MyResourceNotFoundException();
			        }
		
		          logger.info("resultPage:::"+resultPage.getContent());
			
			return	resultPage; //ticketCentorService.findPaginatedCountByCircle(page, size);
			}
	
}
