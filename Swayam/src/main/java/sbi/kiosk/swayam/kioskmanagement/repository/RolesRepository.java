package sbi.kiosk.swayam.kioskmanagement.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.Role;

@Repository
public interface RolesRepository extends CrudRepository<Role, Integer> {
	List<Role> findAll();

}
