package sbi.kiosk.swayam.billingpayment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.TaxEntity;

@Repository
public interface TaxRepository extends CrudRepository<TaxEntity, String> {
	
	@Query(value ="SELECT count(1) FROM TBL_TAX "
			+ "where CRCL_NAME=:circleCode AND VENDOR=:vendor "
			+ "AND STAT_DESC=:state "
			+ "AND FIN_YR=:finyear "
			+ "AND QTR_ID=:qId "
			+ "AND RFP_NO=:rfp",nativeQuery=true)
	int findCount(@Param("circleCode") String circleCode, 
			@Param("vendor")String vendor,
			@Param("state")String state,@Param("finyear")String finyear,
			@Param("qId")String qId,@Param("rfp")String rfp);
	
	
	@Query(value ="SELECT * FROM TBL_TAX "
			+ "where CRCL_NAME=:circleCode AND VENDOR=:vendor "
			+ "AND STAT_DESC=:state "
			+ "AND FIN_YR=:finyear "
			+ "AND QTR_ID=:qId ",nativeQuery=true)
	List<TaxEntity> getTaxDetail(@Param("circleCode") String circleCode, 
			@Param("vendor")String vendor,
			@Param("state")String state,@Param("finyear")String finyear,
			@Param("qId")String qId);

}
