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

@Repository("ticketCentorSearchTextRepository")
public interface TicketCentorSearchTextRepository extends PagingAndSortingRepository<TicketCentor, Long> { 
	
    @Query(value = " SELECT * from TBL_TICKET_CENTRE where  STATUS_OF_COMPLAINT in(:type)  ORDER BY call_log_date DESC ", nativeQuery = true,countQuery="SELECT count(*) from TBL_TICKET_CENTRE where  STATUS_OF_COMPLAINT in(:type)  ORDER BY call_log_date DESC")
	Page<TicketCentor> findAllByStatus(@Param("type") String type, Pageable pageable);
	
     @Query(value="select * from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY " + 
              " and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN(:type) and a.STATUS_OF_COMPLAINT='Active' ORDER BY a.call_log_date DESC ", nativeQuery = true)
   Page<TicketCentor> findAll(@Param("type") String type, Pageable pageable);

   @Query(value="select * from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY " + 
         " and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN(:high,:medium,:low) and a.STATUS_OF_COMPLAINT='Active' ORDER BY a.call_log_date DESC ", nativeQuery = true)
   public Page<TicketCentor> findAllByRisk(@Param("high") String high,@Param("medium") String medium,@Param("low") String low, Pageable pageable);


   @Query(value = "  select CALL_CATEGORY,count(TICKET_ID) from TBL_TICKET_CENTRE GROUP BY CALL_CATEGORY", nativeQuery = true)
   List<Object[]> findAllCategory();
	
   
   @Query(value = "  select CALL_SUBCATEGORY ,count(TICKET_ID),CALL_CATEGORY from TBL_TICKET_CENTRE where CALL_CATEGORY=:category GROUP BY CALL_SUBCATEGORY, CALL_CATEGORY ", nativeQuery = true)
   List<Object[]> findByCategory(@Param("category") String category);
	
	@Query(value="select * from TBL_TICKET_CENTRE where CALL_CATEGORY=?1 and CALL_SUBCATEGORY=?2 ORDER BY call_log_date DESC", nativeQuery = true)
	List<TicketCentor> findByCategoryAndSubCate(String category,String subCategory);
   
	
	// used  by gajanan 
	
	 @Query(value="select kiosk_id from tbl_ticket_centre tc where tc.kiosk_id=:kiosk_id",nativeQuery=true)
		public String findByKisokId(@Param("kiosk_id")String kisokid);
	    
	    @Query(value="select ticket_id from tbl_ticket_centre tc where tc.kiosk_id=:kiosk_id",nativeQuery=true)
	   	public String findByTicketId(@Param("kiosk_id")String kisokid);	
	    
	    Page<TicketCentor> findByCallSubCategory(@Param("callSubCategory") String callSubCategory, Pageable pageable);
	   
	    
	    // Circle user
	    
	    
	    @Query(value="select * from TBL_TICKET_CENTRE WHERE KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle) and STATUS_OF_COMPLAINT='Active' AND "
	    		+ " KIOSK_ID=UPPER(:searchText) OR "
	    	   	+ " (KIOSK_ID=:searchText OR BRANCH_CODE=:searchText) "
	    		+ " ORDER BY call_log_date DESC ", nativeQuery = true)
	Page<TicketCentor> findAllByCircle(@Param("circle") String circle,@Param("searchText") String searchText, Pageable pageable);
	  
