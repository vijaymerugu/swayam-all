package sbi.kiosk.swayam.billingpayment.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.billingpayment.repository.TaxSummaryRepository;
import sbi.kiosk.swayam.common.dto.InvoiceSummaryDto;
import sbi.kiosk.swayam.common.entity.TaxCalculationEntity;
import sbi.kiosk.swayam.common.entity.TaxCalculationEntity2;
import sbi.kiosk.swayam.common.entity.TaxSummaryEntity;
import sbi.kiosk.swayam.healthmonitoring.model.BillingPaymentReport;

@Service
public class TaxServiceImpl implements TaxService {
	
	Logger logger = LoggerFactory.getLogger(TaxServiceImpl.class);
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	TaxSummaryRepository taxSummaryRepository;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TaxCalculationEntity> getTaxCalculation(BillingPaymentReport report,
			int criteria,String gstType) {
		try {
			
			logger.info("Inside getTaxCalculation with  criteria " +  criteria);
			logger.info("Inside getTaxCalculation with  circle " +  report.getCircle());
			logger.info("Inside getTaxCalculation with  state " +  report.getState());
			
			logger.info("Inside getTaxCalculation with  gstType-- " +  gstType);
			List<TaxCalculationEntity> calculationlist =null;
			if(gstType.equals("IGST")) {
				
				calculationlist = 
						entityManager.createNamedStoredProcedureQuery("SP_TAX_CAL")
									.setParameter("CRITERIA", criteria)
									.setParameter("GSTTYPE", gstType)
									.setParameter("CIRCLECODE", Integer.parseInt(report.getCircle()))
									.setParameter("STATDCODE", Integer.parseInt(report.getState()))
									.setParameter("RFPNO", report.getRpfNumber())
									.setParameter("VENDORID",Integer.parseInt(report.getVendor()))
									.setParameter("FINYEAR", report.getYear())
									.setParameter("QTRID", report.getQuarter())
									.setParameter("GST", report.getGst())
									.getResultList();
				
			}else if(gstType.equals("CGST"))  {
				calculationlist = 
						entityManager.createNamedStoredProcedureQuery("SP_TAX_CAL")
									.setParameter("CRITERIA", criteria)
									.setParameter("GSTTYPE", gstType)
									.setParameter("CIRCLECODE", Integer.parseInt(report.getCircle()))
									.setParameter("STATDCODE", Integer.parseInt(report.getState()))
									.setParameter("RFPNO", report.getRpfNumber())
									.setParameter("VENDORID",Integer.parseInt(report.getVendor()))
									.setParameter("FINYEAR", report.getYear())
									.setParameter("QTRID", report.getQuarter())
									.setParameter("GST", report.getCgst())
									.getResultList();
				
				
			}
			
			
			
			gstType=null;
			 return calculationlist;
		} catch (Exception e) {
			logger.error("Exception in getAvailability." + e);
			return new ArrayList<TaxCalculationEntity>();
		}		//return null;
	}

//	@Override
//	public List<TaxCalculationEntity> getTaxCalculation(BillingPaymentReport report,int criteria) {
//		try {
//			
//			logger.info("Inside getTaxCalculation with  criteria " +  criteria);
//			logger.info("Inside getTaxCalculation with  circle " +  report.getCircle());
//			logger.info("Inside getTaxCalculation with  state " +  report.getState());
//			@SuppressWarnings("unchecked")
//			List<TaxCalculationEntity> calculationlist = 
//			entityManager.createNamedStoredProcedureQuery("SP_TAX_CALCUALTIONS")
//						.setParameter("CRITERIA", criteria)
//						.setParameter("GSTTYPE", report.getGstType())
//						.setParameter("CIRCLECODE", Integer.parseInt(report.getCircle()))
//						.setParameter("STATDCODE", Integer.parseInt(report.getState()))
//						.setParameter("RFPNO", report.getRpfNumber())
//						.setParameter("VENDORID",Integer.parseInt(report.getVendor()))
//						.setParameter("FINYEAR", report.getYear())
//						.setParameter("QTRID", report.getQuarter())
//						.setParameter("GST", report.getGst())
//						.getResultList();
//			 return calculationlist;
//		} catch (Exception e) {
//			logger.error("Exception in getAvailability." + e);
//			return new ArrayList<TaxCalculationEntity>();
//		}		//return null;
//	}

	@Override
	public Page<TaxSummaryEntity> findPageByFilterIs(int page, int size, String type, String selectedCircelId,
			String selectedStateId, String quterTimePeriod) {
		
		Page<TaxSummaryEntity> entity = taxSummaryRepository.
				findbyFilter(selectedCircelId, selectedStateId,
						quterTimePeriod, PageRequest.of(page, size));
		
		return entity;
	}

	@Override
	public Page<TaxSummaryEntity> findPageWithoutStateIs(int page, int size, String type, String selectedCircelId,
			String quterTimePeriod) {
		
		 Page<TaxSummaryEntity> entity =null;
			
			if(selectedCircelId.equals("0")) {
				entity = taxSummaryRepository.findCCFilter
					  	(quterTimePeriod, PageRequest.of(page, size));
			}else {
				
			
			  entity = taxSummaryRepository.findbyWithoutStateFilter
					  	(selectedCircelId, quterTimePeriod, PageRequest.of(page, size));
			 
			}
		
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TaxCalculationEntity2> getTaxSGST(BillingPaymentReport report, int criteria, String gstType) {
		try {
			
			logger.info("Inside getTaxCalculation with  criteria " +  criteria);
			logger.info("Inside getTaxCalculation with  circle " +  report.getCircle());
			logger.info("Inside getTaxCalculation with  state " +  report.getState());
			
			logger.info("Inside getTaxCalculation with  gstType-- " +  gstType);
			List<TaxCalculationEntity2> calculationlist =null;
			if(gstType.equals("SGST")) {
				
				calculationlist = 
						entityManager.createNamedStoredProcedureQuery("SP_TAX_CAL1")
									.setParameter("CRITERIA", criteria)
									.setParameter("GSTTYPE", "SGST")
									.setParameter("CIRCLECODE", Integer.parseInt(report.getCircle()))
									.setParameter("STATDCODE", Integer.parseInt(report.getState()))
									.setParameter("RFPNO", report.getRpfNumber())
									.setParameter("VENDORID",Integer.parseInt(report.getVendor()))
									.setParameter("FINYEAR", report.getYear())
									.setParameter("QTRID", report.getQuarter())
									.setParameter("GST", report.getSgst())
									.getResultList();
				
			} 
			gstType=null;
			 return calculationlist;
		} catch (Exception e) {
			logger.error("Exception in getAvailability." + e);
			return new ArrayList<TaxCalculationEntity2>();
		}	
	}



}
