package com.greboreda.portal.business.user.domain;

import com.greboreda.portal.business.Entity;
import com.greboreda.portal.business.user.domain.role.Role;
import org.apache.commons.lang3.Validate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

public class User implements Entity<UserId> {

	private final UserId id;
	private final LocalDateTime creationDate;
	private final Set<Role> roles;

	private User(UserId id, LocalDateTime creationDate, Set<Role> roles) {
		Validate.notNull(id);
		Validate.notNull(creationDate);
		Validate.notNull(roles);
		this.id = id;
		this.creationDate = creationDate;
		this.roles = roles;
	}

	@Override
	public UserId getId() {
		return id;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public Set<Role> getRoles() {
		return Collections.unmodifiableSet(roles);
	}

	public static UserBuilder create() {
		return new UserBuilder();
	}

	public static class UserBuilder {

		@FunctionalInterface
		public interface AddCreationDate {
			AddRoles withCreationDate(LocalDateTime creationDate);
		}
		@FunctionalInterface
		public interface AddRoles {
			Builder withRoles(Set<Role> roles);
		}
		@FunctionalInterface
		public interface Builder {
			User build();
		}

		private UserBuilder() {

		}

		public AddCreationDate withId(UserId id) {
			return creationDate -> roles -> () -> new User(id, creationDate, roles);
		}

	}
}
