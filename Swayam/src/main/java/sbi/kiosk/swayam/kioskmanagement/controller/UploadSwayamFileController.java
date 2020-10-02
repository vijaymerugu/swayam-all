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
		logger.info("files" + files);

		logger.info("I m inside uploadHolidayCalendar");

		if (!files.isEmpty()) {
			try {
				for (MultipartFile file : files) {

					/*
					 * String orgFileName = file.getOriginalFilename();
					 * logger.info("1.File Name========== "+orgFileName); String path =
					 * "/resources/upload/" + orgFileName ;
					 */

					/*
					 * String fileName = file.getOriginalFilename(); Path paths =
					 * Paths.get(fileName); Path orgFileName = paths.getFileName();
					 * 
					 * logger.info("1.File Name========== "+orgFileName);
					 */
					/*
					 * commented temporarily for testing String path =
					 * context.getRealPath("/resources/upload") + File.separator + orgFileName;
					 */
					// String path = "/resources/upload/" + orgFileName;
					/////////////////////////////////
					/*
					 * Path path1 = Paths.get(uploadpath + file.getOriginalFilename()); String path
					 * = path1.toString(); logger.info("1.File Name========== "+orgFileName);
					 */
					/////////////////////////
					// logger.info("2.contextPath============
					///////////////////////// "+context.getRealPath("/resources/upload"));

					/*******************************************/
					/*
					 * String orgFileName = file.getOriginalFilename();
					 * logger.info("1.File Name========== " + orgFileName); String path =
					 * "/resources/upload/" + orgFileName;
					 *//*************************************************//*
																			 * logger.info("2.Path============ " +
																			 * path); File destinationFile = new
																			 * File(path);
																			 * 
																			 * logger.info("3.name============= " +
																			 * destinationFile.getName());
																			 * file.transferTo(destinationFile); logger.
																			 * info("4.File Transfer done!!!!!!!!!");
																			 * uploadedFiles.add(new
																			 * FileInfo(destinationFile.getName(),
																			 * path));
																			 * logger.info("5.uploadedFiles=========== "
																			 * + uploadedFiles);
																			 */
					
					  logger.info("1.File Name========== "+file.getOriginalFilename());
					  String path = uploadpath  + file.getOriginalFilename();
					  logger.info("2.Path============ "+path);
					  File destinationFile = new File(path); 
					  logger.info("3.name============= "+destinationFile.getName());
					  file.transferTo(destinationFile);
					  logger.info("4.File Transfer done!!!!!!!!!");
					  uploadedFiles.add(new FileInfo(destinationFile.getName(), path));
					  logger.info("5.uploadedFiles=========== " + uploadedFiles);

				}

			} catch (Exception e) {
				logger.error(e.getMessage());
			}

		}

		ModelAndView modelAndView = new ModelAndView("upload");
		modelAndView.addObject("files", uploadedFiles);
		String name1 = uploadedFiles.get(0).getName();
		logger.info("6.name1====== " + name1);
		//logger.info("7.uploadpath=========== " + uploadpath);
		File dir = new File(uploadpath);
		
		if (!dir.exists())
			dir.mkdirs();

		// Create the file on server
		File serverFile = new File(dir.getAbsolutePath() + File.separator + name1);

		logger.info("7.Dir AbsolutePath=============" + dir.getAbsolutePath());
		String path = serverFile.getAbsolutePath();
		logger.info("7.Server File path===========" + path);
		String result = uploadService.uploadHolidayCalendarInformation(path);
		// String result = uploadService.uploadKioskCMFInformation(path);
		// String result = uploadService.uploadKioskInformation(path);
		// String result = uploadService.uploadCBSbrhmInformation(path);
		// modelAndView.addObject("holidayCalendarStatus", result);
		logger.info("8.Result part done: " + result);
		ResponseEntity<String> entity = ResponseEntity.ok(result);
		logger.info("9.Transfer to entity");
		return entity;
	}

