package sbi.kiosk.swayam.healthmonitoring.service;

import org.springframework.data.domain.Page;

import sbi.kiosk.swayam.common.dto.TicketCentorDto;


public interface IOperations<T> {

    public Page<T> findPaginated(final int page, final int size);
    public Page<T> findPaginatedCount(final int page, final int size,String type);
    public Page<T> findPaginatedCC(final int page, final int size);
    public Page<T> findPaginatedCmf(final int page, final int size);
    public Page<T> findPaginatedByCircle(final int page, final int size);
    public Page<T> findPaginatedCountByCircle(int page, int size,String type);

}
