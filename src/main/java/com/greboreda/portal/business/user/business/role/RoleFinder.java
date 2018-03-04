package com.greboreda.portal.business.user.business.role;

import com.greboreda.portal.business.user.domain.role.Role;
import com.greboreda.portal.business.user.domain.role.Role.RoleType;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class RoleFinder {

	private final RoleBDAO roleBDAO;

	@Inject
	public RoleFinder(RoleBDAO roleBDAO) {
		this.roleBDAO = roleBDAO;
	}

	public Role findRoleBy(RoleType roleType){
		Validate.notNull(roleType);
		return roleBDAO.findRoleBy(roleType).orElseThrow(IllegalStateException::new);
	}
}
