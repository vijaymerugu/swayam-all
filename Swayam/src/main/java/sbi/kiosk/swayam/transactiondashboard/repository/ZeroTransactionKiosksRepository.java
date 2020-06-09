package sbi.kiosk.swayam.transactiondashboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sbi.kiosk.swayam.common.entity.ZeroTransactionKiosks;

@Repository
public interface ZeroTransactionKiosksRepository extends CrudRepository<ZeroTransactionKiosks, String> {
	
	@Query(value="SELECT * FROM VW_ZERO_TRANSACTION_KIOSKS",nativeQuery=true)
	
	Page<ZeroTransactionKiosks> findAll(Pageable pageable);
	
}
