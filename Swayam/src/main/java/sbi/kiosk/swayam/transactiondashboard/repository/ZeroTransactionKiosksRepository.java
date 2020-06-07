package sbi.kiosk.swayam.transactiondashboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sbi.kiosk.swayam.common.entity.ZeroTransactionKiosks;

@Repository
public interface ZeroTransactionKiosksRepository extends CrudRepository<ZeroTransactionKiosks, String> {
	
	/*
	 * @Query(value="SELECT PF_ID FROM TBL_USER WHERE PF_ID=:pfid",nativeQuery=true)
	 * String findIdByPfId(@Param("pfid") String pfid);
	 */
	@Query(value="SELECT * FROM VW_ZERO_TRANSACTION_KIOSKS",nativeQuery=true)
	//List<ZeroTransactionKiosks> findAll(Pageable pageable);
	Page<ZeroTransactionKiosks> findAll(Pageable pageable);
	
	
//	@Query(value = "{call sp_zero_transaction_kiosks(:fromDate, :toDate)}", nativeQuery = true)
//	//List<ZeroTransactionKiosks> findAll(Pageable pageable);
//	Page<ZeroTransactionKiosks> findAll(Pageable pageable, @Param("fromDate") String fromDate, @Param("toDate") String toDate);
}
