package sbi.kiosk.swayam.misreports.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.billingpayment.controller.RfpController;
import sbi.kiosk.swayam.common.entity.MISAvailableColumns;
import sbi.kiosk.swayam.common.entity.MISGroupingCriteria;
import sbi.kiosk.swayam.common.entity.MISReportData;
import sbi.kiosk.swayam.healthmonitoring.model.RfpResponse;
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
	
	Logger logger =LoggerFactory.getLogger(MISReportController.class);
	
	@Autowired
	MISReporterService misReportService;
	
	
	@RequestMapping(value = "mis/generate-report", method = RequestMethod.POST)
	  public ResponseEntity<?> getMisReportData (@RequestBody MISReportInputDto misReportInputDto, HttpServletResponse response) throws IOException, ParseException {
	      	
			/*
			 * MISReportInputDto misReportInputDto = new MISReportInputDto();
			 * misReportInputDto.setFromDate(fromDate); misReportInputDto.setToDate(toDate);
			 * misReportInputDto.setGroupingCriteriaId(Integer.parseInt(groupingCriteriaId))
			 * ; misReportInputDto.setGroupingCriteriaName(groupingCriteriaName);
			 * misReportInputDto.setSelectedColumnIndexes(selectedColumnIndexes);
			 * misReportInputDto.setReportType(reportType);
			 * 
			 */

			/*
			 * System.out.println("From Date " + misReportInputDto.getFromDate());
			 * System.out.println("To Date " + misReportInputDto.getToDate());
			 * System.out.println("groupingCriteriaId " +
			 * misReportInputDto.getGroupingCriteriaId());
			 * System.out.println("groupingCriteriaName " +
			 * misReportInputDto.getGroupingCriteriaName());
			 * System.out.println("selectedColumnIndexes " +
			 * misReportInputDto.getSelectedColumnIndexes());
			 * System.out.println("reportType " + misReportInputDto.getReportType());
			 * System.out.println("Remove id " + misReportInputDto.getRemoveIds());
			 */
			
			int status=0;
			
			
			Date datefrom = new SimpleDateFormat("dd-MM-yyyy").parse(misReportInputDto.getFromDate());
			Date dateto = new SimpleDateFormat("dd-MM-yyyy").parse(misReportInputDto.getToDate());
			System.out.println("From Date " + datefrom);
			System.out.println("To Date " + dateto);

			if (datefrom.compareTo(dateto) <= 0) {

//					System.out.println("Inside From Date " + datefrom);
//					System.out.println("Inside To Date " + dateto);

				logger.info("fromDate and toDate validaton success");
			} else {
				status = 1;
			}

			if (misReportInputDto.getGroupingCriteriaId() == 1) {
				// hide 7-Onsite / Offsite, 8-Standalone / TTW, 10-Type of requests

				if (misReportInputDto.getRemoveIds().equalsIgnoreCase("7,8,10")) {
					logger.info("Inside group criteria " + misReportInputDto.getGroupingCriteriaId() + " and remove id "
							+ misReportInputDto.getRemoveIds());

				} else {
					status = 1;
				}

			} else if (misReportInputDto.getGroupingCriteriaId() == 2) {
				// hide 7-Onsite / Offsite, 8-Standalone / TTW, 10-Type of requests

				if (misReportInputDto.getRemoveIds().equalsIgnoreCase("7,8,10")) {
					logger.info("Inside group criteria " + misReportInputDto.getGroupingCriteriaId() + " and remove id "
							+ misReportInputDto.getRemoveIds());

				} else {
					status = 1;
				}
			} else if (misReportInputDto.getGroupingCriteriaId() == 3) {
				// hide 2-Branch Counter Transactions, 3-Migration %, 7-Onsite / Offsite,
				// 8-Standalone / TTW, 10-Type of requests, 11-TAT of request completion.

				if (misReportInputDto.getRemoveIds().equalsIgnoreCase("2,3,7,8,10,11")) {
					logger.info("Inside group criteria " + misReportInputDto.getGroupingCriteriaId() + " and remove id "
							+ misReportInputDto.getRemoveIds());

				} else {
					status = 1;
				}
			} else if (misReportInputDto.getGroupingCriteriaId() == 4) {
				// hide 2-Branch Counter Transactions, 3-Migration %, 5-No. of kiosks, 10-Type
				// of requests

				if (misReportInputDto.getRemoveIds().equalsIgnoreCase("2,3,5,10")) {
					logger.info("Inside group criteria " + misReportInputDto.getGroupingCriteriaId() + " and remove id "
							+ misReportInputDto.getRemoveIds());

				} else {
					status = 1;
				}
			} else {

				logger.info("Need to Select valid group criteria");
				status = 1;
			}
			
			logger.info("Status "+ status);
			
		
			ByteArrayInputStream bis = null;
			InputStreamResource resource = null;
			
			
			if(status==0) {
				
				List<MISReportData> misReportDataList = (List<MISReportData>) misReportService
						.getMisReportData(misReportInputDto);

				/*
				 * List<MISReportData> misReportDataList = (List<MISReportData>)
				 * misReportService .getMisReportData(misReportInputDto); ByteArrayInputStream
				 * bis = null; InputStreamResource resource = null;
				 */
			String fileName = "MIS_" + misReportInputDto.getFromDate().replace("-", "").substring(0, 6) + "_"
					+ misReportInputDto.getToDate().replace("-", "").substring(0, 6) + "_"
					+ (new SimpleDateFormat("dd-MM-yyyy")).format(new Date()).replace("-", "").substring(0, 6);
			List<String> selectedColumnIndexList = Arrays
					.asList(misReportInputDto.getSelectedColumnIndexes().split(","));
			List<MISAvailableColumns> columnListByGroupId = misReportService
					.loadMISColumnsFromGroupId(misReportInputDto.getRemoveIds());
			if (misReportInputDto.getReportType().equalsIgnoreCase("PDF")) {
				response.setContentType("application/pdf");

				// MIS_FromDate_ToDate_GeneratedDate.Ex: MIS_010920_020920_040920
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".pdf\"");
				bis = GeneratePdfReport.getMisReport(misReportInputDto, misReportDataList, selectedColumnIndexList,
						columnListByGroupId);
				resource = new InputStreamResource(bis);
			} else if (misReportInputDto.getReportType().equalsIgnoreCase("EXCEL")) {
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".xlsx\"");
				bis = GenerateExcelReport.getMisReport(misReportInputDto, misReportDataList, selectedColumnIndexList,
						columnListByGroupId);
				resource = new InputStreamResource(bis);
			}
			
			}else {
				
				logger.error("Server side validation failed");
				return ResponseEntity.ok("Server side validation failed");
			}

			 return ResponseEntity.ok(resource);
		}
	
	
	
