package sbi.kiosk.swayam.common.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.CommonUrl;

@Repository
public interface CommonUrlConfigRepository  extends CrudRepository<CommonUrl, String>{
	
	@Query(value="select OMS_URL from TBL_URL_CONFIGURE",nativeQuery=true)
	public String findOmsUrl();
	@Query(value="select * from TBL_URL_CONFIGURE",nativeQuery=true)
	public CommonUrl findURL();

}
