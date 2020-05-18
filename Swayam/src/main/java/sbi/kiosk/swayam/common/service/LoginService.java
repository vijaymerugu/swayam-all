package sbi.kiosk.swayam.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import antlr.StringUtils;
import sbi.kiosk.swayam.common.dto.MenuMasterDto;
import sbi.kiosk.swayam.common.dto.RequestResponseLogDto;
import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.entity.MenuMaster;
import sbi.kiosk.swayam.common.entity.RequestResponse;
import sbi.kiosk.swayam.common.entity.User;
import sbi.kiosk.swayam.common.repository.MenuMasterRepository;
import sbi.kiosk.swayam.common.repository.ReqRespAuditLogRepository;
import sbi.kiosk.swayam.common.repository.UserRepository;

@Service
public class LoginService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MenuMasterRepository menuMasterRepository;
	
	@Autowired
	ReqRespAuditLogRepository reqRespAuditLogRepo;
	
	public UserDto getRoleByUsername(String pfId) {
		
		User user = userRepository.findByPfId(pfId);
		//User user = userRepository.findByUsername(pfId);
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
	
	
	public void saveReqAndResponse(RequestResponseLogDto reqRespLogDto){
		try {
			System.out.println("saveReqAndResponse Call()...."+reqRespLogDto);
			
			int srIdSeq=reqRespAuditLogRepo.findSeq();
			System.err.println("reqRespAuditLogRepo.findSeq()  "+srIdSeq);
			RequestResponse	requestResponse=new RequestResponse();
			
			requestResponse.setId(srIdSeq);
			requestResponse.setUserId(reqRespLogDto.getUserId());
			requestResponse.setToken(reqRespLogDto.getToken());
			requestResponse.setRequest(reqRespLogDto.getRequest());
			requestResponse.setUrl(reqRespLogDto.getUrl());
			requestResponse.setResponse(reqRespLogDto.getResponse());
			requestResponse.setSuccess(reqRespLogDto.getSuccess());
			requestResponse.setError(reqRespLogDto.getError());
			//requestResponse.setModifiedDate(new Date());
			System.out.println("requestResponse=="+requestResponse);
			reqRespAuditLogRepo.save(requestResponse);
			
		} catch (Exception e) {
               e.printStackTrace();
		}
		
		
		
		
		
	}
	

}
