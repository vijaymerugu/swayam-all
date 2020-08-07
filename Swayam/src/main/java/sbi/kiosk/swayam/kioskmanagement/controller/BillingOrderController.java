package sbi.kiosk.swayam.kioskmanagement.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.common.constants.ExceptionConstants;
import sbi.kiosk.swayam.common.dto.BillingAllocationDto;
import sbi.kiosk.swayam.common.dto.FileInfo;
import sbi.kiosk.swayam.common.dto.TicketCentorDto;
import sbi.kiosk.swayam.common.entity.BillingAllocation;
import sbi.kiosk.swayam.common.entity.ZeroTransactionKiosks;
import sbi.kiosk.swayam.kioskmanagement.service.BillingService;

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
	
	
	@RequestMapping("km/Billing")
	public ModelAndView drillDownPage(ModelAndView model, HttpSession session) {
		
		try {
			
			logger.info("billingorder");
			model.setViewName("billingorder");
			
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}

	


	// 1--------- ankur Verma -----billing upload 
	
	
	@RequestMapping(value = "billingallocation/get", params = { "page", "size" ,"type"}, method = RequestMethod.GET, produces = "application/json")
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
	

	  	  @RequestMapping(value = "billingallocation", method = RequestMethod.POST)
		public ResponseEntity<String> upload(@RequestParam("uploadfile") List<MultipartFile> files) {
			List<FileInfo> uploadedFiles = new ArrayList<FileInfo>();
			logger.info("files"+files);
			
			
			
			if (!files.isEmpty()) {
				try {
					for (MultipartFile file : files) {
						String path = context.getRealPath("/resources/upload") + File.separator
								+ file.getOriginalFilename();
						File destinationFile = new File(path);
						file.transferTo(destinationFile);
						uploadedFiles.add(new FileInfo(destinationFile.getName(), path));
						logger.info("uploadedFiles" + uploadedFiles);
						logger.info("name"+destinationFile.getName());
							
					}

				} catch (Exception e) {
					logger.error(e.getMessage());
				}

			}

			ModelAndView modelAndView = new ModelAndView("upload");
			modelAndView.addObject("files", uploadedFiles);
			 String name1 = uploadedFiles.get(0).getName();
				logger.info("name1"+name1);

			File dir = new File(uploadpath);
					//new File(rootPath + File.separator + "src\\main\\webapp\\WEB-INF\\uploaded");
			if (!dir.exists())
				dir.mkdirs();

			// Create the file on server
			File serverFile = new File(dir.getAbsolutePath() + File.separator + name1);

			logger.info("Server File Location=" + serverFile.getAbsolutePath());
			String path = serverFile.getAbsolutePath();
			String result = billingService.upload(path);
			
			 
			ResponseEntity<String> entity = ResponseEntity.ok(result);
			return entity;
		}
		
	 
	
}
