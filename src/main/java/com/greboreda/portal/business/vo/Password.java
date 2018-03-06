package com.greboreda.portal.business.vo;

import com.greboreda.portal.business.ValueObject;
import org.apache.commons.lang3.Validate;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class Password implements ValueObject {

	private final String value;

	public Password(PlainPassword plainPassword) {
		Validate.notNull(plainPassword);
		this.value = BCrypt.hashpw(plainPassword.getValue(), BCrypt.gensalt());
	}

	public Password(String hashedPassword) {
		Validate.notNull(hashedPassword);
		this.value = hashedPassword;
	}

	public Boolean matchesWith(PlainPassword plainPassword) {
		Validate.notNull(plainPassword);
		return BCrypt.checkpw(plainPassword.getValue(), value);
	}

	public String getValue() {
		return value;
	}

}
