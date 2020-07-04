package sbi.kiosk.swayam.kioskmanagement.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import sbi.kiosk.swayam.common.constants.Constants;
import sbi.kiosk.swayam.common.constants.ExceptionConstants;
import sbi.kiosk.swayam.common.dto.AddUserDto;
import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.dto.UserManagementDto;
import sbi.kiosk.swayam.common.entity.Supervisor;
import sbi.kiosk.swayam.common.entity.User;
import sbi.kiosk.swayam.common.repository.SupervisorRepository;
import sbi.kiosk.swayam.common.repository.UserRepositoryPaging;
import sbi.kiosk.swayam.kioskmanagement.repository.UserKioskMappingRepository;
import sbi.kiosk.swayam.kioskmanagement.repository.UsersRepository;

@Service
public class UserServiceImpl implements UserService {
	
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	 @Autowired
	 UsersRepository userRepo;
	 
	 @Autowired
	 UserRepositoryPaging userRepositoryPagingRepo;
	 
	 @Autowired
	 UserKioskMappingRepository userKioskMappingRepository;
	 
	 @Autowired
	 SupervisorRepository supervisorRepository;
	 
	 public static HttpSession session() {
	        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	        return attr.getRequest().getSession(true); // true == allow create
	    }

	 @Override
	public List<UserManagementDto> findAllUsers(UserDto userDto) {
		  List<UserManagementDto> userManaDTOList=new ArrayList<UserManagementDto>();
	      List<User> userList=null;
	      if(userDto.getRole().equals("CC")) {
	    	  userList=userRepo.findAllForCC(userDto.getUsername());
	      }
	      else if(userDto.getRole().equals("LA")) {
	    	  userList=userRepo.findAllForLA(userDto.getCircle());
	      }
	      else {
	    	  userList=userRepo.findAll(userDto.getUsername());
	      }      
		  
			if(userList!=null && !userList.isEmpty() && userList.size()>0){
				   for(User userEntity:userList){
				      userManaDTOList.add(new UserManagementDto(userEntity));
				
				}
			}
		return userManaDTOList;
	}
	 
	 @SuppressWarnings("deprecation")
	@Override
	    public Page<UserManagementDto> findPaginated(int page, int size) {
		// List<UserManagementDto> userManaDTOList=new ArrayList<UserManagementDto>();
		// Page<User> userList = userRepositoryPagingRepo.findAll(PageRequest.of(page, size));
		 
		 
		 Page<UserManagementDto> entities = 
				 userRepositoryPagingRepo.findByEnabled("1",PageRequest.of(page, size))
				 .map(UserManagementDto::new);
		 if(entities !=null){
			 for(UserManagementDto dto:entities){
				 if(Constants.SYSTEMADMIN.getCode().equals(dto.getRole())){
					 dto.setRole(Constants.SYSTEMADMIN.getValue());
				 }
				 if(Constants.LOCALADMIN.getCode().equals(dto.getRole())){
					 dto.setRole(Constants.LOCALADMIN.getValue());
				 }
				 if(Constants.CIRCLE.getCode().equals(dto.getRole())){
					 dto.setRole(Constants.CIRCLE.getValue());
				 }
				 if(dto.getPfId() !=null && dto.getPfId() !="" && dto.getRole().equals("CMF")){
					 int kioskCountCmf = userKioskMappingRepository.findKiosksCountByPfId(dto.getPfId());
					 dto.setNoOfAssignedKiosks(String.valueOf(kioskCountCmf));
				 }
				 else if(dto.getPfId() !=null && dto.getPfId() !="" && dto.getRole().equals("CMS")){
					 int total = 0;
					 List<Supervisor> supList =  supervisorRepository.findBySupervisorPfId(dto.getPfId());
					 
					 if(supList !=null && supList.size() > 0){
						 for(Supervisor sup:supList){
							 int kioskCountCmf = userKioskMappingRepository.findKiosksCountByPfId(sup.getPfId());
							 total = total + kioskCountCmf;
						 }
					 }
					 if(total !=0){
						 dto.setNoOfAssignedKiosks(String.valueOf(total));
					 }				 
					 						
				 }
			 }
		 }		 
		   return entities;
	    }
	 
