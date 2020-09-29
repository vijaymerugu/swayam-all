package sbi.kiosk.swayam.misreports.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import sbi.kiosk.swayam.common.entity.MISAvailableColumns;
import sbi.kiosk.swayam.common.entity.MISReportData;
import sbi.kiosk.swayam.misreports.dto.MISReportInputDto;

/**
 * @author vmph2371481
 *
 */
public class GeneratePdfReport {

	public static ByteArrayInputStream getMisReport(MISReportInputDto misReportInputDto,
			List<MISReportData> misReportDataList, List<String> selectedColumnIndexList, List<MISAvailableColumns> columnListByGroupId) {
		List<String> selectedColumnNameList = new ArrayList<>();
		Document document = new Document();
		document.setPageSize(PageSize.A4.rotate());
		float fontSize = 6.7f;
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			PdfWriter writer = PdfWriter.getInstance(document, out);
			Rectangle rect = new Rectangle(30, 30, 550, 800);
			writer.setBoxSize("art", rect);
			HeaderFooterPageEvent event = new HeaderFooterPageEvent();
			event.setReportHeader("MIS Report");
			event.setGeneratedOnDate((new SimpleDateFormat("dd-MM-yyyy")).format(new Date()));
			event.setFromDate(misReportInputDto.getFromDate());
			event.setToDate(misReportInputDto.getToDate());
			event.setGroupingCriteria(misReportInputDto.getGroupingCriteriaName());
			writer.setPageEvent(event);

			int selectedColsCount = misReportInputDto.getSelectedColumnIndexes().split(",").length;
			PdfPTable table;
			if(misReportInputDto.getGroupingCriteriaId() == 2) {
				table = new PdfPTable(selectedColsCount + 2);	
			} else if(misReportInputDto.getGroupingCriteriaId() == 4) {
				table = new PdfPTable(selectedColsCount + 3);	
			} else {
				table = new PdfPTable(selectedColsCount + 1);
			}
			
			table.setWidthPercentage(100);

			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, fontSize);
			Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, fontSize);
			PdfPCell hcell;

			if (misReportDataList != null && misReportDataList.size() > 0) {

				hcell = new PdfPCell(new Phrase(getGroupNameById(misReportInputDto.getGroupingCriteriaId()), headFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(hcell);

				if (misReportInputDto.getGroupingCriteriaId() == 2) {
					hcell = new PdfPCell(new Phrase("Circle Name", headFont));
					hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(hcell);
				}
				if (misReportInputDto.getGroupingCriteriaId() == 4) {
					hcell = new PdfPCell(new Phrase("Branch Code", headFont));
					hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(hcell);

					hcell = new PdfPCell(new Phrase("Vendor Name", headFont));
					hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(hcell);
				}
				
				for(String index : selectedColumnIndexList) {
					for(MISAvailableColumns column : columnListByGroupId) {
						if(index.equals(column.getColumnId())) {
							hcell = new PdfPCell(new Phrase(column.getColumnName(), headFont));
							hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
							table.addCell(hcell);
							selectedColumnNameList.add(column.getColumnName());
						}
					}
				}

				for (MISReportData misReportData : misReportDataList) {

					PdfPCell cell;
					cell = new PdfPCell(new Phrase(misReportData.getSelectedGroup(), dataFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);

					if (misReportInputDto.getGroupingCriteriaId() == 2) {
						cell = new PdfPCell(new Phrase(misReportData.getCircleName(), dataFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						table.addCell(cell);

					}
					if (misReportInputDto.getGroupingCriteriaId() == 4) {
						cell = new PdfPCell(new Phrase(misReportData.getBranchCode(), dataFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						table.addCell(cell);

						cell = new PdfPCell(new Phrase(misReportData.getVendorName(), dataFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
					}
					if (selectedColumnNameList.contains("Swayam Transactions")) {
						cell = new PdfPCell(new Phrase(misReportData.getSwayamTransaction() != null
								? misReportData.getSwayamTransaction().toString()
								: null, dataFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
					}
					if (selectedColumnNameList.contains("Branch Counter Transactions")) {
						cell = new PdfPCell(new Phrase(
								misReportData.getBranchCounter() != null ? misReportData.getBranchCounter().toString()
										: null,
								dataFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
					}
					if (selectedColumnNameList.contains("Migration %")) {
						cell = new PdfPCell(new Phrase(misReportData.getMigrationPercent() != null
								? misReportData.getMigrationPercent().toString()
								: null, dataFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
					}
					if (selectedColumnNameList.contains("Uptime %")) {
						cell = new PdfPCell(new Phrase(
								misReportData.getUptimePercent() != null ? misReportData.getUptimePercent().toString()
										: null,
								dataFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
					}
					if (selectedColumnNameList.contains("No. of kiosks")) {
						cell = new PdfPCell(new Phrase(
								misReportData.getNoOfKiosks() != null ? misReportData.getNoOfKiosks().toString() : null,
								dataFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
					}
					if (selectedColumnNameList.contains("Total downtime")) {
						cell = new PdfPCell(new Phrase(
								misReportData.getTotalDowntime() != null ? misReportData.getTotalDowntime().toString()
										: null,
								dataFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
					}
					if (selectedColumnNameList.contains("Onsite / Offsite")) {
						cell = new PdfPCell(new Phrase(misReportData.getOnSiteOffSite(), dataFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
					}
					if (selectedColumnNameList.contains("Installation Type")) {
						cell = new PdfPCell(new Phrase(misReportData.getStandaloneTTW(), dataFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
					}
					if (selectedColumnNameList.contains("No. of requests raised")) {
						cell = new PdfPCell(new Phrase(misReportData.getNoOfRequestRaised() != null
								? misReportData.getNoOfRequestRaised().toString()
								: null, dataFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
					}
					if (selectedColumnNameList.contains("Type of requests")) {
						cell = new PdfPCell(new Phrase(misReportData.getTypeOfRequest(), dataFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
					}
					if (selectedColumnNameList.contains("TAT of request completion")) {
						cell = new PdfPCell(new Phrase(misReportData.getTatOfRequestCompletion(), dataFont));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
					}
				}
			}
			document.open();
			document.add(table);
			document.close();

		} catch (DocumentException ex) {

			Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
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
