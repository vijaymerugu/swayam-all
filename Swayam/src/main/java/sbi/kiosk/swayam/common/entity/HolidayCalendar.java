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
@Table(name="TBL_BRANCH_HOLIDAY")
public class HolidayCalendar {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBL_BRANCH_HOLIDAY")
	@SequenceGenerator(sequenceName = "SEQ_TBL_BRANCH_HOLIDAY", allocationSize = 1, name = "SEQ_TBL_BRANCH_HOLIDAY")
	@Column(name="ID")
	private Integer id;
	
	@Column(name="HOLIDAY_DATE")
	private String holidayDate;
	
	@Column(name="DAY")
	private String day;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="CIRCLE")
	private String circle;
	
	@Column(name="STATE")
	private String state;
	
}
