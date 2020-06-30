package com.sbi.cron.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sbi.cron.dto.BranchTransactionDayDto;
import com.sbi.cron.entity.BranchTransactionDayEntity;



@Repository
public interface BranchTransactionRepository  extends CrudRepository<BranchTransactionDayEntity, Integer>  {

}

