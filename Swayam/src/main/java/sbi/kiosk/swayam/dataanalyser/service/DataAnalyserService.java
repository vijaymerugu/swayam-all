/**
 * 
 */
package sbi.kiosk.swayam.dataanalyser.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import sbi.kiosk.swayam.common.entity.Availability;
import sbi.kiosk.swayam.common.entity.CumulativeKiosksAvailability;
import sbi.kiosk.swayam.common.entity.CumulativeSummaryOfDownKiosks;
import sbi.kiosk.swayam.common.entity.ErrorTypeWiseCumulativeData;
import sbi.kiosk.swayam.common.entity.ErrorTypeWiseUpTime;
import sbi.kiosk.swayam.common.entity.SummaryOfDownKiosks;
import sbi.kiosk.swayam.common.entity.TATWiseCumulativeData;
import sbi.kiosk.swayam.common.entity.TATofDownKiosks;
import sbi.kiosk.swayam.common.entity.UserWiseKiosksData;
import sbi.kiosk.swayam.common.entity.VendorWiseCumulativeData;
import sbi.kiosk.swayam.common.entity.VendorWiseUptime;

/**
 * @author vmph2362595
 *
 */
public interface DataAnalyserService {

	List<Availability> getAvailability();

	List<VendorWiseUptime> getVendorWiseUpTime(HttpServletRequest request);

	List<ErrorTypeWiseUpTime> getErrorTypeWiseUpTime(HttpServletRequest request);

	List<TATofDownKiosks> getTATofDownKiosks();

	List<SummaryOfDownKiosks> getSummaryOfDownKiosks();

	List<CumulativeKiosksAvailability> getCumulativeKiosksAvailability();

	List<VendorWiseCumulativeData> getVendorWiseCumulativeData();

	List<ErrorTypeWiseCumulativeData> getErrorTypeWiseCumulativeData();

	List<TATWiseCumulativeData> getTATWiseCumulativeData();

	List<CumulativeSummaryOfDownKiosks> getCumulativeSummaryOfDownKiosks();

	List<CumulativeKiosksAvailability> getCumulativeCircleAvailability(HttpServletRequest request);

	List<VendorWiseCumulativeData> getVendorWiseCumulativeCircleData(HttpServletRequest request);

	List<ErrorTypeWiseCumulativeData> getErrorTypeWiseCumulativeCircleData(HttpServletRequest request);

	List<TATWiseCumulativeData> getTATWiseCumulativeCircleData(HttpServletRequest request);

	List<CumulativeSummaryOfDownKiosks> getCumulativeCircleSummaryOfDownKiosks(HttpServletRequest request);

	List<UserWiseKiosksData> getUserWiseDownKiosksData(HttpServletRequest request);

	List<UserWiseKiosksData> getUserWiseZeroTxnKiosksData(HttpServletRequest request);

}