	    @Query(value="select * from TBL_TICKET_CENTRE a  INNER JOIN tbl_CALL_TYPE b  on a.CALL_CATEGORY=b.CATEGORY " + 
	              "  and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  where  b.RISK IN(:type) "
	              + " and a.KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle) and a.STATUS_OF_COMPLAINT='Active' AND "
	              + " a.KIOSK_ID=UPPER(:searchText) OR "
		          + " (a.KIOSK_ID=:searchText OR a.BRANCH_CODE=:searchText) "  
	              + " ORDER BY a.call_log_date DESC" , nativeQuery = true)
	Page<TicketCentor> findAllByCircleAndType(@Param("circle") String circle,@Param("type") String type, @Param("searchText") String searchText,Pageable pageable);
	    
	   
	    @Query(value="select * from TBL_TICKET_CENTRE a  INNER JOIN tbl_CALL_TYPE b  on a.CALL_CATEGORY=b.CATEGORY " + 
	            " and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  where  b.RISK IN(:high,:medium,:low) and a.KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle) and a.STATUS_OF_COMPLAINT='Active' AND "
	            + "	  a.KIOSK_ID=UPPER(:searchText) OR "
	            + "	  (a.KIOSK_ID=:searchText OR a.BRANCH_CODE=:searchText) "
	            + " ORDER BY a.call_log_date DESC ", nativeQuery = true)
	   public Page<TicketCentor> findAllByRiskByCircle(@Param("circle") String circle,@Param("high") String high,@Param("medium") String medium,@Param("low") String low, @Param("searchText") String searchText, Pageable pageable);
	    
	  
	    @Query(value = " select * from TBL_TICKET_CENTRE where AGEING<=4  and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle)  and STATUS_OF_COMPLAINT='Active' "
	    		+ " AND  KIOSK_ID=UPPER(:searchText) OR "
		 		+ "	(KIOSK_ID=:searchText OR BRANCH_CODE=:searchText) "
	    		+ " ORDER BY call_log_date DESC  ", nativeQuery = true)
		public Page<TicketCentor> findAllTicketCentor4HourByCircle(@Param("circle") String circle, @Param("searchText") String searchText,Pageable pageable);
		
	@Query(value = " SELECT * from TBL_TICKET_CENTRE where (AGEING>4 and AGEING<=8) and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle) and STATUS_OF_COMPLAINT='Active' "
			+ " AND  KIOSK_ID=UPPER(:searchText) OR "
	 		+ "	(KIOSK_ID=:searchText OR BRANCH_CODE=:searchText) "
			+ " ORDER BY call_log_date DESC  ", nativeQuery = true)
	public Page<TicketCentor> findAllTicketCentor1DaysByCircle(@Param("circle") String circle, @Param("searchText") String searchText,Pageable pageable);

	@Query(value = " SELECT * from TBL_TICKET_CENTRE where (AGEING>8 and AGEING<=24) and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle) and STATUS_OF_COMPLAINT='Active' "
			+ " AND  KIOSK_ID=UPPER(:searchText) OR "
	 		+ "	(KIOSK_ID=:searchText OR BRANCH_CODE=:searchText) "
			+ " ORDER BY call_log_date DESC ", nativeQuery = true)
	public Page<TicketCentor> findAllTicketCentor3DaysLessByCircle(@Param("circle") String circle, @Param("searchText") String searchText,Pageable pageable);

	@Query(value = " SELECT * from TBL_TICKET_CENTRE where  AGEING>24 and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle) and STATUS_OF_COMPLAINT='Active' "
			+ " AND  KIOSK_ID=UPPER(:searchText) OR "
	 		+ "	(KIOSK_ID=:searchText OR BRANCH_CODE=:searchText) "
			+ " ORDER BY call_log_date DESC  ", 
	countQuery=" SELECT count(*) from TBL_TICKET_CENTRE where  AGEING>24 and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle) and STATUS_OF_COMPLAINT='Active' "
			+ " AND  KIOSK_ID=UPPER(:searchText) OR "
	 		+ "	(KIOSK_ID=:searchText OR BRANCH_CODE=:searchText) "
			+ " ORDER BY call_log_date DESC  ",
	nativeQuery = true)
	public Page<TicketCentor> findAllTicketCentor3DaysGreaterByCircle(@Param("circle") String circle, @Param("searchText") String searchText,Pageable pageable);


	    
	    
	    
	    
	    // Search Text CMF
	    
