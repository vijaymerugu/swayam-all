package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="TBL_SUPERVISOR")
public class Supervisor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBL_SUPERVISOR")
	@SequenceGenerator(sequenceName = "SEQ_TBL_SUPERVISOR", allocationSize = 1, name = "SEQ_TBL_SUPERVISOR")
	@Column(name="ID")
	private Integer id;
	
	@Column(name="PF_ID")
	private String pfId;
	
	@Column(name="PF_ID_SUPERVISOR")
	private String pfIdSupervisor;	
}
