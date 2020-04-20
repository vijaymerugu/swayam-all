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
	@Autowired
	private RoleRepository roleRepository;
	

	
	@Override
	public String addUser(AddUserDto dto) {
		System.out.println("saving adduser form.....");
		Iterable<Role> role = roleRepository.findAll();
		User user=new User();
		user.setPfId(dto.getPfId());
		user.setUsername(dto.getUserName());
		user.setPhoneNo(dto.getPhoneNumber());
		user.setMailId(dto.getEmailId());
		for(Role role1:role) {
			if(role1.getRoleDesc().equalsIgnoreCase(dto.getRole()));
		    user.setRole(role1.getRole());
		}
		System.out.println(user.getRole());
		user.setReportingAuthorityEmail(dto.getReportingAuthorityEmail());
		user.setReportingAuthorityName(dto.getReportingAuthorityName());
          
		User resultSave = userRepository.save(user);
		if(resultSave!=null)
		return "user addd";
		return "user is not add";
    }
	

	@Override
	public String getByPfId(String pfId) {
		System.out.println("inside addservice class::"+pfId);
		int result=userRepository.geByPfId((pfId));
		System.out.println(result);
			if(result==0)
				return "";
			return "This User Pf Id Is Exist";
	}


	@Override
	public String saveRole(String role) {
		// TODO Auto-generated method stub
		return null;
	}

}
