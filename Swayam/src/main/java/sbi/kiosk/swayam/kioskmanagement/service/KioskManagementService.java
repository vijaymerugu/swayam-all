package sbi.kiosk.swayam.kioskmanagement.service;

import java.util.List;

import sbi.kiosk.swayam.common.entity.User;

public interface KioskManagementService {
	
	List<User> fetchAllUsersByCircleAdmin(String username, String circle);
	
	List<String> fetchAllKiosksByCircleAndNotInUserKioskMapping(String circle);
	
	void saveUserKioskMapping(String username, List<String> kioskIdList);
	
	List<User> fetchAllCmsUsersByCircleAdmin(String username, String circle);
	
	List<User> fetchAllCmfUsersByCircleAndInUserKioskMapping(String circle);
	
	void saveCmsCmfUserMapping(String username, List<String> kioskIdList);

}
