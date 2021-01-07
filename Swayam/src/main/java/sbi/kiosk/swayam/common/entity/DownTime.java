package sbi.kiosk.swayam.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
@Data
@Entity
//@Table(name="TBL_TICKET_HISTORY")
public class DownTime implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="KIOSK_ID")
	private String  kioskId;
	@Column(name="circle")
	private String circle;
	@Column(name="NETWORK")
	private String network;
	@Column(name="MODULE")
	private String module;
	@Column(name="BRANCH_CODE")
	private String branchCode;
	@Column(name="VENDOR")
	private String vendor;
	@Column(name="CMS_CMF")
	private String cmsCmf;
	@Column(name="TOTAL_OPERATING_HRS")
	private String totalOperatingHours;
	@Column(name="TOTAL_DOWNTIME")
	private String  totalDowntime;

}
