package com.greboreda.portal.business.user.persistence.mapper;

import com.greboreda.portal.business.user.domain.role.Role;
import com.greboreda.portal.business.user.domain.role.Role.RoleType;
import com.greboreda.portal.business.user.domain.role.RoleId;
import com.greboreda.portal.business.user.persistence.dbo.RoleDBO;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class RoleMapper {

	public static Role map(RoleDBO roleDBO) {
		final UUID uuid = UUID.fromString(roleDBO.getId());
		return Role.create()
				.withId(RoleId.fromUUID(uuid))
				.withType(RoleType.mapByName(roleDBO.getName()))
				.build();
	}

	public static Set<Role> map(List<RoleDBO> roleDBOs) {
		return roleDBOs.stream()
				.map(RoleMapper::map)
				.collect(toSet());
	}

	public static RoleDBO mapToDBO(Role role) {
		final RoleDBO roleDBO = new RoleDBO();
		roleDBO.setId(role.getId().getUuid().toString());
		roleDBO.setName(role.getType().getName());
		return roleDBO;
	}

	public static List<RoleDBO> mapToDBOs(Set<Role> roles) {
		return roles.stream()
				.map(RoleMapper::mapToDBO)
				.collect(toList());
	}
}