	    @Query(value="select * from TBL_TICKET_CENTRE WHERE KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId) and STATUS_OF_COMPLAINT='Active' AND "
	    	   		+ " KIOSK_ID=UPPER(:searchText) OR "
	    	   		+ " (KIOSK_ID=:searchText OR BRANCH_CODE=:searchText)  ORDER BY call_log_date DESC ", nativeQuery = true)
		Page<TicketCentor> findAllByCMFUser(@Param("pfId") String pfId,@Param("searchText") String searchText, Pageable pageable); 
	    
	    
	    @Query(value="select * from TBL_TICKET_CENTRE a INNER JOIN tbl_CALL_TYPE b on  a.CALL_CATEGORY=b.CATEGORY "
	    		+ "	               and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  where   B.RISK IN(:type) AND a.KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING "
	              + "WHERE PF_ID=:pfId) and a.STATUS_OF_COMPLAINT='Active' AND " 
	    	   		+ " a.KIOSK_ID=UPPER(:searchText) OR "
	    	   		+ " (a.KIOSK_ID=:searchText OR a.BRANCH_CODE=:searchText) ORDER BY a.call_log_date DESC ", nativeQuery = true)
	    Page<TicketCentor> findAllByRiskAndCMFUser(@Param("type") String type,@Param("pfId") String pfId,@Param("searchText") String searchText, Pageable pageable);
	    
	    
	    @Query(value="select * from TBL_TICKET_CENTRE a INNER JOIN tbl_CALL_TYPE b on  a.CALL_CATEGORY=b.CATEGORY "
	    		+ "	 and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  where  B.RISK IN(:high,:medium,:low) AND a.KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId) "
	            + "and a.STATUS_OF_COMPLAINT='Active' AND "
	            + "	  a.KIOSK_ID=UPPER(:searchText) OR "
	            + "	  (a.KIOSK_ID=:searchText OR a.BRANCH_CODE=:searchText) ORDER BY a.call_log_date DESC ", nativeQuery = true)
	   public Page<TicketCentor> findAllByAllRiskAndCMFUser(@Param("high") String high,@Param("medium") String medium,@Param("low") String low,@Param("pfId") String pfId,@Param("searchText") String searchText,Pageable pageable);
	   
	   

	 // CMF User List
	 @Query(value = " SELECT *  from TBL_TICKET_CENTRE where  AGEING<=4  and KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING  WHERE PF_ID=:pfId) and STATUS_OF_COMPLAINT='Active' "
	 		+ " AND  KIOSK_ID=UPPER(:searchText) OR "
	 		+ "	(KIOSK_ID=:searchText OR BRANCH_CODE=:searchText) ORDER BY call_log_date DESC", nativeQuery = true)
	 public Page<TicketCentor> findAllTicketCentor4HourAndCMFUser(@Param("pfId") String pfId,@Param("searchText") String searchText,Pageable pageable);

	 @Query(value = " SELECT * from TBL_TICKET_CENTRE where  (AGEING>4 and AGEING<=8) AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId) and STATUS_OF_COMPLAINT='Active' "
			 + " AND  KIOSK_ID=UPPER(:searchText) OR "
			 + "	(KIOSK_ID=:searchText OR BRANCH_CODE=:searchText) "
	 		+ " ORDER BY call_log_date DESC", nativeQuery = true)
	 public Page<TicketCentor> findAllTicketCentor1DaysAndCMFUser(@Param("pfId") String pfId,@Param("searchText") String searchText,Pageable pageable);

	 @Query(value = " SELECT * from TBL_TICKET_CENTRE where  (AGEING>8 and AGEING<=24) AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId) and STATUS_OF_COMPLAINT='Active' "
			 + " AND  KIOSK_ID=UPPER(:searchText) OR "
			 + "	(KIOSK_ID=:searchText OR BRANCH_CODE=:searchText) "
	 		 + " ORDER BY call_log_date DESC", nativeQuery = true)
	 public Page<TicketCentor> findAllTicketCentor3DaysLessAndCMFUser(@Param("pfId") String pfId,@Param("searchText") String searchText,Pageable pageable);

