package sbi.kiosk.swayam.transactiondashboard.service;

import java.util.ArrayList;
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

import sbi.kiosk.swayam.common.entity.SwayamMigrationSummary;
import sbi.kiosk.swayam.transactiondashboard.repository.TransactionDashBoardRepository;
import sbi.kiosk.swayam.transactiondashboard.repository.TransactionDashBoardRepositoryPaging;

@Service
public class TransactionDashBoardServiceImpl implements TransactionDashBoardService{
	Logger logger = LoggerFactory.getLogger(TransactionDashBoardServiceImpl.class);
	@Autowired
	TransactionDashBoardRepository transactionDashBoardRepo;
	
	@Autowired
	TransactionDashBoardRepositoryPaging transactionDashBoardRepositoryPaging;
	
	
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public Page<SwayamMigrationSummary> findPaginated(int page, int size,String fromdate,String todate) {
	   // Page<SwayamMigrationSummary>  entities = transactionDashBoardRepositoryPaging.findAll(PageRequest.of(page, size));
			//.map(VmMigrationSummary::new);
		logger.info("entities=Swayam Migrartion 555555555555=fromdate::="+fromdate);
		logger.info("entities=Swayam Migrartion 2222222=todate::="+todate);
		List<SwayamMigrationSummary> summaryList=new ArrayList<SwayamMigrationSummary>();
		
		if(fromdate!=null && !fromdate.isEmpty() && !fromdate.equals("undefined")){
		StoredProcedureQuery nearByEntities= em.createNamedStoredProcedureQuery("SP_MIGRATION_SUMMARY");
       // nearByEntities.setParameter("fromdate", "2020-05-10");
       // nearByEntities.setParameter("todate", "2020-05-10");
		nearByEntities.setParameter("fromdate", todate);
	   nearByEntities.setParameter("todate", fromdate);
        logger.info("nearByEntities======"+nearByEntities);
        summaryList=nearByEntities.getResultList();
			
	    logger.info("entities=Size()::::"+summaryList.size());
		logger.info("entities=Size()::::"+summaryList);
		for(SwayamMigrationSummary swayamTxn:summaryList){
			logger.info("swayamTxn=1="+swayamTxn.getCrclName());
			logger.info("swayamTxn=2="+swayamTxn.getMigrationPerc());
			logger.info("swayamTxn=3="+swayamTxn.getBranchName());
		}
		
		}else{
			
			
			StoredProcedureQuery nearByEntities= em.createNamedStoredProcedureQuery("SP_MIGRATION_SUMMARY");
		       // nearByEntities.setParameter("fromdate", "2020-05-10");
		       // nearByEntities.setParameter("todate", "2020-05-10");
				nearByEntities.setParameter("fromdate", "");
			   nearByEntities.setParameter("todate", "");
		        logger.info("nearByEntities======"+nearByEntities);
		        summaryList=nearByEntities.getResultList();
					
			    logger.info("entities=Size()::::"+summaryList.size());
				logger.info("entities=Size()::::"+summaryList);
				for(SwayamMigrationSummary swayamTxn:summaryList){
					logger.info("swayamTxn=1="+swayamTxn.getCrclName());
					logger.info("swayamTxn=2="+swayamTxn.getMigrationPerc());
					logger.info("swayamTxn=3="+swayamTxn.getBranchName());
				}
		}
			
Page<SwayamMigrationSummary> pageSummary = new PageImpl<SwayamMigrationSummary>(summaryList, PageRequest.of(page, size),summaryList.size());
logger.info("entities======pageSummary========Size()::::"+pageSummary.getContent());
		
		return pageSummary;
	  }

	
	








	


	


	
}
