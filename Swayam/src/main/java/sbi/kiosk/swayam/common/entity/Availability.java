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
	name="SP_KIOSKS_AVAILABLITY_PROC",
	procedureName="SP_KIOSKS_AVAILABLITY_PROC",
	resultClasses=Availability.class,
	parameters={
		@StoredProcedureParameter(name="cur", type=void.class, mode= ParameterMode.REF_CURSOR)
	}
)

public class Availability implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		@Id
		@Column(name="CIRCLE")
		private String circleName;
		
		@Column(name="NO_OF_KIOSKS")
		private Integer noOfKiosks;

		@Column(name="NO_OF_AVAILABLE_KIOSKS")
		private Integer totalAvailableKiosks;

		@Column(name="NO_OF_UNAVAILABLE_KIOSKS")
		private Integer totalNonAvailableKiosks;
		
		@Column(name="PERCENTAGE_OF_AVAILABLE_KIOSKS")
		private Double availableKiosksPercent;
		
		@Column(name="NON_AVAILABLE_KIOSKS_PERCENT")
		private Double nonAvailableKiosksPercent;
		
}
