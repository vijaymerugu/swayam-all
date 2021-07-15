package sbi.kiosk.swayam.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="TBL_ALLREQUEST_DETAILS")
public class BpRequest {
	
	@Id
	@Column(name="REQUEST_ID")
	private Integer requestId;
	
	@Column(name="REQ_TYPE")
	private String reqType;
	
	@Column(name="REQ_DETAIL")
	private String reqDetail;
	
	@Column(name="REQ_DATE")
	private Date reqDate;
	
	@Column(name="CLOSE_DATE")
	private Date reqCloseDate;
	
	@Column(name="MK_PFID")
	private String makerPfid;
	
	@Column(name="MK_COMMENT")
	private String makersComment;
	
	@Column(name="CH_PFID")
	private String checkerPfid;
	
	@Column(name="CH_COMMNET")
	private String checkerCommnet;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="USER_CIRCLE")
	private String userCircle;

}
