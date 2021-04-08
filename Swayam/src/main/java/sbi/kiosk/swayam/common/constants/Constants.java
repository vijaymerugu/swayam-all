package sbi.kiosk.swayam.common.constants;

public enum Constants {

	MAKER("M","MAKER"),
	CHECKER("C","CHECKER"),
	BILLINGMAKER("BM","BILLING MAKER"),
	BILLINGCHECKER("BC","BILLING CHECKER"),
	APPROVER("A","APPROVER"),
	CREATED("CRTD","CREATED"),
	RECOMMENDED("RCMD","RECOMMENDED"),
	APPROVED("APRD","APPROVED"),
	REJECTED("REJ","REJECTED"),
	LOCALADMIN("LA","LOCAL ADMIN"),
	CIRCLE("C","CIRCLE"),
	SYSTEMADMIN("SA","SYSTEM ADMIN");
	
	
	private String code;
	
	private String value;
	
	private Constants(String code, String value){
		this.code = code;
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}
	
	
}
