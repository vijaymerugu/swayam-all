package sbi.kiosk.swayam.common.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;



@Data
@Entity
@Table(name="TBL_INVOICE_VENDOR")
public class InvoiceVendor {
	
	
	

	@Column(name="FIN_YR")
	private String finYear;
	@Column(name="INVO_NO")
	private Integer invNo;
	@Column(name="INVO_DT")
	private String invDt;
	@Column(name="CUST_NAME")
	private String cusName;
	@Id
	@Column(name="PRN_SRN")
	private String prnSrn;
	@Column(name="PRODUCT")
	private String product;
	@Column(name="INVO_FROM")
	private String invoiceFrom;
	@Column(name="INVO_UPTO")
	private String invoiceUpTo;
	@Column(name="INVO_AMT")
	private Float invoiceAmt;
	@Column(name="SHIP_ADD")
	private String shipAdd;
	@Column(name="SHIP_STATE")
	private String shipState;
	
	
	
	
	

}
