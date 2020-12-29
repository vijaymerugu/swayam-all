package sbi.kiosk.swayam.billingpayment.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.billingpayment.repository.TaxCalInsertRepository;
import sbi.kiosk.swayam.billingpayment.repository.TaxRepository;
import sbi.kiosk.swayam.billingpayment.service.TaxService;
import sbi.kiosk.swayam.common.constants.ExceptionConstants;
import sbi.kiosk.swayam.common.dto.TaxCalculationDto;
import sbi.kiosk.swayam.common.entity.TaxEntity;
import sbi.kiosk.swayam.common.entity.TaxSummaryEntity;
import sbi.kiosk.swayam.common.utils.ObjectMapperUtils;
import sbi.kiosk.swayam.healthmonitoring.model.BillingPaymentReport;


@RestController
public class TaxController {
	
	
	Logger logger =LoggerFactory.getLogger(TaxController.class);
	
	@Autowired
	BillingPaymentReport report;
	
	@Autowired
	TaxService taxService;
	
//	@Autowired
//	TaxService taxService2;
	
	@Autowired
	TaxRepository taxRepo;
	
	@Autowired
	TaxCalInsertRepository insertRepo;
	
	@RequestMapping("bp/taxCal")
	public ModelAndView taxCalculationPage(ModelAndView model, HttpSession session) {
		
		try {
			
			
			model.setViewName("taxCalculation");
			
		} catch (Exception e) {			
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	
	
	
	@RequestMapping("bp/taxSum")
	public ModelAndView taxSummaryPage(ModelAndView model, HttpSession session) {
		
		try {
			
			
			model.setViewName("taxSummary");
			
		} catch (Exception e) {			
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	
	@RequestMapping("tax/insert")
	public int taxInsert() {
		//int status = 
		return 1;
	}
	
	
	@RequestMapping(value = "taxCalculation/get", params = { "page", "size", "type", "selectedCircelId",
			"selectedStateId", "selectedQuarterId", "selectedYearId", "selectedVendorId", "selectedRfpID",
			"selectedGST", "selectedGSTType","selectedSGST","selectedCGST" }, method = RequestMethod.GET, produces = "application/json")
	public Page<TaxCalculationDto> findPaginated(HttpServletRequest request) {
		logger.info("Inside findPaginated");

		int page = Integer.parseInt(request.getParameter("page"));
		int size = Integer.parseInt(request.getParameter("size"));
		String type = request.getParameter("type");
		String selectedCircelId = request.getParameter("selectedCircelId");
		String selectedStateId = request.getParameter("selectedStateId");
		// String quterTimePeriod= request.getParameter("quterTimePeriod");
		String quarter = request.getParameter("selectedQuarterId");
		String year = request.getParameter("selectedYearId");
		String selectedVendorId = request.getParameter("selectedVendorId");
		String selectedRfpID = request.getParameter("selectedRfpID");
		System.out.println("IGST "+ request.getParameter("selectedGST"));
		Float selectedGST = Float.parseFloat(request.getParameter("selectedGST"));
				
		String selectedGSTType = request.getParameter("selectedGSTType");
		Float selectedSGST = Float.parseFloat(request.getParameter("selectedSGST"));
		Float selectedCGST = Float.parseFloat(request.getParameter("selectedCGST"));
		
		System.out.println("selectedGST "+ selectedGST);
		
		System.out.println("selectedSGST "+ selectedSGST);
		System.out.println("selectedCGST "+ selectedCGST);
		
		
		
		

		report.setCircle(selectedCircelId);
		report.setState(selectedStateId);
		report.setYear(year);
		report.setQuarter(quarter);
		report.setVendor(selectedVendorId);
		report.setRpfNumber(selectedRfpID);
		report.setGst(selectedGST);
		report.setGstType(selectedGSTType);
		report.setCgst(selectedCGST);
		report.setSgst(selectedSGST);

		logger.info("selectedCircelId " + selectedCircelId);
		logger.info("selectedStateId " + selectedStateId);
		logger.info("quarter " + quarter);
		logger.info("year " + year);
		logger.info("selectedVendorId " + selectedVendorId);
		logger.info("selectedRfpID " + selectedRfpID);
		logger.info("selectedGST " + selectedGST);
		logger.info("selectedGSTType " + selectedGSTType);
		logger.info("selectedSGST " + selectedSGST);
		logger.info("selectedCGST " + selectedCGST);
		
		ModelMapper mapper= new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		List<TaxCalculationDto> taxList = null;		
		List<TaxCalculationDto> taxList2 = null;		
		List<TaxEntity> taxEntity = null;



		if(selectedGSTType.equalsIgnoreCase("IGST")) {
			taxList= ObjectMapperUtils.mapAll(
					taxService.getTaxCalculation(report, 1, "IGST"), TaxCalculationDto.class);
			
			taxEntity= ObjectMapperUtils.mapAll(taxList, TaxEntity.class);
			System.out.println("Tax Entity " + taxEntity);
			
			  Iterator<TaxEntity> iterable= taxEntity.iterator();
			  
			  TaxEntity taxEntity2 = null;
			  while (iterable.hasNext()) { 
				  taxEntity2 = (TaxEntity)
						  iterable.next(); System.out.println("Inside While taxEntity2 " + taxEntity2
			  ); 
						  
				//insertRepo.insert(taxEntity2); 
				taxRepo.save(taxEntity2);
				
			  }
			 
			
		
			
		}else {
			
			taxList = ObjectMapperUtils.mapAll(taxService.getTaxCalculation(report, 1, "CGST"), TaxCalculationDto.class);

			taxList2 = ObjectMapperUtils.mapAll(taxService.getTaxSGST(report, 1, "SGST"), TaxCalculationDto.class);

			taxList.addAll(taxList2);
			taxEntity= ObjectMapperUtils.mapAll(taxList, TaxEntity.class);
			
			
			Iterator<TaxEntity> iterable=  taxEntity.iterator();

			int i=0;
			TaxEntity taxEntity2 =null;
			double invoiceAmt = 0;
			double penaltyAmt =0;
			double gstInvoiceAmt = 0;
			double gstPenaltyAmt =0;
			double totalGst =0;
			while (iterable.hasNext()) {
				taxEntity2 = (TaxEntity) iterable.next();
				
				gstInvoiceAmt = gstInvoiceAmt + taxEntity2.getAmcGst();
				gstPenaltyAmt = gstPenaltyAmt + taxEntity2.getPenGst();
				totalGst = totalGst + taxEntity2.getToatalGST();
				

			}
			
			taxEntity2.setGstType("SGST/CGST");
			taxEntity2.setCgst(selectedCGST);
			taxEntity2.setSgst(selectedSGST);
			taxEntity2.setGst(null);
			taxEntity2.setAmcGst(gstInvoiceAmt);
			taxEntity2.setPenGst(gstPenaltyAmt);
			taxEntity2.setToatalGST(totalGst);
					
					taxRepo.save(taxEntity2);
		}

		
		Page<TaxCalculationDto> resultPage = new PageImpl<TaxCalculationDto>(taxList, PageRequest.of(page, size),
				taxList.size());
	
		return resultPage;
	}
	
	@RequestMapping(value = "taxSummary/get", params = { "page", "size" ,"type", "selectedCircelId", "selectedStateId","quterTimePeriod"}, method = RequestMethod.GET, produces = "application/json")
	public Page<TaxSummaryEntity> findPaginatedIS(
		      @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("type") String type, @RequestParam("selectedCircelId")
		      String selectedCircelId, @RequestParam("selectedStateId") String selectedStateId,
		      @RequestParam("quterTimePeriod") String quterTimePeriod) {
		logger.info("Inside findPaginatedTS");
	
		
		report.setCircle(selectedCircelId);
		report.setState(selectedStateId);
		report.setTimePeiod(quterTimePeriod);
	
		
		Page<TaxSummaryEntity> resultPage = null;
		
		if(selectedCircelId.equals("undefined") ||
			selectedStateId.equals("undefined") ||
			quterTimePeriod.equals("undefined") 
			) {		
			selectedCircelId="";
			selectedStateId="";
			quterTimePeriod="";
			
		}
		if(selectedStateId.equals("0"))	{
			resultPage = taxService.findPageWithoutStateIs(page, size, type, selectedCircelId, quterTimePeriod);
			
		}else{
			
			resultPage = taxService.findPageByFilterIs(page, size, type, selectedCircelId, selectedStateId, quterTimePeriod);
	}
		   
		  return resultPage;
	}
	
	
	@RequestMapping(value = "taxCal/insert", params = { "selectedCircelId",
			"selectedStateId", "selectedQuarterId", "selectedYearId", "selectedVendorId", "selectedRfpID"}, method = RequestMethod.GET, produces = "application/json")
	public int insertTaxCal(HttpServletRequest request) {
		logger.info("Inside findPaginated");

	
		String selectedCircelId = request.getParameter("selectedCircelId");
		String selectedStateId = request.getParameter("selectedStateId");	
		String quarter = request.getParameter("selectedQuarterId");
		String year = request.getParameter("selectedYearId");
		String selectedVendorId = request.getParameter("selectedVendorId");
		String selectedRfpID = request.getParameter("selectedRfpID");

	

		logger.info("selectedCircelId " + selectedCircelId);
		logger.info("selectedStateId " + selectedStateId);
		logger.info("quarter " + quarter);
		logger.info("year " + year);
		logger.info("selectedVendorId " + selectedVendorId);
		logger.info("selectedRfpID " + selectedRfpID);
	
		
		int count = taxRepo.findCount(selectedCircelId,
				selectedVendorId, selectedStateId, year,
				  quarter, selectedRfpID);
		
		
		return count;
	}
	

}
