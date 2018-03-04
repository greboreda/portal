package com.greboreda.portal.web.admin;

import com.greboreda.portal.business.login.business.EmailAddressAlreadyInUseException;
import com.greboreda.portal.business.login.business.LoginServiceCreator;
import com.greboreda.portal.business.user.domain.role.RoleType;
import com.greboreda.portal.business.vo.EmailAddress;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Set;

@Controller
public class AdminController {

	private final LoginServiceCreator loginServiceCreator;

	@Inject
	public AdminController(LoginServiceCreator loginServiceCreator) {
		this.loginServiceCreator = loginServiceCreator;
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Model model) {
		model.addAttribute("userToCreate", new UserToCreateDTO());
		return "admin";
	}

	@PreAuthorize("hasRole('admin')")
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String createUser(@ModelAttribute("userToCreate") UserToCreateDTO userToCreate) {

		if(!EmailAddress.isValidEmailAddress(userToCreate.email)) {
			return "admin";
		}

		final EmailAddress email = new EmailAddress(userToCreate.email);

		try {
			final Set<RoleType> roleTypes = Collections.singleton(RoleType.USER);
			loginServiceCreator.createLoginServiceForNewUser(email, userToCreate.password, roleTypes);
		} catch (EmailAddressAlreadyInUseException e) {
			return "admin";
		}

		return "admin";
	}

}
