package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;



@NamedStoredProcedureQueries({
	  
	   @NamedStoredProcedureQuery(name = "sp_realtime1", 
	                              procedureName = "sp_realtime1",
	                            		  resultClasses = {RealTimeTransaction.class},
	                              parameters = {
	                                 @StoredProcedureParameter(mode = ParameterMode.IN, name = "fromdate", type = String.class),
	                               //  @StoredProcedureParameter(mode = ParameterMode.IN, name = "todate", type = String.class),
	                                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "cur", type= String.class)
	                              })
	})




@NoArgsConstructor
@Data
@Entity
public class RealTimeTransaction {
/*	@Id
	@Column(name = "KIOSK_ID")
	private String kioskId;
	@Column(name = "BRANCH_CODE")
	private String branchCode;
	@Column(name = "VENDOR")
	private String vendor;
	@Column(name = "REGION")
	private String region;
	
	@Column(name = "MODULE")
	private String module;
	@Column(name = "NETWORK")
	private String network;
	@Column(name = "CRCL_NAME")
	private String crclName;
	@Column(name = "BRANCH_NAME")
	private String branchName;
	@Column(name = "NO_OF_TXNS")
	private String noOfTxns;
	@Column(name="CREATED_DATE")
	private String createdDate;*/
	
	
	  
	    @Id
		@Column(name = "KIOSK_ID")
		private String kioskId;
		@Column(name = "BRANCH_CODE")
		private String branchCode;
		@Column(name = "REGION")
		private String region;
		@Column(name = "VENDOR")
		private String vendor;
		@Column(name = "MODULE")
		private String module;
		@Column(name = "NETWORK")
		private String network;
		@Column(name = "CRCL_NAME")
		private String crclName;
		
		@Column(name = "BRANCH_NAME")
		private String branchName;
		@Column(name = "NO_OF_TXNS")
		private String noOfTxns;
		
		@Column(name = "TXN_DATE")
		private String fromDate;

}
