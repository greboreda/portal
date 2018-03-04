package com.greboreda.portal.business.user.domain.role;

import com.greboreda.portal.business.Id;
import org.apache.commons.lang3.Validate;

import java.util.UUID;

public class RoleId implements Id {

	private final UUID uuid;

	private RoleId(UUID uuid) {
		Validate.notNull(uuid);
		this.uuid = uuid;
	}

	@Override
	public UUID getUuid() {
		return uuid;
	}

	@Override
	public String toString() {
		return uuid.toString();
	}

	public static RoleId fromUUID(UUID uuid) {
		return new RoleId(uuid);
	}
}
