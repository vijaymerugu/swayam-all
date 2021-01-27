package sbi.kiosk.swayam.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OMSController {
public static final String ASTERISK = "*";
	
	public static ResponseEntity<Object> getResponse(Object response, MediaType mediaType) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Success", ASTERISK);
		headers.setContentType(mediaType);
		return new ResponseEntity<>(response, headers, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "omsGenerateToken", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes  = MediaType.APPLICATION_JSON_VALUE)
	//@PostAuthorize("hasPermission('Dummylogin','READ')")
	public ResponseEntity<Object> sendResponse(@RequestBody String Token) {
	System.out.println("inside dummy oms url....");

		String result = "SUCCESS";
		String userId = "6692273";
		//Token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjkyMjczIiwiaWF0IjoxNjExMzE3MTM3LCJleHAiOjE2MTEzMTcxNDJ9.IyPEBjbttHZc-9cKo9sgGVLBu1IeyaQVohloK5q1jwM";
		Map<String, String> mapResponse = null;
		if (Token != null && !Token.isEmpty()) {
			System.out.println("dummy oms url...."+Token);
			mapResponse = new HashMap<String, String>();
			mapResponse.put("result", result);
			mapResponse.put("userId", userId);
		}else {
			System.out.println("else dummy oms url...."+Token);
			mapResponse = new HashMap<String, String>();
			mapResponse.put("result", "SUCCESS");
			//mapResponse.put("url", "${dummy_oms_url}");
		}
		System.out.println("dummy mapResponse...."+mapResponse);
		return OMSController.getResponse(mapResponse, MediaType.APPLICATION_JSON);
	}

	
	
	
	

}
