package sbi.kiosk.swayam.common.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import sbi.kiosk.swayam.billingpayment.repository.BillingPenaltyRepository;
import sbi.kiosk.swayam.billingpayment.repository.InvoiceCompareRepository;
import sbi.kiosk.swayam.billingpayment.repository.InvoiceGenerationRepository;
import sbi.kiosk.swayam.billingpayment.repository.InvoiceSummaryRepository;
import sbi.kiosk.swayam.common.constants.Constants;
import sbi.kiosk.swayam.common.dto.BillingPenaltyDto;
import sbi.kiosk.swayam.common.dto.DownTimeDto;
import sbi.kiosk.swayam.common.dto.ErrorReportingDto;
import sbi.kiosk.swayam.common.dto.InvoiceCompareDto;
import sbi.kiosk.swayam.common.dto.InvoiceGenerationDto;
import sbi.kiosk.swayam.common.dto.InvoiceSummaryDto;
import sbi.kiosk.swayam.common.dto.KioskBranchMasterUserDto;
import sbi.kiosk.swayam.common.dto.RealTimeTransactionDto;
import sbi.kiosk.swayam.common.dto.TicketCentorDto;
import sbi.kiosk.swayam.common.dto.TicketHistoryDto;
import sbi.kiosk.swayam.common.dto.TransactionDashBoardDto;
import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.dto.UserManagementDto;
import sbi.kiosk.swayam.common.dto.ZeroTransactionKiosksDto;
import sbi.kiosk.swayam.common.entity.BillingPenaltyEntity;
import sbi.kiosk.swayam.common.entity.BranchMaster;
import sbi.kiosk.swayam.common.entity.DateFrame;
import sbi.kiosk.swayam.common.entity.DownTime;
import sbi.kiosk.swayam.common.entity.ErrorReporting;
import sbi.kiosk.swayam.common.entity.InvoiceCompare;
import sbi.kiosk.swayam.common.entity.InvoiceGeneration;
import sbi.kiosk.swayam.common.entity.InvoiceSummaryEntity;
import sbi.kiosk.swayam.common.entity.RealTimeTransaction;
import sbi.kiosk.swayam.common.entity.Supervisor;
import sbi.kiosk.swayam.common.entity.SwayamMigrationSummary;
import sbi.kiosk.swayam.common.entity.TicketHistory;
import sbi.kiosk.swayam.common.entity.User;
import sbi.kiosk.swayam.common.entity.UserKioskMapping;
import sbi.kiosk.swayam.common.entity.ZeroTransactionKiosks;
import sbi.kiosk.swayam.common.repository.KioskMasterRepository;
import sbi.kiosk.swayam.common.repository.SupervisorRepository;
import sbi.kiosk.swayam.common.repository.UserRepository;
import sbi.kiosk.swayam.common.repository.UserRepositoryPaging;
import sbi.kiosk.swayam.common.utils.ObjectMapperUtils;
import sbi.kiosk.swayam.healthmonitoring.model.BillingPaymentReport;
import sbi.kiosk.swayam.healthmonitoring.model.DowntimeReport;
import sbi.kiosk.swayam.healthmonitoring.model.TicketHistoryReport;
import sbi.kiosk.swayam.healthmonitoring.repository.DowntimePagingRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.TicketCentorRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.TicketHistoryPagingRepository;
import sbi.kiosk.swayam.kioskmanagement.repository.BranchMasterRepository;
import sbi.kiosk.swayam.kioskmanagement.repository.UserKioskMappingRepository;
import sbi.kiosk.swayam.transactiondashboard.repository.ErrorReportingRepositoryPaging;
import sbi.kiosk.swayam.transactiondashboard.repository.RealTimeTxnRepositoryPaging;
import sbi.kiosk.swayam.transactiondashboard.repository.TransactionDashBoardRepositoryPaging;
import sbi.kiosk.swayam.transactiondashboard.repository.ZeroTransactionKiosksRepository;

@Service
public class JasperServiceImpl implements JasperService {

	Logger logger = LoggerFactory.getLogger(JasperServiceImpl.class);

	@Value("${report.path}")
	private String reportPath;

	@Value("${jrxml.path}")
	private String jrxmlPath;

	@Autowired
	UserRepository userRepo;

	@Autowired
	UserRepositoryPaging userRepositoryPagingRepo;

	@Autowired
	UserKioskMappingRepository userKioskMappingRepository;

	@Autowired
	SupervisorRepository supervisorRepository;

	@Autowired
	KioskMasterRepository kioskMasterRepository;

	@Autowired
	BranchMasterRepository branchMasterRepository;

	@Autowired
	TicketCentorRepository ticketCentorRepo;

	@Autowired
	KioskMasterRepository kioskMasterRepo;

	@Autowired
	TransactionDashBoardRepositoryPaging transactionDashBoardRepositoryPaging;

	@Autowired
	RealTimeTxnRepositoryPaging realTimeTxnRepositoryPaging;

	@Autowired
	ZeroTransactionKiosksRepository zeroTransactionKiosksRepo;

	@Autowired
	ErrorReportingRepositoryPaging errorReportingRepositoryPaging;
	
	 @Autowired DateFrame dateFrame;
	 
    @Autowired
	TicketHistoryReport ticketHistoryReport;
	@Autowired
	TicketHistoryPagingRepository ticketHistoryPagingRepo;
	@Autowired
	DowntimeReport downtimeReport;
	@Autowired
	DowntimePagingRepository downTimePagingRepo;
	
	
	 @Autowired
	 BillingPaymentReport report;
	 
	 
	 @Autowired
	 BillingPenaltyRepository bpRepository;
	 
	 @Autowired
	 InvoiceGenerationRepository IgRepository;
	 
	 @Autowired
	 InvoiceCompareRepository icRepository;
	
	 @Autowired
	 InvoiceSummaryRepository isRepository;

