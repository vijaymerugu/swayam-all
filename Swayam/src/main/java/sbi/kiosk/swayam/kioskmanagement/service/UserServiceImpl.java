package sbi.kiosk.swayam.kioskmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.common.dto.AddUserDto;
import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.dto.UserManagementDto;
import sbi.kiosk.swayam.common.entity.User;
import sbi.kiosk.swayam.common.repository.UserRepositoryPaging;
import sbi.kiosk.swayam.kioskmanagement.repository.UsersRepository;

@Service
public class UserServiceImpl implements UserService {

	 @Autowired
	 UsersRepository userRepo;
	 
	 @Autowired
	 UserRepositoryPaging userRepositoryPagingRepo;

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
		 //Page<UserManagementDto> entitiesNew  = new PageImpl<UserManagementDto>(userManaDTOList);
		 /*for(User user:userList){
			 if(user.getEnabled().equals("1")){
				 entitiesNew.add(new UserManagementDto(user));
			 }
		 }*/
		 /*Page<UserManagementDto> entities = 
				 UserRepositoryPagingRepo.findAll(PageRequest.of(page, size))
				 .stream()
				 .filter(e -> e.getEnabled().equals("1"))
				 .map(UserManagementDto::new).collect(Collectors.toCollection(Page::new));*/
		 
		 /*if(userList!=null && !userList.isEmpty() && userList.size()>0){
			   for(User userEntity:userList){
			      userManaDTOList.add(new UserManagementDto(userEntity));
			
			}
		}*/
		 //Page<UserManagementDto> pageDto = new PageImpl<UserManagementDto>(userList, PageRequest.of(page, size), userList.getSize());
		 	return entities;
	    }
	 
	 @SuppressWarnings("deprecation")
	    @Override
	    public Page<UserManagementDto> findPaginatedCount(int page, int size,String type) {
		System.out.println("type=============findPaginatedCount=================================="+type); 
		Page<UserManagementDto> entities =null;
		if(!type.equals("MUMBAI")){
	       entities =userRepositoryPagingRepo.findByRoleAndEnabled(type,"1",PageRequest.of(page, size)).map(UserManagementDto::new);
	    }else{
	    	 entities =userRepositoryPagingRepo.findByCircleAndEnabled(type,"1",PageRequest.of(page, size)).map(UserManagementDto::new);
	    }
		 return entities;
	    }
	 
	 
	 @Override
		public List<UserManagementDto> findByUserName(String userName) {
			  List<UserManagementDto> userManaDTOList=new ArrayList<UserManagementDto>();
		      List<User> userList=null;
		      System.out.println("findByUserName111==  "+userName);
			  userList=userRepo.findByUserName(userName);
			  System.out.println("userList:: SIZE "+userList.size());
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
		 System.out.println("userId========"+userId);
		 User user=userRepo.findUserByUserId(Integer.parseInt(userId));
		 AddUserDto userDto=new AddUserDto();
		 userDto.setPfId(user.getPfId());
		 userDto.setUserId(user.getUserId());
		 userDto.setRole(user.getRole());
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
               e.printStackTrace();
               result=false;
		}
		return result;
	}
	

	

	@Override
	public boolean deActivateUserById(UserDto usersBean) {
		
		boolean result = false;
		try {
			System.out.println("userid====="+usersBean.getUserId());
			Integer userId=usersBean.getUserId();
			System.out.println("=====id======="+userId);
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
               e.printStackTrace();
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
	    	System.out.println("cmfCount:: "+cmfCount);
			return cmfCount;
		}
	    
	    @Override
		public int findCMSCount() {
	    	int cmsCount=userRepo.findCMSCount();
	    	System.out.println("cmsCount:: "+cmsCount);
			return cmsCount;
		}
	    
	    @Override
		public int findCircleCount() {
	    	int circleCount=userRepo.findCircleCount();
	    	System.out.println("circleCount:: "+circleCount);
			return circleCount;
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
	    public int findCircleCountByRole(String circle){
	    	System.err.println("circle=========================sa===="+circle);
	    	int circleCountByRole=userRepo.findCircleCountByRole(circle);
	    	System.out.println("circleCountByRole:: "+circleCountByRole);
			return circleCountByRole;
	    }
	
	
}
