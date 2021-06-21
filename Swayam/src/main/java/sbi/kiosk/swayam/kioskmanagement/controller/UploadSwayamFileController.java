package sbi.kiosk.swayam.kioskmanagement.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
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

import sbi.kiosk.swayam.common.constants.ExceptionConstants;
import sbi.kiosk.swayam.common.dto.FileInfo;
import sbi.kiosk.swayam.healthmonitoring.controller.TicketCentorController;
import sbi.kiosk.swayam.kioskmanagement.service.UploadService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/")
public class UploadSwayamFileController {

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

	@Autowired
	ServletContext context;

	@RequestMapping(value = "uploadHolidayCalendar", method = RequestMethod.POST)
//	@PreAuthorize("hasPermission('UPDuploadHolidayCalendar','CREATE')")
	public ResponseEntity<String> upload(@RequestParam("myFile") List<MultipartFile> files) {
		List<FileInfo> uploadedFiles = new ArrayList<FileInfo>();
	

		if (!files.isEmpty()) {
			try {
				for (MultipartFile file : files) {

					List<String> fileNames = new ArrayList<String>();
					String fileName = file.getOriginalFilename();
					fileNames.add(fileName);
	
					File imageFile = new File(context.getRealPath("/resources/upload"));
		
					if (!imageFile.exists())
					{
					imageFile.mkdirs();

					}
			
					String path = context.getRealPath("/resources/upload") + File.separator + fileName;

					File destinationFile = new File(path);	

					
				//	file.transferTo(destinationFile);
					// read and write the file to the selected location-
					byte[] bytes = file.getBytes();
					Path path1 = Paths.get(uploadpath + file.getOriginalFilename());
					Files.write(path1, bytes);
					
					path = uploadpath  + file.getOriginalFilename();
					uploadedFiles.add(new FileInfo(destinationFile.getName(), path));
					  
				}

			} catch (Exception e) {

			}

		}

		ModelAndView modelAndView = new ModelAndView("upload");
		modelAndView.addObject("files", uploadedFiles);
		String name1 = uploadedFiles.get(0).getName();

		File dir = new File(uploadpath);
		
		if (!dir.exists())
			dir.mkdirs();

		// Create the file on server
		File serverFile = new File(dir.getAbsolutePath() + File.separator + name1);

		String path = serverFile.getAbsolutePath();
		String result = uploadService.uploadHolidayCalendarInformation(path);
		
		ResponseEntity<String> entity = ResponseEntity.ok(result);

		return entity;
	}

// 2 KioskCMF 
	/*
	 * @RequestMapping(value = "uploadKioskDetails", method = RequestMethod.POST)
	 * // @PreAuthorize("hasPermission('UPDuploadKioskDetails','CREATE')") public
	 * ResponseEntity<String> uploadKioskDetails(@RequestParam("KioskFile")
	 * List<MultipartFile> files) { List<FileInfo> uploadedFiles = new
	 * ArrayList<FileInfo>();
	 * 
	 * if (!files.isEmpty()) { try { for (MultipartFile file : files) {
	 * 
	 * logger.info("i am inside uploadKioskDetails!!!"); // List<String> fileNames =
	 * new ArrayList<String>(); String fileName = file.getOriginalFilename();
	 * logger.info("fileName!!!"+fileName); // fileNames.add(fileName); //
	 * logger.info("fileName list!!!"+fileNames.size()); File imageFile = new
	 * File(context.getRealPath("/resources/upload"));
	 * logger.info("imageFile created!!!"+imageFile.toString()); if
	 * (!imageFile.exists()) {
	 * 
	 * imageFile.mkdirs(); logger.info("imageFile folder created!!!"); }
	 * 
	 * String path = context.getRealPath("/resources/upload") + File.separator +
	 * fileName; logger.info("imageFile folder path:::::"+path); File
	 * destinationFile = new File(path); // file.transferTo(destinationFile); //
	 * read and write the file to the selected location-
	 * logger.info("destinationFile file created!!!"); byte[] bytes =
	 * file.getBytes(); logger.info("Converted to bytes!!!"); Path path1 =
	 * Paths.get(uploadpath + file.getOriginalFilename());
	 * logger.info("path of file !!!"+path1.getFileName()); Files.write(path1,
	 * bytes); logger.info("File write successfully in path:::::1"); path =
	 * uploadpath + file.getOriginalFilename();
	 * logger.info("File write successfully in path:::::2"+path);
	 * uploadedFiles.add(new FileInfo(destinationFile.getName(), path));
	 * logger.info("File write successfully in path:::::3");
	 * 
	 * 
	 * }
	 * 
	 * } catch (Exception e) {
	 * 
	 * }
	 * 
	 * } logger.info("Upload File write successfully in path:::::4"); ModelAndView
	 * modelAndView = new ModelAndView("upload"); modelAndView.addObject("files",
	 * uploadedFiles); String name1 = uploadedFiles.get(0).getName();
	 * logger.info("Upload File write successfully in path:::::5"+name1); File dir =
	 * new File(uploadpath);
	 * 
	 * if (!dir.exists()) dir.mkdirs(); File serverFile = new
	 * File(dir.getAbsolutePath() + File.separator + name1);
	 * 
	 * 
	 * String path = serverFile.getAbsolutePath();
	 * logger.info("Upload File write successfully in path:::::6"+path); String
	 * result = uploadService.uploadKioskCMFInformation(path);
	 * logger.info("Upload File write successfully in path:::::7");
	 * ResponseEntity<String> entity = ResponseEntity.ok(result);
	 * logger.info("Upload File write successfully in path:::::8"+entity); return
	 * entity; }
	 */
	@RequestMapping(value = "uploadKioskDetails", method = RequestMethod.POST)
	// @PreAuthorize("hasPermission('UPDuploadKioskDetails','CREATE')")
	public ResponseEntity<String> uploadKioskDetails(@RequestParam("KioskFile") MultipartFile file) { 
		new ArrayList();
    this.logger.info("Inside uploadKioskDetails");
    String result = null;

    try {
       result = this.uploadService.uploadKioskCMFInformationNew(file.getInputStream());
    } catch (IOException var5) {
       throw new RuntimeException("fail to store excel data: " + var5.getMessage());
    }

    ResponseEntity<String> entity = ResponseEntity.ok(result);
    return entity;
    }
// 3 KioskInformation
	@RequestMapping(value = "uploadKioskCMF", method = RequestMethod.POST)
//	@PreAuthorize("hasPermission('UPDuploadKioskCMF','CREATE')")
	public ResponseEntity<String> uploadKioskInformation(@RequestParam("CMFFile") List<MultipartFile> files) {

		List<FileInfo> uploadedFiles = new ArrayList<FileInfo>();

		if (!files.isEmpty()) {
			try {
				for (MultipartFile file : files) {
					
					List<String> fileNames = new ArrayList<String>();
					String fileName = file.getOriginalFilename();
					fileNames.add(fileName);
			
					File imageFile = new File(context.getRealPath("/resources/upload"));
					if (!imageFile.exists())
					{
					imageFile.mkdirs();
		
					}
			
					String path = context.getRealPath("/resources/upload") + File.separator + fileName;
			
					File destinationFile = new File(path);	
					
				//	file.transferTo(destinationFile);
					// read and write the file to the selected location-
					byte[] bytes = file.getBytes();
					Path path1 = Paths.get(uploadpath + file.getOriginalFilename());

					Files.write(path1, bytes);
		
					path = uploadpath  + file.getOriginalFilename();
		
					uploadedFiles.add(new FileInfo(destinationFile.getName(), path));
					

				}

			} catch (Exception e) {
	
			}

		}

		ModelAndView modelAndView = new ModelAndView("upload");
		modelAndView.addObject("files", uploadedFiles);
		String name1 = uploadedFiles.get(0).getName();

		File dir = new File(uploadpath);

		if (!dir.exists())
			dir.mkdirs();

		// Create the file on server
		File serverFile = new File(dir.getAbsolutePath() + File.separator + name1);

		String path = serverFile.getAbsolutePath();

		String result = uploadService.uploadKioskInformation(path);
		ResponseEntity<String> entity = ResponseEntity.ok(result);

		return entity;
	}

// 4 uploadCBSbrhm
	@RequestMapping(value = "uploadCBSbrhm", method = RequestMethod.POST)
	// @PreAuthorize("hasPermission('UPDuploadCBSbrhm','CREATE')")
	public ResponseEntity<String> uploadCBSbrhm(@RequestParam("BMFile") List<MultipartFile> files) {
		List<FileInfo> uploadedFiles = new ArrayList<FileInfo>();

		if (!files.isEmpty()) {
			try {
				for (MultipartFile file : files) {
					
					List<String> fileNames = new ArrayList<String>();
					String fileName = file.getOriginalFilename();
					fileNames.add(fileName);
	
					File imageFile = new File(context.getRealPath("/resources/upload"));

					
					if (!imageFile.exists())
					{
					imageFile.mkdirs();

					}
			
					String path = context.getRealPath("/resources/upload") + File.separator + fileName;
	
					File destinationFile = new File(path);	
					
				//	file.transferTo(destinationFile);
					// read and write the file to the selected location-
					byte[] bytes = file.getBytes();
					Path path1 = Paths.get(uploadpath + file.getOriginalFilename());

					Files.write(path1, bytes);
		
					path = uploadpath  + file.getOriginalFilename();

					uploadedFiles.add(new FileInfo(destinationFile.getName(), path));
	
				}

			} catch (Exception e) {

			}

		}

		ModelAndView modelAndView = new ModelAndView("upload");
		modelAndView.addObject("files", uploadedFiles);
		String name1 = uploadedFiles.get(0).getName();

		File dir = new File(uploadpath);
		
		if (!dir.exists())
			dir.mkdirs();
		
		
		// Create the file on server
		File serverFile = new File(dir.getAbsolutePath() + File.separator + name1);

		String path = serverFile.getAbsolutePath();
	
		String result = uploadService.uploadCBSbrhmInformation(path);
		ResponseEntity<String> entity = ResponseEntity.ok(result);

		return entity;
	}
	
