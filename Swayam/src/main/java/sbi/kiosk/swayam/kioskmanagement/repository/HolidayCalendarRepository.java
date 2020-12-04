package sbi.kiosk.swayam.kioskmanagement.repository;

import org.springframework.data.jpa.repository.Query;

//By Pankul 28-04-2020-----------STARTS---------

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sbi.kiosk.swayam.common.entity.HolidayCalendar;
import sbi.kiosk.swayam.common.entity.User;

	@Repository
	public interface HolidayCalendarRepository extends CrudRepository<HolidayCalendar, Integer>{
		
		@Query(value="SELECT HOLIDAY_DATE FROM TBL_BRANCH_HOLIDAY WHERE HOLIDAY_DATE=:holidayDate and CIRCLE=:circle and STATE=:state",nativeQuery=true)
	    String findByHolidayDate(@Param("holidayDate") String holidayDate,@Param("circle") String circle,@Param("state") String state);

	}

//-------By Pankul END---------------------------
