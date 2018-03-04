package com.greboreda.portal.business.user.domain.role;

import java.util.Arrays;

public enum RoleType {

	USER("user"),
	ADMIN("admin");

	private final String name;

	RoleType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static RoleType mapByName(String name) {
		return Arrays.stream(RoleType.values())
				.filter(rt -> rt.getName().equals(name))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}
