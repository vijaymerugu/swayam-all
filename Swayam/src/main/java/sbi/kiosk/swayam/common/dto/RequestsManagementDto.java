package sbi.kiosk.swayam.common.dto;

import java.util.Date;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.Requests;

@Data
public class RequestsManagementDto {
	
	public RequestsManagementDto(){
		
	}
	
	public RequestsManagementDto(Requests requests){
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
		/*try {
			this.createdDate = requests.getCreatedDate() !=null ?formatStringToDate(requests.getCreatedDate().toString()):requests.getCreatedDate();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		try {
			this.modifiedDate = requests.getModifiedDate() !=null ?formatStringToDate(requests.getModifiedDate().toString()):requests.getModifiedDate() ;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	
	}
	
	private Integer id;
	
	
	private String caseId;
	
	
	private String branchCode;
	
	
	private String kioskId;
	
	
	private String vendor;
	
	
	private String category;
	
	
	private String subCategory;
	
	
	private String typeOfRequest;
	
	
	private String subject;
	
	
	private String comments;
	
	
	private String userType;
	
	
	private String reqCategory;	
	
	private Date createdDate;
	
	
	private String createdBy;
	
	
	private Date modifiedDate;
	
	
	private String modifiedBy;
	

	private String makerPfId;
	
	private String makerCircle;
	
	private String makerEmail;
	
	private String makerContact;
	
	private String checkerPfId;
	
	private String checkerCircle;
	
	private String checkerEmail;
	
	private String checkerContact;
	
	private String approverPfId;
	
	private String approverCircle;
	
	private String approverEmail;
	
	private String approverContact;
	
	private String kioskIp;
	
	private String kioskMacaddress;
	
	private String kioskOs;
	
	private String kioskVendor;

}
