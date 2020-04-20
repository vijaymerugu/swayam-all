package sbi.kiosk.swayam.healthmonitoring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.Requests;

@Repository
public interface RequestsRepository extends CrudRepository<Requests, Integer>{

}
