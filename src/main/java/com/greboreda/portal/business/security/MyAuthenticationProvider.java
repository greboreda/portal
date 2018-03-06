package com.greboreda.portal.business.security;

import com.greboreda.portal.business.login.business.LoginPerformer;
import com.greboreda.portal.business.login.domain.LoginService;
import com.greboreda.portal.business.vo.EmailAddress;
import com.greboreda.portal.business.vo.PlainPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private LoginPerformer loginPerformer;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		final String userName = authentication.getName();
		final String password = authentication.getCredentials().toString();

		if(!EmailAddress.isValidEmailAddress(userName)) {
			throw new BadCredentialsException("not valid credentials");
		}
		if(!PlainPassword.isValidPlainPassword(password)) {
			throw new BadCredentialsException("not valid credentials");
		}

		final EmailAddress emailAddress = new EmailAddress(userName);
		final PlainPassword plainPassword = new PlainPassword(password);
		final Optional<LoginService> maybeLoginService = loginPerformer.doLogin(emailAddress, plainPassword);
		if(!maybeLoginService.isPresent()) {
			throw new BadCredentialsException("not valid credentials");
		}

		final List<GrantedAuthority> grantedAuthorities = maybeLoginService.get().getUser().getRoles().stream()
				.map(r -> new SimpleGrantedAuthority(r.getType().getName()))
				.collect(toList());

		return new UsernamePasswordAuthenticationToken(userName, authentication.getCredentials(), grantedAuthorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
