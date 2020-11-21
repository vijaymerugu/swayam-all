package sbi.kiosk.swayam.healthmonitoring.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.TicketCentor;

@Repository("ticketCentorAgeingRepository")
public interface TicketCentorAgeingRepository extends CrudRepository<TicketCentor, String> {
	/*	
	@Query(value = " SELECT count(*)  from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-2/24), 'DD-MM-YY HH24:MI:SS' ) "
						+ "  and  to_date(to_char(trunc(sysdate-4/24),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS')  ", nativeQuery = true)
	public int find2_4HoursCount() ;
	
	@Query(value=" SELECT  count(*)  from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-20/24), "
						+ "'DD-MM-YY HH24:MI:SS' )	and  to_date(to_char(trunc(sysdate-1),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS') ",nativeQuery=true)
	public int find_1_DaysCount();
		
	@Query(value=" SELECT  count(*)  from TBL_TICKET_CENTRE where  AGEING between trunc(sysdate-3)	and  trunc(sysdate-22/24)  ",nativeQuery=true)
    public int find_3_Days_LessCount();
	
	@Query(value=" SELECT  count(*)  from TBL_TICKET_CENTRE where  AGEING >=trunc(sysdate-3)  ",nativeQuery=true)
    public int find_3_Days_GreaterThanCount();
	
	//@Query(value=" SELECT  count(*)  from TBL_TICKET_CENTRE where  AGEING between trunc(sysdate-3)	and  trunc(sysdate-1/24)  ",nativeQuery=true)
  
	@Query(value="SELECT  count(*)  from TBL_TICKET_CENTRE where  AGEING IS NOT NULL", nativeQuery=true)
	public int findTotalCount();


	//all ticket list
	@Query(value = " SELECT *  from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-2/24), 'DD-MM-YY HH24:MI:SS' ) "
			+ "  and  to_date(to_char(trunc(sysdate-4/24),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS')  ", nativeQuery = true)

	public Page<TicketCentor> findAllTicketCentor4Hour(Pageable pageable);
	

	@Query(value = " SELECT * from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-20/24), "
						+ "'DD-MM-YY HH24:MI:SS' )	and  to_date(to_char(trunc(sysdate-1),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS') ", nativeQuery = true)
	public Page<TicketCentor> findAllTicketCentor1Days(Pageable pageable);
	
	
	@Query(value = " SELECT * from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-3), 'DD-MM-YY HH24:MI:SS' )	"
			+ "and  to_date(to_char(trunc(sysdate-22/24),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS') ", nativeQuery = true)
	public Page<TicketCentor> findAllTicketCentor3DaysLess(Pageable pageable);
	
	
	@Query(value = " SELECT * from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-3), 'DD-MM-YY HH24:MI:SS' )	"
			+ " and  to_date(to_char(trunc(sysdate-22/72),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS')  ", nativeQuery = true)
	public Page<TicketCentor> findAllTicketCentor3DaysGreater(Pageable pageable);
	
	//@Query(value = " SELECT  *  from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-2/24), 'DD-MM-YY HH24:MI:SS' ) and  to_date(to_char(trunc(sysdate-3),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS') ", nativeQuery = true)
	@Query(value="SELECT  *  from TBL_TICKET_CENTRE where  AGEING is not null", nativeQuery=true)
	public Page<TicketCentor> findAllTicketCentorTotal(Pageable pageable);
	
	
	@Query(value = " SELECT *  from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-2/24), 'DD-MM-YY HH24:MI:SS' ) "
			+ "  and  to_date(to_char(trunc(sysdate-4/24),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS') and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle)", nativeQuery = true)
	public Page<TicketCentor> findAllTicketCentor4HourByCircle(@Param("circle") String circle,Pageable pageable);
	
	@Query(value = " SELECT * from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-20/24), "
			+ "'DD-MM-YY HH24:MI:SS' )	and  to_date(to_char(trunc(sysdate-1),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS') and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle)", nativeQuery = true)
	public Page<TicketCentor> findAllTicketCentor1DaysByCircle(@Param("circle") String circle,Pageable pageable);
	
	@Query(value = " SELECT * from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-3), 'DD-MM-YY HH24:MI:SS' )	"
			+ "and  to_date(to_char(trunc(sysdate-22/24),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS') and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle)", nativeQuery = true)
	public Page<TicketCentor> findAllTicketCentor3DaysLessByCircle(@Param("circle") String circle,Pageable pageable);
	
	@Query(value = " SELECT * from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-3), 'DD-MM-YY HH24:MI:SS' )	"
			+ " and  to_date(to_char(trunc(sysdate-22/72),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS') and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle)", nativeQuery = true)
	public Page<TicketCentor> findAllTicketCentor3DaysGreaterByCircle(@Param("circle") String circle,Pageable pageable);
	
	@Query(value = " SELECT count(*)  from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-2/24), 'DD-MM-YY HH24:MI:SS' ) "
			+ "  and  to_date(to_char(trunc(sysdate-4/24),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS') and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle)", nativeQuery = true)
	public int find2_4HoursCount(@Param("circle") String circle) ;
	
	
	@Query(value=" SELECT  count(*)  from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-20/24), "
			+ "'DD-MM-YY HH24:MI:SS' )	and  to_date(to_char(trunc(sysdate-1),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS') and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle)",nativeQuery=true)
	public int find_1_DaysCount(@Param("circle") String circle);
	
	@Query(value=" SELECT  count(*)  from TBL_TICKET_CENTRE where  AGEING between trunc(sysdate-3)	and  trunc(sysdate-22/24) and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle)",nativeQuery=true)
    public int find_3_Days_LessCount(@Param("circle") String circle);
	
	@Query(value=" SELECT  count(*)  from TBL_TICKET_CENTRE where  AGEING >=trunc(sysdate-3) and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle)",nativeQuery=true)
    public int find_3_Days_GreaterThanCount(@Param("circle") String circle);
	
	@Query(value="SELECT  count(*)  from TBL_TICKET_CENTRE where  AGEING IS NOT NULL and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle)", nativeQuery=true)
	public int findTotalCount(@Param("circle") String circle);
	
	@Query(value = " SELECT *  from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-2/24), 'DD-MM-YY HH24:MI:SS' ) "
			+ "  and  to_date(to_char(trunc(sysdate-4/24),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS')  AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId)", nativeQuery = true)
	public Page<TicketCentor> findAllTicketCentor4HourAndCMFUser(@Param("pfId") String pfId,Pageable pageable);
	
	@Query(value = " SELECT * from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-20/24), "
			+ "'DD-MM-YY HH24:MI:SS' )	and  to_date(to_char(trunc(sysdate-1),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS') AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId)", nativeQuery = true)
	public Page<TicketCentor> findAllTicketCentor1DaysAndCMFUser(@Param("pfId") String pfId,Pageable pageable);
	
	@Query(value = " SELECT * from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-3), 'DD-MM-YY HH24:MI:SS' )	"
			+ "and  to_date(to_char(trunc(sysdate-22/24),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS') AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId)", nativeQuery = true)
	public Page<TicketCentor> findAllTicketCentor3DaysLessAndCMFUser(@Param("pfId") String pfId,Pageable pageable);
	
	@Query(value = " SELECT * from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-3), 'DD-MM-YY HH24:MI:SS' )	"
			+ " and  to_date(to_char(trunc(sysdate-22/72),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS') AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId)", nativeQuery = true)
	public Page<TicketCentor> findAllTicketCentor3DaysGreaterAndCMFUser(@Param("pfId") String pfId,Pageable pageable);
	
	@Query(value = " SELECT count(*)  from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-2/24), 'DD-MM-YY HH24:MI:SS' ) "
			+ "  and  to_date(to_char(trunc(sysdate-4/24),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS') and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId)", nativeQuery = true)
	public int find2_4HoursCountCMF(@Param("pfId") String pfId) ;
	
	
	@Query(value=" SELECT  count(*)  from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-20/24), "
			+ "'DD-MM-YY HH24:MI:SS' )	and  to_date(to_char(trunc(sysdate-1),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS') and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId)",nativeQuery=true)
	public int find_1_DaysCountCMF(@Param("pfId") String pfId);
	
	@Query(value=" SELECT  count(*)  from TBL_TICKET_CENTRE where  AGEING between trunc(sysdate-3)	and  trunc(sysdate-22/24) and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId)",nativeQuery=true)
    public int find_3_Days_LessCountCMF(@Param("pfId") String pfId);
	
	@Query(value=" SELECT  count(*)  from TBL_TICKET_CENTRE where  AGEING >=trunc(sysdate-3) and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId)",nativeQuery=true)
    public int find_3_Days_GreaterThanCountCMF(@Param("pfId") String pfId);
	
	@Query(value="SELECT  count(*)  from TBL_TICKET_CENTRE where  AGEING IS NOT NULL and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId)", nativeQuery=true)
	public int findTotalCountCMF(@Param("pfId") String pfId);
	
	@Query(value = " SELECT count(*)  from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-2/24), 'DD-MM-YY HH24:MI:SS' ) "
			+ "  and  to_date(to_char(trunc(sysdate-4/24),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS') and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))", nativeQuery = true)
	public int find2_4HoursCountCMF(@Param("pfId") Set<String> pfId) ;
	
	
	@Query(value=" SELECT  count(*)  from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-20/24), "
			+ "'DD-MM-YY HH24:MI:SS' )	and  to_date(to_char(trunc(sysdate-1),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS') and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))",nativeQuery=true)
	public int find_1_DaysCountCMF(@Param("pfId") Set<String> pfId);
	
	@Query(value=" SELECT  count(*)  from TBL_TICKET_CENTRE where  AGEING between trunc(sysdate-3)	and  trunc(sysdate-22/24) and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))",nativeQuery=true)
    public int find_3_Days_LessCountCMF(@Param("pfId") Set<String> pfId);
	
	@Query(value=" SELECT  count(*)  from TBL_TICKET_CENTRE where  AGEING >=trunc(sysdate-3) and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))",nativeQuery=true)
    public int find_3_Days_GreaterThanCountCMF(@Param("pfId") Set<String> pfId);
	
	@Query(value="SELECT  count(*)  from TBL_TICKET_CENTRE where  AGEING IS NOT NULL and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))", nativeQuery=true)
	public int findTotalCountCMF(@Param("pfId") Set<String> pfId);
	
	@Query(value = " SELECT *  from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-2/24), 'DD-MM-YY HH24:MI:SS' ) "
			+ "  and  to_date(to_char(trunc(sysdate-4/24),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS')  AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))", nativeQuery = true)
	public Page<TicketCentor> findAllTicketCentor4HourAndCMSUser(@Param("pfId") Set<String> pfId,Pageable pageable);
	
	@Query(value = " SELECT * from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-20/24), "
			+ "'DD-MM-YY HH24:MI:SS' )	and  to_date(to_char(trunc(sysdate-1),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS') AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))", nativeQuery = true)
	public Page<TicketCentor> findAllTicketCentor1DaysAndCMSUser(@Param("pfId") Set<String> pfId,Pageable pageable);
	
	@Query(value = " SELECT * from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-3), 'DD-MM-YY HH24:MI:SS' )	"
			+ "and  to_date(to_char(trunc(sysdate-22/24),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS') AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))", nativeQuery = true)
	public Page<TicketCentor> findAllTicketCentor3DaysLessAndCMSUser(@Param("pfId") Set<String> pfId,Pageable pageable);
	
	@Query(value = " SELECT * from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-3), 'DD-MM-YY HH24:MI:SS' )	"
			+ " and  to_date(to_char(trunc(sysdate-22/72),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS') AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))", nativeQuery = true)
	public Page<TicketCentor> findAllTicketCentor3DaysGreaterAndCMSUser(@Param("pfId") Set<String> pfId,Pageable pageable);
	
	*/

	
	/*

	@Query(value = "  select * from TBL_TICKET_CENTRE a  ,tbl_CALL_TYPE b  where a.CALL_CATEGORY=b.CATEGORY "
			+ " and a.CALL_SUBCATEGORY=b.SUB_CATEGORY  and b.RISK=?1 ", nativeQuery = true)
	public List<TicketCenterEnity> getAllTicketByCallCategory(String category);
	@Query(value = " select *  from TBL_TICKET_CENTRE where CALL_SUBCATEGORY=?1 and CALL_CATEGORY=?2 ", nativeQuery = true)
	public List<TicketCenterEnity> allTicketByCallSubCategory(String callSubCategory,String category);
	
	
	@Query(value = " SELECT * from TBL_TICKET_CENTRE where AGEING>sysdate-1/24 ", nativeQuery = true)
	public List<TicketCenterEnity> findAllTicketCentorTotalCount();
	
	@Query(value = " SELECT * from TBL_TICKET_CENTRE where AGEING>sysdate-1/24 ", nativeQuery = true)
	public List<TicketCenterEnity> findAllTicketCentor1LessHour();
	
	@Query(value =" SELECT * from TBL_TICKET_CENTRE where AGEING>sysdate-2/24 and TICKET_Id not in (SELECT  TICKET_ID from TBL_TICKET_CENTRE where AGEING>=sysdate-1/24) ", nativeQuery = true)
	public List<TicketCenterEnity> findAllTicketCentor1Hour();
	
	
	@Query(value = " SELECT * from TBL_TICKET_CENTRE where AGEING>sysdate-4/24 and TICKET_Id not in (SELECT  TICKET_ID from TBL_TICKET_CENTRE where AGEING>=sysdate-2/24) ", nativeQuery = true)
	public List<TicketCenterEnity> findAllTicketCentor2Hour();
	
	@Query(value = " SELECT * from TBL_TICKET_CENTRE where AGEING>sysdate-4/24 and TICKET_Id not in (SELECT  TICKET_ID from TBL_TICKET_CENTRE where AGEING>=sysdate-2/24) ", nativeQuery = true)
	public List<TicketCenterEnity> findAllTicketCentor4Hour();
	
	@Query(value = " SELECT * from TBL_TICKET_CENTRE where AGEING>sysdate-8/24 and TICKET_Id not in (SELECT  TICKET_ID from TBL_TICKET_CENTRE where AGEING>=sysdate-4/24) ", nativeQuery = true)
	public List<TicketCenterEnity> findAllTicketCentor8Hour();
	
	
	
	@Query(value=" SELECT  *  from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-1), 'DD-MM-YY HH24:MI:SS' ) and  to_date(to_char(trunc(sysdate-1),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS') ",nativeQuery=true)
	public List<TicketCenterEnity> findAllTicketCentor1Days();
	@Query(value=" SELECT  *  from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-3), 'DD-MM-YY HH24:MI:SS' ) and  to_date(to_char(trunc(sysdate-3),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS') ",nativeQuery=true)
	public List<TicketCenterEnity> findAllTicketCentor3Days();
	@Query(value=" SELECT  *  from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-5), 'DD-MM-YY HH24:MI:SS' ) and  to_date(to_char(trunc(sysdate-5),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS') ",nativeQuery=true)
	public List<TicketCenterEnity> findAllTicketCentor5Days();
	@Query(value=" SELECT  *  from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-7), 'DD-MM-YY HH24:MI:SS' ) and  to_date(to_char(trunc(sysdate-7),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS') ",nativeQuery=true)
	public List<TicketCenterEnity> findAllTicketCentor1Week();

	//@Query("from TicketCenterEnity")
	
	@Query(value="SELECT * from TBL_TICKET_CENTRE where  AGEING between 	to_date(trunc(sysdate-8), 'DD-MM-YY HH24:MI:SS' ) "
			+ "	and	to_date(trunc(sysdate-1/24),  'DD-MM-YY HH24:MI:SS') ",nativeQuery=true ) 
	public List<TicketCenterEnity> findAllTotalTicketCentor();
	*/

	

