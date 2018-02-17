package com.greboreda.portal.business.login.domain;

import com.greboreda.portal.business.Id;
import org.apache.commons.lang3.Validate;

import java.util.UUID;

public final class LoginServiceId implements Id {

	private final UUID uuid;

	public LoginServiceId() {
		this.uuid = UUID.randomUUID();
	}

	private LoginServiceId(UUID uuid) {
		Validate.notNull(uuid);
		this.uuid = uuid;
	}

	public UUID getUuid() {
		return uuid;
	}

	public String getValue() {
		return uuid.toString();
	}

	public static LoginServiceId fromUUID(UUID uuid) {
		return new LoginServiceId(uuid);
	}

}
