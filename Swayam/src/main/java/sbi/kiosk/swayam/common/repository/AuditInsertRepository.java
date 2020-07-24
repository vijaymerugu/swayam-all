package sbi.kiosk.swayam.common.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.AuditLogger;


@Repository
public interface AuditInsertRepository extends CrudRepository<AuditLogger, Long>{
	
	//@PersistenceContext
   // private EntityManager entityManager;
	
	
	
	/*@Transactional
	public void insertWithQuery(AuditLogger logger) {
	    entityManager.createNativeQuery("INSERT INTO TBL_AUDIT_LOG (id, user_id, status,session_date,path) VALUES (?,?,?,?,?)")
	      .setParameter(1, logger.getId())
	      .setParameter(2, logger.getUser_Id())
	      .setParameter(3, logger.getStaus())
	      .setParameter(4, logger.getSession_Date())
	      .setParameter(5, logger.getPath())
	      .executeUpdate();
	}*/

}
