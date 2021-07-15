package sbi.kiosk.swayam.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Data;
import sbi.kiosk.swayam.common.dto.BpCompoisteID;
import sbi.kiosk.swayam.common.dto.InvoiceSummaryCompositeId;
@Data
@Entity
@IdClass(value = BpCompoisteID.class)
public class BillingPenaltyEntity  {
	
	
	@Id
	@Column(name="KIOSK_ID")
	private String kisokId;
	@Id
	@Column(name="VENDOR")
	private String vendor;
	@Id
	@Column(name="CRCL_NAME")
	private String circleName;
	@Id
	@Column(name="STAT_DESC")
	private String state;
	@Column(name="KIOSK_SERIAL_NO")
	private String kioskSerialNumber;
	@Column(name="FIN_YR")
	private String year;
	@Column(name="QTR_ID")
	private String quarterId;
	@Column(name="TOT_HRS")
	private Integer toatalhours;
	@Column(name="TOT_DOWNTIME")
	private Integer downTime;
	@Column(name="RELAXATION_HRS")
	private Integer relaxation;
	@Column(name="ACT_DOWNTIME")
	private Integer finalDowntime;
	/*
	 * @Column(name="PENALTY_AMT") private Float penalty;
	 */
	@Column(name="PENALTY_AMT")
	private Double penalty;
	
	@Column(name="RFP_NO")
	private String prfRefNumber;
	


}
