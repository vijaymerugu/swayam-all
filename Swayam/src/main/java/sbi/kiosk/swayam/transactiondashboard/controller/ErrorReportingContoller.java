package sbi.kiosk.swayam.transactiondashboard.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import sbi.kiosk.swayam.common.constants.ExceptionConstants;
import sbi.kiosk.swayam.common.dto.SwayamTxnDailyDto;
import sbi.kiosk.swayam.common.entity.Circle;
import sbi.kiosk.swayam.common.entity.DateFrame;
import sbi.kiosk.swayam.common.entity.DrillDown;
import sbi.kiosk.swayam.common.entity.ErrorReporting;
import sbi.kiosk.swayam.common.entity.ErrorReportingDrillDown;
import sbi.kiosk.swayam.common.validation.ValidationCommon;
import sbi.kiosk.swayam.transactiondashboard.service.ErrorReportingService;

@RestController
public class ErrorReportingContoller {

	
Logger logger = LoggerFactory.getLogger(ErrorReportingContoller.class);
	
	@Autowired
	ErrorReportingService errorReportingService;

	@Autowired
	DateFrame dateFrame;
	
	@RequestMapping("td/errorReporting")
	public ModelAndView errorReporting() {
		logger.info("errorReporting==========");
		ModelAndView mav = new ModelAndView("errorReporting");
		return mav;
	}
	
