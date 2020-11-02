package sbi.kiosk.swayam.dataanalyser.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import sbi.kiosk.swayam.billingpayment.controller.BillingPenaltyController;
import sbi.kiosk.swayam.common.constants.ExceptionConstants;
import sbi.kiosk.swayam.common.entity.MISAvailableColumns;
import sbi.kiosk.swayam.common.entity.DaUrgentInfo;
import sbi.kiosk.swayam.dataanalyser.repository.DaUrgentMessageRepository;


@Controller
public class DaUrgentInfoController {
	
	Logger logger =  LoggerFactory.getLogger(DaUrgentInfoController.class);
	
	@Autowired
	DaUrgentMessageRepository misMessgaes;
	
	@RequestMapping(value = "da/get-urgent-messgaes", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getMISUrgentMessages() {
		String json =null;
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			json = gson.toJson(misMessgaes.findMessages());
			
		}catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.EXCEPTION);
		}
	
		//System.out.println("Urgent Info " + json);
		return ResponseEntity.ok(json);
	}

}
