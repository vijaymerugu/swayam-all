package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

//By Pankul 28-04-2020-----------STARTS---------

@Data
@Entity
@Table(name="TBL_BRANCH_KIOSK_CMF")
public class KioskCMF {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBL_BRANCH_KIOSK_CMF")
	@SequenceGenerator(sequenceName = "SEQ_TBL_BRANCH_KIOSK_CMF", allocationSize = 1, name = "SEQ_TBL_BRANCH_KIOSK_CMF")
	@Column(name="ID")
	private Integer id;
	
	@Column(name="KIOSK_ID")
	private String KioskId;
	
	@Column(name="CMF_PF_ID")
	private String cmfPfId;

}

//--------------- BY Pankul END ------------