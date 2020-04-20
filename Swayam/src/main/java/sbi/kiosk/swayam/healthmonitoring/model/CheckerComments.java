package sbi.kiosk.swayam.healthmonitoring.model;

import lombok.Data;

@Data
public class CheckerComments {
	
	public CheckerComments(int caseId, String comments){
		this.caseId = caseId;
		this.comments = comments;
	}
		
	public int caseId;
	
	public String comments;
}
