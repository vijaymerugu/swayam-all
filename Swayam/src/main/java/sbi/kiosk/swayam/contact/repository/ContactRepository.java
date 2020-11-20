package sbi.kiosk.swayam.contact.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.ContactEntity;
import sbi.kiosk.swayam.common.entity.RfpIdMaster;


@Repository
public interface ContactRepository extends CrudRepository<ContactEntity, Integer>{

}
