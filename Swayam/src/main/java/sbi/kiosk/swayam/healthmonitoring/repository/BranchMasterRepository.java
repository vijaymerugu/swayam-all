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



@Query(value="select BRANCH_CODE from TBL_BRANCH_MASTER  where  CRCL_CODE=:circleCode",nativeQuery=true)
List<String> findAllByCircleCode(@Param("circleCode") String circleCode);

@Query(value="select BRANCH_CODE from TBL_BRANCH_MASTER  where  network=:networkCode",nativeQuery=true)
Iterable<String> findAllByNetworkCode(@Param("networkCode") String networkCode);

@Query(value="select BRANCH_CODE from TBL_BRANCH_MASTER  where  REGION=:regionCode",nativeQuery=true)
Iterable<String> findAllByRegionCodeCode(@Param("regionCode") String regionCode);

@Query(value="select BRANCH_CODE from TBL_BRANCH_MASTER  where  MODULE=:moduleCode",nativeQuery=true)
Iterable<String> findAllByModule(@Param("moduleCode") String moduleCode);

}
