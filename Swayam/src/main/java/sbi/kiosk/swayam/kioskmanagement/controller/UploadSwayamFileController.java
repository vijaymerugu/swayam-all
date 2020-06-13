package sbi.kiosk.swayam.kioskmanagement.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import sbi.kiosk.swayam.kioskmanagement.service.UploadService;

@RestController
@RequestMapping("/")
public class UploadSwayamFileController extends HttpServlet {

	@Autowired
	private UploadService uploadService;

//	@Autowired 
//	HttpServletRequest request;

	@GetMapping("km/upload")
	public ModelAndView UploadPage() {
		System.out.println("inside upload cotroller........");
		ModelAndView view = new ModelAndView();

		view.setViewName("upload");
		return view;
	}

	// By Pankul 10-04-2020-----------STARTS---------


	// 1--------- ankur Verma -----uploadHolidayCalendar
	@Autowired
	ServletContext context;
	@RequestMapping(value = "uploadHolidayCalendar", method = RequestMethod.POST)
	public ResponseEntity<String> upload(@RequestParam("myFile") List<MultipartFile> files) {
		List<FileInfo> uploadedFiles = new ArrayList<FileInfo>();
		System.out.println("files"+files);
		
		
		if (!files.isEmpty()) {
			try {
				for (MultipartFile file : files) {
					String path = context.getRealPath("/WEB-INF/uploaded") + File.separator
							+ file.getOriginalFilename();
					File destinationFile = new File(path);
					file.transferTo(destinationFile);
					uploadedFiles.add(new FileInfo(destinationFile.getName(), path));
					System.out.println("uploadedFiles" + uploadedFiles);
					System.out.println("name"+destinationFile.getName());
					
					
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

		ModelAndView modelAndView = new ModelAndView("upload");
		modelAndView.addObject("files", uploadedFiles);
		 String name1 = uploadedFiles.get(0).getName();
		 System.out.println("name1"+name1);
		String rootPath = System.getProperty("user.dir");
		System.out.println("rootPath" + rootPath);
		File dir = new File(rootPath + File.separator + "src\\main\\webapp\\WEB-INF\\uploaded");
		if (!dir.exists())
			dir.mkdirs();

		// Create the file on server
		File serverFile = new File(dir.getAbsolutePath() + File.separator + name1);

		System.out.println("Server File Location=" + serverFile.getAbsolutePath());
		String path = serverFile.getAbsolutePath();
		String result = uploadService.uploadHolidayCalendarInformation(path);
		//String result = uploadService.uploadKioskCMFInformation(path);
		//String result = uploadService.uploadKioskInformation(path);
	//	String result = uploadService.uploadCBSbrhmInformation(path);
		//modelAndView.addObject("holidayCalendarStatus", result);
		
		ResponseEntity<String> entity = ResponseEntity.ok(result);
		return entity;
	}
// 2  uploadKioskCMFInformation 
	@RequestMapping(value = "uploadKioskDetails", method = RequestMethod.POST)
	public ResponseEntity<String> uploadKioskDetails(@RequestParam("KioskFile") List<MultipartFile> files) {
		List<FileInfo> uploadedFiles = new ArrayList<FileInfo>();
		System.out.println("files"+files);
		
		if (!files.isEmpty()) {
			try {
				for (MultipartFile file : files) {
					String path = context.getRealPath("/WEB-INF/uploaded") + File.separator
							+ file.getOriginalFilename();
					File destinationFile = new File(path);
					file.transferTo(destinationFile);
					uploadedFiles.add(new FileInfo(destinationFile.getName(), path));
					System.out.println("uploadedFiles" + uploadedFiles);
					System.out.println("name"+destinationFile.getName());	
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		ModelAndView modelAndView = new ModelAndView("upload");
		modelAndView.addObject("files", uploadedFiles);
		 String name1 = uploadedFiles.get(0).getName();
		 System.out.println("name1"+name1);
		String rootPath = System.getProperty("user.dir");
		System.out.println("rootPath" + rootPath);
		File dir = new File(rootPath + File.separator + "src\\main\\webapp\\WEB-INF\\uploaded");
		if (!dir.exists())
			dir.mkdirs();
		File serverFile = new File(dir.getAbsolutePath() + File.separator + name1);

		System.out.println("Server File Location=" + serverFile.getAbsolutePath());
		String path = serverFile.getAbsolutePath();
		String result = uploadService.uploadKioskCMFInformation(path);
		ResponseEntity<String> entity = ResponseEntity.ok(result);
		return entity;
	}
	
// 3 KioskInformation
	@RequestMapping(value = "uploadKioskCMF", method = RequestMethod.POST)
	public ResponseEntity<String> uploadKioskInformation(@RequestParam("CMFFile") List<MultipartFile> files) {
		List<FileInfo> uploadedFiles = new ArrayList<FileInfo>();	
		System.out.println("files"+files);
		if (!files.isEmpty()) {
			try {
				for (MultipartFile file : files) {
					String path = context.getRealPath("/WEB-INF/uploaded") + File.separator
							+ file.getOriginalFilename();
					File destinationFile = new File(path);
					file.transferTo(destinationFile);
					uploadedFiles.add(new FileInfo(destinationFile.getName(), path));
					System.out.println("uploadedFiles" + uploadedFiles);
					System.out.println("name"+destinationFile.getName());
						
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

		ModelAndView modelAndView = new ModelAndView("upload");
		modelAndView.addObject("files", uploadedFiles);
		 String name1 = uploadedFiles.get(0).getName();
		 System.out.println("name1"+name1);
		String rootPath = System.getProperty("user.dir");// c
		System.out.println("rootPath" + rootPath);
		File dir = new File(rootPath + File.separator + "src\\main\\webapp\\WEB-INF\\uploaded");
		if (!dir.exists())
			dir.mkdirs();

		// Create the file on server
		File serverFile = new File(dir.getAbsolutePath() + File.separator + name1);

		System.out.println("Server File Location=" + serverFile.getAbsolutePath());
		String path = serverFile.getAbsolutePath();
		
		String result = uploadService.uploadKioskInformation(path);

		
		ResponseEntity<String> entity = ResponseEntity.ok(result);
		return entity;
	}
// 4 uploadCBSbrhm
	@RequestMapping(value = "uploadCBSbrhm", method = RequestMethod.POST)
	public ResponseEntity<String> uploadCBSbrhm(@RequestParam("BMFile") List<MultipartFile> files) {
		List<FileInfo> uploadedFiles = new ArrayList<FileInfo>();	
		System.out.println("files"+files);
		if (!files.isEmpty()) {
			try {
				for (MultipartFile file : files) {
					String path = context.getRealPath("/WEB-INF/uploaded") + File.separator
							+ file.getOriginalFilename();
					File destinationFile = new File(path);
					file.transferTo(destinationFile);
					uploadedFiles.add(new FileInfo(destinationFile.getName(), path));
					System.out.println("uploadedFiles" + uploadedFiles);
					System.out.println("name"+destinationFile.getName());
						
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

		ModelAndView modelAndView = new ModelAndView("upload");
		modelAndView.addObject("files", uploadedFiles);
		 String name1 = uploadedFiles.get(0).getName();
		 System.out.println("name1"+name1);
		String rootPath = System.getProperty("user.dir");// c
		System.out.println("rootPath" + rootPath);
		File dir = new File(rootPath + File.separator + "src\\main\\webapp\\WEB-INF\\uploaded");
		if (!dir.exists())
			dir.mkdirs();

		// Create the file on server
		File serverFile = new File(dir.getAbsolutePath() + File.separator + name1);

		System.out.println("Server File Location=" + serverFile.getAbsolutePath());
		String path = serverFile.getAbsolutePath();
		
		String result = uploadService.uploadCBSbrhmInformation(path);
		//modelAndView.addObject("holidayCalendarStatus", result);
		
		ResponseEntity<String> entity = ResponseEntity.ok(result);
		return entity;
	}
}
