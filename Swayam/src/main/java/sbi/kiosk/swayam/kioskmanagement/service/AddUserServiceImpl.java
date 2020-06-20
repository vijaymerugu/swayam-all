package sbi.kiosk.swayam.kioskmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.common.dto.AddUserDto;
import sbi.kiosk.swayam.common.entity.Role;
import sbi.kiosk.swayam.common.entity.User;
import sbi.kiosk.swayam.common.repository.RoleRepository;
import sbi.kiosk.swayam.common.repository.UserRepository;


@Service
public class AddUserServiceImpl implements AddUserService{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public String addUser(AddUserDto dto,String role,String circle) {
		System.out.println("saving adduser form.....");
		System.out.println("Role==================");
		System.out.println(role);
		User user=new User();
		user.setPfId(dto.getPfId());
		user.setUsername(dto.getUsername());
		user.setPhoneNo(dto.getPhoneNo());
		user.setMailId(dto.getEmailId());
		user.setRole(role);
		if("Select".equals(circle)){
			user.setCircle("");
		}else{
			user.setCircle(circle);
		}
		user.setReportingAuthorityEmail(dto.getReportingAuthorityEmail());
		user.setReportingAuthorityName(dto.getReportingAuthorityName());
		user.setEnabled("1");
		String userPfId=userRepository.findIdByPfId(dto.getPfId());
		if(userPfId!=null  && !userPfId.isEmpty()){
			return "User is Allready Exist";
		}else{
	
			  User resultSave = userRepository.save(user);
			  String userName=resultSave.getUsername();
		  return userName;
		}
		
    }

	
	@Override
	public String updateUser(AddUserDto dto,String role,String circle) {
		System.out.println("saving adduser form.....");
		System.out.println("Role==================");
		System.out.println(role);
		User user=new User();
		user.setUserId(dto.getUserId());
		user.setPfId(dto.getPfId());
		user.setUsername(dto.getUsername());
		user.setPhoneNo(dto.getPhoneNo());
		user.setMailId(dto.getEmailId());
		user.setRole(role);
		if("Select".equals(circle)){
			user.setCircle("");
		}else{
			user.setCircle(circle);
		}
		user.setReportingAuthorityEmail(dto.getReportingAuthorityEmail());
		user.setReportingAuthorityName(dto.getReportingAuthorityName());
		user.setEnabled("1"); 
		
			  User resultSave = userRepository.save(user);
			  String userName=resultSave.getUsername();
		  return userName;
		
		
    }

	
	
	
	@Override
	public String getByPfId(String pfId) {
		System.out.println("inside addservice class::"+pfId);
		int result=userRepository.geByPfId((pfId));
		System.out.println(result);
			if(result==0)
				return "";
			return "PF ID Already Exists";
	}

}
