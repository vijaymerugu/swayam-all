package sbi.kiosk.swayam.kioskmanagement.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.common.dto.AddUserDto;
import sbi.kiosk.swayam.common.entity.Supervisor;
import sbi.kiosk.swayam.common.entity.User;
import sbi.kiosk.swayam.common.repository.SupervisorRepository;
import sbi.kiosk.swayam.common.repository.UserRepository;


@Service
@Transactional
public class AddUserServiceImpl implements AddUserService{
	Logger logger = LoggerFactory.getLogger(AddUserServiceImpl.class);
	@Autowired
	private UserRepository userRepository;
	@Autowired 
	private SupervisorRepository supervisorRepository;
	
	@Override
	public String addUser(AddUserDto dto,String role,String circle) {
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
	
	// added CMS/BC
	
	@Override
	@Transactional
	public String addUserBMAndCMF(AddUserDto dto,String role,String circle) {
		 String userName=null;
	try {
		User user=new User();
		user.setPfId(dto.getPfId());
		user.setUsername(dto.getUsername());
		user.setPhoneNo(dto.getPhoneNo());
		user.setMailId(dto.getEmailId());
		user.setRole(role);
		Supervisor supervisor=new Supervisor();
		supervisor.setPfId(dto.getPfId());
		supervisor.setPfIdSupervisor(dto.getReportingAuthorityPfId());
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
		    userName= "User is Allready Exist";
		}else{
			  Supervisor sup= supervisorRepository.save(supervisor);
			  logger.info("AddUserService======sup:::::Save:::::"+sup);
			  User resultSave = userRepository.save(user);
			   userName=resultSave.getUsername();
		  
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		 return userName;
		
    }

	
	@Override
	public String updateUser(AddUserDto dto,String role,String circle) {
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
		int result=userRepository.geByPfId((pfId));
			if(result==0)
				return "";
			return "PF ID Already Exists";
	}
	
	@Override
	public List<User> getReportingAutMailIdByPfId(String pfId) {
		List<User> result=userRepository.getReportingAMailIdByPfId(pfId);
			
			return result;
	}

}
