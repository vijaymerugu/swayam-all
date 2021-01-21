package sbi.kiosk.swayam.billingpayment.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import sbi.kiosk.swayam.billingpayment.repository.AllocationRepository;
import sbi.kiosk.swayam.billingpayment.repository.BillingPurchaseOrderRepository;
import sbi.kiosk.swayam.billingpayment.repository.DisplayAllocationRepository;
import sbi.kiosk.swayam.billingpayment.repository.VendorRepository;
import sbi.kiosk.swayam.billingpayment.service.BillingService;
import sbi.kiosk.swayam.common.constants.ExceptionConstants;
import sbi.kiosk.swayam.common.dto.AllocationDto;
import sbi.kiosk.swayam.common.dto.BillingAllocationDto;
import sbi.kiosk.swayam.common.dto.FileInfo;
import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.entity.Allocation;
import sbi.kiosk.swayam.common.entity.BillingPurchaseOrder;
import sbi.kiosk.swayam.common.entity.DisplayAllocation;
import sbi.kiosk.swayam.common.repository.AllVendorRepository;
import sbi.kiosk.swayam.common.repository.BillingCircleRepository;
import sbi.kiosk.swayam.healthmonitoring.model.InvoiceUpdateReposne;


@RestController
@RequestMapping("/")
public class BillingOrderController  {
	
	Logger logger = LoggerFactory.getLogger(BillingOrderController.class);

	@Autowired
	private BillingService   billingService;
   
	@Autowired
	ServletContext context;
	
	@Value("${upload.path}")		
	private String uploadpath;
	
	/*
	 * @Autowired BillingCircleRepository circleRepository;
	 * 
	 * @Autowired AllVendorRepository vendorRepository;
	 */
	
	@Autowired
	DisplayAllocationRepository allocationRepoistory;
	
	@Autowired
	AllocationRepository allocationRepository;
	
