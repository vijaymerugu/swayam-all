package sbi.kiosk.swayam.kioskmanagement.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import sbi.kiosk.swayam.common.dto.CmsCmfMappingDto;
import sbi.kiosk.swayam.common.dto.KioskBranchMasterUserDto;
import sbi.kiosk.swayam.common.dto.KioskRegistrationDto;
import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.dto.UserKioskMappingDeMapperDto;
import sbi.kiosk.swayam.common.dto.UserKioskMappingDto;
import sbi.kiosk.swayam.common.dto.UserManagementDto;
import sbi.kiosk.swayam.common.entity.BranchMaster;
import sbi.kiosk.swayam.common.entity.CmsCmfMapping;
import sbi.kiosk.swayam.common.entity.KioskBranchMaster;
import sbi.kiosk.swayam.common.entity.KioskRegistration;
import sbi.kiosk.swayam.common.entity.User;
import sbi.kiosk.swayam.common.entity.UserKioskMapping;
import sbi.kiosk.swayam.common.repository.CmsCmfMappingRepository;
import sbi.kiosk.swayam.common.repository.KioskBranchMasterRepositoryPaging;
import sbi.kiosk.swayam.common.repository.KioskMasterRepository;
import sbi.kiosk.swayam.common.repository.UserRepository;
import sbi.kiosk.swayam.kioskmanagement.repository.BranchMasterRepository;
import sbi.kiosk.swayam.kioskmanagement.repository.KioskRegistrationRepositoryPaging;
import sbi.kiosk.swayam.kioskmanagement.repository.UserKioskMappingRepository;

@Service
public class KioskManagementServiceImpl implements KioskManagementService {

	Logger logger = LoggerFactory.getLogger(KioskManagementServiceImpl.class);
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
	
	@Autowired
	sbi.kiosk.swayam.kioskmanagement.repository.kioskMasterManagementRepository kioskMasterManagementRepository;
	
	@Autowired
	KioskRegistrationRepositoryPaging kioskRegistrationRepositoryPaging;
	
