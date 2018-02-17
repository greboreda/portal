package com.greboreda.portal.business.login.business;

import com.greboreda.portal.business.login.domain.LoginService;
import com.greboreda.portal.business.login.domain.LoginServiceId;
import com.greboreda.portal.business.login.persistence.dao.LoginServiceDAO;
import com.greboreda.portal.business.login.persistence.dbo.LoginServiceDBO;
import com.greboreda.portal.business.login.persistence.mapper.LoginServiceMapper;
import com.greboreda.portal.business.vo.EmailAddress;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import java.util.Optional;

@Named
class LoginServiceBDAO {

	private final LoginServiceDAO loginServiceDAO;

	@Inject
	LoginServiceBDAO(LoginServiceDAO loginServiceDAO) {
		this.loginServiceDAO = loginServiceDAO;
	}

	Optional<LoginService> findBy(LoginServiceId loginServiceId) {
		final Optional<LoginServiceDBO> maybeDBO = loginServiceDAO.findById(loginServiceId.getUuid());
		return maybeDBO.map(LoginServiceMapper::map);
	}

	Optional<LoginService> findBy(EmailAddress emailAddress) {
		final Optional<LoginServiceDBO> maybeDBO = loginServiceDAO.findByEmailAddress(emailAddress.getValue());
		return maybeDBO.map(LoginServiceMapper::map);
	}

	void save(LoginService loginService) {
		final LoginServiceDBO loginServiceDBO = LoginServiceMapper.mapToDBO(loginService);
		loginServiceDAO.save(loginServiceDBO);
	}

}
