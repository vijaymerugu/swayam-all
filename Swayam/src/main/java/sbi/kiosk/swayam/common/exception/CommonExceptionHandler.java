package sbi.kiosk.swayam.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommonExceptionHandler {
	
	@ExceptionHandler(value = Exception.class)
	protected ModelAndView handleException() {
		
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("commonError", "Server Error");
		return mav;
	}

}
