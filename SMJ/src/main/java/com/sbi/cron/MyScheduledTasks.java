/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbi.cron;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Ankur Verma
 */
@Component
public class MyScheduledTasks {
	
	@Autowired
	CommaSeparated commaseparated ;
	@Autowired
	MyProcedureScheduler myProcedureScheduler ;
	
	  @Value("${dailyreport.path}") private String dailyreportPath;//dailyreport.path
	  
	  @Value("${hourreport.path}") private String hourreportpath;
	 

    private static final Logger log = LoggerFactory.getLogger(MyScheduledTasks.class);

    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    // @Scheduled(cron = "0/10 * * * * *") 
    //  @Scheduled(cron = "0 0 12 * * ?")//day
	  public void runEveryday() {
	   String path   =dailyreportPath;
			   //"C:\\Users\\ankur.verma\\Desktop\\sbi_document\\Corn_job\\input\\";// reportPath 
	// commaseparated.fileRead(path);
	  
	  }
	 
 //  @Scheduled(cron = "0/10 * * * * *")
  @Scheduled(cron = "* 0-59 * * * *")  //hour
    public void runhour() {
	String path =hourreportpath ; 
			//"C:\\Users\\ankur.verma\\Desktop\\sbi_document\\Corn_job\\hourlyinput";
    commaseparated.filehourlyRead(path) ;
  }
  
	/*
	 * @Scheduled(cron = "0/10 * * * * *") public void executeprodure() {
	 * System.out.println("welocome"); myProcedureScheduler.executprodure();
	 * 
	 * }
	 */
}
