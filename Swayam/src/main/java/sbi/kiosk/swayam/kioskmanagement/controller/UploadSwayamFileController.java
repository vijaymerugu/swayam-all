package sbi.kiosk.swayam.kioskmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.kioskmanagement.service.UploadService;

@RestController
@RequestMapping("/")
public class UploadSwayamFileController {
	
	@Autowired
	private  UploadService uploadService;
	
	@GetMapping("/km/upload")
	public ModelAndView UploadPage() {
		System.out.println("inside upload cotroller........");
		ModelAndView view=new ModelAndView();
		
		view.setViewName("upload");
		return view;
	}
	
	@GetMapping("/uploadKiosk")
	public ModelAndView uploadKiosk() {
		System.out.println("inside kiosk upload method........");
		ModelAndView view=new ModelAndView();
		String result=uploadService.uploadKioskInformation();
		System.out.println(result);
		view.setViewName("upload");
		return view;
	}
	
	@GetMapping("/uploadCBSbrhm")
	public ModelAndView uploadCBSbrhmInformation() {
		System.out.println("inside kiosk upload method........");
		ModelAndView view=new ModelAndView();
		String result=uploadService.uploadCBSbrhmInformation();
		view.setViewName("upload");
		return view;
	}

}
