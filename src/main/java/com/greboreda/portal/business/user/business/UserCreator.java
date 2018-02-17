package com.greboreda.portal.business.user.business;

import com.greboreda.portal.business.user.domain.User;
import com.greboreda.portal.business.user.domain.UserId;
import com.greboreda.portal.business.user.domain.role.Role;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDateTime;
import java.util.Set;

@Named
@Transactional
public class UserCreator {

	private final UserBDAO userBDAO;

	@Inject
	public UserCreator(UserBDAO userBDAO) {
		this.userBDAO = userBDAO;
	}

	public User createUser(Set<Role> roles) {
		final User user = User.create()
				.withId(new UserId())
				.withCreationDate(LocalDateTime.now())
				.withRoles(roles)
				.build();
		userBDAO.save(user);
		return user;
	}

}