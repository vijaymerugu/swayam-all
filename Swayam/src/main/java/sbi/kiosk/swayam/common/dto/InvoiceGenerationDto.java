package sbi.kiosk.swayam.common.dto;

import javax.persistence.Column;

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
		
		this.year=invoiceGeneration.getYear();

		this.quarterId=invoiceGeneration.getQuarterId();


		this.rpfRefNumber=invoiceGeneration.getRpfRefNumber();
		
		
		this.spareParts=invoiceGeneration.getSpareParts();

		this.penalty=invoiceGeneration.getPenalty();

		this.invoiceAmount=invoiceGeneration.getInvoiceAmount();
		
		this.corrections=invoiceGeneration.getCorrections();
		
		this.finalAmount=invoiceGeneration.getFinalAmount();
		
		this.finalPenalty=invoiceGeneration.getFinalPenalty();
	}
	
	
	

	

	private String kisokId;
	
	private String vendor;
	
	private String circleName;
	
	private String state;

	private String kioskSerialNumber;
	
	private String year;

	private String quarterId;


	private String rpfRefNumber;
	
	
	//private String spareParts;
	
	private Float spareParts;

	private Float penalty;

	private Float invoiceAmount;
	
	private Float corrections;
	
	private Float finalAmount;
	
	
	private Float finalPenalty;
	

}
