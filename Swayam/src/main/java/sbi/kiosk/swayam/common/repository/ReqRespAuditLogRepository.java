 package sbi.kiosk.swayam.common.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.RequestResponse;

@Repository
public interface ReqRespAuditLogRepository extends CrudRepository<RequestResponse, Integer>{
	@Query(value = "select SEQ_TBL_REQ_RESP_LOG.nextval from dual", nativeQuery = true)
	int findSeq();

}
