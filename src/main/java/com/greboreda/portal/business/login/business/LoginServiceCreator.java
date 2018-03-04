package com.greboreda.portal.business.login.business;

import com.greboreda.portal.business.login.domain.LoginService;
import com.greboreda.portal.business.login.domain.LoginServiceId;
import com.greboreda.portal.business.user.business.UserCreator;
import com.greboreda.portal.business.user.domain.User;
import com.greboreda.portal.business.user.domain.role.Role;
import com.greboreda.portal.business.vo.EmailAddress;
import com.greboreda.portal.business.vo.Password;
import org.apache.commons.lang3.Validate;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Named
public class LoginServiceCreator {

	private final LoginServiceBDAO loginServiceBDAO;
	private final UserCreator userCreator;

	@Inject
	public LoginServiceCreator(LoginServiceBDAO loginServiceBDAO, UserCreator userCreator) {
		this.loginServiceBDAO = loginServiceBDAO;
		this.userCreator = userCreator;
	}

	public LoginService createLoginServiceForNewUser(EmailAddress emailAddress, String plainPassword, Set<Role> roles) throws EmailAddressAlreadyInUseException {
		Validate.notNull(emailAddress);
		Validate.notNull(plainPassword);
		Validate.notNull(roles);

		validateEmailAddressIsNotInUse(emailAddress);

		final User user = userCreator.createUser(roles);
		final LocalDateTime now = LocalDateTime.now();
		final LoginService loginService = LoginService.create()
				.withId(new LoginServiceId())
				.withUser(user)
				.withCreationDate(now)
				.withModificationDate(now)
				.withEmailAddress(emailAddress)
				.withPassword(new Password(BCrypt.hashpw(plainPassword, BCrypt.gensalt())))
				.build();

		loginServiceBDAO.save(loginService);

		return loginService;
	}

	private void validateEmailAddressIsNotInUse(EmailAddress emailAddress) throws EmailAddressAlreadyInUseException {
		final Optional<LoginService> maybeLoginService = loginServiceBDAO.findBy(emailAddress);
		if(maybeLoginService.isPresent()) {
			final LoginService loginService = maybeLoginService.get();
			throw new EmailAddressAlreadyInUseException(emailAddress, loginService.getId());
		}
	}

}
