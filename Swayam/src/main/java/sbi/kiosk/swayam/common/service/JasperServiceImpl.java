/*package sbi.kiosk.swayam.common.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import sbi.kiosk.swayam.common.constants.Constants;
import sbi.kiosk.swayam.common.dto.KioskBranchMasterUserDto;
import sbi.kiosk.swayam.common.dto.TicketCentorDto;
import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.dto.UserManagementDto;
import sbi.kiosk.swayam.common.entity.BranchMaster;
import sbi.kiosk.swayam.common.entity.Supervisor;
import sbi.kiosk.swayam.common.entity.User;
import sbi.kiosk.swayam.common.entity.UserKioskMapping;
import sbi.kiosk.swayam.common.repository.KioskMasterRepository;
import sbi.kiosk.swayam.common.repository.SupervisorRepository;
import sbi.kiosk.swayam.common.repository.UserRepository;
import sbi.kiosk.swayam.common.repository.UserRepositoryPaging;
import sbi.kiosk.swayam.common.utils.ObjectMapperUtils;
import sbi.kiosk.swayam.healthmonitoring.repository.TicketCentorRepository;
import sbi.kiosk.swayam.kioskmanagement.repository.BranchMasterRepository;
import sbi.kiosk.swayam.kioskmanagement.repository.UserKioskMappingRepository;


@Service
public class JasperServiceImpl implements JasperService{
	
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
			  logger.info("jrxmlPath "+jrxmlPath);
			  logger.info("reportPath "+reportPath);
		       if(identifyPage.equals("userListSA")){
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
		       }
		       else if(identifyPage.equals("userListLA")){
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
			    }
		       else if(identifyPage.equals("kioskManagementByCircle")){
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
			   }
		       else if(identifyPage.equals("kiosksAll")){
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
			   }
		       else if(identifyPage.equals("ticketCenterCC")){
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
			   }
		       else if(identifyPage.equals("ticketCenterCMF")){
		    	      List<TicketCentorDto> list = findAllTicketsForCmf(); 
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
		       else if(identifyPage.equals("ticketCenterCMS")){
		    	      List<TicketCentorDto> list = findAllTicketsForCms(); 
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
		       else if(identifyPage.equals("ticketCenterCU")){
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
			          
		             //xlsx(jasperPrint);
		       logger.info("PDF File Generated !!");
		       return filename;
		
		         } catch (Exception e) {
		        	 logger.error("Exception is "+e.getMessage());
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
			  logger.info("jrxmlPath "+jrxmlPath);
			  logger.info("reportPath "+reportPath);
		       if(identifyPage.equals("userListSA")){
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
		       }
		       else if(identifyPage.equals("userListLA")){
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
		       }
		       else if(identifyPage.equals("kioskManagementByCircle")){
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
		       }
		       else if(identifyPage.equals("kiosksAll")){
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
		       }
		       else if(identifyPage.equals("ticketCenterCC")){
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
		       }
		       else if(identifyPage.equals("ticketCenterCMF")){
		    	   List<TicketCentorDto> list = findAllTicketsForCmf(); 
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
		       else if(identifyPage.equals("ticketCenterCMS")){
		    	   List<TicketCentorDto> list = findAllTicketsForCms(); 
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
		       else if(identifyPage.equals("ticketCenterCU")){
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
		       logger.info("Excel File Generated !!");
		       return filename;
		
		         } catch (Exception e) {
		        	 logger.error("Exception is "+e.getMessage());
		             return e.getMessage();
		 
		         }
		 
		     }

	 
	 private void xlsx(JasperPrint jasperPrint, String filename) throws JRException {
		 
		         // Exports a JasperReports document to XLSX format. It has character output type and exports the document to a grid-based layout.
		 
		         JRXlsxExporter exporter = new JRXlsxExporter();
		 
		         exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		 
		         exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(reportPath + filename));
		
		         SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
		 
		         //configuration.setOnePagePerSheet(true);
		 
		         configuration.setRemoveEmptySpaceBetweenColumns(true);
		 
		         configuration.setDetectCellType(true);
		 
		         exporter.setConfiguration(configuration);
		
		         exporter.exportReport();
		 
		     }
		 
	
	@Override
    public List<UserManagementDto> findUsersBySA() {	 
	logger.info("Inside==Jasper====findUsersBySA===========");
	 List<UserManagementDto> entities = ObjectMapperUtils.mapAll(userRepo.findByEnabled("1"), UserManagementDto.class);
	 if(entities !=null){
		 for(UserManagementDto dto:entities){
			 if(Constants.SYSTEMADMIN.getCode().equals(dto.getRole())){
				 dto.setRole(Constants.SYSTEMADMIN.getValue());
			 }
			 if(Constants.LOCALADMIN.getCode().equals(dto.getRole())){
				 dto.setRole(Constants.LOCALADMIN.getValue());
			 }
			 if(Constants.CIRCLE.getCode().equals(dto.getRole())){
				 dto.setRole(Constants.CIRCLE.getValue());
			 }
			 if(dto.getPfId() !=null && dto.getPfId() !="" && dto.getRole().equals("CMF")){
				 int kioskCountCmf = userKioskMappingRepository.findKiosksCountByPfId(dto.getPfId());
				 dto.setNoOfAssignedKiosks(String.valueOf(kioskCountCmf));
			 }
			 else if(dto.getPfId() !=null && dto.getPfId() !="" && dto.getRole().equals("CMS")){
				 int total = 0;
				 List<Supervisor> supList =  supervisorRepository.findBySupervisorPfId(dto.getPfId());
				 
				 if(supList !=null && supList.size() > 0){
					 for(Supervisor sup:supList){
						 int kioskCountCmf = userKioskMappingRepository.findKiosksCountByPfId(sup.getPfId());
						 total = total + kioskCountCmf;
					 }
				 }
				 if(total !=0){
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
	// Page<User> userList = userRepositoryPagingRepo.findAll(PageRequest.of(page, size));
	 UserDto user = (UserDto) session().getAttribute("userObj");
	 List<String> roleList= new ArrayList<String>();
	 //roleList.add("LA");
	 roleList.add("SA");
	 roleList.add("CC");
	 
	 List<UserManagementDto> entities = ObjectMapperUtils.mapAll(userRepo.findByCircleAndEnabledAndRoleNotIn(user.getCircle(),"1",roleList), UserManagementDto.class);
	 
	 if(entities !=null){
		 for(UserManagementDto dto:entities){				 
			 if(Constants.LOCALADMIN.getCode().equals(dto.getRole())){
				 dto.setRole(Constants.LOCALADMIN.getValue());
			 }
			 if(Constants.CIRCLE.getCode().equals(dto.getRole())){
				 dto.setRole(Constants.CIRCLE.getValue());
			 }
			 if(dto.getPfId() !=null && dto.getPfId() !="" && dto.getRole().equals("CMF")){
				 int kioskCountCmf = userKioskMappingRepository.findKiosksCountByPfId(dto.getPfId());
				 dto.setNoOfAssignedKiosks(String.valueOf(kioskCountCmf));
			 }
			 else if(dto.getPfId() !=null && dto.getPfId() !="" && dto.getRole().equals("CMS")){
				 int total = 0;
				 List<Supervisor> supList =  supervisorRepository.findBySupervisorPfId(dto.getPfId());
				 
				 if(supList !=null && supList.size() > 0){
					 for(Supervisor sup:supList){
						 int kioskCountCmf = userKioskMappingRepository.findKiosksCountByPfId(sup.getPfId());
						 total = total + kioskCountCmf;
					 }
				 }
				 if(total !=0){
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
		
		List<KioskBranchMasterUserDto> entities = ObjectMapperUtils.mapAll(kioskMasterRepository.findAllByCircle(user.getCircle()), KioskBranchMasterUserDto.class);	
	 	 
	 for(KioskBranchMasterUserDto dto:entities){
		 UserKioskMapping us = userKioskMappingRepository.findByKioskId(String.valueOf(dto.getKioskId()));
		 
		 if(us !=null && us.getPfId() !=null && us.getPfId() !=""){
			 dto.setPfId(us.getPfId());
			 User usr = userRepo.findByPfId(us.getPfId());
			 if(usr !=null && usr.getUsername() !=null && usr.getUsername() !=""){
				 dto.setUsername(usr.getUsername());
			 }
		 }
		 
		 if(dto.getBranchCode() !=null){
			 BranchMaster branchMaster	 = branchMasterRepository.findByBranchCode(dto.getBranchCode());
			 if(branchMaster !=null && branchMaster.getCircle() !=null && branchMaster.getCircle() !=""){
				 dto.setCircle(branchMaster.getCircle());
			 }
		 }
	 }
	 return entities;
	}
	
	@Override
    public List<KioskBranchMasterUserDto> findAllKiosks() {	 	 
		logger.info("Inside==Jasper====findAllKiosks===========");
		List<KioskBranchMasterUserDto> entities = ObjectMapperUtils.mapAll(kioskMasterRepository.findAll(), KioskBranchMasterUserDto.class);
	 	 
	 for(KioskBranchMasterUserDto dto:entities){
		 UserKioskMapping us = userKioskMappingRepository.findByKioskId(String.valueOf(dto.getKioskId()));
		 
		 if(us !=null && us.getPfId() !=null && us.getPfId() !=""){
			 dto.setPfId(us.getPfId());
			 User usr = userRepo.findByPfId(us.getPfId());
			 if(usr !=null && usr.getUsername() !=null && usr.getUsername() !=""){
				 dto.setUsername(usr.getUsername());
			 }
		 }
		 
		 if(dto.getBranchCode() !=null){
			 BranchMaster branchMaster	 = branchMasterRepository.findByBranchCode(dto.getBranchCode());
			 if(branchMaster !=null && branchMaster.getCircle() !=null && branchMaster.getCircle() !=""){
				 dto.setCircle(branchMaster.getCircle());
			 }
		 }
	 }
	 return entities;
    }
	
	@Override
	 public List<TicketCentorDto> findAllTickets(){
		 logger.info("Inside==Jasper====findAllTickets===========ALL DATA");
		 List<TicketCentorDto> entities = ObjectMapperUtils.mapAll(ticketCentorRepo.findAll(), TicketCentorDto.class);		 
		 
		 for(TicketCentorDto dto:entities){
			 
			 String kioskId=dto.getKisokId();			 
			 String kioskBranchMaster= kioskMasterRepo.findKioskByKioskId_circle(kioskId);
			 dto.setServeriry(kioskBranchMaster);
			 
		 }		 
		 return entities;
		 
	 }
	
	@Override
	 public List<TicketCentorDto> findAllTicketsForCmf(){
		logger.info("Inside==Jasper====findAllTicketsForCmf===========");
		UserDto user = (UserDto) session().getAttribute("userObj"); 
		List<TicketCentorDto> entities = ObjectMapperUtils.mapAll(ticketCentorRepo.findAllListByCMFUser(user.getPfId()), TicketCentorDto.class);
				 
		 for(TicketCentorDto dto:entities){			 
			 String kioskId=dto.getKisokId();			 
			 String kioskBranchMaster= kioskMasterRepo.findKioskByKioskId_circle(kioskId);
			 dto.setServeriry(kioskBranchMaster);
			 System.out.println(dto.getKisokId());	
		 }			 
		 return entities;			 
	 }
	
	@Override
	 public List<TicketCentorDto> findAllTicketsForCms(){
		logger.info("Inside==Jasper====findAllTicketsForCms===========");
		UserDto user = (UserDto) session().getAttribute("userObj");
		String supPfId = user.getPfId();
		Set<String> supList =  supervisorRepository.findPfIdListByPfIdSupervisor(supPfId);
		
		List<TicketCentorDto> entities = ObjectMapperUtils.mapAll(ticketCentorRepo.findAllListByCMFUserForCMS(supList), TicketCentorDto.class);
				 
		 for(TicketCentorDto dto:entities){			 
			 String kioskId=dto.getKisokId();			 
			 String kioskBranchMaster= kioskMasterRepo.findKioskByKioskId_circle(kioskId);
			 dto.setServeriry(kioskBranchMaster);
			 System.out.println(dto.getKisokId());	 
			 
		 }			 
		 return entities;			 
	 }
	
	@Override
	 public List<TicketCentorDto> findAllTicketsByCircle(){
		logger.info("Inside==Jasper====findAllTicketsByCircle===========");
		 UserDto user = (UserDto) session().getAttribute("userObj");
		 
		 List<TicketCentorDto> entities = ObjectMapperUtils.mapAll(ticketCentorRepo.findAllListByCircle(user.getCircle()), TicketCentorDto.class);
		 
		 for(TicketCentorDto dto:entities){			 
			 String kioskId=dto.getKisokId();			 
			 String kioskBranchMaster= kioskMasterRepo.findKioskByKioskId_circle(kioskId);
			 dto.setServeriry(kioskBranchMaster);
			 System.out.println(dto.getKisokId());			 
		 }
		 return entities;		 
	 }
	
}
*/