package sbi.kiosk.swayam.kioskmanagement.service;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	
	public String uploadKioskInformation(String path);

	public String uploadCBSbrhmInformation(String path);
	
	public String uploadHolidayCalendarInformation(String path);
	
	public String uploadKioskCMFInformation(String path);
	
	public String uploadInvVendorInformation(String path);

	public String uploadKioskCMFInformationNew(InputStream inputStream);

	public String uploadInvVendorInformationNew(InputStream inputStream);
	

}
