package com.greboreda.portal.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	public static final String LOGIN_PAGE = "login";

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return LOGIN_PAGE;
	}

/*
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		return LOGIN_PAGE;
	}
*/
}
