package sbi.kiosk.swayam.common.entity;

import java.io.Serializable;

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
//@Table(name="TBL_SWAYAM_TXN_DAILY")
public class SwayamTxnDaily implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	//@SequenceGenerator(name = "SWAYAM_TXN_DAILY",allocationSize = 1,sequenceName = "TBL_SWAYAM_TXN_DAILY_ID_SEQ")
	//@GeneratedValue(generator = "SWAYAM_TXN_DAILY", strategy =GenerationType.SEQUENCE )
	//private Integer id; 
	/* @Column(name="UNIQUE_REFERENCE_NO")
	private String unqReferenceNo;
	@Column(name="REQUEST_DATE_TIME")
	private String reqDateTime;
	@Column(name="REQUESTING_BRANCH")
	private String reqBranch;
	@Column(name="KIOSK_ID")
	private String kioskId;
	@Column(name="RESPONSE_DATE_TIME")
	private String respDateTime;
	@Column(name="ACKNOWLEDGE_DATE_TIME")
	private String	acknowledgeDateTime;
	@Column(name="RESPONSE_CODE")
	private String respCode; */
	@Column(name="ERROR_CODE_COUNT")
	private String errorCodeCount; 
	@Column(name="ERROR_CODE")
	private String errorCode; 
	@Column(name="ERROR_DESC")
	private String errorDesc; 
//	@Column(name="STATUS")
//	private String status; 
//	@Column(name="BARCODE")
//	private String barCode;
	@Override
	public String toString() {
		return "SwayamTxnDaily [errorCode=" + errorCode + ", errorDesc=" + errorDesc + "]";
	}

}
