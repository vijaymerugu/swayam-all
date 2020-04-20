package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TBL_CALL_TYPE")
public class CallType {
	@Id
	@Column(name = "CATEGORY")
	private String category;
	@Column(name = "SUB_CATEGORY")
	private String subCategory;
	@Column(name = "RISK")
	private String risk;

	
}
