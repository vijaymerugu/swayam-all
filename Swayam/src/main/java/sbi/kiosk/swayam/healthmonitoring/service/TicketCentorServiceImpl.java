package sbi.kiosk.swayam.healthmonitoring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import sbi.kiosk.swayam.common.dto.TicketCentorDto;
import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.repository.KioskMasterRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.CallTypeRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.TicketCentorAgeingRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.TicketCentorRepository;
import sbi.kiosk.swayam.kioskmanagement.repository.BranchMasterRepository;

@Service
public class TicketCentorServiceImpl implements TicketCentorService {
	
	Logger logger = LoggerFactory.getLogger(TicketCentorServiceImpl.class);
	
	@Autowired
	TicketCentorRepository ticketCentorRepo;
	@Autowired
	private TicketCentorAgeingRepository ticketCentorAgeingRepo;
	
	@Autowired
	CallTypeRepository callTypeRepo;
	
	@Autowired
	KioskMasterRepository kioskMasterRepo;
	@Autowired
	BranchMasterRepository branchMasterRepo;
	
	public static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }
	
	 @Override
	 public Page<TicketCentorDto> findPaginated(final int page, final int size){
		 logger.info("Inside======findPaginatedCC===========ALL DATA");
		 //Page<TicketCentorDto> entities = ticketCentorRepo.findAll("Active",PageRequest.of(page, size)).map(TicketCentorDto::new);
		// changes for status of complaint is active only
		 Page<TicketCentorDto> entities = ticketCentorRepo.findAllByStatus("Active",PageRequest.of(page, size)).map(TicketCentorDto::new);
		 String circle=null;
		 TicketCentorDto ticketCentorDto= new TicketCentorDto();
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
	 public Page<TicketCentorDto> findPaginatedByCircle(final int page, final int size){
		 UserDto user = (UserDto) session().getAttribute("userObj");
		 
		 Page<TicketCentorDto> entities = ticketCentorRepo.findAllByCircle(user.getCircle(),PageRequest.of(page, size)).map(TicketCentorDto::new);
		 TicketCentorDto ticketCentorDto= new TicketCentorDto();
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
	 
	 
		/*
		 * @Override public Page<TicketCentorDto> findPaginatedCount(int page, int
		 * size,String type) {
		 * 
		 * 
		 * Page<TicketCentorDto> entities = null;
		 * 
		 * if(type!=null && !type.isEmpty()){
		 * 
		 * if(type!=null && type.equals("High")){ entities=
		 * ticketCentorRepo.findAll(type, PageRequest.of(page,
		 * size)).map(TicketCentorDto::new); }else if(type!=null &&
		 * type.equals("Medium")){ entities= ticketCentorRepo.findAll(type,
		 * PageRequest.of(page, size)).map(TicketCentorDto::new); }else if(type!=null &&
		 * type.equals("Low")){ entities= ticketCentorRepo.findAll(type,
		 * PageRequest.of(page, size)).map(TicketCentorDto::new); }else if(type!=null &&
		 * type.equals("Total")){ entities =
		 * ticketCentorRepo.findAllByRisk("High","Medium","Low",PageRequest.of(page,
		 * size)).map(TicketCentorDto::new); }else if(type!=null &&
		 * type.equals("TwoToFourHrsCount")){
		 * entities=ticketCentorAgeingRepo.findAllTicketCentor4Hour(PageRequest.of(page,
		 * size)).map(TicketCentorDto::new); }else if(type!=null &&
		 * type.equals("OneDaysCount")){
		 * entities=ticketCentorAgeingRepo.findAllTicketCentor1Days(PageRequest.of(page,
		 * size)).map(TicketCentorDto::new); }else if(type!=null &&
		 * type.equals("ThreeDaysLessCount")){
		 * entities=ticketCentorAgeingRepo.findAllTicketCentor3DaysLess(PageRequest.of(
		 * page, size)).map(TicketCentorDto::new); }else if(type!=null &&
		 * type.equals("ThreeDayGreaterCount")){
		 * entities=ticketCentorAgeingRepo.findAllTicketCentor3DaysGreater(PageRequest.
		 * of(page, size)).map(TicketCentorDto::new); } else{ entities =
		 * ticketCentorRepo.findAll(PageRequest.of(page,
		 * size)).map(TicketCentorDto::new); }
		 * 
		 * } else{ entities = ticketCentorRepo.findAll(PageRequest.of(page,
		 * size)).map(TicketCentorDto::new); } for(TicketCentorDto dto:entities){
		 * 
		 * String kioskId=dto.getKisokId();
		 * 
		 * String kioskBranchMaster= kioskMasterRepo.findKioskByKioskId_circle(kioskId);
		 * dto.setServeriry(kioskBranchMaster);
		 * 
		 * 
		 * }
		 * 
		 * return entities; }
		 */
	 
	 @Override
	    public Page<TicketCentorDto> findPaginatedCount(int page, int size,String type) {	 
		 
		 
		 Page<TicketCentorDto> entities = null;
		 
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
			// entities=ticketCentorAgeingRepo.findAllTicketCentor3DaysGreater(PageRequest.of(page, size)).map(TicketCentorDto::new);
		 }else if(type!=null && type.equals("ThreeDayGreaterCount")){
	    	entities=ticketCentorAgeingRepo.findAllTicketCentor3DaysGreater(PageRequest.of(page, size)).map(TicketCentorDto::new);
	    } else{
			  entities =  ticketCentorRepo.findAll(PageRequest.of(page, size)).map(TicketCentorDto::new);
		      }
		  
		 }
		 else{
			  entities =  ticketCentorRepo.findAll(PageRequest.of(page, size)).map(TicketCentorDto::new);
		      }
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
	    public Page<TicketCentorDto> findPaginatedCountByCircle(int page, int size,String type) {	 
		 
		 UserDto user = (UserDto) session().getAttribute("userObj");
		 String circle =user.getCircle();
		 Page<TicketCentorDto> entities = null;
		 
		 if(type!=null && !type.isEmpty()){
		 
		 if(type!=null && type.equals("High")){
			  entities= ticketCentorRepo.findAllByCircleAndType(circle,type, PageRequest.of(page, size)).map(TicketCentorDto::new);
		  }else if(type!=null && type.equals("Medium")){
		     entities= ticketCentorRepo.findAllByCircleAndType(circle,type, PageRequest.of(page, size)).map(TicketCentorDto::new);
		 }else if(type!=null && type.equals("Low")){
		     entities= ticketCentorRepo.findAllByCircleAndType(circle,type, PageRequest.of(page, size)).map(TicketCentorDto::new);
		 }else if(type!=null && type.equals("Total")){
		     entities =  ticketCentorRepo.findAllByRiskByCircle(circle,"High","Medium","Low",PageRequest.of(page, size)).map(TicketCentorDto::new);
		  }else if(type!=null && type.equals("TwoToFourHrsCount")){
			  entities=ticketCentorAgeingRepo.findAllTicketCentor4HourByCircle(circle,PageRequest.of(page, size)).map(TicketCentorDto::new);
		  }else if(type!=null && type.equals("OneDaysCount")){
			  entities=ticketCentorAgeingRepo.findAllTicketCentor1DaysByCircle(circle,PageRequest.of(page, size)).map(TicketCentorDto::new);
		 }else if(type!=null && type.equals("ThreeDaysLessCount")){
			 entities=ticketCentorAgeingRepo.findAllTicketCentor3DaysLessByCircle(circle,PageRequest.of(page, size)).map(TicketCentorDto::new);
	    }else if(type!=null && type.equals("ThreeDayGreaterCount")){
	    	entities=ticketCentorAgeingRepo.findAllTicketCentor3DaysGreaterByCircle(circle,PageRequest.of(page, size)).map(TicketCentorDto::new);
	    } else{
			  entities =  ticketCentorRepo.findAllByCircle(circle,PageRequest.of(page, size)).map(TicketCentorDto::new);
		      }		  
		 }
		 else{
			  entities =  ticketCentorRepo.findAllByCircle(circle,PageRequest.of(page, size)).map(TicketCentorDto::new);
		      }
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
		public Map<String,Integer> findAllSeverityOfTicketsCount(){	
			Map<String, Integer> mapDataList = new HashMap<String, Integer>();			
			
			 
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
			
			return mapDataList;
		}
		
		@Override
		public Map<String,Integer> findAllSeverityOfTicketsCountByCircle(){	
			UserDto user = (UserDto) session().getAttribute("userObj");
			 String circle =user.getCircle();
			Map<String, Integer> mapDataList = new HashMap<String, Integer>();			
			
			 
			    int countHigh = callTypeRepo.getCallTypeHiegh(circle);
				int countMedium = callTypeRepo.getCallTypeMedium(circle);
				int countLow = callTypeRepo.getCallTypeLow(circle);
				int countTotal = callTypeRepo.getCallTypeTotal(circle);
				
				
				mapDataList.put("High", countHigh);
				mapDataList.put("Medium", countMedium);
				mapDataList.put("Low", countLow);
				mapDataList.put("Total", countTotal);
			
				for (Map.Entry<String, Integer> entry : mapDataList.entrySet()) {

				}
			
			return mapDataList;
		}

		
		
		
		@Override
		public Map<String,Integer> findAllAgeingOfTicketsCount(){	
			Map<String, Integer> mapDataList = new HashMap<String, Integer>();			
			
			 
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
			
				for (Map.Entry<String, Integer> entry : mapDataList.entrySet()) {

				}
			return mapDataList;
		}
		
		@Override
		public Map<String,Integer> findAllAgeingOfTicketsCountByCircle(){	
			UserDto user = (UserDto) session().getAttribute("userObj");
			 String circle =user.getCircle();
			Map<String, Integer> mapDataList = new HashMap<String, Integer>();			
			
			 
			    int twoToFourHrsCount = ticketCentorAgeingRepo.find2_4HoursCount(circle);
				int oneDaysCount = ticketCentorAgeingRepo.find_1_DaysCount(circle);
				int threeDaysCount = ticketCentorAgeingRepo.find_3_Days_LessCount(circle);
				int threeDayGreaterCount = ticketCentorAgeingRepo.find_3_Days_GreaterThanCount(circle);
				int totalCount = ticketCentorAgeingRepo.findTotalCount(circle);
				
				
				mapDataList.put("TwoToFourHrsCount", twoToFourHrsCount);
				mapDataList.put("OneDaysCount", oneDaysCount);
				mapDataList.put("ThreeDaysLessCount", threeDaysCount);
				mapDataList.put("ThreeDayGreaterCount", threeDayGreaterCount);
				mapDataList.put("TotalCount", totalCount);
			
				for (Map.Entry<String, Integer> entry : mapDataList.entrySet()) {

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
		public Page<TicketCentorDto> findPaginatedCC(int page, int size) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public Page<TicketCentorDto> findPaginatedCmf(int page, int size) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Page<TicketCentorDto> findPaginatedCountCmf(int page, int size,
				String type) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Page<TicketCentorDto> findPaginatedCms(int page, int size) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Page<TicketCentorDto> findPaginatedCountCms(int page, int size,
				String type) {
			// TODO Auto-generated method stub
			return null;
		}

}

