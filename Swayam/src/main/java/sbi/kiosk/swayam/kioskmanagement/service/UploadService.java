package sbi.kiosk.swayam.kioskmanagement.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	
	public String uploadKioskInformation(String path);

	public String uploadCBSbrhmInformation(String path);
	
	public String uploadHolidayCalendarInformation(String path);
	
	public String uploadKioskCMFInformation(String path);
	

}
