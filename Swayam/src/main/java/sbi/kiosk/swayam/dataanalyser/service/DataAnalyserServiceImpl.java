/**
 * 
 */
package sbi.kiosk.swayam.dataanalyser.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
@Service
public class DataAnalyserServiceImpl implements DataAnalyserService {

	Logger logger = LoggerFactory.getLogger(DataAnalyserServiceImpl.class);
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Availability> getAvailability() {
		try {
			 StoredProcedureQuery nearByEntities= entityManager.createNamedStoredProcedureQuery("SP_KIOSKS_AVAILABLITY_PROC");
	         return nearByEntities.getResultList();
		} catch (Exception e) {
			logger.error("Exception in getAvailability." + e.getMessage());
			return new ArrayList<Availability>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VendorWiseUptime> getVendorWiseUpTime(HttpServletRequest request) {
		try {
			return entityManager.createNamedStoredProcedureQuery("SP_VENDOR_WISE_UPTIME_PROC")
			.setParameter("vendor", request.getParameter("vendor")).getResultList();
		} catch (Exception e) {
			logger.error("Exception in getVendorWiseUpTime." + e.getMessage());
			return new ArrayList<VendorWiseUptime>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ErrorTypeWiseUpTime> getErrorTypeWiseUpTime(HttpServletRequest request) {
		try {
			StoredProcedureQuery nearByEntities= entityManager.createNamedStoredProcedureQuery("SP_ERROT_TYPE_WISE_UPTIME_PROC")
			.setParameter("callCategory", request.getParameter("callCategory"));
	        return nearByEntities.getResultList();
		} catch (Exception e) {
			logger.error("Exception in getErrorTypeWiseUpTime." + e.getMessage());
			return new ArrayList<ErrorTypeWiseUpTime>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TATofDownKiosks> getTATofDownKiosks() {
		try {
			return entityManager.createNamedStoredProcedureQuery("SP_TAT_OF_DOWN_KIOSKS_PROC").getResultList();
		} catch (Exception e) {
			logger.error("Exception in getTATofDownKiosks." + e.getMessage());
			return new ArrayList<TATofDownKiosks>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SummaryOfDownKiosks> getSummaryOfDownKiosks() {
		try {
			return entityManager.createNamedStoredProcedureQuery("SP_SUMMARY_OF_DOWN_KIOSKS_PROC").getResultList();
		} catch (Exception e) {
			logger.error("Exception in getSummaryOfDownKiosks." + e.getMessage());
			return new ArrayList<SummaryOfDownKiosks>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CumulativeKiosksAvailability> getCumulativeKiosksAvailability() {
		try {
			return entityManager.createNamedStoredProcedureQuery("SP_CUMULATIVE_AVAILABLITY_PROC").getResultList();
		} catch (Exception e) {
			logger.error("Exception in getCumulativeKiosksAvailability." + e.getMessage());
			return new ArrayList<CumulativeKiosksAvailability>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VendorWiseCumulativeData> getVendorWiseCumulativeData() {
		try {
			return entityManager.createNamedStoredProcedureQuery("SP_VENDOR_WISE_CUMULATIVE_DATA").getResultList();
		} catch (Exception e) {
			logger.error("Exception in getVendorWiseCumulativeData." + e.getMessage());
			return new ArrayList<VendorWiseCumulativeData>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ErrorTypeWiseCumulativeData> getErrorTypeWiseCumulativeData() {
		try {
			return entityManager.createNamedStoredProcedureQuery("SP_CUMULATIVE_ERRORTYPE_UPTIME").getResultList();
		} catch (Exception e) {
			logger.error("Exception in getErrorTypeWiseCumulativeData." + e.getMessage());
			return new ArrayList<ErrorTypeWiseCumulativeData>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TATWiseCumulativeData> getTATWiseCumulativeData() {
		try {
			return entityManager.createNamedStoredProcedureQuery("SP_TAT_WISE_CUMULATIVE_DATA").getResultList();
		} catch (Exception e) {
			logger.error("Exception in getTATWiseCumulativeData." + e.getMessage());
			return new ArrayList<TATWiseCumulativeData>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CumulativeSummaryOfDownKiosks> getCumulativeSummaryOfDownKiosks() {
		try {
			return entityManager.createNamedStoredProcedureQuery("SP_CUMULATIVE_KIOSKS_SUMMARY").getResultList();
		} catch (Exception e) {
			logger.error("Exception in getCumulativeSummaryOfDownKiosks." + e.getMessage());
			return new ArrayList<CumulativeSummaryOfDownKiosks>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CumulativeKiosksAvailability> getCumulativeCircleAvailability(HttpServletRequest request) {
		try {
			return entityManager.createNamedStoredProcedureQuery("SP_CUM_CIRCLE_AVAILABLITY")
					.setParameter("userId", request.getParameter("userId")).getResultList();
		} catch (Exception e) {
			logger.error("Exception in getCumulativeCircleAvailability." + e.getMessage());
			return new ArrayList<CumulativeKiosksAvailability>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VendorWiseCumulativeData> getVendorWiseCumulativeCircleData(HttpServletRequest request) {
		try {
			return entityManager.createNamedStoredProcedureQuery("SP_CUM_CIRCLE_VENDOR_DATA")
					.setParameter("userId", request.getParameter("userId")).getResultList();
		} catch (Exception e) {
			logger.error("Exception in getVendorWiseCumulativeCircleData." + e.getMessage());
			return new ArrayList<VendorWiseCumulativeData>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ErrorTypeWiseCumulativeData> getErrorTypeWiseCumulativeCircleData(HttpServletRequest request) {
		try {
			return entityManager.createNamedStoredProcedureQuery("SP_CUM_CIRCLE_ERRORTYPE_UPTIME")
					.setParameter("userId", request.getParameter("userId")).getResultList();
		} catch (Exception e) {
			logger.error("Exception in getErrorTypeWiseCumulativeCircleData." + e.getMessage());
			return new ArrayList<ErrorTypeWiseCumulativeData>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TATWiseCumulativeData> getTATWiseCumulativeCircleData(HttpServletRequest request) {
		try {
			return entityManager.createNamedStoredProcedureQuery("SP_CUM_CIRCLE_TAT_WISE")
					.setParameter("userId", request.getParameter("userId")).getResultList();
		} catch (Exception e) {
			logger.error("Exception in getTATWiseCumulativeCircleData." + e.getMessage());
			return new ArrayList<TATWiseCumulativeData>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CumulativeSummaryOfDownKiosks> getCumulativeCircleSummaryOfDownKiosks(HttpServletRequest request) {
		try {
			return entityManager.createNamedStoredProcedureQuery("SP_CUM_CIRCLE_KIOSKS_SUMMARY")
					.setParameter("userId", request.getParameter("userId")).getResultList();
		} catch (Exception e) {
			logger.error("Exception in getCumulativeCircleSummaryOfDownKiosks." + e.getMessage());
			return new ArrayList<CumulativeSummaryOfDownKiosks>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserWiseKiosksData> getUserWiseDownKiosksData(HttpServletRequest request) {
		try {
			return entityManager.createNamedStoredProcedureQuery("SP_USER_WISE_DOWN_KIOSK")
					.setParameter("userId", request.getParameter("userId")).getResultList();
		} catch (Exception e) {
			logger.error("Exception in getUserWiseDownKiosksData." + e.getMessage());
			return new ArrayList<UserWiseKiosksData>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserWiseKiosksData> getUserWiseZeroTxnKiosksData(HttpServletRequest request) {
		try {
			return entityManager.createNamedStoredProcedureQuery("SP_USER_WISE_ZERO_TXN_KIOSK")
					.setParameter("userId", request.getParameter("userId")).getResultList();
		} catch (Exception e) {
			logger.error("Exception in getUserWiseZeroTxnKiosksData." + e.getMessage());
			return new ArrayList<UserWiseKiosksData>();
		}
	}
	
}
