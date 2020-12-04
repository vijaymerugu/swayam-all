package sbi.kiosk.swayam.kioskmanagement.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import antlr.StringUtils;
import sbi.kiosk.swayam.common.constants.ExceptionConstants;
import sbi.kiosk.swayam.common.dto.CbsBrhmDto;
import sbi.kiosk.swayam.common.dto.HolidayCalendarDto;
import sbi.kiosk.swayam.common.dto.InvoiceVendorDto;
import sbi.kiosk.swayam.common.dto.KioskCMFDto;
import sbi.kiosk.swayam.common.dto.KioskDto;
import sbi.kiosk.swayam.common.entity.BranchMaster;
import sbi.kiosk.swayam.common.entity.HolidayCalendar;
import sbi.kiosk.swayam.common.entity.InvoiceVendor;
import sbi.kiosk.swayam.common.entity.KioskBranchMaster;
import sbi.kiosk.swayam.common.entity.UserKioskMapping;//
import sbi.kiosk.swayam.kioskmanagement.repository.BranchMasterRepository;
import sbi.kiosk.swayam.kioskmanagement.repository.HolidayCalendarRepository;
import sbi.kiosk.swayam.kioskmanagement.repository.InvoiceVendorRepository;
import sbi.kiosk.swayam.kioskmanagement.repository.KioskCMFRepository;
import sbi.kiosk.swayam.kioskmanagement.repository.UserKioskMappingRepository;
import sbi.kiosk.swayam.kioskmanagement.repository.kioskMasterManagementRepository;


@Service
public class UploadServiceImpl implements UploadService {
	
	Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);

	@Autowired
	private kioskMasterManagementRepository kioskMasterManagementRepository;
	@Autowired
	private BranchMasterRepository branchMasterRepository;

	

	@Autowired
	private HolidayCalendarRepository holidayCalendarRepository;

	@Autowired
	private KioskCMFRepository kioskCMFRepository;
	
	@Autowired
	private InvoiceVendorRepository invoiceVendorRepository;
	
	@Autowired
	private UserKioskMappingRepository userKioskMappingRepository;
	
	@Value("${report.path}")		
	private String reportPath1;

	

	public FileInputStream inputStream;
	public Workbook workbook;

	public static ResourceBundle rb;
