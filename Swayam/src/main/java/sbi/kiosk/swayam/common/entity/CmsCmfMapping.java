package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import sbi.kiosk.swayam.common.dto.CmsCmfMappingDto;

@Data
@Entity
@Table(name="TBL_CMS_CMF_MAPPING")
public class CmsCmfMapping {
	
	public CmsCmfMapping(CmsCmfMappingDto cmsCmfMappingDto) {
		this.cmsUsername = cmsCmfMappingDto.getCmsUsername();
		this.cmfUsername = cmsCmfMappingDto.getCmfUsername();
	}	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBL_CMS_CMF_MAPPING")
	@SequenceGenerator(sequenceName = "SEQ_TBL_CMS_CMF_MAPPING", allocationSize = 1, name = "SEQ_TBL_CMS_CMF_MAPPING")
	@Column(name="USER_ID")
	private Integer userId;
	
	@Column(name="CMS_USERNAME")
	private String cmsUsername;
	
	@Column(name="CMF_USERNAME")
	private String cmfUsername;	
}
