package sbi.kiosk.swayam.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import sbi.kiosk.swayam.common.dto.InvoiceSummaryCompositeId;

@Data
@Entity
@IdClass(value = InvoiceSummaryCompositeId.class)
public class InvoiceSummaryEntity{
	
	
	

	//private static final long serialVersionUID = 1L;

	@Id
	@Column(name="STAT_DESC")
	private String state;
	
	@Id
	@Column(name="CRCL_NAME")
	private String circleName;
	
	@Id
	@Column(name="FIN_YR")
	private String year;
	
	@Id
	@Column(name="VENDOR")
	private String vendor;
	
	/*
	 * @Id
	 * 
	 * @Column(name="STAT_DESC") private String state;
	 * 
	 * @Column(name="CRCL_NAME") private String circleName;
	 * 
	 * 
	 * @Column(name="FIN_YR") private String year;
	 * 
	 * @Column(name="VENDOR") private String vendor;
	 */
	
	
	
	
	/*
	 * @Column(name="Q1_INVOICE_AMT") private String q1Im;
	 * 
	 * @Column(name="Q2_INVOICE_AMT") private String q2Im;
	 * 
	 * @Column(name="Q3_INVOICE_AMT") private String q3Im;
	 * 
	 * @Column(name="Q4_INVOICE_AMT") private String q4Im;
	 * 
	 * @Column(name="Q1_PENALTY_AMT") private String q1P;
	 * 
	 * @Column(name="Q2_PENALTY_AMT") private String q2P;
	 * 
	 * @Column(name="Q3_PENALTY_AMT") private String q3P;
	 * 
	 * @Column(name="Q4_PENALTY_AMT") private String q4P;
	 * 
	 * @Column(name="Q1_FINAL_AMT") private String q1Ba;
	 * 
	 * @Column(name="Q2_FINAL_AMT") private String q2Ba;
	 * 
	 * @Column(name="Q3_FINAL_AMT") private String q3Ba;
	 * 
	 * @Column(name="Q4_FINAL_AMT") private String q4Ba;
	 */
	
/* Issue Fix - 10-11-2020 */
	
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
