package sbi.kiosk.swayam.kioskmanagement.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Autowired
	private kioskMasterManagementRepository kioskMasterManagementRepository;
	@Autowired
	private BranchMasterRepository branchMasterRepository;
	
	// By Pankul 28-04-2020-----------STARTS---------
	
	@Autowired
	private HolidayCalendarRepository holidayCalendarRepository;
	
	@Autowired
	private KioskCMFRepository kioskCMFRepository;
	
	//-------By Pankul END---------------------------

	public FileInputStream inputStream;
	public Workbook workbook;
	
	public static ResourceBundle rb;

	@Override
	public String uploadKioskInformation(String path) {
		System.out.println("inside upload kiosk information service...");
		// upload kiosk file information
		try {
			
			// By Pankul 28-04-2020-----------STARTS---------
			
			/*
			 * rb = ResourceBundle.getBundle("rb"+rb);
			 * System.out.println("while reading excel file..."); String kioskFilepath =
			 * rb.getString("kioskFilepath");
			 */

			System.out.println("while reading excel file...");
			inputStream = new FileInputStream(path);
		//			new File("C:\\Users\\Admin\\Downloads\\Swayam_Kiosk_Information.xlsx"));
			
			//-------By Pankul END---------------------------
			
			workbook = new XSSFWorkbook(inputStream);
			
			// By Pankul 28-04-2020-----------STARTS---------
			
			/* HashMap<Integer,String> map = map=new HashMap<Integer,String>(); */
			
			//-------By Pankul END---------------------------
			
			org.apache.poi.ss.usermodel.Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();
			List<KioskDto> lidtDto = new ArrayList<>();
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();	 
				 Iterator<Cell> cellIterator = nextRow.cellIterator();
				KioskDto dto = new KioskDto();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					
					case STRING:

						if (!(String.valueOf(cell.getRow().getRowNum()).equals("0"))) { 
							System.out.print(cell.getColumnIndex());
							System.out.print(cell.getRow().getRowNum());							 
							
							if (String.valueOf(cell.getColumnIndex()).equals("1")) {
								   
								dto.setCircle(cell.getStringCellValue());
								
							}
							
							if (String.valueOf(cell.getColumnIndex()).equals("2")) {
									
								dto.setBranchName(cell.getStringCellValue());							
								
							}
							
							if (String.valueOf(cell.getColumnIndex()).equals("3")) {
									
								dto.setBranchCode(cell.getStringCellValue());
									
							}
							
							if (String.valueOf(cell.getColumnIndex()).equals("4")) {
									
								dto.setKioskID(cell.getStringCellValue());
										
							}
							
							if (String.valueOf(cell.getColumnIndex()).equals("5")) {
									
								dto.setKioskSerialNumber(cell.getStringCellValue());
										
							}
							
							if (String.valueOf(cell.getColumnIndex()).equals("6")) {
									
								dto.setKioskIPAddress(cell.getStringCellValue());
									
							}
							
							if (String.valueOf(cell.getColumnIndex()).equals("7")) {
									
								dto.setoS(cell.getStringCellValue());					
									
							}
							
							if (String.valueOf(cell.getColumnIndex()).equals("8")) {
									
								dto.setMake(cell.getStringCellValue());		
								
							}
							
							
							
							/*
							 * for (HashMap.Entry<Integer, String> entry : map.entrySet()) { Integer key =
							 * entry.getKey(); Object value = entry.getValue();
							 * 
							 * System.out.println("Key : " + key); System.out.println("Value : " + value);
							 * 
							 * }
							 */
							
						}
						break;
					case BOOLEAN:
						System.out.print(cell.getBooleanCellValue());
						break;
					case NUMERIC:
							
						dto.setSrNo(String.valueOf(cell.getNumericCellValue()));
						
						System.out.print(cell.getNumericCellValue());

						break;
						
					// By Pankul 28-04-2020-----------STARTS---------	
					
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
                        
                 //-------By Pankul END---------------------------
                        
					} // switch close
					System.out.print(" - ");
				//	System.out.println(map);
				} // 1st close while loop
				System.out.println();
				lidtDto.add(dto);
			} // 2nd close while loop
			
			
			/*
			 * for (HashMap.Entry<Integer, String> entry : map.entrySet()) { Integer key =
			 * entry.getKey(); Object value = entry.getValue();
			 * 
			 * System.out.println("Key : " + key); System.out.println("Value : " + value);
			 * 
			 * }
			 */
			


			KioskBranchMaster entity = null;
			List<KioskBranchMaster> listEntity = new ArrayList<KioskBranchMaster>();
			int count = 0;

			for (KioskDto lidtDto1 : lidtDto) {
				if (count != 0) {
					entity = new KioskBranchMaster();
					entity.setSrNo(Long.parseLong(lidtDto1.getSrNo().substring(0, lidtDto1.getSrNo().length() - 2)));
					System.out.println(entity.getSrNo());
					entity.setCircle(lidtDto1.getCircle());
					entity.setBranchName(lidtDto1.getBranchName());
					entity.setBranchCode(lidtDto1.getBranchCode());
					entity.setKioskId(lidtDto1.getKioskID());
					entity.setKioskSerialNo(lidtDto1.getKioskSerialNumber());
					entity.setKioskIp(lidtDto1.getKioskIPAddress());
					entity.setOs(lidtDto1.getoS());
					entity.setMake(lidtDto1.getMake());
					listEntity.add(entity);
				}
				count++;
			}
			Iterable<KioskBranchMaster> result = kioskMasterManagementRepository.saveAll(listEntity);
			if (result != null)
				return "Kiosk CMF Uploaded Successfully";
		} catch (Exception e) {
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
				e.printStackTrace();
			}
		}
		return "Data Not Uploaded";

	}

	@Override
	public String uploadCBSbrhmInformation(String path) {

		System.out.println("inside upload kiosk information service...");
		// upload kiosk file information
		try {
			
			// By Pankul 28-04-2020-----------STARTS---------
			
			
			
			//System.out.println("name"+path);//{"myfile":"C:\\Users\\ankur.verma\\Desktop\\xml_file\\CBS_brhm.xlsx"}
			       
		//	rb = ResourceBundle.getBundle("stream");
		
		//	String CBSBrhmFilepath = rb.getString(path);
			
			System.out.println("while reading excel file...");
			inputStream = new FileInputStream(new File(path));
			workbook = new XSSFWorkbook(inputStream);
			Map<String, String> map = new HashMap();
			
		//	List<String> errorList = new ArrayList<String>();
						
			//-------By Pankul END---------------------------
			
			org.apache.poi.ss.usermodel.Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();
			List<CbsBrhmDto> lidtDto = new ArrayList<CbsBrhmDto>();
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();			
				
				// By Pankul 28-04-2020-----------STARTS---------
				
				// map=new HashMap<Integer,String>(); 
				 
				// errorList = new ArrayList<String>();
				
				//-------By Pankul END---------------------------
				
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				CbsBrhmDto dto = new CbsBrhmDto();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {

					case STRING:
						
						if (!(String.valueOf(cell.getRow().getRowNum()).equals("0"))) {

							if (String.valueOf(cell.getColumnIndex()).equals("1")) {
								dto.setBranchName(cell.getStringCellValue());
								System.out.print(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("3")) {
								dto.setcRCLName((String.valueOf(cell.getStringCellValue())));
								System.out.print(cell.getStringCellValue());
							}

							if (String.valueOf(cell.getColumnIndex()).equals("6")) {
								dto.setModule(cell.getStringCellValue());
								System.out.print(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("9")) {
								dto.setPopDesc(cell.getStringCellValue());
								System.out.print(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("10")) {
								dto.setOpenCloseStatu(cell.getStringCellValue());
								System.out.print(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("13")) {
								dto.setStateDesc(cell.getStringCellValue());
								System.out.print(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("15")) {
								dto.setDistDesc(cell.getStringCellValue());
								System.out.print(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("16")) {
								dto.setAddress1(cell.getStringCellValue());
								System.out.print(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("17")) {
								dto.setAddress2(cell.getStringCellValue());
								System.out.print(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("18")) {
								dto.setAddress3(cell.getStringCellValue());
								System.out.print(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("24")) {
								dto.setIfsc(cell.getStringCellValue());
								System.out.print(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("25")) {
								dto.setEmail(cell.getStringCellValue());
								System.out.print(cell.getStringCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("26")) {
								dto.setBranchMgrName(cell.getStringCellValue());
								System.out.print(cell.getStringCellValue());
							}
							
							if (String.valueOf(cell.getColumnIndex()).equals("30")) {
								dto.setOfficeDesc(cell.getStringCellValue());
								System.out.print(cell.getStringCellValue());
							}
							
							break;
							
						}

					case NUMERIC:
						if (!(String.valueOf(cell.getRow().getRowNum()).equals("0"))) {
							if (String.valueOf(cell.getColumnIndex()).equals("0")) {
								System.out.print((String.valueOf(cell.getNumericCellValue())));
								dto.setBranchCode((String.valueOf(cell.getNumericCellValue())));
							}
							if (String.valueOf(cell.getColumnIndex()).equals("2")) {
								dto.setcRCLCode((String.valueOf(cell.getNumericCellValue())));
								System.out.print((String.valueOf(cell.getNumericCellValue())));
							}
							if (String.valueOf(cell.getColumnIndex()).equals("4")) {
								dto.setNetwork((String.valueOf(cell.getNumericCellValue())));
								System.out.print((String.valueOf(cell.getNumericCellValue())));
							}
							if (String.valueOf(cell.getColumnIndex()).equals("5")) {
								dto.setModCode((String.valueOf(cell.getNumericCellValue())));
								System.out.print((String.valueOf(cell.getNumericCellValue())));
							}
							if (String.valueOf(cell.getColumnIndex()).equals("7")) {
								dto.setRegion((String.valueOf(cell.getNumericCellValue())));
								System.out.print((String.valueOf(cell.getNumericCellValue())));
							}
							if (String.valueOf(cell.getColumnIndex()).equals("8")) {
								dto.setPopGroup((String.valueOf(cell.getNumericCellValue())));
								System.out.print((String.valueOf(cell.getNumericCellValue())));
							}
							if (String.valueOf(cell.getColumnIndex()).equals("11")) {
								dto.setOpendt((String.valueOf(cell.getNumericCellValue())));
								System.out.print((String.valueOf(cell.getNumericCellValue())));
							}
							if (String.valueOf(cell.getColumnIndex()).equals("12")) {
								dto.setStatCode((String.valueOf(cell.getNumericCellValue())));
								System.out.print((String.valueOf(cell.getNumericCellValue())));
							}

							if (String.valueOf(cell.getColumnIndex()).equals("14")) {
								dto.setDistCode((String.valueOf(cell.getNumericCellValue())));
								System.out.print((String.valueOf(cell.getNumericCellValue())));
							}
							if (String.valueOf(cell.getColumnIndex()).equals("20")) {
								dto.setPinCode((String.valueOf(cell.getNumericCellValue())));
								System.out.print((String.valueOf(cell.getNumericCellValue())));
							}
							if (String.valueOf(cell.getColumnIndex()).equals("21")) {
								dto.setStdCode((String.valueOf(cell.getNumericCellValue())));
								System.out.print((String.valueOf(cell.getNumericCellValue())));
							}

							if (String.valueOf(cell.getColumnIndex()).equals("22")) {
								dto.setPhone((String.valueOf(cell.getNumericCellValue())));
								System.out.print((String.valueOf(cell.getNumericCellValue())));
							}
							if (String.valueOf(cell.getColumnIndex()).equals("23")) {
								dto.setMicrCode((String.valueOf(cell.getNumericCellValue())));
								System.out.print((String.valueOf(cell.getNumericCellValue())));
							}
							if (String.valueOf(cell.getColumnIndex()).equals("27")) {
								dto.setBranchMgrMobileNo((String.valueOf(cell.getNumericCellValue())));
								System.out.print((String.valueOf(cell.getNumericCellValue())));
							}
							if (String.valueOf(cell.getColumnIndex()).equals("28")) {
								dto.setBusinessHrs((String.valueOf(cell.getNumericCellValue())));
								System.out.print((String.valueOf(cell.getNumericCellValue())));
							}
							if (String.valueOf(cell.getColumnIndex()).equals("29")) {
								dto.setOfficeType((String.valueOf(cell.getNumericCellValue())));
								System.out.print((String.valueOf(cell.getNumericCellValue())));
							}

							break;
						}

//					case BLANK:	
//						
//					  if (!(String.valueOf(cell.getRow().getRowNum()).equals("0"))) {
//						
//						if (String.valueOf(cell.getColumnIndex()).equals("1")) {
//							
//	                        String row=String.valueOf(cell.getRow().getRowNum());
//	                        map.put(dto.getBranchCode(),"Branch Name is empty");
//	                //        errorList.add("Branch Name data cannot be empty of row "+ row + "and column " + cell.getColumnIndex());
//	
//                        }
//
//                        if (String.valueOf(cell.getColumnIndex()).equals("2")) {
//	
//	                        String row=String.valueOf(cell.getRow().getRowNum());
//	                        map.put(dto.getBranchCode(),"Crcl Code data cannot be empty of row "+ row);
//	                //        errorList.add("Crcl Code data cannot be empty of row "+ row + "and column " + cell.getColumnIndex());
//	
//                        }
//						
//						if (String.valueOf(cell.getColumnIndex()).equals("3")) {
//							
//							String row=String.valueOf(cell.getRow().getRowNum());
//							map.put(dto.getBranchCode(),"Crcl Name data cannot be empty of row "+ row);
//					//		errorList.add("Crcl Name data cannot be empty of row "+ row + "and column " + cell.getColumnIndex());
//						}
//						
//						if (String.valueOf(cell.getColumnIndex()).equals("4")) {
//							
//							String row=String.valueOf(cell.getRow().getRowNum());
//							map.put(dto.getBranchCode(),"Network data cannot be empty of row "+ row);
//					//		errorList.add("Network data cannot be empty of row "+ row + "and column " + cell.getColumnIndex());
//						}
//						
//                        if (String.valueOf(cell.getColumnIndex()).equals("5")) {
//							
//							String row=String.valueOf(cell.getRow().getRowNum());
//							map.put(dto.getBranchCode(),"Mod Code data cannot be empty of row "+ row);
//					//		errorList.add("Mod Code data cannot be empty of row "+ row + "and column " + cell.getColumnIndex());
//						}
//                        
//                        if (String.valueOf(cell.getColumnIndex()).equals("6")) {
//							
//							String row=String.valueOf(cell.getRow().getRowNum());
//							map.put(dto.getBranchCode(),"Module data cannot be empty of row "+ row);
//					//		errorList.add("Module data cannot be empty of row "+ row + "and column " + cell.getColumnIndex());
//						}
//                        
//                        if (String.valueOf(cell.getColumnIndex()).equals("7")) {
//							
//							String row=String.valueOf(cell.getRow().getRowNum());
//							map.put(dto.getBranchCode(),"Region data cannot be empty of row "+ row);
//					//		errorList.add("Region data cannot be empty of row "+ row + "and column " + cell.getColumnIndex());
//						}
//                        
//                        if (String.valueOf(cell.getColumnIndex()).equals("12")) {
//                        	
//	                        String row=String.valueOf(cell.getRow().getRowNum());
//	                        map.put(dto.getBranchCode(),"Stat Code data cannot be empty of row "+ row);
//	               //       errorList.add("Stat Code data cannot be empty of row "+ row + "and column " + cell.getColumnIndex());
//                        }
//						                     
//                        break;
//                        
//				      }
					  
//					default:
//						
//					  if (!(String.valueOf(cell.getRow().getRowNum()).equals("0"))) {
//						
//                        if (String.valueOf(cell.getColumnIndex()).equals("1")) {
//							
//	                        String row=String.valueOf(cell.getRow().getRowNum());
//	                        map.put(dto.getBranchCode(),"Branch Name is empty");
//	                //        errorList.add("Branch Name data cannot be empty of row "+ row + "and column " + cell.getColumnIndex());
//	
//                        }
//
//                        if (String.valueOf(cell.getColumnIndex()).equals("2")) {
//	
//	                        String row=String.valueOf(cell.getRow().getRowNum());
//	                        map.put(dto.getBranchCode(),"Crcl Code data cannot be empty of row "+ row);
//	                //        errorList.add("Crcl Code data cannot be empty of row "+ row + "and column " + cell.getColumnIndex());
//	
//                        }
//						
//						if (String.valueOf(cell.getColumnIndex()).equals("3")) {
//							
//							String row=String.valueOf(cell.getRow().getRowNum());
//							map.put(dto.getBranchCode(),"Crcl Name data cannot be empty of row "+ row);
//					//		errorList.add("Crcl Name data cannot be empty of row "+ row + "and column " + cell.getColumnIndex());
//						}
//						
//						if (String.valueOf(cell.getColumnIndex()).equals("4")) {
//							
//							String row=String.valueOf(cell.getRow().getRowNum());
//							map.put(dto.getBranchCode(),"Network data cannot be empty of row "+ row);
//					//		errorList.add("Network data cannot be empty of row "+ row + "and column " + cell.getColumnIndex());
//						}
//						
//                        if (String.valueOf(cell.getColumnIndex()).equals("5")) {
//							
//							String row=String.valueOf(cell.getRow().getRowNum());
//							map.put(dto.getBranchCode(),"Mod Code data cannot be empty of row "+ row);
//					//		errorList.add("Mod Code data cannot be empty of row "+ row + "and column " + cell.getColumnIndex());
//						}
//                        
//                        if (String.valueOf(cell.getColumnIndex()).equals("6")) {
//							
//							String row=String.valueOf(cell.getRow().getRowNum());
//							map.put(dto.getBranchCode(),"Module data cannot be empty of row "+ row);
//					//		errorList.add("Module data cannot be empty of row "+ row + "and column " + cell.getColumnIndex());
//						}
//                        
//                        if (String.valueOf(cell.getColumnIndex()).equals("7")) {
//							
//							String row=String.valueOf(cell.getRow().getRowNum());
//							map.put(dto.getBranchCode(),"Region data cannot be empty of row "+ row);
//					//		errorList.add("Region data cannot be empty of row "+ row + "and column " + cell.getColumnIndex());
//						}
//                        
//                        if (String.valueOf(cell.getColumnIndex()).equals("12")) {
//                        	
//	                        String row=String.valueOf(cell.getRow().getRowNum());
//	                        map.put(dto.getBranchCode(),"Stat Code data cannot be empty of row "+ row);
//	               //       errorList.add("Stat Code data cannot be empty of row "+ row + "and column " + cell.getColumnIndex());
//                        }
//						                     
//                        break;
//					  
//					  }	
                        
					}// switch close
					System.out.print(" - ");
			//		System.out.println(map);
				} // 1st close while loop
				lidtDto.add(dto);
				System.out.println();
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
//			    System.out.println("Key : " + key); System.out.println("Value : " + value);
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
//		    try (FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Pankul\\Desktop\\CBS_Error_List.xlsx")) {
//		        workbook.write(outputStream);
//		    }

			BranchMaster entity = null;
			List<BranchMaster> listEntity = new ArrayList<BranchMaster>();
			int count = 0;
			
			
			for (CbsBrhmDto listDto1 : lidtDto) {
				if (count != 0) {
					entity = new BranchMaster();
					entity.setBranchCode(listDto1.getBranchCode().substring(0, listDto1.getBranchCode().length() - 2));
					entity.setBranchName(listDto1.getBranchName());
					entity.setAddress1(listDto1.getAddress1());
					entity.setAddress2(listDto1.getAddress2());
					entity.setAddress3(listDto1.getAddress3());
					entity.setAddress4(listDto1.getAddress4());
				//	entity.setCRCLCode(listDto1.getcRCLCode().substring(0, listDto1.getcRCLCode().length() - 2));
					entity.setCRCLCode(listDto1.getcRCLCode());
					entity.setBranchMgrName(listDto1.getBranchMgrName());
					entity.setBranchMgrMobileNo(listDto1.getBranchMgrMobileNo());
					entity.setCircle(listDto1.getcRCLName());
					entity.setBusinessHrs(listDto1.getBusinessHrs());
					entity.setDistCode(listDto1.getDistCode().substring(0, listDto1.getDistCode().length() - 2));
					entity.setDistDesc(listDto1.getDistDesc());
					entity.setEmail(listDto1.getEmail());
					entity.setOfficeDesc(listDto1.getOfficeDesc());
					entity.setOfficeType(listDto1.getOfficeType());
					entity.setIfsc(listDto1.getIfsc());
					entity.setMicrCode(listDto1.getMicrCode());
					entity.setModule(listDto1.getModule());
					entity.setPinCode(listDto1.getPinCode().substring(0, listDto1.getPinCode().length() - 2));
					entity.setPopDesc(listDto1.getPopDesc());
					entity.setPopGroup(listDto1.getPopGroup());
					entity.setModCode(listDto1.getModCode().substring(0, listDto1.getModCode().length() - 2));
					entity.setNetwork(listDto1.getNetwork());
					entity.setOpenCloseStatus(listDto1.getOpenCloseStatu());
					entity.setOpendt(listDto1.getOpendt());
					entity.setStdCode(listDto1.getStatCode().substring(0, listDto1.getStatCode().length() - 2));
					entity.setStatDesc(listDto1.getStateDesc());
					listEntity.add(entity);
					if(count==4) {
						break;
					}
				}
				count++;
			}
			System.out.println("before saving excel file");
			branchMasterRepository.saveAll(listEntity);
			return "Branch Master Data Uploaded Successfully";
		} // try close
		catch (Exception e) {
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
				e.printStackTrace();
			}
		}
		return "Data Not Uploaded";

	}
	
	
	// By Pankul 28-04-2020-----------STARTS---------
	
	@Override
	public String uploadHolidayCalendarInformation(String path) {
		
		System.out.println("inside upload Holiday Calendar information service...");
		// upload holiday calendar file information
		
		try {
			
			// By Pankul 28-04-2020-----------STARTS---------
			System.out.println("path"+path);
			//rb = ResourceBundle.getBundle("system");
									
			//String holidayCalendarFilePath = rb.getString("CBSBrhmFilepath");
		//	String holidayCalendarFilePath = rb.getString(path);
			System.out.println("while reading excel file...");
			inputStream = new FileInputStream(new File(path));
			
			//-------By Pankul END---------------------------
			
			workbook = new XSSFWorkbook(inputStream);
			/* HashMap<Integer,String> map=null; */
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
							System.out.print(cell.getColumnIndex());
							System.out.print(cell.getRow().getRowNum());
							
							if (String.valueOf(cell.getColumnIndex()).equals("0")) {
								String cellValueStr = objDefaultFormat.formatCellValue(cell,objFormulaEvaluator);
								dto.setCalendarDate(cellValueStr);
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
					/*	break;
						
					  case BOOLEAN:
							System.out.print(cell.getBooleanCellValue());
							break;
							
					  case NUMERIC:							
							System.out.print(cell.getNumericCellValue());
							 
							break;
					}*/
					System.out.print(" - ");
		
		            } // 1st close while loop
				      System.out.println();
				      lidtDto.add(dto);
				      System.out.println("lidtDto"+lidtDto);
	           } // 2nd close while loop
			
			
			HolidayCalendar entity = null;
			List<HolidayCalendar> listEntity = new ArrayList<HolidayCalendar>();
			int count = 0;

			for (HolidayCalendarDto lidtDto1 : lidtDto) {
				System.out.println();
				if (count != 0) {
					entity = new HolidayCalendar();
					entity.setCalendarDate(lidtDto1.getCalendarDate());
					entity.setDay(lidtDto1.getDay());
					entity.setName(lidtDto1.getName());
					entity.setCircle(lidtDto1.getCircle());
					entity.setState(lidtDto1.getState());
					listEntity.add(entity);
					  System.out.println("listEntity"+listEntity);
				}
				count++;
			}
			Iterable<HolidayCalendar> result = holidayCalendarRepository.saveAll(listEntity);
			System.out.println("result"+result);
			if (result != null)
				return "Holiday Calendar Data Uploaded Successfully";
		}
			
		catch (Exception e) {
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
				e.printStackTrace();
			}
		}
		return "Data Not Uploaded";
	}
	
	
	
	@Override
	public String uploadKioskCMFInformation(String path) {
		
		System.out.println("inside upload Kiosk CMF information service...");
		// upload holiday calendar file information
		
		try {

			// By Pankul 28-04-2020-----------STARTS---------
			
			/*
			 * rb = ResourceBundle.getBundle("system");
			 * 
			 * String kioskCMFFilePath = rb.getString("kioskCMFFilePath");
			 */

			System.out.println("while reading excel file...");
			inputStream = new FileInputStream(new File(path));
						
			//-------By Pankul END---------------------------
			
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
					
					/*
					 * switch (cell.getCellType()) { case STRING:
					 */
						
						if (!(String.valueOf(cell.getRow().getRowNum()).equals("0"))) { 
							System.out.print(cell.getColumnIndex());
							System.out.print(cell.getRow().getRowNum());
							
							if (String.valueOf(cell.getColumnIndex()).equals("0")) {
								//String cellValueStr = objDefaultFormat.formatCellValue(cell,objFormulaEvaluator);
								dto.setKioskId(cell.getStringCellValue());
							}
							
							if (String.valueOf(cell.getColumnIndex()).equals("1")) {
								dto.setCmfPfId(cell.getStringCellValue());
							}							
					}
					/*	break;
						
					  case BOOLEAN:
							System.out.print(cell.getBooleanCellValue());
							break;
							
					  case NUMERIC:							
							System.out.print(cell.getNumericCellValue());
							 
							break;
					}*/
				System.out.print(" - ");
		
		            } // 1st close while loop
				      System.out.println();
				      lidtDto.add(dto);
	           } // 2nd close while loop
			
			
			UserKioskMapping entity = null;
			List<UserKioskMapping> listEntity = new ArrayList<UserKioskMapping>();
			int count = 0;

			for (KioskCMFDto lidtDto1 : lidtDto) {
				System.out.println();
				if (count != 0) {
					entity = new UserKioskMapping();
					entity.setKioskId(lidtDto1.getKioskId());
					entity.setPfId(lidtDto1.getCmfPfId());
					listEntity.add(entity);
				}
				count++;
			}
			Iterable<UserKioskMapping> result = kioskCMFRepository.saveAll(listEntity);
			if (result != null)
				return "Kiosk Details Data Uploaded Successfully";
		}
			
		catch (Exception e) {
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
				e.printStackTrace();
			}
		}
		return "Data Not Uploaded";
	}
		
	//----
	
	
	
	//-------By Pankul END---------
	
}
