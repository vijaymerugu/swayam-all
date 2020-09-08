/**
 * 
 */
package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author vmph2371481
 *
 */
@Data
@Entity
@Table(name="TBL_MIS_AVAILABLE_COLUMNS")
public class MISAvailableColumns {
	
	@Id
	@Column(name="COLUMN_ID")
	private Integer columnId;
	
	@Column(name="COLUMN_NAME")
	private String columnName;
	
}
