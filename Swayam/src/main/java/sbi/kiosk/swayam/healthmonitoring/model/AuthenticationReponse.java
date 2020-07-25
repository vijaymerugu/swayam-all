package sbi.kiosk.swayam.healthmonitoring.model;

public class AuthenticationReponse {

	private final String token;
	private String status;
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AuthenticationReponse(String status,String token) {
		super();
		this.token = token;
		this.status = status;
	}

	public String getToken() {
		return token;
	}

}
