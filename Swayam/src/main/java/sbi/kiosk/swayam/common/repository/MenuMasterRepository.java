package sbi.kiosk.swayam.common.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.MenuMaster;

@Repository
public interface MenuMasterRepository extends CrudRepository<MenuMaster, String> {
	
	List<MenuMaster> findByRole(String role);
}
