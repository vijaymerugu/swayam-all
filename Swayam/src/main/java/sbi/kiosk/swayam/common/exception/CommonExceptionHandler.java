package sbi.kiosk.swayam.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommonExceptionHandler {
	
	Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);
	
	@ExceptionHandler(value = Exception.class)
	protected ModelAndView handleException() {
		logger.error("CommonExceptionHandler -  Exception raised");
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("commonError", "Server Error");
		return mav;
	}
	
	@ExceptionHandler(value = ValidationException.class)
	protected ModelAndView handleValidationException() {
		logger.error("CommonExceptionHandler -  ValidationException raised");
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("commonError", "Bad Request");
		return mav;
	}

}
