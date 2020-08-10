package sbi.kiosk.swayam.common.dto;

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
		
		this.timePeriod=invoiceCompare.getTimePeriod();


		this.rpfRefNumber=invoiceCompare.getRpfRefNumber();
		
		this.invoiceAmountSBI =invoiceCompare.getInvoiceAmountSBI();
		
		this.invoiceAmountVendor=invoiceCompare.getInvoiceAmountVendor();
		
		this.difference = invoiceCompare.getDifference();
		
		
	}
	
	private String kisokId;
	
	private String vendor;
	
	private String circleName;
	
	private String state;

	private String kioskSerialNumber;
	
	private String timePeriod;


	private String rpfRefNumber;
	
	
	private Float invoiceAmountSBI;
	
	private Float invoiceAmountVendor;
	
	private Float difference;

}



