package sbi.kiosk.swayam.common.entity;

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
@StoredProcedureParameter(name="cur", type=void.class, mode= ParameterMode.REF_CURSOR)
}
)
public class ZeroTransactionKiosks {
		
		@Id
		@Column(name="BR_ID")
		private Integer id;
		
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
		
		@Column(name="KIOSK_ID")
		private String kioskId;
		
		@Column(name="VENDOR")
		private String vendor;
}