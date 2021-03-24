package sbi.kiosk.swayam.healthmonitoring.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.common.dto.KioskBranchMasterUserDto;
import sbi.kiosk.swayam.common.dto.TerminalStatusDto;
import sbi.kiosk.swayam.common.dto.TicketCentorDto;
import sbi.kiosk.swayam.common.entity.BranchMaster;
import sbi.kiosk.swayam.common.entity.KioskBranchMaster;
import sbi.kiosk.swayam.common.entity.TerminalStatus;
import sbi.kiosk.swayam.healthmonitoring.repository.BranchMasterRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.KioskMasterRepo;
import sbi.kiosk.swayam.healthmonitoring.repository.TerminalStatusRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.TerminalStatusRepositoryPaging;
import sbi.kiosk.swayam.transactiondashboard.controller.TransactionDashBoardController;

@Service
public class TerminalStatusServiceImpl implements TerminalStatusService {
	Logger logger = LoggerFactory.getLogger(TerminalStatusServiceImpl.class);
	@Autowired
	TerminalStatusRepository terminalStatusRepo;
	@Autowired
	TerminalStatusRepositoryPaging terminalStatusRepositoryPaging;
	@Autowired
	BranchMasterRepository branchMasterRepo;
	@Autowired
	KioskMasterRepo kioskMasterRepo;


	/*@Override
	public Page<TerminalStatusDto> findPaginated(int page, int size) {
		logger.info("Service-========--"+page);
		
		terminalStatusRepo.findById("");
		
		
		
		
		List<TerminalStatus> dto=terminalStatusRepo.findAll();
		logger.info("dto======"+dto.size());
		Page<TerminalStatusDto> entities=terminalStatusRepositoryPaging.findAll(PageRequest.of(page, size)).map(TerminalStatusDto::new);
		
		Page<TerminalStatusDto> entities = terminalStatusRepositoryPaging.findPaginated(PageRequest.of(page, size))
				 .map(TerminalStatusDto::new);
		logger.info("entities terminal status==="+entities.getContent());
		return entities;
	}
*/
	
