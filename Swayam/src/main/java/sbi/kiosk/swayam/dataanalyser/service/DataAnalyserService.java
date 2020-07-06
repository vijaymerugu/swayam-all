/**
 * 
 */
package sbi.kiosk.swayam.dataanalyser.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import sbi.kiosk.swayam.common.entity.Availability;
import sbi.kiosk.swayam.common.entity.ErrorTypeWiseUpTime;
import sbi.kiosk.swayam.common.entity.SummaryOfDownKiosks;
import sbi.kiosk.swayam.common.entity.TATofDownKiosks;
import sbi.kiosk.swayam.common.entity.VendorWiseUptime;

/**
 * @author vmph2362595
 *
 */
public interface DataAnalyserService {

	List<Availability> getAvailability(HttpServletRequest request);

	List<VendorWiseUptime> getVendorWiseUpTime(HttpServletRequest request);

	List<ErrorTypeWiseUpTime> getErrorTypeWiseUpTime(HttpServletRequest request);

	List<TATofDownKiosks> getTATofDownKiosks(HttpServletRequest request);

	List<SummaryOfDownKiosks> getSummaryOfDownKiosks(HttpServletRequest request);

}
