package com.greboreda.portal.business.user.business;

import com.greboreda.portal.business.user.domain.role.Role;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Named
public class RoleFinder {

	private final RoleBDAO roleBDAO;

	@Inject
	public RoleFinder(RoleBDAO roleBDAO) {
		this.roleBDAO = roleBDAO;
	}

	public Optional<Role> findRoleByName(String name){
		Validate.notNull(name);
		return roleBDAO.findRoleByName(name);
	}
}
