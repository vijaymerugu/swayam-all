package com.sbi.cron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoSchedulerApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(DemoSchedulerApplication.class, args);
	}
}
