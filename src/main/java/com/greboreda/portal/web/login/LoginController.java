package com.greboreda.portal.web.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

import static com.greboreda.portal.web.utils.LoggedUserUtils.isAdmin;

@Controller
public class LoginController {

	private static final String LOGIN_PAGE = "login";

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Principal principal) {
		if(principal == null) {
			return LOGIN_PAGE;
		}
		return isAdmin(principal) ? "redirect:/admin" : "redirect:/home";
	}


}
