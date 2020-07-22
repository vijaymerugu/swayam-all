package sbi.kiosk.swayam;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.entity.UserRolePrivileges;

@Component
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppPermissions implements PermissionEvaluator {
	
	@Autowired
	AppCache appCache;
	
	public static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }
	
	@Override
	public boolean hasPermission(Authentication authentication, Object accessType, Object permission) {
		UserDto user = (UserDto) session().getAttribute("userObj");
		String role = user.getRole();
		if (authentication != null && accessType instanceof String) {
			List<UserRolePrivileges> list = appCache.getAllUserRolePrivileges();
			if(list !=null && list.size() > 0) {
				for(UserRolePrivileges access:list) {
					if(access.getRole().equals(role) 
							&& access.getIdentityMethod().equals(accessType)
							&& access.getAccess().equals(permission)) {
						return true;
					}
				}				
			}
			return false;
		}
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		// TODO Auto-generated method stub
		return false;
	}

}
