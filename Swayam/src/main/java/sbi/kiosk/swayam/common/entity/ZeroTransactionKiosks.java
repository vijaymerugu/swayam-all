package sbi.kiosk.swayam.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import lombok.Data;

@Data
@Entity
@NamedStoredProcedureQuery(
name="SP_ZERO_TRANSACTION_KIOSKS",
procedureName="SP_ZERO_TRANSACTION_KIOSKS",
resultClasses=ZeroTransactionKiosks.class,
parameters={
@StoredProcedureParameter( name="fromdate",type=String.class, mode= ParameterMode.IN),
@StoredProcedureParameter(name="todate", type=String.class, mode= ParameterMode.IN),
@StoredProcedureParameter(name = "searchText", type = String.class, mode = ParameterMode.IN),
//@StoredProcedureParameter(name="RESULT_SET", type=void.class, mode= ParameterMode.REF_CURSOR)
@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "RESULT_SET", type= String.class)
}
)
public class ZeroTransactionKiosks implements Serializable{
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
		
		@Id
		@Column(name="KIOSK_ID")
		private String kioskId;
		
		@Column(name="CRCL_NAME")
		private String circleName;
		
		@Column(name="NETWORK")
		private String network;
		
		@Column(name="MODULE")
		private String module;
		
		@Column(name="REGION")
		private String region;
		
		@Column(name="BRANCH_CODE")
		private String branchCode;
		
		@Column(name="BRANCH_NAME")
		private String branchName;
		
	
		
		@Column(name="VENDOR")
		private String vendor;
}