// 2 KioskCMF 
	@RequestMapping(value = "uploadKioskDetails", method = RequestMethod.POST)
	// @PreAuthorize("hasPermission('UPDuploadKioskDetails','CREATE')")
	public ResponseEntity<String> uploadKioskDetails(@RequestParam("KioskFile") List<MultipartFile> files) {
		List<FileInfo> uploadedFiles = new ArrayList<FileInfo>();
		logger.info("files" + files);

		logger.info("I m inside uploadKioskDetails");

		if (!files.isEmpty()) {
			try {
				for (MultipartFile file : files) {
					/*
					 * String path = context.getRealPath("/resources/upload") + File.separator +
					 * file.getOriginalFilename();
					 */
					/*
					 * String fileName = file.getOriginalFilename(); Path paths =
					 * Paths.get(fileName); Path orgFileName = paths.getFileName();
					 * logger.info("1.File Name========== "+orgFileName);
					 */
					/*
					 * commented temporarily for testing String path =
					 * context.getRealPath("/resources/upload") + File.separator + orgFileName;
					 */
					// String path = "/resources/upload/" + orgFileName;
					// logger.info("2.contextPath=============
					// "+context.getRealPath("/resources/upload"));
					/*******************************************/
					/*
					 * String orgFileName_kiosk = file.getOriginalFilename();
					 * logger.info("1.File Name========== "+orgFileName_kiosk); // String path =
					 * "/resources/upload/" + orgFileName_kiosk ; String path = uploadpath +
					 * orgFileName_kiosk ;
					 *//*************************************************//*
																			 * logger.info("2.Path============ "+path);
																			 * File destinationFile = new File(path);
																			 * logger.info("3.name============= "
																			 * +destinationFile.getName());
																			 * file.transferTo(destinationFile); logger.
																			 * info("4.File Transfer done!!!!!!!!!");
																			 * uploadedFiles.add(new
																			 * FileInfo(destinationFile.getName(),
																			 * path));
																			 * logger.info("5.uploadedFiles=========== "
																			 * + uploadedFiles);
																			 */
					logger.info("1.File Name========== " + file.getOriginalFilename());
					String path = uploadpath + file.getOriginalFilename();
					logger.info("2.Path============ " + path);
					File destinationFile = new File(path);
					logger.info("3.name============= " + destinationFile.getName());
					file.transferTo(destinationFile);
					logger.info("4.File Transfer done!!!!!!!!!");
					uploadedFiles.add(new FileInfo(destinationFile.getName(), path));
					logger.info("5.uploadedFiles=========== " + uploadedFiles);

				}

			} catch (Exception e) {
				logger.error(e.getMessage());
			}

		}
		ModelAndView modelAndView = new ModelAndView("upload");
		modelAndView.addObject("files", uploadedFiles);
		String name1 = uploadedFiles.get(0).getName();
		logger.info("6.name1============ " + name1);
		// logger.info("7.uploadpath=========== "+uploadpath);
		File dir = new File(uploadpath);
		// new File(rootPath + File.separator + "src\\main\\webapp\\WEB-INF\\uploaded");
		if (!dir.exists())
			dir.mkdirs();
		File serverFile = new File(dir.getAbsolutePath() + File.separator + name1);
		logger.info("7.Dir AbsolutePath=============" + dir.getAbsolutePath());
		
		String path = serverFile.getAbsolutePath();
		logger.info("8.Server File path========== " + serverFile.getAbsolutePath());
		String result = uploadService.uploadKioskCMFInformation(path);
		logger.info("9.Result part done: " + result);
		ResponseEntity<String> entity = ResponseEntity.ok(result);
		logger.info("9.Transfer to entity");
		return entity;
	}

// 3 KioskInformation
	@RequestMapping(value = "uploadKioskCMF", method = RequestMethod.POST)
//	@PreAuthorize("hasPermission('UPDuploadKioskCMF','CREATE')")
	public ResponseEntity<String> uploadKioskInformation(@RequestParam("CMFFile") List<MultipartFile> files) {
		logger.info("==uploadKioskInformation================");
		List<FileInfo> uploadedFiles = new ArrayList<FileInfo>();

		logger.info("I m inside uploadKioskCMF");
		logger.info("files" + files);
		if (!files.isEmpty()) {
			try {
				for (MultipartFile file : files) {
					/*
					 * String path = context.getRealPath("/resources/upload") + File.separator +
					 * file.getOriginalFilename();
					 */
					/*
					 * String fileName = file.getOriginalFilename(); Path paths =
					 * Paths.get(fileName); Path orgFileName = paths.getFileName();
					 * logger.info("1.File Name========== "+orgFileName);
					 */
					/*
					 * commented temporarily for testing String path =
					 * context.getRealPath("/resources/upload") + File.separator + orgFileName;
					 */

					logger.info("1.File Name========== " + file.getOriginalFilename());
					String path = uploadpath + file.getOriginalFilename();
					logger.info("2.Path============ " + path);
					File destinationFile = new File(path);
					logger.info("3.name============= " + destinationFile.getName());
					file.transferTo(destinationFile);
					logger.info("4.File Transfer done!!!!!!!!!");
					uploadedFiles.add(new FileInfo(destinationFile.getName(), path));
					logger.info("5.uploadedFiles=========== " + uploadedFiles);

					/*******************************************/
					/*
					 * String orgFileName_kioskCMF = file.getOriginalFilename();
					 * logger.info("1.File Name========== "+orgFileName_kioskCMF); String path =
					 * "/resources/upload/" + orgFileName_kioskCMF ;
					 */
					/*************************************************/
					/*
					 * logger.info("2.Path============ "+path); File destinationFile = new
					 * File(path); logger.info("3.name============= "+destinationFile.getName());
					 * file.transferTo(destinationFile);
					 * logger.info("4.File Transfer done!!!!!!!!!");
					 */

				}

			} catch (Exception e) {
				logger.error(e.getMessage());
			}

		}

		ModelAndView modelAndView = new ModelAndView("upload");
		modelAndView.addObject("files", uploadedFiles);
		String name1 = uploadedFiles.get(0).getName();
		logger.info("6.=====name1========" + name1);
		// logger.info("7.uploadpath============= "+uploadpath);
		File dir = new File(uploadpath);

		if (!dir.exists())
			dir.mkdirs();

		// Create the file on server
		File serverFile = new File(dir.getAbsolutePath() + File.separator + name1);
		logger.info("7.Dir AbsolutePath=============" + dir.getAbsolutePath());
		String path = serverFile.getAbsolutePath();
		logger.info("8.Server File Location===========" + path);
		String result = uploadService.uploadKioskInformation(path);
		logger.info("9.Result part done: " + result);

		ResponseEntity<String> entity = ResponseEntity.ok(result);
		logger.info("9.Transfer to entity");
		return entity;
	}

