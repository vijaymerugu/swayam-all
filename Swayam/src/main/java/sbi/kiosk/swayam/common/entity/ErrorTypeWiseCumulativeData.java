/**
 * 
 */
package sbi.kiosk.swayam.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
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

@NamedStoredProcedureQueries({
	
	@NamedStoredProcedureQuery(
			name="SP_CUMULATIVE_ERRORTYPE_UPTIME",
			procedureName="SP_CUMULATIVE_ERRORTYPE_UPTIME",
			resultClasses=ErrorTypeWiseCumulativeData.class,
			parameters={
					@StoredProcedureParameter(name="cur", type=void.class, mode= ParameterMode.REF_CURSOR)
				}
		),
	
	@NamedStoredProcedureQuery(
			name="SP_CUM_CIRCLE_ERRORTYPE_UPTIME",
			procedureName="SP_CUM_CIRCLE_ERRORTYPE_UPTIME",
			resultClasses=ErrorTypeWiseCumulativeData.class,
			parameters={
					@StoredProcedureParameter(name = "userId", type = String.class, mode = ParameterMode.IN),
					@StoredProcedureParameter(name="cur", type=void.class, mode= ParameterMode.REF_CURSOR)
				}
		)
	
})

public class ErrorTypeWiseCumulativeData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ERROR_TYPE")
	private String errorType;
	
	@Column(name="TOTAL_NO_OF_TICKETS")
	private String noOfTickets;
	
	@Column(name="ERROR_WISE_TOTAL_OPEN_TICKETS")
	private Integer errorWiseTotalOpenTickets;

	@Column(name="AVAILABILITY_IN_PERCENT")
	private Double availabilityInPercent;
	
}
