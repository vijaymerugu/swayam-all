package sbi.kiosk.swayam.billingpayment.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import sbi.kiosk.swayam.billingpayment.repository.AllocationRepository;
import sbi.kiosk.swayam.billingpayment.repository.BillingPurchaseOrderRepository;
import sbi.kiosk.swayam.billingpayment.repository.PurchaseOrderRepository;
import sbi.kiosk.swayam.common.constants.ExceptionConstants;
import sbi.kiosk.swayam.common.dto.TransactionDashBoardDto;
import sbi.kiosk.swayam.common.entity.Allocation;
import sbi.kiosk.swayam.common.entity.BillingPurchaseOrder;
import sbi.kiosk.swayam.common.entity.PurchaseOrder;
import sbi.kiosk.swayam.common.entity.RfpIdMaster;
import sbi.kiosk.swayam.healthmonitoring.model.InvoiceUpdateReposne;
import sbi.kiosk.swayam.healthmonitoring.model.RfpResponse;

@RestController
public class PurchaseOrderController {
	
	Logger logger = LoggerFactory.getLogger(PurchaseOrderController.class);
	
	@Autowired
	PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	AllocationRepository allocationRepository;
	
	@Autowired
	BillingPurchaseOrderRepository repo;
	
	@Value("${report.path}")
	private String reportPath;

	@Value("${jrxml.path}")
	private String jrxmlPath;
	
	@RequestMapping("bp/purchaseorder")
	public ModelAndView drillDownPage(ModelAndView model, HttpSession session) {
		
		try {
			
			model.setViewName("purchaseOrder");
			
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	
	
	
	
	@RequestMapping(value = "purchaseorder/get", params = { "page", "size" ,"type"}, method = RequestMethod.GET)
	public Page<PurchaseOrder> findPaginated(
		      @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("type") String type) {
		 Page<PurchaseOrder> resultPage = null;
		 
		 resultPage = purchaseOrderRepository.findPagePurchaseOrder(PageRequest.of(page, size));
			
		 return resultPage;
		    }
	

	
	@RequestMapping(value = "po/edit",params = { "AllocId", "PoNumber" ,"AllocatedQuantity", "PoQuantity", "RemainingQuantity"},
	method = RequestMethod.GET)
	@Transactional
	public ResponseEntity<?> updateCorrections(@RequestParam("AllocId")Integer allocId,
			@RequestParam("PoNumber") Integer poNumber
			, @RequestParam("AllocatedQuantity") Integer allocatedQuantity,
			@RequestParam("PoQuantity") Integer poQuantity,
			@RequestParam("RemainingQuantity") Integer remainingQuantity)
			throws Exception {
		
		logger.info("Inside updateCorrections");
		
		Optional<BillingPurchaseOrder> podetails=repo.findById(poNumber);
		Optional<Allocation> allocationdetails=allocationRepository.findById(allocId);
		Allocation allocation=allocationdetails.get();
		allocation.setRemainingQuantity(allocatedQuantity-poQuantity);
		BillingPurchaseOrder order =podetails.get();
		
		System.out.println("Pd oder date " + order.getPoDate());
		System.out.println("Pd Quantity " + order.getPoQantity());
		
		order.setPoQantity(poQuantity);
		order.setStatus("s");
		
		
		if (allocation.getRemainingQuantity()==0) {
			allocation.setStatus("0");
			
		}
		
		
		if(poQuantity>0) {
			
			repo.save(order);
			allocationRepository.save(allocation);  
			
			
			
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	
		
		 return new ResponseEntity<>(HttpStatus.OK);
		
		//return ResponseEntity.ok(new InvoiceUpdateReposne("Sucess", "Data Saved Successfully"));
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "po/generate",params = { "PoIdList"},
	method = RequestMethod.GET)
	@Transactional
	public ResponseEntity<?> geneartePO(@RequestParam("PoIdList")List poIdList)
			throws Exception {
		
		logger.info("Inside geneartePO " +poIdList);
		
		int lastPoNumber=0;
		try {
			lastPoNumber =repo.findLastPO();
			System.out.println("Last PO Number " + lastPoNumber);
		}catch (AopInvocationException e) {
			
			lastPoNumber=10000;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		//System.out.println("List " + lastPoNumber);
		logger.info("Last PO" + lastPoNumber);
		
	
		int poNumber = lastPoNumber+1;
		@SuppressWarnings("unchecked")
		int updateStatus = repo.updatePoNumber(poNumber, poIdList);
		System.out.println("updateStatus" + updateStatus);
		JasperReport jasperReport = null;
		JRBeanCollectionDataSource source = null;
		JasperPrint jasperPrint = null;
		String filename = null;
		jrxmlPath = jrxmlPath.replaceAll(">", "");
		reportPath = reportPath.replaceAll(">", "");
		
		List<PurchaseOrder> list =purchaseOrderRepository.findPoReport(poIdList);
		
		File file = ResourceUtils.getFile(jrxmlPath + "poGenerate.jrxml");
		InputStream input = new FileInputStream(file);
		jasperReport = JasperCompileManager.compileReport(input);
		source = new JRBeanCollectionDataSource(list);
		Map<String, Object> parameters = new HashMap<>();
		jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
		String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
		filename = "PO_"+poNumber+"_" + timeStamp + ".pdf";
		JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
		int status = repo.updatePoStatus(poIdList,new Date());
		logger.info("PO Generation Status " + status);
		
		
		 return new ResponseEntity<>(HttpStatus.OK);
		
		//return ResponseEntity.ok(new InvoiceUpdateReposne("Sucess", "Data Saved Successfully"));
	}
	
	
	

 

}
