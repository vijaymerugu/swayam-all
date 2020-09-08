package sbi.kiosk.swayam.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import lombok.Data;

/**
 * @author vmph2371481
 *
 */
@Data
@Entity

@NamedStoredProcedureQuery(
		name="SP_MIS_REPORT",
		procedureName="SP_MIS_REPORT",
		resultClasses=MISReportData.class,
		parameters={
				@StoredProcedureParameter( name="GROUPCRITERIA",type=Integer.class, mode= ParameterMode.IN),
				@StoredProcedureParameter( name="FROM_DATE_PARAM",type=String.class, mode= ParameterMode.IN),
				@StoredProcedureParameter( name="TO_DATE_PARAM",type=String.class, mode= ParameterMode.IN),
				@StoredProcedureParameter(name="RESULT_SET", type=void.class, mode= ParameterMode.REF_CURSOR)
			}
	)

	public class MISReportData implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		@Id
		@Column(name="SELECTEDGROUP")
		private String selectedGroup;
		
		@Column(name="CIRCLENAME")
		private String circleName;
		
		@Column(name="BRANCHCODE")
		private String branchCode;
		
		@Column(name="VENDORNAME")
		private String vendorName;
		
		@Column(name="SWAYAMTRANSACTION")
		private Integer swayamTransaction;
		
		@Column(name="BRANCHCOUNTER")
		private Integer branchCounter;

		@Column(name="MIGRATIONPERCENT")
		private Double migrationPercent;

		@Column(name="UPTIMEPERCENT")
		private Double uptimePercent;
		
		@Column(name="NOOFKIOSKS")
		private Integer noOfKiosks;
		
		@Column(name="TOTALDOWNTIME")
		private Integer totalDowntime;
		
		@Column(name="ONSITEOFFSITE")
		private String onSiteOffSite;
		
		@Column(name="STANDALONETTW")
		private String standaloneTTW;
		
		@Column(name="NOOFREQUESTRAISED")
		private Integer noOfRequestRaised;
		
		@Column(name="TYPEOFREQUEST")
		private String typeOfRequest;
		
		@Column(name="TATOFREQUESTCOMPLETION")
		private String tatOfRequestCompletion;
		
	}
