package sbi.kiosk.swayam.billingpayment.service;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import sbi.kiosk.swayam.billingpayment.repository.AllocationRepository;
import sbi.kiosk.swayam.billingpayment.repository.BillingAllocationRepository;
import sbi.kiosk.swayam.billingpayment.repository.BillingPurchaseOrderRepository;
import sbi.kiosk.swayam.common.dto.AllocationDto;
import sbi.kiosk.swayam.common.dto.BillingAllocationDto;
import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.entity.Allocation;
import sbi.kiosk.swayam.common.entity.BillingAllocation;
import sbi.kiosk.swayam.common.entity.BillingPurchaseOrder;
import sbi.kiosk.swayam.common.entity.Circle;
import sbi.kiosk.swayam.common.entity.VendorMaster;

@Service
public class BillingServiceImpl implements BillingService {

	
	@Autowired
	private BillingAllocationRepository billingallocationRepository;
	
	@Autowired
	AllocationRepository repository;

	@Autowired
	private BillingPurchaseOrderRepository billingPurchaseOrderRepository;
	
	@Autowired
	HttpSession httpSession;
	
	

	@Value("${report.path}")
	private String reportPath1;

	public FileInputStream inputStream;
	public Workbook workbook;

	public static ResourceBundle rb;

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public String upload(String path) {
	UserDto user = (UserDto) httpSession.getAttribute("userObj");
		try {
		inputStream = new FileInputStream(new File(path));

			workbook = new XSSFWorkbook(inputStream);
			org.apache.poi.ss.usermodel.Sheet firstSheet = workbook.getSheetAt(0);
			
			//Check header validation
			Row headerRow = firstSheet.getRow(0);
			
			Iterator<Cell> cells = headerRow.cellIterator();
			int header= 0;
	        while (cells.hasNext()) {
	            Cell cell = (Cell) cells.next();
	            if(header==0) {
	            	if(cell.getStringCellValue().equalsIgnoreCase("RFP_REF_NO")) {
	            		//System.out.println("Header - 0" + cell.getStringCellValue());
	            	}else {
	            		return "Please Upload the valid Excel -Invalid Header";
	            	}
	            	
	            	
	            }else if(header==1) {
	            	if(cell.getStringCellValue().equalsIgnoreCase("VENDOR_ID")) {
	            		//System.out.println("Header - 1" + cell.getStringCellValue());
	            	}else {
	            		return "Please Upload the valid Excel - Invalid Header";
	            	}
	            	
	            }else if(header==2) {
	            	if(cell.getStringCellValue().equalsIgnoreCase("CRCL_CODE")) {
	            		//System.out.println("Header - 2" + cell.getStringCellValue());
	            	}else {
	            		return "Please Upload the valid Excel - Invalid Header";
	            	}
	            	
	            }else if(header==3) {
	            	if(cell.getStringCellValue().equalsIgnoreCase("ALLOCATED_QUANTITY")) {
	            		//System.out.println("Header - 3" + cell.getStringCellValue());
	            	}else {
	            		return "Please Upload the valid Excel - Invalid Header";
	            	}
	            	
	            }else if(header==4) {
	            	if(cell.getStringCellValue().equalsIgnoreCase("TYPE")) {
	            		//System.out.println("Header - 4" + cell.getStringCellValue());
	            	}else {
	            		return "Please Upload the valid Excel - Invalid Header";
	            	}
	            	
	            }else if(header==5) {
	            	if(cell.getStringCellValue().equalsIgnoreCase("UNIT_PRICE")) {
	            		//System.out.println("Header - 5" + cell.getStringCellValue());
	            	}else {
	            		return "Please Upload the valid Excel - Invalid Header";
	            	}
	            }else if(header==6) {
	            	return "Please Upload the valid Excel - Invalid Header";
	            }
	            
	            header++;
	        }
			
			

			DataFormatter objDefaultFormat = new DataFormatter();
			FormulaEvaluator objFormulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);

			Iterator<Row> iterator = firstSheet.iterator();
			
			List<AllocationDto> allocationList = new ArrayList<>();
			int rowNumber = 0;
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				 if (rowNumber == 0) {
					 
					 
			          rowNumber++;
			          continue;
			        }
				AllocationDto allocation = new AllocationDto();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				
				int cellIdx = 0;
				
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					objFormulaEvaluator.evaluate(cell);
				    switch (cell.getColumnIndex()) {
					case 0:
						//System.out.println(cell.getCellType());
						
						if(cell.getCellType().equals(CellType.STRING)) {
						allocation.setRepRefNo(cell.getStringCellValue());
						}else if(cell.getCellType().equals(CellType.BLANK)){
							return "Failed - Column contain blank value -  RFP_REF_NO";
						}
							else {
						
							return "Invalid RFP_REF_NO -- "+ cell.getStringCellValue();
						}
						
						break;
						
					case 1:
						//System.out.println(cell.getCellType());
						if(cell.getCellType().equals(CellType.NUMERIC)) {
							allocation.setVendorId((int) cell.getNumericCellValue());
							}else if(cell.getCellType().equals(CellType.BLANK)){
								return "Failed - Column contain blank value -  VENDOR_ID";
							}else {
								return "Invalid VENDOR_ID -- "+cell.getStringCellValue();
							}
						
						
						break;
					case 2:
						if(cell.getCellType().equals(CellType.NUMERIC)) {
							allocation.setCrclCode((int) cell.getNumericCellValue());
							}else if(cell.getCellType().equals(CellType.BLANK)){
								return "Failed - Column contain blank value -  CRCL_CODE";
							}else {
								return "Invalid CRCL_CODE -- "+cell.getStringCellValue();
							}
						
						
						break;
					case 3:
						//System.out.println(cell.getCellType());
						if(cell.getCellType().equals(CellType.NUMERIC)) {
							allocation.setAllocatedQuantity((long) cell.getNumericCellValue());
							}else if(cell.getCellType().equals(CellType.BLANK)){
								
								return "Failed - Column contain blank value -  ALLOCATED_QUANTITY";
						}else {
							return "Invalid ALLOCATED_QUANTITY -- "+cell.getStringCellValue();
							}
						
						break;
					case 4:
						if(cell.getCellType().equals(CellType.STRING)) {
							allocation.setType(cell.getStringCellValue());
							}else if(cell.getCellType().equals(CellType.BLANK)){
								return "Failed - Column contain blank value -  TYPE";
							}else {
								return "Invalid TYPE -- "+cell.getStringCellValue();
							}
						
						
						break;
					case 5:
						if(cell.getCellType().equals(CellType.NUMERIC)) {
							allocation.setUnitPrice(cell.getNumericCellValue());
							}else if(cell.getCellType().equals(CellType.BLANK)){
								return "Failed - Excel contain blank value -  UNIT_PRICE";
							}else {
								return "Please Upload valid excel -- UNIT_PRICE";
							}
						
						break;	
						
					 default:
				            break;
					
					}
				    
				    
					
				}


