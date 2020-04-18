package sbi.kiosk.swayam.healthmonitoring.service;

import java.util.Map;

import org.springframework.data.domain.Page;

import sbi.kiosk.swayam.common.dto.TicketCentorDto;

public interface TicketCentorService extends IOperations<TicketCentorDto> {


	Map<String, Integer> findAllSeverityOfTicketsCount();

	Map<String, Integer> findAllAgeingOfTicketsCount();

	Page<TicketCentorDto> findPaginatedCount(int page, int size, String type);

	Map<String, Object> findAllCategory();

	
		 
}
