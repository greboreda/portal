package com.greboreda.portal.business.user.persistence.mapper;

import com.greboreda.portal.business.user.domain.role.Role;
import com.greboreda.portal.business.user.domain.role.RoleId;
import com.greboreda.portal.business.user.persistence.dbo.RoleDBO;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class RoleMapper {

	public static Role map(RoleDBO roleDBO) {
		return Role.create()
				.withId(RoleId.fromUUID(roleDBO.getId()))
				.withName(roleDBO.getName())
				.build();
	}

	public static Set<Role> map(Set<RoleDBO> roleDBOs) {
		return roleDBOs.stream()
				.map(RoleMapper::map)
				.collect(toSet());
	}

	public static RoleDBO mapToDBO(Role role) {
		final RoleDBO roleDBO = new RoleDBO();
		roleDBO.setId(role.getId().getUuid());
		roleDBO.setName(role.getName());
		return roleDBO;
	}

	public static Set<RoleDBO> mapToDBOs(Set<Role> roles) {
		return roles.stream()
				.map(RoleMapper::mapToDBO)
				.collect(toSet());
	}
}
