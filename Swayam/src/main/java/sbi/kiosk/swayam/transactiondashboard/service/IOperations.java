package sbi.kiosk.swayam.transactiondashboard.service;

import org.springframework.data.domain.Page;

public interface IOperations<T> {
	
	 //public Page<T> findPaginated(final int page, final int size);
	public Page<T> findPaginated(final int page, final int size,String fromdate,String todate);
	
	 

}
