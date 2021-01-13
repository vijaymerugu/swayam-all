package sbi.kiosk.swayam.transactiondashboard.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.apache.commons.lang.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.common.dto.DrillDownDto;
import sbi.kiosk.swayam.common.entity.DrillDown;
import sbi.kiosk.swayam.common.entity.ZeroTransactionKiosks;
import sbi.kiosk.swayam.transactiondashboard.repository.DrillDownRepository;

@Service
public class DrillDownServiceImpl implements DrillDownService{
	Logger logger = LoggerFactory.getLogger(DrillDownServiceImpl.class);
	
	@Autowired
	DrillDownRepository drillDownRepository;
	
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public Page<DrillDownDto> findPaginated(final int page, final int size){
		 Page<DrillDownDto> entities = drillDownRepository.findAll(PageRequest.of(page, size)).map(DrillDownDto::new);
		 
		 return entities;

}
	
	@Override
	public Page<DrillDown> findPaginatedByTxnDate(final int page,final int size,String type, String fromdate, String todate, String in_circle_code, String in_network_code, String in_module_code, String in_region_code){
		
		//List<DrillDown> list= nearByEntities(fromDate,toDate,circleName,networkName,moduleName,regionName);
				
        //Page<DrillDown> pageDto = new PageImpl<DrillDown>(list, PageRequest.of(page, size),list.size());
		 
		Page<DrillDown> pageDrillDown = null;
		if((in_circle_code ==null || in_circle_code.isEmpty())){
			in_circle_code = null;
		}
		if((in_network_code ==null || in_network_code.isEmpty())){
			in_network_code = null;
		}
		if((in_module_code ==null || in_module_code.isEmpty())){
			in_module_code = null;
		}
		if((in_region_code ==null || in_region_code.isEmpty())){
			in_region_code = null;
		}		
	
		if("NW".equals(type)){
			pageDrillDown = drillDownRepository.findByDate(fromdate, todate,in_circle_code, PageRequest.of(page, size));
		}
		else if("MOD".equals(type)){
			pageDrillDown = drillDownRepository.findByDate(fromdate, todate,in_circle_code, in_network_code, PageRequest.of(page, size));
		}
		else if("REG".equals(type)){
			pageDrillDown = drillDownRepository.findByDate(fromdate, todate,in_circle_code, in_network_code,in_module_code, PageRequest.of(page, size));
		}
		else if("BR".equals(type)){
			pageDrillDown = drillDownRepository.findByDate(fromdate, todate,in_circle_code, in_network_code,in_module_code,in_region_code, PageRequest.of(page, size));
		}
		else  {
			
		 pageDrillDown = drillDownRepository.findByDate(fromdate, todate, PageRequest.of(page, size));
		}
		 
		 return pageDrillDown;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Page<DrillDown> findPaginatedByTxnDateSearchNext(final int page,final int size,String type, String fromdate, String todate, String in_circle_code, String in_network_code, String in_module_code, String in_region_code, String searchText){
		
		//List<DrillDown> list= nearByEntities(fromDate,toDate,circleName,networkName,moduleName,regionName);
				
        //Page<DrillDown> pageDto = new PageImpl<DrillDown>(list, PageRequest.of(page, size),list.size());
		 
		Page<DrillDown> pageDrillDown = null;
		if((in_circle_code ==null || in_circle_code.isEmpty())){
			in_circle_code = null;
		}
		if((in_network_code ==null || in_network_code.isEmpty())){
			in_network_code = null;
		}
		if((in_module_code ==null || in_module_code.isEmpty())){
			in_module_code = null;
		}
		if((in_region_code ==null || in_region_code.isEmpty())){
			in_region_code = null;
		}		

		if("NW".equals(type)){
			pageDrillDown = drillDownRepository.findByDate(fromdate, todate,in_circle_code, PageRequest.of(page, size));
		}
		else if("MOD".equals(type)){
			pageDrillDown = drillDownRepository.findByDate(fromdate, todate,in_circle_code, in_network_code, PageRequest.of(page, size));
		}
		else if("REG".equals(type)){
			pageDrillDown = drillDownRepository.findByDate(fromdate, todate,in_circle_code, in_network_code,in_module_code, PageRequest.of(page, size));
		}
		else if("BR".equals(type)){
			pageDrillDown = drillDownRepository.findByDateSearchNext(fromdate, todate,in_circle_code, in_network_code,in_module_code,in_region_code,searchText, PageRequest.of(page, size));
		}
		else  {
			
		 pageDrillDown = drillDownRepository.findByDate(fromdate, todate, PageRequest.of(page, size));
		}
		 
		 return pageDrillDown;
	}
    private List<DrillDown> nearByEntities(String fromDate,String toDate, String circleName, String networkName, String moduleName, String regionName) {
        StoredProcedureQuery nearByEntities= em.createNamedStoredProcedureQuery("sp_drill_down_proc");
        nearByEntities.setParameter("in_fromdate", fromDate);
        nearByEntities.setParameter("in_todate", toDate);
        nearByEntities.setParameter("in_circle_code", circleName);
        nearByEntities.setParameter("in_network_code", networkName);
        nearByEntities.setParameter("in_module_code", moduleName);
        nearByEntities.setParameter("in_region_code", regionName);
 
        return nearByEntities.getResultList();
    }
    
		 
	
	@Override
	public Page<DrillDownDto> findPaginatedByCircle(final int page, final int size, String circleName){
		 Page<DrillDownDto> entities = drillDownRepository.findAllByCircle(PageRequest.of(page, size), circleName).map(DrillDownDto::new);
		 		 
		 return entities;

    }
	
	
	@Override
	public Page<DrillDownDto> findPaginatedByNetwork(final int page, final int size, String networkName){
		 Page<DrillDownDto> entities = drillDownRepository.findAllByNetwork(PageRequest.of(page, size), networkName).map(DrillDownDto::new);
		 
		 return entities;

    }
	
	@Override
	public Page<DrillDownDto> findPaginatedByModule(final int page, final int size, String moduleName){
		 Page<DrillDownDto> entities = drillDownRepository.findAllByModule(PageRequest.of(page, size), moduleName).map(DrillDownDto::new);
		 
		 return entities;

    }
	
	
	@Override
	public Page<DrillDownDto> findPaginatedByRegion(final int page, final int size, String regionName){
		 Page<DrillDownDto> entities = drillDownRepository.findAllByRegion(PageRequest.of(page, size), regionName).map(DrillDownDto::new);
		 
		 return entities;

    }

	@Override
	public Page<ZeroTransactionKiosks> findPaginatedByDate(int page, int size, String fromDate, String toDate) {
		
		return null;
	}

	@Override
	public Page<DrillDown> findPaginatedByTxnDate(int page, int size,
			String fromDate, String toDate, String circleName,
			String networkName, String moduleName, String regionName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public String findSwayamTxnLastUpdatedJob() {
		
		String swayamMigrationTxnDate=null;
		
		try {
			swayamMigrationTxnDate=drillDownRepository.findCurrentDateAuditJob();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return swayamMigrationTxnDate;
		
		
		
	}

	@Override
	public Page<ZeroTransactionKiosks> findPaginatedByDateSearchNext(int page, int size, String fromDate, String toDate,
			String searchText) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
