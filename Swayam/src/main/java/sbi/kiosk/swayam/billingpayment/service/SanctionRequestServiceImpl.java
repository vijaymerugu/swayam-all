package sbi.kiosk.swayam.billingpayment.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.billingpayment.repository.BpRequestRepository;
import sbi.kiosk.swayam.billingpayment.repository.InvoiceCompareFormRepository;
import sbi.kiosk.swayam.billingpayment.repository.RfpdtlsRepository;
import sbi.kiosk.swayam.billingpayment.repository.SanctionFormRequestRepository;
import sbi.kiosk.swayam.billingpayment.repository.TaxDetailRepository;
import sbi.kiosk.swayam.common.entity.InvoiceCompareDtls;
import sbi.kiosk.swayam.common.entity.RfpDetails;
import sbi.kiosk.swayam.common.entity.SanctionRequestEntity;
import sbi.kiosk.swayam.common.entity.TaxDetailEnity;
import sbi.kiosk.swayam.healthmonitoring.service.TerminalStatusServiceImpl;

@Service
public class SanctionRequestServiceImpl implements SanctionRequsetService {

	Logger logger = LoggerFactory.getLogger(SanctionRequestServiceImpl.class);
	
	@Autowired
	BpRequestRepository bpRepo;
	
	@Autowired
	SanctionFormRequestRepository sanRepo;
	
	@Autowired
	InvoiceCompareFormRepository icFormRepo;
	
	@Autowired
	TaxDetailRepository taxFormRepo;
	
	@Autowired
	RfpdtlsRepository rfpFormRepo;
	
	@Override
	public Map<String, Integer> findAllCountRequestStatus(String circle) {
		Map<String, Integer> mapData = null;
		logger.info("Inside findAllCountRequestStatus" );
		mapData = new LinkedHashMap<String, Integer>();
		
		if(circle.equalsIgnoreCase("CORPORATE CENTRE")) {
			circle="";
			
		}
		
		
		int submittedStatusCount = bpRepo.findByStatus("Submitted",circle);
		int rejectedStatusCount = bpRepo.findByStatus("Rejected",circle);
		int approvedStatusCount =bpRepo.findByStatus("Approved",circle);
		
		int snTypeCount = bpRepo.findCountRequestType("Sanction Note",circle);
		int icTypeCount = bpRepo.findCountRequestType("Invoice Compare",circle);
		int taxTypeCount = bpRepo.findCountRequestType("Tax Request",circle);
		int rfpTypeCount = bpRepo.findCountRequestType("RFP Request",circle);
		/*
		 * logger.info("submittedStatusCount====" + submittedStatusCount);
		 * logger.info("rejectedStatusCount====" + rejectedStatusCount);
		 * logger.info("approvedStatusCount====" + approvedStatusCount);
		 */
		
		mapData.put("submitted", submittedStatusCount);
		mapData.put("rejected", rejectedStatusCount);
		mapData.put("approved", approvedStatusCount);
		mapData.put("snnote", snTypeCount);
		mapData.put("invoiceCompare", icTypeCount);
		mapData.put("taxRequest", taxTypeCount);
		mapData.put("rfpRequest", rfpTypeCount);
		
		return mapData;
	}

