package sbi.kiosk.swayam.common.dto;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.InvoiceGeneration;

@Data
public class InvoiceGenerationDto {
	
	public InvoiceGenerationDto() {
		// TODO Auto-generated constructor stub
	}
	
	public InvoiceGenerationDto(InvoiceGeneration invoiceGeneration) {
		this.kisokId=invoiceGeneration.getKisokId();
		
		this.vendor=invoiceGeneration.getVendor();
		
		this.circleName=invoiceGeneration.getCircleName();
		
		this.state=invoiceGeneration.getState();

		this.kioskSerialNumber=invoiceGeneration.getKioskSerialNumber();
		
		this.timePeriod=invoiceGeneration.getTimePeriod();


		this.rpfRefNumber=invoiceGeneration.getRpfRefNumber();
		
		
		this.spareParts=invoiceGeneration.getSpareParts();

		this.penalty=invoiceGeneration.getPenalty();

		this.invoiceAmount=invoiceGeneration.getInvoiceAmount();
		
		this.corrections=invoiceGeneration.getCorrections();
		
		this.finalAmount=invoiceGeneration.getFinalAmount();
	}
	
	
	

	

	private String kisokId;
	
	private String vendor;
	
	private String circleName;
	
	private String state;

	private String kioskSerialNumber;
	
	private String timePeriod;


	private String rpfRefNumber;
	
	
	private String spareParts;

	private Float penalty;

	private Float invoiceAmount;
	
	private Float corrections;
	
	private Float finalAmount;
	

}
