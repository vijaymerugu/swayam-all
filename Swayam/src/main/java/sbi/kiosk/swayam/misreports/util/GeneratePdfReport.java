package sbi.kiosk.swayam.misreports.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

import sbi.kiosk.swayam.misreports.dto.MISReportInputDto;
import sbi.kiosk.swayam.misreports.dto.MISReportOutputDto;

/**
 * @author vmph2371481
 *
 */
public class GeneratePdfReport {
	
	public static ByteArrayInputStream getMisReport(MISReportInputDto misReportInputDto, List<MISReportOutputDto> misReportOutputDtoList) {

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
	        writer.setPageEvent(event);

	        int selectedColsCount = misReportInputDto.getSelectedColumnIndexes().split(",").length;
			PdfPTable table = new PdfPTable(selectedColsCount + 1);
			table.setWidthPercentage(100);

			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, fontSize);
			Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, fontSize);
			PdfPCell hcell;
			
			if(misReportOutputDtoList != null && misReportOutputDtoList.size() > 0) {
				
				if(misReportOutputDtoList.get(0).getSelectedGroup() != null) {
					hcell = new PdfPCell(new Phrase("Group Type", headFont));
					hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(hcell);
				}
				if(misReportOutputDtoList.get(0).getSwayamTransaction()!=null) {
					hcell = new PdfPCell(new Phrase("Swayam Transactions", headFont));
					hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(hcell);		
				}
				if(misReportOutputDtoList.get(0).getBranchCounter() != null) {
					hcell = new PdfPCell(new Phrase("Branch Counter Transactions", headFont));
					hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(hcell);		
				}
				if(misReportOutputDtoList.get(0).getMigrationPercent() != null) {
					hcell = new PdfPCell(new Phrase("Migration %", headFont));
					hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(hcell);		
				}
				if(misReportOutputDtoList.get(0).getUptimePercent() != null) {
					hcell = new PdfPCell(new Phrase("Uptime %", headFont));
					hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(hcell);		
				}
				if(misReportOutputDtoList.get(0).getNoOfKiosks() != null) {
					hcell = new PdfPCell(new Phrase("No. of kiosks", headFont));
					hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(hcell);		
				}
				if(misReportOutputDtoList.get(0).getTotalDowntime() != null) {
					hcell = new PdfPCell(new Phrase("Total downtime", headFont));
					hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(hcell);		
				}
				if(misReportOutputDtoList.get(0).getOnSiteOffSite() != null) {
					hcell = new PdfPCell(new Phrase("Onsite / Offsite", headFont));
					hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(hcell);		
				}
				if(misReportOutputDtoList.get(0).getStandaloneTTW() != null) {
					hcell = new PdfPCell(new Phrase("Standalone / TTW", headFont));
					hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(hcell);		
				}
				if(misReportOutputDtoList.get(0).getNoOfRequestRaised() != null) {
					hcell = new PdfPCell(new Phrase("No. of requests raised", headFont));
					hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(hcell);		
				}
				if(misReportOutputDtoList.get(0).getTypeOfRequest() != null) {
					hcell = new PdfPCell(new Phrase("Type of requests", headFont));
					hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(hcell);		
				}
				if(misReportOutputDtoList.get(0).getTatOfRequestCompletion() != null) {
					hcell = new PdfPCell(new Phrase("TAT of request completion", headFont));
					hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(hcell);		
				}
				
			for (MISReportOutputDto misReportOutputDto : misReportOutputDtoList) {

				PdfPCell cell;
				if(misReportOutputDto.getSelectedGroup() != null) {
					cell = new PdfPCell(new Phrase(misReportOutputDto.getSelectedGroup(), dataFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);		
				}
				if(misReportOutputDto.getSwayamTransaction() != null) {
					cell = new PdfPCell(new Phrase(misReportOutputDto.getSwayamTransaction(), dataFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);		
				}
				if(misReportOutputDto.getBranchCounter() != null) {
					cell = new PdfPCell(new Phrase(misReportOutputDto.getBranchCounter(), dataFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);		
				}
				if(misReportOutputDto.getMigrationPercent() != null) {
					cell = new PdfPCell(new Phrase(misReportOutputDto.getMigrationPercent().toString(), dataFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);		
				}
				if(misReportOutputDto.getUptimePercent() != null) {
					cell = new PdfPCell(new Phrase(misReportOutputDto.getUptimePercent().toString(), dataFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);		
				}
				if(misReportOutputDto.getNoOfKiosks() != null) {
					cell = new PdfPCell(new Phrase(misReportOutputDto.getNoOfKiosks().toString(), dataFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);		
				}
				if(misReportOutputDto.getTotalDowntime() != null) {
					cell = new PdfPCell(new Phrase(misReportOutputDto.getTotalDowntime(), dataFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);	
				}
				if(misReportOutputDto.getOnSiteOffSite() != null) {
					cell = new PdfPCell(new Phrase(misReportOutputDto.getOnSiteOffSite(), dataFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);	
				}
				if(misReportOutputDto.getStandaloneTTW() != null) {
					cell = new PdfPCell(new Phrase(misReportOutputDto.getStandaloneTTW(), dataFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);			
				}
				if(misReportOutputDto.getNoOfRequestRaised() != null) {
					cell = new PdfPCell(new Phrase(misReportOutputDto.getNoOfRequestRaised().toString(), dataFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
				}
				if(misReportOutputDto.getTypeOfRequest() != null) {
					cell = new PdfPCell(new Phrase(misReportOutputDto.getTypeOfRequest(), dataFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);		
				}
				if(misReportOutputDto.getTatOfRequestCompletion() != null) {
					cell = new PdfPCell(new Phrase(misReportOutputDto.getTatOfRequestCompletion(), dataFont));
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
}
