package sbi.kiosk.swayam.transactiondashboard.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sbi.kiosk.swayam.common.dto.ZeroTransactionKiosksDto;
import sbi.kiosk.swayam.common.entity.DrillDown;
import sbi.kiosk.swayam.common.entity.ZeroTransactionKiosks;
import sbi.kiosk.swayam.transactiondashboard.repository.ZeroTransactionKiosksRepository;

@Service
public class ZeroTransactionKiosksServiceImpl implements ZeroTransactionKiosksService {
	
	@Autowired
	ZeroTransactionKiosksRepository zeroTransactionKiosksRepository;
	
	@PersistenceContext
    private EntityManager em;
	
//public List<ZeroTransactionKiosksDto> list(){
//		
//		List<ZeroTransactionKiosksDto> zeroTransactionKiosksDTOList=new ArrayList<ZeroTransactionKiosksDto>();
//		
//		List<ZeroTransactionKiosks> zeroTransactionKiosksList=zeroTransactionKiosksRepository.findAll();
//		
//		if(zeroTransactionKiosksList != null && !zeroTransactionKiosksList.isEmpty() && zeroTransactionKiosksList.size() > 0){
//			
//			   for(ZeroTransactionKiosks zeroTransactionKiosksEntity : zeroTransactionKiosksList){
//			
//				   zeroTransactionKiosksDTOList.add(new ZeroTransactionKiosksDto(zeroTransactionKiosksEntity));
//			
//			   }
//		}
//		
//		return zeroTransactionKiosksDTOList;	
//	}

@Override
public Page<ZeroTransactionKiosksDto> findPaginated(final int page, final int size){
	 Page<ZeroTransactionKiosksDto> entities = zeroTransactionKiosksRepository.findAll(PageRequest.of(page, size)).map(ZeroTransactionKiosksDto::new);
	// ZeroTransactionKiosksDto zeroTransactionKiosksDto= new ZeroTransactionKiosksDto();
//	 for(ZeroTransactionKiosksDto dto:entities.getContent()){
//		 
//		 String kioskId=dto.getKisokId();
//		 
//		 String kioskBranchMaster= kioskMasterRepo.findKioskByKioskId_circle(kioskId);
//		ticketCentorDto.setServeriry(kioskBranchMaster);
//		System.out.println(dto.getKisokId());
//		 
//		 
//	 }
	 
	 return entities;
	 
}
	
	@Override
	public Page<ZeroTransactionKiosks> findPaginatedByDate(final int page, final int size, String fromDate, String toDate){
		// Page<ZeroTransactionKiosksDto> entities = zeroTransactionKiosksRepository.findAll(PageRequest.of(page, size)).map(ZeroTransactionKiosksDto::new);
		 
//		String fromdate="";
//		String todate="";
		List<ZeroTransactionKiosks> list= nearByEntities(fromDate,toDate);
				
        Page<ZeroTransactionKiosks> pageDto = new PageImpl<ZeroTransactionKiosks>(list, PageRequest.of(page, size),list.size());
		 
        System.out.println("entities======pageDto========Size()::::"+pageDto.getContent());
		 
		 return pageDto;
	}
	

    private List<ZeroTransactionKiosks> nearByEntities(String fromDate,String toDate) {
        StoredProcedureQuery nearByEntities= em.createNamedStoredProcedureQuery("SP_ZERO_TRANSACTION_KIOSKS");
        nearByEntities.setParameter("fromdate", fromDate);
        nearByEntities.setParameter("todate", toDate);
        System.out.println("nearByEntities======"+nearByEntities);
        return nearByEntities.getResultList();
    }

	
	
	
	

@Override
public Page<ZeroTransactionKiosksDto> findPaginatedByCircle(int page, int size, String circleName) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Page<ZeroTransactionKiosksDto> findPaginatedByNetwork(int page, int size, String networkName) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Page<ZeroTransactionKiosksDto> findPaginatedByModule(int page, int size, String moduleName) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Page<ZeroTransactionKiosksDto> findPaginatedByRegion(int page, int size, String regionName) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Page<DrillDown> findPaginatedByTxnDate(int page, int size, String fromDate, String toDate, String circleName, String networkName, String moduleName, String regionName) {
	// TODO Auto-generated method stub
	return null;
}

}
