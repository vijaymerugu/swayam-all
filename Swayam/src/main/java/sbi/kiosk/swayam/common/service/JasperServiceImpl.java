package sbi.kiosk.swayam.common.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import sbi.kiosk.swayam.billingpayment.repository.TaxSummaryRepository;
import sbi.kiosk.swayam.billingpayment.service.TaxService;
import sbi.kiosk.swayam.common.constants.Constants;
import sbi.kiosk.swayam.common.dto.BillingPenaltyDto;
import sbi.kiosk.swayam.common.dto.DownTimeDto;
import sbi.kiosk.swayam.common.dto.ErrorReportingDto;
import sbi.kiosk.swayam.common.dto.InvoiceCompareDto;
import sbi.kiosk.swayam.common.dto.InvoiceGenerationDto;
import sbi.kiosk.swayam.common.dto.InvoiceSummaryDto;
import sbi.kiosk.swayam.common.dto.KioskBranchMasterUserDto;
import sbi.kiosk.swayam.common.dto.RealTimeTransactionDto;
import sbi.kiosk.swayam.common.dto.TaxCalculationDto;
import sbi.kiosk.swayam.common.dto.TerminalStatusDto;
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
import sbi.kiosk.swayam.common.entity.KioskBranchMaster;
import sbi.kiosk.swayam.common.entity.RealTimeTransaction;
import sbi.kiosk.swayam.common.entity.Supervisor;
import sbi.kiosk.swayam.common.entity.SwayamMigrationSummary;
import sbi.kiosk.swayam.common.entity.TaxEntity;
import sbi.kiosk.swayam.common.entity.TaxSummaryEntity;
import sbi.kiosk.swayam.common.entity.TerminalStatus;
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
import sbi.kiosk.swayam.healthmonitoring.repository.KioskMasterRepo;
import sbi.kiosk.swayam.healthmonitoring.repository.TerminalStatusRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.TerminalStatusRepositoryPaging;
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
	KioskMasterRepo kioskMasterRepos;


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

    @Autowired
	sbi.kiosk.swayam.healthmonitoring.repository.BranchMasterRepository branchMasterRepo;
    @Autowired
	TerminalStatusRepositoryPaging terminalStatusRepo;
    
    @Autowired
	TerminalStatusRepository terminalStatusRepository;
    
   
    @Autowired
   	BranchMasterRepository branchMastRepository;
    
    @Autowired
    TaxSummaryRepository taxSummaryRepo;
    
    @Autowired
	TaxService taxReportService;
    
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
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
				File file = ResourceUtils.getFile(jrxmlPath + "usersListSA.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "UserList_" + timeStamp + ".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
					}
			} else if (identifyPage.equals("userListLA")) {
				List<UserManagementDto> list = findPaginatedByCircle();
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
				File file = ResourceUtils.getFile(jrxmlPath + "userListLA.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "UserList_" + timeStamp + ".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
			}
			} else if (identifyPage.equals("kioskManagementByCircle")) {
				List<KioskBranchMasterUserDto> list = findKiosksPaginatedByCircle();
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
				File file = ResourceUtils.getFile(jrxmlPath + "kioskManagementByCircle.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "Kiosks_" + timeStamp + ".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
			}
			} else if (identifyPage.equals("kiosksAll")) {
				List<KioskBranchMasterUserDto> list = findAllKiosks();
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
				File file = ResourceUtils.getFile(jrxmlPath + "kioskManagementByCircle.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "Kiosks_" + timeStamp + ".pdf";
				
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
			}
			} else if (identifyPage.equals("ticketCenterCC")) {
				List<TicketCentorDto> list = findAllTickets();
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
				File file = ResourceUtils.getFile(jrxmlPath + "ticketCenter.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "TicketCenter_" + timeStamp + ".pdf";
				
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
			}
			} else if (identifyPage.equals("ticketCenterCMF")) {
				List<TicketCentorDto> list = findAllTicketsForCmf();
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
				File file = ResourceUtils.getFile(jrxmlPath + "ticketCenterCMF.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "TicketCenter_" + timeStamp + ".pdf";
				
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
					}
			} else if (identifyPage.equals("ticketCenterCMS")) {
				List<TicketCentorDto> list = findAllTicketsForCms();
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
				File file = ResourceUtils.getFile(jrxmlPath + "ticketCenterCMF.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "TicketCenter_" + timeStamp + ".pdf";
				
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
					}
			} else if (identifyPage.equals("ticketCenterCU")) {
				List<TicketCentorDto> list = findAllTicketsByCircle();
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
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
			}

			else if (identifyPage.equals("transactionSummary")) {
				logger.info("PDF File TransactionSummary !!");
				List<TransactionDashBoardDto> list = findAllTransactionSummary();
				 if(list.isEmpty()) {
						return filename;
					}else {
				File file = ResourceUtils.getFile(jrxmlPath + "transactionSummary.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				//String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				//filename = "TransactionSummary_" + timeStamp + ".pdf";
				
				 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					Date curDate = new Date();
					curDate.setTime(curDate.getTime() - 48 * 60 * 60 * 1000);
					String  fromdate1 = sdf.format(curDate);
					String todate1 = sdf.format(curDate);
				String fileName ="";
				if(!dateFrame.getFromDate().isEmpty() && !dateFrame.getToDate().isEmpty()) {
				fileName = "SwayamMigration_" + dateFrame.getFromDate().replace("-", "").substring(0, 6) + "_"
						+ dateFrame.getToDate().replace("-", "").substring(0, 6) + "_"
						+ (new SimpleDateFormat("dd-MM-yyyy")).format(new Date()).replace("-", "").substring(0, 6);
				}else {
					
					  
					 fileName = "SwayamMigration_" + fromdate1.replace("-", "").substring(0, 6) + "_"
							+ todate1.replace("-", "").substring(0, 6) + "_"
							+ (new SimpleDateFormat("dd-MM-yyyy")).format(new Date()).replace("-", "").substring(0, 6);
				}
				filename =fileName+".pdf";
				//"attachment; filename=\"" + fileName + ".pdf\""
				logger.info("PDF File filename !!"+filename);
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
					}
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
				//String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				//filename = "BillingPenalty_" + timeStamp + ".pdf";

				//Sharan Change-01-11-2020				
				String quarter= report.getTimePeiod().substring(0, 2);
				String finacialYear= report.getTimePeiod().substring(3);
					
				if(quarter.equalsIgnoreCase("Q1")) {
					String Q1StartDate =  "0104"+finacialYear.substring(0, 4);
					//System.out.println("Q1StartDate "+ Q1StartDate);
					String Q1LastDate =  "3006"+finacialYear.substring(0, 4);
					//System.out.println("Q1LastDate "+ Q1LastDate);
					filename = "BillingPenalty_"+Q1StartDate+"_"+Q1LastDate+".pdf";
					System.out.println("FileName "+ filename);
					
				}else if(quarter.equalsIgnoreCase("Q2")) {
					String Q2StartDate =  "0107"+finacialYear.substring(0, 4);
					String Q2LastDate =  "3009"+finacialYear.substring(0, 4);
					filename = "BillingPenalty_"+Q2StartDate+"_"+Q2LastDate+".pdf";
				}else if(quarter.equalsIgnoreCase("Q3")) {
					String Q3StartDate =  "0110"+finacialYear.substring(0, 4);
					String Q3LastDate =  "3112"+finacialYear.substring(0, 4);
					
					filename = "BillingPenalty_"+Q3StartDate+"_"+Q3LastDate+".pdf";
					
				}else if(quarter.equalsIgnoreCase("Q4")) {
					String Q4StartDate =  "0101"+finacialYear.substring(5);
					String Q4LastDate =  "3103"+finacialYear.substring(5);
					filename = "BillingPenalty_"+Q4StartDate+"_"+Q4LastDate+".pdf";
				}else {
					logger.info("No Quater Period selected");
				}
				
			
				//filename =fileName+".pdf";
				//System.out.println("Final File Name" + filename);

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
				//String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				//filename = "InvoiceGeneration_" + timeStamp + ".pdf";

				
				//Sharan Change-01-11-2020				
				String quarter= report.getTimePeiod().substring(0, 2);
				String finacialYear= report.getTimePeiod().substring(3);
					
				if(quarter.equalsIgnoreCase("Q1")) {
					String Q1StartDate =  "0104"+finacialYear.substring(0, 4);
					//System.out.println("Q1StartDate "+ Q1StartDate);
					String Q1LastDate =  "3006"+finacialYear.substring(0, 4);
					//System.out.println("Q1LastDate "+ Q1LastDate);
					filename = "InvoiceGeneration_"+Q1StartDate+"_"+Q1LastDate+".pdf";
					System.out.println("FileName "+ filename);
					
				}else if(quarter.equalsIgnoreCase("Q2")) {
					String Q2StartDate =  "0107"+finacialYear.substring(0, 4);
					String Q2LastDate =  "3009"+finacialYear.substring(0, 4);
					filename = "InvoiceGeneration_"+Q2StartDate+"_"+Q2LastDate+".pdf";
				}else if(quarter.equalsIgnoreCase("Q3")) {
					String Q3StartDate =  "0110"+finacialYear.substring(0, 4);
					String Q3LastDate =  "3112"+finacialYear.substring(0, 4);
					
					filename = "InvoiceGeneration_"+Q3StartDate+"_"+Q3LastDate+".pdf";
					
				}else if(quarter.equalsIgnoreCase("Q4")) {
					String Q4StartDate =  "0101"+finacialYear.substring(5);
					String Q4LastDate =  "3103-"+finacialYear.substring(5);
					filename = "InvoiceGeneration_"+Q4StartDate+"_"+Q4LastDate+".pdf";
				}else {
					logger.info("No Quater Period selected");
				}
				
				
				
				/*
				 * String fileName = "InvoiceGeneration_" + dateFrame.getFromDate().replace("-",
				 * "").substring(0, 6) + "_" + dateFrame.getToDate().replace("-",
				 * "").substring(0, 6) + "_" + (new SimpleDateFormat("dd-MM-yyyy")).format(new
				 * Date()).replace("-", "").substring(0, 6); filename =fileName+".pdf";
				 */

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
				//String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				//filename = "InvoiceCompare_" + timeStamp + ".pdf";

				
				//Sharan Change-01-11-2020				
				String quarter= report.getTimePeiod().substring(0, 2);
				String finacialYear= report.getTimePeiod().substring(3);
					
				if(quarter.equalsIgnoreCase("Q1")) {
					String Q1StartDate =  "0104"+finacialYear.substring(0, 4);
					//System.out.println("Q1StartDate "+ Q1StartDate);
					String Q1LastDate =  "3006"+finacialYear.substring(0, 4);
					//System.out.println("Q1LastDate "+ Q1LastDate);
					filename = "InvoiceCompare_"+Q1StartDate+"_"+Q1LastDate+".pdf";
					System.out.println("FileName "+ filename);
					
				}else if(quarter.equalsIgnoreCase("Q2")) {
					String Q2StartDate =  "0107"+finacialYear.substring(0, 4);
					String Q2LastDate =  "3009"+finacialYear.substring(0, 4);
					filename = "InvoiceCompare_"+Q2StartDate+"_"+Q2LastDate+".pdf";
				}else if(quarter.equalsIgnoreCase("Q3")) {
					String Q3StartDate =  "0110"+finacialYear.substring(0, 4);
					String Q3LastDate =  "3112"+finacialYear.substring(0, 4);
					
					filename = "InvoiceCompare_"+Q3StartDate+"_"+Q3LastDate+".pdf";
					
				}else if(quarter.equalsIgnoreCase("Q4")) {
					String Q4StartDate =  "0101"+finacialYear.substring(5);
					String Q4LastDate =  "3103-"+finacialYear.substring(5);
					filename = "InvoiceCompare_"+Q4StartDate+"_"+Q4LastDate+".pdf";
				}else {
					logger.info("No Quater Period selected");
				}
				
				
				
				
				/*
				 * String fileName = "InvoiceCompare_" + dateFrame.getFromDate().replace("-",
				 * "").substring(0, 6) + "_" + dateFrame.getToDate().replace("-",
				 * "").substring(0, 6) + "_" + (new SimpleDateFormat("dd-MM-yyyy")).format(new
				 * Date()).replace("-", "").substring(0, 6); filename =fileName+".pdf";
				 */

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

//				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
//				filename = "InvoiceSummary_" + timeStamp + ".pdf";
				
				
				String finacialYear= report.getTimePeiod();
				
				String StartDate =  "0104"+finacialYear.substring(0, 4);
				String LastDate =  "3103"+finacialYear.substring(5);
				filename = "InvoiceSummary_"+StartDate+"_"+LastDate+".pdf";
				
				
				/*
				 * String fileName = "InvoiceSummary_" + dateFrame.getFromDate().replace("-",
				 * "").substring(0, 6) + "_" + dateFrame.getToDate().replace("-",
				 * "").substring(0, 6) + "_" + (new SimpleDateFormat("dd-MM-yyyy")).format(new
				 * Date()).replace("-", "").substring(0, 6); filename =fileName+".pdf";
				 */

				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
				}
			}else if(identifyPage.equals("taxSummaryReport")) {
				logger.info("PDF File taxSummaryReport !!");
				List<TaxSummaryEntity> list = findTaxSummaryReport();
				if(list.isEmpty()) {
					return filename;
				}else {
				File file = ResourceUtils.getFile(jrxmlPath + "taxSummary.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);

//				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
//				filename = "InvoiceSummary_" + timeStamp + ".pdf";
				
				
				String finacialYear= report.getTimePeiod();
				
				String StartDate =  "0104"+finacialYear.substring(0, 4);
				String LastDate =  "3103"+finacialYear.substring(5);
				filename = "TaxSummary_"+StartDate+"_"+LastDate+".pdf";
				
			
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
				}
			}else if(identifyPage.equals("taxCalReport")) {
				logger.info("PDF File taxCalReport !!");
				List<TaxEntity> list = findTaxCalReport();
				if(list.isEmpty()) {
					return filename;
				}else {
				File file = ResourceUtils.getFile(jrxmlPath + "taxCalculation.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);

				//Sharan Change-01-11-2020				
				String quarter= report.getQuarter();
				String finacialYear= report.getYear();
					
				if(quarter.equalsIgnoreCase("Q1")) {
					String Q1StartDate =  "0104"+finacialYear.substring(0, 4);
					//System.out.println("Q1StartDate "+ Q1StartDate);
					String Q1LastDate =  "3006"+finacialYear.substring(0, 4);
					//System.out.println("Q1LastDate "+ Q1LastDate);
					filename = "TaxCalculation_"+Q1StartDate+"_"+Q1LastDate+".pdf";
					//System.out.println("FileName "+ filename);
					
				}else if(quarter.equalsIgnoreCase("Q2")) {
					String Q2StartDate =  "0107"+finacialYear.substring(0, 4);
					String Q2LastDate =  "3009"+finacialYear.substring(0, 4);
					filename = "TaxCalculation_"+Q2StartDate+"_"+Q2LastDate+".pdf";
				}else if(quarter.equalsIgnoreCase("Q3")) {
					String Q3StartDate =  "0110"+finacialYear.substring(0, 4);
					String Q3LastDate =  "3112"+finacialYear.substring(0, 4);
					
					filename = "TaxCalculation_"+Q3StartDate+"_"+Q3LastDate+".pdf";
					
				}else if(quarter.equalsIgnoreCase("Q4")) {
					String Q4StartDate =  "0101"+finacialYear.substring(5);
					String Q4LastDate =  "3103-"+finacialYear.substring(5);
					filename = "TaxCalculation_"+Q4StartDate+"_"+Q4LastDate+".pdf";
				}else {
					logger.info("No Quater Period selected");
				}
				
				
				
				/*
				 * String finacialYear= report.getTimePeiod();
				 * 
				 * String StartDate = "0104"+finacialYear.substring(0, 4); String LastDate =
				 * "3103"+finacialYear.substring(5); filename =
				 * "TaxSummary_"+StartDate+"_"+LastDate+".pdf";
				 */
				
			
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
				}
			}


			else if (identifyPage.equals("realTimeToday")) {
				logger.info("PDF File realTimeToday !!");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date curDate = new Date();
				String todayDate = sdf.format(curDate);
				List<RealTimeTransactionDto> list = findAllDateWiseRealtimeTxn(todayDate);
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
				File file = ResourceUtils.getFile(jrxmlPath + "realTimeToday.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				//String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				//filename = "RealTimeTxnToday_" + timeStamp + ".pdf";
				String fileName = "RealTimeTxnToday_" + todayDate.replace("-", "").substring(0, 6) + "_"
						+ todayDate.replace("-", "").substring(0, 6) + "_"
						+ (new SimpleDateFormat("dd-MM-yyyy")).format(new Date()).replace("-", "").substring(0, 6);
				filename =fileName+".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
					}
			} else if (identifyPage.equals("realTimeYesterday")) {
				logger.info("PDF File RealTimeYesterday !!");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date curDate = new Date();
				curDate.setTime(curDate.getTime() - 24 * 60 * 60 * 1000);
				String yesterdayDate = sdf.format(curDate);

				List<RealTimeTransactionDto> list = findAllDateWiseRealtimeTxn(yesterdayDate);
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
				File file = ResourceUtils.getFile(jrxmlPath + "realTimeYesterday.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				//String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				//filename = "RealTimeTxnYesterday_" + timeStamp + ".pdf";
				String fileName = "RealTimeTxnYesterday_" + yesterdayDate.replace("-", "").substring(0, 6) + "_"
						+ yesterdayDate.replace("-", "").substring(0, 6) + "_"
						+ (new SimpleDateFormat("dd-MM-yyyy")).format(new Date()).replace("-", "").substring(0, 6);
				filename =fileName+".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
					}
			}

			else if (identifyPage.equals("zeroTxnKoisk")) {
				logger.info("PDF File ZeroTxnKoisk !!");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date curDate = new Date();
				String fromdate = sdf.format(curDate);
				String todate = sdf.format(curDate);

				List<ZeroTransactionKiosksDto> list = findAllZeroTxnKoisk(fromdate, todate);
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
				File file = ResourceUtils.getFile(jrxmlPath + "zeroTxnKiosk.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				//String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				//filename = "ZeroTxnKoisk_" + timeStamp + ".pdf";
				String fileName ="";
				if(!dateFrame.getFromDate().isEmpty() && !dateFrame.getToDate().isEmpty()) {
				fileName = "ZeroTxnKoisk_" + dateFrame.getFromDate().replace("-", "").substring(0, 6) + "_"
						+ dateFrame.getToDate().replace("-", "").substring(0, 6) + "_"
						+ (new SimpleDateFormat("dd-MM-yyyy")).format(new Date()).replace("-", "").substring(0, 6);
				}else {
					
					  
					 fileName = "ZeroTxnKoisk_" + fromdate.replace("-", "").substring(0, 6) + "_"
							+ todate.replace("-", "").substring(0, 6) + "_"
							+ (new SimpleDateFormat("dd-MM-yyyy")).format(new Date()).replace("-", "").substring(0, 6);
				}
				filename =fileName+".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
					}
			} else if (identifyPage.equals("errorReporting")) {
				logger.info("PDF File ErrorReporting !!");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date curDate = new Date();
				String fromdate = sdf.format(curDate);
				String todate = sdf.format(curDate);

				List<ErrorReportingDto> list = findAllErrorReprting(fromdate, todate);
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
				File file = ResourceUtils.getFile(jrxmlPath + "errorReporting.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				//String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				//filename = "ErrorReporting_" + timeStamp + ".pdf";
				String fileName ="";
				if(!dateFrame.getFromDate().isEmpty() && !dateFrame.getToDate().isEmpty()) {
				fileName = "ErrorReporting_" + dateFrame.getFromDate().replace("-", "").substring(0, 6) + "_"
						+ dateFrame.getToDate().replace("-", "").substring(0, 6) + "_"
						+ (new SimpleDateFormat("dd-MM-yyyy")).format(new Date()).replace("-", "").substring(0, 6);
				}else {
					
					  
					 fileName = "ErrorReporting_" + fromdate.replace("-", "").substring(0, 6) + "_"
							+ todate.replace("-", "").substring(0, 6) + "_"
							+ (new SimpleDateFormat("dd-MM-yyyy")).format(new Date()).replace("-", "").substring(0, 6);
				}
				filename =fileName+".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
					}
			}
			else if (identifyPage.equals("tiketHistory")) {
				logger.info("PDF File tiketHistory !!");

				List<TicketHistoryDto> list = findTicketHistoryReport();
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
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
			}
			else if (identifyPage.equals("downTime")) {
				logger.info("PDF File DownTime !!");

				List<DownTimeDto> list = findDownTimeReport();
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
				File file = ResourceUtils.getFile(jrxmlPath + "downTime.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
		     	String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "DownTime_" + timeStamp + ".pdf";
				
				/*
				 * SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); Date curDate = new
				 * Date(); String todayDate = sdf.format(curDate); String fromDate =
				 * sdf.format(curDate); String fileName ="";
				 * if(!dateFrame.getFromDate().isEmpty() && !dateFrame.getToDate().isEmpty()) {
				 * fileName = "DownTime_" + dateFrame.getFromDate().replace("-",
				 * "").substring(0, 6) + "_" + dateFrame.getToDate().replace("-",
				 * "").substring(0, 6) + "_" + (new SimpleDateFormat("dd-MM-yyyy")).format(new
				 * Date()).replace("-", "").substring(0, 6); }else {
				 * 
				 * fileName = "DownTime_" + fromDate.replace("-", "").substring(0, 6) + "_" +
				 * todayDate.replace("-", "").substring(0, 6) + "_" + (new
				 * SimpleDateFormat("dd-MM-yyyy")).format(new Date()).replace("-",
				 * "").substring(0, 6); } filename =fileName+".pdf";
				 */
				
				JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
					}
			}else if (identifyPage.equals("terminalStatus")) {
				
				 logger.info("PDF File Terminal Status !!");

			List<TerminalStatusDto> list = findTerminalStatusReport();
			 logger.info("PDF File Terminal Status list !!"+list);
			
			
			 if(list.isEmpty()) {
					
					
					return filename;
				}else {
			File file = ResourceUtils.getFile(jrxmlPath + "terminalStatus.jrxml");
			InputStream input = new FileInputStream(file);
			jasperReport = JasperCompileManager.compileReport(input);
			source = new JRBeanCollectionDataSource(list);
			Map<String, Object> parameters = new HashMap<>();
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
			String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
			filename = "TerminalStatus_" + timeStamp + ".pdf";
			JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + filename);
		}
			 

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
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
				File file = ResourceUtils.getFile(jrxmlPath + "usersListSA.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "UserList_" + timeStamp + ".xlsx";
				xlsx(jasperPrint, filename);
					}
			} else if (identifyPage.equals("userListLA")) {
				List<UserManagementDto> list = findPaginatedByCircle();
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
				File file = ResourceUtils.getFile(jrxmlPath + "userListLA.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "UserList_" + timeStamp + ".xlsx";
				xlsx(jasperPrint, filename);
					}
			} else if (identifyPage.equals("kioskManagementByCircle")) {
				List<KioskBranchMasterUserDto> list = findKiosksPaginatedByCircle();
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
				File file = ResourceUtils.getFile(jrxmlPath + "kioskManagementByCircle.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "Kiosks_" + timeStamp + ".xlsx";
				xlsx(jasperPrint, filename);
					}
			} else if (identifyPage.equals("kiosksAll")) {
				List<KioskBranchMasterUserDto> list = findAllKiosks();
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
				File file = ResourceUtils.getFile(jrxmlPath + "kioskManagementByCircle.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
			    String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "Kiosks_" + timeStamp + ".xlsx";
				xlsx(jasperPrint, filename);
					}
			} else if (identifyPage.equals("ticketCenterCC")) {
				List<TicketCentorDto> list = findAllTickets();
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
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
			} else if (identifyPage.equals("ticketCenterCMF")) {
				List<TicketCentorDto> list = findAllTicketsForCmf();
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
				File file = ResourceUtils.getFile(jrxmlPath + "ticketCenterCMF.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "TicketCenter_" + timeStamp + ".xlsx";
				xlsx(jasperPrint, filename);
					}
			} else if (identifyPage.equals("ticketCenterCMS")) {
				List<TicketCentorDto> list = findAllTicketsForCms();
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
				File file = ResourceUtils.getFile(jrxmlPath + "ticketCenterCMF.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "TicketCenter_" + timeStamp + ".xlsx";
				xlsx(jasperPrint, filename);
					}
			} else if (identifyPage.equals("ticketCenterCU")) {
				List<TicketCentorDto> list = findAllTicketsByCircle();
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
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
			}

			else if (identifyPage.equals("transactionSummary")) {
				logger.info("Excel File TransactionSummary !!");
				List<TransactionDashBoardDto> list = findAllTransactionSummary();
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
				File file = ResourceUtils.getFile(jrxmlPath + "transactionSummary.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				//String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				//filename = "TransactionSummary_" + timeStamp + ".xlsx";
				
				    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					Date curDate = new Date();
					curDate.setTime(curDate.getTime() - 48 * 60 * 60 * 1000);
					String  fromdate1 = sdf.format(curDate);
					String todate1 = sdf.format(curDate);
				
				String fileName ="";
				if(!dateFrame.getFromDate().isEmpty() && !dateFrame.getToDate().isEmpty()) {
				fileName = "SwayamMigration_" + dateFrame.getFromDate().replace("-", "").substring(0, 6) + "_"
						+ dateFrame.getToDate().replace("-", "").substring(0, 6) + "_"
						+ (new SimpleDateFormat("dd-MM-yyyy")).format(new Date()).replace("-", "").substring(0, 6);
				}else {
					
					  
					 fileName = "SwayamMigration_" + fromdate1.replace("-", "").substring(0, 6) + "_"
							+ todate1.replace("-", "").substring(0, 6) + "_"
							+ (new SimpleDateFormat("dd-MM-yyyy")).format(new Date()).replace("-", "").substring(0, 6);
				}
				filename =fileName + ".xlsx";
				
				xlsx(jasperPrint, filename);
					}
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
				//String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				//filename = "BillingPenalty_" + timeStamp + ".xlsx";

				
				String quarter= report.getTimePeiod().substring(0, 2);
				String finacialYear= report.getTimePeiod().substring(3);
					
				if(quarter.equalsIgnoreCase("Q1")) {
					String Q1StartDate =  "0104"+finacialYear.substring(0, 4);
					//System.out.println("Q1StartDate "+ Q1StartDate);
					String Q1LastDate =  "3006"+finacialYear.substring(0, 4);
					//System.out.println("Q1LastDate "+ Q1LastDate);
					filename = "BillingPenalty_"+Q1StartDate+"_"+Q1LastDate+".xlsx";
					System.out.println("FileName "+ filename);
					
				}else if(quarter.equalsIgnoreCase("Q2")) {
					String Q2StartDate =  "0107"+finacialYear.substring(0, 4);
					String Q2LastDate =  "3009"+finacialYear.substring(0, 4);
					filename = "BillingPenalty_"+Q2StartDate+"_"+Q2LastDate+".xlsx";
				}else if(quarter.equalsIgnoreCase("Q3")) {
					String Q3StartDate =  "0110"+finacialYear.substring(0, 4);
					String Q3LastDate =  "3112"+finacialYear.substring(0, 4);
					
					filename = "BillingPenalty_"+Q3StartDate+"_"+Q3LastDate+".xlsx";
					
				}else if(quarter.equalsIgnoreCase("Q4")) {
					String Q4StartDate =  "0101"+finacialYear.substring(5);
					String Q4LastDate =  "3103"+finacialYear.substring(5);
					filename = "BillingPenalty_"+Q4StartDate+"_"+Q4LastDate+".xlsx";
				}else {
					logger.info("No Quater Period selected");
				}
				
				
				
				
				
				/*
				 * String fileName = "BillingPenalty_" + dateFrame.getFromDate().replace("-",
				 * "").substring(0, 6) + "_" + dateFrame.getToDate().replace("-",
				 * "").substring(0, 6) + "_" + (new SimpleDateFormat("dd-MM-yyyy")).format(new
				 * Date()).replace("-", "").substring(0, 6); filename =fileName+".xlsx";
				 */

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
				//String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				//filename = "InvoiceGeneration_" + timeStamp + ".xlsx";

				
				//Sharan Change-01-11-2020				
				String quarter= report.getTimePeiod().substring(0, 2);
				String finacialYear= report.getTimePeiod().substring(3);
					
				if(quarter.equalsIgnoreCase("Q1")) {
					String Q1StartDate =  "0104"+finacialYear.substring(0, 4);
					//System.out.println("Q1StartDate "+ Q1StartDate);
					String Q1LastDate =  "3006"+finacialYear.substring(0, 4);
					//System.out.println("Q1LastDate "+ Q1LastDate);
					filename = "InvoiceGeneration_"+Q1StartDate+"_"+Q1LastDate+".xlsx";
					System.out.println("FileName "+ filename);
					
				}else if(quarter.equalsIgnoreCase("Q2")) {
					String Q2StartDate =  "0107"+finacialYear.substring(0, 4);
					String Q2LastDate =  "3009"+finacialYear.substring(0, 4);
					filename = "InvoiceGeneration_"+Q2StartDate+"_"+Q2LastDate+".xlsx";
				}else if(quarter.equalsIgnoreCase("Q3")) {
					String Q3StartDate =  "0110"+finacialYear.substring(0, 4);
					String Q3LastDate =  "3112"+finacialYear.substring(0, 4);
					
					filename = "InvoiceGeneration_"+Q3StartDate+"_"+Q3LastDate+".xlsx";
					
				}else if(quarter.equalsIgnoreCase("Q4")) {
					String Q4StartDate =  "0101"+finacialYear.substring(5);
					String Q4LastDate =  "3103"+finacialYear.substring(5);
					filename = "InvoiceGeneration_"+Q4StartDate+"_"+Q4LastDate+".xlsx";
				}else {
					logger.info("No Quater Period selected");
				}
				
				
				
				
				
				/*
				 * String fileName = "InvoiceGeneration_" + dateFrame.getFromDate().replace("-",
				 * "").substring(0, 6) + "_" + dateFrame.getToDate().replace("-",
				 * "").substring(0, 6) + "_" + (new SimpleDateFormat("dd-MM-yyyy")).format(new
				 * Date()).replace("-", "").substring(0, 6); filename =fileName+".xlsx";
				 */

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
				//String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				//filename = "InvoiceCompare_" + timeStamp + ".xlsx";

				
				
				//Sharan Change-01-11-2020				
				String quarter= report.getTimePeiod().substring(0, 2);
				String finacialYear= report.getTimePeiod().substring(3);
					
				if(quarter.equalsIgnoreCase("Q1")) {
					String Q1StartDate =  "0104"+finacialYear.substring(0, 4);
					//System.out.println("Q1StartDate "+ Q1StartDate);
					String Q1LastDate =  "3006"+finacialYear.substring(0, 4);
					//System.out.println("Q1LastDate "+ Q1LastDate);
					filename = "InvoiceCompare_"+Q1StartDate+"_"+Q1LastDate+".xlsx";
					System.out.println("FileName "+ filename);
					
				}else if(quarter.equalsIgnoreCase("Q2")) {
					String Q2StartDate =  "0107"+finacialYear.substring(0, 4);
					String Q2LastDate =  "3009"+finacialYear.substring(0, 4);
					filename = "InvoiceCompare_"+Q2StartDate+"_"+Q2LastDate+".xlsx";
				}else if(quarter.equalsIgnoreCase("Q3")) {
					String Q3StartDate =  "0110"+finacialYear.substring(0, 4);
					String Q3LastDate =  "3112"+finacialYear.substring(0, 4);
					
					filename = "InvoiceCompare_"+Q3StartDate+"_"+Q3LastDate+".xlsx";
					
				}else if(quarter.equalsIgnoreCase("Q4")) {
					String Q4StartDate =  "0101"+finacialYear.substring(5);
					String Q4LastDate =  "3103"+finacialYear.substring(5);
					filename = "InvoiceCompare_"+Q4StartDate+"_"+Q4LastDate+".xlsx";
				}else {
					logger.info("No Quater Period selected");
				}
				
				
				/*
				 * String fileName = "InvoiceCompare_" + dateFrame.getFromDate().replace("-",
				 * "").substring(0, 6) + "_" + dateFrame.getToDate().replace("-",
				 * "").substring(0, 6) + "_" + (new SimpleDateFormat("dd-MM-yyyy")).format(new
				 * Date()).replace("-", "").substring(0, 6); filename =fileName+".xlsx";
				 */

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

//				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
//				filename = "InvoiceSummary_" + timeStamp + ".xlsx";
				
				String finacialYear= report.getTimePeiod();
				
				String StartDate =  "0104"+finacialYear.substring(0, 4);
				String LastDate =  "3103"+finacialYear.substring(5);
				filename = "InvoiceSummary_"+StartDate+"_"+LastDate+".xlsx";
				
				
				/*
				 * String fileName = "InvoiceSummary_" + dateFrame.getFromDate().replace("-",
				 * "").substring(0, 6) + "_" + dateFrame.getToDate().replace("-",
				 * "").substring(0, 6) + "_" + (new SimpleDateFormat("dd-MM-yyyy")).format(new
				 * Date()).replace("-", "").substring(0, 6); filename =fileName+".xlsx";
				 */

				xlsx(jasperPrint, filename);
				}
			}else if(identifyPage.equals("taxSummaryReport")) {
				logger.info("Excel File taxSummaryReport !!");
				
				
				List<TaxSummaryEntity> list = findTaxSummaryReport();
				if(list.isEmpty()) {
					
					
					return filename;
				}else {
				File file = ResourceUtils.getFile(jrxmlPath + "taxSummary.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);

//				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
//				filename = "InvoiceSummary_" + timeStamp + ".xlsx";
				
				String finacialYear= report.getTimePeiod();
				
				String StartDate =  "0104"+finacialYear.substring(0, 4);
				String LastDate =  "3103"+finacialYear.substring(5);
				filename = "TaxSummary_"+StartDate+"_"+LastDate+".xlsx";
				
				
				/*
				 * String fileName = "InvoiceSummary_" + dateFrame.getFromDate().replace("-",
				 * "").substring(0, 6) + "_" + dateFrame.getToDate().replace("-",
				 * "").substring(0, 6) + "_" + (new SimpleDateFormat("dd-MM-yyyy")).format(new
				 * Date()).replace("-", "").substring(0, 6); filename =fileName+".xlsx";
				 */

				xlsx(jasperPrint, filename);
				}
			}else if(identifyPage.equals("taxCalReport")) {
				logger.info("Excel File taxCalReport !!");
				List<TaxEntity> list = findTaxCalReport();
				if(list.isEmpty()) {
					return filename;
				}else {
				File file = ResourceUtils.getFile(jrxmlPath + "taxCalculation.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);

				//Sharan Change-01-11-2020				
				String quarter= report.getQuarter();
				String finacialYear= report.getYear();
					
				if(quarter.equalsIgnoreCase("Q1")) {
					String Q1StartDate =  "0104"+finacialYear.substring(0, 4);
					//System.out.println("Q1StartDate "+ Q1StartDate);
					String Q1LastDate =  "3006"+finacialYear.substring(0, 4);
					//System.out.println("Q1LastDate "+ Q1LastDate);
					filename = "TaxCalculation_"+Q1StartDate+"_"+Q1LastDate+".xlsx";
					//System.out.println("FileName "+ filename);
					
				}else if(quarter.equalsIgnoreCase("Q2")) {
					String Q2StartDate =  "0107"+finacialYear.substring(0, 4);
					String Q2LastDate =  "3009"+finacialYear.substring(0, 4);
					filename = "TaxCalculation_"+Q2StartDate+"_"+Q2LastDate+".xlsx";
				}else if(quarter.equalsIgnoreCase("Q3")) {
					String Q3StartDate =  "0110"+finacialYear.substring(0, 4);
					String Q3LastDate =  "3112"+finacialYear.substring(0, 4);
					
					filename = "TaxCalculation_"+Q3StartDate+"_"+Q3LastDate+".xlsx";
					
				}else if(quarter.equalsIgnoreCase("Q4")) {
					String Q4StartDate =  "0101"+finacialYear.substring(5);
					String Q4LastDate =  "3103-"+finacialYear.substring(5);
					filename = "TaxCalculation_"+Q4StartDate+"_"+Q4LastDate+".xlsx";
				}else {
					logger.info("No Quater Period selected");
				}
				
				
				
				/*
				 * String finacialYear= report.getTimePeiod();
				 * 
				 * String StartDate = "0104"+finacialYear.substring(0, 4); String LastDate =
				 * "3103"+finacialYear.substring(5); filename =
				 * "TaxSummary_"+StartDate+"_"+LastDate+".pdf";
				 */
				
			
				xlsx(jasperPrint, filename);
				}
			}


			else if (identifyPage.equals("realTimeToday")) {
				logger.info("Excel File realTimeToday !!");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date curDate = new Date();
				String todayDate = sdf.format(curDate);
				List<RealTimeTransactionDto> list = findAllDateWiseRealtimeTxn(todayDate);
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
				File file = ResourceUtils.getFile(jrxmlPath + "realTimeToday.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				//String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				//filename = "RealTimeTxnToday_" + timeStamp + ".xlsx";
				String fileName = "RealTimeTxnToday_" + todayDate.replace("-", "").substring(0, 6) + "_"
						+ todayDate.replace("-", "").substring(0, 6) + "_"
						+ (new SimpleDateFormat("dd-MM-yyyy")).format(new Date()).replace("-", "").substring(0, 6);
				filename =fileName+".xlsx";
				xlsx(jasperPrint, filename);
					}
			} else if (identifyPage.equals("realTimeYesterday")) {
				logger.info("Excel File RealTimeYesterday !!");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date curDate = new Date();
				curDate.setTime(curDate.getTime() - 24 * 60 * 60 * 1000);
				String yesterdayDate = sdf.format(curDate);
				logger.info("Excel File RealTimeYesterday yesterdayDate::" + yesterdayDate);

				List<RealTimeTransactionDto> list = findAllDateWiseRealtimeTxn(yesterdayDate);
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
				File file = ResourceUtils.getFile(jrxmlPath + "realTimeYesterday.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				//String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				//filename = "RealTimeTxnYesterday_" + timeStamp + ".xlsx";
				String fileName = "RealTimeTxnYesterday_" + yesterdayDate.replace("-", "").substring(0, 6) + "_"
						+ yesterdayDate.replace("-", "").substring(0, 6) + "_"
						+ (new SimpleDateFormat("dd-MM-yyyy")).format(new Date()).replace("-", "").substring(0, 6);
				filename =fileName+".xlsx";
				xlsx(jasperPrint, filename);
					}
			} else if (identifyPage.equals("zeroTxnKoisk")) {
				logger.info("Excel File ZeroTxnKoisk !!");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date curDate = new Date();
				String fromdate = sdf.format(curDate);
				String todate = sdf.format(curDate);

				List<ZeroTransactionKiosksDto> list = findAllZeroTxnKoisk(fromdate, todate);
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
				File file = ResourceUtils.getFile(jrxmlPath + "zeroTxnKiosk.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				//String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				//filename = "ZeroTxnKoisk_" + timeStamp + ".xlsx";
				String fileName ="";
				if(!dateFrame.getFromDate().isEmpty() && !dateFrame.getToDate().isEmpty()) {
				fileName = "ZeroTxnKoisk_" + dateFrame.getFromDate().replace("-", "").substring(0, 6) + "_"
						+ dateFrame.getToDate().replace("-", "").substring(0, 6) + "_"
						+ (new SimpleDateFormat("dd-MM-yyyy")).format(new Date()).replace("-", "").substring(0, 6);
				}else {
					
					  
					 fileName = "ZeroTxnKoisk_" + fromdate.replace("-", "").substring(0, 6) + "_"
							+ todate.replace("-", "").substring(0, 6) + "_"
							+ (new SimpleDateFormat("dd-MM-yyyy")).format(new Date()).replace("-", "").substring(0, 6);
				}
				filename =fileName+".xlsx";

				xlsx(jasperPrint, filename);
					}
			} else if (identifyPage.equals("errorReporting")) {
				logger.info("PDF File ErrorReporting !!");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date curDate = new Date();
				String fromdate = sdf.format(curDate);
				String todate = sdf.format(curDate);

				List<ErrorReportingDto> list = findAllErrorReprting(fromdate, todate);
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
				File file = ResourceUtils.getFile(jrxmlPath + "errorReporting.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
			//	String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
			//	filename = "ErrorReporting_" + timeStamp + ".xlsx";
				
				String fileName ="";
				if(!dateFrame.getFromDate().isEmpty() && !dateFrame.getToDate().isEmpty()) {
				fileName = "ErrorReporting_" + dateFrame.getFromDate().replace("-", "").substring(0, 6) + "_"
						+ dateFrame.getToDate().replace("-", "").substring(0, 6) + "_"
						+ (new SimpleDateFormat("dd-MM-yyyy")).format(new Date()).replace("-", "").substring(0, 6);
				}else {
					
					  
					 fileName = "ErrorReporting_" + fromdate.replace("-", "").substring(0, 6) + "_"
							+ todate.replace("-", "").substring(0, 6) + "_"
							+ (new SimpleDateFormat("dd-MM-yyyy")).format(new Date()).replace("-", "").substring(0, 6);
				}
				filename =fileName + ".xlsx";
				xlsx(jasperPrint, filename);
					}
			}
			
			else if (identifyPage.equals("tiketHistory")) {
				logger.info("PDF File tiketHistory !!");

				List<TicketHistoryDto> list = findTicketHistoryReport();
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
				File file = ResourceUtils.getFile(jrxmlPath + "ticketHistory.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "TicketHistory_" + timeStamp + ".xlsx";
				//String fileName = "TicketHistory_" + dateFrame.getFromDate().replace("-", "").substring(0, 6) + "_"
				//		+ dateFrame.getToDate().replace("-", "").substring(0, 6) + "_"
				//		+ (new SimpleDateFormat("dd-MM-yyyy")).format(new Date()).replace("-", "").substring(0, 6);
				//filename =fileName+".xlsx";
				xlsx(jasperPrint, filename);
					}
			}
			else if (identifyPage.equals("downTime")) {
				logger.info("Excel File downTime !!");

				List<DownTimeDto> list = findDownTimeReport();
				 if(list.isEmpty()) {
						
						
						return filename;
					}else {
				File file = ResourceUtils.getFile(jrxmlPath + "downTime.jrxml");
				InputStream input = new FileInputStream(file);
				jasperReport = JasperCompileManager.compileReport(input);
				source = new JRBeanCollectionDataSource(list);
				Map<String, Object> parameters = new HashMap<>();
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
				String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
				filename = "DownTime_" + timeStamp + ".xlsx";
				/*
				 * SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); Date curDate = new
				 * Date(); String todayDate = sdf.format(curDate); String fromDate =
				 * sdf.format(curDate); String fileName ="";
				 * if(!dateFrame.getFromDate().isEmpty() && !dateFrame.getToDate().isEmpty()) {
				 * fileName = "DownTime_" + dateFrame.getFromDate().replace("-",
				 * "").substring(0, 6) + "_" + dateFrame.getToDate().replace("-",
				 * "").substring(0, 6) + "_" + (new SimpleDateFormat("dd-MM-yyyy")).format(new
				 * Date()).replace("-", "").substring(0, 6); }else {
				 * 
				 * 
				 * fileName = "DownTime_" + fromDate.replace("-", "").substring(0, 6) + "_" +
				 * todayDate.replace("-", "").substring(0, 6) + "_" + (new
				 * SimpleDateFormat("dd-MM-yyyy")).format(new Date()).replace("-",
				 * "").substring(0, 6); } filename =fileName + ".xlsx";
				 */
				
				
				xlsx(jasperPrint, filename);
					}
			}else if (identifyPage.equals("terminalStatus")) {
			
			 logger.info("Excel File Terminal Status !!");

		List<TerminalStatusDto> list = findTerminalStatusReport();
		 if(list.isEmpty()) {
				
				
				return filename;
			}else {
		File file = ResourceUtils.getFile(jrxmlPath + "terminalStatus.jrxml");
		InputStream input = new FileInputStream(file);
		jasperReport = JasperCompileManager.compileReport(input);
		source = new JRBeanCollectionDataSource(list);
		Map<String, Object> parameters = new HashMap<>();
		jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
		String timeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(Calendar.getInstance().getTime());
		filename = "TerminalStatus_" + timeStamp + ".xlsx";
		//String fileName = "TerminalStatus_" + dateFrame.getFromDate().replace("-", "").substring(0, 6) + "_"
			//	+ dateFrame.getToDate().replace("-", "").substring(0, 6) + "_"
				//+ (new SimpleDateFormat("dd-MM-yyyy")).format(new Date()).replace("-", "").substring(0, 6);
		//filename =fileName+".xlsx";
		xlsx(jasperPrint, filename);
	}
			
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

			
			  if (dto.getBranchCode() != null) { BranchMaster branchMaster =
			  branchMasterRepository.findByBranchCode(dto.getBranchCode()); if
			  (branchMaster != null && branchMaster.getCircleName() != null &&
			  branchMaster.getCircleName() != "") { dto.setCircle(branchMaster.getCircleName()); }
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

			
			  if (dto.getBranchCode() != null) { BranchMaster branchMaster =
			  branchMasterRepository.findByBranchCode(dto.getBranchCode()); if
			  (branchMaster != null && branchMaster.getCircleName() != null &&
			  branchMaster.getCircleName() != "") { dto.setCircle(branchMaster.getCircleName()); }
			  }
			 
		}
		return entities;
	}

	@Override
	public List<TicketCentorDto> findAllTickets() {
		logger.info("Inside==Jasper====findAllTickets===========ALL DATA");
		List<TicketCentorDto> entities = ObjectMapperUtils.mapAll(ticketCentorRepo.findAll(), TicketCentorDto.class);
/*
		for (TicketCentorDto dto : entities) {
			String kioskId = dto.getKisokId();
			String kioskBranchMaster = kioskMasterRepo.findKioskByKioskId_circle(kioskId);
			dto.setServeriry(kioskBranchMaster);
		} */
		
		 String circle=null;
			for (TicketCentorDto dto : entities) {
				String kioskId = dto.getKisokId();
				//String kioskBranchMaster = kioskMasterRepo.findKioskByBranchCode(kioskId);
				if(kioskId!=null){
					//later
					  String kioskBranchCode= kioskMasterRepo.findKioskByBranchCode(kioskId);
					  circle= branchMastRepository.findCircleByBranchCode(kioskBranchCode);
					  }
					  dto.setServeriry(circle);

			}
		return entities;

	}

	@Override
	public List<TicketCentorDto> findAllTicketsForCmf() {
		logger.info("Inside==Jasper====findAllTicketsForCmf===========");
		UserDto user = (UserDto) session().getAttribute("userObj");
		List<TicketCentorDto> entities = ObjectMapperUtils.mapAll(ticketCentorRepo.findAllListByCMFUser(user.getPfId()),
				TicketCentorDto.class);
		 String circle=null;
		for (TicketCentorDto dto : entities) {
			String kioskId = dto.getKisokId();
			//String kioskBranchMaster = kioskMasterRepo.findKioskByBranchCode(kioskId);
			if(kioskId!=null){
				//later
				  String kioskBranchCode= kioskMasterRepo.findKioskByBranchCode(kioskId);
				  circle= branchMastRepository.findCircleByBranchCode(kioskBranchCode);
				  }
				  dto.setServeriry(circle);

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

/*		for (TicketCentorDto dto : entities) {
			String kioskId = dto.getKisokId();
			String kioskBranchMaster = kioskMasterRepo.findKioskByKioskId_circle(kioskId);
			dto.setServeriry(kioskBranchMaster);
		}
		
		*/
		
		    String circle=null;
			for (TicketCentorDto dto : entities) {
				String kioskId = dto.getKisokId();
				//String kioskBranchMaster = kioskMasterRepo.findKioskByBranchCode(kioskId);
				if(kioskId!=null){
					//later
					  String kioskBranchCode= kioskMasterRepo.findKioskByBranchCode(kioskId);
					  circle= branchMastRepository.findCircleByBranchCode(kioskBranchCode);
					  }
					  dto.setServeriry(circle);

			}
			
		return entities;
	}

	@Override
	public List<TicketCentorDto> findAllTicketsByCircle() {
		logger.info("Inside==Jasper====findAllTicketsByCircle===========");
		UserDto user = (UserDto) session().getAttribute("userObj");

		List<TicketCentorDto> entities = ObjectMapperUtils
				.mapAll(ticketCentorRepo.findAllListByCircle(user.getCircle()), TicketCentorDto.class);
/*
		for (TicketCentorDto dto : entities) {
			String kioskId = dto.getKisokId();
			String kioskBranchMaster = kioskMasterRepo.findKioskByKioskId_circle(kioskId);
			dto.setServeriry(kioskBranchMaster);
		} */
		
		
		       String circle=null;
			for (TicketCentorDto dto : entities) {
				String kioskId = dto.getKisokId();
				//String kioskBranchMaster = kioskMasterRepo.findKioskByBranchCode(kioskId);
				if(kioskId!=null){
					//later
					  String kioskBranchCode= kioskMasterRepo.findKioskByBranchCode(kioskId);
					  circle= branchMastRepository.findCircleByBranchCode(kioskBranchCode);
					  }
					  dto.setServeriry(circle);

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
	  
	  
	  

		/*
		 * @Override public List<DownTimeDto> findDownTimeReport() {
		 * logger.info("Inside==Jasper====findTicketHistoryReport==========="); //
		 * logger.info(downtimeReport.getCircle()); //
		 * logger.info(downtimeReport.getCmsCmf()); //
		 * logger.info(downtimeReport.getVendor()); //
		 * logger.info(downtimeReport.getFromDate()); //
		 * logger.info(downtimeReport.getToDate());
		 * 
		 * String circle =downtimeReport.getCircle(); String
		 * cmsCmf=downtimeReport.getCmsCmf(); String vendor =downtimeReport.getVendor();
		 * String fromDate=downtimeReport.getFromDate(); String
		 * toDate=downtimeReport.getToDate();
		 * 
		 * 
		 * if (downtimeReport.getCircle().equals("0") ||
		 * downtimeReport.getCircle().equals("undefined")) { circle="";
		 * 
		 * } if(downtimeReport.getCmsCmf().equals("0") ||
		 * downtimeReport.getCmsCmf().equals("undefined") ) { cmsCmf=""; }
		 * if(downtimeReport.getFromDate().isEmpty()) { fromDate=""; }
		 * 
		 * 
		 * if(downtimeReport.getToDate().isEmpty()) { toDate=""; }
		 * 
		 * if (downtimeReport.getVendor().equals("0") ||
		 * downtimeReport.getVendor().equals("undefined")) { vendor = ""; }
		 * 
		 * List<DownTime> list = null;
		 * list=downTimePagingRepo.findAllByFilterDTimeReports(toDate, fromDate,
		 * circle,vendor ,cmsCmf); List<DownTimeDto> entities =
		 * ObjectMapperUtils.mapAll(list, DownTimeDto.class);
		 * logger.info("PDF entities "+entities); return entities; }
		 */
	
	  
	  

		@Override
		public List<DownTimeDto> findDownTimeReport() {
			 logger.info("Inside==Jasper====findDownTimeReport===========");
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
			  List<DownTimeDto> entities =null;
			  
			  try {
				  
//			  if(!downtimeReport.getFromDate().equals("null") && !downtimeReport.getFromDate().isEmpty() && !downtimeReport.getFromDate().equals("undefined")) {
//					fromDate="";
//				}
//				
//				
//				if(downtimeReport.getToDate().equals("null")  && !downtimeReport.getToDate().isEmpty() && !downtimeReport.getToDate().equals("undefined"))  {
//					toDate=toDate;
//				}
			  
			  
			  
			  
			  
			  
			  if( !downtimeReport.getFromDate().isEmpty() && !downtimeReport.getFromDate().equals("undefined") && !downtimeReport.getToDate().isEmpty() && !downtimeReport.getToDate().equals("undefined")) {
				  
			 
					
					if (downtimeReport.getCircle().equals("0") || downtimeReport.getCircle().equals("undefined")) {
						circle="";
						
					}
					if(downtimeReport.getCmsCmf().equals("undefined") ) {
						cmsCmf="";
					}
//					if(!downtimeReport.getFromDate().equals("null") && !downtimeReport.getFromDate().isEmpty() && !downtimeReport.getFromDate().equals("undefined")) {
//						fromDate="";
//					}
//					
//					
//					if(downtimeReport.getToDate().equals("null")  && !downtimeReport.getToDate().isEmpty() && !downtimeReport.getToDate().equals("undefined"))  {
//						toDate=toDate;
//					}
					
					if (downtimeReport.getVendor().equals("0") || downtimeReport.getVendor().equals("undefined")) {
						vendor = "";
					}
					
					
				  List<DownTime> list = null;
				  list=downTimePagingRepo.findAllByFilterDTimeReports(toDate, fromDate, circle,vendor ,cmsCmf);
				  logger.info("list downtime ...PDF  "+list);
				  
				  if(list!=null && !list.isEmpty()) {
				   entities = ObjectMapperUtils.mapAll(list, DownTimeDto.class);
				  logger.info("PDF entities "+entities);
				  }
					
			  }else {
				    circle = downtimeReport.getCircle();
					cmsCmf = downtimeReport.getCmsCmf();
					vendor = downtimeReport.getVendor();
					//fromDate = downtimeReport.getFromDate();
					//toDate = downtimeReport.getToDate();
				 
				  SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
					Date curDate=new Date();
					String date=sdf.format(curDate);
					if(downtimeReport.getFromDate().equals("undefined") || downtimeReport.getFromDate().isEmpty()) {
						fromDate=date;	
					}

					if(downtimeReport.getToDate().equals("undefined") || downtimeReport.getToDate().isEmpty()) {
						toDate=date;	
					}
					
					 List<DownTime> list = null;
					  list=downTimePagingRepo.findAllByFilterDTimeReports(toDate, fromDate, circle,vendor ,cmsCmf);
					  logger.info("list downtime ...PDF  "+list);
					 // List<DownTimeDto> entities =null;
					  if(list!=null && !list.isEmpty()) {
					   entities = ObjectMapperUtils.mapAll(list, DownTimeDto.class);
					  logger.info("PDF entities "+entities);
					  }
					
			  }
			  }catch (Exception e) {
				  e.printStackTrace();
			}
			return entities;
			  }
		
	
	@Override
	public List<BillingPenaltyDto> findBillingPenaltyReport() {
		logger.info("Inside==Jasper====findBillingPenaltyReport===========");
//		logger.info(report.getCircle());
//		logger.info(report.getState());
//		logger.info(report.getRpfNumber());
//		logger.info(report.getVendor());
//		logger.info(report.getTimePeiod());
		
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
				
				if(circle.equals("0")) {
					list =bpRepository.findbyWithoutStateFilterCCReport(quarter,finacialYear, vendor);
				}else {

					list =bpRepository.findbyWithoutStateFilterReport(circle, quarter,finacialYear, vendor);

				}
				
				
				//list =bpRepository.findbyWithoutStateFilterReport(circle, quarter,finacialYear, vendor);

			} else {
				if(circle.equals("0")) {
					list=bpRepository.findbyFilterRfpWithoutStateCCReport(quarter,finacialYear, vendor, rpfNumber);

				}else {

					list=bpRepository.findbyFilterRfpWithoutStateReport(circle, quarter,finacialYear, vendor, rpfNumber);

				}
				
				
				
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
//		logger.info(report.getCircle());
//		logger.info(report.getState());
//		logger.info(report.getRpfNumber());
//		logger.info(report.getVendor());
//		logger.info(report.getTimePeiod());
		
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
				
				if(circle.equals("0")) {
					list =IgRepository.findbyWithoutStateFilterCCReport(quarter,finacialYear, vendor);
				}else {

					list =IgRepository.findbyWithoutStateFilterReport(circle, quarter,finacialYear, vendor);

				}
				
				
				
				//list =IgRepository.findbyWithoutStateFilterReport(circle, quarter,finacialYear, vendor);

			} else {
				
				
				if(circle.equals("0")) {
					list=IgRepository.findbyFilterRfpWithoutStateCCReport(quarter,finacialYear, vendor, rpfNumber);
				}else {

					list=IgRepository.findbyFilterRfpWithoutStateReport(circle, quarter,finacialYear, vendor, rpfNumber);

				}
				
				
				//list=IgRepository.findbyFilterRfpWithoutStateReport(circle, quarter,finacialYear, vendor, rpfNumber);
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
		logger.info("Inside==Jasper====findInvoiceCompareReport===========");
//		logger.info(report.getCircle());
//		logger.info(report.getState());
//		logger.info(report.getRpfNumber());
//		logger.info(report.getVendor());
//		logger.info(report.getTimePeiod());
		
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
				
				if(circle.equals("0")) {
					list =icRepository
							.findbyWithoutStateFilterCCReport(quarter,finacialYear, vendor);
				}else {

					list =icRepository
							.findbyWithoutStateFilterReport(circle, quarter,finacialYear, vendor);

				}
				
				
				
				//list =icRepository.findbyWithoutStateFilterReport(circle, quarter,finacialYear, vendor);

			} else {
				
				
				if(circle.equals("0")) {
					list =icRepository
							.findbyFilterRfpWithoutStateCCReport(quarter,finacialYear, vendor, rpfNumber);
				}else {
					list=icRepository
							.findbyFilterRfpWithoutStateReport(circle, quarter,finacialYear, vendor, rpfNumber);

				}
				
				
				//list=icRepository.findbyFilterRfpWithoutStateReport(circle, quarter,finacialYear, vendor, rpfNumber);
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
		logger.info("Inside==Jasper====findInvoiceSummaryReport==========");
//		logger.info(report.getCircle());
//		logger.info(report.getState());
//		logger.info(report.getTimePeiod());
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
			
			if(circle.equals("0")) {
				list = isRepository.findCCFilterReport(timePeriod);
			}else {
				list = isRepository.findbyWithoutStateFilterReport(circle, timePeriod);
			}
		
			}else{
			
			
				
			list = isRepository.findbyFilterReport(circle, state, timePeriod);
		}
		
	List<InvoiceSummaryDto> entities = ObjectMapperUtils.mapAll(list, InvoiceSummaryDto.class);
		
		return entities;
	}
	
	
	
	@Override
	public List<TerminalStatusDto> findTerminalStatusReport() {
		
		//List<TerminalStatusDto> entities=null;
		List<TerminalStatus> list=(List<TerminalStatus>) terminalStatusRepo.findAll();
		
		logger.info("Inside==Jasper====findTerminalStatusReport==========="+list);
		
		
		
		
		List<KioskBranchMaster> kioskMastlist = null;
		String cmfNameList = null;
		List<TerminalStatusDto> entities =new ArrayList<>();
		for (TerminalStatus ts : list) {
		
			TerminalStatusDto dto=new TerminalStatusDto();
			//    logger.info("entities terminal status===" + entities.getContent());
				kioskMastlist = kioskMasterRepos.findByKioskId(ts.getKioskId());
				 logger.info("kioskMastlist===" + kioskMastlist);
			  for (KioskBranchMaster kioskBranchMast : kioskMastlist) {
				  dto.setKioskSrNo(kioskBranchMast.getKioskSerialNo());
				//	logger.info("kioskId==:: " + kioskBranchMast.getKioskId());
				  dto.setKioskId(kioskBranchMast.getKioskId());
				  dto.setBranchCode(kioskBranchMast.getBranchCode());
					
				}
			  
		//	  logger.info("dto.getKioskId()-------------"+dto.getKioskId());
			   List<BranchMaster> branchMastList = branchMasterRepo.findAllByBranchCode(dto.getBranchCode());
			    logger.info("branchMastList==========" + branchMastList);
				for (BranchMaster branchMast : branchMastList) {
				//	logger.info("Br Code====" + branchMast.getBranchCode());
				//	logger.info("Circle====" + branchMast.getCircle());
					dto.setBranchCode(branchMast.getBranchCode());
					dto.setCircle(branchMast.getCircleName());
					//dto.setBranchCode(branchMast.getBranchCode());
					//dto.setCircle(branchMast.getCircleName());
				}
			  
				
			    cmfNameList = terminalStatusRepository.findByKisoskId(dto.getKioskId());
			    logger.info("CMF User==" + cmfNameList);
			    dto.setCmf(cmfNameList);
				logger.info("dto::::" + dto.toString());
				
				
				  dto.setPbPrinterStatus(ts.getPbPrinterStatus());
				  dto.setCartridgeStatus(ts.getCartridgeStatus());
				  dto.setAgentStatus(ts.getAgentStatus());
				  dto.setAplicationStatus(ts.getAplicationStatus());
				  dto.setRmmsConnectivity(ts.getRmmsConnectivity());
				  dto.setLastPrntTxnDttm(ts.getLastPrntTxnDttm());
				  dto.setLastPmDttm(ts.getLastPmDttm());
				 
				
				 logger.info("dto::::22222224566" + dto);
	             
				
				 entities.add(dto);
	     		//entities.add(dto);
			}
		
		 logger.info("entities:::4444444444444:" + entities);
		 entities = ObjectMapperUtils.mapAll(entities, TerminalStatusDto.class);
         logger.info("dto11111111111::::" + entities);
		
		
		logger.info("terminalStatusReport22222222::::" + entities);
		return entities;
		
	}
	
	
	@Override
	public List<TaxSummaryEntity> findTaxSummaryReport() {
		logger.info("Inside==Jasper====findTaxSummaryReport==========");
//		logger.info(report.getCircle());
//		logger.info(report.getState());
//		logger.info(report.getTimePeiod());
		String circle =null;
		String state=null;
		String timePeriod= null;
		if((report.getCircle()!= "") && (report.getTimePeiod()!= "") ) {
			circle =report.getCircle();
			state=report.getState();
			timePeriod= report.getTimePeiod();
			
		}
		
		List<TaxSummaryEntity> list =null;
		
	if(state.equals("0"))	{
			
			if(circle.equals("0")) {
				list = taxSummaryRepo.findCCFilterReport(timePeriod);
			}else {
				list = taxSummaryRepo.findbyWithoutStateFilterReport(circle, timePeriod);
			}
		
			}else{
			
			
				
			list = taxSummaryRepo.findbyFilterReport(circle, state, timePeriod);
		}
		
	//List<TaxSummaryEntity> entities = ObjectMapperUtils.mapAll(list, InvoiceSummaryDto.class);
		
		return list;
	}
	
	@Override
	public List<TaxEntity> findTaxCalReport() {
		logger.info("Inside==Jasper====findTaxCalReport==========");
		
		List<TaxCalculationDto> taxList = null;		
		List<TaxCalculationDto> taxList2 = null;		
		List<TaxEntity> taxEntity = null;



		if(report.getGstType().equalsIgnoreCase("IGST")) {
			taxList= ObjectMapperUtils.mapAll(
					taxReportService.getTaxCalculation(report, 1, "IGST"), TaxCalculationDto.class);
			
			taxEntity= ObjectMapperUtils.mapAll(taxList, TaxEntity.class);
			
		}else {
			
			taxList = ObjectMapperUtils.mapAll(taxReportService.getTaxCalculation(report, 1, "CGST"), TaxCalculationDto.class);

			taxList2 = ObjectMapperUtils.mapAll(taxReportService.getTaxSGST(report, 1, "SGST"), TaxCalculationDto.class);

			taxList.addAll(taxList2);
			taxEntity= ObjectMapperUtils.mapAll(taxList, TaxEntity.class);
		}

		
		
	//	List<TaxEntity> list =null;
	
		return taxEntity;
	}
	
	

}