	@Override
	public Page<TerminalStatusDto> findPaginated(int page, int size) {
		logger.info("Service-========--" + page);
		List<KioskBranchMaster> kioskMastlist = null;
		String cmfNameList = null;
		Page<TerminalStatusDto> entities = terminalStatusRepositoryPaging.findAll(PageRequest.of(page, size))
				.map(TerminalStatusDto::new);

		for (TerminalStatusDto dto : entities) {
			//    logger.info("entities terminal status===" + entities.getContent());
			//	logger.info("=terminalStatus=====kioskId=======" + dto.getKioskId());
				kioskMastlist = kioskMasterRepo.findByKioskId(dto.getKioskId());
			//	logger.info("kioskId=list zise===" + kioskMastlist.size());
                
			  for (KioskBranchMaster kioskBranchMast : kioskMastlist) {
				//	logger.info("SRNO=========" + kioskBranchMast.getKioskSerialNo());
					dto.setKioskSrNo(kioskBranchMast.getKioskSerialNo());
				//	logger.info("kioskId==:: " + kioskBranchMast.getKioskId());
					dto.setKioskId(kioskBranchMast.getKioskId());
					dto.setBranchCode(kioskBranchMast.getBranchCode());
					
				}
			  
		//	  logger.info("dto.getKioskId()-------------"+dto.getKioskId());
			   List<BranchMaster> branchMastList = branchMasterRepo.findAllByBranchCode(dto.getBranchCode());
			//	 logger.info("branchMastList==========" + branchMastList.size());
				for (BranchMaster branchMast : branchMastList) {
				//	logger.info("Br Code====" + branchMast.getBranchCode());
				//	logger.info("Circle====" + branchMast.getCircle());
					dto.setBranchCode(branchMast.getBranchCode());
					dto.setCircle(branchMast.getCircleName());
				}
			  
						
			    cmfNameList = terminalStatusRepo.findByKisoskId(dto.getKioskId());
			//	logger.info("CMF User==" + cmfNameList);
			    dto.setCmf(cmfNameList);	
			//	logger.info("dto::::" + dto);
			}
		
		return entities;
	}
	
	
	@Override
	public Map<String, Integer> findAllCountAgentStatus() {
		Map<String, Integer> mapData = null;
		try {

			mapData = new LinkedHashMap<String, Integer>();
			int agentStatusRedCount = terminalStatusRepo.findByAgentStatusRed("Red");
			int agentStatusGreenCount = terminalStatusRepo.findByAgentStatusGreen("Green");
			int agentStatusGreyCount = terminalStatusRepo.findByAgentStatusGrey("Gray");
			logger.info("redCount====" + agentStatusRedCount);
			logger.info("agentStatusGreenCount====" + agentStatusGreenCount);
			logger.info("agentStatusGreyCount====" + agentStatusGreyCount);

			mapData.put("AgentStatusRedCount", agentStatusRedCount);
			mapData.put("AgentStatusGreenCount", agentStatusGreenCount);
			mapData.put("AgentStatusGreyCount", agentStatusGreyCount);
			int redCartriCount = terminalStatusRepo.findByCartridgeStatusRed("Red");
			int greenCartriCount = terminalStatusRepo.findByCartridgeStatusGreen("Green");
			int greyCartriCount = terminalStatusRepo.findByCartridgeStatusGrey("Gray");

			mapData.put("RedCartriCount", redCartriCount);
			mapData.put("GreenCartriCount", greenCartriCount);
			mapData.put("GreyCartriCount", greyCartriCount);

			int printerStatusRedCount = terminalStatusRepo.findByPrinterStatusRed("Red");
			int printerStatusGreenCount = terminalStatusRepo.findByPrinterStatusGreen("Green");
			int printerStatusGreyCount = terminalStatusRepo.findByPrinterStatusGrey("Gray");

			mapData.put("PrinterStatusRedCount", printerStatusRedCount);
			mapData.put("PrinterStatusGreenCount", printerStatusGreenCount);
			mapData.put("PrinterStatusGreyCount", printerStatusGreyCount);

			int redApplicationCount = terminalStatusRepo.findByApplicatinStatusRed("Red");
			int greenApplicationCount = terminalStatusRepo.findByApplicatinStatusGreen("Green");
			int greyApplicationCount = terminalStatusRepo.findByApplicatinStatusGray("Gray");

			mapData.put("RedApplicationCount", redApplicationCount);
			mapData.put("GreenApplicationCount", greenApplicationCount);
			mapData.put("GreyApplicationCount", greyApplicationCount);

		} catch (Exception e) {
			
		}
		return mapData;

	}

	

