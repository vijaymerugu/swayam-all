package sbi.kiosk.swayam.healthmonitoring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import sbi.kiosk.swayam.common.dto.TerminalStatusDto;
import sbi.kiosk.swayam.common.entity.TerminalStatus;

public interface TerminalStatusRepositoryPaging extends PagingAndSortingRepository<TerminalStatus,Integer> {

	Page<TerminalStatusDto> findByCartridgeStatus(Pageable pageable, @Param("type") String type);

	//Page<TerminalStatusDto> findByAntivirusStatus(Pageable pageable, @Param("type") String type);
	Page<TerminalStatusDto> findByAgentStatus(Pageable pageable, @Param("type") String type);
	Page<TerminalStatusDto> findByPbPrinterStatus(Pageable pageable, @Param("type") String type);

	Page<TerminalStatusDto> findByAplicationStatus(Pageable pageable, @Param("type") String type);

	
//public Page<TerminalStatus> findByKioskId(Pageable pageable,String kioskId);
	
	
}
