package sbi.kiosk.swayam.billingpayment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.BranchEntity;
import sbi.kiosk.swayam.common.entity.BranchMaster;


@Repository
public interface BranchStateRepository extends CrudRepository<BranchEntity, Integer>{
	
	
	@Query(value = "select * from TBL_BRANCH_MASTER u where  u.CRCL_CODE=:crclCode", 
			nativeQuery = true)
	List<BranchEntity> findByCirclehCode(@Param("crclCode") String circleCode);
	
	
	/*
	 * @Query(value =
	 * "select STAT_CODE, STAT_DESC from TBL_BRANCH_MASTER u where  u.CRCL_CODE=:crclCode"
	 * , nativeQuery = true) List<Object[]> findByCirclehCode(@Param("crclCode")
	 * String circleCode);
	 */
	 
	/*
	 * 
	 * @Query(value =
	 * "select STAT_CODE, STAT_DESC from TBL_BRANCH_MASTER u where  u.CRCL_CODE=:crclCode"
	 * , nativeQuery = true) List<BranchEntity>
	 * findByCirclehCode(@Param("circleCode") String circleCode);
	 */

}
