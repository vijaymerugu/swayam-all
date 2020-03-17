package sbi.kiosk.swayam.kioskmanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.UserKioskMapping;

@Repository
public interface UserKioskMappingRepository extends CrudRepository<UserKioskMapping, String>{

}