	 @Query(value = " SELECT * from TBL_TICKET_CENTRE where  AGEING>24  AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId) and STATUS_OF_COMPLAINT='Active' "
			+ " AND  KIOSK_ID=UPPER(:searchText) OR "
		 	+ "	(KIOSK_ID=:searchText OR BRANCH_CODE=:searchText) "
	 		+ " ORDER BY call_log_date DESC", nativeQuery = true,
	 countQuery="select count(*) from TBL_TICKET_CENTRE where  AGEING>24  AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId) and STATUS_OF_COMPLAINT='Active' "
			 + " AND  KIOSK_ID=UPPER(:searchText) OR "
			 + " (KIOSK_ID=:searchText OR BRANCH_CODE=:searchText) "
	 		+ " ORDER BY call_log_date DESC ")
	 public Page<TicketCentor> findAllTicketCentor3DaysGreaterAndCMFUser(@Param("pfId") String pfId,@Param("searchText") String searchText,Pageable pageable);

	 @Query(value = " SELECT * from TBL_TICKET_CENTRE where KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId) and STATUS_OF_COMPLAINT='Active' "
			 + " AND  KIOSK_ID=UPPER(:searchText) OR "
			 + " (KIOSK_ID=:searchText OR BRANCH_CODE=:searchText) ORDER BY call_log_date DESC", nativeQuery = true,
	 countQuery=" SELECT count(*) from TBL_TICKET_CENTRE where KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId) and STATUS_OF_COMPLAINT='Active' "
			 + " AND  KIOSK_ID=UPPER(:searchText) OR "
			 + "	(KIOSK_ID=:searchText OR BRANCH_CODE=:searchText) "
	 		 + " ORDER BY call_log_date DESC")
	 public Page<TicketCentor> findAllTicketTotalListAndCMFUser(@Param("pfId") String pfId,@Param("searchText") String searchText,Pageable pageable);
	    
	    @Query(value="select * from tbl_ticket_centre where CALL_SUBCATEGORY=:callSubCategory AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId) and STATUS_OF_COMPLAINT='Active' "
	    		+ " AND  KIOSK_ID=UPPER(:searchText) OR "
	    		+ "	(KIOSK_ID=:searchText OR BRANCH_CODE=:searchText) "
	    		+ " ORDER BY call_log_date DESC ",nativeQuery=true)
	    Page<TicketCentor> findByCallSubCategoryAndCMFUser(@Param("callSubCategory") String callSubCategory,@Param("pfId") String pfId, @Param("searchText") String searchText,Pageable pageable);
	   
	    
	    
	    
	    
	    
	    
	    
	    @Query(value = "select CALL_CATEGORY,count(TICKET_ID) from TBL_TICKET_CENTRE WHERE STATUS_OF_COMPLAINT='Active' and  KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId) GROUP BY CALL_CATEGORY", nativeQuery = true)
	    List<Object[]> findAllCategoryByCMF(@Param("pfId") String pfId);
	    
	    @Query(value = "select CALL_SUBCATEGORY ,count(TICKET_ID),CALL_CATEGORY from TBL_TICKET_CENTRE where STATUS_OF_COMPLAINT='Active' and KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId)) AND CALL_CATEGORY=:category GROUP BY CALL_SUBCATEGORY, CALL_CATEGORY ", nativeQuery = true)
	    List<Object[]> findByCategoryCMF(@Param("category") String category,@Param("pfId") Set<String> pfId);
	    
	  
	    // CMS Search Text
	    
	       @Query(value="select * from TBL_TICKET_CENTRE a  INNER JOIN tbl_CALL_TYPE b on  a.CALL_CATEGORY=b.CATEGORY "
	    		+ "	and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  where  B.RISK IN(:type) AND a.KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId)) and a.STATUS_OF_COMPLAINT='Active' AND "
	    	   	+ " a.KIOSK_ID=UPPER(:searchText) OR "
	    	   	+ " (a.KIOSK_ID=:searchText OR a.BRANCH_CODE=:searchText) "
	            + " ORDER BY a.call_log_date DESC ", nativeQuery = true)
	    Page<TicketCentor> findAllByRiskAndCMSUser(@Param("type") String type,@Param("pfId") Set<String> pfId,@Param("searchText") String searchText ,Pageable pageable);
	
	    
	    
