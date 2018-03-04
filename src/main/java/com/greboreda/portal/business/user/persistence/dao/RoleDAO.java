package com.greboreda.portal.business.user.persistence.dao;

import com.greboreda.portal.business.user.persistence.dbo.RoleDBO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleDAO extends CrudRepository<RoleDBO, UUID> {

}
