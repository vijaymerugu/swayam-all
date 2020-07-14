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
			name="SP_USER_WISE_DOWN_KIOSK",
			procedureName="SP_USER_WISE_DOWN_KIOSK",
			resultClasses=UserWiseKiosksData.class,
			parameters={
				@StoredProcedureParameter(name = "userId", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name="cur", type=void.class, mode= ParameterMode.REF_CURSOR)
			}
		),
		
		@NamedStoredProcedureQuery(
			name="SP_USER_WISE_ZERO_TXN_KIOSK",
			procedureName="SP_USER_WISE_ZERO_TXN_KIOSK",
			resultClasses=UserWiseKiosksData.class,
			parameters={
				@StoredProcedureParameter(name = "userId", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name="cur", type=void.class, mode= ParameterMode.REF_CURSOR)
			}
		)
})

public class UserWiseKiosksData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="USERNAME")
	private String userName;
	
	@Column(name="TOTAL_KIOSKS")
	private Integer totalKiosks;

	@Column(name="TOTAL_OPERATIONAL_KIOSKS")
	private Integer totalOperationalKiosks;

	@Column(name="AVAILABILITY_IN_PERCENT")
	private Double availabilityPercentage;
	
	
}
