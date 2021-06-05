package sbi.kiosk.swayam.healthmonitoring.service;

import java.util.Map;

import org.springframework.data.domain.Page;

import sbi.kiosk.swayam.common.dto.TerminalStatusDto;
import sbi.kiosk.swayam.common.dto.TerminalStatusSearchTextDto;

public interface TerminalStatusService{

	Page<TerminalStatusDto> findPaginated(int page, int size);

	Map<String, Integer> findAllCountAgentStatus();

	Page<TerminalStatusDto> findPaginatedCount(int page, int size, String type);

	Page<TerminalStatusSearchTextDto> findPaginatedSearchText(int page, int size, String searchText);

	Page<TerminalStatusSearchTextDto> findPaginatedCount_NQ(int page, int size, String type);

	Page<TerminalStatusSearchTextDto> findPaginatedNew(int page, int size);

	Page<TerminalStatusSearchTextDto> findPaginatedCount_SearchTextNQ(int page, int size, String type,
			String searchText);

	//Page<TerminalStatusDto> findPaginated(int page, int size, String kioskId);

}
