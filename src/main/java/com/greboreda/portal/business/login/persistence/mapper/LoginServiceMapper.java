package com.greboreda.portal.business.login.persistence.mapper;

import com.greboreda.portal.business.login.domain.LoginService;
import com.greboreda.portal.business.login.domain.LoginServiceId;
import com.greboreda.portal.business.login.persistence.dbo.LoginServiceDBO;
import com.greboreda.portal.business.user.persistence.mapper.UserMapper;
import com.greboreda.portal.business.vo.EmailAddress;
import com.greboreda.portal.business.vo.Password;

import java.util.UUID;

public class LoginServiceMapper {

	public static LoginService map(LoginServiceDBO loginServiceDBO) {
		return LoginService.create()
				.withId(LoginServiceId.fromUUID(UUID.fromString(loginServiceDBO.getId())))
				.withUser(UserMapper.map(loginServiceDBO.getUser()))
				.withCreationDate(loginServiceDBO.getCreationDate())
				.withModificationDate(loginServiceDBO.getModificationDate())
				.withEmailAddress(new EmailAddress(loginServiceDBO.getEmailAddress()))
				.withPassword(new Password(loginServiceDBO.getPassword()))
				.build();
	}

	public static LoginServiceDBO mapToDBO(LoginService loginService) {
		final LoginServiceDBO loginServiceDBO = new LoginServiceDBO();
		loginServiceDBO.setId(loginService.getId().getUuid().toString());
		loginServiceDBO.setCreationDate(loginService.getCreationDate());
		loginServiceDBO.setEmailAddress(loginService.getEmailAddress().getValue());
		loginServiceDBO.setModificationDate(loginService.getModificationDate());
		loginServiceDBO.setUser(UserMapper.map(loginService.getUser()));
		loginServiceDBO.setPassword(loginService.getPassword().getValue());
		return loginServiceDBO;
	}


}
