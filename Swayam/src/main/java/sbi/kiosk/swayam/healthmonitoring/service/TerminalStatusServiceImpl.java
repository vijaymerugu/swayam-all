package sbi.kiosk.swayam.healthmonitoring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.common.dto.TerminalStatusDto;
import sbi.kiosk.swayam.common.entity.BranchMaster;
import sbi.kiosk.swayam.common.entity.KioskBranchMaster;
import sbi.kiosk.swayam.healthmonitoring.repository.BranchMasterRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.KioskMasterRepo;
import sbi.kiosk.swayam.healthmonitoring.repository.TerminalStatusRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.TerminalStatusRepositoryPaging;

@Service
public class TerminalStatusServiceImpl implements TerminalStatusService {

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
		System.out.println("Service-========--"+page);
		
		terminalStatusRepo.findById("");
		
		
		
		
		List<TerminalStatus> dto=terminalStatusRepo.findAll();
		System.out.println("dto======"+dto.size());
		Page<TerminalStatusDto> entities=terminalStatusRepositoryPaging.findAll(PageRequest.of(page, size)).map(TerminalStatusDto::new);
		
		Page<TerminalStatusDto> entities = terminalStatusRepositoryPaging.findPaginated(PageRequest.of(page, size))
				 .map(TerminalStatusDto::new);
		System.out.println("entities terminal status==="+entities.getContent());
		return entities;
	}
