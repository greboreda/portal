package com.greboreda.portal.business.user.business;

import com.greboreda.portal.business.user.domain.User;
import com.greboreda.portal.business.user.domain.UserId;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Named
public class UserFinder {

	private final UserBDAO userBDAO;

	@Inject
	public UserFinder(UserBDAO userBDAO) {
		this.userBDAO = userBDAO;
	}

	public Optional<User> findBy(UserId userId) {
		Validate.notNull(userId);
		return userBDAO.findBy(userId);
	}

}
