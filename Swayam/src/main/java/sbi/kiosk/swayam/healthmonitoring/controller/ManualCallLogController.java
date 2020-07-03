package sbi.kiosk.swayam.healthmonitoring.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.common.dto.CallTypeDto;
import sbi.kiosk.swayam.common.dto.ManualTicketCallLogDto;
import sbi.kiosk.swayam.common.exception.ManualTicketNotFoudException;
import sbi.kiosk.swayam.healthmonitoring.service.ManualTicketService;
import sbi.kiosk.swayam.kioskmanagement.controller.UserManagementController;

@RestController
@RequestMapping(value = "/", method = { RequestMethod.POST, RequestMethod.GET })
public class ManualCallLogController {
	
	Logger logger = LoggerFactory.getLogger(ManualCallLogController.class);

	@Autowired
	private ManualTicketService manualTicketService;

	@GetMapping("hm/manualTicket")
	public ModelAndView manualTicketCallLog(
			@ModelAttribute("manualTicketCallLogDto") ManualTicketCallLogDto manualTicketCallLogDto,
			ModelAndView model) {
		List<CallTypeDto> errorList = manualTicketService.getCallTypeCategoryError();
		model.addObject("errorList", errorList);
		model.setViewName("manualTicket");
		return model;
	}

	@PostMapping("manualTicketForm")
	public ResponseEntity<String> createManualForm(@ModelAttribute("manualTicketCallLogDto") ManualTicketCallLogDto manualTicketCallLogDto,
			                  ModelAndView model)	throws ManualTicketNotFoudException {
		
		logger.info("............inside create manual ticekt .....");
		String complaintId = manualTicketService.createManualTicket(manualTicketCallLogDto);
		

		SimpleDateFormat date = new SimpleDateFormat("dd/MM/YY");
		String date1 = date.format(new Date());
		String d = date1.replace("/", "");
		
		ResponseEntity<String> entiry = ResponseEntity.ok(d + "" + complaintId);
		return entiry;
	}

	@PostMapping("getByBranchCode/{brachCode}")
	public ResponseEntity<List<ManualTicketCallLogDto>> getByBranchCode(@PathVariable("brachCode") String brachCode,
			ModelAndView model, @ModelAttribute("manualTicketCallLogDto") ManualTicketCallLogDto manualTicketCallLogDto)
			throws JSONException {

		logger.info("calling for ajax with brach code :::");

		List<ManualTicketCallLogDto> mlist1 = manualTicketService.getByBranchCode(brachCode);

		
		ResponseEntity<List<ManualTicketCallLogDto>> entiry = ResponseEntity.ok(mlist1);
		return entiry;

	}

	@GetMapping("getBykioskId/{kioskId}")
	public ResponseEntity<List<ManualTicketCallLogDto>> getAllCirclesAgainstKioskID(
			@PathVariable("kioskId") String kioskId,
			@ModelAttribute("manualTicketCallLogDto") ManualTicketCallLogDto manualTicketCallLogDto,
			ModelAndView model) {
		logger.info("ajax call for assiging kioskId ");
		List<ManualTicketCallLogDto> ldto = manualTicketService.getByKioskId(kioskId);
		ResponseEntity<List<ManualTicketCallLogDto>> entiry = ResponseEntity.ok(ldto);
		return entiry;

	}

	@GetMapping("getByVendor/{vendor}/{branchcode}")
	public ResponseEntity<List<ManualTicketCallLogDto>> getAllCirclesAgainstVendor(
			@PathVariable("vendor") String vendor, @PathVariable("branchcode") String branchcode,
			@ModelAttribute("manualTicketCallLogDto") ManualTicketCallLogDto manualTicketCallLogDto,
			ModelAndView model) {
		logger.info("ajax call for assiging vendor ");
		List<ManualTicketCallLogDto> ldto = manualTicketService.getByVendor(vendor, branchcode);
		ResponseEntity<List<ManualTicketCallLogDto>> entiry = ResponseEntity.ok(ldto);
		return entiry;

	}

}
