package sbi.kiosk.swayam.common.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.dto.KioskBranchMasterUserDto;
import sbi.kiosk.swayam.common.entity.KioskBranchMaster;

@Repository
public interface KioskMasterRepository extends CrudRepository<KioskBranchMaster, Integer>{

	@Query(value ="SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE KIOSK_ID NOT IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING)) AND BRANCH_CODE IN (SELECT BRANCH_CODE FROM TBL_BRANCH_MASTER WHERE CIRCLE = :circle)",
			nativeQuery=true)
	List<String> fetchAllKiosksByCircleAndNotInUserKioskMapping(@Param("circle") String circle);	
	
	//KioskBranchMaster findKioskByKioskId(String kioskId);	
	
	
	KioskBranchMaster findKioskByKioskId(String kioskId);	
	//@Query(value ="SELECT CIRCLE FROM TBL_KIOSK_MASTER WHERE KIOSK_ID=?1 ",nativeQuery=true)
	@Query(value ="SELECT BRANCH_CODE FROM TBL_KIOSK_MASTER WHERE KIOSK_ID=?1 ",nativeQuery=true)
	String  findKioskByBranchCode(String kioskId);	
	
	
	
	@Query(value ="SELECT count(KIOSK_ID) FROM TBL_KIOSK_MASTER WHERE KIOSK_ID IS NOT NULL ",nativeQuery=true)
	int findKioskMasterCount();
	
	@Query(value ="SELECT COUNT(*) FROM TBL_KIOSK_MASTER WHERE VENDOR IN('CMS') ",nativeQuery=true)
	int findKioskCMSMasterCount();
	@Query(value =" SELECT COUNT(*) FROM  TBL_KIOSK_MASTER WHERE VENDOR IN('LIPI') ",nativeQuery=true)
	int findKioskLIPIMasterCount();
	@Query(value ="SELECT count(*) FROM TBL_KIOSK_MASTER ",nativeQuery=true)
	int findTotalKioskMasterCount();
	
	@Query(value ="SELECT COUNT(*) FROM  TBL_KIOSK_MASTER WHERE INSTALLATION_STATUS IN('Installed') and VENDOR IN('CMS') ",nativeQuery=true)
	int findInstalledStatusCMSVendorWiseCount();
	@Query(value ="SELECT COUNT(*) FROM  TBL_KIOSK_MASTER WHERE INSTALLATION_STATUS IN('Pending') and VENDOR IN('CMS') ",nativeQuery=true)
	int findDeliveredStatusCMSVendorWiseCount();
	
	@Query(value ="SELECT COUNT(*) FROM  TBL_KIOSK_MASTER WHERE INSTALLATION_STATUS IN('Installed') and VENDOR IN('LIPI') ",nativeQuery=true)
	int findInstalledStatusLIPIVendorWiseCount();
	@Query(value ="SELECT COUNT(*) FROM  TBL_KIOSK_MASTER WHERE INSTALLATION_STATUS IN('Pending') and VENDOR IN('LIPI') ",nativeQuery=true)
	int findDeliveredStatusLIPIVendorWiseCount();
	
	/*
	 * @Query(value
	 * =" SELECT COUNT(KIOSK_ID)  FROM  TBL_USER_KIOSK_MAPPING ",nativeQuery=true)
	 */
	
	@Query(value="select count(KIOSK_ID) from TBL_KIOSK_MASTER where  KIOSK_ID in ( SELECT KIOSK_ID FROM  TBL_USER_KIOSK_MAPPING)  ",nativeQuery=true)
	int findAssignedCount();
	
	@Query(value ="select count(KIOSK_ID) from TBL_KIOSK_MASTER where  KIOSK_ID  not in ( SELECT KIOSK_ID FROM  TBL_USER_KIOSK_MAPPING) ",nativeQuery=true)
	int findToBeAssignedCount();
	
	KioskBranchMaster findByKioskId(String kiosId);
	
	@Query(value ="SELECT count(KIOSK_ID) FROM TBL_KIOSK_MASTER WHERE KIOSK_ID IS NOT NULL AND CIRCLE=:circle",nativeQuery=true)
	int findKioskMasterCount(@Param("circle") String circle);
	
	@Query(value ="SELECT COUNT(*) FROM TBL_KIOSK_MASTER WHERE VENDOR IN('CMS') AND CIRCLE=:circle",nativeQuery=true)
	int findKioskCMSMasterCount(@Param("circle") String circle);
	
	@Query(value =" SELECT COUNT(*) FROM  TBL_KIOSK_MASTER WHERE VENDOR IN('LIPI') AND CIRCLE=:circle",nativeQuery=true)
	int findKioskLIPIMasterCount(@Param("circle") String circle);
	
	@Query(value ="SELECT count(*) FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle",nativeQuery=true)
	int findTotalKioskMasterCount(@Param("circle") String circle);
	
	@Query(value ="SELECT COUNT(*) FROM  TBL_KIOSK_MASTER WHERE INSTALLATION_STATUS IN('Installed') and VENDOR IN('CMS') AND CIRCLE=:circle",nativeQuery=true)
	int findInstalledStatusCMSVendorWiseCount(@Param("circle") String circle);
	
	@Query(value ="SELECT COUNT(*) FROM  TBL_KIOSK_MASTER WHERE INSTALLATION_STATUS IN('Pending') and VENDOR IN('CMS') AND CIRCLE=:circle",nativeQuery=true)
	int findDeliveredStatusCMSVendorWiseCount(@Param("circle") String circle);
	
	@Query(value ="SELECT COUNT(*) FROM  TBL_KIOSK_MASTER WHERE INSTALLATION_STATUS IN('Installed') and VENDOR IN('LIPI') AND CIRCLE=:circle",nativeQuery=true)
	int findInstalledStatusLIPIVendorWiseCount(@Param("circle") String circle);
	
	@Query(value ="SELECT COUNT(*) FROM  TBL_KIOSK_MASTER WHERE INSTALLATION_STATUS IN('Pending') and VENDOR IN('LIPI') AND CIRCLE=:circle",nativeQuery=true)
	int findDeliveredStatusLIPIVendorWiseCount(@Param("circle") String circle);
	
	@Query(value =" SELECT COUNT(KIOSK_ID)  FROM  TBL_USER_KIOSK_MAPPING WHERE KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle)",nativeQuery=true)
	int findAssignedCount(@Param("circle") String circle);
	
	@Query(value ="select count(KIOSK_ID) from TBL_KIOSK_MASTER where CIRCLE=:circle AND  KIOSK_ID  not in ( SELECT KIOSK_ID FROM  TBL_USER_KIOSK_MAPPING) ",nativeQuery=true)
	int findToBeAssignedCount(@Param("circle") String circle);
	
	List<KioskBranchMaster> findAllByCircle(@Param("circle") String circle);
	
	List<KioskBranchMaster> findAll();
	
	
	  @Query(value  ="select * from TBL_KIOSK_MASTER where kiosk_id not in (select kiosk_id from  tbl_user_kiosk_mapping)  "
	  ,nativeQuery=true) List<KioskBranchMaster> findToBeAssignedKioskId();
	  
	  @Query(value  ="select * from  TBL_KIOSK_MASTER km where exists (select 1 from tbl_user_kiosk_mapping ukm where upper(km.kiosk_id)=upper(ukm.kiosk_id))  "
			  ,nativeQuery=true) List<KioskBranchMaster> findAssignedKioskId();
	 
}
