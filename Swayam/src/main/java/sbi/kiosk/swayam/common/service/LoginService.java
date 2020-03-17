package sbi.kiosk.swayam.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import antlr.StringUtils;
import sbi.kiosk.swayam.common.dto.MenuMasterDto;
import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.entity.MenuMaster;
import sbi.kiosk.swayam.common.entity.User;
import sbi.kiosk.swayam.common.repository.MenuMasterRepository;
import sbi.kiosk.swayam.common.repository.UserRepository;

@Service
public class LoginService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MenuMasterRepository menuMasterRepository;
	
	public UserDto getRoleByUsername(String username) {
		
		User user = userRepository.findByUsername(username);
		UserDto userDto = new UserDto(user);
		return userDto;
		
	}
	
	public List<MenuMasterDto> getMenusByUserRole(String role){
		List<MenuMasterDto> menuMasterDtoList = new ArrayList<MenuMasterDto>();
		List<MenuMaster> menuMasterEntity =  menuMasterRepository.findByRole(role);
		
		if(menuMasterEntity !=null && menuMasterEntity.size() > 0)
		for(MenuMaster menuMaster :menuMasterEntity) {
			menuMasterDtoList.add(new MenuMasterDto(menuMaster));
		}
		return menuMasterDtoList;
	}

}
