package com.greboreda.portal.business.user.business;

import com.greboreda.portal.business.user.domain.User;
import com.greboreda.portal.business.user.domain.UserId;
import com.greboreda.portal.business.user.persistence.dao.UserDAO;
import com.greboreda.portal.business.user.persistence.dbo.UserDBO;
import com.greboreda.portal.business.user.persistence.mapper.UserMapper;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

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

	List<User> findAll() {
		final Iterable<UserDBO> all = userDAO.findAll();
		return StreamSupport.stream(all.spliterator(), false)
				.map(UserMapper::map)
				.collect(toList());
	}

	void save(User user) {
		final UserDBO userDBO = UserMapper.map(user);
		userDAO.save(userDBO);
	}

}
