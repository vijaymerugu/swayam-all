/**
 * 
 */
package sbi.kiosk.swayam.dataanalyser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.TATofDownKiosks;


/**
 * @author vmph2362595
 *
 */
@Repository
public interface TATofDownKiosksRepo extends CrudRepository<TATofDownKiosks, String> {

	//@Query(value = "{CALL SP_TAT_OF_DOWN_KIOSKS_PROC(:circleCode)}", nativeQuery = true)
	
	@Query(value = "with data_table as (select CRCL_NAME as CIRCLE, count(CALL_SUBCATEGORY) as TOTAL_NO_OF_TICKETS,\r\n" + 
			"			sum(case when round(AGEING/8)=1 then 1 else 0 end) AS ONE_DAY,\r\n" + 
			"			sum(case when (round(AGEING/8)>=2 and round(AGEING/8)<=5) then 1 else 0 end) AS TWO_TO_FIVE_DAYS,\r\n" + 
			"			sum(case when (round(AGEING/8)>5 and round(AGEING/8)<=7) then 1 else 0 end) AS ONE_WEEK,\r\n" + 
			"			sum(case when (round(AGEING/8)>7 and round(AGEING/8)<=14) then 1 else 0 end) AS ONE_TO_TWO_WEEK,\r\n" + 
			"			sum(case when round(AGEING/8)>14 then 1 else 0 end) AS GREATER_THAN_TWO_WEEK\r\n" + 
			"		from TBL_TICKET_CENTRE tc\r\n" + 
			"		left join TBL_KIOSK_MASTER km on km.KIOSK_ID=tc.KIOSK_ID\r\n" + 
			"		left join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=km.BRANCH_CODE\r\n" + 
			"		WHERE '1'=(SELECT CASE WHEN :circleCode is null THEN '1' WHEN CRCL_CODE=:circleCode THEN '1' ELSE '0' END FROM DUAL) \r\n" + 
			"		GROUP BY CRCL_NAME)\r\n" + 
			"		SELECT CIRCLE, TOTAL_NO_OF_TICKETS, ONE_DAY, TWO_TO_FIVE_DAYS, ONE_WEEK, ONE_TO_TWO_WEEK, GREATER_THAN_TWO_WEEK,\r\n" + 
			"			ROUND((ONE_DAY * 100 / TOTAL_NO_OF_TICKETS), 2) as PERCENTAGE_OF_ONE_DAYS,\r\n" + 
			"			ROUND((TWO_TO_FIVE_DAYS * 100 / TOTAL_NO_OF_TICKETS), 2) as PERCENT_OF_TWO_TO_FIVE_DAYS,\r\n" + 
			"			ROUND((ONE_WEEK * 100 / TOTAL_NO_OF_TICKETS), 2) as PERCENTAGE_OF_ONE_WEEK,\r\n" + 
			"			ROUND((ONE_TO_TWO_WEEK * 100 / TOTAL_NO_OF_TICKETS), 2) as PERCENT_ONE_TO_TWO_WEEK,\r\n" + 
			"			ROUND((GREATER_THAN_TWO_WEEK * 100 / TOTAL_NO_OF_TICKETS), 2) as PERCENT_GREATER_THAN_TWO_WEEK\r\n" + 
			"		FROM data_table tbl", nativeQuery = true)
	List<TATofDownKiosks> getTATofDownKiosks(@Param("circleCode") String circleCode);

}
