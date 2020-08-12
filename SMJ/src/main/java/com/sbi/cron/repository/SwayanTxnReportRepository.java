package com.sbi.cron.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sbi.cron.entity.SwayamTxnReport;

@Repository
public interface SwayanTxnReportRepository extends CrudRepository<SwayamTxnReport, Integer>{
	
	void deleteByTxnDate(String date);
}
