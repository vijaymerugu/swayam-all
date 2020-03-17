package sbi.kiosk.swayam.kioskmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.common.dto.RolesDto;
import sbi.kiosk.swayam.common.entity.Role;
import sbi.kiosk.swayam.kioskmanagement.repository.RolesRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RolesRepository roleRepo;

	@Override
	public List<RolesDto> findAllRole() {
		List<RolesDto> listDatao = new ArrayList<RolesDto>();
		List<Role> roleList = roleRepo.findAll();
		if (roleList != null && !roleList.isEmpty() && roleList.size() > 0) {
			for (Role roleEntity : roleList) {
				listDatao.add(new RolesDto(roleEntity));
			}
		}
		return listDatao;
	}

}
