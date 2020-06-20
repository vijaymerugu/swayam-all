package sbi.kiosk.swayam.transactiondashboard.repository;

import org.springframework.data.domain.Page;

import sbi.kiosk.swayam.common.entity.RealTimeTransaction;

public interface IOperations<T> {
	
	// public Page<T> findPaginated(final int page, final int size);
	 Page<RealTimeTransaction> findPaginated(int page, int size, String fromdate);

	 
	
	 

}
