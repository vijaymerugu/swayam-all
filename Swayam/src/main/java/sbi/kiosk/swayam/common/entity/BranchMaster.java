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
@Table(name="TBL_BRANCH_MASTER")
public class BranchMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBL_BRANCH_MASTER")
	@SequenceGenerator(sequenceName = "SEQ_TBL_BRANCH_MASTER", allocationSize = 1, name = "SEQ_TBL_BRANCH_MASTER")
	@Column(name="BR_ID")
	private Integer brId;
	
	@Column(name="CIRCLE")
	private String circle;
	
	@Column(name="CIRCLE_NAME")
	private String circleName;
	
	@Column(name="NW")
	private String network;
	
	@Column(name="MODULE")
	private String module;
	
	@Column(name="REGION")
	private String region;
	
	@Column(name="BRANCH_CODE")
	private String branchCode;
	
	@Column(name="BRANCH_NAME")
	private String branchName;

}
