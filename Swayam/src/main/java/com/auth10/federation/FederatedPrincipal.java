package com.auth10.federation;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

public class FederatedPrincipal implements Principal {
	private static final String NameClaimType = "http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name";
	private static final String EmailClaimType = "http://schemas.xmlsoap.org/claims/EmailAddress";
	
	protected List<Claim> claims = null;

	public FederatedPrincipal(List<Claim> claims) {
		this.claims = claims;
	}

	public String getName() {
		String name = "";
		
		for (Claim claim : claims) {
			if (claim.getClaimType().equals(NameClaimType))
				name = claim.getClaimValue();
		}
		
		if (name.isEmpty()){
			for (Claim claim : claims) {
				if (claim.getClaimType().equals(EmailClaimType))
					name = claim.getClaimValue();
			}			
		}
		
		return name;
	}

	public List<Claim> getClaims() {
		return Collections.unmodifiableList(this.claims);
	}
}