	    @Query(value="select * from TBL_TICKET_CENTRE WHERE KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId)) and STATUS_OF_COMPLAINT='Active' AND "
	    		+ " KIOSK_ID=UPPER(:searchText) OR "
	    	   	+ " (KIOSK_ID=:searchText OR BRANCH_CODE=:searchText) "
	    		+ " ORDER BY call_log_date DESC ", nativeQuery = true)
		Page<TicketCentor> findAllByCMFUserForCMS(@Param("pfId") Set<String> pfId,@Param("searchText") String searchText , Pageable pageable); 
	    
	    
	    
	    
	    
	    @Query(value = "select CALL_CATEGORY,count(TICKET_ID) from TBL_TICKET_CENTRE WHERE  STATUS_OF_COMPLAINT='Active' and KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId)) GROUP BY CALL_CATEGORY", nativeQuery = true)
	    List<Object[]> findAllCategoryByCMS(@Param("pfId") Set<String> pfId);
	    
	       	    
	    @Query(value="select * from TBL_TICKET_CENTRE a  INNER JOIN tbl_CALL_TYPE b on  a.CALL_CATEGORY=b.CATEGORY "
	    		+ "	and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  where  B.RISK IN(:high,:medium,:low) AND a.KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId)) and a.STATUS_OF_COMPLAINT='Active' AND "
	        	+ " a.KIOSK_ID=UPPER(:searchText) OR "
	    	   	+ " (a.KIOSK_ID=:searchText OR a.BRANCH_CODE=:searchText) "
	            + " ORDER BY a.call_log_date DESC ", nativeQuery = true)
	   public Page<TicketCentor> findAllByAllRiskAndCMSUser(@Param("high") String high,@Param("medium") String medium,@Param("low") String low,@Param("pfId") Set<String> pfId,@Param("searchText") String searchText,Pageable pageable);
	   
	    @Query(value="select * from tbl_ticket_centre where CALL_SUBCATEGORY=:callSubCategory AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId)) and STATUS_OF_COMPLAINT='Active' "
	    		+ " AND  KIOSK_ID=UPPER(:searchText) OR "
				+ " (KIOSK_ID=:searchText OR BRANCH_CODE=:searchText) "
	    		+ " ORDER BY call_log_date DESC ",nativeQuery=true)
	    Page<TicketCentor> findByCallSubCategoryAndCMSUser(@Param("callSubCategory") String callSubCategory,@Param("pfId") Set<String> pfId,@Param("searchText") String searchText, Pageable pageable);
	    
	    @Query(value="select * from TBL_TICKET_CENTRE WHERE KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId)) and STATUS_OF_COMPLAINT='Active' "
	    		+ " AND  KIOSK_ID=UPPER(:searchText) OR "
				+ " (KIOSK_ID=:searchText OR BRANCH_CODE=:searchText)"
	    		+ " ORDER BY call_log_date DESC ", nativeQuery = true,
	    		countQuery="select count(*) from TBL_TICKET_CENTRE WHERE KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId)) and STATUS_OF_COMPLAINT='Active' "
	    	    		+ " AND  KIOSK_ID=UPPER(:searchText) OR "
	    				+ " (KIOSK_ID=:searchText OR BRANCH_CODE=:searchText) "
	    	    		+ " ORDER BY call_log_date DESC "
	    		)
		Page<TicketCentor> findAllByCMSUser(@Param("pfId") Set<String> pfId,@Param("searchText") String searchText , Pageable pageable); 
	    
	    
	    
	    
	    @Query(value = " SELECT *  from TBL_TICKET_CENTRE where AGEING<=4  AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))  and STATUS_OF_COMPLAINT='Active' "
	    		+ " AND  KIOSK_ID=UPPER(:searchText) OR "
				+ " (KIOSK_ID=:searchText OR BRANCH_CODE=:searchText) "
	    		+ " ORDER BY call_log_date DESC ", nativeQuery = true)
	    public Page<TicketCentor> findAllTicketCentor4HourAndCMSUser(@Param("pfId") Set<String> pfId,@Param("searchText") String searchText,Pageable pageable);

