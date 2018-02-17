package com.greboreda.portal.business.login.business;

import com.greboreda.portal.business.login.domain.LoginServiceId;
import com.greboreda.portal.business.vo.EmailAddress;

public class EmailAddressAlreadyInUseException extends Exception {

	private final EmailAddress emailAddress;
	private final LoginServiceId owner;

	public EmailAddressAlreadyInUseException(EmailAddress emailAddress, LoginServiceId owner) {
		this.emailAddress = emailAddress;
		this.owner = owner;
	}

	public EmailAddress getEmailAddress() {
		return emailAddress;
	}

	public LoginServiceId getOwner() {
		return owner;
	}
}
