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
		name="SP_VENDOR_WISE_CUMULATIVE_DATA",
		procedureName="SP_VENDOR_WISE_CUMULATIVE_DATA",
		resultClasses=VendorWiseCumulativeData.class,
		parameters={
			@StoredProcedureParameter(name="cur", type=void.class, mode= ParameterMode.REF_CURSOR)
	}),
		
	@NamedStoredProcedureQuery(
		name="SP_CUM_CIRCLE_VENDOR_DATA",
		procedureName="SP_CUM_CIRCLE_VENDOR_DATA",
		resultClasses=VendorWiseCumulativeData.class,
		parameters={
			@StoredProcedureParameter(name = "userId", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name="cur", type=void.class, mode= ParameterMode.REF_CURSOR)
	})
	
})

public class VendorWiseCumulativeData implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		@Id
		@Column(name="VENDOR")
		private String vendorName;
		
		@Column(name="TOTAL_KIOSKS")
		private Integer totalKiosks;

		@Column(name="TOTAL_OPERATIONAL_KIOSKS")
		private Integer totalOperationalKiosks;
		
		@Column(name="AVAILABILITY_IN_PERCENT")
		private Double availableKiosksPercent;
		
}
