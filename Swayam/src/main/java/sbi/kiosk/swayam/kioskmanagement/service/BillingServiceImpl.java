package sbi.kiosk.swayam.kioskmanagement.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

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
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.common.dto.BillingAllocationDto;
import sbi.kiosk.swayam.common.entity.BillingAllocation;
import sbi.kiosk.swayam.common.entity.BillingPurchaseOrder;
import sbi.kiosk.swayam.kioskmanagement.repository.BillingAllocationRepository;
import sbi.kiosk.swayam.kioskmanagement.repository.BillingPurchaseOrderRepository;

@Service
public class BillingServiceImpl implements BillingService {

	// By Ankur 28-04-2020-----------STARTS---------
	@Autowired
	private BillingAllocationRepository billingallocationRepository;

	@Autowired
	private BillingPurchaseOrderRepository billingPurchaseOrderRepository;

	@Value("${report.path}")
	private String reportPath1;

	public FileInputStream inputStream;
	public Workbook workbook;

	public static ResourceBundle rb;

	@Override
	public String upload(String path) {

		System.out.println("inside billing upload  service...");

		try {

			System.out.println("path" + path);

			System.out.println("while reading excel file...");
			inputStream = new FileInputStream(new File(path));

			workbook = new XSSFWorkbook(inputStream);
			org.apache.poi.ss.usermodel.Sheet firstSheet = workbook.getSheetAt(0);

			DataFormatter objDefaultFormat = new DataFormatter();
			FormulaEvaluator objFormulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);

			Iterator<Row> iterator = firstSheet.iterator();
			List<BillingAllocationDto> lidtDto = new ArrayList<>();

			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				BillingAllocationDto dto = new BillingAllocationDto();

				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();
					objFormulaEvaluator.evaluate(cell);
					switch (cell.getCellType()) {
					case STRING:
						if (!(String.valueOf(cell.getRow().getRowNum()).equals("0"))) {
							System.out.print(cell.getColumnIndex());
							System.out.print(cell.getRow().getRowNum());

							if (String.valueOf(cell.getColumnIndex()).equals("0")) {
								String cellValueStr = objDefaultFormat.formatCellValue(cell, objFormulaEvaluator);
								dto.setRepRefNo(cell.getStringCellValue());
							}

							/*
							 * if (String.valueOf(cell.getColumnIndex()).equals("1")) {
							 * dto.setVendorId(cell.getNumericCellValue()); }
							 */

							/*
							 * if (String.valueOf(cell.getColumnIndex()).equals("2")) {
							 * dto.setCrclCode(cell.getStringCellValue()); }
							 */

							/*
							 * if (String.valueOf(cell.getColumnIndex()).equals("3")) {
							 * dto.setAllocatedQuantity(cell.getStringCellValue()); }
							 */

							/*
							 * if (String.valueOf(cell.getColumnIndex()).equals("4")) {
							 * dto.setRemainingQuantity(cell.getStringCellValue()); }
							 */

							if (String.valueOf(cell.getColumnIndex()).equals("5")) {
								dto.setType(cell.getStringCellValue());
							}
							/*
							 * if (String.valueOf(cell.getColumnIndex()).equals("6")) {
							 * dto.setUnitPrice(cell.getStringCellValue()); }
							 */
							if (String.valueOf(cell.getColumnIndex()).equals("7")) {
								dto.setStatus(cell.getStringCellValue());
							}

						}
						break;
					case NUMERIC:
						if (!(String.valueOf(cell.getRow().getRowNum()).equals("0"))) {


							if (String.valueOf(cell.getColumnIndex()).equals("0")) {
								dto.setVendorId(cell.getNumericCellValue());

							}
							if (String.valueOf(cell.getColumnIndex()).equals("1")) {
								System.out.print((String.valueOf(cell.getNumericCellValue())));
								dto.setCrclCode(cell.getNumericCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("3")) {
								System.out.print((String.valueOf(cell.getNumericCellValue())));
								dto.setAllocatedQuantity(cell.getNumericCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("4")) {
								System.out.print((String.valueOf(cell.getNumericCellValue())));
								dto.setRemainingQuantity(cell.getNumericCellValue());
							}
							if (String.valueOf(cell.getColumnIndex()).equals("6")) {
								System.out.print((String.valueOf(cell.getNumericCellValue())));
								dto.setUnitPrice(cell.getNumericCellValue());
							}

							break;
						}
					}
					System.out.print(" - ");

				} // 1st close while loop
				System.out.println();
				lidtDto.add(dto);
				System.out.println("lidtDto" + lidtDto);
			} // 2nd close while loop

			BillingAllocation entity = null;
			List<BillingAllocation> listEntity = new ArrayList<BillingAllocation>();

			int count = 0;

			for (BillingAllocationDto lidtDto1 : lidtDto) {
				System.out.println();
				if (count != 0) {
					entity = new BillingAllocation();
					entity.setRepRefNo(lidtDto1.getRepRefNo());
					entity.setVendorId(lidtDto1.getVendorId());
					entity.setCrclCode(lidtDto1.getCrclCode());
					entity.setAllocatedQuantity(lidtDto1.getAllocatedQuantity());
					entity.setRemainingQuantity(lidtDto1.getRemainingQuantity());
					entity.setType(lidtDto1.getType());
					entity.setUnitPrice(lidtDto1.getUnitPrice());
					entity.setStatus(lidtDto1.getStatus());
					listEntity.add(entity);
					System.out.println("listEntity" + listEntity);
				}
				count++;

			}
			Iterable<BillingAllocation> result = billingallocationRepository.saveAll(listEntity);
			System.out.println("result" + result);
			if (result != null)
				return "Billing_allocation";
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

	public Page<BillingAllocationDto> findPaginated(final int page, final int size) {

		System.out.println("page1" + page);
		Page<BillingAllocationDto> pageDto = billingallocationRepository.findAll(PageRequest.of(page, size))
				.map(BillingAllocationDto::new);

		System.out.println("pageDto" + pageDto);

		return pageDto;
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
		BillingAllocation entity = new BillingAllocation();

		double intallocatedQuantity = dto.getAllocatedQuantity();

		double intUnitPrice = dto.getUnitPrice();

		double RemainingQuantity;
		RemainingQuantity = dto.getRemainingQuantity();
		if (RemainingQuantity == 0) {
			RemainingQuantity = intallocatedQuantity;// 100 100

			RemainingQuantity = RemainingQuantity - intUnitPrice;
		} else {
			RemainingQuantity = RemainingQuantity - intUnitPrice;
		}

		System.out.println("intallocatedQuantity" + intallocatedQuantity + "intUnitPrice" + intUnitPrice
				+ "RemainingQuantity" + RemainingQuantity);

		entity.setRepRefNo(dto.getRepRefNo());
		entity.setVendorId(dto.getVendorId());
		entity.setCrclCode(dto.getCrclCode());
		entity.setAllocatedQuantity(dto.getAllocatedQuantity());
		entity.setUnitPrice(intUnitPrice);
		entity.setRemainingQuantity(RemainingQuantity);
		entity.setStatus("s");

		int allocId = 1;
		String status = "0";

		BillingAllocation resultSave = billingallocationRepository.save(entity);
		allocId = resultSave.getAllocId();

		BillingPurchaseOrder entity1 = new BillingPurchaseOrder(allocId, RemainingQuantity, "s", entity);

		entity.setBillingPurchaseOrder(entity1);

		BillingAllocation resultSave1 = billingallocationRepository.save(entity);
		allocId = resultSave1.getAllocId();
		allocId = allocId - 1;
		System.out.println(allocId);
		billingallocationRepository.Updatestatus(status, allocId);
		String refno = "successfully";
		System.out.println("refno" + refno);

		return refno;

	}

	

}
