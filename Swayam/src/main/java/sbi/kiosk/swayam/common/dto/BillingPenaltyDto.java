package sbi.kiosk.swayam.common.dto;

import javax.persistence.Column;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.BillingPenaltyEntity;

@Data
public class BillingPenaltyDto {

	
	public BillingPenaltyDto() {
		
		
	}
	public BillingPenaltyDto(BillingPenaltyEntity billingPenaltyEntity) {
		
		this.kisokId=billingPenaltyEntity.getKisokId();
		
		this.vendor=billingPenaltyEntity.getVendor();
		
		this.circleName=billingPenaltyEntity.getCircleName();
		
		this.state=billingPenaltyEntity.getState();

		this.kioskSerialNumber=billingPenaltyEntity.getKioskSerialNumber();
		
		this.year=billingPenaltyEntity.getYear();

		this.quarterId=billingPenaltyEntity.getQuarterId();


		this.toatalhours=billingPenaltyEntity.getToatalhours();
		
		this.downTime=billingPenaltyEntity.getDownTime();
		
		this.relaxation=billingPenaltyEntity.getRelaxation();
		this.finalDowntime=billingPenaltyEntity.getFinalDowntime();
		
		this.penalty=billingPenaltyEntity.getPenalty();
		this.prfRefNumber=billingPenaltyEntity.getPrfRefNumber();
		
		
	}
	
	
	
	private String kisokId;
	
	private String vendor;
	
	private String circleName;
	
	private String state;

	private String kioskSerialNumber;
	

	private String year;

	private String quarterId;

	private Integer toatalhours;
	
	private Integer downTime;
	
	private Integer relaxation;
	private Integer finalDowntime;
	
//	private Float penalty;
	
	private Double penalty;
	private String prfRefNumber;
}
