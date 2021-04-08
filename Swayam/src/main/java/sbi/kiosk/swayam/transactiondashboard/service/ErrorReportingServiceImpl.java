package sbi.kiosk.swayam.transactiondashboard.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.common.dto.SwayamTxnDailyDto;
import sbi.kiosk.swayam.common.entity.ErrorReporting;
import sbi.kiosk.swayam.common.entity.ErrorReportingDrillDown;
import sbi.kiosk.swayam.common.entity.SwayamTxnDaily;
import sbi.kiosk.swayam.healthmonitoring.repository.BranchMasterRepository;
import sbi.kiosk.swayam.transactiondashboard.repository.ErrorReportingDrillDownRepository;
import sbi.kiosk.swayam.transactiondashboard.repository.ErrorReportingRepositoryPaging;
import sbi.kiosk.swayam.transactiondashboard.repository.SwayamTxnDailyRepository;

@Service("ErrorReportingServiceImpl")
public class ErrorReportingServiceImpl implements ErrorReportingService {

	Logger logger = LoggerFactory.getLogger(ErrorReportingServiceImpl.class);

	@Autowired
	ErrorReportingRepositoryPaging errorReportingRepo;
	
	@Autowired
	ErrorReportingDrillDownRepository errorReporDrillDownRepo;
	@Autowired
	BranchMasterRepository branchMasterRepo;
	@Autowired
	SwayamTxnDailyRepository swayamTxnDailyRepo;

	@Override
	public Page<ErrorReporting> findPaginated(int page, int size, String fromDate, String toDate) {
		//logger.info("ErrorReportingServiceImpl Started() fromDate:: "+fromDate);
		//logger.info("ErrorReportingServiceImpl Started() toDate::"+toDate);
		//List<ErrorReporting> errorRepoList = nearByEntities(fromDate, toDate);
		if((fromDate ==null || fromDate.isEmpty()) && (toDate ==null || toDate.isEmpty()) || (fromDate.equalsIgnoreCase("undefined") || fromDate.equalsIgnoreCase("undefined"))){
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yy");
			Date curDate=new Date();
			fromDate=sdf.format(curDate);
			toDate=sdf.format(curDate);
		}
		  Page<ErrorReporting> pageErrorReporting= errorReportingRepo.findByDate(fromDate, toDate, PageRequest.of(page, size));			
		//  logger.info("pageErrorReporting"+pageErrorReporting);
		  return pageErrorReporting;
	}
	
	
	@Override
	public Page<ErrorReportingDrillDown> findPaginatedByTxnDate(final int page,final int size,String type, String fromdate, String todate, String in_circle_code, String in_network_code, String in_module_code, String in_region_code){
		
		//List<DrillDown> list= nearByEntities(fromDate,toDate,circleName,networkName,moduleName,regionName);
				
        //Page<DrillDown> pageDto = new PageImpl<DrillDown>(list, PageRequest.of(page, size),list.size());
		 
		Page<ErrorReportingDrillDown> pageErrorRepDrillDown = null;
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
			pageErrorRepDrillDown = errorReporDrillDownRepo.findByDate(fromdate, todate,in_circle_code, PageRequest.of(page, size));
		}
		else if("MOD".equals(type)){
			pageErrorRepDrillDown = errorReporDrillDownRepo.findByDate(fromdate, todate,in_circle_code, in_network_code, PageRequest.of(page, size));
		}
		else if("REG".equals(type)){
			pageErrorRepDrillDown = errorReporDrillDownRepo.findByDate(fromdate, todate,in_circle_code, in_network_code,in_module_code, PageRequest.of(page, size));
		}
		else if("BR".equals(type)){
			pageErrorRepDrillDown = errorReporDrillDownRepo.findByDate(fromdate, todate,in_circle_code, in_network_code,in_module_code,in_region_code, PageRequest.of(page, size));
		}
		else  {
			
			pageErrorRepDrillDown = errorReporDrillDownRepo.findByDate(fromdate, todate, PageRequest.of(page, size));
		}
		 
