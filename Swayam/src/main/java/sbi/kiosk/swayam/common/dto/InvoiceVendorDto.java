package sbi.kiosk.swayam.common.dto;

import lombok.Data;


@Data
public class InvoiceVendorDto {
	
	
	/*
	 * public InvoiceVendorDto() { // TODO Auto-generated constructor stub }
	 */
	

	/*
	 * public InvoiceVendorDto(InvoiceVendor invoiceVendor) { super(); this.finYear=
	 * invoiceVendor.getFinYear(); this.invNo = invoiceVendor.getInvNo();
	 * this.invDt= invoiceVendor.getInvDt(); this.cusName =
	 * invoiceVendor.getCusName(); this.prnSrn= invoiceVendor.getPrnSrn();
	 * this.product = invoiceVendor.getProduct(); this.invoiceFrom=
	 * invoiceVendor.getInvoiceFrom(); this.invoiceUpTo =
	 * invoiceVendor.getInvoiceUpTo(); this.invoiceAmt=
	 * invoiceVendor.getInvoiceAmt(); this.shipAdd = invoiceVendor.getShipAdd();
	 * this.shipState = invoiceVendor.getShipState(); }
	 */

	private String finYear;
	private Integer invNo;
	private String invDt;
	private String cusName;
	private String prnSrn;
	private String product ;
	private String invoiceFrom;
	private String invoiceUpTo;
	private Float invoiceAmt;
	private Float penaltyAmt;
	private String shipAdd;
	private String shipState ;	

}
