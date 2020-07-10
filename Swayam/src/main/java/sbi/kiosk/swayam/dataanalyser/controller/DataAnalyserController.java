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
import sbi.kiosk.swayam.common.entity.CumulativeKiosksAvailability;
import sbi.kiosk.swayam.common.entity.CumulativeSummaryOfDownKiosks;
import sbi.kiosk.swayam.common.entity.ErrorTypeWiseCumulativeData;
import sbi.kiosk.swayam.common.entity.ErrorTypeWiseUpTime;
import sbi.kiosk.swayam.common.entity.SummaryOfDownKiosks;
import sbi.kiosk.swayam.common.entity.TATWiseCumulativeData;
import sbi.kiosk.swayam.common.entity.TATofDownKiosks;
import sbi.kiosk.swayam.common.entity.VendorWiseCumulativeData;
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
	public ResponseEntity<List<Availability>> getAvailability(ModelAndView model) {
		ResponseEntity<List<Availability>> respEntity = ResponseEntity.ok(dataAnalyserService.getAvailability());
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
	public ResponseEntity<List<TATofDownKiosks>> getTATofDownKiosks(ModelAndView model) {
		ResponseEntity<List<TATofDownKiosks>> respEntity = ResponseEntity.ok(dataAnalyserService.getTATofDownKiosks());
		model.addObject("daTATofDownKiosks", respEntity);
		model.setViewName("tATofDownKiosks");
		return respEntity;
	}
	
	@RequestMapping(value = "da/getSummaryOfDownKiosks", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<SummaryOfDownKiosks>> getSummaryOfDownKiosks(ModelAndView model) {
		ResponseEntity<List<SummaryOfDownKiosks>> respEntity = ResponseEntity.ok(dataAnalyserService.getSummaryOfDownKiosks());
		model.addObject("daSummaryOfDownKiosks", respEntity);
		model.setViewName("summaryOfDownKiosks");
		return respEntity;
	}
	
	@RequestMapping(value = "da/getCumulativeAvailability", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<CumulativeKiosksAvailability>> getCumulativeAvailability(ModelAndView model) {
		ResponseEntity<List<CumulativeKiosksAvailability>> respEntity = ResponseEntity.ok(dataAnalyserService.getCumulativeKiosksAvailability());
		model.addObject("daSummaryOfDownKiosks", respEntity);
		return respEntity;
	}
	
	@RequestMapping(value = "da/getVendorWiseCumulativeData", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<VendorWiseCumulativeData>> getVendorWiseCumulativeData(ModelAndView model) {
		ResponseEntity<List<VendorWiseCumulativeData>> respEntity = ResponseEntity.ok(dataAnalyserService.getVendorWiseCumulativeData());
		model.addObject("daVendorWiseUptimeData", respEntity);
		return respEntity;
	}
	
	@RequestMapping(value = "da/getErrorTypeWiseCumulativeData", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<ErrorTypeWiseCumulativeData>> getErrorTypeWiseCumulativeData(ModelAndView model) {
		ResponseEntity<List<ErrorTypeWiseCumulativeData>> respEntity = ResponseEntity.ok(dataAnalyserService.getErrorTypeWiseCumulativeData());
		model.addObject("daErrorTypeWiseCumulativeData", respEntity);
		return respEntity;
	}
	
	@RequestMapping(value = "da/getTATWiseCumulativeData", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<TATWiseCumulativeData>> getTATWiseCumulativeData(ModelAndView model) {
		ResponseEntity<List<TATWiseCumulativeData>> respEntity = ResponseEntity.ok(dataAnalyserService.getTATWiseCumulativeData());
		model.addObject("daTATWiseCumulativeData", respEntity);
		return respEntity;
	}
	
	@RequestMapping(value = "da/getCumulativeSummaryOfDownKiosks", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<CumulativeSummaryOfDownKiosks>> getCumulativeSummaryOfDownKiosks(ModelAndView model) {
		ResponseEntity<List<CumulativeSummaryOfDownKiosks>> respEntity = ResponseEntity.ok(dataAnalyserService.getCumulativeSummaryOfDownKiosks());
		model.addObject("daCumulativeSummaryOfDownKiosks", respEntity);
		return respEntity;
	}
	
}
