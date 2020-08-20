package sbi.kiosk.swayam.healthmonitoring.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.common.dto.DownTimeDto;
import sbi.kiosk.swayam.common.dto.TerminalStatusDto;
import sbi.kiosk.swayam.common.dto.TicketHistoryDto;
import sbi.kiosk.swayam.healthmonitoring.repository.DowntimePagingRepository;

@Service
public class DownTimeServiceImpl implements DowntimeService {
	
	Logger logger = LoggerFactory.getLogger(DownTimeServiceImpl.class);
	@Autowired
	DowntimePagingRepository downtimePagingRepo;
	
	
	
	
	

	@Override
	public Page<DownTimeDto> findAllPaginated(int size, int page, String type, String selectedCircelId,
			String selectedVendorId, String selectedCmsCmfId, String selectedFromDateId, String selectedToDateId) {
		Page<DownTimeDto> pageEntity=null;
		try {
			
			
			//pageEntity=downtimePagingRepo.findAll(PageRequest.of(page, size)).map(DownTimeDto::new);
			
			if(selectedCircelId.equals("0") || selectedCircelId.equals("undefined")) {
				selectedCircelId="";	
			}
			
			if(selectedVendorId.equals("0") || selectedVendorId.equals("undefined")) {
				selectedVendorId="";	
			}
			
			
			if(selectedCmsCmfId.equals("0") || selectedCmsCmfId.equals("undefined") || selectedCmsCmfId.length()<=0) {
				selectedCmsCmfId="";	
			}
				
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			Date curDate=new Date();
			String date=sdf.format(curDate);
			if(selectedFromDateId.equals("undefined") || selectedFromDateId.isEmpty()) {
				selectedFromDateId=date;	
			}

			if(selectedToDateId.equals("undefined") || selectedToDateId.isEmpty()) {
				selectedToDateId=date;	
			}
			
			logger.info("selectedCircelId--------1---------"+selectedCircelId);
			logger.info("selectedVendorId--------2---------"+selectedVendorId);
			logger.info("selectedCmsCmfId--------3---------"+selectedCmsCmfId);
			logger.info("selectedFromDateId--------4---------"+selectedFromDateId);
			logger.info("selectedToDateId--------5---------"+selectedToDateId);
			
			pageEntity=downtimePagingRepo.findAllByFilter(selectedToDateId,selectedFromDateId, selectedCircelId, selectedVendorId,
					 selectedCmsCmfId, PageRequest.of(page, size)).map(DownTimeDto::new);
			
			/*
			 * if(pageEntity!=null) {
			 * 
			 * //List<DowntimeModel> list=downtimePagingRepo.findByHolidayDate("01-07-2020",
			 * "10-07-2020"); //logger.info("list:::"+list); for(DownTimeDto
			 * downTimeDto:pageEntity) {
			 * 
			 * String userName=downtimePagingRepo.findByKioskId(downTimeDto.getKisokId());
			 * logger.info("userName:::"+userName); downTimeDto.setCmsCmf(userName); } }
			 */
			
			logger.info("pageEntity:::"+pageEntity);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("DownTimeServiceImpl Exception():::",e,e.getMessage());
		}
		
		return pageEntity;
		}

	
	
	
	
	
	
	

	@Override
	public Page<TerminalStatusDto> findPaginated(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Page<TicketHistoryDto> paginated(int size, int page) {
		// TODO Auto-generated method stub
		return null;
	}





	@Override
	public Page<DownTimeDto> findAllPaginated(int size, int page) {
		// TODO Auto-generated method stub
		return null;
	}



	
	
	
}
