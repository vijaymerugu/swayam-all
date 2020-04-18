package sbi.kiosk.swayam.healthmonitoring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.CallType;


@Repository
public interface CallTypeRepository extends CrudRepository<CallType, String> {
	
	
	
	@Query(value=" select count(*) from TBL_CALL_TYPE where  RISK in('High') ",nativeQuery=true)
	public int getCallTypeHiegh() ;
	@Query(value=" select count(*) from TBL_CALL_TYPE where  RISK in('Medium') ",nativeQuery=true)
	public int getCallTypeMedium() ;
	@Query(value=" select count(*) from TBL_CALL_TYPE where  RISK in('Low') ",nativeQuery=true)
	public int getCallTypeLow() ;
	@Query(value=" select count(*) from TBL_CALL_TYPE where  RISK in('High','Medium','Low') ",nativeQuery=true)
	public int getCallTypeTotal() ;

	@Query(value=" select * from TBL_CALL_TYPE",nativeQuery=true)
	List<CallType> findCallType();

	@Query(value = " select * from TBL_CALL_TYPE where CATEGORY=?1", nativeQuery = true)
	List<CallType> findCallTypeByCategory(String category);
	

}
