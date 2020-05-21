package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="TBL_CIRCLE")
public class Circle {
	
	@Id
	@Column(name="CRCL_CODE")
	private String circleCode;
	
	@Column(name="CRCL_NAME")
	private String circleName;

}
