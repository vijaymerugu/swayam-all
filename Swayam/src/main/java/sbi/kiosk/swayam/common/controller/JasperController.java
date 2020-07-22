package sbi.kiosk.swayam.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sbi.kiosk.swayam.common.service.JasperService;
import sbi.kiosk.swayam.healthmonitoring.controller.TicketCentorController;

@RestController
public class JasperController {
	
	Logger logger = LoggerFactory.getLogger(JasperController.class);
	
	@Autowired
	JasperService jasperService;
	
	@GetMapping("report")
	@PreAuthorize("hasPermission('JSPgenerateReport','CREATE')")
	public String generateReport(@RequestParam("page") String page, @RequestParam("type") String type) {
		logger.info("Inside==Jasper====generateReport===========page "+page);
		logger.info("Inside==Jasper====generateReport===========type "+type);
			if(type.equals("pdf")){
				return jasperService.generateReportPdf(page);
			}
			else if(type.equals("excel")){
				return jasperService.generateReportExcel(page);
			}
			else{
				return null;
			}	
	}

}
