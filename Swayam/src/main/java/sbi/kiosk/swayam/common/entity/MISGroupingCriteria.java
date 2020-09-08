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
@Table(name="TBL_MIS_GROUPING_CRITERIA")
public class MISGroupingCriteria {
	
	@Id
	@Column(name="GROUP_ID")
	private Integer groupId;
	
	@Column(name="GROUP_NAME")
	private String groupName;
	
}