	 @Override
	    public Page<UserManagementDto> findPaginatedByCircle(int page, int size) {
		// List<UserManagementDto> userManaDTOList=new ArrayList<UserManagementDto>();
		// Page<User> userList = userRepositoryPagingRepo.findAll(PageRequest.of(page, size));
		 UserDto user = (UserDto) session().getAttribute("userObj");
		 List<String> roleList= new ArrayList<String>();
		 //roleList.add("LA");
		 roleList.add("SA");
		 roleList.add("CC");
		 
		 Page<UserManagementDto> entities = 
				 userRepositoryPagingRepo.findByCircleAndEnabledAndRoleNotIn(user.getCircle(),"1",roleList,PageRequest.of(page, size))
				 .map(UserManagementDto::new);
		 if(entities !=null){
			 for(UserManagementDto dto:entities){				 
				 if(Constants.LOCALADMIN.getCode().equals(dto.getRole())){
					 dto.setRole(Constants.LOCALADMIN.getValue());
				 }
				 if(Constants.CIRCLE.getCode().equals(dto.getRole())){
					 dto.setRole(Constants.CIRCLE.getValue());
				 }
				 if(dto.getPfId() !=null && dto.getPfId() !="" && dto.getRole().equals("CMF")){
					 int kioskCountCmf = userKioskMappingRepository.findKiosksCountByPfId(dto.getPfId());
					 dto.setNoOfAssignedKiosks(String.valueOf(kioskCountCmf));
				 }
				 else if(dto.getPfId() !=null && dto.getPfId() !="" && dto.getRole().equals("CMS")){
					 int total = 0;
					 List<Supervisor> supList =  supervisorRepository.findBySupervisorPfId(dto.getPfId());
					 
					 if(supList !=null && supList.size() > 0){
						 for(Supervisor sup:supList){
							 int kioskCountCmf = userKioskMappingRepository.findKiosksCountByPfId(sup.getPfId());
							 total = total + kioskCountCmf;
						 }
					 }
					 if(total !=0){
						 dto.setNoOfAssignedKiosks(String.valueOf(total));
					 }				 
					 						
				 }
			 }
		 }		 
		   return entities;
	    }

	 
	 @SuppressWarnings("deprecation")
	    @Override
	    public Page<UserManagementDto> findPaginatedCount(int page, int size,String type) {
		Page<UserManagementDto> entities =null;		
	    entities =userRepositoryPagingRepo.findByRoleAndEnabled(type,"1",PageRequest.of(page, size)).map(UserManagementDto::new);
	    
		if(entities !=null){
			 for(UserManagementDto dto:entities){
				 if(Constants.SYSTEMADMIN.getCode().equals(dto.getRole())){
					 dto.setRole(Constants.SYSTEMADMIN.getValue());
				 }
				 if(Constants.LOCALADMIN.getCode().equals(dto.getRole())){
					 dto.setRole(Constants.LOCALADMIN.getValue());
				 }
				 if(Constants.CIRCLE.getCode().equals(dto.getRole())){
					 dto.setRole(Constants.CIRCLE.getValue());
				 }
				 if(dto.getPfId() !=null && dto.getPfId() !="" && dto.getRole().equals("CMF")){
					 int kioskCountCmf = userKioskMappingRepository.findKiosksCountByPfId(dto.getPfId());
					 dto.setNoOfAssignedKiosks(String.valueOf(kioskCountCmf));
				 }
				 else if(dto.getPfId() !=null && dto.getPfId() !="" && dto.getRole().equals("CMS")){
					 int total = 0;
					 List<Supervisor> supList =  supervisorRepository.findBySupervisorPfId(dto.getPfId());
					 
					 if(supList !=null && supList.size() > 0){
						 for(Supervisor sup:supList){
							 int kioskCountCmf = userKioskMappingRepository.findKiosksCountByPfId(sup.getPfId());
							 total = total + kioskCountCmf;
						 }
					 }
					 if(total !=0){
						 dto.setNoOfAssignedKiosks(String.valueOf(total));
					 }				 
					 						
				 }
			 }
		 }
		
		 return entities;
	    }
	 
