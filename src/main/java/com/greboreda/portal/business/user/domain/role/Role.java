package com.greboreda.portal.business.user.domain.role;

import com.greboreda.portal.business.Entity;
import org.apache.commons.lang3.Validate;

public class Role implements Entity<RoleId> {

	private static final long serialVersionUID = -7668806501052581972L;

	private final RoleId id;
	private final String name;

	private Role(RoleId id, String name) {
		Validate.notNull(id);
		Validate.notNull(name);
		this.id = id;
		this.name = name;
	}

	@Override
	public RoleId getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static RoleBuilder create() {
		return new RoleBuilder();
	}

	public static class RoleBuilder {

		@FunctionalInterface
		public interface AddName {
			Builder withName(String name);
		}
		@FunctionalInterface
		public interface Builder {
			Role build();
		}

		private RoleBuilder() {

		}

		public AddName withId(RoleId id) {
			return name -> () -> new Role(id, name);
		}
	}
}
