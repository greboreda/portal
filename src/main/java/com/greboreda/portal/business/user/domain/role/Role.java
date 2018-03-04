package com.greboreda.portal.business.user.domain.role;

import com.greboreda.portal.business.Entity;
import org.apache.commons.lang3.Validate;

public class Role implements Entity<RoleId> {

	private static final long serialVersionUID = -7668806501052581972L;

	private final RoleId id;
	private final RoleType type;

	private Role(RoleId id, RoleType roleType) {
		Validate.notNull(id);
		Validate.notNull(roleType);
		this.id = id;
		this.type = roleType;
	}

	@Override
	public RoleId getId() {
		return id;
	}

	public RoleType getType() {
		return type;
	}

	public static RoleBuilder create() {
		return new RoleBuilder();
	}

	public static class RoleBuilder {

		@FunctionalInterface
		public interface AddType {
			Builder withType(RoleType roleType);
		}
		@FunctionalInterface
		public interface Builder {
			Role build();
		}

		private RoleBuilder() {

		}

		public AddType withId(RoleId id) {
			return type -> () -> new Role(id, type);
		}
	}

}