	 @Override
	    public Page<UserManagementDto> findPaginatedCountByCircle(int page, int size,String type) {
		UserDto user = (UserDto) session().getAttribute("userObj");
		Page<UserManagementDto> entities =null;		
	    entities =userRepositoryPagingRepo.findByCircleAndRoleAndEnabled(user.getCircle(),type,"1",PageRequest.of(page, size)).map(UserManagementDto::new);
	    
		if(entities !=null){
			 for(UserManagementDto dto:entities){
				 if(Constants.SYSTEMADMIN.getCode().equals(dto.getRole())){
					 dto.setRole(Constants.SYSTEMADMIN.getValue());
				 }
				 if(Constants.LOCALADMIN.getCode().equals(dto.getRole())){
					 dto.setRole(Constants.LOCALADMIN.getValue());
				 }
				 if(Constants.CIRCLE.getCode().equals(dto.getRole())){
					 dto.setRole(Constants.CIRCLE.getValue());
				 }
				 if(dto.getPfId() !=null && dto.getPfId() !="" && dto.getRole().equals("CMF")){
					 int kioskCountCmf = userKioskMappingRepository.findKiosksCountByPfId(dto.getPfId());
					 dto.setNoOfAssignedKiosks(String.valueOf(kioskCountCmf));
				 }
				 else if(dto.getPfId() !=null && dto.getPfId() !="" && dto.getRole().equals("CMS")){
					 int total = 0;
					 List<Supervisor> supList =  supervisorRepository.findBySupervisorPfId(dto.getPfId());
					 
					 if(supList !=null && supList.size() > 0){
						 for(Supervisor sup:supList){
							 int kioskCountCmf = userKioskMappingRepository.findKiosksCountByPfId(sup.getPfId());
							 total = total + kioskCountCmf;
						 }
					 }
					 if(total !=0){
						 dto.setNoOfAssignedKiosks(String.valueOf(total));
					 }				 
					 						
				 }
			 }
		 }
		
		 return entities;
	    }
	 
	 
	 @Override
		public List<UserManagementDto> findByUserName(String userName) {
			  List<UserManagementDto> userManaDTOList=new ArrayList<UserManagementDto>();
		      List<User> userList=null;
			  userList=userRepo.findByUserName(userName);
				if(userList!=null && !userList.isEmpty() && userList.size()>0){
					   for(User userEntity:userList){
					      userManaDTOList.add(new UserManagementDto(userEntity));
					
					}
				}
			return userManaDTOList;
		}

	@Override
	public void  saveUserMaster(UserDto userDto) {		
		
		User userEntity=new User(userDto);	
		userEntity.setEnabled("1");
		userRepo.save(userEntity);
	}

	 @Override
     public AddUserDto	findUserByUserId(String userId){
		 User user=userRepo.findUserByUserId(Integer.parseInt(userId));
		 AddUserDto userDto=new AddUserDto();
		 userDto.setPfId(user.getPfId());
		 userDto.setUserId(user.getUserId());
		 userDto.setRole(user.getRole());
		 userDto.setCircle(user.getCircle());
		 userDto.setUsername(user.getUsername());
		 userDto.setPhoneNo(user.getPhoneNo());
		 userDto.setEmailId(user.getMailId());
		 userDto.setReportingAuthorityName(user.getReportingAuthorityName());
		 userDto.setReportingAuthorityEmail(user.getReportingAuthorityEmail());
	  return userDto;
		
	}
	 
	 
	
	
	@Override
	public boolean updateUserById(UserDto userDto) {
		boolean result = false;
		try {
			User userEntity=new User(userDto);	
			User userEntityObj= userRepo.findByUsername(userDto.getUsername());
			userEntity.setCreatedBy(userEntityObj.getCreatedBy());
			userEntity.setCreatedDate(userEntityObj.getCreatedDate());
			userEntity.setEnabled("1");
			userRepo.save(userEntity);
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
               result=false;
		}
		return result;
	}
	

	

