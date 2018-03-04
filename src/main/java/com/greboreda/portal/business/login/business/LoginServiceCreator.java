package com.greboreda.portal.business.login.business;

import com.greboreda.portal.business.login.domain.LoginService;
import com.greboreda.portal.business.login.domain.LoginServiceId;
import com.greboreda.portal.business.user.business.UserSaver;
import com.greboreda.portal.business.user.business.role.Roles;
import com.greboreda.portal.business.user.domain.User;
import com.greboreda.portal.business.user.domain.UserId;
import com.greboreda.portal.business.user.domain.role.RoleType;
import com.greboreda.portal.business.vo.EmailAddress;
import com.greboreda.portal.business.vo.Password;
import org.apache.commons.lang3.Validate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Named
@Transactional
public class LoginServiceCreator {

	private final LoginServiceBDAO loginServiceBDAO;
	private final UserSaver userSaver;

	@Inject
	public LoginServiceCreator(LoginServiceBDAO loginServiceBDAO, UserSaver userSaver) {
		this.loginServiceBDAO = loginServiceBDAO;
		this.userSaver = userSaver;
	}

	public LoginService createLoginServiceForNewUser(EmailAddress emailAddress, String plainPassword, Set<RoleType> roleTypes) throws EmailAddressAlreadyInUseException {
		Validate.notNull(emailAddress);
		Validate.notNull(plainPassword);
		Validate.notNull(roleTypes);

		validateEmailAddressIsNotInUse(emailAddress);

		final LocalDateTime now = LocalDateTime.now();

		final User user = User.create()
				.withId(new UserId())
				.withCreationDate(now)
				.withRoles(Roles.get(roleTypes))
				.build();

		userSaver.save(user);

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
