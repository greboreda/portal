package com.greboreda.portal.business.security;

import com.greboreda.portal.business.login.business.LoginServiceFinder;
import com.greboreda.portal.business.login.domain.LoginService;
import com.greboreda.portal.business.user.business.UserFinder;
import com.greboreda.portal.business.user.domain.UserId;
import com.greboreda.portal.business.vo.EmailAddress;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;
import java.util.UUID;

@Named
public class UserSpringSecurityServiceWrapper implements UserDetailsService, UserDetailsManager {

	private final UserFinder userFinder;
	private final LoginServiceFinder loginServiceFinder;

	@Inject
	public UserSpringSecurityServiceWrapper(UserFinder userFinder, LoginServiceFinder loginServiceFinder) {
		this.userFinder = userFinder;
		this.loginServiceFinder = loginServiceFinder;
	}

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		final Optional<LoginService> maybeLoginService = loginServiceFinder.findBy(new EmailAddress(s));
		return maybeLoginService.<UserDetails>map(UserSpringSecurityWrapper::new).orElse(null);
	}

	@Override
	public void createUser(UserDetails userDetails) {

	}

	@Override
	public void updateUser(UserDetails userDetails) {
	}

	@Override
	public void deleteUser(String s) {

	}

	@Override
	public void changePassword(String s, String s1) {

	}

	@Override
	public boolean userExists(String s) {
		return loginServiceFinder.existsLoginWith(new EmailAddress(s));
	}
}
