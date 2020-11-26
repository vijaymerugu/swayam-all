package sbi.kiosk.swayam.healthmonitoring.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.CallType;


@Repository
public interface CallTypeRepository extends CrudRepository<CallType, String> {
	
	
	
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('High') and a.STATUS_OF_COMPLAINT='Active' ",nativeQuery=true)
	public int getCallTypeHiegh() ;
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('Medium') and a.STATUS_OF_COMPLAINT='Active' ",nativeQuery=true)
	public int getCallTypeMedium() ;
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('Low') and a.STATUS_OF_COMPLAINT='Active' ",nativeQuery=true)
	public int getCallTypeLow() ;
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('High','Medium','Low') and a.STATUS_OF_COMPLAINT='Active' ",nativeQuery=true)
	public int getCallTypeTotal() ;

	//@Query(value=" select * from TBL_CALL_TYPE",nativeQuery=true)
	//List<CallType> findCallType();

	@Query(value = " select * from TBL_CALL_TYPE where CATEGORY=?1", nativeQuery = true)
	List<CallType> findCallTypeByCategory(String category);
	
	@Query(value=" select SUB_CATEGORY from TBL_CALL_TYPE",nativeQuery=true)
	List<String> findAllSubCategory();
	
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('High') and a.KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle) and a.STATUS_OF_COMPLAINT='Active' ",nativeQuery=true)
	public int getCallTypeHiegh(@Param("circle") String circle) ;
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('Medium') and a.KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle) and a.STATUS_OF_COMPLAINT='Active' ",nativeQuery=true)
	public int getCallTypeMedium(@Param("circle") String circle) ;
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('Low') and a.KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle) and a.STATUS_OF_COMPLAINT='Active' ",nativeQuery=true)
	public int getCallTypeLow(@Param("circle") String circle) ;
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('High','Medium','Low') and a.KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle) and a.STATUS_OF_COMPLAINT='Active' ",nativeQuery=true)
	public int getCallTypeTotal(@Param("circle") String circle) ;
	
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('High') and a.KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId) and a.STATUS_OF_COMPLAINT='Active' ",nativeQuery=true)
	public int getCallTypeHieghCMF(@Param("pfId") String pfId) ;
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('Medium') and a.KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId) and a.STATUS_OF_COMPLAINT='Active' ",nativeQuery=true)
	public int getCallTypeMediumCMF(@Param("pfId") String pfId) ;
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('Low') and a.KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId) and a.STATUS_OF_COMPLAINT='Active' ",nativeQuery=true)
	public int getCallTypeLowCMF(@Param("pfId") String pfId) ;
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('High','Medium','Low') and a.KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId) and a.STATUS_OF_COMPLAINT='Active' ",nativeQuery=true)
	public int getCallTypeTotalCMF(@Param("pfId") String pfId) ;
	
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('High') and a.KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId)) and a.STATUS_OF_COMPLAINT='Active' ",nativeQuery=true)
	public int getCallTypeHieghCMS(@Param("pfId") Set<String> pfId) ;
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('Medium') and a.KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId)) and a.STATUS_OF_COMPLAINT='Active' ",nativeQuery=true)
	public int getCallTypeMediumCMS(@Param("pfId") Set<String> pfId) ;
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('Low') and a.KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId)) and a.STATUS_OF_COMPLAINT='Active' ",nativeQuery=true)
	public int getCallTypeLowCMS(@Param("pfId") Set<String> pfId) ;
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('High','Medium','Low') and a.KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId)) and a.STATUS_OF_COMPLAINT='Active' ",nativeQuery=true)
	public int getCallTypeTotalCMS(@Param("pfId") Set<String> pfId) ;
	
	@Query(value=" select DISTINCT CATEGORY from TBL_CALL_TYPE ",nativeQuery=true)
	List<String> findCallType();
	
	@Query(value=" select DISTINCT SUB_CATEGORY from TBL_CALL_TYPE ",nativeQuery=true)
	List<String> findCallTypeSubCategory();


}
