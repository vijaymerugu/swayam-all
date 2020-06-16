package sbi.kiosk.swayam.healthmonitoring.repository;

import org.springframework.data.domain.Page;

import sbi.kiosk.swayam.common.dto.TerminalStatusDto;

public interface IOperations<T> {
	Page<TerminalStatusDto> findPaginated(int page, int size);

}