*/
	
	@Override
	public Page<TerminalStatusDto> findPaginated(int page, int size) {
		System.out.println("Service-========--" + page);

		List<KioskBranchMaster> kioskMastlist = null;
		String cmfNameList = null;
		Page<TerminalStatusDto> entities = terminalStatusRepositoryPaging.findAll(PageRequest.of(page, size))
				.map(TerminalStatusDto::new);

		for (TerminalStatusDto dto : entities) {
			    System.out.println("entities terminal status===" + entities.getContent());
				System.out.println("=terminalStatus=====kioskId=======" + dto.getKioskId());
				kioskMastlist = kioskMasterRepo.findByKioskId(dto.getKioskId());
				System.out.println("kioskId=list zise===" + kioskMastlist.size());
                
			  for (KioskBranchMaster kioskBranchMast : kioskMastlist) {
					System.out.println("SRNO=========" + kioskBranchMast.getKioskSerialNo());
					dto.setKioskSrNo(kioskBranchMast.getKioskSerialNo());
					System.out.println("kioskId==:: " + kioskBranchMast.getKioskId());
					dto.setKioskId(kioskBranchMast.getKioskId());
					dto.setBranchCode(kioskBranchMast.getBranchCode());
					
				}
			  
			  System.out.println("dto.getKioskId()-------------"+dto.getKioskId());
			   List<BranchMaster> branchMastList = branchMasterRepo.findAllByBranchCode(dto.getBranchCode());
				 System.out.println("branchMastList==========" + branchMastList.size());
				for (BranchMaster branchMast : branchMastList) {
					System.out.println("Br Code====" + branchMast.getBranchCode());
					System.out.println("Circle====" + branchMast.getCircle());
					dto.setBranchCode(branchMast.getBranchCode());
					dto.setCircle(branchMast.getCircle());
				}
			  
						
			    cmfNameList = terminalStatusRepo.findByKisoskId(dto.getKioskId());
				System.out.println("CMF User==" + cmfNameList);
			    dto.setCmf(cmfNameList);	
				System.out.println("dto::::" + dto);
			}
		
		return entities;
	}

	@Override
	public Map<String, Integer> findAllCountAgentStatus() {
		Map<String, Integer> mapData = null;
		try {

			mapData = new HashMap<String, Integer>();
			int redCount = terminalStatusRepo.findByAgentStatusRed("Red");
			int greenCount = terminalStatusRepo.findByAgentStatusGreen("Green");
			int greyCount = terminalStatusRepo.findByAgentStatusGrey("Grey");
			System.out.println("redCount====" + redCount);

			mapData.put("RedCount", redCount);
			mapData.put("GreenCount", greenCount);
			mapData.put("GreyCount", greyCount);
			int redCartriCount = terminalStatusRepo.findByCartridgeStatusRed("Red");
			int greenCartriCount = terminalStatusRepo.findByCartridgeStatusGreen("Green");
			int greyCartriCount = terminalStatusRepo.findByCartridgeStatusGrey("Grey");

			mapData.put("RedCartriCount", redCartriCount);
			mapData.put("GreenCartriCount", greenCartriCount);
			mapData.put("GreyCartriCount", greyCartriCount);

			int redAntivirusCount = terminalStatusRepo.findByAntivirusStatusRed("Red");
			int greenAntivirusCount = terminalStatusRepo.findByAntivirusStatusGreen("Green");
			int greyAntivirusCount = terminalStatusRepo.findByAntivirusStatusGrey("Grey");

			mapData.put("RedAntivirusCount", redAntivirusCount);
			mapData.put("GreenAntivirusCount", greenAntivirusCount);
			mapData.put("GreyAntivirusCount", greyAntivirusCount);

			int redApplicationCount = terminalStatusRepo.findByApplicatinStatusRed("Red");
			int greenApplicationCount = terminalStatusRepo.findByApplicatinStatusGreen("Green");
			int greyApplicationCount = terminalStatusRepo.findByApplicatinStatusGrey("Grey");

			mapData.put("RedApplicationCount", redApplicationCount);
			mapData.put("GreenApplicationCount", greenApplicationCount);
			mapData.put("GreyApplicationCount", greyApplicationCount);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapData;

	}

	@Override
	public Page<TerminalStatusDto> findPaginatedCount(int page, int size, String type) {
          System.out.println("findPaginatedCount Service=====type"+type);
          Page<TerminalStatusDto> entities =null;
          Page<TerminalStatusDto> terminalEntities =null;

       if(type.equals("NoOfRedPVSRed")){
		 entities = terminalStatusRepositoryPaging.findByPbPrinterStatus(PageRequest.of(page, size),"Red");
		System.out.println("entities::::::::::"+entities.getContent());
		
		terminalEntities=getAllTerminalStatusOfDto(entities);
		
       }else  if(type.equals("NoOfGreenPVSGreen")){
   		 entities = terminalStatusRepositoryPaging.findByPbPrinterStatus(PageRequest.of(page, size),"Green");
   		System.out.println("entities::::::::::"+entities.getContent());
   		
   		terminalEntities=getAllTerminalStatusOfDto(entities);
   		
          } else if(type.equals("NoOfGreyPVSGrey")){
   		entities = terminalStatusRepositoryPaging.findByPbPrinterStatus(PageRequest.of(page, size),"Grey");
   		System.out.println("entities::::::::::"+entities.getContent());
          
   		terminalEntities=getAllTerminalStatusOfDto(entities);
       }
	
    else if(type.equals("NoOfRedCARTRed")){
		entities = terminalStatusRepositoryPaging.findByCartridgeStatus(PageRequest.of(page, size),"Red");
		System.out.println("entities::::::::::"+entities.getContent());
		terminalEntities=getAllTerminalStatusOfDto(entities);
       }
    else if(type.equals("NoOfGreenCARTGreen")){
		entities = terminalStatusRepositoryPaging.findByCartridgeStatus(PageRequest.of(page, size),"Green");
		System.out.println("entities::::::::::"+entities.getContent());
		terminalEntities=getAllTerminalStatusOfDto(entities);
       }
    else if(type.equals("NoOfGreyCARTGrey")){
		entities = terminalStatusRepositoryPaging.findByCartridgeStatus(PageRequest.of(page, size),"Grey");
		System.out.println("entities::::::::::"+entities.getContent());
		terminalEntities=getAllTerminalStatusOfDto(entities);
       }
       
    else if(type.equals("NoOfRedANTRed")){
		entities = terminalStatusRepositoryPaging.findByAntivirusStatus(PageRequest.of(page, size),"Red");
		System.out.println("entities::::::::::"+entities.getContent());
		terminalEntities=getAllTerminalStatusOfDto(entities);
		
       }
       
       
    else if(type.equals("NoOfGreenANTGreen")){
		entities = terminalStatusRepositoryPaging.findByAntivirusStatus(PageRequest.of(page, size),"Green");
		System.out.println("entities::::::::::"+entities.getContent());
		terminalEntities=getAllTerminalStatusOfDto(entities);
       }
       
    else if(type.equals("NoOfGreyANTGrey")){
		entities = terminalStatusRepositoryPaging.findByAntivirusStatus(PageRequest.of(page, size),"Grey");
		System.out.println("entities::::::::::"+entities.getContent());
		terminalEntities=getAllTerminalStatusOfDto(entities);
       }
    else if(type.equals("NoOfRedAPPSRed")){
		entities = terminalStatusRepositoryPaging.findByAplicationStatus(PageRequest.of(page, size),"Red");
		System.out.println("entities::::::::::"+entities.getContent());
		terminalEntities=getAllTerminalStatusOfDto(entities);
       }
    else if(type.equals("NoOfGreenAPPSGreen")){
		entities = terminalStatusRepositoryPaging.findByAplicationStatus(PageRequest.of(page, size),"Green");
		System.out.println("entities::::::::::"+entities.getContent());
		terminalEntities=getAllTerminalStatusOfDto(entities);
       }
    else if(type.equals("NoOfGreyAPPSGrey")){
		entities = terminalStatusRepositoryPaging.findByAplicationStatus(PageRequest.of(page, size),"Grey");
		System.out.println("entities::::::::::"+entities.getContent());
		terminalEntities=getAllTerminalStatusOfDto(entities);
       }
       else{
    	   		entities = terminalStatusRepositoryPaging.findByAplicationStatus(PageRequest.of(page, size),type);
    	   		System.out.println("entities::::::::::"+entities.getContent());
    	   		terminalEntities=getAllTerminalStatusOfDto(entities);
       }
       
		return terminalEntities;
	}
	
	
	public Page<TerminalStatusDto>  getAllTerminalStatusOfDto(Page<TerminalStatusDto> entities){
		
		List<KioskBranchMaster> kioskMastlist = null;
		String cmfNameList = null;
		
		for (TerminalStatusDto dto : entities) {
		    System.out.println("entities terminal status===" + entities.getContent());
			System.out.println("=terminalStatus=====kioskId=======" + dto.getKioskId());
			kioskMastlist = kioskMasterRepo.findByKioskId(dto.getKioskId());
			System.out.println("kioskId=list zise===" + kioskMastlist.size());
            
		  for (KioskBranchMaster kioskBranchMast : kioskMastlist) {
				System.out.println("SRNO=========" + kioskBranchMast.getKioskSerialNo());
				dto.setKioskSrNo(kioskBranchMast.getKioskSerialNo());
				System.out.println("kioskId==:: " + kioskBranchMast.getKioskId());
				dto.setKioskId(kioskBranchMast.getKioskId());
				dto.setBranchCode(kioskBranchMast.getBranchCode());
				
			}
		  
		  System.out.println("dto.getKioskId()-------------"+dto.getKioskId());
		   List<BranchMaster> branchMastList = branchMasterRepo.findAllByBranchCode(dto.getBranchCode());
			 System.out.println("branchMastList==========" + branchMastList.size());
			for (BranchMaster branchMast : branchMastList) {
				System.out.println("Br Code====" + branchMast.getBranchCode());
				System.out.println("Circle====" + branchMast.getCircle());
				dto.setBranchCode(branchMast.getBranchCode());
				dto.setCircle(branchMast.getCircle());
			}
		  
					
		    cmfNameList = terminalStatusRepo.findByKisoskId(dto.getKioskId());
			System.out.println("CMF User==" + cmfNameList);
		    dto.setCmf(cmfNameList);	
			System.out.println("dto::::" + dto);
		}
	return entities;
		
	}
	
	

}
