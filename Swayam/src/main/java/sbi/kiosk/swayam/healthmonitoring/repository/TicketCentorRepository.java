package sbi.kiosk.swayam.healthmonitoring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.Requests;
import sbi.kiosk.swayam.common.entity.TicketCentor;

@Repository("ticketCentorRepository")
public interface TicketCentorRepository extends PagingAndSortingRepository<TicketCentor, Long> { 
	
	
//public Page<TicketCentor>	findByVendor(@Param("type") String type, Pageable pageable);
	
	
 @Query(value="select * from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY " + 
              " and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN(:type)", nativeQuery = true)
Page<TicketCentor> findAll(@Param("type") String type, Pageable pageable);

 @Query(value="select * from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY " + 
         " and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and  B.RISK IN(:high,:medium,:low)", nativeQuery = true)
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
}
