package sbi.kiosk.swayam.kioskmanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.KioskMaster;

@Repository
public interface kioskMasterManagementRepository extends CrudRepository<KioskMaster, Long>{

}
