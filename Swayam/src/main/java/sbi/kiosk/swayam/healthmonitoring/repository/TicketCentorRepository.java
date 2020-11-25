package sbi.kiosk.swayam.healthmonitoring.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.dto.TicketCentorDto;
import sbi.kiosk.swayam.common.entity.TicketCentor;

@Repository("ticketCentorRepository")
public interface TicketCentorRepository extends PagingAndSortingRepository<TicketCentor, Long> { 
	
	// changes for status of complaint is active only
//public Page<TicketCentor>	findByVendor(@Param("type") String type, Pageable pageable);
	Page<TicketCentor> findAllByStatusOfComplaint(@Param("type") String type, Pageable pageable);
	
 @Query(value="select * from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY " + 
              " and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN(:type) and a.STATUS_OF_COMPLAINT='Active' ", nativeQuery = true)
Page<TicketCentor> findAll(@Param("type") String type, Pageable pageable);

 @Query(value="select * from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY " + 
         " and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN(:high,:medium,:low) and a.STATUS_OF_COMPLAINT='Active' ", nativeQuery = true)
public Page<TicketCentor> findAllByRisk(@Param("high") String high,@Param("medium") String medium,@Param("low") String low, Pageable pageable);


   @Query(value = "  select CALL_CATEGORY,count(TICKET_ID) from TBL_TICKET_CENTRE GROUP BY CALL_CATEGORY", nativeQuery = true)
   List<Object[]> findAllCategory();
	
   
   @Query(value = "  select CALL_SUBCATEGORY ,count(TICKET_ID),CALL_CATEGORY from TBL_TICKET_CENTRE where CALL_CATEGORY=:category GROUP BY CALL_SUBCATEGORY, CALL_CATEGORY ", nativeQuery = true)
   List<Object[]> findByCategory(@Param("category") String category);
	
	@Query(value="select * from TBL_TICKET_CENTRE where CALL_CATEGORY=?1 and CALL_SUBCATEGORY=?2", nativeQuery = true)
	List<TicketCentor> findByCategoryAndSubCate(String category,String subCategory);
   
	
	// used  by gajanan 
	
	 @Query(value="select kiosk_id from tbl_ticket_centre tc where tc.kiosk_id=:kiosk_id",nativeQuery=true)
		public String findByKisokId(@Param("kiosk_id")String kisokid);
	    
	    @Query(value="select ticket_id from tbl_ticket_centre tc where tc.kiosk_id=:kiosk_id",nativeQuery=true)
	   	public String findByTicketId(@Param("kiosk_id")String kisokid);	
	    
	    Page<TicketCentor> findByCallSubCategory(@Param("callSubCategory") String callSubCategory, Pageable pageable);
	    
	    @Query(value="select * from TBL_TICKET_CENTRE WHERE KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle)", nativeQuery = true)
	Page<TicketCentor> findAllByCircle(@Param("circle") String circle, Pageable pageable);
	    
	    @Query(value="select * from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY " + 
	              " and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN(:type) and a.KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle)" , nativeQuery = true)
	Page<TicketCentor> findAllByCircleAndType(@Param("circle") String circle,@Param("type") String type, Pageable pageable);
	    
	    @Query(value="select * from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY " + 
	            " and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN(:high,:medium,:low) and a.KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle)", nativeQuery = true)
	   public Page<TicketCentor> findAllByRiskByCircle(@Param("circle") String circle,@Param("high") String high,@Param("medium") String medium,@Param("low") String low, Pageable pageable);
	    
	    @Query(value="select * from TBL_TICKET_CENTRE WHERE KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId)", nativeQuery = true)
		Page<TicketCentor> findAllByCMFUser(@Param("pfId") String pfId, Pageable pageable); 
	    
	    @Query(value="select * from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY " + 
	              " and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN(:type) AND a.KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId)", nativeQuery = true)
	    Page<TicketCentor> findAllByRiskAndCMFUser(@Param("type") String type,@Param("pfId") String pfId, Pageable pageable);
	    
	    @Query(value="select * from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY " + 
	            " and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN(:high,:medium,:low) AND a.KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId)", nativeQuery = true)
	   public Page<TicketCentor> findAllByAllRiskAndCMFUser(@Param("high") String high,@Param("medium") String medium,@Param("low") String low,@Param("pfId") String pfId,Pageable pageable);
	   
