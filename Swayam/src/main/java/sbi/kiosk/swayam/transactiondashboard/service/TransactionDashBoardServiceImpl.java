package sbi.kiosk.swayam.transactiondashboard.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

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
		System.out.println("entities=Swayam Migrartion 555555555555=fromdate::="+fromdate);
		System.out.println("entities=Swayam Migrartion 2222222=todate::="+todate);
		List<SwayamMigrationSummary> summaryList=new ArrayList<SwayamMigrationSummary>();
		
		if(fromdate!=null && !fromdate.isEmpty() && !fromdate.equals("undefined")){
		StoredProcedureQuery nearByEntities= em.createNamedStoredProcedureQuery("SP_MIGRATION_SUMMARY");
       // nearByEntities.setParameter("fromdate", "2020-05-10");
       // nearByEntities.setParameter("todate", "2020-05-10");
		nearByEntities.setParameter("fromdate", todate);
	   nearByEntities.setParameter("todate", fromdate);
        System.out.println("nearByEntities======"+nearByEntities);
        summaryList=nearByEntities.getResultList();
			
	    System.out.println("entities=Size()::::"+summaryList.size());
		System.out.println("entities=Size()::::"+summaryList);
		for(SwayamMigrationSummary swayamTxn:summaryList){
			System.out.println("swayamTxn=1="+swayamTxn.getCrclName());
			System.out.println("swayamTxn=2="+swayamTxn.getMigrationPerc());
			System.out.println("swayamTxn=3="+swayamTxn.getBranchName());
		}
		
		}else{
			
			
			StoredProcedureQuery nearByEntities= em.createNamedStoredProcedureQuery("SP_MIGRATION_SUMMARY");
		       // nearByEntities.setParameter("fromdate", "2020-05-10");
		       // nearByEntities.setParameter("todate", "2020-05-10");
				nearByEntities.setParameter("fromdate", "");
			   nearByEntities.setParameter("todate", "");
		        System.out.println("nearByEntities======"+nearByEntities);
		        summaryList=nearByEntities.getResultList();
					
			    System.out.println("entities=Size()::::"+summaryList.size());
				System.out.println("entities=Size()::::"+summaryList);
				for(SwayamMigrationSummary swayamTxn:summaryList){
					System.out.println("swayamTxn=1="+swayamTxn.getCrclName());
					System.out.println("swayamTxn=2="+swayamTxn.getMigrationPerc());
					System.out.println("swayamTxn=3="+swayamTxn.getBranchName());
				}
		}
			
Page<SwayamMigrationSummary> pageSummary = new PageImpl<SwayamMigrationSummary>(summaryList, PageRequest.of(page, size),summaryList.size());
System.out.println("entities======pageSummary========Size()::::"+pageSummary.getContent());
		
		return pageSummary;
	  }

	








	


	


	
}