//	@RequestMapping(value = "mis/generate-report", method = RequestMethod.POST)
//	  public InputStreamResource getMisReportData (@RequestBody MISReportInputDto misReportInputDto, HttpServletResponse response) throws IOException, ParseException {
//	      	
//			/*
//			 * MISReportInputDto misReportInputDto = new MISReportInputDto();
//			 * misReportInputDto.setFromDate(fromDate); misReportInputDto.setToDate(toDate);
//			 * misReportInputDto.setGroupingCriteriaId(Integer.parseInt(groupingCriteriaId))
//			 * ; misReportInputDto.setGroupingCriteriaName(groupingCriteriaName);
//			 * misReportInputDto.setSelectedColumnIndexes(selectedColumnIndexes);
//			 * misReportInputDto.setReportType(reportType);
//			 * 
//			 */
//
//			/*
//			 * System.out.println("From Date " + misReportInputDto.getFromDate());
//			 * System.out.println("To Date " + misReportInputDto.getToDate());
//			 * System.out.println("groupingCriteriaId " +
//			 * misReportInputDto.getGroupingCriteriaId());
//			 * System.out.println("groupingCriteriaName " +
//			 * misReportInputDto.getGroupingCriteriaName());
//			 * System.out.println("selectedColumnIndexes " +
//			 * misReportInputDto.getSelectedColumnIndexes());
//			 * System.out.println("reportType " + misReportInputDto.getReportType());
//			 * System.out.println("Remove id " + misReportInputDto.getRemoveIds());
//			 */
//			
//			int status=0;
//			
//			
//			Date datefrom = new SimpleDateFormat("dd-MM-yyyy").parse(misReportInputDto.getFromDate());
//			Date dateto = new SimpleDateFormat("dd-MM-yyyy").parse(misReportInputDto.getToDate());
//			System.out.println("From Date " + datefrom);
//			System.out.println("To Date " + dateto);
//
//			if (datefrom.compareTo(dateto) <= 0) {
//
////					System.out.println("Inside From Date " + datefrom);
////					System.out.println("Inside To Date " + dateto);
//
//				logger.info("fromDate and toDate validaton success");
//			} else {
//				status = 1;
//			}
//
//			if (misReportInputDto.getGroupingCriteriaId() == 1) {
//				// hide 7-Onsite / Offsite, 8-Standalone / TTW, 10-Type of requests
//
//				if (misReportInputDto.getRemoveIds().equalsIgnoreCase("7,8,10")) {
//					logger.info("Inside group criteria " + misReportInputDto.getGroupingCriteriaId() + " and remove id "
//							+ misReportInputDto.getRemoveIds());
//
//				} else {
//					status = 1;
//				}
//
//			} else if (misReportInputDto.getGroupingCriteriaId() == 2) {
//				// hide 7-Onsite / Offsite, 8-Standalone / TTW, 10-Type of requests
//
//				if (misReportInputDto.getRemoveIds().equalsIgnoreCase("7,8,10")) {
//					logger.info("Inside group criteria " + misReportInputDto.getGroupingCriteriaId() + " and remove id "
//							+ misReportInputDto.getRemoveIds());
//
//				} else {
//					status = 1;
//				}
//			} else if (misReportInputDto.getGroupingCriteriaId() == 3) {
//				// hide 2-Branch Counter Transactions, 3-Migration %, 7-Onsite / Offsite,
//				// 8-Standalone / TTW, 10-Type of requests, 11-TAT of request completion.
//
//				if (misReportInputDto.getRemoveIds().equalsIgnoreCase("2,3,7,8,10,11")) {
//					logger.info("Inside group criteria " + misReportInputDto.getGroupingCriteriaId() + " and remove id "
//							+ misReportInputDto.getRemoveIds());
//
//				} else {
//					status = 1;
//				}
//			} else if (misReportInputDto.getGroupingCriteriaId() == 4) {
//				// hide 2-Branch Counter Transactions, 3-Migration %, 5-No. of kiosks, 10-Type
//				// of requests
//
//				if (misReportInputDto.getRemoveIds().equalsIgnoreCase("2,3,5,10")) {
//					logger.info("Inside group criteria " + misReportInputDto.getGroupingCriteriaId() + " and remove id "
//							+ misReportInputDto.getRemoveIds());
//
//				} else {
//					status = 1;
//				}
//			} else {
//
//				logger.info("Need to Select valid group criteria");
//				status = 1;
//			}
//			
//			logger.info("Status "+ status);
//			
//		
//			ByteArrayInputStream bis = null;
//			InputStreamResource resource = null;
//			
//			
//			if(status==0) {
//				
//				List<MISReportData> misReportDataList = (List<MISReportData>) misReportService
//						.getMisReportData(misReportInputDto);
//
//				/*
//				 * List<MISReportData> misReportDataList = (List<MISReportData>)
//				 * misReportService .getMisReportData(misReportInputDto); ByteArrayInputStream
//				 * bis = null; InputStreamResource resource = null;
//				 */
//			String fileName = "MIS_" + misReportInputDto.getFromDate().replace("-", "").substring(0, 6) + "_"
//					+ misReportInputDto.getToDate().replace("-", "").substring(0, 6) + "_"
//					+ (new SimpleDateFormat("dd-MM-yyyy")).format(new Date()).replace("-", "").substring(0, 6);
//			List<String> selectedColumnIndexList = Arrays
//					.asList(misReportInputDto.getSelectedColumnIndexes().split(","));
//			List<MISAvailableColumns> columnListByGroupId = misReportService
//					.loadMISColumnsFromGroupId(misReportInputDto.getRemoveIds());
//			if (misReportInputDto.getReportType().equalsIgnoreCase("PDF")) {
//				response.setContentType("application/pdf");
//
//				// MIS_FromDate_ToDate_GeneratedDate.Ex: MIS_010920_020920_040920
//				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".pdf\"");
//				bis = GeneratePdfReport.getMisReport(misReportInputDto, misReportDataList, selectedColumnIndexList,
//						columnListByGroupId);
//				resource = new InputStreamResource(bis);
//			} else if (misReportInputDto.getReportType().equalsIgnoreCase("EXCEL")) {
//				response.setContentType("application/vnd.ms-excel");
//				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".xlsx\"");
//				bis = GenerateExcelReport.getMisReport(misReportInputDto, misReportDataList, selectedColumnIndexList,
//						columnListByGroupId);
//				resource = new InputStreamResource(bis);
//			}
//			
//			}else {
//				
//				logger.error("Server side validation failed");
//				return resource;
//			}
//
//			return resource;
//		}
	
	

	/*
	 * @RequestMapping(value = "mis/generate-report", method = RequestMethod.GET)
	 * public InputStreamResource getMisReportData ( String fromDate, String toDate,
	 * String groupingCriteriaId, String groupingCriteriaName, String
	 * selectedColumnIndexes, String reportType, String removeIds,
	 * HttpServletResponse response) throws IOException {
	 * 
	 * MISReportInputDto misReportInputDto = new MISReportInputDto();
	 * misReportInputDto.setFromDate(fromDate); misReportInputDto.setToDate(toDate);
	 * misReportInputDto.setGroupingCriteriaId(Integer.parseInt(groupingCriteriaId))
	 * ; misReportInputDto.setGroupingCriteriaName(groupingCriteriaName);
	 * misReportInputDto.setSelectedColumnIndexes(selectedColumnIndexes);
	 * misReportInputDto.setReportType(reportType);
	 * 
	 * List<MISReportData> misReportDataList = (List<MISReportData>)
	 * misReportService.getMisReportData(misReportInputDto); ByteArrayInputStream
	 * bis = null; InputStreamResource resource = null; String fileName =
	 * "MIS_"+fromDate.replace("-", "").substring(0,6)+"_" +toDate.replace("-",
	 * "").substring(0,6)+"_" +(new SimpleDateFormat("dd-MM-yyyy")).format(new
	 * Date()).replace("-", "").substring(0,6); List<String> selectedColumnIndexList
	 * = Arrays.asList(misReportInputDto.getSelectedColumnIndexes().split(","));
	 * List<MISAvailableColumns> columnListByGroupId =
	 * misReportService.loadMISColumnsFromGroupId(removeIds);
	 * if(reportType.equalsIgnoreCase("PDF")) {
	 * response.setContentType("application/pdf");
	 * 
	 * //MIS_FromDate_ToDate_GeneratedDate.Ex: MIS_010920_020920_040920
	 * response.setHeader("Content-Disposition",
	 * "attachment; filename=\""+fileName+".pdf\""); bis =
	 * GeneratePdfReport.getMisReport(misReportInputDto, misReportDataList,
	 * selectedColumnIndexList, columnListByGroupId); resource = new
	 * InputStreamResource(bis); }else if(reportType.equalsIgnoreCase("EXCEL")) {
	 * response.setContentType("application/vnd.ms-excel");
	 * response.setHeader("Content-Disposition",
	 * "attachment; filename=\""+fileName+".xlsx\""); bis =
	 * GenerateExcelReport.getMisReport(misReportInputDto, misReportDataList,
	 * selectedColumnIndexList, columnListByGroupId); resource = new
	 * InputStreamResource(bis); }
	 * 
	 * return resource; }
	 */
	
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
