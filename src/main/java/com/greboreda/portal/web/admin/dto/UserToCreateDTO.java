package com.greboreda.portal.web.admin.dto;

import com.greboreda.portal.web.DataTransportObject;

public class UserToCreateDTO implements DataTransportObject {

	private static final long serialVersionUID = -42225830280589929L;

	public String email;
	public String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
