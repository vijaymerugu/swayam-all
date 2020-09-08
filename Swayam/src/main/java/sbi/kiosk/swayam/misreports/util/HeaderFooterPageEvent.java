package sbi.kiosk.swayam.misreports.util;

/**
 * @author vmph2371481
 *
 */
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.Data;

@Data
public class HeaderFooterPageEvent extends PdfPageEventHelper {
	
	private String reportHeader;
	private String generatedOnDate;
	private String fromDate;
	private String toDate;
	private String groupingCriteria;

    public void onStartPage(PdfWriter writer, Document document) {
    	float headerFontSize = 12f;
    	Font reportHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, headerFontSize);
    	
    	float filtersFontSize = 8f;
    	Font reportfiltersFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, filtersFontSize);
    	
    	float generatedOnLeftMargin = document.left();
    	float fromDateLeftMargin = generatedOnLeftMargin + 130;
    	float toDateLeftMargin = fromDateLeftMargin + 130;
    	float groupingCriteriaLeftMargin = toDateLeftMargin + 130;
    	
        PdfContentByte cb = writer.getDirectContent();
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase(getReportHeader(), reportHeaderFont),
            (document.right() - document.left()) / 2 + document.leftMargin(),
            document.top() + 15, 0);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("Generated On: "+ getGeneratedOnDate(),reportfiltersFont),
        		generatedOnLeftMargin,
        		document.top() + 3, 0);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("From Date: "+ getFromDate(),reportfiltersFont),
        		fromDateLeftMargin,
        		document.top() + 3, 0);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("To Date: "+ getToDate(),reportfiltersFont),
        		toDateLeftMargin,
        		document.top() + 3, 0);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("Grouping Criteria: "+ getGroupingCriteria(),reportfiltersFont),
        		groupingCriteriaLeftMargin,
        		document.top() + 3, 0);
    }
}