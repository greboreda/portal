package com.greboreda.portal.business.login.business;

import com.greboreda.portal.business.login.domain.LoginService;
import com.greboreda.portal.business.vo.EmailAddress;
import org.apache.commons.lang3.Validate;
import org.springframework.security.crypto.bcrypt.BCrypt;

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

	public Optional<LoginService> doLogin(EmailAddress emailAddress, String plainPassword) {
		Validate.notNull(emailAddress);
		Validate.notNull(plainPassword);

		final Optional<LoginService> maybeLoginService = loginServiceFinder.findBy(emailAddress);
		if(!maybeLoginService.isPresent()) {
			return Optional.empty();
		}
		final LoginService loginService = maybeLoginService.get();
		final boolean isValidPassword = BCrypt.checkpw(plainPassword, loginService.getPassword().getValue());
		if(!isValidPassword) {
			return Optional.empty();
		}
		return Optional.of(loginService);
	}

}
