/**
 * 
 */
package sbi.kiosk.swayam.dataanalyser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.Availability;


/**
 * @author vmph2362595
 *
 */
@Repository
public interface AvailabilityRepo extends CrudRepository<Availability, String> {

	@Query(value = "with data_table as (select CRCL_NAME as CIRCLE, count(ts.KIOSK_ID) AS NO_OF_KIOSKS,\r\n" + 
			"    sum(case AGENT_STATUS when 'Green' then 1 else 0 end) NO_OF_AVAILABLE_KIOSKS,\r\n" + 
			"    sum(case when AGENT_STATUS IN ('Red','Grey') then 1 else 0 end) NO_OF_UNAVAILABLE_KIOSKS\r\n" + 
			"from TBL_TERMINAL_STATUS ts\r\n" + 
			"left join TBL_KIOSK_MASTER km on km.KIOSK_ID=ts.KIOSK_ID\r\n" + 
			"left join TBL_BRANCH_MASTER bm on bm.BRANCH_CODE=km.BRANCH_CODE\r\n" + 
			"WHERE '1'=(SELECT CASE WHEN :circleCode is null THEN '1' WHEN CRCL_CODE=:circleCode THEN '1' ELSE '0' END FROM DUAL) \r\n" + 
			"GROUP BY CRCL_NAME)\r\n" + 
			"SELECT CIRCLE, NO_OF_KIOSKS, NO_OF_AVAILABLE_KIOSKS, NO_OF_UNAVAILABLE_KIOSKS, \r\n" + 
			"ROUND((NO_OF_AVAILABLE_KIOSKS * 100 / NO_OF_KIOSKS),2) as PERCENTAGE_OF_AVAILABLE_KIOSKS,\r\n" + 
			"ROUND((NO_OF_UNAVAILABLE_KIOSKS * 100 / NO_OF_KIOSKS),2) as NON_AVAILABLE_KIOSKS_PERCENT\r\n" + 
			"FROM data_table tbl", nativeQuery = true)
	List<Availability> getAvailability(@Param("circleCode") String circleCode);

}
