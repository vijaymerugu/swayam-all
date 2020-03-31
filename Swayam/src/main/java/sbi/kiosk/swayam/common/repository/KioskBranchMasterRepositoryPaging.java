package sbi.kiosk.swayam.common.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import sbi.kiosk.swayam.common.entity.KioskBranchMaster;

public interface KioskBranchMasterRepositoryPaging extends PagingAndSortingRepository<KioskBranchMaster, Integer> {

}
