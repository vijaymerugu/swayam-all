package sbi.kiosk.swayam.healthmonitoring.model;

public class AuthenticationRequest {
	
	private String username;
	
	public AuthenticationRequest() {
		// TODO Auto-generated constructor stub
	}
	public AuthenticationRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	private String password;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
