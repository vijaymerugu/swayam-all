/**
 * 
 */
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
 * @author vmph2362595
 *
 */
@Data
@Entity

/*
 * @NamedStoredProcedureQuery( name="SP_TAT_OF_DOWN_KIOSKS_PROC",
 * procedureName="SP_TAT_OF_DOWN_KIOSKS_PROC",
 * resultClasses=ZeroTransactionKiosks.class, parameters={
 * 
 * @StoredProcedureParameter( name="circleCode",type=String.class, mode=
 * ParameterMode.IN),
 * 
 * @StoredProcedureParameter(name="cur", type=void.class, mode=
 * ParameterMode.REF_CURSOR) } )
 */
public class TATofDownKiosks implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CIRCLE")
	private String circleName;
	
	@Column(name="TOTAL_NO_OF_TICKETS")
	private Integer noOfTickets;

	@Column(name="ONE_DAY")
	private Integer oneDay;

	@Column(name="TWO_TO_FIVE_DAYS")
	private Integer twoToFiveDays;
	
	@Column(name="ONE_WEEK")
	private Integer oneWeek;
	
	@Column(name="ONE_TO_TWO_WEEK")
	private Integer oneToTwoWeek;
	
	@Column(name="GREATER_THAN_TWO_WEEK")
	private Integer greaterThanTwoWeek;

	@Column(name="PERCENTAGE_OF_ONE_DAYS")
	private Double percentageOfOneDays;
	
	@Column(name="PERCENT_OF_TWO_TO_FIVE_DAYS")
	private Double percentOfTwoToFiveDays;
	
	@Column(name="PERCENTAGE_OF_ONE_WEEK")
	private Double percentageOfOneWeek;
	
	@Column(name="PERCENT_ONE_TO_TWO_WEEK")
	private Double percentOneToTwoWeek;
	
	@Column(name="PERCENT_GREATER_THAN_TWO_WEEK")
	private Double percentGreaterThanTwoWeek;
	
}
