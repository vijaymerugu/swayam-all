package sbi.kiosk.swayam.transactiondashboard.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import sbi.kiosk.swayam.common.entity.RealTimeTransaction;
import sbi.kiosk.swayam.common.entity.SwayamTransactionReport;
import sbi.kiosk.swayam.transactiondashboard.repository.RealTimeTxnRepositoryPaging;

@Service("realTimeTransactionServiceImpl")
public class RealTimeTransactionServiceImpl implements RealTimeTransactionService{
	@Autowired
	RealTimeTxnRepositoryPaging realTimeTxnRepositoryPaging;
	
	@PersistenceContext
    private EntityManager em;

	/*@Override
	public Page<RealTimeTransaction> findPaginated(int page, int size) {
		System.out.println("page=="+page);
		 Page<RealTimeTransaction>  entities = realTimeTxnRepositoryPaging.findAll(PageRequest.of(page, size));
			//.map(VmMigrationSummary::new);
		  System.out.println("entities==33322="+entities.getContent());
	    return entities;
	}*/

	/*@Override
	public Page<RealTimeTransaction> findPaginatedFromToDate(int page, int size, String fromDate, String toDate) {

		System.out.println("findPaginatedFromToDate Start----from---"+fromDate+"---to----"+toDate);
		 Page<RealTimeTransaction>  entities =	realTimeTxnRepositoryPaging.findByCreatedDate(PageRequest.of(page, size),fromDate,toDate);
		 System.out.println("entities==date wise==="+entities.getContent());
		return entities;
	}*/
	
	
	@Override
	public Page<RealTimeTransaction> findPaginated(int page, int size, String fromdate) {

		System.out.println("findPaginatedFromToDate Start----from---"+fromdate);
		// Page<RealTimeTransaction>  entities =	realTimeTxnRepositoryPaging.findByFromDate(PageRequest.of(page, size),fromdate);
		// System.out.println("entities==date wise==="+entities.getContent());
		 List<RealTimeTransaction> list=new ArrayList<RealTimeTransaction>(); 
		 
		
		 
		
		 if(fromdate!=null && !fromdate.isEmpty() && fromdate.equalsIgnoreCase("yesterday")){
			 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			 Date curDate=new Date();
			 curDate.setTime(curDate.getTime()-24*60*60*1000); 
			 String date=sdf.format(curDate);
			 
			 System.out.println("date===========::"+date);
		 
			StoredProcedureQuery nearByEntities= em.createNamedStoredProcedureQuery("sp_realtime1");
	        nearByEntities.setParameter("fromdate", date);
	       // nearByEntities.setParameter("todate", "20-05-12");
	        System.out.println("nearByEntities===IF===111=="+nearByEntities.getFirstResult());
	        list=nearByEntities.getResultList();
				
			    System.out.println("entities=Size()::::"+list.toString());
				System.out.println("entities=Size()::::"+list);
				for(RealTimeTransaction swayamTxn:list){
					System.out.println("swayamTxn=11="+swayamTxn.getCrclName());
					System.out.println("swayamTxn=22="+swayamTxn.getFromDate());
					System.out.println("swayamTxn=33="+swayamTxn.getBranchName());
				}
				
		 }else if(fromdate!=null && !fromdate.isEmpty() && fromdate.equalsIgnoreCase("today") && !fromdate.equalsIgnoreCase("undefined")){
			 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			 Date curDate=new Date();
			 String todayDate=sdf.format(curDate);
			 
			 System.out.println("Else If===todayDate===========::"+todayDate);
			 
			 StoredProcedureQuery nearByEntities= em.createNamedStoredProcedureQuery("sp_realtime1");
		        nearByEntities.setParameter("fromdate", todayDate);
		       // nearByEntities.setParameter("todate", "20-05-12");
		        System.out.println("nearByEntities====Esle=="+nearByEntities);
		        list=nearByEntities.getResultList();
					
				    System.out.println("entities=Size()::::"+list.toString());
			}else{
				 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				 Date curDate=new Date();
				 String todayDate=sdf.format(curDate);
				//String todayDate=sdf.format(curDate);
				 
				 System.out.println("Else ===todayDate===========::"+todayDate);
				 
				 StoredProcedureQuery nearByEntities= em.createNamedStoredProcedureQuery("sp_realtime1");
			        nearByEntities.setParameter("fromdate", todayDate);
			       // nearByEntities.setParameter("todate", "20-05-12");
			        System.out.println("nearByEntities====Esle=="+nearByEntities);
			        list=nearByEntities.getResultList();
						
					    System.out.println("entities=Size()::::"+list.toString());
				}
		 
					Page<RealTimeTransaction> pageEntity = new PageImpl<RealTimeTransaction>(list, PageRequest.of(page, size),list.size());
							 
		 
		 
		return pageEntity;
	}



	

}
