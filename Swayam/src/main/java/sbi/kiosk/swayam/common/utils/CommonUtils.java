package sbi.kiosk.swayam.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.json.JSONObject;
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
	
	
	
	private static String getJson(String strEncoded) throws UnsupportedEncodingException{
        byte[] decodedBytes =  Base64.getDecoder().decode(strEncoded.getBytes("UTF-8"));
        return new String(decodedBytes, "UTF-8");
    }
	 public static JSONObject decoded(String token) throws Exception {
	        String[] split = token.split("\\.");
			JSONObject test=new JSONObject();
			System.out.println( getJson(split[0]));
			System.out.println( getJson(split[1]));
			
			test.put("header", getJson(split[0]));
			return test.put("payload", getJson(split[1]));

}
	
	
	

}
