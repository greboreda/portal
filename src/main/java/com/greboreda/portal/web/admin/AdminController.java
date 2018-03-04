package com.greboreda.portal.web.admin;

import com.greboreda.portal.business.login.business.EmailAddressAlreadyInUseException;
import com.greboreda.portal.business.login.business.LoginServiceCreator;
import com.greboreda.portal.business.login.business.LoginServiceFinder;
import com.greboreda.portal.business.login.domain.LoginService;
import com.greboreda.portal.business.user.business.UserFinder;
import com.greboreda.portal.business.user.domain.User;
import com.greboreda.portal.business.user.domain.role.RoleType;
import com.greboreda.portal.business.vo.EmailAddress;
import com.greboreda.portal.web.admin.dto.ManageableUserDTO;
import com.greboreda.portal.web.admin.dto.UserToCreateDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class AdminController {

	private final LoginServiceCreator loginServiceCreator;
	private final LoginServiceFinder loginServiceFinder;
	private final UserFinder userFinder;

	@Inject
	public AdminController(LoginServiceCreator loginServiceCreator, LoginServiceFinder loginServiceFinder, UserFinder userFinder) {
		this.loginServiceCreator = loginServiceCreator;
		this.loginServiceFinder = loginServiceFinder;
		this.userFinder = userFinder;
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Model model) {
		model.addAttribute("userToCreate", new UserToCreateDTO());

		final AdminView adminView = AdminView.create()
				.withManageableUsers(findManageableUsers())
				.build();
		model.addAttribute("adminView", adminView);

		return "admin";
	}

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

	private List<ManageableUserDTO> findManageableUsers() {

		final List<ManageableUserDTO> manageableUsers = new ArrayList<>();

		for(User user : userFinder.findAll()) {
			final Optional<LoginService> maybeLoginService = loginServiceFinder.findBy(user.getId());
			final String email = maybeLoginService.isPresent() ? maybeLoginService.get().getEmailAddress().getValue() : null;
			final Boolean isAdmin = user.getRoles().stream().anyMatch(r -> r.getType().equals(RoleType.ADMIN));
			final ManageableUserDTO manageableUser = ManageableUserDTO.create()
					.withUserId(user.getId().toString())
					.withCreationDate(user.getCreationDate())
					.withLoginEmail(email)
					.withIsAdmin(isAdmin)
					.build();
			manageableUsers.add(manageableUser);
		}
		return manageableUsers;
	}

}
