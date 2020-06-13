package sbi.kiosk.swayam.transactiondashboard.repository;

import java.util.List;

import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sbi.kiosk.swayam.common.entity.SwayamTransactionReport;

@Repository



@NamedStoredProcedureQueries({
@NamedStoredProcedureQuery(name = "SP_MIGRATION_SUMMARY1", 
procedureName = "SP_MIGRATION_SUMMARY", 
parameters = {
		 @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class),
       @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class),
	    @StoredProcedureParameter(name="cur",mode = ParameterMode.REF_CURSOR,  type=void.class)
		 })
})
public interface TransactionDashBoardRepository extends CrudRepository<SwayamTransactionReport, Integer>{

	
	  //@Procedure(procedureName = "SP_MIGRATION_SUMMARY")
	    //@Query( value = "call PRO_REALTIME_TRANSACTION (:fromDate,:kioskId) )" ,nativeQuery = true)
	//@Query(value="select * from TBL_SWAYAM_TXN_REPORT  where CREATED_DATE=:fromDate OR CREATED_DATE=:toDate",nativeQuery=true)
		 
	  //@Query(value = "EXECUTE PRO_REALTIME_TRANSACTION :fromDate,:toDate", nativeQuery = true)
	 // Page<SwayamTransactionReport>   findByTxnDate(Pageable pageable,@Param("fromDate") String fromDate,@Param("toDate") String toDate);
	 // SwayamTransactionReport   findByTxnDate(@Param("fromdate") String fromdate,@Param("todate") String todate);
		
	 // List<SwayamTransactionReport>   findAll();
	  //@Procedure(procedureName = "SP_MIGRATION_SUMMARY")
	 // @Query(value = "CALL SP_MIGRATION_SUMMARY :fromdate,:todate", nativeQuery = true)
	  
	 // @Query(value = "{call SP_MIGRATION_SUMMARY()}", nativeQuery = true)

	 // List<SwayamTransactionReport>   findByFromDateAndToDate(@Param("fromdate") String fromdate,@Param("todate") String todate);
		
	 // List<SwayamTransactionReport>   findByFromDateAndToDate();
		
	
}
