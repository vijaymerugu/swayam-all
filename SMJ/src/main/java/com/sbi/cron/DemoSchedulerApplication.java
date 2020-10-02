package com.sbi.cron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoSchedulerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		System.out.println("========Started=======DemoSchedulerApplication=======");
		SpringApplication.run(DemoSchedulerApplication.class, args);
	}
}
