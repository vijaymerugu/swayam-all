package sbi.kiosk.swayam.healthmonitoring.service;

import org.springframework.data.domain.Page;


public interface IOperations<T> {

    public Page<T> findPaginated(final int page, final int size);
    public Page<T> findPaginatedCount(final int page, final int size,String type);
    public Page<T> findPaginatedCC(final int page, final int size);
    public Page<T> findPaginatedCmf(final int page, final int size);
   

}
