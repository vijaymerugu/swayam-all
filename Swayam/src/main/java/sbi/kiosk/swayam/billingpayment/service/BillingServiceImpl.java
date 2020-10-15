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
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

	// By Ankur 28-04-2020-----------STARTS---------
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

		System.out.println("inside billing upload  service...");
			UserDto user = (UserDto) httpSession.getAttribute("userObj");
			System.out.println("Username " + user.getUsername());

		try {
			
			inputStream = new FileInputStream(new File(path));

			workbook = new XSSFWorkbook(inputStream);
			org.apache.poi.ss.usermodel.Sheet firstSheet = workbook.getSheetAt(0);

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
						allocation.setRepRefNo(cell.getStringCellValue());
						System.out.println(cell.getStringCellValue());
						break;
						
					case 1:
						allocation.setVendorId((int) cell.getNumericCellValue());
						//allocation.setVendor(map1.get(allocation.getVendorId()));
						break;
					case 2:
						allocation.setCrclCode((int) cell.getNumericCellValue());
						//allocation.setCircle(map2.get(allocation.getCrclCode()));
						break;
					case 3:
						allocation.setAllocatedQuantity((long) cell.getNumericCellValue());
						break;
					case 4:
						allocation.setType(cell.getStringCellValue());
						break;
					case 5:
						allocation.setUnitPrice(cell.getNumericCellValue());
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
			System.out.println("allocationList outside " + allocationList);
			Iterator<AllocationDto> listDto= allocationList.iterator();
			
			while (listDto.hasNext()) {
				AllocationDto allocationDto = (AllocationDto) listDto.next();
				System.out.println("Allocation DTO "+ allocationDto);
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
				//System.out.println("Entity "+ listEntity);
				
			}

			Iterable<Allocation> result = repository.saveAll(listEntity);
			//System.out.println("result" + result);
			if (result != null)
				return "Billing_allocation";
		}

	catch(Exception e)
	{
		e.printStackTrace();
	}finally
	{
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
	}return"Data Not Uploaded";
	}

	public Page<BillingAllocationDto> findPaginated(final int page, final int size) {

		System.out.println("page1" + page);
		Page<BillingAllocationDto> pageDto = billingallocationRepository.findAll(PageRequest.of(page, size))
				.map(BillingAllocationDto::new);

		System.out.println("pageDto" + pageDto);

		return pageDto;
	}
	
	public Page<AllocationDto> findPaginatedAllocation(final int page, final int size) {

		System.out.println("findPaginatedAllocation" + page);
		Page<AllocationDto> entityAllocation = repository.findAll(PageRequest.of(page, size))
				.map(AllocationDto::new);
	

		System.out.println("findPaginatedAllocation" + entityAllocation);

		return entityAllocation;
	}

	@Override
	public BillingAllocationDto findBillingallocId(Integer allocId) {// Integer.parseInt(repRefNo)

		System.out.println("allocId" + allocId);
		BillingAllocation billing = billingallocationRepository.findrepRefNo(allocId);
		System.out.println("billing" + billing);
		BillingAllocationDto Dto = new BillingAllocationDto();
		Dto.setRepRefNo(billing.getRepRefNo());
		Dto.setVendorId(billing.getVendorId());
		Dto.setCrclCode(billing.getCrclCode());
		Dto.setAllocatedQuantity(billing.getAllocatedQuantity());
		Dto.setRemainingQuantity(billing.getRemainingQuantity());
		System.out.println("Dto" + Dto);
		return Dto;

	}

	@Override
	public String updateBillingAllocation(BillingAllocationDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @Override public String updateBillingAllocation(BillingAllocationDto dto) {
	 * BillingAllocation entity = new BillingAllocation();
	 * 
	 * double intallocatedQuantity = dto.getAllocatedQuantity();
	 * 
	 * double intUnitPrice = dto.getUnitPrice();
	 * 
	 * double RemainingQuantity; RemainingQuantity = dto.getRemainingQuantity(); if
	 * (RemainingQuantity == 0) { RemainingQuantity = intallocatedQuantity;// 100
	 * 100
	 * 
	 * RemainingQuantity = RemainingQuantity - intUnitPrice; } else {
	 * RemainingQuantity = RemainingQuantity - intUnitPrice; }
	 * 
	 * System.out.println("intallocatedQuantity" + intallocatedQuantity +
	 * "intUnitPrice" + intUnitPrice + "RemainingQuantity" + RemainingQuantity);
	 * 
	 * entity.setRepRefNo(dto.getRepRefNo()); entity.setVendorId(dto.getVendorId());
	 * entity.setCrclCode(dto.getCrclCode());
	 * entity.setAllocatedQuantity(dto.getAllocatedQuantity());
	 * entity.setUnitPrice(intUnitPrice);
	 * entity.setRemainingQuantity(RemainingQuantity); entity.setStatus("s");
	 * 
	 * int allocId = 1; String status = "0";
	 * 
	 * BillingAllocation resultSave = billingallocationRepository.save(entity);
	 * allocId = resultSave.getAllocId();
	 * 
	 * BillingPurchaseOrder entity1 = new BillingPurchaseOrder(allocId,
	 * RemainingQuantity, "s", entity);
	 * 
	 * entity.setBillingPurchaseOrder(entity1);
	 * 
	 * BillingAllocation resultSave1 = billingallocationRepository.save(entity);
	 * allocId = resultSave1.getAllocId(); allocId = allocId - 1;
	 * System.out.println(allocId); billingallocationRepository.Updatestatus(status,
	 * allocId); String refno = "successfully"; System.out.println("refno" + refno);
	 * 
	 * return refno;
	 * 
	 * }
	 */

}
