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
	
	
	
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('High')",nativeQuery=true)
	public int getCallTypeHiegh() ;
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('Medium')",nativeQuery=true)
	public int getCallTypeMedium() ;
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('Low')",nativeQuery=true)
	public int getCallTypeLow() ;
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('High','Medium','Low')",nativeQuery=true)
	public int getCallTypeTotal() ;

	//@Query(value=" select * from TBL_CALL_TYPE",nativeQuery=true)
	//List<CallType> findCallType();

	@Query(value = " select * from TBL_CALL_TYPE where CATEGORY=?1", nativeQuery = true)
	List<CallType> findCallTypeByCategory(String category);
	
	@Query(value=" select SUB_CATEGORY from TBL_CALL_TYPE",nativeQuery=true)
	List<String> findAllSubCategory();
	
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('High') and a.KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle)",nativeQuery=true)
	public int getCallTypeHiegh(@Param("circle") String circle) ;
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('Medium') and a.KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle)",nativeQuery=true)
	public int getCallTypeMedium(@Param("circle") String circle) ;
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('Low') and a.KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle)",nativeQuery=true)
	public int getCallTypeLow(@Param("circle") String circle) ;
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('High','Medium','Low') and a.KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle)",nativeQuery=true)
	public int getCallTypeTotal(@Param("circle") String circle) ;
	
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('High') and a.KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId)",nativeQuery=true)
	public int getCallTypeHieghCMF(@Param("pfId") String pfId) ;
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('Medium') and a.KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId)",nativeQuery=true)
	public int getCallTypeMediumCMF(@Param("pfId") String pfId) ;
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('Low') and a.KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId)",nativeQuery=true)
	public int getCallTypeLowCMF(@Param("pfId") String pfId) ;
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('High','Medium','Low') and a.KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId)",nativeQuery=true)
	public int getCallTypeTotalCMF(@Param("pfId") String pfId) ;
	
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('High') and a.KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))",nativeQuery=true)
	public int getCallTypeHieghCMF(@Param("pfId") Set<String> pfId) ;
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('Medium') and a.KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))",nativeQuery=true)
	public int getCallTypeMediumCMF(@Param("pfId") Set<String> pfId) ;
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('Low') and a.KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))",nativeQuery=true)
	public int getCallTypeLowCMF(@Param("pfId") Set<String> pfId) ;
	@Query(value="select count(a.ticket_id) from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN('High','Medium','Low') and a.KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))",nativeQuery=true)
	public int getCallTypeTotalCMF(@Param("pfId") Set<String> pfId) ;
	
	@Query(value=" select DISTINCT CATEGORY from TBL_CALL_TYPE ",nativeQuery=true)
	List<String> findCallType();
	
	@Query(value=" select DISTINCT SUB_CATEGORY from TBL_CALL_TYPE ",nativeQuery=true)
	List<String> findCallTypeSubCategory();


}
