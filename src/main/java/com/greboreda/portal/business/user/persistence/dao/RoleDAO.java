package com.greboreda.portal.business.user.persistence.dao;

import com.greboreda.portal.business.user.persistence.dbo.RoleDBO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleDAO extends CrudRepository<RoleDBO, UUID> {

	@Query(" select r from RoleDBO as r where r.name = :name ")
	Optional<RoleDBO> findRoleByName(@Param("name") String name);

}
