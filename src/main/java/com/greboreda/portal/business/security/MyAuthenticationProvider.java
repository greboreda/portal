package com.greboreda.portal.business.security;

import com.greboreda.portal.business.login.business.LoginPerformer;
import com.greboreda.portal.business.login.domain.LoginService;
import com.greboreda.portal.business.vo.EmailAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
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
			throw new UsernameNotFoundException(userName);
		}

		final Optional<LoginService> maybeLoginService = loginPerformer.doLogin(new EmailAddress(userName), password);
		if(!maybeLoginService.isPresent()) {
			final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, authentication.getCredentials());
			token.setAuthenticated(false);
			return token;
		}

		final List<GrantedAuthority> grantedAuthorities = maybeLoginService.get().getUser().getRoles().stream()
				.map(r -> new SimpleGrantedAuthority(r.getName()))
				.collect(toList());

		final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, authentication.getCredentials(), grantedAuthorities);
		//token.setAuthenticated(true);

		return token;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
