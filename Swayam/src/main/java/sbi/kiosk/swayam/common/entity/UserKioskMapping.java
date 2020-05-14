package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import sbi.kiosk.swayam.common.dto.UserKioskMappingDto;

@Data
@Entity
@Table(name="TBL_USER_KIOSK_MAPPING")
public class UserKioskMapping {
	
	public UserKioskMapping(){
		
	}
	
	
	public UserKioskMapping(UserKioskMappingDto userKioskMappingDto) {
		this.pfId = userKioskMappingDto.getPfId();
		this.kioskId = userKioskMappingDto.getKioskId();
	}	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBL_USER_KIOSK_MAPPING")
	@SequenceGenerator(sequenceName = "SEQ_TBL_USER_KIOSK_MAPPING", allocationSize = 1, name = "SEQ_TBL_USER_KIOSK_MAPPING")
	@Column(name="USER_ID")
	private Integer userId;
	
	@Column(name="PF_ID")
	private String pfId;
	
	@Column(name="KIOSK_ID")
	private String kioskId;		
	

}
