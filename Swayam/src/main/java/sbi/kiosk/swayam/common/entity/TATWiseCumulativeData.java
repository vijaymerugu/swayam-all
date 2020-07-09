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
	name="SP_TAT_WISE_CUMULATIVE_DATA",
	procedureName="SP_TAT_WISE_CUMULATIVE_DATA",
	resultClasses=TATWiseCumulativeData.class,
	parameters={
			@StoredProcedureParameter(name="cur", type=void.class, mode= ParameterMode.REF_CURSOR)
		}
)

public class TATWiseCumulativeData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="VENDOR")
	private String vendorName;
	
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
