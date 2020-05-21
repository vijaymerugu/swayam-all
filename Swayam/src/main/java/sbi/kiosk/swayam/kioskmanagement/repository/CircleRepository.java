package sbi.kiosk.swayam.kioskmanagement.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import sbi.kiosk.swayam.common.entity.Circle;

public interface CircleRepository extends CrudRepository<Circle, Integer>{
	List<Circle> findAll();
}
