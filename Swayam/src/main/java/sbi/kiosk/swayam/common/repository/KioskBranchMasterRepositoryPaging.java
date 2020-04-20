package sbi.kiosk.swayam.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import sbi.kiosk.swayam.common.entity.KioskBranchMaster;

public interface KioskBranchMasterRepositoryPaging extends PagingAndSortingRepository<KioskBranchMaster, Integer> {

	public Page<KioskBranchMaster>	findByVendor(@Param("type") String type, Pageable pageable);

	public Page<KioskBranchMaster> findByVendorAndInstallationStatus(@Param("type") String type,@Param("installationStatus") String installationStatus, Pageable pageable);

}
