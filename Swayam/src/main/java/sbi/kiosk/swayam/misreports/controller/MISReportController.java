package sbi.kiosk.swayam.misreports.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.common.entity.MISAvailableColumns;
import sbi.kiosk.swayam.common.entity.MISGroupingCriteria;
import sbi.kiosk.swayam.misreports.dto.MISReportInputDto;
import sbi.kiosk.swayam.misreports.dto.MISReportOutputDto;
import sbi.kiosk.swayam.misreports.service.MISReporterService;
import sbi.kiosk.swayam.misreports.util.GenerateExcelReport;
import sbi.kiosk.swayam.misreports.util.GeneratePdfReport;

/**
 * @author vmph2371481
 *
 */
@RestController
public class MISReportController {
	
	@Autowired
	MISReporterService misReportService;

	@RequestMapping(value = "mis/generate-report", method = RequestMethod.GET)
	  public InputStreamResource getMisReportData (
			  String fromDate, String toDate, String groupingCriteria, String selectedColumnIndexes,
			  String reportType, HttpServletResponse response) throws IOException {
	      	
			MISReportInputDto misReportInputDto = new MISReportInputDto();
			misReportInputDto.setFromDate(fromDate);
			misReportInputDto.setToDate(toDate);
			misReportInputDto.setGroupingCriteria(Integer.parseInt(groupingCriteria));
			misReportInputDto.setSelectedColumnIndexes(selectedColumnIndexes);
			misReportInputDto.setReportType(reportType);
			
			List<MISReportOutputDto> misReportOutputDto = (List<MISReportOutputDto>) misReportService.getMisReportData(misReportInputDto);
			ByteArrayInputStream bis = null;
			InputStreamResource resource = null;
			if(reportType.equalsIgnoreCase("PDF")) {
				response.setContentType("application/pdf");
		      	response.setHeader("Content-Disposition", "attachment; filename=\"MIS Report.pdf\"");
		      	bis = GeneratePdfReport.getMisReport(misReportInputDto, misReportOutputDto);
				resource = new InputStreamResource(bis);
			}else if(reportType.equalsIgnoreCase("EXCEL")) {
				response.setContentType("application/vnd.ms-excel");
		      	response.setHeader("Content-Disposition", "attachment; filename=\"MIS Report.xlsx\"");
		      	bis = GenerateExcelReport.getMisReport(misReportInputDto, misReportOutputDto);
				resource = new InputStreamResource(bis);
			}
			
			return resource;
	  }
	
	@RequestMapping(value = "mis/get-grouping-criteria", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<MISGroupingCriteria>> getMISGroupingCriteria(ModelAndView model) {
		ResponseEntity<List<MISGroupingCriteria>> respEntity = ResponseEntity.ok(misReportService.getMISGroupingCriteria());
		return respEntity;
	}
	
	@RequestMapping(value = "mis/get-available-columns", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<MISAvailableColumns>> getMISAvailableColumns(ModelAndView model) {
		ResponseEntity<List<MISAvailableColumns>> respEntity = ResponseEntity.ok(misReportService.getMISAvailableColumns());
		return respEntity;
	}
}
