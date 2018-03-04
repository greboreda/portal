package com.greboreda.portal.web.admin;

import com.greboreda.portal.business.login.business.EmailAddressAlreadyInUseException;
import com.greboreda.portal.business.login.business.LoginServiceCreator;
import com.greboreda.portal.business.user.business.RoleFinder;
import com.greboreda.portal.business.user.domain.role.Role;
import com.greboreda.portal.business.vo.EmailAddress;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Controller
public class AdminController {

	private final LoginServiceCreator loginServiceCreator;
	private final RoleFinder roleFinder;

	@Inject
	public AdminController(LoginServiceCreator loginServiceCreator, RoleFinder roleFinder) {
		this.loginServiceCreator = loginServiceCreator;
		this.roleFinder = roleFinder;
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

		final Optional<Role> maybeUserRole = roleFinder.findRoleByName("user");
		if(!maybeUserRole.isPresent()) {
			throw new RuntimeException("role not found");
		}
		final Set<Role> roles = Collections.singleton(maybeUserRole.get());

		try {
			loginServiceCreator.createLoginServiceForNewUser(email, userToCreate.password, roles);
		} catch (EmailAddressAlreadyInUseException e) {
			return "admin";
		}

		return "admin";
	}

}