// 4 uploadCBSbrhm
	@RequestMapping(value = "uploadCBSbrhm", method = RequestMethod.POST)
	// @PreAuthorize("hasPermission('UPDuploadCBSbrhm','CREATE')")
	public ResponseEntity<String> uploadCBSbrhm(@RequestParam("BMFile") List<MultipartFile> files) {
		List<FileInfo> uploadedFiles = new ArrayList<FileInfo>();
		logger.info("files" + files);
		logger.info("i m inside uploadCBSbrhm");
		if (!files.isEmpty()) {
			try {
				for (MultipartFile file : files) {
					/*
					 * String path = context.getRealPath("/resources/upload") + File.separator +
					 * file.getOriginalFilename();
					 */
					/*
					 * String fileName = file.getOriginalFilename(); Path paths =
					 * Paths.get(fileName); Path orgFileName = paths.getFileName();
					 * logger.info("1.File Name========== "+orgFileName);
					 */
					/*
					 * commented temporarily for testing String path =
					 * context.getRealPath("/resources/upload") + File.separator + orgFileName;
					 */

					/* String orgFileName_brmas = file.getOriginalFilename(); */
					logger.info("1.File Name========== " + file.getOriginalFilename());

					String path = uploadpath + file.getOriginalFilename();

					logger.info("2.Path============ " + path);
					File destinationFile = new File(path);
					logger.info("3.name============= " + destinationFile.getName());
					file.transferTo(destinationFile);
					logger.info("4.File Transfer done!!!!!!!!!");

					// String path =
					// "/home/webadmin/wls/oracle/config/domains/TEST/servers/AdminServer/tmp/_WL_user/SMT2/pl2f58/public/resources/upload/"
					// + orgFileName_brmas ;
					/*
					 * String path = uploadpath + orgFileName_brmas ; File destinationFile = new
					 * File(path);
					 */
					uploadedFiles.add(new FileInfo(destinationFile.getName(), path));
					logger.info("5.uploadedFiles=========== " + uploadedFiles);

				}

			} catch (Exception e) {
				logger.error(e.getMessage());
			}

		}

		ModelAndView modelAndView = new ModelAndView("upload");
		modelAndView.addObject("files", uploadedFiles);
		String name1 = uploadedFiles.get(0).getName();
		logger.info("6.name1=======" + name1);
		// logger.info("7.uploadpath=============== "+uploadpath);
		File dir = new File(uploadpath);
		// File dir = new
		// File("/home/webadmin/wls/oracle/config/domains/TEST/servers/AdminServer/tmp/_WL_user/SMT2/pl2f58/public/resources/upload/");
		if (!dir.exists())
			dir.mkdirs();

		// Create the file on server
		File serverFile = new File(dir.getAbsolutePath() + File.separator + name1);

		logger.info("7.Dir AbsolutePath=============" + dir.getAbsolutePath());
		String path = serverFile.getAbsolutePath();
		// String path =
		// "/home/webadmin/wls/oracle/config/domains/TEST/servers/AdminServer/tmp/_WL_user/SMT2/pl2f58/public/resources/upload/"+
		// name1;
		// String path = uploadpath + name1;
		logger.info("8.Server File Location===========" + path);
		String result = uploadService.uploadCBSbrhmInformation(path);
		logger.info("9.Result part done: " + result);

		ResponseEntity<String> entity = ResponseEntity.ok(result);
		logger.info("9.Transfer to entity");
		return entity;
	}
}
