package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class RfpEntity {
	
	@Id
	@Column(name="RFP_NO")
	private String rfpNo;

}
