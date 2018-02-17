package com.greboreda.portal.business.security;

import com.greboreda.portal.business.login.domain.LoginService;
import org.apache.commons.lang3.Validate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserSpringSecurityWrapper implements UserDetails {

	private final LoginService loginService;

	public UserSpringSecurityWrapper(LoginService loginService) {
		Validate.notNull(loginService);
		this.loginService = loginService;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return loginService.getPassword().getValue();
	}

	@Override
	public String getUsername() {
		return loginService.getEmailAddress().getValue();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


}
