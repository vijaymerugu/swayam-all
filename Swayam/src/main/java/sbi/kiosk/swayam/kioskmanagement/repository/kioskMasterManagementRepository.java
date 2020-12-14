package sbi.kiosk.swayam.kioskmanagement.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.KioskBranchMaster;


@Repository("kioskMasterManagementRepository")
public interface kioskMasterManagementRepository extends CrudRepository<KioskBranchMaster, Long>{

	@Query(value="select DISTINCT BRANCH_CODE from TBL_KIOSK_MASTER",nativeQuery=true)
	List<String> findBranchCode();
	
	@Query(value="SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE KIOSK_ID=:kioskId",nativeQuery=true)
    String findDuplicateKioskId(@Param("kioskId") String kioskId);
	
	@Query(value="select * from  TBL_KIOSK_MASTER km where not exists (select * from tbl_user_kiosk_mapping ukm where upper(km.kiosk_id)=upper(ukm.kiosk_id)) ", nativeQuery = true)
	Page<KioskBranchMaster> findAllByNotInUserKiosk( Pageable pageable); 
	
	@Query(value="select * from  TBL_KIOSK_MASTER km where exists (select * from tbl_user_kiosk_mapping ukm where upper(km.kiosk_id)=upper(ukm.kiosk_id)) ", nativeQuery = true)
	Page<KioskBranchMaster> findAllByInUserKiosk( Pageable pageable); 
}
