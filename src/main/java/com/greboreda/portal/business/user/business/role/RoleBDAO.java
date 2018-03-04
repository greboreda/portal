package com.greboreda.portal.business.user.business.role;

import com.greboreda.portal.business.user.domain.role.Role;
import com.greboreda.portal.business.user.persistence.dao.RoleDAO;
import com.greboreda.portal.business.user.persistence.dbo.RoleDBO;
import com.greboreda.portal.business.user.persistence.mapper.RoleMapper;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Named
class RoleBDAO {

	private final RoleDAO roleDAO;

	@Inject
	RoleBDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	List<Role> findAll() {
		final Iterable<RoleDBO> roles = roleDAO.findAll();
		return StreamSupport.stream(roles.spliterator(), false)
				.map(RoleMapper::map)
				.collect(toList());
	}
}
