package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="TBL_REQ_RESP_AUDIT_LOG")
public class RequestResponse extends Common{
	
	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBL_REQ_RESP_LOG")
	//@SequenceGenerator(sequenceName = "SEQ_TBL_REQ_RESP_LOG", allocationSize = 1, name = "SEQ_TBL_REQ_RESP_LOG")
	//@Column(name="ID")
	private Integer id;
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="REQUEST")
	private String request;
	
	@Column(name="RESPONSE")
	private String response;
	
	@Column(name="SUCCESS")
	private String success;
	
	@Column(name="ERROR")
	private String error;
	
	@Column(name="TOKEN")
	private String token;
	
	@Column(name="URL")
	private String url;

	@Override
	public String toString() {
		return "RequestResponse [id=" + id + ", request=" + request + ", response=" + response + ", success=" + success
				+ ", error=" + error + ", token=" + token + ", url=" + url + "]";
	}
	
	

}
