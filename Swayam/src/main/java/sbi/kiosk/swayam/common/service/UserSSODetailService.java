package sbi.kiosk.swayam.common.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.auth10.federation.Claim;
import com.auth10.federation.FederatedPrincipal;

import sbi.kiosk.swayam.common.entity.UserEntity;

@Service
public class UserSSODetailService {
	
	Logger logger = LoggerFactory.getLogger(UserSSODetailService.class);
	
	public UserEntity getUserDetailsFromReq(HttpServletRequest req) {
		UserEntity user = new UserEntity();
		List<Claim> claimsSSO = ((FederatedPrincipal) req.getUserPrincipal()).getClaims();
		for (Claim c : claimsSSO) {
			/*
			 * if (c.getClaimType().contains("PF_INDEX")) {
			 * user.setPfId(Long.valueOf(c.getClaimValue())); } if
			 * (c.getClaimType().contains("EMP_NAME")) {
			 * user.setUserName(c.getClaimValue()); } if
			 * (c.getClaimType().contains("MAIL_ID")) { user.setEmail(c.getClaimValue()); }
			 * if (c.getClaimType().contains("MOB_NO")) {
			 * user.setPhoneNumber(c.getClaimValue()); }
			 */
			
			if (c.getClaimType().contains("upn") || c.getClaimType().contains("UPN")) {
				user.setUpn(c.getClaimValue());
			}
			
		}
		return user;
	}

}
