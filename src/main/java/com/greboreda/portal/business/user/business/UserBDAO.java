package com.greboreda.portal.business.user.business;

import com.greboreda.portal.business.user.domain.User;
import com.greboreda.portal.business.user.domain.UserId;
import com.greboreda.portal.business.user.persistence.dao.UserDAO;
import com.greboreda.portal.business.user.persistence.dbo.UserDBO;
import com.greboreda.portal.business.user.persistence.mapper.UserMapper;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Named
class UserBDAO {

	private final UserDAO userDAO;

	@Inject
	UserBDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	Optional<User> findBy(UserId userId) {
		final Optional<UserDBO> maybeDBO = userDAO.findById(userId.getUuid());
		return maybeDBO.map(UserMapper::map);
	}

	void save(User user) {
		final UserDBO userDBO = UserMapper.map(user);
		userDAO.save(userDBO);
	}

}
