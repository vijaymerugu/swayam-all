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
import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import sbi.kiosk.swayam.common.entity.RealTimeTransaction;
import sbi.kiosk.swayam.transactiondashboard.repository.RealTimeTxnRepositoryPaging;

@Service("realTimeTransactionServiceImpl")
public class RealTimeTransactionServiceImpl implements RealTimeTransactionService{
	
	Logger logger = LoggerFactory.getLogger(RealTimeTransactionServiceImpl.class);
	
	@Autowired
	RealTimeTxnRepositoryPaging realTimeTxnRepositoryPaging;
	@PersistenceContext
    private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<RealTimeTransaction> findPaginated(int page, int size, String fromdate) {
      
		logger.info("findPaginatedFromToDate Start----from---");
		// Page<RealTimeTransaction>  entities =	realTimeTxnRepositoryPaging.findByFromDate(PageRequest.of(page, size),fromdate);
		//logger.info("entities==date wise==="+entities.getContent());
		 List<RealTimeTransaction> list=new ArrayList<RealTimeTransaction>(); 
		
		 String passedDate = null;
		
		 if(fromdate!=null && !fromdate.isEmpty() && fromdate.equalsIgnoreCase("yesterday")){
			 SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			 Date curDate=new Date();
			 curDate.setTime(curDate.getTime()-24*60*60*1000); 
			 passedDate=sdf.format(curDate);
			 
		//	logger.info("date===========::");		 
			/*StoredProcedureQuery nearByEntities= em.createNamedStoredProcedureQuery("SP_REAL_TIME_PROC");
	        nearByEntities.setParameter("fromdate_param", date);
	       // nearByEntities.setParameter("todate", "20-05-12");
	       logger.info("nearByEntities===IF===111=="+nearByEntities.getFirstResult());
	        list=nearByEntities.getResultList();*/				
		 }else if(fromdate!=null && !fromdate.isEmpty() && fromdate.equalsIgnoreCase("today") && !fromdate.equalsIgnoreCase("undefined")){
			 SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			 Date curDate=new Date();
			 passedDate=sdf.format(curDate);
		//	 logger.info("Else If===todayDate===========::");			 
		}else{
				 SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
				 Date curDate=new Date();
				 passedDate=sdf.format(curDate);
				//String todayDate=sdf.format(curDate);
			//	logger.info("Else ===todayDate===========::"+passedDate);
		}
		 
		 try {
			
			 StoredProcedureQuery nearByEntities= em.createNamedStoredProcedureQuery("SP_REAL_TIME_PROC");
		        nearByEntities.setParameter("fromdate_param", passedDate);
		        nearByEntities.setParameter("searchText", "");
		        list=nearByEntities.getResultList();
		       
				
		        
			} catch (Exception e) {
				logger.error("Exception in realTimeTransactionDataList ." + e.getMessage());
				
			}
	//	 Page<RealTimeTransaction> pageEntity = realTimeTxnRepositoryPaging.findByFromDate(passedDate, PageRequest.of(page, size));					
	
		 @SuppressWarnings("deprecation")
		int start =  (int) new PageRequest(page, size).getOffset();
	        @SuppressWarnings("deprecation")
			int end = (start + new PageRequest(page, size).getPageSize()) > list.size() ? list.size() : (start + new PageRequest(page, size).getPageSize());
	        @SuppressWarnings("deprecation")
			Page<RealTimeTransaction> pageEntity = new PageImpl<RealTimeTransaction>(list.subList(start, end), new PageRequest(page, size), list.size());
			
	    
		//	Page<RealTimeTransaction> pageEntity = new PageImpl<RealTimeTransaction>(list,PageRequest.of(page, size), list.size());
		 
		//	System.out.println("pageEntity: "+pageEntity);
		 return pageEntity;
		
	}
	


	@SuppressWarnings("unchecked")
	@Override
	public Page<RealTimeTransaction> findPaginatedSearchNext(int page, int size, String fromdate,String searchText) {
      
		logger.info("findPaginatedFromToDate Start----from---");
	
		 List<RealTimeTransaction> list=new ArrayList<RealTimeTransaction>(); 
		 String passedDate = null;
		
		 if(fromdate!=null && !fromdate.isEmpty() && fromdate.equalsIgnoreCase("yesterday")){
			 SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			 Date curDate=new Date();
			 curDate.setTime(curDate.getTime()-24*60*60*1000); 
			 passedDate=sdf.format(curDate);
			 
				
		 }else if(fromdate!=null && !fromdate.isEmpty() && fromdate.equalsIgnoreCase("today") && !fromdate.equalsIgnoreCase("undefined")){
			 SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			 Date curDate=new Date();
			 passedDate=sdf.format(curDate);
				 
		}else{
				 SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
				 Date curDate=new Date();
				 passedDate=sdf.format(curDate);
				
		}

	//	 Page<RealTimeTransaction> pageEntity = realTimeTxnRepositoryPaging.findByFromDateSearchText(passedDate,searchText, PageRequest.of(page, size));					
	
		 try {
			 			
			 StoredProcedureQuery nearByEntities= em.createNamedStoredProcedureQuery("SP_REAL_TIME_PROC");
		        nearByEntities.setParameter("fromdate_param", passedDate);
		        nearByEntities.setParameter("searchText", searchText);
		        list=nearByEntities.getResultList();
		       
		
			} catch (Exception e) {
				logger.error("Exception in realTimeTransactionDataList ." + e.getMessage());
				
			}
	    
		//	Page<RealTimeTransaction> pageEntity = new PageImpl<RealTimeTransaction>(list,PageRequest.of(page, size), list.size());
		 
		 @SuppressWarnings("deprecation")
			int start =  (int) new PageRequest(page, size).getOffset();
		        @SuppressWarnings("deprecation")
				int end = (start + new PageRequest(page, size).getPageSize()) > list.size() ? list.size() : (start + new PageRequest(page, size).getPageSize());
		        @SuppressWarnings("deprecation")
				Page<RealTimeTransaction> pageEntity = new PageImpl<RealTimeTransaction>(list.subList(start, end), new PageRequest(page, size), list.size());
			 
		 
		 return pageEntity;
	}
	
	public String findLastUpdatedRealTimeJob() {
		String realTime=null;
		
		try {
		      realTime=realTimeTxnRepositoryPaging.findCurrentDateAuditJob();
			
		} catch (Exception e) {
		//	e.printStackTrace();
		}
		return realTime;
	}
	
	

}
