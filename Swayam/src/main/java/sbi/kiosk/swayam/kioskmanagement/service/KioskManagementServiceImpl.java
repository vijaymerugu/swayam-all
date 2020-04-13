package sbi.kiosk.swayam.kioskmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sbi.kiosk.swayam.common.dto.CmsCmfMappingDto;
import sbi.kiosk.swayam.common.dto.KioskBranchMasterUserDto;
import sbi.kiosk.swayam.common.dto.UserKioskMappingDeMapperDto;
import sbi.kiosk.swayam.common.dto.UserKioskMappingDto;
import sbi.kiosk.swayam.common.entity.BranchMaster;
import sbi.kiosk.swayam.common.entity.CmsCmfMapping;
import sbi.kiosk.swayam.common.entity.KioskBranchMaster;
import sbi.kiosk.swayam.common.entity.User;
import sbi.kiosk.swayam.common.entity.UserKioskMapping;
import sbi.kiosk.swayam.common.repository.CmsCmfMappingRepository;
import sbi.kiosk.swayam.common.repository.KioskBranchMasterRepositoryPaging;
import sbi.kiosk.swayam.common.repository.KioskMasterRepository;
import sbi.kiosk.swayam.common.repository.UserRepository;
import sbi.kiosk.swayam.kioskmanagement.repository.BranchMasterRepository;
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
	
	@Autowired
	KioskBranchMasterRepositoryPaging kioskBranchMasterRepositoryPaging;
	
	@Autowired
	BranchMasterRepository branchMasterRepository;

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
			userKioskMappingDto.setPfId(username);
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
	
	@Override
	public List<UserKioskMappingDeMapperDto> getKiosksForUser(String pfId){
		List<UserKioskMappingDeMapperDto> dtoList = new ArrayList<UserKioskMappingDeMapperDto>();
		List<UserKioskMapping> userKioskMappingList = userKioskMappingRepository.findByPfId(pfId);
		User usr = userRepository.findByPfId(pfId);
		int i =0;
		for(UserKioskMapping mapping:userKioskMappingList){
			UserKioskMappingDeMapperDto userKioskMappingDeMapperDto = new UserKioskMappingDeMapperDto();
			
			KioskBranchMaster kioskBranchMasterEntity = kioskMasterRepository.findKioskByKioskId(mapping.getKioskId());
			userKioskMappingDeMapperDto.setUsername(usr.getUsername());
			userKioskMappingDeMapperDto.setPfId(mapping.getPfId());
			userKioskMappingDeMapperDto.setKioskId(kioskBranchMasterEntity.getKioskId().toString());
			userKioskMappingDeMapperDto.setVendor(kioskBranchMasterEntity.getVendor());
			userKioskMappingDeMapperDto.setInstallationStatus(kioskBranchMasterEntity.getInstallationStatus());
			userKioskMappingDeMapperDto.setNo(String.valueOf(++i));
			dtoList.add(userKioskMappingDeMapperDto);
		}
		return dtoList;
	}
	
	@Override
	@Transactional
	public List<UserKioskMappingDeMapperDto> deleteUserKioskMapping(List<UserKioskMappingDeMapperDto> dtoList){
		
		for(UserKioskMappingDeMapperDto dto:dtoList){			
			userKioskMappingRepository.deleteByKioskId(dto.getKioskId());
			KioskBranchMaster kioskBranchMasterEntity = kioskMasterRepository.findKioskByKioskId(dto.getKioskId());
			//dto.setUsername(username);
			//userKioskMappingDeMapperDto.setKioskId(kioskBranchMasterEntity.getKioskId().toString());
			dto.setVendor(kioskBranchMasterEntity.getVendor());
			dto.setInstallationStatus(kioskBranchMasterEntity.getInstallationStatus());
			//userKioskMappingDeMapperDto.setNo(String.valueOf(++i));			
		}
		return dtoList;
	}
	
	@Override
    public Page<KioskBranchMasterUserDto> findPaginated(int page, int size) {	 	 
	 
	 Page<KioskBranchMasterUserDto> entities = 
			 kioskBranchMasterRepositoryPaging.findAll(PageRequest.of(page, size))
			 .map(KioskBranchMasterUserDto::new);
	 
	 for(KioskBranchMasterUserDto dto:entities){
		 UserKioskMapping us = userKioskMappingRepository.findByKioskId(String.valueOf(dto.getKioskId()));
		 
		 if(us !=null && us.getPfId() !=null && us.getPfId() !=""){
			 dto.setPfId(us.getPfId());
			 User usr = userRepository.findByPfId(us.getPfId());
			 if(usr !=null && usr.getUsername() !=null && usr.getUsername() !=""){
				 dto.setUsername(usr.getUsername());
			 }
		 }
		 
		 if(dto.getBranchCode() !=null){
			 BranchMaster branchMaster	 = branchMasterRepository.findByBranchCode(dto.getBranchCode());
			 if(branchMaster !=null && branchMaster.getCircle() !=null && branchMaster.getCircle() !=""){
				 dto.setCircle(branchMaster.getCircle());
			 }
		 }
	 }
	 
	 
		
	 //Page<UserManagementDto> pageDto = new PageImpl<UserManagementDto>(userList, PageRequest.of(page, size), userList.getSize());
	 	return entities;
    }
	
	@Override
	public KioskBranchMasterUserDto getKiosksFromKioskBranchMasterByKioskId(String kioskId){		
		
		KioskBranchMaster entity = 
				kioskMasterRepository.findKioskByKioskId(kioskId);
		KioskBranchMasterUserDto dto = new KioskBranchMasterUserDto(entity);
		if(entity.getBranchCode() !=null){
		 BranchMaster branchMaster	 = branchMasterRepository.findByBranchCode(entity.getBranchCode());
		 if(branchMaster !=null && branchMaster.getCircle() !=null && branchMaster.getCircle() !=""){
			 dto.setCircle(branchMaster.getCircle());
		 }
		}
		
		return dto;
	}
	
	@Override
	public void saveSingleUserKioskMapping(String pfId, String kioskId) {
		
			UserKioskMappingDto userKioskMappingDto = new UserKioskMappingDto();
			userKioskMappingDto.setPfId(pfId);
			userKioskMappingDto.setKioskId(kioskId);			
		
			UserKioskMapping mapping = new UserKioskMapping(userKioskMappingDto);
		
			userKioskMappingRepository.save(mapping);

	}


}
