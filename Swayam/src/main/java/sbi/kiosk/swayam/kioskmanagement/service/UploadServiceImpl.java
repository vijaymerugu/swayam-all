package sbi.kiosk.swayam.kioskmanagement.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.common.dto.CbsBrhmDto;
import sbi.kiosk.swayam.common.dto.KioskDto;
import sbi.kiosk.swayam.common.entity.BranchMaster;
import sbi.kiosk.swayam.common.entity.KioskMaster;
import sbi.kiosk.swayam.kioskmanagement.repository.BranchMasterRepository;
import sbi.kiosk.swayam.kioskmanagement.repository.kioskMasterManagementRepository;

@Service
public class UploadServiceImpl implements UploadService {

	@Autowired
	private kioskMasterManagementRepository kioskMasterManagementRepository;
	@Autowired
	private BranchMasterRepository branchMasterRepository;

	public FileInputStream inputStream;
	public Workbook workbook;

	@Override
	public String uploadKioskInformation() {
		System.out.println("inside upload kiosk information service...");
		// upload kiosk file information
		try {

			System.out.println("while reading excel file...");
			inputStream = new FileInputStream(
					new File("C:\\Users\\Admin\\Downloads\\Swayam_Kiosk_Information (1).xlsx"));
			workbook = new XSSFWorkbook(inputStream);

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
						}
						break;
					case BOOLEAN:
						System.out.print(cell.getBooleanCellValue());
						break;
					case NUMERIC:
						dto.setSrNo(String.valueOf(cell.getNumericCellValue()));
						System.out.print(cell.getNumericCellValue());
						dto.setSrNo(String.valueOf(cell.getNumericCellValue()));
						// System.out.println("serial no:"+dto.getSrNo());

						break;
					}
					System.out.print(" - ");
				} // 1st close while loop
				System.out.println();
				lidtDto.add(dto);
			} // 2nd close while loop

			KioskMaster entity = null;
			List<KioskMaster> listEntity = new ArrayList<KioskMaster>();
			int count = 0;

			for (KioskDto lidtDto1 : lidtDto) {
				if (count != 0) {
					entity = new KioskMaster();
					entity.setSrNo(Long.parseLong(lidtDto1.getSrNo().substring(0, lidtDto1.getSrNo().length() - 2)));
					System.out.println(entity.getSrNo());
					entity.setCircle(lidtDto1.getCircle());
					entity.setBranchName(lidtDto1.getBranchName());
					entity.setBranchCode(lidtDto1.getBranchCode());
					entity.setKioskId(lidtDto1.getKioskID());
					entity.setKioskSerialNo(lidtDto1.getKioskSerialNumber());
					entity.setKioskIP(lidtDto1.getKioskIPAddress());
					entity.setOs(lidtDto1.getoS());
					entity.setMake(lidtDto1.getMake());
					listEntity.add(entity);
				}
				count++;
			}
			Iterable<KioskMaster> result = kioskMasterManagementRepository.saveAll(listEntity);
			if (result != null)
				return "data uploaded";
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
		return "data is not upload";

	}

	@Override
	public String uploadCBSbrhmInformation() {

		System.out.println("inside upload kiosk information service...");
		// upload kiosk file information
		try {
			System.out.println("while reading excel file...");
			inputStream = new FileInputStream(new File("C:\\Users\\Admin\\Downloads\\CBS_brhm_29FEBRUARY2020.xlsx"));
			workbook = new XSSFWorkbook(inputStream);
			org.apache.poi.ss.usermodel.Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();
			List<CbsBrhmDto> lidtDto = new ArrayList<CbsBrhmDto>();
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
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

					}// switch close
					System.out.print(" - ");
				} // 1st close while loop
				lidtDto.add(dto);
				System.out.println();
			} // 2nd close while loop

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
					
					entity.setCRCLCode(listDto1.getcRCLCode().substring(0, listDto1.getcRCLCode().length() - 2));
					
					entity.setBranchMgrName(listDto1.getBranchMgrName());
					entity.setBranchMgrMobileNo(listDto1.getBranchMgrMobileNo());
					entity.setCircle(listDto1.getcRCLName());
					entity.setBusinessHrs("");
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
			return "";
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
		return null;

	}
}
