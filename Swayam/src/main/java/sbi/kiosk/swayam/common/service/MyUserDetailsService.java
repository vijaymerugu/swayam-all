
package sbi.kiosk.swayam.common.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Inside MyUserDetailsService");
		sbi.kiosk.swayam.common.entity.User user = null;
		UserDto userDto = null;
		user = userRepository.findByPfId(username);
		userDto = new UserDto(user);
		return new User(userDto.getPfId(), "", new ArrayList<>());
	}

}
