package com.greboreda.portal.business.user.business;

import com.greboreda.portal.business.user.domain.User;
import org.apache.commons.lang3.Validate;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@Transactional
public class UserSaver {

	private final UserBDAO userBDAO;

	@Inject
	public UserSaver(UserBDAO userBDAO) {
		this.userBDAO = userBDAO;
	}

	public void save(User user) {
		Validate.notNull(user);
		Validate.notNull(user.getId());
		Validate.notNull(user.getCreationDate());
		Validate.notEmpty(user.getRoles());
		Validate.notNull(user.getLoginService());
		userBDAO.save(user);
	}

}
