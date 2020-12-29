package sbi.kiosk.swayam.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import lombok.Data;
@Data
@Entity
//@Table(name="TBL_TICKET_HISTORY")
@NamedStoredProcedureQuery(name = "TicketHistory1", procedureName = "TicketHistory1", resultClasses = TicketHistory.class, parameters = {
		@StoredProcedureParameter(name = "kisokId", type = String.class, mode = ParameterMode.IN),
		@StoredProcedureParameter(name = "circle", type = String.class, mode = ParameterMode.IN),
		@StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR) })

public class TicketHistory {
	
	@Id
	@Column(name="KIOSK_ID")
	private String  kisokId;
	@Column(name="CRCL_NAME")
	private String circle;
	@Column(name="BRANCH_CODE")
	private String branchCode;
	@Column(name="CALL_LOG_DATE")
	private String call_log_date;
	@Column(name="CALL_CLOSED_DATE")
	private String call_closed_date;
	@Column(name="VENDOR")
	private String vendor;
	@Column(name="CALL_CATEGORY")
	private String callCategory;
	@Column(name="CALL_SUB_CATEGORY")
	private String  callSubCategory;
	// add new fro ticket id
	@Column(name="TICKET_ID")
	private String  ticketId;

}