	@RequestMapping("td/errorSummaryDrillDownNoOfTechFailCC")
	public ModelAndView errorSummaryDrillDownTechFailCircleCode(ModelAndView model,@RequestParam("code") String code,HttpSession session, HttpServletRequest request) {
		logger.info("errorSummaryDrillDownTechFailCircleCode=====circleName====="+code);
		
		String fromDate = ValidationCommon.validateString(request.getParameter("fromDate"));
		String toDate = ValidationCommon.validateString(request.getParameter("toDate"));
		model.addObject("circleName", code);
		model.addObject("fromDate", fromDate);
		model.addObject("toDate", toDate);
		List<SwayamTxnDailyDto> swayamTxnList = errorReportingService.findByCircleCodeErrorCount(code,fromDate,toDate);
		//ModelAndView mav = new ModelAndView("ErrorSummary");
		logger.info("swayamTxnList===1111111111111==Controller====="+swayamTxnList.size());
		//for(SwayamTxnDailyDto swayamTxnDailyDto:swayamTxnList) {
			
			//model.addObject(swayamTxnDailyDto);
			//model.addAttribute("errorCode", swayamTxnDailyDto.getErrorCode());
		  // model.addAttribute("errorDesc", swayamTxnDailyDto);
		
				//HashMap<String,String> map=new HashMap<>();
		//map.put("errorCode", "Satendra");
		//////logger.info("map=====Controller====="+map);
		model.addObject("swayamTxnList", swayamTxnList);

	   model.setViewName("ErrorSummary");
	    
		//}
		return model;
	}
	
		
	@RequestMapping(value = "td/getcircle1", method = RequestMethod.GET)
	public ResponseEntity<?> getCircles(@RequestParam("code") String code,@RequestParam("fromDate") String fromDate,@RequestParam("toDate") String toDate){
		logger.info("circles ==================================================="+fromDate);
		logger.info("circles =================code=================================="+code);
		//circleRepo.findAll();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		ResponseEntity<?>resp=null;
		 String json =null;
		try {
		//String fromDate = ValidationCommon.validateString(request.getParameter("fromDate"));
		//String toDate = ValidationCommon.validateString(request.getParameter("toDate"));
		List<SwayamTxnDailyDto> swayamTxnList = errorReportingService.findByCircleCodeErrorCount(code,toDate,fromDate);
		//ModelAndView mav = new ModelAndView("ErrorSummary");
		logger.info("swayamTxnList=====Controller====="+swayamTxnList.size());
	//	for(SwayamTxnDailyDto swayamTxnDailyDto:swayamTxnList) {
			
			//model.addObject(swayamTxnDailyDto);
			//mav.addAttribute("errorCode", swayamTxnDailyDto.getErrorCode());
			//mav.addAttribute("errorDesc", swayamTxnDailyDto);
		SwayamTxnDailyDto dto=new SwayamTxnDailyDto();
		dto.setErrorCode("SBI0234");
		dto.setErrorDesc("Satendra Not Found");
		  json = gson.toJson(dto);
		logger.info("circles "+ json);
		
		 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return resp.ok(json);
		
		
	}
	
	
	@RequestMapping("td/errorSummaryDrillDownNoOfBussinesFailCC")
	public ModelAndView errorSummaryDrillDownNoOfBussinesFailCircleCode(ModelAndView model,@RequestParam("code") String code,HttpSession session, HttpServletRequest request) {
		logger.info("errorSummaryDrillDownNoOfBussinesFail=====circleName====="+code);
		
		String fromDate = ValidationCommon.validateString(request.getParameter("fromDate"));
		String toDate = ValidationCommon.validateString(request.getParameter("toDate"));
		model.addObject("circleName", code);
		model.addObject("fromDate", fromDate);
		model.addObject("toDate", toDate);
		List<SwayamTxnDailyDto> swayamTxnList = errorReportingService.findByCircleCodeErrorCountBussTxnFail(code,fromDate,toDate);
		//ModelAndView mav = new ModelAndView("ErrorSummary");
		logger.info("swayamTxnList=====Controller====="+swayamTxnList.size());
		model.addObject("swayamTxnList", swayamTxnList);
		model.setViewName("ErrorSummary");
		return model;
	}
	
	
	@RequestMapping("td/errorRepoNoOfTechFailDrillDownNW")
	public ModelAndView errorRepoNoOfTechFailDrillDownNW(ModelAndView model, @RequestParam("networkCode") String networkCode,HttpSession session, HttpServletRequest request) {
		
		try {
		
			logger.info("errorRepoNoOfTechFailDrillDownNW=====networkCode====="+networkCode);
			
			//String code = ValidationCommon.validateString(request.getParameter("code"));	
			//String networkName = ValidationCommon.validateString(request.getParameter("code"));
			//logger.info("networkName::::::::"+networkName);
			String fromDate = ValidationCommon.validateString(request.getParameter("fromDate"));
			String toDate = ValidationCommon.validateString(request.getParameter("toDate"));
			
			//model.addObject("circleName", circleName);
			model.addObject("networCode", networkCode);
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);
			
			List<SwayamTxnDailyDto> swayamTxnList = errorReportingService.findByNCodeErrorCount(networkCode,fromDate,toDate);
			//ModelAndView mav = new ModelAndView("ErrorSummary");
			logger.info("swayamTxnList=====Controller====="+swayamTxnList.size());
			if(swayamTxnList!=null && !swayamTxnList.isEmpty() && swayamTxnList.size()>0) {
			model.addObject("swayamTxnList", swayamTxnList);
			}
			model.setViewName("ErrorSummary");
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	
	
	
	@RequestMapping("td/errorRepoNoOfBussFailDrillDownNW")
	public ModelAndView errorRepoNoOfBussFailDrillDownNW(ModelAndView model, @RequestParam("networkCode") String networkCode,HttpSession session, HttpServletRequest request) {
		
		try {
		
			logger.info("errorRepoNoOfBussFailDrillDownNW=====networkCode====="+networkCode);
			
			//String code = ValidationCommon.validateString(request.getParameter("code"));	
			//String networkName = ValidationCommon.validateString(request.getParameter("code"));
			//logger.info("networkName::::::::"+networkName);
			String fromDate = ValidationCommon.validateString(request.getParameter("fromDate"));
			String toDate = ValidationCommon.validateString(request.getParameter("toDate"));
			
			//model.addObject("circleName", circleName);
			model.addObject("networkCode", networkCode);
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);
			
			List<SwayamTxnDailyDto> swayamTxnList = errorReportingService.findByNCodeErrorCount(networkCode,fromDate,toDate);
			//ModelAndView mav = new ModelAndView("ErrorSummary");
			logger.info("swayamTxnList=====Controller====="+swayamTxnList.size());
			if(swayamTxnList!=null && !swayamTxnList.isEmpty() && swayamTxnList.size()>0) {
			model.addObject("swayamTxnList", swayamTxnList);
			}
			model.setViewName("ErrorSummary");
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	
	
	

	
	
	@RequestMapping("td/errorRepoNoOfTechFailDrillDownMC")
	public ModelAndView errorRepoNoOfTechFailDrillDownMC(ModelAndView model, @RequestParam("moduleCode") String moduleCode,HttpSession session, HttpServletRequest request) {
		
		try {
		
			logger.info("errorSummaryDrillDownNoOfBussinesFail=====moduleCode====="+moduleCode);
			
			//String code = ValidationCommon.validateString(request.getParameter("code"));	
			//String networkName = ValidationCommon.validateString(request.getParameter("code"));
			//logger.info("networkName::::::::"+networkName);
			String fromDate = ValidationCommon.validateString(request.getParameter("fromDate"));
			String toDate = ValidationCommon.validateString(request.getParameter("toDate"));
			
			//model.addObject("circleName", circleName);
			model.addObject("moduleCode", moduleCode);
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);
			
			List<SwayamTxnDailyDto> swayamTxnList = errorReportingService.findByMCodeTechFailErrorCount(moduleCode,fromDate,toDate);
			//ModelAndView mav = new ModelAndView("ErrorSummary");
			logger.info("swayamTxnList=====Controller====="+swayamTxnList.size());
			if(swayamTxnList!=null && !swayamTxnList.isEmpty() && swayamTxnList.size()>0) {
			model.addObject("swayamTxnList", swayamTxnList);
			}
			model.setViewName("ErrorSummary");
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	
	

	
	
	
	@RequestMapping("td/errorSummaryDrillDownNoOfBussinesFailRC")
	public ModelAndView errorSummaryDrillDownNoOfBussinesFailRC(ModelAndView model, @RequestParam("regionName") String regionName,HttpSession session, HttpServletRequest request) {
		
		try {
		
			logger.info("errorSummaryDrillDownNoOfBussinesFailRegionName=====regionName====="+regionName);
			
			//String code = ValidationCommon.validateString(request.getParameter("code"));	
			//String networkName = ValidationCommon.validateString(request.getParameter("code"));
			//logger.info("networkName::::::::"+networkName);
			String fromDate = ValidationCommon.validateString(request.getParameter("fromDate"));
			String toDate = ValidationCommon.validateString(request.getParameter("toDate"));
			
			//model.addObject("circleName", circleName);
			model.addObject("regionName", regionName);
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);
			
			List<SwayamTxnDailyDto> swayamTxnList = errorReportingService.findByRegionCodeBussFailErrorCount(regionName,fromDate,toDate);
			//ModelAndView mav = new ModelAndView("ErrorSummary");
			logger.info("swayamTxnList=====Controller====="+swayamTxnList.size());
			if(swayamTxnList!=null && !swayamTxnList.isEmpty() && swayamTxnList.size()>0) {
			model.addObject("swayamTxnList", swayamTxnList);
			}
			model.setViewName("ErrorSummary");
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}

	
		
	@RequestMapping("td/errorSummaryDrillDownNoOfTechFailRegionName")
	public ModelAndView errorSummaryDrillDownNoOfTechFailRegionName(ModelAndView model, @RequestParam("regionName") String regionName,HttpSession session, HttpServletRequest request) {
		
		try {
		
			logger.info("errorSummaryDrillDownNoOfTechFailRegionName=====regionName====="+regionName);
			
			//String code = ValidationCommon.validateString(request.getParameter("code"));	
			//String networkName = ValidationCommon.validateString(request.getParameter("code"));
			//logger.info("networkName::::::::"+networkName);
			String fromDate = ValidationCommon.validateString(request.getParameter("fromDate"));
			String toDate = ValidationCommon.validateString(request.getParameter("toDate"));
			
			//model.addObject("circleName", circleName);
			model.addObject("regionName", regionName);
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);
			
			List<SwayamTxnDailyDto> swayamTxnList = errorReportingService.findByRegionCodeTechFailErrorCount(regionName,fromDate,toDate);
			//ModelAndView mav = new ModelAndView("ErrorSummary");
			logger.info("swayamTxnList=====Controller====="+swayamTxnList.size());
			if(swayamTxnList!=null && !swayamTxnList.isEmpty() && swayamTxnList.size()>0) {
			model.addObject("swayamTxnList", swayamTxnList);
			}
			model.setViewName("ErrorSummary");
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}

	// Error Reporting DrillDown
	
	@RequestMapping("td/errorDrillDownNetwork")
	public ModelAndView drillDownNetwork(ModelAndView model, HttpSession session, HttpServletRequest request) {
		
		try {
			
			
			String circleName = ValidationCommon.validateString(request.getParameter("circleName"));
			String fromDate = ValidationCommon.validateString(request.getParameter("fromDate"));
			String toDate = ValidationCommon.validateString(request.getParameter("toDate"));
			
			model.addObject("circleName", circleName);
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);
			
			model.setViewName("errorDrillDownNetwork");
			
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	
	
	@RequestMapping("td/errorRepoDrillDownModule")
	public ModelAndView drillDownModule(ModelAndView model, HttpSession session, HttpServletRequest request) {
		
		try {
		
			
			String circleName = ValidationCommon.validateString(request.getParameter("circleName"));	
			String networkName = ValidationCommon.validateString(request.getParameter("networkName"));
			String fromDate = ValidationCommon.validateString(request.getParameter("fromDate"));
			String toDate = ValidationCommon.validateString(request.getParameter("toDate"));
			
			model.addObject("circleName", circleName);
			model.addObject("networkName", networkName);
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);
			
			model.setViewName("errorDrillDownModule");
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	

	@RequestMapping("td/errorRepodrillDownRegion")
	public ModelAndView drillDownRegion(ModelAndView model, HttpSession session, HttpServletRequest request) {
		
		try {
			
		
			String circleName = ValidationCommon.validateString(request.getParameter("circleName"));	
			String networkName = ValidationCommon.validateString(request.getParameter("networkName"));
            String moduleName = ValidationCommon.validateString(request.getParameter("moduleName"));
            String fromDate = ValidationCommon.validateString(request.getParameter("fromDate"));
			String toDate = ValidationCommon.validateString(request.getParameter("toDate"));
                     
            model.addObject("circleName", circleName);
			model.addObject("networkName", networkName);
			model.addObject("moduleName", moduleName);
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);
			
			model.setViewName("errorDrillDownRegion");
			
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	

	
	@RequestMapping("td/errorDrillDownBranch")
	public ModelAndView drillDownBranch(ModelAndView model, HttpSession session, HttpServletRequest request) {
		
		try {
			
			String circleName = ValidationCommon.validateString(request.getParameter("circleName"));	
			String networkName = ValidationCommon.validateString(request.getParameter("networkName"));
            String moduleName = ValidationCommon.validateString(request.getParameter("moduleName"));
            String regionName = ValidationCommon.validateString(request.getParameter("regionName"));
            String fromDate = ValidationCommon.validateString(request.getParameter("fromDate"));
			String toDate = ValidationCommon.validateString(request.getParameter("toDate"));
			
            model.addObject("circleName", circleName);
			model.addObject("networkName", networkName);
			model.addObject("moduleName", moduleName);
			model.addObject("regionName", regionName);
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);
						
			model.setViewName("errorDrillDownBranch");
			
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
		return model;
	}
	
	
	
	
	@RequestMapping(value = "td/errorRepod/get", params = { "page", "size", "fromDate",	"toDate" }, method = RequestMethod.GET, produces = "application/json")
	public Page<ErrorReporting> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size,@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
		
		Page<ErrorReporting> resultPage = errorReportingService.findPaginated(page, size, fromDate,toDate);
		
		
		  dateFrame.setFromDate(fromDate); dateFrame.setToDate(toDate);
		  
	/*	  logger.info("Inside ZeroTransactionKiosksController From date from jsp: "
		  +dateFrame.getFromDate());
		  logger.info("Inside ZeroTransactionKiosksController To date from jsp: "
		  +dateFrame.getToDate()); 
		 
		  
		logger.info("resultPage==" + resultPage.getNumberOfElements()); */
		if (page > resultPage.getTotalPages()) {
			// throw new MyResourceNotFoundException();
		}

		return resultPage;
	}
	
	@RequestMapping(value = "errorRepoDrillDown/get", params = { "page", "size", "type", "circleName", "networkName", "moduleName", "regionName", "fromDate", "toDate"}, method = RequestMethod.GET, produces = "application/json")
	public Page<ErrorReportingDrillDown> findPaginated(
		      @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("type") String type, @RequestParam("circleName") String circleName,
		      @RequestParam("networkName") String networkName, @RequestParam("moduleName") String moduleName, @RequestParam("regionName") String regionName, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
		 Page<ErrorReportingDrillDown> resultPage = null;
		 
		 dateFrame.setFromDate(fromDate); 
		 dateFrame.setToDate(toDate);
		 if(networkName.equals("undefined") || moduleName.equals("undefined") || regionName.equals("undefined")) {	 
			 networkName="";
			 moduleName="";
			 regionName="";
		 }
		 
		 logger.info("fromDate-fromdate::"+fromDate); 
		 logger.info("toDate-toDate::"+toDate); 
		 if(fromDate !=null && !fromDate.isEmpty() && toDate !=null && !toDate.isEmpty()) {
		 
				resultPage = errorReportingService.findPaginatedByTxnDate(page, size,type, fromDate, toDate, circleName, networkName, moduleName, regionName);
		 
		 }else {
					SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
					 Date curDate=new Date();
					 curDate.setTime(curDate.getTime()-48*60*60*1000); 
					 String passedDate=sdf.format(curDate);
				
					String fromdate=passedDate;
					String todate=passedDate;
					logger.info("t-2-fromdate::"+fromdate);
					logger.info("t-2-todate::"+todate);
			 resultPage = errorReportingService.findPaginatedByTxnDate(page, size,type, fromdate, todate, circleName, networkName, moduleName, regionName);
		 }

		      return resultPage;
	}
	

/*	
	@RequestMapping(value = "td/errorReporting/getSearchNext", params = { "page", "size", "fromDate",	"toDate", "searchText" }, method = RequestMethod.GET, produces = "application/json")
	public Page<ErrorReportingDrillDown> findPaginatedAfterSearchNext(@RequestParam("page") int page, @RequestParam("size") int size,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
			@RequestParam("searchText") String searchText) {
		
		Page<ErrorReportingDrillDown> resultPage = errorReportingService.findPaginatedSearchNext(page, size, fromDate,toDate,searchText);
		
		
		  dateFrame.setFromDate(fromDate); dateFrame.setToDate(toDate);
		  

		if (page > resultPage.getTotalPages()) {
			// throw new MyResourceNotFoundException();
		}

		return resultPage;
	}
	*/
	
	 @RequestMapping(value = "td/errorReporting/getSearchNext", params = { "page", "size", "type", "circleName", "networkName", "moduleName", "regionName", "fromDate",
	  "toDate"}, method = RequestMethod.GET, produces = "application/json") 
	  public Page<ErrorReportingDrillDown> findPaginated(
	  
			  	@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("type") String type, 
			  	@RequestParam("circleName") String circleName, @RequestParam("networkName") String networkName, @RequestParam("moduleName")  String moduleName, 
			  	@RequestParam("regionName") String regionName, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, 
			  	@RequestParam("searchText")	  String searchText) { 
		  Page<ErrorReportingDrillDown> resultPage = null;
	  
	  if(networkName.equals("undefined") || moduleName.equals("undefined") || regionName.equals("undefined")) { 
		  networkName=""; moduleName="";
		  regionName=""; 
	  }
	  
	  if(fromDate !=null && !fromDate.isEmpty() && toDate !=null && !toDate.isEmpty()) {
	  
		  resultPage = errorReportingService.findPaginatedByTxnDateSearchNext(page,size,type, fromDate, toDate, circleName, networkName, moduleName, regionName, searchText);
	  
	  }
	  else { 
		  SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy"); 
		  Date curDate=new Date(); 
		  curDate.setTime(curDate.getTime()-48*60*60*1000); 
		  String passedDate=sdf.format(curDate);
	  
		  String fromdate=passedDate; 
		  String todate=passedDate;
		  logger.info("t-2-fromdate::"+fromdate); 
		  logger.info("t-2-todate::"+todate);
		  resultPage = errorReportingService.findPaginatedByTxnDateSearchNext(page,size,type, fromdate, todate, circleName, networkName, moduleName, regionName,searchText); 
		  }
	  
	  return resultPage; 
	  }
	
	 // All Back Botton

	 
	 @RequestMapping("td/errorDrillDownNetworkBack")
		public ModelAndView errorDrillDownNetworkBack(ModelAndView model, HttpSession session, HttpServletRequest request) {
			
			try {
				
				
				String circleName = ValidationCommon.validateString(request.getParameter("circleName"));
				String fromDate = ValidationCommon.validateString(request.getParameter("fromDate"));
				String toDate = ValidationCommon.validateString(request.getParameter("toDate"));
				
				model.addObject("circleName", circleName);
				model.addObject("fromDate", fromDate);
				model.addObject("toDate", toDate);
				
				model.setViewName("errorReporting");
				
			} catch (Exception e) {
				logger.error("Exception "+ExceptionConstants.EXCEPTION);
			}
			return model;
		}
	 
	 @RequestMapping("td/errorDrillDownModuleBack")
		public ModelAndView errorDrillDownModuleBack(ModelAndView model, HttpSession session, HttpServletRequest request) {
			
			try {
			
				String circleName = ValidationCommon.validateString(request.getParameter("circleName"));	
				String networkName = ValidationCommon.validateString(request.getParameter("networkName"));
				String fromDate = ValidationCommon.validateString(request.getParameter("fromDate"));
				String toDate = ValidationCommon.validateString(request.getParameter("toDate"));
				
				model.addObject("circleName", circleName);
				model.addObject("networkName", networkName);
				model.addObject("fromDate", fromDate);
				model.addObject("toDate", toDate);
				
				model.setViewName("errorDrillDownNetwork");
			} catch (Exception e) {
				logger.error("Exception "+ExceptionConstants.EXCEPTION);
			}
			return model;
		}
	
	 @RequestMapping("td/errorDrillDownRegionBack")
		public ModelAndView errorDrillDownRegionBack(ModelAndView model, HttpSession session, HttpServletRequest request) {
			
			try {
				
			
				String circleName = ValidationCommon.validateString(request.getParameter("circleName"));	
				String networkName = ValidationCommon.validateString(request.getParameter("networkName"));
	            String moduleName = ValidationCommon.validateString(request.getParameter("moduleName"));
	            String fromDate = ValidationCommon.validateString(request.getParameter("fromDate"));
				String toDate = ValidationCommon.validateString(request.getParameter("toDate"));
	                     
	            model.addObject("circleName", circleName);
				model.addObject("networkName", networkName);
				model.addObject("moduleName", moduleName);
				model.addObject("fromDate", fromDate);
				model.addObject("toDate", toDate);
				model.setViewName("errorDrillDownModule");
				
			} catch (Exception e) {
				logger.error("Exception "+ExceptionConstants.EXCEPTION);
			}
			return model;
		}
	 
	 
	 @RequestMapping("td/errorDrillDownBranchBack")
		public ModelAndView errorDrillDownBranchBack(ModelAndView model, HttpSession session, HttpServletRequest request) {
			
			try {
				
				String circleName = ValidationCommon.validateString(request.getParameter("circleName"));	
				String networkName = ValidationCommon.validateString(request.getParameter("networkName"));
	            String moduleName = ValidationCommon.validateString(request.getParameter("moduleName"));
	            String regionName = ValidationCommon.validateString(request.getParameter("regionName"));
	            String fromDate = ValidationCommon.validateString(request.getParameter("fromDate"));
				String toDate = ValidationCommon.validateString(request.getParameter("toDate"));
				
	            model.addObject("circleName", circleName);
				model.addObject("networkName", networkName);
				model.addObject("moduleName", moduleName);
				model.addObject("regionName", regionName);
				model.addObject("fromDate", fromDate);
				model.addObject("toDate", toDate);
				model.setViewName("errorDrillDownRegion");
				
			} catch (Exception e) {
				logger.error("Exception "+ExceptionConstants.EXCEPTION);
			}
			return model;
		}
	 
	@GetMapping("td/getErrorReportingLastUpDated")
	public ResponseEntity<String>  getLastUpdatedJob() {
		 String lastUpdatedDate= errorReportingService.findSwayamTxnLastUpdatedJob();
		ResponseEntity<String> entity=ResponseEntity.ok(lastUpdatedDate);
		return entity;
	}
	
}
