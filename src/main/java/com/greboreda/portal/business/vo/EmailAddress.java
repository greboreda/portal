package com.greboreda.portal.business.vo;

import com.greboreda.portal.business.ValueObject;
import org.apache.commons.lang3.Validate;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.Objects;

public class EmailAddress implements ValueObject {

	private static final EmailValidator emailValidator = EmailValidator.getInstance();

	private final String value;

	public EmailAddress(String value) {
		Validate.notNull(value);
		if(!emailValidator.isValid(value)) {
			throw new IllegalArgumentException("email address is not valid");
		}
		this.value = value.toLowerCase();
	}

	public String getValue() {
		return value;
	}

	public static boolean isValidEmailAddress(String emailAddress) {
		return emailValidator.isValid(emailAddress);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EmailAddress that = (EmailAddress) o;
		return Objects.equals(value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

}
