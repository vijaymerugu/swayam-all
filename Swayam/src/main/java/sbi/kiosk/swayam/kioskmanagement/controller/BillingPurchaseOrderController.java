package sbi.kiosk.swayam.kioskmanagement.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sbi.kiosk.swayam.common.constants.ExceptionConstants;
import sbi.kiosk.swayam.common.dto.AddUserDto;
import sbi.kiosk.swayam.common.dto.BillingAllocationDto;
import sbi.kiosk.swayam.common.dto.FileInfo;
import sbi.kiosk.swayam.common.dto.RolesDto;
import sbi.kiosk.swayam.common.dto.TicketCentorDto;
import sbi.kiosk.swayam.common.entity.BillingAllocation;
import sbi.kiosk.swayam.common.entity.Circle;
import sbi.kiosk.swayam.common.entity.ZeroTransactionKiosks;
import sbi.kiosk.swayam.kioskmanagement.service.BillingService;

@RestController
@RequestMapping("/")
public class BillingPurchaseOrderController  {
	
	Logger logger = LoggerFactory.getLogger(BillingPurchaseOrderController.class);

	@Autowired
	private BillingService   billingService;
   
	@Autowired
	ServletContext context;
	
	@Value("${upload.path}")		
	private String uploadpath;
	
	
	@RequestMapping("km/purchaseorder")
	public ModelAndView drillDownPage(ModelAndView model, HttpSession session) {
		
		try {
			
			logger.info("billingPurchaseOrder");
			model.setViewName("billingPurchaseOrder");
			
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}

	


	// 1--------- ankur Verma -----billing upload 
	
	
	@RequestMapping(value = "billingorder/get", params = { "page", "size" ,"type"}, method = RequestMethod.GET, produces = "application/json")
	public Page<BillingAllocationDto> findPaginated(
		      @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("type") String type ) {
		 
		logger.info("type=========Ankur========="+type);
		logger.info("size"+size);

		Page<BillingAllocationDto> resultPage = null;
		
	
			
	      resultPage = billingService.findPaginated(page, size);
	      logger.info("resultPage"+resultPage);
	      
	      
		    if (page > resultPage.getTotalPages()){
		            //throw new MyResourceNotFoundException();
		        }
		  return resultPage;
	}
	
	
	//2 ===
	

	
	/*
	 * @RequestMapping(value = "saveBillingPurchaseOrder" , method =
	 * RequestMethod.POST) //public ModelAndView
	 * saveCheckerComments(@RequestParam("array") List<CheckerComments> array) {
	 * public ResponseEntity<String> saveBillingPurchaseOrder() {
	 * 
	 * //billingService.saveOrder(array);
	 * //healthMonitoringService.saveCheckerCommentsCms(array);
	 * logger.info("saveCheckerCommentsCms===array="); //ModelAndView mav = new
	 * ModelAndView("requestFormCms"); //ResponseEntity<String> entiry =
	 * ResponseEntity.ok(array); return entiry; }
	 */
	  	 
	
	// 3
	@RequestMapping(value = "editBillingOrder/allocId")
	public ModelAndView editUserMasterLA(ModelAndView  model, HttpServletRequest request,@RequestParam("allocId") Integer allocId,@ModelAttribute("billingAllocationDto") BillingAllocationDto billingAllocationDto) {
		try {
			System.out.println("billingAllocationDto 1"+billingAllocationDto);
		//BillingAllocationDto billingAllocationDto =new BillingAllocationDto();
		billingAllocationDto= billingService.findBillingallocId(allocId);
			System.out.println("billingAllocationDto 2"+billingAllocationDto);
			
			
			model.addObject("billingAllocationDto", billingAllocationDto);
			
			model.setViewName("billingPurchaseOrderEdit");
            }
			
			

		 catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
			
		} finally {
		}
		return model;
	}
		
	
	// km/addbillingLA
	
	@PostMapping("km/addbillingLA")
	public ResponseEntity<String> addBillingLA(ModelAndView model,@ModelAttribute("billingAllocationDto") BillingAllocationDto billingAllocationDto,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		ResponseEntity<String> entity=null;
		String addBillingResut=null;
		//String addBillingResut1=null;
		
		addBillingResut=billingService.updateBillingAllocation(billingAllocationDto);
		String result=" result "+addBillingResut+ " has been successfully Updated";
		
		entity=ResponseEntity.ok(result);
		
		// addBillingResut1=billingService.updatepurchaseOrder(billingAllocationDto);

		
	return entity;	
	}
	 
	
}
