package com.greboreda.portal.config;

import com.greboreda.portal.business.security.MyAuthenticationProvider;
import com.greboreda.portal.business.security.UserSpringSecurityServiceWrapper;
import com.greboreda.portal.business.user.domain.role.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserSpringSecurityServiceWrapper userSpringSecurityServiceWrapper;
	@Autowired
	private MyAuthenticationProvider myAuthenticationProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userSpringSecurityServiceWrapper);
		auth.authenticationProvider(myAuthenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				.authorizeRequests()
					.antMatchers( "/home").hasAuthority(RoleType.USER.getName())
					.antMatchers("/admin/**", "/user/**").hasAuthority(RoleType.ADMIN.getName())
					.anyRequest().authenticated()
					.and()
				.formLogin()
					.loginPage("/login")
					.permitAll()
					.defaultSuccessUrl("/home")
					.and()
				.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl("/login")
					.permitAll()
					.and()
				.exceptionHandling()
					.accessDeniedPage("/login");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
				.ignoring()
				.antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

}
