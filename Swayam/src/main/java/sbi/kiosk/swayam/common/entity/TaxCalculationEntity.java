package sbi.kiosk.swayam.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import lombok.Data;
import sbi.kiosk.swayam.common.dto.InvoiceSummaryCompositeId;
import sbi.kiosk.swayam.common.dto.TaxCalculationCompoisteId;

@Data
@Entity
@NamedStoredProcedureQuery(
		name="SP_TAX_CAL",
		procedureName="SP_TAX_CAL",
		resultClasses=TaxCalculationEntity.class,
		parameters={
				@StoredProcedureParameter( name="CRITERIA",type=Integer.class, mode= ParameterMode.IN),
				@StoredProcedureParameter( name="GSTTYPE",type=String.class, mode= ParameterMode.IN),
				@StoredProcedureParameter( name="CIRCLECODE",type=Integer.class, mode= ParameterMode.IN),
				@StoredProcedureParameter( name="STATDCODE",type=Integer.class, mode= ParameterMode.IN),
				@StoredProcedureParameter( name="RFPNO",type=String.class, mode= ParameterMode.IN),
				@StoredProcedureParameter( name="VENDORID",type=Integer.class, mode= ParameterMode.IN),
				@StoredProcedureParameter( name="FINYEAR",type=String.class, mode= ParameterMode.IN),
				@StoredProcedureParameter( name="QTRID",type=String.class, mode= ParameterMode.IN),
				@StoredProcedureParameter( name="GST",type=Float.class, mode= ParameterMode.IN),
				@StoredProcedureParameter(name="RESULT_SET", type=void.class, mode= ParameterMode.REF_CURSOR)
			}
	)
@IdClass(value = TaxCalculationCompoisteId.class)
public class TaxCalculationEntity {
	
	//private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CIRCLENAME")
	private String circleName;	
	@Id
	@Column(name="RFPNO")
	private String rfpRefNumber;
	@Id
	@Column(name="VENDOR")
	private String vendor;
	@Id
	@Column(name="STATE")
	private String state;
	
	@Column(name="CIRCLCODE")
	private Integer circleCode;
	
	@Column(name="VENDOR_ID")
	private Integer vendorId;
	
	@Column(name="QUARTER")
	private String quarter;
	
	@Column(name="FINYEAR")
	private String finyear;
	
	@Column(name="INVOICEAMT")
	private Double invoiceAmount;
	@Column(name="PENALTYAMT")
	private Double penaltyAmount;
	@Column(name="GSTTYPE")
	private String gstType;
	@Column(name="GST")
	private Float gst;
	@Column(name="AMCG")
	private Double amcGst;
	@Column(name="PENG")
	private Double penGst;
	@Column(name="ToatalGST")
	private Double toatalGST;
	
	
	
	
	
	
	
	
	

}
