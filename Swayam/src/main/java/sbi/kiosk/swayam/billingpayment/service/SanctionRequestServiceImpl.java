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
import sbi.kiosk.swayam.billingpayment.repository.SanctionFormRequestRepository;
import sbi.kiosk.swayam.common.entity.SanctionRequestEntity;
import sbi.kiosk.swayam.healthmonitoring.service.TerminalStatusServiceImpl;

@Service
public class SanctionRequestServiceImpl implements SanctionRequsetService {

	Logger logger = LoggerFactory.getLogger(SanctionRequestServiceImpl.class);
	
	@Autowired
	BpRequestRepository bpRepo;
	
	@Autowired
	SanctionFormRequestRepository sanRepo;
	
	@Override
	public Map<String, Integer> findAllCountRequestStatus() {
		Map<String, Integer> mapData = null;
		logger.info("Inside findAllCountRequestStatus" );
		mapData = new LinkedHashMap<String, Integer>();
		
		int submittedStatusCount = bpRepo.findByStatus("Submitted");
		int rejectedStatusCount = bpRepo.findByStatus("Rejected");
		int approvedStatusCount =bpRepo.findByStatus("Approved");
		
		int snTypeCount = bpRepo.findCountRequestType("Sanction Note");
		/*
		 * logger.info("submittedStatusCount====" + submittedStatusCount);
		 * logger.info("rejectedStatusCount====" + rejectedStatusCount);
		 * logger.info("approvedStatusCount====" + approvedStatusCount);
		 */
		
		mapData.put("submitted", submittedStatusCount);
		mapData.put("rejected", rejectedStatusCount);
		mapData.put("approved", approvedStatusCount);
		mapData.put("snnote", snTypeCount);
		
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

}
