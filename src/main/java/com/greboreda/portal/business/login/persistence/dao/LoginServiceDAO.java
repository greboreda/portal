package com.greboreda.portal.business.login.persistence.dao;

import com.greboreda.portal.business.login.persistence.dbo.LoginServiceDBO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LoginServiceDAO extends CrudRepository<LoginServiceDBO, UUID> {

	@Query(" select l from LoginServiceDBO as l where l.emailAddress = :emailAddress ")
	Optional<LoginServiceDBO> findByEmailAddress(@Param("emailAddress") String emailAddress);

	@Query(" select l from LoginServiceDBO  as l where l.user.id = :userId ")
	Optional<LoginServiceDBO> findByUserId(@Param("userId") String userId);
}
