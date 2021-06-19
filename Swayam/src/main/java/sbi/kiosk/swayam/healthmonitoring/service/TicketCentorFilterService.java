package sbi.kiosk.swayam.healthmonitoring.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import sbi.kiosk.swayam.common.dto.CallTypeDto;
import sbi.kiosk.swayam.common.dto.TicketCentorDto;

public interface TicketCentorFilterService extends IOperations<TicketCentorDto> {


	Map<String, Integer> findAllSeverityOfTicketsCount();

	Map<String, Integer> findAllAgeingOfTicketsCount();

	Page<TicketCentorDto> findPaginatedCount(int page, int size, String type);

	Map<String, Object> findAllCategory();


	List<CallTypeDto> findSubCategoryByCategory(String category);

	List<TicketCentorDto> findByCategoryAndSubCategory(String category, String subCategory);

	Map<String,Integer> findAllSeverityOfTicketsCountCMF();
	
	Map<String,Integer> findAllAgeingOfTicketsCountCMF();
	
	Map<String, Object> findAllCategoryCMF();
	
	Map<String,Integer> findAllSeverityOfTicketsCountCMS();
	
	Map<String,Integer> findAllAgeingOfTicketsCountCMS();
	
	Map<String, Object> findAllCategoryCMS();

	Page<TicketCentorDto> findPaginatedCmfSearchText(int page, int size,String searchText);

	Page<TicketCentorDto> findPaginatedCountCmfSearchText(int page, int size, String type, String searchText);

	Page<TicketCentorDto> findPaginatedCmsSearchText(int page, int size,String searchText);

	Page<TicketCentorDto> findPaginatedCountCmsSearchText(int page, int size, String type, String searchText);
	
	
	
		 
}
