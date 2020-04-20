package sbi.kiosk.swayam.kioskmanagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import sbi.kiosk.swayam.common.dto.KioskBranchMasterUserDto;
import sbi.kiosk.swayam.common.dto.UserKioskMappingDeMapperDto;
import sbi.kiosk.swayam.common.entity.User;

public interface KioskManagementService {
	
	List<User> fetchAllUsersByCircleAdmin(String username, String circle);
	
	List<String> fetchAllKiosksByCircleAndNotInUserKioskMapping(String circle);
	
	void saveUserKioskMapping(String username, List<String> kioskIdList);
	
	List<User> fetchAllCmsUsersByCircleAdmin(String username, String circle);
	
	List<User> fetchAllCmfUsersByCircleAndInUserKioskMapping(String circle);
	
	void saveCmsCmfUserMapping(String username, List<String> kioskIdList);
	
	List<UserKioskMappingDeMapperDto> getKiosksForUser(String username);
	
	List<UserKioskMappingDeMapperDto> deleteUserKioskMapping(List<UserKioskMappingDeMapperDto> dto);
	
	Page<KioskBranchMasterUserDto> findPaginated(int page, int size);
	
	KioskBranchMasterUserDto getKiosksFromKioskBranchMasterByKioskId(String kioskId);
	
	void saveSingleUserKioskMapping(String username, String kioskId);

	Page<KioskBranchMasterUserDto> findPaginatedCount(int page, int size, String type);

	Map<String, Integer> findAllKioskMasterCount();
}
