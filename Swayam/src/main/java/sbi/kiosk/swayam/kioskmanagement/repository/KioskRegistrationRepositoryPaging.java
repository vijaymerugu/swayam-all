package sbi.kiosk.swayam.kioskmanagement.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.dto.KioskRegistrationDto;
import sbi.kiosk.swayam.common.entity.KioskRegistration;


@Repository
public interface KioskRegistrationRepositoryPaging extends CrudRepository<KioskRegistration, Long> {

	
	@Query(value="SELECT BM.CRCL_NAME AS CIRCLE,BM.BRANCH_CODE AS BRANCH_CODE,KM.VENDOR,KM.KIOSK_ID AS KIOSK_ID,KM.KIOSK_SERIAL_NO AS SERIAL_NO, " + 
			"KM.KIOSK_MAC_ADDRESS AS MAC_ID,KM.KIOSK_IP AS KIOSK_IP,KM.RFP_ID AS RFP_ID,US.USERNAME AS ASSIGNED_CMF, " + 
			"US.PHONENO AS ASSIGNED_CMF_PHONE_NO FROM TBL_KIOSK_MASTER KM " + 
			"INNER JOIN TBL_BRANCH_MASTER BM ON KM.BRANCH_CODE=BM.BRANCH_CODE " + 
			"LEFT JOIN TBL_USER_KIOSK_MAPPING UKM ON KM.KIOSK_ID=UKM.KIOSK_ID " + 
			"LEFT JOIN TBL_USER US ON UKM.PF_ID=US.PF_ID " + 
			"ORDER BY BM.CRCL_NAME,BM.BRANCH_CODE,KM.VENDOR ", nativeQuery = true, countQuery ="SELECT BM.CRCL_NAME AS CIRCLE,BM.BRANCH_CODE AS BRANCH_CODE,KM.VENDOR,KM.KIOSK_ID AS KIOSK_ID,KM.KIOSK_SERIAL_NO AS SERIAL_NO, " + 
					"KM.KIOSK_MAC_ADDRESS AS MAC_ID,KM.KIOSK_IP AS KIOSK_IP,KM.RFP_ID AS RFP_ID,US.USERNAME AS ASSIGNED_CMF, " + 
					"US.PHONENO AS ASSIGNED_CMF_PHONE_NO FROM TBL_KIOSK_MASTER KM " + 
					"INNER JOIN TBL_BRANCH_MASTER BM ON KM.BRANCH_CODE=BM.BRANCH_CODE " + 
					"LEFT JOIN TBL_USER_KIOSK_MAPPING UKM ON KM.KIOSK_ID=UKM.KIOSK_ID " + 
					"LEFT JOIN TBL_USER US ON UKM.PF_ID=US.PF_ID " + 
					"ORDER BY BM.CRCL_NAME,BM.BRANCH_CODE,KM.VENDOR ")
	List<KioskRegistration> findAllByKiosk( );
}
