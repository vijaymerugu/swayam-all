package sbi.kiosk.swayam.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.KioskBranchMaster;

@Repository
public interface KioskMasterRepository extends CrudRepository<KioskBranchMaster, String>{

	@Query(value ="SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE KIOSK_ID NOT IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING)) AND BRANCH_CODE IN (SELECT BRANCH_CODE FROM TBL_BRANCH_MASTER WHERE CIRCLE = :circle)",
			nativeQuery=true)
	List<String> fetchAllKiosksByCircleAndNotInUserKioskMapping(@Param("circle") String circle);	
	

}
