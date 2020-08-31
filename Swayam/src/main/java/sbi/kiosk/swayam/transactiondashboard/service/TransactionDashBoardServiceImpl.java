package sbi.kiosk.swayam.transactiondashboard.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
		
		if((fromdate ==null || fromdate.isEmpty()) && (todate ==null || todate.isEmpty())){
				
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			 Date curDate=new Date();
			 curDate.setTime(curDate.getTime()-48*60*60*1000); 
			 String passedDate=sdf.format(curDate);
		
			fromdate=passedDate;
			todate=passedDate;
			 Page<SwayamMigrationSummary> pageSummary1=transactionDashBoardRepositoryPaging.findByDate(fromdate, todate, PageRequest.of(page, size));
			 return pageSummary1;
		}else {
		
		  Page<SwayamMigrationSummary> pageSummary= transactionDashBoardRepositoryPaging.findByDate(fromdate, todate, PageRequest.of(page, size));			
		  return pageSummary;
		}
		
      
		
		
	  }

	
	








	


	


	
}
