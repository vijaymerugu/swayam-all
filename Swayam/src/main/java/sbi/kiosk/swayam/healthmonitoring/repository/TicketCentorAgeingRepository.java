package sbi.kiosk.swayam.healthmonitoring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.TicketCentor;

@Repository("ticketCentorAgeingRepository")
public interface TicketCentorAgeingRepository extends CrudRepository<TicketCentor, String> {
	
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

}
