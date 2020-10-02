/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbi.cron;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Rameshwar
 */
@Component
public class MyScheduledTasks {
	
	@Autowired
	CommaSeparated commaseparated ;
	
	@Value("${dailyreport.path}")
	private String dailyrReportPath;//dailyreport.path
	  
	@Value("${hourreport.path}")
	private String hourReportPath;
	
	@Scheduled(cron = "${dailyreport.time}")
	public void runEveryday() {
		//System.out.println("=============================runEveryday============================" + new Date());
		String path =dailyrReportPath ; 
		commaseparated.fileRead(path);
	}

	@Scheduled(cron = "${hourreport.time}")
	public void runhour() {
		//System.out.println("===========================runhour==============================" + new Date());
		String path =hourReportPath ; 
		commaseparated.filehourlyRead(path) ;
	}

}
