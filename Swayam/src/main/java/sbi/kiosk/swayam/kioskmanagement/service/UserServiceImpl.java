package sbi.kiosk.swayam.kioskmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.dto.UserManagementDto;
import sbi.kiosk.swayam.common.entity.User;
import sbi.kiosk.swayam.kioskmanagement.repository.UsersRepository;

@Service
public class UserServiceImpl implements UserService {

	 @Autowired
	 UsersRepository userRepo;

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
			User userEntity= userRepo.findByUsername(usersBean.getUsername());
			//User userEntity=new User(usersBean);
			userEntity.setUserId(usersBean.getUserId());
			userEntity.setEnabled("0");
			/*userEntity.setUserName(usersBean.getUserName());
			userEntity.setRole(usersBean.getRole());
			userEntity.setCreatedDate(usersBean.getCreatedDate());
			userEntity.setCreatedBy(usersBean.getCreatedBy());
			userEntity.setModifiedDate(usersBean.getModifiedDate());
			userEntity.setModifiedBy(usersBean.getModifiedBy());*/
			//userEntity.setEnabled("0");
			Integer uid=userEntity.getUserId();
			String enabled=userEntity.getEnabled();
			User user=userRepo.save(userEntity);
			user.getEnabled().equals("0");
			return true;
		} catch (Exception e) {
               e.printStackTrace();
               result=false;
		}
		return result;
		
	}
	
}
