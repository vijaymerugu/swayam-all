package sbi.kiosk.swayam.misreports.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.BorderStyle;
/**
 * @author vmph2371481
 *
 */
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import sbi.kiosk.swayam.misreports.dto.MISReportInputDto;
import sbi.kiosk.swayam.misreports.dto.MISReportOutputDto;

public class GenerateExcelReport {

	public static ByteArrayInputStream getMisReport(MISReportInputDto misReportInputDto,
			List<MISReportOutputDto> misReportOutputDtoList) {

		List<String> headerList = new ArrayList<>();
		List<Object> dataList = new ArrayList<>();
		
		int selectedColsCount = misReportInputDto.getSelectedColumnIndexes().split(",").length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("MIS Report");

		// Creating Style
		CellStyle headerStyle = workbook.createCellStyle();
		CellStyle filterLabelStyle = workbook.createCellStyle();
		CellStyle filterValueStyle = workbook.createCellStyle();
		CellStyle dataHeaderStyle = workbook.createCellStyle();
		CellStyle dataBodyStyle = workbook.createCellStyle();

		// Creating Font and settings
		Font headerFont = workbook.createFont();
		headerFont.setFontHeightInPoints((short) 15);
		headerFont.setFontName("Helvetica");
		headerFont.setBold(true);

		Font filterLableFont = workbook.createFont();
		filterLableFont.setFontHeightInPoints((short) 12);
		filterLableFont.setFontName("Helvetica");
		filterLableFont.setBold(true);

		Font filterValueFont = workbook.createFont();
		filterValueFont.setFontHeightInPoints((short) 12);
		filterValueFont.setFontName("Helvetica");
		filterValueFont.setBold(true);

		Font dataHeaderFont = workbook.createFont();
		dataHeaderFont.setFontHeightInPoints((short) 10);
		dataHeaderFont.setFontName("Helvetica");
		dataHeaderFont.setBold(true);

		Font dataBodyFont = workbook.createFont();
		dataBodyFont.setFontHeightInPoints((short) 10);
		dataBodyFont.setFontName("Helvetica");

		// Applying font to the style
		headerStyle.setFont(headerFont);
		headerStyle.setAlignment(HorizontalAlignment.CENTER);
		filterLabelStyle.setFont(filterLableFont);
		filterLabelStyle.setAlignment(HorizontalAlignment.RIGHT);
		filterValueStyle.setFont(filterValueFont);
		filterValueStyle.setAlignment(HorizontalAlignment.LEFT);

		dataHeaderStyle.setFont(dataHeaderFont);
		// Styling border of data cell.
		dataHeaderStyle.setBorderBottom(BorderStyle.THIN);
		dataHeaderStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		dataHeaderStyle.setBorderRight(BorderStyle.THIN);
		dataHeaderStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		dataHeaderStyle.setBorderTop(BorderStyle.THIN);
		dataHeaderStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

		dataBodyStyle.setFont(dataBodyFont);
		dataBodyStyle.setBorderBottom(BorderStyle.THIN);
		dataBodyStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		dataBodyStyle.setBorderRight(BorderStyle.THIN);
		dataBodyStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		dataBodyStyle.setBorderTop(BorderStyle.THIN);
		dataBodyStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

		int allCol = selectedColsCount + 1;
		int midCol = allCol / 2;
		if(selectedColsCount == 1) midCol = 0;
		
		Row headerRow = sheet.createRow(0);
		Cell headerCell = headerRow.createCell(0);
		headerCell.setCellValue("MIS Report");
		headerCell.setCellStyle(headerStyle);

		Row filterRow1 = sheet.createRow(2);

		Cell filterCellLabel1 = filterRow1.createCell(0);
		filterCellLabel1.setCellValue("Generated On:");
		filterCellLabel1.setCellStyle(filterLabelStyle);

		Cell filterCellValue1 = filterRow1.createCell(midCol + 1);
		filterCellValue1.setCellValue((new SimpleDateFormat("dd-MM-yyyy")).format(new Date()));
		filterCellValue1.setCellStyle(filterValueStyle);


		Row filterRow2 = sheet.createRow(3);

		Cell filterCellLabel2 = filterRow2.createCell(0);
		filterCellLabel2.setCellValue("From Date:");
		filterCellLabel2.setCellStyle(filterLabelStyle);

		Cell filterCellValue2 = filterRow2.createCell(midCol + 1);
		filterCellValue2.setCellValue(misReportInputDto.getFromDate());
		filterCellValue2.setCellStyle(filterValueStyle);

		Row filterRow3 = sheet.createRow(4);

		Cell filterCellLabel3 = filterRow3.createCell(0);
		filterCellLabel3.setCellValue("To Date:");
		filterCellLabel3.setCellStyle(filterLabelStyle);

		Cell filterCellValue3 = filterRow3.createCell(midCol + 1);
		filterCellValue3.setCellValue(misReportInputDto.getToDate());
		filterCellValue3.setCellStyle(filterValueStyle);

		
		//Adding Report merging sections
		// Syntax: parameters(first row, last row, first column, last column)
		
		// Adding header merge region
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, allCol - 1));
		// Adding blank row merge region
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, allCol - 1));

		if(selectedColsCount != 1) {
			// Adding Generated on filter merge region
			sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, midCol));
			sheet.addMergedRegion(new CellRangeAddress(2, 2, midCol + 1, allCol - 1));

			// Adding From Date filter merge region
			sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, midCol));
			sheet.addMergedRegion(new CellRangeAddress(3, 3, midCol + 1, allCol - 1));

			// Adding To Date filter merge region
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, midCol));
			sheet.addMergedRegion(new CellRangeAddress(4, 4, midCol + 1, allCol - 1));

		} 
		
		// Adding Blank Row
		sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, allCol - 1));

		
		Map<Integer, Object[]> data = new LinkedHashMap<Integer, Object[]>();
		if (misReportOutputDtoList != null && misReportOutputDtoList.size() > 0) {

			if (misReportOutputDtoList.get(0).getSelectedGroup() != null) {
				headerList.add("Group Type");
			}
			if (misReportOutputDtoList.get(0).getSwayamTransaction() != null) {
				headerList.add("Swayam Transactions");
			}
			if (misReportOutputDtoList.get(0).getBranchCounter() != null) {
				headerList.add("Branch Counter Transactions");
			}
			if (misReportOutputDtoList.get(0).getMigrationPercent() != null) {
				headerList.add("Migration %");
			}
			if (misReportOutputDtoList.get(0).getUptimePercent() != null) {
				headerList.add("Uptime %");
			}
			if (misReportOutputDtoList.get(0).getNoOfKiosks() != null) {
				headerList.add("No. of kiosks");
			}
			if (misReportOutputDtoList.get(0).getTotalDowntime() != null) {
				headerList.add("Total downtime");
			}
			if (misReportOutputDtoList.get(0).getOnSiteOffSite() != null) {
				headerList.add("Onsite / Offsite");
			}
			if (misReportOutputDtoList.get(0).getStandaloneTTW() != null) {
				headerList.add("Standalone / TTW");
			}
			if (misReportOutputDtoList.get(0).getNoOfRequestRaised() != null) {
				headerList.add("No. of requests raised");
			}
			if (misReportOutputDtoList.get(0).getTypeOfRequest() != null) {
				headerList.add("Type of requests");
			}
			if (misReportOutputDtoList.get(0).getTatOfRequestCompletion() != null) {
				headerList.add("TAT of request completion");
			}

			data.put(0, headerList.toArray());

			for (int i = 0; i < misReportOutputDtoList.size(); i++) {
				dataList = new ArrayList<>();
				if (misReportOutputDtoList.get(i).getSelectedGroup() != null) {
					dataList.add(misReportOutputDtoList.get(i).getSelectedGroup());
				}
				if (misReportOutputDtoList.get(i).getSwayamTransaction() != null) {
					dataList.add(misReportOutputDtoList.get(i).getSwayamTransaction());
				}
				if (misReportOutputDtoList.get(i).getBranchCounter() != null) {
					dataList.add(misReportOutputDtoList.get(i).getBranchCounter());
				}
				if (misReportOutputDtoList.get(i).getMigrationPercent() != null) {
					dataList.add(misReportOutputDtoList.get(i).getMigrationPercent());
				}
				if (misReportOutputDtoList.get(i).getUptimePercent() != null) {
					dataList.add(misReportOutputDtoList.get(i).getUptimePercent());
				}
				if (misReportOutputDtoList.get(i).getNoOfKiosks() != null) {
					dataList.add(misReportOutputDtoList.get(i).getNoOfKiosks());
				}
				if (misReportOutputDtoList.get(i).getTotalDowntime() != null) {
					dataList.add(misReportOutputDtoList.get(i).getTotalDowntime());
				}
				if (misReportOutputDtoList.get(i).getOnSiteOffSite() != null) {
					dataList.add(misReportOutputDtoList.get(i).getOnSiteOffSite());
				}
				if (misReportOutputDtoList.get(i).getStandaloneTTW() != null) {
					dataList.add(misReportOutputDtoList.get(i).getStandaloneTTW());
				}
				if (misReportOutputDtoList.get(i).getNoOfRequestRaised() != null) {
					dataList.add(misReportOutputDtoList.get(i).getNoOfRequestRaised());
				}
				if (misReportOutputDtoList.get(i).getTypeOfRequest() != null) {
					dataList.add(misReportOutputDtoList.get(i).getTypeOfRequest());
				}
				if (misReportOutputDtoList.get(i).getTatOfRequestCompletion() != null) {
					dataList.add(misReportOutputDtoList.get(i).getTatOfRequestCompletion());
				}
				data.put(i + 1, dataList.toArray());
			}
		}

		Set<Integer> keyset = data.keySet();
		int rownum = 6;
		for (Integer key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (cell.getRowIndex() == 6) {
					cell.setCellStyle(dataHeaderStyle);
				} else {
					cell.setCellStyle(dataBodyStyle);
				}

				if (obj instanceof Date) {
					cell.setCellValue((Date) obj);
				} else if (obj instanceof Boolean) {
					cell.setCellValue((Boolean) obj);
				} else if (obj instanceof String) {
					cell.setCellValue((String) obj);
				} else if (obj instanceof Double) {
					cell.setCellValue((Double) obj);
				} else if (obj instanceof Integer) {
					cell.setCellValue((Integer) obj);
				}
			}
		}

		try {
			workbook.write(out);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

}