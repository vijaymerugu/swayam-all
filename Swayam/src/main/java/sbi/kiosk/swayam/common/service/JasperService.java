
  package sbi.kiosk.swayam.common.service;
  
  import java.util.List;

import sbi.kiosk.swayam.common.dto.BillingPenaltyDto;
import sbi.kiosk.swayam.common.dto.DownTimeDto;
import sbi.kiosk.swayam.common.dto.DrillDownDto;
import sbi.kiosk.swayam.common.dto.ErrorReportingDto;
import sbi.kiosk.swayam.common.dto.InvoiceCompareDto;
import sbi.kiosk.swayam.common.dto.InvoiceGenerationDto;
import sbi.kiosk.swayam.common.dto.InvoiceSummaryDto;
import
  sbi.kiosk.swayam.common.dto.KioskBranchMasterUserDto; import
  sbi.kiosk.swayam.common.dto.RealTimeTransactionDto;
import sbi.kiosk.swayam.common.dto.TaxCalculationDto;
import sbi.kiosk.swayam.common.dto.TerminalStatusDto;
import
  sbi.kiosk.swayam.common.dto.TicketCentorDto;
import sbi.kiosk.swayam.common.dto.TicketHistoryDto;
import
  sbi.kiosk.swayam.common.dto.TransactionDashBoardDto; import
  sbi.kiosk.swayam.common.dto.UserManagementDto; import
  sbi.kiosk.swayam.common.dto.ZeroTransactionKiosksDto;
import sbi.kiosk.swayam.common.entity.DrillDown;
import sbi.kiosk.swayam.common.entity.TaxEntity;
import sbi.kiosk.swayam.common.entity.TaxSummaryEntity;
  
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
  
  
  List<TransactionDashBoardDto> findAllTransactionSummary();
  
  List<RealTimeTransactionDto> findAllDateWiseRealtimeTxn(String paramDate);
  
  List<ZeroTransactionKiosksDto> findAllZeroTxnKoisk(String fromdate, String
  todate);
  
  List<ErrorReportingDto> findAllErrorReprting(String fromdate, String todate);

	List<DownTimeDto> findDownTimeReport();

	List<TicketHistoryDto> findTicketHistoryReport();

	List<BillingPenaltyDto> findBillingPenaltyReport();

	List<InvoiceGenerationDto> findInvoiceGenerationReport();

	List<InvoiceCompareDto> findInvoiceCompareReport();
	
	List<InvoiceSummaryDto> findInvoiceSummaryReport();
	
	List<TerminalStatusDto> findTerminalStatusReport();
	
	public List<TaxSummaryEntity> findTaxSummaryReport();

	List<TaxEntity> findTaxCalReport();

	List<DrillDown> findAllDrillDown();
	
	
	
  
  }
 