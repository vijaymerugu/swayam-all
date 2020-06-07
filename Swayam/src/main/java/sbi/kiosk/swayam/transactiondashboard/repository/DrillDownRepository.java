package sbi.kiosk.swayam.transactiondashboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sbi.kiosk.swayam.common.entity.DrillDown;

@Repository
public interface DrillDownRepository extends CrudRepository<DrillDown, String> {
	
	/*
	 * @Query(value="SELECT PF_ID FROM TBL_USER WHERE PF_ID=:pfid",nativeQuery=true)
	 * String findIdByPfId(@Param("pfid") String pfid);
	 */
	@Query(value="SELECT * FROM VW_DRILL_DOWN",nativeQuery=true)
	//List<ZeroTransactionKiosks> findAll(Pageable pageable);
	Page<DrillDown> findAll(Pageable pageable);
	
    @Query(value="SELECT * FROM VW_DRILL_DOWN where CRCL_NAME=:circleName",nativeQuery=true)
//	//List<ZeroTransactionKiosks> findAll(Pageable pageable);
    Page<DrillDown> findAllByCircle(Pageable pageable, @Param("circleName") String circleName);
    
    @Query(value="SELECT * FROM VW_DRILL_DOWN where NETWORK=:networkName",nativeQuery=true)
//	//List<ZeroTransactionKiosks> findAll(Pageable pageable);
    Page<DrillDown> findAllByNetwork(Pageable pageable, @Param("networkName") String networkName);
    
    @Query(value="SELECT * FROM VW_DRILL_DOWN where MODULE=:moduleName",nativeQuery=true)
//	//List<ZeroTransactionKiosks> findAll(Pageable pageable);
    Page<DrillDown> findAllByModule(Pageable pageable, @Param("moduleName") String moduleName);
    
    @Query(value="SELECT * FROM VW_DRILL_DOWN where REGION=:regionName",nativeQuery=true)
//	//List<ZeroTransactionKiosks> findAll(Pageable pageable);
    Page<DrillDown> findAllByRegion(Pageable pageable, @Param("regionName") String regionName);
	
}
