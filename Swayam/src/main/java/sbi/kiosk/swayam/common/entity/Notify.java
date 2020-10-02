/**
 * 
 */
package sbi.kiosk.swayam.common.entity;

import java.util.Date;

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
@Table(name="TBL_NOTIFY")
public class Notify {
	
	@Id
	@Column(name = "NOTIFY_ID")
	private Integer notifyId; 
	@Column(name = "USER_ID")
	private String userId;
	@Column(name = "MESSAGE")
	private String message;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

}
