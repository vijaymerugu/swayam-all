package sbi.kiosk.swayam.healthmonitoring.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AuthenticationHeaderRequest {
	
	private String USER_ID;
	private String password;
	
	public AuthenticationHeaderRequest() {
		// TODO Auto-generated constructor stub
	}

	public AuthenticationHeaderRequest(String USER_ID, String password) {
		super();
		this.USER_ID = USER_ID;
		this.password = password;
	}

}
