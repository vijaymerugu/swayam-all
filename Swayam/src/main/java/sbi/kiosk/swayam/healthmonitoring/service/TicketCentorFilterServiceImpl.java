package sbi.kiosk.swayam.healthmonitoring.service;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import sbi.kiosk.swayam.common.constants.ExceptionConstants;
import sbi.kiosk.swayam.common.dto.CallTypeDto;
import sbi.kiosk.swayam.common.dto.TicketCentorDto;
import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.entity.TicketCentor;
import sbi.kiosk.swayam.common.repository.KioskMasterRepository;
import sbi.kiosk.swayam.common.repository.SupervisorRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.CallTypeRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.TicketCentorAgeingRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.TicketCentorRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.TicketCentorSearchTextRepository;
import sbi.kiosk.swayam.kioskmanagement.repository.BranchMasterRepository;

@Service
public class TicketCentorFilterServiceImpl implements TicketCentorFilterService {
	
	Logger logger = LoggerFactory.getLogger(TicketCentorFilterServiceImpl.class);
	
	@Autowired
	TicketCentorRepository ticketCentorRepo;
	@Autowired
	private TicketCentorAgeingRepository ticketCentorAgeingRepo;
	
	@Autowired
	CallTypeRepository callTypeRepo;
	
	@Autowired
	KioskMasterRepository kioskMasterRepo;
	
	@Autowired
	SupervisorRepository supervisorRepository;
	 
	@Autowired
	BranchMasterRepository branchMasterRepo;
    @Autowired
	TicketCentorSearchTextRepository ticketCentorSearchTextRepo;
	
