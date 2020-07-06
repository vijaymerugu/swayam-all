/**
 * 
 */
package sbi.kiosk.swayam.dataanalyser.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.common.entity.Availability;
import sbi.kiosk.swayam.common.entity.ErrorTypeWiseUpTime;
import sbi.kiosk.swayam.common.entity.SummaryOfDownKiosks;
import sbi.kiosk.swayam.common.entity.TATofDownKiosks;
import sbi.kiosk.swayam.common.entity.VendorWiseUptime;
import sbi.kiosk.swayam.dataanalyser.repository.AvailabilityRepo;
import sbi.kiosk.swayam.dataanalyser.repository.ErrorTypeWiseUpTimeRepo;
import sbi.kiosk.swayam.dataanalyser.repository.SummaryOfDownKiosksRepo;
import sbi.kiosk.swayam.dataanalyser.repository.TATofDownKiosksRepo;
import sbi.kiosk.swayam.dataanalyser.repository.VendorWiseUpTimeRepo;

/**
 * @author vmph2362595
 *
 */
@Service
public class DataAnalyserServiceImpl implements DataAnalyserService {

	Logger logger = LoggerFactory.getLogger(DataAnalyserServiceImpl.class);
	
	@Autowired
	AvailabilityRepo availabilityRepo;
	
	@Autowired
	VendorWiseUpTimeRepo vendorWiseUpTimeRepo;
	
	@Autowired
	ErrorTypeWiseUpTimeRepo errorTypeWiseUpTimeRepo;
	
	@Autowired
	SummaryOfDownKiosksRepo summaryOfDownKiosksRepo;
	
	@Autowired
	TATofDownKiosksRepo tATofDownKiosksRepo;
	
	@Override
	public List<Availability> getAvailability(HttpServletRequest request) {
		try {
			return availabilityRepo.getAvailability(request.getParameter("circleCode"));
		} catch (Exception e) {
			logger.error("Exception in getAvailability." + e.getMessage());
			return new ArrayList<Availability>();
		}
	}

	@Override
	public List<VendorWiseUptime> getVendorWiseUpTime(HttpServletRequest request) {
		try {
			return vendorWiseUpTimeRepo.getVendorWiseUpTime(request.getParameter("vendor"));
		} catch (Exception e) {
			logger.error("Exception in getAvailability." + e.getMessage());
			return new ArrayList<VendorWiseUptime>();
		}
	}

	@Override
	public List<ErrorTypeWiseUpTime> getErrorTypeWiseUpTime(HttpServletRequest request) {
		try {
			return errorTypeWiseUpTimeRepo.getErrorTypeWiseUpTime(request.getParameter("callCategory"));
		} catch (Exception e) {
			logger.error("Exception in getAvailability." + e.getMessage());
			return new ArrayList<ErrorTypeWiseUpTime>();
		}
	}

	@Override
	public List<TATofDownKiosks> getTATofDownKiosks(HttpServletRequest request) {
		try {
			return tATofDownKiosksRepo.getTATofDownKiosks(request.getParameter("circleCode"));
		} catch (Exception e) {
			logger.error("Exception in getAvailability." + e.getMessage());
			return new ArrayList<TATofDownKiosks>();
		}
	}

	@Override
	public List<SummaryOfDownKiosks> getSummaryOfDownKiosks(HttpServletRequest request) {
		try {
			return summaryOfDownKiosksRepo.getSummaryOfDownKiosks(request.getParameter("circleCode"));
		} catch (Exception e) {
			logger.error("Exception in getAvailability." + e.getMessage());
			return new ArrayList<SummaryOfDownKiosks>();
		}
	}

}
