package sbi.kiosk.swayam.healthmonitoring.repository;

import org.springframework.data.domain.Page;

import sbi.kiosk.swayam.common.dto.DownTimeDto;
import sbi.kiosk.swayam.common.dto.TerminalStatusDto;
import sbi.kiosk.swayam.common.dto.TicketHistoryDto;

public interface IOperations<T> {
	Page<TerminalStatusDto> findPaginated(int page, int size);
	Page<TicketHistoryDto> paginated(int size, int page);
	Page<DownTimeDto> findAllPaginated(int size, int page);

}