	/*
	 * // 5 uploadInvVendor
	 * 
	 * @RequestMapping(value = "uploadInvVendor", method = RequestMethod.POST)
	 * 
	 * public ResponseEntity<String> uploadInvVendor(@RequestParam("InFile")
	 * List<MultipartFile> files) { List<FileInfo> uploadedFiles = new
	 * ArrayList<FileInfo>();
	 * 
	 * if (!files.isEmpty()) { try { for (MultipartFile file : files) {
	 * 
	 * List<String> fileNames = new ArrayList<String>(); String fileName =
	 * file.getOriginalFilename(); fileNames.add(fileName);
	 * 
	 * File imageFile = new File(context.getRealPath("/resources/upload"));
	 * 
	 * if (!imageFile.exists()) { imageFile.mkdirs();
	 * 
	 * }
	 * 
	 * String path = context.getRealPath("/resources/upload") + File.separator +
	 * fileName;
	 * 
	 * File destinationFile = new File(path);
	 * 
	 * 
	 * // file.transferTo(destinationFile); // read and write the file to the
	 * selected location- byte[] bytes = file.getBytes(); Path path1 =
	 * Paths.get(uploadpath + file.getOriginalFilename()); Files.write(path1,
	 * bytes);
	 * 
	 * path = uploadpath + file.getOriginalFilename(); uploadedFiles.add(new
	 * FileInfo(destinationFile.getName(), path));
	 * 
	 * }
	 * 
	 * } catch (Exception e) {
	 * 
	 * }
	 * 
	 * }
	 * 
	 * ModelAndView modelAndView = new ModelAndView("upload");
	 * modelAndView.addObject("files", uploadedFiles); String name1 =
	 * uploadedFiles.get(0).getName();
	 * 
	 * File dir = new File(uploadpath);
	 * 
	 * if (!dir.exists()) dir.mkdirs();
	 * 
	 * 
	 * // Create the file on server File serverFile = new File(dir.getAbsolutePath()
	 * + File.separator + name1);
	 * 
	 * String path = serverFile.getAbsolutePath(); String result =
	 * uploadService.uploadInvVendorInformation(path);
	 * 
	 * ResponseEntity<String> entity = ResponseEntity.ok(result);
	 * 
	 * return entity; }
	 */
	// 5 uploadInvVendor
		@RequestMapping(value = "uploadInvVendor", method = RequestMethod.POST)
		
		public ResponseEntity<String> uploadInvVendor(@RequestParam("InFile")  MultipartFile file) { 
			new ArrayList();
		    this.logger.info("Inside uploadInvVendor");
		    String result = null;

		    try {
		       result = this.uploadService.uploadInvVendorInformationNew(file.getInputStream());
		    } catch (IOException var5) {
		       throw new RuntimeException("fail to store excel data: " + var5.getMessage());
		    }

		    ResponseEntity<String> entity = ResponseEntity.ok(result);
		    return entity;
		    }
}
