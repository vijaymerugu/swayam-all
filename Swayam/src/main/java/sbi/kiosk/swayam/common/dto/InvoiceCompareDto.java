package sbi.kiosk.swayam.common.dto;

import javax.persistence.Column;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.InvoiceCompare;

@Data
public class InvoiceCompareDto {
	
	
	public InvoiceCompareDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	public InvoiceCompareDto(InvoiceCompare invoiceCompare) {
		this.kisokId=invoiceCompare.getKisokId();
		
		this.vendor=invoiceCompare.getVendor();
		
		this.circleName=invoiceCompare.getCircleName();
		
		this.state=invoiceCompare.getState();

		this.kioskSerialNumber=invoiceCompare.getKioskSerialNumber();
		
		this.year=invoiceCompare.getYear();

		this.quarterId=invoiceCompare.getQuarterId();


		this.rpfRefNumber=invoiceCompare.getRpfRefNumber();
		
		this.invoiceAmountSBI =invoiceCompare.getInvoiceAmountSBI();
		
		this.invoiceAmountVendor=invoiceCompare.getInvoiceAmountVendor();
		
		this.penaltyAmountSBI = invoiceCompare.getPenaltyAmountSBI();
		
		this.penaltyAmountVendor=invoiceCompare.getPenaltyAmountVendor();
		 
		this.correctionAmount= invoiceCompare.getCorrectionAmount();
		
		this.difference = invoiceCompare.getDifference();
		
		
	}
	
	private String kisokId;
	
	private String vendor;
	
	private String circleName;
	
	private String state;

	private String kioskSerialNumber;
	
	private String year;

	private String quarterId;


	private String rpfRefNumber;
	
	
	private Float invoiceAmountSBI;
	
	private Float invoiceAmountVendor;
	
	private Float penaltyAmountSBI;
	
	private Float correctionAmount;
	
	private Float penaltyAmountVendor;
	
	private Float difference;

}



