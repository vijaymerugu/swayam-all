package com.sbi.cron.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sbi.cron.entity.SwayamTranactionhourEntity;

@Repository
public interface SwayamTranactionhourRepository extends CrudRepository<SwayamTranactionhourEntity, Integer> {

}