	    @Query(value="select * from tbl_ticket_centre where CALL_SUBCATEGORY=:callSubCategory AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId)",nativeQuery=true)
	    Page<TicketCentor> findByCallSubCategoryAndCMFUser(@Param("callSubCategory") String callSubCategory,@Param("pfId") String pfId, Pageable pageable);
	    
	    @Query(value = "select CALL_CATEGORY,count(TICKET_ID) from TBL_TICKET_CENTRE WHERE KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId) GROUP BY CALL_CATEGORY", nativeQuery = true)
	    List<Object[]> findAllCategoryByCMF(@Param("pfId") String pfId);
	    
	    @Query(value = "select CALL_SUBCATEGORY ,count(TICKET_ID),CALL_CATEGORY from TBL_TICKET_CENTRE where KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId)) AND CALL_CATEGORY=:category GROUP BY CALL_SUBCATEGORY, CALL_CATEGORY ", nativeQuery = true)
	    List<Object[]> findByCategoryCMF(@Param("category") String category,@Param("pfId") Set<String> pfId);
	    
	    @Query(value="select * from TBL_TICKET_CENTRE WHERE KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))", nativeQuery = true)
		Page<TicketCentor> findAllByCMFUserForCMS(@Param("pfId") Set<String> pfId, Pageable pageable); 
	    
	    @Query(value = "select CALL_CATEGORY,count(TICKET_ID) from TBL_TICKET_CENTRE WHERE KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId)) GROUP BY CALL_CATEGORY", nativeQuery = true)
	    List<Object[]> findAllCategoryByCMS(@Param("pfId") Set<String> pfId);
	    
	    @Query(value="select * from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY " + 
	              " and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN(:type) AND a.KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))", nativeQuery = true)
	    Page<TicketCentor> findAllByRiskAndCMSUser(@Param("type") String type,@Param("pfId") Set<String> pfId, Pageable pageable);
	    	    
	    @Query(value="select * from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY " + 
	            " and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN(:high,:medium,:low) AND a.KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))", nativeQuery = true)
	   public Page<TicketCentor> findAllByAllRiskAndCMSUser(@Param("high") String high,@Param("medium") String medium,@Param("low") String low,@Param("pfId") Set<String> pfId,Pageable pageable);
	   
	    @Query(value="select * from tbl_ticket_centre where CALL_SUBCATEGORY=:callSubCategory AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))",nativeQuery=true)
	    Page<TicketCentor> findByCallSubCategoryAndCMSUser(@Param("callSubCategory") String callSubCategory,@Param("pfId") Set<String> pfId, Pageable pageable);
	    
	    @Query(value="select * from TBL_TICKET_CENTRE WHERE KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))", nativeQuery = true)
		Page<TicketCentor> findAllByCMSUser(@Param("pfId") Set<String> pfId, Pageable pageable); 
	    
	    // cc user for PDF data
	    @Query(value="select * from TBL_TICKET_CENTRE where STATUS_OF_COMPLAINT='Active'  ", nativeQuery = true)
	    List<TicketCentor> findAll();
	    
	    @Query(value="select * from TBL_TICKET_CENTRE WHERE KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId)", nativeQuery = true)
		List<TicketCentor> findAllListByCMFUser(@Param("pfId") String pfId);
	    
	    @Query(value="select * from TBL_TICKET_CENTRE WHERE KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))", nativeQuery = true)
		List<TicketCentor> findAllListByCMFUserForCMS(@Param("pfId") Set<String> pfId);
	    
	    @Query(value="select * from TBL_TICKET_CENTRE WHERE KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle)", nativeQuery = true)
		List<TicketCentor> findAllListByCircle(@Param("circle") String circle);
	    
	    //
	    
	    @Query(value="select kiosk_id from tbl_ticket_centre tc where tc.kiosk_id=:kiosk_id and tc.CALL_SUBCATEGORY=:subCategory and tc.STATUS_OF_COMPLAINT=:status ",nativeQuery=true)
		public String findByKisokIdAndCallSubCategoryAndStatus(@Param("kiosk_id") String kisokid,@Param("subCategory") String subCategory,@Param("status") String status);
	    
	    @Query(value="select kiosk_id from tbl_ticket_centre tc where tc.kiosk_id=:kiosk_id and tc.CALL_SUBCATEGORY=:subCategory ",nativeQuery=true)
		public String findByKisokIdAndCallSubCategory(@Param("kiosk_id") String kisokid,@Param("subCategory") String subCategory);
	    
}
