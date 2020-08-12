package sbi.kiosk.swayam.kioskmanagement.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
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
import sbi.kiosk.swayam.common.dto.KioskCMFDto;
import sbi.kiosk.swayam.common.dto.KioskDto;
import sbi.kiosk.swayam.common.entity.BranchMaster;
import sbi.kiosk.swayam.common.entity.HolidayCalendar;
import sbi.kiosk.swayam.common.entity.KioskBranchMaster;
import sbi.kiosk.swayam.common.entity.UserKioskMapping;//
import sbi.kiosk.swayam.kioskmanagement.repository.BranchMasterRepository;
import sbi.kiosk.swayam.kioskmanagement.repository.HolidayCalendarRepository;
import sbi.kiosk.swayam.kioskmanagement.repository.KioskCMFRepository;
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
	
	
	@Value("${report.path}")		
	private String reportPath1;

	

	public FileInputStream inputStream;
	public Workbook workbook;

	public static ResourceBundle rb;

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
			// new File("C:\\Users\\Admin\\Downloads\\Swayam_Kiosk_Information.xlsx"));

			// -------By Ankur END---------------------------

			workbook = new XSSFWorkbook(inputStream);

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
					switch (cell.getCellType()) {

					case STRING:

						if (!(String.valueOf(cell.getRow().getRowNum()).equals("0"))) {
							/*if (String.valueOf(cell.getColumnIndex()).equals("1")) {

								dto.setSrNo(cell.getStringCellValue());

							}*/
							if (String.valueOf(cell.getColumnIndex()).equals("0")) {

								dto.setKioskID(cell.getStringCellValue());

							}
							if (String.valueOf(cell.getColumnIndex()).equals("1")) {

								dto.setVendor(cell.getStringCellValue());

							}
							if (String.valueOf(cell.getColumnIndex()).equals("2")) {
								String cellValueStr = objDefaultFormat.formatCellValue(cell, objFormulaEvaluator);	
								dto.setInstallationDate(cellValueStr);

							}

							if (String.valueOf(cell.getColumnIndex()).equals("3")) {

								dto.setKioskIPAddress(cell.getStringCellValue());

							}
							if (String.valueOf(cell.getColumnIndex()).equals("4")) {

								dto.setKioskMacAddress(cell.getStringCellValue());

							}
							if (String.valueOf(cell.getColumnIndex()).equals("5")) {

								dto.setSiteType(cell.getStringCellValue());

							}
							if (String.valueOf(cell.getColumnIndex()).equals("6")) {

								dto.setLocation(cell.getStringCellValue());

							}

							if (String.valueOf(cell.getColumnIndex()).equals("7")) {

								dto.setAddress(cell.getStringCellValue());

							}
							if (String.valueOf(cell.getColumnIndex()).equals("8")) {

								dto.setBranchCode(cell.getStringCellValue());

							}
							if (String.valueOf(cell.getColumnIndex()).equals("9")) {

								dto.setKioskSerialNumber(cell.getStringCellValue());

							}

							if (String.valueOf(cell.getColumnIndex()).equals("10")) {

								dto.setCircle(cell.getStringCellValue());

							}

							if (String.valueOf(cell.getColumnIndex()).equals("11")) {

								dto.setBranchName(cell.getStringCellValue());

							}

							if (String.valueOf(cell.getColumnIndex()).equals("12")) {

								dto.setoS(cell.getStringCellValue());
							}

							if (String.valueOf(cell.getColumnIndex()).equals("13")) {

								dto.setMake(cell.getStringCellValue());

							}

							if (String.valueOf(cell.getColumnIndex()).equals("14")) {

								dto.setInstallationStatus(cell.getStringCellValue());

							}

							/*
							 * for (HashMap.Entry<Integer, String> entry : map.entrySet()) { Integer key =
							 * entry.getKey(); Object value = entry.getValue();
							 * 
							 * 
							 * }
							 */

						}
						break;
					case BOOLEAN:
				//System.out.println(cell.getBooleanCellValue());
						break;
					case NUMERIC:
						if (!(String.valueOf(cell.getRow().getRowNum()).equals("0"))) {
							
							
							if (String.valueOf(cell.getColumnIndex()).equals("0")) {

								dto.setKioskID((String.valueOf((int)cell.getNumericCellValue())));

							}
							if (String.valueOf(cell.getColumnIndex()).equals("1")) {

								dto.setVendor((String.valueOf((int)cell.getNumericCellValue())));

							}
							if (String.valueOf(cell.getColumnIndex()).equals("2")) {
								String cellValueStr = objDefaultFormat.formatCellValue(cell, objFormulaEvaluator);	
								dto.setInstallationDate(cellValueStr);

							}

							if (String.valueOf(cell.getColumnIndex()).equals("3")) {

								dto.setKioskIPAddress((String.valueOf((int)cell.getNumericCellValue())));

							}
							if (String.valueOf(cell.getColumnIndex()).equals("4")) {

								dto.setKioskMacAddress((String.valueOf((int)cell.getNumericCellValue())));

							}
							if (String.valueOf(cell.getColumnIndex()).equals("5")) {

								dto.setSiteType((String.valueOf((int)cell.getNumericCellValue())));

							}
							if (String.valueOf(cell.getColumnIndex()).equals("6")) {

								dto.setLocation((String.valueOf((int)cell.getNumericCellValue())));

							}

							if (String.valueOf(cell.getColumnIndex()).equals("7")) {

								dto.setAddress((String.valueOf((int)cell.getNumericCellValue())));

							}
							if (String.valueOf(cell.getColumnIndex()).equals("8")) {

								dto.setBranchCode((String.valueOf((int)cell.getNumericCellValue())));

							}
							if (String.valueOf(cell.getColumnIndex()).equals("9")) {

								dto.setKioskSerialNumber((String.valueOf((int)cell.getNumericCellValue())));

							}

							if (String.valueOf(cell.getColumnIndex()).equals("10")) {

								dto.setCircle((String.valueOf((int)cell.getNumericCellValue())));

							}

							if (String.valueOf(cell.getColumnIndex()).equals("11")) {

								dto.setBranchName((String.valueOf((int)cell.getNumericCellValue())));

							}

							if (String.valueOf(cell.getColumnIndex()).equals("12")) {

								dto.setoS((String.valueOf((int)cell.getNumericCellValue())));
							}

							if (String.valueOf(cell.getColumnIndex()).equals("13")) {

								dto.setMake((String.valueOf((int)cell.getNumericCellValue())));

							}

							if (String.valueOf(cell.getColumnIndex()).equals("14")) {

								dto.setInstallationStatus((String.valueOf((int)cell.getNumericCellValue())));

							}
							
							break;
						}	

						// dto.setSrNo(String.valueOf(cell.getNumericCellValue()));

						//System.out.println(cell.getNumericCellValue());

						//break;

					// By Ankur 28-04-2020-----------STARTS---------

//					case BLANK:	
//						
//                        if (String.valueOf(cell.getColumnIndex()).equals("0")) {
//							
//							String row=String.valueOf(cell.getRow().getRowNum());
//							map.put(cell.getColumnIndex(),"S No data cannot be empty of row "+ row);
//							
//						}						
//						
//						if (String.valueOf(cell.getColumnIndex()).equals("1")) {
//							
//							String row=String.valueOf(cell.getRow().getRowNum());
//							map.put(cell.getColumnIndex(),"Circle data cannot be empty of row "+ row);
//							
//						}
//						
//						if (String.valueOf(cell.getColumnIndex()).equals("2")) {
//							
//							String row=String.valueOf(cell.getRow().getRowNum());
//							map.put(cell.getColumnIndex(),"Branch Name data cannot be empty of row "+ row);
//							
//						}
//						
//                        if (String.valueOf(cell.getColumnIndex()).equals("3")) {
//							
//							String row=String.valueOf(cell.getRow().getRowNum());
//							map.put(cell.getColumnIndex(),"Branch Code data cannot be empty of row "+ row);
//							
//						}
//                        
//                        if (String.valueOf(cell.getColumnIndex()).equals("4")) {
//							
//							String row=String.valueOf(cell.getRow().getRowNum());
//							map.put(cell.getColumnIndex(),"Kiosk ID data cannot be empty of row "+ row);
//							
//						}
//                        
//                        if (String.valueOf(cell.getColumnIndex()).equals("5")) {
//							
//							String row=String.valueOf(cell.getRow().getRowNum());
//							map.put(cell.getColumnIndex(),"Kiosk Serial Number data cannot be empty of row "+ row);
//							
//						}
//
//                        if (String.valueOf(cell.getColumnIndex()).equals("6")) {
//	
//	                        String row=String.valueOf(cell.getRow().getRowNum());
//	                        map.put(cell.getColumnIndex(),"Kiosk IP Address data cannot be empty of row "+ row);
//	
//                        }
//
//                        if (String.valueOf(cell.getColumnIndex()).equals("7")) {
//	
//	                        String row=String.valueOf(cell.getRow().getRowNum());
//	                        map.put(cell.getColumnIndex(),"OS Name data cannot be empty of row "+ row);
//	
//                        }
//
//                        if (String.valueOf(cell.getColumnIndex()).equals("8")) {
//	
//	                        String row=String.valueOf(cell.getRow().getRowNum());
//	                        map.put(cell.getColumnIndex(),"Make data cannot be empty of row "+ row);
//	
//                        }
//						
//                        
//                        break;

					// -------By Ankur END---------------------------

					} // switch close
					logger.info(" - ");
				} // 1st close while loop
				lidtDto.add(dto);
			} // 2nd close while loop

			/*
			 * for (HashMap.Entry<Integer, String> entry : map.entrySet()) { Integer key =
			 * entry.getKey(); Object value = entry.getValue();
			 * 
			 * 
			 * }
			 */

			KioskBranchMaster entity = null;
			List<KioskBranchMaster> listEntity = new ArrayList<KioskBranchMaster>();
			List<KioskBranchMaster> listEntity1 = new ArrayList<KioskBranchMaster>();
			int count = 0;

			for (KioskDto lidtDto1 : lidtDto) {
				if (count != 0) {
					entity = new KioskBranchMaster();
					// entity.setSrNo(Long.parseLong(lidtDto1.getSrNo().substring(0,
					// lidtDto1.getSrNo().length() - 2)));//1
					//entity.setSrNo(entity.getSrNo());
					entity.setCircle(lidtDto1.getCircle());// 2
					entity.setBranchName(lidtDto1.getBranchName());// 3
					entity.setBranchCode(lidtDto1.getBranchCode());// 4
					entity.setKioskId(lidtDto1.getKioskID());// 5
					entity.setKioskSerialNo(lidtDto1.getKioskSerialNumber());// 6
					entity.setKioskIp(lidtDto1.getKioskIPAddress());// 7
					entity.setOs(lidtDto1.getoS());// 8
					entity.setMake(lidtDto1.getMake());// 9
					entity.setVendor(lidtDto1.getVendor());// 10
					entity.setInstallationDate(lidtDto1.getInstallationDate());
					entity.setKioskMacAddress(lidtDto1.getKioskMacAddress());
					entity.setSiteType(lidtDto1.getSiteType());
					entity.setLocation(lidtDto1.getLocation());
					entity.setAddress(lidtDto1.getAddress());
					entity.setInstallationStatus(lidtDto1.getInstallationStatus());
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
					entity.setSrNo(entity.getSrNo());
					entity.setCircle(lidtDto1.getCircle());// 2
					entity.setBranchName(lidtDto1.getBranchName());// 3
					entity.setBranchCode(lidtDto1.getBranchCode());// 4
					entity.setKioskId(lidtDto1.getKioskID());// 5
					entity.setKioskSerialNo(lidtDto1.getKioskSerialNumber());// 6
					entity.setKioskIp(lidtDto1.getKioskIPAddress());// 7
					entity.setOs(lidtDto1.getoS());// 8
					entity.setMake(lidtDto1.getMake());// 9
					entity.setVendor(lidtDto1.getVendor());// 10
					entity.setInstallationDate(lidtDto1.getInstallationDate());
					entity.setKioskMacAddress(lidtDto1.getKioskMacAddress());
					entity.setSiteType(lidtDto1.getSiteType());
					entity.setLocation(lidtDto1.getLocation());
					entity.setAddress(lidtDto1.getAddress());
					entity.setInstallationStatus(lidtDto1.getInstallationStatus());
					listEntity1.add(entity);
					//KioskBranchMasterxlsx(listEntity1);

				}

			}
			if(listEntity1 !=null && listEntity1.size() > 0)
			{	
				KioskBranchMasterxlsx(listEntity1);
				return "Data Not Uploaded";
			}
			else {
				Iterable<KioskBranchMaster> result = kioskMasterManagementRepository.saveAll(listEntity);
				if (result != null)
					//return "Kiosk Details Uploaded Successfully";
					return "Kiosk_Branch_Master";			
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
				            "BranchCode","KioskSerialNo","Circle","BRANCH_NAME","Os","Make","InstallationStatus"};

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
	            row.createCell(cellIndex++).setCellValue(entity.getMake());
	            row.createCell(cellIndex++).setCellValue(entity.getInstallationStatus());
	
	        }
		
		try {
			// this Writes the workbook KioskC
			FileOutputStream out = new FileOutputStream(new File(reportPath1+filename));
			workbook1.write(out);
			out.close();
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
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
			workbook = new XSSFWorkbook(inputStream);

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
					switch (cell.getCellType()) {

					case STRING:

						if (!(String.valueOf(cell.getRow().getRowNum()).equals("0"))) {

							if (String.valueOf(cell.getColumnIndex()).equals("0")) {
								dto.setBranchCode(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("1")) {
								dto.setBranchName(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("2")) {
								dto.setcRCLCode((String.valueOf(cell.getStringCellValue())));
							}
							if (String.valueOf(cell.getColumnIndex()).equals("3")) {
								dto.setcRCLName((String.valueOf(cell.getStringCellValue())));
							}
							if (String.valueOf(cell.getColumnIndex()).equals("4")) {
								dto.setNetwork((String.valueOf(cell.getStringCellValue())));
							}
							if (String.valueOf(cell.getColumnIndex()).equals("5")) {
								dto.setModCode((String.valueOf(cell.getStringCellValue())));
							}

							if (String.valueOf(cell.getColumnIndex()).equals("6")) {
								dto.setModule(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("7")) {
								dto.setRegion(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("8")) {
								dto.setPopGroup(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("9")) {
								dto.setPopDesc(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("10")) {
								dto.setOpenCloseStatu(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("11")) {
								dto.setOpendt(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("12")) {
								dto.setStatCode(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("13")) {
								dto.setStateDesc(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("14")) {
								dto.setDistCode(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("15")) {
								dto.setDistDesc(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("16")) {
								dto.setAddress1(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("17")) {
								dto.setAddress2(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("18")) {
								dto.setAddress3(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("19")) {
								dto.setAddress4(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("20")) {
								dto.setPinCode(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("21")) {
								dto.setStdCode(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("22")) {
								dto.setPhone(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("23")) {
								dto.setMicrCode(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("24")) {
								dto.setIfsc(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("25")) {
								dto.setEmail(cell.getStringCellValue());
							}

							if (String.valueOf(cell.getColumnIndex()).equals("26")) {
								dto.setBranchMgrName(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("27")) {
								dto.setBranchMgrMobileNo(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("28")) {
								dto.setBusinessHrs(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("29")) {
								dto.setOfficeType(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("30")) {
								dto.setOfficeDesc(cell.getStringCellValue());
							}
							/*
							 * if (String.valueOf(cell.getColumnIndex()).equals("31")) {
							 * dto.setModCode(cell.getStringCellValue()); }
							 */

							break;

						}

					case NUMERIC:
						if (!(String.valueOf(cell.getRow().getRowNum()).equals("0"))) {
							
							  if (String.valueOf(cell.getColumnIndex()).equals("0")) {
							  dto.setBranchCode((String.valueOf((int)cell.getNumericCellValue()))); }
							  
								if (String.valueOf(cell.getColumnIndex()).equals("2")) {
									dto.setcRCLCode((String.valueOf((int)cell.getNumericCellValue())));
								}
								
								if (String.valueOf(cell.getColumnIndex()).equals("4")) {
									dto.setNetwork((String.valueOf((int)cell.getNumericCellValue())));
								}
								if (String.valueOf(cell.getColumnIndex()).equals("5")) {
									dto.setModCode((String.valueOf((int)cell.getNumericCellValue())));
								}

								if (String.valueOf(cell.getColumnIndex()).equals("6")) {
									dto.setModule((String.valueOf((int)cell.getNumericCellValue())));
								}
								if (String.valueOf(cell.getColumnIndex()).equals("7")) {
									dto.setRegion((String.valueOf((int)cell.getNumericCellValue())));
								}
								if (String.valueOf(cell.getColumnIndex()).equals("8")) {
									dto.setPopGroup((String.valueOf((int)cell.getNumericCellValue())));
								}
								if (String.valueOf(cell.getColumnIndex()).equals("9")) {
									dto.setPopDesc((String.valueOf((int)cell.getNumericCellValue())));
								}
								if (String.valueOf(cell.getColumnIndex()).equals("10")) {
									dto.setOpenCloseStatu((String.valueOf((int)cell.getNumericCellValue())));
								}
								if (String.valueOf(cell.getColumnIndex()).equals("11")) {
									dto.setOpendt((String.valueOf((int)cell.getNumericCellValue())));
								}
								if (String.valueOf(cell.getColumnIndex()).equals("12")) {
									dto.setStatCode((String.valueOf((int)cell.getNumericCellValue())));
								}
								if (String.valueOf(cell.getColumnIndex()).equals("13")) {
									dto.setStateDesc((String.valueOf((int)cell.getNumericCellValue())));
								}
								if (String.valueOf(cell.getColumnIndex()).equals("14")) {
									dto.setDistCode((String.valueOf((int)cell.getNumericCellValue())));
								}
								if (String.valueOf(cell.getColumnIndex()).equals("15")) {
									dto.setDistDesc((String.valueOf((int)cell.getNumericCellValue())));
								}
								if (String.valueOf(cell.getColumnIndex()).equals("16")) {
									dto.setAddress1((String.valueOf((int)cell.getNumericCellValue())));
								}
								if (String.valueOf(cell.getColumnIndex()).equals("17")) {
									dto.setAddress2((String.valueOf((int)cell.getNumericCellValue())));
								}
								if (String.valueOf(cell.getColumnIndex()).equals("18")) {
									dto.setAddress3((String.valueOf((int)cell.getNumericCellValue())));
								}
								if (String.valueOf(cell.getColumnIndex()).equals("19")) {
									dto.setAddress4((String.valueOf((int)cell.getNumericCellValue())));
								}
								if (String.valueOf(cell.getColumnIndex()).equals("20")) {
									dto.setPinCode((String.valueOf((int)cell.getNumericCellValue())));
								}
								if (String.valueOf(cell.getColumnIndex()).equals("21")) {
									dto.setStdCode((String.valueOf((int)cell.getNumericCellValue())));
								}
								if (String.valueOf(cell.getColumnIndex()).equals("22")) {
									dto.setPhone((String.valueOf((int)cell.getNumericCellValue())));
								}
								if (String.valueOf(cell.getColumnIndex()).equals("23")) {
									dto.setMicrCode((String.valueOf((int)cell.getNumericCellValue())));
								}
								if (String.valueOf(cell.getColumnIndex()).equals("24")) {
									dto.setIfsc((String.valueOf((int)cell.getNumericCellValue())));
								}
								if (String.valueOf(cell.getColumnIndex()).equals("25")) {
									dto.setEmail((String.valueOf((int)cell.getNumericCellValue())));
								}

								if (String.valueOf(cell.getColumnIndex()).equals("26")) {
									dto.setBranchMgrName((String.valueOf((int)cell.getNumericCellValue())));
								}
								if (String.valueOf(cell.getColumnIndex()).equals("27")) {
									dto.setBranchMgrMobileNo((String.valueOf((int)cell.getNumericCellValue())));
								}
								if (String.valueOf(cell.getColumnIndex()).equals("28")) {
									dto.setBusinessHrs((String.valueOf((int)cell.getNumericCellValue())));
								}
								if (String.valueOf(cell.getColumnIndex()).equals("29")) {
									dto.setOfficeType((String.valueOf((int)cell.getNumericCellValue())));
								}
								if (String.valueOf(cell.getColumnIndex()).equals("30")) {
									dto.setOfficeDesc((String.valueOf((int)cell.getNumericCellValue())));
								}

							
							break;
						}				

					}// switch close
					logger.info(" - ");
				} // 1st close while loop
				if((dto.getBranchCode() ==null || dto.getBranchCode().trim().equals(""))
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
				else {
					lidtDto.add(dto);
				}
			} // 2nd close while loop

//			 XSSFWorkbook workbook = new XSSFWorkbook();
//		        XSSFSheet sheet = workbook.createSheet("BrhmMasterErrorList");
//		       Array errorList[][];
//		        for(int i=0;i < list.size();i++ ) {
//		        	for(int index=0; index<=1;index++) {
//		        		Array a = errorList[i][list.get(index)];	
//		        	}
//		        } 
//		        
//		        Object[][] bookData =errorList;
//		 
//		        int rowCount = 0;
//		         
//		        for (Object[] aBook : bookData) {
//		            Row row = sheet.createRow(++rowCount);
//		             
//		            int columnCount = 0;
//		             
//		            for (Object field : aBook) {
//		                Cell cell = row.createCell(++columnCount);
//		                if (field instanceof String) {
//		                    cell.setCellValue((String) field);
//		                } else if (field instanceof Integer) {
//		                    cell.setCellValue((Integer) field);
//		                }
//		            }
//		             
//		        }
//		         
//		         
//		        try (FileOutputStream outputStream = new FileOutputStream("JavaBooks.xlsx")) {
//		            workbook.write(outputStream);
//		        }
//		    }

//			Workbook workbook = new XSSFWorkbook();
//		    Sheet sheet = workbook.createSheet();
//		 
//		    int rowCount = 0;
//		    
//		    for (HashMap.Entry<String, String> entry : map.entrySet()) { 
//		    	String key = entry.getKey(); 
//		    	Object value = entry.getValue();
//			}
//		 
////		    for (String aBook : errorList) {
////		    	
////		        Row row = sheet.createRow(++rowCount);
////		        
////		        Cell cell = row.createCell(0);
////			    cell.setCellValue(aBook);
////		        
////	//	        writeBook(aBook, row);
////		    }
//		 
//		    try (FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Ankur\\Desktop\\CBS_Error_List.xlsx")) {
//		        workbook.write(outputStream);
//		    }

			BranchMaster entity = null;
			List<BranchMaster> listEntity = new ArrayList<BranchMaster>();
			List<BranchMaster> listEntity1 = new ArrayList<BranchMaster>();
			int count = 0;

			for (CbsBrhmDto listDto1 : lidtDto) {
				//if (count != 0) {
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
					entity.setCircle(listDto1.getcRCLCode());
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
					 
				//}
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
					entity.setCircle(listDto1.getcRCLCode());
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
				return "Data Not Uploaded";
			}
			else {
				Iterable<BranchMaster> result =	branchMasterRepository.saveAll(listEntity);
				if(result != null) {
			//	return "Branch Master Data Uploaded Successfully";
				return "BranchMaster";
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
			FileOutputStream out = new FileOutputStream(
					new File(reportPath1+filename));
			workbook1.write(out);
			out.close();
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
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

			// -------By Ankur END---------------------------

			workbook = new XSSFWorkbook(inputStream);
			// HashMap<Integer,String> map=null;  
			org.apache.poi.ss.usermodel.Sheet firstSheet = workbook.getSheetAt(0);

			DataFormatter objDefaultFormat = new DataFormatter();
			FormulaEvaluator objFormulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);

			Iterator<Row> iterator = firstSheet.iterator();
			List<HolidayCalendarDto> lidtDto = new ArrayList<>();

			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				HolidayCalendarDto dto = new HolidayCalendarDto();

				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();
					objFormulaEvaluator.evaluate(cell);

					/*
					 * switch (cell.getCellType()) { case STRING:
					 */

					if (!(String.valueOf(cell.getRow().getRowNum()).equals("0"))) {

						if (String.valueOf(cell.getColumnIndex()).equals("0")) {
							String cellValueStr = objDefaultFormat.formatCellValue(cell, objFormulaEvaluator);
							dto.setHolidayDate(cellValueStr);
						}

						if (String.valueOf(cell.getColumnIndex()).equals("1")) {
							dto.setDay(cell.getStringCellValue());
						}

						if (String.valueOf(cell.getColumnIndex()).equals("2")) {
							dto.setName(cell.getStringCellValue());
						}

						if (String.valueOf(cell.getColumnIndex()).equals("3")) {
							dto.setCircle(cell.getStringCellValue());
						}

						if (String.valueOf(cell.getColumnIndex()).equals("4")) {
							dto.setState(cell.getStringCellValue());
						}

					}
					
					logger.info(" - ");

				} // 1st close while loop
				lidtDto.add(dto);
			} // 2nd close while loop

			HolidayCalendar entity = null;
			List<HolidayCalendar> listEntity = new ArrayList<HolidayCalendar>();
			List<HolidayCalendar> listEntity1 = new ArrayList<HolidayCalendar>();
			int count = 0;

			for (HolidayCalendarDto lidtDto1 : lidtDto) {
				if (count != 0) {
					entity = new HolidayCalendar();
					entity.setHolidayDate(lidtDto1.getHolidayDate());
					entity.setDay(lidtDto1.getDay());
					entity.setName(lidtDto1.getName());// null
					entity.setCircle(lidtDto1.getCircle());
					entity.setState(lidtDto1.getState());
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
					listEntity1.add(entity);
					//HolidayCalendarxlsx(listEntity1);

				}

			}
			if(listEntity1 !=null && listEntity1.size() > 0)
			{   
				HolidayCalendarxlsx(listEntity1);
				return "Data Not Uploaded";
			}
			else {
				Iterable<HolidayCalendar> result = holidayCalendarRepository.saveAll(listEntity);
				if (result != null)
				//	return "Holiday Calendar Data Uploaded Successfully";
					return "Holiday_Calendar";
			}
		}

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
			}
		}
		return "Data Not Uploaded";
	}

	// ---xlsx HolidayCalendarxlsx
	public void HolidayCalendarxlsx(List<HolidayCalendar> listEntity1) {

		XSSFWorkbook workbook1 = new XSSFWorkbook();
        String filename ="Holiday_Calendar.xlsx" ;
		XSSFSheet sheet = workbook1.createSheet("HolidayCalendar");
		String[] columns = {"Date", "Day","Name","Circle","State"};
		
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
	        }
		
		try {
			// this Writes the workbook KioskC
			FileOutputStream out = new FileOutputStream(
					new File(reportPath1+filename));
			workbook1.write(out);
			out.close();
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}

	}

// -----------------2 Kiosk CMF	

	@Override
	public String uploadKioskCMFInformation(String path) {


		try {

			inputStream = new FileInputStream(new File(path));
			workbook = new XSSFWorkbook(inputStream);
			/* HashMap<Integer,String> map=null; */
			org.apache.poi.ss.usermodel.Sheet firstSheet = workbook.getSheetAt(0);

			/*
			 * DataFormatter objDefaultFormat = new DataFormatter(); FormulaEvaluator
			 * objFormulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);
			 */

			Iterator<Row> iterator = firstSheet.iterator();
			List<KioskCMFDto> lidtDto = new ArrayList<>();

			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				KioskCMFDto dto = new KioskCMFDto();

				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();
					/* objFormulaEvaluator.evaluate(cell); */

					switch (cell.getCellType()) {
					case STRING:
						if (!(String.valueOf(cell.getRow().getRowNum()).equals("0"))) {
						

							if (String.valueOf(cell.getColumnIndex()).equals("0")) {
								dto.setCmfPfId(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("1")) {
							
								dto.setKioskId(cell.getStringCellValue());
							}

						}
						break;
					case NUMERIC:
						if (!(String.valueOf(cell.getRow().getRowNum()).equals("0"))) {
							if (String.valueOf(cell.getColumnIndex()).equals("0")) {
							
								dto.setCmfPfId((String.valueOf((int)cell.getNumericCellValue())));
							}
							if (String.valueOf(cell.getColumnIndex()).equals("1")) {
							
								dto.setKioskId((String.valueOf((int)cell.getNumericCellValue())));
							}

							break;
						}

					}
					logger.info(" - ");

				} // 1st close while loop
				lidtDto.add(dto);
			} // 2nd close while loop

			UserKioskMapping entity = null;
			List<UserKioskMapping> listEntity = new ArrayList<UserKioskMapping>();
			List<UserKioskMapping> listEntity1 = new ArrayList<UserKioskMapping>();
			int count = 0;

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
						&& (!checkNullCmfPfId.isPresent() || checkNullCmfPfId.get().trim().equals(""))) {
					
				}else
				if (!checkNullgetKioskId.isPresent() || !checkNullCmfPfId.isPresent()
						|| checkNullgetKioskId.get().trim().equals("") || checkNullCmfPfId.get().trim().equals("")) {
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
				return "Data Not Uploaded";
			}
			else {
				Iterable<UserKioskMapping> result = kioskCMFRepository.saveAll(listEntity);
				if (result != null)
				//	return "Kiosk_CMF Data Uploaded Successfully";
					return "Kiosk_CMF";
			}
			
			
		}

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
			}
		}
		return "Data Not Uploaded";
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
			FileOutputStream out = new FileOutputStream(
					new File(reportPath1+filename));
			workbook1.write(out);
			out.close();
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}

	}

	

}
