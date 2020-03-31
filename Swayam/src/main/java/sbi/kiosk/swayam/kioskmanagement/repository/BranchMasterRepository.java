package sbi.kiosk.swayam.kioskmanagement.repository;

import org.springframework.data.repository.CrudRepository;

import sbi.kiosk.swayam.common.entity.BranchMaster;

public interface BranchMasterRepository extends CrudRepository<BranchMaster, Integer> {
		
	BranchMaster findByBranchCode(String branchCode);
}
