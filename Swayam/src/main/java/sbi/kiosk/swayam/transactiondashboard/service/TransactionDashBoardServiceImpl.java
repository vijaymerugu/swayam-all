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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.common.entity.RealTimeTransaction;
import sbi.kiosk.swayam.common.entity.SwayamMigrationSummary;
import sbi.kiosk.swayam.common.entity.ZeroTransactionKiosks;
import sbi.kiosk.swayam.transactiondashboard.repository.TransactionDashBoardRepository;
import sbi.kiosk.swayam.transactiondashboard.repository.TransactionDashBoardRepositoryPaging;

@Service
public class TransactionDashBoardServiceImpl implements TransactionDashBoardService{
	private static final String Static = null;
	Logger logger = LoggerFactory.getLogger(TransactionDashBoardServiceImpl.class);
	@Autowired
	TransactionDashBoardRepository transactionDashBoardRepo;
	
	@Autowired
	TransactionDashBoardRepositoryPaging transactionDashBoardRepositoryPaging;
	

	
	@PersistenceContext
    private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<SwayamMigrationSummary> findPaginated(int page, int size,String fromdate,String todate) {
	   // Page<SwayamMigrationSummary>  entities = transactionDashBoardRepositoryPaging.findAll(PageRequest.of(page, size));
			//.map(VmMigrationSummary::new);
		
		 List<SwayamMigrationSummary> list=new ArrayList<SwayamMigrationSummary>(); 
		 
		if((fromdate ==null || fromdate.isEmpty()) && (todate ==null || todate.isEmpty())){
				
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			 Date curDate=new Date();
			 curDate.setTime(curDate.getTime()-48*60*60*1000); 
			 String passedDate=sdf.format(curDate);
		
			fromdate=passedDate;
			todate=passedDate;
		
		//	 Page<SwayamMigrationSummary> pageSummary1=transactionDashBoardRepositoryPaging.findByDate(fromdate, todate, PageRequest.of(page, size));
			try {
				
				 StoredProcedureQuery nearByEntities= em.createNamedStoredProcedureQuery("SP_MIGRATION_SUMMARY");
			        nearByEntities.setParameter("fromdate", fromdate);
			        nearByEntities.setParameter("todate", todate);
			        nearByEntities.setParameter("searchText", "");
			        list=nearByEntities.getResultList();
			       
			
				} catch (Exception e) {
					logger.error("Exception in SwayamMigrationSummaryDataList ." + e.getMessage());
					
				}
		//	Page<SwayamMigrationSummary> pageSummary1 = new PageImpl<SwayamMigrationSummary>(list,PageRequest.of(page, size), list.size());
			 @SuppressWarnings("deprecation")
				int start =  (int) new PageRequest(page, size).getOffset();
			        @SuppressWarnings("deprecation")
					int end = (start + new PageRequest(page, size).getPageSize()) > list.size() ? list.size() : (start + new PageRequest(page, size).getPageSize());
			        @SuppressWarnings("deprecation")
					Page<SwayamMigrationSummary> pageSummary1 = new PageImpl<SwayamMigrationSummary>(list.subList(start, end), new PageRequest(page, size), list.size());
		
			return pageSummary1;
			 
		}else {
			
		//  Page<SwayamMigrationSummary> pageSummary= transactionDashBoardRepositoryPaging.findByDate(fromdate, todate, PageRequest.of(page, size));			
		
			try {
				
				 StoredProcedureQuery nearByEntities= em.createNamedStoredProcedureQuery("SP_MIGRATION_SUMMARY");
			        nearByEntities.setParameter("fromdate", fromdate);
			        nearByEntities.setParameter("todate", todate);
			        nearByEntities.setParameter("searchText", "");
			        list=nearByEntities.getResultList();
			       
			
				} catch (Exception e) {
					logger.error("Exception in SwayamMigrationSummaryDataList ." + e.getMessage());
					
				}
		//	Page<SwayamMigrationSummary> pageSummary = new PageImpl<SwayamMigrationSummary>(list,PageRequest.of(page, size), list.size());
			 @SuppressWarnings("deprecation")
				int start =  (int) new PageRequest(page, size).getOffset();
			        @SuppressWarnings("deprecation")
					int end = (start + new PageRequest(page, size).getPageSize()) > list.size() ? list.size() : (start + new PageRequest(page, size).getPageSize());
			        @SuppressWarnings("deprecation")
					Page<SwayamMigrationSummary> pageSummary = new PageImpl<SwayamMigrationSummary>(list.subList(start, end), new PageRequest(page, size), list.size());
			
		  return pageSummary;
		}
		
      
		
		
	  }
	@SuppressWarnings("unchecked")
	public Page<SwayamMigrationSummary> findPaginatedSearchNext(int page, int size,String fromdate,String todate,String searchText) {
	   // Page<SwayamMigrationSummary>  entities = transactionDashBoardRepositoryPaging.findAll(PageRequest.of(page, size));
			//.map(VmMigrationSummary::new);
		
		
		 List<SwayamMigrationSummary> list=new ArrayList<SwayamMigrationSummary>(); 
		if((fromdate ==null || fromdate.isEmpty()) && (todate ==null || todate.isEmpty())){
				
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			 Date curDate=new Date();
			 curDate.setTime(curDate.getTime()-48*60*60*1000); 
			 String passedDate=sdf.format(curDate);
		
			fromdate=passedDate;
			todate=passedDate;
		
		//	 Page<SwayamMigrationSummary> pageSummary1=transactionDashBoardRepositoryPaging.findByFromDateSearchText(fromdate, todate,searchText, PageRequest.of(page, size));
			try {
				
				 StoredProcedureQuery nearByEntities= em.createNamedStoredProcedureQuery("SP_MIGRATION_SUMMARY");
			        nearByEntities.setParameter("fromdate", fromdate);
			        nearByEntities.setParameter("todate", todate);
			        nearByEntities.setParameter("searchText", searchText);
			        list=nearByEntities.getResultList();
			        
			
				} catch (Exception e) {
					logger.error("Exception in SwayamMigrationSummaryDataList ." + e.getMessage());
					
				}
			//Page<SwayamMigrationSummary> pageSummary1 = new PageImpl<SwayamMigrationSummary>(list,PageRequest.of(page, size), list.size());
			 @SuppressWarnings("deprecation")
			
				 int start =  (int) new PageRequest(page, size).getOffset();
			 
			        @SuppressWarnings("deprecation")
					int end = (start + new PageRequest(page, size).getPageSize()) > list.size() ? list.size() : (start + new PageRequest(page, size).getPageSize());
				//	int x = Math.round(list.size() / end);
					@SuppressWarnings("deprecation")
					Page<SwayamMigrationSummary> pageSummary1 = new PageImpl<SwayamMigrationSummary>(list.subList(start, end), new PageRequest(page, size), list.size());
				

			 return pageSummary1;
		}else {
			
	//	  Page<SwayamMigrationSummary> pageSummary= transactionDashBoardRepositoryPaging.findByFromDateSearchText(fromdate, todate,searchText, PageRequest.of(page, size));			
			try {
				
				 StoredProcedureQuery nearByEntities= em.createNamedStoredProcedureQuery("SP_MIGRATION_SUMMARY");
			        nearByEntities.setParameter("fromdate", fromdate);
			        nearByEntities.setParameter("todate", todate);
			        nearByEntities.setParameter("searchText", searchText);
			        list=nearByEntities.getResultList();
			       
			
				} catch (Exception e) {
					logger.error("Exception in SwayamMigrationSummaryDataList ." + e.getMessage());
					
				}
		//	Page<SwayamMigrationSummary> pageSummary = new PageImpl<SwayamMigrationSummary>(list,PageRequest.of(page, size), list.size());
			 @SuppressWarnings("deprecation")
				int start =  (int) new PageRequest(page, size).getOffset();
			        @SuppressWarnings("deprecation")
					int end = (start + new PageRequest(page, size).getPageSize()) > list.size() ? list.size() : (start + new PageRequest(page, size).getPageSize());
			        @SuppressWarnings("deprecation")
					Page<SwayamMigrationSummary> pageSummary = new PageImpl<SwayamMigrationSummary>(list.subList(start, end), new PageRequest(page, size), list.size());
		
		  return pageSummary;
		}
		
      
		
		
	  }
	@Override
	public String findSwayamTxnLastUpdatedJob() {
		
		String swayamMigrationTxnDate=null;
		
		try {
			swayamMigrationTxnDate=transactionDashBoardRepositoryPaging.findCurrentDateAuditJob();
			
		} catch (Exception e) {
		//	e.printStackTrace();
		}
		return swayamMigrationTxnDate;
		
		
		
	}

	
	








	


	


	
}
