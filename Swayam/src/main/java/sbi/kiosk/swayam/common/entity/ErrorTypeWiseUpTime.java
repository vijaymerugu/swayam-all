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
	name="SP_ERROT_TYPE_WISE_UPTIME_PROC",
	procedureName="SP_ERROT_TYPE_WISE_UPTIME_PROC",
	resultClasses=ErrorTypeWiseUpTime.class,
	parameters={
		@StoredProcedureParameter( name="callCategory",type=String.class, mode= ParameterMode.IN),
		@StoredProcedureParameter(name="cur", type=void.class, mode= ParameterMode.REF_CURSOR)
	}
)

public class ErrorTypeWiseUpTime implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ERROR")
	private String errorType;
	
	@Column(name="CIRCLE")
	private String circleName;
	
	@Column(name="TOTAL_NO_OF_TICKETS")
	private Integer noOfTickets;

	@Column(name="ERROR_WISE_OPEN_TICKETS")
	private Integer errorWiseOpenTickets;

	@Column(name="PERCENTAGE_OF_TICKETS")
	private Double percentageOfTickets;
	
	@Column(name="OTHER_ERROR_TICKETS")
	private Double OtherErrorTickets;
	
}
