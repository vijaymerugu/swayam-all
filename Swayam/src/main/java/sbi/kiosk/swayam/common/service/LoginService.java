package sbi.kiosk.swayam.common.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.common.constants.ExceptionConstants;
import sbi.kiosk.swayam.common.dto.MenuMasterDto;
import sbi.kiosk.swayam.common.dto.RequestResponseLogDto;
import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.entity.MenuMaster;
import sbi.kiosk.swayam.common.entity.RequestResponse;
import sbi.kiosk.swayam.common.entity.User;
import sbi.kiosk.swayam.common.repository.MenuMasterRepository;
import sbi.kiosk.swayam.common.repository.ReqRespAuditLogRepository;
import sbi.kiosk.swayam.common.repository.UserRepository;
import sbi.kiosk.swayam.transactiondashboard.controller.TransactionDashBoardController;

@Service
public class LoginService {
	
	Logger logger = LoggerFactory.getLogger(LoginService.class);
	
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
		List<MenuMaster> menuMasterEntity =  menuMasterRepository.findByRoleAndUseYN(role,"Y");
		
		if(menuMasterEntity !=null && menuMasterEntity.size() > 0)
		for(MenuMaster menuMaster :menuMasterEntity) {
			menuMasterDtoList.add(new MenuMasterDto(menuMaster));
		}
		return menuMasterDtoList;
	}
	
	
	public void saveReqAndResponse(RequestResponseLogDto reqRespLogDto){
		try {
			logger.info("saveReqAndResponse Call()....");
			
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
			logger.info("requestResponse==");
			reqRespAuditLogRepo.save(requestResponse);
			
		} catch (Exception e) {
			logger.info("Exception "+ExceptionConstants.EXCEPTION);
		}
		
		
		
		
		
	}
	

}
