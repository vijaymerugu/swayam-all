package sbi.kiosk.swayam.common.service;

import java.util.List;

import sbi.kiosk.swayam.common.dto.KioskBranchMasterUserDto;
import sbi.kiosk.swayam.common.dto.TicketCentorDto;
import sbi.kiosk.swayam.common.dto.UserManagementDto;

public interface JasperService {
	
	String generateReportPdf(String identifyPage);
	
	String generateReportExcel(String identifyPage);
	
	List<UserManagementDto> findUsersBySA();
	
	List<UserManagementDto> findPaginatedByCircle();
	
	List<KioskBranchMasterUserDto> findKiosksPaginatedByCircle();
	
	List<KioskBranchMasterUserDto> findAllKiosks();
	
	List<TicketCentorDto> findAllTickets();
	
	List<TicketCentorDto> findAllTicketsForCmf();
	
	List<TicketCentorDto> findAllTicketsForCms();
	
	List<TicketCentorDto> findAllTicketsByCircle();

}
