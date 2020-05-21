package sbi.kiosk.swayam.kioskmanagement.service;

import java.util.List;

import sbi.kiosk.swayam.common.dto.RolesDto;
import sbi.kiosk.swayam.common.entity.Circle;

public interface RoleService {
	List<RolesDto> findAllRole();
	
	List<Circle> findAllCircle();
}
