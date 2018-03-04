package com.greboreda.portal.business.user.business.role;

import com.greboreda.portal.business.user.domain.role.Role;
import com.greboreda.portal.business.user.domain.role.RoleType;
import org.apache.commons.lang3.Validate;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Component
public class Roles {

	private static final List<Role> roles = new ArrayList<>();

	private final RoleBDAO roleBDAO;

	@Inject
	public Roles(RoleBDAO roleBDAO) {
		this.roleBDAO = roleBDAO;
	}

	@EventListener(ApplicationReadyEvent.class)
	public void loadRoles() {
		roles.addAll(roleBDAO.findAll());
	}

	public static Role get(RoleType roleType) {
		Validate.notNull(roleType);
		return roles.stream()
				.filter(r -> r.getType().equals(roleType))
				.findFirst()
				.orElseThrow(IllegalStateException::new);
	}

	public static Set<Role> get(Set<RoleType> roleTypes) {
		Validate.notNull(roleTypes);
		return roleTypes.stream()
				.map(Roles::get)
				.collect(toSet());
	}
}