	@Override
	public boolean deActivateUserById(UserDto usersBean) {
		
		boolean result = false;
		try {
			Integer userId=usersBean.getUserId();
		    User userEntity= userRepo.findUserByUserId(usersBean.getUserId());
			//User userEntity=new User();
			//userEntity.setUserId(userId);
			userEntity.setEnabled("0");
			/*userEntity.setUserName(usersBean.getUserName());
			userEntity.setRole(usersBean.getRole());
			userEntity.setCreatedDate(usersBean.getCreatedDate());
			userEntity.setCreatedBy(usersBean.getCreatedBy());
			userEntity.setModifiedDate(usersBean.getModifiedDate());
			userEntity.setModifiedBy(usersBean.getModifiedBy());*/
			//userEntity.setEnabled("0");
			//Integer uid=userEntity.getUserId();
			//String enabled=userEntity.getEnabled();
			User user=userRepo.save(userEntity);
			user.getEnabled().equals("0");
			return true;
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
               result=false;
		}
		return result;
		
	}
	
	@Override
	public List<User> fetchAllCmfUserByCircle(String circle){
		return userRepo.findCmfUserByCircle(circle);
	}
	
	@Override
	public User getUserByPfId(String username){
		return userRepo.findUserByPfId(username);
	}
	
	
	 @Override
		public int findCMFCount() {
	    	int cmfCount=userRepo.findCMFCount();
			return cmfCount;
		}
	    
	    @Override
		public int findCMSCount() {
	    	int cmsCount=userRepo.findCMSCount();
			return cmsCount;
		}
	    
	    @Override
		public int findCMSCountByCircle() {
	    	UserDto user = (UserDto) session().getAttribute("userObj");
	    	int cmsCount=userRepo.findCMSCountByCircle(user.getCircle());
			return cmsCount;
		}
	    
	    @Override
		public int findCMFCountByCircle() {
	    	UserDto user = (UserDto) session().getAttribute("userObj");
	    	int cmsCount=userRepo.findCMFCountByCircle(user.getCircle());
			return cmsCount;
		}
	    
	    @Override
		public int findCircleCount() {
	    	int circleCount=userRepo.findCircleCount();
			return circleCount;
		}
	    
	    @Override
		public int findLACountByCircle() {
	    	UserDto user = (UserDto) session().getAttribute("userObj");
	    	int laCount=userRepo.findLACount(user.getCircle());
			return laCount;
		}
	    
	    @Override
		public int findLACount() {
	    	int laCount=userRepo.findLACount();
			return laCount;
		}
	    
	    @Override
		public int findCCCount() {
	    	int ccCount=userRepo.findCCCount();
			return ccCount;
		}
	    
	    @Override
		public int findSACount() {
	    	int saCount=userRepo.findSACount();
			return saCount;
		}
	    
	    @Override
		public int findCircleUserCount() {
	    	int circleUserCount=userRepo.findCircleUserCount();
			return circleUserCount;
		}
	    
	    @Override
		public int findCircleUserCountByCircle() {
	    	UserDto user = (UserDto) session().getAttribute("userObj");
	    	int circleUserCount=userRepo.findCircleUserCountByCircle(user.getCircle());
			return circleUserCount;
		}

	    
	    @Override
	    public int findCircleCountByRole(String circle){
	    	System.err.println("circle=========================sa===="+circle);
	    	int circleCountByRole=userRepo.findCircleCountByRole(circle);
			return circleCountByRole;
	    }
	
	
}