		 return pageErrorRepDrillDown;
	}
	
	
	
	/*
	@Override
	public Page<ErrorReporting> findPaginatedSearchNext(int page, int size, String fromDate, String toDate, String searchText) {
		//logger.info("ErrorReportingServiceImpl Started() fromDate:: "+fromDate);
		//logger.info("ErrorReportingServiceImpl Started() toDate::"+toDate);
		//List<ErrorReporting> errorRepoList = nearByEntities(fromDate, toDate);
		if((fromDate ==null || fromDate.isEmpty()) && (toDate ==null || toDate.isEmpty()) || (fromDate.equalsIgnoreCase("undefined") || fromDate.equalsIgnoreCase("undefined"))){
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yy");
			Date curDate=new Date();
			fromDate=sdf.format(curDate);
			toDate=sdf.format(curDate);
		}
		  Page<ErrorReporting> pageErrorReporting= errorReportingRepo.findByDateSearchNext(fromDate, toDate,searchText, PageRequest.of(page, size));			
		  logger.info("pageErrorReporting"+pageErrorReporting);
		  return pageErrorReporting;
	}
*/
	
	@SuppressWarnings("deprecation")
	@Override
	public Page<ErrorReportingDrillDown> findPaginatedByTxnDateSearchNext(final int page,final int size,String type, String fromdate, String todate, String in_circle_code, String in_network_code, String in_module_code, String in_region_code, String searchText){
		
		//List<DrillDown> list= nearByEntities(fromDate,toDate,circleName,networkName,moduleName,regionName);
				
        //Page<DrillDown> pageDto = new PageImpl<DrillDown>(list, PageRequest.of(page, size),list.size());
		 
		Page<ErrorReportingDrillDown> pageDrillDown = null;
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
			pageDrillDown = errorReporDrillDownRepo.findByDate(fromdate, todate,in_circle_code, PageRequest.of(page, size));
		}
		else if("MOD".equals(type)){
			pageDrillDown = errorReporDrillDownRepo.findByDate(fromdate, todate,in_circle_code, in_network_code, PageRequest.of(page, size));
		}
		else if("REG".equals(type)){
			pageDrillDown = errorReporDrillDownRepo.findByDate(fromdate, todate,in_circle_code, in_network_code,in_module_code, PageRequest.of(page, size));
		}
		else if("BR".equals(type)){
			pageDrillDown = errorReporDrillDownRepo.findByDateSearchNext(fromdate, todate,in_circle_code, in_network_code,in_module_code,in_region_code,searchText, PageRequest.of(page, size));
		}
		else  {
			
		 pageDrillDown = errorReporDrillDownRepo.findByDate(fromdate, todate, PageRequest.of(page, size));
		}
		 
		 return pageDrillDown;
	}
	
	@Override
	public String findSwayamTxnLastUpdatedJob() {
		
		String swayamMigrationTxnDate=null;
		
		try {
			//swayamMigrationTxnDate=errorReportingRepo.findCurrentDateAuditJob();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return swayamMigrationTxnDate;
		
		
		
	}


	@Override
	public List<SwayamTxnDailyDto> findByCircleCodeErrorCount(String circleCode,String fromdate,String todate) {
		List<SwayamTxnDailyDto> pageEntity=new ArrayList<>();
		List<SwayamTxnDaily> findAllByReqBranch = null;
	
		List<String> findAllByCircleCode = branchMasterRepo.findAllByCircleCode(circleCode);
		try {
			String branchCode=null;
		for(String branchCode1:findAllByCircleCode) {
			
			logger.info("fromdate::::"+fromdate);
			logger.info("todate::::"+todate);
			branchCode=branchCode1;
			if(branchCode!=null) {	
				findAllByReqBranch = swayamTxnDailyRepo.findAllByReqBranchNoOfTechFail(branchCode,fromdate,todate,"RCV");	
				}

			if(findAllByReqBranch!=null && !findAllByReqBranch.isEmpty() && findAllByReqBranch.size()>0) {
				for(SwayamTxnDaily swayamTxnDaily:findAllByReqBranch){
					logger.info("SwayamTxnDailys:2222222222:");
					SwayamTxnDailyDto dto=new SwayamTxnDailyDto();
					dto.setErrorCodeCount(swayamTxnDaily.getErrorCodeCount());
					dto.setErrorCode(swayamTxnDaily.getErrorCode());
					dto.setErrorDesc(swayamTxnDaily.getErrorDesc());
					pageEntity.add(dto);
					//dto.setRespCode(SwayamTxnDailys.getRespCode());
				
				}
				}
			
			
		}
		
		logger.info("branchCode::sssssssssss::"+branchCode);
		//	findAllByReqBranch = swayamTxnDailyRepo.findAllByReqBranchNoOfTechFail("00380","01-09-2020","06-01-2021","RCV");
		
		
		logger.info("findAllByReqBranch::1111111111::"+findAllByReqBranch.size());
		
		}catch (Exception e) {
			e.printStackTrace();
		}	
		//pageEntity.filter(predicate);
		logger.info("pageEntity::::"+pageEntity.size());

		
		return pageEntity;
	}
	
	
	@Override
	public List<SwayamTxnDailyDto> findByCircleCodeErrorCountBussTxnFail(String circleCode,String fromdate,String todate) {
		List<SwayamTxnDailyDto> pageEntity=new ArrayList<>();
		List<SwayamTxnDaily> findAllByReqBranch =null;
		Iterable<String> findAllByCircleCode = branchMasterRepo.findAllByCircleCode(circleCode);
		try {
		String	branchCode=null;
		SwayamTxnDailyDto dto=new SwayamTxnDailyDto();
		for(String branchCode1:findAllByCircleCode) {
			logger.info("branchCode::::"+branchCode1);
			logger.info("fromdate::::"+fromdate);
			logger.info("todate::::"+todate);
			
			branchCode=branchCode1;
			
			if(branchCode!=null) {
			findAllByReqBranch = swayamTxnDailyRepo.findAllByNoOfBussTxnFail(branchCode,fromdate,todate,"NEG");
			}
			logger.info("findAllByReqBranch::::"+findAllByReqBranch.size());
			if(findAllByReqBranch!=null && !findAllByReqBranch.isEmpty() && findAllByReqBranch.size()>0) {
			
				for(SwayamTxnDaily swayamTxnDaily:findAllByReqBranch){
					logger.info("SwayamTxnDailys::");
					
					dto.setErrorCodeCount(swayamTxnDaily.getErrorCodeCount());
					dto.setErrorCode(swayamTxnDaily.getErrorCode());
					dto.setErrorDesc(swayamTxnDaily.getErrorDesc());
					pageEntity.add(dto);
				
				}
				}

			
		}
		
		
				}catch (Exception e) {
			e.printStackTrace();
		}	
		//pageEntity.filter(predicate);
		logger.info("pageEntity::::"+pageEntity.size());

		
		return pageEntity;
	}


	@Override
	public List<SwayamTxnDailyDto> findByNCodeErrorCount(String networkCode, String fromdate,
			String todate) {
		
	List<SwayamTxnDailyDto> pageEntity=new ArrayList<>();
	List<SwayamTxnDaily> findAllByReqBranch =null;
		
		Iterable<String> findAllByNetworkCode = branchMasterRepo.findAllByNetworkCode(networkCode);
		try {
			String branchCode=null;
		for(String branchCode1:findAllByNetworkCode) {
			logger.info("branchCode::::"+branchCode1);
			logger.info("fromdate::::"+fromdate);
			logger.info("todate::::"+todate);
	        branchCode=branchCode1;
	        
	    	findAllByReqBranch = swayamTxnDailyRepo.findAllByNoOfBussTxnFail(branchCode,todate,fromdate,"NEG");
			logger.info("findAllByReqBranch::"+findAllByReqBranch);
			if(findAllByReqBranch!=null && !findAllByReqBranch.isEmpty() && findAllByReqBranch.size()>0) {
				for(SwayamTxnDaily swayamTxnDaily:findAllByReqBranch){
					logger.info("SwayamTxnDailys::"+swayamTxnDaily.getErrorCode());
					SwayamTxnDailyDto dto=new SwayamTxnDailyDto();
				//	dto.setBarCode(SwayamTxnDailys.getBarCode());
					dto.setErrorCode(swayamTxnDaily.getErrorCode());
					dto.setErrorDesc(swayamTxnDaily.getErrorDesc());
					pageEntity.add(dto);
					//dto.setRespCode(SwayamTxnDailys.getRespCode());
				
				}
				
			}
			
		}
		
		
	
		}catch (Exception e) {
			e.printStackTrace();
		}	
		//pageEntity.filter(predicate);
		logger.info("pageEntity::::"+pageEntity.size());

		
		return pageEntity;
		}
	
	@Override
	public List<SwayamTxnDailyDto> findByMCodeBussTxnFailErrorCount(String networkCode, String fromdate,
			String todate) {
		
	List<SwayamTxnDailyDto> pageEntity=new ArrayList<>();
	List<SwayamTxnDaily> findAllByReqBranch =null;
		
		Iterable<String> findAllByNetworkCode = branchMasterRepo.findAllByNetworkCode(networkCode);
		try {
			String branchCode=null;
		for(String branchCode1:findAllByNetworkCode) {
			logger.info("branchCode::::"+branchCode1);
			logger.info("fromdate::::"+fromdate);
			logger.info("todate::::"+todate);
	        branchCode=branchCode1;
	        
	    	findAllByReqBranch = swayamTxnDailyRepo.findAllByNoOfBussTxnFail(branchCode,todate,fromdate,"NEG");
			logger.info("findAllByReqBranch::"+findAllByReqBranch);
			if(findAllByReqBranch!=null && !findAllByReqBranch.isEmpty() && findAllByReqBranch.size()>0) {
				for(SwayamTxnDaily swayamTxnDaily:findAllByReqBranch){
					logger.info("SwayamTxnDailys::"+swayamTxnDaily.getErrorCode());
					SwayamTxnDailyDto dto=new SwayamTxnDailyDto();
				//	dto.setBarCode(SwayamTxnDailys.getBarCode());
					dto.setErrorCode(swayamTxnDaily.getErrorCode());
					dto.setErrorDesc(swayamTxnDaily.getErrorDesc());
					pageEntity.add(dto);
					//dto.setRespCode(SwayamTxnDailys.getRespCode());
				
				}
				
			}
			
		}
		
		
	
		}catch (Exception e) {
			e.printStackTrace();
		}	
		//pageEntity.filter(predicate);
		logger.info("pageEntity::::"+pageEntity.size());

		
		return pageEntity;
		}


	@Override
	public List<SwayamTxnDailyDto> findByMCodeTechFailErrorCount(String moduleCode, String fromDate, String toDate) {
		
		List<SwayamTxnDailyDto> pageEntity=new ArrayList<>();
		List<SwayamTxnDaily> findAllByReqBranch =null;
			
			Iterable<String> findAllByNetworkCode = branchMasterRepo.findAllByModule(moduleCode);
			try {
				String branchCode=null;
			for(String branchCode1:findAllByNetworkCode) {
				logger.info("branchCode::::"+branchCode1);
				logger.info("fromdate::::"+fromDate);
				logger.info("todate::::"+toDate);
		        branchCode=branchCode1;
		        
		    	findAllByReqBranch = swayamTxnDailyRepo.findAllByNoOfBussTxnFail(branchCode,toDate,fromDate,"NEG");
				logger.info("findAllByReqBranch::"+findAllByReqBranch);
				if(findAllByReqBranch!=null && !findAllByReqBranch.isEmpty() && findAllByReqBranch.size()>0) {
					for(SwayamTxnDaily swayamTxnDaily:findAllByReqBranch){
						logger.info("SwayamTxnDailys::"+swayamTxnDaily.getErrorCode());
						SwayamTxnDailyDto dto=new SwayamTxnDailyDto();
					//	dto.setBarCode(SwayamTxnDailys.getBarCode());
						dto.setErrorCode(swayamTxnDaily.getErrorCode());
						dto.setErrorDesc(swayamTxnDaily.getErrorDesc());
						pageEntity.add(dto);
						//dto.setRespCode(SwayamTxnDailys.getRespCode());
					
					}
					
				}
				
			}
			
			
		
			}catch (Exception e) {
				e.printStackTrace();
			}	
			//pageEntity.filter(predicate);
			logger.info("pageEntity::::"+pageEntity.size());

			
			return pageEntity;
	}


	@Override
	public List<SwayamTxnDailyDto> findByRegionCodeBussFailErrorCount(String regionCode, String fromDate,String toDate) {
		List<SwayamTxnDailyDto> pageEntity=new ArrayList<>();
		List<SwayamTxnDaily> findAllByReqBranch =null;
			
			Iterable<String> findAllByNetworkCode = branchMasterRepo.findAllByRegionCodeCode(regionCode);
			try {
				String branchCode=null;
			for(String branchCode1:findAllByNetworkCode) {
				logger.info("branchCode::::"+branchCode1);
				logger.info("fromdate::::"+fromDate);
				logger.info("todate::::"+toDate);
		        branchCode=branchCode1;
		        
		    	findAllByReqBranch = swayamTxnDailyRepo.findAllByNoOfBussTxnFail(branchCode,toDate,fromDate,"NEG");
				logger.info("findAllByReqBranch::"+findAllByReqBranch);
				if(findAllByReqBranch!=null && !findAllByReqBranch.isEmpty() && findAllByReqBranch.size()>0) {
					for(SwayamTxnDaily swayamTxnDaily:findAllByReqBranch){
						logger.info("SwayamTxnDailys::"+swayamTxnDaily.getErrorCode());
						SwayamTxnDailyDto dto=new SwayamTxnDailyDto();
					//	dto.setBarCode(SwayamTxnDailys.getBarCode());
						dto.setErrorCode(swayamTxnDaily.getErrorCode());
						dto.setErrorDesc(swayamTxnDaily.getErrorDesc());
						pageEntity.add(dto);
						//dto.setRespCode(SwayamTxnDailys.getRespCode());
					
					}
					
				}
				
			}
			
			
		
			}catch (Exception e) {
				e.printStackTrace();
			}	
			//pageEntity.filter(predicate);
			logger.info("pageEntity::::"+pageEntity.size());

			
			return pageEntity;
	}


	@Override
	public List<SwayamTxnDailyDto> findByRegionCodeTechFailErrorCount(String regionCode, String fromDate,String toDate) {
		List<SwayamTxnDailyDto> pageEntity=new ArrayList<>();
		List<SwayamTxnDaily> findAllByReqBranch =null;
			
			Iterable<String> findAllByNetworkCode = branchMasterRepo.findAllByRegionCodeCode(regionCode);
			try {
				String branchCode=null;
			for(String branchCode1:findAllByNetworkCode) {
				logger.info("branchCode::::"+branchCode1);
				logger.info("fromdate::::"+fromDate);
				logger.info("todate::::"+toDate);
		        branchCode=branchCode1;
		        
		    	findAllByReqBranch = swayamTxnDailyRepo.findAllByNoOfBussTxnFail(branchCode,toDate,fromDate,"NEG");
				logger.info("findAllByReqBranch::"+findAllByReqBranch);
				if(findAllByReqBranch!=null && !findAllByReqBranch.isEmpty() && findAllByReqBranch.size()>0) {
					for(SwayamTxnDaily swayamTxnDaily:findAllByReqBranch){
						logger.info("SwayamTxnDailys::"+swayamTxnDaily.getErrorCode());
						SwayamTxnDailyDto dto=new SwayamTxnDailyDto();
					//	dto.setBarCode(SwayamTxnDailys.getBarCode());
						dto.setErrorCode(swayamTxnDaily.getErrorCode());
						dto.setErrorDesc(swayamTxnDaily.getErrorDesc());
						pageEntity.add(dto);
						//dto.setRespCode(SwayamTxnDailys.getRespCode());
					
					}
					
				}
				
			}
			
			
		
			}catch (Exception e) {
				e.printStackTrace();
			}	
			//pageEntity.filter(predicate);
			logger.info("pageEntity::::"+pageEntity.size());

			
			return pageEntity;
	}
	
	
}
