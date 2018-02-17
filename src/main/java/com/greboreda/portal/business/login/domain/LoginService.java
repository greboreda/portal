package com.greboreda.portal.business.login.domain;

import com.greboreda.portal.business.Entity;
import com.greboreda.portal.business.user.domain.User;
import com.greboreda.portal.business.vo.EmailAddress;
import com.greboreda.portal.business.vo.Password;
import org.apache.commons.lang3.Validate;

import java.time.LocalDateTime;

public class LoginService implements Entity<LoginServiceId> {

	private final LoginServiceId id;
	private final User user;
	private final LocalDateTime creationDate;
	private final LocalDateTime modificationDate;
	private final EmailAddress emailAddress;
	private final Password password;

	private LoginService(LoginServiceId id, User user, LocalDateTime creationDate, LocalDateTime modificationDate, EmailAddress emailAddress, Password password) {
		Validate.notNull(id);
		Validate.notNull(user);
		Validate.notNull(creationDate);
		Validate.notNull(modificationDate);
		Validate.notNull(emailAddress);
		Validate.notNull(password);
		this.id = id;
		this.user = user;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
		this.emailAddress = emailAddress;
		this.password = password;
	}

	@Override
	public LoginServiceId getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public LocalDateTime getModificationDate() {
		return modificationDate;
	}

	public EmailAddress getEmailAddress() {
		return emailAddress;
	}

	public Password getPassword() {
		return password;
	}

	public static LoginServiceBuilder create() {
		return new LoginServiceBuilder();
	}

	public static final class LoginServiceBuilder {

		@FunctionalInterface
		public interface AddUser {
			AddCreationDate withUser(User user);
		}
		@FunctionalInterface
		public interface AddCreationDate {
			AddModificationDate withCreationDate(LocalDateTime creationDate);
		}
		@FunctionalInterface
		public interface AddModificationDate {
			AddEmailAddress withModificationDate(LocalDateTime modificationDate);
		}
		@FunctionalInterface
		public interface AddEmailAddress {
			AddPassword withEmailAddress(EmailAddress emailAddress);
		}
		@FunctionalInterface
		public interface AddPassword {
			Builder withPassword(Password password);
		}
		@FunctionalInterface
		public interface Builder {
			LoginService build();
		}

		private LoginServiceBuilder() {

		}

		public AddUser withId(LoginServiceId id) {
			return user -> creationDate -> modificationDate -> emailAddress -> password -> () -> new LoginService(id, user, creationDate, modificationDate, emailAddress, password);
		}
	}
}
