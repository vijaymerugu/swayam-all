package sbi.kiosk.swayam.transactiondashboard.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.common.dto.ZeroTransactionKiosksDto;
import sbi.kiosk.swayam.common.entity.DrillDown;
import sbi.kiosk.swayam.common.entity.RealTimeTransaction;
import sbi.kiosk.swayam.common.entity.SwayamMigrationSummary;
import sbi.kiosk.swayam.common.entity.ZeroTransactionKiosks;
import sbi.kiosk.swayam.common.service.JasperServiceImpl;
import sbi.kiosk.swayam.transactiondashboard.repository.ZeroTransactionKiosksRepository;

@Service
public class ZeroTransactionKiosksServiceImpl implements ZeroTransactionKiosksService {
	
	@Autowired
	ZeroTransactionKiosksRepository zeroTransactionKiosksRepository;
	
	@PersistenceContext
    private EntityManager em;
	
	Logger logger = LoggerFactory.getLogger(ZeroTransactionKiosksServiceImpl.class);

@Override
public Page<ZeroTransactionKiosksDto> findPaginated(final int page, final int size){
	 Page<ZeroTransactionKiosksDto> entities = zeroTransactionKiosksRepository.findAll(PageRequest.of(page, size)).map(ZeroTransactionKiosksDto::new);
	 
	 return entities;
	 
}
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<ZeroTransactionKiosks> findPaginatedByDate(final int page, final int size, String fromdate, String todate){
	
		//List<ZeroTransactionKiosks> list= nearByEntities(fromDate,toDate);
		
		 List<ZeroTransactionKiosks> list=new ArrayList<ZeroTransactionKiosks>(); 
		if((fromdate ==null || fromdate.isEmpty()) && (todate ==null || todate.isEmpty())){
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			Date curDate=new Date();
			fromdate=sdf.format(curDate);
			todate=sdf.format(curDate);
		}
		

   //     Page<ZeroTransactionKiosks> pageDto = zeroTransactionKiosksRepository.findByDate(fromdate, todate, PageRequest.of(page, size));
		 
		try {
			
			 StoredProcedureQuery nearByEntities= em.createNamedStoredProcedureQuery("SP_ZERO_TRANSACTION_KIOSKS");
		        nearByEntities.setParameter("fromdate", fromdate);
		        nearByEntities.setParameter("todate", todate);
		        nearByEntities.setParameter("searchText", "");
		        list=nearByEntities.getResultList();
		       
		
			} catch (Exception e) {
				logger.error("Exception in ZeroTransactionKiosksDataList ." + e.getMessage());
				
			}
	//	Page<ZeroTransactionKiosks> pageDto = new PageImpl<ZeroTransactionKiosks>(list,PageRequest.of(page, size), list.size());
		 @SuppressWarnings("deprecation")
			int start =  (int) new PageRequest(page, size).getOffset();
		        @SuppressWarnings("deprecation")
				int end = (start + new PageRequest(page, size).getPageSize()) > list.size() ? list.size() : (start + new PageRequest(page, size).getPageSize());
		        @SuppressWarnings("deprecation")
				Page<ZeroTransactionKiosks> pageDto = new PageImpl<ZeroTransactionKiosks>(list.subList(start, end), new PageRequest(page, size), list.size());
			
		 return pageDto;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<ZeroTransactionKiosks> findPaginatedByDateSearchNext(final int page, final int size, String fromdate, String todate,String searchText){
	
		//List<ZeroTransactionKiosks> list= nearByEntities(fromDate,toDate);
		 List<ZeroTransactionKiosks> list=new ArrayList<ZeroTransactionKiosks>();
		if((fromdate ==null || fromdate.isEmpty()) && (todate ==null || todate.isEmpty())){
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			Date curDate=new Date();
			fromdate=sdf.format(curDate);
			todate=sdf.format(curDate);
		}
				
  //      Page<ZeroTransactionKiosks> pageDto = zeroTransactionKiosksRepository.findByDateSearchNext(fromdate, todate,searchText, PageRequest.of(page, size));
		 
		try {
			
			 StoredProcedureQuery nearByEntities= em.createNamedStoredProcedureQuery("SP_ZERO_TRANSACTION_KIOSKS");
		        nearByEntities.setParameter("fromdate", fromdate);
		        nearByEntities.setParameter("todate", todate);
		        nearByEntities.setParameter("searchText", searchText);
		        list=nearByEntities.getResultList();
		       
		
			} catch (Exception e) {
				logger.error("Exception in ZeroTransactionKiosksDataList ." + e.getMessage());
				
			}
		//Page<ZeroTransactionKiosks> pageDto = new PageImpl<ZeroTransactionKiosks>(list,PageRequest.of(page, size), list.size());
		 @SuppressWarnings("deprecation")
			int start =  (int) new PageRequest(page, size).getOffset();
		        @SuppressWarnings("deprecation")
				int end = (start + new PageRequest(page, size).getPageSize()) > list.size() ? list.size() : (start + new PageRequest(page, size).getPageSize());
		        @SuppressWarnings("deprecation")
				Page<ZeroTransactionKiosks> pageDto = new PageImpl<ZeroTransactionKiosks>(list.subList(start, end), new PageRequest(page, size), list.size());
			
		 return pageDto;
	}
	

    private List<ZeroTransactionKiosks> nearByEntities(String fromDate,String toDate) {
        StoredProcedureQuery nearByEntities= em.createNamedStoredProcedureQuery("SP_ZERO_TRANSACTION_KIOSKS");
        nearByEntities.setParameter("fromdate", fromDate);
        nearByEntities.setParameter("todate", toDate);
        return nearByEntities.getResultList();
    }

@Override
public Page<ZeroTransactionKiosksDto> findPaginatedByCircle(int page, int size, String circleName) {
	
	return null;
}

@Override
public Page<ZeroTransactionKiosksDto> findPaginatedByNetwork(int page, int size, String networkName) {
	
	return null;
}

@Override
public Page<ZeroTransactionKiosksDto> findPaginatedByModule(int page, int size, String moduleName) {
	
	return null;
}

@Override
public Page<ZeroTransactionKiosksDto> findPaginatedByRegion(int page, int size, String regionName) {

	return null;
}

@Override
public Page<DrillDown> findPaginatedByTxnDate(int page, int size, String fromDate, String toDate, String circleName, String networkName, String moduleName, String regionName) {
	
	return null;
}

@Override
public Page<DrillDown> findPaginatedByTxnDate(int page, int size, String type, String fromdate, String todate,
		String in_circle_code, String in_network_code, String in_module_code, String in_region_code) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public String findZeroTxnLastUpdatedJob() {
	// TODO Auto-	public String findCarrentRealTimeJob() {
	String zeroTxnDate=null;
	
	try {
		zeroTxnDate=zeroTransactionKiosksRepository.findCurrentDateAuditJob();
		
	} catch (Exception e) {
	//	e.printStackTrace();
	}
	return zeroTxnDate;

}

@Override
public Page<DrillDown> findPaginatedByTxnDateSearchNext(int i, int size, String type, String fromdate, String todate,
		String circleName, String networkName, String moduleName, String regionName, String searchText) {
	// TODO Auto-generated method stub
	return null;
}

}