	    @Query(value = " SELECT * from TBL_TICKET_CENTRE where  (AGEING>4 and AGEING<=8) AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))  and STATUS_OF_COMPLAINT='Active' "
	    		+ " AND  KIOSK_ID=UPPER(:searchText) OR "
				+ " (KIOSK_ID=:searchText OR BRANCH_CODE=:searchText) "
	    		+ " ORDER BY call_log_date DESC ", nativeQuery = true)
	    public Page<TicketCentor> findAllTicketCentor1DaysAndCMSUser(@Param("pfId") Set<String> pfId,@Param("searchText") String searchText,Pageable pageable);

	    @Query(value = " SELECT * from TBL_TICKET_CENTRE where  (AGEING>8 and AGEING<=24) AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))  and STATUS_OF_COMPLAINT='Active' "
	    		+ " AND  KIOSK_ID=UPPER(:searchText) OR "
				+ " (KIOSK_ID=:searchText OR BRANCH_CODE=:searchText) "
	    		+ " ORDER BY call_log_date DESC ", nativeQuery = true)
	    public Page<TicketCentor> findAllTicketCentor3DaysLessAndCMSUser(@Param("pfId") Set<String> pfId,@Param("searchText") String searchText,Pageable pageable);

	    @Query(value = " SELECT * from TBL_TICKET_CENTRE where  AGEING>24 AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))  and STATUS_OF_COMPLAINT='Active' "
	    		+ " AND  KIOSK_ID=UPPER(:searchText) OR "
				+ " (KIOSK_ID=:searchText OR BRANCH_CODE=:searchText) "
	    		+ " ORDER BY call_log_date DESC ", nativeQuery = true
	    ,countQuery=" SELECT count(*) from TBL_TICKET_CENTRE where  AGEING>24 AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))  and STATUS_OF_COMPLAINT='Active' "
	    		+ " AND  KIOSK_ID=UPPER(:searchText) OR "
				+ " (KIOSK_ID=:searchText OR BRANCH_CODE=:searchText) "
	    		+ " ORDER BY call_log_date DESC " )
	    public Page<TicketCentor> findAllTicketCentor3DaysGreaterAndCMSUser(@Param("pfId") Set<String> pfId,@Param("searchText") String searchText,Pageable pageable);
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    // cc user for PDF data
	    @Query(value="select * from TBL_TICKET_CENTRE where STATUS_OF_COMPLAINT='Active' ORDER BY call_log_date DESC ", nativeQuery = true)
	    List<TicketCentor> findAll();
	    
	    @Query(value="select * from TBL_TICKET_CENTRE WHERE KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId) and STATUS_OF_COMPLAINT='Active' ", nativeQuery = true)
		List<TicketCentor> findAllListByCMFUser(@Param("pfId") String pfId);
	    
	    @Query(value="select * from TBL_TICKET_CENTRE WHERE KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId)) and STATUS_OF_COMPLAINT='Active' ", nativeQuery = true)
		List<TicketCentor> findAllListByCMFUserForCMS(@Param("pfId") Set<String> pfId);
	    
	    @Query(value="select * from TBL_TICKET_CENTRE WHERE KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle) and STATUS_OF_COMPLAINT='Active' ", nativeQuery = true)
		List<TicketCentor> findAllListByCircle(@Param("circle") String circle);
	    
	    //
	    
	    @Query(value="select kiosk_id from tbl_ticket_centre tc where tc.kiosk_id=:kiosk_id and tc.CALL_SUBCATEGORY=:subCategory and tc.STATUS_OF_COMPLAINT=:status ",nativeQuery=true)
		public String findByKisokIdAndCallSubCategoryAndStatus(@Param("kiosk_id") String kisokid,@Param("subCategory") String subCategory,@Param("status") String status);
	    
	    @Query(value="select kiosk_id from tbl_ticket_centre tc where tc.kiosk_id=:kiosk_id and tc.CALL_SUBCATEGORY=:subCategory ",nativeQuery=true)
		public String findByKisokIdAndCallSubCategory(@Param("kiosk_id") String kisokid,@Param("subCategory") String subCategory);
	    
}
