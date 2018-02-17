package com.greboreda.portal.business.user.domain;

import com.greboreda.portal.business.Id;
import org.apache.commons.lang3.Validate;

import java.util.UUID;

public class UserId implements Id {

	private final UUID uuid;

	public UserId() {
		this.uuid = UUID.randomUUID();
	}

	private UserId(UUID uuid) {
		Validate.notNull(uuid);
		this.uuid = uuid;
	}

	@Override
	public UUID getUuid() {
		return uuid;
	}

	public static UserId fromUUID(UUID uuid) {
		return new UserId(uuid);
	}
}
