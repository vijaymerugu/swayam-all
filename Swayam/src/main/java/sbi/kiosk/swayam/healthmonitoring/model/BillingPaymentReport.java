package sbi.kiosk.swayam.healthmonitoring.model;

import org.springframework.stereotype.Component;
import lombok.Data;


@Component
@Data
public class BillingPaymentReport {
	
	private String circle;
	private String state;
	private String vendor;
	private String timePeiod;
	private String rpfNumber;
	

}
