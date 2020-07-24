package sbi.kiosk.swayam.common.exception;

import java.security.SignatureException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import sbi.kiosk.swayam.common.dto.ErrorResponseDto;
import sbi.kiosk.swayam.common.entity.AuditLogger;
import sbi.kiosk.swayam.common.repository.AuditInsertRepository;

@ControllerAdvice
public class CommonExceptionHandler {
	
	Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);
	@Autowired
	AuditInsertRepository audit;
	private String BAD_REQUEST = "BAD_REQUEST";
	/*@ExceptionHandler(value = Exception.class)
	protected ModelAndView handleException() {
		logger.error("CommonExceptionHandler -  Exception raised");
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("commonError", "Server Error");
		return mav;
	}*/
	
	
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	@ExceptionHandler(value = SignatureException.class)
	protected ResponseEntity handleJWTSignatureException(SignatureException ex, HttpServletRequest request) {
		logger.error("SignatureException -  Exception raised");
		AuditLogger auditLogger = new AuditLogger();
		auditLogger.setPath("/authenticateUser");
		auditLogger.setUser_Id(null);
		SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		java.util.Date date = new java.util.Date();
		//System.out.println("date "+ formatter.format(date));
		
		auditLogger.setSession_Date(formatter.format(date));;
		auditLogger.setStatus("400 Bad Reques");
		auditLogger.setToken(request.getQueryString());
		audit.save(auditLogger);
		ErrorResponseDto error = new ErrorResponseDto(BAD_REQUEST, "400 Bad Request");
		logger.error("BAD_REQUEST -  400 Bad Request");
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = ValidationException.class)
	protected ModelAndView handleValidationException() {
		logger.error("CommonExceptionHandler -  ValidationException raised");
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("commonError", "Bad Request");
		return mav;
	}

}
