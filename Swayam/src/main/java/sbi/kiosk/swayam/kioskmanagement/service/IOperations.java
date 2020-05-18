package sbi.kiosk.swayam.kioskmanagement.service;

import org.springframework.data.domain.Page;

import sbi.kiosk.swayam.common.dto.UserManagementDto;

public interface IOperations<T> {

    public Page<T> findPaginated(final int page, final int size);

	Page<UserManagementDto> findPaginatedCount(int page, int size, String type);
	
	public Page<UserManagementDto> findPaginatedByCircle(int page, int size);
	
	Page<UserManagementDto> findPaginatedCountByCircle(int page, int size, String type);

}
