package sbi.kiosk.swayam.healthmonitoring.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sbi.kiosk.swayam.common.dto.CallTypeDto;
import sbi.kiosk.swayam.common.dto.ManualTicketCallLogDto;
import sbi.kiosk.swayam.common.entity.KioskBranchMaster;
import sbi.kiosk.swayam.common.entity.ManualTicketCallLog;
import sbi.kiosk.swayam.common.entity.User;
import sbi.kiosk.swayam.common.exception.ManualTicketNotFoudException;
import sbi.kiosk.swayam.common.repository.UserRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.CallTypeRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.KioskMasterRepo;
import sbi.kiosk.swayam.healthmonitoring.repository.ManualTicketCallLogRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.TicketCentorRepository;
import sbi.kiosk.swayam.kioskmanagement.repository.UserKioskMappingRepository;

@Service
public class ManualTicketServiceImpl implements ManualTicketService {
	@Autowired
	private TicketCentorRepository manualRepo;
	@Autowired
	ManualTicketCallLogRepository manualTicketCallLogRepo;
	@Autowired
	private KioskMasterRepo kioskMasterRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserKioskMappingRepository userKioskMappingRepo;

	@Autowired
	private CallTypeRepository callTypeRpository;

	/*@Override
	public List<ManualTicketCallLogDto> getAllManualTicketCallLog(String brachCode) {
		System.out.println("Branch code::" + brachCode);
		List<KioskMaster> kioskId = kioskMasterRepo.findByBranchCode(brachCode);
		Iterable<CallType> errorList=callTypeRpository.findAll();
		System.out.println("size of kioskID:::" + kioskId.size());
		List<ManualTicketCallLogDto> dtoList = new ArrayList<ManualTicketCallLogDto>();
		User user = null;
		ManualTicketCallLogDto dto = null;
		
		for (KioskMaster kioskId1 : kioskId) {
			System.out.println("kioskId1::" + kioskId1.getKioskId());
			Iterable<UserKioskMapping> userMappingEntity = null;
			if (kioskId1.getBranchCode().equalsIgnoreCase(brachCode))
				userMappingEntity = userKioskMappingRepo.findByKioskId(kioskId1.getKioskId());
			
			for (UserKioskMapping userMapping : userMappingEntity) {
				System.out.println("user name  " + userMapping.getUsername());
				if (userMapping.getKioskId().equalsIgnoreCase(kioskId1.getKioskId()))
					user = userRepo.findByUsername(userMapping.getUsername());
				
				if (user!=null) {
					dto = new ManualTicketCallLogDto();
					dto.setKioskId(kioskId1.getKioskId());
					dto.setVendor(kioskId1.getVendor());
					dto.setCircle(user.getCity());
					dto.setContactPerson(userMapping.getUsername());
					dto.setContactNo(user.getMobileNo());
					for(CallType callTypeErrorList:errorList) {
					dto = new ManualTicketCallLogDto();
					dto.setKioskError(callTypeErrorList.getCategory());
					}
					dtoList.add(dto);
					System.out.println("size of dto:: " + dtoList.size());
				}

			}
		}
		System.out.println("findal size of list" + dtoList.size());
		return dtoList;
	}*/

