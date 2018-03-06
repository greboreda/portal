package com.greboreda.portal.business.login.business;

import com.greboreda.portal.business.login.domain.LoginService;
import com.greboreda.portal.business.vo.EmailAddress;
import com.greboreda.portal.business.vo.PlainPassword;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Named
public class LoginPerformer {

	private final LoginServiceFinder loginServiceFinder;

	@Inject
	public LoginPerformer(LoginServiceFinder loginServiceFinder) {
		this.loginServiceFinder = loginServiceFinder;
	}

	public Optional<LoginService> doLogin(EmailAddress emailAddress, PlainPassword plainPassword) {
		Validate.notNull(emailAddress);
		Validate.notNull(plainPassword);

		final Optional<LoginService> maybeLoginService = loginServiceFinder.findBy(emailAddress);
		if(!maybeLoginService.isPresent()) {
			return Optional.empty();
		}
		final LoginService loginService = maybeLoginService.get();
		final boolean isValidPassword = loginService.getPassword().matchesWith(plainPassword);
		if(!isValidPassword) {
			return Optional.empty();
		}
		return Optional.of(loginService);
	}

}
