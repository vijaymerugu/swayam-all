package sbi.kiosk.swayam.healthmonitoring.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import sbi.kiosk.swayam.common.constants.Constants;
import sbi.kiosk.swayam.common.dto.RequestsDto;
import sbi.kiosk.swayam.common.dto.RequestsManagementDto;
import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.entity.KioskBranchMaster;
import sbi.kiosk.swayam.common.entity.Requests;
import sbi.kiosk.swayam.common.entity.Supervisor;
import sbi.kiosk.swayam.common.entity.User;
import sbi.kiosk.swayam.common.repository.KioskMasterRepository;
import sbi.kiosk.swayam.common.repository.SupervisorRepository;
import sbi.kiosk.swayam.common.repository.UserRepository;
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
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	KioskMasterRepository kioskMasterRepository;
	
	public static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }

	@Override
	 public String checkDuplicateKiosAppr(String kioskId){
	   // String result=null;
	  int  result=requestsRepository.findByKioskId(kioskId);
	    System.out.println("result==="+result);
	     if(result>0){
	    	 System.out.println("result=1=="+result);
		      return "This Kiosk Id Is Allready Exist";
	     }else {
	    	 System.out.println("result=2=="+result);
	    	 return "";
	     }
	 }
	 
	
	@Override
	@Transactional
	public String saveRequestForCmf(RequestsDto dto){
		//int reqSeq=requestsRepository.findRequSeq();
		UserDto user = (UserDto) session().getAttribute("userObj");
		dto.setReqCategory(Constants.CREATED.getCode());
		dto.setUserType(Constants.MAKER.getCode());
		dto.setCreatedBy(user.getPfId());
		dto.setCreatedDate(new Date());
		dto.setModifiedBy(user.getPfId());
		dto.setModifiedDate(new Date());
		Requests requests = new Requests(dto);
		Requests request= requestsRepository.save(requests);
		
		String result="REQ"+request.getId();
		if(result!=null && !result.isEmpty()){
			System.out.println("result update=="+result);
			System.out.println("requests.getId()=="+request.getId());
		 requestsRepository.update(result,request.getId());
		}
		
		return result;
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
    public Page<RequestsDto> findPaginatedCC(int page, int size) {
		
		Page<RequestsDto> entities = 
			 requestsRepositoryPaging.findByUserTypeAndReqCategory(Constants.CHECKER.getCode(),Constants.RECOMMENDED.getCode(),PageRequest.of(page, size,Sort.by("id").descending()))
			.map(RequestsDto::new);
	    return entities;
	  }
	
	@Override
    public Page<RequestsDto> findPaginatedCmf(int page, int size) {
		
		UserDto user = (UserDto) session().getAttribute("userObj");
		String pfId = user.getPfId();
		
		Set<String> set = new HashSet<String>();
		set.add(Constants.APPROVED.getCode());
		set.add(Constants.REJECTED.getCode());
		
		Page<RequestsDto> entities = 
			 requestsRepositoryPaging.findByCreatedByAndReqCategoryIn(pfId, set, PageRequest.of(page, size,Sort.by("id").descending()))
			.map(RequestsDto::new);
	    return entities;
	  }
	
	@Override
	public void saveCheckerCommentsCms(String array){
		UserDto user = (UserDto) session().getAttribute("userObj");
		Map<String, String> map = new HashMap<>();
		String str = array.substring(1,array.length()-1);
		
		List<String> pairs = Arrays.asList(str.split(","));
		if(pairs !=null && pairs.size() > 0){
			for(String pa :pairs){
				String val = pa.substring(1,pa.length()-1);			
				String arrayVal[] = val.split(":"); 
				String id = arrayVal[0].substring(1,arrayVal[0].length()-1);; 
				String comment = arrayVal[1].substring(1,arrayVal[1].length()-1);;
				map.put(id,comment);
			}
		}
		if(map !=null && map.size() > 0){
			for (Map.Entry<String, String> entry : map.entrySet()) {    
			    
			    Requests entity = requestsRepository.findById(Integer.parseInt(entry.getKey()));
			    entity.setComments(entry.getValue());
			    entity.setReqCategory(Constants.RECOMMENDED.getCode());
			    entity.setUserType(Constants.CHECKER.getCode());		    
			    entity.setModifiedBy(user.getPfId());
			    entity.setModifiedDate(new Date());
			    requestsRepository.save(entity);
			}
		}	
		
	}
	
	@Override
	public void rejectCheckerCommentsCms(String array){
		UserDto user = (UserDto) session().getAttribute("userObj");
		Map<String, String> map = new HashMap<>();
		String str = array.substring(1,array.length()-1);
		
		List<String> pairs = Arrays.asList(str.split(","));
		if(pairs !=null && pairs.size() > 0){
			for(String pa :pairs){
				String val = pa.substring(1,pa.length()-1);			
				String arrayVal[] = val.split(":"); 
				String id = arrayVal[0].substring(1,arrayVal[0].length()-1);; 
				String comment = arrayVal[1].substring(1,arrayVal[1].length()-1);;
				map.put(id,comment);
			}
		}
		if(map !=null && map.size() > 0){
			for (Map.Entry<String, String> entry : map.entrySet()) {    
			    
			    Requests entity = requestsRepository.findById(Integer.parseInt(entry.getKey()));
			    entity.setComments(entry.getValue());
			    entity.setReqCategory(Constants.REJECTED.getCode());
			    entity.setUserType(Constants.CHECKER.getCode());		    
			    entity.setModifiedBy(user.getPfId());
			    entity.setModifiedDate(new Date());
			    requestsRepository.save(entity);
			}
		}	
		
	}
	
	
	
	@Override
	public void saveApproverCommentsCC(String array){
		UserDto user = (UserDto) session().getAttribute("userObj");
		Map<String, String> map = new HashMap<>();
		String str = array.substring(1,array.length()-1);
		
		List<String> pairs = Arrays.asList(str.split(","));
		if(pairs !=null && pairs.size() > 0){
			for(String pa :pairs){
				String val = pa.substring(1,pa.length()-1);			
				String arrayVal[] = val.split(":"); 
				String id = arrayVal[0].substring(1,arrayVal[0].length()-1);; 
				String comment = arrayVal[1].substring(1,arrayVal[1].length()-1);;
				map.put(id,comment);
			}
		}
		if(map !=null && map.size() > 0){
			for (Map.Entry<String, String> entry : map.entrySet()) {    
			    
			    Requests entity = requestsRepository.findById(Integer.parseInt(entry.getKey()));
			    entity.setComments(entry.getValue());
			    entity.setReqCategory(Constants.APPROVED.getCode());
			    entity.setUserType(Constants.APPROVER.getCode());		    
			    entity.setModifiedBy(user.getPfId());
			    entity.setModifiedDate(new Date());
			    requestsRepository.save(entity);
			}
		}	
		
	}
	
	@Override
	public void rejectApproverCommentsCC(String array){
		UserDto user = (UserDto) session().getAttribute("userObj");
		Map<String, String> map = new HashMap<>();
		String str = array.substring(1,array.length()-1);
		
		List<String> pairs = Arrays.asList(str.split(","));
		if(pairs !=null && pairs.size() > 0){
			for(String pa :pairs){
				String val = pa.substring(1,pa.length()-1);			
				String arrayVal[] = val.split(":"); 
				String id = arrayVal[0].substring(1,arrayVal[0].length()-1);; 
				String comment = arrayVal[1].substring(1,arrayVal[1].length()-1);;
				map.put(id,comment);
			}
		}
		if(map !=null && map.size() > 0){
			for (Map.Entry<String, String> entry : map.entrySet()) {    
			    
			    Requests entity = requestsRepository.findById(Integer.parseInt(entry.getKey()));
			    entity.setComments(entry.getValue());
			    entity.setReqCategory(Constants.REJECTED.getCode());
			    entity.setUserType(Constants.APPROVER.getCode());		    
			    entity.setModifiedBy(user.getPfId());
			    entity.setModifiedDate(new Date());
			    requestsRepository.save(entity);
			}
		}	
		
	}
	
	@Override
	public RequestsManagementDto viewCaseId(int caseId){
		
		
		Requests entity = requestsRepository.findById(caseId);
		RequestsManagementDto dto = new RequestsManagementDto(entity);
		dto.setMakerPfId(entity.getCreatedBy());
		User usrMaker = userRepository.findByPfId(entity.getCreatedBy());
		dto.setMakerContact(usrMaker.getPhoneNo());
		dto.setMakerCircle(usrMaker.getCircle());
		dto.setMakerEmail(usrMaker.getMailId());
		
		Supervisor supervisor =supervisorRepository.findByPfId(entity.getCreatedBy());
		if(supervisor !=null){
			User usrChecker = userRepository.findByPfId(supervisor.getPfIdSupervisor());
			dto.setCheckerPfId(supervisor.getPfIdSupervisor());
			dto.setCheckerContact(usrChecker.getPhoneNo());
			dto.setCheckerCircle(usrChecker.getCircle());
			dto.setCheckerEmail(usrChecker.getMailId());
		}
		
		if(entity.getReqCategory().equals((Constants.APPROVED.getCode()))
				&& entity.getUserType().equals(Constants.APPROVER.getCode())){
			User usrApprover = userRepository.findByPfId(entity.getModifiedBy());
			dto.setApproverPfId(entity.getModifiedBy());
			dto.setApproverContact(usrApprover.getPhoneNo());
			dto.setApproverCircle(usrApprover.getCircle());
			dto.setApproverEmail(usrApprover.getMailId());			
		}
		
		KioskBranchMaster km = kioskMasterRepository.findByKioskId(entity.getKioskId());
		if(km !=null){
			dto.setKioskIp(km.getKioskId());
			dto.setKioskOs(km.getOs());
			dto.setKioskVendor(km.getVendor());
			dto.setKioskMacaddress(km.getKioskMacAddress());
		}
		
		return dto;
	}

	@Override
	public Page<RequestsDto> findPaginatedCount(int page, int size, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<RequestsDto> findPaginatedByCircle(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<RequestsDto> findPaginatedCountByCircle(int page, int size,
			String type) {
		// TODO Auto-generated method stub
		return null;
	}	
	
}
