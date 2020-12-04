package sbi.kiosk.swayam.kioskmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.KioskBranchMaster;

@Repository
public interface kioskMasterManagementRepository extends CrudRepository<KioskBranchMaster, Long>{

	@Query(value="select DISTINCT BRANCH_CODE from TBL_KIOSK_MASTER",nativeQuery=true)
	List<String> findBranchCode();
	
	@Query(value="SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE KIOSK_ID=:kioskId",nativeQuery=true)
    String findDuplicateKioskId(@Param("kioskId") String kioskId);
}
