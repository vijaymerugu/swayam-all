package sbi.kiosk.swayam.kioskmanagement.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.common.dto.FileInfo;
import sbi.kiosk.swayam.healthmonitoring.controller.TicketCentorController;
import sbi.kiosk.swayam.kioskmanagement.service.UploadService;
import java.nio.file.Path; 
import java.nio.file.Paths; 

@RestController
@RequestMapping("/")
public class UploadSwayamFileController  {
	
	Logger logger = LoggerFactory.getLogger(UploadSwayamFileController.class);

	@Autowired
	private UploadService uploadService;
   
	@Value("${upload.path}")		
	private String uploadpath;
	
//	@Autowired 
//	HttpServletRequest request;

	@GetMapping("km/upload")
	public ModelAndView UploadPage() {
		ModelAndView view = new ModelAndView();

		view.setViewName("upload");
		return view;
	}

	


	// 1--------- ankur Verma -----uploadHolidayCalendar
	@Autowired
	ServletContext context;
	@RequestMapping(value = "uploadHolidayCalendar", method = RequestMethod.POST)
	@PreAuthorize("hasPermission('UPDuploadHolidayCalendar','CREATE')")
	public ResponseEntity<String> upload(@RequestParam("myFile") List<MultipartFile> files) {
		List<FileInfo> uploadedFiles = new ArrayList<FileInfo>();
	//	logger.info("files"+files);
		
		
		
		if (!files.isEmpty()) {
			try {
				for (MultipartFile file : files) {
					
					/*
					 * String path = context.getRealPath("/resources/upload") + File.separator +
					 * file.getOriginalFilename();
					 */
					String fileName = file.getOriginalFilename();
					 Path paths = Paths.get(fileName); 
					 Path orgFileName = paths.getFileName(); 
					
					String path = context.getRealPath("/resources/upload") + File.separator
							+ orgFileName;
					File destinationFile = new File(path);
					file.transferTo(destinationFile);
					uploadedFiles.add(new FileInfo(destinationFile.getName(), path));
				//	logger.info("uploadedFiles" + uploadedFiles);
				//	logger.info("name"+destinationFile.getName());
						
				}

			} catch (Exception e) {
				logger.error(e.getMessage());
			}

		}

		ModelAndView modelAndView = new ModelAndView("upload");
		modelAndView.addObject("files", uploadedFiles);
		 String name1 = uploadedFiles.get(0).getName();
		//	logger.info("name1"+name1);
		File dir = new File(uploadpath);
				//new File(rootPath + File.separator + "src\\main\\webapp\\WEB-INF\\uploaded");
		if (!dir.exists())
			dir.mkdirs();

		// Create the file on server
		File serverFile = new File(dir.getAbsolutePath() + File.separator + name1);

	//	logger.info("Server File Location=" + serverFile.getAbsolutePath());
		String path = serverFile.getAbsolutePath();
		String result = uploadService.uploadHolidayCalendarInformation(path);
		//String result = uploadService.uploadKioskCMFInformation(path);
		//String result = uploadService.uploadKioskInformation(path);
	//	String result = uploadService.uploadCBSbrhmInformation(path);
		//modelAndView.addObject("holidayCalendarStatus", result);
		
		ResponseEntity<String> entity = ResponseEntity.ok(result);
		return entity;
	}
// 2 KioskCMF 
	@RequestMapping(value = "uploadKioskDetails", method = RequestMethod.POST)
	@PreAuthorize("hasPermission('UPDuploadKioskDetails','CREATE')")
	public ResponseEntity<String> uploadKioskDetails(@RequestParam("KioskFile") List<MultipartFile> files) {
		List<FileInfo> uploadedFiles = new ArrayList<FileInfo>();
	//	logger.info("files"+files);
		
		if (!files.isEmpty()) {
			try {
				for (MultipartFile file : files) {
					/*
					 * String path = context.getRealPath("/resources/upload") + File.separator +
					 * file.getOriginalFilename();
					 */
					String fileName = file.getOriginalFilename();
					 Path paths = Paths.get(fileName); 
					 Path orgFileName = paths.getFileName(); 
					
					String path = context.getRealPath("/resources/upload") + File.separator
							+ orgFileName;
					File destinationFile = new File(path);
					file.transferTo(destinationFile);
					uploadedFiles.add(new FileInfo(destinationFile.getName(), path));
				//	logger.info("uploadedFiles" + uploadedFiles);
				//	logger.info("name"+destinationFile.getName());	
				}

			} catch (Exception e) {
				logger.error(e.getMessage());
			}

		}
		ModelAndView modelAndView = new ModelAndView("upload");
		modelAndView.addObject("files", uploadedFiles);
		 String name1 = uploadedFiles.get(0).getName();
		// logger.info("name1"+name1);
		File dir = new File(uploadpath);
				//new File(rootPath + File.separator + "src\\main\\webapp\\WEB-INF\\uploaded");
		if (!dir.exists())
			dir.mkdirs();
		File serverFile = new File(dir.getAbsolutePath() + File.separator + name1);

		logger.info("Server File Location=" + serverFile.getAbsolutePath());
		String path = serverFile.getAbsolutePath();
		String result = uploadService.uploadKioskCMFInformation(path);
	//	logger.info("result=" + result);
		ResponseEntity<String> entity = ResponseEntity.ok(result);
		return entity;
	}
	
// 3 KioskInformation
	@RequestMapping(value = "uploadKioskCMF", method = RequestMethod.POST)
	@PreAuthorize("hasPermission('UPDuploadKioskCMF','CREATE')")
	public ResponseEntity<String> uploadKioskInformation(@RequestParam("CMFFile") List<MultipartFile> files) {
		logger.info("==uploadKioskInformation================");
		List<FileInfo> uploadedFiles = new ArrayList<FileInfo>();	
	//	logger.info("files"+files);
		if (!files.isEmpty()) {
			try {
				for (MultipartFile file : files) {
					/*
					 * String path = context.getRealPath("/resources/upload") + File.separator +
					 * file.getOriginalFilename();
					 */
					String fileName = file.getOriginalFilename();
					 Path paths = Paths.get(fileName); 
					 Path orgFileName = paths.getFileName(); 
					
					String path = context.getRealPath("/resources/upload") + File.separator
							+ orgFileName;
				//	logger.info("==CONTEXT PATH========"+context.getRealPath("/WEB-INF/upload"));
				//	logger.info("==PATH================"+path);
					File destinationFile = new File(path);
					file.transferTo(destinationFile);
					uploadedFiles.add(new FileInfo(destinationFile.getName(), path));
				//	logger.info("=====uploadedFiles=====" + uploadedFiles);
				//	logger.info("=====DESTINATION=======name"+destinationFile.getName());
				
						
				}

			} catch (Exception e) {
				logger.error(e.getMessage());
			}

		}

		ModelAndView modelAndView = new ModelAndView("upload");
		modelAndView.addObject("files", uploadedFiles);
		 String name1 = uploadedFiles.get(0).getName();
	//	 logger.info("=====name1========"+name1);
		File dir = new File(uploadpath);
				
		if (!dir.exists())
			dir.mkdirs();

		// Create the file on server
		File serverFile = new File(dir.getAbsolutePath() + File.separator + name1);

	//	logger.info("Server File Location=====" + serverFile.getAbsolutePath());
		String path = serverFile.getAbsolutePath();
		
		String result = uploadService.uploadKioskInformation(path);

		
		ResponseEntity<String> entity = ResponseEntity.ok(result);
		return entity;
	}
// 4 uploadCBSbrhm
	@RequestMapping(value = "uploadCBSbrhm", method = RequestMethod.POST)
	//@PreAuthorize("hasPermission('UPDuploadCBSbrhm','CREATE')")
	public ResponseEntity<String> uploadCBSbrhm(@RequestParam("BMFile") List<MultipartFile> files) {
		List<FileInfo> uploadedFiles = new ArrayList<FileInfo>();	
	//	logger.info("files"+files);
		if (!files.isEmpty()) {
			try {
				for (MultipartFile file : files) {
					/*
					 * String path = context.getRealPath("/resources/upload") + File.separator +
					 * file.getOriginalFilename();
					 */
					String fileName = file.getOriginalFilename();
					 Path paths = Paths.get(fileName); 
					 Path orgFileName = paths.getFileName(); 
					
					String path = context.getRealPath("/resources/upload") + File.separator
							+ orgFileName;
					File destinationFile = new File(path);
					file.transferTo(destinationFile);
					uploadedFiles.add(new FileInfo(destinationFile.getName(), path));
			//		logger.info("uploadedFiles" + uploadedFiles);
			//		logger.info("name"+destinationFile.getName());
						
				}

			} catch (Exception e) {
				logger.error(e.getMessage());
			}

		}

		ModelAndView modelAndView = new ModelAndView("upload");
		modelAndView.addObject("files", uploadedFiles);
		 String name1 = uploadedFiles.get(0).getName();
	//	 logger.info("name1"+name1);
		
		File dir = new File(uploadpath);
				
		if (!dir.exists())
			dir.mkdirs();

		// Create the file on server
		File serverFile = new File(dir.getAbsolutePath() + File.separator + name1);

	//	logger.info("Server File Location=" + serverFile.getAbsolutePath());
		String path = serverFile.getAbsolutePath();
		
		String result = uploadService.uploadCBSbrhmInformation(path);
		//modelAndView.addObject("holidayCalendarStatus", result);
		
		ResponseEntity<String> entity = ResponseEntity.ok(result);
		return entity;
	}
}
