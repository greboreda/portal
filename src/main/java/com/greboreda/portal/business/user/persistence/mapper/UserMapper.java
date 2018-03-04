package com.greboreda.portal.business.user.persistence.mapper;

import com.greboreda.portal.business.user.domain.User;
import com.greboreda.portal.business.user.domain.UserId;
import com.greboreda.portal.business.user.persistence.dbo.UserDBO;

import java.util.UUID;

public class UserMapper {

	public static User map(UserDBO userDBO) {
		return User.create()
				.withId(UserId.fromUUID(UUID.fromString(userDBO.getId())))
				.withCreationDate(userDBO.getCreationDate())
				.withRoles(RoleMapper.map(userDBO.getRoles()))
				.build();
	}

	public static UserDBO map(User user) {
		final UserDBO userDBO = new UserDBO();
		userDBO.setId(user.getId().getUuid().toString());
		userDBO.setCreationDate(user.getCreationDate());
		userDBO.setRoles(RoleMapper.mapToDBOs(user.getRoles()));
		return userDBO;
	}

}