	public static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }

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
		  
		  for(KioskBranchMasterUserDto dto:entities){ UserKioskMapping us =
		  userKioskMappingRepository.findByKioskId(String.valueOf(dto.getKioskId()));
		  
		  if(us !=null && us.getPfId() !=null && us.getPfId() !=""){
		  dto.setPfId(us.getPfId()); User usr =
		  userRepository.findByPfId(us.getPfId()); if(usr !=null && usr.getUsername()
		  !=null && usr.getUsername() !=""){ dto.setUsername(usr.getUsername());
		  dto.setPhoneNo(usr.getPhoneNo()); } }
		  
		  
		  if(dto.getBranchCode() !=null){ BranchMaster branchMaster =
		  branchMasterRepository.findByBranchCode(dto.getBranchCode()); if(branchMaster
		  !=null && branchMaster.getCircleName() !=null && branchMaster.getCircleName()
		  !=""){ dto.setCircle(branchMaster.getCircleName()); } }
		  
		  }
		 
		/*
		 * List<KioskRegistration> kioskList =
		 * kioskRegistrationRepositoryPaging.findAllByKiosk() ; Page<KioskRegistration>
		 * KioskRegistrationList = new PageImpl<KioskRegistration>(kioskList,
		 * PageRequest.of(page, size), kioskList.size());
		 * 
		 * Page<KioskRegistrationDto> entities =
		 * KioskRegistrationList.map(KioskRegistrationDto:: new);
		 */
	 //Page<UserManagementDto> pageDto = new PageImpl<UserManagementDto>(userList, PageRequest.of(page, size), userList.getSize());
	 	return entities;
    }
	@Override
    public Page<KioskBranchMasterUserDto> findPaginatedSearchNext(int page, int size, String searchText) {	 	 
	
		Page<KioskBranchMasterUserDto> entities =  kioskMasterManagementRepository.findAllByKioskIdSearchText(searchText,PageRequest.of(page, size)).map(KioskBranchMasterUserDto::new);
	 for(KioskBranchMasterUserDto dto:entities){
		 UserKioskMapping us = userKioskMappingRepository.findByKioskId(String.valueOf(dto.getKioskId()));
		 
		 if(us !=null && us.getPfId() !=null && us.getPfId() !=""){
			 dto.setPfId(us.getPfId());
			 User usr = userRepository.findByPfId(us.getPfId());
			 if(usr !=null && usr.getUsername() !=null && usr.getUsername() !=""){
				 dto.setUsername(usr.getUsername());
				 dto.setPhoneNo(usr.getPhoneNo());
			 }
		 }
		 
			
			  if(dto.getBranchCode() !=null){ BranchMaster branchMaster =
			  branchMasterRepository.findByBranchCode(dto.getBranchCode()); if(branchMaster
			  !=null && branchMaster.getCircleName() !=null && branchMaster.getCircleName() !=""){
			  dto.setCircle(branchMaster.getCircleName()); } }
			 
	 }
	 
	 	return entities;
    }
	
	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public Page<KioskBranchMasterUserDto> findAssingedPaginated(int
	 * page, int size,String type) {
	 * 
	 * 
	 * logger.info("1.Going to find entity of KioskBranchMasterUserDto ");
	 * Page<KioskBranchMasterUserDto> entities =
	 * kioskBranchMasterRepositoryPaging.findAll(PageRequest.of(page, size))
	 * .map(KioskBranchMasterUserDto::new);
	 * logger.info("2.fetched all entity of KioskBranchMasterUserDto ");
	 * logger.info("3.Total kiosk avaialable: " + entities.getSize());
	 * List<KioskBranchMaster> kioskEntities1 =
	 * kioskMasterRepository.findAssignedKioskId(); List<KioskBranchMasterUserDto>
	 * entities1 = new ArrayList<KioskBranchMasterUserDto>();
	 * 
	 * // int index=0; for(KioskBranchMaster dto:kioskEntities1){
	 * KioskBranchMasterUserDto postDto = new KioskBranchMasterUserDto();
	 * logger.info("4.inside for loop of KioskBranchMaster ");
	 * 
	 * if(dto.getBranchCode() !=null){ BranchMaster branchMaster =
	 * branchMasterRepository.findByBranchCode(dto.getBranchCode()); if(branchMaster
	 * !=null && branchMaster.getCircleName() !=null && branchMaster.getCircleName()
	 * !=""){ dto.setCircle(branchMaster.getCircleName()); } }
	 * 
	 * 
	 * postDto.setAddress(dto.getAddress());;
	 * postDto.setBranchCode(dto.getBranchCode());
	 * postDto.setBranchName(dto.getBranchName());
	 * postDto.setCircle(dto.getCircle()); postDto.setKioskId(dto.getKioskId());
	 * postDto.setVendor(dto.getVendor());
	 * postDto.setInstallationStatus(dto.getInstallationStatus());
	 * postDto.setId(dto.getId());
	 * postDto.setInstallationDate(dto.getInstallationDate());
	 * postDto.setInstallationType(dto.getInstallationType());
	 * postDto.setKioskIp(dto.getKioskIp());
	 * postDto.setKioskMacAddress(dto.getKioskMacAddress());
	 * postDto.setKioskSerialNo(dto.getKioskSerialNo());
	 * postDto.setLocation(dto.getLocation()); postDto.setOs(dto.getOs());
	 * postDto.setSiteType(dto.getSiteType()); entities1.add(postDto);
	 * logger.info("5.Added to entities1 of KioskBranchMasterUserDto ");
	 * 
	 * } logger.info("6.Size of entities1 of KioskBranchMasterUserDto: "+
	 * entities1.size()); for(KioskBranchMasterUserDto dto:entities1) {
	 * 
	 * logger.info("User Kiosk Mapping data fetching start!!!!!!!");
	 * UserKioskMapping us =
	 * userKioskMappingRepository.findByKioskId(String.valueOf(dto.getKioskId()));
	 * logger.info("User Kiosk Mapping data fetched!!!!!!!"); if(us !=null &&
	 * us.getPfId() !=null && us.getPfId() !=""){ dto.setPfId(us.getPfId()); User
	 * usr = userRepository.findByPfId(us.getPfId()); if(usr !=null &&
	 * usr.getUsername() !=null && usr.getUsername() !=""){
	 * dto.setUsername(usr.getUsername()); } }
	 * logger.info("8.Set username to entities1 of KioskBranchMasterUserDto ");
	 * 
	 * } logger.info("9.Size of entities1 of KioskBranchMasterUserDto: "+
	 * entities1.size()); Page<KioskBranchMasterUserDto> pageDto = new
	 * PageImpl<KioskBranchMasterUserDto>(entities1, PageRequest.of(page, size),
	 * entities1.size());
	 * 
	 * 
	 * 
	 * //return entities; return pageDto; }
	 */
	
	/*
	 * @SuppressWarnings({ "unchecked" })
	 * 
	 * @Override public Page<KioskBranchMasterUserDto> findTobeAssingedPaginated(int
	 * page, int size) {
	 * 
	 * logger.info("1.Going to find entity of KioskBranchMasterUserDto ");
	 * 
	 * List<KioskBranchMaster> kioskEntities1 =
	 * kioskMasterRepository.findToBeAssignedKioskId();
	 * List<KioskBranchMasterUserDto> entities1 = new
	 * ArrayList<KioskBranchMasterUserDto>();
	 * logger.info("3.Total findToBeAssignedKioskId avaialable: " +
	 * kioskEntities1.size()); // KioskBranchMasterUserDto postDto = new
	 * KioskBranchMasterUserDto();
	 * 
	 * for(KioskBranchMaster dto:kioskEntities1){ KioskBranchMasterUserDto postDto =
	 * new KioskBranchMasterUserDto();
	 * 
	 * 
	 * if(dto.getBranchCode() !=null){ BranchMaster branchMaster =
	 * branchMasterRepository.findByBranchCode(dto.getBranchCode()); if(branchMaster
	 * !=null && branchMaster.getCircleName() !=null && branchMaster.getCircleName()
	 * !="") { dto.setCircle(branchMaster.getCircleName()); } }
	 * 
	 * postDto.setAddress(dto.getAddress());;
	 * postDto.setBranchCode(dto.getBranchCode());
	 * postDto.setBranchName(dto.getBranchName());
	 * postDto.setCircle(dto.getCircle()); postDto.setKioskId(dto.getKioskId());
	 * postDto.setVendor(dto.getVendor());
	 * postDto.setInstallationStatus(dto.getInstallationStatus());
	 * postDto.setId(dto.getId());
	 * postDto.setInstallationDate(dto.getInstallationDate());
	 * postDto.setInstallationType(dto.getInstallationType());
	 * postDto.setKioskIp(dto.getKioskIp());
	 * postDto.setKioskMacAddress(dto.getKioskMacAddress());
	 * postDto.setKioskSerialNo(dto.getKioskSerialNo());
	 * postDto.setLocation(dto.getLocation()); postDto.setOs(dto.getOs());
	 * postDto.setSiteType(dto.getSiteType()); entities1.add(postDto); //
	 * logger.info("5.Added to entities1 of KioskBranchMasterUserDto ");
	 * 
	 * } logger.info("6.Size of entities1 of KioskBranchMasterUserDto: "+
	 * entities1.size());
	 * 
	 * 
	 * // Page<KioskBranchMasterUserDto> pageDto = new
	 * PageImpl<KioskBranchMasterUserDto>(entities1, PageRequest.of(page, size),
	 * entities1.size());
	 * 
	 * Page<KioskBranchMasterUserDto> pageDto = new
	 * PageImpl<KioskBranchMasterUserDto>(entities1); //return entities; return
	 * pageDto; }
	 */
	@Override
    public Page<KioskBranchMasterUserDto> findAssingedPaginated(int page, int size, String type) {	 	 
		
		Page<KioskBranchMasterUserDto> entities = kioskMasterManagementRepository.findAllByInUserKiosk(PageRequest.of(page, size))
				 .map(KioskBranchMasterUserDto::new);
	 
	 for(KioskBranchMasterUserDto dto:entities){
			
			  UserKioskMapping us =
			  userKioskMappingRepository.findByKioskId(String.valueOf(dto.getKioskId()));
			  
			  if(us !=null && us.getPfId() !=null && us.getPfId() !=""){
			  dto.setPfId(us.getPfId()); User usr =
			  userRepository.findByPfId(us.getPfId()); if(usr !=null && usr.getUsername()
			  !=null && usr.getUsername() !=""){ dto.setUsername(usr.getUsername()); dto.setPhoneNo(usr.getPhoneNo());} }
			 
		 
			
			  if(dto.getBranchCode() !=null){ BranchMaster branchMaster =
			  branchMasterRepository.findByBranchCode(dto.getBranchCode()); if(branchMaster
			  !=null && branchMaster.getCircleName() !=null && branchMaster.getCircleName() !=""){
			  dto.setCircle(branchMaster.getCircleName()); } }
			 
	 }
	 

	 	return entities;
    }
	@Override
    public Page<KioskBranchMasterUserDto> findTobeAssingedPaginated(int page, int size, String type) {	 	 
		
		Page<KioskBranchMasterUserDto> entities = kioskMasterManagementRepository.findAllByNotInUserKiosk(PageRequest.of(page, size))
				 .map(KioskBranchMasterUserDto::new);
	 
	 for(KioskBranchMasterUserDto dto:entities){
			/*
			 * UserKioskMapping us =
			 * userKioskMappingRepository.findByKioskId(String.valueOf(dto.getKioskId()));
			 * 
			 * if(us !=null && us.getPfId() !=null && us.getPfId() !=""){
			 * dto.setPfId(us.getPfId()); User usr =
			 * userRepository.findByPfId(us.getPfId()); if(usr !=null && usr.getUsername()
			 * !=null && usr.getUsername() !=""){ dto.setUsername(usr.getUsername()); } }
			 */
		 
			
			  if(dto.getBranchCode() !=null){ BranchMaster branchMaster =
			  branchMasterRepository.findByBranchCode(dto.getBranchCode()); if(branchMaster
			  !=null && branchMaster.getCircleName() !=null && branchMaster.getCircleName() !=""){
			  dto.setCircle(branchMaster.getCircleName()); } }
			 
	 }
	 

	 	return entities;
    }

	@Override
    public Page<KioskBranchMasterUserDto> findPaginatedByCircle(int page, int size) {	 	 
		UserDto user = (UserDto) session().getAttribute("userObj");
	 Page<KioskBranchMasterUserDto> entities = 
			 kioskBranchMasterRepositoryPaging.findAllByCircle(user.getCircle(),PageRequest.of(page, size))
			 .map(KioskBranchMasterUserDto::new);
	 
	 for(KioskBranchMasterUserDto dto:entities){
		 UserKioskMapping us = userKioskMappingRepository.findByKioskId(String.valueOf(dto.getKioskId()));
		 
		 if(us !=null && us.getPfId() !=null && us.getPfId() !=""){
			 dto.setPfId(us.getPfId());
			 User usr = userRepository.findByPfId(us.getPfId());
			 if(usr !=null && usr.getUsername() !=null && usr.getUsername() !=""){
				 dto.setUsername(usr.getUsername());
				 dto.setPhoneNo(usr.getPhoneNo());
			 }
		 }
		 
			
			  if(dto.getBranchCode() !=null){ BranchMaster branchMaster =
			  branchMasterRepository.findByBranchCode(dto.getBranchCode()); if(branchMaster
			  !=null && branchMaster.getCircleName() !=null && branchMaster.getCircleName() !=""){
			  dto.setCircle(branchMaster.getCircleName()); } }
			 
	 }
	 
	 
		
	 //Page<UserManagementDto> pageDto = new PageImpl<UserManagementDto>(userList, PageRequest.of(page, size), userList.getSize());
	 	return entities;
    }
	
	
	@Override
    public Page<KioskBranchMasterUserDto> findPaginatedCount(int page, int size,String type) {	 
	 
	 
	 Page<KioskBranchMasterUserDto> entities = null;
	 
	 if(type!=null && !type.isEmpty()){
	 
	 if(type!=null && type.equals("CMS")){
		  entities =  kioskBranchMasterRepositoryPaging.findByVendor(type,PageRequest.of(page, size)).map(KioskBranchMasterUserDto::new);
	  }else if(type!=null && type.equals("LIPI")){
	     entities =  kioskBranchMasterRepositoryPaging.findByVendor(type,PageRequest.of(page, size)).map(KioskBranchMasterUserDto::new);
      
	  } if(type!=null && type.equals("InstalledCMSVendor")){
		  entities =  kioskBranchMasterRepositoryPaging.findByVendorAndInstallationStatus("CMS","Installed",PageRequest.of(page, size)).map(KioskBranchMasterUserDto::new);
	  }else if(type!=null && type.equals("DeleviredCMSVendor")){
		 // type="CMS";
		  entities =  kioskBranchMasterRepositoryPaging.findByVendorAndInstallationStatus("CMS","Pending",PageRequest.of(page, size)).map(KioskBranchMasterUserDto::new);
	  } if(type!=null && type.equals("InstalledLIPIVendor")){
		  entities =  kioskBranchMasterRepositoryPaging.findByVendorAndInstallationStatus("LIPI","Installed",PageRequest.of(page, size)).map(KioskBranchMasterUserDto::new);
	  }else if(type!=null &&type.equals("DeleviredLIPIVendor")){
		  entities =  kioskBranchMasterRepositoryPaging.findByVendorAndInstallationStatus("LIPI","Pending",PageRequest.of(page, size)).map(KioskBranchMasterUserDto::new);
	  }
	 }
	  
	 for(KioskBranchMasterUserDto dto:entities){
		 UserKioskMapping us = userKioskMappingRepository.findByKioskId(String.valueOf(dto.getKioskId()));
		 
		 if(us !=null && us.getPfId() !=null && us.getPfId() !=""){
			 dto.setPfId(us.getPfId());
			 User usr = userRepository.findByPfId(us.getPfId());
			 if(usr !=null && usr.getUsername() !=null && usr.getUsername() !=""){
				 dto.setUsername(usr.getUsername());
				 dto.setPhoneNo(usr.getPhoneNo());
			 }
		 }
		 
			
			  if(dto.getBranchCode() !=null){ BranchMaster branchMaster =
			  branchMasterRepository.findByBranchCode(dto.getBranchCode()); if(branchMaster
			  !=null && branchMaster.getCircleName() !=null && branchMaster.getCircleName() !=""){
			  dto.setCircle(branchMaster.getCircleName()); } }
			 
	 }
	 

	
 	return entities;
}
	
	@Override
    public Page<KioskBranchMasterUserDto> findPaginatedCountByCircle(int page, int size,String type) {	 
	 
		UserDto user = (UserDto) session().getAttribute("userObj");
		String circle=user.getCircle();
	 Page<KioskBranchMasterUserDto> entities = null;
	 
	 if(type!=null && !type.isEmpty()){
	 
	 if(type!=null && type.equals("CMS")){
		  entities =  kioskBranchMasterRepositoryPaging.findByVendorAndCircle(type,circle,PageRequest.of(page, size)).map(KioskBranchMasterUserDto::new);
	  }else if(type!=null && type.equals("LIPI")){
	     entities =  kioskBranchMasterRepositoryPaging.findByVendorAndCircle(type,circle,PageRequest.of(page, size)).map(KioskBranchMasterUserDto::new);
      
	  } if(type!=null && type.equals("InstalledCMSVendor")){
		  entities =  kioskBranchMasterRepositoryPaging.findByVendorAndInstallationStatusAndCircle("CMS","Installed",circle,PageRequest.of(page, size)).map(KioskBranchMasterUserDto::new);
	  }else if(type!=null && type.equals("DeleviredCMSVendor")){
		 // type="CMS";
		  entities =  kioskBranchMasterRepositoryPaging.findByVendorAndInstallationStatusAndCircle("CMS","Pending",circle,PageRequest.of(page, size)).map(KioskBranchMasterUserDto::new);
	  } if(type!=null && type.equals("InstalledLIPIVendor")){
		  entities =  kioskBranchMasterRepositoryPaging.findByVendorAndInstallationStatusAndCircle("LIPI","Installed",circle,PageRequest.of(page, size)).map(KioskBranchMasterUserDto::new);
	  }else if(type!=null &&type.equals("DeleviredLIPIVendor")){
		  entities =  kioskBranchMasterRepositoryPaging.findByVendorAndInstallationStatusAndCircle("LIPI","Pending",circle,PageRequest.of(page, size)).map(KioskBranchMasterUserDto::new);
	  } if(type!=null && type.equals("Assigned")){logger.info("if Assigned!!!!!!!!!!11111111");
		   entities = kioskMasterManagementRepository.findAllByInUserKioskByCircle(circle,PageRequest.of(page, size)).map(KioskBranchMasterUserDto::new);  
	  }else if(type!=null &&type.equals("ToBeAssigned")){logger.info("if ToBeAssigned!!!!!!!!!!11111111");
		  entities = kioskMasterManagementRepository.findAllByNotInUserKioskByCircle(circle,PageRequest.of(page, size)).map(KioskBranchMasterUserDto::new); 
	  }
	  
	 }
	  
	 for(KioskBranchMasterUserDto dto:entities){
		 UserKioskMapping us = userKioskMappingRepository.findByKioskId(String.valueOf(dto.getKioskId()));
		 
		 if(us !=null && us.getPfId() !=null && us.getPfId() !=""){
			 dto.setPfId(us.getPfId());
			 User usr = userRepository.findByPfId(us.getPfId());
			 if(usr !=null && usr.getUsername() !=null && usr.getUsername() !=""){
				 dto.setUsername(usr.getUsername());
				 dto.setPhoneNo(usr.getPhoneNo());
			 }
		 }
		 
			
			  if(dto.getBranchCode() !=null){ BranchMaster branchMaster =
			  branchMasterRepository.findByBranchCode(dto.getBranchCode()); if(branchMaster
			  !=null && branchMaster.getCircleName() !=null && branchMaster.getCircleName() !=""){
			  dto.setCircle(branchMaster.getCircleName()); } }
			 
	 }
	 

	
 	return entities;
}

	
	@Override
	public KioskBranchMasterUserDto getKiosksFromKioskBranchMasterByKioskId(String kioskId){		
		
		KioskBranchMaster entity = 
				kioskMasterRepository.findKioskByKioskId(kioskId);
		KioskBranchMasterUserDto dto = new KioskBranchMasterUserDto(entity);
		if(entity.getBranchCode() !=null){
		 BranchMaster branchMaster	 = branchMasterRepository.findByBranchCode(entity.getBranchCode());
			
			  if(branchMaster !=null && branchMaster.getCircleName() !=null &&
			  branchMaster.getCircleName() !=""){ dto.setCircle(branchMaster.getCircleName()); }
			 
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

	
	@Override
	public Map<String,Integer> findAllKioskMasterCount(){	
		Map<String,Integer> map=new HashMap<String,Integer>();
		
		
		int installedKioskCount = kioskMasterRepository.findKioskMasterCount();
		int cmsCount=kioskMasterRepository.findKioskCMSMasterCount();
		int lipiCount=kioskMasterRepository.findKioskLIPIMasterCount();
		int totalKioskCount=kioskMasterRepository.findTotalKioskMasterCount();
		int installedStatusCMSVendorWiseCount = kioskMasterRepository.findInstalledStatusCMSVendorWiseCount();
		int deleviredStatusCMSVendorWiseCount= kioskMasterRepository.findDeliveredStatusCMSVendorWiseCount();
		int installedStatusLIPIVendorWiseCount = kioskMasterRepository.findInstalledStatusLIPIVendorWiseCount();
		int deleviredStatusLIPIVendorWiseCount= kioskMasterRepository.findDeliveredStatusLIPIVendorWiseCount();
		
		int assignedCount=kioskMasterRepository.findAssignedCount();
		int toBeAssignedCount=kioskMasterRepository.findToBeAssignedCount();
		
		map.put("InstalledKiosks", installedKioskCount);
		map.put("CMS", cmsCount);
		map.put("LIPI", lipiCount);
		map.put("TotalKiosk", totalKioskCount);
		map.put("InstalledCMSVendor", installedStatusCMSVendorWiseCount);
		map.put("DeleviredCMSVendor", deleviredStatusCMSVendorWiseCount);
		map.put("InstalledLIPIVendor", installedStatusLIPIVendorWiseCount);
		map.put("DeleviredLIPIVendor", deleviredStatusLIPIVendorWiseCount);
		
		map.put("Assigned", assignedCount);
		map.put("ToBeAssigned", toBeAssignedCount);
		
		
		return map;
	}
	
	
	@Override
	public Map<String,Integer> findAllKioskMasterCountByCircle(){	
		UserDto user = (UserDto) session().getAttribute("userObj");
		String circle= user.getCircle();
		Map<String,Integer> map=new HashMap<String,Integer>();
		
		
		int installedKioskCount = kioskMasterRepository.findKioskMasterCount(circle);
		int cmsCount=kioskMasterRepository.findKioskCMSMasterCount(circle);
		int lipiCount=kioskMasterRepository.findKioskLIPIMasterCount(circle);
		int totalKioskCount=kioskMasterRepository.findTotalKioskMasterCount(circle);
		int installedStatusCMSVendorWiseCount = kioskMasterRepository.findInstalledStatusCMSVendorWiseCount(circle);
		int deleviredStatusCMSVendorWiseCount= kioskMasterRepository.findDeliveredStatusCMSVendorWiseCount(circle);
		int installedStatusLIPIVendorWiseCount = kioskMasterRepository.findInstalledStatusLIPIVendorWiseCount(circle);
		int deleviredStatusLIPIVendorWiseCount= kioskMasterRepository.findDeliveredStatusLIPIVendorWiseCount(circle);
		
		int assignedCount=kioskMasterRepository.findAssignedCount(circle);
		int toBeAssignedCount=kioskMasterRepository.findToBeAssignedCount(circle);
		
		map.put("InstalledKiosks", installedKioskCount);
		map.put("CMS", cmsCount);
		map.put("LIPI", lipiCount);
		map.put("TotalKiosk", totalKioskCount);
		map.put("InstalledCMSVendor", installedStatusCMSVendorWiseCount);
		map.put("DeleviredCMSVendor", deleviredStatusCMSVendorWiseCount);
		map.put("InstalledLIPIVendor", installedStatusLIPIVendorWiseCount);
		map.put("DeleviredLIPIVendor", deleviredStatusLIPIVendorWiseCount);
		
		map.put("Assigned", assignedCount);
		map.put("ToBeAssigned", toBeAssignedCount);
		
		
		return map;
	}

	@Override
    public Page<KioskBranchMasterUserDto> findPaginatedCountSearchNext(int page, int size,String type, String searchText) {	 
	 
	 
	 Page<KioskBranchMasterUserDto> entities = null;
	 
	 if(type!=null && !type.isEmpty()){
	 
	 if(type!=null && type.equals("CMS")){
		  entities =  kioskMasterManagementRepository.findByVendorSearchText(type,searchText,PageRequest.of(page, size)).map(KioskBranchMasterUserDto::new);
	  }else if(type!=null && type.equals("LIPI")){
	     entities =  kioskMasterManagementRepository.findByVendorSearchText(type,searchText,PageRequest.of(page, size)).map(KioskBranchMasterUserDto::new);
      
	  } if(type!=null && type.equals("InstalledCMSVendor")){
		  entities =  kioskMasterManagementRepository.findByVendorAndInstallationStatusSearchText("CMS","Installed",searchText,PageRequest.of(page, size)).map(KioskBranchMasterUserDto::new);
	  }else if(type!=null && type.equals("DeleviredCMSVendor")){
		 // type="CMS";
		  entities =  kioskMasterManagementRepository.findByVendorAndInstallationStatusSearchText("CMS","Pending",searchText,PageRequest.of(page, size)).map(KioskBranchMasterUserDto::new);
	  } if(type!=null && type.equals("InstalledLIPIVendor")){
		  entities =  kioskMasterManagementRepository.findByVendorAndInstallationStatusSearchText("LIPI","Installed",searchText,PageRequest.of(page, size)).map(KioskBranchMasterUserDto::new);
	  }else if(type!=null &&type.equals("DeleviredLIPIVendor")){
		  entities =  kioskMasterManagementRepository.findByVendorAndInstallationStatusSearchText("LIPI","Pending",searchText,PageRequest.of(page, size)).map(KioskBranchMasterUserDto::new);
	  }
	 }
	  
	 for(KioskBranchMasterUserDto dto:entities){
		 UserKioskMapping us = userKioskMappingRepository.findByKioskId(String.valueOf(dto.getKioskId()));
		 
		 if(us !=null && us.getPfId() !=null && us.getPfId() !=""){
			 dto.setPfId(us.getPfId());
			 User usr = userRepository.findByPfId(us.getPfId());
			 if(usr !=null && usr.getUsername() !=null && usr.getUsername() !=""){
				 dto.setUsername(usr.getUsername());
				 dto.setPhoneNo(usr.getPhoneNo());
			 }
		 }
		 
			
			  if(dto.getBranchCode() !=null){ BranchMaster branchMaster =
			  branchMasterRepository.findByBranchCode(dto.getBranchCode()); if(branchMaster
			  !=null && branchMaster.getCircleName() !=null && branchMaster.getCircleName() !=""){
			  dto.setCircle(branchMaster.getCircleName()); } }
			 
	 }
	 

	
 	return entities;
}

	@Override
	public Page<KioskBranchMasterUserDto> findAssingedPaginatedSearchNext(int page, int size, String type,
			String searchText) {
	 	 
		
		Page<KioskBranchMasterUserDto> entities = kioskMasterManagementRepository.findAllByInUserKioskSearchText(searchText,PageRequest.of(page, size))
				 .map(KioskBranchMasterUserDto::new);
	 
	 for(KioskBranchMasterUserDto dto:entities){
			
			  UserKioskMapping us =
			  userKioskMappingRepository.findByKioskId(String.valueOf(dto.getKioskId()));
			  
			  if(us !=null && us.getPfId() !=null && us.getPfId() !=""){
			  dto.setPfId(us.getPfId()); User usr =
			  userRepository.findByPfId(us.getPfId()); if(usr !=null && usr.getUsername()
			  !=null && usr.getUsername() !=""){ dto.setUsername(usr.getUsername()); dto.setPhoneNo(usr.getPhoneNo());} }
			 
		 
			
			  if(dto.getBranchCode() !=null){ BranchMaster branchMaster =
			  branchMasterRepository.findByBranchCode(dto.getBranchCode()); if(branchMaster
			  !=null && branchMaster.getCircleName() !=null && branchMaster.getCircleName() !=""){
			  dto.setCircle(branchMaster.getCircleName()); } }
			 
	 }
	 

	 	return entities;
   
	}

	@Override
	public Page<KioskBranchMasterUserDto> findTobeAssingedPaginatedSearchNext(int page, int size, String type,
			String searchText) {
	 	 
		
		Page<KioskBranchMasterUserDto> entities = kioskMasterManagementRepository.findAllByNotInUserKioskSearchText(searchText,PageRequest.of(page, size))
				 .map(KioskBranchMasterUserDto::new);
	 
	 for(KioskBranchMasterUserDto dto:entities){
			/*
			 * UserKioskMapping us =
			 * userKioskMappingRepository.findByKioskId(String.valueOf(dto.getKioskId()));
			 * 
			 * if(us !=null && us.getPfId() !=null && us.getPfId() !=""){
			 * dto.setPfId(us.getPfId()); User usr =
			 * userRepository.findByPfId(us.getPfId()); if(usr !=null && usr.getUsername()
			 * !=null && usr.getUsername() !=""){ dto.setUsername(usr.getUsername()); } }
			 */
		 
			
			  if(dto.getBranchCode() !=null){ BranchMaster branchMaster =
			  branchMasterRepository.findByBranchCode(dto.getBranchCode()); if(branchMaster
			  !=null && branchMaster.getCircleName() !=null && branchMaster.getCircleName() !=""){
			  dto.setCircle(branchMaster.getCircleName()); } }
			 
	 }
	 

	 	return entities;
   
	}
	



}