//Kiosk Master
	@Override
	public String uploadKioskInformation(String path) {
		// upload kiosk file information
		
		Map<String, String> map = new HashMap();
		try {

			// By Ankur 28-04-2020-----------STARTS---------

			/*
			 * rb = ResourceBundle.getBundle("rb"+rb);
			 * rb.getString("kioskFilepath");
			 */

			inputStream = new FileInputStream(path);
			logger.info("7 A.file read successfully!! "+ path);
			// new File("C:\\Users\\Admin\\Downloads\\Swayam_Kiosk_Information.xlsx"));

			// -------By Ankur END---------------------------

			workbook = new XSSFWorkbook(inputStream);

			logger.info("7 B.workbook parsing started!!");
			// By Ankur 28-04-2020-----------STARTS---------

			/* HashMap<Integer,String> map = map=new HashMap<Integer,String>(); */

			// -------By Ankur END---------------------------

			org.apache.poi.ss.usermodel.Sheet firstSheet = workbook.getSheetAt(0);
			DataFormatter objDefaultFormat = new DataFormatter();
			FormulaEvaluator objFormulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);
			
			Iterator<Row> iterator = firstSheet.iterator();
			List<KioskDto> lidtDto = new ArrayList<>();
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				KioskDto dto = new KioskDto();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					objFormulaEvaluator.evaluate(cell);
					
					if(String.valueOf(cell.getRow().getRowNum()).equals("0")) 
					{
						/*
						 * if(String.valueOf(cell.getColumnIndex()).equals("0")) {
						 * if(!((String.valueOf(cell.getColumnIndex()).equals("0")) &&
						 * (cell.getStringCellValue().equalsIgnoreCase("ID")))) {
						 * logger.error("Wrong File or Data Sequence for upload!!");
						 * 
						 * return "Wrong File or Data Sequence for upload"; } }
						 */
						if(String.valueOf(cell.getColumnIndex()).equals("1")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("1")) && (cell.getStringCellValue().equalsIgnoreCase("KIOSK_ID"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						} 
						if (String.valueOf(cell.getColumnIndex()).equals("1")) {

							dto.setKioskID(cell.getStringCellValue());

						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("2")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("2")) && (cell.getStringCellValue().equalsIgnoreCase("VENDOR"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						} 
						if (String.valueOf(cell.getColumnIndex()).equals("2")) {

							dto.setVendor(cell.getStringCellValue());

						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("3")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("3")) && (cell.getStringCellValue().equalsIgnoreCase("INSTALLATION_DATE"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("3")) {

							dto.setInstallationDate(cell.getStringCellValue());

						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("4")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("4")) && (cell.getStringCellValue().equalsIgnoreCase("KIOSK_IP"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("4")) {

							dto.setKioskIPAddress(cell.getStringCellValue());

						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("5")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("5")) && (cell.getStringCellValue().equalsIgnoreCase("KIOSK_MAC_ADDRESS"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("5")) {

							dto.setKioskMacAddress(cell.getStringCellValue());

						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("6")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("6")) && (cell.getStringCellValue().equalsIgnoreCase("SITE_TYPE"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("6")) {

							dto.setSiteType(cell.getStringCellValue());

						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("7")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("7")) && (cell.getStringCellValue().equalsIgnoreCase("LOCATION"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("7")) {

							dto.setLocation(cell.getStringCellValue());

						}
						}
						
						if(String.valueOf(cell.getColumnIndex()).equals("8")) {
							if(!((String.valueOf(cell.getColumnIndex()).equals("8")) && (cell.getStringCellValue().equalsIgnoreCase("ADDRESS"))))
							{
								logger.error("Wrong File or Data Sequence for upload!!");
								
								return "Wrong File or Data Sequence for upload";
							}
							if (String.valueOf(cell.getColumnIndex()).equals("8")) {

								dto.setAddress(cell.getStringCellValue());

							}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("9")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("9")) && (cell.getStringCellValue().equalsIgnoreCase("BRANCH_CODE"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						} 
						if (String.valueOf(cell.getColumnIndex()).equals("9")) {

							dto.setBranchCode(cell.getStringCellValue());

						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("10")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("10")) && (cell.getStringCellValue().equalsIgnoreCase("KIOSK_SERIAL_NO"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("10")) {

							dto.setKioskSerialNumber(cell.getStringCellValue());

						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("11")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("11")) && (cell.getStringCellValue().equalsIgnoreCase("CIRCLE"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("11")) {

							dto.setCircle(cell.getStringCellValue());

						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("12")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("12")) && (cell.getStringCellValue().equalsIgnoreCase("BRANCH_NAME"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("12")) {

							dto.setBranchName(cell.getStringCellValue());

						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("13")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("13")) && (cell.getStringCellValue().equalsIgnoreCase("OS"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("13")) {

							dto.setoS(cell.getStringCellValue());

						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("14")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("14")) && (cell.getStringCellValue().equalsIgnoreCase("INSTALLATION_STATUS"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("14")) {

							dto.setInstallationStatus(cell.getStringCellValue());

						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("15")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("15")) && (cell.getStringCellValue().equalsIgnoreCase("INSTALLATION_TYPE"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("15")) {

							dto.setInstallationType(cell.getStringCellValue());

						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("16")) {
							if(!((String.valueOf(cell.getColumnIndex()).equals("16")) && (cell.getStringCellValue().equalsIgnoreCase("RFP_ID"))))
							{
								logger.error("Wrong File or Data Sequence for upload!!");
								
								return "Wrong File or Data Sequence for upload";
							}
							if (String.valueOf(cell.getColumnIndex()).equals("16")) {

								dto.setRfpID(cell.getStringCellValue());

							}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("17")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("17")) && (cell.getStringCellValue().equalsIgnoreCase("CREATED_BY"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						} 
						
						}
						if(String.valueOf(cell.getColumnIndex()).equals("18")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("18")) && (cell.getStringCellValue().equalsIgnoreCase("CREATED_DATE"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						} 
						}
						if(String.valueOf(cell.getColumnIndex()).equals("19")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("19")) && (cell.getStringCellValue().equalsIgnoreCase("MODIFIED_BY"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("20")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("20")) && (cell.getStringCellValue().equalsIgnoreCase("MODIFIED_DATE"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						}
						
					}
					switch (cell.getCellType()) {

					case STRING:

						if (!(String.valueOf(cell.getRow().getRowNum()).equals("0"))) {
							/*if (String.valueOf(cell.getColumnIndex()).equals("1")) {

								dto.setSrNo(cell.getStringCellValue());

							}*/
							if (String.valueOf(cell.getColumnIndex()).equals("1")) {

								dto.setKioskID(cell.getStringCellValue());

							}
							if (String.valueOf(cell.getColumnIndex()).equals("2")) {

								dto.setVendor(cell.getStringCellValue());

							}
							if (String.valueOf(cell.getColumnIndex()).equals("3")) {
								String cellValueStr = objDefaultFormat.formatCellValue(cell, objFormulaEvaluator);	
								dto.setInstallationDate(cellValueStr);

							}

							if (String.valueOf(cell.getColumnIndex()).equals("4")) {

								dto.setKioskIPAddress(cell.getStringCellValue());

							}
							if (String.valueOf(cell.getColumnIndex()).equals("5")) {

								dto.setKioskMacAddress(cell.getStringCellValue());

							}
							if (String.valueOf(cell.getColumnIndex()).equals("6")) {

								dto.setSiteType(cell.getStringCellValue());

							}
							if (String.valueOf(cell.getColumnIndex()).equals("7")) {

								dto.setLocation(cell.getStringCellValue());

							}

							if (String.valueOf(cell.getColumnIndex()).equals("8")) {

								dto.setAddress(cell.getStringCellValue());

							}
							if (String.valueOf(cell.getColumnIndex()).equals("9")) {

								dto.setBranchCode(cell.getStringCellValue());

							}
							if (String.valueOf(cell.getColumnIndex()).equals("10")) {

								dto.setKioskSerialNumber(cell.getStringCellValue());

							}

							if (String.valueOf(cell.getColumnIndex()).equals("11")) {

								dto.setCircle(cell.getStringCellValue());

							}

							if (String.valueOf(cell.getColumnIndex()).equals("12")) {

								dto.setBranchName(cell.getStringCellValue());

							}

							if (String.valueOf(cell.getColumnIndex()).equals("13")) {

								dto.setoS(cell.getStringCellValue());
							}

							/*
							 * if (String.valueOf(cell.getColumnIndex()).equals("14")) {
							 * 
							 * dto.setMake(cell.getStringCellValue());
							 * 
							 * }
							 */

							if (String.valueOf(cell.getColumnIndex()).equals("14")) {

								dto.setInstallationStatus(cell.getStringCellValue());

							}

							if (String.valueOf(cell.getColumnIndex()).equals("15")) { 

								dto.setRfpID(cell.getStringCellValue());

							}
							if (String.valueOf(cell.getColumnIndex()).equals("16")) {

								dto.setInstallationType(cell.getStringCellValue());

							}
							

						}
						break;
					case BOOLEAN:
				
						break;
					case NUMERIC:
						if (!(String.valueOf(cell.getRow().getRowNum()).equals("0"))) {
							
							
							if (String.valueOf(cell.getColumnIndex()).equals("1")) {

								dto.setKioskID((String.valueOf((int)cell.getNumericCellValue())));

							}
							if (String.valueOf(cell.getColumnIndex()).equals("2")) {

								dto.setVendor((String.valueOf((int)cell.getNumericCellValue())));

							}
							if (String.valueOf(cell.getColumnIndex()).equals("3")) {
								String cellValueStr = objDefaultFormat.formatCellValue(cell, objFormulaEvaluator);	
								dto.setInstallationDate(cellValueStr);

							}

							if (String.valueOf(cell.getColumnIndex()).equals("4")) {

								dto.setKioskIPAddress((String.valueOf((int)cell.getNumericCellValue())));

							}
							if (String.valueOf(cell.getColumnIndex()).equals("5")) {

								dto.setKioskMacAddress((String.valueOf((int)cell.getNumericCellValue())));

							}
							if (String.valueOf(cell.getColumnIndex()).equals("6")) {

								dto.setSiteType((String.valueOf((int)cell.getNumericCellValue())));

							}
							if (String.valueOf(cell.getColumnIndex()).equals("7")) {

								dto.setLocation((String.valueOf((int)cell.getNumericCellValue())));

							}

							if (String.valueOf(cell.getColumnIndex()).equals("8")) {

								dto.setAddress((String.valueOf((int)cell.getNumericCellValue())));

							}
							if (String.valueOf(cell.getColumnIndex()).equals("9")) {

								dto.setBranchCode((String.valueOf((int)cell.getNumericCellValue())));

							}
							if (String.valueOf(cell.getColumnIndex()).equals("10")) {

								dto.setKioskSerialNumber((String.valueOf((int)cell.getNumericCellValue())));

							}

							if (String.valueOf(cell.getColumnIndex()).equals("11")) {

								dto.setCircle((String.valueOf((int)cell.getNumericCellValue())));

							}

							if (String.valueOf(cell.getColumnIndex()).equals("12")) {

								dto.setBranchName((String.valueOf((int)cell.getNumericCellValue())));

							}

							if (String.valueOf(cell.getColumnIndex()).equals("13")) {

								dto.setoS((String.valueOf((int)cell.getNumericCellValue())));
							}

							/*
							 * if (String.valueOf(cell.getColumnIndex()).equals("14")) {
							 * 
							 * dto.setMake((String.valueOf((int)cell.getNumericCellValue())));
							 * 
							 * }
							 */

							if (String.valueOf(cell.getColumnIndex()).equals("14")) {

								dto.setInstallationStatus((String.valueOf((int)cell.getNumericCellValue())));

							}
							if (String.valueOf(cell.getColumnIndex()).equals("15")) { 

								dto.setRfpID((String.valueOf((int)cell.getNumericCellValue())));

							}
							if (String.valueOf(cell.getColumnIndex()).equals("16")) {

								dto.setInstallationType((String.valueOf((int)cell.getNumericCellValue())));

							}
							
							break;
						}	

						
					} // switch close
					logger.info(" - ");
				} // 1st close while loop
				lidtDto.add(dto);
				if((lidtDto.get(0).getKioskID() ==null || lidtDto.get(0).getKioskID().trim().equals(""))
						|| (lidtDto.get(0).getVendor() ==null || lidtDto.get(0).getVendor().trim().equals(""))
						|| (lidtDto.get(0).getInstallationDate() ==null || lidtDto.get(0).getInstallationDate().trim().equals(""))
						|| (lidtDto.get(0).getKioskIPAddress() ==null || lidtDto.get(0).getKioskIPAddress().trim().equals(""))
						|| (lidtDto.get(0).getKioskMacAddress() ==null || lidtDto.get(0).getKioskMacAddress().trim().equals(""))
						|| (lidtDto.get(0).getSiteType() ==null || lidtDto.get(0).getSiteType().trim().equals(""))
						|| (lidtDto.get(0).getLocation() ==null || lidtDto.get(0).getLocation().trim().equals(""))
					    || (lidtDto.get(0).getAddress() ==null || lidtDto.get(0).getAddress().trim().equals(""))
					    || (lidtDto.get(0).getBranchCode() ==null || lidtDto.get(0).getBranchCode().trim().equals(""))
					    || (lidtDto.get(0).getKioskSerialNumber() ==null || lidtDto.get(0).getKioskSerialNumber().trim().equals(""))
					    || (lidtDto.get(0).getCircle() ==null || lidtDto.get(0).getCircle().trim().equals(""))
					    || (lidtDto.get(0).getBranchName() ==null || lidtDto.get(0).getBranchName().trim().equals(""))
					    || (lidtDto.get(0).getoS() ==null || lidtDto.get(0).getoS().trim().equals(""))
					    || (lidtDto.get(0).getInstallationStatus() ==null || lidtDto.get(0).getInstallationStatus().trim().equals(""))
					    || (lidtDto.get(0).getInstallationType() ==null || lidtDto.get(0).getInstallationType().trim().equals(""))					    
					 	|| (lidtDto.get(0).getInstallationType() ==null || lidtDto.get(0).getInstallationType().trim().equals(""))		
						) {
							logger.error("Header missing in file!!");
							
							return "Header missing in file";
						}
			} // 2nd close while loop

			

			KioskBranchMaster entity = null;
			List<KioskBranchMaster> listEntity = new ArrayList<KioskBranchMaster>();
			List<KioskBranchMaster> listEntity1 = new ArrayList<KioskBranchMaster>();
			int count = 0;
			if( lidtDto.size() == 0)
			{
				logger.error("Blank File for upload!!");
				
				return "Blank File for upload";
			}
			if( lidtDto.size() == 1)
			{
				logger.error("Blank File(Fill only Column name) for upload!!");
				
				return "Blank File(Fill only Column name) for upload";
			}
			for (KioskDto lidtDto1 : lidtDto) {
				if (count != 0) {
					entity = new KioskBranchMaster();
					
					entity.setKioskId(lidtDto1.getKioskID());//1
					entity.setVendor(lidtDto1.getVendor());//2
					
			
					String installationDate = "";
					try {
					String sDate1=lidtDto1.getInstallationDate();
					  
					  logger.info("Installation date in entity format: "+sDate1); 
					  
					  sDate1= sDate1.replaceAll("/", "-")
						  		.replaceAll("-", "-");
						  logger.info("replaced date in entity format: "+sDate1); 
						 				
						  Date date =new Date();
					
						  SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
					
						  date = formatter.parse(sDate1);
					  
					  installationDate =  new SimpleDateFormat("dd-mm-yyyy").format(date);
					  
					  logger.info("installationDate date in String format: "+installationDate);
					  entity.setInstallationDate(installationDate);//3
					}
					catch (Exception e) {
						logger.error("Exception "+ExceptionConstants.DATE_EXCEPTION);
						installationDate = "";
						lidtDto1.setInstallationDate("");
						 entity.setInstallationDate(installationDate);//3
					} 
					entity.setKioskIp(lidtDto1.getKioskIPAddress());// 4
					entity.setKioskMacAddress(lidtDto1.getKioskMacAddress());//5
					entity.setSiteType(lidtDto1.getSiteType());//6
					entity.setLocation(lidtDto1.getLocation());//7
					entity.setAddress(lidtDto1.getAddress());//8
					entity.setBranchCode(lidtDto1.getBranchCode());// 9
					entity.setKioskSerialNo(lidtDto1.getKioskSerialNumber());// 10
					entity.setCircle(lidtDto1.getCircle());// 11
					entity.setBranchName(lidtDto1.getBranchName());// 12
					entity.setOs(lidtDto1.getoS());// 13
					entity.setInstallationStatus(lidtDto1.getInstallationStatus());//14
					entity.setRefId(lidtDto1.getRfpID());//15
					entity.setInstallationType(lidtDto1.getInstallationType());//16
				
					
					listEntity.add(entity);
				}
				count++;

				Optional<String> checkNullgetKioskID = Optional.ofNullable(lidtDto1.getKioskID());//NETWORK
				Optional<String> checkNullVendor = Optional.ofNullable(lidtDto1.getVendor());
				Optional<String> checkNullInstallationDate = Optional.ofNullable(lidtDto1.getInstallationDate());
				Optional<String> checkNullKioskMacAddress = Optional.ofNullable(lidtDto1.getKioskMacAddress());
				Optional<String> checkNullBranchCode = Optional.ofNullable(lidtDto1.getBranchCode());
				Optional<String> checkNullKioskSerialNumber = Optional.ofNullable(lidtDto1.getKioskSerialNumber());
				Optional<String> checkNulloS = Optional.ofNullable(lidtDto1.getoS());
				
				Optional<String> checkNullSrNo = Optional.ofNullable(lidtDto1.getSrNo());
				Optional<String> checkNullCircle = Optional.ofNullable(lidtDto1.getCircle());
				Optional<String> checkNullBranchName= Optional.ofNullable(lidtDto1.getBranchName());
				Optional<String> checkNullMake = Optional.ofNullable(lidtDto1.getMake());
				Optional<String> checkNullSiteType = Optional.ofNullable(lidtDto1.getSiteType());
				Optional<String> checkNullLocation = Optional.ofNullable(lidtDto1.getLocation());
				Optional<String> checkNullAddress = Optional.ofNullable(lidtDto1.getAddress());
				Optional<String> checkNullInstallationStatus = Optional.ofNullable(lidtDto1.getInstallationStatus());		
				
				
				if ((!checkNullgetKioskID.isPresent() || checkNullgetKioskID.get().trim().equals(""))
						&& (!checkNullVendor.isPresent() || checkNullVendor.get().trim().equals(""))
						&& (!checkNullInstallationDate.isPresent() || checkNullInstallationDate.get().trim().equals(""))
						&& (!checkNullKioskMacAddress.isPresent() || checkNullKioskMacAddress.get().trim().equals(""))
						&& (!checkNullBranchCode.isPresent() || checkNullBranchCode.get().trim().equals(""))
						&& (!checkNullKioskSerialNumber.isPresent() || checkNullKioskSerialNumber.get().trim().equals(""))
						&& (!checkNulloS.isPresent() || checkNulloS.get().trim().equals(""))						
						
						&& (!checkNullSrNo.isPresent() || checkNullSrNo.get().trim().equals(""))
						&& (!checkNullCircle.isPresent() || checkNullCircle.get().trim().equals(""))
						&& (!checkNullBranchName.isPresent() || checkNullBranchName.get().trim().equals(""))
						&& (!checkNullMake.isPresent() || checkNullMake.get().trim().equals(""))
						&& (!checkNullSiteType.isPresent() || checkNullSiteType.get().trim().equals(""))
						&& (!checkNullLocation.isPresent() || checkNullLocation.get().trim().equals(""))
						&& (!checkNullAddress.isPresent() || checkNullAddress.get().trim().equals(""))					
						&& (!checkNullInstallationStatus.isPresent() || checkNullInstallationStatus.get().trim().equals(""))) {
					
				}else				
				if (!checkNullgetKioskID.isPresent() || !checkNullVendor.isPresent() || !checkNullInstallationDate.isPresent()
						|| !checkNullKioskMacAddress.isPresent() || !checkNullBranchCode.isPresent() || !checkNullKioskSerialNumber.isPresent()
						|| !checkNulloS.isPresent()
						|| checkNullgetKioskID.get().trim().equals("") || checkNullVendor.get().trim().equals("") || checkNullInstallationDate.get().trim().equals("")
						|| checkNullKioskMacAddress.get().trim().equals("") || checkNullBranchCode.get().trim().equals("") || checkNullKioskSerialNumber.get().trim().equals("")
						|| checkNulloS.get().trim().equals("")) {
					entity = new KioskBranchMaster();
					entity.setKioskId(lidtDto1.getKioskID());//1
					entity.setVendor(lidtDto1.getVendor());//2
					entity.setInstallationDate(lidtDto1.getInstallationDate());//3
					entity.setKioskIp(lidtDto1.getKioskIPAddress());// 4
					entity.setKioskMacAddress(lidtDto1.getKioskMacAddress());//5
					entity.setSiteType(lidtDto1.getSiteType());//6
					entity.setLocation(lidtDto1.getLocation());//7
					entity.setAddress(lidtDto1.getAddress());//8
					entity.setBranchCode(lidtDto1.getBranchCode());// 9
					entity.setKioskSerialNo(lidtDto1.getKioskSerialNumber());// 10
					entity.setCircle(lidtDto1.getCircle());// 11
					entity.setBranchName(lidtDto1.getBranchName());// 12
					entity.setOs(lidtDto1.getoS());// 13
					entity.setInstallationStatus(lidtDto1.getInstallationStatus());//14
					entity.setRefId(lidtDto1.getRfpID());//19
					entity.setInstallationType(lidtDto1.getInstallationType());//20
					
					listEntity1.add(entity);
					//KioskBranchMasterxlsx(listEntity1);

				}

			}
			if(listEntity1 !=null && listEntity1.size() > 0)
			{	
				KioskBranchMasterxlsx(listEntity1);
				logger.info("Kiosk Details Data Not Uploaded");
				return "Data Not Uploaded";
			}
			else {
				List<KioskBranchMaster> listEntityDup = new ArrayList<KioskBranchMaster>();
				for (KioskBranchMaster listEntityNew : listEntity) {
				String kioskid=kioskMasterManagementRepository.findDuplicateKioskId(listEntityNew.getKioskId());
				if(kioskid!=null  && !kioskid.isEmpty()){
					entity = new KioskBranchMaster();
					entity.setKioskId(listEntityNew.getKioskId());//1
					entity.setVendor(listEntityNew.getVendor());//2
					entity.setInstallationDate(listEntityNew.getInstallationDate());//3
					entity.setKioskIp(listEntityNew.getKioskIp());// 4
					entity.setKioskMacAddress(listEntityNew.getKioskMacAddress());//5
					entity.setSiteType(listEntityNew.getSiteType());//6
					entity.setLocation(listEntityNew.getLocation());//7
					entity.setAddress(listEntityNew.getAddress());//8
					entity.setBranchCode(listEntityNew.getBranchCode());// 9
					entity.setKioskSerialNo(listEntityNew.getKioskSerialNo());// 10
					entity.setCircle(listEntityNew.getCircle());// 11
					entity.setBranchName(listEntityNew.getBranchName());// 12
					entity.setOs(listEntityNew.getOs());// 13
					entity.setInstallationStatus(listEntityNew.getInstallationStatus());//14
					entity.setRefId(listEntityNew.getRefId());//19
					entity.setInstallationType(listEntityNew.getInstallationType());//20
					listEntityDup.add(entity);
					
					
				}
				}
				if(listEntityDup !=null && listEntityDup.size() > 0)
				{   
					KioskBranchMasterxlsx(listEntityDup);
					logger.info("Kiosk Id is Already Exist");
					return "Kiosk Id is Already Exist";
				}
				else{
				Iterable<KioskBranchMaster> result = kioskMasterManagementRepository.saveAll(listEntity);
				if (result != null)
					logger.info("Kiosk Details Uploaded Successfully");
					return "Kiosk_Branch_Master";	
				}
			}
			
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		} finally {
			try {
				if (workbook != null) {
					workbook.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}

			} catch (Exception e) {
				logger.error("Exception "+ExceptionConstants.EXCEPTION);
				e.printStackTrace();
			}
		}
		return "Data Not Uploaded";

	}

	// xlsx KioskBranchMaster
	public void KioskBranchMasterxlsx(List<KioskBranchMaster> listEntity1) {

		XSSFWorkbook workbook1 = new XSSFWorkbook();
        String filename = "Kiosk_Branch_Master.xlsx";
		XSSFSheet sheet = workbook1.createSheet("Kiosh_BranchMaster");
		String[] columns = {"Kiosk_ID","Vendor","InstallationDate","KioskIp","KioskMacAddress","SiteType","Location","Address",
				            "BranchCode","KioskSerialNo","Circle","BRANCH_NAME","Os","InstallationStatus","INSTALLATION_TYPE","RFP_ID"};

		 Font headerFont = workbook1.createFont();
	        headerFont.setBold(true);
	     
	      
		    // Create a CellStyle with the font
	        CellStyle headerCellStyle = workbook1.createCellStyle();
		    headerCellStyle.setFont(headerFont);

	        // Create a Row
	        Row headerRow = sheet.createRow(0);

	        // Create cells
	        for(int i = 0; i < columns.length; i++) {
	            Cell cell = headerRow.createCell(i);
	            cell.setCellValue(columns[i]);
	            
	            cell.setCellStyle(headerCellStyle);
	        }
		
		int rownum = 1;
		 int rowIndex = 1;
	        for(KioskBranchMaster entity : listEntity1){
	            Row row = sheet.createRow(rowIndex++);
	            int cellIndex = 0;
	            //first place in row is pfid
	        //    row.createCell(cellIndex++).setCellValue(entity.getSrNo());
	            row.createCell(cellIndex++).setCellValue(entity.getKioskId());
	            row.createCell(cellIndex++).setCellValue(entity.getVendor());
	            row.createCell(cellIndex++).setCellValue(entity.getInstallationDate());
	            row.createCell(cellIndex++).setCellValue(entity.getKioskIp());
	            row.createCell(cellIndex++).setCellValue(entity.getKioskMacAddress());
	            row.createCell(cellIndex++).setCellValue(entity.getSiteType());
	            row.createCell(cellIndex++).setCellValue(entity.getLocation());
	            row.createCell(cellIndex++).setCellValue(entity.getAddress());
	            row.createCell(cellIndex++).setCellValue(entity.getBranchCode());
	            row.createCell(cellIndex++).setCellValue(entity.getKioskSerialNo());
	            row.createCell(cellIndex++).setCellValue(entity.getCircle());
	            row.createCell(cellIndex++).setCellValue(entity.getBranchName());
	            row.createCell(cellIndex++).setCellValue(entity.getOs());
				/* Commented for Kiosk Branch Master entity change
				 * row.createCell(cellIndex++).setCellValue(entity.getMake()); */
	            row.createCell(cellIndex++).setCellValue(entity.getInstallationStatus());
	            row.createCell(cellIndex++).setCellValue(entity.getInstallationType());
	            row.createCell(cellIndex++).setCellValue(entity.getRefId());
	
	        }
		
		try {
			// this Writes the workbook KioskC
			logger.info("Error file path: "+reportPath1+filename);
			FileOutputStream out = new FileOutputStream(new File(reportPath1+filename));
			workbook1.write(out);
			out.close();
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
			e.printStackTrace();
		}

	}

	// ==================

	@Override
	public String uploadCBSbrhmInformation(String path) {

		// upload kiosk file information
		try {

			// By Ankur 28-04-2020-----------STARTS---------


			// rb = ResourceBundle.getBundle("stream");

			// String CBSBrhmFilepath = rb.getString(path);

			inputStream = new FileInputStream(new File(path));
			logger.info("7 A.file read successfully!!");
			workbook = new XSSFWorkbook(inputStream);
			logger.info("7 B.workbook parsing started!!");
			// List<String> errorList = new ArrayList<String>();

			// -------By Ankur END---------------------------

			org.apache.poi.ss.usermodel.Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();
			List<CbsBrhmDto> lidtDto = new ArrayList<CbsBrhmDto>();
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();

				// By Ankur 28-04-2020-----------STARTS---------

				// map=new HashMap<Integer,String>();

				// errorList = new ArrayList<String>();

				// -------By Ankur END---------------------------

				Iterator<Cell> cellIterator = nextRow.cellIterator();
				CbsBrhmDto dto = new CbsBrhmDto();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
				
					if(String.valueOf(cell.getRow().getRowNum()).equals("0")) 
					{
						if(String.valueOf(cell.getColumnIndex()).equals("0")) {
							if(!((String.valueOf(cell.getColumnIndex()).equals("0")) && (cell.getStringCellValue().equalsIgnoreCase("BR_ID"))))
							{
								logger.error("Wrong File or Data Sequence for upload!!");
								
								return "Wrong File or Data Sequence for upload";
							}
							
						}
						if(String.valueOf(cell.getColumnIndex()).equals("1")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("1")) && (cell.getStringCellValue().equalsIgnoreCase("CRCL_NAME"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						} 
						if (String.valueOf(cell.getColumnIndex()).equals("1")) {
							dto.setcRCLName((String.valueOf(cell.getStringCellValue())));
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("2")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("2")) && (cell.getStringCellValue().equalsIgnoreCase("NETWORK"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						} 
						if (String.valueOf(cell.getColumnIndex()).equals("2")) {
							dto.setNetwork((String.valueOf(cell.getStringCellValue())));
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("3")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("3")) && (cell.getStringCellValue().equalsIgnoreCase("MOD_CODE"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("3")) {
							dto.setModCode((String.valueOf(cell.getStringCellValue())));
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("4")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("4")) && (cell.getStringCellValue().equalsIgnoreCase("MODULE"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("4")) {
							dto.setModule((String.valueOf(cell.getStringCellValue())));
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("5")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("5")) && (cell.getStringCellValue().equalsIgnoreCase("REGION"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("5")) {
							dto.setRegion((String.valueOf(cell.getStringCellValue())));
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("6")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("6")) && (cell.getStringCellValue().equalsIgnoreCase("BRANCH_CODE"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("6")) {
							dto.setBranchCode((String.valueOf(cell.getStringCellValue())));
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("7")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("7")) && (cell.getStringCellValue().equalsIgnoreCase("BRANCH_NAME"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("7")) {
							dto.setBranchName((String.valueOf(cell.getStringCellValue())));
						}
						}
						
						if(String.valueOf(cell.getColumnIndex()).equals("8")) {
							if(!((String.valueOf(cell.getColumnIndex()).equals("8")) && (cell.getStringCellValue().equalsIgnoreCase("CRCL_CODE"))))
							{
								logger.error("Wrong File or Data Sequence for upload!!");
								
								return "Wrong File or Data Sequence for upload";
							}
							if (String.valueOf(cell.getColumnIndex()).equals("8")) {
								dto.setcRCLCode((String.valueOf(cell.getStringCellValue())));
							}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("9")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("9")) && (cell.getStringCellValue().equalsIgnoreCase("POP_GROUP"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("9")) {
							dto.setPopGroup((String.valueOf(cell.getStringCellValue())));
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("10")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("10")) && (cell.getStringCellValue().equalsIgnoreCase("POP_DESC"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						} 
						if (String.valueOf(cell.getColumnIndex()).equals("10")) {
							dto.setPopDesc((String.valueOf(cell.getStringCellValue())));
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("11")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("11")) && (cell.getStringCellValue().equalsIgnoreCase("OPEN_CLOSE_STATUS"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("11")) {
							dto.setOpenCloseStatu((String.valueOf(cell.getStringCellValue())));
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("12")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("12")) && (cell.getStringCellValue().equalsIgnoreCase("OPENDT"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("12")) {
							dto.setOpendt((String.valueOf(cell.getStringCellValue())));
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("13")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("13")) && (cell.getStringCellValue().equalsIgnoreCase("STAT_CODE"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("13")) {
							dto.setStatCode((String.valueOf(cell.getStringCellValue())));
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("14")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("14")) && (cell.getStringCellValue().equalsIgnoreCase("STAT_DESC"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("14")) {
							dto.setStateDesc((String.valueOf(cell.getStringCellValue())));
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("15")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("15")) && (cell.getStringCellValue().equalsIgnoreCase("DIST_CODE"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("15")) {
							dto.setDistCode((String.valueOf(cell.getStringCellValue())));
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("16")) {
							if(!((String.valueOf(cell.getColumnIndex()).equals("16")) && (cell.getStringCellValue().equalsIgnoreCase("DIST_DESC"))))
							{
								logger.error("Wrong File or Data Sequence for upload!!");
								
								return "Wrong File or Data Sequence for upload";
							}
							if (String.valueOf(cell.getColumnIndex()).equals("16")) {
								dto.setDistDesc((String.valueOf(cell.getStringCellValue())));
							}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("17")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("17")) && (cell.getStringCellValue().equalsIgnoreCase("ADDRESS1"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						} 
						if (String.valueOf(cell.getColumnIndex()).equals("17")) {
							dto.setAddress1((String.valueOf(cell.getStringCellValue())));
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("18")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("18")) && (cell.getStringCellValue().equalsIgnoreCase("ADDRESS2"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						} 
						if (String.valueOf(cell.getColumnIndex()).equals("18")) {
							dto.setAddress2((String.valueOf(cell.getStringCellValue())));
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("19")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("19")) && (cell.getStringCellValue().equalsIgnoreCase("ADDRESS3"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("19")) {
							dto.setAddress3((String.valueOf(cell.getStringCellValue())));
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("20")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("20")) && (cell.getStringCellValue().equalsIgnoreCase("ADDRESS4"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("20")) {
							dto.setAddress4((String.valueOf(cell.getStringCellValue())));
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("21")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("21")) && (cell.getStringCellValue().equalsIgnoreCase("PINCODE"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("21")) {
							dto.setPinCode((String.valueOf(cell.getStringCellValue())));
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("22")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("22")) && (cell.getStringCellValue().equalsIgnoreCase("STD_CODE"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("22")) {
							dto.setStdCode((String.valueOf(cell.getStringCellValue())));
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("23")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("23")) && (cell.getStringCellValue().equalsIgnoreCase("PHONE"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("23")) {
							dto.setPhone((String.valueOf(cell.getStringCellValue())));
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("24")) {
							if(!((String.valueOf(cell.getColumnIndex()).equals("24")) && (cell.getStringCellValue().equalsIgnoreCase("MICR_CODE"))))
							{
								logger.error("Wrong File or Data Sequence for upload!!");
								
								return "Wrong File or Data Sequence for upload";
							}
							if (String.valueOf(cell.getColumnIndex()).equals("24")) {
								dto.setMicrCode((String.valueOf(cell.getStringCellValue())));
							}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("25")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("25")) && (cell.getStringCellValue().equalsIgnoreCase("IFSC"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						} 
						if (String.valueOf(cell.getColumnIndex()).equals("25")) {
							dto.setIfsc((String.valueOf(cell.getStringCellValue())));
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("26")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("26")) && (cell.getStringCellValue().equalsIgnoreCase("EMAIL"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("26")) {
							dto.setEmail((String.valueOf(cell.getStringCellValue())));
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("27")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("27")) && (cell.getStringCellValue().equalsIgnoreCase("BRANCHMGR_NAME"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("27")) {
							dto.setBranchMgrName((String.valueOf(cell.getStringCellValue())));
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("28")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("28")) && (cell.getStringCellValue().equalsIgnoreCase("BRANCHMGR_MOBILE"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("28")) {
							dto.setBranchMgrMobileNo((String.valueOf(cell.getStringCellValue())));
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("29")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("29")) && (cell.getStringCellValue().equalsIgnoreCase("BUSINESSHRS"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("29")) {
							dto.setBusinessHrs((String.valueOf(cell.getStringCellValue())));
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("30")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("30")) && (cell.getStringCellValue().equalsIgnoreCase("OFFICE_TYPE"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("30")) {
							dto.setOfficeType((String.valueOf(cell.getStringCellValue())));
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("31")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("31")) && (cell.getStringCellValue().equalsIgnoreCase("OFFICE_DESC"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("31")) {
							dto.setOfficeDesc((String.valueOf(cell.getStringCellValue())));
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("32")) {
							if(!((String.valueOf(cell.getColumnIndex()).equals("32")) && (cell.getStringCellValue().equalsIgnoreCase("CREATED_BY"))))
							{
								logger.error("Wrong File or Data Sequence for upload!!");
								
								return "Wrong File or Data Sequence for upload";
							}
							
							}
						if(String.valueOf(cell.getColumnIndex()).equals("33")) {
							if(!((String.valueOf(cell.getColumnIndex()).equals("33")) && (cell.getStringCellValue().equalsIgnoreCase("CREATED_DATE"))))
							{
								logger.error("Wrong File or Data Sequence for upload!!");
								
								return "Wrong File or Data Sequence for upload";
							}
							}
						if(String.valueOf(cell.getColumnIndex()).equals("34")) {
							if(!((String.valueOf(cell.getColumnIndex()).equals("34")) && (cell.getStringCellValue().equalsIgnoreCase("MODIFIED_BY"))))
							{
								logger.error("Wrong File or Data Sequence for upload!!");
								
								return "Wrong File or Data Sequence for upload";
							}
							}
						if(String.valueOf(cell.getColumnIndex()).equals("35")) {
							if(!((String.valueOf(cell.getColumnIndex()).equals("35")) && (cell.getStringCellValue().equalsIgnoreCase("MODIFIED_DATE"))))
							{
								logger.error("Wrong File or Data Sequence for upload!!");
								
								return "Wrong File or Data Sequence for upload";
							}
							}
					}
					switch (cell.getCellType()) {

					case STRING:

						if (!(String.valueOf(cell.getRow().getRowNum()).equals("0"))) {

							
							
							if (String.valueOf(cell.getColumnIndex()).equals("1")) {
								dto.setcRCLName((String.valueOf(cell.getStringCellValue())));
							}
							if (String.valueOf(cell.getColumnIndex()).equals("2")) {
								dto.setNetwork((String.valueOf(cell.getStringCellValue())));
							}
							if (String.valueOf(cell.getColumnIndex()).equals("3")) {
								dto.setModCode((String.valueOf(cell.getStringCellValue())));
							}

							if (String.valueOf(cell.getColumnIndex()).equals("4")) {
								dto.setModule(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("5")) {
								dto.setRegion(cell.getStringCellValue());
							}
							
							if (String.valueOf(cell.getColumnIndex()).equals("6")) {
								dto.setBranchCode(cell.getStringCellValue());
							}
							
							if (String.valueOf(cell.getColumnIndex()).equals("7")) {
								dto.setBranchName(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("8")) {
								dto.setcRCLCode((String.valueOf(cell.getStringCellValue())));
							}
							if (String.valueOf(cell.getColumnIndex()).equals("9")) {
								dto.setPopGroup(cell.getStringCellValue());
							}
							
							if (String.valueOf(cell.getColumnIndex()).equals("10")) {
								dto.setPopDesc(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("11")) {
								dto.setOpenCloseStatu(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("12")) {
								dto.setOpendt(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("13")) {
								dto.setStatCode(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("14")) {
								dto.setStateDesc(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("15")) {
								dto.setDistCode(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("16")) {
								dto.setDistDesc(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("17")) {
								dto.setAddress1(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("18")) {
								dto.setAddress2(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("19")) {
								dto.setAddress3(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("20")) {
								dto.setAddress4(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("21")) {
								dto.setPinCode(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("22")) {
								dto.setStdCode(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("23")) {
								dto.setPhone(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("24")) {
								dto.setMicrCode(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("25")) {
								dto.setIfsc(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("26")) {
								dto.setEmail(cell.getStringCellValue());
							}

							if (String.valueOf(cell.getColumnIndex()).equals("27")) {
								dto.setBranchMgrName(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("28")) {
								dto.setBranchMgrMobileNo(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("29")) {
								dto.setBusinessHrs(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("30")) {
								dto.setOfficeType(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("31")) {
								dto.setOfficeDesc(cell.getStringCellValue());
							}
							
							
							 

							break;

						}

					case NUMERIC:
						if (!(String.valueOf(cell.getRow().getRowNum()).equals("0"))) {
							
							
							
							        if (String.valueOf(cell.getColumnIndex()).equals("1")) {
								       dto.setcRCLName((String.valueOf((int)cell.getNumericCellValue()))); }
								  
									if (String.valueOf(cell.getColumnIndex()).equals("2")) {
										dto.setNetwork((String.valueOf((int)cell.getNumericCellValue())));
									}
									if (String.valueOf(cell.getColumnIndex()).equals("3")) {
										dto.setModCode((String.valueOf((int)cell.getNumericCellValue())));
									}

									if (String.valueOf(cell.getColumnIndex()).equals("4")) {
										dto.setModule((String.valueOf((int)cell.getNumericCellValue())));
									}
									if (String.valueOf(cell.getColumnIndex()).equals("5")) {
										dto.setRegion((String.valueOf((int)cell.getNumericCellValue())));
									}
									if (String.valueOf(cell.getColumnIndex()).equals("6")) {
										dto.setBranchCode((String.valueOf((int)cell.getNumericCellValue())));
									}
									if (String.valueOf(cell.getColumnIndex()).equals("7")) {
										dto.setBranchName((String.valueOf((int)cell.getNumericCellValue())));
									}
									if (String.valueOf(cell.getColumnIndex()).equals("8")) {
										dto.setcRCLCode((String.valueOf((int)cell.getNumericCellValue())));
									}
									if (String.valueOf(cell.getColumnIndex()).equals("9")) {
										dto.setPopGroup((String.valueOf((int)cell.getNumericCellValue())));
									}
									if (String.valueOf(cell.getColumnIndex()).equals("10")) {
										dto.setPopDesc((String.valueOf((int)cell.getNumericCellValue())));
									}
									if (String.valueOf(cell.getColumnIndex()).equals("11")) {
										dto.setOpenCloseStatu((String.valueOf((int)cell.getNumericCellValue())));
									}
									if (String.valueOf(cell.getColumnIndex()).equals("12")) {
										dto.setOpendt((String.valueOf((int)cell.getNumericCellValue())));
									}
									if (String.valueOf(cell.getColumnIndex()).equals("13")) {
										dto.setStatCode((String.valueOf((int)cell.getNumericCellValue())));
									}
									if (String.valueOf(cell.getColumnIndex()).equals("14")) {
										dto.setStateDesc((String.valueOf((int)cell.getNumericCellValue())));
									}
									if (String.valueOf(cell.getColumnIndex()).equals("15")) {
										dto.setDistCode((String.valueOf((int)cell.getNumericCellValue())));
									}
									if (String.valueOf(cell.getColumnIndex()).equals("16")) {
										dto.setDistDesc((String.valueOf((int)cell.getNumericCellValue())));
									}
									if (String.valueOf(cell.getColumnIndex()).equals("17")) {
										dto.setAddress1((String.valueOf((int)cell.getNumericCellValue())));
									}
									if (String.valueOf(cell.getColumnIndex()).equals("18")) {
										dto.setAddress2((String.valueOf((int)cell.getNumericCellValue())));
									}
									if (String.valueOf(cell.getColumnIndex()).equals("19")) {
										dto.setAddress3((String.valueOf((int)cell.getNumericCellValue())));
									}
									if (String.valueOf(cell.getColumnIndex()).equals("20")) {
										dto.setAddress4((String.valueOf((int)cell.getNumericCellValue())));
									}
									if (String.valueOf(cell.getColumnIndex()).equals("21")) {
										dto.setPinCode((String.valueOf((int)cell.getNumericCellValue())));
									}
									if (String.valueOf(cell.getColumnIndex()).equals("22")) {
										dto.setStdCode((String.valueOf((int)cell.getNumericCellValue())));
									}
									if (String.valueOf(cell.getColumnIndex()).equals("23")) {
										dto.setPhone((String.valueOf((int)cell.getNumericCellValue())));
									}
									if (String.valueOf(cell.getColumnIndex()).equals("24")) {
										dto.setMicrCode((String.valueOf((int)cell.getNumericCellValue())));
									}
									if (String.valueOf(cell.getColumnIndex()).equals("25")) {
										dto.setIfsc((String.valueOf((int)cell.getNumericCellValue())));
									}
									if (String.valueOf(cell.getColumnIndex()).equals("26")) {
										dto.setEmail((String.valueOf((int)cell.getNumericCellValue())));
									}

									if (String.valueOf(cell.getColumnIndex()).equals("27")) {
										dto.setBranchMgrName((String.valueOf((int)cell.getNumericCellValue())));
									}
									if (String.valueOf(cell.getColumnIndex()).equals("28")) {
										dto.setBranchMgrMobileNo((String.valueOf((int)cell.getNumericCellValue())));
									}
									if (String.valueOf(cell.getColumnIndex()).equals("29")) {
										dto.setBusinessHrs((String.valueOf((int)cell.getNumericCellValue())));
									}
									if (String.valueOf(cell.getColumnIndex()).equals("30")) {
										dto.setOfficeType((String.valueOf((int)cell.getNumericCellValue())));
									}
									if (String.valueOf(cell.getColumnIndex()).equals("31")) {
										dto.setOfficeDesc((String.valueOf((int)cell.getNumericCellValue())));
									}

							
							break;
						}				

					}// switch close
					logger.info(" - ");
				} // 1st close while loop
	/*			if((dto.getBranchCode() ==null || dto.getBranchCode().trim().equals(""))
						&& (dto.getBranchName() ==null || dto.getBranchName().trim().equals(""))
						&& (dto.getcRCLCode() ==null || dto.getcRCLCode().trim().equals(""))
						&& (dto.getcRCLName() ==null || dto.getcRCLName().trim().equals(""))
						&& (dto.getNetwork() ==null || dto.getNetwork().trim().equals(""))
						&& (dto.getModCode() ==null || dto.getModCode().trim().equals(""))
						&& (dto.getModule() ==null || dto.getModule().trim().equals(""))
					    && (dto.getRegion() ==null || dto.getRegion().trim().equals(""))
					    && (dto.getPopGroup() ==null || dto.getPopGroup().trim().equals(""))
					    && (dto.getPopDesc() ==null || dto.getPopDesc().trim().equals(""))
					    && (dto.getOpenCloseStatu() ==null || dto.getOpenCloseStatu().trim().equals(""))
					    && (dto.getOpendt() ==null || dto.getOpendt().trim().equals(""))
					    && (dto.getStatCode() ==null || dto.getStatCode().trim().equals(""))
					    && (dto.getStateDesc() ==null || dto.getStateDesc().trim().equals(""))
					    && (dto.getDistCode() ==null || dto.getDistCode().trim().equals(""))					    
					    && (dto.getDistDesc() ==null || dto.getDistDesc().trim().equals(""))	
						&& (dto.getAddress1() ==null || dto.getAddress1().trim().equals(""))
					    && (dto.getAddress2() ==null || dto.getAddress2().trim().equals(""))
					    && (dto.getAddress3() ==null || dto.getAddress3().trim().equals(""))
					    && (dto.getAddress4() ==null || dto.getAddress4().trim().equals(""))
					    && (dto.getPinCode() ==null || dto.getPinCode().trim().equals(""))
						&& (dto.getStdCode() ==null || dto.getStdCode().trim().equals(""))
					    && (dto.getPhone() ==null || dto.getPhone().trim().equals(""))
					    		&& (dto.getMicrCode() ==null || dto.getMicrCode().trim().equals(""))
								&& (dto.getIfsc() ==null || dto.getIfsc().trim().equals(""))
								&& (dto.getEmail() ==null || dto.getEmail().trim().equals(""))
								&& (dto.getBranchMgrName() ==null || dto.getBranchMgrName().trim().equals(""))
								&& (dto.getBranchMgrMobileNo() ==null || dto.getBranchMgrMobileNo().trim().equals(""))
								&& (dto.getBusinessHrs() ==null || dto.getBusinessHrs().trim().equals(""))
							    && (dto.getOfficeType() ==null || dto.getOfficeType().trim().equals(""))
							    && (dto.getOfficeDesc() ==null || dto.getOfficeDesc().trim().equals(""))							    
						) {
				
				}
				else {*/
					lidtDto.add(dto);
					if((lidtDto.get(0).getBranchCode() ==null || lidtDto.get(0).getBranchCode().trim().equals(""))
							|| (lidtDto.get(0).getBranchName() ==null || lidtDto.get(0).getBranchName().trim().equals(""))
							|| (lidtDto.get(0).getcRCLCode() ==null || lidtDto.get(0).getcRCLCode().trim().equals(""))
							|| (lidtDto.get(0).getcRCLName() ==null || lidtDto.get(0).getcRCLName().trim().equals(""))
							|| (lidtDto.get(0).getNetwork() ==null || lidtDto.get(0).getNetwork().trim().equals(""))
							|| (lidtDto.get(0).getModCode() ==null || lidtDto.get(0).getModCode().trim().equals(""))
							|| (lidtDto.get(0).getModule() ==null || lidtDto.get(0).getModule().trim().equals(""))
						    || (lidtDto.get(0).getRegion() ==null || lidtDto.get(0).getRegion().trim().equals(""))
						    || (lidtDto.get(0).getPopGroup() ==null || lidtDto.get(0).getPopGroup().trim().equals(""))
						    || (lidtDto.get(0).getPopDesc() ==null || lidtDto.get(0).getPopDesc().trim().equals(""))
						    || (lidtDto.get(0).getOpenCloseStatu() ==null || lidtDto.get(0).getOpenCloseStatu().trim().equals(""))
						    || (lidtDto.get(0).getOpendt() ==null || lidtDto.get(0).getOpendt().trim().equals(""))
						    || (lidtDto.get(0).getStatCode() ==null || lidtDto.get(0).getStatCode().trim().equals(""))
						    || (lidtDto.get(0).getStateDesc() ==null || lidtDto.get(0).getStateDesc().trim().equals(""))
						    || (lidtDto.get(0).getDistCode() ==null || lidtDto.get(0).getDistCode().trim().equals(""))					    
						    || (lidtDto.get(0).getDistDesc() ==null || lidtDto.get(0).getDistDesc().trim().equals(""))	
							|| (lidtDto.get(0).getAddress1() ==null || lidtDto.get(0).getAddress1().trim().equals(""))
						    || (lidtDto.get(0).getAddress2() ==null || lidtDto.get(0).getAddress2().trim().equals(""))
						    || (lidtDto.get(0).getAddress3() ==null || lidtDto.get(0).getAddress3().trim().equals(""))
						    || (lidtDto.get(0).getAddress4() ==null || lidtDto.get(0).getAddress4().trim().equals(""))
						    || (lidtDto.get(0).getPinCode() ==null || lidtDto.get(0).getPinCode().trim().equals(""))
							|| (lidtDto.get(0).getStdCode() ==null || lidtDto.get(0).getStdCode().trim().equals(""))
						    || (lidtDto.get(0).getPhone() ==null || lidtDto.get(0).getPhone().trim().equals(""))
						    		|| (lidtDto.get(0).getMicrCode() ==null || lidtDto.get(0).getMicrCode().trim().equals(""))
									|| (lidtDto.get(0).getIfsc() ==null || lidtDto.get(0).getIfsc().trim().equals(""))
									|| (lidtDto.get(0).getEmail() ==null || lidtDto.get(0).getEmail().trim().equals(""))
									|| (lidtDto.get(0).getBranchMgrName() ==null || lidtDto.get(0).getBranchMgrName().trim().equals(""))
									|| (lidtDto.get(0).getBranchMgrMobileNo() ==null || lidtDto.get(0).getBranchMgrMobileNo().trim().equals(""))
									|| (lidtDto.get(0).getBusinessHrs() ==null || lidtDto.get(0).getBusinessHrs().trim().equals(""))
								    || (lidtDto.get(0).getOfficeType() ==null || lidtDto.get(0).getOfficeType().trim().equals(""))
								    || (lidtDto.get(0).getOfficeDesc() ==null || lidtDto.get(0).getOfficeDesc().trim().equals(""))							    
							) {
						logger.error("Header missing in file!!");
						
						return "Header missing in file";
					}
					
					
					
			//	}
			} // 2nd close while loop


			BranchMaster entity = null;
			List<BranchMaster> listEntity = new ArrayList<BranchMaster>();
			List<BranchMaster> listEntity1 = new ArrayList<BranchMaster>();
			int count = 0;
			if( lidtDto.size() == 0)
			{
				logger.error("Blank File for upload!!");
				
				return "Blank File for upload";
			}
			if( lidtDto.size() == 1)
			{
				logger.error("Blank File(Fill only Column name) for upload!!");
				
				return "Blank File(Fill only Column name) for upload";
			}
			for (CbsBrhmDto listDto1 : lidtDto) {
				if (count != 0) {
					entity = new BranchMaster();

					// entity.setBranchCode(listDto1.getBranchCode().substring(0,
					// listDto1.getBranchCode().length() - 2));
					entity.setBranchCode(listDto1.getBranchCode());
					entity.setBranchName(listDto1.getBranchName());
					entity.setAddress1(listDto1.getAddress1());
					entity.setAddress2(listDto1.getAddress2());
					entity.setAddress3(listDto1.getAddress3());
					entity.setAddress4(listDto1.getAddress4());
					// entity.setCRCLCode(listDto1.getcRCLCode().substring(0,
					// listDto1.getcRCLCode().length() - 2));
					entity.setCRCLCode(listDto1.getcRCLCode());
					entity.setBranchMgrName(listDto1.getBranchMgrName());
					entity.setBranchMgrMobileNo(listDto1.getBranchMgrMobileNo());
					entity.setCircleName(listDto1.getcRCLName());
				//	entity.setCircle(listDto1.getcRCLCode());
					entity.setRegion(listDto1.getRegion());
					entity.setBusinessHrs(listDto1.getBusinessHrs());
					// entity.setDistCode(listDto1.getDistCode().substring(0,
					// listDto1.getDistCode().length() - 2));
					entity.setDistCode(listDto1.getDistCode());
					entity.setDistDesc(listDto1.getDistDesc());
					entity.setEmail(listDto1.getEmail());
					entity.setOfficeDesc(listDto1.getOfficeDesc());
					entity.setOfficeType(listDto1.getOfficeType());
					entity.setIfsc(listDto1.getIfsc());
					entity.setMicrCode(listDto1.getMicrCode());
					entity.setModule(listDto1.getModule());
					// entity.setPinCode(listDto1.getPinCode().substring(0,
					// listDto1.getPinCode().length() - 2));
					entity.setPinCode(listDto1.getPinCode());
					entity.setPopDesc(listDto1.getPopDesc());
					entity.setPopGroup(listDto1.getPopGroup());
					// entity.setModCode(listDto1.getModCode().substring(0,
					// listDto1.getModCode().length() - 2));
					entity.setModCode(listDto1.getModCode());
					entity.setNetwork(listDto1.getNetwork());
					entity.setOpenCloseStatus(listDto1.getOpenCloseStatu());
					entity.setOpendt(listDto1.getOpendt());
					entity.setStatCode(listDto1.getStatCode());
					entity.setPhone(listDto1.getPhone());
					// entity.setStdCode(listDto1.getStatCode().substring(0,
					// listDto1.getStatCode().length() - 2));
					entity.setStdCode(listDto1.getStatCode());
					entity.setStatDesc(listDto1.getStateDesc());

					listEntity.add(entity);
					
					  //if (count == 4) { break; }
					 
				}
				count++;
				Optional<String> checkNullcRCLName = Optional.ofNullable(listDto1.getcRCLName());
				Optional<String> checkNullRegion = Optional.ofNullable(listDto1.getRegion());
				Optional<String> checkNullBranchCode= Optional.ofNullable(listDto1.getBranchCode());
				Optional<String> checkNullModcode = Optional.ofNullable(listDto1.getModCode());
				Optional<String> checkNullModule = Optional.ofNullable(listDto1.getModule());
				Optional<String> checkNullBranchname = Optional.ofNullable(listDto1.getBranchName());
				Optional<String> checkNullstatcode = Optional.ofNullable(listDto1.getStatCode());
				
				Optional<String> checkNullAddr1 = Optional.ofNullable(listDto1.getAddress1());
				Optional<String> checkNullAddr2 = Optional.ofNullable(listDto1.getAddress2());
				Optional<String> checkNullAddr3= Optional.ofNullable(listDto1.getAddress3());
				Optional<String> checkNullAddr4 = Optional.ofNullable(listDto1.getAddress4());
				Optional<String> checkNullcRCLCode = Optional.ofNullable(listDto1.getcRCLCode());
				Optional<String> checkNullBranchMgrName = Optional.ofNullable(listDto1.getBranchMgrName());
				Optional<String> checkNullBranchMgrMobileNo = Optional.ofNullable(listDto1.getBranchMgrMobileNo());
				Optional<String> checkNullBusinessHrs = Optional.ofNullable(listDto1.getBusinessHrs());
				Optional<String> checkNullDistCode = Optional.ofNullable(listDto1.getDistCode());
				Optional<String> checkNullDistDesc= Optional.ofNullable(listDto1.getDistDesc());
				Optional<String> checkNullEmail = Optional.ofNullable(listDto1.getEmail());
				Optional<String> checkNullPinCode = Optional.ofNullable(listDto1.getPinCode());
				Optional<String> checkNullOfficeDesc = Optional.ofNullable(listDto1.getOfficeDesc());
				Optional<String> checkNullOfficeType = Optional.ofNullable(listDto1.getOfficeType());
				
				
				
				if ((!checkNullcRCLName.isPresent() || checkNullcRCLName.get().trim().equals(""))
						&& (!checkNullRegion.isPresent() || checkNullRegion.get().trim().equals(""))
						&& (!checkNullBranchCode.isPresent() || checkNullBranchCode.get().trim().equals(""))
						&& (!checkNullModcode.isPresent() || checkNullModcode.get().trim().equals(""))
						&& (!checkNullModule.isPresent() || checkNullModule.get().trim().equals(""))
						&& (!checkNullBranchname.isPresent() || checkNullBranchname.get().trim().equals(""))
						&& (!checkNullstatcode.isPresent() || checkNullstatcode.get().trim().equals(""))						
						&& (!checkNullAddr1.isPresent() || checkNullAddr1.get().trim().equals(""))
						&& (!checkNullAddr2.isPresent() || checkNullAddr2.get().trim().equals(""))
						&& (!checkNullAddr3.isPresent() || checkNullAddr3.get().trim().equals(""))
						&& (!checkNullAddr4.isPresent() || checkNullAddr4.get().trim().equals(""))
						&& (!checkNullcRCLCode.isPresent() || checkNullcRCLCode.get().trim().equals(""))
						&& (!checkNullBranchMgrName.isPresent() || checkNullBranchMgrName.get().trim().equals(""))
						&& (!checkNullBranchMgrMobileNo.isPresent() || checkNullBranchMgrMobileNo.get().trim().equals(""))
						&& (!checkNullBusinessHrs.isPresent() || checkNullBusinessHrs.get().trim().equals(""))
						&& (!checkNullDistCode.isPresent() || checkNullDistCode.get().trim().equals(""))
						&& (!checkNullDistDesc.isPresent() || checkNullDistDesc.get().trim().equals(""))
						&& (!checkNullEmail.isPresent() || checkNullEmail.get().trim().equals(""))
						&& (!checkNullPinCode.isPresent() || checkNullPinCode.get().trim().equals(""))
						&& (!checkNullOfficeDesc.isPresent() || checkNullOfficeDesc.get().trim().equals(""))
						&& (!checkNullOfficeType.isPresent() || checkNullOfficeType.get().trim().equals(""))) {
					
				}else
				if (!checkNullRegion.isPresent() || !checkNullBranchCode.isPresent() || !checkNullcRCLName.isPresent() || !checkNullModcode.isPresent()
						|| !checkNullModule.isPresent() || !checkNullBranchname.isPresent() || !checkNullstatcode.isPresent()
						|| checkNullRegion.get().trim().equals("") || checkNullBranchCode.get().trim().equals("") || checkNullcRCLName.get().trim().equals("")
						|| checkNullModcode.get().trim().equals("") || checkNullModule.get().trim().equals("") || checkNullBranchname.get().trim().equals("")
						|| checkNullstatcode.get().trim().equals("")) {
					entity = new BranchMaster();
					entity.setBranchCode(listDto1.getBranchCode());
					entity.setBranchName(listDto1.getBranchName());
					entity.setAddress1(listDto1.getAddress1());
					entity.setAddress2(listDto1.getAddress2());
					entity.setAddress3(listDto1.getAddress3());
					entity.setAddress4(listDto1.getAddress4());
					entity.setCRCLCode(listDto1.getcRCLCode());
					entity.setBranchMgrName(listDto1.getBranchMgrName());
					entity.setBranchMgrMobileNo(listDto1.getBranchMgrMobileNo());
					entity.setCircleName(listDto1.getcRCLName());
				//	entity.setCircle(listDto1.getcRCLCode());
					entity.setRegion(listDto1.getRegion());
					entity.setBusinessHrs(listDto1.getBusinessHrs());
					entity.setDistCode(listDto1.getDistCode());
					entity.setDistDesc(listDto1.getDistDesc());
					entity.setEmail(listDto1.getEmail());
					entity.setOfficeDesc(listDto1.getOfficeDesc());
					entity.setOfficeType(listDto1.getOfficeType());
					entity.setIfsc(listDto1.getIfsc());
					entity.setMicrCode(listDto1.getMicrCode());
					entity.setModule(listDto1.getModule());
					entity.setPinCode(listDto1.getPinCode());
					entity.setPopDesc(listDto1.getPopDesc());
					entity.setPopGroup(listDto1.getPopGroup());
					entity.setModCode(listDto1.getModCode());
					entity.setNetwork(listDto1.getNetwork());
					entity.setOpenCloseStatus(listDto1.getOpenCloseStatu());
					entity.setOpendt(listDto1.getOpendt());
					entity.setStatCode(listDto1.getStatCode());
					entity.setPhone(listDto1.getPhone());
					entity.setStdCode(listDto1.getStatCode());
					entity.setStatDesc(listDto1.getStateDesc());

					listEntity1.add(entity);
					//branchmasterxlsx(listEntity1);

				}
			}
			if(listEntity1 !=null && listEntity1.size() > 0)
			{	
				
				branchmasterxlsx(listEntity1);
				logger.info("Data Not Uploaded");
				return "Data Not Uploaded";
			}
			
			else {
				List<BranchMaster> listEntityDup = new ArrayList<BranchMaster>();
				for (BranchMaster listEntityNew : listEntity) {
				String branchCd=branchMasterRepository.findDuplicateByBranchCode(listEntityNew.getBranchCode());
				if(branchCd!=null  && !branchCd.isEmpty()){
					entity = new BranchMaster();
					entity.setBranchCode(listEntityNew.getBranchCode());
					entity.setBranchName(listEntityNew.getBranchName());
					entity.setAddress1(listEntityNew.getAddress1());
					entity.setAddress2(listEntityNew.getAddress2());
					entity.setAddress3(listEntityNew.getAddress3());
					entity.setAddress4(listEntityNew.getAddress4());
					entity.setCRCLCode(listEntityNew.getCRCLCode());
					entity.setBranchMgrName(listEntityNew.getBranchMgrName());
					entity.setBranchMgrMobileNo(listEntityNew.getBranchMgrMobileNo());
					entity.setCircleName(listEntityNew.getCircleName());
				//	entity.setCircle(listEntityNew.getcRCLCode());
					entity.setRegion(listEntityNew.getRegion());
					entity.setBusinessHrs(listEntityNew.getBusinessHrs());
					entity.setDistCode(listEntityNew.getDistCode());
					entity.setDistDesc(listEntityNew.getDistDesc());
					entity.setEmail(listEntityNew.getEmail());
					entity.setOfficeDesc(listEntityNew.getOfficeDesc());
					entity.setOfficeType(listEntityNew.getOfficeType());
					entity.setIfsc(listEntityNew.getIfsc());
					entity.setMicrCode(listEntityNew.getMicrCode());
					entity.setModule(listEntityNew.getModule());
					entity.setPinCode(listEntityNew.getPinCode());
					entity.setPopDesc(listEntityNew.getPopDesc());
					entity.setPopGroup(listEntityNew.getPopGroup());
					entity.setModCode(listEntityNew.getModCode());
					entity.setNetwork(listEntityNew.getNetwork());
					entity.setOpenCloseStatus(listEntityNew.getOpenCloseStatus());
					entity.setOpendt(listEntityNew.getOpendt());
					entity.setStatCode(listEntityNew.getStatCode());
					entity.setPhone(listEntityNew.getPhone());
					entity.setStdCode(listEntityNew.getStatCode());
					entity.setStatDesc(listEntityNew.getStatDesc());
					listEntityDup.add(entity);
					
					
				}
				}
				if(listEntityDup !=null && listEntityDup.size() > 0)
				{   
					branchmasterxlsx(listEntityDup);
					logger.info("Branch code is Already Exist");
					return "Branch code is Already Exist";
				}
				else{
				Iterable<BranchMaster> result =	branchMasterRepository.saveAll(listEntity);
				if(result != null) {
					logger.info( "Branch Master Data Uploaded Successfully");
				return "BranchMaster";
				}
			}
			
			}
		} // try close
		catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		} finally {
			try {
				if (workbook != null) {
					workbook.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e) {
				logger.error("Exception "+ExceptionConstants.EXCEPTION);
				e.printStackTrace();
			}
		}
		return "Data Not Uploaded";

	}

	// branchmasterxlsx
	public void branchmasterxlsx(List<BranchMaster> listEntity1) {

		XSSFWorkbook workbook1 = new XSSFWorkbook();
        String filename ="BranchMaster.xlsx";
        String[] columns = {"BRANCHCD", "BRANCHNAME","CRCL_CODE","CRCL_NAME","NETWORK","MOD_CODE","MODULE","REGION","PopGroup",
        		            "PopDesc","OpenCloseStatus","Opendt","STAT_CODE","STATE DESC","DIST_CODE","DistDesc","Address1","Address2","Address3","Address4"
        		            ,"PINCODE","STD_CODE","PHONE","MICR_CODE","IFSC","EMAIL","BRANCHMGR_NAME","BRANCHMGR_MOBILE","BUSINESSHRS","OfficeType",
        		            "OfficeDesc"};
		XSSFSheet sheet = workbook1.createSheet("Branch_Master");
		
		// Create a Font for styling header cells
        Font headerFont = workbook1.createFont();
        headerFont.setBold(true);
     
      
	    // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook1.createCellStyle();
	    headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Create cells
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            
            cell.setCellStyle(headerCellStyle);
        }
		int rownum = 1;
		 int rowIndex = 1;
	        for(BranchMaster entity : listEntity1){
	            Row row = sheet.createRow(rowIndex++);
	            int cellIndex = 0;
						
	            row.createCell(cellIndex++).setCellValue(entity.getBranchCode());
	            row.createCell(cellIndex++).setCellValue(entity.getBranchName());
	            row.createCell(cellIndex++).setCellValue(entity.getCRCLCode());
	            row.createCell(cellIndex++).setCellValue(entity.getCircleName());
	            row.createCell(cellIndex++).setCellValue(entity.getNetwork());
	            row.createCell(cellIndex++).setCellValue(entity.getModCode());
	            row.createCell(cellIndex++).setCellValue(entity.getModule());
	            row.createCell(cellIndex++).setCellValue(entity.getRegion());
	            row.createCell(cellIndex++).setCellValue(entity.getPopGroup());
	            row.createCell(cellIndex++).setCellValue(entity.getPopDesc());
	            row.createCell(cellIndex++).setCellValue(entity.getOpenCloseStatus());
	            row.createCell(cellIndex++).setCellValue(entity.getOpendt());
	            row.createCell(cellIndex++).setCellValue(entity.getStatCode());
	            row.createCell(cellIndex++).setCellValue(entity.getStatDesc());
	            row.createCell(cellIndex++).setCellValue(entity.getDistCode());
	            row.createCell(cellIndex++).setCellValue(entity.getDistDesc());
	            row.createCell(cellIndex++).setCellValue(entity.getAddress1());
	            row.createCell(cellIndex++).setCellValue(entity.getAddress2());
	            row.createCell(cellIndex++).setCellValue(entity.getAddress3());
	            row.createCell(cellIndex++).setCellValue(entity.getAddress4());
	            row.createCell(cellIndex++).setCellValue(entity.getPinCode());
	            row.createCell(cellIndex++).setCellValue(entity.getStdCode());
	            row.createCell(cellIndex++).setCellValue(entity.getPhone());
	            row.createCell(cellIndex++).setCellValue(entity.getMicrCode());
	            row.createCell(cellIndex++).setCellValue(entity.getIfsc());
	            row.createCell(cellIndex++).setCellValue(entity.getEmail());
	            row.createCell(cellIndex++).setCellValue(entity.getBranchMgrName());
	            row.createCell(cellIndex++).setCellValue(entity.getBranchMgrMobileNo());
	            row.createCell(cellIndex++).setCellValue(entity.getBusinessHrs());
	            row.createCell(cellIndex++).setCellValue(entity.getOfficeType());
	            row.createCell(cellIndex++).setCellValue(entity.getOfficeDesc());
	           // row.createCell(cellIndex++).setCellValue(entity.getModule());
	        }
		
		try {
			// this Writes the workbook KioskC
			logger.info("Error file path: "+reportPath1+filename);
			FileOutputStream out = new FileOutputStream(
					new File(reportPath1+filename));
			workbook1.write(out);
			out.close();
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
			e.printStackTrace();
		}

	}

	// By ankur -----------STARTS---------1 uploadHolidayCalendarInformation

	@Override
	public String uploadHolidayCalendarInformation(String path) {

		
		// upload holiday calendar file information

		try {

			// By Ankur 28-04-2020-----------STARTS---------
			// rb = ResourceBundle.getBundle("system");

			// String holidayCalendarFilePath = rb.getString("CBSBrhmFilepath");
			// String holidayCalendarFilePath = rb.getString(path);
			inputStream = new FileInputStream(new File(path));
			logger.info("7 A.file read successfully!! "+ path);
			// -------By Ankur END---------------------------

			workbook = new XSSFWorkbook(inputStream);
			logger.info("7 B.workbook parsing started!!");
			// HashMap<Integer,String> map=null;  
			org.apache.poi.ss.usermodel.Sheet firstSheet = workbook.getSheetAt(0);

			DataFormatter objDefaultFormat = new DataFormatter();
			FormulaEvaluator objFormulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);

			Iterator<Row> iterator = firstSheet.iterator();
			List<HolidayCalendarDto> lidtDto = new ArrayList<>();
		//	String[] columns = {"ID","HOLIDAY_DATE","DAY","NAME","CIRCLE","STATE","FIN_YR","QUARTER"};
			
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				HolidayCalendarDto dto = new HolidayCalendarDto();

				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();
					objFormulaEvaluator.evaluate(cell);
					if(String.valueOf(cell.getRow().getRowNum()).equals("0")) 
					{
						/*
						 * if(String.valueOf(cell.getColumnIndex()).equals("0")) {
						 * if(!((String.valueOf(cell.getColumnIndex()).equals("0")) &&
						 * (cell.getStringCellValue().equalsIgnoreCase("ID")))) {
						 * logger.error("Wrong File or Data Sequence for upload!!");
						 * 
						 * return "Wrong File or Data Sequence for upload"; } }
						 */
						if(String.valueOf(cell.getColumnIndex()).equals("1")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("1")) && (cell.getStringCellValue().equalsIgnoreCase("HOLIDAY_DATE"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("1")) {
							dto.setHolidayDate(cell.getStringCellValue());
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("2")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("2")) && (cell.getStringCellValue().equalsIgnoreCase("DAY"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						} 
						if (String.valueOf(cell.getColumnIndex()).equals("2")) {
							dto.setDay(cell.getStringCellValue());
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("3")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("3")) && (cell.getStringCellValue().equalsIgnoreCase("NAME"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("3")) {
							dto.setName(cell.getStringCellValue());
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("4")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("4")) && (cell.getStringCellValue().equalsIgnoreCase("CIRCLE"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("4")) {
							dto.setCircle(cell.getStringCellValue());
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("5")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("5")) && (cell.getStringCellValue().equalsIgnoreCase("STATE"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("5")) {
							dto.setState(cell.getStringCellValue());
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("6")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("6")) && (cell.getStringCellValue().equalsIgnoreCase("FIN_YR"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("6")) {
							dto.setFinYr(cell.getStringCellValue());
						}
						}
						if(String.valueOf(cell.getColumnIndex()).equals("7")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("7")) && (cell.getStringCellValue().equalsIgnoreCase("QUARTER"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						}
						if (String.valueOf(cell.getColumnIndex()).equals("7")) {
							dto.setQuarter(cell.getStringCellValue());
						}
						}
						
						
					}
					

					if (!(String.valueOf(cell.getRow().getRowNum()).equals("0"))) {

						if (String.valueOf(cell.getColumnIndex()).equals("1")) 
						{
							
							String cellValueStr = objDefaultFormat.formatCellValue(cell, objFormulaEvaluator);
							dto.setHolidayDate(cellValueStr);
							}

						if (String.valueOf(cell.getColumnIndex()).equals("2")) {
							dto.setDay(cell.getStringCellValue());
						}

						if (String.valueOf(cell.getColumnIndex()).equals("3")) {
							dto.setName(cell.getStringCellValue());
						}

						if (String.valueOf(cell.getColumnIndex()).equals("4")) {
							dto.setCircle(cell.getStringCellValue());
						}

						if (String.valueOf(cell.getColumnIndex()).equals("5")) {
							dto.setState(cell.getStringCellValue());
						}
						if (String.valueOf(cell.getColumnIndex()).equals("6")) {
							dto.setFinYr(cell.getStringCellValue());
						}
						if (String.valueOf(cell.getColumnIndex()).equals("7")) {
							dto.setQuarter(cell.getStringCellValue());
						}

					}
					
					logger.info(" - ");

				} // 1st close while loop
				lidtDto.add(dto);
				if((lidtDto.get(0).getHolidayDate()==null) 
						|| (lidtDto.get(0).getDay()==null)
						|| (lidtDto.get(0).getName()==null)
						|| (lidtDto.get(0).getCircle()==null)
						|| (lidtDto.get(0).getState()==null)
						|| (lidtDto.get(0).getFinYr()==null)
						|| (lidtDto.get(0).getQuarter()==null) ) {
					logger.error("Header missing in file!!");
					
					return "Header missing in file";
				}
				
			} // 2nd close while loop

			HolidayCalendar entity = null;
			List<HolidayCalendar> listEntity = new ArrayList<HolidayCalendar>();
			List<HolidayCalendar> listEntity1 = new ArrayList<HolidayCalendar>();
			int count = 0;
			if( lidtDto.size() == 0)
			{
				logger.error("Blank File for upload!!");
				
				return "Blank File for upload";
			}
			if( lidtDto.size() == 1)
			{
				logger.error("Blank File(Fill only Column name) for upload!!");
				
				return "Blank File(Fill only Column name) for upload";
			}
			for (HolidayCalendarDto lidtDto1 : lidtDto) {
				if (count != 0) {
					entity = new HolidayCalendar();
					
				//	entity.setHolidayDate(lidtDto1.getHolidayDate());
					/*
					 * String holidayDate = ""; try { String sDate1=lidtDto1.getHolidayDate();
					 * 
					 * logger.info("Holiday date in entity format: "+sDate1); // SimpleDateFormat
					 * formatter = new SimpleDateFormat("mm/dd/yyyy"); SimpleDateFormat formatter =
					 * new SimpleDateFormat("dd-mm-yyyy"); Date date = formatter.parse(sDate1);
					 * holidayDate = new SimpleDateFormat("dd-mm-yyyy").format(date);
					 * 
					 * logger.info("Holiday date in String format: "+holidayDate);
					 * entity.setHolidayDate(holidayDate); } catch (Exception e) {
					 * logger.error("Exception "+ExceptionConstants.DATE_EXCEPTION); holidayDate =
					 * ""; lidtDto1.setHolidayDate(""); entity.setHolidayDate(holidayDate);//3 }
					 */
					String holidayDate = "";
					try {	
						  String sDate1=lidtDto1.getHolidayDate();
						  
						  logger.info("Holiday date in entity format: "+sDate1);
						 
						  sDate1= sDate1.replaceAll("/", "-")
						  		.replaceAll("-", "-");
						  logger.info("replaced date in entity format: "+sDate1); 
						 				
						  Date date =new Date();
					
						  SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
					
						  date = formatter.parse(sDate1);
					
						  holidayDate =  new SimpleDateFormat("dd-mm-yyyy").format(date);
						   
						  logger.info("Holiday date in String format: "+holidayDate);
						  entity.setHolidayDate(holidayDate);
					}
					catch (Exception e) {
						logger.error("Exception "+ExceptionConstants.DATE_EXCEPTION);
						holidayDate = "";
						lidtDto1.setHolidayDate("");
						 entity.setHolidayDate(holidayDate);//3
					} 
					entity.setDay(lidtDto1.getDay());
					entity.setName(lidtDto1.getName());// null
					entity.setCircle(lidtDto1.getCircle());
					entity.setState(lidtDto1.getState());
					entity.setFinYr(lidtDto1.getFinYr());
					entity.setQuarter(lidtDto1.getQuarter());
					listEntity.add(entity);
				}
				count++;
				// ==== not null check
				Optional<String> checkNullDay = Optional.ofNullable(lidtDto1.getDay());
				Optional<String> checkNullCircle = Optional.ofNullable(lidtDto1.getCircle());
				Optional<String> checkNullState = Optional.ofNullable(lidtDto1.getState());
				Optional<String> checkNullDate = Optional.ofNullable(lidtDto1.getHolidayDate());
				
				Optional<String> checkName = Optional.ofNullable(lidtDto1.getName());
				
				if ((!checkNullDay.isPresent() || checkNullDay.get().trim().equals(""))
						&& (!checkNullCircle.isPresent() || checkNullCircle.get().trim().equals(""))
						&& (!checkNullState.isPresent() || checkNullState.get().trim().equals(""))
						&& (!checkNullDate.isPresent() || checkNullDate.get().trim().equals(""))
						&& (!checkName.isPresent() || checkName.get().trim().equals(""))) {
					
				}else				
				if (!checkNullDay.isPresent() || !checkNullCircle.isPresent() || !checkNullState.isPresent() || ! checkNullDate.isPresent() 
						|| checkNullDay.get().trim().equals("") || checkNullCircle.get().trim().equals("")  || checkNullState.get().trim().equals("")  || checkNullDate.get().trim().equals("") ) {
					
					entity = new HolidayCalendar();
					entity.setHolidayDate(lidtDto1.getHolidayDate());
					entity.setDay(lidtDto1.getDay());
					entity.setName(lidtDto1.getName());// null
					entity.setCircle(lidtDto1.getCircle());
					entity.setState(lidtDto1.getState());
					entity.setFinYr(lidtDto1.getFinYr());
					entity.setQuarter(lidtDto1.getQuarter());
					listEntity1.add(entity);
					//HolidayCalendarxlsx(listEntity1);

				}

			}
			if(listEntity1 !=null && listEntity1.size() > 0)
			{   
				HolidayCalendarxlsx(listEntity1);
				logger.info("Holiday Calendar Data Not Uploaded");
				return "Data Not Uploaded";
			}
		
			else { 
				List<HolidayCalendar> listEntityDup = new ArrayList<HolidayCalendar>();
				for (HolidayCalendar listEntityNew : listEntity) {
				String holidayDate=holidayCalendarRepository.findByHolidayDate(listEntityNew.getHolidayDate(),listEntityNew.getCircle(),listEntityNew.getState());
				if(holidayDate!=null  && !holidayDate.isEmpty()){
					entity = new HolidayCalendar();
					entity.setHolidayDate(listEntityNew.getHolidayDate());
					entity.setDay(listEntityNew.getDay());
					entity.setName(listEntityNew.getName());// null
					entity.setCircle(listEntityNew.getCircle());
					entity.setState(listEntityNew.getState());
					entity.setFinYr(listEntityNew.getFinYr());
					entity.setQuarter(listEntityNew.getQuarter());
					listEntityDup.add(entity);
					
					
				}
				}
				if(listEntityDup !=null && listEntityDup.size() > 0)
				{   
					HolidayCalendarxlsx(listEntityDup);
					logger.info("Holiday Date is Already Exist");
					return "Holiday Date is Already Exist";
				}
				else{
					
					Iterable<HolidayCalendar> result = holidayCalendarRepository.saveAll(listEntity);
					if (result != null)
					logger.info("Holiday Calendar Data Uploaded Successfully");
						return "Holiday_Calendar";
					
				}
				
				
			}
		}

		catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
			e.printStackTrace();
		} finally {
			try {
				if (workbook != null) {
					workbook.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}

			} catch (Exception e) {
				logger.error("Exception "+ExceptionConstants.EXCEPTION);
				e.printStackTrace();
			}
		}
		return "Data Not Uploaded";
	}

	// ---xlsx HolidayCalendarxlsx
	public void HolidayCalendarxlsx(List<HolidayCalendar> listEntity1) {

		XSSFWorkbook workbook1 = new XSSFWorkbook();
        String filename ="Holiday_Calendar.xlsx" ;
		XSSFSheet sheet = workbook1.createSheet("HolidayCalendar");
		String[] columns = {"HOLIDAY_DATE", "Day","Name","Circle","State","FIN_YR","QUARTER"};
		
		Font headerFont = workbook1.createFont();
        headerFont.setBold(true);
     // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook1.createCellStyle();
	    headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Create cells
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            
            cell.setCellStyle(headerCellStyle);
        }
		
		int rownum = 1;
		 int rowIndex = 1;
	        for(HolidayCalendar entity : listEntity1){
	            Row row = sheet.createRow(rowIndex++);
	            int cellIndex = 0;
	            row.createCell(cellIndex++).setCellValue(entity.getHolidayDate());
	            row.createCell(cellIndex++).setCellValue(entity.getDay());
	            row.createCell(cellIndex++).setCellValue(entity.getName());
	            row.createCell(cellIndex++).setCellValue(entity.getCircle());
	            row.createCell(cellIndex++).setCellValue(entity.getState());
	            row.createCell(cellIndex++).setCellValue(entity.getFinYr());
	            row.createCell(cellIndex++).setCellValue(entity.getQuarter());
	        }
		
		try {
			// this Writes the workbook KioskC
			logger.info("Error file path: "+reportPath1+filename);
			FileOutputStream out = new FileOutputStream(
					new File(reportPath1+filename));
			
			workbook1.write(out);
			out.close();
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
			e.printStackTrace();
		}

	}

// -----------------2 Kiosk CMF	

	@Override
	public String uploadKioskCMFInformation(String path) {


		try {

			inputStream = new FileInputStream(new File(path));
			logger.info("7 A.file read successfully!! "+ path);
			workbook = new XSSFWorkbook(inputStream);
			logger.info("7 B.workbook parsing started!!");
			/* HashMap<Integer,String> map=null; */
			org.apache.poi.ss.usermodel.Sheet firstSheet = workbook.getSheetAt(0);

			logger.info("7 C.First worksheet fetched!!");
			/*
			 * DataFormatter objDefaultFormat = new DataFormatter(); FormulaEvaluator
			 * objFormulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);
			 */

			Iterator<Row> iterator = firstSheet.iterator();
			List<KioskCMFDto> lidtDto = new ArrayList<>();
			int i = 0, j=0;
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				KioskCMFDto dto = new KioskCMFDto();
				String [] columns = {"ID","PF_ID","KIOSK_ID"};
				String [] columnsNew = {};
				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();
					
					if(String.valueOf(cell.getRow().getRowNum()).equals("0")) 
					{
						 
						/*
						 * if(String.valueOf(cell.getColumnIndex()).equals("0")) {
						 * if(!((String.valueOf(cell.getColumnIndex()).equals("0")) &&
						 * (cell.getStringCellValue().equalsIgnoreCase("ID")))) {
						 * logger.error("Wrong File or Data Sequence for upload!!");
						 * 
						 * return "Wrong File or Data Sequence for upload"; } }
						 */
						if((String.valueOf(cell.getColumnIndex()).equals("1")) ||((cell.getRow().getPhysicalNumberOfCells())==1)) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("1")) && (cell.getStringCellValue().equalsIgnoreCase("PF_ID"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						} 
						}
						if (String.valueOf(cell.getColumnIndex()).equals("1")) {
							
							dto.setCmfPfId(cell.getStringCellValue());
						}
						if(String.valueOf(cell.getColumnIndex()).equals("2")) {
						if(!((String.valueOf(cell.getColumnIndex()).equals("2")) && (cell.getStringCellValue().equalsIgnoreCase("KIOSK_ID"))))
						{
							logger.error("Wrong File or Data Sequence for upload!!");
							
							return "Wrong File or Data Sequence for upload";
						} 
						}
						if (String.valueOf(cell.getColumnIndex()).equals("2")) {
							
							dto.setKioskId(cell.getStringCellValue());
						}
					}
				//	switch (cell.getCellType()) {
				//	case STRING:
						if (!(String.valueOf(cell.getRow().getRowNum()).equals("0"))) {
						

							/*
							 * if (String.valueOf(cell.getColumnIndex()).equals("1")) {
							 * dto.setCmfPfId(cell.getStringCellValue()); }
							 */
							if (String.valueOf(cell.getColumnIndex()).equals("2")) {
							
								dto.setKioskId(cell.getStringCellValue());
							}

				//		}
				//		break;
				//	case NUMERIC:
				//		if (!(String.valueOf(cell.getRow().getRowNum()).equals("0"))) {
							if (String.valueOf(cell.getColumnIndex()).equals("1")) {
							
								dto.setCmfPfId((String.valueOf((int)cell.getNumericCellValue())));
							}
							/*
							 * if (String.valueOf(cell.getColumnIndex()).equals("2")) {
							 * 
							 * dto.setKioskId((String.valueOf((int)cell.getNumericCellValue()))); }
							 */

				//			break;
						}

				//	}
					logger.info(" 1st close while loop- "+i++);

				} // 1st close while loop
				lidtDto.add(dto);
				if(lidtDto.get(0).getCmfPfId()==null)
				{
					logger.error("Header missing in file!!");
					
					return "Header missing in file";
				}
				if(lidtDto.get(0).getKioskId() == null)
				{
					
					logger.error("Header missing in file");
					
					return "Header missing in file";
				}
				//lidtDto.remove(0);
				logger.info("2nd close while loop - "+j++);
			} // 2nd close while loop

			UserKioskMapping entity = null;
			List<UserKioskMapping> listEntity = new ArrayList<UserKioskMapping>();
			List<UserKioskMapping> listEntity1 = new ArrayList<UserKioskMapping>();
			int count = 0;
			if( lidtDto.size() == 0)
			{
				logger.error("Blank File for upload!!");
				
				return "Blank File for upload";
			}
			if( lidtDto.size() == 1)
			{
				logger.error("Blank File(Fill only Column name) for upload!!");
				
				return "Blank File(Fill only Column name) for upload";
			}
			for (KioskCMFDto lidtDto1 : lidtDto) {
				if (count != 0) {
					entity = new UserKioskMapping();
					entity.setPfId(lidtDto1.getCmfPfId());
					entity.setKioskId(lidtDto1.getKioskId());
					listEntity.add(entity);

				}
				count++;
				Optional<String> checkNullCmfPfId = Optional.ofNullable(lidtDto1.getCmfPfId());
				Optional<String> checkNullgetKioskId = Optional.ofNullable(lidtDto1.getKioskId());
				if ((!checkNullgetKioskId.isPresent() || checkNullgetKioskId.get().trim().equals(""))						
						&& (!checkNullCmfPfId.isPresent() || checkNullCmfPfId.get().trim().equals(""))) { logger.info("i m inside if clause: "+ count);
					
				}else
				if (!checkNullgetKioskId.isPresent() || !checkNullCmfPfId.isPresent()
						|| checkNullgetKioskId.get().trim().equals("") || checkNullCmfPfId.get().trim().equals("")) { logger.info("i m inside else-if clause: "+ count);
					entity = new UserKioskMapping();
					entity.setPfId(lidtDto1.getCmfPfId());
					entity.setKioskId(lidtDto1.getKioskId());
					listEntity1.add(entity);
					
					//emptyxlsx(listEntity1);

				}
			}
			if(listEntity1 !=null && listEntity1.size() > 0)
			{	
				emptyxlsx(listEntity1);
				logger.info("Kiosk_CMF Data Not Uploaded");
				return "Data Not Uploaded";
			}
			else {
				List<UserKioskMapping> listEntityDup = new ArrayList<UserKioskMapping>();
				for (UserKioskMapping listEntityNew : listEntity) {
				String kioskId=userKioskMappingRepository.findByKioskid(listEntityNew.getKioskId());
				if(kioskId!=null  && !kioskId.isEmpty()){
					entity = new UserKioskMapping();
					entity.setPfId(listEntityNew.getPfId());
					entity.setKioskId(listEntityNew.getKioskId());
					listEntityDup.add(entity);
					
					
				}
				}
				if(listEntityDup !=null && listEntityDup.size() > 0)
				{   
					emptyxlsx(listEntityDup);
					logger.info("Kiosk Id is Already Exist");
					return "Kiosk Id is Already Exist";
				}
				else{
				Iterable<UserKioskMapping> result = kioskCMFRepository.saveAll(listEntity);
				if (result != null)
				logger.info("Kiosk_CMF Data Uploaded Successfully");
					return "Kiosk_CMF";
				}
			}
			
			
		}

		catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
			e.printStackTrace();
		} finally {
			try {
				if (workbook != null) {
					workbook.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}

			} catch (Exception e) {
				logger.error("Exception "+ExceptionConstants.EXCEPTION);
				e.printStackTrace();
			}
		}
		return "Due to Error Data Not Uploaded";
	}

	// create xlsx file
	public void emptyxlsx(List<UserKioskMapping> listEntity1) {

		XSSFWorkbook workbook1 = new XSSFWorkbook();

		XSSFSheet sheet = workbook1.createSheet("Kiosk_CMF");
		String[] columns = {"PFID", "Kiosk_ID"};
	    String filename = "Kiosk_CMF.xlsx";
	    
	    
	  //  CreationHelper createHelper = workbook1.getCreationHelper();
	 // Create a Font for styling header cells
        Font headerFont = workbook1.createFont();
        headerFont.setBold(true);
     
      
	    // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook1.createCellStyle();
	    headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Create cells
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            
            cell.setCellStyle(headerCellStyle);
        }
        
		int rownum = 1;
		 int rowIndex = 1;
	        for(UserKioskMapping entity : listEntity1){
	            Row row = sheet.createRow(rowIndex++);
	            int cellIndex = 0;
	            //first place in row is pfid
	            row.createCell(cellIndex++).setCellValue(entity.getPfId());
	 
	            //second place in row is marks in kioskid
	            row.createCell(cellIndex++).setCellValue(entity.getKioskId());
	        }
		
		try {
			// this Writes the workbook KioskC  reportPath1
			logger.info("Error file path: "+reportPath1+filename);
			FileOutputStream out = new FileOutputStream(
					new File(reportPath1+filename));
			workbook1.write(out);
			out.close();
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
			e.printStackTrace();
		}

	}

	// -----------------5 Vendor Invoice	

		@SuppressWarnings("unlikely-arg-type")
		@Override
		public String uploadInvVendorInformation(String path) {


			try {

				inputStream = new FileInputStream(new File(path));
				logger.info("7 A.file read successfully!! "+ path);
				workbook = new XSSFWorkbook(inputStream);
				logger.info("7 B.workbook parsing started!!");
				/* HashMap<Integer,String> map=null; */
				org.apache.poi.ss.usermodel.Sheet firstSheet = workbook.getSheetAt(0);

				logger.info("7 C.First worksheet fetched!!");
				
				DataFormatter objDefaultFormat = new DataFormatter();
				FormulaEvaluator objFormulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);

				Iterator<Row> iterator = firstSheet.iterator();
				List<InvoiceVendorDto> lidtDto = new ArrayList<>();
				int i = 0, j=0;
				while (iterator.hasNext()) {
					Row nextRow = iterator.next();
					Iterator<Cell> cellIterator = nextRow.cellIterator();
					InvoiceVendorDto dto = new InvoiceVendorDto();

					while (cellIterator.hasNext()) {

						Cell cell = cellIterator.next();
					
						if(String.valueOf(cell.getRow().getRowNum()).equals("0")) 
						{
							if(String.valueOf(cell.getColumnIndex()).equals("0")) {
								if(!((String.valueOf(cell.getColumnIndex()).equals("0")) && (cell.getStringCellValue().equalsIgnoreCase("FIN_YR"))))
								{
									logger.error("Wrong File or Data Sequence for upload!!");
									
									return "Wrong File or Data Sequence for upload";
								}
								if (String.valueOf(cell.getColumnIndex()).equals("0")) {
									
									dto.setFinYear(cell.getStringCellValue());
								}
							}
							if(String.valueOf(cell.getColumnIndex()).equals("1")) {
							if(!((String.valueOf(cell.getColumnIndex()).equals("1")) && (cell.getStringCellValue().equalsIgnoreCase("INVO_NO"))))
							{
								logger.error("Wrong File or Data Sequence for upload!!");
								
								return "Wrong File or Data Sequence for upload";
							} 
							if (String.valueOf(cell.getColumnIndex()).equals("1")) {
								
								dto.setInvNo(cell.getStringCellValue().equalsIgnoreCase("INVO_NO") ? 1 : 0);
								
							}
							}
							if(String.valueOf(cell.getColumnIndex()).equals("2")) {
							if(!((String.valueOf(cell.getColumnIndex()).equals("2")) && (cell.getStringCellValue().equalsIgnoreCase("INVO_DT"))))
							{
								logger.error("Wrong File or Data Sequence for upload!!");
								
								return "Wrong File or Data Sequence for upload";
							} 
							if (String.valueOf(cell.getColumnIndex()).equals("2")) {
								
								dto.setInvDt(cell.getStringCellValue());
							}
							}
					
							if(String.valueOf(cell.getColumnIndex()).equals("3")) {
								if(!((String.valueOf(cell.getColumnIndex()).equals("3")) && (cell.getStringCellValue().equalsIgnoreCase("CUST_NAME"))))
								{
									logger.error("Wrong File or Data Sequence for upload!!");
									
									return "Wrong File or Data Sequence for upload";
								}
								if (String.valueOf(cell.getColumnIndex()).equals("3")) {
									
									dto.setCusName(cell.getStringCellValue());
								}
							}
							if(String.valueOf(cell.getColumnIndex()).equals("4")) {
							if(!((String.valueOf(cell.getColumnIndex()).equals("4")) && (cell.getStringCellValue().equalsIgnoreCase("PRN_SRN"))))
							{
								logger.error("Wrong File or Data Sequence for upload!!");
								
								return "Wrong File or Data Sequence for upload";
							} 
							if (String.valueOf(cell.getColumnIndex()).equals("4")) {
								
								dto.setPrnSrn(cell.getStringCellValue());
							}
							}
							if(String.valueOf(cell.getColumnIndex()).equals("5")) {
							if(!((String.valueOf(cell.getColumnIndex()).equals("5")) && (cell.getStringCellValue().equalsIgnoreCase("Product"))))
							{
								logger.error("Wrong File or Data Sequence for upload!!");
								
								return "Wrong File or Data Sequence for upload";
							} 
							if (String.valueOf(cell.getColumnIndex()).equals("5")) {
								
								dto.setProduct(cell.getStringCellValue());
							}
							}
							if(String.valueOf(cell.getColumnIndex()).equals("6")) {
								if(!((String.valueOf(cell.getColumnIndex()).equals("6")) && (cell.getStringCellValue().equalsIgnoreCase("INVO_FROM"))))
								{
									logger.error("Wrong File or Data Sequence for upload!!");
									
									return "Wrong File or Data Sequence for upload";
								}
								if (String.valueOf(cell.getColumnIndex()).equals("6")) {
									
									dto.setInvoiceFrom(cell.getStringCellValue());
								}
							}
							if(String.valueOf(cell.getColumnIndex()).equals("7")) {
							if(!((String.valueOf(cell.getColumnIndex()).equals("7")) && (cell.getStringCellValue().equalsIgnoreCase("INVO_UPTO"))))
							{
								logger.error("Wrong File or Data Sequence for upload!!");
								
								return "Wrong File or Data Sequence for upload";
							} 
							if (String.valueOf(cell.getColumnIndex()).equals("7")) {
								
								dto.setInvoiceUpTo(cell.getStringCellValue());
							}
							}
							if(String.valueOf(cell.getColumnIndex()).equals("8")) {
							if(!((String.valueOf(cell.getColumnIndex()).equals("8")) && (cell.getStringCellValue().equalsIgnoreCase("INVO_AMT"))))
							{
								logger.error("Wrong File or Data Sequence for upload!!");
								
								return "Wrong File or Data Sequence for upload";
							} 
							if (String.valueOf(cell.getColumnIndex()).equals("8")) {
								
							//	dto.setInvoiceAmt((float)cell.getNumericCellValue());
								dto.setInvoiceAmt((float) (cell.getStringCellValue().equalsIgnoreCase("INVO_AMT") ? 1 : 0));
							}
							}
							if(String.valueOf(cell.getColumnIndex()).equals("9")) {
								if(!((String.valueOf(cell.getColumnIndex()).equals("9")) && (cell.getStringCellValue().equalsIgnoreCase("SHIP_ADD"))))
								{
									logger.error("Wrong File or Data Sequence for upload!!");
									
									return "Wrong File or Data Sequence for upload";
								} 
								if (String.valueOf(cell.getColumnIndex()).equals("9")) {
									
									dto.setShipAdd(cell.getStringCellValue());
								}
								}
							if(String.valueOf(cell.getColumnIndex()).equals("10")) {
								if(!((String.valueOf(cell.getColumnIndex()).equals("10")) && (cell.getStringCellValue().equalsIgnoreCase("SHIP_STATE"))))
								{
									logger.error("Wrong File or Data Sequence for upload!!");
									
									return "Wrong File or Data Sequence for upload";
								} 
								if (String.valueOf(cell.getColumnIndex()).equals("10")) {
									
									dto.setShipState(cell.getStringCellValue());
								}
								}
						}

						switch (cell.getCellType()) {
						case STRING:
							if (!(String.valueOf(cell.getRow().getRowNum()).equals("0"))) {
							
								if (String.valueOf(cell.getColumnIndex()).equals("0")) {
									dto.setFinYear(cell.getStringCellValue());
								}
								
								if (String.valueOf(cell.getColumnIndex()).equals("3")) {
								
									dto.setCusName(cell.getStringCellValue());
								}
								if (String.valueOf(cell.getColumnIndex()).equals("4")) {
									dto.setPrnSrn(cell.getStringCellValue());
								}
								if (String.valueOf(cell.getColumnIndex()).equals("5")) {
								
									dto.setProduct(cell.getStringCellValue());
								}
								
								if (String.valueOf(cell.getColumnIndex()).equals("9")) {
									dto.setShipAdd(cell.getStringCellValue());
								}
								if (String.valueOf(cell.getColumnIndex()).equals("10")) {
								
									dto.setShipState(cell.getStringCellValue());
								}
								
								
							}
							break;
						case NUMERIC:
							if (!(String.valueOf(cell.getRow().getRowNum()).equals("0"))) {
								if (String.valueOf(cell.getColumnIndex()).equals("1")) {
								
									dto.setInvNo((int)cell.getNumericCellValue());
								}
								if (String.valueOf(cell.getColumnIndex()).equals("8")) {
								
									dto.setInvoiceAmt((float)cell.getNumericCellValue());
								}
								if (String.valueOf(cell.getColumnIndex()).equals("2")) {
									String cellValueStr = objDefaultFormat.formatCellValue(cell, objFormulaEvaluator);	
									dto.setInvDt(cellValueStr);

								}
								if (String.valueOf(cell.getColumnIndex()).equals("6")) {
									String cellValueStr = objDefaultFormat.formatCellValue(cell, objFormulaEvaluator);	
									dto.setInvoiceFrom(cellValueStr);
								}
								if (String.valueOf(cell.getColumnIndex()).equals("7")) {
									String cellValueStr = objDefaultFormat.formatCellValue(cell, objFormulaEvaluator);	
									dto.setInvoiceUpTo(cellValueStr);
								}
								break;
							}

						}
						logger.info(" 1st close while loop- "+i++);

					} // 1st close while loop
					lidtDto.add(dto);
					if((lidtDto.get(0).getFinYear()==null) 
							|| (lidtDto.get(0).getInvNo()==null)
							|| (lidtDto.get(0).getInvDt()==null)
							|| (lidtDto.get(0).getCusName()==null)
							|| (lidtDto.get(0).getPrnSrn()==null)
							|| (lidtDto.get(0).getProduct()==null)
							|| (lidtDto.get(0).getInvoiceFrom()==null)
									|| (lidtDto.get(0).getInvoiceUpTo()==null)
									|| (lidtDto.get(0).getInvoiceAmt()==null)
									|| (lidtDto.get(0).getShipAdd()==null)
									|| (lidtDto.get(0).getShipState()==null) ) {
						logger.error("Header missing in file!!");
						
						return "Header missing in file";
					}
					logger.info("2nd close while loop - "+j++);
				} // 2nd close while loop

				InvoiceVendor entity = null;
				List<InvoiceVendor> listEntity = new ArrayList<InvoiceVendor>();
				List<InvoiceVendor> listEntity1 = new ArrayList<InvoiceVendor>();
				int count = 0;
				if( lidtDto.size() == 0)
				{
					logger.error("Blank File for upload!!");
					
					return "Blank File for upload";
				}
				if( lidtDto.size() == 1)
				{
					logger.error("Blank File(Fill only Column name) for upload!!");
					
					return "Blank File(Fill only Column name) for upload";
				}
				for (InvoiceVendorDto lidtDto1 : lidtDto) {
					if (count != 0) {
						entity = new InvoiceVendor();
						entity.setFinYear(lidtDto1.getFinYear());
						entity.setInvNo(lidtDto1.getInvNo());
					//	entity.setInvDt(lidtDto1.getInvDt());
						String invoiceDate = "";
						try {
						String sDate1=lidtDto1.getInvDt();
						  
						  logger.info("invoice date in entity format: "+sDate1); 
						  
						  sDate1= sDate1.replaceAll("/", "-")
							  		.replaceAll("-", "-");
							  logger.info("replaced date in entity format: "+sDate1); 
							 				
							  Date date =new Date();
						
							  SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
						
							  date = formatter.parse(sDate1);
						  
							  invoiceDate =  new SimpleDateFormat("dd-mm-yyyy").format(date);
						  
						  logger.info("installationDate date in String format: "+invoiceDate);
						  entity.setInvDt(invoiceDate);//3
						}
						catch (Exception e) {
							logger.error("Exception "+ExceptionConstants.DATE_EXCEPTION);
							invoiceDate = "";
							lidtDto1.setInvDt("");
							 entity.setInvDt(invoiceDate);//3
						} 
						entity.setCusName(lidtDto1.getCusName());
						entity.setPrnSrn(lidtDto1.getPrnSrn());
						entity.setProduct(lidtDto1.getProduct());
					//	entity.setInvoiceFrom(lidtDto1.getInvoiceFrom());
						String invoiceFrom = "";
						try {
						String sDate1=lidtDto1.getInvoiceFrom();
						  
						  logger.info("invoice date in entity format: "+sDate1); 
						  
						  sDate1= sDate1.replaceAll("/", "-")
							  		.replaceAll("-", "-");
							  logger.info("replaced date in entity format: "+sDate1); 
							 				
							  Date date =new Date();
						
							  SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
						
							  date = formatter.parse(sDate1);
						  
							  invoiceFrom =  new SimpleDateFormat("dd-mm-yyyy").format(date);
						  
						  logger.info("installationDate date in String format: "+invoiceFrom);
						  entity.setInvoiceFrom(invoiceFrom);//3
						}
						catch (Exception e) {
							logger.error("Exception "+ExceptionConstants.DATE_EXCEPTION);
							invoiceFrom = "";
							lidtDto1.setInvoiceFrom("");
							 entity.setInvoiceFrom(invoiceFrom);//3
						}
					//	entity.setInvoiceUpTo(lidtDto1.getInvoiceUpTo());
						String invoiceUpto = "";
						try {
						String sDate1=lidtDto1.getInvoiceUpTo();
						  
						  logger.info("invoice date in entity format: "+sDate1); 
						  
						  sDate1= sDate1.replaceAll("/", "-")
							  		.replaceAll("-", "-");
							  logger.info("replaced date in entity format: "+sDate1); 
							 				
							  Date date =new Date();
						
							  SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
						
							  date = formatter.parse(sDate1);
						  
							  invoiceUpto =  new SimpleDateFormat("dd-mm-yyyy").format(date);
						  
						  logger.info("installationDate date in String format: "+invoiceUpto);
						  entity.setInvoiceUpTo(invoiceUpto);//3
						}
						catch (Exception e) {
							logger.error("Exception "+ExceptionConstants.DATE_EXCEPTION);
							invoiceUpto = "";
							lidtDto1.setInvoiceUpTo("");
							 entity.setInvoiceUpTo(invoiceUpto);//3
						}
						entity.setInvoiceAmt(lidtDto1.getInvoiceAmt());
						entity.setShipAdd(lidtDto1.getShipAdd());
						entity.setShipState(lidtDto1.getShipState());
						
						listEntity.add(entity);

					}
					count++;
					Optional<String> checkNullFinYear = Optional.ofNullable(lidtDto1.getFinYear());
					Optional<Integer> checkNullInvNo = Optional.ofNullable(lidtDto1.getInvNo());
					Optional<String> checkNullInvDt = Optional.ofNullable(lidtDto1.getInvDt());
					Optional<String> checkNullCusName = Optional.ofNullable(lidtDto1.getCusName());
					Optional<String> checkNullPrnSrn = Optional.ofNullable(lidtDto1.getPrnSrn());
					Optional<String> checkNullProduct = Optional.ofNullable(lidtDto1.getProduct());
					Optional<String> checkNullInvoiceFrom = Optional.ofNullable(lidtDto1.getInvoiceFrom());
					Optional<String> checkNullInvoiceUpTo = Optional.ofNullable(lidtDto1.getInvoiceUpTo());
					Optional<Float> checkNullInvoiceAmt = Optional.ofNullable(lidtDto1.getInvoiceAmt());
					Optional<String> checkNullShipAdd = Optional.ofNullable(lidtDto1.getShipAdd());
					Optional<String> checkNullShipState = Optional.ofNullable(lidtDto1.getShipState());
					
					if ((!checkNullFinYear.isPresent() || checkNullFinYear.get().trim().equals(""))		
							&& (!checkNullInvNo.isPresent() || checkNullInvNo.get().equals(0))
							&& (!checkNullInvDt.isPresent() || checkNullInvDt.get().equals(""))
							&& (!checkNullCusName.isPresent() || checkNullCusName.get().equals(""))
							&& (!checkNullPrnSrn.isPresent() || checkNullPrnSrn.get().equals(""))
							&& (!checkNullProduct.isPresent() || checkNullProduct.get().equals(""))
							&& (!checkNullInvoiceFrom.isPresent() || checkNullInvoiceFrom.get().equals(""))
							&& (!checkNullInvoiceUpTo.isPresent() || checkNullInvoiceUpTo.get().equals(""))
							&& (!checkNullInvoiceAmt.isPresent() || checkNullInvoiceAmt.get().equals(0))
							&& (!checkNullShipAdd.isPresent() || checkNullShipAdd.get().equals(""))
							&& (!checkNullShipState.isPresent() || checkNullShipState.get().equals(""))) { logger.info("i m inside if clause: "+ count);
						
					}else
					if (!checkNullFinYear.isPresent() || checkNullFinYear.get().trim().equals("")		
							|| !checkNullInvNo.isPresent() || checkNullInvNo.get().equals(0)
							|| !checkNullInvDt.isPresent() || checkNullInvDt.get().equals("")
							|| !checkNullCusName.isPresent() || checkNullCusName.get().equals("")
							|| !checkNullPrnSrn.isPresent() || checkNullPrnSrn.get().equals("")
							|| !checkNullProduct.isPresent() || checkNullProduct.get().equals("")
							|| !checkNullInvoiceFrom.isPresent() || checkNullInvoiceFrom.get().equals("")
							|| !checkNullInvoiceUpTo.isPresent() || checkNullInvoiceUpTo.get().equals("")
							|| !checkNullInvoiceAmt.isPresent() || checkNullInvoiceAmt.get().equals(0)
							|| !checkNullShipAdd.isPresent() || checkNullShipAdd.get().equals("")
							|| !checkNullShipState.isPresent() || checkNullShipState.get().equals("")) { logger.info("i m inside else-if clause: "+ count);
						entity = new InvoiceVendor();
						entity.setFinYear(lidtDto1.getFinYear());
						entity.setInvNo(lidtDto1.getInvNo()== null ? 0 : lidtDto1.getInvNo() );
						entity.setInvDt(lidtDto1.getInvDt());
						entity.setCusName(lidtDto1.getCusName());
						entity.setPrnSrn(lidtDto1.getPrnSrn());
						entity.setProduct(lidtDto1.getProduct());
						entity.setInvoiceFrom(lidtDto1.getInvoiceFrom());
						entity.setInvoiceUpTo(lidtDto1.getInvoiceUpTo());
					//	entity.setInvoiceAmt(lidtDto1.getInvoiceAmt());
						entity.setInvoiceAmt((float) (lidtDto1.getInvoiceAmt()== null ? 0.0 : lidtDto1.getInvoiceAmt()) );
						entity.setShipAdd(lidtDto1.getShipAdd());
						entity.setShipState(lidtDto1.getShipState());
						
						listEntity1.add(entity);
						
						//emptyxlsx(listEntity1);

					}
				}
				if(listEntity1 !=null && listEntity1.size() > 0)
				{	
					vendorInvxlsx(listEntity1);
					logger.info("Vendor Invoice Data Not Uploaded");
					return "Data Not Uploaded";
				}
				else {
					List<InvoiceVendor> listEntityDup = new ArrayList<InvoiceVendor>();
					for (InvoiceVendor listEntityNew : listEntity) {
					String prnSrn=invoiceVendorRepository.findDuplicate(listEntityNew.getPrnSrn(),listEntityNew.getInvoiceFrom(),listEntityNew.getInvoiceUpTo());
					if(prnSrn!=null  && !prnSrn.isEmpty()){
						entity = new InvoiceVendor();
						entity.setFinYear(listEntityNew.getFinYear());
						entity.setInvNo(listEntityNew.getInvNo()== null ? 0 : listEntityNew.getInvNo() );
						entity.setInvDt(listEntityNew.getInvDt());
						entity.setCusName(listEntityNew.getCusName());
						entity.setPrnSrn(listEntityNew.getPrnSrn());
						entity.setProduct(listEntityNew.getProduct());
						entity.setInvoiceFrom(listEntityNew.getInvoiceFrom());
						entity.setInvoiceUpTo(listEntityNew.getInvoiceUpTo());
					//	entity.setInvoiceAmt(lidtDto1.getInvoiceAmt());
						entity.setInvoiceAmt((float) (listEntityNew.getInvoiceAmt()== null ? 0.0 : listEntityNew.getInvoiceAmt()) );
						entity.setShipAdd(listEntityNew.getShipAdd());
						entity.setShipState(listEntityNew.getShipState());
						listEntityDup.add(entity);
						
						
					}
					}
					if(listEntityDup !=null && listEntityDup.size() > 0)
					{   
						vendorInvxlsx(listEntityDup);
						logger.info("Invoice No is Already Exist");
						return "Invoice No is Already Exist";
					}
					else{
					Iterable<InvoiceVendor> result = invoiceVendorRepository.saveAll(listEntity);
					if (result != null)
					logger.info("Vendor Invoice Data Uploaded Successfully");
						return "Vendor_Invoice";
					}
				}
				
				
			}

			catch (Exception e) {
				logger.error("Exception "+ExceptionConstants.EXCEPTION);
				e.printStackTrace();
			} finally {
				try {
					if (workbook != null) {
						workbook.close();
					}
					if (inputStream != null) {
						inputStream.close();
					}

				} catch (Exception e) {
					logger.error("Exception "+ExceptionConstants.EXCEPTION);
					e.printStackTrace();
				}
			}
			return "Due to Error Data Not Uploaded";
		}

		// create xlsx file
		public void vendorInvxlsx(List<InvoiceVendor> listEntity1) {

			XSSFWorkbook workbook1 = new XSSFWorkbook();

			XSSFSheet sheet = workbook1.createSheet("Vendor_Invoice");
			String[] columns = {"FIN_YR","INVO_NO","INVO_DT","CUST_NAME","PRN_SRN","Product","INVO_FROM","INVO_UPTO", "INVO_AMT","SHIP_ADD","SHIP_STATE"};
		    String filename = "Vendor_Invoice.xlsx";
		    
		    
		  //  CreationHelper createHelper = workbook1.getCreationHelper();
		 // Create a Font for styling header cells
	        Font headerFont = workbook1.createFont();
	        headerFont.setBold(true);
	     
	      
		    // Create a CellStyle with the font
	        CellStyle headerCellStyle = workbook1.createCellStyle();
		    headerCellStyle.setFont(headerFont);

	        // Create a Row
	        Row headerRow = sheet.createRow(0);

	        // Create cells
	        for(int i = 0; i < columns.length; i++) {
	            Cell cell = headerRow.createCell(i);
	            cell.setCellValue(columns[i]);
	            
	            cell.setCellStyle(headerCellStyle);
	        }
	        
			int rownum = 1;
			 int rowIndex = 1;
		        for(InvoiceVendor entity : listEntity1){
		            Row row = sheet.createRow(rowIndex++);
		            int cellIndex = 0;
		            
		            row.createCell(cellIndex++).setCellValue(entity.getFinYear());
		 
		            row.createCell(cellIndex++).setCellValue(entity.getInvNo());
		            row.createCell(cellIndex++).setCellValue(entity.getInvDt());
		   		 
		            row.createCell(cellIndex++).setCellValue(entity.getCusName());
		            row.createCell(cellIndex++).setCellValue(entity.getPrnSrn());
		   		 
		            row.createCell(cellIndex++).setCellValue(entity.getProduct());
		            row.createCell(cellIndex++).setCellValue(entity.getInvoiceFrom());
		   		 
		            row.createCell(cellIndex++).setCellValue(entity.getInvoiceUpTo());
		            row.createCell(cellIndex++).setCellValue(entity.getInvoiceAmt());
		   		 
		            row.createCell(cellIndex++).setCellValue(entity.getShipAdd());
		            row.createCell(cellIndex++).setCellValue(entity.getShipState());
		        }
			
			try {
				// this Writes the workbook KioskC  reportPath1
				logger.info("Error file path: "+reportPath1+filename);
				FileOutputStream out = new FileOutputStream(
						new File(reportPath1+filename));
				workbook1.write(out);
				out.close();
			} catch (Exception e) {
				logger.error("Exception "+ExceptionConstants.EXCEPTION);
				e.printStackTrace();
			}

		}

}
