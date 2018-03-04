package com.greboreda.portal.web.utils;

import com.greboreda.portal.business.user.domain.role.RoleType;
import org.apache.commons.lang3.Validate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.security.Principal;

public class LoggedUserUtils {

	public static Boolean isAdmin(Principal principal) {
		Validate.notNull(principal);
		final Authentication auth = (Authentication) principal;
		return auth.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.map(RoleType::mapByName)
				.anyMatch(a -> a.equals(RoleType.ADMIN));
	}

}
