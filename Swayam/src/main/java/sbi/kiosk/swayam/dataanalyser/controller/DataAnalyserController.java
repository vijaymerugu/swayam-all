/**
 * 
 */
package sbi.kiosk.swayam.dataanalyser.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.common.entity.Availability;
import sbi.kiosk.swayam.common.entity.ErrorTypeWiseUpTime;
import sbi.kiosk.swayam.common.entity.SummaryOfDownKiosks;
import sbi.kiosk.swayam.common.entity.TATofDownKiosks;
import sbi.kiosk.swayam.common.entity.VendorWiseUptime;
import sbi.kiosk.swayam.dataanalyser.service.DataAnalyserService;

/**
 * @author vmph2362595
 *
 */

@RestController
public class DataAnalyserController {

	@Autowired
	DataAnalyserService dataAnalyserService;
	
	@RequestMapping(value = "da/getAvailability", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Availability>> getAvailability(ModelAndView model, HttpServletRequest request) {
		ResponseEntity<List<Availability>> respEntity = ResponseEntity.ok(dataAnalyserService.getAvailability(request));
		model.addObject("daAvailabilityData", respEntity);
		model.setViewName("daAvailability");
		return respEntity;
	}
	
	@RequestMapping(value = "da/getVendorWiseUpTime", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<VendorWiseUptime>> getVendorWiseUpTime(ModelAndView model, HttpServletRequest request) {
		ResponseEntity<List<VendorWiseUptime>> respEntity = ResponseEntity.ok(dataAnalyserService.getVendorWiseUpTime(request));
		model.addObject("daVendorWiseUptimeData", respEntity);
		model.setViewName("vendorWiseUpTime");
		return respEntity;
	}
	
	@RequestMapping(value = "da/getErrorTypeWiseUpTime", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<ErrorTypeWiseUpTime>> getErrorTypeWiseUpTime(ModelAndView model, HttpServletRequest request) {
		ResponseEntity<List<ErrorTypeWiseUpTime>> respEntity = ResponseEntity.ok(dataAnalyserService.getErrorTypeWiseUpTime(request));
		model.addObject("daErrorTypeWiseUpTime", respEntity);
		model.setViewName("errorTypeWiseUpTime");
		return respEntity;
	}
	
	@RequestMapping(value = "da/getTATofDownKiosks", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<TATofDownKiosks>> getTATofDownKiosks(ModelAndView model, HttpServletRequest request) {
		ResponseEntity<List<TATofDownKiosks>> respEntity = ResponseEntity.ok(dataAnalyserService.getTATofDownKiosks(request));
		model.addObject("daTATofDownKiosks", respEntity);
		model.setViewName("tATofDownKiosks");
		return respEntity;
	}
	
	@RequestMapping(value = "da/getSummaryOfDownKiosks", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<SummaryOfDownKiosks>> getSummaryOfDownKiosks(ModelAndView model, HttpServletRequest request) {
		ResponseEntity<List<SummaryOfDownKiosks>> respEntity = ResponseEntity.ok(dataAnalyserService.getSummaryOfDownKiosks(request));
		model.addObject("daSummaryOfDownKiosks", respEntity);
		model.setViewName("summaryOfDownKiosks");
		return respEntity;
	}
	
}
