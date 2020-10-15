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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;

import sbi.kiosk.swayam.billingpayment.controller.BillingPenaltyController;
import sbi.kiosk.swayam.common.constants.ExceptionConstants;
import sbi.kiosk.swayam.common.entity.MISAvailableColumns;
import sbi.kiosk.swayam.common.entity.MISReportData;
import sbi.kiosk.swayam.misreports.dto.MISReportInputDto;

/**
 * @author vmph2371481
 *
 */

public class GenerateExcelReport {
	
	static Logger logger =  LoggerFactory.getLogger(GenerateExcelReport.class);
	

	public static ByteArrayInputStream getMisReport(MISReportInputDto misReportInputDto,
			List<MISReportData> misReportDataList, List<String> selectedColumnIndexList, List<MISAvailableColumns> columnListByGroupId) {
		List<String> selectedColumnNameList = new ArrayList<>();
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
		if (selectedColsCount == 1)
			midCol = 0;

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

		Row filterRow4 = sheet.createRow(5);

		Cell filterCellLabel4 = filterRow4.createCell(0);
		filterCellLabel4.setCellValue("Grouping Criteria:");
		filterCellLabel4.setCellStyle(filterLabelStyle);

		Cell filterCellValue4 = filterRow4.createCell(midCol + 1);
		filterCellValue4.setCellValue(misReportInputDto.getGroupingCriteriaName());
		filterCellValue4.setCellStyle(filterValueStyle);

		// Adding Report merging sections
		// Syntax: parameters(first row, last row, first column, last column)

		// Adding header merge region
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, allCol));
		// Adding blank row merge region
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, allCol));

		if (selectedColsCount != 1) {
			// Adding Generated on filter merge region
			sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, midCol));
			sheet.addMergedRegion(new CellRangeAddress(2, 2, midCol + 1, allCol));

			// Adding From Date filter merge region
			sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, midCol));
			sheet.addMergedRegion(new CellRangeAddress(3, 3, midCol + 1, allCol));

			// Adding To Date filter merge region
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, midCol));
			sheet.addMergedRegion(new CellRangeAddress(4, 4, midCol + 1, allCol));

			// Adding Grouping Criteria filter merge region
			sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, midCol));
			sheet.addMergedRegion(new CellRangeAddress(5, 5, midCol + 1, allCol));
		}

		// Adding Blank Row
		sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, allCol));

		Map<Integer, Object[]> data = new LinkedHashMap<Integer, Object[]>();
		if (misReportDataList != null && misReportDataList.size() > 0) {

			headerList.add(getGroupNameById(misReportInputDto.getGroupingCriteriaId()));

			if (misReportInputDto.getGroupingCriteriaId() == 2) {
				headerList.add("Circle Name");
			}
			if (misReportInputDto.getGroupingCriteriaId() == 4) {
				headerList.add("Branch Code");
				headerList.add("Vendor Name");
			}
			for(String index : selectedColumnIndexList) {
				for(MISAvailableColumns column : columnListByGroupId) {
					if(index.equals(column.getColumnId())) {
						headerList.add(column.getColumnName());
						selectedColumnNameList.add(column.getColumnName());
					}
				}
			}

			data.put(0, headerList.toArray());

			for (int i = 0; i < misReportDataList.size(); i++) {
				dataList = new ArrayList<>();
				dataList.add(misReportDataList.get(i).getSelectedGroup());

				if (misReportInputDto.getGroupingCriteriaId() == 2) {
					dataList.add(misReportDataList.get(i).getCircleName());
				}
				if (misReportInputDto.getGroupingCriteriaId() == 4) {
					dataList.add(misReportDataList.get(i).getBranchCode());
					dataList.add(misReportDataList.get(i).getVendorName());
				}

				if (selectedColumnNameList.contains("Swayam Transactions")) {
					dataList.add(misReportDataList.get(i).getSwayamTransaction());
				}
				if (selectedColumnNameList.contains("Branch Counter Transactions")) {
					dataList.add(misReportDataList.get(i).getBranchCounter());
				}
				if (selectedColumnNameList.contains("Migration %")) {
					dataList.add(misReportDataList.get(i).getMigrationPercent());
				}
				if (selectedColumnNameList.contains("Uptime %")) {
					dataList.add(misReportDataList.get(i).getUptimePercent());
				}
				if (selectedColumnNameList.contains("No. of kiosks")) {
					dataList.add(misReportDataList.get(i).getNoOfKiosks());
				}
				if (selectedColumnNameList.contains("Total downtime")) {
					dataList.add(misReportDataList.get(i).getTotalDowntime());
				}
				if (selectedColumnNameList.contains("Onsite / Offsite")) {
					dataList.add(misReportDataList.get(i).getOnSiteOffSite());
				}
				if (selectedColumnNameList.contains("Installation Type")) {
					dataList.add(misReportDataList.get(i).getStandaloneTTW());
				}
				if (selectedColumnNameList.contains("No. of requests raised")) {
					dataList.add(misReportDataList.get(i).getNoOfRequestRaised());
				}
				if (selectedColumnNameList.contains("Type of requests")) {
					dataList.add(misReportDataList.get(i).getTypeOfRequest());
				}
				if (selectedColumnNameList.contains("TAT of request completion")) {
					dataList.add(misReportDataList.get(i).getTatOfRequestCompletion());
				}
				data.put(i + 1, dataList.toArray());
			}
		}

		Set<Integer> keyset = data.keySet();
		int rownum = 7;
		for (Integer key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (cell.getRowIndex() == 7) {
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
			//e.printStackTrace();
			logger.error("IO Exception "+ExceptionConstants.EXCEPTION);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	public static String getGroupNameById(int groupId) {
		String groupName = null;
		if (groupId == 1) {
			groupName = "Circle Name";
		} else if (groupId == 2) {
			groupName = "Branch Code";
		} else if (groupId == 3) {
			groupName = "Vendor Name";
		} else if (groupId == 4) {
			groupName = "Kiosk ID";
		}
		return groupName;
	}

}