	@Override
	public Map<String, Object> findSRApprovalForm(Integer requestId) {
		logger.info("Inside findSRApprovalForm ");
		
		Map<String, Object> mapData = null;
		
		mapData = new LinkedHashMap<String, Object>();
		
		List<SanctionRequestEntity> sanctionlist = 
				sanRepo.findAllByRequestId(requestId);
		
		Iterator<SanctionRequestEntity> iterator= sanctionlist.iterator();
		
		SanctionRequestEntity sanctionRequestEntity =null;
		
		while (iterator.hasNext()) {
			sanctionRequestEntity = (SanctionRequestEntity) iterator.next();
		}
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
	
		String sanctionNoteDate = dateFormat.format(sanctionRequestEntity.getSanNoteDt()); 
		String receiptDate = dateFormat.format(sanctionRequestEntity.getReceiptDt()); 
		String invoiceDate = dateFormat.format(sanctionRequestEntity.getInvoiceDt()); 
		String toDate = dateFormat.format(sanctionRequestEntity.getInvTo());
		String fromDate = dateFormat.format(sanctionRequestEntity.getInvFr());
		
		String circularDate= dateFormat.format(sanctionRequestEntity.getCircularDate());
		
		
		
		mapData.put("requestId", sanctionRequestEntity.getRequestId());
		mapData.put("sanctionAuth", sanctionRequestEntity.getSanctionAuth());
		mapData.put("ctlrAuth", sanctionRequestEntity.getCtlrAuth());
		mapData.put("recomAuth", sanctionRequestEntity.getRecomAuth());
		mapData.put("circle", sanctionRequestEntity.getCircle());
		mapData.put("state", sanctionRequestEntity.getState());
		mapData.put("vendor", sanctionRequestEntity.getVendor());
		mapData.put("sanNoteNo", sanctionRequestEntity.getSanNoteNo());
		mapData.put("sanNoteDt", sanctionNoteDate);
		mapData.put("invoiceNo", sanctionRequestEntity.getInvoiceNo());
		mapData.put("invoiceDt", invoiceDate);
		mapData.put("invFr", fromDate);
		mapData.put("invTo", toDate);
		mapData.put("invoiceAmt", sanctionRequestEntity.getInvoiceAmt());
		mapData.put("receiptDt", receiptDate);
		mapData.put("gstType", sanctionRequestEntity.getGstType());
		mapData.put("igst", sanctionRequestEntity.getIgst());
		mapData.put("sgst", sanctionRequestEntity.getSgst());
		mapData.put("cgst", sanctionRequestEntity.getCgst());
		mapData.put("penaltyAmt", sanctionRequestEntity.getPenaltyAmt());
		mapData.put("gstInvoiceAmt", sanctionRequestEntity.getGstInvoiceAmt());
		mapData.put("gstPenaltyAmt", sanctionRequestEntity.getGstPenaltyAmt());
		mapData.put("tdsPct", sanctionRequestEntity.getTdsPct());
		mapData.put("tdsAmt", sanctionRequestEntity.getTdsAmt());
		mapData.put("gstTdsLimitAmt", sanctionRequestEntity.getGstTdsLimitAmt());
		mapData.put("gstTdsPer", sanctionRequestEntity.getGstTdsPer());
		mapData.put("gstTdsAmt", sanctionRequestEntity.getGstTdsAmt());
		//mapData.put("amtWords", sanctionRequestEntity.getAmtWords());
		mapData.put("manualEntry", sanctionRequestEntity.getManualEntry());
		mapData.put("creditEntry", sanctionRequestEntity.getCreditEntry());
		mapData.put("noOfKiosk", sanctionRequestEntity.getNoOfKiosk());
		mapData.put("quarter", sanctionRequestEntity.getQuarter());
		mapData.put("year", sanctionRequestEntity.getYear());
		mapData.put("sanLimit", sanctionRequestEntity.getSanLimit());
		mapData.put("circularNo", sanctionRequestEntity.getCircularNo());
		mapData.put("circularDate", circularDate);
		mapData.put("circularSlNo", sanctionRequestEntity.getCircularSlNo());
		
		
		//logger.info("mapData===" + mapData);
		
		
		
		
		return mapData;
	}

	@Override
	public Map<String, Object> findICApprovalForm(Integer requestId) {
		logger.info("Inside findICApprovalForm ");
		
		Map<String, Object> mapData = null;
		
		mapData = new LinkedHashMap<String, Object>();
		
		List<InvoiceCompareDtls> sanctionlist = icFormRepo.findAllByRequestId(requestId);
				//sanRepo.findAllByRequestId(requestId);
		
		Iterator<InvoiceCompareDtls> iterator= sanctionlist.iterator();
		
		InvoiceCompareDtls requestEntity =null;
		
		while (iterator.hasNext()) {
			requestEntity = (InvoiceCompareDtls) iterator.next();
		}
		
		Double currentFinalAmount = requestEntity.getInvoiceAmountSBI()+requestEntity.getAmcSpareParts()
		- requestEntity.getPenaltyAmountSBI();
	
		System.out.println("Last correction " + requestEntity.getLastcorrectionAmount());
		System.out.println("New correctioncorrection " + requestEntity.getCorrectionAmount());
		
		Double newUpdatefinalAmount = 
				(requestEntity.getInvoiceAmountSBI()+requestEntity.getAmcSpareParts())
		- (requestEntity.getPenaltyAmountSBI()+requestEntity.getLastcorrectionAmount())+requestEntity.getCorrectionAmount();
	
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
	
		//String sanctionNoteDate = dateFormat.format(sanctionRequestEntity.getSanNoteDt()); 
		
		mapData.put("requestId", requestEntity.getRequestId());
		mapData.put("kisokId", requestEntity.getKisokId());
		mapData.put("vendor", requestEntity.getVendor());
		mapData.put("circleName", requestEntity.getCircleName());
		
		mapData.put("state", requestEntity.getState());
		mapData.put("kioskSerialNumber", requestEntity.getKioskSerialNumber());
		mapData.put("year", requestEntity.getYear());
		mapData.put("quarterId", requestEntity.getQuarterId());
		mapData.put("rpfRefNumber", requestEntity.getRpfRefNumber());
		
		mapData.put("invoiceAmountSBI", requestEntity.getInvoiceAmountSBI());
		mapData.put("invoiceAmountVendor", requestEntity.getInvoiceAmountVendor());
		mapData.put("penaltyAmountSBI", requestEntity.getPenaltyAmountSBI());
		mapData.put("correctionAmount", requestEntity.getCorrectionAmount());
		mapData.put("lastcorrectionAmount", requestEntity.getLastcorrectionAmount());
		
		mapData.put("penaltyAmountVendor", requestEntity.getPenaltyAmountVendor());
		mapData.put("difference", requestEntity.getDifference());
		mapData.put("currentFinalAmount", currentFinalAmount);
		mapData.put("remarks", requestEntity.getRemarks());
		mapData.put("newUpdatefinalAmount", newUpdatefinalAmount);
		
		
		
		
		
		
		//logger.info("mapData===" + mapData);
		
		
		
		
		return mapData;
	}
	
	
	@Override
	public Map<String, Object> findTaxApprovalForm(Integer requestId) {
		logger.info("Inside findTaxApprovalForm ");
		
		Map<String, Object> mapData = null;
		
		mapData = new LinkedHashMap<String, Object>();
		
		List<TaxDetailEnity> sanctionlist = taxFormRepo.findAllByRequestId(requestId);
				//sanRepo.findAllByRequestId(requestId);
		
		Iterator<TaxDetailEnity> iterator= sanctionlist.iterator();
		
		TaxDetailEnity requestEntity =null;
		
		while (iterator.hasNext()) {
			requestEntity = (TaxDetailEnity) iterator.next();
		}
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
	
		//String sanctionNoteDate = dateFormat.format(sanctionRequestEntity.getSanNoteDt()); 
		
		
		
		
		mapData.put("requestId", requestEntity.getRequestId());
		mapData.put("circleName", requestEntity.getCircleName());
		mapData.put("vendor", requestEntity.getVendor());
		mapData.put("state", requestEntity.getState());
		mapData.put("finyear", requestEntity.getFinyear());
		mapData.put("quarter", requestEntity.getQuarter());
		mapData.put("rfpno", requestEntity.getRfpRefNumber());
		mapData.put("gstType", requestEntity.getGstType());
		mapData.put("gst", requestEntity.getGst());
		mapData.put("sgst", requestEntity.getSgst());
		mapData.put("cgst", requestEntity.getCgst());
		mapData.put("invoiceAmt", requestEntity.getInvoiceAmount());
		mapData.put("penaltyAmt", requestEntity.getPenaltyAmount());
		mapData.put("amcGst", requestEntity.getAmcGst());
		mapData.put("penGst", requestEntity.getPenGst());
		mapData.put("totalGst", requestEntity.getToatalGST());
		
		
		//logger.info("mapData===" + mapData);
		
		return mapData;
	}