	@Override
	@Transactional(rollbackFor=Exception.class)
	public String createManualTicket(ManualTicketCallLogDto manualTicketCallLogDto)
			throws ManualTicketNotFoudException {
		
		String manual_call_log_id = null;
		SimpleDateFormat date = null;
		String complaintId=null;
		try {
               System.out.println("error message"+manualTicketCallLogDto.getKioskError());
			manual_call_log_id = manualTicketCallLogRepo.findSeq();
			System.out.println("squence generated" + manual_call_log_id);
			String seq = "MANUAL_CALL_LOG_ID" + manual_call_log_id;
			ManualTicketCallLog manualEnity = new ManualTicketCallLog();
			manualEnity.setBranchCode(manualTicketCallLogDto.getBranchCode());
			manualEnity.setCircle(manualTicketCallLogDto.getCircle());
			manualEnity.setManual_call_log_id(seq);
			manualEnity.setKioskId(manualTicketCallLogDto.getKioskId());
			manualEnity.setAgenetStatus(manualTicketCallLogDto.getComment());
			manualEnity.setContactNo(manualTicketCallLogDto.getContactNo());
			manualEnity.setContactPerson(manualTicketCallLogDto.getContactPerson());
			manualEnity.setStatus(manualTicketCallLogDto.getStatus());
			manualEnity.setVendor(manualTicketCallLogDto.getVendor());
			manualEnity.setKioskError(manualTicketCallLogDto.getKioskError());
			manualTicketCallLogRepo.save(manualEnity);
			
		String	kioskId = manualRepo.findByKisokId(manualTicketCallLogDto.getKioskId());
		System.out.println("kioskId::"+kioskId);
		String id=manualRepo.findByTicketId(kioskId);	
		        if(kioskId !=null) {
				if (kioskId.equals(manualTicketCallLogDto.getKioskId())) {
				  date=new SimpleDateFormat("dd/MM/YY");
				  String date1 = date.format(new Date());
				  String createdDate=date1.replace("/","");
				  complaintId="INC"+createdDate+id;
				  manualTicketCallLogRepo.updateComplaintId(date1, complaintId, manualTicketCallLogDto.getKioskId());
				  return id;
				  }
		        }
				else {
					date=new SimpleDateFormat("dd/MM/YY");
					  String date1 = date.format(new Date());
					  String createdDate=date1.replace("/","");
					  complaintId="INC"+createdDate+manual_call_log_id;
					  String kisokId=manualTicketCallLogDto.getKioskId();
					  manualTicketCallLogRepo.updateComplaintId(date1,complaintId,kisokId);
					  return manual_call_log_id;
					  
				}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	    	return complaintId;	
	}

	public List<ManualTicketCallLogDto> getByBranchCode(String brachCode) {
		List<String> kioskMasterList = kioskMasterRepo.getByBranchCode(brachCode);
		List<ManualTicketCallLogDto> listDto=new ArrayList<ManualTicketCallLogDto>();
		ManualTicketCallLogDto dto=null;
		for(String kioskMasterList1:kioskMasterList) {
			dto= new ManualTicketCallLogDto();
			dto.setVendor(kioskMasterList1);
			listDto.add(dto);
		}
		System.out.println("kioskMasterList size()::::" + listDto.size());
		return listDto;
	}

	// update method 27-03-2020
	public List<ManualTicketCallLogDto> getByKioskId(String kioskId) {
		ManualTicketCallLogDto dto = null;
		List<ManualTicketCallLogDto> dtoList = new ArrayList<ManualTicketCallLogDto>();
		//User user = userRepo.findByKioskId(kioskId);
		User user = new User();
		if (user!=null) {
			dto = new ManualTicketCallLogDto();
			dto.setCircle(user.getCircle());
			dto.setContactPerson(user.getUsername());
			dto.setContactNo(user.getPhoneNo());
			dto.setKioskError("Printer Error");
			dtoList.add(dto);
			System.out.println("kiosk id:" + dto.getKioskId());
			System.out.println("kiosk id:" + dto.getKioskError());
			System.out.println("vendor :" + dto.getVendor());
			System.out.println("circle :" + dto.getCircle());
			System.out.println("username :" + dto.getContactPerson());
			System.out.println("contact:" + dto.getContactNo());
			
			System.out.println("size of dto:: " + dtoList.size());
		}

		return dtoList;
	}

	@Override
	public List<CallTypeDto> getCallTypeCategoryError() {
		CallTypeDto dto=null;
		List<CallTypeDto> dtoList = new ArrayList<CallTypeDto>();
		//Iterable<CallTypeEntity> errorList=callTypeRpository.findAll();
		List<String> list = callTypeRpository.findAllSubCategory();
		   
		for(String list1:list) {
			   dto=new CallTypeDto();
			   dto.setSubCategory(list1);
			   dtoList.add(dto);
		   }
		return dtoList;
	}

	@Override
	public List<ManualTicketCallLogDto> getByVendor(String vendor,String branchcode) {
		List<KioskBranchMaster> kioskMasterList = kioskMasterRepo.findByVendor(vendor,branchcode);
		List<ManualTicketCallLogDto> manualTicketCallLogDtoList=new ArrayList<ManualTicketCallLogDto>();
		ManualTicketCallLogDto dto=null;
		for(KioskBranchMaster kioskMaster:kioskMasterList) {
			dto=new ManualTicketCallLogDto();
			dto.setKioskId(kioskMaster.getKioskId());
			dto.setVendor(kioskMaster.getVendor());
			manualTicketCallLogDtoList.add(dto);
		}
		System.out.println("kioskMasterList size()::::" + kioskMasterList.size());
		return manualTicketCallLogDtoList;

	}

}