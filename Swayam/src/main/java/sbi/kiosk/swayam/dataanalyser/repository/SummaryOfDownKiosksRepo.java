/**
 * 
 */
package sbi.kiosk.swayam.dataanalyser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.SummaryOfDownKiosks;


/**
 * @author vmph2362595
 *
 */
@Repository
public interface SummaryOfDownKiosksRepo extends CrudRepository<SummaryOfDownKiosks, String> {

	//@Query(value = "{CALL SP_ERROT_TYPE_WISE_UPTIME_PROC(:circleCode)}", nativeQuery = true)
	
	@Query(value = "with data_table as (select CRCL_NAME as CIRCLE, count(CALL_SUBCATEGORY) as TOTAL_NO_OF_TICKETS,\r\n" + 
			"			sum(case STATUS_OF_COMPLAINT when 'Active' then 1 else 0 end) NO_OF_OPEN_TICKETS,\r\n" + 
			"			sum(case STATUS_OF_COMPLAINT when 'Closed' then 1 else 0 end) NO_OF_CLOSED_TICKETS\r\n" + 
			"		from TBL_TICKET_CENTRE tc\r\n" + 
			"		left join TBL_KIOSK_MASTER km on km.KIOSK_ID=tc.KIOSK_ID\r\n" + 
			"		left join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=km.BRANCH_CODE\r\n" + 
			"		WHERE '1'=(SELECT CASE WHEN :circleCode is null THEN '1' WHEN CRCL_CODE=:circleCode THEN '1' ELSE '0' END FROM DUAL) \r\n" + 
			"		GROUP BY CRCL_NAME)\r\n" + 
			"		SELECT CIRCLE, TOTAL_NO_OF_TICKETS, NO_OF_OPEN_TICKETS, NO_OF_CLOSED_TICKETS,\r\n" + 
			"			ROUND((NO_OF_OPEN_TICKETS * 100 / TOTAL_NO_OF_TICKETS), 2) as PERCENTAGE_OF_TICKETS, \r\n" + 
			"			ROUND((NO_OF_CLOSED_TICKETS * 100 / TOTAL_NO_OF_TICKETS), 2) as PERCENT_OF_CLOSED_TICKETS\r\n" + 
			"		FROM data_table tbl", nativeQuery = true)
	List<SummaryOfDownKiosks> getSummaryOfDownKiosks(@Param("circleCode") String circleCode);

}
