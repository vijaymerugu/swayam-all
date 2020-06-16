package sbi.kiosk.swayam.transactiondashboard.service;

import org.springframework.data.domain.Page;

import sbi.kiosk.swayam.common.entity.RealTimeTransaction;
import sbi.kiosk.swayam.transactiondashboard.repository.IOperations;

public interface RealTimeTransactionService extends IOperations<RealTimeTransaction>{
	//Page<RealTimeTransaction> findPaginated(int page, int size);

	Page<RealTimeTransaction> findPaginated(int page, int size, String fromdate);

}
