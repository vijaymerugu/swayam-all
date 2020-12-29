package sbi.kiosk.swayam.billingpayment.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;

import sbi.kiosk.swayam.common.dto.InvoiceSummaryDto;
import sbi.kiosk.swayam.common.entity.TaxCalculationEntity;
import sbi.kiosk.swayam.common.entity.TaxCalculationEntity2;
import sbi.kiosk.swayam.common.entity.TaxSummaryEntity;
import sbi.kiosk.swayam.healthmonitoring.model.BillingPaymentReport;


public interface TaxService {
	
	


	//List<TaxCalculationEntity> getTaxCalculation(BillingPaymentReport report, int criteria);
	
	List<TaxCalculationEntity> getTaxCalculation(BillingPaymentReport report, int criteria, String gstType);
	
	List<TaxCalculationEntity2> getTaxSGST(BillingPaymentReport report, int criteria, String gstType);
	
	Page<TaxSummaryEntity> findPageByFilterIs(int page, int size, String type,
		      String selectedCircelId, String selectedStateId, String quterTimePeriod);
	
	
	Page<TaxSummaryEntity> findPageWithoutStateIs(int page, int size, String type,
		      String selectedCircelId, String quterTimePeriod);

}
