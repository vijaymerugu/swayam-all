package sbi.kiosk.swayam.kioskmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.common.dto.CmsCmfMappingDto;
import sbi.kiosk.swayam.common.dto.UserKioskMappingDto;
import sbi.kiosk.swayam.common.entity.CmsCmfMapping;
import sbi.kiosk.swayam.common.entity.User;
import sbi.kiosk.swayam.common.entity.UserKioskMapping;
import sbi.kiosk.swayam.common.repository.CmsCmfMappingRepository;
import sbi.kiosk.swayam.common.repository.KioskMasterRepository;
import sbi.kiosk.swayam.common.repository.UserRepository;
import sbi.kiosk.swayam.kioskmanagement.repository.UserKioskMappingRepository;

@Service
public class KioskManagementServiceImpl implements KioskManagementService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	KioskMasterRepository kioskMasterRepository;

	@Autowired
	UserKioskMappingRepository userKioskMappingRepository;
	
	@Autowired
	CmsCmfMappingRepository cmsCmfMappingRepository;

	@Override
	public List<User> fetchAllUsersByCircleAdmin(String username, String circle) {

		return (List<User>) userRepository.fetchAllUsersByCircleAdmin(username, circle);
	}

	@Override
	public List<String> fetchAllKiosksByCircleAndNotInUserKioskMapping(String circle) {

		return kioskMasterRepository.fetchAllKiosksByCircleAndNotInUserKioskMapping(circle);
	}

	@Override
	public void saveUserKioskMapping(String username, List<String> kioskIdList) {

		List<UserKioskMapping> userKioskMappingList = new ArrayList<>();
		List<UserKioskMappingDto> userKioskMappingDtoList = new ArrayList<>();

		for (String kioskId : kioskIdList) {
			UserKioskMappingDto userKioskMappingDto = new UserKioskMappingDto();
			userKioskMappingDto.setUsername(username);
			userKioskMappingDto.setKioskId(kioskId);
			userKioskMappingDtoList.add(userKioskMappingDto);
		}

		if (userKioskMappingDtoList != null && userKioskMappingDtoList.size() > 0) {
			for (UserKioskMappingDto userKioskMappingDto : userKioskMappingDtoList) {
				userKioskMappingList.add(new UserKioskMapping(userKioskMappingDto));
			}
		}

		userKioskMappingRepository.saveAll(userKioskMappingList);

	}
	
	
	@Override
	public List<User> fetchAllCmsUsersByCircleAdmin(String username, String circle) {

		return (List<User>) userRepository.fetchAllCmsUsersByCircleAdmin(username, circle);
	}

	@Override
	public List<User> fetchAllCmfUsersByCircleAndInUserKioskMapping(String circle) {

		return userRepository.fetchAllCmfUsersByCircleAndInUserKioskMapping(circle);
	}

	@Override
	public void saveCmsCmfUserMapping(String cmsUsername, List<String> cmfUserIdIdList) {

		List<CmsCmfMapping> CmsCmfMappingList = new ArrayList<>();
		List<CmsCmfMappingDto> cmsCmfMappingDtoList = new ArrayList<>();

		for (String cmfUsername : cmfUserIdIdList) {
			CmsCmfMappingDto cmsCmfMappingDto = new CmsCmfMappingDto();
			cmsCmfMappingDto.setCmsUsername(cmsUsername);
			cmsCmfMappingDto.setCmfUsername(cmfUsername);
			cmsCmfMappingDtoList.add(cmsCmfMappingDto);
		}

		if (cmsCmfMappingDtoList != null && cmsCmfMappingDtoList.size() > 0) {
			for (CmsCmfMappingDto userKioskMappingDto : cmsCmfMappingDtoList) {
				CmsCmfMappingList.add(new CmsCmfMapping(userKioskMappingDto));
			}
		}

		cmsCmfMappingRepository.saveAll(CmsCmfMappingList);

	}

}
