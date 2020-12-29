package sbi.kiosk.swayam.billingpayment.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.TaxEntity;

@Repository
@Transactional
public class TaxCalInsertRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public int insert(TaxEntity tax) {
		
		System.out.println("Inside Tax cal insert "+ tax);
		
		entityManager.persist(tax);
		entityManager.flush();
		entityManager.clear();
		
		System.out.println("Inside Tax cal insert  sucesss");
	
		return 1;
	}


}
