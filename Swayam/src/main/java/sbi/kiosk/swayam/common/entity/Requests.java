package sbi.kiosk.swayam.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

import org.hibernate.envers.Audited;

import sbi.kiosk.swayam.common.dto.RequestsDto;

@Data
@Entity
@Table(name="TBL_REQUESTS")
@Audited
public class Requests  {
	
	public Requests(){
		
	}
	
	public Requests(RequestsDto requests){
		this.id = requests.getId();
		this.caseId = requests.getCaseId();
		this.branchCode = requests.getBranchCode();
		this.kioskId = requests.getKioskId();
		this.vendor = requests.getVendor();
		this.category = requests.getCategory();
		this.subCategory =  requests.getSubCategory();
		this.typeOfRequest  = requests.getTypeOfRequest();
		this.subject = requests.getSubject();
		this.comments = requests.getComments();
		this.userType = requests.getUserType();
		this.reqCategory = requests.getReqCategory();
		this.createdDate = requests.getCreatedDate();
		this.createdBy = requests.getCreatedBy();
		this.modifiedDate = requests.getModifiedDate();
		this.modifiedBy = requests.getModifiedBy();
	}
	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBL_REQUESTS")
	@SequenceGenerator(sequenceName = "SEQ_TBL_REQUESTS", allocationSize = 1, name = "SEQ_TBL_REQUESTS")
	@Column(name="ID")
	private Integer id;
	
	@Column(name="CASE_ID")
	private String caseId;
	
	@Column(name="BRANCH_CODE")
	private String branchCode;
	
	@Column(name="KIOSK_ID")
	private String kioskId;
	
	@Column(name="VENDOR")
	private String vendor;
	
	@Column(name="CATEGORY")
	private String category;
	
	@Column(name="SUB_CATEGORY")
	private String subCategory;
	
	@Column(name="TYPE_OF_REQUEST")
	private String typeOfRequest;
	
	@Column(name="SUBJECT")
	private String subject;
	
	@Column(name="COMMENTS")
	private String comments;
	
	@Column(name="USER_TYPE")
	private String userType;
	
	@Column(name="REQ_CAT")
	private String reqCategory;	
	
	@Column(name="CREATED_BY")
	public String createdBy;
	
	@Column(name="CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	public Date createdDate;
	
	@Column(name="MODIFIED_BY")
	public String modifiedBy;
	
	@Column(name="MODIFIED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	public Date modifiedDate;
	
}
