package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBL_URL_CONFIGURE")
public class CommonUrl {
	
	@Id
	@Column(name = "ID", nullable = false)
	private Integer ID;
	
	@Column(name="OMS_URL")
	private String omsUrl;
	
	@Column(name="SBI_URL")
	private String sbiUrl;
	
	@Column(name="CREATED_DATE")
	private String createdDATE;
	@Column(name="PWD")
	private String password;
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getOmsUrl() {
		return omsUrl;
	}

	public void setOmsUrl(String omsUrl) {
		this.omsUrl = omsUrl;
	}

	public String getSbiUrl() {
		return sbiUrl;
	}

	public void setSbiUrl(String sbiUrl) {
		this.sbiUrl = sbiUrl;
	}

	public String getCreatedDATE() {
		return createdDATE;
	}

	public void setCreatedDATE(String createdDATE) {
		this.createdDATE = createdDATE;
	}
	
	
	

}