	/*
	 * @Override public Map<String, Integer> findAllCountAgentStatus() { Map<String,
	 * Integer> mapData = null; try {
	 * 
	 * mapData = new HashMap<String, Integer>(); int redCount =
	 * terminalStatusRepo.findByAgentStatusRed("Red"); int greenCount =
	 * terminalStatusRepo.findByAgentStatusGreen("Green"); int greyCount =
	 * terminalStatusRepo.findByAgentStatusGrey("Grey"); logger.info("redCount===="
	 * + redCount);
	 * 
	 * mapData.put("RedCount", redCount); mapData.put("GreenCount", greenCount);
	 * mapData.put("GreyCount", greyCount); int redCartriCount =
	 * terminalStatusRepo.findByCartridgeStatusRed("Red"); int greenCartriCount =
	 * terminalStatusRepo.findByCartridgeStatusGreen("Green"); int greyCartriCount =
	 * terminalStatusRepo.findByCartridgeStatusGrey("Grey");
	 * 
	 * mapData.put("RedCartriCount", redCartriCount);
	 * mapData.put("GreenCartriCount", greenCartriCount);
	 * mapData.put("GreyCartriCount", greyCartriCount);
	 * 
	 * int redAntivirusCount = terminalStatusRepo.findByAntivirusStatusRed("Red");
	 * int greenAntivirusCount =
	 * terminalStatusRepo.findByAntivirusStatusGreen("Green"); int
	 * greyAntivirusCount = terminalStatusRepo.findByAntivirusStatusGrey("Grey");
	 * 
	 * mapData.put("RedAntivirusCount", redAntivirusCount);
	 * mapData.put("GreenAntivirusCount", greenAntivirusCount);
	 * mapData.put("GreyAntivirusCount", greyAntivirusCount);
	 * 
	 * int redApplicationCount =
	 * terminalStatusRepo.findByApplicatinStatusRed("Red"); int
	 * greenApplicationCount =
	 * terminalStatusRepo.findByApplicatinStatusGreen("Green"); int
	 * greyApplicationCount = terminalStatusRepo.findByApplicatinStatusGrey("Grey");
	 * 
	 * mapData.put("RedApplicationCount", redApplicationCount);
	 * mapData.put("GreenApplicationCount", greenApplicationCount);
	 * mapData.put("GreyApplicationCount", greyApplicationCount);
	 * 
	 * } catch (Exception e) {
	 * 
	 * } return mapData;
	 * 
	 * }
	 */

//	@Override
//	public Page<TerminalStatusDto> findPaginatedCount(int page, int size, String type) {
//    //      logger.info("findPaginatedCount Service=====type"+type);
//          Page<TerminalStatusDto> entities =null;
//          Page<TerminalStatusDto> terminalEntities =null;
//
//       if(type.equals("NoOfRedPVSRed")){
//		 entities = terminalStatusRepositoryPaging.findByPbPrinterStatus(PageRequest.of(page, size),"Red");
//	//	logger.info("entities::::::::::"+entities.getContent());
//		
//		terminalEntities=getAllTerminalStatusOfDto(entities);
//		
//       }else  if(type.equals("NoOfGreenPVSGreen")){
//   		 entities = terminalStatusRepositoryPaging.findByPbPrinterStatus(PageRequest.of(page, size),"Green");
//   	//	logger.info("entities::::::::::"+entities.getContent());
//   		
//   		terminalEntities=getAllTerminalStatusOfDto(entities);
//   		
//          } else if(type.equals("NoOfGreyPVSGrey")){
//   		entities = terminalStatusRepositoryPaging.findByPbPrinterStatus(PageRequest.of(page, size),"Grey");
//   	//	logger.info("entities::::::::::"+entities.getContent());
//          
//   		terminalEntities=getAllTerminalStatusOfDto(entities);
//       }
//	
//    else if(type.equals("NoOfRedCARTRed")){
//		entities = terminalStatusRepositoryPaging.findByCartridgeStatus(PageRequest.of(page, size),"Red");
//	//	logger.info("entities::::::::::"+entities.getContent());
//		terminalEntities=getAllTerminalStatusOfDto(entities);
//       }
//    else if(type.equals("NoOfGreenCARTGreen")){
//		entities = terminalStatusRepositoryPaging.findByCartridgeStatus(PageRequest.of(page, size),"Green");
//	//	logger.info("entities::::::::::"+entities.getContent());
//		terminalEntities=getAllTerminalStatusOfDto(entities);
//       }
//    else if(type.equals("NoOfGreyCARTGrey")){
//		entities = terminalStatusRepositoryPaging.findByCartridgeStatus(PageRequest.of(page, size),"Grey");
//	//	logger.info("entities::::::::::"+entities.getContent());
//		terminalEntities=getAllTerminalStatusOfDto(entities);
//       }
//       
//    else if(type.equals("NoOfRedANTRed")){
//		entities = terminalStatusRepositoryPaging.findByAntivirusStatus(PageRequest.of(page, size),"Red");
//	//	logger.info("entities::::::::::"+entities.getContent());
//		terminalEntities=getAllTerminalStatusOfDto(entities);
//		
//       }
//       
//       
//    else if(type.equals("NoOfGreenANTGreen")){
//		entities = terminalStatusRepositoryPaging.findByAntivirusStatus(PageRequest.of(page, size),"Green");
//	//	logger.info("entities::::::::::"+entities.getContent());
//		terminalEntities=getAllTerminalStatusOfDto(entities);
//       }
//       
//    else if(type.equals("NoOfGreyANTGrey")){
//		entities = terminalStatusRepositoryPaging.findByAntivirusStatus(PageRequest.of(page, size),"Grey");
//	//	logger.info("entities::::::::::"+entities.getContent());
//		terminalEntities=getAllTerminalStatusOfDto(entities);
//       }
//    else if(type.equals("NoOfRedAPPSRed")){
//		entities = terminalStatusRepositoryPaging.findByAplicationStatus(PageRequest.of(page, size),"Red");
//	//	logger.info("entities::::::::::"+entities.getContent());
//		terminalEntities=getAllTerminalStatusOfDto(entities);
//       }
//    else if(type.equals("NoOfGreenAPPSGreen")){
//		entities = terminalStatusRepositoryPaging.findByAplicationStatus(PageRequest.of(page, size),"Green");
//	//	logger.info("entities::::::::::"+entities.getContent());
//		terminalEntities=getAllTerminalStatusOfDto(entities);
//       }
//    else if(type.equals("NoOfGreyAPPSGrey")){
//		entities = terminalStatusRepositoryPaging.findByAplicationStatus(PageRequest.of(page, size),"Grey");
//	//	logger.info("entities::::::::::"+entities.getContent());
//		terminalEntities=getAllTerminalStatusOfDto(entities);
//       }
//       else{
//    	   		entities = terminalStatusRepositoryPaging.findByAplicationStatus(PageRequest.of(page, size),type);
//    	   //		logger.info("entities::::::::::"+entities.getContent());
//    	   		terminalEntities=getAllTerminalStatusOfDto(entities);
//       }
//       
//		return terminalEntities;
//	}
//	
//	
//	public Page<TerminalStatusDto>  getAllTerminalStatusOfDto(Page<TerminalStatusDto> entities){
//		
//		List<KioskBranchMaster> kioskMastlist = null;
//		String cmfNameList = null;
//		
//		for (TerminalStatusDto dto : entities) {
//	//	    logger.info("entities terminal status===" + entities.getContent());
//	//		logger.info("=terminalStatus=====kioskId=======" + dto.getKioskId());
//			kioskMastlist = kioskMasterRepo.findByKioskId(dto.getKioskId());
//	//		logger.info("kioskId=list zise===" + kioskMastlist.size());
//            
//		  for (KioskBranchMaster kioskBranchMast : kioskMastlist) {
//		//		logger.info("SRNO=========" + kioskBranchMast.getKioskSerialNo());
//				dto.setKioskSrNo(kioskBranchMast.getKioskSerialNo());
//		//		logger.info("kioskId==:: " + kioskBranchMast.getKioskId());
//				dto.setKioskId(kioskBranchMast.getKioskId());
//				dto.setBranchCode(kioskBranchMast.getBranchCode());
//				
//			}
//		  
//	//	  logger.info("dto.getKioskId()-------------"+dto.getKioskId());
//		   List<BranchMaster> branchMastList = branchMasterRepo.findAllByBranchCode(dto.getBranchCode());
//	//		 logger.info("branchMastList==========" + branchMastList.size());
//			for (BranchMaster branchMast : branchMastList) {
//	//			logger.info("Br Code====" + branchMast.getBranchCode());
//	//			logger.info("Circle====" + branchMast.getCircle());
//				dto.setBranchCode(branchMast.getBranchCode());
//		//		dto.setCircle(branchMast.getCircle());
//			}
//		  
//					
//		    cmfNameList = terminalStatusRepo.findByKisoskId(dto.getKioskId());
//	//		logger.info("CMF User==" + cmfNameList);
//		    dto.setCmf(cmfNameList);	
//	//		logger.info("dto::::" + dto);
//		}
//	return entities;
//		
//	}
	
	
	@Override
	public Page<TerminalStatusDto> findPaginatedCount(int page, int size, String type) {
    //      logger.info("findPaginatedCount Service=====type"+type);
          Page<TerminalStatusDto> entities =null;
          Page<TerminalStatusDto> terminalEntities =null;

       if(type.equals("NoOfRedPVSRed")){
		 //entities = terminalStatusRepositoryPaging.findByPbPrinterStatus(PageRequest.of(page, size),"Red");
		 entities =terminalStatusRepo.findByPrinterStatusRedList(PageRequest.of(page,size)).map(TerminalStatusDto::new);
		logger.info("entities::::::NoOfRedPVSRed::::"+entities.getContent());
		
		terminalEntities=getAllTerminalStatusOfDto(entities);
		
       }else  if(type.equals("NoOfGreenPVSGreen")){
   		 //entities = terminalStatusRepositoryPaging.findByPbPrinterStatus(PageRequest.of(page, size),"Green");
   		entities =terminalStatusRepo.findByPrinterStatusGreenList(PageRequest.of(page,size)).map(TerminalStatusDto::new);
   		logger.info("entities:::::::NoOfGreenPVSGreen:::"+entities.getContent());
   		
   		terminalEntities=getAllTerminalStatusOfDto(entities);
   		
          } else if(type.equals("NoOfGreyPVSGrey")){
   		//entities = terminalStatusRepositoryPaging.findByPbPrinterStatus(PageRequest.of(page, size),"Grey");
   		entities =terminalStatusRepo.findByPrinterStatusGrayList(PageRequest.of(page,size)).map(TerminalStatusDto::new);
   		logger.info("entities::::::NoOfGreyPVSGrey::::"+entities.getContent());
          
   		terminalEntities=getAllTerminalStatusOfDto(entities);
       } else if(type.equals("NoOfRedCARTRed")){
		//entities = terminalStatusRepositoryPaging.findByCartridgeStatus(PageRequest.of(page, size),"Red");
		entities =terminalStatusRepo.findByCartridgeStatusRedList(PageRequest.of(page,size)).map(TerminalStatusDto::new);
	logger.info("entities::::::NoOfRedCARTRed::::"+entities.getContent());
		terminalEntities=getAllTerminalStatusOfDto(entities);
       }
    else if(type.equals("NoOfGreenCARTGreen")){
		//entities = terminalStatusRepositoryPaging.findByCartridgeStatus(PageRequest.of(page, size),"Green");
		entities =terminalStatusRepo.findByCartridgeStatusGreenList(PageRequest.of(page,size)).map(TerminalStatusDto::new);
		logger.info("entities:::::NoOfGreenCARTGreen:::::"+entities.getContent());
		terminalEntities=getAllTerminalStatusOfDto(entities);
       }
    else if(type.equals("NoOfGreyCARTGrey")){
		//entities = terminalStatusRepositoryPaging.findByCartridgeStatus(PageRequest.of(page, size),"Grey");
		entities =terminalStatusRepo.findByCartridgeStatusGrayList(PageRequest.of(page,size)).map(TerminalStatusDto::new);
		logger.info("entities::::::::NoOfGreyCARTGrey::"+entities.getContent());
		terminalEntities=getAllTerminalStatusOfDto(entities);
       }
       
    else if(type.equals("NoOfRedANTRed")){
		//entities = terminalStatusRepositoryPaging.findByAgentStatus(PageRequest.of(page, size),"Red");
		//entities = terminalStatusRepositoryPaging.findByAgentStatus(PageRequest.of(page, size),"red","RED","Red");
    	entities =terminalStatusRepo.findByAgentStatusRedList(PageRequest.of(page,size)).map(TerminalStatusDto::new);
	    logger.info("entitiesList=====111111111111111111=========:NoOfRedANTRed:::::::::"+entities.getContent().size());
		
	//logger.info("entities:NoOfRedANTRed:::::::::"+entities11.getContent().size());
	
	
		terminalEntities=getAllTerminalStatusOfDto(entities);
		
       }
       
       
    else if(type.equals("NoOfGreenANTGreen")){
		//entities = terminalStatusRepositoryPaging.findByAgentStatus(PageRequest.of(page, size),"Green");
		entities =terminalStatusRepo.findByAgentStatusGreenList(PageRequest.of(page,size)).map(TerminalStatusDto::new);
	logger.info("entities::::NoOfGreenANTGreen::::::"+entities.getContent().size());
		terminalEntities=getAllTerminalStatusOfDto(entities);
       }
       
    else if(type.equals("NoOfGreyANTGrey")){
		//entities = terminalStatusRepositoryPaging.findByAgentStatus(PageRequest.of(page, size),"Grey");
		entities =terminalStatusRepo.findByAgentStatusGrayList(PageRequest.of(page,size)).map(TerminalStatusDto::new);
		logger.info("entities::::::::::"+entities.getContent().size());
		terminalEntities=getAllTerminalStatusOfDto(entities);
       }
    else if(type.equals("NoOfRedAPPSRed")){
		//entities = terminalStatusRepositoryPaging.findByAplicationStatus(PageRequest.of(page, size),"Red");
		entities =terminalStatusRepo.findByApplicatinStatusRedList(PageRequest.of(page,size)).map(TerminalStatusDto::new);
		logger.info("entities::::::::::"+entities.getContent());
		terminalEntities=getAllTerminalStatusOfDto(entities);
       }
    else if(type.equals("NoOfGreenAPPSGreen")){
		//entities = terminalStatusRepositoryPaging.findByAplicationStatus(PageRequest.of(page, size),"Green");
		entities =terminalStatusRepo.findByApplicatinStatusGreenList(PageRequest.of(page,size)).map(TerminalStatusDto::new);
		logger.info("entities::::::::::"+entities.getContent());
		terminalEntities=getAllTerminalStatusOfDto(entities);
       }
    else if(type.equals("NoOfGreyAPPSGrey")){
		//entities = terminalStatusRepositoryPaging.findByAplicationStatus(PageRequest.of(page, size),"Grey");
		entities =terminalStatusRepo.findByApplicatinStatusGrayList(PageRequest.of(page,size)).map(TerminalStatusDto::new);
		logger.info("entities::::::::::"+entities.getContent());
		terminalEntities=getAllTerminalStatusOfDto(entities);
       }
       else{
    	   		entities = terminalStatusRepositoryPaging.findByAplicationStatus(PageRequest.of(page, size),type);
    	  		logger.info("entities::::::::::"+entities.getContent());
    	   		terminalEntities=getAllTerminalStatusOfDto(entities);
       }
       
		return terminalEntities;
	}
	
	
	public Page<TerminalStatusDto> convert(List<TerminalStatusDto> entities11,Integer page,Integer size) {
		Page<TerminalStatusDto> dto =null;
				dto = new PageImpl<TerminalStatusDto>(entities11);
        // Conversion logic

        return dto;
    }
	
	
	public Page<TerminalStatusDto>  getAllTerminalStatusOfDto(Page<TerminalStatusDto> entities){
		
		List<KioskBranchMaster> kioskMastlist = null;
		String cmfNameList = null;
		
		for (TerminalStatusDto dto : entities) {
	//	    logger.info("entities terminal status===" + entities.getContent());
	//		logger.info("=terminalStatus=====kioskId=======" + dto.getKioskId());
			kioskMastlist = kioskMasterRepo.findByKioskId(dto.getKioskId());
			logger.info("kioskId=list zise===" + kioskMastlist.size());
            
		  for (KioskBranchMaster kioskBranchMast : kioskMastlist) {
		//		logger.info("SRNO=========" + kioskBranchMast.getKioskSerialNo());
				dto.setKioskSrNo(kioskBranchMast.getKioskSerialNo());
		//		logger.info("kioskId==:: " + kioskBranchMast.getKioskId());
				dto.setKioskId(kioskBranchMast.getKioskId());
				dto.setBranchCode(kioskBranchMast.getBranchCode());
				
			}
		  
	//	  logger.info("dto.getKioskId()-------------"+dto.getKioskId());
		   List<BranchMaster> branchMastList = branchMasterRepo.findAllByBranchCode(dto.getBranchCode());
			 logger.info("branchMastList==========" + branchMastList.size());
			for (BranchMaster branchMast : branchMastList) {
	//			logger.info("Br Code====" + branchMast.getBranchCode());
	//			logger.info("Circle====" + branchMast.getCircle());
				dto.setBranchCode(branchMast.getBranchCode());
				dto.setCircle(branchMast.getCircleName());
			}
		  
					
		    cmfNameList = terminalStatusRepo.findByKisoskId(dto.getKioskId());
			logger.info("CMF User==" + cmfNameList);
		    dto.setCmf(cmfNameList);	
	//		logger.info("dto::::" + dto);
		}
	return entities;
		
	}
	
	

}
