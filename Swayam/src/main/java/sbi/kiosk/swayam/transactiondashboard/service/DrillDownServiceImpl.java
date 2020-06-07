package sbi.kiosk.swayam.transactiondashboard.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sbi.kiosk.swayam.common.dto.DrillDownDto;
import sbi.kiosk.swayam.common.entity.DrillDown;
import sbi.kiosk.swayam.common.entity.ZeroTransactionKiosks;
//import sbi.kiosk.swayam.transactiondashboard.repository.DrillDownPagingRepository;
import sbi.kiosk.swayam.transactiondashboard.repository.DrillDownRepository;

@Service
public class DrillDownServiceImpl implements DrillDownService{
	
	@Autowired
	DrillDownRepository drillDownRepository;
	
	@PersistenceContext
    private EntityManager em;
	
//	public List<DrillDownDto> list(){
//		
//		List<DrillDownDto> drillDownDTOList=new ArrayList<DrillDownDto>();
//		
//		List<DrillDown> drillDownList=drillDownRepository.findAll();
//		
//		if(drillDownList != null && !drillDownList.isEmpty() && drillDownList.size() > 0){
//			
//			   for(DrillDown drillDownEntity : drillDownList){
//			
//				   drillDownDTOList.add(new DrillDownDto(drillDownEntity));
//			
//			}
//		}
//		
//		return drillDownDTOList;	
//	}
	
	@Override
	public Page<DrillDownDto> findPaginated(final int page, final int size){
		 Page<DrillDownDto> entities = drillDownRepository.findAll(PageRequest.of(page, size)).map(DrillDownDto::new);
		// ZeroTransactionKiosksDto zeroTransactionKiosksDto= new ZeroTransactionKiosksDto();
//		 for(ZeroTransactionKiosksDto dto:entities.getContent()){
//			 
//			 String kioskId=dto.getKisokId();
//			 
//			 String kioskBranchMaster= kioskMasterRepo.findKioskByKioskId_circle(kioskId);
//			ticketCentorDto.setServeriry(kioskBranchMaster);
//			System.out.println(dto.getKisokId());
//			 
//			 
//		 }
		 
		 
		 
		 return entities;

}
	
	@Override
	public Page<DrillDown> findPaginatedByTxnDate(final int page,final int size, String fromDate, String toDate, String circleName, String networkName, String moduleName, String regionName){
		
		List<DrillDown> list= nearByEntities(fromDate,toDate,circleName,networkName,moduleName,regionName);
				
        Page<DrillDown> pageDto = new PageImpl<DrillDown>(list, PageRequest.of(page, size),list.size());
		 
        System.out.println("entities======pageDto========Size()::::"+pageDto.getContent());
		 
		 return pageDto;
	}
	

    private List<DrillDown> nearByEntities(String fromDate,String toDate, String circleName, String networkName, String moduleName, String regionName) {
        StoredProcedureQuery nearByEntities= em.createNamedStoredProcedureQuery("SP_DRILL_DOWN");
        nearByEntities.setParameter("fromdate", fromDate);
        nearByEntities.setParameter("todate", toDate);
        nearByEntities.setParameter("circleName", circleName);
        nearByEntities.setParameter("networkName", networkName);
        nearByEntities.setParameter("moduleName", moduleName);
        nearByEntities.setParameter("regionName", regionName);
 //       nearByEntities.setParameter("moduleName", moduleName);
 //       nearByEntities.setParameter("todate", toDate);
        System.out.println("nearByEntities======"+nearByEntities);
        return nearByEntities.getResultList();
    }
    
		 
	
	@Override
	public Page<DrillDownDto> findPaginatedByCircle(final int page, final int size, String circleName){
		 Page<DrillDownDto> entities = drillDownRepository.findAllByCircle(PageRequest.of(page, size), circleName).map(DrillDownDto::new);
		// ZeroTransactionKiosksDto zeroTransactionKiosksDto= new ZeroTransactionKiosksDto();
//		 for(ZeroTransactionKiosksDto dto:entities.getContent()){
//			 
//			 String kioskId=dto.getKisokId();
//			 
//			 String kioskBranchMaster= kioskMasterRepo.findKioskByKioskId_circle(kioskId);
//			ticketCentorDto.setServeriry(kioskBranchMaster);
//			System.out.println(dto.getKisokId());
//			 
//			 
//		 }
		 
		 
		 
		 return entities;

    }
	
	
	@Override
	public Page<DrillDownDto> findPaginatedByNetwork(final int page, final int size, String networkName){
		 Page<DrillDownDto> entities = drillDownRepository.findAllByNetwork(PageRequest.of(page, size), networkName).map(DrillDownDto::new);
		// ZeroTransactionKiosksDto zeroTransactionKiosksDto= new ZeroTransactionKiosksDto();
//		 for(ZeroTransactionKiosksDto dto:entities.getContent()){
//			 
//			 String kioskId=dto.getKisokId();
//			 
//			 String kioskBranchMaster= kioskMasterRepo.findKioskByKioskId_circle(kioskId);
//			ticketCentorDto.setServeriry(kioskBranchMaster);
//			System.out.println(dto.getKisokId());
//			 
//			 
//		 }
		 
		 
		 
		 return entities;

    }
	
	@Override
	public Page<DrillDownDto> findPaginatedByModule(final int page, final int size, String moduleName){
		 Page<DrillDownDto> entities = drillDownRepository.findAllByModule(PageRequest.of(page, size), moduleName).map(DrillDownDto::new);
		// ZeroTransactionKiosksDto zeroTransactionKiosksDto= new ZeroTransactionKiosksDto();
//		 for(ZeroTransactionKiosksDto dto:entities.getContent()){
//			 
//			 String kioskId=dto.getKisokId();
//			 
//			 String kioskBranchMaster= kioskMasterRepo.findKioskByKioskId_circle(kioskId);
//			ticketCentorDto.setServeriry(kioskBranchMaster);
//			System.out.println(dto.getKisokId());
//			 
//			 
//		 }
		 
		 
		 
		 return entities;

    }
	
	
	@Override
	public Page<DrillDownDto> findPaginatedByRegion(final int page, final int size, String regionName){
		 Page<DrillDownDto> entities = drillDownRepository.findAllByRegion(PageRequest.of(page, size), regionName).map(DrillDownDto::new);
		// ZeroTransactionKiosksDto zeroTransactionKiosksDto= new ZeroTransactionKiosksDto();
//		 for(ZeroTransactionKiosksDto dto:entities.getContent()){
//			 
//			 String kioskId=dto.getKisokId();
//			 
//			 String kioskBranchMaster= kioskMasterRepo.findKioskByKioskId_circle(kioskId);
//			ticketCentorDto.setServeriry(kioskBranchMaster);
//			System.out.println(dto.getKisokId());
//			 
//			 
//		 }
		 
		 
		 
		 return entities;

    }

	@Override
	public Page<ZeroTransactionKiosks> findPaginatedByDate(int page, int size, String fromDate, String toDate) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
