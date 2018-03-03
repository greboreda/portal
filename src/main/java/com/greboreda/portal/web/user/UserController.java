package com.greboreda.portal.web.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class UserController {

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String privateArea(Model model, Principal principal) {
		final UserView userView = UserView.create()
				.withUserName(principal.getName())
				.withLogoutUrl("/logout")
				.build();
		model.addAttribute("userView", userView);
		return "user";
	}

}
