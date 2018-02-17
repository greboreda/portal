package com.greboreda.portal.business.login.business;

import com.greboreda.portal.business.login.domain.LoginService;
import com.greboreda.portal.business.login.domain.LoginServiceId;
import com.greboreda.portal.business.vo.EmailAddress;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Named
public class LoginServiceFinder {

	private final LoginServiceBDAO loginServiceBDAO;

	@Inject
	public LoginServiceFinder(LoginServiceBDAO loginServiceBDAO) {
		this.loginServiceBDAO = loginServiceBDAO;
	}

	public Optional<LoginService> findBy(LoginServiceId loginServiceId) {
		Validate.notNull(loginServiceId);
		return loginServiceBDAO.findBy(loginServiceId);
	}

	public Optional<LoginService> findBy(EmailAddress emailAddress) {
		Validate.notNull(emailAddress);
		return loginServiceBDAO.findBy(emailAddress);
	}

	public Boolean existsLoginWith(EmailAddress emailAddress) {
		Validate.notNull(emailAddress);
		return loginServiceBDAO.findBy(emailAddress).isPresent();
	}

}
