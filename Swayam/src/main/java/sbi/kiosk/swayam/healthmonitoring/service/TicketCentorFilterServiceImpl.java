package sbi.kiosk.swayam.healthmonitoring.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.common.dto.CallTypeDto;
import sbi.kiosk.swayam.common.dto.TicketCentorDto;
import sbi.kiosk.swayam.common.entity.KioskBranchMaster;
import sbi.kiosk.swayam.common.entity.TicketCentor;
import sbi.kiosk.swayam.common.repository.KioskMasterRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.CallTypeRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.TicketCentorAgeingRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.TicketCentorRepository;

@Service
public class TicketCentorFilterServiceImpl implements TicketCentorFilterService {
	@Autowired
	TicketCentorRepository ticketCentorRepo;
	@Autowired
	private TicketCentorAgeingRepository ticketCentorAgeingRepo;
	
	@Autowired
	CallTypeRepository callTypeRepo;
	
	@Autowired
	KioskMasterRepository kioskMasterRepo;
	

	 @Override
	 public Page<TicketCentorDto> findPaginated(final int page, final int size){
		 Page<TicketCentorDto> entities = ticketCentorRepo.findAll(PageRequest.of(page, size)).map(TicketCentorDto::new);
		 TicketCentorDto ticketCentorDto= new TicketCentorDto();
		 for(TicketCentorDto dto:entities.getContent()){
			 
			 String kioskId=dto.getKisokId();
			 
			 String kioskBranchMaster= kioskMasterRepo.findKioskByKioskId_circle(kioskId);
			 ticketCentorDto.setServeriry(kioskBranchMaster);
			 System.out.println(dto.getKisokId());
			 
			 
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
		    } else{
				  entities =  ticketCentorRepo.findAll(PageRequest.of(page, size)).map(TicketCentorDto::new);
			      }
			 }
		 }catch (Exception e) {
			 e.printStackTrace();
		}
	 	return entities;
   }

		@Override
		public Map<String,Integer> findAllSeverityOfTicketsCount(){	
			Map<String, Integer> mapDataList = null;
		 try{
			 System.out.println("findAllSeverityOfTicketsCount Start():::");
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
					System.out.println("key===" + entry.getKey());
					System.out.println("Value " + entry.getValue());

				}
				System.out.println("countKioskCount::::::::::mapData:::::::::::" + mapDataList.size());
		 }catch (Exception e) {
            e.printStackTrace();
		 } 
			
			return mapDataList;
		}
		
		
		@Override
		public Map<String,Integer> findAllAgeingOfTicketsCount(){	
			Map<String, Integer> mapDataList = null;		
			try{
				 System.out.println("findAllAgeingOfTicketsCount Start():::");
				 mapDataList = new HashMap<String, Integer>();	
			    int twoToFourHrsCount = ticketCentorAgeingRepo.find2_4HoursCount();
				System.out.println("twoToFourHrsCount==" + twoToFourHrsCount);
				int oneDaysCount = ticketCentorAgeingRepo.find_1_DaysCount();
				int threeDaysCount = ticketCentorAgeingRepo.find_3_Days_LessCount();
				int threeDayGreaterCount = ticketCentorAgeingRepo.find_3_Days_GreaterThanCount();
				int totalCount = ticketCentorAgeingRepo.findTotalCount();
				
				
				mapDataList.put("TwoToFourHrsCount", twoToFourHrsCount);
				mapDataList.put("OneDaysCount", oneDaysCount);
				mapDataList.put("ThreeDaysLessCount", threeDaysCount);
				mapDataList.put("ThreeDayGreaterCount", threeDayGreaterCount);
				mapDataList.put("TotalCount", totalCount);
				System.out.println("findAllAgeingOfTicketsCount::::::::::mapData:::::::::::" + mapDataList.size());
			 
			}catch (Exception e) {
                e.printStackTrace();
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
	public List<CallTypeDto> findSubCategoryByCategory(String category){
			 System.out.println("findSubCategoryByCategory==="+category);
			 
			 List<Object[]>  list= ticketCentorRepo.findByCategory(category);
			 System.out.println("list=Size::="+list.size());
             List<CallTypeDto> callTypeList=new ArrayList<>();
			 
			 for(Object[] object1 :list){
				 CallTypeDto callType=new CallTypeDto();
				 System.out.println("getCallCategory==1======"+object1[0]);
				 System.out.println("getCallCategory==2======"+object1[1]);
				 System.out.println("getCallCategory==3======"+object1[2]);
				 
				 int count = ((BigDecimal) object1[1]).intValue();
				 System.out.println("grandChildCount:::"+count);
				 
				
				 
				 callType.setCategory((String) object1[2]);
				 callType.setCount(count);
				 callType.setSubCategory((String) object1[0]);
				 callTypeList.add(callType);
				 
			 }	
			 
			 System.out.println("callTypeList Size::"+callTypeList.size());
			 System.out.println("callTypeList ::::"+callTypeList.toString());
			 
			 
		    return callTypeList;
			 
		 }


		@Override
		public List<TicketCentorDto> findByCategoryAndSubCategory(String category, String subCategory) {
			List<TicketCentorDto> listData=null;
		try{
		    System.out.println("findByCategoryAndSubCategory Start():::");
			List<TicketCentor> ticketCentorList=ticketCentorRepo.findByCategoryAndSubCate(category, subCategory);
			listData=new ArrayList<>();
			for(TicketCentor ticketCentor:ticketCentorList){
				System.out.println("listData Size()::=="+listData.size());
				TicketCentorDto dto=new TicketCentorDto(ticketCentor);
				listData.add(dto);
			}
			System.out.println("listData=22222222222222="+listData);
			}catch (Exception e) {
               e.printStackTrace();
			}
			return listData;
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

}

