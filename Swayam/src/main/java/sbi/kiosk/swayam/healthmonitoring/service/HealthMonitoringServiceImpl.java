package sbi.kiosk.swayam.healthmonitoring.service;

import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import sbi.kiosk.swayam.common.constants.Constants;
import sbi.kiosk.swayam.common.dto.KioskBranchMasterUserDto;
import sbi.kiosk.swayam.common.dto.RequestsDto;
import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.dto.UserManagementDto;
import sbi.kiosk.swayam.common.entity.Requests;
import sbi.kiosk.swayam.common.repository.SupervisorRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.RequestsRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.RequestsRepositoryPaging;

@Service
public class HealthMonitoringServiceImpl implements HealthMonitoringService {
	
	@Autowired
	RequestsRepository requestsRepository;
	
	@Autowired
	RequestsRepositoryPaging requestsRepositoryPaging;
	
	@Autowired
	SupervisorRepository supervisorRepository;
	
	public static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }

	
	@Override
	public void saveRequestForCmf(RequestsDto dto){
		UserDto user = (UserDto) session().getAttribute("userObj");
		dto.setReqCategory("C");
		dto.setUserType("M");
		dto.setCreatedBy(user.getPfId());
		dto.setCreatedDate(new Date());
		dto.setModifiedBy(user.getPfId());
		dto.setModifiedDate(new Date());
		Requests requests = new Requests(dto);
		requestsRepository.save(requests);
	}
	
	@Override
    public Page<RequestsDto> findPaginated(int page, int size) {
		UserDto user = (UserDto) session().getAttribute("userObj");
	 
		Set<String> pfIdList = supervisorRepository.findPfIdListByPfIdSupervisor(user.getPfId());	
		
		Page<RequestsDto> entities = 
			 requestsRepositoryPaging.findByUserTypeAndModifiedByIn(Constants.MAKER.getCode(),pfIdList,PageRequest.of(page, size,Sort.by("id").descending()))
			.map(RequestsDto::new);
	    return entities;
	  }


	@Override
	public Page<UserManagementDto> findPaginatedCount(int page, int size,
			String type) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
