package sbi.kiosk.swayam.common.dto;

import java.util.List;

import lombok.Data;

@Data
public class ErrorResponseDto {
	
	 public ErrorResponseDto(String message,String status) {
	        super();
	        this.message = message;
	        this.status = status;
	        
	       
	    }
	  
	    private String message;
	    private String status;

	    
}