	// new added by satendra 
	

//

	/*@Query(value = " SELECT nvl(count(CALL_SUBCATEGORY),0)  from TBL_TICKET_CENTRE where  AGEING<=4 and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))", nativeQuery = true)
	public int find2_4HoursCount1(@Param("pfId") Set<String> pfId) ;

	@Query(value=" SELECT nvl(count(CALL_SUBCATEGORY),0)  from TBL_TICKET_CENTRE where  (AGEING>4 and AGEING<=8) and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))",nativeQuery=true)
	public int find_1_DaysCount1(@Param("pfId") Set<String> pfId);

	@Query(value=" SELECT  nvl(count(CALL_SUBCATEGORY),0)  from TBL_TICKET_CENTRE where  (AGEING>8 and AGEING<=24) and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))",nativeQuery=true)
	public int find_3_Days_LessCount1(@Param("pfId") Set<String> pfId);

	@Query(value=" SELECT  nvl(count(CALL_SUBCATEGORY),0)  from TBL_TICKET_CENTRE where (AGEING>24) and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))",nativeQuery=true)
	public int find_3_Days_GreaterThanCount1(@Param("pfId") Set<String> pfId);
*/


// CMF User Count for ageing 
@Query(value = "select nvl(count(CALL_SUBCATEGORY),0)  from TBL_TICKET_CENTRE where  AGEING<=4  "
		+ " and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING  WHERE PF_ID=:pfId)", nativeQuery = true)
public int find2_4HoursCountCMF(@Param("pfId") String pfId) ;


@Query(value = "select nvl(count(CALL_SUBCATEGORY),0)  from TBL_TICKET_CENTRE where   (AGEING>4 and AGEING<=8)  "
		+ " and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING  WHERE PF_ID=:pfId)", nativeQuery = true)
public int find_1_DaysCountCMF(@Param("pfId") String pfId);

@Query(value = "select nvl(count(CALL_SUBCATEGORY),0)  from TBL_TICKET_CENTRE where   (AGEING>8 and AGEING<=24) "
		+ " and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING  WHERE PF_ID=:pfId) ", nativeQuery = true)
public int find_3_Days_LessCountCMF(@Param("pfId") String pfId);

@Query(value = "select nvl(count(CALL_SUBCATEGORY),0)  from TBL_TICKET_CENTRE where  AGEING>24 and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING  WHERE PF_ID=:pfId)", nativeQuery = true)
public int find_3_Days_GreaterThanCountCMF(@Param("pfId") String pfId);

@Query(value="SELECT nvl(count(CALL_SUBCATEGORY),0)  from TBL_TICKET_CENTRE where  KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId)", nativeQuery=true)
public int findTotalCountCMF(@Param("pfId") String pfId);


// CMF User List
@Query(value = " SELECT *  from TBL_TICKET_CENTRE where  AGEING<=4  and KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING  WHERE PF_ID=:pfId) ", nativeQuery = true)
public Page<TicketCentor> findAllTicketCentor4HourAndCMFUser(@Param("pfId") String pfId,Pageable pageable);

@Query(value = " SELECT * from TBL_TICKET_CENTRE where  (AGEING>4 and AGEING<=8) AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId)", nativeQuery = true)
public Page<TicketCentor> findAllTicketCentor1DaysAndCMFUser(@Param("pfId") String pfId,Pageable pageable);

@Query(value = " SELECT * from TBL_TICKET_CENTRE where  (AGEING>8 and AGEING<=24) AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId)", nativeQuery = true)
public Page<TicketCentor> findAllTicketCentor3DaysLessAndCMFUser(@Param("pfId") String pfId,Pageable pageable);

@Query(value = " SELECT * from TBL_TICKET_CENTRE where  AGEING>24  AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId)", nativeQuery = true)
public Page<TicketCentor> findAllTicketCentor3DaysGreaterAndCMFUser(@Param("pfId") String pfId,Pageable pageable);
@Query(value = " SELECT * from TBL_TICKET_CENTRE where KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID=:pfId)", nativeQuery = true)
public Page<TicketCentor> findAllTicketTotalListAndCMFUser(@Param("pfId") String pfId,Pageable pageable);



// CC User


@Query(value=" select nvl(count(CALL_SUBCATEGORY),0)  from TBL_TICKET_CENTRE where  (AGEING>4 and AGEING<=8) ",nativeQuery=true)
public int find_1_DaysCount();

@Query(value=" SELECT  nvl(count(CALL_SUBCATEGORY),0)  from TBL_TICKET_CENTRE where  (AGEING>8 and AGEING<=24)  ",nativeQuery=true)
public int find_3_Days_LessCount();

@Query(value=" SELECT  nvl(count(CALL_SUBCATEGORY),0)  from TBL_TICKET_CENTRE where   AGEING>24  ",nativeQuery=true)
public int find_3_Days_GreaterThanCount();

//@Query(value=" SELECT  count(*)  from TBL_TICKET_CENTRE where  AGEING between trunc(sysdate-3)	and  trunc(sysdate-1/24)  ",nativeQuery=true)

@Query(value="SELECT nvl(count(CALL_SUBCATEGORY),0)  from TBL_TICKET_CENTRE ", nativeQuery=true)
public int findTotalCount();

// Circle User Count

@Query(value = " SELECT nvl(count(CALL_SUBCATEGORY),0)  from TBL_TICKET_CENTRE where  AGEING<=4  and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle)", nativeQuery = true)
public int find2_4HoursCount(@Param("circle") String circle) ;

@Query(value=" SELECT nvl(count(CALL_SUBCATEGORY),0)  from TBL_TICKET_CENTRE where  (AGEING>4 and AGEING<=8) 	 and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle)",nativeQuery=true)
public int find_1_DaysCount(@Param("circle") String circle);

@Query(value=" SELECT nvl(count(CALL_SUBCATEGORY),0)  from TBL_TICKET_CENTRE where   (AGEING>8 and AGEING<=24)	and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle)",nativeQuery=true)
public int find_3_Days_LessCount(@Param("circle") String circle);

@Query(value=" SELECT nvl(count(CALL_SUBCATEGORY),0)  from TBL_TICKET_CENTRE where  AGEING >24 and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle)",nativeQuery=true)
public int find_3_Days_GreaterThanCount(@Param("circle") String circle);

@Query(value="SELECT nvl(count(CALL_SUBCATEGORY),0)  from TBL_TICKET_CENTRE where KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle)", nativeQuery=true)
public int findTotalCount(@Param("circle") String circle);

//Circle User List
@Query(value = " select * from TBL_TICKET_CENTRE where AGEING<=4  and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle)", nativeQuery = true)
	public Page<TicketCentor> findAllTicketCentor4HourByCircle(@Param("circle") String circle,Pageable pageable);
	
@Query(value = " SELECT * from TBL_TICKET_CENTRE where (AGEING>4 and AGEING<=8) and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle)", nativeQuery = true)
public Page<TicketCentor> findAllTicketCentor1DaysByCircle(@Param("circle") String circle,Pageable pageable);

@Query(value = " SELECT * from TBL_TICKET_CENTRE where (AGEING>8 and AGEING<=24) and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle)", nativeQuery = true)
public Page<TicketCentor> findAllTicketCentor3DaysLessByCircle(@Param("circle") String circle,Pageable pageable);

@Query(value = " SELECT * from TBL_TICKET_CENTRE where  (AGEING>24) and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE CIRCLE=:circle)", nativeQuery = true)
public Page<TicketCentor> findAllTicketCentor3DaysGreaterByCircle(@Param("circle") String circle,Pageable pageable);





// CMS User Count

@Query(value = "select nvl(count(CALL_SUBCATEGORY),0)  from TBL_TICKET_CENTRE where   AGEING<=4  "
		+ " and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING  WHERE PF_ID=:pfId)", nativeQuery = true)
public int find2_4HoursCountCMS(@Param("pfId") Set<String> pfId) ;

@Query(value = "select nvl(count(CALL_SUBCATEGORY),0)  from TBL_TICKET_CENTRE where   (AGEING>4 and AGEING<=8)  "
		+ " and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING  WHERE PF_ID=:pfId)", nativeQuery = true)
public int find_1_DaysCountCMS(@Param("pfId") Set<String> pfId);

@Query(value = "select nvl(count(CALL_SUBCATEGORY),0)  from TBL_TICKET_CENTRE where   (AGEING>8 and AGEING<=24) "
		+ " and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING  WHERE PF_ID=:pfId) ", nativeQuery = true)
public int find_3_Days_LessCountCMS(@Param("pfId") Set<String> pfId);

@Query(value = "select nvl(count(CALL_SUBCATEGORY),0)  from TBL_TICKET_CENTRE where   AGEING>24 "
		+ " and KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING  WHERE PF_ID=:pfId)", nativeQuery = true)
public int find_3_Days_GreaterThanCountCMS(@Param("pfId") Set<String> pfId);

@Query(value="SELECT  nvl(count(CALL_SUBCATEGORY),0)  from TBL_TICKET_CENTRE where KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))", nativeQuery=true)
public int findTotalCountCMS(@Param("pfId") Set<String> pfId);


//CMS User List
@Query(value = " SELECT *  from TBL_TICKET_CENTRE where AGEING<=4  AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))", nativeQuery = true)
public Page<TicketCentor> findAllTicketCentor4HourAndCMSUser(@Param("pfId") Set<String> pfId,Pageable pageable);

@Query(value = " SELECT * from TBL_TICKET_CENTRE where  (AGEING>4 and AGEING<=8) AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))", nativeQuery = true)
public Page<TicketCentor> findAllTicketCentor1DaysAndCMSUser(@Param("pfId") Set<String> pfId,Pageable pageable);

@Query(value = " SELECT * from TBL_TICKET_CENTRE where  (AGEING>8 and AGEING<=24) AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))", nativeQuery = true)
public Page<TicketCentor> findAllTicketCentor3DaysLessAndCMSUser(@Param("pfId") Set<String> pfId,Pageable pageable);

@Query(value = " SELECT * from TBL_TICKET_CENTRE where  (AGEING>24) AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))", nativeQuery = true)
public Page<TicketCentor> findAllTicketCentor3DaysGreaterAndCMSUser(@Param("pfId") Set<String> pfId,Pageable pageable);



/*@Query(value="SELECT  nvl(count(CALL_SUBCATEGORY),0)  from TBL_TICKET_CENTRE where KIOSK_ID in (SELECT KIOSK_ID FROM TBL_USER_KIOSK_MAPPING WHERE PF_ID in (:pfId))", nativeQuery=true)
public int findTotalCountCMS(@Param("pfId") Set<String> pfId);*/

//


//CC User
// CC
@Query(value = " select nvl(count(CALL_SUBCATEGORY),0) from TBL_TICKET_CENTRE where AGEING<=4 " , nativeQuery = true)
public int find2_4HoursCount();
@Query(value = " SELECT * from TBL_TICKET_CENTRE where (AGEING>4 and AGEING<=8)  ", nativeQuery = true)
public Page<TicketCentor> findAllTicketCentor1Days(Pageable pageable);


//@Query(value = " SELECT * from TBL_TICKET_CENTRE  where (AGEING>8 and AGEING<=24) ", nativeQuery = true)
@Query(value = " SELECT * from TBL_TICKET_CENTRE where (AGEING>8 and AGEING<=24)  ", nativeQuery = true,countQuery = "  SELECT count(*) from TBL_TICKET_CENTRE where (AGEING>8 and AGEING<=24) ")
public Page<TicketCentor> findAllTicketCentor3DaysLess(Pageable pageable);


@Query(value = " SELECT * from TBL_TICKET_CENTRE where  AGEING>24  ", nativeQuery = true)
public Page<TicketCentor> findAllTicketCentor3DaysGreater(Pageable pageable);

//@Query(value = " SELECT  *  from TBL_TICKET_CENTRE where  AGEING between to_date(trunc(sysdate-2/24), 'DD-MM-YY HH24:MI:SS' ) and  to_date(to_char(trunc(sysdate-3),'DD-MM-YY')||' 23:59:59',  'DD-MM-YY HH24:MI:SS') ", nativeQuery = true)
@Query(value=" SELECT  *  from TBL_TICKET_CENTRE ", nativeQuery=true)
public Page<TicketCentor> findAllTicketCentorTotal(Pageable pageable);


// Ticket Centor

@Query(value = "SELECT *  from TBL_TICKET_CENTRE where  AGEING<=4 ", nativeQuery = true)
public Page<TicketCentor> findAllTicketCentor4Hour(Pageable pageable);
	
}
