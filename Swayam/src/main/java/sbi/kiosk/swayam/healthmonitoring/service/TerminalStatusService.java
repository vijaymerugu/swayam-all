package sbi.kiosk.swayam.healthmonitoring.service;

import java.util.Map;

import org.springframework.data.domain.Page;

import sbi.kiosk.swayam.common.dto.TerminalStatusDto;

public interface TerminalStatusService{

	Page<TerminalStatusDto> findPaginated(int page, int size);

	Map<String, Integer> findAllCountAgentStatus();

	Page<TerminalStatusDto> findPaginatedCount(int page, int size, String type);

	//Page<TerminalStatusDto> findPaginated(int page, int size, String kioskId);

}
