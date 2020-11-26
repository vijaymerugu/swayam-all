package sbi.kiosk.swayam.kioskmanagement.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import sbi.kiosk.swayam.common.entity.BranchMaster;

public interface BranchMasterRepository extends CrudRepository<BranchMaster, Integer> {
		
	BranchMaster findByBranchCode(String branchCode);
	

@Query(value="select CRCL_NAME from TBL_BRANCH_MASTER  where  BRANCH_CODE=:branchCode",nativeQuery=true)
String findCircleByBranchCode(@Param("branchCode") String branchCode);
}
