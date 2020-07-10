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

@NamedStoredProcedureQuery(
	name="SP_SUMMARY_OF_DOWN_KIOSKS_PROC",
	procedureName="SP_SUMMARY_OF_DOWN_KIOSKS_PROC",
	resultClasses=SummaryOfDownKiosks.class,
	parameters={
		@StoredProcedureParameter(name="cur", type=void.class, mode= ParameterMode.REF_CURSOR)
	}
)

public class SummaryOfDownKiosks implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CIRCLE")
	private String circleName;
	
	@Column(name="TOTAL_NO_OF_TICKETS")
	private Integer noOfTickets;

	@Column(name="NO_OF_OPEN_TICKETS")
	private Integer noOfOpenTickets;

	@Column(name="NO_OF_CLOSED_TICKETS")
	private Integer noOfClosedTickets;
	
	@Column(name="PERCENTAGE_OF_TICKETS")
	private Double percentageOfTickets;
	
	@Column(name="PERCENT_OF_CLOSED_TICKETS")
	private Double percentageOfClosedTickets;
	
}
