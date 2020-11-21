package sbi.kiosk.swayam.healthmonitoring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.BranchMaster;

@Repository("branchMastersRepository")
public interface BranchMasterRepository extends CrudRepository<BranchMaster,String>{

@Query(value="select * from TBL_BRANCH_MASTER  where  BRANCH_CODE=:brachCode",nativeQuery=true)
List<BranchMaster> findByBranchCode(@Param("brachCode") String brachCode);


@Query(value="select * from TBL_BRANCH_MASTER  where  BRANCH_CODE=:branchCode",nativeQuery=true)
List<BranchMaster> findAllByBranchCode(@Param("branchCode") String branchCode);




}
