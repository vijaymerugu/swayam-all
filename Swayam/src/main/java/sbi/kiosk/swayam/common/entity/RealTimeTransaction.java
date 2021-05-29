package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import org.springframework.data.repository.CrudRepository;

import lombok.Data;
import lombok.NoArgsConstructor;



@NamedStoredProcedureQueries({
	  
	   @NamedStoredProcedureQuery(name = "SP_REAL_TIME_PROC", 
	                              procedureName = "SP_REAL_TIME_PROC",
	                            		  resultClasses = {RealTimeTransaction.class},
	                              parameters = {
	                                 @StoredProcedureParameter(mode = ParameterMode.IN, name = "fromdate_param", type = String.class),
	                                 @StoredProcedureParameter(mode = ParameterMode.IN, name = "searchText", type = String.class),
	                                 @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "RESULT_SET", type= String.class)
	                              })
	})




@NoArgsConstructor
@Data
@Entity
public class RealTimeTransaction {
	  
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
		
		

}
