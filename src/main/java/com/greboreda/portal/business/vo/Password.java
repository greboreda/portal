package com.greboreda.portal.business.vo;

import org.apache.commons.lang3.Validate;

public class Password {

	private final String value;

	public Password(String value) {
		Validate.notNull(value);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
