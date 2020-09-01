package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class State {
	
	
	@Id
	@Column(name="STAT_CODE")
	private String statCode;
	
	@Column(name="STAT_DESC")
	private String statDesc;

}
