package sbi.kiosk.swayam.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
@Data
@Entity
public class BillingPenaltyEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="KIOSK_ID")
	private String kisokId;
	@Column(name="VENDOR")
	private String vendor;
	@Column(name="CRCL_NAME")
	private String circleName;
	@Column(name="STAT_DESC")
	private String state;
	@Column(name="KIOSK_SERIAL_NO")
	private String kioskSerialNumber;
	@Column(name="TIME_PERIOD")
	private String timePeriod;
	@Column(name="TOTAL_HRS")
	private Integer toatalhours;
	@Column(name="DOWNTIME")
	private Integer downTime;
	@Column(name="RELAXATION")
	private Integer relaxation;
	@Column(name="FINAL_DOWNTIME")
	private Integer finalDowntime;
	@Column(name="PENALTY")
	private Float penalty;
	@Column(name="RFP_REF_NO")
	private String prfRefNumber;
	


}
