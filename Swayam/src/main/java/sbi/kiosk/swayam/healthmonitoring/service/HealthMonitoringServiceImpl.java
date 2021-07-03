package sbi.kiosk.swayam.healthmonitoring.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import sbi.kiosk.swayam.common.entity.VendorMaster;
import sbi.kiosk.swayam.common.repository.AllVendorRepository;
import sbi.kiosk.swayam.common.repository.KioskMasterRepository;
import sbi.kiosk.swayam.common.repository.SupervisorRepository;
import sbi.kiosk.swayam.common.repository.UserRepository;
import sbi.kiosk.swayam.common.validation.ValidationCommon;
import sbi.kiosk.swayam.healthmonitoring.repository.RequestsRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.RequestsRepositoryPaging;

@Service
public class HealthMonitoringServiceImpl implements HealthMonitoringService {
	
	Logger logger = LoggerFactory.getLogger(HealthMonitoringServiceImpl.class);
	
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
	@Autowired
	AllVendorRepository vendorRepo;
	
	public static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }

	@Override
	 public String checkDuplicateKiosAppr(String kioskId){
	   // String result=null;
	  int  result=requestsRepository.findByKioskId(kioskId);
	    
	     if(result>0){
	    	 
		      return "This Kiosk Id Is Allready Exist";
	     }else {
	    	 
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
		
		String result=null;
		 
		logger.info("Before save dto:::"+dto);
		int kioskIdCount=requestsRepository.findByKioskIdAndUserType(dto.getKioskId(),"M");
		logger.info("After:::kioskId:::"+kioskIdCount);
		if(kioskIdCount>0) {	
			String kioskId=requestsRepository.findKioskId(dto.getKioskId(),"M");
			//result="Requested: "+kioskId+ " KioskId Already Exist";
			result= "Request already exist for Kioskid "+kioskId;
	    }else {
		
		Requests requests = new Requests(dto);
		logger.info("Before save ELSE dto:::"+dto);
		logger.info("requests=fromdate=="+requests.getFromDate());
		logger.info("requests=todate=="+requests.getToDate());
		logger.info("Before save ELSE requests CreatedDate:::"+requests.getCreatedDate());
		logger.info("Before save ELSE requests ModifiedDate:::"+requests.getModifiedDate());
		logger.info("Before save ELSE requests:::"+requests);
		Requests request= requestsRepository.save(requests);
		logger.info("requests=id=="+request.getId());
		String resultResp="REQ"+request.getId();
		result="Request: "+resultResp+ " has been successfully created";
		if(resultResp!=null && !resultResp.isEmpty()){
			
		 requestsRepository.update(resultResp,request.getId());
		}
	    }
		logger.info("result::::"+result);
		return result;
	}
	
	
	@Override
	@Transactional
	public String saveRequestForCircle(RequestsDto dto){
		//int reqSeq=requestsRepository.findRequSeq();
		UserDto user = (UserDto) session().getAttribute("userObj");
		dto.setReqCategory(Constants.RECOMMENDED.getCode());
		dto.setUserType(Constants.CHECKER.getCode());
		dto.setCreatedBy(user.getPfId());
		dto.setCreatedDate(new Date());
		dto.setModifiedBy(user.getPfId());
		dto.setModifiedDate(new Date());
		
		String result=null;
		 
		logger.info("Before save dto:::"+dto);
		int kioskIdCount=requestsRepository.findByKioskIdAndUserType(dto.getKioskId(),"C");
		logger.info("After:::kioskId:::"+kioskIdCount);
		if(kioskIdCount>0) {	
			String kioskId=requestsRepository.findKioskId(dto.getKioskId(),"C");
			//result="Requested: "+kioskId+ " KioskId Already Exist";
			result= "Request already exist for Kioskid "+kioskId;
	    }else {
		
		Requests requests = new Requests(dto);
		logger.info("Before save ELSE dto:::"+dto);
		logger.info("requests=fromdate=="+requests.getFromDate());
		logger.info("requests=todate=="+requests.getToDate());
		logger.info("Before save ELSE requests CreatedDate:::"+requests.getCreatedDate());
		logger.info("Before save ELSE requests ModifiedDate:::"+requests.getModifiedDate());
		logger.info("Before save ELSE requests:::"+requests);
		Requests request= requestsRepository.save(requests);
		logger.info("requests=id=="+request.getId());
		String resultResp="REQ"+request.getId();
		result="Request: "+resultResp+ " has been successfully created";
		if(resultResp!=null && !resultResp.isEmpty()){
			
		 requestsRepository.update(resultResp,request.getId());
		}
	    }
		logger.info("result::::"+result);
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
		
		Page<RequestsDto> entities =requestsRepositoryPaging.findByUserTypeAndReqCategory(Constants.CHECKER.getCode(),Constants.RECOMMENDED.getCode(),PageRequest.of(page, size,Sort.by("id").descending()))
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
		// add by satendra
		set.add(Constants.CREATED.getCode());
		
		Page<RequestsDto> entities = 
			 requestsRepositoryPaging.findByCreatedByAndReqCategoryIn(pfId, set, PageRequest.of(page, size,Sort.by("id").descending()))
			.map(RequestsDto::new);
		System.err.println("entities=id=="+entities.getContent());
	    return entities;
	  }
	
	
	@Override
    public Page<RequestsDto> findPaginatedCircle(int page, int size) {
		
		UserDto user = (UserDto) session().getAttribute("userObj");
		String pfId = user.getPfId();
		
		Set<String> set = new HashSet<String>();
		set.add(Constants.APPROVED.getCode());
		set.add(Constants.REJECTED.getCode());
		// add by satendra
		set.add(Constants.CREATED.getCode());
		set.add(Constants.RECOMMENDED.getCode());
		
		Page<RequestsDto> entities = 
			 requestsRepositoryPaging.findByCreatedByAndReqCategoryIn(pfId, set, PageRequest.of(page, size,Sort.by("id").descending()))
			.map(RequestsDto::new);
		System.err.println("entities=id=="+entities.getContent());
	    return entities;
	  }
	
	
	@Override
	@Transactional
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
				map.put(ValidationCommon.validateNumber(id),ValidationCommon.validateStringChar(comment));
			}
		}
		if(map !=null && map.size() > 0){
			for (Map.Entry<String, String> entry : map.entrySet()) {    
			    
			    Requests entity = requestsRepository.findById(Integer.parseInt(entry.getKey()));
			    
			    Requests reqEntity =new  Requests();
			    
			    reqEntity.setComments(entry.getValue());
			    reqEntity.setReqCategory(Constants.RECOMMENDED.getCode());
			    reqEntity.setUserType(Constants.CHECKER.getCode());		    
			    reqEntity.setModifiedBy(user.getPfId());
			    reqEntity.setCreatedDate(new Date());
			    reqEntity.setModifiedDate(new Date()); 
		    //  entity.setFromDate(date1);
			 // entity.setToDate(date1);
		    //  requestsRepository.save(entity);
					
			    try {		
				  
			    	 requestsRepository.updateAndSave(reqEntity.getComments(),reqEntity.getReqCategory(),reqEntity.getUserType(),reqEntity.getModifiedBy(),getFormatTS(),Integer.parseInt(entry.getKey()));
			   // requestsReposDemo.save(entity);
			}catch(Exception e) {  
				e.printStackTrace();
			}
			}
		}	
		
	}
	
	@Override
	@Transactional
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
				map.put(ValidationCommon.validateNumber(id),ValidationCommon.validateStringChar(comment));
			}
		}
		if(map !=null && map.size() > 0){
			for (Map.Entry<String, String> entry : map.entrySet()) {    
			    
			    Requests entity = requestsRepository.findById(Integer.parseInt(entry.getKey()));
			   
			    Requests reqEntity = new Requests();
			    reqEntity.setComments(entry.getValue());
			    reqEntity.setReqCategory(Constants.REJECTED.getCode());
			    reqEntity.setUserType(Constants.CHECKER.getCode());		    
			    reqEntity.setModifiedBy(user.getPfId());
			    //reqEntity.setModifiedDate(new Date());
			    //requestsRepository.save(entity);
			    //String modifiedDate=getFormatTS();
			    
			    requestsRepository.updateAndSave(reqEntity.getComments(),reqEntity.getReqCategory(),reqEntity.getUserType(),reqEntity.getModifiedBy(),getFormatTS(),Integer.parseInt(entry.getKey()));
			}
		}	
		
	}
	
	
	
	private String getFormatTS() {
		String format =null;
		try {
			    Date date=new Date();
				SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss.ms a");
				 format = dateFormat2.format(date);
				 if (format!=null) {
					 System.out.println("format :" + format);
				  }
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return format;
	}

	@Override
	@Transactional
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
				map.put(ValidationCommon.validateNumber(id),ValidationCommon.validateStringChar(comment));
			}
		}
		if(map !=null && map.size() > 0){
			for (Map.Entry<String, String> entry : map.entrySet()) {    
			    
			   Requests entity = requestsRepository.findById(Integer.parseInt(entry.getKey()));
				 Requests reqEntity = new Requests();
				 reqEntity.setComments(entry.getValue());
				 reqEntity.setReqCategory(Constants.APPROVED.getCode());
				 reqEntity.setUserType(Constants.APPROVER.getCode());		    
				 reqEntity.setModifiedBy(user.getPfId());
				 reqEntity.setModifiedDate(new Date());
			    // added by satendra 13052021
			  //  SimpleDateFormat   date = new SimpleDateFormat("dd-MM-YY");
			//	String date1 = date.format(new Date());
			  //  entity.setFromDate(date1);
			  //  entity.setToDate(date1);
			    
			   // requestsRepository.save(entity);
			    
				 requestsRepository.updateAndSave(reqEntity.getComments(),reqEntity.getReqCategory(),reqEntity.getUserType(),reqEntity.getModifiedBy(),getFormatTS(),Integer.parseInt(entry.getKey()));
			    
			    
			}
		}	
		
	}
	
	@Override
	@Transactional
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
				map.put(ValidationCommon.validateNumber(id),ValidationCommon.validateStringChar(comment));
			}
		}
		if(map !=null && map.size() > 0){
			for (Map.Entry<String, String> entry : map.entrySet()) {    
			    
			    Requests entity = requestsRepository.findById(Integer.parseInt(entry.getKey()));
			    Requests reqEntity =new  Requests();
			    reqEntity.setComments(entry.getValue());
			    reqEntity.setReqCategory(Constants.REJECTED.getCode());
			    reqEntity.setUserType(Constants.APPROVER.getCode());		    
			    reqEntity.setModifiedBy(user.getPfId());
			    reqEntity.setModifiedDate(new Date());
			    // added by satendra 13052021
			    SimpleDateFormat   date = new SimpleDateFormat("dd-MM-YY");
				String date1 = date.format(new Date());
			   // entity.setFromDate(date1);
			    //entity.setToDate(date1);
			    //requestsRepository.save(entity);
			    
				 requestsRepository.updateAndSave(reqEntity.getComments(),reqEntity.getReqCategory(),reqEntity.getUserType(),reqEntity.getModifiedBy(),getFormatTS(),Integer.parseInt(entry.getKey()));
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

	@Override
	public Page<RequestsDto> findPaginatedCountCmf(int page, int size,
			String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<RequestsDto> findPaginatedCms(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<RequestsDto> findPaginatedCountCms(int page, int size,
			String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<VendorMaster> getVendor(String brachCode) {
		
		Iterable<VendorMaster> findAll = vendorRepo.findAll();
		return findAll;
	}

	@Override
	public List<RequestsDto> getByVendorAndBranchCode(String vendor, String branchcode) {
		
		List<KioskBranchMaster> findAllByVendorAndBrachCode = kioskMasterRepository.findAllByVendorAndBranchCode(vendor,branchcode);
		List<RequestsDto> requestDotList= new ArrayList<>();
		
		findAllByVendorAndBrachCode.forEach(reqDto ->{
			RequestsDto dto=new RequestsDto();
			dto.setKioskId(reqDto.getKioskId());
			requestDotList.add(dto);
		});
		
		
		
		return requestDotList;
	}
	
	@Override
	@Transactional
	public RequestsManagementDto activateKiosk(int caseId) {

		 SimpleDateFormat   date = new SimpleDateFormat("dd-MM-YY");
			String toDate = date.format(new Date());
		   requestsRepository.updateToDate(toDate,caseId);
		   System.err.println("caseId==");
		
		return null;
	}	
	
	
	
	
	
	
	
	

}
