package sbi.kiosk.swayam.common.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.Circle;

@Repository
public interface BillingCircleRepository extends CrudRepository<Circle, String>{


}