	public static HttpSession session() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession(true); // true == allow create
	}

	@Override
	public String generateReportPdf(String identifyPage) {
		JasperReport jasperReport = null;
		JRBeanCollectionDataSource source = null;
		JasperPrint jasperPrint = null;
		String filename = null;

		try {
			jrxmlPath = jrxmlPath.replaceAll(">", "");
			reportPath = reportPath.replaceAll(">", "");
		//	logger.info("jrxmlPath " + jrxmlPath);
		//	logger.info("reportPath " + reportPath);
			if (identifyPage.equals("userListSA")) {
				List<UserManagementDto> list = findUsersBySA();
				File file = ResourceUtils.getFile(jrxmlPath + "usersListSA.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "UserList_" + timeStamp + ".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
			} else if (identifyPage.equals("userListLA")) {
				List<UserManagementDto> list = findPaginatedByCircle();
				File file = ResourceUtils.getFile(jrxmlPath + "userListLA.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "UserList_" + timeStamp + ".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
			} else if (identifyPage.equals("kioskManagementByCircle")) {
				List<KioskBranchMasterUserDto> list = findKiosksPaginatedByCircle();
				File file = ResourceUtils.getFile(jrxmlPath + "kioskManagementByCircle.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "Kiosks_" + timeStamp + ".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
			} else if (identifyPage.equals("kiosksAll")) {
				List<KioskBranchMasterUserDto> list = findAllKiosks();
				File file = ResourceUtils.getFile(jrxmlPath + "kioskManagementByCircle.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "Kiosks_" + timeStamp + ".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
			} else if (identifyPage.equals("ticketCenterCC")) {
				List<TicketCentorDto> list = findAllTickets();
				File file = ResourceUtils.getFile(jrxmlPath + "ticketCenter.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "TicketCenter_" + timeStamp + ".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
			} else if (identifyPage.equals("ticketCenterCMF")) {
				List<TicketCentorDto> list = findAllTicketsForCmf();
				File file = ResourceUtils.getFile(jrxmlPath + "ticketCenterCMF.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "TicketCenter_" + timeStamp + ".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
			} else if (identifyPage.equals("ticketCenterCMS")) {
				List<TicketCentorDto> list = findAllTicketsForCms();
				File file = ResourceUtils.getFile(jrxmlPath + "ticketCenterCMF.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "TicketCenter_" + timeStamp + ".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
			} else if (identifyPage.equals("ticketCenterCU")) {
				List<TicketCentorDto> list = findAllTicketsByCircle();
				File file = ResourceUtils.getFile(jrxmlPath + "ticketCenter.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "TicketCenter_" + timeStamp + ".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
			}

			else if (identifyPage.equals("transactionSummary")) {
				logger.info("PDF File TransactionSummary !!");
				List<TransactionDashBoardDto> list = findAllTransactionSummary();
				File file = ResourceUtils.getFile(jrxmlPath + "transactionSummary.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "TransactionSummary_" + timeStamp + ".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
			}else if(identifyPage.equals("bpReport")) {
				logger.info("PDF File bpReport !!");
				
				List<BillingPenaltyDto> list = findBillingPenaltyReport();
				
				if(list.isEmpty()) {
					
					
					return filename;
				}else {
					
				
				File file = ResourceUtils.getFile(jrxmlPath + "bpPenalty.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "BillingPenalty_" + timeStamp + ".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
				}
			}else if(identifyPage.equals("invoiceReport")) {
				logger.info("PDF File InvoiceGenarationReport !!");
				
				
				List<InvoiceGenerationDto> list = findInvoiceGenerationReport();
				if(list.isEmpty()) {
					
					
					return filename;
				}else {
				File file = ResourceUtils.getFile(jrxmlPath + "invoiceReport.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "InvoiceGeneration_" + timeStamp + ".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
				}
			}
			
			else if(identifyPage.equals("invoiceCompareReport")) {
				logger.info("PDF File InvoiceCompareReport !!");
				List<InvoiceCompareDto> list = findInvoiceCompareReport();
				if(list.isEmpty()) {
					
					
					return filename;
				}else {
				
				
				File file = ResourceUtils.getFile(jrxmlPath + "invoiceCompareReport.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "InvoiceCompare_" + timeStamp + ".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
				}
			}else if(identifyPage.equals("invoiceSummaryReport")) {
				logger.info("PDF File InvoiceSummaryReport !!");
				List<InvoiceSummaryDto> list = findInvoiceSummaryReport();
				if(list.isEmpty()) {
					
					
					return filename;
				}else {
				
				
				File file = ResourceUtils.getFile(jrxmlPath + "invoiceSummary.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "InvoiceSummary_" + timeStamp + ".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
				}
			}

			else if (identifyPage.equals("realTimeToday")) {
				logger.info("PDF File realTimeToday !!");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date curDate = new Date();
				String todayDate = sdf.format(curDate);
				List<RealTimeTransactionDto> list = findAllDateWiseRealtimeTxn(todayDate);
				File file = ResourceUtils.getFile(jrxmlPath + "realTimeToday.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "RealTimeTxnToday_" + timeStamp + ".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
			} else if (identifyPage.equals("realTimeYesterday")) {
				logger.info("PDF File RealTimeYesterday !!");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date curDate = new Date();
				curDate.setTime(curDate.getTime() - 24 * 60 * 60 * 1000);
				String yesterdayDate = sdf.format(curDate);

				List<RealTimeTransactionDto> list = findAllDateWiseRealtimeTxn(yesterdayDate);
				File file = ResourceUtils.getFile(jrxmlPath + "realTimeYesterday.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "RealTimeTxnYesterday_" + timeStamp + ".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
			}

			else if (identifyPage.equals("zeroTxnKoisk")) {
				logger.info("PDF File ZeroTxnKoisk !!");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date curDate = new Date();
				String fromdate = sdf.format(curDate);
				String todate = sdf.format(curDate);

				List<ZeroTransactionKiosksDto> list = findAllZeroTxnKoisk(fromdate, todate);
				File file = ResourceUtils.getFile(jrxmlPath + "zeroTxnKiosk.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "ZeroTxnKoisk_" + timeStamp + ".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
			} else if (identifyPage.equals("errorReporting")) {
				logger.info("PDF File ErrorReporting !!");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date curDate = new Date();
				String fromdate = sdf.format(curDate);
				String todate = sdf.format(curDate);

				List<ErrorReportingDto> list = findAllErrorReprting(fromdate, todate);
				File file = ResourceUtils.getFile(jrxmlPath + "errorReporting.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "ErrorReporting_" + timeStamp + ".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
			}
			else if (identifyPage.equals("tiketHistory")) {
				logger.info("PDF File tiketHistory !!");

				List<TicketHistoryDto> list = findTicketHistoryReport();
				File file = ResourceUtils.getFile(jrxmlPath + "ticketHistory.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "TicketHistory_" + timeStamp + ".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
			}
			else if (identifyPage.equals("downTime")) {
				logger.info("PDF File DownTime !!");

				List<DownTimeDto> list = findDownTimeReport();
				File file = ResourceUtils.getFile(jrxmlPath + "downTime.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "DownTime_" + timeStamp + ".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
			}
			 

			// xlsx(jasperPrint);
			logger.info("PDF File Generated !!");
			return filename;

		} catch (Exception e) {
			logger.error("Exception is " + e.getMessage());
			return e.getMessage();

		}

	}

	@Override
	public String generateReportExcel(String identifyPage) {
		JasperReport jasperReport = null;
		JRBeanCollectionDataSource source = null;
		JasperPrint jasperPrint = null;
		String filename = null;

		try {
		//	logger.info("jrxmlPath " + jrxmlPath);
		//	logger.info("reportPath " + reportPath);
			if (identifyPage.equals("userListSA")) {
				List<UserManagementDto> list = findUsersBySA();
				File file = ResourceUtils.getFile(jrxmlPath + "usersListSA.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "UserList_" + timeStamp + ".xlsx";
				xlsx(jasperPrint, filename);
			} else if (identifyPage.equals("userListLA")) {
				List<UserManagementDto> list = findPaginatedByCircle();
				File file = ResourceUtils.getFile(jrxmlPath + "userListLA.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "UserList_" + timeStamp + ".xlsx";
				xlsx(jasperPrint, filename);
			} else if (identifyPage.equals("kioskManagementByCircle")) {
				List<KioskBranchMasterUserDto> list = findKiosksPaginatedByCircle();
				File file = ResourceUtils.getFile(jrxmlPath + "kioskManagementByCircle.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "Kiosks_" + timeStamp + ".xlsx";
				xlsx(jasperPrint, filename);
			} else if (identifyPage.equals("kiosksAll")) {
				List<KioskBranchMasterUserDto> list = findAllKiosks();
				File file = ResourceUtils.getFile(jrxmlPath + "kioskManagementByCircle.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "Kiosks_" + timeStamp + ".xlsx";
				xlsx(jasperPrint, filename);
			} else if (identifyPage.equals("ticketCenterCC")) {
				List<TicketCentorDto> list = findAllTickets();
				File file = ResourceUtils.getFile(jrxmlPath + "ticketCenter.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "TicketCenter_" + timeStamp + ".xlsx";
				xlsx(jasperPrint, filename);
			} else if (identifyPage.equals("ticketCenterCMF")) {
				List<TicketCentorDto> list = findAllTicketsForCmf();
				File file = ResourceUtils.getFile(jrxmlPath + "ticketCenterCMF.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "TicketCenter_" + timeStamp + ".xlsx";
				xlsx(jasperPrint, filename);
			} else if (identifyPage.equals("ticketCenterCMS")) {
				List<TicketCentorDto> list = findAllTicketsForCms();
				File file = ResourceUtils.getFile(jrxmlPath + "ticketCenterCMF.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "TicketCenter_" + timeStamp + ".xlsx";
				xlsx(jasperPrint, filename);
			} else if (identifyPage.equals("ticketCenterCU")) {
				List<TicketCentorDto> list = findAllTicketsByCircle();
				File file = ResourceUtils.getFile(jrxmlPath + "ticketCenter.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "TicketCenter_" + timeStamp + ".xlsx";
				xlsx(jasperPrint, filename);
			}

			else if (identifyPage.equals("transactionSummary")) {
				logger.info("Excel File TransactionSummary !!");
				List<TransactionDashBoardDto> list = findAllTransactionSummary();
				File file = ResourceUtils.getFile(jrxmlPath + "transactionSummary.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "TransactionSummary_" + timeStamp + ".xlsx";
				xlsx(jasperPrint, filename);
			}else if(identifyPage.equals("bpReport")) {
				logger.info("Excel File bpReport !!");
				
				List<BillingPenaltyDto> list = findBillingPenaltyReport();
				if(list.isEmpty()) {
					
					
					return filename;
				}else {
				File file = ResourceUtils.getFile(jrxmlPath + "bpPenalty.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "BillingPenalty_" + timeStamp + ".xlsx";
				xlsx(jasperPrint, filename);
				}
			}else if(identifyPage.equals("invoiceReport")) {
				logger.info("Excel File InvoiceGenarationReport !!");
				
				List<InvoiceGenerationDto> list = findInvoiceGenerationReport();
				if(list.isEmpty()) {
					
					
					return filename;
				}else {
				File file = ResourceUtils.getFile(jrxmlPath + "invoiceReport.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "InvoiceGeneration_" + timeStamp + ".xlsx";
				xlsx(jasperPrint, filename);
				}
			}
			else if(identifyPage.equals("invoiceCompareReport")) {
				logger.info("Excel File InvoiceCompareReport !!");
				
				
				List<InvoiceCompareDto> list = findInvoiceCompareReport();
				if(list.isEmpty()) {
					
					
					return filename;
				}else {
				File file = ResourceUtils.getFile(jrxmlPath + "invoiceCompareReport.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "InvoiceCompare_" + timeStamp + ".xlsx";
				xlsx(jasperPrint, filename);
			}
			}else if(identifyPage.equals("invoiceSummaryReport")) {
				logger.info("Excel File InvoiceSummaryReport !!");
				
				
				List<InvoiceSummaryDto> list = findInvoiceSummaryReport();
				if(list.isEmpty()) {
					
					
					return filename;
				}else {
				File file = ResourceUtils.getFile(jrxmlPath + "invoiceSummary.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "InvoiceSummary_" + timeStamp + ".xlsx";
				xlsx(jasperPrint, filename);
				}
			}

			else if (identifyPage.equals("realTimeToday")) {
				logger.info("Excel File realTimeToday !!");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date curDate = new Date();
				String todayDate = sdf.format(curDate);
				List<RealTimeTransactionDto> list = findAllDateWiseRealtimeTxn(todayDate);
				File file = ResourceUtils.getFile(jrxmlPath + "realTimeToday.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "RealTimeTxnToday_" + timeStamp + ".xlsx";
				xlsx(jasperPrint, filename);
			} else if (identifyPage.equals("realTimeYesterday")) {
				logger.info("Excel File RealTimeYesterday !!");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date curDate = new Date();
				curDate.setTime(curDate.getTime() - 24 * 60 * 60 * 1000);
				String yesterdayDate = sdf.format(curDate);
				logger.info("Excel File RealTimeYesterday yesterdayDate::" + yesterdayDate);

				List<RealTimeTransactionDto> list = findAllDateWiseRealtimeTxn(yesterdayDate);
				File file = ResourceUtils.getFile(jrxmlPath + "realTimeYesterday.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "RealTimeTxnYesterday_" + timeStamp + ".xlsx";
				xlsx(jasperPrint, filename);
			} else if (identifyPage.equals("zeroTxnKoisk")) {
				logger.info("Excel File ZeroTxnKoisk !!");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date curDate = new Date();
				String fromdate = sdf.format(curDate);
				String todate = sdf.format(curDate);

				List<ZeroTransactionKiosksDto> list = findAllZeroTxnKoisk(fromdate, todate);
				File file = ResourceUtils.getFile(jrxmlPath + "zeroTxnKiosk.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "ZeroTxnKoisk_" + timeStamp + ".xlsx";
				xlsx(jasperPrint, filename);
			} else if (identifyPage.equals("errorReporting")) {
				logger.info("PDF File ErrorReporting !!");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date curDate = new Date();
				String fromdate = sdf.format(curDate);
				String todate = sdf.format(curDate);

				List<ErrorReportingDto> list = findAllErrorReprting(fromdate, todate);
				File file = ResourceUtils.getFile(jrxmlPath + "errorReporting.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "ErrorReporting_" + timeStamp + ".xlsx";
				xlsx(jasperPrint, filename);
			}
			
			else if (identifyPage.equals("tiketHistory")) {
				logger.info("PDF File tiketHistory !!");

				List<TicketHistoryDto> list = findTicketHistoryReport();
				File file = ResourceUtils.getFile(jrxmlPath + "ticketHistory.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "TicketHistory_" + timeStamp + ".xlsx";
				xlsx(jasperPrint, filename);
			}
			else if (identifyPage.equals("downTime")) {
				logger.info("Excel File downTime !!");

				List<DownTimeDto> list = findDownTimeReport();
				File file = ResourceUtils.getFile(jrxmlPath + "downTime.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "DownTime_" + timeStamp + ".xlsx";
				xlsx(jasperPrint, filename);
			}
			
			logger.info("Excel File Generated !!");
			return filename;

		} catch (Exception e) {
			logger.error("Exception is " + e.getMessage());
			return e.getMessage();

		}

	}

	private void xlsx(JasperPrint jasperPrint, String filename) throws JRException {

		// Exports a JasperReports document to XLSX format. It has character output type
		// and exports the document to a grid-based layout.

		JRXlsxExporter exporter = new JRXlsxExporter();

		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(reportPath + filename));

		SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();

		// configuration.setOnePagePerSheet(true);

		configuration.setRemoveEmptySpaceBetweenColumns(true);

		configuration.setDetectCellType(true);

		exporter.setConfiguration(configuration);

		exporter.exportReport();

	}

	@Override
	public List<UserManagementDto> findUsersBySA() {
		logger.info("Inside==Jasper====findUsersBySA===========");
		List<UserManagementDto> entities = ObjectMapperUtils.mapAll(userRepo.findByEnabled("1"),
				UserManagementDto.class);
		if (entities != null) {
			for (UserManagementDto dto : entities) {
				if (Constants.SYSTEMADMIN.getCode().equals(dto.getRole())) {
					dto.setRole(Constants.SYSTEMADMIN.getValue());
				}
				if (Constants.LOCALADMIN.getCode().equals(dto.getRole())) {
					dto.setRole(Constants.LOCALADMIN.getValue());
				}
				if (Constants.CIRCLE.getCode().equals(dto.getRole())) {
					dto.setRole(Constants.CIRCLE.getValue());
				}
				if (dto.getPfId() != null && dto.getPfId() != "" && dto.getRole().equals("CMF")) {
					int kioskCountCmf = userKioskMappingRepository.findKiosksCountByPfId(dto.getPfId());
					dto.setNoOfAssignedKiosks(String.valueOf(kioskCountCmf));
				} else if (dto.getPfId() != null && dto.getPfId() != "" && dto.getRole().equals("CMS")) {
					int total = 0;
					List<Supervisor> supList = supervisorRepository.findBySupervisorPfId(dto.getPfId());

					if (supList != null && supList.size() > 0) {
						for (Supervisor sup : supList) {
							int kioskCountCmf = userKioskMappingRepository.findKiosksCountByPfId(sup.getPfId());
							total = total + kioskCountCmf;
						}
					}
					if (total != 0) {
						dto.setNoOfAssignedKiosks(String.valueOf(total));
					}

				}
			}
		}
		return entities;
	}

	@Override
	public List<UserManagementDto> findPaginatedByCircle() {
		logger.info("Inside==Jasper====findPaginatedByCircle===========");
		// List<UserManagementDto> userManaDTOList=new ArrayList<UserManagementDto>();
		// Page<User> userList = userRepositoryPagingRepo.findAll(PageRequest.of(page,
		// size));
		UserDto user = (UserDto) session().getAttribute("userObj");
		List<String> roleList = new ArrayList<String>();
		// roleList.add("LA");
		roleList.add("SA");
		roleList.add("CC");

		List<UserManagementDto> entities = ObjectMapperUtils.mapAll(
				userRepo.findByCircleAndEnabledAndRoleNotIn(user.getCircle(), "1", roleList), UserManagementDto.class);

		if (entities != null) {
			for (UserManagementDto dto : entities) {
				if (Constants.LOCALADMIN.getCode().equals(dto.getRole())) {
					dto.setRole(Constants.LOCALADMIN.getValue());
				}
				if (Constants.CIRCLE.getCode().equals(dto.getRole())) {
					dto.setRole(Constants.CIRCLE.getValue());
				}
				if (dto.getPfId() != null && dto.getPfId() != "" && dto.getRole().equals("CMF")) {
					int kioskCountCmf = userKioskMappingRepository.findKiosksCountByPfId(dto.getPfId());
					dto.setNoOfAssignedKiosks(String.valueOf(kioskCountCmf));
				} else if (dto.getPfId() != null && dto.getPfId() != "" && dto.getRole().equals("CMS")) {
					int total = 0;
					List<Supervisor> supList = supervisorRepository.findBySupervisorPfId(dto.getPfId());

					if (supList != null && supList.size() > 0) {
						for (Supervisor sup : supList) {
							int kioskCountCmf = userKioskMappingRepository.findKiosksCountByPfId(sup.getPfId());
							total = total + kioskCountCmf;
						}
					}
					if (total != 0) {
						dto.setNoOfAssignedKiosks(String.valueOf(total));
					}

				}
			}
		}
		return entities;
	}

	@Override
	public List<KioskBranchMasterUserDto> findKiosksPaginatedByCircle() {
		logger.info("Inside==Jasper====findKiosksPaginatedByCircle===========");
		UserDto user = (UserDto) session().getAttribute("userObj");

		List<KioskBranchMasterUserDto> entities = ObjectMapperUtils
				.mapAll(kioskMasterRepository.findAllByCircle(user.getCircle()), KioskBranchMasterUserDto.class);

		for (KioskBranchMasterUserDto dto : entities) {
			UserKioskMapping us = userKioskMappingRepository.findByKioskId(String.valueOf(dto.getKioskId()));

			if (us != null && us.getPfId() != null && us.getPfId() != "") {
				dto.setPfId(us.getPfId());
				User usr = userRepo.findByPfId(us.getPfId());
				if (usr != null && usr.getUsername() != null && usr.getUsername() != "") {
					dto.setUsername(usr.getUsername());
				}
			}

			if (dto.getBranchCode() != null) {
				BranchMaster branchMaster = branchMasterRepository.findByBranchCode(dto.getBranchCode());
				if (branchMaster != null && branchMaster.getCircle() != null && branchMaster.getCircle() != "") {
					dto.setCircle(branchMaster.getCircle());
				}
			}
		}
		return entities;
	}

	@Override
	public List<KioskBranchMasterUserDto> findAllKiosks() {
		logger.info("Inside==Jasper====findAllKiosks===========");
		List<KioskBranchMasterUserDto> entities = ObjectMapperUtils.mapAll(kioskMasterRepository.findAll(),
				KioskBranchMasterUserDto.class);

		for (KioskBranchMasterUserDto dto : entities) {
			UserKioskMapping us = userKioskMappingRepository.findByKioskId(String.valueOf(dto.getKioskId()));

			if (us != null && us.getPfId() != null && us.getPfId() != "") {
				dto.setPfId(us.getPfId());
				User usr = userRepo.findByPfId(us.getPfId());
				if (usr != null && usr.getUsername() != null && usr.getUsername() != "") {
					dto.setUsername(usr.getUsername());
				}
			}

			if (dto.getBranchCode() != null) {
				BranchMaster branchMaster = branchMasterRepository.findByBranchCode(dto.getBranchCode());
				if (branchMaster != null && branchMaster.getCircle() != null && branchMaster.getCircle() != "") {
					dto.setCircle(branchMaster.getCircle());
				}
			}
		}
		return entities;
	}

	@Override
	public List<TicketCentorDto> findAllTickets() {
		logger.info("Inside==Jasper====findAllTickets===========ALL DATA");
		List<TicketCentorDto> entities = ObjectMapperUtils.mapAll(ticketCentorRepo.findAll(), TicketCentorDto.class);

		for (TicketCentorDto dto : entities) {

			String kioskId = dto.getKisokId();
			String kioskBranchMaster = kioskMasterRepo.findKioskByKioskId_circle(kioskId);
			dto.setServeriry(kioskBranchMaster);

		}
		return entities;

	}

	@Override
	public List<TicketCentorDto> findAllTicketsForCmf() {
		logger.info("Inside==Jasper====findAllTicketsForCmf===========");
		UserDto user = (UserDto) session().getAttribute("userObj");
		List<TicketCentorDto> entities = ObjectMapperUtils.mapAll(ticketCentorRepo.findAllListByCMFUser(user.getPfId()),
				TicketCentorDto.class);

		for (TicketCentorDto dto : entities) {
			String kioskId = dto.getKisokId();
			String kioskBranchMaster = kioskMasterRepo.findKioskByKioskId_circle(kioskId);
			dto.setServeriry(kioskBranchMaster);

		}
		return entities;
	}

	@Override
	public List<TicketCentorDto> findAllTicketsForCms() {
		logger.info("Inside==Jasper====findAllTicketsForCms===========");
		UserDto user = (UserDto) session().getAttribute("userObj");
		String supPfId = user.getPfId();
		Set<String> supList = supervisorRepository.findPfIdListByPfIdSupervisor(supPfId);

		List<TicketCentorDto> entities = ObjectMapperUtils.mapAll(ticketCentorRepo.findAllListByCMFUserForCMS(supList),
				TicketCentorDto.class);

		for (TicketCentorDto dto : entities) {
			String kioskId = dto.getKisokId();
			String kioskBranchMaster = kioskMasterRepo.findKioskByKioskId_circle(kioskId);
			dto.setServeriry(kioskBranchMaster);

		}
		return entities;
	}

	@Override
	public List<TicketCentorDto> findAllTicketsByCircle() {
		logger.info("Inside==Jasper====findAllTicketsByCircle===========");
		UserDto user = (UserDto) session().getAttribute("userObj");

		List<TicketCentorDto> entities = ObjectMapperUtils
				.mapAll(ticketCentorRepo.findAllListByCircle(user.getCircle()), TicketCentorDto.class);

		for (TicketCentorDto dto : entities) {
			String kioskId = dto.getKisokId();
			String kioskBranchMaster = kioskMasterRepo.findKioskByKioskId_circle(kioskId);
			dto.setServeriry(kioskBranchMaster);

		}
		return entities;
	}

	@Override
	public List<TransactionDashBoardDto> findAllTransactionSummary() {
		
		logger.info("Inside==Jasper====findAllTransactionSummary===========");
		String fromdate = "";
		String todate = "";
		
	/*	  if((dateFrame.getFromDate()== "") && (dateFrame.getToDate()== "")) {*/
			
			  SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date curDate = new Date();
				curDate.setTime(curDate.getTime() - 48 * 60 * 60 * 1000);
				 fromdate = sdf.format(curDate);
				 todate = sdf.format(curDate);
				 
			//	 logger.info("Inside findAllTransactionSummary===========Current==========From date: "+fromdate);
			//	 logger.info("Inside findAllTransactionSummary===========Current==========To date: "+todate);
	//	  }
		  if((dateFrame.getFromDate().isEmpty()== false) && (dateFrame.getToDate().isEmpty()== false)) 
		  {
			   fromdate = dateFrame.getFromDate();
			   todate = dateFrame.getToDate();
			   
			//   logger.info("Inside findAllTransactionSummary===========TimeFrame==========From date: "+fromdate);
			//   logger.info("Inside findAllTransactionSummary===========TimeFrame==========To date: "+todate);
		  }
		
		logger.info("Inside==Jasper====findAllTransactionSummary=======after date setting====");
		
		
		List<SwayamMigrationSummary> page = transactionDashBoardRepositoryPaging.findAllByDate(fromdate, todate);
		List<TransactionDashBoardDto> entities = ObjectMapperUtils.mapAll(page, TransactionDashBoardDto.class);
		return entities;
	}

	@Override
	public List<RealTimeTransactionDto> findAllDateWiseRealtimeTxn(String fromdate) {
		logger.info("Inside==Jasper====findAllDateWiseRealtimeTxn===========");
		List<RealTimeTransaction> list = realTimeTxnRepositoryPaging.findByDate(fromdate);
	//	logger.info("Inside==Jasper=list=" + list);
		List<RealTimeTransactionDto> entities = ObjectMapperUtils.mapAll(list, RealTimeTransactionDto.class);
		return entities;
	}

	@Override
	public List<ZeroTransactionKiosksDto> findAllZeroTxnKoisk(String fromdate, String todate) {
		logger.info("Inside==Jasper====findAllZeroTxnKoisk===========");
		//logger.info("Inside==Jasper====findAllZeroTxnKoisk=========== From date: "+dateFrame.getFromDate());
		//  logger.info("Inside==Jasper====findAllZeroTxnKoisk===========To date: "+dateFrame.getToDate());
		 
		  if((dateFrame.getFromDate()!= "") && (dateFrame.getToDate()!= "")) {
				
			  fromdate = dateFrame.getFromDate();
			   todate = dateFrame.getToDate();
		  }
		 
		List<ZeroTransactionKiosks> list = zeroTransactionKiosksRepo.findByDateZeroTxn(fromdate, todate);
		List<ZeroTransactionKiosksDto> entities = ObjectMapperUtils.mapAll(list, ZeroTransactionKiosksDto.class);
		return entities;
	}

	@Override
	public List<ErrorReportingDto> findAllErrorReprting(String fromdate, String todate) {
		logger.info("Inside==Jasper====findAllErrorReprting===========");
		
		
		  
		  if((dateFrame.getFromDate()!= "") && (dateFrame.getToDate()!= "")) {
		  
		  fromdate = dateFrame.getFromDate(); todate = dateFrame.getToDate(); }
		 
		List<ErrorReporting> list = errorReportingRepositoryPaging.findAllErrReport(fromdate, todate);
		List<ErrorReportingDto> entities = ObjectMapperUtils.mapAll(list, ErrorReportingDto.class);
		return entities;
	}
	
	

	  @Override 
	  public List<TicketHistoryDto> findTicketHistoryReport() {
	  logger.info("Inside==Jasper====findTicketHistoryReport===========");
	  logger.info(ticketHistoryReport.getKisokId());
	  logger.info(ticketHistoryReport.getCircle());
	  logger.info(ticketHistoryReport.getBranchCode());
	  logger.info(ticketHistoryReport.getCall_log_date());
	  logger.info(ticketHistoryReport.getCall_closed_date());
	  logger.info(ticketHistoryReport.getVendor());
	  logger.info(ticketHistoryReport.getCallCategory());
	  logger.info(ticketHistoryReport.getCallSubCategory());
	  
	  String kioskId=ticketHistoryReport.getKisokId(); String circle =ticketHistoryReport.getCircle();
	  String branch=ticketHistoryReport.getBranchCode();  String  callLogDate=ticketHistoryReport.getCall_log_date(); 
	  String callClosedDate=ticketHistoryReport.getCall_closed_date(); 
	  String vendor =ticketHistoryReport.getVendor(); String  category=ticketHistoryReport.getCallCategory();
	  String subCategory=ticketHistoryReport.getCallSubCategory();
	  
		if (ticketHistoryReport.getKisokId().equals("undefined")) {
			kioskId="";
		}
		if (ticketHistoryReport.getCircle().equals("0") || ticketHistoryReport.getCircle().equals("undefined")) {
			circle="";
			
		}
		if(ticketHistoryReport.getBranchCode().equals("undefined")) {
			branch="";
		}
		
		
		if(ticketHistoryReport.getCall_log_date().isEmpty()) {
			callLogDate="";
		}
		if(ticketHistoryReport.getCall_closed_date().isEmpty()) {
			callClosedDate="";
		}
		
		if (ticketHistoryReport.getVendor().equals("0") || ticketHistoryReport.getVendor().equals("undefined")) {
			vendor = "";
		}
		if(ticketHistoryReport.getCallCategory().equals("0") || ticketHistoryReport.getCallCategory().equals("undefined")) {
			category ="";
		}
		
		if(ticketHistoryReport.getCallSubCategory().equals("0") || ticketHistoryReport.getCallSubCategory().equals("undefined")) {
			subCategory="";
		}

		/*
		 * kioskId = ticketHistoryReport.getKisokId(); circle =
		 * ticketHistoryReport.getCircle(); branch = ticketHistoryReport.getBranch();
		 * callLogDate = ticketHistoryReport.getCall_log_date(); callClosedDate =
		 * ticketHistoryReport.getCall_closed_date(); vendor =
		 * ticketHistoryReport.getVendor(); category =
		 * ticketHistoryReport.getCallCategory(); subCategory =
		 * ticketHistoryReport.getCallSubCategory();
		 */
	  
	  List<TicketHistory> list = null;
	  logger.info("Start....");
	  list=ticketHistoryPagingRepo.findbyFilterAndReport(kioskId, callLogDate, category, branch, callClosedDate, 
			                   subCategory, circle, vendor);
	  
	  List<TicketHistoryDto> entities = ObjectMapperUtils.mapAll(list,TicketHistoryDto.class);
	  logger.info("entities======pdf======="+entities);
	  
	  return entities; 
	  }
	  
	  
	  

	@Override
	public List<DownTimeDto> findDownTimeReport() {
		 logger.info("Inside==Jasper====findTicketHistoryReport===========");
		  logger.info(downtimeReport.getCircle());
		  logger.info(downtimeReport.getCmsCmf());
		  logger.info(downtimeReport.getVendor());
		  logger.info(downtimeReport.getFromDate());
		  logger.info(downtimeReport.getToDate());
		  
		  String circle =downtimeReport.getCircle();
		  String cmsCmf=downtimeReport.getCmsCmf(); 
		  String vendor =downtimeReport.getVendor();
		  String fromDate=downtimeReport.getFromDate(); 
		  String toDate=downtimeReport.getToDate();
			  
				
				if (downtimeReport.getCircle().equals("0") || downtimeReport.getCircle().equals("undefined")) {
					circle="";
					
				}
				if(downtimeReport.getCmsCmf().equals("0") || downtimeReport.getCmsCmf().equals("undefined") ) {
					cmsCmf="";
				}
				if(downtimeReport.getFromDate().isEmpty()) {
					fromDate="";
				}
				
				
				if(downtimeReport.getToDate().isEmpty()) {
					toDate="";
				}
				
				if (downtimeReport.getVendor().equals("0") || downtimeReport.getVendor().equals("undefined")) {
					vendor = "";
				}
				
		  List<DownTime> list = null;
		  list=downTimePagingRepo.findAllByFilterDTimeReports(toDate, fromDate, circle,vendor ,cmsCmf);
		  List<DownTimeDto> entities = ObjectMapperUtils.mapAll(list, DownTimeDto.class);
		  logger.info("PDF entities "+entities);
		  return entities; 
		  }
	
	
	@Override
	public List<BillingPenaltyDto> findBillingPenaltyReport() {
		logger.info("Inside==Jasper====findBillingPenaltyReport===========");
		logger.info(report.getCircle());
		logger.info(report.getState());
		logger.info(report.getRpfNumber());
		logger.info(report.getVendor());
		logger.info(report.getTimePeiod());
		
		String circle =null;
		String vendor =null;
		String state=null;
		String rpfNumber=null;
		String timePeriod= null;
		
		
		if((report.getCircle()!= "") && (report.getState()!= "") 
				&& (report.getRpfNumber()!= "") && (report.getVendor()!= "") ) {
			circle =report.getCircle();
			vendor =report.getVendor();
			state=report.getState();
			rpfNumber=report.getRpfNumber();
			timePeriod= report.getTimePeiod();
			
		}
		
		String quarter= timePeriod.substring(0, 2);
		String finacialYear= timePeriod.substring(3);
		
		List<BillingPenaltyEntity> list =null;
		
		if (state.equals("0")) {

			if (rpfNumber.equalsIgnoreCase("1")) {
				list =bpRepository.findbyWithoutStateFilterReport(circle, quarter,finacialYear, vendor);

			} else {
				
				list=bpRepository.findbyFilterRfpWithoutStateReport(circle, quarter,finacialYear, vendor, rpfNumber);
			}

		} else {
			if (rpfNumber.equalsIgnoreCase("1")) {
				list=bpRepository.findbyFilterReport(circle, state, quarter,finacialYear, vendor);
				
			} else {
				list= bpRepository.findbyFilterWithRFPReport(circle, state, quarter,finacialYear, vendor, rpfNumber);
			}

		}
		
		System.out.println("List ----" + list);
		
		System.out.println(list.isEmpty());
		List<BillingPenaltyDto> entities = null;
		
		/*
		 * if(list.isEmpty()) {
		 * 
		 * return entities; }else {
		 * 
		 * entities = ObjectMapperUtils.mapAll(list, BillingPenaltyDto.class);
		 * 
		 * }
		 */
		
		entities = ObjectMapperUtils.mapAll(list, BillingPenaltyDto.class);
		return entities;
	}

	@Override
	public List<InvoiceGenerationDto> findInvoiceGenerationReport() {
		logger.info("Inside==Jasper====findInvoiceGenerationReport===========");
		logger.info(report.getCircle());
		logger.info(report.getState());
		logger.info(report.getRpfNumber());
		logger.info(report.getVendor());
		logger.info(report.getTimePeiod());
		
		String circle =null;
		String vendor =null;
		String state=null;
		String rpfNumber=null;
		String timePeriod= null;
		
		
		if((report.getCircle()!= "") && (report.getState()!= "") 
				&& (report.getRpfNumber()!= "") && (report.getVendor()!= "") ) {
			circle =report.getCircle();
			vendor =report.getVendor();
			state=report.getState();
			rpfNumber=report.getRpfNumber();
			timePeriod= report.getTimePeiod();
			
		}
		
		String quarter= timePeriod.substring(0, 2);
		String finacialYear= timePeriod.substring(3);
		
		List<InvoiceGeneration> list =null;
		
		if (state.equals("0")) {

			if (rpfNumber.equalsIgnoreCase("1")) {
				list =IgRepository.findbyWithoutStateFilterReport(circle, quarter,finacialYear, vendor);

			} else {
				
				list=IgRepository.findbyFilterRfpWithoutStateReport(circle, quarter,finacialYear, vendor, rpfNumber);
			}

		} else {
			if (rpfNumber.equalsIgnoreCase("1")) {
				list=IgRepository.findbyFilterReport(circle, state, quarter,finacialYear, vendor);
				
			} else {
				list= IgRepository.findbyFilterWithRFPReport(circle, state, quarter,finacialYear, vendor, rpfNumber);
			}

		}
  
		
		List<InvoiceGenerationDto> entities = ObjectMapperUtils.mapAll(list, InvoiceGenerationDto.class);
 
		
		return entities;
	}

	@Override
	public List<InvoiceCompareDto> findInvoiceCompareReport() {
		logger.info("Inside==Jasper====findInvoiceGenerationReport===========");
		logger.info(report.getCircle());
		logger.info(report.getState());
		logger.info(report.getRpfNumber());
		logger.info(report.getVendor());
		logger.info(report.getTimePeiod());
		
		String circle =null;
		String vendor =null;
		String state=null;
		String rpfNumber=null;
		String timePeriod= null;
		
		
		if((report.getCircle()!= "") && (report.getState()!= "") 
				&& (report.getRpfNumber()!= "") && (report.getVendor()!= "") ) {
			circle =report.getCircle();
			vendor =report.getVendor();
			state=report.getState();
			rpfNumber=report.getRpfNumber();
			timePeriod= report.getTimePeiod();
			
		}
		String quarter= timePeriod.substring(0, 2);
		String finacialYear= timePeriod.substring(3);
		List<InvoiceCompare> list =null;
		
		if (state.equals("0")) {

			if (rpfNumber.equalsIgnoreCase("1")) {
				list =icRepository.findbyWithoutStateFilterReport(circle, quarter,finacialYear, vendor);

			} else {
				
				list=icRepository.findbyFilterRfpWithoutStateReport(circle, quarter,finacialYear, vendor, rpfNumber);
			}

		} else {
			if (rpfNumber.equalsIgnoreCase("1")) {
				list=icRepository.findbyFilterReport(circle, state, quarter,finacialYear, vendor);
				
			} else {
				list= icRepository.findbyFilterWithRFPReport(circle, state, quarter,finacialYear, vendor, rpfNumber);
			}

		}
  
		
		List<InvoiceCompareDto> entities = ObjectMapperUtils.mapAll(list, InvoiceCompareDto.class);
 
		
		return entities;
	}

	@Override
	public List<InvoiceSummaryDto> findInvoiceSummaryReport() {
		logger.info("Inside==Jasper====findInvoiceSummaryReport===========");
		logger.info(report.getCircle());
		logger.info(report.getState());
		logger.info(report.getTimePeiod());
		String circle =null;
		String state=null;
		String timePeriod= null;
		if((report.getCircle()!= "") && (report.getTimePeiod()!= "") ) {
			circle =report.getCircle();
			state=report.getState();
			timePeriod= report.getTimePeiod();
			
		}
		
		List<InvoiceSummaryEntity> list =null;
		
	if(state.equals("0"))	{
			
			
			list = isRepository.findbyWithoutStateFilterReport(circle, timePeriod);
			
			}else{
			
			
				
			list = isRepository.findbyFilterReport(circle, state, timePeriod);
		}
		  
		
	List<InvoiceSummaryDto> entities = ObjectMapperUtils.mapAll(list, InvoiceSummaryDto.class);
		
		return entities;
	}
	
	

}
