package sbi.kiosk.swayam.misreports.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
import sbi.kiosk.swayam.common.entity.MISReportData;
import sbi.kiosk.swayam.misreports.dto.MISReportInputDto;
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
			  String fromDate, String toDate, String groupingCriteriaId, String groupingCriteriaName, String selectedColumnIndexes,
			  String reportType, HttpServletResponse response) throws IOException {
	      	
			MISReportInputDto misReportInputDto = new MISReportInputDto();
			misReportInputDto.setFromDate(fromDate);
			misReportInputDto.setToDate(toDate);
			misReportInputDto.setGroupingCriteriaId(Integer.parseInt(groupingCriteriaId));
			misReportInputDto.setGroupingCriteriaName(groupingCriteriaName);
			misReportInputDto.setSelectedColumnIndexes(selectedColumnIndexes);
			misReportInputDto.setReportType(reportType);
			
			List<MISReportData> misReportDataList = (List<MISReportData>) misReportService.getMisReportData(misReportInputDto);
			ByteArrayInputStream bis = null;
			InputStreamResource resource = null;
			String fileName = "MIS_"+fromDate.replace("-", "").substring(0,6)+"_"
					+toDate.replace("-", "").substring(0,6)+"_"
					+(new SimpleDateFormat("dd-MM-yyyy")).format(new Date()).replace("-", "").substring(0,6);
			List<String> selectedColumnList = Arrays.asList(misReportInputDto.getSelectedColumnIndexes().split(","));
			if(reportType.equalsIgnoreCase("PDF")) {
				response.setContentType("application/pdf");
				
				//MIS_FromDate_ToDate_GeneratedDate.Ex: MIS_010920_020920_040920
		      	response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+".pdf\"");
		      	bis = GeneratePdfReport.getMisReport(misReportInputDto, misReportDataList, selectedColumnList);
				resource = new InputStreamResource(bis);
			}else if(reportType.equalsIgnoreCase("EXCEL")) {
				response.setContentType("application/vnd.ms-excel");
		      	response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+".xlsx\"");
		      	bis = GenerateExcelReport.getMisReport(misReportInputDto, misReportDataList, selectedColumnList);
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
	public ResponseEntity<List<MISAvailableColumns>> getMISAvailableColumns(String removeIds) {
		ResponseEntity<List<MISAvailableColumns>> respEntity = ResponseEntity.ok(misReportService.loadMISColumnsFromGroupId(removeIds));
		return respEntity;
	}
}
