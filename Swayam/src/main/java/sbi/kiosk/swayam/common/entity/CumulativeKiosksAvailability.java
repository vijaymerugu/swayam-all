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
	name="SP_CUMULATIVE_AVAILABLITY_PROC",
	procedureName="SP_CUMULATIVE_AVAILABLITY_PROC",
	resultClasses=CumulativeKiosksAvailability.class,
	parameters={
		@StoredProcedureParameter(name="cur", type=void.class, mode= ParameterMode.REF_CURSOR)
	}
)

public class CumulativeKiosksAvailability implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		@Id
		@Column(name="CIRCLE")
		private String circleName;
		
		@Column(name="TOTAL_KIOSKS")
		private Integer totalKiosks;

		@Column(name="TOTAL_OPERATIONAL_KIOSKS")
		private Integer totalOperationalKiosks;
		
		@Column(name="AVAILABILITY_IN_PERCENT")
		private Double availableKiosksPercent;
		
}