	@Override
	public Map<String, Object> findRFPApprovalForm(Integer requestId) {
		logger.info("Inside findRFPApprovalForm ");
		
		Map<String, Object> mapData = null;
		
		mapData = new LinkedHashMap<String, Object>();
		
		List<RfpDetails> sanctionlist = rfpFormRepo.findAllByRequestId(requestId);
				//sanRepo.findAllByRequestId(requestId);
		
		Iterator<RfpDetails> iterator= sanctionlist.iterator();
		
		RfpDetails requestEntity =null;
		
		while (iterator.hasNext()) {
			requestEntity = (RfpDetails) iterator.next();
		}
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
	
		String rfpDate = dateFormat.format(requestEntity.getRfpDate()); 
		String amcStartDate = dateFormat.format(requestEntity.getAmcStartDate()); 
		String slaStartDate = dateFormat.format(requestEntity.getSlaStartDate()); 
		String slaExpiryDate = dateFormat.format(requestEntity.getSlaExpiryDate()); 
		
		mapData.put("requestId", requestEntity.getRequestId());
		mapData.put("rfpId", requestEntity.getRfpId());
		mapData.put("rfpNo", requestEntity.getRfpNo());
		mapData.put("vendor", requestEntity.getVendor());
		mapData.put("kisokCost", requestEntity.getKisokCost());
		mapData.put("amcCost", requestEntity.getAmcCost());
		mapData.put("companyPenaltyHour", requestEntity.getCompanyPenaltyHour());
		mapData.put("companyPermDntmMuHrs", requestEntity.getCompanyPermDntmMuHrs());
		mapData.put("companyPermDntmSrHrs", requestEntity.getCompanyPermDntmSrHrs());
		mapData.put("companyPermDntmPct", requestEntity.getCompanyPermDntmPct());

		mapData.put("maxPenaltyPct", requestEntity.getMaxPenaltyPct());
		mapData.put("rfpDate", rfpDate);
		mapData.put("amcStartDate", amcStartDate);
		mapData.put("slaStartDate", slaStartDate);
		mapData.put("slaExpiryDate", slaExpiryDate);
		mapData.put("periodicity", requestEntity.getPeriodicity());
		mapData.put("penaltyType", requestEntity.getPenaltyType());
		mapData.put("penaltyToRange", requestEntity.getPenaltyToRange());
		mapData.put("penaltyfromRange", requestEntity.getPenaltyfromRange());
		mapData.put("reqOps", requestEntity.getReqOps());
		
		return mapData;
	}

}
