package sbi.kiosk.swayam.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
public class InvoiceSummaryEntity implements Serializable {
	
	
	
	@Id
	@Column(name="STAT_DESC")
	private String state;
	
	@Column(name="CRCL_NAME", unique = true)
	private String circleName;
	

	@Column(name="FIN_YR", unique = true)
	private String year;
	
	@Column(name="VENDOR")
	private String vendor;
	
	
	
	@Column(name="Q1_INVOICE_AMT")
	private String q1Im;
	@Column(name="Q2_INVOICE_AMT")
	private String q2Im;
	@Column(name="Q3_INVOICE_AMT")
	private String q3Im;
	@Column(name="Q4_INVOICE_AMT")
	private String q4Im;
	@Column(name="Q1_PENALTY_AMT")
	private String q1P;
	@Column(name="Q2_PENALTY_AMT")
	private String q2P;
	@Column(name="Q3_PENALTY_AMT")
	private String q3P;
	@Column(name="Q4_PENALTY_AMT")
	private String q4P;
	@Column(name="Q1_FINAL_AMT")
	private String q1Ba;
	@Column(name="Q2_FINAL_AMT")
	private String q2Ba;
	@Column(name="Q3_FINAL_AMT")
	private String q3Ba;
	@Column(name="Q4_FINAL_AMT")
	private String q4Ba;

}
