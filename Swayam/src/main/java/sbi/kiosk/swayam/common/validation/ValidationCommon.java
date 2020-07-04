package sbi.kiosk.swayam.common.validation;

import sbi.kiosk.swayam.common.exception.ValidationException;

public class ValidationCommon {

	public static String validateString(String str) {
		
		if(str !=null && str.matches("^[a-zA-Z0-9 -]*$")){
			return str;
		}
		else{
			throw new ValidationException("Invalid Data");
		}		
	}	
	
	public static String validateStringChar(String str){
		
		if(str !=null && str.matches("^[a-zA-Z0-9 -,.]*$")){
			return str;
		}
		else{
			throw new ValidationException("Invalid Data");
		}		
	}	
}
