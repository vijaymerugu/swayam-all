package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Data;
import sbi.kiosk.swayam.common.dto.TaxSummaryCompositeId;

@Data
@Entity
@IdClass(value = TaxSummaryCompositeId.class)
public class TaxSummaryEntity {
	
	@Id
	@Column(name="FIN_YR")
	private String year;
	
	
	@Id
	@Column(name="STAT_DESC")
	private String state;
	
	@Id
	@Column(name="RFP_NO")
	private String rfpRefNumber;
	
	@Id
	@Column(name="CRCL_NAME")
	private String circleName;
	
	@Id
	@Column(name="COMPANY_SHORT_NM")
	private String vendor;
	
	
	
	@Column(name="Q1_INVOICE_AMT")
	private Long q1Im;
	@Column(name="Q2_INVOICE_AMT")
	private Long q2Im;
	@Column(name="Q3_INVOICE_AMT")
	private Long q3Im;
	@Column(name="Q4_INVOICE_AMT")
	private Long q4Im;
	@Column(name="Q1_PENALTY_AMT")
	private Long q1P;
	@Column(name="Q2_PENALTY_AMT")
	private Long q2P;
	@Column(name="Q3_PENALTY_AMT")
	private Long q3P;
	@Column(name="Q4_PENALTY_AMT")
	private Long q4P;
	@Column(name="Q1_FINAL_AMT")
	private Long q1Ba;
	@Column(name="Q2_FINAL_AMT")
	private Long q2Ba;
	@Column(name="Q3_FINAL_AMT")
	private Long q3Ba;
	@Column(name="Q4_FINAL_AMT")
	private Long q4Ba;

}
