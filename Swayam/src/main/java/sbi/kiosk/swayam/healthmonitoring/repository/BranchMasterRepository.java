package sbi.kiosk.swayam.healthmonitoring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.BranchMaster;

@Repository("branchMastersRepository")
public interface BranchMasterRepository extends CrudRepository<BranchMaster,String>{

@Query(value="select BRANCH_NAME from TBL_BRANCH_MASTER  where  BRANCH_CODE=:brachCode",nativeQuery=true)
List<String> findByBranchCode(@Param("brachCode") String brachCode);


}
