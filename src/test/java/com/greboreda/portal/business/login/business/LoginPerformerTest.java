package com.greboreda.portal.business.login.business;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LoginPerformerTest {

	@Test
	public void foo() {

		final String pass1 = BCrypt.hashpw("pass1", BCrypt.gensalt());
		System.out.println(pass1);

		assertThat(BCrypt.checkpw("pass1", "$2a$10$XoF2fWA6QrtT23uKzS1XMuEghv0vOnBgxDGsa7Jmi5TG6wOk3.uIW"), is(true));

	}

}