				allocationList.add(allocation);
			} // 2nd close while loop

			
			Allocation entity = null;
			List<Allocation> listEntity = new ArrayList<Allocation>();

			int count = 0;
			//System.out.println("allocationList outside " + allocationList);
			Iterator<AllocationDto> listDto= allocationList.iterator();
			
			while (listDto.hasNext()) {
				AllocationDto allocationDto = (AllocationDto) listDto.next();
				//System.out.println("Allocation DTO "+ allocationDto);
				entity = new Allocation();
				entity.setRepRefNo(allocationDto.getRepRefNo());
				entity.setVendorId(allocationDto.getVendorId());
				entity.setCrclCode(allocationDto.getCrclCode());
				entity.setAllocatedQuantity(allocationDto.getAllocatedQuantity());
				entity.setRemainingQuantity(allocationDto.getAllocatedQuantity());
				entity.setType(allocationDto.getType());
				entity.setUnitPrice(allocationDto.getUnitPrice());
				entity.setStatus("1");
				entity.setCreatedBy(user.getUsername());
				entity.setCreatedDate(new Date());
				listEntity.add(entity);
				////System.out.println("Entity "+ listEntity);
				
			}

			Iterable<Allocation> result = repository.saveAll(listEntity);
			////System.out.println("result" + result);
			if (result != null)
				return "Allocation Completed";
		}

		
		  catch(ConstraintViolationException e) { 
			  System.out.println("Error -3" + e.getLocalizedMessage());
			  return "Failed - Duplicate records";
		  }
		  //e.printStackTrace(); }
		 catch (Exception e) {
			 System.out.println("Error -1" + e.getLocalizedMessage());
			 return "Failed - Duplicate records";
		//e.printStackTrace();
	}
		finally
	{
		try {
			if (workbook != null) {
				workbook.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}

		} catch (Exception e) {
			 System.out.println("Error-2 " + e.getLocalizedMessage());
			 
			//e.printStackTrace();
		}
	}return"Data Not Uploaded";
	}

	public Page<BillingAllocationDto> findPaginated(final int page, final int size) {

		//System.out.println("page1" + page);
		Page<BillingAllocationDto> pageDto = billingallocationRepository.findAll(PageRequest.of(page, size))
				.map(BillingAllocationDto::new);

		//System.out.println("pageDto" + pageDto);

		return pageDto;
	}
	
	public Page<AllocationDto> findPaginatedAllocation(final int page, final int size) {

		//System.out.println("findPaginatedAllocation" + page);
		Page<AllocationDto> entityAllocation = repository.findAll(PageRequest.of(page, size))
				.map(AllocationDto::new);
	

		//System.out.println("findPaginatedAllocation" + entityAllocation);

		return entityAllocation;
	}

	@Override
	public BillingAllocationDto findBillingallocId(Integer allocId) {// Integer.parseInt(repRefNo)

		//System.out.println("allocId" + allocId);
		BillingAllocation billing = billingallocationRepository.findrepRefNo(allocId);
		//System.out.println("billing" + billing);
		BillingAllocationDto Dto = new BillingAllocationDto();
		Dto.setRepRefNo(billing.getRepRefNo());
		Dto.setVendorId(billing.getVendorId());
		Dto.setCrclCode(billing.getCrclCode());
		Dto.setAllocatedQuantity(billing.getAllocatedQuantity());
		Dto.setRemainingQuantity(billing.getRemainingQuantity());
		//System.out.println("Dto" + Dto);
		return Dto;

	}

	@Override
	public String updateBillingAllocation(BillingAllocationDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
