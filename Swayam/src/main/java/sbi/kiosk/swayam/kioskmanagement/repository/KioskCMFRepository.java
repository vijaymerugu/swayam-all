package sbi.kiosk.swayam.kioskmanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sbi.kiosk.swayam.common.entity.UserKioskMapping;

//By Pankul 28-04-2020-----------STARTS---------

@Repository
public interface KioskCMFRepository extends CrudRepository<UserKioskMapping, Integer>{	

}

//-------By Pankul END---------------------------