package sbi.kiosk.swayam.kioskmanagement.controller;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.kioskmanagement.service.UploadService;

@RestController
@RequestMapping("/")
public class UploadSwayamFileController {
	
	@Autowired
	private  UploadService uploadService;
	
	
//	@Autowired 
//	HttpServletRequest request;
	 
	
	@GetMapping("km/upload")
	public ModelAndView UploadPage() {
		System.out.println("inside upload cotroller........");
		ModelAndView view=new ModelAndView();
		
		view.setViewName("upload");
		return view;
	}
	
	@PostMapping("uploadKiosk")
	public String uploadKiosk(HttpServletRequest request) throws ServletException {
		System.out.println("inside kiosk upload method........");
        //ModelAndView view=new ModelAndView();
		
		/*
		 * String fileName = request.getParameter("myFile"); String r=
		 * request.getRealPath(fileName); String r1 = request.getServletPath();
		 * 
		 * File file = new File(fileName); String name = file.getAbsolutePath();
		 */
		//String filePath = request.getPathInfo();
		/*
		 * if(fileName == null || fileName.equals("")){ throw new
		 * ServletException("File Name can't be null or empty"); } File file = new
		 * File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+
		 * fileName); if(!file.exists()){ throw new
		 * ServletException("File doesn't exists on server."); }
		 * System.out.println("File location on server::"+file.getAbsolutePath());
		 */
	    
	    
	    
	    
		String result=uploadService.uploadKioskInformation();
		/*
		 * view.addObject("kioskUploadStatus", result); System.out.println(result);
		 * view.setViewName("upload"); return view;
		 */
		return result;
	}
	
	@PostMapping("uploadCBSbrhm")
	public String uploadCBSbrhmInformation() {
		System.out.println("inside upload CBS Information method........");
		//ModelAndView view=new ModelAndView();
		String result=uploadService.uploadCBSbrhmInformation();
		/*
		 * view.addObject("CBSBrhmStatus", result); 
		 * view.setViewName("upload");
		 */
		return result;
	}
	
	// By Pankul 28-04-2020-----------STARTS---------
	
	@PostMapping("uploadHolidayCalendar")
	public String uploadHolidayCalendarInformation() {
		System.out.println("inside upload Holiday Calendar method........");
		//ModelAndView view=new ModelAndView();
		String result=uploadService.uploadHolidayCalendarInformation();
		/*
		 * view.addObject("holidayCalendarStatus", result); 
		 * view.setViewName("upload");
		 */
		return result;
	}
	
	@PostMapping("uploadKioskCMF")
	public String uploadKioskCMFInformation() {
		System.out.println("inside upload Kiosk CMF method........");
		//ModelAndView view=new ModelAndView();
		String result=uploadService.uploadKioskCMFInformation();
		/*
		 * view.addObject("KioskCMFStatus", result); 
		 * view.setViewName("upload");
		 */
		return result;
	}
	
	// -------By Pankul END---------

}
