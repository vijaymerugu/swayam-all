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
import sbi.kiosk.swayam.healthmonitoring.repository.BranchMasterRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.CallTypeRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.KioskMasterRepo;
import sbi.kiosk.swayam.healthmonitoring.repository.ManualTicketCallLogRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.TicketCentorRepository;

@Service
public class ManualTicketServiceImpl implements ManualTicketService {
	@Autowired
	private TicketCentorRepository ticketCentorRepo;
	@Autowired
	ManualTicketCallLogRepository manualTicketCallLogRepo;
	@Autowired
	private KioskMasterRepo kioskMasterRepo;
	@Autowired
	private BranchMasterRepository branchMasterRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CallTypeRepository callTypeRpository;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String createManualTicket(ManualTicketCallLogDto manualTicketCallLogDto)
			throws ManualTicketNotFoudException {

		String manual_call_log_id = null;
		SimpleDateFormat date = null;
		String complaintId = null;
		try {
			System.out.println("error message" + manualTicketCallLogDto.getKioskError());
			manual_call_log_id = manualTicketCallLogRepo.findSeq();
			System.out.println("squence generated" + manual_call_log_id);
			// String seq = "MANUAL_CALL_LOG_ID" + manual_call_log_id;
			ManualTicketCallLog manualEnity = new ManualTicketCallLog();
			manualEnity.setBranchCode(manualTicketCallLogDto.getBranchCode());
			manualEnity.setCircle(manualTicketCallLogDto.getCircle());
			manualEnity.setManual_call_log_id(manual_call_log_id);
			manualEnity.setKioskId(manualTicketCallLogDto.getKioskId());
			manualEnity.setComments(manualTicketCallLogDto.getComment());
			manualEnity.setContactNo(manualTicketCallLogDto.getContactNo());
			manualEnity.setContactPerson(manualTicketCallLogDto.getContactPerson());
			manualEnity.setStatus(manualTicketCallLogDto.getStatus());
			manualEnity.setVendor(manualTicketCallLogDto.getVendor());
			manualEnity.setKioskError(manualTicketCallLogDto.getKioskError());
			manualTicketCallLogRepo.save(manualEnity);

			String kioskId = ticketCentorRepo.findByKisokId(manualTicketCallLogDto.getKioskId());
			System.out.println("kioskId::" + kioskId);
			String id = ticketCentorRepo.findByTicketId(kioskId);
			if (kioskId != null) {
				if (kioskId.equals(manualTicketCallLogDto.getKioskId())) {
					date = new SimpleDateFormat("dd/MM/YY");
					String date1 = date.format(new Date());
					String createdDate = date1.replace("/", "");
					complaintId = "INC" + createdDate + id;
					manualTicketCallLogRepo.updateComplaintId(date1, complaintId, manualTicketCallLogDto.getKioskId());
					return id;
				}
			} else {
				date = new SimpleDateFormat("dd/MM/YY");
				String date1 = date.format(new Date());
				String createdDate = date1.replace("/", "");
				complaintId = "INC" + createdDate + manual_call_log_id;
				String kisokId = manualTicketCallLogDto.getKioskId();
				manualTicketCallLogRepo.updateComplaintId(date1, complaintId, kisokId);
				return manual_call_log_id;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return complaintId;
	}

	public List<ManualTicketCallLogDto> getByBranchCode(String brachCode) {
		String branchName=null;
		List<String> kioskMasterList = kioskMasterRepo.getByBranchCode(brachCode);
		List<String> kioskMastBranchNameList = branchMasterRepo.findByBranchCode(brachCode);
		for(String branchNames:kioskMastBranchNameList){
			branchName=branchNames;
		}
		List<ManualTicketCallLogDto> listDto = new ArrayList<ManualTicketCallLogDto>();
		ManualTicketCallLogDto dto = null;
		for (String kioskMasterList1 : kioskMasterList) {
			dto = new ManualTicketCallLogDto();
			dto.setVendor(kioskMasterList1);
			dto.setBranchName(branchName);
			listDto.add(dto);
		}
		System.out.println("kioskMasterList size()::::" + listDto.size());
		return listDto;
	}

	// update method 27-03-2020
	public List<ManualTicketCallLogDto> getByKioskId(String kioskId) {
		ManualTicketCallLogDto dto = null;
		List<ManualTicketCallLogDto> dtoList = new ArrayList<ManualTicketCallLogDto>();
		User user = userRepo.findIdByKioskId(kioskId);
		if (user != null) {
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
		CallTypeDto dto = null;
		List<CallTypeDto> dtoList = new ArrayList<CallTypeDto>();
		// Iterable<CallTypeEntity> errorList=callTypeRpository.findAll();
		List<String> list = callTypeRpository.findAllSubCategory();

		for (String list1 : list) {
			dto = new CallTypeDto();
			dto.setSubCategory(list1);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public List<ManualTicketCallLogDto> getByVendor(String vendor, String branchcode) {
		List<KioskBranchMaster> kioskMasterList = kioskMasterRepo.findByVendor(vendor, branchcode);
		List<ManualTicketCallLogDto> manualTicketCallLogDtoList = new ArrayList<ManualTicketCallLogDto>();
		ManualTicketCallLogDto dto = null;
		for (KioskBranchMaster kioskMaster : kioskMasterList) {
			dto = new ManualTicketCallLogDto();
			dto.setKioskId(kioskMaster.getKioskId());
			dto.setVendor(kioskMaster.getVendor());
			manualTicketCallLogDtoList.add(dto);
		}
		System.out.println("kioskMasterList size()::::" + kioskMasterList.size());
		return manualTicketCallLogDtoList;

	}

}