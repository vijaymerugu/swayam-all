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

import sbi.kiosk.swayam.common.entity.RealTimeTransaction;
import sbi.kiosk.swayam.transactiondashboard.repository.RealTimeTxnRepositoryPaging;

@Service("realTimeTransactionServiceImpl")
public class RealTimeTransactionServiceImpl implements RealTimeTransactionService{
	
	Logger logger = LoggerFactory.getLogger(RealTimeTransactionServiceImpl.class);
	
	@Autowired
	RealTimeTxnRepositoryPaging realTimeTxnRepositoryPaging;
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public Page<RealTimeTransaction> findPaginated(int page, int size, String fromdate) {
      
		logger.info("findPaginatedFromToDate Start----from---"+fromdate);
		// Page<RealTimeTransaction>  entities =	realTimeTxnRepositoryPaging.findByFromDate(PageRequest.of(page, size),fromdate);
		//logger.info("entities==date wise==="+entities.getContent());
		 List<RealTimeTransaction> list=new ArrayList<RealTimeTransaction>(); 
		
		 if(fromdate!=null && !fromdate.isEmpty() && fromdate.equalsIgnoreCase("yesterday")){
			 SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			 Date curDate=new Date();
			 curDate.setTime(curDate.getTime()-24*60*60*1000); 
			 String date=sdf.format(curDate);
			 
			logger.info("date===========::"+date);
		 
			StoredProcedureQuery nearByEntities= em.createNamedStoredProcedureQuery("SP_REAL_TIME_PROC");
	        nearByEntities.setParameter("fromdate_param", date);
	       // nearByEntities.setParameter("todate", "20-05-12");
	       logger.info("nearByEntities===IF===111=="+nearByEntities.getFirstResult());
	        list=nearByEntities.getResultList();
				
			   logger.info("entities=Size()::::"+list.toString());
				System.out.println("entities=Size()::::"+list);
				for(RealTimeTransaction swayamTxn:list){
					System.out.println("swayamTxn=11="+swayamTxn.getCrclName());
					System.out.println("swayamTxn=33="+swayamTxn.getBranchName());
				}
				
		 }else if(fromdate!=null && !fromdate.isEmpty() && fromdate.equalsIgnoreCase("today") && !fromdate.equalsIgnoreCase("undefined")){
			 SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			 Date curDate=new Date();
			 String todayDate=sdf.format(curDate);
			 logger.info("Else If===todayDate===========::"+todayDate);
			 StoredProcedureQuery nearByEntities= em.createNamedStoredProcedureQuery("SP_REAL_TIME_PROC");
		     nearByEntities.setParameter("fromdate_param", todayDate);
		     // nearByEntities.setParameter("todate", "20-05-12");
		     logger.info("nearByEntities====Esle=="+nearByEntities);
		     list=nearByEntities.getResultList();
		     logger.info("entities=Size()::::"+list.toString());
			}else{
				 SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
				 Date curDate=new Date();
				 String todayDate=sdf.format(curDate);
				//String todayDate=sdf.format(curDate);
				logger.info("Else ===todayDate===========::"+todayDate);
				StoredProcedureQuery nearByEntities= em.createNamedStoredProcedureQuery("SP_REAL_TIME_PROC");
			    nearByEntities.setParameter("fromdate_param", todayDate);
			    logger.info("nearByEntities====Esle=="+nearByEntities);
			    list=nearByEntities.getResultList();
			    logger.info("entities=Size()::::"+list.toString());
				}
					Page<RealTimeTransaction> pageEntity = new PageImpl<RealTimeTransaction>(list, PageRequest.of(page, size),list.size());
		return pageEntity;
	}

}
