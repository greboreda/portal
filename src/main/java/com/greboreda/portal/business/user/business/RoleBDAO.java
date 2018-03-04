package com.greboreda.portal.business.user.business;

import com.greboreda.portal.business.user.domain.role.Role;
import com.greboreda.portal.business.user.persistence.dao.RoleDAO;
import com.greboreda.portal.business.user.persistence.mapper.RoleMapper;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Named
class RoleBDAO {

	private final RoleDAO roleDAO;

	@Inject
	RoleBDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	Optional<Role> findRoleByName(String name) {
		return roleDAO.findRoleByName(name).map(RoleMapper::map);
	}
}
