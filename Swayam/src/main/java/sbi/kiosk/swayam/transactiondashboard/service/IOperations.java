package sbi.kiosk.swayam.transactiondashboard.service;

import org.springframework.data.domain.Page;

import sbi.kiosk.swayam.common.entity.DrillDown;
import sbi.kiosk.swayam.common.entity.ZeroTransactionKiosks;

public interface IOperations<T> {
	
	public Page<T> findPaginated(final int page, final int size);
	
	public Page<ZeroTransactionKiosks> findPaginatedByDate(final int page, final int size, String fromDate, String toDate);
	
	public Page<DrillDown> findPaginatedByTxnDate(final int page, final int size, String fromDate, String toDate, String circleName, String networkName, String moduleName, String regionName);
	
	public Page<T> findPaginatedByCircle(final int page, final int size, String circleName);
	
	public Page<T> findPaginatedByNetwork(final int page, final int size, String networkName);
	
	public Page<T> findPaginatedByModule(final int page, final int size, String moduleName);
	
	public Page<T> findPaginatedByRegion(final int page, final int size, String regionName);

	Page<DrillDown> findPaginatedByTxnDate(int page, int size, String type, String fromdate, String todate,
			String in_circle_code, String in_network_code, String in_module_code, String in_region_code);
	
	Page<ZeroTransactionKiosks> findPaginatedByDateSearchNext(int page, int size, String fromDate, String toDate,String searchText);

	Page<DrillDown> findPaginatedByTxnDateSearchNext(int i, int size, String type, String fromdate, String todate,
			String circleName, String networkName, String moduleName, String regionName, String searchText);
}
