package sbi.kiosk.swayam.common.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class CommonUtils {
	public static final String ASTERISK = "*";
	
	public static ResponseEntity<Object> getResponse(Object response, MediaType mediaType) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Success", ASTERISK);
		headers.setContentType(mediaType);
		return new ResponseEntity<>(response, headers, HttpStatus.OK);
	}
	
	

}