	@Autowired
	BillingPurchaseOrderRepository purchaseRepo;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping("bp/allocation")
	public ModelAndView drillDownPage(ModelAndView model, HttpSession session) {
		
		try {
			
			logger.info("billingorder");
//			model.setViewName("billingorder");
			model.setViewName("PurchaseAllocation");
			
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}

	
	
	@RequestMapping(value = "billingallocation/get", params = { "page", "size" ,"type"}, method = RequestMethod.GET, produces = "application/json")
	public Page<DisplayAllocation> findPaginated(
		      @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("type") String type ) {
		 
		logger.info("type=================="+type);
		//logger.info("size"+size);

		Page<DisplayAllocation> resultPage = null;
		
	
			
	      resultPage = allocationRepoistory.findAll(PageRequest.of(page, size));
	     // logger.info("resultPage"+resultPage);
	      
	      
		    if (page > resultPage.getTotalPages()){
		            //throw new MyResourceNotFoundException();
		        }
		  return resultPage;
	}
	

	  	@RequestMapping(value = "billingallocation", method = RequestMethod.POST)
		public ResponseEntity<?> upload(@RequestParam("uploadfile") List<MultipartFile> files) {
			List<FileInfo> uploadedFiles = new ArrayList<FileInfo>();
			logger.info("files"+files);
			
			
			if (!files.isEmpty()) {
				try {
					for (MultipartFile file : files) {
						
					
						List<String> fileNames = new ArrayList<String>();
						String fileName = file.getOriginalFilename();
						fileNames.add(fileName);
						//logger.info("1.File Name========== "+file.getOriginalFilename());
						File imageFile = new File(context.getRealPath("/resources/upload"));
						//logger.info("imageFile path: "+imageFile.getPath());
						//logger.info("imageFile path1: "+context.getRealPath("/resources/upload"));
						
						if (!imageFile.exists())
						{
						imageFile.mkdirs();
						//logger.info("Directory created!!!"+imageFile);
						}
						//	imageFile = new File(context.getRealPath("/resources/upload"), fileName);
						String path = context.getRealPath("/resources/upload") + File.separator + fileName;
						//logger.info("2.Path============ "+path);
						File destinationFile = new File(path);	
						//logger.info("3.name============= "+destinationFile.getName());
						//logger.info("//////////A.File transfer started!!!!!!!!!!!////////////////");
						
					//	file.transferTo(destinationFile);
						// read and write the file to the selected location-
						byte[] bytes = file.getBytes();
						Path path1 = Paths.get(uploadpath + file.getOriginalFilename());
						//logger.info("File write path: "+path1.toString());
						Files.write(path1, bytes);
						
					//	logger.info("/////////////////File transfer completed: "+path1.toString());
						//logger.info("4.File Transfer done!!!!!!!!!");
						path = uploadpath  + file.getOriginalFilename();
					//	logger.info("5.uploadedFiles path=========== " + path);
						uploadedFiles.add(new FileInfo(destinationFile.getName(), path));
						//logger.info("6.uploadedFiles=========== " + uploadedFiles);
						
						

					}

				} catch (Exception e) {
					logger.error("Exception "+ExceptionConstants.EXCEPTION);

				}

			}
			
			
			
			
			
			/*
			 * if (!files.isEmpty()) { try { for (MultipartFile file : files) {
			 * 
			 * String path = uploadpath + File.separator + file.getOriginalFilename();
			 * System.out.println("First " + path); File destinationFile = new File(path);
			 * file.transferTo(destinationFile); uploadedFiles.add(new
			 * FileInfo(destinationFile.getName(), path)); logger.info("uploadedFiles" +
			 * uploadedFiles); logger.info("name"+destinationFile.getName());
			 * 
			 * }
			 * 
			 * } catch (Exception e) { logger.error(e.getMessage()); }
			 * 
			 * }
			 */

			ModelAndView modelAndView = new ModelAndView("upload");
			modelAndView.addObject("files", uploadedFiles);
			String name1 = uploadedFiles.get(0).getName();
			//logger.info("6.name1=======" + name1);

			File dir = new File(uploadpath);
					//new File(rootPath + File.separator + "src\\main\\webapp\\WEB-INF\\uploaded");
			if (!dir.exists())
				dir.mkdirs();

			// Create the file on server
			File serverFile = new File(dir.getAbsolutePath() + File.separator + name1);
			//logger.info("7.Dir AbsolutePath=============" + dir.getAbsolutePath());
			
			String path = serverFile.getAbsolutePath();	
		//	logger.info("8.Server File Location===========" + path);
			
//			Gson gson = new GsonBuilder().setPrettyPrinting().create();
//			String vendor = gson.toJson(vendorRepository.findAll());
//			String circle = gson.toJson(circleRepository.findAll());
			
			String result = billingService.upload(path);
		//	logger.info("9.Result part done: " + result);
			 
			//ResponseEntity<InvoiceUpdateReposne> entity = ResponseEntity.ok(new InvoiceUpdateReposne("", result));
			
			ResponseEntity<String> entity = ResponseEntity.ok(result);

			//logger.info("10.Transfer to entity");
			
			return entity;
		}
	  	
	  	
	  	@RequestMapping(value = "al/edit",params = { "AllocId" ,"AllocatedQuantity", "PoQuantity", "RemainingQuantity"},
	  			method = RequestMethod.GET)
	  			@Transactional
	  			public ResponseEntity<?> updateCorrections(@RequestParam("AllocId")Integer allocId,
	  					@RequestParam("AllocatedQuantity") Integer allocatedQuantity,
	  					@RequestParam("PoQuantity") Integer poQuantity,
	  					@RequestParam("RemainingQuantity") Integer remainingQuantity)
	  					throws Exception {
	  				
	  				logger.info("Inside updateCorrections");
	  				UserDto user = (UserDto) session.getAttribute("userObj");
	  			//	System.out.println("Username " + user.getUsername());
	  				
	  				//Optional<BillingPurchaseOrder> podetails=repo.findById(poNumber);
	  				Optional<Allocation> allocationdetails=allocationRepository.findById(allocId);
	  				Allocation allocation=allocationdetails.get();
	  				allocation.setRemainingQuantity(remainingQuantity-poQuantity);
	  				allocation.setUpdatdBy(user.getUsername());
	  				allocation.setUpdatdDate(new Date());
	  				//BillingPurchaseOrder order =podetails.get();
	  				BillingPurchaseOrder order =new BillingPurchaseOrder();
	  				
	  				order.setBillingallocId(allocId);
	  				order.setPoQantity(poQuantity);
	  				order.setPoDate(new Date());
	  				order.setCreatedBy(user.getUsername());
	  				order.setCreatedDate(new Date());
	  				order.setStatus("S");
	  				order.setPoNumber(0);
	  				order.setUpdatdBy(user.getUsername());
	  				order.setUpdatdDate(new Date());
	  				//System.out.println("Pd oder date " + order.getPoDate());
	  				//System.out.println("Pd Quantity " + order.getPoQantity());
	  				
	  				if (allocation.getRemainingQuantity()==0) {
	  					allocation.setStatus("0");
	  					
	  				}
	  				
	  				
	  				if(poQuantity>0) {
	  					
	  					purchaseRepo.save(order);
	  					allocationRepository.save(allocation);
	  					
	  					
	  					
	  				}else {
	  					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  				}
	  				
	  			
	  				
	  				 return new ResponseEntity<>(HttpStatus.OK);
	  				
	  				//return ResponseEntity.ok(new InvoiceUpdateReposne("Sucess", "Data Saved Successfully"));
	  			}
	  	
		
	 
	
}
