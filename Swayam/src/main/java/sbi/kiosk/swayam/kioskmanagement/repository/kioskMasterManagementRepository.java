package sbi.kiosk.swayam.kioskmanagement.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.dto.KioskRegistrationDto;
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
	
	@Query(value="select * from  TBL_KIOSK_MASTER km where not exists (select * from tbl_user_kiosk_mapping ukm where upper(km.kiosk_id)=upper(ukm.kiosk_id)) and km.circle=:circle", nativeQuery = true)
	Page<KioskBranchMaster> findAllByNotInUserKioskByCircle( @Param("circle") String circle,Pageable pageable); 
	
	@Query(value="select * from  TBL_KIOSK_MASTER km where exists (select * from tbl_user_kiosk_mapping ukm where upper(km.kiosk_id)=upper(ukm.kiosk_id)) and km.circle=:circle ", nativeQuery = true)
	Page<KioskBranchMaster> findAllByInUserKioskByCircle( @Param("circle") String circle,Pageable pageable); 
	
	@Query( value="select * from tbl_kiosk_master B where (B.CIRCLE=UPPER(:searchText) OR B.BRANCH_CODE=UPPER(:searchText) OR B.BRANCH_NAME=UPPER(:searchText)OR b.kiosk_id=UPPER(:searchText))", nativeQuery=true,
			 countQuery = " select * from tbl_kiosk_master B where (B.CIRCLE=UPPER(:searchText) OR B.BRANCH_CODE=UPPER(:searchText) OR B.BRANCH_NAME=UPPER(:searchText)OR b.kiosk_id=UPPER(:searchText))") 
	 Page<KioskBranchMaster> findAllByKioskIdSearchText(@Param("searchText") String searchText,Pageable pageable);	
	 
	 @Query( value="select * from tbl_kiosk_master B where B.vendor=:type and (B.CIRCLE=UPPER(:searchText) OR B.BRANCH_CODE=UPPER(:searchText) OR B.BRANCH_NAME=UPPER(:searchText)OR b.kiosk_id=UPPER(:searchText))", nativeQuery=true,
			 countQuery = " select * from tbl_kiosk_master B where B.vendor=:type and (B.CIRCLE=UPPER(:searchText) OR B.BRANCH_CODE=UPPER(:searchText) OR B.BRANCH_NAME=UPPER(:searchText)OR b.kiosk_id=UPPER(:searchText))") 
	 Page<KioskBranchMaster> findByVendorSearchText(@Param("type") String type,@Param("searchText") String searchText,Pageable pageable);	
	 
	 @Query( value="select * from tbl_kiosk_master B where B.vendor=:type and B.installation_status=:installationStatus and (B.CIRCLE=UPPER(:searchText) OR B.BRANCH_CODE=UPPER(:searchText) " + 
	 		"OR B.BRANCH_NAME=UPPER(:searchText)OR b.kiosk_id=UPPER(:searchText))", nativeQuery=true,
			 countQuery = " select * from tbl_kiosk_master B where B.vendor=:type and B.installation_status=:installationStatus and (B.CIRCLE=UPPER(:searchText) OR B.BRANCH_CODE=UPPER(:searchText) " + 
			 		"OR B.BRANCH_NAME=UPPER(:searchText)OR b.kiosk_id=UPPER(:searchText))") 
	 Page<KioskBranchMaster> findByVendorAndInstallationStatusSearchText(@Param("type") String type,@Param("installationStatus") String installationStatus,@Param("searchText") String searchText,Pageable pageable);
	 
	@Query(value="select * from  TBL_KIOSK_MASTER km where exists (select * from tbl_user_kiosk_mapping ukm where upper(km.kiosk_id)=upper(ukm.kiosk_id)) and (km.CIRCLE=UPPER(:searchText) "
			+ "OR km.BRANCH_CODE=UPPER(:searchText) OR km.BRANCH_NAME=UPPER(:searchText) OR km.kiosk_id=UPPER(:searchText))", nativeQuery = true)
	Page<KioskBranchMaster> findAllByInUserKioskSearchText(@Param("searchText") String searchText, Pageable pageable);
	
	@Query(value="select * from  TBL_KIOSK_MASTER km where not exists (select * from tbl_user_kiosk_mapping ukm where upper(km.kiosk_id)=upper(ukm.kiosk_id)) and (km.CIRCLE=UPPER(:searchText) OR km.BRANCH_CODE=UPPER(:searchText) OR km.BRANCH_NAME=UPPER(:searchText) OR km.kiosk_id=UPPER(:searchText)) ", nativeQuery = true)
	Page<KioskBranchMaster> findAllByNotInUserKioskSearchText( @Param("searchText") String searchText,Pageable pageable); 

	
	/*
	 * @Query(
	 * value="SELECT BM.CRCL_NAME AS CIRCLE,BM.BRANCH_CODE AS BRANCH_CODE,KM.VENDOR,KM.KIOSK_ID AS KIOSK_ID,KM.KIOSK_SERIAL_NO AS SERIAL_NO,\r\n"
	 * +
	 * "KM.KIOSK_MAC_ADDRESS AS MAC_ID,KM.KIOSK_IP AS IP_ADDRESS,KM.RFP_ID AS RFP_ID,US.USERNAME AS ASSIGNED_CMF, \r\n"
	 * + "US.PHONENO AS ASSIGNED_CMF_PHONE_NO FROM TBL_KIOSK_MASTER KM \r\n" +
	 * "INNER JOIN TBL_BRANCH_MASTER BM ON KM.BRANCH_CODE=BM.BRANCH_CODE\r\n" +
	 * "LEFT JOIN TBL_USER_KIOSK_MAPPING UKM ON KM.KIOSK_ID=UKM.KIOSK_ID\r\n" +
	 * "LEFT JOIN TBL_USER US ON UKM.PF_ID=US.PF_ID\r\n" +
	 * "ORDER BY BM.CRCL_NAME,BM.BRANCH_CODE,KM.VENDOR ", nativeQuery = true)
	 * List<KioskRegistrationDto> findAllByKiosk( Pageable pageable);
	 */
	 

}