	public static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }	

	 @Override
	 public Page<TicketCentorDto> findPaginated(final int page, final int size){
		 logger.info("Inside======findPaginated===========");
		 Page<TicketCentorDto> entities = ticketCentorRepo.findAll(PageRequest.of(page, size)).map(TicketCentorDto::new);
		 String circle=null;
		 for(TicketCentorDto dto:entities){
			 
			 String kioskId=dto.getKisokId();
			  if(kioskId!=null){
				  String kioskBranchCode= kioskMasterRepo.findKioskByBranchCode(kioskId);
				  circle= branchMasterRepo.findCircleByBranchCode(kioskBranchCode);
				  }
				  dto.setServeriry(circle);
		 }
		 
		 
		 return entities;
		 
	 }
	 
	 
	     @Override
	    public Page<TicketCentorDto> findPaginatedCount(int page, int size,String type) {	 
		 Page<TicketCentorDto> entities = null;
		 try{
			 
			 if(type!=null && !type.isEmpty()){
			 if(type!=null && type.equals("High")){
				  entities= ticketCentorRepo.findAll(type, PageRequest.of(page, size)).map(TicketCentorDto::new);
			  }else if(type!=null && type.equals("Medium")){
			     entities= ticketCentorRepo.findAll(type, PageRequest.of(page, size)).map(TicketCentorDto::new);
			 }else if(type!=null && type.equals("Low")){
			     entities= ticketCentorRepo.findAll(type, PageRequest.of(page, size)).map(TicketCentorDto::new);
			 }else if(type!=null && type.equals("Total")){
			     entities =  ticketCentorRepo.findAllByRisk("High","Medium","Low",PageRequest.of(page, size)).map(TicketCentorDto::new);
			  }else if(type!=null && type.equals("TwoToFourHrsCount")){
				  entities=ticketCentorAgeingRepo.findAllTicketCentor4Hour(PageRequest.of(page, size)).map(TicketCentorDto::new);
			  }else if(type!=null && type.equals("OneDaysCount")){
				  entities=ticketCentorAgeingRepo.findAllTicketCentor1Days(PageRequest.of(page, size)).map(TicketCentorDto::new);
			 }else if(type!=null && type.equals("ThreeDaysLessCount")){
		  	 entities=ticketCentorAgeingRepo.findAllTicketCentor3DaysLess(PageRequest.of(page, size)).map(TicketCentorDto::new);
		    }else if(type!=null && type.equals("ThreeDayGreaterCount")){
		    	entities=ticketCentorAgeingRepo.findAllTicketCentor3DaysGreater(PageRequest.of(page, size)).map(TicketCentorDto::new);
		    } else if(type !=null && type !="" && type !="undefined"){	    	
		    	entities= ticketCentorRepo.findByCallSubCategory(type, PageRequest.of(page, size,Sort.by("ticketId").descending())).map(TicketCentorDto::new);	         
		    }else{
				  entities =  ticketCentorRepo.findAll(PageRequest.of(page, size,Sort.by("call_log_date").descending())).map(TicketCentorDto::new);
			      }
			 }
		 }catch (Exception e) {
			 logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
	 	return entities;
   }

		@Override
		public Map<String,Integer> findAllSeverityOfTicketsCount(){	
			Map<String, Integer> mapDataList = null;
		 try{
			    mapDataList = new HashMap<String, Integer>();			
			    int countHigh = callTypeRepo.getCallTypeHiegh();
				int countMedium = callTypeRepo.getCallTypeMedium();
				int countLow = callTypeRepo.getCallTypeLow();
				int countTotal = callTypeRepo.getCallTypeTotal();
				mapDataList.put("High", countHigh);
				mapDataList.put("Medium", countMedium);
				mapDataList.put("Low", countLow);
				mapDataList.put("Total", countTotal);
			
				for (Map.Entry<String, Integer> entry : mapDataList.entrySet()) {

				}
		 }catch (Exception e) {
			 logger.error("Exception "+ExceptionConstants.EXCEPTION);
		 } 
			
			return mapDataList;
		}
		
		@Override
		public Map<String,Integer> findAllSeverityOfTicketsCountCMF(){	
			UserDto user = (UserDto) session().getAttribute("userObj");
			String pfId = user.getPfId();
			Map<String, Integer> mapDataList = null;
		 try{
			
			    mapDataList = new HashMap<String, Integer>();			
			    int countHigh = callTypeRepo.getCallTypeHieghCMF(pfId);
				int countMedium = callTypeRepo.getCallTypeMediumCMF(pfId);
				int countLow = callTypeRepo.getCallTypeLowCMF(pfId);
				int countTotal = callTypeRepo.getCallTypeTotalCMF(pfId);
				mapDataList.put("High", countHigh);
				mapDataList.put("Medium", countMedium);
				mapDataList.put("Low", countLow);
				mapDataList.put("Total", countTotal);
			
				for (Map.Entry<String, Integer> entry : mapDataList.entrySet()) {

				}
		 }catch (Exception e) {
			 logger.error("Exception "+ExceptionConstants.EXCEPTION);
		 } 
			
			return mapDataList;
		}

		@Override
		public Map<String,Integer> findAllSeverityOfTicketsCountCMS(){	
			UserDto user = (UserDto) session().getAttribute("userObj");
			String supPfId = user.getPfId();
			Set<String> supList =  supervisorRepository.findPfIdListByPfIdSupervisor(supPfId);
			Map<String, Integer> mapDataList = null;
		 try{
			
			    mapDataList = new HashMap<String, Integer>();			
			    int countHigh = callTypeRepo.getCallTypeHieghCMS(supList);
				int countMedium = callTypeRepo.getCallTypeMediumCMS(supList);
				int countLow = callTypeRepo.getCallTypeLowCMS(supList);
				int countTotal = callTypeRepo.getCallTypeTotalCMS(supList);
				mapDataList.put("High", countHigh);
				mapDataList.put("Medium", countMedium);
				mapDataList.put("Low", countLow);
				mapDataList.put("Total", countTotal);
			
				for (Map.Entry<String, Integer> entry : mapDataList.entrySet()) {

				}
			
		 }catch (Exception e) {
		//	 e.printStackTrace();
			 logger.error("Exception111 "+ExceptionConstants.EXCEPTION);
		 } 
			
			return mapDataList;
		}

		
		@Override
		public Map<String,Integer> findAllAgeingOfTicketsCount(){	
			Map<String, Integer> mapDataList = null;		
			try{
				 
				 mapDataList = new HashMap<String, Integer>();	
			    int twoToFourHrsCount = ticketCentorAgeingRepo.find2_4HoursCount();
				int oneDaysCount = ticketCentorAgeingRepo.find_1_DaysCount();
				int threeDaysCount = ticketCentorAgeingRepo.find_3_Days_LessCount();
				int threeDayGreaterCount = ticketCentorAgeingRepo.find_3_Days_GreaterThanCount();
				int totalCount = ticketCentorAgeingRepo.findTotalCount();
				
				
				mapDataList.put("TwoToFourHrsCount", twoToFourHrsCount);
				mapDataList.put("OneDaysCount", oneDaysCount);
				mapDataList.put("ThreeDaysLessCount", threeDaysCount);
				mapDataList.put("ThreeDayGreaterCount", threeDayGreaterCount);
				mapDataList.put("TotalCount", totalCount);
				
			 
			}catch (Exception e) {
				logger.error("Exception "+ExceptionConstants.EXCEPTION);
			}
			return mapDataList;
		}
		
		@Override
		public Map<String,Integer> findAllAgeingOfTicketsCountCMF(){
			UserDto user = (UserDto) session().getAttribute("userObj");
			String pfId = user.getPfId();
			Map<String, Integer> mapDataList = null;		
			try{
				
				 mapDataList = new HashMap<String, Integer>();	
			    int twoToFourHrsCount = ticketCentorAgeingRepo.find2_4HoursCountCMF(pfId);
				
				int oneDaysCount = ticketCentorAgeingRepo.find_1_DaysCountCMF(pfId);
				int threeDaysCount = ticketCentorAgeingRepo.find_3_Days_LessCountCMF(pfId);
				int threeDayGreaterCount = ticketCentorAgeingRepo.find_3_Days_GreaterThanCountCMF(pfId);
				int totalCount = ticketCentorAgeingRepo.findTotalCountCMF(pfId);
				
				
				mapDataList.put("TwoToFourHrsCount", twoToFourHrsCount);
				mapDataList.put("OneDaysCount", oneDaysCount);
				mapDataList.put("ThreeDaysLessCount", threeDaysCount);
				mapDataList.put("ThreeDayGreaterCount", threeDayGreaterCount);
				mapDataList.put("TotalCount", totalCount);
			
			}catch (Exception e) {
				logger.error("Exception "+ExceptionConstants.EXCEPTION);
			}
			return mapDataList;
		}
		
		@Override
		public Map<String,Integer> findAllAgeingOfTicketsCountCMS(){
			UserDto user = (UserDto) session().getAttribute("userObj");
			String supPfId = user.getPfId();
			Set<String> supList =  supervisorRepository.findPfIdListByPfIdSupervisor(supPfId);
			Map<String, Integer> mapDataList = null;		
			try{
				 mapDataList = new HashMap<String, Integer>();	
			    int twoToFourHrsCount = ticketCentorAgeingRepo.find2_4HoursCountCMS(supList);
				int oneDaysCount = ticketCentorAgeingRepo.find_1_DaysCountCMS(supList);
				int threeDaysCount = ticketCentorAgeingRepo.find_3_Days_LessCountCMS(supList);
				int threeDayGreaterCount = ticketCentorAgeingRepo.find_3_Days_GreaterThanCountCMS(supList);
				int totalCount = ticketCentorAgeingRepo.findTotalCountCMS(supList);
				
				
				mapDataList.put("TwoToFourHrsCount", twoToFourHrsCount);
				mapDataList.put("OneDaysCount", oneDaysCount);
				mapDataList.put("ThreeDaysLessCount", threeDaysCount);
				mapDataList.put("ThreeDayGreaterCount", threeDayGreaterCount);
				mapDataList.put("TotalCount", totalCount);
			
			}catch (Exception e) {
				logger.error("Exception "+ExceptionConstants.EXCEPTION);
			}
			return mapDataList;
		}		
		
		
		 @Override
	    public Map<String, Object> findAllCategory(){
	    	List<Object[]>  categorylist=ticketCentorRepo.findAllCategory();
	    	Map<String, Object> mapData=new HashMap<String, Object>();
	    	if(categorylist!=null && !categorylist.isEmpty()){
	           for (Object[] object : categorylist) {
	        	  mapData.put(new String((String) object[0]), object[1]); 
	           }
	    	}
	           return mapData;
	    }
		 
		 @Override
		 public Map<String, Object> findAllCategoryCMF(){
			 UserDto user = (UserDto) session().getAttribute("userObj");
			 String pfId = user.getPfId();
		    	List<Object[]>  categorylist=ticketCentorRepo.findAllCategoryByCMF(pfId);
		    	Map<String, Object> mapData=new HashMap<String, Object>();
		    	if(categorylist!=null && !categorylist.isEmpty()){
		           for (Object[] object : categorylist) {
		        	  mapData.put(new String((String) object[0]), object[1]); 
		           }
		    	}
		           return mapData;
		    }
	    
		 @Override
		 public Map<String, Object> findAllCategoryCMS(){
			 UserDto user = (UserDto) session().getAttribute("userObj");			 
			 String supPfId = user.getPfId();
			Set<String> supList =  supervisorRepository.findPfIdListByPfIdSupervisor(supPfId);
		    	List<Object[]>  categorylist=ticketCentorRepo.findAllCategoryByCMS(supList);
		    	Map<String, Object> mapData=new HashMap<String, Object>();
		    	if(categorylist!=null && !categorylist.isEmpty()){
		           for (Object[] object : categorylist) {
		        	  mapData.put(new String((String) object[0]), object[1]); 
		           }
		    	}
		           return mapData;
		    }
		 
		 
    @Override 
	public List<CallTypeDto> findSubCategoryByCategory(String category){
			 Set<String> supList = null;
			 UserDto user = (UserDto) session().getAttribute("userObj");
			 if("CMS".equals(user.getRole())){
				 String supPfId = user.getPfId();
				supList =  supervisorRepository.findPfIdListByPfIdSupervisor(supPfId);
			 	}
			 else{
				 String pfId = user.getPfId();
				  supList = new HashSet<>();
				  supList.add(pfId);
			 }
			 List<Object[]>  list= ticketCentorRepo.findByCategoryCMF(category,supList);
			
             List<CallTypeDto> callTypeList=new ArrayList<>();
			 
			 for(Object[] object1 :list){
				 CallTypeDto callType=new CallTypeDto();
				
				 int count = ((BigDecimal) object1[1]).intValue();
				
				 
				
				 
				 callType.setCategory((String) object1[2]);
				 callType.setCount(count);
				 callType.setSubCategory((String) object1[0]);
				 callTypeList.add(callType);
				 
			 }	
			 
			
			 
			 
		    return callTypeList;
			 
		 }


		@Override
		public List<TicketCentorDto> findByCategoryAndSubCategory(String category, String subCategory) {
			List<TicketCentorDto> listData=null;
		try{
		 
			List<TicketCentor> ticketCentorList=ticketCentorRepo.findByCategoryAndSubCate(category, subCategory);
			listData=new ArrayList<>();
			for(TicketCentor ticketCentor:ticketCentorList){
				TicketCentorDto dto=new TicketCentorDto(ticketCentor);
				listData.add(dto);
			}
			}catch (Exception e) {
				logger.error("Exception "+ExceptionConstants.EXCEPTION);
			}
			return listData;
		}


		@Override
		public Page<TicketCentorDto> findPaginatedCC(int page, int size) {
			// TODO Auto-generated method stub
			return null;
		}		
		
		@Override
		 public Page<TicketCentorDto> findPaginatedCmf(final int page, final int size){
			logger.info("Inside======findPaginatedCmf===========");
			UserDto user = (UserDto) session().getAttribute("userObj"); 
			Page<TicketCentorDto> entities = ticketCentorRepo.findAllByCMFUser(user.getPfId(),PageRequest.of(page, size)).map(TicketCentorDto::new);
			 String circle=null;
			 for(TicketCentorDto dto:entities){
				 
				 String kioskId=dto.getKisokId();
				 
				  if(kioskId!=null){
					  String kioskBranchCode= kioskMasterRepo.findKioskByBranchCode(kioskId);
					  circle= branchMasterRepo.findCircleByBranchCode(kioskBranchCode);
					  }
					  dto.setServeriry(circle);
				 
				 
			 }			 
			 return entities;			 
		 }
		
		@Override
		 public Page<TicketCentorDto> findPaginatedCms(final int page, final int size){
			UserDto user = (UserDto) session().getAttribute("userObj");
			String supPfId = user.getPfId();
			Set<String> supList =  supervisorRepository.findPfIdListByPfIdSupervisor(supPfId);
			
			Page<TicketCentorDto> entities = ticketCentorRepo.findAllByCMFUserForCMS(supList,PageRequest.of(page, size)).map(TicketCentorDto::new);
			 String circle=null;
			 for(TicketCentorDto dto:entities){
				 
				 String kioskId=dto.getKisokId();
				 
				  if(kioskId!=null){
					  String kioskBranchCode= kioskMasterRepo.findKioskByBranchCode(kioskId);
					  circle= branchMasterRepo.findCircleByBranchCode(kioskBranchCode);
					  }
					  dto.setServeriry(circle);
				 
				 
			 }			 
			 return entities;			 
		 }
		
		@Override
		public Page<TicketCentorDto> findPaginatedCountCmf(int page, int size,String type) {
			logger.info("Inside======findPaginatedCountCmf===========");
			 Page<TicketCentorDto> entities = null;
			 UserDto user = (UserDto) session().getAttribute("userObj"); 
			 String pfId = user.getPfId();
			 try{
				 logger.info("Inside======findPaginatedCountCmf=======outside==type!=null && !type.isEmpty()="+type);
				 if(type!=null && !type.isEmpty()){
				 logger.info("Inside======findPaginatedCountCmf=====inside==type!=null && !type.isEmpty()="+type);
				 if(type!=null && type.equals("High")){
					  entities= ticketCentorRepo.findAllByRiskAndCMFUser(type,pfId, PageRequest.of(page, size)).map(TicketCentorDto::new);
				  }else if(type!=null && type.equals("Medium")){
				     entities= ticketCentorRepo.findAllByRiskAndCMFUser(type,pfId, PageRequest.of(page, size)).map(TicketCentorDto::new);
				 }else if(type!=null && type.equals("Low")){
				     entities= ticketCentorRepo.findAllByRiskAndCMFUser(type,pfId, PageRequest.of(page, size)).map(TicketCentorDto::new);
				 }else if(type!=null && type.equals("Total")){
				     entities =  ticketCentorRepo.findAllByAllRiskAndCMFUser("High","Medium","Low",pfId,PageRequest.of(page, size)).map(TicketCentorDto::new);
				  }else if(type!=null && type.equals("TwoToFourHrsCount")){
					  entities=ticketCentorAgeingRepo.findAllTicketCentor4HourAndCMFUser(pfId,PageRequest.of(page, size)).map(TicketCentorDto::new);
				  }else if(type!=null && type.equals("OneDaysCount")){
					  entities=ticketCentorAgeingRepo.findAllTicketCentor1DaysAndCMFUser(pfId,PageRequest.of(page, size)).map(TicketCentorDto::new);
				 }else if(type!=null && type.equals("ThreeDaysLessCount")){
					 entities=ticketCentorAgeingRepo.findAllTicketCentor3DaysLessAndCMFUser(pfId,PageRequest.of(page, size)).map(TicketCentorDto::new);
			    }else if(type!=null && type.equals("ThreeDayGreaterCount")){
			    	entities=ticketCentorAgeingRepo.findAllTicketCentor3DaysGreaterAndCMFUser(pfId,PageRequest.of(page, size)).map(TicketCentorDto::new);
			    } 
			    else if(type!=null && type.equals("TotalCount")){
			    	entities=ticketCentorAgeingRepo.findAllTicketTotalListAndCMFUser(pfId,PageRequest.of(page, size)).map(TicketCentorDto::new);
			    }
			    else if(type !=null && type !="" && type !="undefined" && !type.equals("TotalCount")){	    	
			    	entities= ticketCentorRepo.findByCallSubCategoryAndCMFUser(type,pfId, PageRequest.of(page, size,Sort.by("TICKET_ID").descending())).map(TicketCentorDto::new);	         
			    }else{
			    	logger.info("Inside======findPaginatedCountCmf======inside==else");
					  entities =  ticketCentorRepo.findAllByCMFUser(pfId,PageRequest.of(page, size)).map(TicketCentorDto::new);
				      }
				 }
				 else{
				    logger.info("Inside======findPaginatedCountCmf======inside==NO ONE");
					entities =  ticketCentorRepo.findAllByCMFUser(pfId,PageRequest.of(page, size)).map(TicketCentorDto::new);
				}
			 }catch (Exception e) {
				 e.printStackTrace();
				// logger.error("Exception "+ExceptionConstants.EXCEPTION);
			}
		 	return entities;
	   }
		
		@Override
		public Page<TicketCentorDto> findPaginatedCountCms(int page, int size,String type) {	 
			 Page<TicketCentorDto> entities = null;
			 UserDto user = (UserDto) session().getAttribute("userObj"); 
			 String supPfId = user.getPfId();
			Set<String> supList =  supervisorRepository.findPfIdListByPfIdSupervisor(supPfId);				
			 try{
				 
				 if(type!=null && !type.isEmpty()){
				 if(type!=null && type.equals("High")){
					  entities= ticketCentorRepo.findAllByRiskAndCMSUser(type,supList, PageRequest.of(page, size)).map(TicketCentorDto::new);
				  }else if(type!=null && type.equals("Medium")){
				     entities= ticketCentorRepo.findAllByRiskAndCMSUser(type,supList, PageRequest.of(page, size)).map(TicketCentorDto::new);
				 }else if(type!=null && type.equals("Low")){
				     entities= ticketCentorRepo.findAllByRiskAndCMSUser(type,supList, PageRequest.of(page, size)).map(TicketCentorDto::new);
				 }else if(type!=null && type.equals("Total")){
				     entities =  ticketCentorRepo.findAllByAllRiskAndCMSUser("High","Medium","Low",supList,PageRequest.of(page, size)).map(TicketCentorDto::new);
				  }else if(type!=null && type.equals("TwoToFourHrsCount")){
					  entities=ticketCentorAgeingRepo.findAllTicketCentor4HourAndCMSUser(supList,PageRequest.of(page, size)).map(TicketCentorDto::new);
				  }else if(type!=null && type.equals("OneDaysCount")){
					  entities=ticketCentorAgeingRepo.findAllTicketCentor1DaysAndCMSUser(supList,PageRequest.of(page, size)).map(TicketCentorDto::new);
				 }else if(type!=null && type.equals("ThreeDaysLessCount")){
					 entities=ticketCentorAgeingRepo.findAllTicketCentor3DaysLessAndCMSUser(supList,PageRequest.of(page, size)).map(TicketCentorDto::new);
			    }else if(type!=null && type.equals("ThreeDayGreaterCount")){
			    	entities=ticketCentorAgeingRepo.findAllTicketCentor3DaysGreaterAndCMSUser(supList,PageRequest.of(page, size)).map(TicketCentorDto::new);
			    } else if(type !=null && type !="" && type !="undefined" && !type.equals("TotalCount")){	    	
			    	entities= ticketCentorRepo.findByCallSubCategoryAndCMSUser(type,supList, PageRequest.of(page, size,Sort.by("TICKET_ID").descending())).map(TicketCentorDto::new);	         
			    }else{
					  entities =  ticketCentorRepo.findAllByCMSUser(supList,PageRequest.of(page, size)).map(TicketCentorDto::new);
				      }
				 }else{
					  entities =  ticketCentorRepo.findAllByCMSUser(supList,PageRequest.of(page, size)).map(TicketCentorDto::new);
				      }
			 }catch (Exception e) {
		//		 e.printStackTrace();
				 logger.error("Exception "+ExceptionConstants.EXCEPTION);
			}
		 	return entities;
	   }


		
		
		
		
		
		@Override
		 public Page<TicketCentorDto> findPaginatedCmfSearchText(final int page, final int size,String searchText){
			logger.info("Inside======findPaginatedCmfSearchText====searchText======="+searchText);
			UserDto user = (UserDto) session().getAttribute("userObj"); 
			Page<TicketCentorDto> entities = ticketCentorSearchTextRepo.findAllByCMFUser(user.getPfId(),searchText,PageRequest.of(page, size)).map(TicketCentorDto::new);
			 String circle=null;
			 for(TicketCentorDto dto:entities){
				 
				 String kioskId=dto.getKisokId();
				 
				  if(kioskId!=null){
					  String kioskBranchCode= kioskMasterRepo.findKioskByBranchCode(kioskId);
					  circle= branchMasterRepo.findCircleByBranchCode(kioskBranchCode);
					  }
					  dto.setServeriry(circle);
				 
				 
			 }			 
			 return entities;			 
		 }
		
		
		
		@Override
		public Page<TicketCentorDto> findPaginatedCountCmfSearchText(int page, int size,String type,String searchText) {
			logger.info("Inside======findPaginatedCountCmf===========");
			 Page<TicketCentorDto> entities = null;
			 UserDto user = (UserDto) session().getAttribute("userObj"); 
			 String pfId = user.getPfId();
			 //ticketCentorSearchTextRepo
			 
			 try{
				 logger.info("Inside======findPaginatedCountCmf=======outside==type!=null && !type.isEmpty()="+type);
				 if(type!=null && !type.isEmpty()){
				 logger.info("Inside======findPaginatedCountCmf=====inside==type!=null && !type.isEmpty()="+type);
				 if(type!=null && type.equals("High")){
					  entities= ticketCentorSearchTextRepo.findAllByRiskAndCMFUser(type,pfId,searchText, PageRequest.of(page, size)).map(TicketCentorDto::new);
				  }else if(type!=null && type.equals("Medium")){
				     entities= ticketCentorSearchTextRepo.findAllByRiskAndCMFUser(type,pfId,searchText, PageRequest.of(page, size)).map(TicketCentorDto::new);
				 }else if(type!=null && type.equals("Low")){
				     entities= ticketCentorSearchTextRepo.findAllByRiskAndCMFUser(type,pfId,searchText, PageRequest.of(page, size)).map(TicketCentorDto::new);
				 }else if(type!=null && type.equals("Total")){
				     entities =  ticketCentorSearchTextRepo.findAllByAllRiskAndCMFUser("High","Medium","Low",pfId,searchText,PageRequest.of(page, size)).map(TicketCentorDto::new);
				  }else if(type!=null && type.equals("TwoToFourHrsCount")){
					  entities=ticketCentorSearchTextRepo.findAllTicketCentor4HourAndCMFUser(pfId,searchText,PageRequest.of(page, size)).map(TicketCentorDto::new);
				  }else if(type!=null && type.equals("OneDaysCount")){
					  entities=ticketCentorSearchTextRepo.findAllTicketCentor1DaysAndCMFUser(pfId,searchText,PageRequest.of(page, size)).map(TicketCentorDto::new);
				 }else if(type!=null && type.equals("ThreeDaysLessCount")){
					 entities=ticketCentorSearchTextRepo.findAllTicketCentor3DaysLessAndCMFUser(pfId,searchText,PageRequest.of(page, size)).map(TicketCentorDto::new);
			    }else if(type!=null && type.equals("ThreeDayGreaterCount")){
			    	entities=ticketCentorSearchTextRepo.findAllTicketCentor3DaysGreaterAndCMFUser(pfId,searchText,PageRequest.of(page, size)).map(TicketCentorDto::new);
			    } 
			    else if(type!=null && type.equals("TotalCount")){
			    	entities=ticketCentorSearchTextRepo.findAllTicketTotalListAndCMFUser(pfId,searchText,PageRequest.of(page, size)).map(TicketCentorDto::new);
			    }
			    else if(type !=null && type !="" && type !="undefined" && !type.equals("TotalCount")){	    	
			    	entities= ticketCentorSearchTextRepo.findByCallSubCategoryAndCMFUser(type,pfId,searchText, PageRequest.of(page, size,Sort.by("TICKET_ID").descending())).map(TicketCentorDto::new);	         
			    }else{
			    	logger.info("Inside======findPaginatedCountCmf======inside==else");
					  entities =  ticketCentorSearchTextRepo.findAllByCMFUser(pfId,searchText,PageRequest.of(page, size)).map(TicketCentorDto::new);
				      }
				 }
				 else{
				    logger.info("Inside======findPaginatedCountCmf======inside==NO ONE");
					entities =  ticketCentorSearchTextRepo.findAllByCMFUser(pfId,searchText,PageRequest.of(page, size)).map(TicketCentorDto::new);
				}
			 }catch (Exception e) {
				 e.printStackTrace();
				// logger.error("Exception "+ExceptionConstants.EXCEPTION);
			}
		 	return entities;
	   }
		
		
		// CMS Search text
		
		
		
		@Override
		public Page<TicketCentorDto> findPaginatedCountCmsSearchText(int page, int size,String type,String searchText) {	 
			 Page<TicketCentorDto> entities = null;
			 UserDto user = (UserDto) session().getAttribute("userObj"); 
			 String supPfId = user.getPfId();
			Set<String> supList =  supervisorRepository.findPfIdListByPfIdSupervisor(supPfId);				
			 try{
				 
				 if(type!=null && !type.isEmpty()){
				 if(type!=null && type.equals("High")){
					  entities= ticketCentorSearchTextRepo.findAllByRiskAndCMSUser(type,supList,searchText, PageRequest.of(page, size)).map(TicketCentorDto::new);
				  }else if(type!=null && type.equals("Medium")){
				     entities= ticketCentorSearchTextRepo.findAllByRiskAndCMSUser(type,supList,searchText, PageRequest.of(page, size)).map(TicketCentorDto::new);
				 }else if(type!=null && type.equals("Low")){
				     entities= ticketCentorSearchTextRepo.findAllByRiskAndCMSUser(type,supList,searchText, PageRequest.of(page, size)).map(TicketCentorDto::new);
				 }else if(type!=null && type.equals("Total")){
				     entities =  ticketCentorSearchTextRepo.findAllByAllRiskAndCMSUser("High","Medium","Low",supList,searchText,PageRequest.of(page, size)).map(TicketCentorDto::new);
				  }else if(type!=null && type.equals("TwoToFourHrsCount")){
					  entities=ticketCentorSearchTextRepo.findAllTicketCentor4HourAndCMSUser(supList,searchText,PageRequest.of(page, size)).map(TicketCentorDto::new);
				  }else if(type!=null && type.equals("OneDaysCount")){
					  entities=ticketCentorSearchTextRepo.findAllTicketCentor1DaysAndCMSUser(supList,searchText,PageRequest.of(page, size)).map(TicketCentorDto::new);
				 }else if(type!=null && type.equals("ThreeDaysLessCount")){
					 entities=ticketCentorSearchTextRepo.findAllTicketCentor3DaysLessAndCMSUser(supList,searchText,PageRequest.of(page, size)).map(TicketCentorDto::new);
			    }else if(type!=null && type.equals("ThreeDayGreaterCount")){
			    	entities=ticketCentorSearchTextRepo.findAllTicketCentor3DaysGreaterAndCMSUser(supList,searchText,PageRequest.of(page, size)).map(TicketCentorDto::new);
			    } else if(type !=null && type !="" && type !="undefined" && !type.equals("TotalCount")){	    	
			    	entities= ticketCentorSearchTextRepo.findByCallSubCategoryAndCMSUser(type,supList,searchText, PageRequest.of(page, size,Sort.by("TICKET_ID").descending())).map(TicketCentorDto::new);	         
			    }else{
					  entities =  ticketCentorSearchTextRepo.findAllByCMSUser(supList,searchText,PageRequest.of(page, size)).map(TicketCentorDto::new);
				      }
				 }else{
					  entities =  ticketCentorSearchTextRepo.findAllByCMSUser(supList,searchText,PageRequest.of(page, size)).map(TicketCentorDto::new);
				      }
			 }catch (Exception e) {
		//		 e.printStackTrace();
				 logger.error("Exception "+ExceptionConstants.EXCEPTION);
			}
		 	return entities;
	   }


		
		@Override
		 public Page<TicketCentorDto> findPaginatedCmsSearchText(final int page, final int size,String searchText){
			UserDto user = (UserDto) session().getAttribute("userObj");
			String supPfId = user.getPfId();
			Set<String> supList =  supervisorRepository.findPfIdListByPfIdSupervisor(supPfId);
			
			Page<TicketCentorDto> entities = ticketCentorSearchTextRepo.findAllByCMFUserForCMS(supList,searchText,PageRequest.of(page, size)).map(TicketCentorDto::new);
			 String circle=null;
			 for(TicketCentorDto dto:entities){
				 
				 String kioskId=dto.getKisokId();
				 
				  if(kioskId!=null){
					  String kioskBranchCode= kioskMasterRepo.findKioskByBranchCode(kioskId);
					  circle= branchMasterRepo.findCircleByBranchCode(kioskBranchCode);
					  }
					  dto.setServeriry(circle);
				 
				 
			 }			 
			 return entities;			 
		 }
		
		
		

		@Override
		public Page<TicketCentorDto> findPaginatedByCircle(int page, int size) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Page<TicketCentorDto> findPaginatedCountByCircle(int page,
				int size, String type) {
			// TODO Auto-generated method stub
			return null;
		}		

}

