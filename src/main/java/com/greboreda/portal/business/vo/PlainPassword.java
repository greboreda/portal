package com.greboreda.portal.business.vo;

import com.greboreda.portal.business.ValueObject;
import org.apache.commons.lang3.Validate;

public class PlainPassword implements ValueObject {

	private final String value;

	public PlainPassword(String value) {
		Validate.notNull(value);
		if(!isValidPlainPassword(value)) {
			throw new IllegalArgumentException("passowrd must be at least 4 characters");
		}
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString(){
		return value;
	}

	public static Boolean isValidPlainPassword(String plainPassword) {
		return plainPassword != null && plainPassword.length() >= 4;
	}
}
